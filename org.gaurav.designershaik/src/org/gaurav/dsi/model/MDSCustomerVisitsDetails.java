package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSCustomerVisitsDetails extends X_DS_CustomerVisits_Details {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSCustomerVisitsDetails(Properties ctx,
			int DS_CustomerVisits_Details_ID, String trxName) {
		super(ctx, DS_CustomerVisits_Details_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSCustomerVisitsDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
