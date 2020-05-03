package com.gaurav.dsi.purchase.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class SetSalesRepInRequest implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		int R_Group_ID = (Integer)value;
		int LoggedInUser = Env.getAD_User_ID(ctx);
		
		int count = DB.getSQLValue(null, "select count(*) "
				+ "from DSI_GroupResponsible where AD_User_ID= ? and R_Group_ID =? ", LoggedInUser,R_Group_ID);
		if(count==0)
			mTab.fireDataStatusEEvent("", "", true);
		return null;
	}

}
