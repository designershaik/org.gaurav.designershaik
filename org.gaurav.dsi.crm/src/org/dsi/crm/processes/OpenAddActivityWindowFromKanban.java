package org.dsi.crm.processes;


import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.session.SessionManager;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.dsi.crm.form.AddActivity;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class OpenAddActivityWindowFromKanban extends SvrProcess implements EventListener<Event>{

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
			Env.setContext(getCtx(), "DS_lead_ID", selectedCard.substring(1));
			Env.setContext(getCtx(), "DS_BPartner_ID", 0);
		}
		else if(selectedCard.startsWith("9"))
		{
			Env.setContext(getCtx(), "DS_BPartner_ID", selectedCard.substring(1));
			Env.setContext(getCtx(), "DS_lead_ID", 0);
		}
		else 
			Env.setContext(getCtx(), "DS_lead_ID", selectedCard);
		
		
		Executions.schedule(AEnv.getDesktop(), this, new Event("addActivity"));
		return null;
	}

	@Override
	public void onEvent(Event event) throws Exception 
	{
		if(event.getName().equals("addActivity"))
		{
			AddActivity requestWin = new AddActivity();
			SessionManager.getAppDesktop().showWindow(requestWin);
		}
	}
}
