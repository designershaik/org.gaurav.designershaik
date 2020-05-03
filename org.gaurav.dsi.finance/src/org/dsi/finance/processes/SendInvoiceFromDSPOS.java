package org.dsi.finance.processes;

import java.io.File;

import org.adempiere.exceptions.FillMandatoryException;
import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.MDunningRunEntry;
import org.compiere.model.MMailText;
import org.compiere.model.MQuery;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.model.PrintInfo;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.EMail;
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
		
		String textMsg = "";
		int defaultMailTemplate = 0;
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		MUser from=new MUser(Env.getCtx(), AD_User_ID, get_TrxName());
		defaultMailTemplate=Integer.parseInt(MSysConfig.getValue("DEFAULT_CUSTOMERINVOICE_EMAILTEMPLATE"));
		String defaultEmailID=MSysConfig.getValue("DEFAULT_ACCOUNT_EMAIL_ID","accounts@shaik.net");
		sendTo = header.getEMail();
		MPrintFormat format = null;
		int AD_PrintFormat_ID = DB.getSQLValue(get_TrxName(), "SELECT AD_PrintFormat_ID FROM AD_PrintFormat "
				+ "WHERE AD_Table_ID IN (SELECT AD_Table_ID From AD_Table Where TableName like 'DS_POSHeader') and JasperProcess_ID is not null ");
		
		
		format = MPrintFormat.get (getCtx(),AD_PrintFormat_ID, false);
		MQuery query = new MQuery("DS_POSHeader");
		query.addRestriction("DS_POSHeader_ID", MQuery.EQUAL, 
			new Integer(getRecord_ID()));

		//	Engine
		PrintInfo info = new PrintInfo(header.getBPName(),MDunningRunEntry.Table_ID,getRecord_ID());
		StringBuilder msginfo = new StringBuilder().append(header.getBPName()).append(", Amt=").append("");
		info.setDescription(msginfo.toString());
		ReportEngine re = null;
		if (format != null)
			re = new ReportEngine(getCtx(), format, query, info);
		
		MMailText text = new MMailText (Env.getCtx(), defaultMailTemplate, null);
		text.setPO(header, true);
		String subject = text.getMailHeader();
		String message = "";
		File pdf = re.getPDF(File.createTempFile(header.getBPName(), ".pdf"));
		if(pdf.length()>3000)
		{
			MClient client = MClient.get(getCtx(),getAD_Client_ID());
			EMail email =client.createEMail(from, sendTo, subject,message,true);
			email.setFrom(defaultEmailID);
			if (text.isHtml())
			{
				email.setMessageHTML(text.getMailHeader(), message);
				email.setSubject(email.getSubject().concat(subject));
			}
			else
			{
				email.setSubject (text.getMailHeader());
				email.setMessageText (message);
			}
			
			email.addAttachment(pdf);
			String ifSent = email.send();
			log.info("pdf name: "+pdf.getName()+" Size of the file: "+pdf.length());
			log.info("\n");
			log.info(ifSent);
			
			
			MAttachment ma= new MAttachment(Env.getCtx(), 0, get_TrxName());
			ma.setRecord_ID(getRecord_ID());
			ma.addEntry(pdf);
			ma.set_TrxName(get_TrxName());
			ma.setAD_Table_ID(MDSPOSHeader.Table_ID);
			ma.setTextMsg(textMsg);
			ma.save();
		}
		
		return null;
	}

	

}
