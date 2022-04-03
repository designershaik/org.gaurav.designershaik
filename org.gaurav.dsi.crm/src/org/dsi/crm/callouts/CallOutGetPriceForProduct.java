package org.dsi.crm.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPartner;
import org.compiere.model.MRequest;
import org.compiere.model.MUser;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutGetPriceForProduct implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value == null)
			return null ; 
		
		Integer R_Request_ID = (Integer)mTab.getValue("R_Request_ID");
		MRequest req = new MRequest(ctx,R_Request_ID,null);
		if(req.getR_RequestType().getName().equalsIgnoreCase("Vendor Request"))
		{
			if(mField.getColumnName().equalsIgnoreCase("M_Product_ID"))
			{
				Integer M_Product_ID = (Integer)value;
				Integer AD_User_ID = Env.getAD_User_ID(ctx);
				MUser user = new MUser(ctx,AD_User_ID,null);
				MBPartner bp = (MBPartner)user.getC_BPartner();
				BigDecimal qty = (BigDecimal)mTab.getValue("QtyRequired");
				BigDecimal price = DB.getSQLValueBD(null, "SELECT pr.PriceStd "
						+ "	FROM M_ProductPrice pr , M_PriceList_Version ver , M_PriceList lst "
						+ "where pr.M_PriceList_Version_ID=ver.M_PriceList_Version_ID "
						+ "and ver.M_PriceList_ID=lst.M_PriceList_ID "
						+ "and lst.IsActive='Y' "
						+ "and ver.IsActive='Y' "
						+ "and lst.M_PriceList_ID = ? "
						+ "and pr.M_Product_ID = ? "
						+ "Order By ver.ValidFrom Desc ", bp.getM_PriceList_ID(),M_Product_ID);
				mTab.setValue("PriceEntered", price);
				mTab.setValue("DS_Budget", price.multiply(qty));
			}
			if(mField.getColumnName().equalsIgnoreCase("QtyRequired"))
			{
				BigDecimal qty = (BigDecimal)value;
				BigDecimal price = (BigDecimal)mTab.getValue("PriceEntered");
				mTab.setValue("DS_Budget", price.multiply(qty));
			}
		}
		return null;
	}


}
