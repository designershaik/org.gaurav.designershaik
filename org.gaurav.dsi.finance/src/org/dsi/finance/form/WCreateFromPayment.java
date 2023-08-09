package org.dsi.finance.form;

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
import org.compiere.model.MPayment;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.MDSSettledInvoices;

public class WCreateFromPayment extends CreateFrom{

	public WCreateFromPayment(GridTab gridTab) {
		super(gridTab);
		// TODO Auto-generated constructor stub
	}

	public boolean dynInit() throws Exception
	{
		log.config("");
		setTitle(Msg.getElement(Env.getCtx(), "C_Payment_ID") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
		
		return true;
	}
	
	protected Vector<Vector<Object>> getPaymentData( Object BPartner, String DocumentNo, 
			Object DateFrom, Object DateTo, Object AmtFrom, Object AmtTo, Object IsSoTrx)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select p.DateAcct,p.C_Invoice_ID,(p.DocumentNo||'-'||COALESCE(p.description,'')), p.C_Currency_ID,c.ISO_Code, p.GrandTotal,");
		sql.append("p.GrandTotal, bp.Name ");
		sql.append("FROM C_Invoice_v p INNER JOIN C_Currency c ON (p.C_Currency_ID=c.C_Currency_ID)"
				+ "  LEFT OUTER JOIN C_BPartner bp ON (p.C_BPartner_ID=bp.C_BPartner_ID)");
		sql.append(getSQLWhere(BPartner, DocumentNo, DateFrom, DateTo, AmtFrom, AmtTo, IsSoTrx));
		sql.append(" ORDER BY p.DateAcct Desc ");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			setParameters(pstmt, BPartner, DocumentNo, DateFrom, DateTo, AmtFrom, AmtTo, IsSoTrx);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Vector<Object> line = new Vector<Object>(6);
				line.add(new Boolean(false));       //  0-Selection
				line.add(rs.getTimestamp(1));       //  1-DateTrx
				KeyNamePair pp = new KeyNamePair(rs.getInt(2), rs.getString(3));
				line.add(pp);                       //  2-C_Invoice_ID
				pp = new KeyNamePair(rs.getInt(4), rs.getString(5));
				line.add(pp);                       //  3-Currency
				line.add(rs.getBigDecimal(6));      //  4-PayAmt
				line.add(rs.getBigDecimal(7));      //  5-Conv Amt
				line.add(rs.getString(8));      	//  6-BParner
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
	
	protected void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		miniTable.setColumnClass(1, Timestamp.class, false);    //  1-TrxDate / nmicoud - idempiere 240 Let user choose the 'Statement Line Date'
		miniTable.setColumnClass(2, String.class, true);        //  2-Payment
		miniTable.setColumnClass(3, String.class, true);        //  3-Currency
		miniTable.setColumnClass(4, BigDecimal.class, true);    //  4-Amount
		miniTable.setColumnClass(5, BigDecimal.class, true);    //  5-ConvAmount
		miniTable.setColumnClass(6, String.class, true);    	//  6-BPartner
		//  Table UI
		miniTable.autoSize();
	}

