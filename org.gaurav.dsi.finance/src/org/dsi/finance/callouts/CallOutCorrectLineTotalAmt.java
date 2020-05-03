package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutCorrectLineTotalAmt implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(value==null)
			return null;
		
		BigDecimal lineNetAmt = (BigDecimal)mTab.getValue("LineNetAmt");
		BigDecimal taxAmt = (BigDecimal)mTab.getValue("TaxAmt");
		
		lineNetAmt = lineNetAmt == null ? Env.ZERO: lineNetAmt;
		taxAmt = taxAmt == null ? Env.ZERO: taxAmt;
		
		mTab.setValue("LineTotalAmt", lineNetAmt.add(taxAmt));
		
		return null;
	}


}
