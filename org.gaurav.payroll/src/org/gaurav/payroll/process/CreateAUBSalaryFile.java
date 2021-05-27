package org.gaurav.payroll.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttachment;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSB2BConfiguration;
import org.gaurav.payroll.model.MGSHRMonthlySalary;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class CreateAUBSalaryFile extends SvrProcess
{
	int MonthlySalary_ID = 0 ;
	MGSHRMonthlySalary sal = null;
	int C_BankAccount_ID = 0 ; 
	String fileName = "";
	String FileName;
	String orgName = "";
	File TextFile;
	PrintWriter pw;
	String S1 = "";
	String S2 = "";
	StringBuilder S3 = new StringBuilder("");
	static String LINE_Seperator = "\r\n";
	int p_GS_HR_Employee_ID = 0 ; 
	int p_C_BP_BankAccount_ID = 0 ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("GS_HR_Employee_ID"))
				p_GS_HR_Employee_ID = para[i].getParameterAsInt();	
			else if (name.equals("C_BP_BankAccount_ID"))
				p_C_BP_BankAccount_ID = para[i].getParameterAsInt();	
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
		MonthlySalary_ID = getRecord_ID();
		sal = new MGSHRMonthlySalary(getCtx(), MonthlySalary_ID, get_TrxName());	
	}

	@Override
	protected String doIt() throws Exception 
	{
		int AD_Org_ID = sal.getAD_Org_ID();
		C_BankAccount_ID = DB.getSQLValue(get_TrxName(), "Select C_BankAccount_ID From C_BankAccount Where DS_IsSalaryBankAccount='Y' and AD_Org_ID = ? ",AD_Org_ID);
		orgName = DB.getSQLValueString(get_TrxName(), "Select OrgName From AD_OrgInfo Where AD_Org_ID = ? ", AD_Org_ID);
		fileName = createSalaryTransfer();
		return "@Generated@";
	}

	private String createSalaryTransfer() throws IOException, SQLException 
	{
		createFiles();
		prepareSalaryDetails();
		return postProcessFiles();
	}
	
	private void createFiles() throws IOException 
	{
		String FilePath=System.getProperty("java.io.tmpdir");
		File directory = new File(FilePath);
		
		FilePath = directory+File.separator+orgName+"_SAL";
		
		String dateTime = DB.getSQLValueString(get_TrxName(), "select TO_CHAR(now(), 'DDMMYYYYHH24MISS') ");
		TextFile = new File(FilePath+"_"+dateTime+".txt");
		
		FileName=FilePath+"_"+dateTime+".txt";
		
		addLog("File Name: "+FileName);
		// (works for both Windows and Linux)
		TextFile.createNewFile();
	
		pw = new PrintWriter(new FileWriter(TextFile));
	}
	
	private void prepareSalaryDetails() throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder("select distinct 'S1,',Substr(info.DUNS,1,7)||',',bank.ACCOUNTNO||',','MXD'||',','1,',',',TO_CHAR(now(),'DD-Mon-YY')||',' ,TO_CHAR(now(), 'DDMMYYYYHH24MISS') "
				+ "from AD_ORG org,AD_OrgInfo info,C_BankAccount bank "
				+ "where bank.AD_ORG_ID=org.AD_ORG_ID and org.AD_ORG_ID=info.AD_ORG_ID and bank.c_bankaccount_id=? ");

		pstmt = DB.prepareStatement(sql.toString(),get_TrxName());
		try {
			pstmt.setInt(1, C_BankAccount_ID);
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
				pw.append(LINE_Seperator);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			rs = null;
		}
		int totalCount = 0 ;
		BigDecimal totalSalaryPaid = Env.ZERO;
		sql = new StringBuilder("select 'S2,',bpacc.dsi_transfermethod ||',' as TransferMethod,empmnth.gs_hr_netamt as net_amount ,','||cc.iso_code||',' as Currency ,',' as ExchangeRate,',' as DealRefNo,',' as PrefDate,',' as DebitAccount,"
				+ "coalesce(bpacc.AccountNo,bpacc.iban)||',' as EmpAccountNo,substr(generate_uuid(),1,30) as UniqueTransRef,"
				+ "trim(coalesce(SUBSTR(mnth.Description,1,35),''))||',' DBNar1, ',' DBNar2,"
				+ "	trim(coalesce(SUBSTR(mnth.Description,1,35),''))||',' CRNar,'SAL,' as PaymentDetail1,trim(coalesce(SUBSTR(mnth.Description,1,35),''))||',' as PaymentDetail12,trim(coalesce(SUBSTR(mnth.Description,36,70),''))||',' as PaymentDetail3,','PaymentDetail4,"
				+ "	SUBSTR(bpacc.A_Name,1,35)||',' as BusinessPartner,coalesce(SUBSTR(bpacc.A_Street,1,35),'')||',' as Adress1,trim(coalesce(SUBSTR(bpacc.A_City,1,35),''))||',' as Adress2,bank.name||',' as BankName,',',"
				+ "	',',',',coalesce(bank.SwiftCode,bpacc.swiftcode) ||',' as SwiftCode ,',' IntermediateAccount,',' IntermediatSwift,','IntermediateName,',' IntermediateAd1,"
				+ "',' IntermediateAd2,',' IntermediateAd3,'A,' ChargeType,',' SortCode,',' BIC,bp.C_BPartner_ID "
				+ "	from GS_HR_MonthlySalary mnth , GS_HR_EmployeeMonthlySalary empmnth, GS_HR_Employee emp, C_BPartner bp,C_BP_BankAccount bpacc,c_bank bank,"
				+ "	C_Currency cc "
				+ "	where mnth.GS_HR_MonthlySalary_ID=empmnth.GS_HR_MonthlySalary_ID "
				+ "	and empmnth.gs_hr_employee_id =emp.gs_hr_employee_id"
				+ "	and bp.c_Bpartner_id = emp.c_Bpartner_id	"
				+ " and bp.c_bpartner_id=bpacc.c_bpartner_id"
				+ "	and bpacc.c_bank_id=bank.c_bank_id "
				+ " and bpacc.C_Currency_ID=cc.C_Currency_ID"
				+ "	and empmnth.IsActive='Y' "
				+ " and mnth.GS_HR_MonthlySalary_ID = ? "
				+ " and bpacc.gs_hr_salaryaccount ='Y' "
				+ " and empmnth.gs_hr_netamt>0 ");
		if(p_GS_HR_Employee_ID>0)
				sql.append(" and empmnth.GS_HR_Employee_ID = ? ");
		
		if(p_C_BP_BankAccount_ID>0)
			sql.append(" and bpacc.C_BP_BankAccount_ID = ? ");
		
		sql.append(" order by BankName,BusinessPartner");
		PreparedStatement dPstmt = null;
		ResultSet dRs = null;
		dPstmt = DB.prepareStatement(sql.toString(), get_TrxName());
		try 
		{
			dPstmt.setInt(1,MonthlySalary_ID);
			if(p_GS_HR_Employee_ID>0)
				dPstmt.setInt(2,p_GS_HR_Employee_ID);
			if(p_C_BP_BankAccount_ID>0)
				dPstmt.setInt(3,p_C_BP_BankAccount_ID);
			dRs = dPstmt.executeQuery();
			while (dRs.next()) 
			{
				int C_BPartner_ID = dRs.getInt("C_BPartner_ID");
				StringBuilder countOfBusinessPartnerAccounts = new StringBuilder("select count(bpacc.*),bp.c_bpartner_id " + 
						"from GS_HR_Employee emp, C_BPartner bp,C_BP_BankAccount bpacc " + 
						"where emp.c_bpartner_id = bp.c_bpartner_id " + 
						"and bp.c_bpartner_id = bpacc.c_bpartner_id " + 
						"and bpacc.gs_hr_salaryaccount ='Y' " + 
						"and bpacc.IsActive ='Y' " + 
						"and bpacc.C_BPartner_ID =? ");
				countOfBusinessPartnerAccounts.append(" group by bp.c_bpartner_id ");
				int totalBankAccountNo = DB.getSQLValue(get_TrxName(), countOfBusinessPartnerAccounts.toString(),C_BPartner_ID);
				S2 = dRs.getString(1); // Section Index
				S2 = S2.concat(dRs.getString(2)); // Transfer Method
				DecimalFormat df = new DecimalFormat("#.000");
				BigDecimal Amount=dRs.getBigDecimal(3);
				BigDecimal dividedAmt = Amount.divide(new BigDecimal(totalBankAccountNo), 3, RoundingMode.HALF_UP);
				String roundedAmt=df.format(dividedAmt);
//				System.out.println(roundedAmt);
				String uniqueReference = UUID.randomUUID().toString().substring(1, 30);
				S2 = S2.concat(roundedAmt); // Credit Amount
				S2 = S2.concat(dRs.getString(4)); // Credit Currency
				S2 = S2.concat(dRs.getString(5)); // Exchange Rate
				S2 = S2.concat(dRs.getString(6)); // Deal Ref No
				S2 = S2.concat(dRs.getString(7)); // Preferred Value Date
				S2 = S2.concat(dRs.getString(8)); // Debit Account Number
				S2 = S2.concat(dRs.getString(9)); // Beneficiary Account Number
				S2 = S2.concat(uniqueReference+","); // Unique Transaction Ref No
				S2 = S2.concat(dRs.getString(11)); // Debit Narrative 1
				S2 = S2.concat(dRs.getString(12)); // Debit Narrative 2
				S2 = S2.concat(dRs.getString(13)); // Credit Narrative
				S2 = S2.concat(dRs.getString(14)); // Payment Details Line 1
				S2 = S2.concat(dRs.getString(15)); // Payment Details Line 2
				S2 = S2.concat(dRs.getString(16)); // Payment Details Line 3
				S2 = S2.concat(dRs.getString(17)); // Payment Details Line 4
				S2 = S2.concat(dRs.getString(18)); // Beneficiary Name
				S2 = S2.concat(dRs.getString(19)); // Beneficiary Address Line 1
				S2 = S2.concat(dRs.getString(20)); // Beneficiary Address Line 2
				S2 = S2.concat(dRs.getString(21)); // Beneficiary Bank Name
				S2 = S2.concat(dRs.getString(22)); // Beneficiary Bank Address 1
				S2 = S2.concat(dRs.getString(23)); // Beneficiary Bank Address 2
				S2 = S2.concat(dRs.getString(24)); // Beneficiary Bank Address 3
				S2 = S2.concat(dRs.getString(25)); // SWIFT Code
				S2 = S2.concat(dRs.getString(26)); // Intermediary Account
				S2 = S2.concat(dRs.getString(27)); // Intermediary Swift Code
				S2 = S2.concat(dRs.getString(28)); // Intermediary Name
				S2 = S2.concat(dRs.getString(29)); // Intermediary Address 1
				S2 = S2.concat(dRs.getString(30)); // Intermediary Address 2
				S2 = S2.concat(dRs.getString(31)); // Intermediary Address 3
				S2 = S2.concat(dRs.getString(32)); // Charges Type
				S2 = S2.concat(dRs.getString(33)); // Sort Code
				S2 = S2.concat(dRs.getString(34)); // BIC
//				S2 = S2.concat(ors.getString(35)); // ABA Routing Number
				pw.write(S2);
				pw.append(LINE_Seperator);
				totalCount++;
				totalSalaryPaid = totalSalaryPaid.add(dividedAmt);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, sql.toString(), e);
		} finally {
			dRs = null;
		}
		
		DecimalFormat df = new DecimalFormat("#.000");
		String roundedAmt=df.format(totalSalaryPaid);
		S3.append("S3,").append(totalCount).append(",").append(roundedAmt);
		pw.write(S3.toString());
		pw.append(LINE_Seperator);
		
		pw.close();
	}

	private String postProcessFiles() throws IOException 
	{
		MAttachment maa = new MAttachment(getCtx(), getTable_ID(),getRecord_ID(), get_TrxName());
		if (maa.get_ID()<=0) {
			MAttachment ma = new MAttachment(getCtx(), 0, get_TrxName());
			ma.setRecord_ID(getRecord_ID());
			ma.addEntry(TextFile);
				ma.set_TrxName(get_TrxName());
			ma.setAD_Table_ID(getTable_ID());
			ma.addTextMsg("Added new Text File" + FileName);
			ma.saveEx();
		} else {	
			maa.addEntry(TextFile);
			maa.set_TrxName(get_TrxName());
			maa.addTextMsg("Added new Text File" + FileName);
			maa.saveEx();
		}
		boolean uploadedSuccessfully = UploadToSFTP(TextFile);
		return "File Created : -" + FileName + " E Advice created : -"+ " File uploaded "+ uploadedSuccessfully;
	}
	
	private boolean UploadToSFTP(File MainFile) throws IOException 
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
		        log.info("File transfered successfully to host.");
			} 
			catch (Exception ex) 
			{
				 throw new AdempiereException("Exception found while tranfer the response.");
		    }
	    
			finally
			{
				if(channelSftp!=null)
				{
					channelSftp.exit();
		        	log.info("sftp Channel exited.");
				}
				if(channel!=null)
				{
			        channel.disconnect();
			        log.info("Channel disconnected.");
				}
				if(session!=null)
				{
			        session.disconnect();
			        log.info("Host Session disconnected.");
				}
		    }
		}
		return true;
		
	}
	
}
