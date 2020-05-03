package org.dsi.crm.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class GetPriceForProduct implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value == null)
			return null ; 
		Integer M_Product_ID = (Integer)value;
		
		BigDecimal price = DB.getSQLValueBD(null, "SELECT "
				+ "	FROM M_ProductPrice pr , M_PriceList_Version ver , M_PriceList lst where ", M_Product_ID);
		
		return null;
	}


}
