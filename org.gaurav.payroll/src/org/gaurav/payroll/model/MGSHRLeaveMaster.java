package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRLeaveMaster extends X_GS_HR_Leave_Master {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3882917161295374440L;

	public MGSHRLeaveMaster(Properties ctx, int GS_HR_Leave_Master_ID,
			String trxName) {
		super(ctx, GS_HR_Leave_Master_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRLeaveMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
