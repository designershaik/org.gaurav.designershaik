package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSFixedDeposit extends X_DS_FixedDeposit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSFixedDeposit(Properties ctx, int DS_FixedDeposit_ID,
			String trxName) {
		super(ctx, DS_FixedDeposit_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSFixedDeposit(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
