package com.gaurav.dsi.manufacturing.factories;

import org.compiere.grid.ICreateFrom;
import org.compiere.grid.ICreateFromFactory;
import org.compiere.model.GridTab;
import org.compiere.model.I_M_MovementLine;
import org.libero.model.MPPOrderBOMLine;

import com.gaurav.dsi.manufacturing.form.WCreateFromForASIUI;

public class DSIManufacturingCreateFromfactory implements ICreateFromFactory{

	@Override
	public ICreateFrom create(GridTab mTab) 
	{
		String tableName = mTab.getTableName();
		if(tableName.equals(I_M_MovementLine.Table_Name))
			return new WCreateFromForASIUI(mTab);
		
		if(tableName.equals(MPPOrderBOMLine.Table_Name))
			return new WCreateFromForASIUI(mTab);
		
		return null;
	}

}
