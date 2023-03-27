package org.gaurav.payroll.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.gaurav.payroll.process.CalculateSalary;
import org.gaurav.payroll.process.ConsolidateAttendance;
import org.gaurav.payroll.process.CreateAUBSalaryFile;
import org.gaurav.payroll.process.CreateLoanPayment;
import org.gaurav.payroll.process.FinalizeAttendance;
import org.gaurav.payroll.process.GenerateAttendance;
import org.gaurav.payroll.process.GenerateInstallments;
import org.gaurav.payroll.process.SubmitForApproval;
import org.gaurav.payroll.process.YearCreateSalaryPeriods;

public class PayrollProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equals(SubmitForApproval.class.getName()))
			return new SubmitForApproval();
		
		if(className.equals(GenerateAttendance.class.getName()))
			return new GenerateAttendance();
		
		if(className.equals(CreateLoanPayment.class.getName()))
			return new CreateLoanPayment();
		
		if(className.equals(GenerateInstallments.class.getName()))
			return new GenerateInstallments();
		
		if(className.equals(YearCreateSalaryPeriods.class.getName()))
			return new YearCreateSalaryPeriods();
		
		if(className.equals(ConsolidateAttendance.class.getName()))
			return new ConsolidateAttendance();
		
		if(className.equals(CalculateSalary.class.getName()))
			return new CalculateSalary();
		
		if(className.equals(FinalizeAttendance.class.getName()))
			return new FinalizeAttendance();
		
		if(className.equals(CreateAUBSalaryFile.class.getName()))
			return new CreateAUBSalaryFile();
		
		
		return null;
	}

}
