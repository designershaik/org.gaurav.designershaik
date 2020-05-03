// Not used
package org.dsi.crm.processes;

import org.compiere.model.MUser;
import org.compiere.process.SvrProcess;
import org.gaurav.dsi.model.MDSContactMaster;

public class CopyMasterContactDetails extends SvrProcess
{
	int AD_User_ID;
	int ContactMasterID;
	MUser mu;
	@Override
	protected void prepare() 
	{
		AD_User_ID=getRecord_ID();
		mu=new MUser(getCtx(), AD_User_ID, get_TrxName());
		ContactMasterID=mu.get_ValueAsInt("DS_ContactMaster_ID");
	}

	@Override
	protected String doIt() throws Exception 
	{
		
		MDSContactMaster contact=new MDSContactMaster(getCtx(), ContactMasterID, get_TrxName());
		mu.setName(contact.getName());
		mu.setPhone(contact.getPhone());
		mu.setPhone2(contact.getPhone2());
		mu.setEMail(contact.getEMail());
		mu.setTitle(contact.getTitle());
		mu.setBirthday(contact.getBirthday());
		mu.setFax(contact.getFax());
		mu.set_ValueOfColumn("DS_Phone_Ext1", contact.getDS_Phone_Ext1());
		mu.set_ValueOfColumn("DS_Phone_Ext2", contact.getDS_Phone_Ext2());
		mu.save();
		
		return "Details updated based on contact master";
	}

}
