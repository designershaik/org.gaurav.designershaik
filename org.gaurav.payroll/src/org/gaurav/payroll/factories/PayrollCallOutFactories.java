package org.gaurav.payroll.factories;

import java.util.ArrayList;
import java.util.List;
import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.gaurav.payroll.callouts.CallOutCalculateInstallmentPerMonth;
import org.gaurav.payroll.callouts.CallOutCalculateLeaveDaysBasedOnRequiredLeaves;
import org.gaurav.payroll.callouts.CallOutSetAvailableLeaves;
import org.gaurav.payroll.callouts.CallOutSetCurrentLoanBalance;
import org.gaurav.payroll.callouts.CallOutSetExpectedLoanCloseDate;
import org.gaurav.payroll.callouts.CallOutSetUserFromEmployee;
import org.gaurav.payroll.model.MGSHREmployeeAdvance;
import org.gaurav.payroll.model.MGSHRLeaveApplication;
import org.gaurav.payroll.model.X_GS_HR_EmployeeAdvance;


public class PayrollCallOutFactories implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) {
		
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MGSHRLeaveApplication.Table_Name) 
				&& columnName.equalsIgnoreCase(MGSHRLeaveApplication.COLUMNNAME_GS_HR_Employee_ID))
			list.add(new CallOutSetUserFromEmployee());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_GS_HR_Employee_ID))
			list.add(new CallOutSetUserFromEmployee());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_GS_HR_LoanAmt))
			list.add(new CallOutCalculateInstallmentPerMonth());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_GS_HR_Installments))
			list.add(new CallOutCalculateInstallmentPerMonth());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_GS_InstallmentAmt))
			list.add(new CallOutCalculateInstallmentPerMonth());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_StartDate))
			list.add(new CallOutSetExpectedLoanCloseDate());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_GS_HR_Installments))
			list.add(new CallOutSetExpectedLoanCloseDate());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_GS_HR_Employee_ID))
			list.add(new CallOutSetCurrentLoanBalance());
		
		if(tableName.equalsIgnoreCase(MGSHREmployeeAdvance.Table_Name) 
				&& columnName.equalsIgnoreCase(X_GS_HR_EmployeeAdvance.COLUMNNAME_DS_HR_ApprovedAmt))
			list.add(new CallOutCalculateInstallmentPerMonth());
	
		if(tableName.equalsIgnoreCase(MGSHRLeaveApplication.Table_Name) 
				&& columnName.equalsIgnoreCase(MGSHRLeaveApplication.COLUMNNAME_GS_HR_Employee_ID))
			list.add(new CallOutSetAvailableLeaves());
		
		if(tableName.equalsIgnoreCase(MGSHRLeaveApplication.Table_Name) 
				&& columnName.equalsIgnoreCase(MGSHRLeaveApplication.COLUMNNAME_GS_HR_Leave_Master_ID))
			list.add(new CallOutSetAvailableLeaves());
		
		if(tableName.equalsIgnoreCase(MGSHRLeaveApplication.Table_Name) 
				&& columnName.equalsIgnoreCase(MGSHRLeaveApplication.COLUMNNAME_GS_HR_RequiredLeaves))
			list.add(new CallOutSetAvailableLeaves());
		
		if(tableName.equalsIgnoreCase(MGSHRLeaveApplication.Table_Name) 
				&& columnName.equalsIgnoreCase(MGSHRLeaveApplication.COLUMNNAME_GS_HR_RequiredLeaves))
			list.add(new CallOutCalculateLeaveDaysBasedOnRequiredLeaves());
		
		if(tableName.equalsIgnoreCase(MGSHRLeaveApplication.Table_Name) 
				&& columnName.equalsIgnoreCase("GS_HR_LeavesApproved"))
			list.add(new CallOutSetAvailableLeaves());
			
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}

}
