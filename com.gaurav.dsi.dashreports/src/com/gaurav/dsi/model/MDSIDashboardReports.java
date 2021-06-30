package com.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

import com.gaurav.dsi.model.X_DSI_DashboardReports;

public class MDSIDashboardReports extends X_DSI_DashboardReports {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSIDashboardReports(Properties ctx, int DSI_DashboardReports_ID, String trxName) {
		super(ctx, DSI_DashboardReports_ID, trxName);
		
	}

	public MDSIDashboardReports(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
