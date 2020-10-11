package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSCashPositionSetup extends X_DS_CashPosition_Setup {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSCashPositionSetup(Properties ctx, int DS_CashPosition_Setup_ID, String trxName) {
		super(ctx, DS_CashPosition_Setup_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSCashPositionSetup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
