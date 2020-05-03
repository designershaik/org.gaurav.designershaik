package org.gaurav.dsi.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Properties;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MCostElement;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategoryAcct;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class GetProductCosts {

	public MCost getProductCost(MProduct product)
	{
		Properties ctx = Env.getCtx();
		String trxName = product.get_TrxName();
		MCost cost = null;
		MAcctSchema[] schemas = MAcctSchema.getClientAcctSchema(Env.getCtx(), Env.getAD_Client_ID(Env.getCtx()));
		for(MAcctSchema as : schemas)
		{
			if(product!=null)
			{
				String costingMethod = product.getCostingMethod(as);
				MProductCategoryAcct prodCatAcct = MProductCategoryAcct.get(ctx, product.getM_Product_Category_ID(),as.getC_AcctSchema_ID(), trxName);
				costingMethod = prodCatAcct.getCostingMethod() == null ? product.getCostingMethod(as) : prodCatAcct.getCostingMethod() ;
				
				MCostElement element = MCostElement.getMaterialCostElement(ctx, costingMethod, Env.getAD_Org_ID(ctx));
				
				cost = MCost.get(product, 0, as, 0, element.getM_CostElement_ID(),trxName);
			}
		}
		return cost;
	}
	public BigDecimal getQtyOnHand(int M_Product_ID)
	{
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT SUM(oh.QtyOnHand) FROM M_StorageOnHand oh WHERE oh.M_Product_ID=? ");
		ArrayList<Object> params = new ArrayList<Object>();
		params.add(M_Product_ID);
		BigDecimal qty = DB.getSQLValueBD(null, sql.toString(), params);
		if (qty == null)
			qty = Env.ZERO;

		return qty;
	}

}
