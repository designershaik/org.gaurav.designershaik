package com.logilite.metabaseBI.form;

import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.panel.ADForm;
import org.compiere.model.MForm;
import org.compiere.model.MSysConfig;
import org.compiere.model.MTable;
import org.compiere.util.Env;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.North;

/**
 * @author ravi
 */
public class WCustomFormController extends ADForm implements EventListener <Event>
{

	private static final long	serialVersionUID	= 1L;
	private Iframe				iframe				= new Iframe();
	private Textbox				txtURL				= new Textbox();

	/* Whether to show URL on top of Form or not */
	public static final String	METABASEUI_SHOW_URL	= "METABASEUI_SHOW_URL";

	private void init( )
	{
		// Get form id
		int formID = getAdFormId();
		MForm form = (MForm) MTable.get(Env.getCtx(), MForm.Table_ID).getPO(formID, null);
		String url = form.get_ValueAsString("X_MetabaseURL");
		String urlZoom = url;
		boolean showURL = MSysConfig.getBooleanValue(METABASEUI_SHOW_URL, true, Env.getAD_Client_ID(Env.getCtx()));

		// Append dashboard number in url
		Integer dashboardNumber = (Integer) form.get_Value("X_DashboardNumber");
		if (dashboardNumber != null && url != null)
		{
			// strip out "public/"
			urlZoom = urlZoom.replace("/public/", "/");
			// remove suffix after "dashboard/"
			String suffix = "/dashboard/";
			int index = urlZoom.lastIndexOf(suffix);
			if (index > 0)
			{
				urlZoom = urlZoom.substring(0, index + suffix.length());
			}
			urlZoom = urlZoom + dashboardNumber;
		}

		txtURL.setText(urlZoom);
		txtURL.setReadonly(true);
		txtURL.setWidth("98%");
		txtURL.setVisible(showURL);
		txtURL.addEventListener(Events.ON_OK, this);

		iframe.setHflex("true");
		iframe.setVflex("true");
		iframe.setStyle("width:100%; height:100%");
		iframe.setSrc(url);

		Center center = new Center();
		center.appendChild(iframe);

		North north = new North();
		north.appendChild(txtURL);

		Borderlayout mainLayout = new Borderlayout();
		mainLayout.appendChild(north);
		mainLayout.appendChild(center);
		this.appendChild(mainLayout);
		this.setStyle("width:101%; height:100%; position:absolute");
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		if (event.getTarget().equals(txtURL) && txtURL.getValue().trim().length() > 0)
		{
			iframe.setSrc(txtURL.getValue());
		}
	}

	@Override
	protected void initForm( )
	{
		init();
	}

}
