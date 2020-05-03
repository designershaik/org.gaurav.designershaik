package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class UpdatePerUnitProductionActivityTime extends SvrProcess
{

	@Override
	protected void prepare() {
		
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		
		String sql = "SELECT sum(durationinnumber) as DurationInNumber,"
				+ "M_Product_ID,sum(qtyentered),sum(qtyentered),"
				+ "(sum(durationinnumber)*60)/(case when sum(qtyentered)=0 then (sum(durationinnumber)*60) else sum(qtyentered) end) as timeConsumedPerUnit,M_Product_ID "
				+ "FROM ds_assignment_v "
				+ "group by M_Product_ID ";
		PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			int M_Product_ID = rs.getInt("M_Product_ID");
			if(M_Product_ID>0)
			{
				BigDecimal minutesPerUnit = rs.getBigDecimal("timeConsumedPerUnit").setScale(2, RoundingMode.HALF_UP);
				MProduct product = new MProduct(getCtx(), M_Product_ID, get_TrxName());
				product.set_ValueNoCheck("DS_TimeConsumedPerUnit", minutesPerUnit);
				product.save(get_TrxName());
			}
		}
		return "@Updated@";
	}

}
