package org.dsi.crm.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.X_C_OrderSource;

public class CallOutSetOrderSourceValue implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {

		if(value==null)
			return null;
		
		Integer C_OrderSource_ID = (Integer)value;
		X_C_OrderSource source = new X_C_OrderSource(ctx, C_OrderSource_ID, null);
		mTab.setValue("C_OrderSourceValue", source.getValue());
		int M_WarehouseSource_ID = source.get_ValueAsInt("M_WarehouseSource_ID");
		if(M_WarehouseSource_ID>0)
			mTab.setValue("M_Warehouse_ID", M_WarehouseSource_ID);
		
		return null;
	}


}
