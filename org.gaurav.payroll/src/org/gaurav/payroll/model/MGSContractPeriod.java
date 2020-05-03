package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSContractPeriod extends X_GS_ContractPeriod {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8025906556217210644L;

	public MGSContractPeriod(Properties ctx, int GS_ContractPeriod_ID, String trxName) {
		super(ctx, GS_ContractPeriod_ID, trxName);
		
	}

	public MGSContractPeriod(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
