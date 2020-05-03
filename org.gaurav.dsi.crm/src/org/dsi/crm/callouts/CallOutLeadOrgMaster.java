package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSOrgMaster;

public class CallOutLeadOrgMaster implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		int orgMasterID;
		if (value == null)
			return "";
		else
			orgMasterID = (Integer) value;
		MDSOrgMaster master = new MDSOrgMaster(ctx, orgMasterID, null);
		String name = master.getName();
		mTab.setValue("BPName", name);
		int OrgLocationID=DB.getSQLValue(null,"select C_Location_ID from DS_OrgMaster_Location where DS_OrgMaster_ID="+orgMasterID);		
		mTab.setValue("BP_Location_ID", OrgLocationID);
		return null;
	}

}
