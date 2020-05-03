package org.dsi.crm.processes;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.util.ServerPushTemplate;
import org.compiere.model.MQuery;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.processes.CommonServerPushCallbackOpenWindow;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class ZoomToWindowFromKanbanLeadCard extends SvrProcess implements EventListener<Event>{

	int AD_User_ID = 0;
	int C_BPartner_ID = 0;
	@Override
	protected void prepare() {
		
		
	}

	@Override
	protected String doIt() throws Exception {
		
		int selectedCard_ID = DB.getSQLValue(get_TrxName()," select t_selection_ID from t_selection "
                +"where ad_pinstance_id=? ",getAD_PInstance_ID());
		String selectedCard = Integer.toString(selectedCard_ID);
		if(selectedCard.startsWith("8"))
		{
			AD_User_ID = Integer.parseInt(selectedCard.substring(1));
			Env.setContext(getCtx(), "DS_lead_ID", selectedCard.substring(1));
			Env.setContext(getCtx(), "DS_BPartner_ID", 0);
			Executions.schedule(AEnv.getDesktop(), this, new Event("openLead"));
		}
		if(selectedCard.startsWith("9"))
		{
			C_BPartner_ID = Integer.parseInt(selectedCard.substring(1));
			Env.setContext(getCtx(), "DS_BPartner_ID", selectedCard.substring(1));
			Env.setContext(getCtx(), "DS_lead_ID", 0);
			Executions.schedule(AEnv.getDesktop(), this, new Event("openCustomer"));
		}
		
		
		return null;
	}

	@Override
	public void onEvent(Event event) throws Exception 
	{
		int windowid = 0 ;
		String restriction = "";
		if(event.getName().equals("openLead"))
		{
			windowid = DB.getSQLValue(get_TrxName(), "Select AD_Window_ID From AD_Window Where AD_Window_UU like '0760556b-d289-468e-bd1a-063d455a37ef' ");
			restriction = "AD_User_ID="+AD_User_ID;
		}
		if(event.getName().equals("openCustomer"))
		{
			windowid = DB.getSQLValue(get_TrxName(), "Select AD_Window_ID From AD_Window Where AD_Window_UU like '8cd5c0e7-77b5-48a4-a994-35403831e37f' ");
			restriction = "C_BPartner_ID="+C_BPartner_ID;
		}

		MQuery query = new MQuery("");
		query.addRestriction(restriction);

		Desktop desktop = AEnv.getDesktop();
		ServerPushTemplate pushUpdateUi = new ServerPushTemplate (desktop);
		CommonServerPushCallbackOpenWindow callback = new CommonServerPushCallbackOpenWindow();
		callback.setParam(windowid, query, false);
		pushUpdateUi.executeAsync (callback);
	}

}
