package com.gaurav.dsi.purchase.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MRequest;
import org.compiere.model.MRequestType;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.I_DS_Product_Request;
import org.gaurav.dsi.model.MDSProductRequest;

public class CreateVendorInvoiceFromPurchaseRequest extends SvrProcess{

	int R_Request_ID = 0; 
	MRequest request = null ; 
	MRequestType type = null;
	Timestamp invoiceDate = null ; 

	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DateInvoiced"))
				invoiceDate = (Timestamp)para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		  }
		R_Request_ID = getRecord_ID();
		request = new MRequest(getCtx(), R_Request_ID, get_TrxName());
		type = new MRequestType(getCtx(), request.getR_RequestType_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		
		if(request.getC_BPartner_ID()==0)
			throw new AdempiereException(Msg.getMsg(getCtx(), "DS_BPartnerMandatory"));
		
		int count = DB.getSQLValue(get_TrxName(), "Select count(*) From DS_Product_Request Where R_Request_ID = ? and M_Product_ID Is not null ",R_Request_ID);
		if(count>0)
			throw new AdempiereException("Cant create invoice product exist in the purchase request");

		String sql = "Select C_Currency_ID,(case when PaymentRule is null then 'CS' else PaymentRule end) PaymentRule "
				+ "From DS_Product_Request "
				+ "Where R_Request_ID = ? "
				+ "group by C_Currency_ID,(case when PaymentRule is null then 'CS' else PaymentRule end)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, R_Request_ID);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				int C_Currency_ID = rs.getInt("C_Currency_ID"); 
				String paymentMode = rs.getString(2);
				String paymentRule = "P";
				if(paymentMode.equalsIgnoreCase("CS"))
					paymentRule = "X";
				if(paymentMode.equalsIgnoreCase("CC"))
					paymentRule = "P";
				
				MBPartner bpartner = new MBPartner(getCtx(), request.getC_BPartner_ID(), get_TrxName());
				int M_PriceList_ID = DB.getSQLValue(get_TrxName(), "Select M_PriceList_ID from M_PriceList "
						+ "Where C_Currency_ID = ? and IsSoPriceList='N' "
						+ "and AD_Client_ID=? Order By IsDefault desc ",C_Currency_ID,getAD_Client_ID());
				MInvoice invoice = new MInvoice(getCtx(), 0, get_TrxName());
				invoice.setIsSOTrx(false);
				invoice.setBPartner(bpartner);
				invoice.setDateAcct(invoiceDate);
				invoice.setDateInvoiced(invoiceDate);
				invoice.setC_DocTypeTarget_ID(type.get_ValueAsInt("C_DocType_ID"));
				invoice.setC_BPartner_Location_ID(request.get_ValueAsInt("C_BPartner_Location_ID"));
				invoice.setSalesRep_ID(request.getSalesRep_ID());
				invoice.setDescription(request.getSummary());
				invoice.setPaymentRule(paymentRule);
				if(bpartner.getPO_PriceList_ID()<=0)
					invoice.setM_PriceList_ID(M_PriceList_ID);
				invoice.saveEx();
				
				List<MDSProductRequest> products = new Query(getCtx(), I_DS_Product_Request.Table_Name, 
						" R_Request_ID = ? and C_Currency_ID = ? and PaymentRule = ?  ", get_TrxName()).
						setParameters(R_Request_ID,C_Currency_ID,paymentMode).
						list();
				for(MDSProductRequest product : products )
				{
					int C_Charge_ID = 0 ;
					BigDecimal priceEntered = product.getDS_ActualAmt();
					if(priceEntered.compareTo(Env.ZERO)!=0)
					{
						if(priceEntered.compareTo(Env.ONE)==0)
							priceEntered = product.getDS_Budget();
						
						MInvoiceLine line = new MInvoiceLine(invoice);
						line.setC_Invoice_ID(invoice.getC_Invoice_ID());
						line.setQtyEntered(product.getQtyRequired());
						line.setQtyInvoiced(product.getQtyRequired());
						line.set_ValueOfColumn("R_Request_ID", R_Request_ID);
						line.set_ValueOfColumn("DS_Product_Request_ID", product.getDS_Product_Request_ID());
						line.setDescription(product.getProductDescription());
						line.setUser1_ID(product.getUser1_ID());
						line.setUser2_ID(product.getUser2_ID());
						line.setC_Project_ID(product.getC_Project_ID());
						line.setC_Activity_ID(product.getC_Activity_ID());
						line.setC_ProjectPhase_ID(product.getC_ProjectPhase_ID());
						line.setC_ProjectTask_ID(product.getC_ProjectTask_ID());
						
						if(product.get_ValueAsInt("RelatedProduct_ID")>0)
							line.set_ValueNoCheck("RelatedProduct_ID", product.get_Value("RelatedProduct_ID"));
						
						if(product.getC_Charge_ID()!=0)
						{
							C_Charge_ID = product.getC_Charge_ID();
							line.setC_Charge_ID(C_Charge_ID);
						}
						line.setPriceEntered(priceEntered);
						line.setPriceActual(priceEntered);
						if(bpartner.isEmployee() || bpartner.getTaxID()==null)
						{
							int C_Tax_ID = DB.getSQLValue(get_TrxName(), "Select C_Tax_ID From C_Tax "
									+ "Where AD_Client_ID = ? AND IsDefault='Y' AND SOPOTYPE='P' ",getAD_Client_ID());
							line.setC_Tax_ID(C_Tax_ID);
						}
						line.saveEx();	
					
						line.setLineTotalAmt(line.getLineNetAmt().add(line.getTaxAmt()));
						line.saveEx();
						
						product.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
						product.saveEx();
					}
				}
				if(request.getRecord_ID()==0)
				{
					request.setRecord_ID(invoice.getC_Invoice_ID());
					request.setAD_Table_ID(invoice.get_Table_ID());
				}
				request.setC_Invoice_ID(invoice.getC_Invoice_ID());
				request.doClose();
				request.setResult("Closed by "+Env.getAD_User_ID(getCtx())+" Process generate vendor invoice from purchase request, Invoice Number: "+invoice.getDocumentNo());
				request.save();
				
				addLog(Msg.getMsg(getCtx(), "DS_AddAmtInInvoiceFromPurchaseRequest"));
				
				addLog(invoice.getC_Invoice_ID(), null, invoice.getTotalLines(), 
						invoice.getDocumentNo().concat(" : ").concat(invoice.getC_Currency().getISO_Code()), MInvoice.Table_ID, invoice.getC_Invoice_ID());
			}
		}
		catch(Exception e)
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return "@OK@";
	}


}
