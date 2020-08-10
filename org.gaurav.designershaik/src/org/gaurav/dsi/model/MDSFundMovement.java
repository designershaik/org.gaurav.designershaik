package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSFundMovement extends X_DS_FundMovement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSFundMovement(Properties ctx, int DS_FundMovement_ID, String trxName) {
		super(ctx, DS_FundMovement_ID, trxName);
		
	}

	public MDSFundMovement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
