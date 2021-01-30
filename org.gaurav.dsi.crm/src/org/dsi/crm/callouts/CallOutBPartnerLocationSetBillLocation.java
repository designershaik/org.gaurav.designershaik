package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.X_C_BP_Relation;
import org.compiere.util.DB;

public class CallOutBPartnerLocationSetBillLocation implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer C_BPartner_Location_ID = (Integer)value;
		MBPartnerLocation loc = new MBPartnerLocation(ctx, C_BPartner_Location_ID, null);
		Integer C_BPartner_ID = (Integer)mTab.getValue("C_BPartner_ID");
		X_C_BP_Relation relation = null;
		if(!loc.isBillTo())
		{
			
			int C_BP_Relation_ID = DB.getSQLValue(null, "Select C_BP_Relation_ID "
					+ "From C_BP_Relation Where C_BPartner_ID = ? "
					+ "and C_BPartner_Location_ID = ?"
					+ "and isBillTo='Y' and IsActive='Y' ",C_BPartner_ID,C_BPartner_Location_ID);
			relation = new X_C_BP_Relation(ctx, C_BP_Relation_ID, null);
			mTab.setValue("Bill_BPartner_ID", relation.getC_BPartnerRelation_ID());
			mTab.setValue("Bill_Location_ID", relation.getC_BPartnerRelation_Location_ID());
		}
		if(relation!=null && relation.get_ID()<=0)
		{
			mTab.setValue("Bill_BPartner_ID", C_BPartner_ID);
			mTab.setValue("Bill_Location_ID", C_BPartner_Location_ID);
		}
		return null;
	}



}
