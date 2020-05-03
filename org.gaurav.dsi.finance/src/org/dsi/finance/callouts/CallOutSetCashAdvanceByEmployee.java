package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAccount;
import org.compiere.util.DB;

public class CallOutSetCashAdvanceByEmployee implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer C_BankAccount_ID = (Integer)mTab.getValue("C_BankAccount_ID");
		Integer C_BPartner_ID = (Integer)mTab.getValue("DS_LiquidatedGivenTo_ID");
		if(C_BPartner_ID!=null && C_BankAccount_ID!=null)
		{
			String bankaccounttype = mTab.get_ValueAsString("BankAccountType");
			if(bankaccounttype.equalsIgnoreCase("B"))
			{
				int validCombination_ID = DB.getSQLValue(null, "Select B_PaymentSelect_Acct From C_BankAccount_Acct Where C_BankAccount_ID = ? ",C_BankAccount_ID); 
				MAccount combination = MAccount.get(ctx, validCombination_ID);
				BigDecimal cashAdvance = DB.getSQLValueBD(null, "Select coalesce(sum(AmtAcctDr),0)-coalesce(sum(AmtAcctCr),0) "
						+ "From Fact_Acct where C_BPartner_ID = ? and Account_ID = ? ", C_BPartner_ID,combination.getAccount_ID());
				mTab.setValue("DS_CashAdvanceEmployee", cashAdvance);
			}
		}
		return null;
	}
}
