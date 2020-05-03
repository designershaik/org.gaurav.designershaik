package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSImportInvoicesFiling extends X_DS_ImportInvoices_Filing {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSImportInvoicesFiling(Properties ctx, int DS_ImportInvoices_Filing_ID, String trxName) {
		super(ctx, DS_ImportInvoices_Filing_ID, trxName);
		
	}

	public MDSImportInvoicesFiling(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
