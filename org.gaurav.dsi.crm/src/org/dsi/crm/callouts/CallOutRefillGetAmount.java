package org.dsi.crm.callouts;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutRefillGetAmount implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		String sql="select currencybase("+(BigDecimal)value+", "+mTab.getValue("C_Currency_ID")+", sysdate, "+Env.getAD_Client_ID(ctx)+", "+Env.getAD_Org_ID(ctx)+") as baseAmount from dual " ;
		
		pstmt=DB.prepareStatement(sql, null);
		try 
		{
			rs=pstmt.executeQuery();
			if(rs.next())
			{		
				
				mTab.setValue("A_Base_Amount", rs.getBigDecimal("baseAmount"));
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
		
				
				return null;
	
	}

}
