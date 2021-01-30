package org.gaurav.payroll.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutSetApprovalForDailyAttendanceCorrections implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		boolean isApproved = (boolean)value;
		boolean oldValueOfIsApproved = (boolean)oldValue;
		if(isApproved)
			mTab.setValue("GS_HR_Approval_ID", Env.getAD_User_ID(ctx));
		if(!isApproved && !oldValueOfIsApproved)
			mTab.setValue("GS_HR_Approval_ID", null);
		
		return null;
	}



}
