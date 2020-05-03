package org.gaurav.payroll.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MNote;
import org.compiere.model.MUser;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.gaurav.payroll.model.MGSHRApprovals;
import org.gaurav.payroll.model.MGSHREmployeeAdvance;

public class SubmitLoanForApproval extends SvrProcess {

	int GS_HR_EmployeeAdvance_ID = 0;
	MGSHREmployeeAdvance advance = null;
	String isApproved = "N";
	String p_Resulst = ""; 
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DSI_IsApproved"))
				isApproved = para[i].getParameterAsString();
			else if (name.equals("Result"))
				p_Resulst = para[i].getParameterAsString();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		GS_HR_EmployeeAdvance_ID = getRecord_ID();
		advance = new MGSHREmployeeAdvance(getCtx(), GS_HR_EmployeeAdvance_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		
		MUser forwardedTo = null;
		String forwardedUser = "";
		int AD_User_ID = advance.getGS_HR_Approval_ID();
		MUser user = new MUser(getCtx(), AD_User_ID, get_TrxName());
		MUser currentLoggedInUser = new MUser(getCtx(), getAD_User_ID(), get_TrxName());
		int currentApproval_ID = 0;
		if(isApproved.equalsIgnoreCase("Y"))
		{
			forwardedTo = new MUser(getCtx(), user.getSupervisor_ID(), get_TrxName());
			if(currentLoggedInUser.get_ValueAsBoolean("BTW_IsHRAdmin"))
			{
				if(advance.getDS_HR_ApprovedAmt().compareTo(Env.ZERO)<=0)
					throw new AdempiereException("Mandatory approval Amount.");
				advance.setIsApproved(true);
				advance.setStatus(MGSHREmployeeAdvance.STATUS_Approved);
			}
			else
				advance.setStatus(MGSHREmployeeAdvance.STATUS_InProgress);
		}
		if((isApproved.equalsIgnoreCase("N") || isApproved.equalsIgnoreCase("H")) && !p_Resulst.isEmpty())
		{
			forwardedTo = new MUser(getCtx(), advance.getCreatedBy(), get_TrxName());
			advance.setIsApproved(false);
			advance.setDS_RejectionResult(advance.getDS_RejectionResult()==null ? "":advance.getDS_RejectionResult()+"\n"+p_Resulst);
			advance.setStatus(MGSHREmployeeAdvance.STATUS_Hold);
		}
		if(forwardedTo!=null)
		{
			currentApproval_ID = forwardedTo.getAD_User_ID();
			advance.setGS_HR_Approval_ID(currentApproval_ID);
			forwardedUser = forwardedTo.getName();
		}
		else
		{
			currentApproval_ID = user.getSupervisor_ID();
			advance.setGS_HR_Approval_ID(currentApproval_ID);
			forwardedUser = user.getSupervisor().getName();
		}
		advance.saveEx();
		
		MGSHRApprovals approval = new MGSHRApprovals(getCtx(), 0, get_TrxName());
		approval.setGS_HR_EmployeeAdvance_ID(GS_HR_EmployeeAdvance_ID);
		if(forwardedTo!=null)
			approval.setGS_HR_Approval_ID(forwardedTo.getAD_User_ID());
		else
			approval.setGS_HR_Approval_ID(user.getSupervisor_ID());
		approval.setDS_DateApproved(new Timestamp(System.currentTimeMillis()));
		approval.saveEx();
		
		MNote note = new MNote(getCtx(), 863, currentApproval_ID, MGSHREmployeeAdvance.Table_ID, advance.getGS_HR_EmployeeAdvance_ID(),
				advance.getDocumentNo()+ " / "+advance.getGS_HR_Employee().getName(), 
				advance.getGS_HR_Reason().concat("\n").concat(" Applied Amount: ")+advance.getGS_HR_LoanAmt()+("\n").concat(" Approved Amount: ")+advance.getDS_HR_ApprovedAmt(), get_TrxName());
		note.saveEx();
		
		return Msg.getMsg(getCtx(), "Forwarded to: "+forwardedUser);
	}

}
