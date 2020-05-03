/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.dsi.finance.processes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.util.Callback;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.SimplePDFViewer;
import org.compiere.model.I_C_DunningRunEntry;
import org.compiere.model.I_C_SalesRegion;
import org.compiere.model.MAttachment;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MClient;
import org.compiere.model.MDunningLevel;
import org.compiere.model.MDunningRun;
import org.compiere.model.MDunningRunEntry;
import org.compiere.model.MMailText;
import org.compiere.model.MQuery;
import org.compiere.model.MSalesRegion;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.ProcessInfo;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.gaurav.dsi.model.I_DS_DunningInclude_Emails;
import org.gaurav.dsi.model.I_DS_EmailContacts;
import org.gaurav.dsi.model.MDSDunningEntryEmails;
import org.gaurav.dsi.model.MDSDunningIncludeEmails;
import org.gaurav.dsi.model.MDSEmailContacts;

/**
 *	Dunning Letter Print
 *	
 *  @author Jorg Janke
 *  @version $Id: DunningPrint.java,v 1.2 2006/07/30 00:51:02 jjanke Exp $
 *  
 *  FR 2872010 - Dunning Run for a complete Dunning (not just level) - Developer: Carlos Ruiz - globalqss - Sponsor: Metas
 */
public class DSICustomDunningPrint extends SvrProcess
{
	/**	Mail PDF				*/
	private boolean		p_EMailPDF = false;
	/** Mail Template			*/
	private int			p_R_MailText_ID = 0;
	/** Dunning Run				*/
	private int			p_C_DunningRun_ID = 0;
	/** Print only Outstanding	*/
	private boolean		p_IsOnlyIfBPBalance = true;
	/** Print only unprocessed lines */
	private boolean p_PrintUnprocessedOnly = true;
	private MUser emailSender = null;
	protected boolean p_decision = true ;
	
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("EMailPDF"))
				p_EMailPDF = "Y".equals(para[i].getParameter());
			else if (name.equals("R_MailText_ID"))
				p_R_MailText_ID = para[i].getParameterAsInt();
			else if (name.equals("C_DunningRun_ID"))
				p_C_DunningRun_ID = para[i].getParameterAsInt();
			else if (name.equals("IsOnlyIfBPBalance"))
				p_IsOnlyIfBPBalance = "Y".equals(para[i].getParameter());
			else if (name.equals("PrintUnprocessedOnly"))
				p_PrintUnprocessedOnly = "Y".equals(para[i].getParameter());
			else if (name.equals("PrintUnprocessedOnly"))
				p_PrintUnprocessedOnly = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		emailSender = new MUser(getCtx(), Env.getAD_User_ID(getCtx()),get_TrxName());
	}	//	prepare

	/**
	 * Process
	 * @return info
	 * @throws Exception
	 */
	protected String doIt () throws Exception
	{
		if (log.isLoggable(Level.INFO)) log.info("C_DunningRun_ID=" + p_C_DunningRun_ID + ",R_MailText_ID=" + p_R_MailText_ID 
			+ ", EmailPDF=" + p_EMailPDF + ",IsOnlyIfBPBalance=" + p_IsOnlyIfBPBalance 
			+ ",PrintUnprocessedOnly=" + p_PrintUnprocessedOnly);
		
		//	Need to have Template
		
		//
		MDunningRun run = new MDunningRun (getCtx(), p_C_DunningRun_ID, get_TrxName());
		if (run.get_ID() == 0)
			throw new AdempiereUserError ("@NotFound@: @C_DunningRun_ID@ - " + p_C_DunningRun_ID);
		MClient client = MClient.get(getCtx());
		SimpleDateFormat DunningMonthFormat = new SimpleDateFormat("MMM-yyyy");
		String dunningYearMonth = DunningMonthFormat.format(run.getDunningDate());
		int count = 0;
		int errors = 0;
		MDunningRunEntry[] entries = run.getEntries(false);
		final List<File> pdfList = new ArrayList<File>();
		for (int i = 0; i < entries.length; i++)
		{
			File attachment = null ;
			String subject = "" ; 
			MDunningRunEntry entry = entries[i];
			if(!entry.isProcessed())
			{
				MBPartner bp = new MBPartner (getCtx(), entry.getC_BPartner_ID(), get_TrxName());
				int R_MailText_ID = p_R_MailText_ID;
				
				if(R_MailText_ID>0)
				{
					final StringBuffer answer = new StringBuffer("");
					this.processUI.ask("Are you sure you want to proceed with the selected mail template.", new Callback<Boolean>() 
					{
						public void onCallback(Boolean save) 
						{
							if(!save)
							{
								p_decision  = false;
							}
							answer.append("done");
						}	
					});
	
					while (answer.length() == 0) {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
					}
				}
				if(!p_decision)
					return "Nothing Processed";
				
				if(p_R_MailText_ID<=0)
					R_MailText_ID = bp.get_ValueAsInt("R_MailText_ID")==0 ? p_R_MailText_ID:bp.get_ValueAsInt("R_MailText_ID");
				
				if (p_EMailPDF && R_MailText_ID <= 0)
				{
					addLog("@NotFound@: @R_MailText_ID@ - " +entry.getC_BPartner().getName());
					errors++;
					continue;
				}
//				String subject = "";
				MMailText mText = null;
				
				mText = new MMailText(getCtx(), R_MailText_ID, get_TrxName());
				//	Print Format on Dunning Level
				MDunningLevel level = new MDunningLevel (getCtx(), entry.getC_DunningLevel_ID(), get_TrxName());
				MPrintFormat format = null;
				if (level.getDunning_PrintFormat_ID() > 0)
					format = MPrintFormat.get (getCtx(), level.getDunning_PrintFormat_ID(), false);
				
				if (p_IsOnlyIfBPBalance && entry.getAmt().signum() <= 0)
					continue;
				if (p_PrintUnprocessedOnly && entry.isProcessed())
					continue;
				//	To BPartner
				File outstandingBalanceInCSV = null;
				String typeOfAttachment = bp.get_ValueAsString("DS_AttachmentFormatForDunning");
				if(typeOfAttachment.equalsIgnoreCase("CSV"))
					outstandingBalanceInCSV = getOutStandingBalanceExcel(entry);
				if (bp.get_ID() == 0)
				{
					StringBuilder msglog = new StringBuilder("@NotFound@: @C_BPartner_ID@ ").append(entry.getC_BPartner_ID());
					addLog (entry.get_ID(), null, null,msglog.toString() );
					errors++;
					continue;
				}
				//	To User
				MUser to = new MUser (getCtx(), entry.getAD_User_ID(), get_TrxName());
				String toEmailIDs = "sherryl@shaik.net";
				if (p_EMailPDF)
				{
					if (to.get_ID() == 0)
					{
						StringBuilder msglog = new StringBuilder("@NotFound@: @AD_User_ID@ - ").append(bp.getName());
						addLog (entry.get_ID(), null, null,msglog.toString());
						errors++;
						continue;
					}
					else if (toEmailIDs == null || toEmailIDs.length() == 0)
					{
						StringBuilder msglog = new StringBuilder("@NotFound@: @EMail@ - ").append(to.getName());
						addLog (entry.get_ID(), null, null, msglog.toString());
						errors++;
						continue;
					}
				}			
					
				//	query
				MQuery query = new MQuery("C_Dunning_Header_v");
				query.addRestriction("C_DunningRunEntry_ID", MQuery.EQUAL, 
					new Integer(entry.getC_DunningRunEntry_ID()));
	
				//	Engine
				PrintInfo info = new PrintInfo(
					bp.getName(),
					MDunningRunEntry.Table_ID,
					entry.getC_DunningRunEntry_ID(),
					entry.getC_BPartner_ID());
				StringBuilder msginfo = new StringBuilder().append(bp.getName()).append(", Amt=").append(entry.getAmt());
				info.setDescription(msginfo.toString());
				ReportEngine re = null;
				if (format != null)
					re = new ReportEngine(getCtx(), format, query, info);
				boolean printed = false;
				if (p_EMailPDF)
				{
//					EMail email = client.createEMail(emailSender,toEmailIDs, null, null);
//					String fromEmail = MSysConfig.getValue("DS_Dunning_FromEmailID", "accounts@shaik.net");
					EMail email = client.createEMailFrom(emailSender.getEMail(), toEmailIDs, null, null, true);					
					List<MDSEmailContacts> contacts=new Query(getCtx(), I_DS_EmailContacts.Table_Name, 
								" DS_IsDunningEmail='Y' AND C_BPARTNER_ID = ? ", get_TrxName()).setParameters(entry.getC_BPartner_ID()).
								setOnlyActiveRecords(true).list();
						for(MDSEmailContacts contact : contacts)
						{
							MUser user = new MUser(getCtx(), contact.getAD_User_ID(), get_TrxName());
							if(user.getEMail()!=null)
								email.addTo(user.getEMail());
							MDSDunningEntryEmails emails = new MDSDunningEntryEmails(getCtx(), 0, get_TrxName());
							emails.setAD_User_ID(contact.getAD_User_ID());
							emails.setC_BPartner_ID(entry.getC_BPartner_ID());
							emails.setEMail(user.getEMail());
							emails.setC_DunningRunEntry_ID(entry.getC_DunningRunEntry_ID());
							emails.save();
						}
					List<MDSDunningIncludeEmails> emailContacts = new Query(getCtx(), I_DS_DunningInclude_Emails.Table_Name," C_Dunning_ID = ? ", get_TrxName()).setParameters(run.getC_Dunning_ID()).setOnlyActiveRecords(true).list();
					for(MDSDunningIncludeEmails commonContact : emailContacts)
					{
						MUser user = new MUser(getCtx(), commonContact.getAD_User_ID(), get_TrxName());
						email.addTo(user.getEMail());
						
						MDSDunningEntryEmails emails = new MDSDunningEntryEmails(getCtx(), 0, get_TrxName());
						emails.setAD_User_ID(commonContact.getAD_User_ID());
						emails.setC_BPartner_ID(entry.getC_BPartner_ID());
						emails.setEMail(user.getEMail());
						emails.setC_DunningRunEntry_ID(entry.getC_DunningRunEntry_ID());
						emails.save();
					}
					MBPartnerLocation location = new MBPartnerLocation(getCtx(), entry.getC_BPartner_Location_ID(), get_TrxName());
					List<MSalesRegion> salesRegionContacts = new Query(getCtx(), I_C_SalesRegion.Table_Name," C_SalesRegion_ID = ? ", get_TrxName()).setParameters(location.getC_SalesRegion_ID()).setOnlyActiveRecords(true).list();
					for(MSalesRegion salesRegContact : salesRegionContacts)
					{
						MUser user = new MUser(getCtx(), salesRegContact.getSalesRep_ID(), get_TrxName());
						if(user.getEMail()!=null)
							email.addTo(user.getEMail());
						
						MDSDunningEntryEmails emails = new MDSDunningEntryEmails(getCtx(), 0, get_TrxName());
						emails.setAD_User_ID(salesRegContact.getSalesRep_ID());
						emails.setC_BPartner_ID(entry.getC_BPartner_ID());
						emails.setEMail(user.getEMail());
						emails.setC_DunningRunEntry_ID(entry.getC_DunningRunEntry_ID());
						emails.save();
					}
					if (!email.isValid())
					{
						StringBuilder msglog = new StringBuilder(
								"@RequestActionEMailError@ Invalid EMail: ").append(to);
						addLog (entry.get_ID(), null, null,msglog.toString() );
						errors++;
						continue;
					}
					mText.setUser(to);	//	variable context
					mText.setBPartner(bp);
					mText.setPO(entry);
					String message = mText.getMailText(true);
					subject = " ".concat(bp.getName()).concat(" ").concat(dunningYearMonth);
					if (mText.isHtml())
					{
						email.setMessageHTML(mText.getMailHeader(), message);
						email.setSubject(email.getSubject().concat(subject));
					}
					else
					{
						email.setSubject (mText.getMailHeader());
						email.setMessageText (message);
					}
					//
					if (re != null) {
						attachment = re.getPDF(File.createTempFile(bp.getName(), ".pdf"));
						StringBuilder msglog = new StringBuilder().append(to.toString()).append(" - ").append(attachment);
						if (log.isLoggable(Level.FINE)) log.fine(msglog.toString());
						if(typeOfAttachment.equalsIgnoreCase("PDF") || typeOfAttachment.isEmpty())
							email.addAttachment(attachment);
						else
							email.addAttachment(outstandingBalanceInCSV);
					}
					//
					String msg = email.send();
					
					MUserMail um = new MUserMail(mText, entry.getAD_User_ID(), email);
					um.saveEx();
					if (msg.equals(EMail.SENT_OK))
					{
						StringBuilder msglog = new StringBuilder()
								.append(bp.getName()).append(" @RequestActionEMailOK@");
						addLog (entry.get_ID(), null, null,msglog.toString());
						count++;
						printed = true;
					}
					else
					{
						StringBuilder msglog = new StringBuilder().append(bp.getName()).append(" @RequestActionEMailError@ ").append(msg);
						addLog (entry.get_ID(), null, null,msglog.toString()	);
						errors++;
					}
				}
				else
				{
					if (re != null) {
						pdfList.add(re.getPDF());					
						count++;
						printed = true;
					}
				}
				if (printed)
				{
					if(attachment!=null)
					{
						MAttachment ma= new MAttachment(getCtx(), 0, get_TrxName());
						ma.setRecord_ID(entry.getC_DunningRunEntry_ID());
						ma.addEntry(attachment);
						ma.addEntry(outstandingBalanceInCSV);
						ma.set_TrxName(get_TrxName());
						ma.setAD_Table_ID(I_C_DunningRunEntry.Table_ID);
						ma.addTextMsg(subject);
						ma.saveEx();
					}
					entry.setProcessed (true);
					entry.save ();
				}
			}
		}	//	for all dunning letters
		if (errors==0) {
			run.setProcessed(true);
			run.saveEx();
		}
		if (p_EMailPDF){
			StringBuilder msgreturn = new StringBuilder("@Sent@=").append(count).append(" - @Errors@=").append(errors);
			return msgreturn.toString();
		}
		
		AEnv.executeAsyncDesktopTask(new Runnable() {			
			@Override
			public void run() {
				showReports(pdfList);
			}
		});
		
		StringBuilder msgreturn = new StringBuilder("@Printed@=").append(count);
		return msgreturn.toString();
	}	//	doIt


	private File getOutStandingBalanceExcel(MDunningRunEntry entry) 
	{
		final String COMMA_DELIMITER = ",";
		final String NEW_LINE_SEPARATOR = "\n";
		String header = "Doc Type,Invoice No,Date,PO Reference,Ship Partner,Currency,Payment Term,Due Date,Age,Invoice Amount,Open Amount";
		boolean applyPenalty = entry.getC_DunningLevel().isChargeFee();
		MBPartner bp = new MBPartner(getCtx(), entry.getC_BPartner_ID(), get_TrxName());
		int EntryID = entry.getC_DunningRunEntry_ID();
		
		String fileName = replaceAllSpecialCharacters(bp.getName());
		fileName = fileName.concat(".csv");
		File outstandingcsvfile = new File(fileName);
		FileWriter  fileWriter = null;
		BigDecimal totalInvoiceAmt = Env.ZERO;
		BigDecimal totalOpenAmt = Env.ZERO;
		BigDecimal totalPenaltyAmt = Env.ZERO;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			fileWriter = new FileWriter(outstandingcsvfile);
			String header1 = "Statement Of Outstanding Account";
			fileWriter.append(header1);
			fileWriter.append(NEW_LINE_SEPARATOR);
			String header2 = "Customer Name :, "+bp.getName();
			fileWriter.append(header2);
			fileWriter.append(NEW_LINE_SEPARATOR);
			header2 = "Statement Date : ,"+formatter.format(entry.getC_DunningRun().getDunningDate());
			fileWriter.append(header2);
			fileWriter.append(NEW_LINE_SEPARATOR);
			if(applyPenalty)
				header = "Doc Type,Invoice No,Date,PO Reference,Ship Partner,Currency,Payment Term,Due Date,Age,Invoice Amount,Open Amount,Penalty";
			fileWriter.append(header);
			fileWriter.append(NEW_LINE_SEPARATOR);
			ResultSet rs;
			PreparedStatement pstmt;
			String sql = "select bp.name as BillPartner,bloc.name as BillLocation,sp.name||' - '|| sloc.name as ShipPartner,ci.grandtotal as grandtotal,"
					+ "currencybase(ci.grandtotal,ci.c_Currency_id,ci.DateAcct,ci.ad_client_id,ci.ad_org_id) as BaseTransAmount,"
					+ "cc.iso_code,pt.netdays,pt.name as Paymentterm,ci.DateAcct as DateInvoiced,ci.dateAcct+pt.netdays as DueDate,"
					+ "ci.documentno as InvoiceNo,ci.POreference,ci.description,cd.name as DocumentType,"
					+ "case when EXTRACT(epoch FROM ((select run.DunningDate from C_DunningRun run,C_DunningRunEntry entry where entry.C_DunningRun_ID=run.C_DunningRun_ID and "
					+ "entry.C_DunningRunEntry_ID= ? )::timestamp::date-(ci.dateAcct+pt.netdays)))/3600/24<0 then 0 "
					+ "else EXTRACT(epoch FROM (select run.DunningDate from C_DunningRun run,C_DunningRunEntry entry where entry.C_DunningRun_ID=run.C_DunningRun_ID "
					+ "and entry.C_DunningRunEntry_ID=?)::timestamp::date-(ci.dateAcct+pt.netdays))/3600/24 end Aging,"
					+ "InvoiceopenToDate(ci.C_INVOICE_ID,0,(select run.DunningDate from C_DunningRun run,C_DunningRunEntry entry "
					+ "where entry.C_DunningRun_ID=run.C_DunningRun_ID and entry.C_DunningRunEntry_ID=? )::timestamp::date) as OpenAmount,"
					+ "ci.ispaid,ci.ad_client_id,(select run.DunningDate from C_DunningRun run,C_DunningRunEntry entry "
					+ "where entry.C_DunningRun_ID=run.C_DunningRun_ID and entry.C_DunningRunEntry_ID=? ) as DunningDate"
					+ " from c_invoice_v ci left outer join c_order co on ci.c_order_id=co.c_order_id "
					+ "left outer join c_bpartner sp on co.C_BPartner_ID=sp.c_bpartner_id "
					+ "left outer join c_bpartner_location sloc on co.c_bpartner_location_id=sloc.c_bpartner_location_id "
					+ "left outer join c_bpartner bp on ci.C_BPartner_ID=bp.c_Bpartner_id "
					+ "left outer join c_Bpartner_location bloc on ci.C_BPartner_Location_ID=bloc.c_Bpartner_location_id "
					+ "left outer join c_currency cc on ci.c_currency_id=cc.c_currency_id "
					+ "left outer join C_PaymentTerm pt on ci.C_PaymentTerm_ID=pt.C_PaymentTerm_ID,c_doctype cd  "
					+ "where ci.DateAcct<=? "
					+ "and ci.issotrx='Y' "
					+ "and ci.c_bpartner_id=(select entry.C_BPartner_ID from C_DunningRunEntry entry where entry.C_DunningRunEntry_ID=?)  "
					+ "and ci.C_DocTypeTarget_ID=cd.C_DocType_ID "
					+ "and ci.docstatus not in ('DR') "
					+ "and round(InvoiceopenToDate(ci.C_INVOICE_ID,0,?),1)<>0 "
					+ "UNION ALL "
					+ "select bp.name as BillPartner,null as Billocation , null as ShipPartner, "
					+ "pay.PayAmt*-1 as grandtotal, currencybase(pay.payamt,pay.c_currency_id,pay.DateAcct,pay.ad_client_id,pay.ad_org_id) as BaseTransAmount, "
					+ "cc.iso_code,null as NetDays,null as Paymentterm,pay.dateacct as DateInvoiced,null as DueDate, "
					+ "Pay.documentno as InvoiceNo,null as POReference,pay.description,cd.name as DocumentType, 0 as Aging , "
					+ "paymentavailable(pay.c_payment_id)*-1 as OpenAmount,pay.isallocated as IsPaid,pay.ad_client_id,"
					+ "(select run.DunningDate from C_DunningRun run,C_DunningRunEntry entry where entry.C_DunningRun_ID=run.C_DunningRun_ID and entry.C_DunningRunEntry_ID=?) as DunningDate "
					+ "from c_payment_v pay , c_bpartner bp , c_currency cc ,c_doctype cd "
					+ "where pay.c_bpartner_id=bp.c_Bpartner_id "
					+ "and pay.c_currency_id=cc.c_currency_id "
					+ "and pay.C_DocType_ID=cd.C_DocType_ID "
					+ "and pay.isallocated<>'Y' "
					+ "and paymentavailable(pay.c_payment_id)<>0 "
					+ "and pay.DateAcct<=(select run.DunningDate from C_DunningRun run,C_DunningRunEntry entry where entry.C_DunningRun_ID=run.C_DunningRun_ID and entry.C_DunningRunEntry_ID=?)::timestamp::date "
					+ "and pay.c_bpartner_id=(select entry.C_BPartner_ID from C_DunningRunEntry entry where entry.C_DunningRunEntry_ID=?) and pay.docstatus not in ('DR') "
					+ "and round(paymentavailable(pay.c_payment_id),1)<>0 order by DateInvoiced";
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, EntryID);
			pstmt.setInt(2, EntryID);
			pstmt.setInt(3, EntryID);
			pstmt.setInt(4, EntryID);
			pstmt.setTimestamp(5, entry.getC_DunningRun().getDunningDate());
			pstmt.setInt(6, EntryID);
			pstmt.setTimestamp(7, entry.getC_DunningRun().getDunningDate());
			pstmt.setInt(8, EntryID);
			pstmt.setInt(9, EntryID);
			pstmt.setInt(10, EntryID);
			rs = pstmt.executeQuery();
			BigDecimal current = Env.ZERO;
			BigDecimal Between0to30 = Env.ZERO;
			BigDecimal Between30to60 = Env.ZERO;
			BigDecimal Between60to90 = Env.ZERO;
			BigDecimal GreaterThan90 = Env.ZERO;
			while(rs.next())
			{
				int age = rs.getInt("Aging");
				String documentType = rs.getString("DocumentType")==null ? "":rs.getString("DocumentType");
				String poReference = rs.getString("POReference")==null ? "":rs.getString("POReference");
				String shipPartner =  rs.getString("ShipPartner")==null ? "":rs.getString("ShipPartner");
				BigDecimal invoiceAmt = rs.getBigDecimal("grandtotal");
				BigDecimal openAmt = rs.getBigDecimal("OpenAmount");
				String DocumentNo = rs.getString("InvoiceNo")==null ? "":rs.getString("InvoiceNo");
				String DocumentDate = rs.getDate("DateInvoiced")==null ? "":rs.getDate("DateInvoiced").toString();
				String currency = rs.getString("iso_code")==null ? "":rs.getString("iso_code");
				String dueDate = rs.getDate("DueDate")==null ? "":rs.getDate("DueDate").toString();
				String paymentTerm = rs.getString("Paymentterm")==null ? "":rs.getString("Paymentterm");
				if(openAmt.compareTo(Env.ZERO)!=0)
				{
//					$F{penaltyrate}.multiply(($F{aging}/365)*12).compareTo(new BigDecimal(0))<0 ? new BigDecimal(0):$F{penaltyrate}.multiply(($F{aging}/365)*12)
					BigDecimal penalty = Env.ZERO;
					
					if(applyPenalty)
					{
						BigDecimal penaltyRate = openAmt.multiply(new BigDecimal(0.01)).setScale(5, RoundingMode.HALF_UP);
						BigDecimal agingInBD = new BigDecimal(age);
						BigDecimal months = agingInBD.divide(new BigDecimal(365), 5, RoundingMode.HALF_DOWN).multiply(new BigDecimal(12));
						penalty = penaltyRate.multiply(months).compareTo(Env.ZERO)<0 ? Env.ZERO:penaltyRate.multiply(months);
						totalPenaltyAmt = totalPenaltyAmt.add(penalty);
					}
					fileWriter.append(documentType);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(DocumentNo);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(DocumentDate);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(poReference);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(shipPartner);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(currency);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(paymentTerm);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(dueDate);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(Integer.toString(age));
					fileWriter.append(COMMA_DELIMITER);
					String invAmt = invoiceAmt.setScale(2, RoundingMode.CEILING).toString().concat(" ");
					fileWriter.append(invAmt);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(openAmt.setScale(2, RoundingMode.CEILING).toString().concat(" "));
					if(applyPenalty)
					{
						fileWriter.append(COMMA_DELIMITER);
						fileWriter.append(penalty.setScale(2, RoundingMode.CEILING).toString().concat(" "));
					}
					fileWriter.append(NEW_LINE_SEPARATOR);
					totalInvoiceAmt = totalInvoiceAmt.add(invoiceAmt);
					totalOpenAmt = totalOpenAmt.add(openAmt);
					
					if(age==0)
						current = current.add(openAmt);
					if(age > 0 && age <= 30)
						Between0to30 = Between0to30.add(openAmt);
					if(age > 30 && age <= 60)
						Between30to60 = Between30to60.add(openAmt);
					if(age > 60 && age <= 90)
						Between60to90 = Between60to90.add(openAmt);
					if(age > 90)
						GreaterThan90 = GreaterThan90.add(openAmt);
				}
			}
			
			String footer = ",,,,,,,,,"+totalInvoiceAmt.toString()+","+totalOpenAmt.setScale(2, RoundingMode.CEILING).toString()+"";
			if(applyPenalty)
				footer = ",,,,,,,,,"+totalInvoiceAmt.toString()+","+totalOpenAmt.setScale(2, RoundingMode.CEILING).toString()+","+totalPenaltyAmt.setScale(2, RoundingMode.CEILING).toString()+"";
			fileWriter.append(footer);
			fileWriter.append(NEW_LINE_SEPARATOR);
			fileWriter.append("Aging Summary");
			fileWriter.append(NEW_LINE_SEPARATOR);
			String agingSummary = "Current :, "+current.setScale(2, RoundingMode.CEILING).toString().concat(" ");
			fileWriter.append(agingSummary);
			fileWriter.append(NEW_LINE_SEPARATOR);
			agingSummary = "1-30 :, "+Between0to30.setScale(2, RoundingMode.CEILING).toString().concat(" ");
			fileWriter.append(agingSummary);
			fileWriter.append(NEW_LINE_SEPARATOR);
			agingSummary = "30-60 :, "+Between30to60.setScale(2, RoundingMode.CEILING).toString().concat(" ");
			fileWriter.append(agingSummary);
			fileWriter.append(NEW_LINE_SEPARATOR);
			agingSummary = "60-90 :, "+Between60to90.setScale(2, RoundingMode.CEILING).toString().concat(" ");
			fileWriter.append(agingSummary);
			fileWriter.append(NEW_LINE_SEPARATOR);
			agingSummary = ">90 :, "+GreaterThan90.setScale(2, RoundingMode.CEILING).toString().concat(" ");
			fileWriter.append(agingSummary);
			fileWriter.append(NEW_LINE_SEPARATOR);
			agingSummary = "Total :, "+totalOpenAmt.setScale(2, RoundingMode.CEILING).toString().concat(" ");	
			fileWriter.append(agingSummary);
		}
		catch(Exception e)
		{
			log.severe("Exception in generating csv file");
		}
		finally
		{
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				log.severe(" Exception while flushing filewriter "+e.getMessage());
			}
			
		}
		return outstandingcsvfile;
	}

	private String replaceAllSpecialCharacters(String fileName) 
	{
		if(fileName==null)
		{
			return "";
		}
		if(fileName.contains("/"))
			fileName = fileName.replace("/", "");
		if(fileName.contains("\\"))
			fileName = fileName.replace("\\", "");
		if(fileName.contains("*"))
			fileName = fileName.replace("*", "");
		if(fileName.contains("["))
			fileName = fileName.replace("[", "");
		if(fileName.contains("]"))
			fileName = fileName.replace("]", "");
		
		return fileName;
	}

	private void showReports(List<File> pdfList) {
		if (pdfList.size() > 1) {
			try {
				File outFile = File.createTempFile("DunningPrint", ".pdf");					
				AEnv.mergePdf(pdfList, outFile);
				Window win = new SimplePDFViewer(this.getName(), new FileInputStream(outFile));
				win.setAttribute(Window.MODE_KEY, Window.MODE_HIGHLIGHTED);
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e) {
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		} else if (pdfList.size() > 0) {
			try {
				Window win = new SimplePDFViewer(this.getName(), new FileInputStream(pdfList.get(0)));
				win.setAttribute(Window.MODE_KEY, Window.MODE_HIGHLIGHTED);
				SessionManager.getAppDesktop().showWindow(win, "center");
			} catch (Exception e)
			{
				log.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}
		}
	}
	
}	//	DunningPrint
