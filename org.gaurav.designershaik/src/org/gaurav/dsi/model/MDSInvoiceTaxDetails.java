package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSInvoiceTaxDetails extends X_DS_Invoice_TaxDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -167465441038196775L;

	public MDSInvoiceTaxDetails(Properties ctx, int DS_Invoice_TaxDetails_ID, String trxName) {
		super(ctx, DS_Invoice_TaxDetails_ID, trxName);
		
	}

	public MDSInvoiceTaxDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
