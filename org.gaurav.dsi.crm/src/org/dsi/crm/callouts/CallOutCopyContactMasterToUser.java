package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSContactMaster;

public class CallOutCopyContactMasterToUser implements IColumnCallout
{
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		int contactMasterID;
		if (value == null)
			return null;
		else
			contactMasterID=(Integer)value;
		MDSContactMaster master=new MDSContactMaster(ctx, contactMasterID, null);
		String searchKey=master.getValue();
		String name=master.getName();
		mTab.setValue("value", searchKey);
		mTab.setValue("Name", name);
		mTab.setValue("EMail", master.getEMail());
		mTab.setValue("Phone", master.getPhone());
		mTab.setValue("Phone2", master.getPhone2());
		mTab.setValue("Birthday", master.getBirthday());
		mTab.setValue("Fax", master.getFax());
		mTab.setValue("Title", master.getTitle());
		int contactLocation_ID=DB.getSQLValue(null,"select C_Location_ID from DS_ContactMaster_Location where DS_ContactMaster_ID="+contactMasterID);		
		mTab.setValue("C_Location_ID", contactLocation_ID);
		
		return null;
	}
}
