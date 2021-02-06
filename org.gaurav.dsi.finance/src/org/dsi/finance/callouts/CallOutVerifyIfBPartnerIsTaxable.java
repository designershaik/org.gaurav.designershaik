package org.dsi.finance.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MInvoice;
import org.compiere.model.MTax;
import org.compiere.util.Env;

public class CallOutVerifyIfBPartnerIsTaxable implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer C_Tax_ID = (Integer)value;
		Integer C_Invoice_ID = (Integer)mTab.getValue("C_Invoice_ID");
		if(C_Invoice_ID>0)
		{
			MInvoice inv = new MInvoice(ctx,C_Invoice_ID,null);
			MTax tax = new MTax(ctx,C_Tax_ID,null);
			if(tax.getRate().compareTo(Env.ZERO)>0 && (inv.getC_BPartner().getTaxID()==null || inv.getC_BPartner().getTaxID().isBlank()))
			{
				mTab.setValue("C_Tax_ID", null);
				mTab.fireDataStatusEEvent("", "Business partner is not tax registered", true);
			}
				
		}
		return null;
	}



}
