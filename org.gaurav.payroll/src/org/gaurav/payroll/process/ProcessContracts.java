package org.gaurav.payroll.process;

import org.compiere.process.SvrProcess;
import org.gaurav.payroll.model.MHRContract;

public class ProcessContracts extends SvrProcess{

	MHRContract contract = null ; 
	@Override
	protected void prepare() {
		
		contract = new MHRContract(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		
		
		return null;
	}


}
