package org.dsi.finance.eventhandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.PeriodClosedException;
import org.compiere.model.I_C_Cash;
import org.compiere.model.I_C_CashLine;
import org.compiere.model.I_C_Invoice;
import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_C_Payment;
import org.compiere.model.I_GL_JournalLine;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_Inventory;
import org.compiere.model.I_M_InventoryLine;
import org.compiere.model.I_M_Movement;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MBPartner;
import org.compiere.model.MBankAccount;
import org.compiere.model.MBankStatement;
import org.compiere.model.MCash;
import org.compiere.model.MCharge;
import org.compiere.model.MClient;
import org.compiere.model.MClientInfo;
import org.compiere.model.MDocType;
import org.compiere.model.MElementValue;
import org.compiere.model.MInOut;
import org.compiere.model.MInventory;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.MMovement;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MOrderPaySchedule;
import org.compiere.model.MPaySchedule;
import org.compiere.model.MPayment;
import org.compiere.model.MPaymentTerm;
import org.compiere.model.MPeriod;
import org.compiere.model.MPrivateAccess;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MTax;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.dsi.finance.processes.SendEmails;
import org.gaurav.dsi.model.I_DS_Product_Request;
import org.gaurav.dsi.model.MDSDenomiationDetCashBook;
import org.gaurav.dsi.model.MDSDenomiationDetTrans;
import org.gaurav.dsi.model.MDSDenominationOnStatement;
import org.gaurav.dsi.model.MDSIExportPayments;
import org.gaurav.dsi.model.MDSIExportPaymentsLine;
import org.gaurav.dsi.model.MDSSettledInvoices;
import org.osgi.service.event.Event;

