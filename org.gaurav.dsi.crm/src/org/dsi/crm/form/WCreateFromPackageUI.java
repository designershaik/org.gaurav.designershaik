package org.dsi.crm.form;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
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
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Language;
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
			loadOrderLines(0);
			window.tableChanged(null);
		}
		if (e.getTarget().equals(orderField))
		{
			ListItem li = orderField.getSelectedItem();
			int C_Order_ID = 0;
			if (li != null && li.getValue() != null)
				C_Order_ID = ((Integer) li.getValue()).intValue();
			//  set Invoice, RMA and Shipment to Null
			loadOrderLines(C_Order_ID);
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
	
	protected Label subBPartnerLabel = new Label();
	protected WTableDirEditor subBPartnerField;
	
	protected Label orderLabel = new Label();
	protected Listbox orderField = ListboxFactory.newDropdownListbox();
	
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
		
		super.dynInit();
		Language language = Language.getLoginLanguage(); // Base Language
		//Refresh button
		Button refreshButton = window.getConfirmPanel().createButton(ConfirmPanel.A_REFRESH);
		refreshButton.addEventListener(Events.ON_CLICK, this);
		window.getConfirmPanel().addButton(refreshButton);
		
		confirm.setLabel(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "FIL_Confirm")));
		confirm.addActionListener(this);
		confirm.setAutodisable("self");
		
		window.setTitle(getTitle());
		m_C_BPartner_ID = Env.getContextAsInt(Env.getCtx(), p_WindowNo, "C_BPartner_ID");

		MLookup lookupbp =  MLookupFactory.get(Env.getCtx(), p_WindowNo,MColumn.getColumn_ID(MBPartner.Table_Name,MBPartner.COLUMNNAME_C_BPartner_ID),DisplayType.TableDir, language, MOrder.COLUMNNAME_C_BPartner_ID, 0, false,
																							"C_BPartner.C_BPartner_ID = "+m_C_BPartner_ID+"");
		
		MLookup lookupSubBPartner =  MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, MColumn.getColumn_ID(MOrder.Table_Name, "C_BPartner_ID"),DisplayType.Table);
		
		bpartnerField = new WTableDirEditor("C_BPartner_ID", true, false, false, lookupbp);
		
		subBPartnerField = new WTableDirEditor("C_BPartner_ID", false, false, true, lookupSubBPartner);
		//  Set Default
	
		bpartnerField.setValue(new Integer(m_C_BPartner_ID));
		bpartnerField.addValueChangeListener(this);
		subBPartnerField.addValueChangeListener(this);
		//  initial Loading
		
		initOrder(m_C_BPartner_ID,m_FIL_Sub_BPartner_ID);
		
		
		return true;
	}   //  dynInit
	
	private void initOrder(int C_BPartner_ID, int m_FIL_Sub_BPartner_ID) throws Exception 
	{
		if (log.isLoggable(Level.CONFIG)) log.config("C_BPartner_ID=" + C_BPartner_ID);
		KeyNamePair pp = new KeyNamePair(0,"");
		orderField.removeActionListener(this);
		orderField.removeAllItems();
		orderField.addItem(pp);
		
		ArrayList<KeyNamePair> list = loadOrderData(C_BPartner_ID, m_FIL_Sub_BPartner_ID);
		for(KeyNamePair knp : list)
			orderField.addItem(knp);
		
		orderField.setSelectedIndex(0);
		orderField.addActionListener(this);
	}

	private ArrayList<KeyNamePair> loadOrderData(int C_BPartner_ID, int m_FIL_Sub_BPartner_ID) 
	{
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();

		//	Display
		StringBuffer display = new StringBuffer("o.DocumentNo||' - ' ||")
			.append(DB.TO_CHAR("o.DateOrdered", DisplayType.Date, Env.getAD_Language(Env.getCtx())))
			.append("||' - '||")
			.append(DB.TO_CHAR("o.GrandTotal", DisplayType.Amount, Env.getAD_Language(Env.getCtx())));
	
		StringBuffer sql = new StringBuffer("SELECT o.C_Order_ID,").append(display)
			.append(" FROM C_Order o "
			+ "WHERE o.C_BPartner_ID in (?,?) AND o.IsSOTrx='Y' AND o.DocStatus IN ('CL','CO')"
			+ " AND o.C_Order_ID IN (select line.C_Order_ID From C_OrderLine line,C_Order co "
			+ "Where line.C_Order_ID = co.C_Order_ID "
			+ "and line.qtyDelivered<>line.qtyordered  group by line.C_Order_ID having count(*)>0) ");
	
		sql = sql.append("ORDER BY o.DateOrdered,o.DocumentNo");
		//
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			pstmt.setInt(1, C_BPartner_ID);
			pstmt.setInt(2, m_FIL_Sub_BPartner_ID);
			
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				list.add(new KeyNamePair(rs.getInt(1), rs.getString(2)));
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}

		return list;
	}

	protected void zkInit() throws Exception
	{
		bpartnerLabel.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		subBPartnerLabel.setText(Msg.translate(Env.getCtx(), "FIL_Sub_BPartner_ID"));
		
		orderLabel.setText(Msg.translate(Env.getCtx(), "C_Order_ID"));
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
		
		row = rows.newRow();
		row.appendChild(subBPartnerLabel.rightAlign());
		row.appendChild(subBPartnerField.getComponent());
		
		row = rows.newRow();
		row.appendChild(orderLabel.rightAlign());
		row.appendChild(orderField);		
	}
	
	protected void loadOrderLines(int C_Order_ID)
	{
		loadTableOIS(getOrderLines(C_Order_ID));
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
	
	/**
	 *  Change Listener
	 *  @param e event
	 */
	public void valueChange (ValueChangeEvent e)
	{
		if (log.isLoggable(Level.CONFIG)) log.config(e.getPropertyName() + "=" + e.getNewValue());

		
		//  BPartner - load Order/Invoice/Shipment
		if (e.getPropertyName().equals("C_BPartner_ID"))
		{
			m_C_BPartner_ID = ((Integer)e.getNewValue()).intValue();
			try
			{
				orderField.setSelectedIndex(-1);
				initBPOrderDetails (m_C_BPartner_ID,m_FIL_Sub_BPartner_ID);
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		if (e.getPropertyName().equals("FIL_Sub_BPartner_ID"))
		{
			if(e.getNewValue()!=null)
			{
				m_FIL_Sub_BPartner_ID = ((Integer)e.getNewValue()).intValue();
				try
				{
					orderField.setSelectedIndex(-1);
					initBPOrderDetails (m_C_BPartner_ID,m_FIL_Sub_BPartner_ID);
				} 
				catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		}		
		window.tableChanged(null);
	}   //  vetoableChange

	private void initBPOrderDetails(int C_BPartner_ID, int m_FIL_Sub_BPartner_ID) throws Exception 
	{
		if (log.isLoggable(Level.CONFIG)) log.config("C_BPartner_ID=" + C_BPartner_ID+" Sub BPartner: "+m_FIL_Sub_BPartner_ID);
		orderField.removeActionListener(this);
		initOrder(C_BPartner_ID,m_FIL_Sub_BPartner_ID);
	}
	

}