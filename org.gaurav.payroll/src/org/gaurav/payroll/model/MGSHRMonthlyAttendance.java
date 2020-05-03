package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRMonthlyAttendance extends X_GS_HR_MonthlyAttendance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRMonthlyAttendance(Properties ctx, int GS_HR_MonthlyAttendance_ID, String trxName) {
		super(ctx, GS_HR_MonthlyAttendance_ID, trxName);
		
	}

	public MGSHRMonthlyAttendance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
