package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCost;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MProduct;
import org.compiere.util.Env;
import org.gaurav.dsi.model.GetProductCosts;

public class CallOutSetCostIfThePriceProduct implements IColumnCallout{

	private static final int AD_Client_ID = 0;

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer M_Product_ID = (Integer)value;
		BigDecimal priceEntered = (BigDecimal)mTab.getValue("PriceEntered");
		priceEntered = priceEntered ==null ? Env.ZERO:priceEntered;
		Integer C_Order_ID = (Integer)mTab.getValue("C_Order_ID");
		Integer C_Invoice_ID = (Integer)mTab.getValue("C_Invoice_ID");
		int C_Currency_ID = 0;
		Timestamp ConvDate = null;
		int C_ConversionType_ID = 0;
		int AD_Org_ID = 0;
		if(C_Order_ID!=null)
		{
			MOrder order = new MOrder(ctx, C_Order_ID, null);
			if(!order.isSOTrx())
				return null;
			C_Currency_ID = order.getC_Currency_ID();
			ConvDate = order.getDateAcct();
			C_ConversionType_ID = order.getC_ConversionType_ID();
			AD_Org_ID = order.getAD_Org_ID();
		}
		if(C_Invoice_ID!=null)
		{
			MInvoice invoice = new MInvoice(ctx, C_Invoice_ID, null);
			if(!invoice.isSOTrx())
				return null;
			C_Currency_ID = invoice.getC_Currency_ID();
			ConvDate = invoice.getDateAcct();
			C_ConversionType_ID = invoice.getC_ConversionType_ID();
			AD_Org_ID = invoice.getAD_Org_ID();
		}
		GetProductCosts productCost = new GetProductCosts();
		MProduct product = new MProduct(ctx, M_Product_ID, null);
		
		MAcctSchema[] schema=MAcctSchema.getClientAcctSchema(Env.getCtx(),Env.getAD_Client_ID(Env.getCtx()));
		int id =schema[0].getC_Currency_ID();
		
		MCost assemblyCost = productCost.getProductCost(product);
		BigDecimal cost = assemblyCost.getCurrentCostPrice().setScale(2, RoundingMode.HALF_UP);
		BigDecimal convertedCost = MConversionRate.convert(ctx, cost,id, C_Currency_ID, ConvDate, C_ConversionType_ID, Env.getAD_Client_ID(ctx), AD_Org_ID, false);
		mTab.setValue("PriceCost", convertedCost);
//		if(priceEntered.compareTo(Env.ZERO)==0)
//			mTab.setValue("PriceEntered", convertedCost);
				
		return null;
	}
}
