package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSOutstandingAgingSummary extends X_DS_OutstandingAgingSummary {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3999086107285953058L;

	public MDSOutstandingAgingSummary(Properties ctx,
			int DS_OutstandingAgingSummary_ID, String trxName) {
		super(ctx, DS_OutstandingAgingSummary_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSOutstandingAgingSummary(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
