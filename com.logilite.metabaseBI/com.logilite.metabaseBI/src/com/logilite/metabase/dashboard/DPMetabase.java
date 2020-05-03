package com.logilite.metabase.dashboard;

import java.util.List;

import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.dashboard.DashboardPanel;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.theme.ThemeManager;
import org.compiere.model.MForm;
import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Box;
import org.zkoss.zul.Vbox;

public class DPMetabase extends DashboardPanel implements EventListener <Event>
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	public DPMetabase()
	{
		super();
		setSclass("views-box");
		this.appendChild(createViewPanel());
	}

	private Box createViewPanel( )
	{
		Vbox vbox = new Vbox();

		List <MForm> list = new Query(Env.getCtx(), MForm.Table_Name, "X_MetabaseURL IS NOT NULL", null)
						.setOnlyActiveRecords(true).setOrderBy("Name").list();

		MForm[] forms = list.toArray(new MForm[list.size()]);

		for (int i = 0; i < forms.length; i++)
		{
			MForm form = forms[i];

			if (form != null && checkAccess(form))
			{
				ToolBarButton btnViewItem = new ToolBarButton(form.getName());
				btnViewItem.setSclass("link");
				btnViewItem.setLabel(form.get_Translation("Name"));
				btnViewItem.setImage(ThemeManager.getThemeResource("images/Info16.png"));
				btnViewItem.addEventListener(Events.ON_CLICK, this);
				vbox.appendChild(btnViewItem);
			}
		}
		return vbox;
	}

	public Boolean checkAccess(MForm form)
	{
		Boolean access = MRole.getDefault().getFormAccess(form.getAD_Form_ID());
		if (access != null && access.booleanValue())
			return true;
		return false;
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		Component comp = event.getTarget();
		String eventName = event.getName();

		if (eventName.equals(Events.ON_CLICK))
		{
			if (comp instanceof ToolBarButton)
			{
				ToolBarButton btn = (ToolBarButton) comp;
				String actionCommand = btn.getName();

				int formID = new Query(Env.getCtx(), MForm.Table_Name, "Name = ?", null).setParameters(actionCommand)
								.setOnlyActiveRecords(true).firstIdOnly();

				if (formID <= 0)
					return;

				SessionManager.getAppDesktop().openForm(formID);
			}
		}
	}

}
