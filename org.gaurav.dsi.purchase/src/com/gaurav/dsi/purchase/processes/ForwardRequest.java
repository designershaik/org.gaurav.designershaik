package com.gaurav.dsi.purchase.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRequest;
import org.compiere.model.MSysConfig;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.TimeUtil;

public class ForwardRequest extends SvrProcess{

	int Request_ID = 0;
	MRequest request = null ;
	int p_AD_User_ID = 0 ; 
	String p_Comments = "";
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_User_ID"))
				p_AD_User_ID = para[i].getParameterAsInt();
			else if (name.equals("R_Request_ID"))
				Request_ID = para[i].getParameterAsInt();
			else if (name.equals("Comments"))
				p_Comments = para[i].getParameterAsString();
			else
				
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		Request_ID = getRecord_ID();
		request = new MRequest(getCtx(), Request_ID, get_TrxName());
		
	}

	@Override
	protected String doIt() throws Exception {
		
		int requestTypeID=MSysConfig.getIntValue("PURCHASE_REQUEST_TYPE_ID", 1000004);
		
		if(request.getR_RequestType_ID()==requestTypeID || request.getR_RequestType_ID()==1000007)
		{
			int productEntryID=DB.getSQLValue(get_TrxName(), "SELECT coalesce(COUNT(*),0) FROM DS_Product_Request "
					+ "WHERE R_Request_ID=?",Request_ID);
			
			if(productEntryID==0)
				throw new AdempiereException(Msg.getMsg(getCtx(), "DS_REQUESTDETAILSAREMANDATORY"));
		}
		String msg = "@NoApprovalIsLoggedIn@";
		if(p_AD_User_ID!=0)
		{
			request.setSalesRep_ID(p_AD_User_ID);
			request.setResult(p_Comments);
			request.saveEx();
			return msg = "@ForwardedToPurchaser@";
		}
		int CurrentLoggedInUser = getAD_User_ID();
		
		
		
//		String sql = "Select rsp.AD_User_ID From DSI_GroupResponsible rsp,R_Request rr Where rsp.R_Group_ID= rr.R_Group_ID and rsp.DS_CanApprove='Y' and rr.R_Request_ID = ? order by rsp.Priority";
		String sql = "Select rsp.AD_User_ID From DSI_GroupResponsible rsp,R_Request rr Where rsp.DS_Request_SubGroup_ID= rr.DS_Request_SubGroup_ID and rsp.DS_CanApprove='Y' and rr.R_Request_ID = ? order by rsp.Priority";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		pstmt = DB.prepareStatement(sql, get_TrxName());
		pstmt.setInt(1, Request_ID);
		rs = pstmt.executeQuery();
		Timestamp date = new Timestamp(System.currentTimeMillis());
		while(rs.next())
		{
			Timestamp dateDay = TimeUtil.trunc(date, TimeUtil.TRUNC_DAY);

			int AD_User_ID = rs.getInt("AD_User_ID");
			int count = DB.getSQLValue(get_TrxName(), "Select count(*) From AD_Session Where CreatedBy = ? AND  "
					+ " TRUNC(Created,'DAY') = ? AND CreatedBy!= ? ",AD_User_ID,dateDay,CurrentLoggedInUser);
			if(count>0)
			{
				request.setSalesRep_ID(AD_User_ID);
				request.set_ValueOfColumn("DS_CanApprove", "Y");
				request.setResult(p_Comments);
				request.saveEx();
				msg = "@ForwardedToTheApprovar@";
				break;
			}
		}
		
		addLog(msg);
		return "@OK@";
	}

}
