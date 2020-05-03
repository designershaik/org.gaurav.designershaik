/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 * Portions created by Carlos Ruiz are Copyright (C) 2005 QSS Ltda.           *
 * Contributor(s): Carlos Ruiz (globalqss)                                    *
 *****************************************************************************/
package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;

import org.compiere.acct.DocManager;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MClient;
import org.compiere.model.MCost;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MProduct;
import org.compiere.model.MUOM;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.ValueNamePair;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.gaurav.dsi.model.GetProductCosts;

/**
 * Title: Check BOM Structure (free of cycles) Description: Tree cannot contain
 * BOMs which are already referenced
 *
 * @author Tony Snook (tspc)
 * @author Teo Sarca, SC ARHIPAC SERVICE SRL
 */
public class VerifyBOMAndCost extends SvrProcess
{

	/** The Record */
	private int p_Record_ID = 0;
	int C_AcctSchema_ID = 0 ;
	MAcctSchema as = null;
	int AD_Org_ID = 0;
	/**
	 * Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		p_Record_ID = getRecord_ID();
		C_AcctSchema_ID = DB.getSQLValue(get_TrxName(), "SELECT C_AcctSchema_ID From C_AcctSchema where AD_Client_ID = ? ",getAD_Client_ID());
		as = new MAcctSchema(getCtx(), C_AcctSchema_ID, get_TrxName());
		
	} // prepare

	/**
	 * Process
	 *
	 * @return message
	 * @throws Exception
	 */
	protected String doIt() throws Exception
	{
		log.info("Check BOM Structure");

		// Record ID is M_Product_ID of product to be tested
		MProduct xp = new MProduct(Env.getCtx(), p_Record_ID, get_TrxName());
		AD_Org_ID = xp.getAD_Org_ID();
		if (!xp.isBOM())
		{
			log.info("Product is not a BOM");
			// No BOM - should not happen, but no problem
			return "OK";
		}

		// Check Parent Level
		int lowlevel = MPPProductBOMLine.getLowLevel(getCtx(), p_Record_ID, get_TrxName());
		xp.setLowLevel(lowlevel);
		xp.setIsVerified(true);
		xp.saveEx();

		// Get Default BOM from this product
		MPPProductBOM tbom = MPPProductBOM.getDefault(xp, get_TrxName());
		if (tbom == null) {
			raiseError("No Default BOM found: ", "Check BOM Parent search key");
		}

		// Check All BOM Lines
		if (tbom.getM_Product_ID() != 0)
		{
			MPPProductBOMLine[] tbomlines = tbom.getLines();
			for (MPPProductBOMLine tbomline : tbomlines)
			{
				lowlevel = tbomline.getLowLevel();
				MProduct p = new MProduct(getCtx(), tbomline.getM_Product_ID(), get_TrxName());
				p.setLowLevel(lowlevel);
				p.setIsVerified(true);
				p.saveEx();
			}
		}
		UpdateCost();
//		UpdateCostsToTheLowestLevel();
		return "OK";
	} // doIt
//
//	private void UpdateCostsToTheLowestLevel() 
//	{
//		MProduct product = new MProduct(getCtx(), p_Record_ID, get_TrxName());
//		MCost cost = null ;
//		List<MPPProductBOM> boms = MPPProductBOM.getProductBOMs(product);
//		for(MPPProductBOM bom : boms)
//		{
//			expandBOM(bom);
//			MProductCategoryAcct prodAcct = MProductCategoryAcct.get(getCtx(), product.getM_Product_Category_ID(), C_AcctSchema_ID, get_TrxName());
//			BigDecimal totalBOMCost = Env.ZERO;
//			MPPProductBOMLine[] costLines = bom.getLines();
//			for(MPPProductBOMLine line : costLines)
//			{
//				MProduct bomProduct = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
//				if(bomProduct.isBOM())
//				{
////					System.out.println(" Product "+bomProduct.getValue()+" Current Cost Price = "+getCurrentCost(bomProduct,line.getQtyBOM())+" BOM Qty "+line.getQtyBOM());
//					totalBOMCost = totalBOMCost.add(getCurrentCost(bomProduct,line.getQtyBOM()));
//				}
//				else
//				{
////					System.out.println(" Product "+bomProduct.getValue()+" Current Cost Price = "+getCurrentCost(bomProduct,line.getQtyBOM())+" BOM Qty "+line.getQtyBOM());
//					totalBOMCost = totalBOMCost.add(getCurrentCost(bomProduct,line.getQtyBOM()).multiply(line.getQtyBOM()));
//				}
//			}				
////			System.out.println("Main Product value : "+product.getValue()+", Cost : "+totalBOMCost);
//			MCostElement ce = MCostElement.getMaterialCostElement(getCtx(), prodAcct.getCostingMethod(), AD_Org_ID);
//			cost = MCost.get(product, 0, as, 0, ce.getM_CostElement_ID(),get_TrxName());
//			cost.setCurrentCostPrice(totalBOMCost);
//			cost.saveEx();
//		}
//	}

