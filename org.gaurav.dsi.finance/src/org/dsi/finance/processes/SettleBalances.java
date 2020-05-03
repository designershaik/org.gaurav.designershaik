package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSFinReports;
import org.gaurav.dsi.model.MDSFinReportslevel1;
import org.gaurav.dsi.model.MDSFinReportslevel2;

public class SettleBalances extends SvrProcess 
{
	int ID;
	String reportType;

	BigDecimal totalAssetValue=Env.ZERO;
	BigDecimal totalLiabilities=Env.ZERO;
	BigDecimal totalOwnerEquity=Env.ZERO;
	BigDecimal totalExpense=Env.ZERO;
	BigDecimal totalRevenue=Env.ZERO;
	MDSFinReports fin;
	@Override
	protected void prepare() 
	{
		ID=getRecord_ID();
		fin=new MDSFinReports(getCtx(), ID, get_TrxName());
		reportType=fin.getDS_ReportType();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(!fin.isGenerated())
			throw new AdempiereException("Account balances are not generated");
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int level1_ID;
		BigDecimal totalLevel1;
		String sql="select lv1.DS_FINREPORTS_LEVEL1_ID from DS_FINREPORTS_LEVEL1 lv1 where lv1.DS_FINREPORTS_ID="+ID;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				level1_ID=rs.getInt("DS_FINREPORTS_LEVEL1_ID");
				totalLevel1=setBalanceLevel2(level1_ID);
				if(reportType.equalsIgnoreCase("BS"))
				{
					MDSFinReportslevel1 lv1=new MDSFinReportslevel1(getCtx(), level1_ID, get_TrxName());
					lv1.setBalance(totalLevel1);
					lv1.save();
					updateBalanceSheet(totalLevel1,lv1.getAccountType());
				}
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		fin.setProcessed(true);
		fin.save();
		return "@OK@";
	}

	

	private BigDecimal setBalanceLevel2(int level_ID) 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int level2_ID;
		BigDecimal totalLevel3;
		BigDecimal totalLevel2 = Env.ZERO;
		String sql="select Lv2.Ds_Finreports_Level2_Id from DS_FINREPORTS_LEVEL1 lv1 ,DS_FINREPORTS_LEVEL2 lv2 "
				+ "where lv1.DS_FINREPORTS_LEVEL1_ID=lv2.DS_FINREPORTS_LEVEL1_ID and lv1.DS_FINREPORTS_LEVEL1_ID="+level_ID+" and lv1.DS_FinReports_ID = "+ID;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				level2_ID=rs.getInt("Ds_Finreports_Level2_Id");
				totalLevel3=getBalanceLevel3(level2_ID);
				totalLevel3 = totalLevel3==null ? Env.ZERO : totalLevel3;
				MDSFinReportslevel2 lv2=new MDSFinReportslevel2(getCtx(), level2_ID, get_TrxName());
				lv2.setBalance(totalLevel3);
				lv2.save();
				totalLevel2=totalLevel2.add(totalLevel3);
				String accountType=lv2.getAccountType();
				if(reportType.equalsIgnoreCase("PL"))
				{
					updateProfitAndLoss(totalLevel3,accountType,level_ID);
				}
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return totalLevel2;
		
	}

	private BigDecimal getBalanceLevel3(int level2_ID) 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BigDecimal totalLevel3 = Env.ZERO;
		String sql ="select lv1.name as AccountType1,lv2.name as AccountType2,sum(lv3.BALANCE) as Levl3Balance"
				+ " from DS_FINREPORTS_LEVEL1 lv1 ,DS_FINREPORTS_LEVEL2 lv2 ,DS_FINREPORTS_LEVEL3 lv3  "
				+ "where lv1.DS_FINREPORTS_LEVEL1_ID=lv2.DS_FINREPORTS_LEVEL1_ID "
				+ "and lv2.DS_FINREPORTS_LEVEL2_ID=lv3.DS_FINREPORTS_LEVEL2_ID and lv1.DS_FinReports_ID ="+ID
				+ " and lv3.DS_FinReports_Level2_ID="+level2_ID+" group by lv1.name,lv2.name";
		try {
			pstmt = DB.prepareStatement(sql, null);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				totalLevel3=rs.getBigDecimal("Levl3Balance");
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		return totalLevel3;
	}
	
	private void updateBalanceSheet(BigDecimal totalLevel1, String accountType) 
	{
		BigDecimal balanceSheetBalance;
		if(accountType.equalsIgnoreCase("A"))
			{
				totalAssetValue=totalLevel1;
			}
			else if(accountType.equals("L"))
			{
				totalLiabilities=totalLevel1;
			}
			else if(accountType.equals("O"))
			{
				totalOwnerEquity=totalLevel1;
			}
		balanceSheetBalance=totalAssetValue.subtract((totalLiabilities.add(totalOwnerEquity)));
		fin.setBalance(balanceSheetBalance);
		fin.save();
	}
	
	private void updateProfitAndLoss(BigDecimal totalLevel2, String accountType, int level1_ID) 
	{
		BigDecimal totalProfit=Env.ZERO;
		MDSFinReportslevel1 finLevel1=new MDSFinReportslevel1(getCtx(), level1_ID, get_TrxName());
		if(accountType.equalsIgnoreCase("R"))
			{
				totalRevenue=totalRevenue.add(totalLevel2);
			}
		else if(accountType.equals("E"))
			{
				totalExpense=totalExpense.add(totalLevel2);
			}
		totalProfit=totalRevenue.subtract(totalExpense);
		finLevel1.setBalance(totalProfit);
		finLevel1.save();
		fin.setBalance(totalProfit);
		fin.save();
	}

}



