package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderBOM;
import org.libero.model.MPPOrderBOMLine;


public class ProcessBatchNumbers extends SvrProcess {
	int p_BOM_OrderID;
	int productID;
	int p_warehouseID;
	int p_OrderID;
	private static CLogger s_log = CLogger
			.getCLogger(ProcessBatchNumbers.class);

	MPPOrder order;
	
	@Override
	protected void prepare() {
		p_BOM_OrderID = getRecord_ID();
		MPPOrderBOM bom = new MPPOrderBOM(getCtx(), p_BOM_OrderID,
				get_TrxName());
		p_OrderID = bom.getPP_Order_ID();
		order = new MPPOrder(getCtx(), p_OrderID, get_TrxName());

	}

	@Override
	protected String doIt() throws Exception {

		PreparedStatement pstmt;
		ResultSet rs;
		int orderBOMLineID;
		String sql = "Select line.M_Product_ID,line.PP_Order_BOMLine_ID "
				+ "from PP_Order_BOMLine line,m_Product prod "
				+ " where line.m_product_id=prod.m_product_id"
				+ " and prod.M_AttributeSet_ID is not null"
				+ " and line.pp_order_bom_id="+ p_BOM_OrderID;
		pstmt = DB.prepareStatement(sql, get_TrxName());
		rs = pstmt.executeQuery();
		while (rs.next()) {
			productID = rs.getInt("M_Product_ID");
			orderBOMLineID = rs.getInt("PP_Order_BOMLine_ID");
			updateASI(productID, orderBOMLineID);
		}
		return "Batch Number updated , Please verify";
	}

	private String updateASI(int productID, int orderBOMLineID)
			throws SQLException {

		int locatorID;
		int oldAttributeSet;
		BigDecimal qtyEntered = Env.ZERO;
		MPPOrderBOMLine line = new MPPOrderBOMLine(getCtx(), orderBOMLineID,
				get_TrxName());
		qtyEntered = line.getQtyRequired();
		oldAttributeSet = line.getM_AttributeSetInstance_ID();
		locatorID = line.getM_Locator_ID();
		if (oldAttributeSet == 0) 
		{
			if(checkIfEnoughStock(productID,qtyEntered))
			{
				setASI(productID, locatorID, qtyEntered, line);
			}
		}
		return "Success";
	}

	private boolean checkIfEnoughStock(int productID,BigDecimal qtyEntered) 
	{
		boolean enough = false;
		String sql = "SELECT coalesce(sum(qtyonhand),0) as QtyOnHand "
				+ "FROM M_StorageOnHand "
				+ "WHERE M_Product_ID=? "
				+ "AND M_AttributeSetInstance_ID > 0 "
				+ "AND m_locator_id in "
				+ "(select loc.m_locator_id from m_warehouse wh , m_locator loc where wh.m_warehouse_id=loc.m_warehouse_id and Wh.S_Resource_ID= ? "
				+ "	union all "
				+ "	select M_Locator_ID From M_Locator Where M_Warehouse_ID = ? ) ";
		BigDecimal qtyOnHand = DB.getSQLValueBD(get_TrxName(), sql, productID,order.getS_Resource_ID(),order.getM_Warehouse_ID());
		if(qtyEntered.compareTo(qtyOnHand)<=0)
			enough = true;
		else
		{
			MProduct product = new MProduct(getCtx(), productID, get_TrxName());
			addLog(productID, order.getCreated(), qtyEntered, Msg.getMsg(getCtx(), "DS_MO_STOCKNOTENOUGH")+"-"+product.getValue(), product.get_Table_ID(), productID);
		}		
		return enough;
	}

