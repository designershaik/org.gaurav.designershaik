package org.gaurav.payroll.callouts;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.TimeUtil;

public class CallOutCalculateLeaveDaysBasedOnRequiredLeaves implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, 
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		BigDecimal leavesApplied = (BigDecimal)value;
		Timestamp startDate = (Timestamp)mTab.getValue("StartDate");
		mTab.setValue("EndDate", TimeUtil.addDays(startDate, leavesApplied.intValue()));
		
		return null;
	}



}
