package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSInventoryAging extends X_DS_InventoryAging {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSInventoryAging(Properties ctx, int DS_InventoryAging_ID,
			String trxName) {
		super(ctx, DS_InventoryAging_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSInventoryAging(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
