package org.dsi.crm.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.adempiere.exceptions.DBException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSContactMaster;
import org.gaurav.dsi.model.MDSContactMasterLocation;

public class MigrateUsersToContactMaster extends SvrProcess{
	
	int C_BPartner_ID;
	int i=0;

	@Override
	protected void prepare() 
	{
				
	}

	@Override
	protected String doIt() throws Exception 
	{
		PreparedStatement pstmt;
		ResultSet rs;
		String sql="select bp.C_BPARTNER_ID from c_bpartner bp where bp.DS_CONTACTMASTER_ID is null and bp.DS_ORGMASTER_ID is null and bp.ad_client_id=1000000 ";
		pstmt=DB.prepareStatement(sql, get_TrxName());
		rs=pstmt.executeQuery();
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				i++;
				C_BPartner_ID=rs.getInt("C_BPARTNER_ID");
				log.info("user ID"+C_BPartner_ID);
				MBPartner partner=new MBPartner(getCtx(), C_BPartner_ID, get_TrxName());
				MDSContactMaster contactMaster=new MDSContactMaster(getCtx(), 0, get_TrxName());
				contactMaster.setName(partner.getName());
				contactMaster.setValue(partner.getName());
				contactMaster.set_ValueOfColumn("C_BPartner_ID", partner.getC_BPartner_ID());
				contactMaster.setIsActive(partner.isActive());
				contactMaster.save(get_TrxName());
				partner.set_ValueOfColumn("DS_ContactMaster_ID", contactMaster.getDS_ContactMaster_ID());
				partner.save(get_TrxName());
				MBPartnerLocation[] location=MBPartnerLocation.getForBPartner(getCtx(), C_BPartner_ID,get_TrxName());
				for(MBPartnerLocation loc:location)
				{
					MDSContactMasterLocation contactlocation=new MDSContactMasterLocation(getCtx(), 0, get_TrxName());
					contactlocation.setDS_ContactMaster_ID(contactMaster.getDS_ContactMaster_ID());
					contactlocation.setPhone(loc.getPhone());
					contactlocation.setPhone2(loc.getPhone2());
					contactlocation.setFax(loc.getFax());
					contactlocation.setC_Location_ID(loc.getC_Location_ID());
					contactlocation.setName(loc.getName());
					contactlocation.setC_SalesRegion_ID(loc.getC_SalesRegion_ID());
					contactlocation.setIsActive(loc.isActive());
					contactlocation.set_ValueOfColumn("C_BPartner_Location_ID", loc.getC_BPartner_Location_ID());
					contactlocation.save(get_TrxName());
				}
			}
		}
		catch (SQLException e)
		{
			throw new DBException(e, sql);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		return "no of records"+i;
	}

}
