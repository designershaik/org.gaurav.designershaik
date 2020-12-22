package org.gaurav.payroll.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.gaurav.payroll.model.MGSHREmployee;

public class CallOutSetUserFromEmployee implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		int GS_HR_Employee_ID = (Integer)value;
		MGSHREmployee employee = new MGSHREmployee(ctx, GS_HR_Employee_ID, null);
		int AD_User_ID  = employee.getAD_User_ID();
		mTab.setValue("GS_HR_Approval_ID", AD_User_ID);
		
		return null;
	}


}