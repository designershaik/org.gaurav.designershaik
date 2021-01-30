package org.dsi.crm.form;


import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.form.WCreateFromWindow;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MColumn;
import org.compiere.model.MInOut;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;

public class WCreateFromPackageUI extends WCreateFromPackage implements EventListener<Event>, ValueChangeListener{

	public WCreateFromPackageUI(GridTab mTab)
	{
		super(mTab);
		log.info(getGridTab().toString());
		
		window = new WCreateFromWindow(this, getGridTab().getWindowNo());
		window.setHeight("70%");
		window.setWidth("80%");
		p_WindowNo = getGridTab().getWindowNo();

		try
		{
			if (!dynInit())
				return;
			zkInit();
			setInitOK(true);
		}
		catch(Exception e)
		{
			log.log(Level.SEVERE, "", e);
			setInitOK(false);
		}
		AEnv.showWindow(window);
	}

	@Override
	public void onEvent(Event e) throws Exception 
	{
		if (log.isLoggable(Level.CONFIG)) log.config("Action=" + e.getTarget().getId());
		if(e.getTarget().equals(window.getConfirmPanel().getButton(ConfirmPanel.A_REFRESH)))
		{
			getShippableLinesForPackage();
			window.tableChanged(null);
		}
		else if(e.getTarget().equals(confirm))
		{
			getGridTab();
		}
	}
	private WCreateFromWindow window;

	/** Window No               */
	private int p_WindowNo;

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());

	protected Label bpartnerLabel = new Label();
	protected WTableDirEditor bpartnerField;
	
	protected Label l_confirmButtn = new Label();
	private Button confirm = new Button();

	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception
	{
		log.config("");
		String masterShipment_ID = Env.getContext(Env.getCtx(), p_WindowNo, "M_InOut_ID");
		MInOut inout = new MInOut(Env.getCtx(), Integer.parseInt(masterShipment_ID), null);
		super.dynInit();
		//Refresh button
		Button refreshButton = window.getConfirmPanel().createButton(ConfirmPanel.A_REFRESH);
		refreshButton.addEventListener(Events.ON_CLICK, this);
		window.getConfirmPanel().addButton(refreshButton);
		
		confirm.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "FIL_Confirm")));
		confirm.addActionListener(this);
		confirm.setAutodisable("self");
		
		window.setTitle(getTitle());

		MLookup lookupbp =  MLookupFactory.get(Env.getCtx(), p_WindowNo,0,MColumn.getColumn_ID(MBPartner.Table_Name,MBPartner.COLUMNNAME_C_BPartner_ID),DisplayType.TableDir);		
		bpartnerField = new WTableDirEditor("C_BPartner_ID", true, false, false, lookupbp);
		
		//  Set Default
		bpartnerField.setValue(inout.getC_BPartner_ID());
		getShippableLinesForPackage();
		return true;
	}   //  dynInit
	

	protected void zkInit() throws Exception
	{
		bpartnerLabel.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		Borderlayout parameterLayout = new Borderlayout();
		ZKUpdateUtil.setHeight(parameterLayout, "110px");
		ZKUpdateUtil.setWidth(parameterLayout, "100%");
		Panel parameterPanel = window.getParameterPanel();
		parameterPanel.appendChild(parameterLayout);
		
		Grid paremeterLayout = GridFactory.newGridLayout();
    		Panel parameterProjectPanel = new Panel();
    		parameterProjectPanel.appendChild(paremeterLayout);

		Center center = new Center();
		parameterLayout.appendChild(center);
		center.appendChild(parameterProjectPanel);
		
		Columns columns = new Columns();
		paremeterLayout.appendChild(columns);
		Column column = new Column();
		columns.appendChild(column);		
		column = new Column();
		column.setWidth("15%");
		columns.appendChild(column);
		column.setWidth("35%");
		column = new Column();
		column.setWidth("15%");
		columns.appendChild(column);
		column = new Column();
		column.setWidth("35%");
		columns.appendChild(column);
		
		Rows rows = (Rows) paremeterLayout.newRows();
		
		Row row = rows.newRow();
		row.appendChild(bpartnerLabel.rightAlign());
		row.appendChild(bpartnerField.getComponent());
	}
	
	protected void getShippableLinesForPackage()
	{
		loadTableOIS(getShipmentLines());
	}
	

	protected void loadTableOIS (Vector<?> data)
	{
		window.getWListbox().clear();
		
		//  Remove previous listeners
		window.getWListbox().getModel().removeTableModelListener(window);
		//  Set Model
		ListModelTable model = new ListModelTable(data);
		model.addTableModelListener(window);
		window.getWListbox().setData(model, getOISColumnNames());
		//
		
		configureMiniTable(window.getWListbox());
	}
	
	public void showWindow()
	{
		window.setVisible(true);
	}
	
	public void closeWindow()
	{
		window.dispose();
	}

	@Override
	public Object getWindow() 
	{
		return window;
	}

	@Override
	public void valueChange(ValueChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}