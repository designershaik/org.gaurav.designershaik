package org.dsi.finance.processes;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.exceptions.DBException;
import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.MInvoice;
import org.compiere.model.MMailText;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.process.DocAction;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ServerProcessCtl;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;

public class SendEmails {

	static CLogger log = CLogger.getCLogger(SendEmails.class);
	static StringBuilder logs = new StringBuilder("");
	public static String sendEmail(MInvoice ci, int billpartnerID, int billPartnerLocationID, String trxName) 
	{
		ArrayList<String> toList=getEmails(trxName,billpartnerID,billPartnerLocationID);
	    if(!toList.isEmpty())
	    {
	    		createSendEmail(ci,toList);
	    }
	   return logs.toString();
	}

	static void createSendEmail(MInvoice ci, ArrayList<String> to) 
	{
		String textMsg = "";
		int defaultMailTemplate = 0;
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		MUser from=new MUser(Env.getCtx(), AD_User_ID, ci.get_TrxName());
		defaultMailTemplate= MSysConfig.getIntValue("DEFAULT_CUSTOMERINVOICE_EMAILTEMPLATE", 1000003, ci.getAD_Client_ID()) ;
		String defaultEmailID=MSysConfig.getValue("DEFAULT_ACCOUNT_EMAIL_ID","accounts@shaik.net", ci.getAD_Client_ID());
		String sendTo=defaultEmailID;
		DocAction doc = (DocAction)ci;
		MMailText text = new MMailText (Env.getCtx(), defaultMailTemplate, null);
		text.setPO(ci, true);
		String subject = text.getMailHeader();
		String message = text.getMailText(true)
			+ "\n--------\n" + doc.getDocumentInfo()
			+ "\n" + doc.getSummary();
		File pdf = doc.createPDF();
		if(pdf.length()>3000)
		{
			MClient client = MClient.get(doc.getCtx(), doc.getAD_Client_ID());
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
			for(int i=0;i<to.size();i++)
			{
				String emailID=to.get(i);
				email.addTo(emailID);
				
				textMsg=textMsg.concat("-").concat(emailID);
			}
			email.addAttachment(pdf);
			String ifSent = email.send();
			log.info("pdf name: "+pdf.getName()+" Size of the file: "+pdf.length());
			log.info("\n");
			log.info(ifSent);
			
			logs.append("\n");
			logs.append(ifSent);
			logs.append("\n").append("Attached file name is: "+pdf.getName()+" Size of the file: "+pdf.length());
			attachPDF(pdf,ci,ci.getC_Invoice_ID(),textMsg);
		}
	}
	
	public static void createSendEmail(MInvoice ci, ArrayList<String> to,File pdf) 
	{
		String textMsg = "";
		int defaultMailTemplate = 0;
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		MUser from=new MUser(Env.getCtx(), AD_User_ID, ci.get_TrxName());
		defaultMailTemplate= MSysConfig.getIntValue("DEFAULT_CUSTOMERINVOICE_EMAILTEMPLATE", 1000003, ci.getAD_Client_ID()) ;
		String defaultEmailID=MSysConfig.getValue("DEFAULT_ACCOUNT_EMAIL_ID","accounts@shaik.net", ci.getAD_Client_ID());
		String sendTo=defaultEmailID;
		DocAction doc = (DocAction)ci;
		MMailText text = new MMailText (Env.getCtx(), defaultMailTemplate, null);
		text.setPO(ci, true);
		String subject = text.getMailHeader();
		String message = text.getMailText(true)
			+ "\n--------\n" + doc.getDocumentInfo()
			+ "\n" + doc.getSummary();
		if(pdf.length()>3000)
		{
			MClient client = MClient.get(doc.getCtx(), doc.getAD_Client_ID());
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
			for(int i=0;i<to.size();i++)
			{
				String emailID=to.get(i);
				email.addTo(emailID);
				
				textMsg=textMsg.concat("-").concat(emailID);
			}
			email.addAttachment(pdf);
			String ifSent = email.send();
			log.info("pdf name: "+pdf.getName()+" Size of the file: "+pdf.length());
			log.info("\n");
			log.info(ifSent);
			
			logs.append("\n");
			logs.append(ifSent);
			logs.append("\n").append("Attached file name is: "+pdf.getName()+" Size of the file: "+pdf.length());
		}
	}

