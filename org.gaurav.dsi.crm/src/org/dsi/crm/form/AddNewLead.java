package org.dsi.crm.form;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.component.Borderlayout;
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
import org.adempiere.webui.editor.WNumberEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MColumn;
import org.compiere.model.MCountryGroup;
import org.compiere.model.MLocation;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MRequest;
import org.compiere.model.MUser;
import org.compiere.model.MRole;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.MCContactActivity;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Center;
import org.zkoss.zul.South;


/**
 * 
 * @author Elaine
 *
 */
public class AddNewLead extends Window implements EventListener<Event> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7757368164776005797L;
	
	/** Read Only				*/
	private boolean m_readOnly = false;
	CLogger log = CLogger.getCLogger(AddNewLead.class);
	private WTableDirEditor greetingField;
	private WTableDirEditor channelField;
	private WTableDirEditor salesRepField ;
	private Textbox name = null;
	private Textbox mobileNo =  new Textbox();
	private Textbox landLine =  new Textbox();
	private Textbox email =  new Textbox();
	private Textbox fax =  new Textbox();
	private Textbox position =  new Textbox();
	private WTableDirEditor country;
	private Textbox city = new Textbox();
	private Textbox addressLine =  new Textbox();
	private WTableDirEditor partnerType ;
	private Textbox bpName =  new Textbox();
	private WTableDirEditor leadStatus ;
	private WTableDirEditor leadSource ;
	private Textbox leadStatusDescription =  new Textbox();;
	private Textbox comments =  new Textbox();;
	private WNumberEditor noOfPos = new WNumberEditor();
	private WTableDirEditor outletType ;
	private WTableDirEditor countryGroup ;
	private ConfirmPanel confirmPanel;
	
	
	private WTableDirEditor leadField, activityTypeField;
	private Textbox txtSummary = null;
	private Datebox startDate = new Datebox();
	private Datebox endDate = new Datebox();
	
	public AddNewLead() {
		
		super();
		
		Properties ctx = Env.getCtx();
		setTitle(Msg.getMsg(Env.getCtx(),"Event"));
		setAttribute(Window.MODE_KEY, Window.MODE_OVERLAPPED);
		ZKUpdateUtil.setWindowWidthX(this, 1000);
		ZKUpdateUtil.setWindowHeightX(this, 700);
		this.setSclass("popup-dialog");
		this.setStyle("position: relative;");
		this.setBorder("normal");
		this.setShadow(true);
		this.setClosable(true);
		
		int lead_ID = Env.getContextAsInt(ctx, "DS_lead_ID");
		startDate.setValue(new Date());
		m_readOnly = !MRole.getDefault().canUpdate(
				Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx), 
				MUser.Table_ID, 0, false);
		Label lblGreetings           = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_C_Greeting_ID));
		Label lblBPType       = new Label(Msg.getElement(ctx, "DS_BPType"));
		Label lblName          = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_Name));
		Label lblContactAddress      = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_C_Location_ID));
		Label lblMobile          = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_Phone));
		Label lblPhone = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_Phone2));
		Label lblEmail          = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_EMail));
		Label lblFax = new Label(Msg.getElement(ctx, MUser.COLUMNNAME_Fax));
		Label lblPosition = new Label(Msg.getElement(ctx, "Position"));
		Label lblContactCity = new Label(Msg.getElement(ctx, "C_City_ID"));
		
		Label lblBPName = new Label(Msg.getElement(ctx, "BPName"));
		Label lblSalesRep = new Label(Msg.getElement(ctx, "SalesRep_ID"));
		Label lblLeadStatus = new Label(Msg.getElement(ctx, "LeadStatus"));
		Label lblLeadSource = new Label(Msg.getElement(ctx, "LeadSource"));
		Label lblLeadStatusDescription = new Label(Msg.getElement(ctx, "LeadStatusDescription"));
		Label lblComments = new Label(Msg.getElement(ctx, "Comments"));
		Label lblNoOfPOS = new Label(Msg.getElement(ctx, "DS_NoOfPOS"));
		Label lblOutletType = new Label(Msg.getElement(ctx, "DS_TypeOfOutlet"));
		Label lblCountryGroup = new Label(Msg.getElement(ctx, "C_CountryGroup_ID"));
		
		int columnID = MColumn.getColumn_ID(MUser.Table_Name, "DS_BPType");
		MLookup lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		partnerType = new WTableDirEditor("DS_BPType", true, false, true, lookup);
		if(partnerType.getValue() == null || partnerType.getValue().equals(""))
			if(partnerType.getComponent().getItemCount() > 1)
				partnerType.setValue(partnerType.getComponent().getItemAtIndex(1).getValue());
		
		columnID = MColumn.getColumn_ID(MUser.Table_Name, MUser.COLUMNNAME_C_Greeting_ID);
		MLookup lookupGreeting = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		greetingField = new WTableDirEditor("C_Greeting_ID", true, false, false, lookupGreeting);
		
		columnID = MColumn.getColumn_ID(MUser.Table_Name, "DS_Tags_ID");
		MLookup lookupChannels = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		channelField = new WTableDirEditor("DS_Tags_ID", true, false, true, lookupChannels);
		if(channelField.getValue() == null || channelField.getValue().equals(""))
			if(channelField.getComponent().getItemCount() > 1)
				channelField.setValue(channelField.getComponent().getItemAtIndex(1).getValue());
		
		columnID = MColumn.getColumn_ID(MUser.Table_Name, MUser.COLUMNNAME_SalesRep_ID);
		MLookup lookupsalesRep = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		salesRepField = new WTableDirEditor("SalesRep_ID", true, false, true, lookupsalesRep);
		salesRepField.setValue(Env.getContextAsInt(ctx, "SalesRep_ID"));
		if(salesRepField.getValue() == null || salesRepField.getValue().equals(0))
			if(salesRepField.getComponent().getItemCount() > 1)
				salesRepField.setValue(salesRepField.getComponent().getItemAtIndex(1).getValue());
		
		columnID = MColumn.getColumn_ID(MUser.Table_Name, "LeadStatus");
		MLookup lookupLeadStatus = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		leadStatus = new WTableDirEditor("LeadStatus", true, false, true, lookupLeadStatus);
		if(leadStatus.getValue() == null || leadStatus.getValue().equals(""))
			if(leadStatus.getComponent().getItemCount() > 1)
				leadStatus.setValue(leadStatus.getComponent().getItemAtIndex(1).getValue());

		columnID = MColumn.getColumn_ID(MUser.Table_Name, "LeadSource");
		MLookup lookupLeadSource = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		leadSource = new WTableDirEditor("LeadSource", true, false, true, lookupLeadSource);
		if(leadSource.getValue() == null || leadSource.getValue().equals(""))
			if(leadSource.getComponent().getItemCount() > 1)
				leadSource.setValue(leadSource.getComponent().getItemAtIndex(1).getValue());
		
		columnID = MColumn.getColumn_ID(MUser.Table_Name, "DS_TypeOfOutlet");
		MLookup lookupoutlettype = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		outletType = new WTableDirEditor("DS_TypeOfOutlet", true, false, true, lookupoutlettype);
		if(outletType.getValue() == null || outletType.getValue().equals(""))
			if(outletType.getComponent().getItemCount() > 1)
				outletType.setValue(outletType.getComponent().getItemAtIndex(1).getValue());
		
		
		columnID = MColumn.getColumn_ID(MLocation.Table_Name, MLocation.COLUMNNAME_C_Country_ID);
		MLookup lookupContactCountry = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		country = new WTableDirEditor("C_Country_ID", true, false, true, lookupContactCountry);
		country.setValue(Env.getContextAsInt(ctx, "C_Country_ID"));
		if(country.getValue() == null || country.getValue().equals(0))
			if(country.getComponent().getItemCount() > 1)
				country.setValue(country.getComponent().getItemAtIndex(1).getValue());
			
		columnID = MColumn.getColumn_ID(MCountryGroup.Table_Name, "C_CountryGroup_ID");
		MLookup lookupCountryGroup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		countryGroup = new WTableDirEditor("C_CountryGroup_ID", true, false, false, lookupCountryGroup);
		
		name = new Textbox();
		ZKUpdateUtil.setWidth(name, "95%");
		ZKUpdateUtil.setHeight(name, "100%");
		
		ZKUpdateUtil.setWidth(addressLine, "100%");
		ZKUpdateUtil.setHeight(addressLine, "100%");
		
		confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);
	
		/*
		 * Add activity form within adding lead
		 * 
		 */
		
		Label lblActivityType      = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_ContactActivityType));
		Label lblSummary           = new Label(Msg.getElement(ctx, MRequest.COLUMNNAME_Summary));
		Label lblStartDate = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_StartDate));
		Label lblEndDate = new Label(Msg.getElement(ctx, MCContactActivity.COLUMNNAME_EndDate));
		
		columnID = MColumn.getColumn_ID(MCContactActivity.Table_Name, MCContactActivity.COLUMNNAME_ContactActivityType);
		MLookup lookupActivity = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
		activityTypeField = new WTableDirEditor("ContactActivityType", true, false, true, lookupActivity);

		if(activityTypeField.getValue() == null || activityTypeField.getValue().equals(""))
			if(activityTypeField.getComponent().getItemCount() > 1)
				activityTypeField.setValue(activityTypeField.getComponent().getItemAtIndex(1).getValue());
		
		columnID = MColumn.getColumn_ID(MCContactActivity.Table_Name, MCContactActivity.COLUMNNAME_AD_User_ID);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.TableDir);
		leadField = new WTableDirEditor("AD_User_ID", true, false, true, lookup);
		leadField.setValue(lead_ID);
		if(leadField.getValue() == null || leadField.getValue().equals(""))
			if(leadField.getComponent().getItemCount() > 1)
				leadField.setValue(leadField.getComponent().getItemAtIndex(1).getValue());
				
		columnID = MColumn.getColumn_ID(MRequest.Table_Name, MRequest.COLUMNNAME_Priority);
		lookup = MLookupFactory.get(ctx, 0, 0, columnID, DisplayType.List);
	
		comments = new Textbox();
		ZKUpdateUtil.setWidth(comments, "95%");
		ZKUpdateUtil.setHeight(comments, "100%");
		
		txtSummary = new Textbox();
		ZKUpdateUtil.setWidth(txtSummary, "95%");
		ZKUpdateUtil.setHeight(txtSummary, "100%");
		
		confirmPanel = new ConfirmPanel(true);
		confirmPanel.addActionListener(this);
		/*
		 * 
		 */
		
		Grid grid = GridFactory.newGridLayout();
		
		Columns columns = new Columns();
		grid.appendChild(columns);
		
		Column column = new Column();
		columns.appendChild(column);
		
		column = new Column();
		columns.appendChild(column);
		ZKUpdateUtil.setWidth(column, "300px");
		
		Rows rows = new Rows();
		grid.appendChild(rows);
		
		Row row = new Row();
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblActivityType.rightAlign());
		row.appendChild(activityTypeField.getComponent());
		
		row.appendChild(lblSalesRep.rightAlign());
		row.appendChild(salesRepField.getComponent());
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblGreetings.rightAlign());
		row.appendChild(greetingField.getComponent());
			
		row.appendChild(lblBPType.rightAlign());
		row.appendChild(partnerType.getComponent());
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblName.rightAlign());
		row.appendChild(name);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblCountryGroup.rightAlign());
		row.appendChild(countryGroup.getComponent());
			
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblContactCity.rightAlign());
		row.appendChild(city);	
		row.appendChild(country.getComponent());
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblContactAddress.rightAlign());
		row.appendCellChild(addressLine);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblPhone.rightAlign());
		row.appendChild(landLine);
		
			
		row.appendChild(lblMobile.rightAlign());
		row.appendChild(mobileNo);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblEmail.rightAlign());
		row.appendChild(email);
			
		row.appendChild(lblFax.rightAlign());
		row.appendChild(fax);
			
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblPosition.rightAlign());
		row.appendChild(position);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblBPName.rightAlign());
		row.appendChild(bpName);
				
		row = new Row();
		rows.appendChild(row);
		
		leadStatus.setValue("N");
		row.appendChild(lblLeadStatus.rightAlign());
		row.appendChild(leadStatus.getComponent());
		
		row.appendChild(lblLeadSource.rightAlign());
		row.appendChild(leadSource.getComponent());
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblLeadStatusDescription.rightAlign());
		leadStatusDescription.setMultiline(true);
		row.appendChild(leadStatusDescription);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblNoOfPOS.rightAlign());
		row.appendChild(noOfPos.getComponent());
		
		row.appendChild(lblOutletType.rightAlign());
		row.appendChild(outletType.getComponent());
		
		/*
		 *  Adding activity within lead form
		 */
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblStartDate.rightAlign());
		row.appendChild(startDate);
		
		row.appendChild(lblEndDate.rightAlign());
		row.appendChild(endDate);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblSummary.rightAlign());
		row.appendChild(txtSummary);
		
		row = new Row();
		rows.appendChild(row);
		row.appendChild(lblComments.rightAlign());
		row.appendChild(comments);
		
		
		
		
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
		MUser user = null;
		int C_BPartner_ID = 0;
		
		if(name==null || name.getValue()==null || name.getValue().isEmpty())
			throw new AdempiereException("Mandatory : "+MUser.COLUMNNAME_Name);
			
		if(greetingField.getValue()==null)
			throw new AdempiereException("Mandatory	: "+Msg.getElement(Env.getCtx(), "C_Greeting_ID"));
		
		if(countryGroup.getValue()==null)
			throw new AdempiereException("Mandatory	: "+Msg.getElement(Env.getCtx(), "C_CountryGroup_ID"));
		
