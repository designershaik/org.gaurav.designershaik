package com.gaurav.dsi.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MConversionRate;
import org.compiere.model.MConversionType;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSCouponSchedule;

public class ProcessCouponReceiptAndAdjustAmortization extends SvrProcess{

	int p_CouponSchedule_ID ;
	MDSCouponSchedule sc = null ;
	Timestamp p_Date = null;
	int p_C_Charge_ID = 0;
	String p_description = null;
	int p_C_BPartner_ID = 0;
	BigDecimal couponAmt = Env.ZERO;
	int p_C_Tax_ID = 0 ; 
	int p_C_DocType_ID = 0 ; 
	int p_GL_Category_ID = 0 ;
	String p_PostingType = "";
	int p_Amortization_Charge_ID = 0 ;
	int C_ConversionType_ID  = 0 ;
	int C_Currency_ID = 0 ;
	MAcctSchema as = null ;
	int p_AccruedIncome_Charge_ID = 0 ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DateAcct"))
				p_Date = para[i].getParameterAsTimestamp();
			else if (name.equals("C_Charge_ID"))
				p_C_Charge_ID = para[i].getParameterAsInt();
			else if (name.equals("Description"))
				p_description =para[i].getParameterAsString();
			else if (name.equals("GS_CouponAmount"))
				couponAmt =para[i].getParameterAsBigDecimal();
			else if (name.equals("C_Tax_ID"))
				p_C_Tax_ID =para[i].getParameterAsInt();
			else if (name.equals("C_DocType_ID"))
				p_C_DocType_ID =para[i].getParameterAsInt();
			else if (name.equals("GL_Category_ID"))
				p_GL_Category_ID =para[i].getParameterAsInt();
			else if (name.equals("PostingType"))
				p_PostingType =para[i].getParameterAsString();
			else if (name.equals("Amortization_Charge_ID"))
				p_Amortization_Charge_ID =para[i].getParameterAsInt();
			else if (name.equals("AccruedIncome_Charge_ID"))
				p_AccruedIncome_Charge_ID =para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
		p_CouponSchedule_ID = getRecord_ID();
		sc = new MDSCouponSchedule(getCtx(), p_CouponSchedule_ID, get_TrxName());
		C_ConversionType_ID = MConversionType.getDefault(getAD_Client_ID());
		C_Currency_ID = sc.getC_Currency_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(sc.getRef_Invoice_ID()<=0)
		{
			int invoiceDocType_ID = DB.getSQLValue(get_TrxName(), "Select C_DocType_ID From C_DocType Where DS_IsInvestmentRelated='Y' and AD_Client_ID = ? and DocBaseType = ? ",getAD_Client_ID(),MDocType.DOCBASETYPE_ARInvoice);
			MClient client = MClient.get(getCtx());
			as = client.getAcctSchema();
			MBPartner bp = (MBPartner)sc.getC_BPartner();
			MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
			invoice.setBPartner(bp);
			invoice.setC_DocTypeTarget_ID(invoiceDocType_ID);
			invoice.setDateAcct(p_Date);
			invoice.setDescription(p_description);
			if(invoice.getC_Currency_ID()!=sc.getC_Currency_ID())
			{
				int M_PriceList_ID =  DB.getSQLValue(get_TrxName(), "Select M_PriceList_ID From M_PriceList Where DS_IsInvestmentRelated='Y' and AD_Client_ID = ? and C_Currency_ID = ? ",getAD_Client_ID(),sc.getC_Currency_ID());
				invoice.setM_PriceList_ID(M_PriceList_ID);
			}
			invoice.saveEx();

			MInvoiceLine line = new MInvoiceLine(invoice);
			line.setC_Charge_ID(p_AccruedIncome_Charge_ID);
			line.setC_Tax_ID(p_C_Tax_ID);
			line.setDescription(p_description);
			line.setPrice(couponAmt);
			line.set_ValueNoCheck("RelatedProduct_ID", sc.getM_Product_ID());
			line.setQty(Env.ONE);
			line.save();
			
			sc.setRef_Invoice_ID(invoice.getC_Invoice_ID());
			sc.saveEx();
			
			addLog(invoice.getC_Invoice_ID(), null, null,"Coupon Receipt: "+invoice.getDocumentNo(), MInvoice.Table_ID, invoice.getC_Invoice_ID());
			BigDecimal adjustedAmortization = sc.getDS_AmortizationAmt_OverSchedul();
			int C_Period_ID = getPeriodBasedOnTheDateForGL(p_Date);

			BigDecimal rate = MConversionRate.getRate(C_Currency_ID,as.getC_Currency_ID(), p_Date, C_ConversionType_ID, sc.getAD_Client_ID(), 0);
			BigDecimal baseAmt = MConversionRate.convertBase(getCtx(), adjustedAmortization, C_Currency_ID, p_Date, C_ConversionType_ID, sc.getAD_Client_ID(), sc.getAD_Org_ID());

			MJournal journal = new MJournal(getCtx(), 0, get_TrxName());
			journal.setDescription(p_description);
			journal.setPostingType(p_PostingType);
			journal.setC_DocType_ID(p_C_DocType_ID);
			journal.setDateAcct(p_Date);
			journal.setDateDoc(p_Date);
			journal.setC_Currency_ID(C_Currency_ID);
			journal.setAD_Org_ID(sc.getAD_Org_ID());
			journal.setC_AcctSchema_ID(as.getC_AcctSchema_ID());
			journal.setGL_Category_ID(p_GL_Category_ID);
			journal.setC_Period_ID(C_Period_ID);
			journal.setC_ConversionType_ID(114);
			journal.setCurrencyRate(rate);
			journal.save();
			int amortization_ValComb_ID = DB.getSQLValue(get_TrxName(), "Select Ch_Expense_Acct From C_Charge_Acct Where C_Charge_ID = ? ", p_Amortization_Charge_ID);
			int asset_ValidComb_ID = DB.getSQLValue(get_TrxName(), "Select p_Asset_Acct From M_Product_Acct Where M_Product_ID = ? ", sc.getM_Product_ID());
			int couponIncome_ValidComb_ID = DB.getSQLValue(get_TrxName(), "Select Ch_Expense_Acct From C_Charge_Acct Where C_Charge_ID = ? ", p_C_Charge_ID);
			int accruedIncome_ValidComb_ID = DB.getSQLValue(get_TrxName(), "Select Ch_Expense_Acct From C_Charge_Acct Where C_Charge_ID = ? ", p_AccruedIncome_Charge_ID);

			MJournalLine assetAccountDrEntry= createPrincipalJournalLine(asset_ValidComb_ID,adjustedAmortization,rate,baseAmt,journal.getGL_Journal_ID(),true);
			MJournalLine amortizationCrEntry = createPrincipalJournalLine(amortization_ValComb_ID,adjustedAmortization,rate,baseAmt,journal.getGL_Journal_ID(),false);

			MJournalLine accruedIncomeDrEntry= createPrincipalJournalLine(accruedIncome_ValidComb_ID,couponAmt,rate,baseAmt,journal.getGL_Journal_ID(),true);
			MJournalLine couponIncomeCrEntry = createPrincipalJournalLine(couponIncome_ValidComb_ID,couponAmt,rate,baseAmt,journal.getGL_Journal_ID(),false);
			log.info("Asset Account "+assetAccountDrEntry.getC_ValidCombination_ID()+" Amortization Account: "+amortizationCrEntry.getC_ValidCombination_ID()+
					" Accrued Income Account: "+accruedIncomeDrEntry.getC_ValidCombination_ID()
					+"Coupon Income Account: "+couponIncomeCrEntry.getC_ValidCombination_ID());

			addLog(journal.getGL_Journal_ID(), null, null, journal.getDocumentNo(), MJournal.Table_ID, journal.getGL_Journal_ID());
			sc.setGL_Journal_ID(journal.getGL_Journal_ID());
			sc.saveEx();

		}
		return null;
	}

	private MJournalLine createPrincipalJournalLine(int validCombinationID,BigDecimal adjustedAmortization,BigDecimal rate,BigDecimal baseAmt, int gl_Journal_ID, boolean isDr) 
	{
	
				
		MAccount validCombination = new MAccount(getCtx(), validCombinationID, get_TrxName());
		MJournalLine line = new MJournalLine(getCtx(), 0, get_TrxName());
		line.setC_Currency_ID(C_Currency_ID);
		line.setGL_Journal_ID(gl_Journal_ID);
		line.setC_BPartner_ID(sc.getC_BPartner_ID());
		line.setAccount_ID(validCombination.getAccount_ID());
		line.setAD_Org_ID(sc.getAD_Org_ID());
		line.setDescription(p_description);
		line.setM_Product_ID(sc.getM_Product_ID());
		line.setCurrencyRate(rate);
		if(isDr)
		{
			line.setAmtSourceDr(adjustedAmortization);
			line.setAmtAcct(baseAmt, Env.ZERO);
		}
		else
		{ 
			line.setAmtSourceCr(adjustedAmortization);
			line.setAmtAcct(Env.ZERO,baseAmt);
		}
		line.setC_ConversionType_ID(C_ConversionType_ID);
		line.save();
		
		return line;
	}
	
	private int getPeriodBasedOnTheDateForGL(Timestamp DateAcct) 
	{
		int C_Period_ID = 0;
		String sql = "SELECT C_Period_ID "
				+ "FROM C_Period "
				+ "WHERE C_Year_ID IN "
				+ "	(SELECT C_Year_ID FROM C_Year WHERE C_Calendar_ID ="
				+ "  (SELECT C_Calendar_ID FROM AD_ClientInfo WHERE AD_Client_ID=?))"
				+ " AND ? BETWEEN StartDate AND EndDate"
				+ " AND IsActive='Y'"
				+ " AND PeriodType='S'";
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				pstmt = DB.prepareStatement(sql, null);
				pstmt.setInt(1, getAD_Client_ID());
				pstmt.setTimestamp(2, DateAcct);
				rs = pstmt.executeQuery();
				if (rs.next())
					C_Period_ID = rs.getInt(1);
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null;
				pstmt = null;
			}
			return C_Period_ID;
	}
}
