package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MOrder;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutSetBPartnerTypeOnOrder implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {
		
		if(value==null)
			return null;
		if(mField.getColumnName().equalsIgnoreCase(MOrder.COLUMNNAME_Bill_BPartner_ID))
		{
			int C_BPartner_ID = (Integer)value;
			MBPartner bp = new MBPartner(ctx, C_BPartner_ID, null);
			if(bp.isSendEMail())
			{
				int C_DocType_ID = DB.getSQLValue(null, "Select C_DocType_ID From C_DocType "
						+ "Where AD_Client_ID = ? and DocSubTypeSO='SO' and DocBaseType='SOO' and IsDefault='Y' ", Env.getAD_Client_ID(ctx));
				if(bp.get_Value("C_DocType_ID")==null)
					mTab.setValue("C_DocTypeTarget_ID", C_DocType_ID);
				else
					mTab.setValue("C_DocTypeTarget_ID", bp.get_Value("C_DocType_ID"));
			}
			
			mTab.setValue("DS_ShippingTerms", bp.get_Value("DS_ShippingTerms"));
		}
		if(mField.getColumnName().equalsIgnoreCase(MOrder.COLUMNNAME_C_BPartner_ID))
		{
			int C_BPartner_ID = (Integer)value;
			MBPartner bp = new MBPartner(ctx, C_BPartner_ID, null);
			mTab.setValue("C_OrderSource_ID", bp.get_Value("C_OrderSource_ID"));
		}
		return null;
	}

}
