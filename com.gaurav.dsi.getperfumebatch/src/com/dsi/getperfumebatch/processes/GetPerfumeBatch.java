package com.dsi.getperfumebatch.processes;

import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderBOMLine;

public class GetPerfumeBatch extends SvrProcess {

	int manufacturingOrder_ID = 0 ;
	int perfumeBatch_ID = 0;
	@Override
	protected void prepare() 
	{
		manufacturingOrder_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(manufacturingOrder_ID>0)
		{
			MPPOrder order = new MPPOrder(getCtx(),manufacturingOrder_ID,get_TrxName());
			loopOnManufacturingOrders(order);
			if(perfumeBatch_ID>0)
			{
				order.set_ValueNoCheck("GS_PerfumeBatchMO_ID", perfumeBatch_ID);
				order.saveEx();
			}
		}
		else
		{

		}
		return null;
	}

	private void loopOnManufacturingOrders(MPPOrder order) 
	{
		if(perfumeBatch_ID<=0)
		{
			String sql = "select line.PP_Order_BOMLine_ID " + 
					" from PP_Order_BOMLine line " + 
					" where line.PP_Order_ID = ? "  ;
			
			System.out.println("Main PP Order: "+order.getDocumentNo());
			int[] manufacturingLine_IDs = DB.getIDsEx(get_TrxName(), sql, order.getPP_Order_ID());
			for(int ppBOMLine_ID : manufacturingLine_IDs)
			{
				if(perfumeBatch_ID>0)
					continue;
				MPPOrderBOMLine bomLine = new MPPOrderBOMLine(getCtx(),ppBOMLine_ID,get_TrxName());
				if(bomLine.getM_AttributeSetInstance_ID()>0)
				{
					MProduct prod = (MProduct)bomLine.getM_Product();
					System.out.println(prod.getM_Product_ID()+" / ID / "+"Product: "+prod.getValue()+" Name: "+prod.getName()+" Order Ids: "+order.getDocumentNo()+" Main Batch"+prod.get_ValueAsBoolean("GS_MaintainBatchhierarchy"));
					if(prod.get_ValueAsBoolean("GS_MaintainBatchhierarchy"))
					{
						perfumeBatch_ID =  bomLine.getM_AttributeSetInstance_ID();
					}
					else if (perfumeBatch_ID<=0)
					{
						int childOrder_ID = DB.getSQLValue(get_TrxName(), "Select PP_Order_ID From PP_Order Where M_AttributeSetInstance_ID = ? and M_Product_ID = ? ",bomLine.getM_AttributeSetInstance_ID(),bomLine.getM_Product_ID());
						MPPOrder childOrder = new MPPOrder(getCtx(),childOrder_ID,get_TrxName());
						loopOnManufacturingOrders(childOrder);
					}
				}
			}
		}
	}
	
//	private int loopOnTheASI(int m_AttributeSetInstance_ID, int m_Product_ID) 
//	{
//		String sql = "select line.PP_Order_ID " + 
//				"from PP_Order_BOMLine line,PP_Order po " + 
//				"where line.PP_Order_ID=po.PP_Order_ID " + 
//				"and line.M_AttributeSetInstance_ID= ? " +
//				"and line.M_Product_ID = ? ";
//		int[] orderIds = DB.getIDsEx(get_TrxName(), sql, m_AttributeSetInstance_ID,m_Product_ID);
//		for(int pp_order_id : orderIds)
//		{
//			MPPOrder order = new MPPOrder(getCtx(), pp_order_id, get_TrxName());
//			MProduct prod = (MProduct)order.getM_Product();
//			System.out.println("Product: "+prod.getValue()+" Name: "+prod.getName()+" Order Ids: "+order.getDocumentNo());
//			if(prod.get_ValueAsBoolean("GS_MaintainBatchhierarchy"))
//				return pp_order_id;
//			
////			MPPOrderBOMLine[] bomLines =  order.getLines();
////			for(MPPOrderBOMLine bomLine:bomLines)
////				loopOnTheASI(bomLine.getM_AttributeSetInstance_ID(), bomLine.getM_Product_ID());
//		}
//		return 0;
//	}			

}
