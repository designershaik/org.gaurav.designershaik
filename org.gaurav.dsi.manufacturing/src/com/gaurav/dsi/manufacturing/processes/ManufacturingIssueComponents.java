package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MDocType;
import org.compiere.model.MProduct;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.libero.model.MPPCostCollector;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderBOMLine;
import org.libero.model.MPPOrderNode;

public class ManufacturingIssueComponents extends SvrProcess{

	int PP_Order_ID ; 
	MPPOrder order ; 
	Timestamp MovementDate;
	MAcctSchema as;
	String TrxType;
	int M_Locator_ID ; 
	@Override
	protected void prepare() 
	{
		PP_Order_ID = getRecord_ID();
		order = new MPPOrder(getCtx(), PP_Order_ID, get_TrxName());
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("MovementDate"))
				MovementDate = para[i].getParameterAsTimestamp();
			else if (name.equals("TrxType"))
				TrxType = para[i].getParameterAsString();
			else if (name.equals("M_Locator_ID"))
				M_Locator_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		int C_AcctSchema_ID = DB.getSQLValue(get_TrxName(), "SELECT C_AcctSchema_ID From C_AcctSchema where AD_Client_ID = ? ", getAD_Client_ID());
		as = new MAcctSchema(getCtx(), C_AcctSchema_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(TrxType.equalsIgnoreCase("IS"))
		{
			checkForStockProductCost();

			MPPOrderBOMLine[] bomlines = order.getLines();
			for(MPPOrderBOMLine line : bomlines)
			{
				BigDecimal QtyRequired = line.getQtyReserved();
				int M_Product_ID = line.getM_Product_ID();
				int M_AttributeSetInstance_ID = line.getM_AttributeSetInstance_ID();
				int PP_Order_BOMLine_ID = line.getPP_Order_BOMLine_ID();
				MProduct product = MProduct.get(getCtx(), M_Product_ID);
				if (product != null && product.get_ID() != 0 && product.isStocked() && QtyRequired.compareTo(Env.ZERO)!=0) 
				{
					MStorageOnHand[] storages = MPPOrder.getStorages(Env.getCtx(),
							M_Product_ID, order.getM_Warehouse_ID(),
							M_AttributeSetInstance_ID, MovementDate,
							order.get_TrxName());
		
					MPPOrder.createIssue(order, PP_Order_BOMLine_ID, MovementDate,
							QtyRequired, Env.ZERO, Env.ZERO, storages,
							false);
				}
			}
			order.set_ValueOfColumn("DS_IsComponentIssued", true);
			order.save();
			addLog("Components issued");
		}
		else if(TrxType.equalsIgnoreCase("RC"))
		{
			checkIfAllComponentIssued();
			BigDecimal QtyToDeliver = order.getQtyEntered().subtract(order.getQtyDelivered());
			if(QtyToDeliver.compareTo(Env.ZERO)!=0)
			{
				MPPOrder.createReceipt(order,
						MovementDate,
						QtyToDeliver,
						QtyToDeliver, 
						Env.ZERO,
						Env.ZERO,
						M_Locator_ID,
						order.getM_AttributeSetInstance_ID());
//				order.createStandardCosts();
				addLog("Product received");
				autoReportActivities();
//				order.setDocStatus("CL");
//				order.saveEx();
			}
			else
			{
				return "All the transactions for this work order are already processed.";
			}
			
		}
		return "Process completed successfully";
	}


	private void autoReportActivities() 
	{
		List<MPPOrderNode> nodes = new Query(getCtx(), MPPOrderNode.Table_Name, " PP_Order_ID = ? and IsMileStone='N' ", 
									get_TrxName()).setParameters(order.getPP_Order_ID()).
									setOrderBy("Priority").setOnlyActiveRecords(true).list();
		for(MPPOrderNode activity : nodes)
		{
			MPPCostCollector cc = MPPCostCollector.createCollector(
					order, 
					order.getM_Product_ID(), 
					order.getM_Locator_ID(), 
					order.getM_AttributeSetInstance_ID(), 
					order.getS_Resource_ID(), 
					0, 
					activity.getPP_Order_Node_ID(),
					MDocType.getDocType(MDocType.DOCBASETYPE_ManufacturingCostCollector),
					MPPCostCollector.COSTCOLLECTORTYPE_ActivityControl,
					order.getUpdated(),
					activity.getQtyToDeliver(),
					Env.ZERO, 
					Env.ZERO, 
					0, 
					Env.ZERO);
		}
	}

	private boolean checkIfAllComponentIssued() 
	{
		MPPOrderBOMLine[] bomlines = order.getLines();
		boolean issuedAlready = true;
		for(MPPOrderBOMLine line : bomlines)
		{
			MProduct product = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
			if(line.getQtyReserved().compareTo(Env.ZERO)!=0)
			{
				issuedAlready = false;
				addLog("Product is not issued: "+product.getName()+" check line number "+line.getLine());
				throw new AdempiereException("Product has not been issued: "+product.getValue());
			}
		}
		return issuedAlready;
	}

	private String checkForStockProductCost() 
	{
		MProduct mainProduct = new MProduct(getCtx(), order.getM_Product_ID(), get_TrxName());
		MCost cost = mainProduct.getCostingRecord(as,order.getAD_Org_ID(), 0);
		BigDecimal currentCostPrice = cost.getCurrentCostPrice();
		if(currentCostPrice.compareTo(Env.ZERO)==0)
		{
			addLog("Product "+mainProduct.getValue()+" does not have valid cost.");
			throw new AdempiereException("Product cost is zero, Issue not allowed");
		}
		MPPOrderBOMLine[] bomlines = order.getLines();
		for(MPPOrderBOMLine line : bomlines)
		{
			BigDecimal QtyRequired = line.getQtyReserved();
			int M_Product_ID = line.getM_Product_ID();
			int M_AttributeSetInstance_ID = 0;
			MProduct product = MProduct.get(getCtx(), M_Product_ID);
			if (product != null && product.get_ID() != 0 && product.isStocked() && QtyRequired.compareTo(Env.ZERO)!=0) 
			{
				M_AttributeSetInstance_ID = line.getM_AttributeSetInstance_ID();
				BigDecimal QtyOnHand = MStorageOnHand.getQtyOnHand(M_Product_ID, line.getM_Warehouse_ID(), M_AttributeSetInstance_ID, get_TrxName());
				if(QtyOnHand.compareTo(QtyRequired)<0)
				{
					addLog("Product "+product.getValue()+" does not have enough stock.");
					throw new AdempiereException("Product has not enough stock: "+product.getValue());
				}
				
				cost = product.getCostingRecord(as,line.getAD_Org_ID(), M_AttributeSetInstance_ID);
				currentCostPrice = cost.getCurrentCostPrice();
				if(currentCostPrice.compareTo(Env.ZERO)==0)
				{
					addLog("Product "+product.getValue()+" does not have valid cost.");
					throw new AdempiereException("Product cost is zero, Issue not allowed");
				}
			}	
		}
		
		return null;
	}

}
