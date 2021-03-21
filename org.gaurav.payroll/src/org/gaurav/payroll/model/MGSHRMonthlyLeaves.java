package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

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
}
