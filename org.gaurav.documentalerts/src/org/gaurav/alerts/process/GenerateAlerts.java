package org.gaurav.alerts.process;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;

import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MClient;
import org.compiere.model.MImage;
import org.compiere.model.MMailText;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.gaurav.alerts.model.MGSDocumentForAlerts;
import org.gaurav.alerts.model.MGSDocumentsRecipients;
import org.gaurav.payroll.model.MGSHRDocumentBaseType;

public class GenerateAlerts extends SvrProcess{

	@Override
	protected void prepare() {
		
		
	}

	@Override
	protected String doIt() throws Exception {
	
		String sql = "select det.GS_DocumentForAlerts_ID,base.GS_HR_DocumentBaseType_ID " + 
				"from GS_DocumentForAlerts det,HR_Documents base,GS_DocReminder_Schedule sch " + 
				"where det.GS_DocumentForAlerts_ID=sch.GS_DocumentForAlerts_ID " + 
				"and det.HR_Documents_ID = base.HR_Documents_ID "+ 
				"and sch.Date1 = now()+1 ";
		PreparedStatement pstmt = null;
		ResultSet rs = 	null;
		pstmt = DB.prepareStatement(sql, get_TrxName());
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			byte[] im = null;
			File attachmentFile = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			String employeeName = "";
			MImage image = null;
			int GS_DocumentForAlerts_ID = rs.getInt("GS_DocumentForAlerts_ID");
			int baseType_ID = rs.getInt("GS_HR_DocumentBaseType_ID");
			MGSHRDocumentBaseType baseType = new MGSHRDocumentBaseType(getCtx(), baseType_ID,get_TrxName());
			MGSDocumentForAlerts alert = new MGSDocumentForAlerts(getCtx(), GS_DocumentForAlerts_ID, get_TrxName());
			String documentName = alert.getHR_Documents().getName();
			String docNumber = alert.getHR_DocNumber();
			String expiryDate = format.format(alert.getHR_Doc_ExpiryDate());
			String dateIssued = format.format(alert.getHR_Doc_IssueDate());
			String description = alert.getDescription()==null ? "":alert.getDescription();
			if(alert.getGS_HR_Employee_ID()>0)
			{
				employeeName = alert.getGS_HR_Employee().getName();
			}
			if(alert.getAD_Image_ID()>0)
			{
				image = new MImage(getCtx(), alert.getAD_Image_ID(), get_TrxName());
				im = image.getBinaryData();
			}

			MAttachment attachments = alert.getAttachment();
			if(attachments!=null)
			{
				MAttachmentEntry entry = attachments.getEntry(0);
				attachmentFile = entry.getFile();
			}
			MClient client = MClient.get(getCtx(), getAD_Client_ID());
			MMailText text = new MMailText (Env.getCtx(), baseType.getR_MailText_ID(), get_TrxName());
			text.setPO(alert, true);
			String message = text.getMailText(true);
			String subject = text.getMailHeader();
			if(!employeeName.isEmpty())
			{
				subject = subject.concat("Employee: "+employeeName);
			}
			StringBuilder docDetails = new StringBuilder("");
			docDetails.append("<p> Document: &nbsp;"+documentName+"</p>");
			docDetails.append("<p>Document No: &nbsp;"+docNumber+"</p>");
			docDetails.append("<p>Document Note: &nbsp;"+description+"</p>");
			docDetails.append("<p>Date Issued: &nbsp;"+dateIssued+"</p>");
			docDetails.append("<p>Date Expiry: &nbsp;"+expiryDate+"</p>");
			message = message.concat(docDetails.toString());
			subject = subject+": "+documentName;
			EMail email = client.createEMail("gaurav@shaik.net",subject,message,true);
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
			List<MGSDocumentsRecipients> recepients = new Query(getCtx(), MGSDocumentsRecipients.Table_Name," GS_DocumentForAlerts_ID=? ", get_TrxName())
															.setParameters(alert.getGS_DocumentForAlerts_ID())
															.setOnlyActiveRecords(true)
															.list();
			for(MGSDocumentsRecipients rec : recepients)
			{
				email.addTo(rec.getAD_User().getEMail());
			}
			if(alert.getAD_Image_ID()>0)
			{
				String imageURL = image.getImageURL();
				int lastIndex = imageURL.lastIndexOf(".");
				String extension = imageURL.substring(lastIndex+1,imageURL.length());
				if(lastIndex>0)
					email.addAttachment(im, "image/"+extension, "Doc");
			}
			if(attachmentFile!=null)
				email.addAttachment(attachmentFile);
			String ifSent = email.send();
			addLog(ifSent+": "+subject);
		}
		
		return "@Alerted@";
	}


}
