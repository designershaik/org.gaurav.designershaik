package com.gaurav.display.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MDocType;
import org.compiere.model.MMovement;
import org.compiere.util.DB;

public class CallOutSetLocator implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		Integer M_Movement_ID = (Integer)mTab.getValue("M_Movement_ID");
		MMovement movement = new MMovement(ctx, M_Movement_ID, null);
		MDocType docType = new MDocType(ctx, movement.getC_DocType_ID(), null);
		if(docType.get_ValueAsBoolean("DS_IsUseCogsForMovement"))
		{
			int M_Locator_ID = DB.getSQLValue(null, "Select M_Locator_ID From M_Locator "
					+ "Where C_BPartner_ID = ? and C_BPartner_Location_ID = ? ",movement.getC_BPartner_ID(),movement.getC_BPartner_Location_ID());
			mTab.setValue("M_LocatorTo_ID", M_Locator_ID);
		}
		
		return null;
	}



}
