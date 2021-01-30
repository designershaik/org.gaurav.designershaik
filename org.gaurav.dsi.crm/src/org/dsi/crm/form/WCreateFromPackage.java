package org.dsi.crm.form;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import org.compiere.apps.IStatusBar;
import org.compiere.grid.CreateFrom;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MInOutLine;
import org.compiere.model.MPackage;
import org.compiere.model.MPackageLine;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;

public class WCreateFromPackage extends CreateFrom
{
	public WCreateFromPackage(GridTab gridTab) {
		super(gridTab);
	}

	public boolean dynInit() throws Exception
	{
	
		log.config("");
		setTitle(Msg.getElement(Env.getCtx(), "M_Package_ID") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
		
		return true;
	}
	
	protected Vector<Vector<Object>> getShipmentLines()
	{
		int M_Package_ID = ((Integer) getGridTab().getValue("M_Package_ID")).intValue();
		MPackage pack = new MPackage(Env.getCtx(), M_Package_ID, null);
		String shipments[] = pack.get_ValueAsString("DS_ShipmentsInPackage").split(",");
		StringBuilder sqlWhere = new StringBuilder("");
		for(String s:shipments)
		{
			if(sqlWhere.toString().isBlank())
				sqlWhere.append("in (").append(s);
			else
				sqlWhere.append(",").append(s);
		}
		if(!sqlWhere.toString().isBlank())
			sqlWhere.append(")");
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		String sql = "select l.M_InOutLine_ID,l.Line||'-'||m.DocumentNo as LineDescription,l.movementqty,l.M_Product_ID ,coalesce(mp.UnitsPerPack,0)UnitsPerPack ,mp.value ||'-'||mp.name as Description ,mp.m_product_category_id  " + 
				"from m_inoutline l,m_product mp,M_InOut m  " + 
				"where l.m_product_id = mp.m_product_id  " + 
				"and l.M_InOut_ID=m.M_InOut_ID "+ 
				"and l.m_inout_id  "+sqlWhere+
				"order by m_product_category_id,m_product_id ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql,null);
			rs = pstmt.executeQuery();
			int totalBox = 1;
			while(rs.next())
			{
				int M_InOutLine_ID = rs.getInt("M_InOutLine_ID");
				String documentNo = rs.getString("LineDescription");
				BigDecimal movementQty = rs.getBigDecimal("MovementQty");
				int M_Product_ID = rs.getInt("M_Product_ID");
				BigDecimal unitsPerPack = rs.getBigDecimal("UnitsPerPack");
				unitsPerPack = unitsPerPack.compareTo(Env.ZERO)==0? movementQty:unitsPerPack;
				
				String description = rs.getString("Description");
				int totalBoxRequired = movementQty.divide(unitsPerPack, 2, RoundingMode.DOWN).intValue();
				BigDecimal totalPackagedQty = Env.ZERO;
				BigDecimal remainingQty = Env.ZERO;
				for(int i =0 ;i<totalBoxRequired;i++)
				{
					BigDecimal packagedQty = unitsPerPack;
					totalPackagedQty = totalPackagedQty.add(packagedQty);
					Vector<Object> parent = new Vector<Object>(6);
					parent.add(true);  // 0. Selection
					KeyNamePair pp = new KeyNamePair(M_InOutLine_ID, documentNo);
					parent.add(pp); // 1. InOutLines
					pp = new KeyNamePair(M_Product_ID, description);
					parent.add(pp); // 2. Product
					parent.add(unitsPerPack); // 3 UnitsPerPack
					parent.add(packagedQty); // 4. Packed Quantity
					parent.add(totalPackagedQty); // 5. total Packed quantity
					parent.add(description); // 6 Description to Change
					parent.add(new BigDecimal(totalBox)); // 7 Total Box
					data.add(parent);
					totalBox ++;
					
				}
				remainingQty = movementQty.subtract(totalPackagedQty);
				if(remainingQty.compareTo(Env.ZERO)!=0)
				{
					Vector<Object> parent = new Vector<Object>(6);
					parent.add(true);  // 0. Selection
					KeyNamePair pp = new KeyNamePair(M_InOutLine_ID, documentNo);
					parent.add(pp); // 1. Line
					pp = new KeyNamePair(M_Product_ID, description);
					parent.add(pp); // 2. Product
					parent.add(unitsPerPack); // 3 UnitsPerPack
					parent.add(remainingQty); // 4. Packed Quantity
					parent.add(movementQty); // 5. Total Packed Quantity
					parent.add(description); // 6 Description to Change
					parent.add(new BigDecimal(totalBox)); // 7 Total Box
					data.add(parent);
					totalBox ++;
				}
					
			}
		}
		catch(Exception e)
		{
			log.severe("Problem running getting shipment lines: "+e.toString());
		}
		finally
		{
			DB.close(rs,pstmt);
		}	
		return data;
	}
	
	
	
