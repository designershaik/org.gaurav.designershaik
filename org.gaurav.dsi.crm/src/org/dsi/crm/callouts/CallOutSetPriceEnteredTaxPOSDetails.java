package org.dsi.crm.callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MPriceList;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProductPrice;
import org.compiere.model.MTax;
import org.compiere.model.Tax;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.gaurav.dsi.model.MDSPOSHeader;

public class CallOutSetPriceEnteredTaxPOSDetails implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(value==null)
			return null;
		
		Calendar cal = TimeUtil.getToday();
		BigDecimal discount = Env.ZERO;
		Timestamp today = new Timestamp(cal.getTimeInMillis());
		int AD_Org_ID = Env.getAD_Org_ID(ctx);
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		BigDecimal QtyOrdered = Env.ZERO;
		Integer M_Product_ID = 0;
		Integer DS_POSHeader_ID = (Integer)mTab.getValue("DS_POSHeader_ID") ; 
		MDSPOSHeader header = new MDSPOSHeader(ctx, DS_POSHeader_ID, null);
		int M_Warehouse_ID = header.get_ValueAsInt("M_Warehouse_ID");
		int C_BPartner_Location_ID = DB.getSQLValue(null, "Select loc.C_BPartner_Location_ID From C_BPartner_Location loc,AD_ClientInfo inf "
				+ "Where loc.C_BPartner_ID=inf.C_BPartnerCashTrx_ID "
				+ "and inf.AD_Client_ID = ? ",AD_Client_ID);
		if(mField.getColumnName().equalsIgnoreCase("QtyOrdered"))
		{
			QtyOrdered = (BigDecimal)value;
			M_Product_ID = (Integer)mTab.getValue("M_Product_ID");
			discount = (BigDecimal)mTab.getValue("Discount");
		}
		if(mField.getColumnName().equalsIgnoreCase("M_Product_ID"))
		{
			QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
			M_Product_ID = (Integer)value;
			discount = (BigDecimal)mTab.getValue("Discount");
		}
		if(mField.getColumnName().equalsIgnoreCase("Discount"))
		{
			discount = (BigDecimal)value;
			QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
			M_Product_ID = (Integer)mTab.getValue("M_Product_ID");
		}
		if(M_Product_ID!=null)
		{
			int M_PriceList_ID = header.get_ValueAsInt("M_PriceList_ID");
			if(M_PriceList_ID<=0)
				M_PriceList_ID = DB.getSQLValue(null, "Select M_PriceList_ID from M_PriceList "
						+ "Where DS_IsDSPOSPriceList='Y' and AD_Client_ID = ? ",Env.getAD_Client_ID(ctx));
			if(M_PriceList_ID<=0)
				return "Please add pricelist for the customer";
			
			int precision = MPriceList.getPricePrecision(ctx, M_PriceList_ID);
			
			MPriceList pricelist = MPriceList.get(M_PriceList_ID, null);
			MPriceListVersion version =  pricelist.getPriceListVersion(today);
			MProductPrice productPrice = MProductPrice.get(ctx, version.getM_PriceList_Version_ID(), M_Product_ID, null);
			
			if(productPrice!=null)
			{
				BigDecimal priceStnd = productPrice.getPriceStd();
				if(discount.compareTo(Env.ZERO)!=0)
				{
					BigDecimal discountAmt = priceStnd.multiply(discount).divide(Env.ONEHUNDRED,2, RoundingMode.CEILING);
					priceStnd = priceStnd.subtract(discountAmt);
					mTab.setValue("DiscountAmt", discountAmt);
				}
				int C_Tax_ID = DB.getSQLValue(null, "Select C_Tax_ID From C_Tax Where GS_DefaultPOSTax='Y' and AD_Client_ID = ? ",header.getAD_Client_ID());
				if(C_Tax_ID<=0)
					C_Tax_ID = Tax.getProduct(ctx, M_Product_ID, today, today, AD_Org_ID, M_Warehouse_ID, C_BPartner_Location_ID, C_BPartner_Location_ID, true, null);
				MTax tax = new MTax(ctx, C_Tax_ID, null);
				BigDecimal rate = tax.getRate();
				BigDecimal lineNetAmt =  QtyOrdered.multiply(priceStnd);
				BigDecimal taxAmt = lineNetAmt.multiply(rate.divide(Env.ONEHUNDRED, precision, RoundingMode.CEILING));
				mTab.setValue("Price", priceStnd.setScale(precision, RoundingMode.CEILING));
				mTab.setValue("LineNetAmt", lineNetAmt.setScale(precision, RoundingMode.CEILING));
				mTab.setValue("TaxAmt", taxAmt.setScale(precision, RoundingMode.CEILING));
				mTab.setValue("LineTotalAmt", (lineNetAmt.add(taxAmt)).setScale(precision, RoundingMode.CEILING));
				//mTab.setValue("LineTotalAmt", lineNetAmt.setScale(precision, RoundingMode.CEILING));
				mTab.setValue("Rate", rate);
				mTab.setValue("C_Tax_ID", C_Tax_ID);
				mTab.setValue("PriceList", priceStnd.add(discount));
			}
			else
				return "Product is not the pricelist.";
		}
		
		return null;
	}
}
