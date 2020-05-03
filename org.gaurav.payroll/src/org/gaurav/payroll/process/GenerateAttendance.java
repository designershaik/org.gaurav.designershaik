package org.gaurav.payroll.process;

import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.payroll.model.MGSHRAttendanceT;
import org.gaurav.payroll.model.MGSHRDailyAttendanceLog;

public class GenerateAttendance extends SvrProcess{

	int p_HR_Department_ID = 0	; 
	int p_HR_Employee_ID = 0	;
	Timestamp p_StartDate  = null ;
	Timestamp p_EndDate = null ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("HR_Department_ID"))
				p_HR_Department_ID = para[i].getParameterAsInt();
			else if (name.equals("GS_HR_Employee_ID"))
				p_HR_Employee_ID = para[i].getParameterAsInt();
			else if (name.equalsIgnoreCase("StartDate"))
				p_StartDate = (Timestamp)para[i].getParameter();
			else if (name.equalsIgnoreCase("EndDate"))
			    p_EndDate = (Timestamp)para[i].getParameter();
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception 
	{
		DB.executeUpdate("Delete From GS_HR_Attendance_T ", get_TrxName());
		
		int loggedInUser = Env.getAD_User_ID(getCtx());
		StringBuilder whereClause = new StringBuilder(" GS_PunchTime BETWEEN  ? and ? ");
		
		if(loggedInUser!=100)
		{
			String restriction = " AND GS_HR_Employee_ID in "
					+ "(select acc.GS_HR_Employee_ID from GS_HR_Attendance_Access acc "
					+ "where acc.AD_User_ID= "+loggedInUser+" "
					+ "union all "
					+ "select emp.GS_HR_Employee_ID "
					+ "from GS_HR_Employee emp ,AD_User adu "
					+ "where emp.C_BPartner_ID=adu.C_BPartner_ID "
					+ "and adu.AD_User_ID="+loggedInUser+")";
			if(p_HR_Department_ID!=0)
				whereClause.append(" AND GS_HR_Employee_ID IN (SELECT GS_HR_Employee_ID FROM GS_HR_Employee Where HR_Department_ID= "+p_HR_Department_ID+") ")
								.append(restriction);
			else if(p_HR_Employee_ID!=0)
				whereClause.append(" AND GS_HR_Employee_ID = "+p_HR_Employee_ID+"  ").append(restriction);
			else
				whereClause.append(restriction);
		}
		else
		{
			if(p_HR_Employee_ID!=0)
				whereClause.append(" AND GS_HR_Employee_ID = "+p_HR_Employee_ID);
			if(p_HR_Department_ID!=0)
				whereClause.append(" AND GS_HR_Employee_ID IN (SELECT GS_HR_Employee_ID FROM GS_HR_Employee Where HR_Department_ID= "+p_HR_Department_ID+")");
		}
		List<MGSHRDailyAttendanceLog> logs = new Query(getCtx(), MGSHRDailyAttendanceLog.Table_Name, whereClause.toString(), get_TrxName())
										.setParameters(p_StartDate,p_EndDate)
//										.setOrderBy("GS_HR_Employee_ID , GS_Year,GS_Month,GS_Day,GS_AMPM,GS_Hour,GS_Minutes,GS_Seconds ")
										.setOrderBy("GS_HR_Employee_ID , GS_PunchTime Asc ")
										.list();
		for(MGSHRDailyAttendanceLog log : logs)
		{ 
			int GS_HR_Attendance_T_ID = getAttendanceTempIDIfAlreadyExist(log.getGS_HR_Employee_ID(),log.getGS_Day(),log.getGS_Month(),log.getGS_Year());
			MGSHRAttendanceT t = new MGSHRAttendanceT(getCtx(), GS_HR_Attendance_T_ID, get_TrxName());
			if(!t.verifyIfAlreadyPresent(log.getGS_PunchTime(),log.getGS_TriggerType()))
			{
				t.setStartDate(p_StartDate);
				t.setEndDate(p_EndDate);
				if(GS_HR_Attendance_T_ID==0)
					t.setLog(log);
				else
					t.SetTime(log.getGS_PunchTime(),log.getGS_TriggerType());
		
				t.save();
			}
//			{
//				log.setProcessed(true);
//				log.saveEx();
//			}
		}
		return "@Formatted@";
	}
	
	private int getAttendanceTempIDIfAlreadyExist(int emp_ID, int day, int month, int year) {
		int GS_HR_Attendance_T_ID = DB.getSQLValue(get_TrxName(), "SELECT GS_HR_Attendance_T_ID FROM  GS_HR_Attendance_T "
				+ "where GS_HR_Employee_ID = ? and GS_Day =? and GS_Month = ? and GS_Year = ? ",emp_ID,day,month,year);
		return GS_HR_Attendance_T_ID==-1 ? 0 : GS_HR_Attendance_T_ID;
	}
}
