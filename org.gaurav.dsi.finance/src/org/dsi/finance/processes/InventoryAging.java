package org.dsi.finance.processes;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSInventoryAging;

public class InventoryAging extends SvrProcess{

	private Timestamp p_StatementDate = null;

	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DateAcct"))
				p_StatementDate = (Timestamp)para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	@Override
	protected String doIt() throws Exception {
		
		DeleteFromInventoryAging();
		String dateacct = DB.TO_DATE(p_StatementDate);  
		int totalInOuts  = UpdateFromInOut(dateacct);
		int totalInventories = UpdateFromInventory(dateacct);
		int totalProductions = UpdateFromProduction(dateacct);
		int totalManufacturing = UpdateFromManufacturing(dateacct);
		return "Total InOuts "+totalInOuts+" Total Inventories "+totalInventories+" total Productions "+totalProductions+" total Manufacturings "+totalManufacturing;
	}

	private void DeleteFromInventoryAging() {
		
		String sql = "Delete from DS_InventoryAging";
		int i = DB.executeUpdate(sql, get_TrxName());
		System.out.println("total deleted entries"+i);
	}

	private int UpdateFromManufacturing(String dateacct) {
		
		int totalManufacturingForAging = 0 ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT line.m_product_id,CASE WHEN line.costcollectortype='110' THEN line.movementqty*-1 ELSE  line.movementqty END AS MovementQty, "
				+ " line.M_Locator_ID,EXTRACT(epoch FROM "+dateacct+"-line.dateacct) as Age,53035 as AD_Table_ID, line.PP_Cost_Collector_ID as ID ,line.PP_Cost_Collector_ID as LineID,prod.C_UOM_ID "
				+ "from PP_Cost_Collector line,m_product prod "
				+ "where line.m_product_id=prod.m_product_id "
				+ "and line.costcollectortype IN ('110','100') ");
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				totalManufacturingForAging ++;
				MDSInventoryAging aging = new MDSInventoryAging(getCtx(), 0, get_TrxName());
				aging.setM_Locator_ID(rs.getInt("M_Locator_ID"));
				aging.setM_Product_ID(rs.getInt("M_Product_ID"));
				aging.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				int age = rs.getInt("Age");
				int AD_Table_ID = rs.getInt("AD_Table_ID");
				int ID = rs.getInt("ID");
				int LineID = rs.getInt("LineID");
				BigDecimal invCount = rs.getBigDecimal("MovementQty");
				BigDecimal inventoryGLAmt = getCost(AD_Table_ID,ID,LineID);;
				if(age<30)
				{
					aging.setDS_InvBetween0To1Month(invCount);
					aging.setDS_InvAmtBetween0To1Month(inventoryGLAmt);
				}
				if(age<60 && age>30)
				{
					aging.setDS_InvBetween1To2Months(invCount);
					aging.setDS_InvAmtBetween1To2Months(inventoryGLAmt);
				}
				if(age<90 && age>60)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<120 && age>90)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<150 && age>120)
				{
					aging.setDS_InvBetween3To4Months(invCount);
					aging.setDS_InvAmtBetween3To4Months(inventoryGLAmt);
				}
				if(age<180 && age>150)
				{
					aging.setDS_InvBetween4To5Months(invCount);
					aging.setDS_InvAmtBetween4To5Months(inventoryGLAmt);
				}
				if(age>180)
				{
					aging.setDS_InvMorethan6Months(invCount);
					aging.setDS_InvAmtMorethan6Months(inventoryGLAmt);
				}
				aging.saveEx();
				System.out.println(" Total Manufacturing "+totalManufacturingForAging);
			}
		} 
		catch (Exception e) 
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return totalManufacturingForAging;
	}

	private int UpdateFromProduction(String dateacct) {
		
		int totalProductForAging = 0 ;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT line.m_product_id,case when line.IsEndProduct='Y' then line.MovementQty else line.QtyUsed*-1 end movementqty, "
				+ " line.M_Locator_ID,EXTRACT(epoch FROM "+dateacct+"-minout.movementdate) as Age,325 as AD_Table_ID, "
				+ "line.M_Production_ID as ID ,line.M_ProductionLine_ID as LineID,prod.C_UOM_ID "
				+ "from M_Production minout,M_ProductionLine line,m_product prod "
				+ "where minout.M_Production_ID=line.M_Production_ID and line.m_product_id=prod.m_product_id ");
		
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				totalProductForAging ++;
				MDSInventoryAging aging = new MDSInventoryAging(getCtx(), 0, get_TrxName());
				aging.setM_Locator_ID(rs.getInt("M_Locator_ID"));
				aging.setM_Product_ID(rs.getInt("M_Product_ID"));
				aging.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				int age = rs.getInt("Age");
				int AD_Table_ID = rs.getInt("AD_Table_ID");
				int ID = rs.getInt("ID");
				int LineID = rs.getInt("LineID");
				BigDecimal invCount = rs.getBigDecimal("MovementQty");
				BigDecimal inventoryGLAmt = getCost(AD_Table_ID,ID,LineID);;
				if(age<30)
				{
					aging.setDS_InvBetween0To1Month(invCount);
					aging.setDS_InvAmtBetween0To1Month(inventoryGLAmt);
				}
				if(age<60 && age>30)
				{
					aging.setDS_InvBetween1To2Months(invCount);
					aging.setDS_InvAmtBetween1To2Months(inventoryGLAmt);
				}
				if(age<90 && age>60)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<120 && age>90)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<150 && age>120)
				{
					aging.setDS_InvBetween3To4Months(invCount);
					aging.setDS_InvAmtBetween3To4Months(inventoryGLAmt);
				}
				if(age<180 && age>150)
				{
					aging.setDS_InvBetween4To5Months(invCount);
					aging.setDS_InvAmtBetween4To5Months(inventoryGLAmt);
				}
				if(age>180)
				{
					aging.setDS_InvMorethan6Months(invCount);
					aging.setDS_InvAmtMorethan6Months(inventoryGLAmt);
				}
				aging.saveEx();
				System.out.println(" Total Production "+totalProductForAging);
			}
		} 
		catch (Exception e) 
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return totalProductForAging;
	}

	private int UpdateFromInventory(String dateacct) {

		int totalInventoriesForAging = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT line.m_product_id,case when line.QtyInternalUse=0 then line.qtybook-line.QtyCount else line.QtyInternalUse*-1 end movementqty, "
				+ " line.M_Locator_ID,EXTRACT(epoch FROM "+dateacct+"-minout.movementdate)/3600/24 as Age,321 as AD_Table_ID, line.M_Inventory_ID as ID ,line.M_InventoryLine_ID as LineID,prod.C_UOM_ID "
				+ "from M_Inventory minout,M_InventoryLine line,m_product prod "
				+ "where minout.M_Inventory_ID=line.M_Inventory_ID "
				+ "and line.m_product_id=prod.m_product_id ");
		
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				totalInventoriesForAging ++;
				MDSInventoryAging aging = new MDSInventoryAging(getCtx(), 0, get_TrxName());
				aging.setM_Locator_ID(rs.getInt("M_Locator_ID"));
				aging.setM_Product_ID(rs.getInt("M_Product_ID"));
				aging.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				int age = rs.getInt("Age");
				int AD_Table_ID = rs.getInt("AD_Table_ID");
				int ID = rs.getInt("ID");
				int LineID = rs.getInt("LineID");
				BigDecimal invCount = rs.getBigDecimal("MovementQty");
				BigDecimal inventoryGLAmt = getCost(AD_Table_ID,ID,LineID);;
				if(age<30)
				{
					aging.setDS_InvBetween0To1Month(invCount);
					aging.setDS_InvAmtBetween0To1Month(inventoryGLAmt);
				}
				if(age<60 && age>30)
				{
					aging.setDS_InvBetween1To2Months(invCount);
					aging.setDS_InvAmtBetween1To2Months(inventoryGLAmt);
				}
				if(age<90 && age>60)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<120 && age>90)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<150 && age>120)
				{
					aging.setDS_InvBetween3To4Months(invCount);
					aging.setDS_InvAmtBetween3To4Months(inventoryGLAmt);
				}
				if(age<180 && age>150)
				{
					aging.setDS_InvBetween4To5Months(invCount);
					aging.setDS_InvAmtBetween4To5Months(inventoryGLAmt);
				}
				if(age>180)
				{
					aging.setDS_InvMorethan6Months(invCount);
					aging.setDS_InvAmtMorethan6Months(inventoryGLAmt);
				}
				aging.saveEx();
				System.out.println(" Total Inventories "+totalInventoriesForAging);
			}
		} 
		catch (Exception e) 
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return totalInventoriesForAging ; 
	}

	private int UpdateFromInOut(String dateacct) {
		
		int totalInOutsForAging = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT line.m_product_id,case when  minout.issotrx='N' then line.movementqty else  line.movementqty*-1 end as MovementQty,"
				+ " line.M_Locator_ID,EXTRACT(epoch FROM "+dateacct+"-minout.dateacct)/3600/24 as Age,319  as AD_Table_ID, minout.m_inout_id as ID ,line.M_InOutLine_ID as LineID,prod.C_UOM_ID  "
				+ "from M_InOut minout,M_InOutLine line,m_product prod "
				+ "where minout.m_inout_id=line.m_inout_id "
				+ "and minout.movementdate< "+dateacct+"  "
				+ "and line.m_product_id=prod.m_product_id ");		
		try 
		{
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				totalInOutsForAging ++ ;
				MDSInventoryAging aging = new MDSInventoryAging(getCtx(), 0, get_TrxName());
				aging.setM_Locator_ID(rs.getInt("M_Locator_ID"));
				aging.setM_Product_ID(rs.getInt("M_Product_ID"));
				aging.setC_UOM_ID(rs.getInt("C_UOM_ID"));
				int age = rs.getInt("Age");
				int AD_Table_ID = rs.getInt("AD_Table_ID");
				int ID = rs.getInt("ID");
				int LineID = rs.getInt("LineID");
				BigDecimal invCount = rs.getBigDecimal("MovementQty");
				BigDecimal inventoryGLAmt = getCost(AD_Table_ID,ID,LineID);;
				if(age<30)
				{
					aging.setDS_InvBetween0To1Month(invCount);
					aging.setDS_InvAmtBetween0To1Month(inventoryGLAmt);
				}
				if(age<60 && age>30)
				{
					aging.setDS_InvBetween1To2Months(invCount);
					aging.setDS_InvAmtBetween1To2Months(inventoryGLAmt);
				}
				if(age<90 && age>60)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<120 && age>90)
				{
					aging.setDS_InvBetween2To3Months(invCount);
					aging.setDS_InvAmtBetween2To3Months(inventoryGLAmt);
				}
				if(age<150 && age>120)
				{
					aging.setDS_InvBetween3To4Months(invCount);
					aging.setDS_InvAmtBetween3To4Months(inventoryGLAmt);
				}
				if(age<180 && age>150)
				{
					aging.setDS_InvBetween4To5Months(invCount);
					aging.setDS_InvAmtBetween4To5Months(inventoryGLAmt);
				}
				if(age>180)
				{
					aging.setDS_InvMorethan6Months(invCount);
					aging.setDS_InvAmtMorethan6Months(inventoryGLAmt);
				}
				aging.saveEx();
			}
		} 
		catch (Exception e) 
		{
			log.severe(e.toString());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		System.out.println(" Total Inventories "+totalInOutsForAging);
		return totalInOutsForAging;
	}

	private BigDecimal getCost(int AD_Table_ID,int ID , int LineID) 
	{
		BigDecimal Balance = Env.ZERO; 
		PreparedStatement ps = null;
		ResultSet costrs = null;
		StringBuilder sql = new StringBuilder("");
		if(AD_Table_ID==319)
			sql = sql.append("select sum(factacct.AmtAcctDr)-sum(factacct.AmtAcctCr) "
					+ "from M_Product prod, M_Product_Category cat,M_Product_Category_Acct acct,C_ValidCombination comb,fact_Acct factacct "
					+ "where prod.M_Product_Category_ID=cat.m_product_category_id "
					+ "and cat.M_Product_Category_ID=acct.M_Product_Category_ID "
					+ "and acct.P_Asset_Acct=comb.C_ValidCombination_ID "
					+ "and comb.account_id=factacct.account_id "
					+ "and factacct.m_product_id=prod.m_product_id "
					+ "and factacct.record_id= ? "
					+ "and factacct.line_id=? and factacct.AD_Table_ID=319");
		else if(AD_Table_ID==321)
			sql = sql.append("select sum(factacct.AmtAcctDr)-sum(factacct.AmtAcctCr) "
					+ "from M_Product prod, M_Product_Category cat,M_Product_Category_Acct acct,C_ValidCombination comb,fact_Acct factacct "
					+ "where prod.M_Product_Category_ID=cat.m_product_category_id "
					+ "and cat.M_Product_Category_ID=acct.M_Product_Category_ID "
					+ "and acct.P_Asset_Acct=comb.C_ValidCombination_ID "
					+ "and comb.account_id=factacct.account_id "
					+ "and factacct.m_product_id=prod.m_product_id "
					+ "and factacct.record_id= ? "
					+ "and factacct.line_id=? and factacct.AD_Table_ID=321");
		else if(AD_Table_ID==53035)
			sql = sql.append("select sum(factacct.AmtAcctDr)-sum(factacct.AmtAcctCr) "
					+ "from M_Product prod, M_Product_Category cat,M_Product_Category_Acct acct,C_ValidCombination comb,fact_Acct factacct "
					+ "where prod.M_Product_Category_ID=cat.m_product_category_id "
					+ "and cat.M_Product_Category_ID=acct.M_Product_Category_ID "
					+ "and acct.P_Asset_Acct=comb.C_ValidCombination_ID "
					+ "and comb.account_id=factacct.account_id "
					+ "and factacct.m_product_id=prod.m_product_id "
					+ "and factacct.record_id= ? "
					+ "and factacct.line_id=? and factacct.AD_Table_ID=53035");
		else if(AD_Table_ID==325)
			sql = sql.append("select sum(factacct.AmtAcctDr)-sum(factacct.AmtAcctCr) "
					+ "from M_Product prod, M_Product_Category cat,M_Product_Category_Acct acct,C_ValidCombination comb,fact_Acct factacct "
					+ "where prod.M_Product_Category_ID=cat.m_product_category_id "
					+ "and cat.M_Product_Category_ID=acct.M_Product_Category_ID "
					+ "and acct.P_Asset_Acct=comb.C_ValidCombination_ID "
					+ "and comb.account_id=factacct.account_id "
					+ "and factacct.m_product_id=prod.m_product_id "
					+ "and factacct.record_id= ? "
					+ "and factacct.line_id=? and factacct.AD_Table_ID=325");
		try 
		{
			ps = DB.prepareStatement(sql.toString(),get_TrxName());
			ps.setInt(1, ID);
			ps.setInt(2, LineID);
			costrs = ps.executeQuery();
			if(costrs.next())
			{
				Balance = costrs.getBigDecimal(1);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return Balance;
	}

}
