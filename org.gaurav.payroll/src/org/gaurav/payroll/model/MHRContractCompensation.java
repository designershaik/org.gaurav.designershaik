package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRContractCompensation extends X_HR_Contract_Compensation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MHRContractCompensation(Properties ctx, int HR_Contract_Compensation_ID, String trxName) {
		super(ctx, HR_Contract_Compensation_ID, trxName);
		
	}

	public MHRContractCompensation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
