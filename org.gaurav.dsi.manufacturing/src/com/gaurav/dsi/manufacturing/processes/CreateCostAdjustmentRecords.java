package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.compiere.acct.DocManager;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.gaurav.dsi.model.GetProductCosts;

public class CreateCostAdjustmentRecords extends SvrProcess{

	@Override
	protected void prepare() {
		
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		List<MProduct> products = new Query(getCtx(), MProduct.Table_Name, "AD_Client_ID=? AND IsBOM='Y' AND M_Product_ID IN (SELECT distinct bom.M_Product_ID FROM PP_Product_BOMLine line,PP_Product_BOM bom \r\n" + 
				"WHERE line.PP_Product_BOM_ID=bom.PP_Product_BOM_ID and bom.IsActive='Y' AND bom.AD_Client_ID=?) "
				+ " ", get_TrxName())
				.setParameters(new Object[]{Env.getAD_Client_ID(getCtx()),getAD_Client_ID()})
				.setOrderBy(" lowlevel desc ")
				.list();
		for(MProduct product : products)
		{
			int m_Product_ID = product.getM_Product_ID();
			BigDecimal f_Cost = Env.ZERO;
			MProduct finalProduct = new MProduct(getCtx(), m_Product_ID, get_TrxName());
			MPPProductBOM bom = MPPProductBOM.getDefault(finalProduct, get_TrxName());
			if(bom==null)
				addLog("Wrong BOM : "+finalProduct.getValue());
			else
			{
				if(bom.getLines()!=null)
				{
					MPPProductBOMLine[] bomLines = bom.getLines();
					for(MPPProductBOMLine line : bomLines)
					{
						MProduct bomComponents = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
						GetProductCosts costs = new GetProductCosts();
						MCost cost = costs.getProductCost(bomComponents);
						if(cost==null)
							addLog("Cost for the following component is not present : "+bomComponents.getValue());
						if(cost.getCurrentCostPrice().compareTo(Env.ZERO)==0)
							addLog("Cost for the following component is not present : "+cost.getM_Product().getValue());
						f_Cost = (cost.getCurrentCostPrice().multiply(line.getQtyBOM())).add(f_Cost);
					}
					MUOM uom = new MUOM(getCtx(), finalProduct.getC_UOM_ID(), get_TrxName());
					f_Cost = f_Cost.setScale(uom.getCostingPrecision(), RoundingMode.HALF_UP);
					GetProductCosts productCost = new GetProductCosts();
					MCost assemblyCost = productCost.getProductCost(finalProduct);
					BigDecimal qtyOnHand = productCost.getQtyOnHand(m_Product_ID);
					if(qtyOnHand.compareTo(Env.ZERO)>0)
					{
						BigDecimal currentCostPrice = assemblyCost.getCurrentCostPrice();
						if(f_Cost.compareTo(currentCostPrice)!=0)
						{
							int C_DocType = DB.getSQLValue(get_TrxName(), "Select C_DocType_ID From C_DocType Where DocSubTypeInv='CA' and AD_Client_ID = ? ", getAD_Client_ID());
							MInventory costingDoc = new MInventory(getCtx(), 0, get_TrxName());
							costingDoc.setC_DocType_ID(C_DocType);
							costingDoc.setCostingMethod("S");
							costingDoc.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
							costingDoc.saveEx();
		//					addLog(costingDoc.getM_Inventory_ID(), costingDoc.getCreated(), Env.ONE, costingDoc.getDocumentNo(), MInventory.Table_ID, costingDoc.getM_Inventory_ID());
							
							MInventoryLine costingLine = new MInventoryLine(getCtx(), 0, get_TrxName());
							costingLine.setM_Inventory_ID(costingDoc.getM_Inventory_ID());
							costingLine.setM_Product_ID(m_Product_ID);
							costingLine.setCurrentCostPrice(currentCostPrice);
							costingLine.setNewCostPrice(f_Cost);
							costingLine.setM_Locator_ID(0);
							costingLine.setAD_Org_ID(Env.getAD_Org_ID(getCtx()));
							costingLine.setM_AttributeSetInstance_ID(0);
							costingLine.saveEx();
							if(costingDoc.processIt(MInventory.DOCACTION_Complete))
							{
								costingDoc.saveEx();
								if(MClient.isClientAccountingQueue())
									DocManager.postDocument(MAcctSchema.getClientAcctSchema(getCtx(),Env.getAD_Client_ID(getCtx())), MInventory.Table_ID, costingDoc.getM_Inventory_ID(), true, false, get_TrxName());
							}
						}
					}
					DB.executeUpdate("Update M_Cost Set CurrentCostPrice = "+f_Cost+" Where M_Product_ID =  "+m_Product_ID+" "
							+ "and M_CostElement_ID IN (SELECT M_CostElement_ID FROM M_CostElement WHERE IsActive='Y' and CostingMethod is not null ) ",get_TrxName());
					addLog("Cost updated for the Product : "+finalProduct.getValue()+" New Updated Cost : "+f_Cost);
				}
			}
		}
		return null;
	}

}
