package com.gaurav.doc;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;
import org.compiere.model.MTable;
import org.compiere.process.DocumentEngine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class RepostDocuments extends SvrProcess
{
	int p_AD_Table_ID = 0;
	Timestamp p_DateAcct_From = null;
	Timestamp p_DateAcct_To = null;
	boolean p_isDeleteAndRecreate = false;

	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if (name.equals("AD_Table_ID"))
				p_AD_Table_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DateAcct"))
			{
				p_DateAcct_From = (Timestamp)para[i].getParameter();
				p_DateAcct_To = (Timestamp)para[i].getParameter_To();
			}
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		MTable table = new MTable(getCtx(), p_AD_Table_ID, get_TrxName());
		String sql = "Select "+table.getTableName()+"_ID"
				+ " FROM "+table.getTableName()+" WHERE AD_Client_ID=" + getAD_Client_ID()
				+ " AND Posted='Y' and dateAcct between ? and ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		pstmt = DB.prepareStatement(sql, get_TrxName());
		pstmt.setTimestamp(1, p_DateAcct_From);
		pstmt.setTimestamp(2, p_DateAcct_To);
		rs = pstmt.executeQuery();
		int i =0;
		while(rs.next())
		{
			i++ ;
			int Record_ID = rs.getInt(1);
			DocumentEngine.postImmediate(getCtx(), getAD_Client_ID(), p_AD_Table_ID, Record_ID, true, get_TrxName());
		}
		
		return "Total Reposted Document: "+i;
	}


}
