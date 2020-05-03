package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSTaxFilingDet extends X_DS_TaxFiling_Det {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2826886586756575166L;

	public MDSTaxFilingDet(Properties ctx, int DS_TaxFiling_Det_ID, String trxName) {
		super(ctx, DS_TaxFiling_Det_ID, trxName);
		
	}

	public MDSTaxFilingDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
