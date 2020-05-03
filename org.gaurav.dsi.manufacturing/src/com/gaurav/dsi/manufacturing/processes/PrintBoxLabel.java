package com.gaurav.dsi.manufacturing.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;

import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSIBoxLabelsT;
import org.gaurav.dsi.model.MDSISrNoProduct;

public class PrintBoxLabel extends SvrProcess
{
	int orderID;
	int productID;
	int unitsPerpack;
	int qtyDelivered;
	MDSISrNoProduct dsp;
	MProduct prod = null;
	@Override
	protected void prepare() 
	{
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_ID"))
				productID = para[i].getParameterAsInt();
			else if (name.equals("QtyDelivered"))
				qtyDelivered = para[i].getParameterAsInt();
			else	
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

		orderID=getRecord_ID();
		prod=new MProduct(getCtx(), productID, get_TrxName());
		unitsPerpack=prod.get_ValueAsInt("UnitsPerPack");
	}

	@Override
	protected String doIt()
	{
		Integer BoxNo= prod.get_ValueAsInt("DSI_BoxNo");
		int FirstBoxNo=BoxNo;
		PreparedStatement pstmt;
		ResultSet rs = null;
		int i=0;
		int k=0;
		
		String sql="SELECT DSI_SRNO,  M_PRODUCT_ID,DS_SerialNumberFinal "
				+ "FROM dsi_serialnotrx trx "
			+ "where dsi_srno not in (select dsi_srno from DSI_BOXLABELS_T bt "
				+ "where bt.pp_order_id= trx.pp_order_id) and trx.PP_Order_ID="+orderID+" order by dsi_srno";
		pstmt=DB.prepareStatement(sql,get_TrxName());
		
		try 
		{
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				k++;
				i++;
				int srNo=rs.getInt("DSI_SRNO");
				String DS_SerialNumberFinal = rs.getString("DS_SerialNumberFinal");
				MDSIBoxLabelsT bt=new MDSIBoxLabelsT(getCtx(), 0, get_TrxName());
				bt.setDSI_SrNo(srNo);
				bt.setM_Product_ID(rs.getInt("M_Product_ID"));
				bt.setPP_Order_ID(orderID);
				bt.setDSI_BoxNo(BoxNo);
				bt.setQty(Env.ONE);
				bt.setDS_SerialNumberFinal(DS_SerialNumberFinal);
				bt.saveEx();
				if(i==unitsPerpack)
				{
					BoxNo++;
					i=0;			
				}
			}
		} 
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql, e);
		} 
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		if(k==0)
		{
			return " Box labeles already created for this Manufacturing order";
		}
		addLog(" Box labeles creating starting from "+FirstBoxNo+"-"+BoxNo);
		BoxNo = DB.getSQLValue(get_TrxName(), "Select max(DSI_BoxNo) From DSI_BoxLabels_T Where M_Product_ID = ? and pp_Order_ID=? ",productID,orderID);
		prod.set_ValueOfColumn("DSI_BoxNo", BoxNo+1);
		prod.saveEx();
		return "@Generated@";
	}
}
