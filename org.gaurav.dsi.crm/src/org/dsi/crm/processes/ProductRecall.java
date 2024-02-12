package org.dsi.crm.processes;

import java.math.BigDecimal;
import java.util.List;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInOutLine;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSISerialNoTrx;
import org.gaurav.dsi.model.MDSITRecallProducts;
import org.gaurav.dsi.model.MDSITrackProductBatch;
import org.libero.model.MPPOrder;

public class ProductRecall extends SvrProcess {

	int DSI_SerialNoTrx_ID = 0 ;
	int p_M_Product_ID = 0 ;
	int p_M_AttributeSetInstance_ID = 0 ;
	BigDecimal productionQty = Env.ZERO;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null && para[i].getParameter_To() == null)
				;
			else if (name.equals("M_Product_ID"))
				p_M_Product_ID = para[i].getParameterAsInt();
			else if (name.equals("M_AttributeSetInstance_ID"))
				p_M_AttributeSetInstance_ID = para[i].getParameterAsInt();
		}
		DSI_SerialNoTrx_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		DB.executeUpdate("Delete from DSI_T_RecallProducts ", get_TrxName());
		if(DSI_SerialNoTrx_ID>0)
		{
			List<MDSITrackProductBatch> batchDetails = new Query(getCtx(), MDSITrackProductBatch.Table_Name, " 	DSI_SerialNoTrx_ID =  ? "
															+ "and M_AttributeSetInstance_ID is not null ", get_TrxName())
															.setParameters(DSI_SerialNoTrx_ID)
															.list();
			for(MDSITrackProductBatch det : batchDetails)
			{
				int M_AttributeSetInstance_ID = det.getM_AttributeSetInstance_ID();
				int M_Product_ID = det.getM_Product_ID();
				recallProductFromThisBatch(M_AttributeSetInstance_ID,M_Product_ID);
			}
		}
		else
		{
			recallProductFromThisBatch(p_M_AttributeSetInstance_ID,p_M_Product_ID);
			
			if(p_M_Product_ID>0)
				DB.executeUpdate("Update DSI_T_RecallProducts set Recall_RawMaterial_ID =? ,Recall_QtyAffected= ? ", new Object[]{p_M_Product_ID,productionQty}, false, get_TrxName());
		}
		return null;
	}

	private void recallProductFromThisBatch(int M_AttributeSetInstance_ID, int M_Product_ID) 
	{
		MAttributeSetInstance msi = new MAttributeSetInstance(getCtx(), M_AttributeSetInstance_ID, get_TrxName());
		List<MAttributeSetInstance> instances = new Query(getCtx(), MAttributeSetInstance.Table_Name, " lot like ? and M_AttributeSet_ID IN "
				+ "(SELECT M_AttributeSet_ID FROM M_AttributeSetInstance Where M_AttributeSetInstance_ID=?)", get_TrxName())
				.setParameters(msi.getLot(),M_AttributeSetInstance_ID)
				.list();
		for(MAttributeSetInstance instance : instances)
		{
			BigDecimal qty = DB.getSQLValueBD(get_TrxName(), "Select coalesce(sum(MovementQty),0) from M_Transaction "
					+ "Where M_Product_ID = ? "
					+ "and M_AttributeSetInstance_ID = ? "
					+ "and MovementType in ('W+','V+') ", M_Product_ID,instance.getM_AttributeSetInstance_ID());
			productionQty = productionQty.add(qty);
			loopOnTheASI(instance.getM_AttributeSetInstance_ID(),M_Product_ID);
		}
	}

	private void loopOnTheASI(int m_AttributeSetInstance_ID, int m_Product_ID) 
	{
		String sql = "select po.PP_Order_ID " + 
				"from PP_Order_BOMLine line,PP_Order po " + 
				"where line.PP_Order_ID=po.PP_Order_ID " + 
				"and line.M_AttributeSetInstance_ID= ? " +
				"and line.M_Product_ID = ? ";
		int[] orderIds = DB.getIDsEx(get_TrxName(), sql, m_AttributeSetInstance_ID,m_Product_ID);
		for(int pp_order_id : orderIds)
		{
			MPPOrder order = new MPPOrder(getCtx(), pp_order_id, get_TrxName());
			updateSerialNumber(pp_order_id, order.getM_Product_ID());
			loopOnTheASI(order.getM_AttributeSetInstance_ID(), order.getM_Product_ID());
		}
	}
	
	public void updateSerialNumber(int pp_order_id,int m_Product_ID)
	{
		String sql = " Select distinct trx.DSI_SerialNoTrx_ID "
				+ "FROM DSI_SerialNoTrx trx,PP_Order pp " + 
				"Where trx.PP_Order_ID=pp.PP_Order_ID " +
				"and pp.M_Product_ID = ? "+
				"and pp.PP_Order_ID = ? ";
		int[] trxIds = DB.getIDsEx(get_TrxName(), sql, m_Product_ID,pp_order_id);
		for(Integer id : trxIds)
		{
			MDSISerialNoTrx trx = new MDSISerialNoTrx(getCtx(), id, get_TrxName());
			int serialNumber = trx.getDSI_SrNo();
			String finalSerialNumber = trx.getDS_SerialNumberFinal();
			int count = DB.getSQLValue(get_TrxName(), "select count(*) From DSI_T_RecallProducts where M_Product_ID = ? and DS_SerialNumberFinal like ? ",m_Product_ID,finalSerialNumber);
			if(count<=0)
			{
				int M_InOutLine_ID = DB.getSQLValue(get_TrxName(), "Select M_InOutLine_ID from DSI_SerialNoTrx "
						+ "where M_Product_ID = ? and DSI_SrNo = ? and M_InOutLine_ID is not null ",m_Product_ID,serialNumber);
				MDSITRecallProducts recall = new MDSITRecallProducts(getCtx(), 0, get_TrxName());
				recall.setDS_SerialNumberFinal(trx.getDS_SerialNumberFinal());
				recall.setM_Product_ID(trx.getM_Product_ID());
				if(DSI_SerialNoTrx_ID>0)
					recall.setDSI_SerialNoTrx_ID(DSI_SerialNoTrx_ID);
				recall.setPP_Order_ID(pp_order_id);
				if(M_InOutLine_ID>0)
				{
					MInOutLine line = new MInOutLine(getCtx(), M_InOutLine_ID, get_TrxName());
					recall.setC_OrderLine_ID(line.getC_OrderLine_ID());
					recall.setM_InOutLine_ID(M_InOutLine_ID);
					if(p_M_AttributeSetInstance_ID<=0)
						recall.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
					else
						recall.setM_AttributeSetInstance_ID(p_M_AttributeSetInstance_ID);
						
					recall.setMovementDate(line.getM_InOut().getMovementDate());
					recall.set_ValueNoCheck("Qty", line.getMovementQty());
					recall.set_ValueNoCheck("C_BPartner_ID", line.getM_InOut().getC_BPartner_ID());
					recall.set_ValueNoCheck("C_BPartner_Location_ID", line.getM_InOut().getC_BPartner_Location_ID());
					recall.set_ValueNoCheck("AD_PInstance_ID", getAD_PInstance_ID());
				}
				recall.saveEx();	
			}
		}
	}

}
