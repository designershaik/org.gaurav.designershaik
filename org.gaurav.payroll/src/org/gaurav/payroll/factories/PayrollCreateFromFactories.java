package org.gaurav.payroll.factories;

import org.compiere.grid.ICreateFrom;
import org.compiere.grid.ICreateFromFactory;
import org.compiere.model.GridTab;
import org.compiere.model.MJournal;
import org.gaurav.payroll.form.WCreateLoanEntriesUI;

public class PayrollCreateFromFactories implements ICreateFromFactory{

	@Override
	public ICreateFrom create(GridTab mTab) {
		
		String tableName = mTab.getTableName();
		if(tableName.equals(MJournal.Table_Name))
			return new WCreateLoanEntriesUI(mTab);
		
		return null;
	}


}
