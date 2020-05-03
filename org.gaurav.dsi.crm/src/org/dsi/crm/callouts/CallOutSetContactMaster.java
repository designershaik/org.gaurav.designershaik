package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.dsi.model.MDSContactMaster;

public class CallOutSetContactMaster implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		int contactMasterID;
		if (value == null)
			return "";
		
		else
		
		contactMasterID=(Integer)value;
		MDSContactMaster master=new MDSContactMaster(ctx, contactMasterID, null);
		String searchKey=master.getValue();
		String name=master.getName();
		mTab.setValue("value", searchKey);
		mTab.setValue("Name", name);
		mTab.setValue("URL", master.getWebSite());
		return null;
	}

}
