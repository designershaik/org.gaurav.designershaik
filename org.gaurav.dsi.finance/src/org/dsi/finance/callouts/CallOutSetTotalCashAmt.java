package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutSetTotalCashAmt implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		BigDecimal payAmt = (BigDecimal)value;
		BigDecimal totalCasAmt = (BigDecimal)mTab.getValue("DS_TotalCashBillAmt");
		if(totalCasAmt.compareTo(Env.ZERO)==0)
			mTab.setValue("DS_TotalCashBillAmt", payAmt);
		
		return null;
	}


}
