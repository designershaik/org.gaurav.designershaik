package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSIAging extends X_DSI_Aging {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSIAging(Properties ctx, int DSI_Aging_ID, String trxName) {
		super(ctx, DSI_Aging_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSIAging(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
