package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRApprovals extends X_GS_HR_Approvals {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5202254889151038104L;

	public MGSHRApprovals(Properties ctx, int GS_HR_Approvals_ID, String trxName) {
		super(ctx, GS_HR_Approvals_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRApprovals(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
