package org.dsi.finance.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBankAccount;

public class CallOutPaymentCurrency implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue)
	{
		if(value==null)
			return null;
		
		int accountID=(Integer)value;
		MBankAccount ba=new MBankAccount(ctx, accountID, null);
		mTab.setValue("C_Currency_ID", ba.getC_Currency_ID());
		mTab.setValue("BankAccountType", ba.getBankAccountType());
		
		return null;
	}

}
