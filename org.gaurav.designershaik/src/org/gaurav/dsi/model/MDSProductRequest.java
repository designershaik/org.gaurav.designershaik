package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSProductRequest extends X_DS_Product_Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = 616339998365516916L;

	public MDSProductRequest(Properties ctx, int DS_Product_Request_ID,
			String trxName) {
		super(ctx, DS_Product_Request_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSProductRequest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
