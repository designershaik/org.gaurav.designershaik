package com.gaurav.dsi.purchase.processes;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRequest;
import org.compiere.model.MRequestType;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class ApprovePurchaseRequest extends SvrProcess {

	int Request_ID = 0;
	MRequest request =null ;
	String p_isApprove = "N";
	boolean p_DS_SendBack = false;
	String p_Result = "";
	int p_AD_User_ID = 0 ;
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("IsApproved"))
				p_isApprove = para[i].getParameterAsString();
			else if (name.equals("DS_SendBack"))
				p_DS_SendBack = "Y".equals(para[i].getParameter());
			else if (name.equals("Reply_Remarks"))
				p_Result = para[i].getParameterAsString();
			else if (name.equals("AD_User_ID"))
				p_AD_User_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		Request_ID = getRecord_ID();
		request = new MRequest(getCtx(), Request_ID, get_TrxName());

	}

	@Override
	protected String doIt() throws Exception {
		
		int createdBy = request.getCreatedBy();
		if(p_AD_User_ID!=0)
			createdBy = p_AD_User_ID;
		Timestamp date = new Timestamp(System.currentTimeMillis());
		System.out.println(getAD_User_ID());
		if(p_isApprove.equals("Y"))
		{
			int exist = DB.getSQLValue(get_TrxName(), "Select count(rsp.*) From DSI_GroupResponsible rsp,R_Request rr"
					+ " Where rsp.DS_Request_SubGroup_ID= rr.DS_Request_SubGroup_ID and rsp.AD_User_ID = ? and rsp.DS_CanApprove='Y' and rr.R_Request_ID = ? ",getAD_User_ID(),Request_ID);
			
			if (createdBy == getAD_User_ID() || exist<1) 
				throw new AdempiereException(Msg.getMsg(getCtx(), "DS_NOTALLOWEDTOAPPROVEPURCHASEREQUEST"));
			
			MRequestType type = new MRequestType(getCtx(), request.getR_RequestType_ID(), get_TrxName());
			request.set_ValueOfColumn("DS_DateApproved", date);
			request.set_ValueOfColumn("DS_ApprovedBy_ID", Env.getAD_User_ID(getCtx()));
			request.set_ValueOfColumn("DS_Approve", "Y");
			if(type.get_ValueAsBoolean("DS_OnceApprovedSendToAccounts"))
				request.setSalesRep_ID(type.get_ValueAsInt("AD_User_ID"));
			else
				request.setSalesRep_ID(createdBy);
			request.set_ValueOfColumn("DSI_IsApproved", true);
			request.saveEx();
		}
		else
		{
			if(p_DS_SendBack)
			{
				request.setSalesRep_ID(createdBy);
				request.setResult(p_Result);
				request.saveEx();
			}
			else
			{
				int R_Status_ID = DB.getSQLValue(get_TrxName(), "Select stat.R_Status_ID "
						+ "From R_Request rr , R_RequestType type,R_Status stat "
						+ "Where rr.R_RequestType_ID = type.R_RequestType_ID and type.R_StatusCategory_ID=stat.R_StatusCategory_ID "
						+ "and stat.isFinalClose='Y' and stat.IsWebCanUpdate='N' ");
				request.setR_Status_ID(R_Status_ID);
				request.saveEx();
			}
		}
		
		return Msg.getMsg(Env.getCtx(), "DS_RequestApproved");
	}

}
