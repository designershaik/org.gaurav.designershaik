package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MOrder;

public class CallOutShipmentOrder implements IColumnCallout
{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		int C_Order_ID = (Integer)value;
		MOrder order = new MOrder(ctx, C_Order_ID, null);
		mTab.setValue("DatePromised", order.getDatePromised());
		if(order.isDelivered())
			mTab.fireDataStatusEEvent ("OrderAlreadyShipped", order.getDocumentNo(), true);
		
		return null;
	}
	
}
