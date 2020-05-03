package org.gaurav.payroll.process;

import java.util.logging.Level;

import org.adempiere.base.Core;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MPInstance;
import org.compiere.model.MPayment;
import org.compiere.model.MProcess;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Process;
import org.compiere.model.X_C_DocType;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.payroll.model.MGSHRCompensationMaster;
import org.gaurav.payroll.model.MGSHREmployeeAdvance;

public class CreateLoanPayment extends SvrProcess{

	String TenderType = null;
	String CheckNo = null;
	int C_BP_BankAccount_ID = 0;
	MGSHREmployeeAdvance advance = null;
	MBPartner bp = null;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_BP_BankAccount_ID"))
				C_BP_BankAccount_ID =para[i].getParameterAsInt();
			else if (name.equals("CheckNo"))
				CheckNo =para[i].getParameterAsString();
			else if (name.equals("TenderType"))
				TenderType =para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		advance = new MGSHREmployeeAdvance(getCtx(), getRecord_ID(), get_TrxName());
		bp = new MBPartner(getCtx(), advance.getGS_HR_Employee().getC_BPartner_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		createInstallments();
		if(advance.getC_BankAccount_ID()<=0)
			throw new AdempiereException("Bank account mandatory");
		
		int C_DocTypePayment_ID = DB.getSQLValue(get_TrxName(), "SELECT C_DocType_ID FROM C_DocType "
				+ "Where BankAccountType like ? and AD_Client_ID = ? AND DocBaseType = ? ", 
				advance.getC_BankAccount().getBankAccountType(),getAD_Client_ID(),X_C_DocType.DOCBASETYPE_APPayment);
		
		MGSHRCompensationMaster compensation = new MGSHRCompensationMaster(getCtx(), advance.getGS_HR_Compensation_Master_ID(), get_TrxName());
		MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
		payment.setC_BankAccount_ID(advance.getC_BankAccount_ID());
		payment.setAmount(advance.getC_BankAccount().getC_Currency_ID(), advance.getDS_HR_ApprovedAmt());
		payment.setC_DocType_ID(C_DocTypePayment_ID);
		payment.setTenderType(TenderType);
		payment.setCheckNo(CheckNo);
		payment.setC_BP_BankAccount_ID(C_BP_BankAccount_ID);
		payment.setC_BPartner_ID(advance.getGS_HR_Employee().getC_BPartner_ID());
		payment.setDescription(advance.getGS_HR_Reason());
		payment.setC_Charge_ID(compensation.getC_Charge_ID());
		payment.saveEx();
		
		advance.setC_Payment_ID(payment.getC_Payment_ID());
		advance.setPayDate(payment.getDateAcct());
		advance.setProcessed(true);
		advance.saveEx();
		
		addLog(payment.getC_Payment_ID(), null, null, payment.getDocumentNo(), MPayment.Table_ID, payment.getC_Payment_ID());
		
		return "@OK@";
	}

	private void createInstallments() 
	{
		int AD_Process_ID = DB.getSQLValue(get_TrxName(), "Select AD_Process_ID From AD_Process Where AD_Process_UU='bb78ad60-f8c3-4685-9cea-9d3d68c1f404'");
		
		ProcessInfo pi = new ProcessInfo("", AD_Process_ID, MGSHREmployeeAdvance.Table_ID,getRecord_ID());
		
		MProcess pr = new Query(Env.getCtx(), X_AD_Process.Table_Name,"AD_Process_ID=?", get_TrxName()).setParameters(AD_Process_ID).first();

		// Create an instance of the process I want to run
		ProcessCall processCall = null;
		boolean procSuccess = false;
		processCall = Core.getProcess(pr.getClassname());

		// Create process instance (mainly for logging/sync purpose)
		MPInstance mpi = new MPInstance(Env.getCtx(), pr.get_ID(),getRecord_ID());
		mpi.saveEx();

		// Connect the process to the process instance.
		pi.setAD_PInstance_ID(mpi.get_ID());
		procSuccess = processCall.startProcess(Env.getCtx(), pi, null);

		if (!procSuccess)
			log.info("not generated");
		
	}


}
