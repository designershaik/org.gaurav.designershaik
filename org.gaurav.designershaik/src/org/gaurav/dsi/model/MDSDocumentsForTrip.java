package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSDocumentsForTrip extends X_DS_DocumentsForTrip {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSDocumentsForTrip(Properties ctx, int DS_DocumentsForTrip_ID,
			String trxName) {
		super(ctx, DS_DocumentsForTrip_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSDocumentsForTrip(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
