package org.dsi.finance.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCountry;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;

public class CallOutSetIsPOPMandatory implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
		{
			mTab.setValue("DS_IsPOPCodeMandatory", false);
			return null;
		}
		int C_DocType_ID = (Integer)mTab.getValue("C_DocType_ID");
		MDocType type = new MDocType(ctx, C_DocType_ID, null);
		if(!type.getDocBaseType().contentEquals(MDocType.DOCBASETYPE_ARReceipt))
		{
			int C_BPartner_Location_ID = 0 ; 
			if(mField.getColumnName().equalsIgnoreCase("C_Invoice_ID"))
			{
				Integer C_Invoice_ID = (Integer)value;
				MInvoice inv = new MInvoice(ctx, C_Invoice_ID, null);
				C_BPartner_Location_ID = inv.getC_BPartner_Location_ID();
			}
			if(mField.getColumnName().equalsIgnoreCase("C_Order_ID"))
			{
				Integer C_Order_ID = (Integer)value;
				MOrder inv = new MOrder(ctx, C_Order_ID, null);
				C_BPartner_Location_ID = inv.getBill_Location_ID();
			}
			if(C_BPartner_Location_ID>0)
			{
				MBPartnerLocation loc = new MBPartnerLocation(ctx, C_BPartner_Location_ID, null);
				MCountry cntr = (MCountry)loc.getC_Location().getC_Country();
				mTab.setValue("DS_IsPOPCodeMandatory", cntr.get_Value("DS_IsPOPCodeMandatory"));
			}
		}
		return null;
	}


}
