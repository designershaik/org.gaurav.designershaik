package org.dsi.crm.processes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MCountry;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocation;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRegion;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.dsi.finance.processes.SendEmails;
import org.gaurav.dsi.model.MDSPOSHeader;
import org.gaurav.dsi.model.MDSPOSItemDetails;
import java.io.File;

public class GenerateOrderFromDSPOS extends SvrProcess{

	int pos_ID = 0;
	MDSPOSHeader header = null;
	MInvoice invoice = null;
	int M_Warehouse_ID = 0 ;
	MInOut inout = null;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Warehouse_ID"))
				M_Warehouse_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		pos_ID = getRecord_ID();
		header = new MDSPOSHeader(getCtx(), pos_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
//		try
//		{
			int count = DB.getSQLValue(get_TrxName(), "Select count(*) from DS_POS_ItemDetails Where DS_POSHeader_ID =  ? and M_Product_ID is not null ",pos_ID);
			if(count==0)
				throw new AdempiereException(Msg.getMsg(getCtx(), "NoLines"));
			if(header.getC_BPartner_ID()==0)
				throw new AdempiereException(Msg.getMsg(getCtx(), "search.customer.notfound"));
			
			String description = header.getPhone2()==null ? "":"Phone: "+ header.getPhone2();
			String email = header.getEMail()==null ? "":header.getEMail();
			description = description.isEmpty() ? email:description.concat("\n").concat(email);
			
			String sqlWhere = " where AD_Client_ID = ? ";
			if(!Util.isEmpty(email, true))
				sqlWhere = sqlWhere.concat(" and trim(upper(email)) like '"+email.toUpperCase().trim()+"' ");
			String phone = header.getPhone2();
			if(!Util.isEmpty(phone, true))
				sqlWhere = sqlWhere.concat(" or trim(upper(phone2)) like '"+phone.toUpperCase().trim()+"' ");
			
			int C_BPartner_ID = DB.getSQLValue(get_TrxName(), "Select C_BPartner_ID From AD_User  "+sqlWhere,getAD_Client_ID());
			MBPartner bp = null;
			if(C_BPartner_ID>0)
			{
				bp = new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
				bp.setM_PriceList_ID(header.getC_BPartner().getM_PriceList_ID());
				bp.saveEx();
			}
			else
			{
				MCountry country = (MCountry) header.getC_BPartner_Location().getC_Location().getC_Country();
				MRegion region = (MRegion) header.getC_BPartner_Location().getC_Location().getC_Region();
				
				bp = new MBPartner(getCtx(), 0, get_TrxName());
				bp.setValue(header.getBPName());
				bp.setName(header.getBPName());
				bp.setName2(email);
				bp.setDescription(phone);
				bp.setM_PriceList_ID(header.getC_BPartner().getM_PriceList_ID());
				bp.saveEx();
				
				MBPartnerLocation bpLoc = new MBPartnerLocation(getCtx(), 0, get_TrxName());
				MLocation loc = new MLocation(country, region);
				loc.setAddress1(email);
				loc.setAddress2(phone);
				loc.saveEx();
				bpLoc.setC_Location_ID(loc.getC_Location_ID());
				bpLoc.setName(header.getBPName());
				bpLoc.setC_BPartner_ID(bp.getC_BPartner_ID());
				bpLoc.saveEx();
				
				MUser user = new MUser(bp);
				user.setPhone2(phone);
				user.setEMail(email);
				user.saveEx();
				
			}
			
			int C_SODocType_ID = DB.getSQLValue(get_TrxName(), "Select C_DocType_ID From C_DocType WHERE Name like 'POS Standard Order' and AD_Client_ID = ? ",getAD_Client_ID());
			MOrder order = new MOrder(getCtx(), 0, get_TrxName());
			order.setIsSOTrx(true);
			order.setC_DocType_ID(C_SODocType_ID);
			order.setC_DocTypeTarget_ID(C_SODocType_ID);
			order.setBPartner(bp);
			order.setClientOrg(header.getAD_Client_ID(), header.getAD_Org_ID());
			order.setC_OrderSource_ID(header.getC_OrderSource_ID());
			order.setSalesRep_ID(header.getSalesRep_ID());
			order.setM_Warehouse_ID(M_Warehouse_ID);
			order.setDocumentNo(header.getDocumentNo());
			order.setDescription(description);
			order.setDateAcct(header.getDate1());
			order.setPOReference(header.getDocumentNo());
			if(order.save())
			{
				int C_DocType_ID = MDocType.getDocType("ARI");
				invoice = new MInvoice(order, C_DocType_ID, order.getDateAcct());
				invoice.setDocumentNo(header.getDocumentNo());
				invoice.setPOReference(header.getDocumentNo());
				invoice.saveEx();
				
				inout = new MInOut(order, MDocType.getDocType("MMS"), order.getDateAcct());
				inout.saveEx();
			}
			else
				throw new AdempiereException("Order didn't save.");
			
			List<MDSPOSItemDetails> details = new Query(getCtx(), MDSPOSItemDetails.Table_Name, " DS_POSHeader_ID = ? AND M_Product_ID is not null ", get_TrxName())
														.setParameters(pos_ID)
														.list();
			for(MDSPOSItemDetails det: details)
			{
				MOrderLine line = new MOrderLine(order);
				line.setM_Product_ID(det.getM_Product_ID());
				line.setQty(det.getQtyOrdered());
				line.setPriceEntered(det.getPrice());
				line.setPriceActual(det.getPrice());
				line.setC_Tax_ID(det.get_ValueAsInt("C_Tax_ID"));
				line.setDiscount((BigDecimal)det.get_Value("Discount"));
				line.setPriceList((BigDecimal)det.get_Value("PriceList"));
				line.setLineNetAmt(det.getLineNetAmt());
				description +=line.getM_Product().getValue().concat("_").concat(line.getM_Product().getName()).concat(" Qty: ")+det.getQtyOrdered()+"\n";
				if(line.save())
				{
					MInvoiceLine invLine = new MInvoiceLine(invoice);
					invLine.setOrderLine(line);
					invLine.setQty(det.getQtyOrdered());
					invLine.setPriceList((BigDecimal)det.get_Value("PriceList"));
					invLine.saveEx();
					
					MInOutLine inoutLine = new MInOutLine(inout);
					inoutLine.setOrderLine(line, 0, det.getQtyOrdered());
					inoutLine.saveEx();
				}
						
				
			}
			header.set_ValueOfColumn("C_Invoice_ID", invoice.getC_Invoice_ID());
			header.set_ValueNoCheck("C_Order_ID", order.getC_Order_ID());
			header.setProcessed(true);
			header.saveEx();
			
//			description += header.getBPName()==null ? "":"Customer Name: "+header.getBPName();
			
			order.setDescription(description);
			order.saveEx();
			
			invoice.setDescription(description);
			invoice.saveEx();
//			
			if(order.processIt(MOrder.DOCACTION_Complete))
				order.saveEx();
			
			if(inout.processIt(MOrder.DOCACTION_Complete))
				inout.saveEx();
			
			addLog(pos_ID, null, null, order.getDocumentInfo(), MOrder.Table_ID, order.getC_Order_ID());
			addLog(pos_ID, null, null, invoice.getDocumentNo(), MInvoice.Table_ID, invoice.getC_Invoice_ID());
			addLog(pos_ID, null, null, inout.getDocumentInfo(), MInOut.Table_ID, inout.getM_InOut_ID());
			
			if(header.getSalesRep().getSupervisor_ID()>0)
			{
				ArrayList<String> to = new ArrayList<>();
				to.add(header.getSalesRep().getSupervisor().getEMail());
				
				File pdf = SendEmails.getPOSPDF(invoice.getC_Invoice_ID(),get_TrxName());
				
				SendEmails.createSendEmail(invoice, to,pdf);
			}
//		}
//		catch(Exception e)
//		{
//			throw new AdempiereException(e.getLocalizedMessage());
//		}
		
		
		return "@Generated@";
	}



}
