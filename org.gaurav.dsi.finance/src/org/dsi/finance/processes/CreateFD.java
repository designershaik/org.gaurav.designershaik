package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_C_Payment;
import org.compiere.model.I_GL_Journal;
import org.compiere.model.I_GL_JournalLine;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MConversionRate;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MPayment;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSFixedDeposit;
import org.joda.time.DateTime;

public class CreateFD extends SvrProcess 
{
	int FixedDeposit_ID = 0;
	MDSFixedDeposit deposit ;
	String actionWithFD;
	int FDTerm = 0;
	boolean IncludeInterestWhileRenewing = false ;
	Timestamp renewalDate ;
	BigDecimal InterestPercent;
	BigDecimal renewalPrincipalAmt = Env.ZERO;
	String newCertificateNumber;
	String GLDescription ;
	String PostingType ; 
	int C_DocType_ID;
	int GL_Category_ID;
	MAcctSchema schema;
	@Override
	protected void prepare() 
	{
		FixedDeposit_ID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DS_FDTerminateOrRenew"))
				actionWithFD = para[i].getParameterAsString();
			else if (name.equals("DS_FDTerm"))
				FDTerm = para[i].getParameterAsInt();
			else if (name.equals("DS_WithInterest"))
				IncludeInterestWhileRenewing = para[i].getParameterAsBoolean();
			else if (name.equals("DateAcct"))
				renewalDate = para[i].getParameterAsTimestamp();
			else if (name.equals("InterestPercent"))
				InterestPercent = para[i].getParameterAsBigDecimal();
			else if (name.equals("DS_CertificateNumber"))
				newCertificateNumber = para[i].getParameterAsString();
			else if (name.equals("Description"))
				GLDescription = para[i].getParameterAsString();
			else if (name.equals("PostingType"))
				PostingType = para[i].getParameterAsString();
			else if (name.equals("C_DocType_ID"))
				C_DocType_ID = para[i].getParameterAsInt();
			else if (name.equals("GL_Category_ID"))
				GL_Category_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		deposit = new MDSFixedDeposit(getCtx(), FixedDeposit_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		MAcctSchema[] macctschemas =MAcctSchema.getClientAcctSchema(getCtx(), getAD_Client_ID(), get_TrxName());
		for(MAcctSchema macctscheme:macctschemas)
		{
			schema = macctscheme;
		}
		if(actionWithFD==null)
			throw new AdempiereException("Please provide the type");
		if(actionWithFD.equalsIgnoreCase("NE"))
		{
			MPayment newDeposit = CreatePayment(deposit.getDS_PrincipalAmtCharge_ID(),deposit.getDS_PrincipalAmount(),deposit.getC_Currency_ID(),false);
			addLog(newDeposit.getC_Payment_ID(),newDeposit.getDateAcct(),newDeposit.getPayAmt(),newDeposit.getDocumentNo(),I_C_Payment.Table_ID,newDeposit.getC_Payment_ID());
			
		}
		else if(actionWithFD.equalsIgnoreCase("RE"))
		{
//			renewalDate = deposit.getDS_MaturityDate();
			if(FDTerm==deposit.getDS_FDTerm() && !IncludeInterestWhileRenewing)
				renewalPrincipalAmt = deposit.getDS_PrincipalAmount();
			else
				renewalPrincipalAmt = deposit.getDS_PrincipalAmount().add(deposit.getInterestAmt());
			
			renewalPrincipalAmt = renewalPrincipalAmt.setScale(3,RoundingMode.CEILING);
			int C_Currency_ID = deposit.getC_Currency_ID();
			int PrincipalAmtChargeID = deposit.getDS_PrincipalAmtCharge_ID();
			int InterestAmtChargeID = deposit.getDS_InterestAmtCharge_ID();
			int principalValidCombinationID = DB.getSQLValue(get_TrxName(), "Select Ch_Expense_Acct From C_Charge_Acct Where C_Charge_ID = ? ", PrincipalAmtChargeID);
			int interestValidCombinationID = DB.getSQLValue(get_TrxName(), "Select Ch_Expense_Acct From C_Charge_Acct Where C_Charge_ID = ? ", InterestAmtChargeID);
			
			Timestamp newFDMaturityDate = getMaturityDate();
			BigDecimal renewalInterestAmt = getInterestAmt(renewalPrincipalAmt);
			BigDecimal renewalInterestBaseAmt = MConversionRate.convertBase(getCtx(), renewalInterestAmt, deposit.getC_Currency_ID(), renewalDate, 114,getAD_Client_ID(), deposit.getAD_Org_ID());
			BigDecimal renewalPrincipalBaseAmt =  MConversionRate.convertBase(getCtx(), renewalPrincipalAmt, deposit.getC_Currency_ID(), renewalDate, 114,getAD_Client_ID(), deposit.getAD_Org_ID());
			
			MDSFixedDeposit renewdDeposit = new MDSFixedDeposit(getCtx(), 0, get_TrxName());
			renewdDeposit.setC_Bank_ID(deposit.getC_Bank_ID());
			renewdDeposit.setC_BankAccount_ID(deposit.getC_BankAccount_ID());
			renewdDeposit.setDS_PrincipalAmount(deposit.getDS_PrincipalAmount());
			renewdDeposit.setC_Currency_ID(C_Currency_ID);
			renewdDeposit.setDS_FDTerm(FDTerm);
			renewdDeposit.setDS_MaturityDate(newFDMaturityDate);
			renewdDeposit.setDateAcct(renewalDate);
			renewdDeposit.setDS_FDTerm(FDTerm);
			renewdDeposit.setDS_PrincipalAmount(renewalPrincipalAmt);
			renewdDeposit.setC_BPartner_ID(deposit.getC_BPartner_ID());
			renewdDeposit.setDS_PrincipalAmtCharge_ID(PrincipalAmtChargeID);
			renewdDeposit.setDS_CertificateNumber(newCertificateNumber);
			renewdDeposit.setInterestAmt(renewalInterestAmt);
			renewdDeposit.setDS_BaseInterestAmount(renewalInterestBaseAmt);
			renewdDeposit.setDS_BasePrincipalAmount(renewalPrincipalBaseAmt);
			renewdDeposit.setInterestPercent(InterestPercent);
			renewdDeposit.setDS_InterestAmtCharge_ID(InterestAmtChargeID);
			renewdDeposit.setDateAcct(renewalDate);
			deposit.setDS_IsNewFD(true);
			renewdDeposit.save();
			
			deposit.setDS_RenewedFD_ID(renewdDeposit.getDS_FixedDeposit_ID());
			deposit.setProcessed(true);
			deposit.setDS_Matured(true);
			deposit.setCloseDate(renewalDate);
			deposit.setDS_IsNewFD(false);
			deposit.save();
			
			
			int C_Period_ID = getPeriodBasedOnTheDateForGL(renewalDate);
			MJournal journal = new MJournal(getCtx(), 0, get_TrxName());
			journal.setDescription(GLDescription);
			journal.setPostingType(PostingType);
			journal.setC_DocType_ID(C_DocType_ID);
			journal.setDateAcct(renewalDate);
			journal.setDateDoc(renewalDate);
			journal.setC_Currency_ID(deposit.getC_Currency_ID());
			journal.setC_Period_ID(C_Period_ID);
			journal.setAD_Org_ID(deposit.getAD_Org_ID());
			journal.setC_AcctSchema_ID(schema.getC_AcctSchema_ID());
			journal.setGL_Category_ID(GL_Category_ID);
			journal.setC_ConversionType_ID(114);
			journal.save();
			
			MJournalLine totalDrEntry= createPrincipalJournalLine(C_Currency_ID,deposit.getC_BPartner_ID(),principalValidCombinationID,renewalPrincipalAmt,renewalPrincipalBaseAmt,journal.getGL_Journal_ID(),true,renewdDeposit.getDS_FixedDeposit_ID());
			addLog(totalDrEntry.getGL_JournalLine_ID(),totalDrEntry.getDateAcct(),totalDrEntry.getAmtAcctDr(),"New FD : "+totalDrEntry.getDescription(),I_GL_JournalLine.Table_ID,totalDrEntry.getGL_JournalLine_ID());
			if(IncludeInterestWhileRenewing)
			{
				MJournalLine interestCrEntry = createPrincipalJournalLine(C_Currency_ID,deposit.getC_BPartner_ID(),interestValidCombinationID,deposit.getInterestAmt(),deposit.getDS_BaseInterestAmount(),journal.getGL_Journal_ID(),false,FixedDeposit_ID);
				addLog(interestCrEntry.getGL_JournalLine_ID(),interestCrEntry.getDateAcct(),interestCrEntry.getAmtAcctCr(),"Old FD principal Amt : "+interestCrEntry.getDescription(),I_GL_JournalLine.Table_ID,interestCrEntry.getGL_JournalLine_ID());
				
				MJournalLine principalCrEntry = createPrincipalJournalLine(C_Currency_ID,deposit.getC_BPartner_ID(),principalValidCombinationID,deposit.getDS_PrincipalAmount(),deposit.getDS_BasePrincipalAmount(),journal.getGL_Journal_ID(),false,FixedDeposit_ID);
				addLog(principalCrEntry.getGL_JournalLine_ID(),principalCrEntry.getDateAcct(),principalCrEntry.getAmtAcctCr(),"Old FD principal Amt Interest: "+principalCrEntry.getDescription(),I_GL_JournalLine.Table_ID,principalCrEntry.getGL_JournalLine_ID());
			}
			else
			{
				MPayment InterestReceived = CreatePayment(deposit.getDS_InterestAmtCharge_ID(),deposit.getInterestAmt(),deposit.getC_Currency_ID(),true);
				addLog(InterestReceived.getC_Payment_ID(),InterestReceived.getDateAcct(),InterestReceived.getPayAmt(),InterestReceived.getDocumentNo(),I_C_Payment.Table_ID,InterestReceived.getC_Payment_ID());
			}
			
				
			
		}
		else if(actionWithFD.equalsIgnoreCase("TE"))
		{
			MPayment interestReceiptEntry = CreatePayment(deposit.getDS_InterestAmtCharge_ID(),deposit.getInterestAmt(),deposit.getC_Currency_ID(),true);
			addLog(interestReceiptEntry.getC_Payment_ID(),interestReceiptEntry.getDateAcct(),interestReceiptEntry.getPayAmt(),interestReceiptEntry.getDocumentNo(),I_C_Payment.Table_ID,interestReceiptEntry.getC_Payment_ID());
			
			MPayment  terminateFDReceipt = CreatePayment(deposit.getDS_PrincipalAmtCharge_ID(),deposit.getDS_PrincipalAmount(),deposit.getC_Currency_ID(),true);
			addLog(terminateFDReceipt.getC_Payment_ID(),terminateFDReceipt.getDateAcct(),terminateFDReceipt.getPayAmt(),terminateFDReceipt.getDocumentNo(),I_C_Payment.Table_ID,terminateFDReceipt.getC_Payment_ID());
		}
		return null;
	}
	
	private MPayment CreatePayment(int ChargeID, BigDecimal Amt, int C_Currency_ID, boolean receipt) 
	{
		MPayment payment = new MPayment(getCtx(), 0, get_TrxName());
		payment.setC_BankAccount_ID(deposit.getC_BankAccount_ID());
		payment.setAmount(C_Currency_ID,Amt );
		payment.setDateAcct(deposit.getDateAcct());
		payment.setDateTrx(deposit.getDateAcct());
		payment.setC_BPartner_ID(deposit.getC_BPartner_ID());
		payment.setC_Charge_ID(ChargeID);
		payment.setCheckNo(deposit.getDS_CertificateNumber());
		payment.set_ValueOfColumn("DS_FixedDeposit_ID", FixedDeposit_ID);
		payment.setC_DocType_ID(receipt);
		payment.setAD_Org_ID(deposit.getAD_Org_ID());
		payment.saveEx();
		
		return payment;
	}

	private MJournalLine createPrincipalJournalLine(int C_Currency_ID,int C_BPartner_ID, int validCombinationID,BigDecimal amount,
			BigDecimal baseamount, int GL_Journal_ID,boolean isDr, int depositID) 
	{
		MAccount validCombination = new MAccount(getCtx(), validCombinationID, get_TrxName());
		MJournalLine line = new MJournalLine(getCtx(), 0, get_TrxName());
		line.setC_Currency_ID(C_Currency_ID);
		line.setGL_Journal_ID(GL_Journal_ID);
		line.setC_BPartner_ID(deposit.getC_BPartner_ID());
		line.setAccount_ID(validCombination.getAccount_ID());
		line.setAD_Org_ID(deposit.getAD_Org_ID());
		line.setDescription(GLDescription);
		if(isDr)
		{
			line.setAmtAcct(baseamount, Env.ZERO);
			line.setAmtSourceDr(amount);
			line.setAmtSourceCr(Env.ZERO);
		}
		else
		{
			line.setAmtAcct(Env.ZERO,baseamount);
			line.setAmtSourceDr(Env.ZERO);
			line.setAmtSourceCr(amount);
		}
		line.setC_ValidCombination_ID(validCombinationID);
		line.setC_ConversionType_ID(114);
		line.set_ValueOfColumn("DS_FixedDeposit_ID", depositID);
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

	private BigDecimal getInterestAmt(BigDecimal PrincipalAmt) 
	{
		BigDecimal interestAmt = Env.ZERO;
		BigDecimal interest = InterestPercent.divide(Env.ONEHUNDRED, 4,RoundingMode.CEILING);
		interestAmt  = PrincipalAmt.multiply(interest).multiply((new BigDecimal(FDTerm).divide(new BigDecimal(360), 3,RoundingMode.CEILING))).setScale(3, RoundingMode.CEILING);
		return interestAmt;
	}

	private Timestamp getMaturityDate() 
	{
		DateTime dateTime = new DateTime(renewalDate);
		dateTime = dateTime.plusDays(FDTerm);
		
		Timestamp maturityDate = new Timestamp(dateTime.getMillis());
		return maturityDate;
	}
}

	