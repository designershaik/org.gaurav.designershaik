package com.gaurav.dsi.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.acct.Fact.Balance;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBankAccount;
import org.compiere.model.MClient;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.model.MOrg;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.gaurav.dsi.model.MDSBankBalancesFD;
import org.gaurav.dsi.model.MDSCashPositionSetup;
import org.gaurav.dsi.model.MDSFundMovement;

public class GenerateCashPositions extends SvrProcess
{
	int p_AD_Org_ID = 0 ;
	int p_C_BankAccount_ID = 0 ;
	Timestamp p_DateTo = null;
	MAcctSchema as = null;
	boolean p_DoNotRegenerate = false;
	int currentMonth = 0;
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
			else if (name.equals("C_BankAccount_ID"))
				p_C_BankAccount_ID = para[i].getParameterAsInt();
			else if (name.equals("DateAcct"))
				p_DateTo = para[i].getParameterAsTimestamp();
			else if (name.equals("DS_DoNotRegenerate"))
				p_DoNotRegenerate = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}	
		currentMonth = DB.getSQLValue(get_TrxName(), "Select to_Char(now(),'MM')::numeric ");
		if(currentMonth+1>12)
			currentMonth = 1;
	}

	@Override
	protected String doIt() throws Exception 
	{
		MClient client = MClient.get(getCtx());
		as = client.getAcctSchema();
		DB.executeUpdateEx("Delete from DS_BankBalancesFD ", get_TrxName());
		
		
		createBankBalancesAndNonReconciledBalance();
		createFixedDeposits();

		if(p_DoNotRegenerate)
			return "@OK@";

		createCashReceipts();
		
		createProjections();
		
		return null;
	}

	private void createProjections() 
	{
		updateMonthsForCashProjection("Investment",0);
		updateCashProjection("Investment","Coupons",1);
		updateFixedDepositsInterestsAndMaturityAmts("Investment","Interests (Bank Accts,FD)",2);
		updateFixedDepositsInterestsAndMaturityAmts("Investment","Maturity & Sell-out Amounts",3);
		updateAgingForCreditAccounts("Operations Business","Collection from Credit Accounts",4);
		updateAgingForCreditAccounts("Operations Business","Collection from Overdue Accounts",5);
		updateAgingForCreditAccounts("Operations Business","Total Overdue",6);
	}

	private void updateAgingForCreditAccounts(String type,String value,int sequence) 
	{		
		String sql = "";
		MDSFundMovement movement = null;

		if(value.equalsIgnoreCase("Collection from Credit Accounts"))
			sql = "with t1 as (select invoiceopentodate(ci.c_invoice_id,0,now())*multiplier,c_currency_id ,paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct) as DueDate, " + 
					"to_Char(paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct),'MM') as MM,to_Char(paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct),'YYYY') as YYYY, " + 
					"currencybase(invoiceopentodate(ci.c_invoice_id,0,now())*multiplier,ci.c_currency_id ,ci.dateacct ,ci.ad_client_id ,ci.ad_org_id ) as BaseAmt " + 
					"from c_invoice_v ci ,c_bpartner bp " + 
					"where ci.c_bpartner_id = bp.c_bpartner_id  " + 
					"and invoiceopen(ci.c_invoice_id ,0)<>0  " + 
					"and issotrx ='Y'  " + 
					"and posted='Y' " + 
					"and ispaid ='N' " + 
					"and paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct) > ? " + 
					"and bp.c_bp_group_id in (select c_bp_group_id from DS_CashPosition_Setup where categorytype ='P' and ElementValue is null) " + 
					"order by to_Char(paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct),'YYYY')::numeric,to_Char(paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct),'MM')::numeric " + 
					") " + 
					"select sum(BaseAmt) as Balance,MM as MonthNo,YYYY " + 
					"from t1 " + 
					"group by YYYY,MM";
		else
			sql ="with t1 as (select invoiceopentodate(ci.c_invoice_id,0,now())*multiplier,c_currency_id ,paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct) as DueDate, " + 
					"to_Char(?::timestamp,'MM') as MM,to_Char(?::timestamp,'YYYY') as YYYY, " + 
					"currencybase(invoiceopentodate(ci.c_invoice_id,0,now())*multiplier,ci.c_currency_id ,ci.dateacct ,ci.ad_client_id ,ci.ad_org_id ) as BaseAmt " + 
					"from c_invoice_v ci ,c_bpartner bp " + 
					"where ci.c_bpartner_id = bp.c_bpartner_id  " + 
					"and invoiceopen(ci.c_invoice_id ,0)<>0  " + 
					"and issotrx ='Y'  " + 
					"and posted='Y' " + 
					"and ispaid ='N' " + 
					"and paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct) > ? " + 
					"and bp.c_bp_group_id in (select c_bp_group_id from DS_CashPosition_Setup where categorytype ='P' and ElementValue is null) " + 
					"order by to_Char(paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct),'YYYY')::numeric,to_Char(paymenttermduedate(ci.c_paymentterm_id ,ci.dateacct),'MM')::numeric " + 
					") " + 
					"select sum(BaseAmt) as Balance,MM as MonthNo,YYYY " + 
					"from t1 " + 
					"group by YYYY,MM ";
			
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setTimestamp(1, p_DateTo);
			if(!value.equalsIgnoreCase("Collection from Credit Accounts"))
			{
				pstmt.setTimestamp(2, p_DateTo);
				pstmt.setTimestamp(3, p_DateTo);
			}
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				boolean isAdded = false;
				int monthNo = rs.getInt("MonthNo"); // Col2 has january , col3 as february and so on.
				BigDecimal BHDAmt = rs.getBigDecimal("Balance");
				String balance = BHDAmt.toString();
				if(value.equalsIgnoreCase("Total Overdue"))
					balance  = BHDAmt.negate().toString();
				
				int FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement Where Col_1 like ? and DS_Section='C' and AD_PInstance_ID=? and Col_26='P' ",value,getAD_PInstance_ID());
				if(FundMovement_ID<=0)
				{
					movement = new MDSFundMovement(getCtx(), 0, get_TrxName());
					movement.setCol_0(type);
					movement.setCol_1(value);
					movement.setSeqNo(sequence);
					movement.setDS_Section("C");
					movement.setCol_26("P");
					isAdded = true;
					if(monthNo==1)
						movement.setCol_2(balance);
					if(monthNo==2)
						movement.setCol_3(balance);
					if(monthNo==3)
						movement.setCol_4(balance);
					if(monthNo==4)
						movement.setCol_5(balance);
					if(monthNo==5)
						movement.setCol_6(balance);
					if(monthNo==6)
						movement.setCol_7(balance);
					if(monthNo==7)
						movement.setCol_8(balance);
					if(monthNo==8)
						movement.setCol_9(balance);
					if(monthNo==9)
						movement.setCol_10(balance);
					if(monthNo==10)
						movement.setCol_11(balance);
					if(monthNo==11)
						movement.setCol_12(balance);
					if(monthNo==12)
						movement.setCol_13(balance);
					
					movement.setAD_PInstance_ID(getAD_PInstance_ID());
				}
				else
				{
					movement = new MDSFundMovement(getCtx(), FundMovement_ID, get_TrxName());
					if(!isAdded && movement.getCol_2()==null && monthNo==1)
					{
						movement.setCol_2(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_3()==null && monthNo==2)
					{
						movement.setCol_3(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_4()==null && monthNo==3)
					{
						movement.setCol_4(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_5()==null && monthNo==4)
					{
						movement.setCol_5(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_6()==null && monthNo==5)
					{
						movement.setCol_6(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_7()==null && monthNo==6)
					{
						movement.setCol_7(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_8()==null && monthNo==7)
					{
						movement.setCol_8(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_9()==null && monthNo==8)
					{
						movement.setCol_9(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_10()==null && monthNo==9)
					{
						movement.setCol_10(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_11()==null && monthNo==10)
					{
						movement.setCol_11(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_12()==null && monthNo==11)
					{
						movement.setCol_12(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_13()==null && monthNo==12)
					{
						movement.setCol_13(balance);
						isAdded = true;
					}
				}
				movement.saveEx();
			}
			
		}
		catch(Exception e)
		{
			log.severe("Error running cash receipt query. "+sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		 
	}

	private void updateFixedDepositsInterestsAndMaturityAmts(String type,String value,int sequence) 
	{
		MDSFundMovement movement = null;
		String sql = "";
		if(value.equalsIgnoreCase("Interests (Bank Accts,FD)"))
			sql = "select FDYear,InterestAmtBHD as Balance,FDMonth as MonthNo,MaturityDate,AD_Org_ID from cashposition_fixeddepositprojection where MaturityDate between ? and ? order by MaturityDate";
		else
			sql = "select FDYear,PrincipalAmtBHD as Balance,FDMonth as MonthNo,MaturityDate,AD_Org_ID from cashposition_fixeddepositprojection where MaturityDate between ? and ? order by MaturityDate";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setTimestamp(1, TimeUtil.addDays(p_DateTo, 1));
			pstmt.setTimestamp(2, TimeUtil.addDays(p_DateTo, 366));
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				boolean isAdded = false;
				int monthNo = rs.getInt("MonthNo"); // Col2 has january , col3 as february and so on.
				BigDecimal BHDAmt = rs.getBigDecimal("Balance");
				String balance = BHDAmt.toString();
				int FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement Where Col_1 like ? and DS_Section='C' and AD_PInstance_ID=? and Col_26='P' ",value,getAD_PInstance_ID());
				if(FundMovement_ID<=0)
				{
					movement = new MDSFundMovement(getCtx(), 0, get_TrxName());
					movement.setCol_0(type);
					movement.setCol_1(value);
					movement.setSeqNo(sequence);
					movement.setDS_Section("C");
					movement.setCol_26("P");
					isAdded = true;
					if(monthNo==1)
						movement.setCol_2(balance);
					if(monthNo==2)
						movement.setCol_3(balance);
					if(monthNo==3)
						movement.setCol_4(balance);
					if(monthNo==4)
						movement.setCol_5(balance);
					if(monthNo==5)
						movement.setCol_6(balance);
					if(monthNo==6)
						movement.setCol_7(balance);
					if(monthNo==7)
						movement.setCol_8(balance);
					if(monthNo==8)
						movement.setCol_9(balance);
					if(monthNo==9)
						movement.setCol_10(balance);
					if(monthNo==10)
						movement.setCol_11(balance);
					if(monthNo==11)
						movement.setCol_12(balance);
					if(monthNo==12)
						movement.setCol_13(balance);
					
					movement.setAD_PInstance_ID(getAD_PInstance_ID());
				}
				else
				{
					movement = new MDSFundMovement(getCtx(), FundMovement_ID, get_TrxName());
					if(!isAdded && movement.getCol_2()==null && monthNo==1)
					{
						movement.setCol_2(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_3()==null && monthNo==2)
					{
						movement.setCol_3(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_4()==null && monthNo==3)
					{
						movement.setCol_4(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_5()==null && monthNo==4)
					{
						movement.setCol_5(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_6()==null && monthNo==5)
					{
						movement.setCol_6(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_7()==null && monthNo==6)
					{
						movement.setCol_7(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_8()==null && monthNo==7)
					{
						movement.setCol_8(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_9()==null && monthNo==8)
					{
						movement.setCol_9(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_10()==null && monthNo==9)
					{
						movement.setCol_10(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_11()==null && monthNo==10)
					{
						movement.setCol_11(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_12()==null && monthNo==11)
					{
						movement.setCol_12(balance);
						isAdded = true;
					}
					if(!isAdded && movement.getCol_13()==null && monthNo==12)
					{
						movement.setCol_13(balance);
						isAdded = true;
					}
				}
				movement.saveEx();
			}
			
		}
		catch(Exception e)
		{
			log.severe("Error running cash receipt query. "+sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}

	private void updateCashProjection(String type, String value, int seqNo) 
	{
		MDSFundMovement movement = null;
		String sql = "select to_char(gs_coupondate ,'MM')::numeric as MonthNo,to_char(gs_coupondate ,'YYYY')::numeric ,C_Currency_ID ,gs_couponamount as CouponAmt,AD_Org_ID, "
				+ "to_char(gs_coupondate ,'Month') from DS_CouponSchedule where gs_coupondate between ? and ? order by to_char(gs_coupondate ,'YYYY')::numeric,to_char(gs_coupondate ,'MM')::numeric ";	
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			pstmt.setTimestamp(1, TimeUtil.addDays(p_DateTo, 1));
			pstmt.setTimestamp(2, TimeUtil.addDays(p_DateTo, 366));
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				BigDecimal couponAmt = rs.getBigDecimal("CouponAmt");
				int CurFrom_ID = rs.getInt("C_Currency_ID");
				int AD_Org_ID = rs.getInt("AD_Org_ID");
				BigDecimal bhdAmt = MConversionRate.convertBase(getCtx(),couponAmt, CurFrom_ID, p_DateTo, 0, getAD_Client_ID(), AD_Org_ID);
				String balance = bhdAmt.toString();
				int FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement Where Col_1 like ? and DS_Section='C' and AD_PInstance_ID=? and Col_26='P' ",value,getAD_PInstance_ID());
				if(FundMovement_ID<=0)
				{
					movement = new MDSFundMovement(getCtx(), 0, get_TrxName());
					movement.setCol_0(type);
					movement.setCol_1(value);
					movement.setSeqNo(seqNo);
					movement.setDS_Section("C");
					movement.setCol_26("P");
					movement.set_ValueNoCheck("Col_"+currentMonth+1, balance);	
					movement.setAD_PInstance_ID(getAD_PInstance_ID());
				}
				else
				{
					movement = new MDSFundMovement(getCtx(), FundMovement_ID, get_TrxName());
					
					movement.set_ValueNoCheck("Col_"+currentMonth+1, balance);
				}
				movement.saveEx();
			}
			
		}
		catch(Exception e)
		{
			log.severe("Error running cash receipt query. "+sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}

	private void updateMonthsForCashProjection(String type,int sequence) 
	{
		
		MDSFundMovement movement = null;
		for(int i=0;i<12;i++)
		{
			boolean isAdded = false;
			String MonthName = DB.getSQLValueString(get_TrxName(), "select replace(to_char(?::timestamp ,'Month')||'-'||to_char(?::timestamp ,'YYYY'),' ','')", TimeUtil.addMonths(p_DateTo, i),TimeUtil.addMonths(p_DateTo, i));
			int FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement Where DS_Section='M' and AD_PInstance_ID=? and Col_26='P' ",getAD_PInstance_ID());
			if(FundMovement_ID<=0)
			{
				isAdded = true;
				movement = new MDSFundMovement(getCtx(), 0, get_TrxName());
				movement.setCol_2(MonthName);
				movement.setDS_Section("M");
				movement.setCol_26("P");
				movement.setSeqNo(0);
				movement.setAD_PInstance_ID(getAD_PInstance_ID());
				movement.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
			}
			else
			{
				movement = new MDSFundMovement(getCtx(), FundMovement_ID, get_TrxName());
				if(!isAdded && movement.getCol_3()==null)
				{
					movement.setCol_3(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_4()==null)
				{
					movement.setCol_4(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_5()==null)
				{
					movement.setCol_5(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_6()==null)
				{
					movement.setCol_6(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_7()==null)
				{
					movement.setCol_7(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_8()==null)
				{
					movement.setCol_8(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_9()==null)
				{
					movement.setCol_9(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_10()==null)
				{
					movement.setCol_10(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_11()==null)
				{
					movement.setCol_11(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_12()==null)
				{
					movement.setCol_12(MonthName);
					isAdded = true;
				}
				if(!isAdded && movement.getCol_13()==null)
				{
					movement.setCol_13(MonthName);
					isAdded = true;
				}
			}
			movement.saveEx();
		}
	}

	private void createCashReceipts() 
	{
		DB.executeUpdateEx("Delete from DS_FundMovement",get_TrxName());
		updateMonthsForCashReceipt();
		List<MDSCashPositionSetup> cashSetups = new Query(getCtx(), MDSCashPositionSetup.Table_Name, " AD_Client_ID = ? and CategoryType='R' ", get_TrxName())
				.setParameters(getAD_Client_ID())
				.setOnlyActiveRecords(true)
				.setOrderBy(MDSCashPositionSetup.COLUMNNAME_SeqNo)
				.list();
		
		for(MDSCashPositionSetup setup:cashSetups)
		{
			MDSFundMovement movement = null;
			String bpartnerGroup = setup.getBPGroupName();
			String accounts = setup.getElementValue();
			String category = setup.getCategory();
			int SeqNo = setup.getSeqNo();
			String value = setup.getValue();
			
			StringBuilder sql = new StringBuilder("with t1 as (select ce.value ,ce.name ,acct.amtacctdr,acct.amtacctcr,to_char(to_timestamp (date_part('month', acct.dateacct)::text, 'MM'), 'Month') as MonthName,"+ 
					"extract(month from acct.dateacct) as MonthNo " + 
					"from fact_Acct acct,c_elementvalue ce " + 
					"where acct.account_id = ce.c_elementvalue_id "+
				    "and acct.dateacct between (SELECT date_trunc('year', ?::timestamp)) and ?::timestamp ") ;
			if(bpartnerGroup!=null && !bpartnerGroup.isEmpty())
				sql.append(" and acct.C_BPartner_ID IN (select C_BPartner_ID from c_bpartner where c_bp_group_id in ( "+bpartnerGroup+" ))");
			if(accounts!=null && !accounts.isEmpty())
				sql.append(" and acct.account_id IN ( "+accounts+") ");
			
			sql.append(" and acct.AD_Table_ID not in (319,735)) "+ 
					   "select sum(amtacctdr) as Dr,sum(amtacctcr) as Cr,MonthName,MonthNo from t1 group by MonthName,MonthNo order by MonthNo");
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
				pstmt.setTimestamp(1, p_DateTo);
				pstmt.setTimestamp(2, p_DateTo);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					BigDecimal dr = rs.getBigDecimal("Dr");
					BigDecimal cr = rs.getBigDecimal("Cr");
					boolean isAdded = false;
					int monthNo = rs.getInt("MonthNo"); // Col2 has january , col3 as february and so on.
					String balance = dr.subtract(cr).toEngineeringString();
					int FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement Where Col_1 like ? and DS_Section='B' and AD_PInstance_ID=? and Col_26='R' ",value,getAD_PInstance_ID());
					if(FundMovement_ID<=0)
					{
						movement = new MDSFundMovement(getCtx(), 0, get_TrxName());
						movement.setCol_0(category);
						movement.setCol_1(value);
						movement.setSeqNo(SeqNo);
						movement.setDS_Section("B");
						movement.setCol_26("R");
						isAdded = true;
						if(monthNo==1)
							movement.setCol_2(balance);
						if(monthNo==2)
							movement.setCol_3(balance);
						if(monthNo==3)
							movement.setCol_4(balance);
						if(monthNo==4)
							movement.setCol_5(balance);
						if(monthNo==5)
							movement.setCol_6(balance);
						if(monthNo==6)
							movement.setCol_7(balance);
						if(monthNo==7)
							movement.setCol_8(balance);
						if(monthNo==8)
							movement.setCol_9(balance);
						if(monthNo==9)
							movement.setCol_10(balance);
						if(monthNo==10)
							movement.setCol_11(balance);
						if(monthNo==11)
							movement.setCol_12(balance);
						if(monthNo==12)
							movement.setCol_13(balance);
						
						movement.setAD_PInstance_ID(getAD_PInstance_ID());
					}
					else
					{
						movement = new MDSFundMovement(getCtx(), FundMovement_ID, get_TrxName());
						if(!isAdded && movement.getCol_2()==null && monthNo==1)
						{
							movement.setCol_2(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_3()==null && monthNo==2)
						{
							movement.setCol_3(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_4()==null && monthNo==3)
						{
							movement.setCol_4(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_5()==null && monthNo==4)
						{
							movement.setCol_5(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_6()==null && monthNo==5)
						{
							movement.setCol_6(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_7()==null && monthNo==6)
						{
							movement.setCol_7(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_8()==null && monthNo==7)
						{
							movement.setCol_8(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_9()==null && monthNo==8)
						{
							movement.setCol_9(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_10()==null && monthNo==9)
						{
							movement.setCol_10(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_11()==null && monthNo==10)
						{
							movement.setCol_11(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_12()==null && monthNo==11)
						{
							movement.setCol_12(balance);
							isAdded = true;
						}
						if(!isAdded && movement.getCol_13()==null && monthNo==12)
						{
							movement.setCol_13(balance);
							isAdded = true;
						}
					}
					movement.saveEx();
				}
				
			}
			catch(Exception e)
			{
				log.severe("Error running cash receipt query. "+sql.toString());
			}
			finally
			{
				DB.close(rs, pstmt);
			}
		}
	}

	private void updateMonthsForCashReceipt() 
	{
		MDSFundMovement movement = null;
		String sqlMonths = "select distinct to_char(to_timestamp (date_part('month', acct.dateacct)::text, 'MM'), 'Month') as MonthName, " + 
				"extract(month from acct.dateacct) as MonthNo,extract(year from acct.dateacct) as Year " + 
				"from fact_Acct acct " + 
				"where acct.dateacct between (SELECT date_trunc('year', ?::timestamp)) and ? order by monthno";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sqlMonths, get_TrxName());
			int i = 0;
			pstmt.setTimestamp(++i, p_DateTo);
			pstmt.setTimestamp(++i, p_DateTo);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				boolean isAdded = false;
				String MonthName = rs.getString("MonthName");
				String year = rs.getString("Year");
				
				int FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement Where DS_Section='A' and AD_PInstance_ID=? and Col_26='R' ",getAD_PInstance_ID());
				if(FundMovement_ID<=0)
				{
					movement = new MDSFundMovement(getCtx(), 0, get_TrxName());
					movement.setCol_2(MonthName.concat("-").concat(year));
					movement.setDS_Section("A");
					movement.setCol_26("R");
					isAdded = true;
					movement.setAD_PInstance_ID(getAD_PInstance_ID());
				}
				else
				{
					if(!isAdded && movement.getCol_3()==null)
					{
						movement.setCol_3(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_4()==null)
					{
						movement.setCol_4(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_5()==null)
					{
						movement.setCol_5(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_6()==null)
					{
						movement.setCol_6(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_7()==null)
					{
						movement.setCol_7(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_8()==null)
					{
						movement.setCol_8(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_9()==null)
					{
						movement.setCol_9(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_10()==null)
					{
						movement.setCol_10(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_11()==null)
					{
						movement.setCol_11(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_12()==null)
					{
						movement.setCol_12(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_13()==null)
					{
						movement.setCol_13(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_14()==null)
					{
						movement.setCol_14(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_15()==null)
					{
						movement.setCol_15(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_16()==null)
					{
						movement.setCol_16(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_17()==null)
					{
						movement.setCol_17(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_18()==null)
					{
						movement.setCol_18(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_19()==null)
					{
						movement.setCol_19(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_20()==null)
					{
						movement.setCol_20(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_21()==null)
					{
						movement.setCol_21(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_22()==null)
					{
						movement.setCol_22(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_23()==null)
					{
						movement.setCol_23(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_24()==null)
					{
						movement.setCol_24(MonthName.concat("-").concat(year));
						isAdded = true;
					}
					if(!isAdded && movement.getCol_25()==null)
					{
						movement.setCol_25(MonthName.concat("-").concat(year));
						isAdded = true;
					}
				}
				movement.saveEx();
			}
		}
		catch(Exception e)
		{
			log.severe("Error running cash receipt query. "+sqlMonths);
		}
		finally
		{
			DB.close(rs, pstmt);
			pstmt = null;
			rs = null;
		}
	}

	private void createBankBalancesAndNonReconciledBalance() 
	{
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String whereClause = " IsActive='Y' ";
		if(p_C_BankAccount_ID>0)
			whereClause += " and C_BankAccount_ID = "+p_C_BankAccount_ID;
		if(p_AD_Org_ID>0 )
			whereClause += " and AD_Org_ID =  "+p_AD_Org_ID;

		List<MBankAccount> bankAccounts = new Query(getCtx(), MBankAccount.Table_Name, whereClause, get_TrxName()).list();
		
		for(MBankAccount account: bankAccounts)
		{
			int C_BankAccount_ID = account.getC_BankAccount_ID();
			BigDecimal currencyDiff = (BigDecimal)account.get_Value("DS_CurrencyDiff");
			currencyDiff = currencyDiff == null ? Env.ZERO:currencyDiff;
			BigDecimal currentBalanceInAccountCurrency = account.getCurrentBalance();
			boolean isCreditCard = account.getBankAccountType().equalsIgnoreCase(MBankAccount.BANKACCOUNTTYPE_Card)? true:false;
			if(isCreditCard && currentBalanceInAccountCurrency.compareTo(Env.ZERO)>0)
				currentBalanceInAccountCurrency = Env.ZERO;
			
			int C_Currency_ID = account.getC_Currency_ID();
			String currency = MCurrency.getISO_Code(getCtx(), C_Currency_ID);
			BigDecimal bhdBalance = MConversionRate.convertBase(getCtx(), currentBalanceInAccountCurrency, C_Currency_ID, Env.getContextAsDate(getCtx(), "@#Date@"), 0, getAD_Client_ID(),  Env.getAD_Org_ID(getCtx()));
			bhdBalance = bhdBalance==null ? Env.ZERO:bhdBalance;
			bhdBalance = bhdBalance.add(currencyDiff);
			BigDecimal totalBHD = bhdBalance;
			BigDecimal nonReconciledBalanceInAccountCurrency = Env.ZERO;
			BigDecimal nonReconciledBalanceInBHD = Env.ZERO;
			int AD_Org_ID = account.getAD_Org_ID();
			MOrg org = new MOrg(getCtx(), AD_Org_ID, get_TrxName());
			
			sql = "with t2 as( " + 
					"select currencyconvert(cp.payamt,cp.c_currency_id ,cb.c_currency_id ,cp.dateacct ,0,cp.ad_client_id ,cp.ad_org_id ) as CheckinTransferInBankCurrency,  " + 
					"currencybase(cp.payamt,cp.c_currency_id ,cp.dateacct,cp.ad_client_id ,cp.ad_org_id ) as nonrenciledbhd, " + 
					"cp.c_currency_id ,cb.c_currency_id as BankCurrency,cp.dateacct, cb.c_bankaccount_id ,cp.AD_Org_ID    " + 
					"from C_Payment_v cp,c_bankaccount cb  " + 
					"where cp.c_bankaccount_id = cb.c_bankaccount_id  " + 
					"and cp.isreconciled ='N' " + 
					"and cp.processed ='Y' " + 
					"and cp.C_BankAccount_ID=?  " + 
					"and cp.DateAcct<= ? ) " + 
					"select  coalesce(round(sum(t2.CheckinTransferInBankCurrency),3),0) as NonReconciledBalance, " + 
					"coalesce(round(sum(t2.nonrenciledbhd),3),0) as nonrenciledbhd from t2 ";
			try
			{
				pstmt = DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, C_BankAccount_ID);
				pstmt.setTimestamp(2, p_DateTo);
				rs = pstmt.executeQuery();
				while(rs.next())
				{
					nonReconciledBalanceInAccountCurrency = rs.getBigDecimal("NonReconciledBalance");
					if(isCreditCard && nonReconciledBalanceInAccountCurrency.compareTo(Env.ZERO)>0)
					{
						nonReconciledBalanceInAccountCurrency = Env.ZERO;
						nonReconciledBalanceInBHD = Env.ZERO;
					}
					else
						nonReconciledBalanceInBHD = rs.getBigDecimal("nonrenciledbhd");
					
					totalBHD = nonReconciledBalanceInBHD.add(totalBHD);
						
				}
				
				
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
					fd.set_ValueNoCheck("DS_CurrentBankBalance", currentBalanceInAccountCurrency);
					fd.set_ValueNoCheck("OrgName", org.getName());
					fd.setAD_Org_ID(AD_Org_ID);
					fd.set_ValueNoCheck("Comments", "Bank Balance");
					fd.setName(account.getName());
					fd.setAccountNo(account.getAccountNo());
					fd.setCurrentBalance(totalBHD);
					fd.set_ValueNoCheck("CurrentBank_NonRenciledPayment", nonReconciledBalanceInBHD);
					fd.set_ValueNoCheck("CurrentBank_Bal",bhdBalance);
					fd.setInterestPercent((BigDecimal)account.get_Value("InterestPercent"));
					fd.saveEx();
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
		}
	}

	private void createFixedDeposits()
	{
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String fdWhereClause = " where 1=1 ";
		if(p_C_BankAccount_ID>0)
			fdWhereClause += " and C_BankAccount_ID = "+p_C_BankAccount_ID;
		if(p_AD_Org_ID>0 )
			fdWhereClause += " and AD_Org_ID =  "+p_AD_Org_ID;
		sql = "select * from ds_fixeddepositsummary_v "+fdWhereClause+" order by C_BankAccount_ID ";
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
				Timestamp maturityDate = rs.getTimestamp("Maturity Date");
				BigDecimal interesetAmt = rs.getBigDecimal("interestpercent");
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
					fd.setAD_Org_ID(AD_Org_ID);
					fd.set_ValueNoCheck("Comments", "Fixed Deposits");
					fd.setDS_BasePrincipalAmount(principalBHD);
					fd.setInterestAmt(interestAmt);
					fd.setName(account.getName());
					fd.setAccountNo(account.getAccountNo());
					fd.setCurrentBalance(postMaturityBHD);
					fd.set_ValueNoCheck("DS_MaturityDate", maturityDate);
					fd.setInterestPercent(interesetAmt);
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
		
	}
}
