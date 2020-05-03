package com.gaurav.dsi.purchase.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class CallOutSetDefautlSubGroup implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		int R_Group_ID = (Integer)value;
		int DS_Request_SubGroup_ID = DB.getSQLValue(null, "Select DS_Request_SubGroup_ID From DS_Request_SubGroup "
				+ "WHERE R_Group_ID = ? and IsActive='Y' ", R_Group_ID);
		mTab.setValue("DS_Request_SubGroup_ID", DS_Request_SubGroup_ID);
		
		
		return null;
	}


}
