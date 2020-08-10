package com.gaurav.dsi.process;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.PO;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class DistributeAmountWithinPortfolio extends SvrProcess 
{
	MInvoiceLine line = null;
	int C_InvoiceLine_ID = 0 ; 
	@Override
	protected void prepare() {
		
		C_InvoiceLine_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		
		line = new MInvoiceLine(getCtx(), C_InvoiceLine_ID, get_TrxName());
		
		if(line.getPriceEntered().compareTo(Env.ZERO)<=0)
			return "Amount is zero";
		
		MInvoice inv = new MInvoice(getCtx(), line.getC_Invoice_ID(), get_TrxName());
		String content = line.get_ValueAsString("DS_Portfolio_Content");
		StringTokenizer tokenizer = new StringTokenizer(content, ",");
        
		ArrayList<Integer> investments = new ArrayList<Integer>();
        while (tokenizer.hasMoreTokens()) 
        {
        	investments.add(Integer.parseInt(tokenizer.nextToken()));
        }
        int totalRelatedInvestments = investments.size();
		
        BigDecimal splitAmt = line.getLineNetAmt().divide(new BigDecimal(totalRelatedInvestments), inv.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
        int i = 1;
		for(int relatedProduct_ID : investments)
		{
			MInvoiceLine newLine = new MInvoiceLine(getCtx(), 0, get_TrxName());
			PO.copyValues(line, newLine);
			newLine.setPrice(splitAmt);
			newLine.setLineNetAmt();
			newLine.set_ValueNoCheck("RelatedProduct_ID", relatedProduct_ID);
			newLine.setLine(line.getLine()+i);
			newLine.setAD_Org_ID(line.getAD_Org_ID());
			newLine.setTaxAmt();
			BigDecimal lineTotalAmt = newLine.getLineNetAmt().add(newLine.getTaxAmt());
			newLine.setLineTotalAmt(lineTotalAmt);
			newLine.saveEx();
			i++;
		}
		line.delete(true);
		return "Distributed, added lines: "+i;
	}

}
