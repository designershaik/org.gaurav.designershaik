package org.gaurav.payroll.process;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MNote;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.gaurav.payroll.model.MGSHRApprovals;
import org.gaurav.payroll.model.MGSHREmployee;
import org.gaurav.payroll.model.MGSHREmployeeAdvance;
import org.gaurav.payroll.model.MGSHRLeaveApplication;

public class SubmitForApproval extends SvrProcess {

	int p_id = 0;
	PO p_Object = null;
	String isApproved = "N";
	String p_Resulst = ""; 
	int AD_Table_ID = 0;
	String applied = "";
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
		p_id = getRecord_ID();
		AD_Table_ID = getTable_ID();
		if(AD_Table_ID==MGSHREmployeeAdvance.Table_ID)
		{
			MGSHREmployeeAdvance advance = new MGSHREmployeeAdvance(getCtx(), p_id, get_TrxName());
			p_Object = (PO)advance;
		}
		if(AD_Table_ID==MGSHRLeaveApplication.Table_ID)
		{
			MGSHRLeaveApplication application = new MGSHRLeaveApplication(getCtx(), p_id, get_TrxName());
			p_Object = (PO)application;
		}
	}

	@Override
	protected String doIt() throws Exception {
		
		MUser forwardedTo = null;
		String forwardedUser = "";
		int AD_User_ID = p_Object.get_ValueAsInt("GS_HR_Approval_ID");
		MUser user = new MUser(getCtx(), AD_User_ID, get_TrxName());
		MUser currentLoggedInUser = new MUser(getCtx(), getAD_User_ID(), get_TrxName());
		int currentApproval_ID = 0;
		if(isApproved.equalsIgnoreCase("Y"))
		{
			forwardedTo = new MUser(getCtx(), user.getSupervisor_ID(), get_TrxName());
			if(currentLoggedInUser.get_ValueAsBoolean("BTW_IsHRAdmin"))
			{
				BigDecimal approvalAmt = (BigDecimal)p_Object.get_Value("DS_HR_ApprovedAmt");
				if(approvalAmt!=null && approvalAmt.compareTo(Env.ZERO)<=0)
					throw new AdempiereException("Mandatory approval Amount.");
				p_Object.set_ValueNoCheck("IsApproved", true);
				p_Object.set_ValueNoCheck("Status",MGSHREmployeeAdvance.STATUS_Approved);
			}
			else
				p_Object.set_ValueNoCheck("Status",MGSHREmployeeAdvance.STATUS_InProgress);
		}
		if((isApproved.equalsIgnoreCase("N") || isApproved.equalsIgnoreCase("H")) && !p_Resulst.isEmpty())
		{
			forwardedTo = new MUser(getCtx(), p_Object.getCreatedBy(), get_TrxName());
			p_Object.set_ValueNoCheck("IsApproved", false);
			p_Object.set_ValueNoCheck("DS_RejectionResult",p_Object.get_Value("DS_RejectionResult")==null ? "":p_Object.get_Value("DS_RejectionResult")+"\n"+p_Resulst);
			p_Object.set_ValueNoCheck("Status",MGSHREmployeeAdvance.STATUS_Hold);
		}
		if(forwardedTo!=null)
		{
			currentApproval_ID = forwardedTo.getAD_User_ID();
			p_Object.set_ValueNoCheck("GS_HR_Approval_ID",currentApproval_ID);
			forwardedUser = forwardedTo.getName();
		}
		else
		{
			currentApproval_ID = user.getSupervisor_ID();
			p_Object.set_ValueNoCheck("GS_HR_Approval_ID",currentApproval_ID);
			forwardedUser = user.getSupervisor().getName();
		}
		p_Object.saveEx();
		
		MGSHRApprovals approval = new MGSHRApprovals(getCtx(), 0, get_TrxName());
		approval.set_ValueNoCheck(p_Object.get_TableName()+"_ID", p_id);
		if(forwardedTo!=null)
			approval.setGS_HR_Approval_ID(forwardedTo.getAD_User_ID());
		else
			approval.setGS_HR_Approval_ID(user.getSupervisor_ID());
		approval.setDS_DateApproved(new Timestamp(System.currentTimeMillis()));
		approval.saveEx();
		
		MGSHREmployee emp = new MGSHREmployee(getCtx(), p_Object.get_ValueAsInt("GS_HR_Employee_ID"), get_TrxName());
		
		MNote note = new MNote(getCtx(), 863, currentApproval_ID, p_Object.get_Table_ID(), p_id,
					p_Object.get_ValueAsString("DocumentNo")+ " / "+emp.getName(), 
					p_Object.get_ValueAsString("GS_HR_Reason").concat("\n").concat(" Applied: ")+p_Object.get_Value("GS_HR_LoanAmt")+("\n").concat(" Approved: ")+p_Object.get_Value("DS_HR_ApprovedAmt"), get_TrxName());
		note.saveEx();
		
		
		return Msg.getMsg(getCtx(), "Forwarded to: "+forwardedUser);
	}

}
