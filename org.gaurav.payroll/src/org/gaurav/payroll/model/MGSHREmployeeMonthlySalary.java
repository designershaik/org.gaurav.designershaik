package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHREmployeeMonthlySalary extends X_GS_HR_EmployeeMonthlySalary {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHREmployeeMonthlySalary(Properties ctx, int GS_HR_EmployeeMonthlySalary_ID, String trxName) {
		super(ctx, GS_HR_EmployeeMonthlySalary_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHREmployeeMonthlySalary(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
