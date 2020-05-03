package org.dsi.finance.form;


import java.sql.Timestamp;
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
import org.adempiere.webui.editor.WDateEditor;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridTab;
import org.compiere.model.MColumn;
import org.compiere.model.MField;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPayment;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Hbox;

public class WCreateFromPaymentUI extends WCreateFromPayment implements EventListener<Event>{

	public WCreateFromPaymentUI(GridTab mTab)
	{
		super(mTab);
		log.info(getGridTab().toString());
		
		window = new WCreateFromWindow(this, getGridTab().getWindowNo());
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
			loadBankAccount();
			window.tableChanged(null);
		}
	}
	
	private WCreateFromWindow window;



	/** Window No               */
	private int p_WindowNo;

	/**	Logger			*/
	private CLogger log = CLogger.getCLogger(getClass());

	protected Label documentNoLabel = new Label(Msg.translate(Env.getCtx(), "DocumentNo"));
	protected WStringEditor documentNoField = new WStringEditor();

	protected Label l_isSOTrx = new Label();
	protected WTableDirEditor isSOTrx;

	protected Label amtFromLabel = new Label(Msg.translate(Env.getCtx(), "GrandTotal"));
	protected WNumberEditor amtFromField = new WNumberEditor("AmtFrom", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtFrom"));
	protected Label amtToLabel = new Label("-");
	protected WNumberEditor amtToField = new WNumberEditor("AmtTo", false, false, true, DisplayType.Amount, Msg.translate(Env.getCtx(), "AmtTo"));
	
	protected Label BPartner_idLabel = new Label(Msg.translate(Env.getCtx(), "BPartner"));
	protected WEditor bPartnerLookup;

	protected Label dateFromLabel = new Label(Msg.translate(Env.getCtx(), "DateTrx"));
	protected WDateEditor dateFromField = new WDateEditor("DateFrom", false, false, true, Msg.translate(Env.getCtx(), "DateFrom"));
	protected Label dateToLabel = new Label("-");
	protected WDateEditor dateToField = new WDateEditor("DateTo", false, false, true, Msg.translate(Env.getCtx(), "DateTo"));

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
				
		if (getGridTab().getValue("C_Payment_ID") == null)
		{
			FDialog.error(0, window, "SaveErrorRowNotFound");
			return false;
		}
		
		window.setTitle(getTitle());
				
		MLookup lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MField.Table_Name, MField.COLUMNNAME_IsMandatory), DisplayType.List);
		isSOTrx = new WTableDirEditor (MField.COLUMNNAME_IsMandatory,false,false,true,lookup);
		isSOTrx.getComponent().addEventListener(Events.ON_CHANGE, this);
		
		lookup = MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 3499, DisplayType.Search);
		bPartnerLookup = new WSearchEditor ("C_BPartner_ID", false, false, true, lookup);
		
		Timestamp date = Env.getContextAsDate(Env.getCtx(), p_WindowNo, MPayment.COLUMNNAME_DateAcct);
		dateToField.setValue(date);
		isSOTrx.setValue("N");
		loadBankAccount();
		
		return true;
	}   //  dynInit
	
	protected void zkInit() throws Exception
	{
		l_isSOTrx.setText(Msg.translate(Env.getCtx(), "IsSOTrx"));
    
    	dateFromField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "DateFrom"));
    	dateToField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "DateTo"));
    	
    	amtFromField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "AmtFrom"));
    	amtToField.getComponent().setTooltiptext(Msg.translate(Env.getCtx(), "AmtTo"));
    	
    	Borderlayout parameterLayout = new Borderlayout();
    	parameterLayout.setHeight("130px");
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
		row.appendChild(documentNoLabel.rightAlign());
		row.appendChild(documentNoField.getComponent());
		
		row.appendChild(l_isSOTrx.rightAlign());
		row.appendChild(isSOTrx.getComponent());
		
		row = rows.newRow();
		row.appendChild(amtFromLabel.rightAlign());
		Hbox hbox = new Hbox();
		hbox.appendChild(amtFromField.getComponent());
		hbox.appendChild(amtToLabel.rightAlign());
		hbox.appendChild(amtToField.getComponent());
		row.appendChild(hbox);
		
		row = rows.newRow();
		row.appendChild(BPartner_idLabel.rightAlign());
		row.appendChild(bPartnerLookup.getComponent());
		row.appendChild(dateFromLabel.rightAlign());
		
		hbox = new Hbox();
		hbox.appendChild(dateFromField.getComponent());
		hbox.appendChild(dateToLabel.rightAlign());
		hbox.appendChild(dateToField.getComponent());
		row.appendChild(hbox);
	}

	protected void loadBankAccount()
	{
		loadTableOIS(getPaymentData(bPartnerLookup.getValue(), 
				documentNoField.getValue().toString(), dateFromField.getValue(), dateToField.getValue(),
				amtFromField.getValue(), amtToField.getValue(), 
				isSOTrx.getValue()));
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
