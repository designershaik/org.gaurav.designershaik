package org.gaurav.dsi.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MStorageOnHand;
import org.compiere.util.Env;

public class CallOutQtyOnHand implements IColumnCallout
{
	BigDecimal QtyOnHand=Env.ZERO;
	
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null || mTab.getValue("M_Product_ID")==null)
			return null;
		
		int mLocatorID=(Integer)value;
		int mProductID=(Integer)mTab.getValue("M_Product_ID");
		
		QtyOnHand = MStorageOnHand.getQtyOnHandForLocator(mProductID, mLocatorID, 0, null);
		mTab.setValue("QtyOnhand", QtyOnHand);
		
		return null;
	}

}
