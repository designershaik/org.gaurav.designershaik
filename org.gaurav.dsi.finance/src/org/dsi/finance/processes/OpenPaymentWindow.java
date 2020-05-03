package org.dsi.finance.processes;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.util.ServerPushTemplate;
import org.compiere.model.MQuery;
import org.compiere.process.SvrProcess;
import org.gaurav.dsi.processes.CommonServerPushCallbackOpenWindow;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class OpenPaymentWindow extends SvrProcess implements EventListener<Event>

{
	int C_Payment_ID = 0 ;
	@Override
	protected void prepare() {
			C_Payment_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
			Executions.schedule(AEnv.getDesktop(), this, new Event("openPaymentWindow"));
		return null;
	}

	@Override

	public void onEvent(Event evnt) throws Exception 

	{
		if("openPaymentWindow".equals(evnt.getName()))
		{
			SessionManager.getAppDesktop().closeActiveWindow();
			int windowid = 195 ;
			MQuery query = new MQuery("");
			query.addRestriction(" C_Payment.C_Payment_ID IN (SELECT Ref_Payment_ID FROM DS_Settled_Invoices WHERE C_Payment_ID = "+C_Payment_ID+") or C_Payment.C_Payment_ID="+C_Payment_ID);
			Desktop desktop = AEnv.getDesktop();
			ServerPushTemplate pushUpdateUi = new ServerPushTemplate (desktop);
			CommonServerPushCallbackOpenWindow callback = new CommonServerPushCallbackOpenWindow();
			callback.setParam(windowid, query, false);
			pushUpdateUi.executeAsync (callback);

		}		

	}




}
