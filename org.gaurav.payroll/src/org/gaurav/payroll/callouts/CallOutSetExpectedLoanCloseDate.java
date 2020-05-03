package org.gaurav.payroll.callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

public class CallOutSetExpectedLoanCloseDate implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		String column = mField.getColumnName();
		if (value == null)
			return "";
		Timestamp DateAcct = null;
		BigDecimal noOfInstallments = Env.ZERO ;
		
		if(column.equalsIgnoreCase("StartDate"))
		{
			DateAcct  = (Timestamp)value;
			noOfInstallments = (BigDecimal)mTab.getValue("GS_HR_Installments");
		}
		if(column.equalsIgnoreCase("GS_HR_Installments"))
		{
			noOfInstallments = (BigDecimal)value;
			DateAcct  = (Timestamp)mTab.getValue("StartDate");
		}
		System.out.println(noOfInstallments.setScale(0, RoundingMode.CEILING).intValue());
		noOfInstallments = (BigDecimal)mTab.getValue("GS_HR_Installments");
		Timestamp loanEndDate = TimeUtil.addMonths(DateAcct, noOfInstallments.setScale(0, RoundingMode.CEILING).intValue());
		mTab.setValue("ExpectedCloseDate", loanEndDate);
		
		return null;
	}

}
