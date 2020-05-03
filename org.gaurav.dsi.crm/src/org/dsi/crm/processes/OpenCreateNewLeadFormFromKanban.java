package org.dsi.crm.processes;


import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.session.SessionManager;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.dsi.crm.form.AddActivity;
import org.dsi.crm.form.AddNewLead;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

public class OpenCreateNewLeadFormFromKanban extends SvrProcess implements EventListener<Event>{

	int p_C_BPartner_ID = 0 ;
	int p_AD_User_ID = 0 ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_BPartner_ID"))
				p_C_BPartner_ID = para[i].getParameterAsInt();
			else if (name.equals("AD_User_ID"))
				p_AD_User_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(p_C_BPartner_ID<=0)
			Executions.schedule(AEnv.getDesktop(), this, new Event("OpenCreateLead"));
		if(p_C_BPartner_ID>0)
		{
			Env.setContext(getCtx(), "DS_BPartner_ID", p_C_BPartner_ID);
			Env.setContext(getCtx(), "Selected_User_ID", p_AD_User_ID);
			Env.setContext(getCtx(), "DS_lead_ID", 0);
			Executions.schedule(AEnv.getDesktop(), this, new Event("addActivity"));
		}
		return "@OK@";
	}

	@Override
	public void onEvent(Event event) throws Exception 
	{
		if(event.getName().equals("OpenCreateLead"))
		{
			AddNewLead addNewLead = new AddNewLead();
			SessionManager.getAppDesktop().showWindow(addNewLead);
		}
		if(event.getName().equals("addActivity"))
		{
			AddActivity requestWin = new AddActivity();
			SessionManager.getAppDesktop().showWindow(requestWin);
		}
	}
}
