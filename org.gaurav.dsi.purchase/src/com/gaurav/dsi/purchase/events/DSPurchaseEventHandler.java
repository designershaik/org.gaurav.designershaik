package com.gaurav.dsi.purchase.events;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBankAccount;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MRequest;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.MDSProductRequest;
import org.osgi.service.event.Event;

public class DSPurchaseEventHandler extends AbstractEventHandler {
	String trxName = null;
	Properties ctx = Env.getCtx();
	@Override
	protected void doHandleEvent(Event event) 
	{
		PO po = getPO(event);
		trxName = po.get_TrxName();
		if(po instanceof MDSProductRequest)
		{
			MDSProductRequest products = (MDSProductRequest)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW) || event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_CHANGE))
			{
				if(products.getDS_Budget().compareTo(Env.ZERO)==0)
					throw new AdempiereException(Msg.getMsg(Env.getCtx(), "DS_ESTIMATEDAMTCANTBEZERO"));
			}
			
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW) || event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE))
			{
				int R_Request_ID = products.getR_Request_ID();
				MRequest request = new MRequest(Env.getCtx(), R_Request_ID, trxName);
				if(request.get_ValueAsBoolean("DSI_IsApproved") && !products.is_ValueChanged(MDSProductRequest.COLUMNNAME_C_InvoiceLine_ID))
				{
					request.set_ValueOfColumn("DSI_IsApproved", false);
					request.set_ValueOfColumn("DS_DateApproved", null);
					request.set_ValueOfColumn("DS_ApprovedBy_ID", null);
					request.setResult("Disapproved because of change in the products in the request");
					request.saveEx();
				}
				BigDecimal totalRequestAmt = DB.getSQLValueBD(trxName, "Select sum(DS_Budget*QtyRequired) From DS_Product_Request Where R_Request_ID = ? ", R_Request_ID);
				request.setRequestAmt(totalRequestAmt);
				request.saveEx();
			}
		}
		if(po instanceof MInvoice)
		{
			MInvoice invoice = (MInvoice)po;
			if(!invoice.isSOTrx())
			{
				if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW) || 
						(event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE)&& invoice.is_ValueChanged(MOrder.COLUMNNAME_POReference)))
				{
					if(invoice.getPOReference()!=null)
						verifyIfThePOReferenceAlreadyExists(invoice);
				}
			}
			if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE))
			{
				int C_BankAccount_ID = invoice.get_ValueAsInt("C_BankAccount_ID");
				if(C_BankAccount_ID>0)
				{
					MBankAccount account = new MBankAccount(ctx, C_BankAccount_ID, trxName);
					if(!account.get_ValueAsBoolean("DS_AllowDifferentCurrency") && invoice.getC_Currency_ID()!=account.getC_Currency_ID())
						throw new AdempiereException("Not allowed to pay in different currency with this bank account.");
					
					String docBaseType = invoice.isSOTrx() ? MDocType.DOCBASETYPE_ARReceipt:MDocType.DOCBASETYPE_APPayment;
					int C_DocType_ID = DB.getSQLValue(trxName, "Select C_DocType_ID From C_DocType Where AD_Client_ID = ? "
							+ "and DocBaseType = ? and BankAccountType = ? ",invoice.getAD_Client_ID(),docBaseType,account.getBankAccountType());
					
					MPayment payment = new MPayment(ctx, 0, trxName);
					payment.setAD_Org_ID(invoice.getAD_Org_ID());
					payment.setC_DocType_ID(C_DocType_ID);
					payment.setTenderType(MPayment.TENDERTYPE_CreditCard);
					payment.setC_BankAccount_ID(C_BankAccount_ID);
					payment.setC_BPartner_ID(invoice.getC_BPartner_ID());
					payment.setC_Invoice_ID(invoice.getC_Invoice_ID());
					payment.setC_Currency_ID(invoice.getC_Currency_ID());			
					payment.setDescription(invoice.getDescription());
					if (invoice.isCreditMemo())
						payment.setPayAmt(invoice.getGrandTotal().negate());
					else
						payment.setPayAmt(invoice.getGrandTotal());
					payment.setIsPrepayment(false);					
					payment.setDateAcct(invoice.getDateAcct());
					payment.setDateTrx(invoice.getDateInvoiced());

					//	Save payment
					payment.saveEx();
				}
			}
		}
	}
	
	

	private void verifyIfThePOReferenceAlreadyExists(MInvoice invoice) 
	{
		int count = DB.getSQLValue(trxName, "SELECT count(*) FROM C_Invoice ci WHERE trim(ci.POReference) like ? AND ci.AD_Client_ID = ? "
											+ "AND ci.C_BPartner_ID = ? AND ci.C_BPartner_Location_ID = ? AND ci.DocStatus in ('CO','DR','IP') "
											+ "and ci.C_Order_ID is null ",invoice.getPOReference(),invoice.getAD_Client_ID(),
											invoice.getC_BPartner_ID(),invoice.getC_BPartner_Location_ID());
		if(count>1)
			throw new AdempiereException("Invoice with same PO reference already exist: "+invoice.getPOReference());
	}

	@Override
	protected void initialize() {
			
		registerTableEvent(IEventTopics.PO_BEFORE_NEW,MDSProductRequest.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE,MDSProductRequest.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW,MDSProductRequest.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE,MDSProductRequest.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MInvoice.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MInvoice.Table_Name);
		
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MInvoice.Table_Name);		
	}

}
