package org.dsi.crm.processes;

import org.compiere.process.SvrProcess;
import org.gaurav.dsi.model.MDSIRefillPrintForm;

public class PrintRefillForms extends SvrProcess {
	int refill_ID;
	int noofPrints;
	Integer documentNo;
	Integer NewDocNum;
	MDSIRefillPrintForm rpf;

	@Override
	protected String doIt() throws Exception 
	{
		for (int i = 0; i < noofPrints - 1; i++) 
		{
			NewDocNum = NewDocNum+1 ;
			MDSIRefillPrintForm rprf = new MDSIRefillPrintForm(getCtx(), 0,
					get_TrxName());
			rprf.setC_BPartner_ID(rpf.getC_BPartner_ID());
			rprf.setC_BPartner_Location_ID(rpf.getC_BPartner_Location_ID());
			rprf.setDSI_Print("Y");
			rprf.setValueNumber(rpf.getValueNumber());
			rprf.setIsPrinted(true);
			rprf.setDSI_FirstID(refill_ID);
			rprf.save();
		}
		
		rpf.setIsPrinted(true);
		rpf.setDSI_FirstID(refill_ID);
		rpf.saveEx();

		return "Process successful";
	}

	@Override
	protected void prepare() {
		refill_ID = getRecord_ID();
		rpf = new MDSIRefillPrintForm(getCtx(), refill_ID, get_TrxName());
		documentNo = Integer.parseInt(rpf.getDocumentNo());
		NewDocNum=documentNo;
		noofPrints = rpf.getValueNumber().intValue();

	}

}
