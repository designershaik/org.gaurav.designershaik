package com.logilite.metabaseBI.factory;

import org.adempiere.webui.factory.IDashboardGadgetFactory;
import org.zkoss.zk.ui.Component;

import com.logilite.metabase.dashboard.DPMetabase;

public class MetabaseDashboardGadgetFactory implements IDashboardGadgetFactory
{

	@Override
	public Component getGadget(String uri, Component parent)
	{
		if (uri != null && uri.toLowerCase().endsWith("/metabase.zul"))
		{
			return new DPMetabase();
		}
		return null;
	}

}
