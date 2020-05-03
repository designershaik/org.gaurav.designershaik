package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRCompCalc extends X_GS_HR_Comp_Calc{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8347011604378671229L;

	public MGSHRCompCalc(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	public MGSHRCompCalc(Properties ctx, int GS_HR_Comp_Calc_ID, String trxName) {
		super(ctx, GS_HR_Comp_Calc_ID, trxName);
		
	}

	
}
