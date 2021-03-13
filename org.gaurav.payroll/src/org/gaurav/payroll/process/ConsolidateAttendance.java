package org.gaurav.payroll.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.gaurav.payroll.model.MGSHRAttendanceDayWise;
import org.gaurav.payroll.model.MGSHRAttendanceDet;
import org.gaurav.payroll.model.MGSHREmployee;
import org.gaurav.payroll.model.MGSHRMonthlyAttendance;
import org.gaurav.payroll.model.MGSHRSalaryMonths;
import org.gaurav.payroll.model.MGSHRTimeSlot;

public class ConsolidateAttendance extends SvrProcess 
{
	MGSHRMonthlyAttendance mAtt = null;
	int monthlyAttendance_ID = 0 ;
	int p_Employee_ID = 0 ;
	Timestamp monthStartDate = null;
	Timestamp monthEndDate = null;
	BigDecimal TotalMonthDays = Env.ZERO ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("GS_HR_Employee_ID"))
				p_Employee_ID = para[i].getParameterAsInt();		
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
		monthlyAttendance_ID = getRecord_ID();
		mAtt = new MGSHRMonthlyAttendance(getCtx(), monthlyAttendance_ID, get_TrxName());
		
		monthStartDate = mAtt.getGS_HR_SalaryMonths().getStartDate();
		monthEndDate = mAtt.getGS_HR_SalaryMonths().getEndDate();
		TotalMonthDays = new BigDecimal(TimeUtil.getDaysBetween(monthStartDate, monthEndDate));
	}

	@Override
	protected String doIt() throws Exception 
	{
		MGSHRAttendanceDet det = null;
		int existingEmployee_ID = 0 ;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		MGSHRSalaryMonths salMonths = new MGSHRSalaryMonths(getCtx(),mAtt.getGS_HR_SalaryMonths_ID(),get_TrxName());
		Timestamp startDate = salMonths.getStartDate();
		Timestamp endDate = salMonths.getEndDate();
		StringBuilder sqlWhere = new StringBuilder(" and lg.AD_Client_ID = "+getAD_Client_ID());
		if(p_Employee_ID>0)
			sqlWhere = sqlWhere.append(" and lg.GS_HR_Employee_ID = ?  ");
		String sql = "select (select max(gs_punchtime) from GS_HR_DailyAttendance_Log " + 
				"where gs_day =lg.gs_day and gs_month =lg.gs_month and gs_year=lg.gs_year and gs_hr_employee_id =lg.gs_hr_employee_id " + 
				"and gs_triggertype ='OUT' group by gs_day ,gs_hr_employee_id) as OutTime," + 
				"(select min(gs_punchtime) from GS_HR_DailyAttendance_Log " + 
				"where gs_day =lg.gs_day and gs_month =lg.gs_month and gs_year=lg.gs_year and gs_hr_employee_id =lg.gs_hr_employee_id " + 
				"and gs_triggertype ='IN' group by gs_day ,gs_hr_employee_id) as InTime,GS_Day ,GS_HR_Employee_ID ,lg.GS_DayOfTheWeek " + 
				"from GS_HR_DailyAttendance_Log lg " + 
				"where lg.gs_punchtime::TIMESTAMP::timestamp between ? and ? "+sqlWhere.toString()+ 
				"group by lg.gs_day ,lg.GS_HR_Employee_ID ,lg.gs_year ,lg.gs_month,lg.GS_DayOfTheWeek "+ 
				"order by GS_HR_Employee_ID,gs_year ,gs_month ,gs_day ";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);
			if(p_Employee_ID>0)
				pstmt.setInt(3, p_Employee_ID);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Timestamp inTime = rs.getTimestamp("InTime");
				Timestamp outTime = rs.getTimestamp("OutTime");
				int employee_ID = rs.getInt("GS_HR_Employee_ID");
				MGSHREmployee emp = new MGSHREmployee(getCtx(), employee_ID, get_TrxName());
				int Day = rs.getInt("GS_Day");
				String daysOfTheWeek = rs.getString("GS_DayOfTheWeek").trim();
				if(!punchedOnHoliday( daysOfTheWeek,employee_ID))
				{
					if(outTime==null && inTime!=null)
						outTime = inTime;
					if(inTime==null && outTime!=null)
						inTime = outTime;
					
					long diff = outTime.getTime() - inTime.getTime();
			        long diffSeconds = diff / 1000 % 60;
			        long diffMinutes = diff / (60 * 1000) % 60;
			        long diffHours = diff / (60 * 60 * 1000) % 24;
			        if(existingEmployee_ID==0 || existingEmployee_ID!=employee_ID)
			        {
			        	existingEmployee_ID = employee_ID;
			        	det = new MGSHRAttendanceDet(getCtx(), 0, get_TrxName());
			        	det.setGS_HR_Employee_ID(existingEmployee_ID);
			        	det.setGS_HR_SalaryMonths_ID(mAtt.getGS_HR_SalaryMonths_ID());
			        	det.setGS_HR_MonthlyAttendance_ID(getRecord_ID());
			        	det.setYear(mAtt.getYear());
			        	det.saveEx();
			        }
			       	MGSHRAttendanceDayWise daywise = new MGSHRAttendanceDayWise(getCtx(), 0, get_TrxName());
					daywise.setDS_In(inTime);
					daywise.setDS_Out(outTime);
					daywise.setGS_Day(Day);
					daywise.setGS_DayOfTheWeek(daysOfTheWeek);
					daywise.setGS_HR_Employee_ID(employee_ID);
					daywise.setGS_HR_MonthlyAttendance_ID(monthlyAttendance_ID);
					daywise.setGS_Hour(diffHours+":"+diffMinutes+":"+diffSeconds);
					daywise.setGS_HR_Attendance_Det_ID(det.getGS_HR_Attendance_Det_ID());
					if(!emp.isGS_HR_ExemptFromLateDeduction())
						setLateOrEarlyOutDeductions(daywise,employee_ID,inTime,daysOfTheWeek);
					daywise.saveEx();
				}
			}
		}
		catch(Exception e)
		{
			log.severe(e.getLocalizedMessage()+" "+sql);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		updateHolidays();
		return "@OK@";
	}

	private boolean punchedOnHoliday(String daysOfTheWeek,int employee_ID) 
	{
		boolean isNonHoliday = false;
		int[] weekdendSlot_ID = DB.getIDsEx(get_TrxName(), "select slt.gs_hr_timeslot_id from gs_hr_employee emp,GS_HR_TimeSlot slt "
				+ "where emp.gs_hr_employee_id = ? and emp.gs_hr_timeslot_group_id = slt.gs_hr_timeslot_group_id  "
				+ "and slt.gs_hr_weeklyoff ='Y'"
				+ "and slt.GS_DayOfTheWeek like ? ",employee_ID,daysOfTheWeek);
		if(weekdendSlot_ID.length>0)
			isNonHoliday = true;
		
		return isNonHoliday;
	}

	private void updateHolidays() 
	{
		List<MGSHRAttendanceDet> details = new Query(getCtx(), MGSHRAttendanceDet.Table_Name, " GS_HR_MonthlyAttendance_ID= ? ", get_TrxName())
											.setParameters(monthlyAttendance_ID)
											.list();
		for(MGSHRAttendanceDet det:details)
		{
			int totalHolidays = DB.getSQLValue(get_TrxName(), "select count(*) from C_NonBusinessDay nb where nb.date1 between ? and ? ",monthStartDate,monthEndDate);
			int weekDays = 0 ;
			int[] weekdendSlot_ID = DB.getIDsEx(get_TrxName(), "select slt.gs_hr_timeslot_id from gs_hr_employee emp,GS_HR_TimeSlot slt "
															+ "where emp.gs_hr_employee_id = ? and emp.gs_hr_timeslot_group_id = slt.gs_hr_timeslot_group_id  "
															+ "and slt.gs_hr_weeklyoff ='Y'",det.getGS_HR_Employee_ID());
			for(int slot_ID: weekdendSlot_ID)
			{
				MGSHRTimeSlot slot = new MGSHRTimeSlot(getCtx(), slot_ID, get_TrxName());
				weekDays = getWorkingDaysBetweenTwoDates(slot.getWeekDay(), weekDays);
			}
			BigDecimal totalPresentDays = det.getGS_HR_PresentDays().add(new BigDecimal((weekDays)));
			BigDecimal holidays = new BigDecimal(totalHolidays);
			BigDecimal remainingDaysWhichAreConsideredAsAbsent =TotalMonthDays.subtract(totalPresentDays).subtract(new BigDecimal(totalHolidays));
			det.setGS_HR_PresentDays(totalPresentDays);
			det.setGS_HR_Holidays(holidays);
			det.setGS_HR_AbsentDays(remainingDaysWhichAreConsideredAsAbsent.compareTo(Env.ZERO)<=0?Env.ZERO:remainingDaysWhichAreConsideredAsAbsent);
			det.saveEx();
		}
	}
	
	public int getWorkingDaysBetweenTwoDates(int weekDay,int weekendDays) 
	{
		int DaysBetween = TimeUtil.getDaysBetween(monthStartDate, monthEndDate);
		int totalDays = DB.getSQLValue(get_TrxName(),"select coalesce(count(1),0) "
				+ "from (select ?::date + s*'1day'::interval as datum "
				+ "from generate_series(0,?) s)foo where extract(dow from datum)=? ",monthStartDate,DaysBetween,weekDay-1);

	    return weekendDays+totalDays;
	}

	private void setLateOrEarlyOutDeductions(MGSHRAttendanceDayWise daywise, int employee_ID, Timestamp inTime, String daysOfTheWeek) 
	{
		BigDecimal deduction = DB.getSQLValueBD(get_TrxName(), "select coalesce(gs_hr_deductionbyhour,0) "
				+ "from GS_HR_DeductionOnLate where ? between cast (ds_in::timestamp as time) "
				+ "and cast (ds_out::timestamp as time) and AD_Client_ID = ? ", inTime,getAD_Client_ID());
		deduction = deduction==null ? Env.ZERO:deduction;
		BigDecimal salaryPaidOn = Env.ONE.subtract(deduction);
		daywise.setGS_HR_SalaryPaidOn(salaryPaidOn);
		daywise.setGS_HR_TotalDeduction(deduction);
		
		String sqlDeduction = "select cast (?::timestamp as time)-cast (slt.ds_in+(slt.gs_hr_graceperiod  * interval '1 minute') as time) as LateInString,"
				+ "round((EXTRACT(EPOCH from cast (?::timestamp as time)-cast (slt.ds_in+(slt.gs_hr_graceperiod  * interval '1 minute') as time))/60)::numeric,2) as LateInMinutes " + 
				"from gs_hr_employee emp,GS_HR_TimeSlot slt " + 
				"where emp.gs_hr_employee_id = ? " + 
				"and emp.gs_hr_timeslot_group_id = slt.gs_hr_timeslot_group_id  " + 
				"and slt.gs_dayoftheweek = ? "+ 
				"and slt.AD_Client_ID = ? ";	
		PreparedStatement pstmtDed = null;
		ResultSet rsDed = null;
		try
		{
			pstmtDed = DB.prepareStatement(sqlDeduction, get_TrxName());
			pstmtDed.setTimestamp(1, inTime);
			pstmtDed.setTimestamp(2, inTime);
			pstmtDed.setInt(3, employee_ID);
			pstmtDed.setString(4,daysOfTheWeek);
			pstmtDed.setInt(5, getAD_Client_ID());
			rsDed = pstmtDed.executeQuery();
			while(rsDed.next())
			{
				BigDecimal LateTime = rsDed.getBigDecimal("LateInMinutes");
				String lateInString = rsDed.getString("LateInString");
				if(LateTime.compareTo(Env.ZERO)>0)
				{
					daywise.setGS_HR_LateIn(LateTime);
					daywise.setGS_HR_LateIn(LateTime);
					daywise.setDS_LateBy(lateInString);
				}
			}
			
		}
		catch(Exception sqlException)
		{
			log.severe(sqlException.getLocalizedMessage()+" running query "+sqlDeduction);
		}
		finally
		{
			DB.close(rsDed, pstmtDed);
		}
	}

}
