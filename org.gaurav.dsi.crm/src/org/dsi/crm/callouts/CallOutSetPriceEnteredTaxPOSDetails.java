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
		}
		if(mField.getColumnName().equalsIgnoreCase("M_Product_ID"))
		{
			QtyOrdered = (BigDecimal)mTab.getValue("QtyOrdered");
			M_Product_ID = (Integer)value;
		}
		if(M_Product_ID!=null)
		{
			int M_PriceList_ID = DB.getSQLValue(null, "Select M_PriceList_ID from M_PriceList "
					+ "Where DS_IsDSPOSPriceList='Y' and AD_Client_ID = ? ",Env.getAD_Client_ID(ctx));
			int precision = MPriceList.getPricePrecision(ctx, M_PriceList_ID);
			
			BigDecimal priceStnd = DB.getSQLValueBD(null, "Select price.PriceStd "
					+ "From M_PriceList_Version prv,M_PriceList pl,M_ProductPrice price "
					+ "Where pl.DS_IsDSPOSPriceList='Y' "
					+ "and pl.M_PriceList_ID=prv.M_PriceList_ID "
					+ "and prv.M_PriceList_Version_ID=price.M_PriceList_Version_ID "
					+ "and price.M_Product_ID = ? order by validfrom desc ",M_Product_ID);
			
			if(priceStnd!=null)
			{
				int C_Tax_ID = Tax.getProduct(ctx, M_Product_ID, today, today, AD_Org_ID, M_Warehouse_ID, C_BPartner_Location_ID, C_BPartner_Location_ID, true, null);
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
			}
		}
		
		return null;
	}
}
