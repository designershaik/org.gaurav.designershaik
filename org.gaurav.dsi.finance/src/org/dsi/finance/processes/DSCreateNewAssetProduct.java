// Not used

package org.dsi.finance.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.compiere.model.MProduct;
import org.compiere.model.MProductPrice;
import org.compiere.model.X_I_Order;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class DSCreateNewAssetProduct extends SvrProcess {

	@Override
	protected void prepare() {

	}

	@Override
	protected String doIt() throws Exception {
		PreparedStatement pstmt;
		ResultSet rs;
		String sql = "select I_Order_ID from i_order where M_Product_ID is null";
		pstmt = DB.prepareStatement(sql, get_TrxName());
		rs = pstmt.executeQuery();
		int inserted=0;
		int updated=0;
		while (rs.next())
		{
			int I_Order_ID=rs.getInt("I_Order_ID");
			X_I_Order imp = new X_I_Order (getCtx (), I_Order_ID, get_TrxName());
			imp.getProductValue();
			int existingProductID=DB.getSQLValue(get_TrxName(), "select m_product_id from m_product where trim(upper(value)) like trim(upper('"+imp.getProductValue()+"')) ");
			if(existingProductID==-1 && imp.getM_Product_ID()==0)
			{
				MProduct product=new MProduct(getCtx(), 0, get_TrxName());
				product.setValue(imp.getProductValue());
				product.setName(imp.getProductValue());
				int productCategoryID=DB.getSQLValue(get_TrxName(), "select m_product_category_id from m_product_category where upper(name) like upper('"+imp.get_ValueAsString("ProductCategory")+"')");
				product.setM_Product_Category_ID(productCategoryID);
				product.setSalesRep_ID(imp.getSalesRep_ID());
				product.setC_UOM_ID(imp.getC_UOM_ID());
				product.setDescription("Product created as part of import process, net book value is "+imp.get_ValueAsInt("DS_NetBookValue")+" Purchase date"+imp.get_ValueAsString("DS_PurchaseDate"));
				product.setC_TaxCategory_ID(1000000);
				product.saveEx();
				DB.executeUpdate("Update I_Order set m_product_Id="+product.getM_Product_ID()+" where I_Order_ID="+I_Order_ID, get_TrxName());
				if(imp.getPriceActual().intValue()!=0 && imp.getM_PriceList_ID()!=0)
				{
					int ProductVersionID=DB.getSQLValue(get_TrxName(), "select M_PriceList_Version_ID from M_PriceList_Version where M_PriceList_ID=?", imp.getM_PriceList_ID());
					MProductPrice price=new MProductPrice(getCtx(), ProductVersionID, product.getM_Product_ID(), imp.getPriceActual(), imp.getPriceActual(), imp.getPriceActual(), get_TrxName());
					price.saveEx();
				}
				inserted++;
			}
			else {
			DB.executeUpdate("Update I_Order set m_product_Id="+existingProductID+" where I_Order_ID="+I_Order_ID, get_TrxName());
			updated++;
			}
		}
		return "Update Products-"+updated+" inserted products-"+inserted;
	}
}
