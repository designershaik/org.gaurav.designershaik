/******************************************************************************
 * Copyright (C) 2009 Low Heng Sin                                            *
 * Copyright (C) 2009 Idalica Corporation                                     *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.dsi.finance.form;

import java.util.ArrayList;
import java.util.logging.Level;

import org.adempiere.webui.apps.form.WGenForm;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WStringEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MOrder;
import org.compiere.model.MRMA;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Space;

/**
 * Generate Invoice (manual) view class
 *
 */
public class WDSIInvoiceGen extends DSInvoiceGen implements IFormController, EventListener<Event>, ValueChangeListener
{
	private WGenForm form;

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(WDSIInvoiceGen.class);
	//
	private Label lOrg = new Label();
	private WTableDirEditor fOrg;
	private Label lBPartner = new Label();
	private WSearchEditor fBPartner;
	private Label     lDocType = new Label();
	private Listbox  cmbDocType = ListboxFactory.newDropdownListbox();
	private Label   lDocAction = new Label();
	private WTableDirEditor docAction;
	private Checkbox isSelectZeroInvoices = new Checkbox();
	private Label     lOrderType = new Label();
	private WTableDirEditor  fOrderType ;
	
	private Checkbox showConsignmentInvoices = new Checkbox();
	
	private Label lSalesOrder = new Label();
	private WStringEditor fSalesOrderNumber = new WStringEditor(); 
	
	public WDSIInvoiceGen()
	{
		log.info("");

		form = new WGenForm(this);
		Env.setContext(Env.getCtx(), form.getWindowNo(), "IsSOTrx", "Y");

		try
		{
			super.dynInit();
			dynInit();
			zkInit();

			form.postQueryEvent();
		}
		catch(Exception ex)
		{
			log.log(Level.SEVERE, "init", ex);
		}
	}	//	init