	private boolean setASI(int M_Product_ID, int M_Locator_ID,
			BigDecimal qtyEntered, MPPOrderBOMLine bomLine) 
	{
		int attributeSet = 0;
		BigDecimal qtyRequired = qtyEntered;
		String sql = "with t1 as ( SELECT M_AttributeSetInstance_ID,coalesce(sum(qtyonhand),0) as QtyOnHand "
				+ "FROM M_StorageOnHand WHERE M_Product_ID=? "
				+ "AND M_AttributeSetInstance_ID > 0 "
				+ "and m_locator_id in "
				+ "(select loc.m_locator_id from m_warehouse wh , m_locator loc "
				+ "where wh.m_warehouse_id=loc.m_warehouse_id and Wh.S_Resource_ID= ? ) "
				+ "group by M_AttributeSetInstance_ID having sum(qtyonhand)<>0 ORDER BY M_AttributeSetInstance_ID) "
				+ ", "
				+ "t2 as ( SELECT st.M_AttributeSetInstance_ID,coalesce(sum(st.qty),0) as QtyReserved "
				+ "FROM M_StorageReservation st,m_warehouse war "
				+ "WHERE st.m_warehouse_id=war.m_Warehouse_id and st.M_Product_ID=? "
				+ "AND st.M_AttributeSetInstance_ID > 0 and war.S_Resource_ID= ?  "
				+ "and st.issotrx='Y' group by ST.M_AttributeSetInstance_ID "
				+ "having sum(st.qty)<>0 ORDER BY st.M_AttributeSetInstance_ID) "
				+ "select coalesce(t1.QtyOnHand,0)-coalesce(t2.QtyReserved,0) as QtyAvailable,"
				+ "t1.M_AttributeSetInstance_ID from t1 "
				+ "left outer join t2 on t1.M_AttributeSetInstance_ID=t2.M_AttributeSetInstance_ID  "
				+ "where (coalesce(t1.QtyOnHand,0)-coalesce(t2.QtyReserved,0))>0 ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, order.getS_Resource_ID());
			pstmt.setInt(3, M_Product_ID);
			pstmt.setInt(4, order.getS_Resource_ID());
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				if(qtyRequired.compareTo(Env.ZERO)!=0)
				{
					attributeSet = rs.getInt("M_AttributeSetInstance_ID");
					BigDecimal qtyAvailable = rs.getBigDecimal("QtyAvailable");
					if (qtyRequired.compareTo(qtyAvailable)<=0) 
					{
						MPPOrderBOMLine line = new MPPOrderBOMLine(getCtx(), 0,
								get_TrxName());
						line.setPP_Order_BOM_ID(p_BOM_OrderID);
						line.setPP_Order_ID(p_OrderID);
						line.setM_Warehouse_ID(order.getM_Warehouse_ID());
						line.setM_Locator_ID(M_Locator_ID);
						line.setM_Product_ID(M_Product_ID);
						line.setM_AttributeSetInstance_ID(attributeSet);
						line.setQtyEntered(qtyRequired);
						line.setQtyRequired(qtyRequired);
						line.setM_ChangeNotice_ID(bomLine
								.getM_ChangeNotice_ID());
						line.setDescription(bomLine.getDescription());
						line.setHelp(bomLine.getHelp());
						line.setAssay(bomLine.getAssay());
						line.setQtyBatch(bomLine.getQtyBatch());
						line.setQtyBOM(bomLine.getQtyBOM());
						line.setIsQtyPercentage(bomLine.isQtyPercentage());
						line.setComponentType(bomLine.getComponentType());
						line.setC_UOM_ID(bomLine.getC_UOM_ID());
						line.setForecast(bomLine.getForecast());
						line.setIsCritical(bomLine.isCritical());
						line.setIssueMethod(bomLine.getIssueMethod());
						line.setLeadTimeOffset(bomLine.getLeadTimeOffset());
						line.setM_Product_ID(bomLine.getM_Product_ID());
						line.setScrap(bomLine.getScrap());
						line.setValidFrom(bomLine.getValidFrom());
						line.setValidTo(bomLine.getValidTo());
						line.setBackflushGroup(bomLine.getBackflushGroup());
						line.saveEx();
						qtyRequired = Env.ZERO;
						break;
					}
					else
					{
						MPPOrderBOMLine line = new MPPOrderBOMLine(getCtx(), 0,
								get_TrxName());
						line.setPP_Order_BOM_ID(p_BOM_OrderID);
						line.setPP_Order_ID(p_OrderID);
						line.setM_Warehouse_ID(order.getM_Warehouse_ID());
						line.setM_Locator_ID(M_Locator_ID);
						line.setM_Product_ID(M_Product_ID);
						line.setM_AttributeSetInstance_ID(attributeSet);
						line.setQtyEntered(qtyAvailable);
						line.setQtyRequired(qtyAvailable);
						line.setM_ChangeNotice_ID(bomLine
								.getM_ChangeNotice_ID());
						line.setDescription(bomLine.getDescription());
						line.setHelp(bomLine.getHelp());
						line.setAssay(bomLine.getAssay());
						line.setQtyBatch(bomLine.getQtyBatch());
						line.setQtyBOM(bomLine.getQtyBOM());
						line.setIsQtyPercentage(bomLine.isQtyPercentage());
						line.setComponentType(bomLine.getComponentType());
						line.setC_UOM_ID(bomLine.getC_UOM_ID());
						line.setForecast(bomLine.getForecast());
						line.setIsCritical(bomLine.isCritical());
						line.setIssueMethod(bomLine.getIssueMethod());
						line.setLeadTimeOffset(bomLine.getLeadTimeOffset());
						line.setM_Product_ID(bomLine.getM_Product_ID());
						line.setScrap(bomLine.getScrap());
						line.setValidFrom(bomLine.getValidFrom());
						line.setValidTo(bomLine.getValidTo());
						line.setBackflushGroup(bomLine.getBackflushGroup());
						line.saveEx();
						qtyRequired = qtyRequired.subtract(qtyAvailable);
					}
						
				}
			}
		} catch (SQLException ex) {
			s_log.log(Level.SEVERE, sql, ex);
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		bomLine.delete(true, get_TrxName());
		return true;
	}
}
