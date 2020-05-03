package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.model.MSequence;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSISerialNoTrx;
import org.libero.model.MPPOrder;


public class GenerateSerialNumberMO extends SvrProcess 
{
	int p_Product_ID;
	int p_PP_Order_ID;
	int p_Locator_ID;
	int srNumber;
	ResultSet rs;
	PreparedStatement pstmt;
	String generatedMessage;
	String docStatus;
	int token=0;
	MPPOrder order = null;
	@Override
	protected void prepare() 
	{
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_ID"))
				p_Product_ID = para[i].getParameterAsInt();
			else if (name.equals("PP_Order_ID"))
				p_PP_Order_ID = para[i].getParameterAsInt();
			else if (name.equals("M_Locator_ID"))
				p_Locator_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		order = new MPPOrder(getCtx(), getRecord_ID(), get_TrxName());
	}

	@Override
	protected String doIt() throws Exception
	{
		int qtyOrdered = order.getQtyEntered().intValue();
		MProduct product = new MProduct(getCtx(), p_Product_ID, get_TrxName());
		int AD_Sequence_ID = product.get_ValueAsInt("AD_Sequence_ID");
		int totalSerialNumbersGenrated = DB.getSQLValue(get_TrxName(), "Select count(*) From DSI_SerialNoTrx Where PP_Order_ID = ? ",p_PP_Order_ID);
		if(totalSerialNumbersGenrated==qtyOrdered)
		{
			generatedMessage="Serial Number for this work order is being already Generated";
			
		}
		else
		{
			if(AD_Sequence_ID!=-1)
			{
				MSequence seq = new MSequence(getCtx(), AD_Sequence_ID, get_TrxName());
				srNumber = seq.getCurrentNext();
				for(int i=0;i<qtyOrdered;i++)
				{
					String serialNumber = MSequence.getDocumentNoFromSeq(seq, get_TrxName(), order);
					MDSISerialNoTrx msi=new MDSISerialNoTrx(getCtx(), 0, get_TrxName());
					msi.setDSI_SrNo(srNumber);
					msi.setM_Product_ID(p_Product_ID);
					msi.setPP_Order_ID(p_PP_Order_ID);
					msi.setSuffix(seq.getSuffix());
					msi.setPrefix(seq.getPrefix());
					msi.setQty(BigDecimal.ONE);
					msi.setDS_SerialNumberFinal(serialNumber);
					msi.setM_Locator_ID(p_Locator_ID);
					msi.setDS_OldSerialNumberFormat(false);
					msi.save();
					srNumber=srNumber+1;
				}
				generatedMessage="Serial numbers are generated, Serial Number Generated --- > "+srNumber;
			}
		}
		return generatedMessage;
	}

//	private void deleteSerialNumbers()
//	{
//		ResultSet rs;
//		PreparedStatement pstmt;
//		String sql="delete from dsi_";
//		
//	}

}