	protected Vector<String> getOISColumnNames()
	{
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.getElement(Env.getCtx(), "Line"));
		columnNames.add(Msg.getElement(Env.getCtx(), "M_Product_ID"));
		columnNames.add(Msg.getMsg(Env.getCtx(), "UnitsPerPack"));
		columnNames.add(Msg.getElement(Env.getCtx(), "PackedQuantities"));
		columnNames.add(Msg.getElement(Env.getCtx(), "TotalPackedQuantities"));
		columnNames.add(Msg.getElement(Env.getCtx(), "Description"));
		columnNames.add(Msg.getMsg(Env.getCtx(), "TotalBoxes"));
		
	    return columnNames;
	}
	

	protected void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		miniTable.setColumnClass(1, String.class, true);        //  1-Line
		miniTable.setColumnClass(2, String.class, true);        //  2-Product
		miniTable.setColumnClass(3, BigDecimal.class, true);   //  3-Units Per Pack
		miniTable.setColumnClass(4, String.class, true);        //  4-Packed Quantity
		miniTable.setColumnClass(5, BigDecimal.class, true);   //  5-Total Packed Quantity
		miniTable.setColumnClass(6, String.class, false);        //  6-Description to Change
		miniTable.setColumnClass(7, BigDecimal.class, false);    //  7- Total Box
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
				
				KeyNamePair pp =  (KeyNamePair)miniTable.getValueAt(i, 1);   //  line // M_InOutLine_ID

				String inoutLine_ID = pp.getID();
				pp =  (KeyNamePair)miniTable.getValueAt(i, 2);   //  product
				String product_ID = pp.getID();
				
				Integer M_Product_ID = Integer.parseInt(product_ID);
				Integer M_InOutLine_ID = Integer.parseInt(inoutLine_ID);
				BigDecimal qtyToPack = (BigDecimal) miniTable.getValueAt(i, 3); // Qty to parcel
				String description = (String)miniTable.getValueAt(i, 6);
				BigDecimal boxNo = (BigDecimal)miniTable.getValueAt(i, 7);
				
				MInOutLine line = new MInOutLine(Env.getCtx(), M_InOutLine_ID, trxName);
				BigDecimal price = line.getC_OrderLine().getPriceEntered();
				if(price.compareTo(Env.ZERO)==0)
					price = DB.getSQLValueBD(trxName, "Select ds_getcostoftheproduct(?) ", M_Product_ID);
				
				MPackageLine packLine = new MPackageLine(parcel);
				packLine.set_ValueNoCheck("Line", maxLineNo);
				packLine.setM_InOutLine_ID(M_InOutLine_ID);
				packLine.setM_Product_ID(M_Product_ID);
				packLine.setQty(qtyToPack);
				packLine.setDescription(description);
				packLine.set_ValueNoCheck("UnitsPerPack", qtyToPack);
				packLine.set_ValueNoCheck("DSI_BoxNo", boxNo);
				packLine.set_ValueNoCheck("PriceEntered", price);
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