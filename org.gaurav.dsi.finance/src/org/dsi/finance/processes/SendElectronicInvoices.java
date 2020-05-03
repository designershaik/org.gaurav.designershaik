package org.dsi.finance.processes;

import org.compiere.model.MInvoice;
import org.compiere.process.SvrProcess;

public class SendElectronicInvoices extends SvrProcess{

	MInvoice ci = null;
	@Override
	protected void prepare() {
		
		ci = new MInvoice(getCtx(),getRecord_ID(),get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		
		int billPartnerLocationID=ci.getC_BPartner_Location_ID();
		int billpartnerID=ci.getC_BPartner_ID();
		String output = SendEmails.sendEmail(ci,billpartnerID,billPartnerLocationID,get_TrxName());
		
		return output;
	}


}
