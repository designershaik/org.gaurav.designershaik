package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
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
			if(is_ValueChanged(COLUMNNAME_GS_HR_SalaryPaidOn))
			{
				BigDecimal oldValue = (BigDecimal) get_ValueOld("GS_HR_SalaryPaidOn");
				BigDecimal salariesPaidOn = getGS_HR_SalaryPaidOn();
				BigDecimal difference = oldValue.subtract(salariesPaidOn);
				System.out.println(difference);
				System.out.println(det.getGS_HR_PresentDays().subtract(difference));
				det.setGS_HR_PresentDays(det.getGS_HR_PresentDays().subtract(difference));
				det.setGS_HR_AbsentDays(det.getGS_HR_AbsentDays().add(difference));
				det.saveEx();
			}
		}
		det.saveEx();
		return true;
	}
}
