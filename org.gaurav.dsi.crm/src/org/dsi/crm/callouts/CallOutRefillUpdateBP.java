package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.dsi.model.MDSIRefillPrintForm;

public class CallOutRefillUpdateBP implements IColumnCallout 
{
	int printRefillID;

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
		{
			return "";
		} 
		printRefillID=(Integer)value;
		MDSIRefillPrintForm frm= new MDSIRefillPrintForm(ctx, printRefillID, null);
		mTab.setValue("C_BPartner_ID", frm.getC_BPartner_ID());
		mTab.setValue("C_BPartner_Location_ID", frm.getC_BPartner_Location_ID());
		
		return "";
	}
}
