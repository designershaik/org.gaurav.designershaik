package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSRequiredDocForShipment extends X_DS_RequiredDocForShipment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8615235401700358713L;

	public MDSRequiredDocForShipment(Properties ctx,
			int DS_RequiredDocForShipment_ID, String trxName) {
		super(ctx, DS_RequiredDocForShipment_ID, trxName);
		
	}

	public MDSRequiredDocForShipment(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		
	}

}
