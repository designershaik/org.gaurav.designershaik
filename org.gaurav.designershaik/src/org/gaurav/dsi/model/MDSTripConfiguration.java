package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSTripConfiguration extends X_DS_Trip_Configuration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSTripConfiguration(Properties ctx, int DS_Trip_Configuration_ID,
			String trxName) {
		super(ctx, DS_Trip_Configuration_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSTripConfiguration(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
