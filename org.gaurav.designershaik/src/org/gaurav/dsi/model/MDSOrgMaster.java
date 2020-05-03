package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.I_C_BPartner;
import org.compiere.model.MBPartner;
import org.compiere.model.Query;

public class MDSOrgMaster extends X_DS_OrgMaster 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6567503586243630047L;

	public MDSOrgMaster(Properties ctx, int DS_OrgMaster_ID, String trxName) 
	{
		super(ctx, DS_OrgMaster_ID, trxName);
		
	}

	public MDSOrgMaster(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
		
	}
	public MDSOrgContactRelation[] getLines (int orgMasterID)
	{
		
		//red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		StringBuffer whereClauseFinal = new StringBuffer(MDSOrgContactRelation.COLUMNNAME_DS_OrgMaster_ID+"=? ");

		List<MDSOrgContactRelation> list = new Query(getCtx(), I_DS_OrgContactRelation.Table_Name, whereClauseFinal.toString(),
													get_TrxName()).
													setParameters(orgMasterID)
													.list();	
		return list.toArray(new MDSOrgContactRelation[list.size()]);	
	}	//	getLines
	/**************************************************************************
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return success
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (newRecord && success)
		{
			return success;
		}

		//	Value/Name change
		if (success && !newRecord)
		{
			MBPartner[] bpartner=getBPartners(getDS_OrgMaster_ID());
			for(int i=0;i<bpartner.length;i++)
			{
				int bpartnerID = bpartner[i].getC_BPartner_ID();
				MBPartner bp=new MBPartner(getCtx(), bpartnerID, get_TrxName());
				if(is_ValueChanged("Description"))
				{
					bp.setDescription(get_ValueAsString("Description"));	
				}
				if(is_ValueChanged("Name"))
				{
					bp.setName(getName());
				}
				if(is_ValueChanged("Value"))
				{
					bp.setValue(getValue());
				}
				if(is_ValueChanged("WebSite"))
				{
					bp.setURL(getWebSite());
				}
					bp.saveEx(get_TrxName());
			}
							
		}
		
		return success;
	}	//	afterSave
	private MBPartner[] getBPartners (int orgMasterID)
	{
		
		//red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		StringBuffer whereClauseFinal = new StringBuffer("C_BPartner.DS_OrgMaster_ID=? ");

		List<MBPartner> list = new Query(getCtx(), I_C_BPartner.Table_Name, whereClauseFinal.toString(),
													get_TrxName()).
													setParameters(orgMasterID)
													.list();	
		return list.toArray(new MBPartner[list.size()]);	
	}	//
}
