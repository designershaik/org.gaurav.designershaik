package org.gaurav.dsi.attendance.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.gaurav.payroll.model.I_GS_HR_Employee;
import org.gaurav.payroll.model.I_GS_HR_TerminalDetails;
import org.gaurav.payroll.model.MGSHRDailyAttendanceLog;
import org.gaurav.payroll.model.MGSHREmployee;
import org.gaurav.payroll.model.MGSHRTerminalDetails;

import com.hectrix.www.ACTAtek_service.ACTAtekLocator;
import com.hectrix.www.ACTAtek_service.ACTAtekPortType;
import com.hectrix.www.ACTAtek_xsd.EventType;
import com.hectrix.www.ACTAtek_xsd.GetLogsCriteria;
import com.hectrix.www.ACTAtek_xsd.Log;
public class AttendanceLogSheet extends SvrProcess
{
	int GS_HR_Employee_ID = 0 ; 
	int p_NoOfDays = 0 ;
	int GS_HR_TerminalDetails_ID = 0 ; 
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("KeepLogDays"))
				p_NoOfDays = para[i].getParameterAsInt();
			else if (name.equals("GS_HR_TerminalDetails_ID"))
				GS_HR_TerminalDetails_ID = para[i].getParameterAsInt();		
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
		GS_HR_Employee_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		System.out.println("Sachin Testing this process....");
		Timestamp ts = null;
		Log log ;
		ACTAtekLocator locator = new ACTAtekLocator();
		Date toDate = TimeUtil.addDays(new Timestamp(TimeUtil.getToday().getTime().getTime()), 1);
		Calendar to = Calendar.getInstance();
		to.setTime(toDate);
		
		if(p_NoOfDays==0)
			ts = TimeUtil.addDays(new Timestamp(to.getTime().getTime()), -3);
		else
			ts = TimeUtil.addDays(new Timestamp(to.getTime().getTime()), p_NoOfDays);
		
		Date fromDate = new Date(ts.getTime());

		Calendar from = Calendar.getInstance();
		from.setTime(fromDate);
		String sqlWhere = "";
		if(GS_HR_TerminalDetails_ID>0)
			sqlWhere = " and GS_HR_TerminalDetails_ID =  "+GS_HR_TerminalDetails_ID;
		List<MGSHRTerminalDetails> terminals = new Query(getCtx(), I_GS_HR_TerminalDetails.Table_Name, " AD_Client_ID = ? "+sqlWhere, 
				get_TrxName()).setParameters(getAD_Client_ID()).setOnlyActiveRecords(true).list();
		for(MGSHRTerminalDetails terminal : terminals)
		{
			List<MGSHREmployee> employees = null;
			ACTAtekPortType api = locator.getACTAtek(new java.net.URL(terminal.getGS_HR_TerminalWSDL()));
			long sessionID = api.login(terminal.getLoginName(), terminal.getPassword());
			GetLogsCriteria criteria = new GetLogsCriteria();
			if(GS_HR_Employee_ID==0)
				employees = new Query(getCtx(), I_GS_HR_Employee.Table_Name, " code is not null and AD_Client_ID = ?  ", 
						get_TrxName()).setParameters(getAD_Client_ID()).setOnlyActiveRecords(true).list();
			else
				employees = new Query(getCtx(), I_GS_HR_Employee.Table_Name, " code is not null and AD_Client_ID = ? and GS_HR_Employee_ID = ?  ", 
						get_TrxName()).setParameters(getAD_Client_ID(),GS_HR_Employee_ID).setOnlyActiveRecords(true).list();
			for(MGSHREmployee empl : employees)
			{
				String empCode = Integer.toString(empl.getCode());
				empCode = ("000" + empl.getCode()).substring(empCode.length());
				criteria.setEmployeeID(empCode);
				criteria.setFrom(from);
				criteria.setTo(to);

				Log[] logs = api.getLogs(sessionID, criteria);
				boolean updated = false;
				if(logs!=null)
				for(int i = 0 ; i <logs.length; i ++)
				{
					log = logs[i];
					String userID = log.getUserID();
					String terminalSN = log.getTerminalSN();
					EventType trigger = log.getTrigger();
					String triggertype = trigger.getValue();
					Calendar punchTime = log.getTimestamp();
					
					int year = punchTime.get(Calendar.YEAR);
					int month = punchTime.get(Calendar.MONTH)+1;
					int day = punchTime.get(Calendar.DAY_OF_MONTH);
					int hour = punchTime.get(Calendar.HOUR)+3;
					int ampm = punchTime.get(Calendar.AM_PM);
					int minute = punchTime.get(Calendar.MINUTE);
					int second = punchTime.get(Calendar.SECOND);
					long logID = log.getLogID();
					java.util.Date punchTimeDate = punchTime.getTime();
					java.sql.Timestamp punchTimeStamp = new java.sql.Timestamp(punchTimeDate.getTime());
					
					DateFormat dayFormate=new SimpleDateFormat("EEEE"); 
					String dayFromDate=dayFormate.format(punchTimeStamp);
					String dayName = dayFromDate;
					int HR_Employee_ID = MGSHREmployee.getEmployeeID(userID,getAD_Client_ID());
					if(HR_Employee_ID==-1)
						new AdempiereException("User ID not exists");
					int GS_HR_DailyAttendance_Log_ID = LogIDExists(year,month,day,hour,ampm,minute,second,HR_Employee_ID);
					
					MGSHRDailyAttendanceLog attLog = new MGSHRDailyAttendanceLog(getCtx(), GS_HR_DailyAttendance_Log_ID, get_TrxName());
					if(GS_HR_DailyAttendance_Log_ID<=0)
					{
						attLog.setCode(Integer.parseInt(userID));
						attLog.setGS_Year(year);
						attLog.setGS_Month(month);
						attLog.setGS_Hour(new BigDecimal(hour));
						attLog.setGS_AMPM(ampm);
						attLog.setGS_Minutes(minute);
						attLog.setGS_Seconds(second);
						attLog.setGS_PunchTime(punchTimeStamp);
						attLog.setGS_HR_Employee_ID(HR_Employee_ID);
						attLog.setGS_Day(day);
						attLog.setGS_DayOfTheWeek(dayName);
						attLog.setGS_LogID((int)logID);
						if(triggertype.equals("REJECTED"))
							triggertype = "RJ";
						attLog.setGS_TriggerType(triggertype);
						attLog.setGS_HR_TerminalSN(terminalSN);
						attLog.setGS_HR_TerminalDetails_ID(terminal.getGS_HR_TerminalDetails_ID());
						attLog.saveEx();
					
						if(!updated)
							updated = true;
					}
					
				}
				if(updated)
					addLog("Logs updated for employee : "+empl.getName()+" Terminal "+terminal.getName());
			}
		}
		
		return "@OK@";
	}

	private int LogIDExists(int year,int month,int day,int hour,int ampm, int minute,int second,int HR_Employee_ID) 
	{
		int GS_HR_DailyAttendance_Log_ID = DB.getSQLValue(get_TrxName(), "select GS_HR_DailyAttendance_Log_ID from GS_HR_DailyAttendance_Log "
				+ "where GS_Year = ? and GS_Month = ? and GS_Day = ? and GS_Hour=? and GS_AMPM =? and GS_Minutes=? and GS_Seconds=? and GS_HR_Employee_ID = ? and AD_Client_ID = ? ",
				year,month,day,hour,ampm,minute,second,HR_Employee_ID,getAD_Client_ID());
			
		return GS_HR_DailyAttendance_Log_ID<0 ? 0:GS_HR_DailyAttendance_Log_ID;
	}
	
}
