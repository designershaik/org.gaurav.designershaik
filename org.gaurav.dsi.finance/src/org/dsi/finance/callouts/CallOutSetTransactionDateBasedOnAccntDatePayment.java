package org.dsi.finance.callouts;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CallOutSetTransactionDateBasedOnAccntDatePayment implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {

		if(value==null)
			return null;
		
		Timestamp dateAcct = (Timestamp)value;
		mTab.setValue("DateTrx", dateAcct);
		
		return null;
	}



}
