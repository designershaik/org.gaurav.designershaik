package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSContactRelation extends X_DS_ContactRelation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8980580918775102042L;

	public MDSContactRelation(Properties ctx, int DS_ContactRelation_ID,
			String trxName) {
		super(ctx, DS_ContactRelation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSContactRelation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
