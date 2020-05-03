package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutSetOrderSourceOnPOS implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, 
			GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		
		int AD_User_ID = Env.getAD_User_ID(ctx);
		Integer C_OrderSource_ID = (Integer)mTab.getValue("C_OrderSource_ID");
		if(C_OrderSource_ID==null)
		{
			C_OrderSource_ID = DB.getSQLValue(null, "Select bp.C_OrderSource_ID From C_BPartner bp ,AD_User adu "
				+ "Where adu.C_BPartner_ID=bp.C_BPartner_ID and adu.AD_User_ID = ? and adu.IsActive='Y' ",AD_User_ID);
			mTab.setValue("C_OrderSource_ID", C_OrderSource_ID);
		}
		return null;
	}
}
