package com.gaurav.dsi.purchase.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSProductRequest;

public class CallOutCopyPRDetailsToInvoice implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		Integer R_Request_ID = (Integer)mTab.getValue("R_Request_ID");
		R_Request_ID = R_Request_ID == null ? 0 : R_Request_ID;
		if(value==null)
		{
			if(R_Request_ID>0)
			{
				mTab.setValue("M_Product_ID", null);
	 			mTab.setValue("C_Charge_ID", null);
	 			mTab.setValue("QtyEntered", Env.ONE);
				mTab.setValue("User1_ID",null);
				mTab.setValue("User2_ID", null);
				mTab.setValue("C_Activity_ID", null);
				mTab.setValue("C_Project_ID", null);
				mTab.setValue("C_ProjectPhase_ID", null);
				mTab.setValue("C_ProjectTask_ID", null);
				mTab.setValue("PriceEntered", Env.ZERO);
			}
			return null;
		} 
		
		Integer requestDetID=(Integer)mTab.getValue("DS_Product_Request_ID");
		int productID;
		MDSProductRequest mo= new MDSProductRequest(ctx, requestDetID, null);
		productID=mo.getM_Product_ID();
 		if(productID!=0)
		{
 			mTab.setValue("C_Charge_ID", null);
			mTab.setValue("M_Product_ID", productID);
		}
 		if(mo.getC_Charge_ID()>0)
 		{
 			mTab.setValue("C_Charge_ID", mo.getC_Charge_ID());
			mTab.setValue("M_Product_ID", null);
 		}
 		mTab.setValue("C_UOM_ID", mo.getC_UOM_ID());
		mTab.setValue("Description", mo.getProductDescription());
		mTab.setValue("QtyEntered", mo.getQtyRequired());
		mTab.setValue("QtyInvoiced", mo.getQtyRequired());
		mTab.setValue("User1_ID", mo.getUser1_ID()==0 ?  null : mo.getUser1_ID());
		mTab.setValue("User2_ID", mo.getUser2_ID()==0 ?  null : mo.getUser2_ID());
		mTab.setValue("C_Activity_ID", mo.getC_Activity_ID()==0 ? null: mo.getC_Activity_ID());
		mTab.setValue("C_Project_ID", mo.getC_Project_ID()==0 ? null:mo.getC_Project_ID());
		mTab.setValue("C_ProjectPhase_ID", mo.getC_ProjectPhase_ID()==0 ? null:mo.getC_ProjectPhase_ID());
		mTab.setValue("C_ProjectTask_ID", mo.getC_ProjectTask_ID()==0 ? null:mo.getC_ProjectTask_ID());
		mTab.setValue("PriceEntered", mo.getDS_Budget());
		mTab.setValue("PriceActual", mo.getDS_Budget());
		mTab.setValue("RelatedProduct_ID", mo.get_Value("RelatedProduct_ID"));
		return null;
	}

}
