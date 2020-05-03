package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSSettledInvoices extends X_DS_Settled_Invoices {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSSettledInvoices(Properties ctx, int DS_Settled_Invoices_ID, String trxName) {
		super(ctx, DS_Settled_Invoices_ID, trxName);
		
	}

	public MDSSettledInvoices(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
