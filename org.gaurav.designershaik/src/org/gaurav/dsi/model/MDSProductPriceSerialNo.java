package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSProductPriceSerialNo extends X_DS_ProductPriceSerialNo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSProductPriceSerialNo(Properties ctx,
			int DS_ProductPriceSerialNo_ID, String trxName) {
		super(ctx, DS_ProductPriceSerialNo_ID, trxName);
		
	}

	public MDSProductPriceSerialNo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
