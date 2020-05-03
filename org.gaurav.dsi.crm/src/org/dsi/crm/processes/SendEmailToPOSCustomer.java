package org.dsi.crm.processes;

import org.compiere.model.MQuery;
import org.compiere.process.SvrProcess;

public class SendEmailToPOSCustomer extends SvrProcess{

	int DS_POS_ID ;
	@Override
	protected void prepare() 
	{
		DS_POS_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		MQuery query = new MQuery("C_Dunning_Header_v");
		query.addRestriction("C_DunningRunEntry_ID", MQuery.EQUAL, 
			new Integer(DS_POS_ID));
		return null;
	}
}
