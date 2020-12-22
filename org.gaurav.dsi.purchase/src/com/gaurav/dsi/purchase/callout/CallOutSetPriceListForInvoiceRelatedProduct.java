package com.gaurav.dsi.purchase.callout;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;
import org.adempiere.base.Core;
import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IProductPricing;
import org.adempiere.model.GridTabWrapper;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_C_InvoiceLine;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutSetPriceListForInvoiceRelatedProduct implements IColumnCallout
{
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		Integer M_Product_ID = (Integer)value;
		if (M_Product_ID == null || M_Product_ID.intValue() == 0)
			return "";
	
		/*****	Price Calculation see also qty	****/
		boolean IsSOTrx = Env.getContext(ctx, WindowNo, "IsSOTrx").equals("Y");
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
		BigDecimal Qty = (BigDecimal)mTab.getValue("QtyInvoiced");
		IProductPricing pp = Core.getProductPricing();
		pp.setInitialValues(M_Product_ID.intValue(), C_BPartner_ID, Qty, IsSOTrx, null);
		Timestamp invoiceDate = Env.getContextAsDate(ctx, WindowNo, "DateInvoiced");
		pp.setPriceDate(invoiceDate);
		I_C_InvoiceLine invoiceLine = GridTabWrapper.create(mTab, I_C_InvoiceLine.class);
		int M_PriceList_ID = invoiceLine.getC_Invoice().getM_PriceList_ID();
		//
		pp.setM_PriceList_ID(M_PriceList_ID);

		/** PLV is only accurate if PL selected in header */
		int M_PriceList_Version_ID = Env.getContextAsInt(ctx, WindowNo, "M_PriceList_Version_ID");
		if ( M_PriceList_Version_ID == 0 && M_PriceList_ID > 0)
		{
			String sql = "SELECT plv.M_PriceList_Version_ID "
				+ "FROM M_PriceList_Version plv "
				+ "WHERE plv.M_PriceList_ID=? "						//	1
				+ " AND plv.ValidFrom <= ? "
				+ "ORDER BY plv.ValidFrom DESC";
			//	Use newest price list - may not be future
			
			M_PriceList_Version_ID = DB.getSQLValueEx(null, sql, M_PriceList_ID, invoiceDate);
			if ( M_PriceList_Version_ID > 0 )
				Env.setContext(ctx, WindowNo, "M_PriceList_Version_ID", M_PriceList_Version_ID );
		}
		
		if(M_PriceList_Version_ID>0)
		{
			mTab.setValue("PriceList", pp.getPriceList());
			mTab.setValue("PriceLimit", pp.getPriceLimit());
			mTab.setValue("PriceActual", pp.getPriceStd());
			mTab.setValue("PriceEntered", pp.getPriceStd());
			mTab.setValue("C_Currency_ID", Integer.valueOf(pp.getC_Currency_ID()));
			mTab.setValue("C_UOM_ID", Integer.valueOf(pp.getC_UOM_ID()));
			Env.setContext(ctx, WindowNo, "EnforcePriceLimit", pp.isEnforcePriceLimit() ? "Y" : "N");
			Env.setContext(ctx, WindowNo, "DiscountSchema", pp.isDiscountSchema() ? "Y" : "N");
		}

		return null;
	}
}
