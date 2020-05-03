package org.dsi.crm.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.compiere.util.Util;

public class GenerateManagementSummary extends SvrProcess
{
	Timestamp p_DateTo = null;
	Timestamp p_DateFrom = null;
	
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter para : paras)
		{
			String name = para.getParameterName();
			if ( Util.isEmpty(name) )
				;
			else if ("DateAcct".equals(name))
				p_DateFrom = para.getParameterAsTimestamp();
		}
	
//		p_DateTo = TimeUtil.addDays(new Timestamp(TimeUtil.getToday().getTimeInMillis()),-6);
//		Calendar c = Calendar.getInstance();   // this takes current date
//	    c.set(Calendar.DAY_OF_MONTH, 1);
//		 p_DateFrom = TimeUtil.addDays(p_DateTo, -30);
	   
	}

	@Override
	protected String doIt() throws Exception 
	{
		p_DateTo = TimeUtil.addDays(p_DateFrom, 30);
		StringBuilder result = new StringBuilder("Month \t\t Sales \t\t MonthToDate \t\t YearToDate");
		String sql = "select distinct  sum(TotalAmount) AS TotalAmtA, mnth , yr , " + 
				"(select sum(totalamount) from ds_salesreport_v where dateacct between date_trunc('Year', ?::timestamp) and  ? ) as YearToDateSales, " + 
				"(select sum(totalamount) from ds_salesreport_v where dateacct between date_trunc('Month', ?::timestamp) and  ? ) as MonthToDateSales " + 
				"from ds_salesreport_v " + 
				"where dateacct between ? and  ?  " + 
				"group by yr,mnth "+
				" union all " +
				" select distinct  sum(TotalAmount) AS TotalAmtA, mnth , yr , " + 
				"(select sum(totalamount) from ds_salesreport_v where dateacct between date_trunc('Year', ?::timestamp) + interval '-12 month' and  ?::timestamp + interval '-12 month') as YearToDateSales, " + 
				"(select sum(totalamount) from ds_salesreport_v where dateacct between date_trunc('Month', ?::timestamp) + interval '-12 month' and  ?::timestamp + interval '-12 month') as MonthToDateSales " + 
				"from ds_salesreport_v " + 
				"where dateacct between date_trunc('Month', ?::timestamp) + interval '-1 month'  and  ?::timestamp + interval '-1 month'   " + 
				"group by yr,mnth " +
				" union all " +
				" select distinct  sum(TotalAmount) AS TotalAmtA, mnth , yr , " + 
				"(select sum(totalamount) from ds_salesreport_v where dateacct between date_trunc('Year', ?::timestamp) + interval '-24 month' and  ?::timestamp + interval '-24 month') as YearToDateSales, " + 
				"(select sum(totalamount) from ds_salesreport_v where dateacct between date_trunc('Month', ?::timestamp) + interval '-24 month' and  ?::timestamp + interval '-24 month') as MonthToDateSales " + 
				"from ds_salesreport_v " + 
				"where dateacct between date_trunc('Month', ?::timestamp) + interval '-2 month'  and  ?::timestamp + interval '-2 month'  " + 
				"group by yr,mnth ";
		
		PreparedStatement pstmt = DB.prepareStatement(sql, get_TrxName());
		setParameters(pstmt);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next())
		{
			BigDecimal totalSales = rs.getBigDecimal(1);
			String month = rs.getString(2);
			int year = rs.getInt(3);
			BigDecimal dateToYearSales = rs.getBigDecimal(4);
			BigDecimal dateToMonthSales = rs.getBigDecimal(4);
			result.append("\n");
			result.append("%15s%15s%15s\n"+month.concat("-")+year+" \t\t "+totalSales+" \t\t "+dateToMonthSales+" \t\t "+dateToYearSales);
			result.append("\n");
		}
		System.out.println(result);
		return result.toString();
	}

	private void setParameters(PreparedStatement pstmt) throws SQLException
	{
		pstmt.setTimestamp(1, p_DateFrom);
		pstmt.setTimestamp(2, p_DateTo);
		
		pstmt.setTimestamp(3, p_DateFrom);
		pstmt.setTimestamp(4, p_DateTo);
	
		pstmt.setTimestamp(5, p_DateFrom);
		pstmt.setTimestamp(6, p_DateTo);
		
		pstmt.setTimestamp(7, p_DateFrom);
		pstmt.setTimestamp(8, p_DateTo);
		
		pstmt.setTimestamp(9, p_DateFrom);
		pstmt.setTimestamp(10, p_DateTo);
	
		pstmt.setTimestamp(11, p_DateFrom);
		pstmt.setTimestamp(12, p_DateTo);
		
		pstmt.setTimestamp(13, p_DateFrom);
		pstmt.setTimestamp(14, p_DateTo);
		
		pstmt.setTimestamp(15, p_DateFrom);
		pstmt.setTimestamp(16, p_DateTo);
		
		pstmt.setTimestamp(17, p_DateFrom);
		pstmt.setTimestamp(18, p_DateTo);
	}


}
