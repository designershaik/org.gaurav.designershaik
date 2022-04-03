package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSISalesForecast extends X_DSI_SalesForecast {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1550639318372206023L;

	public MDSISalesForecast(Properties ctx, int DSI_SalesForecast_ID, String trxName) {
		super(ctx, DSI_SalesForecast_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSISalesForecast(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
