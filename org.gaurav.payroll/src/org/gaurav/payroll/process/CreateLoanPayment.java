package org.gaurav.payroll.process;

import java.util.logging.Level;

import org.adempiere.base.Core;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
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
	int p_C_Activity_ID = 0 ;
	int p_User1_ID = 0 ;
	int p_User2_ID =0 ;
	int p_C_DocType_ID = 0 ;
	
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
			else if (name.equals("C_Activity_ID"))
				p_C_Activity_ID =para[i].getParameterAsInt();
			else if (name.equals("User1_ID"))
				p_User1_ID =para[i].getParameterAsInt();
			else if (name.equals("User2_ID"))
				p_User2_ID =para[i].getParameterAsInt();
			else if (name.equals("C_DocType_ID"))
				p_C_DocType_ID =para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		advance = new MGSHREmployeeAdvance(getCtx(), getRecord_ID(), get_TrxName());
		bp = new MBPartner(getCtx(), advance.getGS_HR_Employee().getC_BPartner_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		createInstallments(getRecord_ID(),get_TrxName());
		
		int C_BPartner_ID = advance.getGS_HR_Employee().getC_BPartner_ID();
		MBPartner bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
		MGSHRCompensationMaster compensation = new MGSHRCompensationMaster(getCtx(), advance.getGS_HR_Compensation_Master_ID(), get_TrxName());
	
		int M_PriceList_ID = bp.getPO_PriceList_ID();
		if(M_PriceList_ID<=0)
			M_PriceList_ID = DB.getSQLValue(get_TrxName(), "Select M_PriceList_ID from M_PriceList "
					+ "Where C_Currency_ID = ? and IsSoPriceList='N' "
					+ "and AD_Client_ID=? Order By IsDefault desc ",advance.getC_BankAccount().getC_Currency_ID(),getAD_Client_ID());
	
		MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
		invoice.setIsSOTrx(false);
		invoice.setBPartner(bp);
		invoice.setC_DocType_ID(p_C_DocType_ID);
		invoice.setM_PriceList_ID(M_PriceList_ID);
		invoice.setDescription(advance.getGS_HR_Reason());
		invoice.saveEx();
		
		MInvoiceLine line = new MInvoiceLine(invoice);
		line.setC_Charge_ID(compensation.getC_Charge_ID());
		line.setPrice(advance.getDS_HR_ApprovedAmt());
		line.setQty(Env.ONE);
		line.setC_Activity_ID(p_C_Activity_ID);
		line.setUser1_ID(p_User1_ID);
		line.setUser2_ID(p_User2_ID);
		line.saveEx();
		
		invoice.processIt(MInvoice.ACTION_Complete);
		if(invoice.save())
		{
			addLog(invoice.getC_Invoice_ID(), null, null, invoice.getDocumentNo(), MInvoice.Table_ID, invoice.getC_Invoice_ID());
			if(advance.getC_BankAccount_ID()<=0)
				throw new AdempiereException("Bank account mandatory");
			
			int C_DocTypePayment_ID = DB.getSQLValue(get_TrxName(), "SELECT C_DocType_ID FROM C_DocType "
					+ "Where BankAccountType LIKE ( ? ) and AD_Client_ID = ? AND DocBaseType = ? ", 
					"%"+advance.getC_BankAccount().getBankAccountType()+"%",getAD_Client_ID(),X_C_DocType.DOCBASETYPE_APPayment);
			
			MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
			payment.setC_BankAccount_ID(advance.getC_BankAccount_ID());
			payment.setAmount(advance.getC_BankAccount().getC_Currency_ID(), advance.getDS_HR_ApprovedAmt());
			payment.setC_DocType_ID(C_DocTypePayment_ID);
			payment.setTenderType(TenderType);
			payment.setCheckNo(CheckNo);
			payment.setC_BP_BankAccount_ID(C_BP_BankAccount_ID);
			payment.setC_BPartner_ID(C_BPartner_ID);
			payment.setDescription(advance.getGS_HR_Reason());
			payment.setC_Invoice_ID(invoice.getC_Invoice_ID());
			payment.saveEx();
			
			advance.setC_Payment_ID(payment.getC_Payment_ID());
			advance.setPayDate(payment.getDateAcct());
			advance.setProcessed(true);
			advance.saveEx();
			
			addLog(payment.getC_Payment_ID(), null, null, payment.getDocumentNo(), MPayment.Table_ID, payment.getC_Payment_ID());
		}
		
		return "@OK@";
	}

	public void createInstallments(int advance_ID,String trxName) 
	{
		int AD_Process_ID = DB.getSQLValue(trxName, "Select AD_Process_ID From AD_Process Where AD_Process_UU='bb78ad60-f8c3-4685-9cea-9d3d68c1f404'");
		
		ProcessInfoParameter pi1 = new ProcessInfoParameter("GS_HR_EmployeeAdvance_ID", advance_ID, "", "", "");
		ProcessInfo pi = new ProcessInfo("", AD_Process_ID, MGSHREmployeeAdvance.Table_ID,advance_ID);
		pi.setParameter(new ProcessInfoParameter[]{pi1});
		MProcess pr = new Query(Env.getCtx(), X_AD_Process.Table_Name,"AD_Process_ID=?", trxName).setParameters(AD_Process_ID).first();

		// Create an instance of the process I want to run
		ProcessCall processCall = null;
		boolean procSuccess = false;
		processCall = Core.getProcess(pr.getClassname());

		// Create process instance (mainly for logging/sync purpose)
		MPInstance mpi = new MPInstance(Env.getCtx(), pr.get_ID(),advance_ID);
		mpi.saveEx();

		// Connect the process to the process instance.
		pi.setAD_PInstance_ID(mpi.get_ID());
		procSuccess = processCall.startProcess(Env.getCtx(), pi, null);

		if (!procSuccess)
			log.info("not generated");
	}


}
