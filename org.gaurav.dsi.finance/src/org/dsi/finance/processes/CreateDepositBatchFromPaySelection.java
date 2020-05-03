package org.dsi.finance.processes;

import java.io.File;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import org.compiere.model.MPaySelection;
import org.compiere.model.MPaySelectionCheck;
import org.compiere.model.MPaySelectionLine;
import org.compiere.model.MPayment;
import org.compiere.model.X_C_Payment;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CreateDepositBatchFromPaySelection extends SvrProcess{

	/**	Payment Selection	*/
	public int         				m_C_PaySelection_ID = 0;
	/**	Payment Selection Table	*/
	public int         				m_AD_Table_ID = 0;
	/** Payment Information */
	public MPaySelectionCheck[]     m_checks = null;
	/**	Used PaymentRule	*/
	public String					p_PaymentRule = null;
	/**	Used DocType	*/
	public int						p_C_DocType_ID = -1;
	/**	Used DepositBatch	*/
	public int						p_C_DepositBatch_ID = 0;
	

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Mixed POS Payment = M */
	public static final String PAYMENTRULE_MixedPOSPayment = "M";
	
	File sepafile;
	File azvFile;
	CLogger s_log =CLogger.getCLogger(CreateDepositBatchFromPaySelection.class);
	
	MPaySelection payselection;
	
	String systemName = "";
	String PayselectionName = "";
	
	@Override
	protected void prepare() {
		
		m_C_PaySelection_ID=this.getRecord_ID();
		m_AD_Table_ID=this.getTable_ID();
		
		ProcessInfoParameter[] params = this.getParameter();
		for(ProcessInfoParameter param : params){
			String ParamName=param.getParameterName();
			if(ParamName.equalsIgnoreCase("PaymentRule")){
				p_PaymentRule=param.getParameterAsString();
			} else if(ParamName.equalsIgnoreCase("C_DocType_ID")){
				p_C_DocType_ID=param.getParameterAsInt();
			} else if(ParamName.equalsIgnoreCase("C_DepositBatch_ID")){
				p_C_DepositBatch_ID=param.getParameterAsInt();
			} else {
				s_log.log(Level.SEVERE, "Unknown Parameter: " + ParamName);
			}
		}
		
	}

	@Override
	protected String doIt() throws Exception 
	{
//		verifyForNegativeBalance();
		payselection =new MPaySelection(Env.getCtx(),m_C_PaySelection_ID,null);
		log.info("Create batch process started Process started");
		log.info("s_log Create batch process started Process started");
		systemName = DB.getSQLValueString(get_TrxName(), "select name from AD_System where AD_System_ID = 0 ");		
		m_checks = get(m_C_PaySelection_ID, p_PaymentRule, get_TrxName());		
		CreateAllPayments(m_checks);
		return null;
	}
	
	
//	private void verifyForNegativeBalance() 
//	{
//		int negativePayments = DB.getSQLValue(get_TrxName(), "select count(*) From C_PaySelectionCheck "
//				+ "Where PayAmt <= 0 and C_PaySelection_ID = ? ",m_C_PaySelection_ID);
//		if(negativePayments>0)
//			throw new AdempiereException("@NegativePaymentsExists@");
//		
//	}

	private int CreateAllPayments (MPaySelectionCheck[] checks) {
		
		log.info("in create all payments method fildan ");
		int lastDocumentNo = 0;
		for (int i = 0; i < checks.length; i++)
		{
			MPaySelectionCheck check = checks[i];
			
			CreateSinglePayment(check);
			//	Get Check Document No
			try
			{
				int no = Integer.parseInt(check.getDocumentNo());
				if (lastDocumentNo < no)
					lastDocumentNo = no;
			}
			catch (NumberFormatException ex)
			{
				s_log.log(Level.SEVERE, "DocumentNo=" + check.getDocumentNo(), ex);
			}
		}
		
		if (s_log.isLoggable(Level.FINE)) s_log.fine("Last Document No = " + lastDocumentNo);
		return lastDocumentNo;
	}
	
	private void CreateSinglePayment (MPaySelectionCheck check)
	{
		log.info("in create single payment method fildan ");
		MPayment payment = new MPayment(check.getCtx(), check.getC_Payment_ID(), get_TrxName());
		//	Existing Payment
		if (check.getC_Payment_ID() != 0)
		{
			log.info("Existed payment ");
			//	Update check number
			if (check.getPaymentRule().equals(PAYMENTRULE_Check))
			{
				payment.setCheckNo(check.getDocumentNo());
				payment.saveEx();
			}
		}
		else	//	New Payment
		{
			log.info("New payment ");
			payment = new MPayment(check.getCtx(), 0, get_TrxName());
			payment.setAD_Org_ID(check.getAD_Org_ID());
			payment.setC_BankAccount_ID(payselection.getC_BankAccount_ID());
			//
			if (check.getPaymentRule().equals(PAYMENTRULE_Check))
				payment.setBankCheck (check.getParent().getC_BankAccount_ID(), false, check.getDocumentNo());
			else if (check.getPaymentRule().equals(PAYMENTRULE_CreditCard))
				payment.setTenderType(X_C_Payment.TENDERTYPE_CreditCard);
			else if (check.getPaymentRule().equals(PAYMENTRULE_DirectDeposit)
					|| check.getPaymentRule().equals(PAYMENTRULE_DirectDebit)){
				payment.setBankACH(check);
				payment.setC_BP_BankAccount_ID(check.getC_BP_BankAccount_ID());
			}
			payment.setC_DocType_ID(p_C_DocType_ID);			
			payment.setTrxType(X_C_Payment.TRXTYPE_CreditPayment);
			payment.setAmount(check.get_ValueAsInt("C_Currency_ID"), check.getPayAmt());
			payment.setDiscountAmt(check.getDiscountAmt());
			payment.setDateTrx(check.getParent().getPayDate());
			payment.setDateAcct(payment.getDateTrx()); // globalqss [ 2030685 ]
			payment.setC_BPartner_ID(check.getC_BPartner_ID());
			//	Link to Invoice
			MPaySelectionLine[] psls = check.getPaySelectionLines(true);
			if (s_log.isLoggable(Level.FINE)) s_log.fine("confirmPrint - " + check + " (#SelectionLines=" + psls.length + ")");
			if (check.getQty() == 1 && psls != null && psls.length == 1)
			{
				MPaySelectionLine psl = psls[0];
				if (s_log.isLoggable(Level.FINE)) s_log.fine("Map to Invoice " + psl);
				//
				payment.setC_Currency_ID(psl.getC_Invoice().getC_Currency_ID());
				payment.setC_Invoice_ID (psl.getC_Invoice_ID());
				payment.setDiscountAmt (psl.getDiscountAmt());
				payment.setWriteOffAmt(psl.getDifferenceAmt());
				BigDecimal overUnder = psl.getOpenAmt().subtract(psl.getPayAmt())
						.subtract(psl.getDiscountAmt()).subtract(psl.getDifferenceAmt());
				payment.setOverUnderAmt(overUnder);
			}
			else
				payment.setDiscountAmt(Env.ZERO);
			payment.setWriteOffAmt(Env.ZERO);
			payment.saveEx();
			//
			int C_Payment_ID = payment.get_ID();
			if (C_Payment_ID < 1)
				s_log.log(Level.SEVERE, "Payment not created=" + check);
			else
			{
				check.setC_Payment_ID (C_Payment_ID);
				check.saveEx();	//	Payment process needs it
			}
		}	
		check.setIsPrinted(true);
		check.setProcessed(true);
		check.saveEx();

	}
	
	public MPaySelectionCheck[] get (int C_PaySelection_ID, String PaymentRule, String trxName)
	{
		if (s_log.isLoggable(Level.FINE)) s_log.fine("C_PaySelection_ID=" + C_PaySelection_ID
			+ ", PaymentRule=" +  PaymentRule);
		ArrayList<MPaySelectionCheck> list = new ArrayList<MPaySelectionCheck>();
		
		StringBuilder sql = new StringBuilder("SELECT * FROM C_PaySelectionCheck "
			+ "WHERE C_PaySelection_ID=? ");
		if(PaymentRule!=null)
			sql.append(" AND PaymentRule=? ");
		
		sql.append(" ORDER BY C_PaySelectionCheck_ID"); // order by the C_PaySelectionCheck_ID
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), trxName);
			pstmt.setInt(1, C_PaySelection_ID);
			if(PaymentRule!=null)
				pstmt.setString(2, PaymentRule);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MPaySelectionCheck check = new MPaySelectionCheck (Env.getCtx(), rs, trxName);
				list.add(check);
			}
		}
		catch (SQLException e)
		{
			s_log.severe(sql.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//  convert to Array
		MPaySelectionCheck[] retValue = new MPaySelectionCheck[list.size()];
		list.toArray(retValue);
		return retValue;
	}   //  get
	//Payment Creation Based on MPaySelectionCheck.java
}
