package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSB2BEmailConf extends X_DS_B2B_EmailConf {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4206693271691298153L;

	public MDSB2BEmailConf(Properties ctx, int DS_B2B_EmailConf_ID,
			String trxName) {
		super(ctx, DS_B2B_EmailConf_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSB2BEmailConf(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
