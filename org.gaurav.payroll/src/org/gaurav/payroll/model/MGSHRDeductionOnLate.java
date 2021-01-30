package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRDeductionOnLate extends X_GS_HR_DeductionOnLate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRDeductionOnLate(Properties ctx, int GS_HR_DeductionOnLate_ID, String trxName) {
		super(ctx, GS_HR_DeductionOnLate_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRDeductionOnLate(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
