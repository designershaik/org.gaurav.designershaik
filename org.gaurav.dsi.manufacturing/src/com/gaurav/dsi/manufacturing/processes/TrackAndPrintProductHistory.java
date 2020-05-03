package com.gaurav.dsi.manufacturing.processes;

import java.sql.Timestamp;
import java.util.List;

import org.compiere.model.MInOutLine;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSISerialNoTrx;
import org.gaurav.dsi.model.MDSITrackProductBatch;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderBOMLine;

public class TrackAndPrintProductHistory extends SvrProcess
{
	int DSI_SerialNoTrx_ID = 0 ; 
	@Override
	protected void prepare() 
	{
		DSI_SerialNoTrx_ID = getRecord_ID();
		
	}

	@Override
	protected String doIt() throws Exception 
	{
//		DB.executeUpdate("delete from DSI_TrackProductBatch", get_TrxName());
		MDSISerialNoTrx trx = new MDSISerialNoTrx(getCtx(), getRecord_ID(), get_TrxName());

		int PP_Order_ID = DB.getSQLValue(get_TrxName(), "Select PP_Order_ID "
				+ "From DSI_SerialNoTrx where DS_SerialNumberFinal like ? "
				+ "and M_Product_ID = ? and PP_Order_ID is not null order by PP_Order_ID ",trx.getDS_SerialNumberFinal(),trx.getM_Product_ID());
		MPPOrder order = new MPPOrder(getCtx(), PP_Order_ID, get_TrxName());
		addTrackData(order.getM_Product_ID(),order.getDateDelivered(),trx.getDS_SerialNumberFinal(),order.getM_AttributeSetInstance_ID(),-1,-1,PP_Order_ID);
		loopOnOrderBatch(order);
		return null;
	}

	private void loopOnOrderBatch(MPPOrder order)
	{
		List<MPPOrderBOMLine> lines = new Query(getCtx(), MPPOrderBOMLine.Table_Name, 
				" PP_Order_ID = ? and M_AttributeSetInstance_ID > 0 "
				+ "and M_Product_ID in (select M_Product_ID From M_Product Where M_AttributeSet_ID is not null) ", 
				get_TrxName()).setParameters(order.getPP_Order_ID()).list();
		for(MPPOrderBOMLine line : lines)
		{
			int PP_Order_ID = DB.getSQLValue(get_TrxName(), "Select PP_Order_ID From PP_Order "
					+ "Where M_AttributeSetInstance_ID = ? and M_Product_ID = ? ",line.getM_AttributeSetInstance_ID(),line.getM_Product_ID());
			if(PP_Order_ID>0)
			{
				MPPOrder order1 = new MPPOrder(getCtx(), PP_Order_ID, get_TrxName());
				loopOnOrderBatch(order1);
			}
			else
				loopOnMaterialReceiptBatch(line);
		}
		addTrackData(order.getM_Product_ID(),order.getDateDelivered(),null,order.getM_AttributeSetInstance_ID(),-1,-1,order.getPP_Order_ID());
	}

	private void loopOnMaterialReceiptBatch(MPPOrderBOMLine line)
	{
		int M_InOutLine_ID = DB.getSQLValue(get_TrxName(), "Select M_InOutLine_ID From M_InOutLine Where M_Product_ID = ? and M_AttributeSetInstance_ID = ? ",
				line.getM_Product_ID(),line.getM_AttributeSetInstance_ID());
		MInOutLine inoutLine = new MInOutLine(getCtx(), M_InOutLine_ID, get_TrxName());
		addTrackData(inoutLine.getM_Product_ID(),inoutLine.getM_InOut().getMovementDate(),null,inoutLine.getM_AttributeSetInstance_ID(),inoutLine.getC_OrderLine_ID(),M_InOutLine_ID,-1);
	}

	private void addTrackData(int m_Product_ID, Timestamp dateDelivered, String ds_SerialNumberFinal,
			int m_AttributeSetInstance_ID,Integer C_OrderLine_ID,Integer M_InOutLine_ID, int PP_Order_ID) 
	{
		int count = DB.getSQLValue(get_TrxName(), "Select count(*) "
				+ "From DSI_TrackProductBatch "
				+ "Where (PP_Order_ID=? or M_InOutLine_ID=?) and DSI_SerialNoTrx_ID = ? ",PP_Order_ID,M_InOutLine_ID,DSI_SerialNoTrx_ID);
		if(count<=0)
		{
			MProduct product = new MProduct(getCtx(), m_Product_ID, get_TrxName());
			MDSITrackProductBatch track = new MDSITrackProductBatch(getCtx(), 0, get_TrxName());
			track.set_ValueNoCheck("DSI_SerialNoTrx_ID", DSI_SerialNoTrx_ID);
			track.setMovementDate(dateDelivered);
			track.setM_AttributeSetInstance_ID(m_AttributeSetInstance_ID);
			track.setM_Product_ID(m_Product_ID);
			track.setDS_SerialNumberFinal(ds_SerialNumberFinal);
			track.setC_OrderLine_ID(C_OrderLine_ID);
			track.setM_InOutLine_ID(M_InOutLine_ID);
			track.setLowLevel(product.getLowLevel());
			track.setPP_Order_ID(PP_Order_ID);
			track.saveEx();
		}
	}
	
}
