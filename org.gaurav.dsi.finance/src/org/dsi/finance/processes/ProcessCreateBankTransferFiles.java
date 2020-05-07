package org.dsi.finance.processes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttachment;
import org.compiere.model.MClient;
import org.compiere.model.MMailText;
import org.compiere.model.MPayment;
import org.compiere.model.MQuery;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.model.MUserMail;
import org.compiere.model.PrintInfo;
import org.compiere.model.Query;
import org.compiere.print.MPrintFormat;
import org.compiere.print.ReportEngine;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.gaurav.dsi.model.I_DS_B2B_EmailConf;
import org.gaurav.dsi.model.MDSB2BConfiguration;
import org.gaurav.dsi.model.MDSB2BEmailConf;
import org.gaurav.dsi.model.MDSIExportPayments;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class ProcessCreateBankTransferFiles extends SvrProcess
{
	PreparedStatement pstmt;
	ResultSet rs;
	int C_bankAccount_ID;
	String p_SalaryMonth;
	String orgName;
	int p_Year;
	String S1 = "";
	String S2 = "";
	String S3 = "";
	String line;
	File TextFile;
	String FileName;
	int exportBatch_ID;
	private boolean		p_CreateEadvice = true;
	File directory;
	String DocumentNote;
	String eAdviceH1;
	String eAdviceH2;
	String eAdviceH3;
	File eAdviceFile;
	String eAdvicefileName;
	static String finalFileName;
	CreateEAdviceFile eAdvice=new CreateEAdviceFile();
	PrintWriter eadviceWriter;
	PrintWriter pw;
	String headerFileName;
	MDSIExportPayments export ;
	static String LINE_Seperator = "\r\n";
	@Override
	protected void prepare() 
	{
		exportBatch_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception
	{
		export = new MDSIExportPayments(getCtx(),exportBatch_ID, get_TrxName());
		DocumentNote = export.getDocumentNote();
		if(export.getDS_PaymentType().equalsIgnoreCase("IP"))
			FileName=createVendorIndividualPayments();
		else if(export.getDS_PaymentType().equalsIgnoreCase("BP"))
			FileName=createVendorBatchPayment();
		else if(export.getDS_PaymentType().equalsIgnoreCase("SP"))
		{
			C_bankAccount_ID=export.getC_BankAccount_ID();
			p_SalaryMonth=export.getDSI_Month();
			p_Year=export.getCalendarYear();
			orgName=export.getOrgName();
			FileName=createSalaryTransfer();
		}
		export.setProcessed(true);
		export.save();
		export.setDocumentNote(DocumentNote.concat(" , ").concat(FileName));
		export.save();	
		return FileName;
	}

	private String createSalaryTransfer() throws IOException, SQLException 
	{
		createFiles(true);
		prepareSalaryDetails();
		return postProcessFiles(true);
	}

	private void prepareSalaryDetails() throws SQLException 
	{
		String port = MSysConfig.getValue("DS_Vienna_Oracle_Port", 1521);
		String hostName = MSysConfig.getValue("DS_Vienna_Oracle_Host","192.168.10.19");
		String dbUsername = MSysConfig.getValue("DS_Vienna_Oracle_DBUsername","dsierp");
		String dbPassword = MSysConfig.getValue("DS_Vienna_Oracle_DBPassword","dsierp");
		String dbName = MSysConfig.getValue("DS_Vienna_Oracle_DBName","orcl");
		ResultSet ors = null ;
		PreparedStatement ostmt = null;
		Connection oracleConnection = null ; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			oracleConnection =DriverManager.getConnection("jdbc:oracle:thin:@"+hostName+":"+port+":"+dbName,dbUsername,dbPassword);
		} 
		catch (ClassNotFoundException e1) 
		{
			log.severe("Problem getting the connection"+e1.getMessage());
		} catch (SQLException e) 
		{
			log.severe("Problem getting the connection"+e.getMessage());
		}  
		String sql = "select distinct 'S1,',Substr(info.DUNS,1,7)||',',bank.ACCOUNTNO||',','MXD'||',','1,',',',TO_CHAR(now(),'DD-Mon-YY')||',' ,TO_CHAR(now(), 'DDMMYYYYHH24MISS') "
				+ "from AD_ORG org,AD_OrgInfo info,C_BankAccount bank "
				+ "where bank.AD_ORG_ID=org.AD_ORG_ID and org.AD_ORG_ID=info.AD_ORG_ID and bank.c_bankaccount_id="
				+ C_bankAccount_ID;
		pstmt = DB.prepareStatement(sql,get_TrxName());
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				S1 = rs.getString(1); // Section Index
				S1 = S1.concat(rs.getString(2)); // Company CR
				S1 = S1.concat(rs.getString(3)); // Debit Account Number 
				S1 = S1.concat(rs.getString(4)); // Transfer Method
				S1 = S1.concat(rs.getString(5)); // Debit Mode
				S1 = S1.concat(rs.getString(6)); // Debit Narrative (reference)
				S1 = S1.concat(rs.getString(7)); // Request Date
				S1 = S1.concat(rs.getString(8)); // Company's Batch Reference
				pw.write(S1);
				pw.println();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
			closeOracleConnection(oracleConnection,ors,ostmt,e.getMessage());
		} finally {
			ors = null;
			ostmt = null;
		}
		sql = "select distinct 'S2,',case when bank.name like '%Ahli%' then 'TRF' else 'LCL' end ||',' as TransferMethod," +
				"empmnth.NET_AMOUNT,',BHD,' as Currency ,',' as ExchangeRate,"
				+ "',' as DealRefNo,',' as PrefDate,',' as DebitAccount,"
				+ "bpacc.AccountNo||',' as EmpAccountNo,mnth.documentno||empmnth.VSS_EMPMONTHLYSALARY_ID||',' as UniqueTransRef," +
						"trim(nvl(SUBSTR(mnth.Description,1,35),''))||',' DBNar1, ',' DBNar2,"
				+ "trim(nvl(SUBSTR(mnth.Description,1,35),''))||',' CRNar,trim(nvl(SUBSTR(mnth.Description,1,35),''))||',' as PaymentDetail1,','PaymentDetail12,','PaymentDetail3,','PaymentDetail4,"
				+ "SUBSTR(bp.name,1,35)||',' as BusinessPartner,nvl(SUBSTR(bpacc.A_Street,1,35),'')||',' as Adress1,"
				+ "trim(nvl(SUBSTR(bpacc.A_City,1,35),''))||',' as Adress2,bank.name||',' as BankName,nvl(loc.ADDRESS1,'')||',',"
				+ "nvl(loc.ADDRESS2,'')||',',nvl(loc.ADDRESS3,'')||',',bank.SwiftCode ||',' as SwiftCode ,',' IntermediateAccount,"
				+ "',' IntermediatSwift,','IntermediateName,',' IntermediateAd1,',' IntermediateAd2,',' IntermediateAd3,'A,' ChargeType,',' SortCode,"
				+ "',' BIC "
				+ "from dsierp.VSS_MONTHLYSALARY mnth left outer join dsierp.vss_months mt on mnth.vss_months_id=mt.vss_months_id " 
				+ "left outer join dsierp.VSS_EMPMONTHLYSALARY empmnth on mnth.VSS_MONTHLYSALARY_ID=empmnth.VSS_MONTHLYSALARY_ID and empmnth.IsActive='Y' "
				+ "left outer join dsierp.VSS_EMPMONTHSALDETAILS empdet on empmnth.VSS_EMPMONTHLYSALARY_ID=empdet.VSS_EMPMONTHLYSALARY_ID "
				+ "left outer join dsierp.C_BPartner bp on bp.c_Bpartner_id = empmnth.c_Bpartner_id "
				+ "left outer join dsierp.C_BP_BankAccount bpacc on  bp.c_bpartner_id=bpacc.c_bpartner_id "
				+ "left outer join dsierp.c_bank bank on bpacc.c_bank_id=bank.c_bank_id "
				+ "left outer join dsierp.c_location loc on bank.c_location_id=loc.c_location_id "
				+ "where mt.name like '"+p_SalaryMonth+"' "
				+ "and mnth.attend_year="+p_Year+" " 
				+ "and bpacc.C_Bank_ID is not null "
				+ "and bank.swiftcode is not null AND BP.DS_ORGNAME='"+orgName+"' " +
						" order by BankName,BusinessPartner";
		ostmt = oracleConnection.prepareStatement(sql);
		try {
			ors = ostmt.executeQuery();
			while (ors.next()) {
				S2 = ors.getString(1); // Section Index
				S2 = S2.concat(ors.getString(2)); // Transfer Method
				DecimalFormat df = new DecimalFormat("#.000");
				Double Amount=ors.getDouble(3);
				String roundedAmt=df.format(Amount);
//				System.out.println(roundedAmt);
				S2 = S2.concat(roundedAmt); // Credit Amount
				S2 = S2.concat(ors.getString(4)); // Credit Currency
				S2 = S2.concat(ors.getString(5)); // Exchange Rate
				S2 = S2.concat(ors.getString(6)); // Deal Ref No
				S2 = S2.concat(ors.getString(7)); // Preferred Value Date
				S2 = S2.concat(ors.getString(8)); // Debit Account Number
				S2 = S2.concat(ors.getString(9)); // Beneficiary Account Number
				S2 = S2.concat(ors.getString(10)); // Unique Transaction Ref No
				S2 = S2.concat(ors.getString(11)); // Debit Narrative 1
				S2 = S2.concat(ors.getString(12)); // Debit Narrative 2
				S2 = S2.concat(ors.getString(13)); // Credit Narrative
				S2 = S2.concat(ors.getString(14)); // Payment Details Line 1
				S2 = S2.concat(ors.getString(15)); // Payment Details Line 2
				S2 = S2.concat(ors.getString(16)); // Payment Details Line 3
				S2 = S2.concat(ors.getString(17)); // Payment Details Line 4
				S2 = S2.concat(ors.getString(18)); // Beneficiary Name
				S2 = S2.concat(ors.getString(19)); // Beneficiary Address Line 1
				S2 = S2.concat(ors.getString(20)); // Beneficiary Address Line 2
				S2 = S2.concat(ors.getString(21)); // Beneficiary Bank Name
				S2 = S2.concat(ors.getString(22)); // Beneficiary Bank Address 1
				S2 = S2.concat(ors.getString(23)); // Beneficiary Bank Address 2
				S2 = S2.concat(ors.getString(24)); // Beneficiary Bank Address 3
				S2 = S2.concat(ors.getString(25)); // SWIFT Code
				S2 = S2.concat(ors.getString(26)); // Intermediary Account
				S2 = S2.concat(ors.getString(27)); // Intermediary Swift Code
				S2 = S2.concat(ors.getString(28)); // Intermediary Name
				S2 = S2.concat(ors.getString(29)); // Intermediary Address 1
				S2 = S2.concat(ors.getString(30)); // Intermediary Address 2
				S2 = S2.concat(ors.getString(31)); // Intermediary Address 3
				S2 = S2.concat(ors.getString(32)); // Charges Type
				S2 = S2.concat(ors.getString(33)); // Sort Code
				S2 = S2.concat(ors.getString(34)); // BIC
//				S2 = S2.concat(ors.getString(35)); // ABA Routing Number
				pw.write(S2);
				pw.println();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
			closeOracleConnection(oracleConnection,ors,ostmt,e.getMessage());
		} finally {
			ors = null;
			ostmt = null;
		}
		sql = " with t1 as (select distinct 'S2,',empmnth.NET_AMOUNT as Net_Amount,',','BHD,' as Currency ,',' as ExchangeRate,"
				+ "',' as DealRefNo,',' as PrefDate,',' as DebitAccount,"
				+ "bpacc.AccountNo||',' as EmpAccountNo,mnth.documentno||empmnth.VSS_EMPMONTHLYSALARY_ID||',' as UniqueTransRef,',' DBNar1,',' DBNar2,',' CRNar,"
				+ "nvl(mnth.Description,' ')||',' as PaymentDetail1,','PaymentDetail12,','PaymentDetail3,' 'PaymentDetail4,SUBSTR(bp.name,1,35)||',' as BusinessPartner,"
				+ "nvl(SUBSTR(bpacc.A_Street,1,35),' ')||',' as Adress1,nvl(SUBSTR(bpacc.A_City,1,35),' ')||',' as Adress2,bank.name||',' as BankName,nvl(loc.ADDRESS1,'')||',',"
				+ "nvl(loc.ADDRESS2,' ')||',',nvl(loc.ADDRESS3,' ')||',',bank.swiftcode || ',' as SwiftCode,',' IntermediateAccount,','Intermediatebank,',' IntermediatSwift,','IntermediateName,"
				+ "',' IntermediateAd1,',' IntermediateAd2,',' IntermediateAd3,'A,' ChargeType,',' SortCode,',' BIC,',' ABA " +
						"from dsierp.VSS_MONTHLYSALARY mnth " +
						"left outer join dsierp.vss_months mt on mnth.vss_months_id=mt.vss_months_id "
				+ "left outer join dsierp.VSS_EMPMONTHLYSALARY empmnth on mnth.VSS_MONTHLYSALARY_ID=empmnth.VSS_MONTHLYSALARY_ID and empmnth.IsActive='Y' "
				+ "left outer join dsierp.VSS_EMPMONTHSALDETAILS empdet on empmnth.VSS_EMPMONTHLYSALARY_ID=empdet.VSS_EMPMONTHLYSALARY_ID "
				+ "left outer join dsierp.C_BPartner bp on bp.c_Bpartner_id = empmnth.c_Bpartner_id "
				+ "left outer join dsierp.C_BP_BankAccount bpacc on  bp.c_bpartner_id=bpacc.c_bpartner_id "
				+ "left outer join dsierp.c_bank bank on bpacc.c_bank_id=bank.c_bank_id "
				+ "left outer join dsierp.c_location loc on bank.c_location_id=loc.c_location_id " +
						"where mt.name like '"+p_SalaryMonth+"' and mnth.attend_year="+p_Year+" " +
						"and  bpacc.C_Bank_ID is not null AND BP.DS_ORGNAME='"+orgName+"' " +
						"and bank.swiftcode is not null ) "
				+ "select 'S3,',count(*)||',',sum(net_amount)  from t1 ";
		ostmt = oracleConnection.prepareStatement(sql);
		try {
			ors = ostmt.executeQuery();
			while (ors.next()) {
				S3 = ors.getString(1); // Section Index
				S3 = S3.concat(ors.getString(2)); // Number of Records
				DecimalFormat df = new DecimalFormat("#.000");
				Double Amount=ors.getDouble(3);
				String roundedAmt=df.format(Amount);
				S3=S3.concat(roundedAmt);
				S3 = S3.concat("");// Hash Total
				pw.write(S3);
				pw.println();
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
			closeOracleConnection(oracleConnection,ors,ostmt,e.getMessage());
		} finally {
			closeOracleConnection(oracleConnection,ors,ostmt,"");
		}
		pw.close();
	}

	private void closeOracleConnection(Connection oracleConnection, ResultSet ors, PreparedStatement ostmt, String exception) throws SQLException 
	{
		ors = null;
		ostmt = null;
		oracleConnection.close();
		if(!exception.isEmpty())
			throw new AdempiereException(exception);
	}

	private String createVendorBatchPayment() throws IOException 
	{		
		if(export.getDescription()==null || export.getDescription().isEmpty())
			throw new AdempiereException("Description can not be empty.");
		
		createFiles(false);
		addTopSection();
		String sql = "select SECTIONHEADER,TRANSFERMETHOD,sum(CREDITAMOUNT) as CreditAmount,CURRENCY,EXCHANGERATE,DEALREFNO," +
				"PREFEREDVALUEDATE,DEBITACCOUNTNO, BENBANKACCOUNTNUMBER,max(UNIQTRANSREF) as UniqueReference," +
				"coalesce(replace(SUBSTR(Description,1,35), ','::text, ''::text),'') ||',' as DebNar1,coalesce(replace(SUBSTR(Description,36,35), ','::text, ''::text),'') ||',' as DebNar2, " +
				"coalesce(replace(SUBSTR(Description,1,35), ','::text, ''::text),'') ||',' as CredNar,coalesce(replace(SUBSTR(Description,1,35), ','::text, ''::text),'') ||',' as PayDet1," +
				" coalesce(replace(SUBSTR(Description,36,35), ','::text, ''::text),'') ||',' as PayDet2,coalesce(replace(SUBSTR(Description,71,35), ','::text, ''::text),'') ||',' as PayDet3," +
				" coalesce(replace(SUBSTR(Description,106,35), ','::text, ''::text),'') ||',' as PayDet4,ACCOUNTNAME,BENADRESS1,BENADRESS2,BENIFICIARYBANK," +
				" BENBANKADRESS1,BENBANKADRESS2,BENBANKADDRESS3,SWIFTCODE,ACCOUNTNO,INTERBANKSWIFTCODE, INTERMEDIATEBANKNAME," +
				"INTERMEDIATEBANKADDRES1,INTERMEDIATEBANKADDRESS2,INTERMEDIATEBANKADDRES3, CHARGESTYPE,SORTCODE,BIC,ROUTINGNO," +
				"DSI_EXPORTPAYMENTS_ID from DSI_ExportVendorPayment  where DSI_EXPORTPAYMENTS_ID="+exportBatch_ID +
				"group by SECTIONHEADER,TRANSFERMETHOD,CURRENCY,EXCHANGERATE,DEALREFNO,PREFEREDVALUEDATE," +
				"DEBITACCOUNTNO,BENBANKACCOUNTNUMBER,ACCOUNTNAME,BENADRESS1,BENADRESS2,BENIFICIARYBANK,BENBANKADRESS1," +
				"BENBANKADRESS2,BENBANKADDRESS3,SWIFTCODE,ACCOUNTNO,INTERBANKSWIFTCODE,INTERMEDIATEBANKNAME,INTERMEDIATEBANKADDRES1," +
				"INTERMEDIATEBANKADDRESS2,INTERMEDIATEBANKADDRES3,CHARGESTYPE,SORTCODE,BIC,ROUTINGNO,DSI_EXPORTPAYMENTS_ID,description";

		addDetails(sql, true); 
		sql = "select 'S3,' as Sindex ,1 || ',' as TotalCount,sum(cp.PAYAMT) as TotalPay from DSI_EXPORTPAYMENTSLINE line," +
				"c_payment cp where line.c_payment_id=cp.c_payment_id and line.DSI_EXPORTPAYMENTS_ID="+exportBatch_ID;
		
		addTotalSection(sql);
		updatePayments();
		return postProcessFiles(false);
	}
	
	private String createVendorIndividualPayments() throws IOException 
	{
		createFiles(false);
		addTopSection();

		String sql = "select SECTIONHEADER,TRANSFERMETHOD,CREDITAMOUNT,CURRENCY,EXCHANGERATE,"
				+ "DEALREFNO,PREFEREDVALUEDATE,DEBITACCOUNTNO,BENBANKACCOUNTNUMBER,UNIQTRANSREF,"
				+ "DEBNAR1,DEBNAR2,CREDNAR,PAYDET1,PAYDET2,PAYDET3,PAYDET4,ACCOUNTNAME,BENADRESS1,"
				+ "BENADRESS2,BENIFICIARYBANK,BENBANKADRESS1,BENBANKADRESS2,BENBANKADDRESS3,SWIFTCODE,ACCOUNTNO,"
				+ "INTERBANKSWIFTCODE,INTERMEDIATEBANKNAME,INTERMEDIATEBANKADDRES1,INTERMEDIATEBANKADDRESS2,"
				+ "INTERMEDIATEBANKADDRES3,CHARGESTYPE,SORTCODE,BIC,ROUTINGNO,"
				+ "C_PAYMENT_ID,LINENO,DSI_EXPORTPAYMENTS_ID,DESCRIPTION from DSI_EXPORTVENDORPAYMENT "
				+ "where DSI_EXPORTPAYMENTS_ID="
				+ exportBatch_ID
				+ " order by LineNo ";
		addDetails(sql, false);
		
		sql = "select 'S3,' as Sindex ,count(*) ||',' as TotalCount,sum(cp.PAYAMT) as TotalPay"
				+ " from DSI_EXPORTPAYMENTSLINE line,c_payment cp where "
				+ "line.c_payment_id=cp.c_payment_id and line.DSI_EXPORTPAYMENTS_ID="
				+ exportBatch_ID;
		
		addTotalSection(sql);
		return postProcessFiles(false);
	}
	
	private void createFiles(boolean isSalary) throws IOException 
	{
		String FilePath="C:" + File.separator+"VendorPayemts";
		directory = new File(FilePath);
		if(isSalary)
			FilePath = FilePath+File.separator+"hello" + File.separator + orgName+"_SAL";
		else
			FilePath = FilePath + File.separator +"DS_PAY";
		String eAdvicePath=directory+File.separator+"DS_EADVICE";
		directory.mkdirs();
		MDSIExportPayments export= new MDSIExportPayments(getCtx(), exportBatch_ID, get_TrxName());
		DocumentNote=export.getDocumentNote();
		if(!(DocumentNote != null))
		{
			DocumentNote=" ";
		}
		String d="select TO_CHAR(now(), 'DDMMYYYYHH24MISS') ";
		pstmt = DB.prepareStatement(d, get_TrxName());
		try
		{
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				TextFile = new File(FilePath+"_"+rs.getString(1)+".txt");
				FileName=FilePath+"_"+rs.getString(1)+".txt";
				
				// (works for both Windows and Linux)
				TextFile.createNewFile();
				if(!isSalary)
				{
					finalFileName="DS_PAY_"+rs.getString(1)+".txt";
					/*
					 *  EAdvice part added later 02-11-2014 
					 *  
					 */
					eAdviceFile=new File(eAdvicePath+"_"+rs.getString(1)+".txt");
					eAdvicefileName=eAdvicePath+"_"+rs.getString(1)+".txt";
					eAdviceFile.createNewFile();
				}
			}
		}
		catch (Exception e) 
		{
			log.log(Level.SEVERE, d, e);
		} 
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		pw = new PrintWriter(new FileWriter(TextFile));
		if(!isSalary)
			eadviceWriter=new PrintWriter(new FileWriter(eAdviceFile));
	}

	private void addTopSection() throws IOException 
	{
		String sql = "select 'S1,',inf.DUNS||',',',' as DebitAccount,'MXD,' as TransferMethod," +
				"'M,',','DebitNarrative ,Exp.DATE1||',' as RequestDate, " +
				"TO_Char(Exp.DATE1,'DDMMYYYY')||Exp.DSI_EXPORTPAYMENTS_ID as BatchNo " +
				"from DSI_EXPORTPAYMENTS exp,ad_org org,ad_orginfo inf " +
				"where exp.ad_org_id=org.ad_org_id and org.ad_org_id=inf.ad_org_id " +
				"and exp.DSI_EXPORTPAYMENTS_ID="+exportBatch_ID;
		
		pstmt = DB.prepareStatement(sql, get_TrxName());
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				S1 = rs.getString(1); // Section Index
				S1 = S1.concat(rs.getString(2)); // Company CR
				S1 = S1.concat(rs.getString(3)); // Debit Account Number 
				S1 = S1.concat(rs.getString(4)); // Transfer Method
				S1 = S1.concat(rs.getString(5)); // Debit Mode
				S1 = S1.concat(rs.getString(6)); // Debit Narrative (reference)
				SimpleDateFormat filedateFormat=new SimpleDateFormat("dd-MMM-yyyy");
				String PayDate = filedateFormat.format(export.getDate1()).concat(",");
				S1 = S1.concat(PayDate); // Request Date
				S1 = S1.concat(rs.getString(8)); // Company's Batch Reference
				pw.write(S1);
				pw.append(LINE_Seperator);
				
			//	EAdvice part added later 02-11-2014   
				eAdviceH1 = "H,";
				eAdviceH1 = eAdviceH1.concat(finalFileName.concat(","));
				eAdviceH1 = eAdviceH1.concat(rs.getString(8));
				eadviceWriter.write(eAdviceH1);
				eadviceWriter.append(LINE_Seperator);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}
	
	private void addDetails(String sql, boolean batch) 
	{
		pstmt = DB.prepareStatement(sql, get_TrxName());
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				int paymentID = 0;
				S2 = rs.getString(1); // Section Index
				S2 = S2.concat(rs.getString(2)); // Transfer Method
				DecimalFormat df = new DecimalFormat("#.000");
				Double Amount=rs.getDouble(3);
				String roundedAmt=df.format(Amount);
				S2 = S2.concat(roundedAmt); // Credit Amount
				S2 = S2.concat(rs.getString(4)); // Credit Currency
				S2 = S2.concat(rs.getString(5)); // Exchange Rate
				S2 = S2.concat(rs.getString(6)); // Deal Ref No
				S2 = S2.concat(rs.getString(7)); // Preferred Value Date
				S2 = S2.concat(rs.getString(8)); // Debit Account Number
				S2 = S2.concat(rs.getString(9)); // Beneficiary Account Number
				S2 = S2.concat(rs.getString(10)); // Unique Transaction Ref No
				S2 = S2.concat(rs.getString(11)); // Debit Narrative 1
				S2 = S2.concat(rs.getString(12)); // Debit Narrative 2
				S2 = S2.concat(rs.getString(13)); // Credit Narrative
				S2 = S2.concat(rs.getString(14)); // Payment Details Line 1
				S2 = S2.concat(rs.getString(15)); // Payment Details Line 2
				S2 = S2.concat(rs.getString(16)); // Payment Details Line 3
				S2 = S2.concat(rs.getString(17)); // Payment Details Line 4
				S2 = S2.concat(rs.getString(18)); // Beneficiary Name
				S2 = S2.concat(rs.getString(19)); // Beneficiary Address Line 1
				S2 = S2.concat(rs.getString(20)); // Beneficiary Address Line 2
				S2 = S2.concat(rs.getString(21)); // Beneficiary Bank Name
				S2 = S2.concat(rs.getString(22)); // Beneficiary Bank Address 1
				S2 = S2.concat(rs.getString(23)); // Beneficiary Bank Address 2
				S2 = S2.concat(rs.getString(24)); // Beneficiary Bank Address 3
				S2 = S2.concat(rs.getString(25)); // SWIFT Code
				S2 = S2.concat(rs.getString(26)); // Intermediary Account
				S2 = S2.concat(rs.getString(27)); // Intermediary Swift Code
				S2 = S2.concat(rs.getString(28)); // Intermediary Name
				S2 = S2.concat(rs.getString(29)); // Intermediary Address 1
				S2 = S2.concat(rs.getString(30)); // Intermediary Address 2
				S2 = S2.concat(rs.getString(31)); // Intermediary Address 3
				S2 = S2.concat(rs.getString(32)); // Charges Type
				S2 = S2.concat(rs.getString(33)); // Sort Code
				S2 = S2.concat(rs.getString(34)); // BIC
//				S2 = S2.concat(rs.getString(35)); // ABA Routing Number
				pw.write(S2);
				pw.append(LINE_Seperator);
				
				if(!batch)
				{
					paymentID = rs.getInt(36);
					MPayment mp = new MPayment(getCtx(), paymentID, get_TrxName());
					mp.set_ValueOfColumn("DSI_IsExported", true);
					mp.saveEx();
				}
				if(batch)
					paymentID=DB.getSQLValue(get_TrxName(), "select line.C_PAYMENT_ID from DSI_EXPORTPAYMENTSLINE line where line.DSI_EXPORTPAYMENTS_ID=?", exportBatch_ID);
				
				String eadviceDes;
				eAdviceH2 = "D,";
				eAdviceH2 = eAdviceH2.concat(rs.getString(11));
				ArrayList<String> getDet=eAdvice.getDetailsPerPayment(paymentID);
				eAdviceH2=eAdviceH2.concat(getDet.get(0).concat(","));
				eAdviceH2=eAdviceH2.concat(getDet.get(1).concat(","));
				eAdviceH2=eAdviceH2.concat(getDet.get(2).concat(","));
				if(rs.getString(11).length()<20)
				{
					eadviceDes=rs.getString(11);
					eadviceDes.replace(',','-');
				}
				else
				{
					eadviceDes=rs.getString(11).substring(0, 20);
				}
				eAdviceH2=eAdviceH2.concat(eadviceDes.concat(","));
				eAdviceH2=eAdviceH2.concat((rs.getString(4).substring(1,4)).concat(" ".concat(roundedAmt)));
				eadviceWriter.write(eAdviceH2);
				eadviceWriter.append(LINE_Seperator);
			}
			
		} 
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql, e);
		} 
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
	}

	private String postProcessFiles(boolean isSalary) throws IOException 
	{
		MAttachment maa = new MAttachment(getCtx(), getTable_ID(),
				exportBatch_ID, get_TrxName());
		if (maa.get_ID()<=0) {
			MAttachment ma = new MAttachment(getCtx(), 0, get_TrxName());
			ma.setRecord_ID(exportBatch_ID);
			ma.addEntry(TextFile);
			if(!isSalary)
				ma.addEntry(eAdviceFile);
			ma.set_TrxName(get_TrxName());
			ma.setAD_Table_ID(getTable_ID());
			ma.addTextMsg("Added new Text File" + FileName);
			ma.saveEx();
		} else {
			
			maa.addEntry(TextFile);
			if(!isSalary)
				maa.addEntry(eAdviceFile);
			maa.set_TrxName(get_TrxName());
			maa.addTextMsg("Added new Text File" + FileName);
			maa.saveEx();
		}
		boolean uploadedSuccessfully = UploadToSFTP(TextFile,eAdviceFile,isSalary);
		return "File Created : -" + FileName + " E Advice created : -"
				+ eAdvicefileName +" File uploaded "+ uploadedSuccessfully;
		
	}

	private void addTotalSection(String sql) 
	{
		pstmt = DB.prepareStatement(sql, get_TrxName());
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				
				S3 = rs.getString(1); // Section Index
				S3 = S3.concat(rs.getString(2)); // Number of Records
				DecimalFormat df = new DecimalFormat("#.000");
				Double Amount=rs.getDouble(3);
				String roundedAmt=df.format(Amount);
				S3 = S3.concat(roundedAmt);// Hash Total
				pw.write(S3);
				pw.append(LINE_Seperator);
				if(p_CreateEadvice)
				{
				eAdviceH2 = "T,"; // Section Index
				eAdviceH2 = eAdviceH2.concat(rs.getString(2)); // Number of Records
				eAdviceH2 = eAdviceH2.concat(roundedAmt);// Hash Total
				eadviceWriter.write(eAdviceH2);
				eadviceWriter.append(LINE_Seperator);
				}
			}
		} 
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql, e);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		eadviceWriter.close();
		pw.close();
	}

	private boolean UploadToSFTP(File MainFile, File eAdviceFile, boolean isSalary) throws IOException 
	{
		int B2BConfigurationID = DB.getSQLValue(get_TrxName(), "select DS_B2B_Configuration_ID from DS_B2B_Configuration Where ad_org_id = ? ", Env.getAD_Org_ID(getCtx()));
		MDSB2BConfiguration conf = new MDSB2BConfiguration(getCtx(), B2BConfigurationID, get_TrxName());

		String SFTPHOST = conf.getDS_SftpHost();
		int SFTPPORT = conf.getDS_SftpPort();
		String SFTPUSER = conf.getDS_SftpUser();
		String SFTPPASS = conf.getDS_SftpPassword();
		String SFTPWORKINGDIR = conf.getDS_SftpWorkingDir();
		if(SFTPHOST!=null || SFTPPORT!=0 || SFTPUSER!=null || SFTPPASS!=null || SFTPWORKINGDIR!=null)
		{
			Session session = null ;
			Channel channel = null ;
			ChannelSftp channelSftp = null ; 
			
			try 
			{
			    JSch jsch = new JSch();
		        session = jsch.getSession(SFTPUSER, SFTPHOST, SFTPPORT);
		        session.setPassword(SFTPPASS);
		        java.util.Properties config = new java.util.Properties();
		        config.put("StrictHostKeyChecking", "no");
		        session.setConfig(config);
		        session.connect();
		        log.info("Host connected.");
		        channel = session.openChannel("sftp");
		        channel.connect();
		        log.info("sftp channel opened and connected.");
		        channelSftp = (ChannelSftp) channel;
		        channelSftp.cd(SFTPWORKINGDIR);
		        channelSftp.put(new FileInputStream(MainFile), MainFile.getName());
		        channelSftp.put(new FileInputStream(eAdviceFile), eAdviceFile.getName());
		        log.info("File transfered successfully to host.");
			} 
			catch (Exception ex) 
			{
				 log.warning("Exception found while tranfer the response.");
		    }
	    
			finally
			{
		        channelSftp.exit();
		        log.info("sftp Channel exited.");
		        channel.disconnect();
		        log.info("Channel disconnected.");
		        session.disconnect();
		        log.info("Host Session disconnected.");
		    }
			if(!isSalary)
				sendUploadEmail(conf);
		}
		return true;
		
	}

	private void sendUploadEmail(MDSB2BConfiguration conf) throws IOException 
	{
		int PrintFormatID = 0 ;
		MClient client = MClient.get(getCtx());
		MMailText mText = new MMailText(getCtx(), conf.getR_MailText_ID(), get_TrxName());
		int B2BEmailConfID = DB.getSQLValue(get_TrxName(), "select DS_B2B_EmailConf_ID from DS_B2B_EmailConf Where ad_org_id = ? ", Env.getAD_Org_ID(getCtx()));
		MDSB2BEmailConf Emailconf = new MDSB2BEmailConf(getCtx(), B2BEmailConfID, get_TrxName());
		String toEmailIDs = MSysConfig.getValue("B2B_Default_EmailToID","gaurav@shaik.net");
		EMail email = client.createEMail(toEmailIDs, null, null);
		List<MDSB2BEmailConf> contacts=new Query(getCtx(), I_DS_B2B_EmailConf.Table_Name, 
					" AD_Org_ID = ? ", get_TrxName()).
					setParameters(Env.getAD_Org_ID(getCtx())).
					setOnlyActiveRecords(true).list();
			for(MDSB2BEmailConf contact : contacts)
			{
				MUser user = new MUser(getCtx(), contact.getAD_User_ID(), get_TrxName());
				email.addTo(user.getEMail());
				mText.setPO(Emailconf);
				mText.setUser(user);
				mText.saveEx();
			}
		if (!email.isValid())
		{
			StringBuilder msglog = new StringBuilder("@RequestActionEMailError@ Invalid EMail: ").append(toEmailIDs);
			log.severe(" Email sent failed "+msglog);
		}
		PrintFormatID = MSysConfig.getIntValue("B2B_Default_PrintFormat", 1000129);
		PrintFormatID = conf.getAD_PrintFormat_ID();
		MQuery query = new MQuery("DSI_ExportPayments");
		query.addRestriction("DSI_ExportPayments_ID", MQuery.EQUAL, export.getDSI_ExportPayments_ID());
		if(PrintFormatID>0)
		{
			MPrintFormat format = MPrintFormat.get (getCtx(),PrintFormatID , false);
			PrintInfo info = new PrintInfo("B2B file Transfer",MDSIExportPayments.Table_ID,export.getDSI_ExportPayments_ID(),0);
			ReportEngine re = null;
			if (format != null)
				re = new ReportEngine(getCtx(), format, query, info);
			
			File attachment = re.getPDF(File.createTempFile("VendorPayments", ".pdf"));
			StringBuilder msglog = new StringBuilder().append(toEmailIDs.toString()).append(" - ").append(attachment);
			if (log.isLoggable(Level.FINE)) log.fine(msglog.toString());
			email.addAttachment(attachment);
			
			
			String message = mText.getMailText(true);
			if (mText.isHtml())
				email.setMessageHTML(mText.getMailHeader(), message);
			else
			{
				email.setSubject (mText.getMailHeader());
				email.setMessageText (message);
			}
			//
			//
			String msg = email.send();
			for(MDSB2BEmailConf contact : contacts)
			{
				MUserMail um = new MUserMail(mText, contact.getAD_User_ID(), email);
				um.saveEx();
			}
			log.info("Email sent with the subject "+msg);
		}
	}

	private void updatePayments() 
	{
		String sql="select c_payment_id from DSI_ExportVendorPayment where DSI_EXPORTPAYMENTS_ID="+exportBatch_ID;
		pstmt = DB.prepareStatement(sql, get_TrxName());
		try {
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				int paymentID = rs.getInt(1);
				MPayment mp=new MPayment(getCtx(), paymentID, get_TrxName());
				mp.set_ValueOfColumn("DSI_IsExported", true);
				mp.saveEx();
			}
		}
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql, e);
		} 
		finally 
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
	}

}
