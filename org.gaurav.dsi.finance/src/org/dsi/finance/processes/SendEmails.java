package org.dsi.finance.processes;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.MInvoice;
import org.compiere.model.MMailText;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.process.DocAction;
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

	private static void createSendEmail(MInvoice ci, ArrayList<String> to) 
	{
		String textMsg = "";
		int defaultMailTemplate = 0;
		int AD_User_ID = Env.getContextAsInt(Env.getCtx(), "#AD_User_ID");
		MUser from=new MUser(Env.getCtx(), AD_User_ID, ci.get_TrxName());
		defaultMailTemplate=Integer.parseInt(MSysConfig.getValue("DEFAULT_CUSTOMERINVOICE_EMAILTEMPLATE"));
		String defaultEmailID=MSysConfig.getValue("DEFAULT_ACCOUNT_EMAIL_ID","accounts@shaik.net");
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

}
