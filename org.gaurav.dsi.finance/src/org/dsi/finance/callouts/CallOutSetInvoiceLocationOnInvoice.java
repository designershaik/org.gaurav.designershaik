package org.dsi.finance.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartnerLocation;
import org.compiere.util.DB;

public class CallOutSetInvoiceLocationOnInvoice implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(value==null)
			return null;
		
		Integer C_BPartner_ID = (Integer)value;
		Integer C_BPartner_Location_ID = (Integer)mTab.getValue("C_BPartner_Location_ID");
		
		MBPartnerLocation location = new MBPartnerLocation(ctx, C_BPartner_Location_ID, null);
		if(!location.isBillTo())
		{
			C_BPartner_Location_ID = DB.getSQLValue(null, "SELECT C_BPartner_Location_ID FROM C_BPartner_Location "
					+ "WHERE C_BPartner_ID = ? AND IsBillTo='Y' AND IsActive='Y' ",C_BPartner_ID);
			mTab.setValue("C_BPartner_Location_ID", C_BPartner_Location_ID);
		}
		
		return null;
	}

}
