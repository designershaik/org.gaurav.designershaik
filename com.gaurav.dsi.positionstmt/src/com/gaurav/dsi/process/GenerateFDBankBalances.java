package com.gaurav.dsi.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MBankAccount;
import org.compiere.model.MOrg;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSBankBalancesFD;

public class GenerateFDBankBalances extends SvrProcess
{
	int p_AD_Org_ID = 0 ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_Org_ID"))
				p_AD_Org_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}		
	}

	@Override
	protected String doIt() throws Exception 
	{
//		String sqlWhere = " where AD_Org_ID = "+p_AD_Org_ID;
//		if(p_AD_Org_ID==0)
		String	sqlWhere = " ";
		DB.executeUpdate("Delete from DS_BankBalancesFD ", get_TrxName());
		String sql = "select * from ds_bankbalances_v "+sqlWhere+" order by C_BankAccount_ID ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
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
				MOrg org = new MOrg(getCtx(), AD_Org_ID, get_TrxName());
				MBankAccount account = new MBankAccount(getCtx(), C_BankAccount_ID, get_TrxName());
				BigDecimal totalBHD = nonReconciledBalanceInBHD.add(bhdBalance);
				if(totalBHD.compareTo(Env.ZERO)!=0)
				{
					MDSBankBalancesFD fd = new MDSBankBalancesFD(getCtx(), 0, get_TrxName());
					fd.setC_Bank_ID(account.getC_Bank_ID());
					fd.setC_Currency_ID(account.getC_Currency_ID());
					if(currency.equalsIgnoreCase("USD"))
					{
						fd.setDS_USD(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("AED"))
					{
						fd.setDS_AED(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("EUR"))
					{
						fd.setDS_EUR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("GBP"))
					{
						fd.setDS_GBP(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("KWD"))
					{
						fd.setDS_KWD(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("OMR"))
					{
						fd.setDS_OMR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("QAR"))
					{
						fd.setDS_QAR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("SAR"))
					{
						fd.setDS_SAR(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					if(currency.equalsIgnoreCase("BHD"))
					{
						fd.setDS_BHD(nonReconciledBalanceInAccountCurrency.add(currentBalanceInAccountCurrency));
						fd.set_ValueNoCheck("DS_NonRenciledPayments", nonReconciledBalanceInAccountCurrency);
					}
					fd.set_ValueNoCheck("OrgName", org.getName());
					fd.set_ValueNoCheck("Comments", "Bank Balance");
					fd.setName(account.getName());
					fd.setAccountNo(account.getAccountNo());
					fd.setCurrentBalance(totalBHD);
					fd.setInterestPercent((BigDecimal)account.get_Value("InterestPercent"));
					fd.saveEx();
				}
			}
		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		
		sql = "select * from ds_fixeddepositsummary_v "+sqlWhere+" order by C_BankAccount_ID ";
		pstmt = null;
		rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				BigDecimal principalAmt = rs.getBigDecimal("Principal Amount");
				BigDecimal interestAmt = rs.getBigDecimal("Interest Amt");
				BigDecimal principalBHD = rs.getBigDecimal("Principal(BHD)");
				BigDecimal postMaturityBHD = rs.getBigDecimal("Post Maturity(BHD)");
				int C_BankAccount_ID = rs.getInt("C_BankAccount_ID");
				String currency = rs.getString("Currency");
				int AD_Org_ID = rs.getInt("AD_Org_ID");
				MOrg org = new MOrg(getCtx(), AD_Org_ID, get_TrxName());
				MBankAccount account = new MBankAccount(getCtx(), C_BankAccount_ID, get_TrxName());
				if(postMaturityBHD.compareTo(Env.ZERO)!=0)
				{
					MDSBankBalancesFD fd = new MDSBankBalancesFD(getCtx(), 0, get_TrxName());
					fd.setC_Bank_ID(account.getC_Bank_ID());
					fd.setC_Currency_ID(account.getC_Currency_ID());
					if(currency.equalsIgnoreCase("USD"))
						fd.setDS_USD(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("AED"))
						fd.setDS_AED(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("EUR"))
						fd.setDS_EUR(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("GBP"))
						fd.setDS_GBP(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("KWD"))
						fd.setDS_KWD(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("OMR"))
						fd.setDS_OMR(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("QAR"))
						fd.setDS_QAR(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("SAR"))
						fd.setDS_SAR(principalAmt.add(interestAmt));
					if(currency.equalsIgnoreCase("BHD"))
						fd.setDS_BHD(principalAmt.add(interestAmt));
					
					fd.set_ValueNoCheck("OrgName", org.getName());
					fd.set_ValueNoCheck("Comments", "Fixed Deposits");
					fd.setDS_BasePrincipalAmount(principalBHD);
					fd.setInterestAmt(interestAmt);
					fd.setName(account.getName());
					fd.setAccountNo(account.getAccountNo());
					fd.setCurrentBalance(postMaturityBHD);
					fd.setInterestPercent((BigDecimal)account.get_Value("InterestPercent"));
					fd.saveEx();
				}
			}
		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return null;
	}
}
