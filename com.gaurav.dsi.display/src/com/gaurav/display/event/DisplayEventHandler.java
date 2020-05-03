package com.gaurav.display.event;

import java.util.Properties;
import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MDocType;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

public class DisplayEventHandler extends AbstractEventHandler
{

	String trxName = null;
	Properties ctx = Env.getCtx();
	MAcctSchema ass = null;
	CLogger log = CLogger.getCLogger(DisplayEventHandler.class);
	@Override
	protected void doHandleEvent(Event event) 
	{
		PO po = getPO(event);
		if(po instanceof MMovement)
		{
			MMovement mov = (MMovement)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW) || 
					(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE) && 
							(mov.is_ValueChanged(MMovement.COLUMNNAME_C_BPartner_ID) || mov.is_ValueChanged(MMovement.COLUMNNAME_C_BPartner_Location_ID))))
			{
				MDocType docType = new MDocType(ctx, mov.getC_DocType_ID(), trxName);
				if(docType.get_ValueAsBoolean("DS_IsUseCogsForMovement"))
				{
					int C_BPartner_ID = mov.getC_BPartner_ID();
					int C_BPartner_Location_ID = mov.getC_BPartner_Location_ID();
					int M_Warehouse_ID = DB.getSQLValue(trxName, "Select M_Warehouse_ID From M_Warehouse Where DS_IsUseCogsForMovement='Y' and DS_IsCustomerWarehouse='Y' ");
					int M_Locator_ID = DB.getSQLValue(trxName, "Select loc.M_Locator_ID From M_Locator loc "
							+ "where loc.C_BPartner_ID = ? and loc.C_BPartner_Location_ID = ? and loc.M_Warehouse_ID = ?  ",C_BPartner_ID,C_BPartner_Location_ID,M_Warehouse_ID);
					if(M_Locator_ID<=0)
					{
						
						MWarehouse warehouse = new MWarehouse(ctx, M_Warehouse_ID, trxName);
						MLocator loc = new MLocator(warehouse, mov.getC_BPartner().getValue().concat(" / ").concat(mov.getC_BPartner_Location().getName()));
						loc.set_ValueNoCheck("C_BPartner_ID", C_BPartner_ID);
						loc.set_ValueNoCheck("C_BPartner_Location_ID", C_BPartner_Location_ID);
						loc.setX(mov.getC_BPartner_Location().getName());
						loc.saveEx();
					}
				}
			}
		}
	}

	@Override
	protected void initialize() {

		registerTableEvent(IEventTopics.PO_AFTER_NEW, MMovement.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MMovement.Table_Name);
	}




}
