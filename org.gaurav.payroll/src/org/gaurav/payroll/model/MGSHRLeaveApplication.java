package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRLeaveApplication extends X_GS_HR_LeaveApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRLeaveApplication(Properties ctx, int GS_HR_LeaveApplication_ID, String trxName) {
		super(ctx, GS_HR_LeaveApplication_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRLeaveApplication(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
