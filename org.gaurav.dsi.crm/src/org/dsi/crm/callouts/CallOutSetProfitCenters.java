package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MOrder;
import org.compiere.model.MSysConfig;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutSetProfitCenters implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		Integer C_Order_ID = (Integer)mTab.getValue("C_Order_ID");
		if(C_Order_ID>0)
		{
			int profitcenterTree_ID = MSysConfig.getIntValue("DS_PROFITCENTER_TREE_ID",1000013,Env.getAD_Client_ID(ctx));
			MOrder order = new MOrder(ctx, C_Order_ID, null);
			String countryName = order.getBill_Location().getC_Location().getC_Country().getName();
			int profitCenter_ID = DB.getSQLValue(null, "select val.c_elementvalue_id from c_element el,c_elementvalue val "
														+ "where el.AD_Tree_ID=? "
														+ "and el.C_Element_Id=val.C_Element_ID "
														+ "and val.name=?",profitcenterTree_ID,countryName);
					mTab.setValue("User1_ID", profitCenter_ID);
		}
		return null;
	}

}
