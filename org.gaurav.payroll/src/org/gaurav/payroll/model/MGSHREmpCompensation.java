package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHREmpCompensation extends X_GS_HR_EmpCompensation {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1608231218954586521L;

	public MGSHREmpCompensation(Properties ctx, int GS_HR_EmpCompensation_ID,
			String trxName) {
		super(ctx, GS_HR_EmpCompensation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHREmpCompensation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
