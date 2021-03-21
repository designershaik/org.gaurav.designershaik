package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRMonthlySalary extends X_GS_HR_MonthlySalary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRMonthlySalary(Properties ctx, int GS_HR_MonthlySalary_ID, String trxName) {
		super(ctx, GS_HR_MonthlySalary_ID, trxName);
		
	}

	public MGSHRMonthlySalary(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
