package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRCompensationMaster extends X_GS_HR_Compensation_Master {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6568059996607465677L;

	public MGSHRCompensationMaster(Properties ctx,
			int GS_HR_Compensation_Master_ID, String trxName) {
		super(ctx, GS_HR_Compensation_Master_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRCompensationMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
