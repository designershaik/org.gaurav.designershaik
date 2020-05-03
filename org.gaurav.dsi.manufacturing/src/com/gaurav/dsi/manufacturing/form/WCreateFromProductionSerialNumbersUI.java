package com.gaurav.dsi.manufacturing.form;


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
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.compiere.model.GridTab;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;

public class WCreateFromProductionSerialNumbersUI extends WCreateFromForASI implements EventListener<Event>{

	public WCreateFromProductionSerialNumbersUI(GridTab mTab)
	{
		super(mTab);
		log.info(getGridTab().toString());
		
		window = new WCreateFromWindow(this, getGridTab().getWindowNo());
		
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
			loadASIDetails();
			window.tableChanged(null);
		}
	}
	
	private WCreateFromWindow window;



	/** Window No               */
	private int p_WindowNo;

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());

	protected Label productLabel = new Label();
	protected WTableDirEditor productField;
	
	protected Label locator_idLabel = new Label(Msg.getElement(Env.getCtx(), "M_Locator_ID"));
	protected WEditor locatorLookup;
	
	protected Label lbl_FromSerialNumber = new Label(Msg.getElement(Env.getCtx(), "M_Locator_ID"));
	/**
	 *  Dynamic Init
	 *  @throws Exception if Lookups cannot be initialized
	 *  @return true if initialized
	 */
	public boolean dynInit() throws Exception
	{
		log.config("");
		
		super.dynInit();
		
		//Refresh button
		Button refreshButton = window.getConfirmPanel().createButton(ConfirmPanel.A_REFRESH);
		refreshButton.addEventListener(Events.ON_CLICK, this);
		window.getConfirmPanel().addButton(refreshButton);
				
		window.setTitle(getTitle());
		
		int AD_Column_ID = 3593;        //  M_MovementLine.M_Product_ID
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, AD_Column_ID, DisplayType.TableDir);
		productField = new WTableDirEditor ("M_Product_ID", true, false, true, lookup);
		
		int M_Product_ID = (int) getGridTab().getValue("M_Product_ID");
		productField.setValue(new Integer(M_Product_ID));
	
		//  Set Default
			
		lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 3591, DisplayType.Search);
		locatorLookup = new WSearchEditor ("M_Locator_ID", false, false, true, lookup);
		
		Integer M_Locator_ID = (Integer) getGridTab().getValue("M_Locator_ID");
		if(M_Locator_ID!=null)
			locatorLookup.setValue(new Integer(M_Locator_ID));
		
		loadASIDetails();
		
		return true;
	}   //  dynInit
	
	protected void zkInit() throws Exception
	{
		productLabel.setText(Msg.translate(Env.getCtx(), "M_Product_ID"));
    	
		Borderlayout parameterLayout = new Borderlayout();
    	parameterLayout.setHeight("30px");
		parameterLayout.setWidth("100%");
    	Panel parameterPanel = window.getParameterPanel();
		parameterPanel.appendChild(parameterLayout);
		
		Grid parameterBankLayout = GridFactory.newGridLayout();
    	Panel parameterBankPanel = new Panel();
    	parameterBankPanel.appendChild(parameterBankLayout);

		Center center = new Center();
		parameterLayout.appendChild(center);
		center.appendChild(parameterBankPanel);
		
		Columns columns = new Columns();
		parameterBankLayout.appendChild(columns);
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
		
		Rows rows = (Rows) parameterBankLayout.newRows();
		Row row = rows.newRow();
		row.appendChild(productLabel.rightAlign());
		row.appendChild(productField.getComponent());
			
		row.appendChild(locator_idLabel.rightAlign());
		row.appendChild(locatorLookup.getComponent());
	}

	protected void loadASIDetails()
	{
		loadTableOIS(getProductAttributeSetInstanceData(productField.getValue(), locatorLookup.getValue()));
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

}
