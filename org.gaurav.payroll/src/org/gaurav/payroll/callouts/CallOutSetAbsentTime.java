package org.gaurav.payroll.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutSetAbsentTime implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		BigDecimal workingHoursForADay = (BigDecimal)value;
		if(workingHoursForADay.compareTo(Env.ONE)>0)
			throw new AdempiereException("Salary Paid On Can't be more than one.");
		
		mTab.setValue("GS_HR_TotalDeduction", Env.ONE.subtract(workingHoursForADay));
		
		return null;
	}



}
