package org.gaurav.dsi.processes;

import org.adempiere.webui.adwindow.ADTabpanel;
import org.adempiere.webui.adwindow.ADWindow;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.util.IServerPushCallback;
import org.compiere.model.MQuery;
import org.adempiere.util.Callback;

public class CommonServerPushCallbackOpenWindow implements IServerPushCallback {


	 private int m_windowID = 0;
	 private MQuery m_query = null;
	 private boolean m_isNew = false;


	 public void setParam(int winID, MQuery query, boolean isNew) 
	 {
		 m_windowID = winID;
		 m_query = query;
		 m_isNew = isNew;
	 }


	 @Override
	 public void updateUI() 
	 {


		SessionManager.getAppDesktop().openWindow(m_windowID, m_query, new Callback<ADWindow>() 
		{
	
			 @Override
			 public void onCallback(ADWindow result) 
			 {
				 if(result == null)
				 return;
				 if (m_isNew)
			
				 result.getADWindowContent().onNew();
				 ADTabpanel adtabpanel = (ADTabpanel) result.getADWindowContent().getADTab().getSelectedTabpanel();
				 adtabpanel.focusToFirstEditor(false);
			 }
		 });
	 }
	}