/******************************************************************************
 * Copyright (C) 2008 Elaine Tan                                              *
 * Copyright (C) 2008 Idalica Corporation                                     *
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
package org.dsi.crm.form;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Datebox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MRequest;
import org.compiere.model.MRole;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.MCContactActivity;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Center;
import org.zkoss.zul.South;
import org.zkoss.zul.Timebox;


/**
 * 
 * @author Elaine
 *
 */
public class AddActivity extends Window implements EventListener<Event>,ValueChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7757368164776005797L;

	private static CLogger log = CLogger.getCLogger(AddActivity.class);
	
	/** Read Only				*/
	private boolean m_readOnly = false;
	private WTableDirEditor leadField, activityTypeField, salesRepField,bpartnerField;
	private Textbox txtSummary = null;
	private Textbox comments = null;
	private ConfirmPanel confirmPanel;
	private Datebox startDate = new Datebox();
	private Datebox endDate = new Datebox();
	
	private Textbox txtUserName = null;
	private Textbox txtEmail = null;
	private Textbox txtPhone = null;
	
	private WTableDirEditor user ;
	private Checkbox addFollowUp = new Checkbox();
	private Datebox followupDate = new Datebox();
	private Timebox timeFence = new Timebox();
	
	public AddActivity() throws Exception {
		
		super();
		
		
		Properties ctx = Env.getCtx();
		Language language = Language.getLoginLanguage();
		int lead_ID = Env.getContextAsInt(ctx, "DS_lead_ID");
		int C_BPartner_ID = Env.getContextAsInt(ctx, "DS_BPartner_ID");
		int Selected_User_ID = Env.getContextAsInt(ctx, "Selected_User_ID");
		startDate.setValue(new Date());
		lead_ID = lead_ID==0 ? -1:lead_ID;
		setTitle(Msg.getMsg(Env.getCtx(),"Activity"));
		setAttribute(Window.MODE_KEY, Window.MODE_OVERLAPPED);
		
		ZKUpdateUtil.setWindowWidthX(this, 700);
		ZKUpdateUtil.setWindowHeightX(this, 500);
		
		this.setSclass("popup-dialog");
		this.setStyle("position: relative;");
		this.setBorder("normal");
		this.setShadow(true);
		this.setClosable(true);
		
		m_readOnly = !MRole.getDefault().canUpdate(
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), 
				MRequest.Table_ID, 0, false);

		Label lblActivityType           = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_ContactActivityType));
		Label lblLeadName       = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_AD_User_ID));
		Label lblSummary           = new Label(Msg.getElement(ctx, MRequest.COLUMNNAME_Summary));
		Label lblSalesRep          = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_SalesRep_ID));
		Label lblCustomer          = new Label(Msg.getElement(ctx, "C_BPartner_ID"));
		Label lblComments          = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_Comments));
		Label lblStartDate = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_StartDate));
		Label lblEndDate = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_EndDate));
		
		Label lblUser = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_AD_User_ID));
		Label lblUserName = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_Name));
		Label lblUserEMail = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_EMail));
		Label lblisFollowUp = new Label(Msg.getElement(ctx, "IsFollowupRequired"));
		Label lblfollowUpDate = new Label(Msg.getElement(ctx, "DateFollowUp"));
		Label lblfollowUpTime = new Label(Msg.getElement(ctx, "TimeFence"));
		Label lblPhone = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_Phone));
		
		addFollowUp.addActionListener(this);
		int columnID = MColumn.getColumn_ID(MCContactActivity.Table_Name, MCContactActivity.COLUMNNAME_ContactActivityType);
		MLookup lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		activityTypeField = new WTableDirEditor("ContactActivityType", true, false, true, lookup);
		if(activityTypeField.getValue() == null || activityTypeField.getValue().equals(""))
			if(activityTypeField.getComponent().getItemCount() > 1)
				activityTypeField.setValue(activityTypeField.getComponent().getItemAtIndex(1).getValue());
		
		columnID = MColumn.getColumn_ID(MCContactActivity.Table_Name, MCContactActivity.COLUMNNAME_AD_User_ID);
		if(C_BPartner_ID>0)
			lookup = MLookupFactory.get(ctx, 0, columnID, DisplayType.TableDir, language, MUser.COLUMNNAME_AD_User_ID, 0, false, "C_BPartner_ID ="+C_BPartner_ID);
		else
			lookup = MLookupFactory.get(ctx, 0, columnID, DisplayType.TableDir, language, MUser.COLUMNNAME_AD_User_ID, 0, false, "AD_User_ID ="+lead_ID);
		
		user = new WTableDirEditor("AD_User_ID", true, false, true, lookup);
		if(Selected_User_ID>0)
			user.setValue(Selected_User_ID);
		
		user.addValueChangeListener(this);
		columnID = MColumn.getColumn_ID(MCContactActivity.Table_Name, MCContactActivity.COLUMNNAME_AD_User_ID);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		leadField = new WTableDirEditor("AD_User_ID", true, false, true, lookup);
		leadField.setValue(lead_ID);
		
				
		columnID = MColumn.getColumn_ID(MRequest.Table_Name, MRequest.COLUMNNAME_Priority);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		
		
		columnID = MColumn.getColumn_ID(MRequest.Table_Name, MRequest.COLUMNNAME_ConfidentialType);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		
		columnID = MColumn.getColumn_ID(MRequest.Table_Name, MRequest.COLUMNNAME_SalesRep_ID);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		salesRepField = new WTableDirEditor("SalesRep_ID", true, false, true, lookup);
		salesRepField.setValue(Env.getContextAsInt(ctx, "SalesRep_ID"));
		if(salesRepField.getValue() == null || salesRepField.getValue().equals(0))
			if(salesRepField.getComponent().getItemCount() > 1)
				salesRepField.setValue(salesRepField.getComponent().getItemAtIndex(1).getValue());
		
		columnID = MColumn.getColumn_ID(MRequest.Table_Name, MRequest.COLUMNNAME_C_BPartner_ID);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		bpartnerField = new WTableDirEditor("C_BPartner_ID", true, false, true, lookup);
		bpartnerField.setValue(C_BPartner_ID);
		
		columnID = MColumn.getColumn_ID(MRequest.Table_Name, MRequest.COLUMNNAME_ConfidentialTypeEntry);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		comments = new Textbox();
		ZKUpdateUtil.setWidth(comments, "95%");
		ZKUpdateUtil.setHeight(comments, "100%");
		
		txtSummary = new Textbox();
		ZKUpdateUtil.setWidth(txtSummary, "95%");
		ZKUpdateUtil.setHeight(txtSummary, "100%");
		
		txtUserName = new Textbox();
		ZKUpdateUtil.setWidth(txtUserName, "95%");
		ZKUpdateUtil.setHeight(txtUserName, "100%");
		
		txtEmail = new Textbox();
		ZKUpdateUtil.setWidth(txtEmail, "95%");
		ZKUpdateUtil.setHeight(txtEmail, "100%");
		
		txtPhone = new Textbox();
		ZKUpdateUtil.setWidth(txtPhone, "95%");
		ZKUpdateUtil.setHeight(txtPhone, "100%");
		
		confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);
		
		
		Grid grid = GridFactory.newGridLayout();
		
		Columns columns = new Columns();
		grid.appendChild(columns);
		
		Column column = new Column();
		columns.appendChild(column);
		
		column = new Column();
		columns.appendChild(column);
		ZKUpdateUtil.setWidth(column, "250px");
		
		Rows rows = new Rows();
		grid.appendChild(rows);
		
		Row row = new Row();
		
		rows.appendChild(row);
		row.appendChild(lblSalesRep.rightAlign());
		row.appendChild(salesRepField.getComponent());
		
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblActivityType.rightAlign());
		row.appendChild(activityTypeField.getComponent());
		
	
		
		if(lead_ID>0)
		{
			row = new Row();
			rows.appendChild(row);
			row.appendChild(lblLeadName.rightAlign());
			row.appendChild(leadField.getComponent());
		}
		if(C_BPartner_ID>0)
		{
			row = new Row();
			rows.appendChild(row);
			row.appendChild(lblCustomer.rightAlign());
			row.appendChild(bpartnerField.getComponent());
		}
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblUser.rightAlign());
		if(C_BPartner_ID>0 && Selected_User_ID>0)
			user.setReadWrite(false);
		row.appendChild(user.getComponent());
		
		if(Selected_User_ID==0 && lead_ID<=0)
		{
			row = new Row();
			rows.appendChild(row);
			row.appendChild(lblUserName.rightAlign());
			row.appendChild(txtUserName);
			
			row = new Row();
			rows.appendChild(row);
			row.appendChild(lblUserEMail.rightAlign());
			row.appendChild(txtEmail);
			
			row.appendChild(lblPhone.rightAlign());
			row.appendChild(txtPhone);
		}
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblSummary.rightAlign());
		row.appendChild(txtSummary);
		
				
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblComments.rightAlign());
		row.appendChild(comments);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblStartDate.rightAlign());
		row.appendChild(startDate);
		
		row.appendChild(lblEndDate.rightAlign());
		row.appendChild(endDate);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblisFollowUp.rightAlign());
		row.appendChild(addFollowUp);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblfollowUpDate.rightAlign());
		row.appendChild(followupDate);
		
		row.appendChild(lblfollowUpTime.rightAlign());
		row.appendChild(timeFence);
		
		
		Borderlayout borderlayout = new Borderlayout();
		this.appendChild(borderlayout);
		ZKUpdateUtil.setHflex(borderlayout, "1");
		ZKUpdateUtil.setVflex(borderlayout, "1");
		
		Center centerPane = new Center();
		centerPane.setSclass("dialog-content");
		centerPane.setAutoscroll(true);
		borderlayout.appendChild(centerPane);
		
		centerPane.appendChild(grid);
		ZKUpdateUtil.setVflex(grid, "min");
		ZKUpdateUtil.setHflex(grid, "1");
		ZKUpdateUtil.setVflex(centerPane, "min");

		South southPane = new South();
		southPane.setSclass("dialog-footer");
		borderlayout.appendChild(southPane);
		southPane.appendChild(confirmPanel);
	}
	
	public void onEvent(Event e) throws Exception {
		if (m_readOnly)
			this.detach();
		else if (e.getTarget() == confirmPanel.getButton(ConfirmPanel.A_OK)) 
		{
			saveRequest();
		}
		else if (e.getTarget() == confirmPanel.getButton(ConfirmPanel.A_CANCEL))
			this.detach();
	}

	private void saveRequest()
	{	
		MUser newUser = null;
		MCContactActivity activity = new MCContactActivity(Env.getCtx(), 0, null);
		activity.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx()));
		activity.setContactActivityType((String) activityTypeField.getValue());
		
		if(leadField.getValue()!=null)
			activity.setAD_User_ID((Integer) leadField.getValue());
		
		if(bpartnerField.getValue()!=null)
			activity.set_ValueOfColumn("C_BPartner_ID", (Integer) bpartnerField.getValue());
		
		if(bpartnerField.getValue()!=null && user.getValue()==null && txtUserName!=null)
		{
			newUser = new MUser(Env.getCtx(), 0, null);
			newUser.setC_BPartner_ID((Integer)bpartnerField.getValue());
			newUser.setName(txtUserName.getValue());
			newUser.setValue(txtUserName.getValue());
			if(txtEmail.getValue()!=null && EMail.validate(txtEmail.getValue()))
				newUser.setEMail(txtEmail.getValue());
			
			if(txtPhone.getValue()!=null)
				newUser.setPhone(txtPhone.getValue());
			newUser.saveEx();
		}
		
		activity.setDescription(txtSummary.getRawValue().toString());
		activity.setSalesRep_ID((Integer) salesRepField.getValue());
		activity.setComments(comments.getRawValue().toString());
		activity.setStartDate(new Timestamp(startDate.getValue().getTime()));
		activity.set_ValueNoCheck("IsFollowupRequired", addFollowUp.isChecked());
		if(newUser!=null)
			activity.setAD_User_ID(newUser.getAD_User_ID());

		if(user.getValue()!=null && bpartnerField.getValue()!=null)
			activity.setAD_User_ID((Integer)user.getValue());
		
		
		if(followupDate.getValue()!=null)
			activity.set_ValueNoCheck("DateFollowUp", new Timestamp(followupDate.getValue().getTime()));
		
		if(timeFence.getValue()!=null)
			activity.set_ValueNoCheck("TimeFence", new Timestamp(timeFence.getValue().getTime()));
		
		if(endDate.getValue()!=null)
			activity.setStartDate(new Timestamp(endDate.getValue().getTime()));
		
		if (activity.save())
		{
			if (log.isLoggable(Level.FINE)) log.fine("C_ContactActivity_ID=" + activity.getC_ContactActivity_ID());
		}
		else
		{
			FDialog.error(0, this, "Activity record not saved");
			return;
		}
		
		this.detach();
	}

	@Override
	public void valueChange(ValueChangeEvent e) 
	{
		String name = e.getPropertyName();
		Object value = e.getNewValue();
		if (value == null)
		{
			txtUserName.setValue(null);
			txtEmail.setValue(null);
			txtPhone.setValue(null);
			txtUserName.setReadonly(false);
			txtEmail.setReadonly(false);
			txtPhone.setReadonly(false);
			return;
		}
		// Organization
		if (name.equals("AD_User_ID"))
		{
			Integer AD_User_ID = ((Integer) value).intValue();
			if(AD_User_ID!=null || AD_User_ID>0)
			{
				MUser user = new MUser(Env.getCtx(),AD_User_ID,null);
				txtUserName.setValue(user.getName());
				txtEmail.setValue(user.getEMail());
				txtPhone.setValue(user.getPhone());
				txtUserName.setReadonly(true);
				txtEmail.setReadonly(true);
				txtPhone.setReadonly(true);
			}
		}
	}
}
