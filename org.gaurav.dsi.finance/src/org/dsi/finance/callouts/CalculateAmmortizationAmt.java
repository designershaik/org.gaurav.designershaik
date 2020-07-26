package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CalculateAmmortizationAmt implements IColumnCallout{

	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		BigDecimal qtyInvoiced = (BigDecimal)mTab.getValue("QtyInvoiced");
		BigDecimal listPrice = (BigDecimal)mTab.getValue("PriceList");
		BigDecimal standardPrice = (BigDecimal)mTab.getValue("PriceActual");
		if(qtyInvoiced!=null && listPrice!=null && standardPrice!=null)
		{
			BigDecimal amortizationAmt = (qtyInvoiced.multiply(listPrice)).subtract(qtyInvoiced.multiply(standardPrice));
			mTab.setValue("DS_Amortization_Amt", amortizationAmt);
		}
		return null;
	}

}