public class FinanceEventHandler extends AbstractEventHandler
{
	Properties ctx = Env.getCtx();
	MClient client ; 
	String trxName ;
	@Override
	protected void doHandleEvent(Event event) 
	{
		PO po = getPO(event);
		trxName = po.get_TrxName();
		client = new MClient(ctx, po.get_TrxName());
		if(po.get_ValueAsInt("C_Charge_ID")!=0)
		{
			validateIfCostCenterAndRevenueCodesExist(po);
		}
		if(event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE) || event.getTopic().equals(IEventTopics.PO_BEFORE_NEW))
		{
			boolean isProcessed = po.get_ValueAsBoolean("Processed");
			if(!isProcessed)
			{
				int AD_Org_ID = po.getAD_Org_ID();
				int C_TargetDocType_ID = po.get_ValueAsInt("C_DocTypeTarget_ID");
				if(C_TargetDocType_ID==0)
					C_TargetDocType_ID = po.get_ValueAsInt("C_DocType_ID");
				Timestamp dateAcct = (Timestamp)po.get_Value("DateAcct");
				if(po instanceof MOrder || po instanceof MInvoice || po instanceof MInOut || po instanceof MPayment || po instanceof MJournal)
					MPeriod.testPeriodOpen(Env.getCtx(), dateAcct, C_TargetDocType_ID, AD_Org_ID);
				
				if(po instanceof MCash)
				{
					if (!MPeriod.isOpen(ctx, dateAcct, MDocType.DOCBASETYPE_CashJournal, AD_Org_ID))
						throw new PeriodClosedException(dateAcct,MDocType.DOCBASETYPE_CashJournal);
				}
				if(po instanceof MMovement || po instanceof MInventory)
				{
					dateAcct = (Timestamp)po.get_Value("MovementDate");
					MPeriod.testPeriodOpen(Env.getCtx(), dateAcct, C_TargetDocType_ID, AD_Org_ID);
				}
			}
		}
		if(po instanceof MInvoiceLine)
		{
			MInvoiceLine line = (MInvoiceLine)po;
			MInvoice invoice = new MInvoice(Env.getCtx(), line.getC_Invoice_ID(), po.get_TrxName());
			MCharge charge=new MCharge(Env.getCtx(), line.getC_Charge_ID(), po.get_TrxName());
			if(!event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_DELETE))
			{
				boolean isPRRequired=charge.get_ValueAsBoolean("DS_IsPRRequired");
				MOrderLine coLine = new MOrderLine(ctx, line.getC_OrderLine_ID(), po.get_TrxName());
				int R_Request_ID = coLine.get_ValueAsInt("R_Request_ID");
				if(!invoice.isSOTrx() && (invoice.getDocStatus().equalsIgnoreCase("DR") || invoice.getDocStatus().equalsIgnoreCase("IP")))
				{
					if(line.getC_OrderLine_ID()<=0 && isPRRequired)
					{
						if(po.get_ValueAsInt("R_Request_ID")==0 || po.get_ValueAsInt("DS_Product_Request_ID")==0)
							throw new AdempiereException("Request is mandatory");
					}
					if(!invoice.get_ValueAsBoolean("DS_IsZeroValueAllowed") && line.getLineNetAmt().compareTo(Env.ZERO)==0 && line.getLineTotalAmt().compareTo(Env.ZERO)==0)
					{
						throw new AdempiereException(Msg.getMsg(Env.getCtx(), "ZeroValueInvoiceNotAllowed"));
					}
					if(line.getC_OrderLine_ID()!=0 && R_Request_ID!=0)
					{
						int DS_Product_Request_ID = coLine.get_ValueAsInt("DS_Product_Request_ID");
						line.set_ValueOfColumn("R_Request_ID", R_Request_ID);
						line.set_ValueOfColumn("DS_Product_Request_ID",DS_Product_Request_ID==0 ? null : DS_Product_Request_ID );
					}
				}
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_DELETE))
			{
				int DS_Product_Request_ID = line.get_ValueAsInt("DS_Product_Request_ID");
				if(DS_Product_Request_ID>0)
					DB.executeUpdate("Update DS_Product_Request set C_InvoiceLine_ID = null where DS_Product_Request_ID="+DS_Product_Request_ID, line.get_TrxName());
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE) || event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW))
			{
				BigDecimal lineTotalAmt = DB.getSQLValueBD(trxName, "Select sum(LineTotalAmt) From C_InvoiceLine Where C_Invoice_ID = ? ", line.getC_Invoice_ID());
				DB.executeUpdateEx("Update C_Invoice Set GrandTotal = ? Where C_Invoice_ID = ? ", new Object[] {lineTotalAmt,invoice.getC_Invoice_ID()},trxName);
			}
			if((event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW) || event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_CHANGE))  && !invoice.isSOTrx())
			{
				Integer C_Tax_ID = line.getC_Tax_ID();
				MTax tax = new MTax(ctx,C_Tax_ID,null);
				int C_BPartner_ID = invoice.getC_BPartner_ID();
				MBPartner bp = new MBPartner(ctx, C_BPartner_ID, trxName);
				if(tax.getRate().compareTo(Env.ZERO)>0 && (bp.getTaxID()==null || bp.getTaxID().isBlank()))
					throw new AdempiereException("Business partner is not tax registered");
			}
		}
		if(po instanceof MOrderLine)
		{
			MOrderLine coLine =  (MOrderLine)po;
			MOrder order = new MOrder(Env.getCtx(), coLine.getC_Order_ID(), po.get_TrxName());
			boolean isPRRequired = false;
			if(!order.isSOTrx() && (order.getDocStatus().equalsIgnoreCase("DR") || order.getDocStatus().equalsIgnoreCase("IP")))
			{
				if(coLine.getM_Product_ID()<=0)
				{
					MCharge charge=new MCharge(Env.getCtx(), coLine.getC_Charge_ID(), po.get_TrxName());
					isPRRequired = charge.get_ValueAsBoolean("DS_IsPRRequired");
				}
				if(coLine.getC_Charge_ID()<=0)
				{
					MProductCategory cat = new MProductCategory(ctx, coLine.getM_Product().getM_Product_Category_ID(), trxName);
					isPRRequired = cat.get_ValueAsBoolean("DS_IsPRRequired");
				}
				if((coLine.get_ValueAsInt("R_Request_ID")==0 || coLine.get_ValueAsInt("DS_Product_Request_ID")==0) && isPRRequired)
					throw new AdempiereException("Purchase request is mandatory");
			}
		}
		if(po instanceof MInvoice)
		{
			MInvoice ci = (MInvoice)po;
			int billpartnerID=ci.getC_BPartner_ID();
			MBPartner bp = new MBPartner(ctx, billpartnerID, ci.get_TrxName());
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_COMPLETE))
			{
				int isChargeExistWithLockRecord = DB.getSQLValue(trxName, "select count(line.*) "
						+ "from C_InvoiceLine line,C_Charge charge where line.C_Charge_ID = charge.C_Charge_ID "
						+ "and charge.DS_AutoLockRecord='Y' and line.C_Invoice_ID = ? ",ci.getC_Invoice_ID());
				if(isChargeExistWithLockRecord>=1)
					lockParentRecord(Env.getAD_User_ID(ctx),MInvoice.Table_ID,ci.getC_Invoice_ID());
			
				int billPartnerLocationID=ci.getC_BPartner_Location_ID();
				
				if (ci.isSendEMail()) 
					SendEmails.sendEmail(ci,billpartnerID,billPartnerLocationID,ci.get_TrxName());
				
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW))
			{
				ci.setSendEMail(bp.isSendEMail());
			}
		}
		if(po instanceof MPayment)
		{
			MPayment payment = (MPayment)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW) || event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_CHANGE))
			{
				if(!payment.isReceipt())
				{
					int C_BankAccount_ID = payment.getC_BankAccount_ID();
					MBankAccount account = new MBankAccount(ctx, C_BankAccount_ID, payment.get_TrxName());
					boolean allowDifferentCurrency = account.get_ValueAsBoolean("DS_AllowDifferentCurrency");
					int bankAccountCurrency_ID = account.getC_Currency_ID();
					int paymentCurrency_ID = payment.getC_Currency_ID();
					if(bankAccountCurrency_ID!=paymentCurrency_ID & !allowDifferentCurrency)
						throw new AdempiereException("Payment Currency has to be same as bank account currency.");
				}
				payment.set_ValueNoCheck("BankAccountType", payment.getC_BankAccount().getBankAccountType());
			}
			if((event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW) || (event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE) && payment.is_ValueChanged(MPayment.COLUMNNAME_PayAmt))) && !payment.isSelfService() && !payment.isReceipt())
			{
				if(payment.getC_BankAccount().getBankAccountType().equalsIgnoreCase(MBankAccount.BANKACCOUNTTYPE_Cash))
				{
					BigDecimal payAmt = (BigDecimal)payment.get_Value("DS_TotalCashBillAmt");
					List<MDSDenomiationDetTrans> transactions = new Query(ctx, MDSDenomiationDetTrans.Table_Name, " C_Payment_ID = ? ", trxName).setParameters(payment.getC_Payment_ID()).list();
					for(MDSDenomiationDetTrans trans : transactions)
						trans.delete(true);
					
					BigDecimal remainingAmt = payAmt.compareTo(Env.ZERO)<0 ? payAmt.negate():payAmt;
					boolean isNegative = payAmt.compareTo(Env.ZERO)<0 ? true : false;
					List<MDSDenomiationDetCashBook> cashDenominations = new Query(ctx, MDSDenomiationDetCashBook.Table_Name, 
							" C_BankAccount_ID = ? AND Qty<>0 AND DS_DenominationType::numeric<=? ", trxName).
							setParameters(payment.getC_BankAccount_ID(),remainingAmt).
							setOrderBy("DS_DenominationType::numeric desc").
							list();
					for(MDSDenomiationDetCashBook denomination : cashDenominations)
					{
						BigDecimal denominationType = new BigDecimal(denomination.getDS_DenominationType());
						if(denominationType.compareTo(remainingAmt)<=0 || remainingAmt.compareTo(payAmt)==0)
						{
							BigDecimal requiredDenominations = remainingAmt.divideToIntegralValue(denominationType);
							remainingAmt = remainingAmt.subtract(requiredDenominations.multiply(new BigDecimal(denomination.getDS_DenominationType())));
							MDSDenomiationDetTrans transDenomiations = new MDSDenomiationDetTrans(ctx, 0, trxName);
							transDenomiations.setC_Payment_ID(payment.getC_Payment_ID());
							if(isNegative)
								transDenomiations.setQty(requiredDenominations.multiply(new BigDecimal(-1)));
							else
								transDenomiations.setQty(requiredDenominations);
							transDenomiations.setIsManual(false);
							transDenomiations.setDS_DenominationType(denomination.getDS_DenominationType());
							transDenomiations.saveEx();
							if(remainingAmt.compareTo(Env.ZERO)==0)
								break;
						}
					}
				}
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_BEFORE_COMPLETE))
			{
				if(!payment.isReceipt() && payment.getC_Invoice_ID()>0)
				{
					MInvoice invoice = (MInvoice)payment.getC_Invoice();
					if(invoice.isPaid())
						throw new AdempiereException("Invoice is already paid: "+invoice.getDocumentNo());
				}
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_COMPLETE))
			{
				if(payment.getC_BankAccount().getBankAccountType().equalsIgnoreCase(MBankAccount.BANKACCOUNTTYPE_Cash) && !payment.isSelfService())
				{
					boolean isSoTrx = payment.isReceipt();
					List<MDSDenomiationDetTrans> transactions = new Query(ctx, MDSDenomiationDetTrans.Table_Name, " C_Payment_ID = ? ", trxName).setParameters(payment.getC_Payment_ID()).list();
					for(MDSDenomiationDetTrans trans : transactions)
					{
						int cashbookDenomination_ID = DB.getSQLValue(trxName, "Select DS_DenomiationDet_CashBook_ID From DS_DenomiationDet_CashBook where DS_DenominationType=? and C_BankAccount_ID = ? ",
								trans.getDS_DenominationType(),payment.getC_BankAccount_ID());
						MDSDenomiationDetCashBook book = new MDSDenomiationDetCashBook(ctx, cashbookDenomination_ID, trxName);
						if(isSoTrx)
							book.setQty(trans.getQty().add(book.getQty()));
						else
							book.setQty(book.getQty().subtract(trans.getQty()));
						book.saveEx();
					}
				}
				if(payment.getC_Charge_ID()!=0)
				{
					MCharge charge=new MCharge(Env.getCtx(), payment.getC_Charge_ID(), po.get_TrxName());
					boolean isLockRecord = charge.get_ValueAsBoolean("DS_AutoLockRecord");
					if(isLockRecord)
						lockParentRecord(Env.getAD_User_ID(ctx), MPayment.Table_ID,payment.getC_Payment_ID());
				}
			}
		}
		if(po instanceof MDSDenomiationDetTrans)
		{
			MDSDenomiationDetTrans trans = (MDSDenomiationDetTrans)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW) || event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE))
			{
				if(trans.isManual())
				{
					BigDecimal qty = DB.getSQLValueBD(trxName, "Select coalesce(Qty,0) From DS_DenomiationDet_CashBook Where DS_DenominationType like ? and C_BankAccount_ID = ? ",
							trans.getDS_DenominationType(),trans.getC_Payment().getC_BankAccount_ID());
					if(qty.compareTo(trans.getQty())<0)
						throw new AdempiereException(Msg.getMsg(ctx, "DS_WrongDenomination"));
				}
			}
		}
		if(po instanceof MDSSettledInvoices)
		{
			MDSSettledInvoices inv = (MDSSettledInvoices)po;
			MPayment payment = new MPayment(ctx, inv.getC_Payment_ID(), trxName);
			BigDecimal totalCashPaidByEmp = (BigDecimal) payment.get_Value("DS_TotalCashBillAmt");
			BigDecimal totalAmt = DB.getSQLValueBD(trxName, "Select coalesce(sum(pay.PayAmt),0) "
					+ "From C_Payment pay,DS_Settled_Invoices dsi "
					+ "where pay.C_Payment_ID = dsi.Ref_Payment_ID and dsi.C_Payment_ID = ? ", inv.getC_Payment_ID());
			if(totalAmt.compareTo(totalCashPaidByEmp)>0)
				throw new AdempiereException("Payment amout is less than liquividating invoices total");
		}
		if(po instanceof MBankStatement)
		{
			MBankStatement statement = (MBankStatement)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW) || 
					(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE) && statement.is_ValueChanged(MBankStatement.COLUMNNAME_C_BankAccount_ID)))
			{
				List<MDSDenominationOnStatement> denominationsAtBankStatement = new Query(ctx, MDSDenominationOnStatement.Table_Name, " C_BankStatement_ID = ? ", trxName).setParameters(statement.getC_BankStatement_ID()).list();
				for(MDSDenominationOnStatement denomination : denominationsAtBankStatement)
				{
					denomination.delete(true);
				}
				List<MDSDenomiationDetCashBook> lastDenominations = new Query(ctx, MDSDenomiationDetCashBook.Table_Name, " C_BankAccount_ID = ? ", trxName).setParameters(statement.getC_BankAccount_ID()).list();
				for(MDSDenomiationDetCashBook lastDenomination : lastDenominations)
				{
					MDSDenominationOnStatement ds = new MDSDenominationOnStatement(ctx, 0, trxName);
					ds.setC_BankAccount_ID(statement.getC_BankAccount_ID());
					ds.setC_BankStatement_ID(statement.getC_BankStatement_ID());
					ds.setQty(lastDenomination.getQty());
					ds.setDS_DenominationType(lastDenomination.getDS_DenominationType());
					ds.setIsActive(lastDenomination.isActive());
					ds.setIsManual(lastDenomination.isManual());
					ds.saveEx();
				}
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_COMPLETE))
			{
				List<MDSDenominationOnStatement> denominationsAtBankStatement = new Query(ctx, MDSDenominationOnStatement.Table_Name, " C_BankStatement_ID = ? ", trxName).setParameters(statement.getC_BankStatement_ID()).list();
				for(MDSDenominationOnStatement denomination : denominationsAtBankStatement)
				{
					int DS_DenomiationDet_CashBook_ID = DB.getSQLValue(trxName, "SELECT DS_DenomiationDet_CashBook_ID From DS_DenomiationDet_CashBook "
							+ "Where C_BankAccount_ID = ? and DS_DenominationType = ? ",statement.getC_BankAccount_ID(),denomination.getDS_DenominationType());
					if(DS_DenomiationDet_CashBook_ID>0)
					{
						MDSDenomiationDetCashBook cashbookDenomination = new MDSDenomiationDetCashBook(ctx, DS_DenomiationDet_CashBook_ID, trxName);
						cashbookDenomination.setQty(denomination.getQty());
						cashbookDenomination.save();
					}
					else
						throw new AdempiereException("DenominationsNotPresent: "+denomination.getDS_DenominationType());
				}
			}
		}
		if(po instanceof MJournalLine)
		{
			MJournalLine line = (MJournalLine)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW) || 
					(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_CHANGE) && 
							(line.is_ValueChanged(MJournalLine.COLUMNNAME_AmtSourceCr) || 
									line.is_ValueChanged(MJournalLine.COLUMNNAME_AmtSourceDr)
									|| line.is_ValueChanged(MJournalLine.COLUMNNAME_C_Currency_ID)
									|| line.is_ValueChanged(MJournalLine.COLUMNNAME_DateAcct))))
			{
				BigDecimal debit = line.getCurrencyRate().multiply(line.getAmtSourceDr());
				BigDecimal credit = line.getCurrencyRate().multiply(line.getAmtSourceCr());
				
				int precision = line.getC_Currency().getStdPrecision();
				
				line.setAmtAcctDr(debit.setScale(precision, RoundingMode.HALF_UP));
				line.setAmtAcctCr(credit.setScale(precision, RoundingMode.HALF_UP));
			}
		}
		
		if(po instanceof MProduct)
		{
			MProduct product = (MProduct)po;
			if(product.is_ValueChanged(MProduct.COLUMNNAME_M_Product_Category_ID))
			{
				int count = DB.getSQLValue(trxName, "Select count(*) from Fact_Acct Where M_Product_ID = ? ",product.getM_Product_ID());
				if(count>0)
					throw new AdempiereException(Msg.getMsg(ctx, "DS_SaveCategoryError"));
			}
		}
		
		if(po instanceof MDSIExportPaymentsLine)
		{
			MDSIExportPaymentsLine line = (MDSIExportPaymentsLine)po;
			if(line.getDSI_ExportPayments().getDS_PaymentType().equalsIgnoreCase(MDSIExportPayments.DS_PAYMENTTYPE_BatchPayment))
			{
				if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW) || 
						(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_CHANGE) && 
								(line.is_ValueChanged(MDSIExportPaymentsLine.COLUMNNAME_C_Payment_ID))))
				{
					int existingBPartner_ID = DB.getSQLValue(trxName, "Select distinct cp.C_BPartner_ID "
							+ "from DSI_ExportPaymentsLine l,C_Payment cp "
							+ "Where l.C_Payment_ID=cp.C_Payment_ID "
							+ "and l.DSI_ExportPayments_ID = ? ",line.getDSI_ExportPayments_ID());
					if(existingBPartner_ID>0)
					{
						int C_BPartner_ID = line.getC_Payment().getC_BPartner_ID();
						if(existingBPartner_ID!=C_BPartner_ID)
							throw new AdempiereException("Batch can have only bpartner payments.");
					}
				}
			}
		}
		if(po instanceof MDSIExportPayments)
		{
			Timestamp time = Env.getContextAsDate(Env.getCtx(), "#Date");
			MDSIExportPayments header = (MDSIExportPayments)po;
			header.setTransfertTime(time);
		}
		
		if(po instanceof MOrder)
		{
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_PREPARE))
			{
				MOrder order = (MOrder)po;
				if (order.getGrandTotal().signum() != 0
						&& (MOrder.PAYMENTRULE_OnCredit.equals(order.getPaymentRule()) || MOrder.PAYMENTRULE_DirectDebit.equals(order.getPaymentRule())) && !order.isSOTrx())
				{
					int C_PaymentTerm_ID = order.getC_PaymentTerm_ID();
					MPaymentTerm term = new MPaymentTerm(ctx, C_PaymentTerm_ID, trxName);
					if(term.isAfterDelivery())
					{
						int C_OrderPaySchedule_ID = DB.getSQLValue(trxName, "Select C_OrderPaySchedule_ID From C_OrderPaySchedule "
								+ "Where C_Order_ID = ? and DueDate < ? Order by dueDate desc",order.getC_Order_ID(),order.getDatePromised());
						if(C_OrderPaySchedule_ID>0)
						{
							MOrderPaySchedule payscheduleInOrder = new MOrderPaySchedule(ctx,C_OrderPaySchedule_ID,trxName);
							payscheduleInOrder.setDueDate(order.getDatePromised());
							payscheduleInOrder.saveEx();
						}
					}
				}
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_BEFORE_PREPARE))
			{
				MOrder order = (MOrder)po;
				if (order.getGrandTotal().signum() != 0 && order.isSOTrx())
				{
					int C_PaymentTerm_ID = order.getC_PaymentTerm_ID();
					MPaymentTerm term = new MPaymentTerm(ctx, C_PaymentTerm_ID, trxName);
					MPaySchedule[] schedule = term.getSchedule(true);
					int C_OrderPaySchedule_ID = DB.getSQLValue(trxName, "Select C_OrderPaySchedule_ID From C_OrderPaySchedule "
								+ "Where C_Order_ID = ? and DueDate < ? Order by dueDate desc",order.getC_Order_ID(),order.getDatePromised());
					if(C_OrderPaySchedule_ID>0)
					{
						MOrderPaySchedule payscheduleInOrder = new MOrderPaySchedule(ctx,C_OrderPaySchedule_ID,trxName);
						payscheduleInOrder.setDueDate(order.getDatePromised());
						if(schedule.length==1 && payscheduleInOrder.getDueAmt().compareTo(order.getGrandTotal())!=0)
							payscheduleInOrder.setDueAmt(order.getGrandTotal());
						payscheduleInOrder.saveEx();
					}
				}
			}
		}
	}
	
	private void lockParentRecord(int AD_User_ID, int AD_Table_ID, int ID) 
	{
		MPrivateAccess access = MPrivateAccess.get (ctx, AD_User_ID,AD_Table_ID, ID);
		if (access == null)
			access = new MPrivateAccess (ctx, AD_User_ID,AD_Table_ID, ID);
		access.setIsActive(true);
		access.saveEx();
		
	}
	private void validateIfCostCenterAndRevenueCodesExist(PO po) 
	{
		int C_Charge_ID = po.get_ValueAsInt("C_Charge_ID");
		MAcctSchema[] mass = MAcctSchema.getClientAcctSchema(ctx, Env.getAD_Client_ID(Env.getCtx()));
		MClientInfo info = client.getInfo();
		int chargeFreightID = info.getC_ChargeFreight_ID();
		for(MAcctSchema mas : mass)
		{
			MAccount account = MCharge.getAccount(C_Charge_ID, mas)	;
			MElementValue value = new MElementValue(ctx, account.getAccount_ID(), po.get_TrxName());
			if(value.get_ValueAsBoolean("DS_CCRCRequired") && C_Charge_ID!=chargeFreightID)
			{
				int C_Activity_ID = po.get_ValueAsInt("C_Activity_ID");
				int User1_ID = po.get_ValueAsInt("User1_ID");
				int User2_ID = po.get_ValueAsInt("User2_ID");
				if(C_Activity_ID == 0 || User1_ID == 0 || User2_ID == 0)
					throw new AdempiereException(Msg.getMsg(ctx, "DS_CostCenter_RevenueCode_ProfitCenter_Mandatory"));
			}
			if(po.get_Table_ID()==MInvoiceLine.Table_ID && value.get_ValueAsBoolean("DS_IsInvestmentRelated") && C_Charge_ID!=chargeFreightID)
			{
				int relatedProduct_ID = po.get_ValueAsInt("RelatedProduct_ID");
				if(relatedProduct_ID<=0)
					throw new AdempiereException("Related product is mandatory");
			}
		}
	}

	@Override
	protected void initialize() 
	{
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_C_InvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_C_InvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_InvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_DELETE, I_C_InvoiceLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_InvoiceLine.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_M_InOut.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_M_InOut.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_C_Invoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_C_Invoice.Table_Name);
		
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrder.Table_Name);
		
		registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MOrder.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_C_Cash.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_C_Cash.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_M_Movement.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_M_Movement.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_M_Inventory.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_M_Inventory.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrderLine.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_M_InventoryLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_M_InventoryLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_GL_JournalLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_GL_JournalLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_C_CashLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_C_CashLine.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_DS_Product_Request.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_DS_Product_Request.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_C_Payment.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_C_Payment.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_Payment.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_Payment.Table_Name);
				
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MPayment.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MPayment.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MDSDenomiationDetCashBook.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MDSDenomiationDetTrans.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MDSDenomiationDetTrans.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MDSSettledInvoices.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MDSSettledInvoices.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MBankStatement.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MBankStatement.Table_Name);
		
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MBankStatement.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MJournalLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MJournalLine.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MProduct.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MDSIExportPaymentsLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MDSIExportPaymentsLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MDSIExportPayments.Table_Name);

	}

}
