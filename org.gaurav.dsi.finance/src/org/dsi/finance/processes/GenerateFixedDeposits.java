package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.compiere.model.MBankAccount;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSBankBalancesFD;

public class GenerateFixedDeposits extends SvrProcess{

	@Override
	protected void prepare() 
	{
				
	}

	@Override
	protected String doIt() throws Exception 
	{
		DB.executeUpdate("Delete from ds_fixeddepositsummary_v ", get_TrxName());
		String sql = "select * from ds_fixeddepositsummary_v where AD_Org_ID = ? order by C_BankAccount_ID ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		pstmt = DB.prepareStatement(sql, get_TrxName());
		pstmt.setInt(1, Env.getAD_Org_ID(getCtx()));
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			BigDecimal principalAmt = rs.getBigDecimal("Principal Amount");
			BigDecimal interestAmt = rs.getBigDecimal("Interest Amt");
			Timestamp maturityDate = rs.getTimestamp("Maturity Date");
			BigDecimal principalBHD = rs.getBigDecimal("Principal(BHD)");
			BigDecimal interestBHD = rs.getBigDecimal("InterestAmt (BHD)");
			BigDecimal postMaturityBHD = rs.getBigDecimal("Post Maturity(BHD)");
			Timestamp startDate = rs.getTimestamp("startdate");
			int C_BankAccount_ID = rs.getInt("C_BankAccount_ID");
			String currency = rs.getString("Currency");
			int AD_Org_ID = rs.getInt("AD_Org_ID");
			 
			MBankAccount account = new MBankAccount(getCtx(), C_BankAccount_ID, get_TrxName());
			if(postMaturityBHD.compareTo(Env.ZERO)!=0)
			{
				MDSBankBalancesFD fd = new MDSBankBalancesFD(getCtx(), 0, get_TrxName());
				fd.setC_Bank_ID(account.getC_Bank_ID());
				fd.setC_Currency_ID(account.getC_Currency_ID());
				if(currency.equalsIgnoreCase("USD"))
					fd.setDS_USD(principalAmt);
				if(currency.equalsIgnoreCase("AED"))
					fd.setDS_AED(principalAmt);
				if(currency.equalsIgnoreCase("EUR"))
					fd.setDS_EUR(principalAmt);
				if(currency.equalsIgnoreCase("GBP"))
					fd.setDS_GBP(principalAmt);
				if(currency.equalsIgnoreCase("KWD"))
					fd.setDS_KWD(principalAmt);
				if(currency.equalsIgnoreCase("OMR"))
					fd.setDS_OMR(principalAmt);
				if(currency.equalsIgnoreCase("QAR"))
					fd.setDS_QAR(principalAmt);
				if(currency.equalsIgnoreCase("SAR"))
					fd.setDS_SAR(principalAmt);
				if(currency.equalsIgnoreCase("BHD"))
					fd.setDS_BHD(principalAmt);
				fd.setDS_BasePrincipalAmount(principalBHD);
				fd.setInterestAmt(interestAmt);
				fd.setName(account.getName());
				fd.setAccountNo(account.getAccountNo());
				fd.setAD_Org_ID(AD_Org_ID);
				fd.setCurrentBalance(postMaturityBHD);
				fd.setInterestPercent((BigDecimal)account.get_Value("InterestPercent"));
				fd.saveEx();
			}
		}
		return null;
	}

}
