package com.gaurav.dsi.purchase.processes;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MRequest;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class CreatePurchaseRequestFromFacilityRequest extends SvrProcess
{
	int p_R_RequestType_ID = 0 ;
	int R_Group_ID = 0 ;
	int DS_Request_SubGroup_ID = 0 ;
	MRequest facilityRequest; 
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("R_RequestType_ID"))
				p_R_RequestType_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("R_Group_ID"))
				R_Group_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DS_Request_SubGroup_ID"))
				DS_Request_SubGroup_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		facilityRequest = new MRequest(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		MRequest purchaseRequest = new MRequest(getCtx(), 0, get_TrxName());
		purchaseRequest.setR_RequestType_ID(p_R_RequestType_ID);
		purchaseRequest.setR_Group_ID(R_Group_ID);
		purchaseRequest.setSalesRep_ID(getAD_User_ID());
		purchaseRequest.setSummary(facilityRequest.getSummary());
		purchaseRequest.setAD_Table_ID(facilityRequest.get_Table_ID());
		purchaseRequest.setRecord_ID(facilityRequest.getR_Request_ID());
		purchaseRequest.setR_RequestRelated_ID(getRecord_ID());
		purchaseRequest.setSalesRep_ID(getAD_User_ID());
		purchaseRequest.saveEx();
		
		facilityRequest.setR_RequestRelated_ID(purchaseRequest.getR_Request_ID());
		facilityRequest.setResult("Purchase Request generated");
		facilityRequest.saveEx();
		addLog("Purchase Request Created ");
		addLog(purchaseRequest.getR_Request_ID(),purchaseRequest.getStartDate(),Env.ZERO,purchaseRequest.getSummary(),purchaseRequest.get_Table_ID(),purchaseRequest.getR_Request_ID());;
		return null;
	}
}
