package com.gaurav.dsi.manufacturing.events;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMailText;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MStorageReservation;
import org.compiere.model.MWarehouse;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.EMail;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.gaurav.dsi.model.GetProductCosts;
import org.gaurav.dsi.model.I_DS_Manufacturing_QA;
import org.gaurav.dsi.model.I_DS_Manufacturing_QA_BOM;
import org.gaurav.dsi.model.MDSManufacturingQA;
import org.gaurav.dsi.model.MDSManufacturingQABOM;
import org.gaurav.dsi.model.MDSQARejectionReason;
import org.gaurav.dsi.model.MDSQARejectionSetup;
import org.libero.model.MPPCostCollector;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderBOMLine;
import org.osgi.service.event.Event;


public class DSManufacturingEventHandler extends AbstractEventHandler {

	CLogger log = CLogger.getCLogger(DSManufacturingEventHandler.class);
	String trxName ;
	Properties ctx ;
	MPPOrder pporder = null;
	@Override
	protected void doHandleEvent(Event event) 
	{
		PO po = getPO(event);
		trxName = po.get_TrxName();
		ctx = Env.getCtx();
		MClient client = new MClient(ctx, trxName);
		if(po instanceof MPPOrder)
		{
			pporder = (MPPOrder)po;
			if(event.getTopic().equals(IEventTopics.PO_AFTER_NEW) || 
					(event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE) && po.is_ValueChanged("QtyEntered")))
			{
				MProduct product = new MProduct(ctx, pporder.getM_Product_ID(), trxName);
				MPPProductBOM bom = MPPProductBOM.getDefault(product, trxName);
				DeleteAllQualityData();
				MDSManufacturingQA qa = new MDSManufacturingQA(ctx,0,trxName);
				qa.setAD_Org_ID(pporder.getAD_Org_ID());
				qa.setPP_Order_ID(pporder.getPP_Order_ID());
				qa.setQtyEntered(pporder.getQtyOrdered());
				qa.setQtyScrap(Env.ZERO);
				qa.setC_UOM_ID(product.getC_UOM_ID());
				qa.setM_Product_ID(product.getM_Product_ID());
				qa.saveEx();
				expandBOM(bom,qa.getDS_Manufacturing_QA_ID(),pporder.getQtyOrdered());
			}
			if(event.getTopic().equals(IEventTopics.DOC_AFTER_CLOSE))
			{
				MInventory internalUse = null;
				int DS_QA_Rejection_Setup_ID = DB.getSQLValue(trxName, "Select DS_QA_Rejection_Setup_ID "
						+ "From DS_QA_Rejection_Setup Where AD_Client_ID = ? ",pporder.getAD_Client_ID());
				MDSQARejectionSetup setup = new MDSQARejectionSetup(ctx, DS_QA_Rejection_Setup_ID, trxName);
				MWarehouse wh = new MWarehouse(ctx,pporder.getM_Warehouse().getM_Warehouse_ID(),trxName);
				MLocator loc = wh.getDefaultLocator();
				int C_DocType_ID = DB.getSQLValue(trxName, "select C_DocType_ID from C_DocType where DocBaseType= 'MMI' and DocSubTypeInv='IU' and AD_Client_ID = ? ", pporder.getAD_Client_ID());
				String sql = "select rej.QtyReject,bom.M_Product_ID,rej.DS_QARejection_Reason_ID "
						+ " from DS_Manufacturing_QA qa,DS_Manufacturing_QA_BOM bom, DS_QA_MO_Rejection rej "
						+ " where qa.DS_Manufacturing_QA_ID=bom.DS_Manufacturing_QA_ID "
						+ " and bom.DS_Manufacturing_QA_BOM_ID=rej.DS_Manufacturing_QA_BOM_ID"
						+ " and qa.PP_Order_ID= ? ";
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try
				{
					pstmt = DB.prepareStatement(sql, trxName);
					pstmt.setInt(1, pporder.getPP_Order_ID());
					rs = pstmt.executeQuery();
					while(rs.next())
					{
						int M_Product_ID = rs.getInt("M_Product_ID");
						int DS_QARejection_Reason_ID = rs.getInt("DS_QARejection_Reason_ID");
						BigDecimal QtyInternalUse = rs.getBigDecimal("QtyReject");
						MDSQARejectionReason reason = new MDSQARejectionReason(ctx, DS_QARejection_Reason_ID, trxName);
						if(internalUse==null)
						{
							internalUse = new MInventory(wh, trxName);
							internalUse.setDescription("Auto Generated from rejection process");
							internalUse.set_ValueOfColumn("PP_Order_ID", pporder.getPP_Order_ID());
							internalUse.setC_DocType_ID(C_DocType_ID);
							internalUse.saveEx();
						}
						MInventoryLine invLine = new MInventoryLine(internalUse, loc.getM_Locator_ID(), M_Product_ID, 0, Env.ZERO, Env.ZERO, QtyInternalUse);
						invLine.setDescription(reason.getName());
						invLine.setC_Charge_ID(reason.getC_Charge_ID());
						invLine.saveEx();
					}
				}
				catch(Exception e)
				{
					log.severe(e.toString());
				}
				finally
				{
					rs = null;
					pstmt = null;
					DB.close(rs, pstmt);
				}
				if(internalUse!=null)
				{
					String linkToWindow = "http://www.dsierp.com/webui/index.zul?Action=Zoom&AD_Table_ID="+internalUse.get_Table_ID()+"&Record_ID="+internalUse.getM_Inventory_ID();
					MMailText text = new MMailText(ctx, setup.getR_MailText().getR_MailText_ID(), trxName);
					String message = text.getMailText().concat("\n").concat(linkToWindow);
					EMail email = client.createEMail(setup.getAD_User().getEMail(), 
							setup.getR_MailText().getMailHeader(),
							message, true);
					
					email.send();
				}
			}
			if(event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE))
			{
				MProduct product = new MProduct(ctx, pporder.getM_Product_ID(), trxName);
				int M_AttributeSetInstance_ID = pporder.getM_AttributeSetInstance_ID();
				if(product.getM_AttributeSet_ID()!=0 && M_AttributeSetInstance_ID==0)
				{
					MAttributeSet attribute = new MAttributeSet(ctx, product.getM_AttributeSet_ID(), trxName);
					if(attribute.isLot())
					{
						MAttributeSetInstance asi = MAttributeSetInstance.generateLot(ctx, product, trxName);
						if(attribute.isGuaranteeDate())
						{
							int days = attribute.getGuaranteeDays();
							if (days > 0)
							{
								Timestamp ts = TimeUtil.addDays(pporder.getDateFinishSchedule(), days);
								asi.setGuaranteeDate(ts);
							}
							asi.setDescription();
							asi.saveEx();
						}
						pporder.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
						pporder.saveEx();
					}
					if(attribute.isSerNo())
					{
						MAttributeSetInstance asi = new MAttributeSetInstance(ctx, 0, trxName);
						asi.setM_AttributeSet_ID(product.getM_AttributeSet_ID());
						String serNo = asi.getSerNo(true);
						asi.setSerNo(serNo);
						asi.setDescription();
						asi.saveEx();
						pporder.setM_AttributeSetInstance_ID(asi.getM_AttributeSetInstance_ID());
						pporder.saveEx();
					}
					
				}
				UpdateCost(pporder.getM_Product_ID());
			}
		}
		if(po instanceof MPPOrderBOMLine)
		{
			MPPOrderBOMLine bomLine = (MPPOrderBOMLine)po;
			if(event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE))
			{
				if(bomLine.is_ValueChanged("M_AttributeSetInstance_ID"))
				{
					int M_OldASI_ID = bomLine.get_ValueOldAsInt("M_AttributeSetInstance_ID");
					int M_NewASI_ID = bomLine.getM_AttributeSetInstance_ID();
					if (!MStorageReservation.add(ctx, bomLine.getM_Warehouse_ID(), bomLine.getM_Product_ID(),
							M_OldASI_ID, bomLine.getQtyReserved().negate(), true, trxName))
						throw new AdempiereException("Can't correct reservation");
					else
						MStorageReservation.add(ctx, bomLine.getM_Warehouse_ID(), bomLine.getM_Product_ID(),
								M_NewASI_ID, bomLine.getQtyReserved(), true, trxName);
				}
			}
			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW) || (event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE) && bomLine.is_ValueChanged("M_AttributeSetInstance_ID")))
			{
				int M_AttributeSetInstance_ID = bomLine.getM_AttributeSetInstance_ID();
				if(M_AttributeSetInstance_ID>0)
				{
					MAttributeSetInstance msi = new MAttributeSetInstance(ctx, M_AttributeSetInstance_ID, trxName);
					if(msi.getGuaranteeDate()!=null)
					{
						Timestamp today = new Timestamp(TimeUtil.getToday().getTimeInMillis());
						if(msi.getGuaranteeDate().before(today))
							throw new AdempiereException("Expired batch");
					}
				}
			}
		}
		
		if(po instanceof MMovement)
		{
			MMovement movement = (MMovement)po;
			if(event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE))
			{
				List<MMovementLine> lines = new Query(ctx, MMovementLine.Table_Name, " M_Movement_ID = ? "
												+ " and M_Product_ID IN (SELECT M_Product_ID From M_Product "
												+ "Where M_AttributeSet_ID is null) and M_AttributeSetInstance_ID = 0  ", trxName)
												.setParameters(movement.getM_Movement_ID())
												.list();
				for(MMovementLine line : lines)
				{
					int M_Product_ID = line.getM_Product_ID();
					String sql = "Select M_Locator_ID , count(*) as Count,sum(Qtyonhand) as Qty From M_StorageOnHand Where M_Product_ID = ? "
							+ "group by M_Locator_ID having count(*)>1";
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try
					{
						pstmt = DB.prepareStatement(sql, trxName);
						pstmt.setInt(1, M_Product_ID);
						rs = pstmt.executeQuery();
						while(rs.next())
						{
							BigDecimal qtyOnHand = rs.getBigDecimal("Qty");
							int count = rs.getInt("Count");
							int M_Locator_ID = rs.getInt("M_Locator_ID");
							MLocator locator = new MLocator(ctx, M_Locator_ID, trxName);
							if(qtyOnHand.compareTo(Env.ZERO)==0)
								DB.executeUpdate(" DELETE FROM M_StorageOnHand where M_Locator_ID= "+M_Locator_ID+" "
										+ " and M_Product_ID = "+M_Product_ID, trxName);
							if(count>1 && qtyOnHand.compareTo(Env.ZERO)!=0)
							{
								MStorageOnHand[] storage = MStorageOnHand.getAll(ctx, M_Product_ID, M_Locator_ID, trxName);
								for(MStorageOnHand onhand : storage)
								{
									DB.executeUpdate(" DELETE FROM M_StorageOnHand where M_StorageOnHand_UU = '"+onhand.getM_StorageOnHand_UU()+"'", trxName);
								}
							}
							
							Timestamp now = new Timestamp(TimeUtil.getToday().getTimeInMillis());
							MStorageOnHand.add(ctx, locator.getM_Warehouse_ID(), M_Locator_ID, M_Product_ID, 0, qtyOnHand,now , trxName);
						}
					}
					catch(Exception e)
					{
						log.severe(e.toString());
					}
					finally
					{
						DB.close(rs, pstmt);
					}
				}
				
			}
		}
		if(po instanceof MPPCostCollector)
		{
			MPPCostCollector collector = (MPPCostCollector)po;
			MProduct product = new MProduct(ctx, collector.getM_Product_ID(), trxName);
			if(product.getM_AttributeSet_ID()<=0 && collector.getM_AttributeSetInstance_ID()>0)
				collector.setM_AttributeSetInstance_ID(0);
		}
	}

	private void UpdateCost(int M_Product_ID) 
	{
		BigDecimal f_Cost = Env.ZERO;
		MProduct finalProduct = new MProduct(ctx, M_Product_ID, trxName);
		MPPProductBOM bom = MPPProductBOM.getDefault(finalProduct, trxName);
		MPPProductBOMLine[] bomLines = bom.getLines();
		for(MPPProductBOMLine line : bomLines)
		{
			MProduct bomComponents = new MProduct(ctx, line.getM_Product_ID(), trxName);
			GetProductCosts costs = new GetProductCosts();
			MCost cost = costs.getProductCost(bomComponents);
			if(cost==null)
				new AdempiereException("Cost for the following component is not present : "+bomComponents.getValue());
			if(cost.getCurrentCostPrice().compareTo(Env.ZERO)==0)
				new AdempiereException("Cost for the following component is not present : "+bomComponents.getValue());
			f_Cost = (cost.getCurrentCostPrice().multiply(line.getQtyBOM())).add(f_Cost);
		}
		GetProductCosts final_Cost = new GetProductCosts();
		MCost assemblyCost = final_Cost.getProductCost(finalProduct);
		assemblyCost.setCurrentCostPrice(f_Cost);
		assemblyCost.saveEx();
	}
	private void DeleteAllQualityData() 
	{
		List<MDSManufacturingQABOM>	qaBOMLines = new Query(ctx, I_DS_Manufacturing_QA_BOM.Table_Name, " PP_Order_ID = ? ", trxName).setParameters(pporder.getPP_Order_ID()).list();
		for(MDSManufacturingQABOM qaLine: qaBOMLines)
		{
			qaLine.delete(true);
		}
		List<MDSManufacturingQA>	qaBOMs = new Query(ctx, I_DS_Manufacturing_QA.Table_Name, " PP_Order_ID = ? ", trxName).setParameters(pporder.getPP_Order_ID()).list();
		for(MDSManufacturingQA qabom: qaBOMs)
		{
			qabom.delete(true);
		}
	}

	@Override
	protected void initialize() {

		registerTableEvent(IEventTopics.PO_AFTER_NEW, MPPOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MPPOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_CLOSE, MPPOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MPPOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MPPOrderBOMLine.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MPPOrderBOMLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MPPOrderBOMLine.Table_Name);
		
		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MMovement.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MPPCostCollector.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MPPCostCollector.Table_Name);
	}
	
	/**
	 * Expand BOM and make requisition if any component is purchased.
	 * @param PP_Product_BOM
	 * @param MO_QA_Header_ID 
	 * @param PP_Order_BOM
	 */
	private void expandBOM(final MPPProductBOM PP_Product_BOM, int MO_QA_Header_ID, BigDecimal parentQty) 
	{
		for (MPPProductBOMLine PP_Product_BOMline : PP_Product_BOM.getLines(true))
		{				
			MProduct product = (MProduct) PP_Product_BOMline.getM_Product();
			if (PP_Product_BOMline.isValidFromTo(pporder.getDateStartSchedule()))
			{
				MDSManufacturingQABOM qaline = new MDSManufacturingQABOM(ctx,0,trxName);
				qaline.setAD_Org_ID(pporder.getAD_Org_ID());
				qaline.setPP_Order_ID(pporder.getPP_Order_ID());
				qaline.setQtyEntered(parentQty.multiply(PP_Product_BOMline.getQty()));
				qaline.setQtyScrap(Env.ZERO);
				qaline.setM_Product_ID(product.getM_Product_ID());
				qaline.setDS_Manufacturing_QA_ID(MO_QA_Header_ID);
				qaline.setIsBOM(product.isBOM());
				qaline.saveEx();
		 
				MPPProductBOM bom = MPPProductBOM.getDefault(product, trxName);
				if (bom!=null) 
				{
					MDSManufacturingQA qa = new MDSManufacturingQA(ctx,0,trxName);
					qa.setAD_Org_ID(pporder.getAD_Org_ID());
					qa.setPP_Order_ID(pporder.getPP_Order_ID());
					qa.setQtyEntered(pporder.getQtyOrdered());
					qa.setQtyScrap(Env.ZERO);
					qa.setM_Product_ID(product.getM_Product_ID());
					qa.saveEx();
					expandBOM(bom,qa.getDS_Manufacturing_QA_ID(), parentQty.multiply(PP_Product_BOMline.getQty()));
				}				
			}  
			else
			{
				log.fine("BOM Line skiped - "+PP_Product_BOMline);
			}
		} 
	}

}
