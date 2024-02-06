package com.gaurav.display.factories;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.PO;
import org.compiere.util.Env;

import com.gaurav.display.model.MDSAsset;
import com.gaurav.display.model.MDSMovement;
import com.gaurav.display.model.MDSMovementLine;

public class DisplayModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		
		if(MMovementLine.Table_Name.equalsIgnoreCase(tableName))
			return MDSMovementLine.class;
		
		if(MMovement.Table_Name.equalsIgnoreCase(tableName))
			return MDSMovement.class;
		
		if(MDSAsset.Table_Name.equalsIgnoreCase(tableName))
			return MDSAsset.class;
		
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		
		if(MMovementLine.Table_Name.equalsIgnoreCase(tableName))
			return new MDSMovementLine(Env.getCtx(), Record_ID, trxName);
		
		if(MMovement.Table_Name.equalsIgnoreCase(tableName))
			return new MDSMovement(Env.getCtx(), Record_ID, trxName);
		
		if(MDSAsset.Table_Name.equalsIgnoreCase(tableName))
			return new MDSAsset(Env.getCtx(), Record_ID, trxName);
		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		
		if(MMovementLine.Table_Name.equalsIgnoreCase(tableName))
			return new MDSMovementLine(Env.getCtx(), rs, trxName);
		
		if(MMovement.Table_Name.equalsIgnoreCase(tableName))
			return new MDSMovement(Env.getCtx(), rs, trxName);
		
		if(MDSAsset.Table_Name.equalsIgnoreCase(tableName))
			return new MDSAsset(Env.getCtx(), rs, trxName);
		
		return null;
	}

	
}
