package org.dsi.finance.processes;

import java.io.File;
import java.util.ArrayList;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.MAttachment;
import org.compiere.model.MInvoice;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSPOSHeader;

public class SendInvoiceFromDSPOS extends SvrProcess{

	MDSPOSHeader header = null;
	String sendTo = "";
	@Override
	protected void prepare() {
		
		header = new MDSPOSHeader(getCtx(), getRecord_ID(), get_TrxName());
		sendTo = header.getEMail();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(header.getEMail()==null)
			throw new FillMandatoryException(MDSPOSHeader.COLUMNNAME_EMail);
		
		File pdf = null;
		int C_Invoice_ID = header.get_ValueAsInt("C_Invoice_ID");
		if(C_Invoice_ID<=0)
			C_Invoice_ID = DB.getSQLValue(get_TrxName(), "Select C_Invoice_ID From C_Invoice Where C_Order_ID = ? and DocStatus not in ('RE','VO') ");
		
		if(C_Invoice_ID<=0)
			throw new FillMandatoryException("No Invoice Found. Please process the POS order first.");

		MInvoice ci = new MInvoice(getCtx(),C_Invoice_ID,get_TrxName());
		if(!(ci.getDocStatus().equalsIgnoreCase(MInvoice.DOCSTATUS_Completed) || 
				ci.getDocStatus().equalsIgnoreCase(MInvoice.DOCSTATUS_Drafted) ||
				ci.getDocStatus().equalsIgnoreCase(MInvoice.DOCSTATUS_InProgress)))
			throw new AdempiereException("Can't send. Invoice is voided or reversed");
		
		ArrayList<String> to = new ArrayList<>();
		to.add(header.getEMail());
		
		pdf = SendEmails.getPOSPDF(C_Invoice_ID,get_TrxName());
		
		SendEmails.createSendEmail(ci, to,pdf);
			
		MAttachment ma= new MAttachment(Env.getCtx(), 0, get_TrxName());
		ma.setRecord_ID(getRecord_ID());
		ma.addEntry(pdf);
		ma.set_TrxName(get_TrxName());
		ma.setAD_Table_ID(MDSPOSHeader.Table_ID);
		ma.setTextMsg(to.toString());
		ma.save();
		
		return null;
	}

	

}
