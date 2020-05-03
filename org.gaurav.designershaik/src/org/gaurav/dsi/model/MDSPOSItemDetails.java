package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSPOSItemDetails extends X_DS_POS_ItemDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSPOSItemDetails(Properties ctx, int DS_POS_ItemDetails_ID, String trxName) {
		super(ctx, DS_POS_ItemDetails_ID, trxName);
		
	}

	public MDSPOSItemDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
