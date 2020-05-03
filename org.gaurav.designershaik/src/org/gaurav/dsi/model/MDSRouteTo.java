package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSRouteTo extends X_DS_RouteTo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9051452564497975868L;
	public MDSRouteTo(Properties ctx, int DS_RouteTo_ID, String trxName) {
		super(ctx, DS_RouteTo_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSRouteTo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
