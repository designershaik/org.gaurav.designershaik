package org.gaurav.payroll.callouts;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.payroll.model.MGSHREmployee;

public class CallOutSetCurrentLoanBalance implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(value==null)
			return null;
		
		int GS_HR_Employee_ID = (Integer)value;
		int GS_HR_Compensation_Master_ID = (Integer)mTab.getValue("GS_HR_Compensation_Master_ID");
		Timestamp startDate = (Timestamp)mTab.getValue("StartDate");
		MGSHREmployee employee = new MGSHREmployee(ctx, GS_HR_Employee_ID, null);
		BigDecimal loanAmt = employee.getLoanAmt(startDate,GS_HR_Compensation_Master_ID);
		mTab.setValue("GS_ExistingLoanAmt",loanAmt);
		
		return null;
	}


}
