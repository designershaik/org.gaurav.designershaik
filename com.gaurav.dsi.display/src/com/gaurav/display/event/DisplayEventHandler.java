package com.gaurav.display.event;

import java.util.Properties;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.I_M_Movement;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAssetAddition;
import org.compiere.model.MDocType;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMatchInv;
import org.compiere.model.MMovement;
import org.compiere.model.MProductCategory;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.osgi.service.event.Event;

import com.gaurav.display.model.MDSMovementLine;

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
		if(po instanceof MMovement)
		{
			MMovement mov = (MMovement)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_COMPLETE))
			{
				int[] movementLineID = DB.getIDsEx(trxName, "Select M_MovementLine_ID From M_MovementLine Where M_Movement_ID = ? ", mov.getM_Movement_ID());
				
				for(int line_ID : movementLineID)
				{
					MDSMovementLine line = new MDSMovementLine(ctx, line_ID, trxName);
					if(line.get_ValueAsBoolean("IsGenerated"))
						log.info("Already generated: "+line.getLine());
					else
						line.generateAssetAndTransferDisplay(line,trxName);
				}
			}
		}
//		if(po instanceof MMatchInv)
//		{
//			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW))
//			{
//				MMatchInv matchInv = (MMatchInv)po;
//				int assetGroup_ID = DB.getSQLValue(trxName, "select cat.DS_AssetGroup_ID from m_product prod,m_product_category cat where prod.M_Product_Category_ID = cat.M_Product_Category_ID and prod.M_Product_ID = ? ",matchInv.getM_Product_ID());
//				if(matchInv.getC_InvoiceLine_ID()>0)
//				{
//					MInvoiceLine line = (MInvoiceLine)matchInv.getC_InvoiceLine();
//					line.setA_CapvsExp(MInvoiceLine.A_CAPVSEXP_Capital);
//					line.setA_Asset_Group_ID(assetGroup_ID);
//					line.saveEx();
//				}
//				if(matchInv.getM_Product().getM_Product_Category_ID()>0)
//				{
//					MProductCategory cat = new MProductCategory(Env.getCtx(),matchInv.getM_Product().getM_Product_Category_ID(),matchInv.get_TrxName());
//					cat.setA_Asset_Group_ID(assetGroup_ID);
//					cat.saveEx();
//				}
//				
//				if(assetGroup_ID>0)
//					MAssetAddition.createAsset(matchInv);
//				
//				if(matchInv.getM_Product().getM_Product_Category_ID()>0)
//				{
//					MProductCategory cat = new MProductCategory(Env.getCtx(),matchInv.getM_Product().getM_Product_Category_ID(),matchInv.get_TrxName());
//					cat.setA_Asset_Group_ID(-1);
//					cat.saveEx();
//				}
//			}
//		}
		if(po instanceof MProductCategory)
		{
			MProductCategory prodCat = (MProductCategory)po;
			if(prodCat.get_ValueAsInt("DS_AssetGroup_ID")>0)
				prodCat.setA_Asset_Group_ID(prodCat.get_ValueAsInt("DS_AssetGroup_ID"));
			
		}
	}

	@Override
	protected void initialize() {

		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_M_Movement.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_M_Movement.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, I_M_Movement.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MMatchInv.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MProductCategory.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MProductCategory.Table_Name);
		
		
	}




}
