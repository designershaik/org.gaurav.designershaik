package org.dsi.crm.processes;

import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.MDSPOSHeader;
import org.gaurav.dsi.model.MDSPOSItemDetails;

public class GenerateOrderFromDSPOS extends SvrProcess{

	int pos_ID = 0;
	MDSPOSHeader header = null;
	MInvoice invoice = null;
	int M_Warehouse_ID = 0 ;
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
		int count = DB.getSQLValue(get_TrxName(), "Select count(*) from DS_POS_ItemDetails Where DS_POSHeader_ID = ? and M_Product_ID is not null ",pos_ID);
		if(count==0)
			throw new AdempiereException(Msg.getMsg(getCtx(), "NoLines"));
		if(header.getC_BPartner_ID()==0)
			throw new AdempiereException(Msg.getMsg(getCtx(), "search.customer.notfound"));
		
		MBPartner bp = new MBPartner(getCtx(), header.getC_BPartner_ID(), get_TrxName());
		String description = "";
		MOrder order = new MOrder(getCtx(), 0, get_TrxName());
		order.setIsSOTrx(true);
		order.setC_DocType_ID(1000030);
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
		}
		List<MDSPOSItemDetails> details = new Query(getCtx(), MDSPOSItemDetails.Table_Name, " DS_POSHeader_ID = ? AND M_Product_ID is not null ", get_TrxName())
													.setParameters(pos_ID)
													.list();
		for(MDSPOSItemDetails det: details)
		{
			MOrderLine line = new MOrderLine(order);
			line.setM_Product_ID(det.getM_Product_ID());
			line.setQty(det.getQtyOrdered());
			line.setPrice();
			line.setTax();		
			description +=line.getM_Product().getValue().concat("_").concat(line.getM_Product().getName()).concat(" Qty: ")+det.getQtyOrdered()+"\n";
			if(line.save())
			{
				MInvoiceLine invLine = new MInvoiceLine(invoice);
				invLine.setOrderLine(line);
				invLine.setQty(det.getQtyOrdered());
				invLine.saveEx();
			}
					
			
		}
		header.set_ValueNoCheck("C_Order_ID", order.getC_Order_ID());
		header.setProcessed(true);
		header.saveEx();
		
		description += header.getBPName()==null ? "":"Customer Name: "+header.getBPName();
		description = description.isEmpty() ? (header.getPhone2()==null ? "":header.getPhone2()):description.concat(header.getPhone2()==null ? "":"\n"+header.getPhone2());
		description = description.isEmpty() ? (header.getEMail()==null ? "":header.getEMail()):description.concat("\n").concat(header.getEMail()==null ? "":"\n"+header.getEMail());
		order.setDescription(description);
		order.saveEx();
		
		invoice.setDescription(description);
		invoice.saveEx();
		
		addLog(pos_ID, null, null, order.getDocumentInfo(), MOrder.Table_ID, order.getC_Order_ID());
		addLog(pos_ID, null, null, invoice.getDocumentNo(), MInvoice.Table_ID, invoice.getC_Invoice_ID());
		
		return "@Generated@";
	}



}
