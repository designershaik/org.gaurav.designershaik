package com.gaurav.display.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MDocType;
import org.compiere.model.MMovement;
import org.compiere.model.MProduct;
import org.compiere.util.Env;

public class CallOutSetCost implements IColumnCallout {

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		
		Integer M_Movement_ID = (Integer)mTab.getValue("M_Movement_ID");
		MMovement movement = new MMovement(ctx, M_Movement_ID, null);
		MDocType docType = new MDocType(ctx, movement.getC_DocType_ID(), null);
		if(docType.get_ValueAsBoolean("DS_IsUseCogsForMovement"))
		{
			MClient client = new MClient(ctx, movement.getAD_Client_ID(), null);
			MAcctSchema asc = client.getAcctSchema();
			Integer M_AttributeSetInstance_ID = (Integer)mTab.getValue("M_AttributeSetInstance_ID");
			M_AttributeSetInstance_ID = M_AttributeSetInstance_ID==null ? 0:M_AttributeSetInstance_ID;
			Integer M_Product_ID = (Integer)mTab.getValue("M_Product_ID");
			
			MProduct product = new MProduct(ctx, M_Product_ID, null);
			BigDecimal currentCostPrice = MCost.getCurrentCost(product, M_AttributeSetInstance_ID, asc, 0, null, Env.ONE, 0, true, null);
			mTab.setValue("PriceEntered", currentCostPrice);
			mTab.setValue("PriceList", currentCostPrice);
		}
		return null;
	}

}
