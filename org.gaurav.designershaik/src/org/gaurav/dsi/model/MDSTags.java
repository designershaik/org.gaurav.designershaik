package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSTags extends X_DS_Tags {

	public MDSTags(Properties ctx, int DS_Tags_ID, String trxName) {
		super(ctx, DS_Tags_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSTags(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
