package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;

public class MGSHRAttendanceT extends X_GS_HR_Attendance_T {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRAttendanceT(Properties ctx, int GS_HR_Attendance_T_ID,
			String trxName) {
		super(ctx, GS_HR_Attendance_T_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRAttendanceT(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public void setLog(MGSHRDailyAttendanceLog log) 
	{
		setGS_HR_Employee_ID(log.getGS_HR_Employee_ID());
		setGS_Day(log.getGS_Day());
		setGS_Month(log.getGS_Month());
		setGS_Year(log.getGS_Year());
		setGS_PunchTime(log.getGS_PunchTime());
		setDS_In1(log.getGS_PunchTime());
		setGS_DayOfTheWeek(log.getGS_DayOfTheWeek());
		if(!log.getGS_TriggerType().equals("RJ"))
			setGS_AMPM(log.getGS_AMPM());
		setHR_Department_ID(log.getGS_HR_Employee().getHR_Department_ID());
	}

	public void SetTime(Timestamp punchTime, String triggerType) 
	{
		if(triggerType.equalsIgnoreCase("In"))
		{
			if(getDS_In1()==null)
				setDS_In1(punchTime);
			else if(getDS_In2()==null)
				setDS_In2(punchTime);
			else if(getDS_In3()==null)
				setDS_In3(punchTime);
			else if(getDS_In4()==null)
				setDS_In4(punchTime);
			else if(getDS_In5()==null)
				setDS_In5(punchTime);
			else if(getDS_In6()==null)
				setDS_In6(punchTime);
			else if(getDS_In7()==null)
				setDS_In7(punchTime);
			else if(getDS_In8()==null)
				setDS_In8(punchTime);
			else if(getDS_In9()==null)
				setDS_In9(punchTime);
			else if(getDS_In10()==null)
				setDS_In10(punchTime);
			else if(getDS_In11()==null)
				setDS_In11(punchTime);
			else if(getDS_In12()==null)
				setDS_In12(punchTime);
		}
		if(triggerType.equalsIgnoreCase("Out"))
		{
			if(getDS_Out1()==null)
				setDS_Out1(punchTime);
			else if(getDS_Out2()==null)
				setDS_Out2(punchTime);
			else if(getDS_Out3()==null)
				setDS_Out3(punchTime);
			else if(getDS_Out4()==null)
				setDS_Out4(punchTime);
			else if(getDS_Out5()==null)
				setDS_Out5(punchTime);
			else if(getDS_Out6()==null)
				setDS_Out6(punchTime);
			else if(getDS_Out7()==null)
				setDS_Out7(punchTime);
			else if(getDS_Out8()==null)
				setDS_Out8(punchTime);
			else if(getDS_Out9()==null)
				setDS_Out9(punchTime);
			else if(getDS_Out10()==null)
				setDS_Out10(punchTime);
			else if(getDS_Out11()==null)
				setDS_Out11(punchTime);
			else if(getDS_Out12()==null)
				setDS_Out12(punchTime);
		}
		
	}

	public boolean verifyIfAlreadyPresent(Timestamp gs_PunchTime, String gs_TriggerType) 
	{
		String sqlWhere = "";
		boolean ifExists = false;
		if(gs_TriggerType.equalsIgnoreCase("In"))
			sqlWhere = " ? in (DS_In1,DS_In2,DS_In3,DS_In4,DS_In5,DS_In6,DS_In7,DS_In8,DS_In9,DS_In10,DS_In11,DS_In12) and GS_HR_Attendance_T_ID = ? ";
		if(gs_TriggerType.equalsIgnoreCase("Out"))
			sqlWhere = " ? in (DS_Out1,DS_Out2,DS_Out3,DS_Out4,DS_Out5,DS_Out6,DS_Out7,DS_Out8,DS_Out9,DS_Out10,DS_Out11,DS_Out12) and GS_HR_Attendance_T_ID = ? ";
		
		int count = DB.getSQLValue(get_TrxName(), "Select count(*) From GS_HR_Attendance_T Where "+sqlWhere,gs_PunchTime,getGS_HR_Attendance_T_ID());
		if(count>0)
			ifExists = true;
		
		return ifExists;
	}

}
