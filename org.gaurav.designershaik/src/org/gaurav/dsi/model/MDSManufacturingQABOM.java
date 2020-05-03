package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSManufacturingQABOM extends X_DS_Manufacturing_QA_BOM {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2604577355919658657L;

	public MDSManufacturingQABOM(Properties ctx,
			int DS_Manufacturing_QA_BOM_ID, String trxName) {
		super(ctx, DS_Manufacturing_QA_BOM_ID, trxName);
		
	}

	public MDSManufacturingQABOM(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
