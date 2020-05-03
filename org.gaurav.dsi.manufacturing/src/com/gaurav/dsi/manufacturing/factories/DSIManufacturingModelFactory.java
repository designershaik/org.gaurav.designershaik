package com.gaurav.dsi.manufacturing.factories;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;

import com.gaurav.dsi.manufacturing.model.MDSMnfgMaterialMeasurement;

public class DSIManufacturingModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		
		if(MDSMnfgMaterialMeasurement.Table_Name.equalsIgnoreCase(tableName))
			return MDSMnfgMaterialMeasurement.class;
		
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		
		if(MDSMnfgMaterialMeasurement.Table_Name.equalsIgnoreCase(tableName))
			return new MDSMnfgMaterialMeasurement(Env.getCtx(), Record_ID, trxName);
		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		
		if(MDSMnfgMaterialMeasurement.Table_Name.equalsIgnoreCase(tableName))
			return new MDSMnfgMaterialMeasurement(Env.getCtx(), rs, trxName);
		
		return null;
	}


}
