package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.PO;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class SplitInvoiceLineForTax extends SvrProcess{

	BigDecimal percent = Env.ZERO;
	MInvoiceLine line = null;
	int C_InvoiceLine_ID = 0 ; 
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null)
				;
			else if (name.equals("Percent"))
				percent = para[i].getParameterAsBigDecimal();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
		C_InvoiceLine_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(C_InvoiceLine_ID<=0)
			throw new AdempiereException(Msg.getMsg(getCtx(), "NotFound"));
		
		
		line = new MInvoiceLine(getCtx(), C_InvoiceLine_ID, get_TrxName());
		if(line.getPriceEntered().compareTo(Env.ZERO)<=0)
			return "Amount is zero";
			
		BigDecimal lineNetAmt = line.getLineNetAmt();
		BigDecimal taxAmt = line.getTaxAmt();
		int precision = lineNetAmt.scale();
		
		BigDecimal percentAmt = (lineNetAmt.multiply(percent)).divide(Env.ONEHUNDRED,precision,RoundingMode.CEILING);
		
		MInvoice invoice = new MInvoice(getCtx(), line.getC_Invoice_ID(), get_TrxName());
		
		MInvoiceLine newLine = new MInvoiceLine(invoice);
		PO.copyValues(newLine, line);
		newLine.setQty(Env.ONE);
		newLine.setPrice(percentAmt);
		newLine.setC_Activity_ID(line.getC_Activity_ID());
		newLine.setUser1_ID(line.getUser1_ID());
		newLine.setUser2_ID(line.getUser2_ID());
		if(line.getC_Charge_ID()>0)
			newLine.setC_Charge_ID(line.getC_Charge_ID());
		if(line.getM_Product_ID()>0)
			newLine.setM_Product_ID(line.getM_Product_ID());
		newLine.setDescription(line.getDescription());
		newLine.set_ValueNoCheck("R_Request_ID", line.get_Value("R_Request_ID"));
		newLine.set_ValueNoCheck("DS_Product_Request_ID", line.get_Value("DS_Product_Request_ID"));	
		newLine.set_ValueNoCheck("DS_OriginalLine_ID", line.getC_InvoiceLine_ID());
		newLine.setLineNetAmt();
		newLine.saveEx();
		
		int C_Tax_ID = DB.getSQLValue(get_TrxName(), "Select C_Tax_ID From C_Tax Where DS_IsNonRecoverable='Y' and AD_Client_ID = ? ",getAD_Client_ID());
		
		BigDecimal remainingTaxAmt = lineNetAmt.add(taxAmt).subtract(newLine.getLineTotalAmt());
		line.setPrice(remainingTaxAmt);
		line.setC_Tax_ID(C_Tax_ID);
		line.setLineNetAmt();
		line.setLineTotalAmt(remainingTaxAmt);
		line.setTaxAmt();
		line.saveEx();
		
		addLog("New line created: "+newLine.getLine());
		return "Done";
	}


}
