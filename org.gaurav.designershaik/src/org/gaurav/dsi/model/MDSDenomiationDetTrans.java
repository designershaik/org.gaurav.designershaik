package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSDenomiationDetTrans extends X_DS_DenomiationDet_Trans {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSDenomiationDetTrans(Properties ctx, int DS_DenomiationDet_Trans_ID, String trxName) {
		super(ctx, DS_DenomiationDet_Trans_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSDenomiationDetTrans(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
