package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSDays extends X_DS_Days {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSDays(Properties ctx, int DS_Days_ID, String trxName) {
		super(ctx, DS_Days_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSDays(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
