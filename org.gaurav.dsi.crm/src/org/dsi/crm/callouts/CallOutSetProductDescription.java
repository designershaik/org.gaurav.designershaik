package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProduct;

public class CallOutSetProductDescription implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		int M_Product_ID = (Integer)value;
		MProduct product = new MProduct(ctx, M_Product_ID, null);
		mTab.setValue("ProductDescription", product.getName());
		return null;
	}


}
