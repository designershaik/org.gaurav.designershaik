package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.dsi.model.MDSServiceRequest;

public class CallOutCopyServiceRequestDetails implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		Integer requestDetID=(Integer)value;
		int productID;
		if(requestDetID==null)
		{
			return "";
		} 
		MDSServiceRequest mo= new MDSServiceRequest(ctx, requestDetID, null);
		productID=mo.getM_Product_ID();
 		if(productID==0)
		{
			mTab.fireDataStatusEEvent("ProductMandatory", "To Create Internal use inventory product is required", true);
		}
 		else
 		{
	 		mTab.setValue("M_Product_ID", productID);
			mTab.setValue("Description", mo.getProductDescription());
			mTab.setValue("QtyInternalUse", mo.getQtyRequired());
 		}
		return "";
	}

}
