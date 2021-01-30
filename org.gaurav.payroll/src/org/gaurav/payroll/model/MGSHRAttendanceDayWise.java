package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MGSHRAttendanceDayWise extends X_GS_HR_AttendanceDayWise {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRAttendanceDayWise(Properties ctx, int GS_HR_AttendanceDayWise_ID, String trxName) {
		super(ctx, GS_HR_AttendanceDayWise_ID, trxName);
		
	}

	public MGSHRAttendanceDayWise(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		System.out.println(getGS_HR_SalaryPaidOn());
		if(getGS_HR_SalaryPaidOn().compareTo(Env.ONE)>0)
			throw new AdempiereException("Salary Paid On Can't be more than one.");
		
		MGSHRAttendanceDet det = new MGSHRAttendanceDet(getCtx(), getGS_HR_Attendance_Det_ID(), get_TrxName());
		if(newRecord)
		{
			det.setGS_HR_PresentDays(det.getGS_HR_PresentDays().add(getGS_HR_SalaryPaidOn()));
			det.setGS_HR_AbsentDays(det.getGS_HR_AbsentDays().add(getGS_HR_TotalDeduction()));
			
			long diff = getDS_Out().getTime()-getDS_In().getTime();
	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000) % 24;
	        BigDecimal totalMinutes = det.getGS_HR_TotalWorkingMinutes().add(new BigDecimal(diffMinutes));
	        int calculatedHour = totalMinutes.intValue()/60;
	        BigDecimal totalHours = det.getGS_HR_TotalWorkingHours().add(new BigDecimal(calculatedHour)).add(new BigDecimal(diffHours));
	        det.setGS_HR_TotalWorkingHours(det.getGS_HR_TotalWorkingHours().add(new BigDecimal(diffHours)));
			det.setGS_HR_TotalWorkingMinutes(det.getGS_HR_TotalWorkingMinutes().add(new BigDecimal(diffMinutes)));
			det.setGS_HR_ConsolidateWorkingHours(totalHours);
			det.saveEx();
		}
		else
		{
			BigDecimal totalPresentDays = DB.getSQLValueBD(get_TrxName(), "Select coalesce(sum(GS_HR_SalaryPaidOn),0) from GS_HR_AttendanceDayWise Where GS_HR_Attendance_Det_ID = ? ", getGS_HR_Attendance_Det_ID());
			BigDecimal totalAbsentDays = DB.getSQLValueBD(get_TrxName(), "Select coalesce(sum(GS_HR_TotalDeduction),0) from GS_HR_AttendanceDayWise Where GS_HR_Attendance_Det_ID = ? ", getGS_HR_Attendance_Det_ID());
			det.setGS_HR_PresentDays(totalPresentDays);
			det.setGS_HR_AbsentDays(totalAbsentDays);
			det.saveEx();
		}
		det.saveEx();
		return true;
	}
}
