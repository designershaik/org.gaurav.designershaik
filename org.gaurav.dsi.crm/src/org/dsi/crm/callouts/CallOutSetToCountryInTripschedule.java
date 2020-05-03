package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.dsi.model.MDSRouteMaster;

public class CallOutSetToCountryInTripschedule implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer routeMasterID = (Integer)value;
		MDSRouteMaster master = new MDSRouteMaster(ctx, routeMasterID, null);
		mTab.setValue("DS_ToCountry_ID", master.getDS_ToCountry_ID());
		return null;
	}



}