	public boolean save(IMiniTable miniTable, String trxName)
	{
		//  fixed values
		int C_Payment_ID = ((Integer) getGridTab().getValue("C_Payment_ID")).intValue();
		MPayment payment = new MPayment(Env.getCtx(), C_Payment_ID, trxName);
		//  Lines
		for(int i = 0; i < miniTable.getRowCount(); i++)
		{
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				KeyNamePair pp = (KeyNamePair) miniTable.getValueAt(i, 2);   //  2-C_Invoice_ID
				int C_Invoice_ID = pp.getKey();
				BigDecimal TrxAmt = (BigDecimal) miniTable.getValueAt(i, 5); //  5- Conv Amt
				int maxLine = DB.getSQLValue(trxName, "Select coalesce(max(line)+10,10) From DS_Settled_Invoices Where C_Payment_ID = ? ",C_Payment_ID);
				MDSSettledInvoices inv = new MDSSettledInvoices(Env.getCtx(), 0, trxName);
				inv.setC_Invoice_ID(C_Invoice_ID);
				inv.setC_Payment_ID(C_Payment_ID);
				inv.setLine(maxLine);
				inv.setTrxAmt(TrxAmt);
				inv.saveEx();
				
				MPayment newPayment = createReferencePayment(inv.getC_Invoice_ID(), payment.getDateAcct(), payment, inv.getTrxAmt(), 
						inv.getC_Invoice().getC_BPartner_ID(), inv.getLine(),inv.getC_Invoice().getDescription(), trxName);
				inv.setRef_Payment_ID(newPayment.getC_Payment_ID());
				inv.saveEx();
			}   //   if selected
		}   //  for all rows
		return true;
	}   //  save
	
	protected Vector<String> getOISColumnNames()
	{
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "Date"));
		columnNames.add(Msg.getElement(Env.getCtx(), "C_Invoice_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_Currency_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "Amount"));
		columnNames.add(Msg.translate(Env.getCtx(), "ConvertedAmount"));
		columnNames.add(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
	    
	    return columnNames;
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
		int rows = miniTable.getRowCount();
		int count = 0;
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)miniTable.getValueAt(i, 0)).booleanValue())
			{
				total = total.add((BigDecimal)miniTable.getValueAt(i, 5));
				count++;
			}
		}
		statusBar.setStatusLine(count+" - "+Msg.getMsg(Env.getCtx(), " Sum ") + "  " + format.format(total));
		
	}
	
	public String getSQLWhere(Object BPartner, String DocumentNo, Object DateFrom, Object DateTo, 
			Object AmtFrom, Object AmtTo, Object IsSoTrx)
	{
		StringBuilder sql = new StringBuilder();
		sql.append("WHERE p.Processed='Y' AND p.IsPaid='N'");
		sql.append(" AND p.DocStatus IN ('CO','CL','RE','VO') AND p.GrandTotal<>0"); 
	    sql.append(" AND NOT EXISTS (SELECT * FROM DS_Settled_Invoices l,C_Payment cp "
	    		+ "WHERE l.C_Payment_ID=cp.C_Payment_ID and cp.DocStatus='CO' and p.C_Invoice_ID=l.C_Invoice_ID)");
	    
	    if(IsSoTrx != null)
			sql.append(" AND p.isSOTrx = ? ");
	  	if(BPartner != null)
			sql.append(" AND p.C_BPartner_ID=?");
		
		if(DocumentNo.length() > 0)
			sql.append(" AND UPPER(p.DocumentNo) LIKE ?");
			
		if(AmtFrom != null || AmtTo != null)
		{
			BigDecimal from = (BigDecimal) AmtFrom;
			BigDecimal to = (BigDecimal) AmtTo;
			if(from == null && to != null)
				sql.append(" AND p.GrandTotal <= ?");
			else if(from != null && to == null)
				sql.append(" AND p.GrandTotal >= ?");
			else if(from != null && to != null)
				sql.append(" AND p.GrandTotal BETWEEN ? AND ?");
		}
		
		if(DateFrom != null || DateTo != null)
		{
			Timestamp from = (Timestamp) DateFrom;
			Timestamp to = (Timestamp) DateTo;
			if(from == null && to != null)
				sql.append(" AND TRUNC(p.DateAcct) <= ?");
			else if(from != null && to == null)
				sql.append(" AND TRUNC(p.DateAcct) >= ?");
			else if(from != null && to != null)
				sql.append(" AND TRUNC(p.DateAcct) BETWEEN ? AND ?");
		}
		sql.append(" AND p.AD_Client_ID = " + Env.getAD_Client_ID(Env.getCtx()));

		if (log.isLoggable(Level.FINE)) log.fine(sql.toString());
		return sql.toString();
	}
	
	void setParameters(PreparedStatement pstmt, Object BPartner, String DocumentNo, Object DateFrom, Object DateTo, 
			Object AmtFrom, Object AmtTo, Object IsSOTrx)
	throws SQLException
	{
		int index = 1;
		
		if(IsSOTrx != null)
			pstmt.setString(index++, IsSOTrx.toString());
		
		if(BPartner != null)
			pstmt.setInt(index++, (Integer) BPartner);
		
		if(DocumentNo.length() > 0)
			pstmt.setString(index++, getSQLText(DocumentNo));
		
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
	
	private String getSQLText(String text)
	{
		String s = text.toUpperCase();
		if(!s.endsWith("%"))
			s += "%";
		if (log.isLoggable(Level.FINE)) log.fine( "String=" + s);
		return s;
	}
	
	private MPayment createReferencePayment(int C_Invoice_ID,Timestamp dateInvoiced, MPayment payment, BigDecimal payAmt, int C_BPartner_ID,int line, String description, String trxName) 
	{
		MPayment newPayment = new MPayment(Env.getCtx(), 0, trxName);
		newPayment.setAD_Org_ID(payment.getAD_Org_ID());
		newPayment.setTenderType(payment.getTenderType());
		newPayment.setC_BankAccount_ID(payment.getC_BankAccount_ID());
		newPayment.setC_BPartner_ID(C_BPartner_ID);
		if(C_Invoice_ID>0)
			newPayment.setC_Invoice_ID(C_Invoice_ID);
		newPayment.setC_Currency_ID(payment.getC_Currency_ID());	
		newPayment.setC_DocType_ID(false);
		newPayment.setDocumentNo(payment.getDocumentNo().concat("-").concat(Integer.toString(line)));
		newPayment.setPayAmt(payAmt);
		newPayment.setIsPrepayment(false);					
		newPayment.setDateAcct(payment.getDateAcct());
		newPayment.setDateTrx(dateInvoiced);
		newPayment.setDescription(description);
		newPayment.setIsSelfService(true);
		newPayment.set_ValueNoCheck("DS_CashAdvanceEmployee", payment.get_Value("DS_CashAdvanceEmployee"));
		newPayment.set_ValueNoCheck("DS_Signature_ID", payment.get_Value("DS_Signature_ID"));
		newPayment.set_ValueNoCheck("BankAccountType", payment.getC_BankAccount().getBankAccountType());
		newPayment.set_ValueOfColumn("DS_LiquidatedGivenTo_ID", payment.get_Value("DS_LiquidatedGivenTo_ID"));
		newPayment.set_ValueOfColumn("DS_TotalCashBillAmt", payment.get_Value("DS_TotalCashBillAmt"));
		//	Save payment
		newPayment.saveEx();
		
		return newPayment;
	}
}
