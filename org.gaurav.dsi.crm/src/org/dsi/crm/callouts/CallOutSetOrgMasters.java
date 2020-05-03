package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.dsi.model.MDSOrgMaster;

public class CallOutSetOrgMasters implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		int orgMasterID;
		if (value == null)
			return "";
		else
			orgMasterID=(Integer)value;
		MDSOrgMaster master=new MDSOrgMaster(ctx, orgMasterID, null);
		String searchKey=master.getValue();
		String name=master.getName();
		mTab.setValue("value", searchKey);
		mTab.setValue("Name", name);
		mTab.setValue("URL", master.getWebSite());
		mTab.setValue("Description", master.get_Value("Description"));
		return null;
	}

}
