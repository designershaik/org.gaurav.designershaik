package org.dsi.finance.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MInvoice;

public class CopyInvoiceDescription implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		int C_Invoice_ID = (Integer)value;
		
		MInvoice invoice = new MInvoice(ctx, C_Invoice_ID, null);
		mTab.setValue("Description", invoice.getDescription());
		
		
		return null;
	}

}
