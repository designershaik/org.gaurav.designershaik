package org.gaurav.payroll.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.gaurav.payroll.model.MGSHREmployeeAdvance;
import org.gaurav.payroll.model.MGSHRInstallments;

public class GenerateInstallments extends SvrProcess{

	MGSHREmployeeAdvance advance = null;
	int p_emp_Advance_ID = 0;
	
	@Override
	protected void prepare() {
		
		p_emp_Advance_ID = getRecord_ID();		
		advance = new MGSHREmployeeAdvance(getCtx(), p_emp_Advance_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		BigDecimal approvedLoanAmt = advance.getDS_HR_ApprovedAmt();
		int totalInstallement = advance.getGS_HR_Installments().setScale(0, RoundingMode.DOWN).intValue();
		BigDecimal totalInstallments = Env.ZERO;
		int finalCount = 0;
		for(int i=0;i<totalInstallement;i++)
		{
			totalInstallments = totalInstallments.add(advance.getGS_InstallmentAmt());
			Timestamp payDate = TimeUtil.addMonths(advance.getStartDate(),i);
			MGSHRInstallments inst = new MGSHRInstallments(getCtx(), 0, get_TrxName());
			inst.setGS_HR_EmployeeAdvance_ID(p_emp_Advance_ID);
			inst.setRemainingAmt(approvedLoanAmt.subtract(totalInstallments));
			inst.setGS_InstallmentAmt(advance.getGS_InstallmentAmt());
			inst.setPayDate(payDate);
			inst.setHR_Break(false);
			inst.setIsPaid(false);
			inst.setLine(i+1);
			inst.save();
			
			finalCount = i;
		} 
		if((approvedLoanAmt.subtract(totalInstallments)).compareTo(Env.ZERO)!=0)
		{
			MGSHRInstallments inst = new MGSHRInstallments(getCtx(), 0, get_TrxName());
			inst.setGS_HR_EmployeeAdvance_ID(p_emp_Advance_ID);
			inst.setRemainingAmt(Env.ZERO);
			inst.setGS_InstallmentAmt(approvedLoanAmt.subtract(totalInstallments));
			inst.setPayDate(TimeUtil.addMonths(advance.getStartDate(),finalCount+1));
			inst.setHR_Break(false);
			inst.setIsPaid(false);
			inst.setLine(finalCount+1);
			inst.save();
		}
		return "@OK@";
	}

}
