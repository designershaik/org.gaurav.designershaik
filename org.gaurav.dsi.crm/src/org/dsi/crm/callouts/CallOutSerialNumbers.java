package org.dsi.crm.callouts;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class CallOutSerialNumbers extends CalloutEngine
{
	
	public String SerialAvailability (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if(WindowNo==341 || WindowNo==168 || WindowNo==191)
		{
			BigDecimal Qty = Env.ZERO;
			String suffix;
			String prefix;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			Integer DSI_SrNo = (Integer) value;
			
			if (DSI_SrNo == null || DSI_SrNo.intValue() == 0)
				return "";
			
			String sql="select trx.prefix,trx.suffix,sum(trx.qty) as Qty,trx.dsi_srno" +
					" from DSI_SerialNoTrx trx where trx.dsi_srno="+DSI_SrNo+"  group by trx.prefix,trx.suffix,trx.dsi_srno having sum(trx.qty)<>0" ;
			pstmt=DB.prepareStatement(sql, null);
			try 
			{
				rs=pstmt.executeQuery();
				if(rs.next())
				{		
					prefix=rs.getString("prefix");
					suffix=rs.getString("suffix");
					Qty=rs.getBigDecimal("qty");
					mTab.setValue("prefix", prefix);
					mTab.setValue("suffix",suffix );
					mTab.setValue("qty", new BigDecimal(-1));
				
				}
				else
				{
					String info = Msg.parseTranslation(ctx, "Serial Number "+DSI_SrNo+" is invalid or Dont have quantity:-"+Qty); 
							
					mTab.fireDataStatusEEvent ("Invalid Serial Number / Insufficient Qty Available",info, false);
				}
				
			} 
			catch (SQLException e)
			{
				
				e.printStackTrace();
			}
			finally
			{
				DB.close(rs, pstmt);
				rs=null;
				pstmt=null;
			}
		}
		return "";
	}
}
