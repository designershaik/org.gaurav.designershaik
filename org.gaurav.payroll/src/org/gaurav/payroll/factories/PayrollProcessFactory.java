package org.gaurav.payroll.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.gaurav.payroll.process.AttendanceLogSheet;
import org.gaurav.payroll.process.CreateLoanPayment;
import org.gaurav.payroll.process.GenerateAttendance;
import org.gaurav.payroll.process.GenerateInstallments;
import org.gaurav.payroll.process.SubmitLoanForApproval;
import org.gaurav.payroll.process.YearCreateSalaryPeriods;

public class PayrollProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equals(AttendanceLogSheet.class.getName()))
			return new AttendanceLogSheet();
		
		if(className.equals(SubmitLoanForApproval.class.getName()))
			return new SubmitLoanForApproval();
		
		if(className.equals(GenerateAttendance.class.getName()))
			return new GenerateAttendance();
		
		if(className.equals(CreateLoanPayment.class.getName()))
			return new CreateLoanPayment();
		
		if(className.equals(GenerateInstallments.class.getName()))
			return new GenerateInstallments();
		
		if(className.equals(YearCreateSalaryPeriods.class.getName()))
			return new YearCreateSalaryPeriods();
		
		return null;
	}

}
