package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRInstallments extends X_GS_HR_Installments {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRInstallments(Properties ctx, int GS_HR_Installments_ID, String trxName) {
		super(ctx, GS_HR_Installments_ID, trxName);
		
	}

	public MGSHRInstallments(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