	/**
	 * Expand BOM and make requisition if any component is purchased.
	 * @param PP_Product_BOM
	 * @param MO_QA_Header_ID 
	 * @param PP_Order_BOM
	 */
//	private void expandBOM(MPPProductBOM productBOM) 
//	{
//		
//		BigDecimal currentCost = Env.ZERO;
//		
//		MCost cost = null ;
//		MPPProductBOMLine[] bomLines = productBOM.getLines(true);
//		if(bomLines!=null)
//		{
//			for (MPPProductBOMLine bomline : bomLines)
//			{
//				if(bomline.isActive())
//				{
//					MProduct product = (MProduct) bomline.getM_Product();
//					MProductCategoryAcct prodAcct = MProductCategoryAcct.get(getCtx(), product.getM_Product_Category_ID(), C_AcctSchema_ID, get_TrxName());
//					MCostElement ce = MCostElement.getMaterialCostElement(getCtx(), prodAcct.getCostingMethod(), AD_Org_ID);
//					if(product.isBOM())
//					{
//						MPPProductBOM bom = MPPProductBOM.getDefault(product,get_TrxName());
//						if(bom==null)
//							throw new AdempiereUserError("This Product is Marked as BOM But has no bill of material: "+product.getValue());
//						if(bom.getValue().equals(product.getValue()))
//						{
//							expandBOM(bom);
//							MPPProductBOMLine[] costLines = bom.getLines();
//							BigDecimal totalBOMCost = Env.ZERO;
//							for(MPPProductBOMLine line : costLines)
//							{
//								MProduct bomProduct = new MProduct(getCtx(), line.getM_Product_ID(), get_TrxName());
//								totalBOMCost = totalBOMCost.add(getCurrentCost(bomProduct,line.getQtyBOM()));
//							}						
//							currentCost = totalBOMCost;
//						}
//					}
//					else
//						currentCost = getCurrentCost(product,bomline.getQtyBOM());
//					
//					BigDecimal currentQty = DB.getSQLValueBD(get_TrxName(), "Select sum(QtyOnHand) From M_StorageOnHand Where M_Product_ID = ? ", product.getM_Product_ID());
//					System.out.println("Product value : "+product.getValue()+", Cost : "+currentCost);
//					cost = MCost.get(product, 0, as, 0, ce.getM_CostElement_ID(),get_TrxName());
//					cost.setCurrentCostPrice(currentCost);
//					cost.setCurrentQty(currentQty);
//					cost.saveEx();
//				}
//			}
//		} 
//	}
//	private BigDecimal getCurrentCost(MProduct product, BigDecimal Qty) 
//	{
//		BigDecimal currentCost = Env.ZERO;
//		MProductCategoryAcct prodAcct = MProductCategoryAcct.get(getCtx(), product.getM_Product_Category_ID(), C_AcctSchema_ID, get_TrxName());
//		if(product.isBOM())
//		{
//			currentCost = MCost.getCurrentCost(product, 0, as, 0, 
//					prodAcct.getCostingMethod(),Qty , 0, true, get_TrxName());
//		}
//		else
//		{
//			currentCost = MCost.calculateAveragePO(product,0 , as, AD_Org_ID);
//			if(currentCost==null)
//			{
//				MCostElement element = MCostElement.getMaterialCostElement(getCtx(), prodAcct.getCostingMethod());
//				MCost cost = MCost.get(product, 0, as, 0, element.getM_CostElement_ID(), get_TrxName());
//				currentCost = cost.getCurrentCostPrice();
//			}
//			else
//			{
//				MCostElement element = MCostElement.getMaterialCostElement(getCtx(), prodAcct.getCostingMethod());
//				MCost cost = MCost.get(product, 0, as, 0, element.getM_CostElement_ID(), get_TrxName());
//				cost.setCurrentCostPrice(currentCost);
//				cost.saveEx();
//			}
//			currentCost = currentCost.multiply(Qty);
//		}
//		return currentCost;
//	}

	private void raiseError(String string, String hint) throws Exception
	{
		DB.rollback(false, get_TrxName());
		MProduct xp = new MProduct(getCtx(), p_Record_ID, null); // parent
		xp.setIsVerified(false); // set BOM not verified
		xp.saveEx();
		String msg = string;
		ValueNamePair pp = CLogger.retrieveError();
		if (pp != null)
			msg = pp.getName() + " - ";
		msg += hint;
		throw new AdempiereUserError(msg);
	}
	
	private void UpdateCost() 
	{
		BigDecimal f_Cost = Env.ZERO;
		MProduct finalProduct = new MProduct(getCtx(), p_Record_ID, get_TrxName());
		MPPProductBOM bom = MPPProductBOM.getDefault(finalProduct, get_TrxName());
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
		BigDecimal qtyOnHand = productCost.getQtyOnHand(p_Record_ID);
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
				addLog(costingDoc.getM_Inventory_ID(), costingDoc.getCreated(), Env.ONE, costingDoc.getDocumentNo(), MInventory.Table_ID, costingDoc.getM_Inventory_ID());
				
				
				MInventoryLine costingLine = new MInventoryLine(getCtx(), 0, get_TrxName());
				costingLine.setM_Inventory_ID(costingDoc.getM_Inventory_ID());
				costingLine.setM_Product_ID(p_Record_ID);
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
		DB.executeUpdate("Update M_Cost Set CurrentCostPrice = "+f_Cost+" Where M_Product_ID =  "+p_Record_ID+" "
				+ "and M_CostElement_ID IN (SELECT M_CostElement_ID FROM M_CostElement WHERE IsActive='Y' and CostingMethod is not null ) ",get_TrxName());
		addLog("Cost updated for the Product : "+finalProduct.getValue()+" New Updated Cost : "+f_Cost);
	}

} //	M_Product_BOM_Check
