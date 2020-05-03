package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSAramexShipmentTracking extends X_DS_Aramex_ShipmentTracking {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSAramexShipmentTracking(Properties ctx,
			int DS_Aramex_ShipmentTracking_ID, String trxName) {
		super(ctx, DS_Aramex_ShipmentTracking_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSAramexShipmentTracking(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
