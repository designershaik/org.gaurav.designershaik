package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSIManufacturingPlan extends X_DSI_ManufacturingPlan {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSIManufacturingPlan(Properties ctx, int DSI_ManufacturingPlan_ID, String trxName) {
		super(ctx, DSI_ManufacturingPlan_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSIManufacturingPlan(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public int getM_WarehouseID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
