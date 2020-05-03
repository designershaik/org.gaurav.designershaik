package com.gaurav.display.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.util.DB;

public class CallOutVerifyIfAssetAlreadyExists implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		Integer M_Locator_ID = (Integer)value;
		Integer M_Movement_ID = (Integer)mTab.getValue("M_Movement_ID");
		Integer M_Product_ID = (Integer)mTab.getValue("M_Product_ID");
		if(M_Product_ID!=null)
		{
			MMovement movement = new MMovement(ctx, M_Movement_ID, null);
			MDocType docType = new MDocType(ctx, movement.getC_DocType_ID(), null);
			if(docType.get_ValueAsBoolean("DS_IsUseCogsForMovement"))
			{
				MLocator locator = new MLocator(ctx, M_Locator_ID, null);
				int A_Asset_ID = DB.getSQLValue(null, 
						"Select workfile.A_Asset_ID from A_Asset asd,A_Depreciation_Workfile workfile "
						+ "Where asd.A_Asset_ID = workfile.A_Asset_ID "
						+ "and asd.M_Product_ID = ? and asd.M_Locator_ID = ? "
						+ "and asd.C_BPartner_ID = ? and asd.C_BPartner_Location_ID = ? "
						+ "and workfile.A_Asset_Remaining>0 ",M_Product_ID,
						M_Locator_ID,locator.get_Value("C_BPartner_ID"),
						locator.get_Value("C_BPartner_Location_ID"));	
				mTab.setValue("A_Asset_ID", A_Asset_ID);
				
				
			}
		}
			
		return null;
	}


}
