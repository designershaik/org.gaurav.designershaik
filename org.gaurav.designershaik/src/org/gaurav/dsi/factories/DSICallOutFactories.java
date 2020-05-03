package org.gaurav.dsi.factories;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.I_M_InventoryLine;
import org.compiere.model.I_M_MovementLine;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMovementLine;
import org.gaurav.dsi.callouts.CallOutQtyOnHand;

public class DSICallOutFactories implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) 
	{
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MMovementLine.Table_Name) 
				&& columnName.equalsIgnoreCase(I_M_MovementLine.COLUMNNAME_M_Locator_ID))
			list.add(new CallOutQtyOnHand());
		
		if(tableName.equalsIgnoreCase(MInventoryLine.Table_Name) 
				&& columnName.equalsIgnoreCase(I_M_InventoryLine.COLUMNNAME_M_Locator_ID))
			list.add(new CallOutQtyOnHand());
			
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}
}
