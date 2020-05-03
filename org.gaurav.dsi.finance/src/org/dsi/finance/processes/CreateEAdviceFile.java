package org.dsi.finance.processes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.compiere.model.MCurrency;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrder;
import org.compiere.model.MPayment;
import org.compiere.model.MUser;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CreateEAdviceFile 
{
	protected ArrayList<String> getDetailsPerPayment(int paymentID)
	{
		ArrayList<String> eAdviceDetails=new ArrayList<String>();
		String emailID="Invalid Email ID";
		MPayment mp= new MPayment(Env.getCtx(),paymentID,null);
		int orderID=mp.getC_Order_ID();
		int invoiceID=mp.getC_Invoice_ID();
		if(orderID!=0)
		{
			
			MOrder po=new MOrder(Env.getCtx(), orderID, null);
			po.getAD_User_ID();
			MCurrency cc=new MCurrency(Env.getCtx(),po.getC_Currency_ID(), null);
			cc.getISO_Code();
			if((Integer)po.getAD_User_ID()!=null||po.getAD_User_ID()!=0)
			{
				MUser mu=new MUser(Env.getCtx(), po.getAD_User_ID(), null);
				emailID=mu.getEMail();
				if(emailID==null)
				{
					emailID="Invalid Email ID";
				}
				eAdviceDetails.add(0, emailID);
				if(po.getPOReference()==null)
				{
					eAdviceDetails.add(1, po.getDocumentNo());
				}
				else
				{
				eAdviceDetails.add(1, po.getPOReference());
				}
			
				String poDate=new SimpleDateFormat("dd-MM-yyyy").format(po.getDateOrdered());
				eAdviceDetails.add(2, poDate);
			}
		}
		if(invoiceID!=0)
		{
			MInvoice mi=new MInvoice(Env.getCtx(), invoiceID, null);
			MCurrency cc=new MCurrency(Env.getCtx(),mi.getC_Currency_ID(), null);
			cc.getISO_Code();
			mi.getAD_User_ID();
			if((Integer)mi.getAD_User_ID()!=null || mi.getAD_User_ID()!=0)
			{
				MUser mu=new MUser(Env.getCtx(), mi.getAD_User_ID(), null);
				emailID=mu.getEMail();
				if(emailID==null)
				{
					emailID="Invalid Email ID";
				}
				eAdviceDetails.add(0, emailID);
				if(mi.getPOReference()==null)
				{
					eAdviceDetails.add(1, mi.getDocumentNo());
				}
				else
				{
					eAdviceDetails.add(1, mi.getPOReference());
				}
				String InvDate=new SimpleDateFormat("dd-MM-yyyy").format(mi.getDateAcct());
				eAdviceDetails.add(2, InvDate);
			}
		}
		if(invoiceID==0 && orderID==0)
		{
			String InvDate = "";
			int C_Invoice_ID = DB.getSQLValue(null, "select c_invoice_id from C_PaymentAllocate where c_payment_id= ? ",paymentID);
			if(C_Invoice_ID==-1)
				C_Invoice_ID = DB.getSQLValue(null, "select c_invoice_id from C_AllocationLine where c_payment_id= ? ",paymentID);
			if(C_Invoice_ID!=-1)
			{
				MInvoice mi=new MInvoice(Env.getCtx(), C_Invoice_ID, null);
				MCurrency cc=new MCurrency(Env.getCtx(),mi.getC_Currency_ID(), null);
				cc.getISO_Code();
				mi.getAD_User_ID();
				if((Integer)mi.getAD_User_ID()!=null || mi.getAD_User_ID()!=0)
				{
					MUser mu=new MUser(Env.getCtx(), mi.getAD_User_ID(), null);
					emailID=mu.getEMail();
					if(emailID==null)
					{
						emailID="Invalid Email ID";
					}
					eAdviceDetails.add(0, emailID);
					if(mi.getPOReference()==null)
					{
						eAdviceDetails.add(1, mi.getDocumentNo());
					}
					else
					{
						eAdviceDetails.add(1, mi.getPOReference());
					}
					
					InvDate=new SimpleDateFormat("dd-MM-yyyy").format(mi.getDateAcct());
					eAdviceDetails.add(2, InvDate);
				}
			}
			else
			{
				InvDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
				eAdviceDetails.add(0, emailID);
				eAdviceDetails.add(1, "NOPoRef");
				eAdviceDetails.add(2, InvDate);
			}
		}
		return eAdviceDetails;
	}
	
}
