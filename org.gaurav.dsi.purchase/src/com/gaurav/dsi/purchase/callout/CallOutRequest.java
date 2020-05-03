package com.gaurav.dsi.purchase.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutRequest implements IColumnCallout
{
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue)
	{
		Boolean isApproved=(Boolean)value;
		if(!isApproved)
		{
			return "";
		}
		if(mTab.getValue("CreatedBy")==null)
		{
			mTab.setValue("DS_IsApproved", 'N');
			mTab.fireDataStatusEEvent(null, "We got you !!! Get it right bro :)", true);
		}else
		{
			int createdBy=(Integer)mTab.getValue("CreatedBy");
			if(createdBy==Env.getAD_User_ID(ctx))
				mTab.setValue("DS_IsApproved", 'N');
				mTab.fireDataStatusEEvent(null, " lol , I know what are you trying to do :P !!! ", true);
		}
		return null;
	}
}
