package org.dsi.crm.processes;

import java.util.List;
import java.util.Properties;

import org.compiere.model.I_AD_User;
import org.compiere.model.I_C_BPartner;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MUser;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.gaurav.dsi.model.MDSContactMaster;
import org.gaurav.dsi.model.MDSOrgContactRelation;
import org.gaurav.dsi.model.MDSOrgMaster;
import org.gaurav.dsi.model.MDSOrgMasterLocation;

public class MigrateBPToOrgMasters extends SvrProcess {

	@Override
	protected void prepare() {
	}

	@Override
	protected String doIt() throws Exception
	{
		List<MBPartner> bp=new Query(getCtx(), I_C_BPartner.Table_Name, " C_BP_Group_ID not in (1000005,1000001,1000011) ", get_TrxName()).list();
		int i=0;
		for(MBPartner partner:bp)
		{
			i++;
			MDSOrgMaster org=new MDSOrgMaster(getCtx(), 0, get_TrxName());
			org.setName(partner.getName());
			org.setWebSite(partner.getURL());
			org.setValue(partner.getValue());
			org.set_ValueOfColumn("Description", partner.getDescription());
			org.setIsActive(partner.isActive());
			org.set_ValueOfColumn("C_BPartner_ID", partner.getC_BPartner_ID());
			org.save();
			partner.set_ValueOfColumn("DS_OrgMaster_ID", org.getDS_OrgMaster_ID());
			partner.save(get_TrxName());
			MBPartnerLocation[] bpLocation=MBPartnerLocation.getForBPartner(getCtx(), partner.getC_BPartner_ID(), get_TrxName());
			for(MBPartnerLocation loc:bpLocation)
			{
				MDSOrgMasterLocation orgloc=new MDSOrgMasterLocation(getCtx(), 0, get_TrxName());
				orgloc.setDS_OrgMaster_ID(org.getDS_OrgMaster_ID());
				orgloc.setPhone(loc.getPhone());
				orgloc.setPhone2(loc.getPhone2());
				orgloc.setFax(loc.getFax());
				orgloc.setC_Location_ID(loc.getC_Location_ID());
				orgloc.setName(loc.getName());
				orgloc.setC_SalesRegion_ID(loc.getC_SalesRegion_ID());
				orgloc.setIsActive(loc.isActive());
				orgloc.set_ValueOfColumn("C_BPartner_Location_ID", loc.getC_BPartner_Location_ID());
				orgloc.save(get_TrxName());
				MUser[] userfl=getOfBPartner(getCtx(), partner.getC_BPartner_ID(),get_TrxName(), loc.getC_BPartner_Location_ID());
				for(MUser uloop:userfl)
				{
					MDSContactMaster contMaster=new MDSContactMaster(getCtx(), 0, get_TrxName());
					contMaster.setName(uloop.getName());
					contMaster.setPhone(uloop.getPhone());
					contMaster.setPhone2(uloop.getPhone2());
					contMaster.setBirthday(uloop.getBirthday());
					contMaster.setEMail(uloop.getEMail());
					contMaster.setFax(uloop.getFax());
					contMaster.setTitle(uloop.getTitle());
					contMaster.setValue(uloop.getName());
					contMaster.set_ValueOfColumn("AD_User_ID", uloop.getAD_User_ID());
					contMaster.save(get_TrxName());
					MDSOrgContactRelation orgtocont=new MDSOrgContactRelation(getCtx(), 0, get_TrxName());
					orgtocont.setDS_ContactMaster_ID(contMaster.getDS_ContactMaster_ID());
					orgtocont.setDS_OrgMaster_ID(org.getDS_OrgMaster_ID());
					orgtocont.setTitle(uloop.getTitle());
					orgtocont.set_ValueOfColumn("DS_OrgMaster_Location_ID", orgloc.getDS_OrgMaster_Location_ID());
					orgtocont.save(get_TrxName());
				}
			}
			MUser[] user= getOfBPartnerWithNoLoc(getCtx(), partner.getC_BPartner_ID(),get_TrxName());
			for(MUser userloop:user)
			{
				MDSContactMaster contMaster=new MDSContactMaster(getCtx(), 0, get_TrxName());
				contMaster.setName(userloop.getName());
				contMaster.setPhone(userloop.getPhone());
				contMaster.setPhone2(userloop.getPhone2());
				contMaster.setBirthday(userloop.getBirthday());
				contMaster.setEMail(userloop.getEMail());
				contMaster.setFax(userloop.getFax());
				contMaster.setTitle(userloop.getTitle());
				contMaster.setValue(userloop.getValue());
				contMaster.set_ValueOfColumn("AD_User_ID", userloop.getAD_User_ID());
				contMaster.save(get_TrxName());
				MDSOrgContactRelation orgtocont=new MDSOrgContactRelation(getCtx(), 0, get_TrxName());
				orgtocont.setDS_ContactMaster_ID(contMaster.getDS_ContactMaster_ID());
				orgtocont.setDS_OrgMaster_ID(org.getDS_OrgMaster_ID());
				orgtocont.setTitle(userloop.getTitle());
				orgtocont.save(get_TrxName());
			}
		}
		return "Migrated Records ......"+i;
	}
	
	
	private static MUser[] getOfBPartner (Properties ctx, int C_BPartner_ID, String trxName,int C_BPartner_Location_ID)
	{
		List<MUser> list = new Query(ctx, I_AD_User.Table_Name, "C_BPartner_ID=? and C_BPartner_Location_ID=?", trxName)
		.setParameters(C_BPartner_ID,C_BPartner_Location_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MUser[] retValue = new MUser[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfBPartner

	private static MUser[] getOfBPartnerWithNoLoc (Properties ctx, int C_BPartner_ID, String trxName)
	{
		List<MUser> list = new Query(ctx, I_AD_User.Table_Name, "C_BPartner_ID=? and C_BPartner_Location_ID is null ", trxName)
		.setParameters(C_BPartner_ID)
		.setOnlyActiveRecords(true)
		.list();
		
		MUser[] retValue = new MUser[list.size ()];
		list.toArray (retValue);
		return retValue;
	}	//	getOfBPartnerWithNoLoc
}
