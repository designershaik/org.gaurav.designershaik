package org.dsi.crm.form;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Vector;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.IStatusBar;
import org.compiere.grid.CreateFrom;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPackage;
import org.compiere.model.MPackageLine;
import org.compiere.model.MProduct;
import org.compiere.model.MRequest;
import org.compiere.model.MUOMConversion;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

public class WCreateFromPackage extends CreateFrom
{
	int m_C_BPartner_ID = 0;
	int m_FIL_Sub_BPartner_ID = 0 ;
	public WCreateFromPackage(GridTab gridTab) {
		super(gridTab);
	}

	public boolean dynInit() throws Exception
	{
	
		log.config("");
		setTitle(Msg.getElement(Env.getCtx(), "C_Project_IDs") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
		
		return true;
	}
	
	protected Vector<Vector<Object>> getOrderLines(int C_Order_ID)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		MOrder order = new MOrder(Env.getCtx(), C_Order_ID, null);
		MOrderLine[] lines = order.getLines();	
		for(MOrderLine l : lines)
		{
			BigDecimal qtyOrdered = l.getQtyOrdered();
			
			BigDecimal totalPackedExpdQty = DB.getSQLValueBD(null, "Select coalesce(sum(l.Qty),0) From M_PackageLine l "
					+ "where l.C_OrderLine_ID = ? ", l.getC_OrderLine_ID());
		
			if(totalPackedExpdQty.compareTo(qtyOrdered)<0)
			{
				Vector<Object> parent = new Vector<Object>(6);
				
				parent.add(new Boolean(false));  // 0. Selection
				KeyNamePair pp = new KeyNamePair(l.getC_OrderLine_ID(), Integer.toString(l.getLine()));
				parent.add(pp); // 1. Order Line      
				parent.add(l.getDatePromised()); // 2 Promise date
				pp = new KeyNamePair(l.getM_Product_ID(), l.getM_Product().getValue().concat("-").
						concat(l.getM_Product().getName()));
				parent.add(pp);                       //  3-M_Product_ID
				
				parent.addElement(qtyOrdered.subtract(totalPackedExpdQty)); // 4 Pack Qty
				parent.add(qtyOrdered); // 5 Qty Ordered
				parent.add(totalPackedExpdQty); // 6 Total Pack Qty
				data.add(parent);
			}
		}
			
		return data;
	}
	
	protected Vector<String> getOISColumnNames()
	{
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.getElement(Env.getCtx(), "Line"));
		columnNames.add(Msg.getElement(Env.getCtx(), "DatePromised"));
		columnNames.add(Msg.getElement(Env.getCtx(), "M_Product_ID"));
		columnNames.add(Msg.getMsg(Env.getCtx(), "FIL_QtyToParcel"));
		columnNames.add(Msg.getElement(Env.getCtx(), "QtyOrdered"));
		columnNames.add(Msg.getMsg(Env.getCtx(), "FIL_TotalPackedQty"));
		
	    return columnNames;
	}
	
	protected void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		miniTable.setColumnClass(1, Integer.class, true);        //  1-Line
		miniTable.setColumnClass(2, Timestamp.class, true);        //  2-Date Promised
		miniTable.setColumnClass(3, String.class, true);        //  3-Product
		miniTable.setColumnClass(4, BigDecimal.class, false);        //  5-Qty to parcel from EXPD
		miniTable.setColumnClass(5, BigDecimal.class, true);        //  6-Qty Ordered	
		miniTable.setColumnClass(6, BigDecimal.class, true);        //  7- Total Packed Qty
		//  Table UI
		miniTable.autoSize();
	}

	public boolean save(IMiniTable miniTable, String trxName)
	{
		//  fixed values
		int M_Package_ID = ((Integer) getGridTab().getValue("M_Package_ID")).intValue();
		
		MPackage parcel = new MPackage (Env.getCtx(), M_Package_ID, trxName);
		//  Lines
		for(int i = 0; i < miniTable.getRowCount(); i++)
		{
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				int maxLineNo = DB.getSQLValue(trxName, "Select coalesce(max(line),0)+1 From M_PackageLine Where M_Package_ID = "+M_Package_ID);
				
				KeyNamePair pp =  (KeyNamePair)miniTable.getValueAt(i, 1);   //  line

				String C_OrderLine_ID = pp.getID();
				
				pp =  (KeyNamePair)miniTable.getValueAt(i, 3);   //  product
				String M_Product_ID = pp.getID();
				
				BigDecimal qtyToParcel = (BigDecimal) miniTable.getValueAt(i, 4); // Qty to parcel
				BigDecimal alreadyPackedQty = (BigDecimal) miniTable.getValueAt(i, 6); // Qty already packed
				BigDecimal qtyOrdered = (BigDecimal) miniTable.getValueAt(i, 5); // Qty
				BigDecimal remainedQtyAfterTakingFromLaga = Env.ZERO;
				
				if(qtyToParcel.compareTo(Env.ZERO)<=0)
					throw new AdempiereException(Msg.getMsg(Env.getCtx(), "FIL_QuantityIsZero"));

				if((alreadyPackedQty.add(qtyToParcel)).compareTo(qtyOrdered)>0)
					throw new AdempiereException(Msg.getMsg(Env.getCtx(), "FIL_PackQtyMoreThanOrderQty"));

			
				MOrderLine line = new MOrderLine(Env.getCtx(), Integer.parseInt(C_OrderLine_ID), trxName);
				MOrder order = new MOrder(Env.getCtx(), line.getC_Order_ID(), trxName);
				
				MRequest request = new MRequest(Env.getCtx(), parcel.get_ValueAsInt("R_Request_ID"), trxName);
				if(!request.get_ValueAsString("DeliveryRule").equalsIgnoreCase(order.getDeliveryRule()))
				{
					request.set_ValueOfColumn("DeliveryRule", order.getDeliveryRule());
					request.saveEx();
				}
				
				MPackageLine packLine = new MPackageLine(parcel);
				packLine.set_ValueNoCheck("C_OrderLine_ID", Integer.parseInt(C_OrderLine_ID));
				packLine.setM_Product_ID(Integer.parseInt(M_Product_ID));
				if(remainedQtyAfterTakingFromLaga.compareTo(Env.ZERO)>0)
					packLine.setQty(remainedQtyAfterTakingFromLaga);
				packLine.set_ValueNoCheck("Line", maxLineNo);
				packLine.set_ValueNoCheck("PickedQty", qtyToParcel);
				packLine.set_ValueNoCheck("C_UOM_ID", line.getC_UOM_ID());
				packLine.saveEx();				
			}   //   if selected
		}   //  for all rows
		return true;
	}   //  save
	
	

	@Override
	public Object getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void info(IMiniTable miniTable, IStatusBar statusBar) 
	{
		
	}
}