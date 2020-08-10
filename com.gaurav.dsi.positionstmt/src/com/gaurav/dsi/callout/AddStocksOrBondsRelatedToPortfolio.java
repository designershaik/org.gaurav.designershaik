package com.gaurav.dsi.callout;

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MInvoice;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPO;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class AddStocksOrBondsRelatedToPortfolio implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		if(value==null)
		{
			mTab.setValue("DS_Portfolio_Content", null);
			return null;
		}
		mTab.setValue("DS_Portfolio_Content", null);
		int M_PartType_ID = (Integer)value;
		Integer C_Invoice_ID = (Integer)mTab.getValue("C_Invoice_ID");
		MInvoice inv = new MInvoice(ctx,C_Invoice_ID,null);
		int C_BPartner_ID = inv.getC_BPartner_ID();
		List<MProduct> investments = new Query(ctx, MProduct.Table_Name, " M_PartType_ID = ? ", null).setParameters(M_PartType_ID).list();
		for(MProduct prod: investments)
		{
			String portfolioContent = (String)mTab.getValue("DS_Portfolio_Content");
			portfolioContent = portfolioContent==null ? "":portfolioContent+",";
			int M_Product_ID = prod.getM_Product_ID();
			BigDecimal qtyOnHand = DB.getSQLValueBD(null, "Select sum(QtyOnHand) From M_StorageOnHand Where M_Product_ID = ? ", M_Product_ID);
			if(qtyOnHand!=null && qtyOnHand.compareTo(Env.ZERO)>0)
			{
				MProductPO[] vendors = MProductPO.getOfProduct(ctx, M_Product_ID, null); 
				for(MProductPO vendor : vendors)
				{
					if(vendor.getC_BPartner_ID()== C_BPartner_ID && vendor.isCurrentVendor() && vendor.isActive())
					{
						mTab.setValue("DS_Portfolio_Content", portfolioContent.concat(""+prod.getM_Product_ID()));
					}
				}
			}
		}
		return null;
	}

}
