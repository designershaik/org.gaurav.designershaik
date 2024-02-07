package com.gaurav.dsi.purchase.processes;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.adempiere.base.Core;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MGroup;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRequest;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Process;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Trx;

public class CreateCashRequestFromPurchaseRequest extends SvrProcess{

	int R_Request_ID = 0 ; 
	MRequest request = null;
	BigDecimal requestAmt = Env.ZERO;
	int p_R_RequestType_ID = 0 ;
	Trx trx = null ; 
	int RelatedCashRequest_ID = 0 ;
	MRequest cashRequest = null; 
	int p_C_BPartner_ID = 0;
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("RequestAmt"))
				requestAmt = para[i].getParameterAsBigDecimal();
			else if (name.equals("R_RequestType_ID"))
				p_R_RequestType_ID = para[i].getParameterAsInt();
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		R_Request_ID = getRecord_ID();
		request = new MRequest(getCtx(), R_Request_ID, get_TrxName());
		trx = Trx.get(get_TrxName(), false);
	}

	@Override
	protected String doIt() throws Exception {
		
		if(getAD_User_ID()!=request.getSalesRep_ID())
			throw new AdempiereException("You are not allowed to create cash Request");
		
		int AD_Process_ID = DB.getSQLValue(get_TrxName(), "Select AD_Process_ID From AD_Process Where AD_Process_UU='92c167bf-756b-4869-b086-06d62cd94a0b'");
		MUser user = new MUser(getCtx(), getAD_User_ID(), get_TrxName());
		RelatedCashRequest_ID = DB.getSQLValue(get_TrxName(), "Select R_Request_ID From R_Request Where R_RequestRelated_ID = ? ",R_Request_ID);
		if(RelatedCashRequest_ID==-1)
		{
			MGroup group = new MGroup(getCtx(), request.getR_Group_ID(), get_TrxName());
			int CashRequestGroup_ID = group.get_ValueAsInt("DS_RelatedCashRequestGroup_ID");
			int DS_SubGroup_ID = DB.getSQLValue(get_TrxName(), "Select DS_Request_SubGroup_ID From DS_Request_SubGroup Where R_Group_ID = ? ",CashRequestGroup_ID);
			cashRequest = new MRequest(getCtx(), getAD_User_ID(), p_R_RequestType_ID, request.getSummary(), false, get_TrxName());
			cashRequest.setRequestAmt(requestAmt);
			cashRequest.setR_Group_ID(CashRequestGroup_ID);
			cashRequest.set_ValueOfColumn("DS_Request_SubGroup_ID", DS_SubGroup_ID);
			cashRequest.setR_RequestRelated_ID(request.getR_Request_ID());
			cashRequest.setC_BPartner_ID(user.getC_BPartner_ID());
			cashRequest.set_ValueNoCheck("C_Currency_ID", request.get_Value("C_Currency_ID"));
			if(user.getC_BPartner_Location_ID()>0)
				cashRequest.set_ValueOfColumn("C_BPartner_Location_ID", user.getC_BPartner_Location_ID());
			cashRequest.saveEx();
			trx.commit();	
			
			request.setR_RequestRelated_ID(cashRequest.getR_Request_ID());
			request.setResult("Cash request generated: "+cashRequest.getDocumentNo());
			request.saveEx();
			
			ProcessInfoParameter pi1 = new ProcessInfoParameter("AD_User_ID", 0, "", "", "");
			ProcessInfoParameter pi2 = new ProcessInfoParameter("R_Request_ID", cashRequest.getR_Request_ID(), "", "", "");
			ProcessInfo pi = new ProcessInfo("", AD_Process_ID, getTable_ID(), cashRequest.getR_Request_ID());
			pi.setParameter(new ProcessInfoParameter[]{pi1,pi2});
			MProcess pr = new Query(Env.getCtx(), X_AD_Process.Table_Name, " AD_Process_ID = ? ", get_TrxName()).setParameters(AD_Process_ID).first();
			if(pr==null)
			{
				log.warning("Can't find process.");
			}
			ProcessCall processCall = null;
			boolean procSuccess = false;
			
			processCall = Core.getProcess(pr.getClassname());
			MPInstance mpi = new MPInstance(getCtx(), pr.get_ID(),getRecord_ID());
			mpi.saveEx();
			pi.setAD_PInstance_ID(mpi.get_ID());
			procSuccess = processCall.startProcess(getCtx(), pi, trx);
			if(!procSuccess)
			{
				log.warning("Process Failed "+pr.getClassname());
			}
		}
		else
			cashRequest = new MRequest(getCtx(), RelatedCashRequest_ID, get_TrxName());

		
		addLog(Msg.getMsg(getCtx(), "DS_CASHREQUESTFORWARDEDTOAPPROVAR").concat(cashRequest.getDocumentNo()));
		
		return "@CashRequestCreated@";
	}
}