//		if(email==null || email.getValue()==null || email.getValue().isEmpty())
//			throw new AdempiereException("Mandatory : "+MUser.COLUMNNAME_EMail);
		
		if(bpName==null || bpName.getValue()==null || bpName.getValue().isEmpty())
			throw new AdempiereException("Mandatory : "+Msg.getElement(Env.getCtx(), "BPName"));
			
//		if(mobileNo==null || mobileNo.getValue()==null || mobileNo.getValue().isEmpty())
//			throw new AdempiereException("Mandatory : "+Msg.getElement(Env.getCtx(), "Phone"));
			
//		if(position==null || position.getValue()==null || position.getValue().isEmpty())
//			throw new AdempiereException("Mandatory : "+Msg.getElement(Env.getCtx(), "Position"));
		
//		if(city==null || city.getValue()==null || city.getValue().isEmpty())
//			throw new AdempiereException("Mandatory Contact : "+Msg.getElement(Env.getCtx(), "C_City_ID"));
			
//		if(!EMail.validate(email.getValue()))
//		{
//			log.saveError("SaveError", Msg.getMsg(Env.getCtx(), "InvalidEMailFormat") + Msg.getElement(Env.getCtx(), "EMail") + " - [" + email.getValue() + "]");
//			throw new AdempiereException("InvalidEMailFormat : "+Msg.getElement(Env.getCtx(), "Email")+" : "+email.getValue());
//		}
		int count = DB.getSQLValue(null, "Select count(*) From AD_User Where UPPER(Email) like ? ",email.getValue().toUpperCase());
		if(count>0)
		{
			log.saveError("SaveError", Msg.getMsg(Env.getCtx(), "AlreadyExists") + Msg.getElement(Env.getCtx(), "EMail") + " - [" + email.getValue() + "]");
			throw new AdempiereException("AlreadyExist : "+Msg.getElement(Env.getCtx(), "Email")+" : "+email.getValue());
		}
			
		C_BPartner_ID = DB.getSQLValue(null, "Select C_BPartner_ID From C_BPartner Where (Trim(UPPER(Value)) like ? or Trim(UPPER(Name)) like ?)  ",bpName.getValue().toUpperCase().trim(),bpName.getValue().toUpperCase().trim());
		if(C_BPartner_ID>0)
		{
			
			log.saveError("SaveError", Msg.getMsg(Env.getCtx(), "AlreadyExists") + Msg.getElement(Env.getCtx(), "Name") + " - [" + bpName.getValue() + "]");
			throw new AdempiereException("AlreadyExist : "+Msg.getElement(Env.getCtx(), "Name")+" : "+bpName.getValue());
		}
			
		count = DB.getSQLValue(null, "Select count(*) From AD_User Where Trim(UPPER(BPName)) like ? ",bpName.getValue().toUpperCase().trim());
		if(count>0)
		{
			log.saveError("SaveError", Msg.getMsg(Env.getCtx(), "AlreadyExists") + Msg.getElement(Env.getCtx(), "Name") + " - [" + bpName.getValue() + "]");
			throw new AdempiereException("AlreadyExist : "+Msg.getElement(Env.getCtx(), "Name")+" : "+bpName.getValue());
		}
		
		int C_Greeting_ID = greetingField.getValue()==null ? null:Integer.parseInt(greetingField.getValue().toString());
		int DS_NoOfPOS = noOfPos.getValue()==null ? 0 : Integer.parseInt(noOfPos.getValue().toString()); 
		
		user = new MUser(Env.getCtx(), 0, null);
		user.setName(name.getValue());
		user.setValue(bpName.getValue());
		user.setC_Greeting_ID(C_Greeting_ID);
		user.set_ValueNoCheck("DS_BPType", partnerType.getValue());
		
		if(city.getValue()!=null && addressLine.getValue()!=null)
		{
			MLocation contactLoc = new MLocation(Env.getCtx(), Integer.parseInt(country.getValue().toString()), 0, city.getValue(), null);
			contactLoc.setAddress1(addressLine.getValue());
			contactLoc.saveEx();
			user.setC_Location_ID(contactLoc.getC_Location_ID());
			user.setBP_Location_ID(contactLoc.getC_Location_ID());
		}
		
		user.setPhone(mobileNo.getValue());
		user.setPhone2(landLine.getValue());
		user.setEMail(email.getValue());
		user.setFax(fax.getValue());
		user.set_ValueNoCheck("Position", position.getValue());
		user.set_ValueNoCheck("BPName", bpName.getValue());
		user.setIsSalesLead(true);			
		user.setLeadStatus(leadStatus.getValue().toString());
		user.setLeadSource(leadSource.getValue().toString());
		user.setLeadSourceDescription(leadStatusDescription.getValue());
		user.setComments(comments.getValue());
		user.set_ValueNoCheck("DS_NoOfPOS", DS_NoOfPOS);
		user.set_ValueNoCheck("DS_TypeOfOutlet", outletType.getValue());
		user.set_ValueNoCheck("C_CountryGroup_ID",countryGroup.getValue());
		user.saveEx();
		
		MCContactActivity activity = new MCContactActivity(Env.getCtx(), 0, null);
		activity.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx()));
		activity.setContactActivityType((String) activityTypeField.getValue());
		activity.setAD_User_ID(user.getAD_User_ID());
		activity.setDescription(txtSummary.getRawValue().toString());
		activity.setSalesRep_ID((Integer) salesRepField.getValue());
		activity.setComments(comments.getRawValue().toString());
		activity.setStartDate(new Timestamp(startDate.getValue().getTime()));
		if(endDate.getValue()!=null)
			activity.setStartDate(new Timestamp(endDate.getValue().getTime()));
			
		if (activity.save())
		{
			if (log.isLoggable(Level.FINE)) log.fine("R_Request_ID=" + activity.getC_ContactActivity_ID());
		}
		else
		{
			user.delete(true);
			FDialog.error(0, this, "Request record not saved");
			return;
		}
		this.detach();
	}
}
