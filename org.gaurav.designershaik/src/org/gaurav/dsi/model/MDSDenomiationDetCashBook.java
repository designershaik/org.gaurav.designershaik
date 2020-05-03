package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSDenomiationDetCashBook extends X_DS_DenomiationDet_CashBook {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSDenomiationDetCashBook(Properties ctx, int DS_DenomiationDet_CashBook_ID, String trxName) {
		super(ctx, DS_DenomiationDet_CashBook_ID, trxName);
	}

	public MDSDenomiationDetCashBook(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

}
