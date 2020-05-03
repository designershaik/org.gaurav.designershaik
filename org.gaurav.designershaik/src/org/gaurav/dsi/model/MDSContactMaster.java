package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.EMail;
import org.compiere.util.Msg;

public class MDSContactMaster extends X_DS_ContactMaster {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public MDSContactMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	
	public MDSContactMaster(Properties ctx, int DS_ContactMaster_ID,
			String trxName) {
		super(ctx, DS_ContactMaster_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	
	
	public MDSContactMasterLocation[] getLocations (int contactMasterID)
	{
		
		StringBuffer whereClauseFinal = new StringBuffer("DS_ContactMaster_ID=? ");

		List<MDSContactMasterLocation> list = new Query(getCtx(), I_DS_ContactMaster_Location.Table_Name, whereClauseFinal.toString(),get_TrxName()).setParameters(contactMasterID).list();	
		return list.toArray(new MDSContactMasterLocation[list.size()]);	
	}	
	
	protected boolean beforeSave (boolean newRecord)
	{
		if(getEMail()!=null)
		{
			if (! EMail.validate(getEMail())) 
			{
				log.saveError("SaveError", Msg.getMsg(getCtx(), "InvalidEMailFormat") + Msg.getElement(getCtx(), COLUMNNAME_EMail) + " - [" + getEMail() + "]");
				throw new AdempiereException("Email is not in valid format");
			}
		}
		return true;
	}
}
