package org.dsi.eventhandlers;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_R_Request;
import org.compiere.model.MRequest;
import org.compiere.model.MSysConfig;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

public class DSEventHandler extends AbstractEventHandler {

	CLogger log = CLogger.getCLogger(DSEventHandler.class);
	@Override
	protected void doHandleEvent(Event event) 
	{
		PO po = getPO(event);
		if(po instanceof MRequest)
		{
			int R_Request_ID = po.get_ID();
			MRequest request = new MRequest(Env.getCtx(), R_Request_ID, po.get_TrxName());
			int requestTypeID=MSysConfig.getIntValue("PURCHASE_REQUEST_TYPE_ID", 1000004);
			if(po.is_ValueChanged(I_R_Request.COLUMNNAME_SalesRep_ID));
			{
				if(request.getR_RequestType_ID()==requestTypeID)
				{
					int productEntryID=DB.getSQLValue(po.get_TrxName(), "SELECT coalesce(COUNT(*),0) FROM DS_Product_Request "
							+ "WHERE R_Request_ID=?",R_Request_ID);
					if(productEntryID==0 && request.getSalesRep_ID()!=request.getCreatedBy())
						throw new AdempiereException("Bro go back to product and enter atleast one product - not funny at all.");
				}
			}
			if(po.is_ValueChanged("DS_IsApproved"))
			{
				log.info(" User logged in "+Env.getAD_User_ID(Env.getCtx())+" created by "+request.getCreatedBy());
				if(Env.getAD_User_ID(Env.getCtx())==request.getCreatedBy())
					throw new AdempiereException("Hands up. gun fire . Dont approve your own funny request.");
				
			}
		}
	}

	@Override
	protected void initialize() {
			registerTableEvent(IEventTopics.PO_AFTER_CHANGE,MRequest.Table_Name);
			registerTableEvent(IEventTopics.PO_AFTER_NEW,MRequest.Table_Name);
	}

}
