package org.dsi.crm.callouts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.I_M_Movement;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutMovementPriceList implements IColumnCallout
{
	CLogger log=CLogger.getCLogger(CallOutMovementPriceList.class);
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
	
		Integer M_PriceList_ID = (Integer) mTab.getValue("M_PriceList_ID");
		if (M_PriceList_ID == null || M_PriceList_ID.intValue()== 0)
			return "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT pl.IsTaxIncluded,pl.EnforcePriceLimit,pl.C_Currency_ID,c.StdPrecision,"
			+ "plv.M_PriceList_Version_ID,plv.ValidFrom "
			+ "FROM M_PriceList pl,C_Currency c,M_PriceList_Version plv "
			+ "WHERE pl.C_Currency_ID=c.C_Currency_ID"
			+ " AND pl.M_PriceList_ID=plv.M_PriceList_ID"
			+ " AND pl.M_PriceList_ID=? "						//	1
			+ " AND plv.ValidFrom <= ? "
			+ "ORDER BY plv.ValidFrom DESC";
		//	Use newest price list - may not be future
		try
		{
			pstmt = DB.prepareStatement(sql, null);
			pstmt.setInt(1, M_PriceList_ID.intValue());
			Timestamp date = new Timestamp(System.currentTimeMillis());
			if (mTab.getAD_Table_ID() == I_M_Movement.Table_ID)
				date = Env.getContextAsDate(ctx, WindowNo, "MovementDate");
			pstmt.setTimestamp(2, date);
			
			rs = pstmt.executeQuery();
			if (rs.next())
			{
				//	Currency
				Integer ii = new Integer(rs.getInt(3));
				mTab.setValue("C_Currency_ID", ii);
				//	PriceList Version
				Env.setContext(ctx, WindowNo, "M_PriceList_Version_ID", rs.getInt(5));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql, e);
			return e.getLocalizedMessage();
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return null;
	}

}
