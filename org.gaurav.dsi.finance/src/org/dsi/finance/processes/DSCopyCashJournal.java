// Not used

package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.MCash;
import org.compiere.model.MCashLine;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class DSCopyCashJournal extends SvrProcess 
{
	int cashJournal_ID;
	int original_Cash_ID;
	@Override
	protected void prepare() 
	{
		cashJournal_ID=getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_Cash_ID"))
				original_Cash_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		MCash cash=new MCash(getCtx(), original_Cash_ID, get_TrxName());
		MCashLine[] lines=cash.getLines(false);
		for(int i=0;i<lines.length;i++)
		{
			MCashLine line=lines[i];
			MCashLine newLine=new MCashLine(getCtx(), 0, get_TrxName());
			newLine.setLine(line.getLine());
			newLine.setDescription(line.getDescription());
			newLine.setC_Invoice_ID(line.getC_Invoice_ID());
			newLine.setC_Charge_ID(line.getC_Charge_ID());
			newLine.set_ValueOfColumn("C_BPartner_ID", line.get_Value("C_BPartner_ID"));
			if(line.getC_Invoice_ID()==0 || (Integer)line.getC_Invoice_ID()==null)
			{
				newLine.setAmount(line.getAmount());
			}
			newLine.setCashType(line.getCashType());
			newLine.setC_Cash_ID(cashJournal_ID);
			newLine.save();
		}
		return null;
	}

}
