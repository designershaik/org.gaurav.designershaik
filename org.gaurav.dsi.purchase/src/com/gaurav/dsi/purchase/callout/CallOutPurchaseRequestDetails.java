package com.gaurav.dsi.purchase.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MUOMConversion;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSProductRequest;
import org.gaurav.dsi.model.MDSServiceRequest;

public class CallOutPurchaseRequestDetails implements IColumnCallout{

	CLogger log=CLogger.getCLogger(CallOutPurchaseRequestDetails.class);
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
	 			mTab.setValue("QtyEntered", null);
				mTab.setValue("User1_ID",null);
				mTab.setValue("User2_ID", null);
				mTab.setValue("C_Activity_ID", null);
				mTab.setValue("C_Project_ID", null);
				mTab.setValue("C_ProjectPhase_ID", null);
				mTab.setValue("C_ProjectTask_ID", null);
				mTab.setValue("QtyOrdered", Env.ONE);
				mTab.setValue("PriceEntered", Env.ZERO);
				mTab.setValue("PriceActual", Env.ZERO);
			}
			return null;
		} 
		
		Integer requestDetID=(Integer)value;
		BigDecimal QtyEntered;
		BigDecimal QtyOrdered=Env.ZERO;
		BigDecimal PriceActual, PriceEntered;
		int M_Product_ID = 0;
		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));
		if(!IsSOTrx)
		{
			MDSProductRequest mo= new MDSProductRequest(ctx, requestDetID, null);
			M_Product_ID=mo.getM_Product_ID();
	 		if(M_Product_ID!=0)
	 		{
				mTab.setValue("M_Product_ID", M_Product_ID);
				mTab.setValue("C_Charge_ID", null);
	 		}
	 		if(mo.getC_Charge_ID()>0)
	 		{
	 			mTab.setValue("M_Product_ID", null);
	 			mTab.setValue("C_Charge_ID", mo.getC_Charge_ID());
	 		}
	 		mTab.setValue("Description", mo.getProductDescription());
	 		if(mo.getC_UOM_ID()>0)
				mTab.setValue("C_UOM_ID", mo.getC_UOM_ID());
	 		
			mTab.setValue("QtyEntered", mo.getQtyRequired());
			mTab.setValue("User1_ID", mo.getUser1_ID()==0 ?  null : mo.getUser1_ID());
			mTab.setValue("User2_ID", mo.getUser2_ID()==0 ?  null : mo.getUser2_ID());
			mTab.setValue("C_Activity_ID", mo.getC_Activity_ID()==0 ? null: mo.getC_Activity_ID());
			mTab.setValue("C_Project_ID", mo.getC_Project_ID()==0 ? null:mo.getC_Project_ID());
			mTab.setValue("C_ProjectPhase_ID", mo.getC_ProjectPhase_ID()==0 ? null:mo.getC_ProjectPhase_ID());
			mTab.setValue("C_ProjectTask_ID", mo.getC_ProjectTask_ID()==0 ? null:mo.getC_ProjectTask_ID());
			if(M_Product_ID<=0)
			{
				mTab.setValue("PriceEntered", mo.getDS_Budget());
				mTab.setValue("PriceActual", mo.getDS_Budget());
			}
			int C_UOM_To_ID = (Integer)mTab.getValue("C_UOM_ID");
			QtyEntered = mo.getQtyRequired();
			QtyOrdered = MUOMConversion.convertProductFrom (ctx, M_Product_ID, C_UOM_To_ID, QtyEntered);
			if (QtyOrdered == null)
				QtyOrdered = QtyEntered;
			boolean conversion = QtyEntered.compareTo(QtyOrdered) != 0;
			PriceActual = (BigDecimal)mTab.getValue("PriceActual");
			PriceEntered = MUOMConversion.convertProductFrom (ctx, M_Product_ID,C_UOM_To_ID, PriceActual);
			if (PriceEntered == null)
				PriceEntered = PriceActual; 
			log.fine("UOM=" + C_UOM_To_ID + ", QtyEntered/PriceActual=" + QtyEntered + "/" + PriceActual	+ " -> " +
					conversion	+ " QtyOrdered/PriceEntered=" + QtyOrdered + "/" + PriceEntered);
			Env.setContext(ctx, WindowNo, "UOMConversion", conversion ? "Y" : "N");
			mTab.setValue("QtyOrdered", QtyOrdered);
			mTab.setValue("PriceEntered", PriceEntered);
		}
		else
		{
			MDSServiceRequest mo= new MDSServiceRequest(ctx, requestDetID, null);
			M_Product_ID=mo.getM_Product_ID();
	 		if(M_Product_ID!=0)
				mTab.setValue("M_Product_ID", M_Product_ID);
			mTab.setValue("Description", mo.getProductDescription());
			mTab.setValue("QtyEntered", mo.getQtyRequired());
			mTab.setValue("C_Charge_ID", mo.get_Value("C_Charge_ID"));
		}
		
		return "";
	}

}
