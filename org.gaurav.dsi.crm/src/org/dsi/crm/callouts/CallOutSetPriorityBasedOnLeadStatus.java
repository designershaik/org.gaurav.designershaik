package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CallOutSetPriorityBasedOnLeadStatus implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		if(value==null)
			return null;
		

		//Converted C 3
		//Expired E 9
		//New N 
		//Recycled R
		//In Progress W
		//Reject X

		String status = (String) value;
		if(status.equalsIgnoreCase("C") || status.equalsIgnoreCase("W"))
			mTab.setValue("PriorityRule", "1");
		
		if(status.equalsIgnoreCase("E") || status.equalsIgnoreCase("X"))
			mTab.setValue("PriorityRule", "0");
		
		if(status.equalsIgnoreCase("R"))
			mTab.setValue("PriorityRule", "0");
		
			
		return null;
	}


}
