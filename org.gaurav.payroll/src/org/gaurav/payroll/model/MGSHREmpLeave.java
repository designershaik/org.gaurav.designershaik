package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHREmpLeave extends X_GS_HR_Emp_Leave {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1858769804904782662L;

	public MGSHREmpLeave(Properties ctx, int GS_HR_Emp_Leave_ID, String trxName) {
		super(ctx, GS_HR_Emp_Leave_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHREmpLeave(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
