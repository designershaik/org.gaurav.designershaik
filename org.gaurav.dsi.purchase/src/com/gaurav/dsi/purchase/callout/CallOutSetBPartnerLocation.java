package com.gaurav.dsi.purchase.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class CallOutSetBPartnerLocation implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer C_BPartner_ID = (Integer)value;
		int C_BPartner_Location_ID = DB.getSQLValue(null, "SELECT C_BPartner_Location_ID "
				+ "From C_BPartner_Location where C_BPartner_ID =  ? and IsActive='Y' ",C_BPartner_ID);
		mTab.setValue("C_BPartner_Location_ID", C_BPartner_Location_ID);
		
		return null;
	}


}
