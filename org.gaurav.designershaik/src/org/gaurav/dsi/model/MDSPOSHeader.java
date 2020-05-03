package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSPOSHeader extends X_DS_POSHeader {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSPOSHeader(Properties ctx, int DS_POSHeader_ID, String trxName) {
		super(ctx, DS_POSHeader_ID, trxName);
		
	}

	public MDSPOSHeader(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
