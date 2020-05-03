package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.Query;

public class MDSISrNoProduct extends X_DSI_SrNo_Product 
{

	public MDSISrNoProduct(Properties ctx, int DSI_SrNo_Product_ID,
			String trxName) {
		super(ctx, DSI_SrNo_Product_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSISrNoProduct(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSISrNoProduct(Properties ctx, int productID, int recordID,String trxName) 
	{
		this (ctx
				, MDSISrNoProduct.get(ctx, productID).get_ID()
				, trxName);
	}

	private static MDSISrNoProduct get(Properties ctx, int productID)
	{
		final String whereClause = I_DSI_SrNo_Product.COLUMNNAME_M_Product_ID+"=?";
		MDSISrNoProduct retValue = new Query(ctx,I_DSI_SrNo_Product.Table_Name,whereClause, null)
		.setParameters(productID)
		.first();
		return retValue;
	}

}
