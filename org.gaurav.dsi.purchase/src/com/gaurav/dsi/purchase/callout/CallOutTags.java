package com.gaurav.dsi.purchase.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.dsi.model.MDSTags;

public class CallOutTags implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if (value == null)
			
			return "";
		int ID = (Integer) value;
		String tagsName = "";

		String description = (mTab.getValue("Description") == null) ? "":mTab.getValue("Description").toString();
		MDSTags dst = new MDSTags(ctx, ID, null);
		if(dst.getCategoryName().equalsIgnoreCase("Channels"))
		{
		tagsName =dst.getName();
		}
		else
		{
			tagsName = dst.getCategoryName().concat("_").concat(dst.getName());
		}
		if (description.contains(tagsName)) 
		{
			mTab.setValue("Description",description.replaceAll((",").concat(tagsName), ""));
		} 
		else
		{
		mTab.setValue("Description", description.concat(",").concat(tagsName));
		}
		mTab.setValue("DS_Tags_ID", 0);
		return "";
	}
}
