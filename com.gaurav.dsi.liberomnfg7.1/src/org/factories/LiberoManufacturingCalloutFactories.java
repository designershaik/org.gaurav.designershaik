package org.factories;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.eevolution.model.MDDOrderLine;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;
import org.libero.callouts.Callout_DD_OrderLine;
import org.libero.callouts.Callout_PP_Cost_Collector;
import org.libero.callouts.Callout_PP_Order;
import org.libero.callouts.Callout_PP_Order_BOMLine;
import org.libero.callouts.Callout_PP_Product_BOMLine;
import org.libero.model.MPPCostCollector;
import org.libero.model.MPPOrder;
import org.libero.model.MPPOrderBOMLine;

public class LiberoManufacturingCalloutFactories implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) 
	{
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MPPProductBOMLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPProductBOMLine.COLUMNNAME_M_Product_ID))
			list.add(new Callout_PP_Product_BOMLine());
		
		if(tableName.equalsIgnoreCase(MPPProductBOM.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPProductBOM.COLUMNNAME_M_Product_ID))
			list.add(new Callout_PP_Product_BOMLine());
		
		if(tableName.equalsIgnoreCase(MPPOrder.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPOrder.COLUMNNAME_QtyEntered))
			list.add(new Callout_PP_Order());
		
		if(tableName.equalsIgnoreCase(MPPOrder.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPOrder.COLUMNNAME_M_Product_ID))
			list.add(new Callout_PP_Order());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_M_Product_ID))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_M_AttributeSetInstance_ID))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_C_UOM_ID))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_ConfirmedQty))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_M_AttributeSetInstanceTo_ID))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_QtyOrdered))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_QtyEntered))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MDDOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MDDOrderLine.COLUMNNAME_AD_Org_ID))
			list.add(new Callout_DD_OrderLine());
		
		if(tableName.equalsIgnoreCase(MPPCostCollector.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPCostCollector.COLUMNNAME_PP_Order_ID))
			list.add(new Callout_PP_Cost_Collector());
		
		if(tableName.equalsIgnoreCase(MPPCostCollector.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPCostCollector.COLUMNNAME_PP_Order_Node_ID))
			list.add(new Callout_PP_Cost_Collector());
		
		if(tableName.equalsIgnoreCase(MPPCostCollector.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPCostCollector.COLUMNNAME_MovementQty))
			list.add(new Callout_PP_Cost_Collector());
		
		if(tableName.equalsIgnoreCase(MPPOrderBOMLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPOrderBOMLine.COLUMNNAME_QtyEntered))
			list.add(new Callout_PP_Order_BOMLine());
		
		if(tableName.equalsIgnoreCase(MPPOrderBOMLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MPPOrderBOMLine.COLUMNNAME_QtyRequired))
			list.add(new Callout_PP_Order_BOMLine());
		
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];

	}

}
