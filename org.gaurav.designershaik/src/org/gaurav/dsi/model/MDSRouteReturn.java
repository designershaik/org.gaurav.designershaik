package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSRouteReturn extends X_DS_RouteReturn {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4104114532758655185L;

	public MDSRouteReturn(Properties ctx, int DS_RouteReturn_ID, String trxName) {
		super(ctx, DS_RouteReturn_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSRouteReturn(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
