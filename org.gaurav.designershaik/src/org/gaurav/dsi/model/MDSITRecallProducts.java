package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSITRecallProducts extends X_DSI_T_RecallProducts {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSITRecallProducts(Properties ctx, int DSI_T_RecallProducts_ID, String trxName) {
		super(ctx, DSI_T_RecallProducts_ID, trxName);
		
	}

	public MDSITRecallProducts(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