	/**
	 *	Static Init.
	 *  <pre>
	 *  selPanel (tabbed)
	 *      fOrg, fBPartner
	 *      scrollPane & miniTable
	 *  genPanel
	 *      info
	 *  </pre>
	 *  @throws Exception
	 */
	void zkInit() throws Exception
	{
		lOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		lBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));

		Row row = form.getParameterPanel().newRows().newRow();
		row.appendCellChild(lOrg.rightAlign());
		ZKUpdateUtil.setHflex(fOrg.getComponent(), "true");
		row.appendCellChild(fOrg.getComponent());
		row.appendCellChild(new Space());
		row.appendCellChild(lBPartner.rightAlign());
		ZKUpdateUtil.setHflex(fBPartner.getComponent(), "true");
		row.appendCellChild(fBPartner.getComponent());
		row.appendCellChild(new Space());

		row = new Row();
		form.getParameterPanel().getRows().appendChild(row);
		row.appendCellChild(lDocType.rightAlign());
		ZKUpdateUtil.setHflex(cmbDocType, "true");
		row.appendCellChild(cmbDocType);
		row.appendCellChild(new Space());
		row.appendCellChild(lDocAction.rightAlign());
		ZKUpdateUtil.setHflex(docAction.getComponent(),"true");
		row.appendCellChild(docAction.getComponent());
		row.appendCellChild(new Space());
		
		row = new Row();
		form.getParameterPanel().getRows().appendChild(row);
		row.appendCellChild(new Space());
		row.appendCellChild(isSelectZeroInvoices);
		row.appendCellChild(new Space());
		row.appendCellChild(lOrderType.rightAlign());
		row.appendCellChild(fOrderType.getComponent());
		
		row = new Row();
		form.getParameterPanel().getRows().appendChild(row);
		row.appendCellChild(new Space());
		row.appendCellChild(showConsignmentInvoices);
		row.appendCellChild(new Space());
		row.appendChild(lSalesOrder.rightAlign());
		row.appendChild(fSalesOrderNumber.getComponent());
	}	//	jbInit

	/**
	 *	Fill Picks.
	 *		Column_ID from C_Order
	 *  @throws Exception if Lookups cannot be initialized
	 */
	public void dynInit() throws Exception
	{
		MLookup orgL = MLookupFactory.get (Env.getCtx(), form.getWindowNo(), 0, 2163, DisplayType.TableDir);
		fOrg = new WTableDirEditor ("AD_Org_ID", false, false, true, orgL);
	//	lOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
		fOrg.addValueChangeListener(this);
		//
		MLookup bpL = MLookupFactory.get (Env.getCtx(), form.getWindowNo(), 0, 2762, DisplayType.Search);
		fBPartner = new WSearchEditor ("C_BPartner_ID", false, false, true, bpL);
	//	lBPartner.setText(Msg.translate(Env.getCtx(), "C_BPartner_ID"));
		fBPartner.addValueChangeListener(this);
		//      Document Action Prepared/ Completed
		lDocAction.setText(Msg.translate(Env.getCtx(), "DocAction"));
		MLookup docActionL = MLookupFactory.get(Env.getCtx(), form.getWindowNo(), 3495 /* C_Invoice.DocAction */,
				DisplayType.List, Env.getLanguage(Env.getCtx()), "DocAction", 135 /* _Document Action */,
				false, "AD_Ref_List.Value IN ('CO','PR')");
		docAction = new WTableDirEditor("DocAction", true, false, true,docActionL);
		docAction.setValue(DocAction.ACTION_Complete);
		// docAction.addValueChangeListener(this); // IDEMPIERE-768

//      Document Type Sales Order/Vendor RMA
        lDocType.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
        cmbDocType.addItem(new KeyNamePair(MOrder.Table_ID, Msg.translate(Env.getCtx(), "Order")));
        cmbDocType.addItem(new KeyNamePair(MRMA.Table_ID, Msg.translate(Env.getCtx(), "CustomerRMA")));
        cmbDocType.addActionListener(this);
        cmbDocType.setSelectedIndex(0);
        
        
        isSelectZeroInvoices.setText(Msg.getMsg(Env.getCtx(), "DS_DontShowZeroValue"));
        isSelectZeroInvoices.addActionListener(this);
        
        lOrderType.setText(Msg.translate(Env.getCtx(), "C_DocType_ID"));
        MLookup orderTypeL = MLookupFactory.get (Env.getCtx(), form.getWindowNo(), 0, MColumn.getColumn_ID(MOrder.Table_Name, MOrder.COLUMNNAME_C_DocTypeTarget_ID), DisplayType.TableDir);
        fOrderType = new WTableDirEditor ("C_DocType_ID", false, false, true, orderTypeL);
	//	lOrg.setText(Msg.translate(Env.getCtx(), "AD_Org_ID"));
        fOrderType.addValueChangeListener(this);
		
        showConsignmentInvoices.setText(Msg.getMsg(Env.getCtx(), "DS_ConsignmentInvoices"));
        showConsignmentInvoices.addActionListener(this);
        
        lSalesOrder.setText(Msg.getElement(Env.getCtx(), "DocumentNo"));
        fSalesOrderNumber = new WStringEditor ("DocumentNo", false, false, true, 10, 30, null, null);
        fSalesOrderNumber.addValueChangeListener(this);
        
        form.getStatusBar().setStatusLine(Msg.getMsg(Env.getCtx(), "InvGenerateSel"));//@@
	}	//	fillPicks

	/**
	 *  Query Info
	 */
	public void executeQuery()
	{
		KeyNamePair docTypeKNPair = cmbDocType.getSelectedItem().toKeyNamePair();
		executeQuery(docTypeKNPair, form.getMiniTable());
		form.getMiniTable().repaint();
		form.invalidate();
	}   //  executeQuery

	/**
	 *	Action Listener
	 *  @param e event
	 */
	public void onEvent(Event e)
	{
		if (log.isLoggable(Level.INFO)) log.info("Cmd=" + e.getTarget().getId());
		//
		if(cmbDocType.equals(e.getTarget()))
		{
		    form.postQueryEvent();
		    return;
		}
		if(isSelectZeroInvoices.equals(e.getTarget()))
		{
			m_DontShowZeroValue = isSelectZeroInvoices.isSelected();
			form.postQueryEvent();
			return;
		}
		if(showConsignmentInvoices.equals(e.getTarget()))
		{
			m_ShowConsignmentInvoices = showConsignmentInvoices.isSelected();
			if(m_ShowConsignmentInvoices)
			{
				docAction.setValue(DocAction.ACTION_Prepare);
				docAction.setReadWrite(false);
			}
			else
			{
				docAction.setValue(DocAction.ACTION_Complete);
				docAction.setReadWrite(true);
			}
				form.postQueryEvent();
			return;
		}
		
		//
		validate();
	}	//	actionPerformed

	public void validate()
	{
		String docActionSelected = (String)docAction.getValue();
		m_DontShowZeroValue = isSelectZeroInvoices.isSelected();

		if ( docActionSelected==null || docActionSelected.isEmpty() )
			throw new WrongValueException(docAction.getComponent(), Msg.translate(Env.getCtx(), "FillMandatory"));

		form.saveSelection();

		ArrayList<Integer> selection = getSelection();
		if (selection != null && selection.size() > 0 && isSelectionActive())
			form.generate();
		else
			form.dispose();
	}

	/**
	 *	Value Change Listener - requery
	 *  @param e event
	 */
	public void valueChange(ValueChangeEvent e)
	{
		if (log.isLoggable(Level.INFO)) log.info(e.getPropertyName() + "=" + e.getNewValue());
		if (e.getPropertyName().equals("AD_Org_ID"))
			m_AD_Org_ID = e.getNewValue();
		if (e.getPropertyName().equals("C_BPartner_ID"))
		{
			m_C_BPartner_ID = e.getNewValue();
			fBPartner.setValue(m_C_BPartner_ID);	//	display value
		}
		if (e.getPropertyName().equals("C_DocType_ID"))
			m_C_orderType_ID = e.getNewValue();
		
		if (e.getPropertyName().equals("DocumentNo"))
			m_SalesOrderDocNo = (String)e.getNewValue();
		
		form.postQueryEvent();
	}	//	vetoableChange

	/**************************************************************************
	 *	Generate Shipments
	 */
	public String generate()
	{
		KeyNamePair docTypeKNPair = (KeyNamePair)cmbDocType.getSelectedItem().toKeyNamePair();
		String docActionSelected = (String)docAction.getValue();
		return generate(form.getStatusBar(), docTypeKNPair, docActionSelected);
	}	//	generateShipments

	public ADForm getForm()
	{
		return form;
	}
}