package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHREmployeeSalaryDetails extends X_GS_HR_EmployeeSalaryDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHREmployeeSalaryDetails(Properties ctx, int GS_HR_EmployeeSalaryDetails_ID, String trxName) {
		super(ctx, GS_HR_EmployeeSalaryDetails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHREmployeeSalaryDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHREmployeeSalaryDetails(MGSHREmployeeMonthlySalary monthSal) 
	{
		this (monthSal.getCtx(), 0, monthSal.get_TrxName());
		if (monthSal.get_ID() == 0)
			throw new IllegalArgumentException("Header not saved");
		setGS_HR_EmployeeMonthlySalary_ID(monthSal.getGS_HR_EmployeeMonthlySalary_ID());	//	parent
		setClientOrg(monthSal);
	}

}
