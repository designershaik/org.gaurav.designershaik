package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MBPartnerLocation;

public class MDSOrgMasterLocation extends X_DS_OrgMaster_Location {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6359135390813986924L;

	public MDSOrgMasterLocation(Properties ctx, int DS_OrgMaster_Location_ID,
			String trxName) {
		super(ctx, DS_OrgMaster_Location_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSOrgMasterLocation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (success && !newRecord)
		{
			int partnerLocationID=get_ValueAsInt("C_BPartner_Location_ID");
			MBPartnerLocation location=new MBPartnerLocation(getCtx(), partnerLocationID, get_TrxName());
			if(is_ValueChanged("Phone"))
				location.setPhone(getPhone());
			if(is_ValueChanged("Phone2"))
				location.setPhone2(getPhone2());
			if(is_ValueChanged("Fax"))
				location.setFax(getFax());
			if(is_ValueChanged("DS_Phone_Ext1"))
				location.set_ValueOfColumn("DS_Phone_Ext1",getDS_Phone_Ext1());
			if(is_ValueChanged("DS_Phone_Ext2"))
				location.set_ValueOfColumn("DS_Phone_Ext2",getDS_Phone_Ext2());
			if(is_ValueChanged("C_SalesRegion_ID"))
				location.setC_SalesRegion_ID(getC_SalesRegion_ID());
			location.save(get_TrxName());
		}
		
		return success;
	}	//	afterSave
}
