package org.gaurav.payroll.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;

import org.compiere.apps.IStatusBar;
import org.compiere.grid.CreateFrom;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCharge;
import org.compiere.model.MClient;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.gaurav.payroll.model.MGSHREmpCompensation;
import org.gaurav.payroll.model.MGSHREmployee;
import org.gaurav.payroll.model.MGSHRInstallments;

public class WCreateLoanGLEntries extends CreateFrom{

	public WCreateLoanGLEntries(GridTab gridTab) {
		super(gridTab);
		// TODO Auto-generated constructor stub
	}

	public boolean dynInit() throws Exception
	{
		log.config("");
		setTitle(Msg.getElement(Env.getCtx(), "GL_Journal_ID") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
		
		return true;
	}
	
	protected Vector<Vector<Object>> getBankAccountData(Object BPartner, Object DateFrom, Object DateTo, Object AmtFrom, Object AmtTo)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.GS_HR_Employee_ID,e.Name,i.PayDate,i.GS_HR_Installments_ID,a.DocumentNo,I.GS_InstallmentAmt,"
				+ "i.RemainingAmt,i.HR_Break,mst.C_Charge_ID,mst.name ");
		sql.append("FROM GS_HR_EmployeeAdvance a,GS_HR_Installments i,GS_HR_Employee e,GS_HR_Compensation_Master mst ");
		sql.append("WHERE a.GS_HR_EmployeeAdvance_ID=i.GS_HR_EmployeeAdvance_ID ");
		sql.append("AND a.GS_HR_Employee_ID=e.GS_HR_Employee_ID ");
		sql.append("AND a.GS_HR_Compensation_Master_ID = mst.GS_HR_Compensation_Master_ID ");
		sql.append("AND i.GS_HR_Installments_ID NOT IN (Select coalesce(GS_HR_Installments_ID,0) From GL_JournalLine) ");
		sql.append(getSQLWhere(BPartner,DateFrom, DateTo, AmtFrom, AmtTo));
		sql.append(" ORDER BY e.Name");

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			setParameters(pstmt, BPartner,  DateFrom, DateTo, AmtFrom, AmtTo);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Vector<Object> line = new Vector<Object>(6);
				line.add(new Boolean(true));       //  0-Selection
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2));
				line.add(pp);      	//  1-Employee Name
				line.add(rs.getTimestamp(3));       //  2-Installment Date
				pp = new KeyNamePair(rs.getInt(4), rs.getString(5));
				line.add(pp);                       //  3-GS_HR_Installments_ID
				line.add(rs.getBigDecimal(6));      //  4-Installment Amount
				line.add(rs.getBigDecimal(6));      //  5-Installment Amount
				line.add(rs.getBigDecimal(7));      //  6-Remaining Amount after this installement.
				line.add(rs.getString(8));      	//  7-Break
				pp = new KeyNamePair(rs.getInt(9), rs.getString(10));
				line.add(pp);
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return data;
	}
	
		public boolean save(IMiniTable miniTable, String trxName)
	{
		//  fixed values
		int GL_Journal_ID = ((Integer) getGridTab().getValue("GL_Journal_ID")).intValue();
		MJournal journal = new MJournal (Env.getCtx(), GL_Journal_ID, trxName);
		if (log.isLoggable(Level.CONFIG)) log.config(journal.toString());
		MClient client = MClient.get(Env.getCtx());
		MAcctSchema as = client.getAcctSchema();
		//  Lines
		for(int i = 0; i < miniTable.getRowCount(); i++)
		{
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				KeyNamePair pp = (KeyNamePair) miniTable.getValueAt(i, 1);   //  1-GS_HR_Employee
				int GS_HR_Employee_ID = pp.getKey();
				MGSHREmployee employee = new MGSHREmployee(Env.getCtx(), GS_HR_Employee_ID, trxName);
				
				Timestamp trxDate = (Timestamp) miniTable.getValueAt(i, 2);  //  2-DateTrx
				pp = (KeyNamePair) miniTable.getValueAt(i, 3);               //  3-Installement ID
				int GS_HR_Installments_ID = pp.getKey();
				
				MGSHRInstallments inst = new MGSHRInstallments(Env.getCtx(), GS_HR_Installments_ID, trxName);
				BigDecimal installmentAMt = (BigDecimal) miniTable.getValueAt(i, 4); //  4- Installments Amt
				BigDecimal loanRepaymentAmt = (BigDecimal) miniTable.getValueAt(i, 5); //  4- Installments Amt
				BigDecimal difference = installmentAMt.subtract(loanRepaymentAmt);
				if(difference.compareTo(Env.ZERO)!=0)
				{
					int next_Installment_ID = DB.getSQLValue(trxName, "Select GS_HR_Installments_ID "
							+ "From GS_HR_Installments Where PayDate> ? and GS_HR_EmployeeAdvance_ID=? order by PayDate ",inst.getPayDate(),inst.getGS_HR_EmployeeAdvance_ID());
					MGSHRInstallments adjustment = new MGSHRInstallments(Env.getCtx(), next_Installment_ID, trxName);
					adjustment.setGS_InstallmentAmt(adjustment.getGS_InstallmentAmt().add(difference));
					adjustment.set_ValueNoCheck("Description", adjustment.get_Value("Description")+" added from "+inst.getPayDate()+" / "+difference);
					adjustment.saveEx();
				}
				pp = (KeyNamePair) miniTable.getValueAt(i, 8);               //  7-Compensation
				int C_Charge_ID = pp.getKey();
				MAccount account = MCharge.getAccount(C_Charge_ID, as);
				int lineNo = getLineNumber(journal);
				if (log.isLoggable(Level.FINE)) log.fine("Line Date=" + trxDate
					+ ", Employee=" + employee.getC_BPartner_ID() + ", GS_HR_Installments_ID =" + GS_HR_Installments_ID + ", Amt=" + installmentAMt);
				//	
				MJournalLine crLine = new MJournalLine (journal);
				crLine.setDateAcct(journal.getDateAcct());
				crLine.setC_BPartner_ID(employee.getC_BPartner_ID());
				crLine.setAmtSourceCr(loanRepaymentAmt);
				crLine.setAccount_ID(account.getAccount_ID());
				crLine.setDescription("Loan deduction for the month: "+journal.getC_Period().getName());
				crLine.setC_Activity_ID(employee.getC_Activity_ID());
				crLine.set_ValueNoCheck("GS_HR_Installments_ID",GS_HR_Installments_ID);
				crLine.setLine(lineNo);
				if (!crLine.save())
					log.log(Level.SEVERE, "Line not created #" + i);
				
				MGSHREmpCompensation basicSalary = employee.getBasicSalary();
				C_Charge_ID = basicSalary.getGS_HR_Compensation_Master().getC_Charge_ID();
				account = MCharge.getAccount(C_Charge_ID, as);
				lineNo = getLineNumber(journal);
				
				MJournalLine drLine = new MJournalLine (journal);
				drLine.setDateAcct(journal.getDateAcct());
				drLine.setC_BPartner_ID(employee.getC_BPartner_ID());
				drLine.setAmtSourceDr(loanRepaymentAmt);
				drLine.setAccount_ID(account.getAccount_ID());
				drLine.setDescription("Loan deduction for the month: "+journal.getC_Period().getName());
				drLine.setC_Activity_ID(employee.getC_Activity_ID());
				drLine.setLine(lineNo);
				drLine.set_ValueNoCheck("GS_HR_Installments_ID",GS_HR_Installments_ID);
				if (!drLine.save())
					log.log(Level.SEVERE, "Line not created #" + i);
			}   //   if selected
		}   //  for all rows
		return true;
	}   //  save
	
	private int getLineNumber(MJournal journal) 
	{
		int line = DB.getSQLValue(journal.get_TrxName(), "Select coalesce(max(Line),0)+10 From GL_JournalLine Where GL_Journal_ID = ? ",journal.getGL_Journal_ID());
		
		return line;
	}

	protected Vector<String> getOISColumnNames()
	{
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "GS_HR_Employee_ID"));
		columnNames.add(Msg.getElement(Env.getCtx(), "PayDate"));
		columnNames.add(Msg.translate(Env.getCtx(), "GS_HR_Installments_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "GS_InstallmentAmt"));
		columnNames.add(Msg.translate(Env.getCtx(), "PayAmt"));
		columnNames.add(Msg.translate(Env.getCtx(), "RemainingAmt"));
		columnNames.add(Msg.translate(Env.getCtx(), "HR_Break"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Charge_ID"));
	    
	    return columnNames;
	}

	protected void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.setColumnClass(0, Boolean.class, false);     // 0-Selection
		miniTable.setColumnClass(1, String.class, false);    //  1-Employee Name
		miniTable.setColumnClass(2, Timestamp.class, true);       //  2-Installment Date
		miniTable.setColumnClass(3, String.class, true);         //  3-GS_HR_Installments_ID
		miniTable.setColumnClass(4, BigDecimal.class, true);    //  4-Installment Amount
		miniTable.setColumnClass(5, BigDecimal.class, false);    //  5-Pay Amt
		miniTable.setColumnClass(6, BigDecimal.class, true);    //  6-Remaining Amount after this installement.
		miniTable.setColumnClass(7, String.class, true);    	//  7-Break
		miniTable.setColumnClass(8, String.class, true);    	//  8-Compensation
		//  Table UI
		miniTable.autoSize();
	}
	
	@Override
	public Object getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void info(IMiniTable miniTable, IStatusBar statusBar) 
	{
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);

		BigDecimal total = new BigDecimal(0.0);
		BigDecimal PayAmt = new BigDecimal(0.0);
		int rows = miniTable.getRowCount();
		int count = 0;
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)miniTable.getValueAt(i, 0)).booleanValue())
			{
				PayAmt = PayAmt.add((BigDecimal)miniTable.getValueAt(i, 5));
				count++;
			}
		}
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)miniTable.getValueAt(i, 0)).booleanValue())
			{
				total = total.add((BigDecimal)miniTable.getValueAt(i, 4));
				count++;
			}
		}
		statusBar.setStatusLine(count+" - "+" Installment Amt: "+"  " + format.format(total)+" Payment Amt: "+"  " + format.format(total));
		
	}
	
	public String getSQLWhere(Object BPartner, Object DateFrom, Object DateTo, Object AmtFrom, Object AmtTo)
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" AND IsPaid='N' AND i.GS_InstallmentAmt<>0"); 
	
		if(BPartner != null)
			sql.append(" AND e.C_BPartner_ID=?");
		
		if(AmtFrom != null || AmtTo != null)
		{
			BigDecimal from = (BigDecimal) AmtFrom;
			BigDecimal to = (BigDecimal) AmtTo;
			if(from == null && to != null)
				sql.append(" AND i.GS_InstallmentAmt <= ?");
			else if(from != null && to == null)
				sql.append(" AND i.GS_InstallmentAmt >= ?");
			else if(from != null && to != null)
				sql.append(" AND i.GS_InstallmentAmt BETWEEN ? AND ?");
		}
		
		if(DateFrom != null || DateTo != null)
		{
			Timestamp from = (Timestamp) DateFrom;
			Timestamp to = (Timestamp) DateTo;
			if(from == null && to != null)
				sql.append(" AND TRUNC(i.PayDate) <= ?");
			else if(from != null && to == null)
				sql.append(" AND TRUNC(i.PayDate) >= ?");
			else if(from != null && to != null)
				sql.append(" AND TRUNC(i.PayDate) BETWEEN ? AND ?");
		}

		if (log.isLoggable(Level.FINE)) log.fine(sql.toString());
		return sql.toString();
	}
	
	void setParameters(PreparedStatement pstmt, Object BPartner, Object DateFrom, Object DateTo, 
			Object AmtFrom, Object AmtTo)
	throws SQLException
	{
		int index = 1;
		
		if(BPartner != null)
			pstmt.setInt(index++, (Integer) BPartner);
		
		if(AmtFrom != null || AmtTo != null)
		{
			BigDecimal from = (BigDecimal) AmtFrom;
			BigDecimal to = (BigDecimal) AmtTo;
			if (log.isLoggable(Level.FINE)) log.fine("Amt From=" + from + ", To=" + to);
			if(from == null && to != null)
				pstmt.setBigDecimal(index++, to);
			else if(from != null && to == null)
				pstmt.setBigDecimal(index++, from);
			else if(from != null && to != null)
			{
				pstmt.setBigDecimal(index++, from);
				pstmt.setBigDecimal(index++, to);
			}
		}
		
		if(DateFrom != null || DateTo != null)
		{
			Timestamp from = (Timestamp) DateFrom;
			Timestamp to = (Timestamp) DateTo;
			if (log.isLoggable(Level.FINE)) log.fine("Date From=" + from + ", To=" + to);
			if(from == null && to != null)
				pstmt.setTimestamp(index++, to);
			else if(from != null && to == null)
				pstmt.setTimestamp(index++, from);
			else if(from != null && to != null)
			{
				pstmt.setTimestamp(index++, from);
				pstmt.setTimestamp(index++, to);
			}
		}
	}
}
