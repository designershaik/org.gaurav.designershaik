package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCost;
import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class UpdateStandardCosting extends SvrProcess
{
	@Override
	protected void prepare() 
	{
		
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		PreparedStatement pstmt;
		ResultSet rs = null;
		String sql="select cst.M_PRODUCT_ID,cst.CURRENTCOSTPRICE,cst.M_ATTRIBUTESETINSTANCE_ID,"
				+ "cst.C_ACCTSCHEMA_ID,cst.AD_ORG_ID ,cst.M_COSTELEMENT_ID "
				+ "from m_cost cst,M_COSTELEMENT ele,m_product prod "
				+ "where cst.M_COSTELEMENT_ID=ele.M_COSTELEMENT_ID "
				+ "and cst.M_PRODUCT_ID=prod.M_PRODUCT_ID and prod.ISACTIVE='Y' "
				+ " and cst.CurrentCostPrice=0 "
				+ " and cst.M_PRODUCT_ID in (select m_product_id from m_transaction) "
				+ "AND cst.currentCostPriceLL=0 AND  ele.COSTINGMETHOD='S' AND PROD.AD_CLIENT_ID="+getAD_Client_ID();
		pstmt = DB.prepareStatement(sql, null);
		try
		{
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				int productID=rs.getInt("M_PRODUCT_ID");
				int attributeSetInstanceID=rs.getInt("M_ATTRIBUTESETINSTANCE_ID");
				int accountSchemaID=rs.getInt("C_ACCTSCHEMA_ID");
				int orgID=rs.getInt("AD_ORG_ID");
				int M_CostElement_ID=rs.getInt("M_COSTELEMENT_ID");
				MProduct product=new MProduct(getCtx(), productID, get_TrxName());
				MAcctSchema as=new MAcctSchema(getCtx(), accountSchemaID, get_TrxName());
				BigDecimal currentCostPrice=getCurrentCostPrice(product,attributeSetInstanceID,accountSchemaID,orgID,as);
				MCost cost=MCost.get(product, attributeSetInstanceID, as, orgID, M_CostElement_ID, get_TrxName());
				cost.setCurrentCostPrice(currentCostPrice);
				cost.saveEx();
				log.log(Level.INFO, "Product: "+product+" updated with cost : "+currentCostPrice);
			}
		}
		catch (Exception e) 
		{
			log.log(Level.SEVERE, sql, e);
		} 
		finally
		{
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		
		return "@OK@";
	}

	private BigDecimal getCurrentCostPrice(MProduct product,int attributeSetInstanceID, int accountSchemaID, int orgID, MAcctSchema as) 
	{
		BigDecimal currentCost=MCost.getCurrentCost(product, attributeSetInstanceID, as, orgID, "I", Env.ONE, 0, true, get_TrxName());
		return currentCost;
	}

}
