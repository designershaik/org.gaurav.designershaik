package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSTaxFiling extends X_DS_TaxFiling {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5208385905011925030L;

	public MDSTaxFiling(Properties ctx, int DS_TaxFiling_ID, String trxName) {
		super(ctx, DS_TaxFiling_ID, trxName);
		
	}

	public MDSTaxFiling(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
