package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MConversionRate;
import org.compiere.model.MCurrency;
import org.compiere.model.MRole;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.gaurav.dsi.model.MDSIAging;

public class DSAging extends SvrProcess {
	private Timestamp p_StatementDate = null;
	private boolean p_DateAcct = false;
	private boolean p_IsSOTrx = false;
	private int p_C_BPartner_ID = 0;
	private boolean p_IsListInvoices = false;
	/** Number of days between today and statement date */
	private int m_statementOffset = 0;

	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("StatementDate"))
				p_StatementDate = (Timestamp) para[i].getParameter();
			else if (name.equals("DateAcct"))
				p_DateAcct = "Y".equals(para[i].getParameter());
			else if (name.equals("IsSOTrx"))
				p_IsSOTrx = "Y".equals(para[i].getParameter());
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = ((BigDecimal) para[i].getParameter())
						.intValue();
			else if (name.equals("IsListInvoices"))
				p_IsListInvoices = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		if (p_StatementDate == null)
			p_StatementDate = new Timestamp(System.currentTimeMillis());
		else
			m_statementOffset = TimeUtil.getDaysBetween(
					new Timestamp(System.currentTimeMillis()), p_StatementDate);

	}

	@Override
	protected String doIt() throws Exception {
		deleteAllDate();
		log.info("StatementDate=" + p_StatementDate + ", IsSOTrx=" + p_IsSOTrx
				+ ", IsListInvoices=" + p_IsListInvoices);
		// FR 1933937
		String dateacct = DB.TO_DATE(p_StatementDate);

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bp.C_BP_Group_ID, oi.C_BPartner_ID,oi.C_Invoice_ID, " // 1..4
				+ "oi.C_Currency_ID, oi.IsSOTrx, " // 5..6
				+ "oi.DateInvoiced, oi.NetDays,oi.DueDate,oi.DaysDue, "); // 7..10
		if (!p_DateAcct)// FR 1933937
		{
			sql.append(" oi.GrandTotal, oi.PaidAmt, oi.OpenAmt "); // 11..13
		} else {
			sql.append(" oi.GrandTotal, invoicePaidToDate(oi.C_Invoice_ID, oi.C_Currency_ID, 1,"
					+ dateacct
					+ ") AS PaidAmt, invoiceOpenToDate(oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID,"+ dateacct + ") AS OpenAmt "); // 11..13
		}
		sql.append(",oi.C_Activity_ID,oi.C_Campaign_ID,oi.C_Project_ID,oi.AD_Org_ID,oi.c_conversiontype_id,"
				+ "currencybase(invoiceOpenToDate(oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID,"+ dateacct + "), oi.C_Currency_ID, oi.DateInvoiced, oi.AD_Client_ID,oi.AD_org_ID) as BaseOpenAmt "); // 14..17
		if (!p_DateAcct)// FR 1933937
		{
			sql.append(" FROM RV_OpenItem oi");
		} else {
			sql.append(" FROM RV_OpenItemToDate oi");
		}

		sql.append(
				" INNER JOIN C_BPartner bp ON (oi.C_BPartner_ID=bp.C_BPartner_ID) "
						+ "WHERE oi.ISSoTrx=")
				.append(p_IsSOTrx ? "'Y'" : "'N'");
		if (p_C_BPartner_ID > 0) {
			sql.append(" AND oi.C_BPartner_ID=").append(p_C_BPartner_ID);
		}

		if (p_DateAcct)// FR 1933937
		{
			sql.append(" AND invoiceOpenToDate(oi.C_Invoice_ID,oi.C_InvoicePaySchedule_ID,"
					+ dateacct + ") <> 0 ");
		}

		sql.append(" ORDER BY oi.C_BPartner_ID, oi.C_Currency_ID, oi.C_Invoice_ID");

		log.finest(sql.toString());
		String finalSql = MRole.getDefault(getCtx(), false).addAccessSQL(
				sql.toString(), "oi", MRole.SQL_FULLYQUALIFIED, MRole.SQL_RO);
		log.finer(finalSql);

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//
		int counter = 0;
		int rows = 0;
		try {
			pstmt = DB.prepareStatement(finalSql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MDSIAging dsa = new MDSIAging(getCtx(), 0, get_TrxName());
				int C_BP_Group_ID = rs.getInt(1);
				int C_BPartner_ID = rs.getInt(2);
				int C_Invoice_ID = p_IsListInvoices ? rs.getInt(3) : 0;
				int C_Currency_ID = rs.getInt(4);
				boolean IsSOTrx = "Y".equals(rs.getString(5));
				//
				Timestamp DateInvoiced = rs.getTimestamp(6);
				Timestamp DueDate = rs.getTimestamp(8);
				// Days Due
				int DaysDue = rs.getInt(9) // based on today
						+ m_statementOffset;
				//
				BigDecimal GrandTotal = rs.getBigDecimal(10);
				BigDecimal OpenAmt = rs.getBigDecimal(12);
				int AD_Org_ID = rs.getInt("AD_Org_ID");
				BigDecimal BaseOpenAmt = rs.getBigDecimal("BaseOpenAmt");
				rows++;
				dsa.setC_BPartner_ID(C_BPartner_ID);
				dsa.setC_BP_Group_ID(C_BP_Group_ID);
				dsa.setC_Invoice_ID(C_Invoice_ID);
				dsa.setC_Currency_ID(C_Currency_ID);
				dsa.setIsSOTrx(IsSOTrx);
				dsa.setInvoicedAmt(GrandTotal);
				dsa.setDaysDue(DaysDue);
				dsa.setAD_Org_ID(AD_Org_ID);
				dsa.setDueAmt(OpenAmt);
				dsa.setDueDate(DueDate);
				dsa.setStatementDate(p_StatementDate);
				dsa.setDateInvoiced(DateInvoiced);
				if (DaysDue <= 0) 
				{
					dsa.setPastDueAmt(OpenAmt);
					dsa.setbasepastdueamt(BaseOpenAmt);
				} 
				else
				if (DaysDue <= 30) {
						dsa.setDue0_30(OpenAmt);
						dsa.setbasedueupto30(BaseOpenAmt);
					}
					if (DaysDue >= 31 && DaysDue <= 60) {
						dsa.setDue31_60(OpenAmt);
						dsa.setbasedue3160(BaseOpenAmt);
					}
					if (DaysDue >= 61 && DaysDue <= 90) {
						dsa.setDue61_90(OpenAmt);
						dsa.setbasedue6190(BaseOpenAmt);
					}
					if (DaysDue >= 91 && DaysDue <= 120) {
						dsa.setDue91_120(OpenAmt);
						dsa.setbasedue91120(BaseOpenAmt);
					}
					if (DaysDue >= 121 && DaysDue <= 150) {
						dsa.setDue121_150(OpenAmt);
						dsa.setbasedue121150(BaseOpenAmt);
					}
					if (DaysDue >= 151 && DaysDue <= 180) {
						dsa.setDue151_180(OpenAmt);
						dsa.setbasedue151180(BaseOpenAmt);
					}
					if (DaysDue >= 181 && DaysDue <= 210) {
						dsa.setDue181_210(OpenAmt);
						dsa.setbasedue181210(BaseOpenAmt);
					}
					if (DaysDue >= 211 && DaysDue <= 241) {
						dsa.setDue211_240(OpenAmt);
						dsa.setbasedue211240(BaseOpenAmt);
					}
					if (DaysDue >= 241 && DaysDue <= 270) {
						dsa.setDue241_270(OpenAmt);
						dsa.setbasedue241270(BaseOpenAmt);
					}
					if (DaysDue >= 271 && DaysDue <= 300) {
						dsa.setDue271_300(OpenAmt);
						dsa.setbasedue271300(BaseOpenAmt);
					}
					if (DaysDue >= 301 && DaysDue <= 330) {
						dsa.setDue301_330(OpenAmt);
						dsa.setbasedue301330(BaseOpenAmt);
					}
					if (DaysDue >= 331 && DaysDue <= 365) {
						dsa.setDue331_365(OpenAmt);
						dsa.setbasedue331365(BaseOpenAmt);
					}
					if (DaysDue >= 366 && DaysDue <= 425) {
						dsa.setDue366_425(OpenAmt);
						dsa.setbasedue366425(BaseOpenAmt);
					}
					if (DaysDue >= 426 && DaysDue <= 485) {
						dsa.setDue426_485(OpenAmt);
						dsa.setbasedue426485(BaseOpenAmt);
					}
					if (DaysDue >= 486 && DaysDue <= 545) {
						dsa.setDue486_545(OpenAmt);
						dsa.setbasedue486545(BaseOpenAmt);
					}
					if (DaysDue >= 546 && DaysDue <= 585) {
						dsa.setDue546_585(OpenAmt);
						dsa.setbasedue546585(BaseOpenAmt);
					}
					if (DaysDue >= 586 && DaysDue <= 645) {
						dsa.setDue586_645(OpenAmt);
						dsa.setbasedue586645(BaseOpenAmt);
					}
					if (DaysDue >= 646 && DaysDue <= 705) {
						dsa.setDue646_705(OpenAmt);
						dsa.setbasedue646705(BaseOpenAmt);
					}
					if (DaysDue >= 706 && DaysDue <= 766) {
						dsa.setDue706_766(OpenAmt);
						dsa.setbasedue706766(BaseOpenAmt);
					}
					if (DaysDue >= 767) {
						dsa.setDue766Plus(OpenAmt);
						dsa.setbasedue766plus(BaseOpenAmt);
					}

				dsa.setOpenAmt(OpenAmt);
				dsa.setbasetotal(BaseOpenAmt);
				dsa.save();
			}
		} catch (SQLException e) {
			throw new DBException(e, finalSql);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		//
		log.info("#" + counter + " - rows=" + rows);
		return "@OK@";
	}
	private void deleteAllDate() {
		PreparedStatement pstmt = null;
		String sql = "delete from DSI_aging";
		pstmt = DB.prepareStatement(sql, get_TrxName());
		try 
		{
			pstmt.executeUpdate();
			commitEx();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
	}
}