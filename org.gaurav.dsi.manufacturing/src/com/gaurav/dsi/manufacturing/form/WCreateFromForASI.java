package com.gaurav.dsi.manufacturing.form;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.apps.IStatusBar;
import org.compiere.grid.CreateFrom;
import org.compiere.minigrid.IMiniTable;
import org.compiere.model.GridTab;
import org.compiere.model.MMovementLine;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.libero.model.MPPOrderBOMLine;

public class WCreateFromForASI extends CreateFrom{

	public WCreateFromForASI(GridTab gridTab) {
		super(gridTab);
		// TODO Auto-generated constructor stub
	}

	public boolean dynInit() throws Exception
	{
		log.config("");
		setTitle(Msg.getElement(Env.getCtx(), "M_AttributeSetInstance_ID") + " .. " + Msg.translate(Env.getCtx(), "CreateFrom"));
		
		return true;
	}
	
	protected Vector<Vector<Object>> getProductAttributeSetInstanceData(Object Product, Object Locator)
	{
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT onhand.M_Product_ID,prod.value || ' - ' ||prod.name as ProductName," // Product 1.2
				+ "onhand.M_AttributeSetInstance_ID,asi.description," // Attribute set instance 3.4
				+ "war.m_warehouse_id,war.name as Warehouse, " // Warehouse 5.6
				+ "  loc.m_locator_id,loc.value as Locator, " // Locator 7.8
				+ "coalesce(sum(onhand.qtyonhand),0) as QtyOnHand "); // On Hand Qty 9 
		sql.append(" FROM M_StorageOnHand onhand , m_locator loc,m_product prod,M_Warehouse war,M_AttributeSetInstance asi ");
		sql.append(" WHERE onhand.m_locator_id=loc.m_locator_id ");
		sql.append(" AND onhand.M_Product_ID=prod.m_product_id ");
		sql.append(" AND loc.M_Warehouse_ID=war.M_Warehouse_ID ");
		sql.append(" AND onhand.M_AttributeSetInstance_ID=asi.M_AttributeSetInstance_ID ");
		sql.append(" AND onhand.M_AttributeSetInstance_ID > 0 ");
		sql.append(" AND onhand.M_Product_ID = ? ");
		if(Locator != null)
			sql.append("AND onhand.M_Locator_ID = ? ");
		
		sql.append(" group by onhand.M_AttributeSetInstance_ID,war.m_warehouse_id,onhand.M_Product_ID,"
				+ "	loc.m_locator_id,prod.value,prod.name,asi.description,war.name,loc.value having sum(onhand.qtyonhand)<>0 "
				+ "  ORDER BY onhand.M_AttributeSetInstance_ID");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql.toString(), null);
			int index = 1;
			
			//pstmt.setTimestamp(index++, ts);	
			pstmt.setInt(index++, (Integer) Product);
			if(Locator != null)
				pstmt.setInt(index++, (Integer) Locator);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				Vector<Object> line = new Vector<Object>(6);
				line.add(new Boolean(false));       //  0-Selection
				KeyNamePair pp = new KeyNamePair(rs.getInt(1), rs.getString(2)); // Product
				line.add(pp);                      
				pp = new KeyNamePair(rs.getInt(3), rs.getString(4)); //  Attribute set instance
				line.add(pp);                      
				pp = new KeyNamePair(rs.getInt(5), rs.getString(6)); //Warehouse
				line.add(pp);    
				pp = new KeyNamePair(rs.getInt(7), rs.getString(8)); // Locator
				line.add(pp);    
				line.add(rs.getBigDecimal(9));      //  4-QtyOnHand
				
				data.add(line);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, sql.toString(), e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		
		return data;
	}
	
	protected void configureMiniTable(IMiniTable miniTable)
	{
		miniTable.setColumnClass(0, Boolean.class, false);      //  0-Selection
		miniTable.setColumnClass(1, String.class, true);    //  1 - Product
		miniTable.setColumnClass(2, String.class, true);        //  2-ASI
		miniTable.setColumnClass(3, String.class, true);        //  3-Warehouse
		miniTable.setColumnClass(4, String.class, true);    //  4-Locator
		miniTable.setColumnClass(5, BigDecimal.class, true);    //  5-Quantity On Hand
	
		//  Table UI
		miniTable.autoSize();
	}

	public boolean save(IMiniTable miniTable, String trxName)
	{
		//  fixed values
		MMovementLine moveLine = null ;
		MPPOrderBOMLine bomLine = null;
		
		Integer M_MovementLine_ID = ((Integer) getGridTab().getValue("M_MovementLine_ID"));
		Integer PP_Order_BOMLine_ID = ((Integer) getGridTab().getValue("PP_Order_BOMLine_ID"));
		if(M_MovementLine_ID!=null)
			moveLine = new MMovementLine (Env.getCtx(), M_MovementLine_ID, trxName);
		if(PP_Order_BOMLine_ID!=null)
			bomLine = new MPPOrderBOMLine (Env.getCtx(), PP_Order_BOMLine_ID, trxName);
		
		//  Lines
		int selectionCount = 0;
		for(int i = 0; i < miniTable.getRowCount(); i++)
		{
			
			if(((Boolean) miniTable.getValueAt(i, 0)).booleanValue())
			{
				selectionCount ++;
				if(selectionCount>1)
					throw new AdempiereException("Not allowed to select more than one");
				KeyNamePair pp = (KeyNamePair) miniTable.getValueAt(i, 2);               //  2-ASI
				int M_AttributeSetInstance_ID = pp.getKey();
				
				pp = (KeyNamePair) miniTable.getValueAt(i, 4);               //  2-Warehouse
				int M_Locator_ID = pp.getKey();
				
				if(M_MovementLine_ID!=null)
				{
					moveLine.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
					moveLine.setM_Locator_ID(M_Locator_ID);
					if (!moveLine.save())
						log.log(Level.SEVERE, "Line not saved #" + i);
				}
				if(PP_Order_BOMLine_ID!=null)
				{
					bomLine.setM_AttributeSetInstance_ID(M_AttributeSetInstance_ID);
					if (!bomLine.save())
						log.log(Level.SEVERE, "Line not saved #" + i);
				}
			}   //   if selected
		}   //  for all rows
		return true;
	}   //  save
	
	protected Vector<String> getOISColumnNames()
	{
		//  Header Info
		Vector<String> columnNames = new Vector<String>(7);
		columnNames.add(Msg.getMsg(Env.getCtx(), "Select"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Product_ID"));
		columnNames.add(Msg.getElement(Env.getCtx(), "M_AttributeSetInstance_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Warehose_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "M_Locator_ID"));
		columnNames.add(Msg.translate(Env.getCtx(), "QtyOnHand"));
	    
	    return columnNames;
	}

	@Override
	public Object getWindow() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void info(IMiniTable miniTable, IStatusBar statusBar) 
	{
		DecimalFormat format = DisplayType.getNumberFormat(DisplayType.Amount);

		BigDecimal total = new BigDecimal(0.0);
		int rows = miniTable.getRowCount();
		int count = 0;
		for (int i = 0; i < rows; i++)
		{
			if (((Boolean)miniTable.getValueAt(i, 0)).booleanValue())
			{
				total = total.add((BigDecimal)miniTable.getValueAt(i, 5));
				count++;
			}
		}
		statusBar.setStatusLine(count+" - "+Msg.getMsg(Env.getCtx(), " Sum ") + "  " + format.format(total));
		
	}
}
