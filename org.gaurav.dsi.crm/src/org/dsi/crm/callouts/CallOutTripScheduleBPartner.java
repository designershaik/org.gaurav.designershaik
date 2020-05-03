package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutTripScheduleBPartner implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(value==null)
			return null;
		
		String loc = Env.getContext(ctx, WindowNo, Env.TAB_INFO, "C_BPartner_Location_ID");
		int locationId = 0;
		if (loc.length() > 0)
			locationId = Integer.parseInt(loc);
		
		mTab.setValue("C_BPartner_Location_ID", new Integer(locationId));
		
		String user = Env.getContext(ctx, WindowNo, Env.TAB_INFO, "AD_User_ID");
		int userID = 0;
		if (loc.length() > 0)
			userID = Integer.parseInt(user);
		
		mTab.setValue("AD_User_ID", new Integer(userID));
		
		return null;
	}


}
