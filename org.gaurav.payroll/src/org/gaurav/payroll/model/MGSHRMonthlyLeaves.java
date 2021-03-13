package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.TimeUtil;

public class MGSHRMonthlyLeaves extends X_GS_HR_MonthlyLeaves {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRMonthlyLeaves(Properties ctx, int GS_HR_MonthlyLeaves_ID, String trxName) {
		super(ctx, GS_HR_MonthlyLeaves_ID, trxName);
		
	}

	public MGSHRMonthlyLeaves(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		
		MGSHRMonthlyAttendance att = new MGSHRMonthlyAttendance(getCtx(), getGS_HR_MonthlyAttendance_ID(), get_TrxName());
		
		Timestamp monthStartDate = att.getGS_HR_SalaryMonths().getStartDate();
		Timestamp monthEndDate = att.getGS_HR_SalaryMonths().getEndDate();
		BigDecimal TotalMonthDays = new BigDecimal(TimeUtil.getDaysBetween(monthStartDate, monthEndDate));
		
		MGSHRAttendanceDet det = new MGSHRAttendanceDet(getCtx(), getGS_HR_Attendance_Det_ID(), get_TrxName());
		BigDecimal totalDays =  det.getGS_HR_AbsentDays().add(det.getGS_HR_PresentDays()).add(det.getGS_HR_Holidays()).add(getGS_HR_LeavesConsumed());
		if(totalDays.compareTo(TotalMonthDays)>0)
			throw new AdempiereException("Total Days In The Month "+TotalMonthDays+" < "+totalDays);
		
		return true;
	}
}
