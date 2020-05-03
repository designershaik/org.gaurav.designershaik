package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSB2BConfiguration extends X_DS_B2B_Configuration {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSB2BConfiguration(Properties ctx, int DS_B2B_Configuration_ID,
			String trxName) {
		super(ctx, DS_B2B_Configuration_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSB2BConfiguration(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
