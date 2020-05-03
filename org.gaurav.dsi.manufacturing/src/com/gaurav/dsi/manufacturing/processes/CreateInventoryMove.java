package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduct;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MUser;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderBOMLine;

public class CreateInventoryMove extends SvrProcess {

	int PP_Order_ID = 0 ; 
	MPPOrder order ; 
	int C_MovementDocType_ID ;
	int M_Warehouse_ID ; 
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_DocType_ID"))
				C_MovementDocType_ID = para[i].getParameterAsInt();
			else if (name.equals("M_Warehouse_ID"))
				M_Warehouse_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		PP_Order_ID = getRecord_ID();
		order = new MPPOrder(Env.getCtx(), PP_Order_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		
		if(order.get_ValueAsInt("M_Movement_ID")!=0)
			return "@Already Created Movement@";
		int AD_User_ID = getAD_User_ID();
		MUser user = new MUser(getCtx(), AD_User_ID, get_TrxName());
		
		MMovement movement = new MMovement(getCtx(), 0, get_TrxName());
		movement.setC_DocType_ID(C_MovementDocType_ID);
		movement.set_ValueOfColumn("PP_Order_ID", PP_Order_ID);
		movement.setDescription("Auto generated movement from the order by "+user.getName());
		movement.setAD_Org_ID(order.getAD_Org_ID());
		movement.setSalesRep_ID(AD_User_ID);
		movement.save();
		MPPOrderBOMLine[] lines = order.getLines();
		for(MPPOrderBOMLine line : lines )
		{
			String whereClause = "";
			int M_AttributeSet_ID = line.getM_Product().getM_AttributeSet_ID();
			if(M_AttributeSet_ID>0)
			{
				whereClause = " and st.m_attributesetinstance_id= "+line.getM_AttributeSetInstance_ID();
			}
			int M_Locator_ID = getLocator(line.getM_Product_ID(),whereClause);
			BigDecimal QtyOnHand = MStorageOnHand.getQtyOnHandForLocator(line.getM_Product_ID(),M_Locator_ID , line.getM_AttributeSetInstance_ID(), get_TrxName());
			if((M_Locator_ID!=-1 && M_Locator_ID!=0))
			{
				if(M_Locator_ID!=line.getM_Locator_ID() && line.getQtyReserved().intValue()!=0)
				{
					MMovementLine moveLine = new MMovementLine(movement);
					moveLine.setM_Product_ID(line.getM_Product_ID());
					moveLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
					moveLine.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
					moveLine.setM_Locator_ID(M_Locator_ID);
					moveLine.setM_LocatorTo_ID(line.getM_Locator_ID());
					moveLine.setMovementQty(line.getQtyReserved());
					moveLine.set_ValueOfColumn("QtyOnHand", QtyOnHand);
					moveLine.save();
				}
			}
			else
				addLog("Locator not found "+line.getM_Product().getValue());
		}
		order.set_ValueOfColumn("M_Movement_ID", movement.getM_Movement_ID());
		order.saveEx();
		addLog(movement.getM_Movement_ID(), movement.getMovementDate(), Env.ONE, movement.getDocumentInfo(), movement.get_Table_ID(), movement.getM_Movement_ID());
		
		return "@OK@";
	}

	private int getLocator(int M_Product_ID, String whereClause) 
	{
		int locatorid = 0 ; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT sum(st.qtyonhand), ST.M_LOCATOR_ID "
					+ " FROM M_StorageOnHand st,M_Locator lc , M_Warehouse war "
					+ "where st.m_locator_id=lc.m_locator_id and lc.m_warehouse_id=war.m_warehouse_id "
					+ "and st.m_product_id=? "+whereClause+" and war.S_Resource_ID = ? group by  ST.M_LOCATOR_ID order by sum(st.qtyonhand) desc ";
		try
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, M_Product_ID);
			pstmt.setInt(2, order.getS_Resource_ID());
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				locatorid = rs.getInt("M_LOCATOR_ID");
			}
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			DB.close(rs, pstmt);
			pstmt = null ;
			rs = null ;		
		}
		return locatorid;
	}

}
