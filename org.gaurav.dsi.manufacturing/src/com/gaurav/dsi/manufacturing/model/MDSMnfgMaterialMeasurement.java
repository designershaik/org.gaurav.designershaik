package com.gaurav.dsi.manufacturing.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSMnfgMaterialMeasurement extends X_DS_Mnfg_MaterialMeasurement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSMnfgMaterialMeasurement(Properties ctx, int DS_Mnfg_MaterialMeasurement_ID, String trxName) {
		super(ctx, DS_Mnfg_MaterialMeasurement_ID, trxName);
		
	}

	public MDSMnfgMaterialMeasurement(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
