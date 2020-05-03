package org.dsi.crm.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MBPartner;
import org.compiere.model.Query;
import org.compiere.model.X_C_BPartner;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class ReSequenceTree extends SvrProcess{

	int AD_Tree_ID = 0 ; 
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Tree_ID"))
				AD_Tree_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception 
	{
		getOrphanedNodes();
		String sql = "Select Name,C_BP_Group_ID From C_BP_Group Where AD_Client_ID = ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int C_BP_Group_ID = rs.getInt(2);
				List<MBPartner> bpartner = new Query(getCtx(), MBPartner.Table_Name, " C_BP_Group_ID = ? ", get_TrxName()).
											setParameters(C_BP_Group_ID).
											setOrderBy(X_C_BPartner.COLUMNNAME_Value).
											list();
				int i = 1;
				for(MBPartner bp:bpartner)
				{
					Object[] param = new Object[]{i,bp.getC_BPartner_ID()};
					DB.executeUpdateEx("Update AD_TreeNodeBP SET SEQNO= ? WHERE NODE_ID = ? ", param, get_TrxName());
					i ++ ;
				}
				
			}
		}
		catch(Exception e)
		{
			log.severe("Exception : "+e.toString());
		}
		return "@TreeUpdated@";
	}

	private int getOrphanedNodes() 
	{
		String sql  = " select bp.value,grp.Name,bp.C_BPartner_ID "
				+ "from  AD_TreeNodeBP tree,c_Bpartner bp,c_bp_group grp "
				+ "where tree.ad_client_id=? "
				+ "and tree.parent_id=0 "
				+ "and tree.node_id not in (Select bp.C_BPartner_ID From C_BPartner bp where bp.IsSummary='Y') "
				+ "and tree.node_id=bp.c_bpartner_id and bp.c_Bp_Group_id=grp.c_bp_group_id order by bp.value";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, getAD_Client_ID());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int C_BPartner_ID = rs.getInt("C_BPartner_ID");
				String groupName = rs.getString("Name");
				Object[] param = new Object[]{groupName,getAD_Client_ID(),C_BPartner_ID,getAD_Client_ID()};
				
				DB.executeUpdateEx("Update AD_TreeNodeBP SET Parent_ID="
						+ "(SELECT bp.C_BPartner_ID FROM C_BPartner bp WHERE bp.value = ? "
						+ "and bp.AD_Client_ID=?) WHERE Node_ID = ? AND AD_Client_ID = ? ", param,  get_TrxName());
			}
		}
		catch(Exception e)
		{
			log.severe("Exception : "+e.toString());
		}
		
		return AD_Tree_ID;
	}

}
