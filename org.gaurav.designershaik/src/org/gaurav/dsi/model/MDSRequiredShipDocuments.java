package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSRequiredShipDocuments extends X_DS_RequiredShipDocuments {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSRequiredShipDocuments(Properties ctx,
			int DS_RequiredShipDocuments_ID, String trxName) {
		super(ctx, DS_RequiredShipDocuments_ID, trxName);
		
	}

	public MDSRequiredShipDocuments(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
