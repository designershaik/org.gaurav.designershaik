package com.gaurav.dsi.purchase.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutApprovedPurchaseRequest implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		Boolean isApproved = (Boolean) value;
		int createdBy = 0;
		if (mTab.getValue("CreatedBy") != null)
			createdBy = (Integer) mTab.getValue("CreatedBy");

		Timestamp date = new Timestamp(System.currentTimeMillis());

		if (!isApproved) {
			return "";
		}
		if (mTab.getValue("CreatedBy") == null) 
		{
			mTab.setValue("DS_IsApproved", false);
			mTab.fireDataStatusEEvent(null,
					"We got you !!! Get it right bro :)", true);
		}
		if (createdBy == Env.getAD_User_ID(ctx)) 
		{
			mTab.setValue("DS_IsApproved",false);
			mTab.fireDataStatusEEvent(null,
					" lol , I know what are you trying to do :P !!! ", true);
		} else {
			mTab.setValue("DS_DateApproved", date);
			mTab.setValue("DS_ApprovedBy_ID", Env.getAD_User_ID(ctx));
		}
		return null;
	}

}