	private static ArrayList<String> getEmails(String trxName, int billpartnerID, int billPartnerLocationID) 
	{
		logs.append("Adding email receipients.").append("\n");
		log.info("Adding email receipients: ".concat("\n"));
		ArrayList<String> to=new ArrayList<String>();
		String sql="SELECT dec.AD_User_ID "
				+ "FROM DS_EmailContacts dec "
				+ "WHERE dec.c_bpartner_id = ? "
				+ "AND dec.AD_USER_ID in (select adu.ad_user_id from ad_user adu where adu.c_bpartner_id=dec.c_bpartner_id "
				+ "AND adu.C_BPARTNER_LOCATION_ID = ? and adu.IsActive='Y' and adu.DS_IsInvoiceContact='Y') AND dec.IsActive='Y' "
				+ "union all "
				+ "select DISTINCT cs.SALESREP_ID "
				+ "from C_BPARTNER_LOCATION loc,C_SalesRegion cs "
				+ "where loc.C_SALESREGION_ID=cs.C_SALESREGION_ID "
				+ "and loc.C_BPARTNER_LOCATION_ID = ? ";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, billpartnerID);
			pstmt.setInt(2, billPartnerLocationID);
			pstmt.setInt(3, billPartnerLocationID);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				if(((Integer)rs.getInt("AD_User_ID")!=null))
				{
					int userID=rs.getInt("AD_User_ID");
					MUser mu=new MUser(Env.getCtx(), userID, trxName);
					if(userID!=0)
					{
						log.info(mu.getEMail().concat("\n"));
						logs.append(mu.getEMail()).append("\n");
						to.add(mu.getEMail());
					}
				}
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return to;
		
	}
	private static int attachPDF(File pdf, MInvoice ci, int invID, String textMsg)
	{
		int attachmentID;
		MAttachment ma= new MAttachment(Env.getCtx(), 0, ci.get_TrxName());
		ma.setRecord_ID(invID);
		ma.addEntry(pdf);
		ma.set_TrxName(ci.get_TrxName());
		ma.setAD_Table_ID(ci.get_Table_ID());
		ma.setTextMsg(textMsg);
		ma.save();
		attachmentID=ma.getAD_Attachment_ID();
		return attachmentID;
	}

	public static File getPOSPDF(int C_Invoice_ID, String trxName) 
	{
		File pdf = null;
		int adProcessID = MProcess.getProcess_ID("DSI_ARTaxInvoice_POS", trxName);
		if (adProcessID <= 0)
			throw new AdempiereUserError("Print Format is not configured on Dunning Level.");

		if (adProcessID > 0) {
			MPInstance instance = new MPInstance(Env.getCtx(), adProcessID, C_Invoice_ID);
			instance.saveEx();

			MPInstancePara parameter = new MPInstancePara(instance, 10);
			parameter.setParameter("AD_PInstance_ID", instance.get_ID());
			parameter.setParameter("C_Invoice_ID", C_Invoice_ID);
			parameter.saveEx();

			ProcessInfo pInfo = new ProcessInfo("Statement Attachment", adProcessID);
			pInfo.setPrintPreview(true);
			pInfo.setIsBatch(true);
			pInfo.setAD_PInstance_ID(instance.get_ID());

			ServerProcessCtl.process(pInfo, null);
			log.log(Level.WARNING, "getEntryAttachment, entryID = " + C_Invoice_ID);
			pdf = pInfo.getPDFReport();
		} else {
			throw new AdempiereException("No Print process configure for Entry : " + C_Invoice_ID);
		}
		return pdf;
	}

}
