package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.compiere.model.MBankAccount;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSBankBalancesFD;

public class GenerateFDBankBalances extends SvrProcess{

	@Override
	protected void prepare() 
	{
				
	}

	@Override
	protected String doIt() throws Exception 
	{
		DB.executeUpdate("Delete from DS_BankBalancesFD ", get_TrxName());
		String sql = "select * from ds_bankbalances_v where AD_Org_ID = ? order by C_BankAccount_ID ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		pstmt = DB.prepareStatement(sql, get_TrxName());
		pstmt.setInt(1, Env.getAD_Org_ID(getCtx()));
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			BigDecimal nonReconciledBalanceInAccountCurrency = rs.getBigDecimal("Non Reconciled Balance");
			BigDecimal currentBalanceInAccountCurrency = rs.getBigDecimal("Current Balance");
			int C_BankAccount_ID = rs.getInt("C_BankAccount_ID");
			String currency = rs.getString("Currency");
//			String bankName = rs.getString("Bank Name");
//			String organization = rs.getString("Organization");
			BigDecimal nonReconciledBalanceInBHD = rs.getBigDecimal("nonrenciledbhd");
			BigDecimal bhdBalance = rs.getBigDecimal("bhdbalance");
			int AD_Org_ID = rs.getInt("AD_Org_ID");
			
			MBankAccount account = new MBankAccount(getCtx(), C_BankAccount_ID, get_TrxName());
			BigDecimal totalBHD = nonReconciledBalanceInBHD.add(bhdBalance);
			if(totalBHD.compareTo(Env.ZERO)!=0)
			{
				MDSBankBalancesFD fd = new MDSBankBalancesFD(getCtx(), 0, get_TrxName());
				fd.setC_Bank_ID(account.getC_Bank_ID());
				fd.setC_Currency_ID(account.getC_Currency_ID());
				if(currency.equalsIgnoreCase("USD"))
					fd.setDS_USD(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("AED"))
					fd.setDS_AED(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("EUR"))
					fd.setDS_EUR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("GBP"))
					fd.setDS_GBP(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("KWD"))
					fd.setDS_KWD(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("OMR"))
					fd.setDS_OMR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("QAR"))
					fd.setDS_QAR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("SAR"))
					fd.setDS_SAR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				if(currency.equalsIgnoreCase("BHD"))
					fd.setDS_BHD(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
				
				fd.setName(account.getName());
				fd.setAccountNo(account.getAccountNo());
				fd.setAD_Org_ID(AD_Org_ID);
				fd.setCurrentBalance(totalBHD);
				fd.setInterestPercent((BigDecimal)account.get_Value("InterestPercent"));
				fd.saveEx();
			}
		}
		return null;
	}
}
