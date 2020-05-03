package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSManufacturingQA extends X_DS_Manufacturing_QA {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7712438420929445389L;

	public MDSManufacturingQA(Properties ctx, int DS_Manufacturing_QA_ID,
			String trxName) {
		super(ctx, DS_Manufacturing_QA_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSManufacturingQA(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
