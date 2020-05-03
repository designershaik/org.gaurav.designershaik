package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRContractLeave extends X_HR_Contract_Leave {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MHRContractLeave(Properties ctx, int HR_Contract_Leave_ID, String trxName) {
		super(ctx, HR_Contract_Leave_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRContractLeave(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
