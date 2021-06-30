package com.gaurav.dsi.factories;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;

import com.gaurav.dsi.model.MDSIDashboardReports;

public class DSIDashboardModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		if(tableName.equalsIgnoreCase(MDSIDashboardReports.Table_Name))
			return MDSIDashboardReports.class;
		
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
	
		if(tableName.equalsIgnoreCase(MDSIDashboardReports.Table_Name))
			return new MDSIDashboardReports(Env.getCtx(), Record_ID, trxName);
		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
	
		if(tableName.equalsIgnoreCase(MDSIDashboardReports.Table_Name))
			return new MDSIDashboardReports(Env.getCtx(), rs, trxName);
		
		return null;
	}



}
