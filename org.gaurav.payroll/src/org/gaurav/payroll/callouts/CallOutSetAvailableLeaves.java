package org.gaurav.payroll.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.payroll.model.MGSHREmpLeave;

public class CallOutSetAvailableLeaves implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer employee_ID = (Integer)mTab.getValue("GS_HR_Employee_ID");
		Integer leaveMaster_Id = (Integer)mTab.getValue("GS_HR_Leave_Master_ID");
		BigDecimal leavesApplied = (BigDecimal)mTab.getValue("GS_HR_RequiredLeaves");
		BigDecimal leavesApproved = (BigDecimal)mTab.getValue("GS_HR_LeavesApproved");
		
		if(employee_ID==null || leaveMaster_Id==null)
			return null ;
		
		int leaveBalance_ID = DB.getSQLValue(null, "Select GS_HR_Emp_Leave_ID From GS_HR_Emp_Leave Where GS_HR_Leave_Master_ID=? and GS_HR_Employee_ID= ? ",leaveMaster_Id,employee_ID);
		if(leaveBalance_ID>0)
		{
			MGSHREmpLeave empLeave = new MGSHREmpLeave(ctx, leaveBalance_ID, null);
			BigDecimal leaveAllowed = empLeave.getGS_HR_LeavesAllowed();
			mTab.setValue("GS_HR_LeavesAllowed", leaveAllowed);
			if(leavesApplied.compareTo(Env.ZERO)>0)
			{
				if(leavesApplied.compareTo(leaveAllowed)>0)
				{
					mTab.setValue("GS_HR_RequiredLeaves", leaveAllowed);
					mTab.fireDataStatusEEvent("Allowed", leavesApplied+" > "+leaveAllowed, true);
				}
			}
		}
		if(mField.getColumnName().equalsIgnoreCase("GS_HR_LeavesApproved"))
		{
			BigDecimal leaveAllowed = (BigDecimal)mTab.getValue("GS_HR_LeavesAllowed");
			if(leavesApproved.compareTo(leaveAllowed)>0)
			{
				mTab.setValue("GS_HR_LeavesApproved", leaveAllowed);
				mTab.fireDataStatusEEvent("Allowed", leavesApproved+" > "+leaveAllowed, true);
			}
		}
		return null;
	}


}
