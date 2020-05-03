package org.dsi.finance.factories;

import org.compiere.grid.ICreateFrom;
import org.compiere.grid.ICreateFromFactory;
import org.compiere.model.GridTab;
import org.compiere.model.I_C_BankStatement;
import org.compiere.model.MPayment;
import org.dsi.finance.form.WCreateFromBankStatementUI;
import org.dsi.finance.form.WCreateFromPaymentUI;

public class DSIFinanceCreateFromfactory implements ICreateFromFactory{

	@Override
	public ICreateFrom create(GridTab mTab) 
	{
		String tableName = mTab.getTableName();
		if(tableName.equals(I_C_BankStatement.Table_Name))
			return new WCreateFromBankStatementUI(mTab);
		
		if(tableName.equals(MPayment.Table_Name))
			return new WCreateFromPaymentUI(mTab);
		
		return null;
	}

}
