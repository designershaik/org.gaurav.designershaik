package org.dsi.crm.factories;

import org.compiere.grid.ICreateFrom;
import org.compiere.grid.ICreateFromFactory;
import org.compiere.model.GridTab;
import org.compiere.model.MPackage;
import org.dsi.crm.form.WCreateFromPackageUI;

public class DSICRMCreateFromFactory implements ICreateFromFactory{

	@Override
	public ICreateFrom create(GridTab mTab)
	{
		if(mTab.getTableName().equalsIgnoreCase(MPackage.Table_Name))
			return new WCreateFromPackageUI(mTab);
		
		return null;
	}



}
