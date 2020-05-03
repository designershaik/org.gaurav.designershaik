package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CallOutLeadCopyNameToValue implements IColumnCallout{

	public CallOutLeadCopyNameToValue() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if (value == null)
			return "";
		
		String bpName = value.toString();
		mTab.setValue("Value", bpName);
		return null;
	}

}
