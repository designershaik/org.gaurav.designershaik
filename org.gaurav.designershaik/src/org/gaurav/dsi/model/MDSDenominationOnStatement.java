package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSDenominationOnStatement extends X_DS_DenominationOnStatement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSDenominationOnStatement(Properties ctx, int DS_DenominationOnStatement_ID, String trxName) {
		super(ctx, DS_DenominationOnStatement_ID, trxName);
		
	}

	public MDSDenominationOnStatement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
