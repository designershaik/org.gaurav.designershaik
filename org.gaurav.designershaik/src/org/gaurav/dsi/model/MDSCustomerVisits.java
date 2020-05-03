package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSCustomerVisits extends X_DS_CustomerVisits {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSCustomerVisits(Properties ctx, int DS_CustomerVisits_ID,
			String trxName) {
		super(ctx, DS_CustomerVisits_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSCustomerVisits(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
