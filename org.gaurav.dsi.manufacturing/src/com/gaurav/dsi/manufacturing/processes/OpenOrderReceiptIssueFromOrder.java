package com.gaurav.dsi.manufacturing.processes;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.session.SessionManager;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;


public class OpenOrderReceiptIssueFromOrder extends SvrProcess implements EventListener<Event>
{

	@Override
	protected void prepare() 
	{
		
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		Env.setContext(getCtx(), "DS_MOOrder_ID", getRecord_ID());
		Env.setContext(getCtx(), "DS_FromProcess", "true");
		Executions.schedule(AEnv.getDesktop(), this, new Event("DSOrderReceiptForm"));
		return null;
	}

	@Override
	public void onEvent(Event event) throws Exception 
	{
		int FormID = DB.getSQLValue(get_TrxName(), "Select AD_Form_ID from AD_Form where AD_Form_UU=''");
		if("DSOrderReceiptForm".equals(event.getName()))
			SessionManager.getAppDesktop().openForm(FormID);
	}


}
