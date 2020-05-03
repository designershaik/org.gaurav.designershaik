package com.gaurav.display.factories;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;

import com.gaurav.display.callout.CallOutSetCost;
import com.gaurav.display.callout.CallOutSetLocator;
import com.gaurav.display.callout.CallOutVerifyIfAssetAlreadyExists;
import com.gaurav.display.callout.MovementSetBPartnerLocation;

public class DisplayCallOutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) 
	{
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MMovementLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MMovementLine.COLUMNNAME_M_Locator_ID))
			list.add(new CallOutVerifyIfAssetAlreadyExists());
		
		if(tableName.equalsIgnoreCase(MMovementLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MMovementLine.COLUMNNAME_M_Movement_ID))
			list.add(new CallOutSetLocator());
		
		if(tableName.equalsIgnoreCase(MMovementLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MMovementLine.COLUMNNAME_M_Product_ID))
			list.add(new CallOutSetCost());
		
		if(tableName.equalsIgnoreCase(MMovementLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MMovementLine.COLUMNNAME_M_AttributeSetInstance_ID))
			list.add(new CallOutSetCost());
		
		if(tableName.equalsIgnoreCase(MMovement.Table_Name) 
				&& columnName.equalsIgnoreCase(MMovement.COLUMNNAME_C_BPartner_ID))
			list.add(new MovementSetBPartnerLocation());
		
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}



}
