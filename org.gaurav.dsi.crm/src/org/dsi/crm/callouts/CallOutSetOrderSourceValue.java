package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.X_C_OrderSource;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutSetOrderSourceValue implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {

		if(value==null)
			return null;
		
		Integer C_OrderSource_ID = (Integer)value;
		int M_PriceList_ID = 0;
		X_C_OrderSource source = new X_C_OrderSource(ctx, C_OrderSource_ID, null);
		mTab.setValue("C_OrderSourceValue", source.getValue());
		int M_WarehouseSource_ID = source.get_ValueAsInt("M_WarehouseSource_ID");
		if(M_WarehouseSource_ID>0)
			mTab.setValue("M_Warehouse_ID", M_WarehouseSource_ID);
		if(source.get_ValueAsInt("C_BPartner_ID")>0)
		{
			int bpartnerID = source.get_ValueAsInt("C_BPartner_ID");
			if(bpartnerID>0)
			{
				MBPartner bp = new MBPartner(ctx,bpartnerID,null);
				mTab.setValue("C_BPartner_ID", bpartnerID);
				M_PriceList_ID = getPriceList(bp);
				
			}
		}
		else
			M_PriceList_ID = getPriceList(null);
		mTab.setValue("M_PriceList_ID", M_PriceList_ID);
		if(source.get_ValueAsInt("C_BPartner_Location_ID")>0)
			mTab.setValue("C_BPartner_Location_ID", source.get_ValueAsInt("C_BPartner_Location_ID"));
		
		return null;
	}

	private int getPriceList(MBPartner bp) {
		
		int M_PriceList_ID = 0 ;
		if(bp!=null && bp.getM_PriceList_ID()>0)
			M_PriceList_ID = bp.getM_PriceList_ID();
		else
		{
			M_PriceList_ID = DB.getSQLValue(null, "Select M_PriceList_ID from M_PriceList "
					+ "Where DS_IsDSPOSPriceList='Y' and AD_Client_ID = ? ",Env.getAD_Client_ID(Env.getCtx()));
		}
		return M_PriceList_ID;
	}


}
