package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CreateAveragePORecordAndCorrectCurrentQty extends SvrProcess
{
	private boolean p_UpdateCurrentQty;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null)
				;
			else if (name.equals("UpdateQty"))
				p_UpdateCurrentQty = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(p_UpdateCurrentQty)
			updateCurrentQty();
		
		List<MCost> costs = new Query(getCtx(), MCost.Table_Name, " M_Product_ID in (select m_product_id from m_cost group by m_product_id having count(*)=1) AND AD_Client_ID = ? "
							+ " ", get_TrxName()).
							setParameters(getAD_Client_ID()).
							list();
		for(MCost cost : costs)
		{
			int M_CostElement_ID = cost.getM_CostElement_ID();
			int M_Product_ID = cost.getM_Product_ID();
			MProduct product = new MProduct(getCtx(), M_Product_ID, get_TrxName());
			int C_AcctSchema_ID = DB.getSQLValue(get_TrxName(), "SELECT C_AcctSchema_ID From C_AcctSchema Where AD_Client_ID = ? and IsActive='Y'",getAD_Client_ID());
			MAcctSchema as = new MAcctSchema(getCtx(), C_AcctSchema_ID, get_TrxName());
			int missingCostElement_ID = DB.getSQLValue(get_TrxName(), "Select M_CostElement_ID FROM M_CostElement Where IsActive='Y' "
					+ "and M_CostElement_ID!=? and CostingMethod is not null and AD_Client_ID = ? ",M_CostElement_ID,getAD_Client_ID());
			MCost missingCost = new MCost(product, 0, as, 0, missingCostElement_ID);
			missingCost.setCurrentCostPrice(cost.getCurrentCostPrice());
			missingCost.setCurrentCostPriceLL(cost.getCurrentCostPriceLL());
			missingCost.setCumulatedAmt(cost.getCumulatedAmt());
			missingCost.setCumulatedQty(cost.getCumulatedQty());
			missingCost.setCurrentQty(cost.getCurrentQty());
			missingCost.setFutureCostPrice(cost.getFutureCostPrice());
			missingCost.setFutureCostPriceLL(cost.getFutureCostPriceLL());
			missingCost.saveEx();
		}
		
		return "@OK@";
	}

	private void updateCurrentQty() throws SQLException 
	{
		String sql = "Select distinct M_Product_ID From ds_rv_unposted where ad_client_id = ? AND M_Product_ID > 0 ";
		PreparedStatement pstmt = null;
		ResultSet rs = 	null;
		pstmt = DB.prepareStatement(sql, get_TrxName());
		pstmt.setInt(1, getAD_Client_ID());
		rs = pstmt.executeQuery();
		while(rs.next())
		{
			int M_Product_ID = rs.getInt("M_Product_ID");
			BigDecimal currentQty = getCurrentQty(M_Product_ID);
			DB.executeUpdate("Update M_Cost Set CurrentQty = ? where M_Product_ID = ? ",new Object[]{currentQty,M_Product_ID},false,get_TrxName());
		}
		
	}

	private BigDecimal getCurrentQty(int M_Product_ID) 
	{
		BigDecimal shipmentQty = DB.getSQLValueBD(get_TrxName(), "select coalesce(sum(line.movementqty),0) "
																+ "from rv_unposted rv,m_inout inout ,m_inoutline line "
																+ "where rv.record_id=inout.m_inout_id and inout.m_inout_id=line.m_inout_id "
																+ "and rv.ad_table_id=319 "
																+ "and line.m_product_id is not null "
																+ "and rv.docstatus not in ('DR','IN')"
																+ "and line.M_Product_ID = ? ", M_Product_ID);
		
		BigDecimal internalUseQty = DB.getSQLValueBD(get_TrxName(), "select coalesce(sum(line.QtyInternalUse),0) "
				+ "from rv_unposted rv,M_Inventory inv ,M_Inventoryline line,C_DocType type "
				+ "where rv.record_id=inv.M_Inventory_id "
				+ "and inv.M_Inventory_id=line.M_Inventory_id "
				+ "and inv.C_DocType_ID=type.C_DocType_ID "
				+ "and rv.ad_table_id=321 "
				+ "and line.m_product_id is not null "
				+ "and rv.docstatus not in ('DR','IN') "
				+ "and line.M_Product_ID = ? "
				+ "and type.docsubtypeinv='IU' ", M_Product_ID);
		
		BigDecimal physicalQty = DB.getSQLValueBD(get_TrxName(), "select coalesce(sum(line.QtyBook),0)-coalesce(sum(line.QtyCount),0) "
				+ "from rv_unposted rv,M_Inventory inv ,M_Inventoryline line,C_DocType type "
				+ "where rv.record_id=inv.M_Inventory_id "
				+ "and inv.M_Inventory_id=line.M_Inventory_id "
				+ "and inv.C_DocType_ID=type.C_DocType_ID "
				+ "and rv.ad_table_id=321 "
				+ "and line.m_product_id is not null "
				+ "and rv.docstatus not in ('DR','IN') "
				+ "and line.M_Product_ID = ? "
				+ "and type.docsubtypeinv='PI' ", M_Product_ID);
		
		BigDecimal QtyOnHand = DB.getSQLValueBD(get_TrxName(), "select coalesce(sum(qtyonhand),0) from m_storageonhand where m_product_id= ? ", M_Product_ID);
		if(QtyOnHand.compareTo(Env.ZERO)<0)
			QtyOnHand = Env.ZERO;
		
		return shipmentQty.add(internalUseQty).add(QtyOnHand).add(physicalQty);
	}
	

}
