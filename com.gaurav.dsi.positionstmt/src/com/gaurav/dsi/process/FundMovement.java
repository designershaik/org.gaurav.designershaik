package com.gaurav.dsi.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSFundMovement;

public class FundMovement extends SvrProcess
{
	Timestamp currentDate = null;
	@Override
	protected void prepare() 
	{
		currentDate = Env.getContextAsDate(getCtx(), "@#Date@");
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		DB.executeUpdateEx("Delete from DS_FundMovement",get_TrxName());
		addYearsAndQuarters();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MDSFundMovement invMov = null; 
		int i = 3;
		try 
		{
			String sql = "select m_product_id ,name, year,sum(quater1) as Q1,sum(quater2) as Q2,sum(quater3) as Q3,sum(quater4) as Q4,M_AttributeSetInstance_ID " + 
					"from DSFundMovementQuaterly group by name,year,m_product_id,M_AttributeSetInstance_ID order by year ";
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				boolean isAdded = false;
				int m_Product_ID = rs.getInt("M_Product_ID");
				String name = rs.getString("Name");
				BigDecimal quarter1 = rs.getBigDecimal("Q1");
				BigDecimal quarter2 = rs.getBigDecimal("Q2");
				BigDecimal quarter3 = rs.getBigDecimal("Q3");
				BigDecimal quarter4 = rs.getBigDecimal("Q4");
				int asi_id = rs.getInt("M_AttributeSetInstance_ID");
				String year = rs.getString("Year");
				int DS_FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement where m_product_id = ? and M_AttributeSetInstance_ID = ? and DS_Section='C' ",m_Product_ID,asi_id);
				int dsYearMovementFromSectionA_ID = DB.getSQLValue(get_TrxName(), "Select DS_Movement_ID From DS_FundMovement where Year = ? and DS_Section='A'", year);
				MDSFundMovement yearMov = new MDSFundMovement(getCtx(),dsYearMovementFromSectionA_ID, get_TrxName());
				String colYear0 = yearMov.getCol_0();
				String colYear4 = yearMov.getCol_4();
//				String colYear8 = yearMov.getCol_8();
//				String colYear12 = yearMov.getCol_12();
//				String colYear16 = yearMov.getCol_16();
//				String colYear20 = yearMov.getCol_20();
//				String colYear24 = yearMov.getCol_24();
				log.info("Column year 4: "+colYear4);
				if(DS_FundMovement_ID<=0 && colYear0.equalsIgnoreCase(year))
				{
					invMov = new MDSFundMovement(getCtx(), 0, get_TrxName());
					invMov.setDS_Section("C");
					invMov.setName(name);
					invMov.setM_Product_ID(m_Product_ID);
					invMov.setSeqNo(i);
					invMov.setM_AttributeSetInstance_ID(asi_id);
					invMov.setCol_0(quarter1.toString());
					invMov.setCol_1(quarter2.toString());
					invMov.setCol_2(quarter3.toString());
					invMov.setCol_3(quarter4.toString());
					invMov.setAD_PInstance_ID(getAD_PInstance_ID());
					isAdded = true;
					i++;
				}
				else
				{
					invMov = new MDSFundMovement(getCtx(), DS_FundMovement_ID, get_TrxName());
					if(!isAdded && invMov.getCol_4()==null && invMov.getCol_5()==null && invMov.getCol_6()==null && invMov.getCol_7()==null)
					{
						invMov.setCol_4(quarter1.toString());
						invMov.setCol_5(quarter2.toString());
						invMov.setCol_6(quarter3.toString());
						invMov.setCol_7(quarter4.toString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_8()==null && invMov.getCol_9()==null && invMov.getCol_10()==null && invMov.getCol_11()==null)
					{
						invMov.setCol_8(quarter1.toString());
						invMov.setCol_9(quarter2.toString());
						invMov.setCol_10(quarter3.toString());
						invMov.setCol_11(quarter4.toString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_12()==null && invMov.getCol_13()==null && invMov.getCol_14()==null && invMov.getCol_15()==null)
					{
						invMov.setCol_12(quarter1.toString());
						invMov.setCol_13(quarter2.toString());
						invMov.setCol_14(quarter3.toString());
						invMov.setCol_15(quarter4.toString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_16()==null && invMov.getCol_17()==null && invMov.getCol_18()==null && invMov.getCol_19()==null)
					{
						invMov.setCol_16(quarter1.toString());
						invMov.setCol_17(quarter2.toString());
						invMov.setCol_18(quarter3.toString());
						invMov.setCol_19(quarter4.toString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_20()==null)
					{
						invMov.setCol_20(quarter1.add(quarter2).add(quarter3).add(quarter4).toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_21()==null)
					{
						invMov.setCol_21(quarter1.add(quarter2).add(quarter3).add(quarter4).toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_22()==null)
					{
						invMov.setCol_22(quarter1.add(quarter2).add(quarter3).add(quarter4).toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_23()==null)
					{
						invMov.setCol_23(quarter1.add(quarter2).add(quarter3).add(quarter4).toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_24()==null)
					{
						invMov.setCol_24(quarter1.add(quarter2).add(quarter3).add(quarter4).toEngineeringString());
					}
					if(!isAdded && invMov.getCol_25()==null)
					{
						invMov.setCol_25(quarter1.add(quarter2).add(quarter3).add(quarter4).toEngineeringString());
						isAdded = true;
					}
					if(!isAdded)
					{
						BigDecimal col_26 = new BigDecimal(invMov.getCol_26());
						col_26 = col_26 ==null ? Env.ZERO:col_26;
						invMov.setCol_26(col_26.add(quarter1.add(quarter2).add(quarter3).add(quarter4)).toEngineeringString());
						isAdded = true;
					}
				}
				
				invMov.saveEx();
			}

		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		addSummaryYears(i);
		addSummary(i);
		return null;
	}

	private void addSummaryYears(int sequence) 
	{
		MDSFundMovement invMov = null;
		String sql = "select distinct year from DSFundMovementQuaterly order by year";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				boolean isAdded = false;
				String year = rs.getString("year");
				if(invMov==null)
				{
					invMov = new MDSFundMovement(getCtx(), 0, get_TrxName());
					invMov.setDS_Section("D");
					invMov.setSeqNo(sequence++);
					invMov.setCol_0(year);
					invMov.setAD_PInstance_ID(getAD_PInstance_ID());
					isAdded = true;
				}
				else
				{
					if(!isAdded && invMov.getCol_1()==null)
					{
						invMov.setCol_1(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_2()==null)
					{
						invMov.setCol_2(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_3()==null)
					{
						invMov.setCol_3(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_4()==null)
					{
						invMov.setCol_4(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_5()==null)
					{
						invMov.setCol_5(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_6()==null)
					{
						invMov.setCol_6(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_7()==null)
					{
						invMov.setCol_7(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_8()==null)
					{
						invMov.setCol_8(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_9()==null)
					{
						invMov.setCol_9(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_10()==null)
					{
						invMov.setCol_10(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_11()==null)
					{
						invMov.setCol_11(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_12()==null)
					{
						invMov.setCol_12(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_13()==null)
					{
						invMov.setCol_13(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_14()==null)
					{
						invMov.setCol_14(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_15()==null)
					{
						invMov.setCol_15(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_16()==null)
					{
						invMov.setCol_16(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_17()==null)
					{
						invMov.setCol_17(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_18()==null)
					{
						invMov.setCol_18(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_19()==null)
					{
						invMov.setCol_19(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_20()==null)
					{
						invMov.setCol_20(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_21()==null)
					{
						invMov.setCol_21(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_22()==null)
					{
						invMov.setCol_22(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_23()==null)
					{
						invMov.setCol_23(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_24()==null)
					{
						invMov.setCol_24(year);
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_25()==null)
					{
						invMov.setCol_25(year);
						isAdded = true;
					}
					if(!isAdded)
					{
						invMov.setCol_26(year);
						isAdded = true;
					}
				}
				
				invMov.saveEx();
			}
		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		
	}

	
	private void addSummary(int sequence) 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MDSFundMovement invMov = null; 
		try 
		{
			String sql = "select m_product_id ,name, year,sum(quater1)+sum(quater2)+sum(quater3) +sum(quater4) as total,M_AttributeSetInstance_ID " + 
					"from DSFundMovementQuaterly group by name,year,m_product_id,M_AttributeSetInstance_ID order by m_product_id,year ";
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				boolean isAdded = false;
				int m_Product_ID = rs.getInt("M_Product_ID");
				String name = rs.getString("Name");
				BigDecimal total = rs.getBigDecimal("total");
				int asi_id = rs.getInt("M_AttributeSetInstance_ID");
				int DS_FundMovement_ID = DB.getSQLValue(get_TrxName(), "Select DS_FundMovement_ID from DS_FundMovement where m_product_id = ? and M_AttributeSetInstance_ID = ? and DS_Section='E' ",m_Product_ID,asi_id);
				if(DS_FundMovement_ID<=0)
				{
					invMov = new MDSFundMovement(getCtx(), 0, get_TrxName());
					invMov.setDS_Section("E");
					invMov.setName(name);
					invMov.setM_Product_ID(m_Product_ID);
					invMov.setSeqNo(sequence++);
					invMov.setCol_0(total.toEngineeringString());
					invMov.setM_AttributeSetInstance_ID(asi_id);
					invMov.setAD_PInstance_ID(getAD_PInstance_ID());
					isAdded = true;
				}
				else
				{
					invMov = new MDSFundMovement(getCtx(), DS_FundMovement_ID, get_TrxName());
					if(!isAdded && invMov.getCol_1()==null)
					{
						invMov.setCol_1(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_2()==null)
					{
						invMov.setCol_2(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_3()==null)
					{
						invMov.setCol_3(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_4()==null)
					{
						invMov.setCol_4(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_5()==null)
					{
						invMov.setCol_5(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_6()==null)
					{
						invMov.setCol_6(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_7()==null)
					{
						invMov.setCol_7(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_8()==null)
					{
						invMov.setCol_8(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_9()==null)
					{
						invMov.setCol_9(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_10()==null)
					{
						invMov.setCol_10(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_11()==null)
					{
						invMov.setCol_11(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_12()==null)
					{
						invMov.setCol_12(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_13()==null)
					{
						invMov.setCol_13(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_14()==null)
					{
						invMov.setCol_14(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_15()==null)
					{
						invMov.setCol_15(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_16()==null)
					{
						invMov.setCol_16(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_17()==null)
					{
						invMov.setCol_17(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_18()==null)
					{
						invMov.setCol_18(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_19()==null)
					{
						invMov.setCol_19(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_20()==null)
					{
						invMov.setCol_20(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_21()==null)
					{
						invMov.setCol_21(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_22()==null)
					{
						invMov.setCol_22(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_23()==null)
					{
						invMov.setCol_23(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_24()==null)
					{
						invMov.setCol_24(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded && invMov.getCol_25()==null)
					{
						invMov.setCol_25(total.toEngineeringString());
						isAdded = true;
					}
					if(!isAdded)
					{
						BigDecimal col_26 = new BigDecimal(invMov.getCol_26());
						col_26 = col_26 ==null ? Env.ZERO:col_26;
						invMov.setCol_26(col_26.add(total).toEngineeringString());
						isAdded = true;
					}
				}
				
				invMov.saveEx();
			}

		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}

	private void addYearsAndQuarters() 
	{
		MDSFundMovement yearMov = null;
		MDSFundMovement quarterMov = null;
		String sql = "select distinct year from DSFundMovementQuaterly order by year";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = DB.prepareStatement(sql, get_TrxName());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				boolean isAdded = false;
				String year = rs.getString("Year");
				if(yearMov==null)
				{
					yearMov = new MDSFundMovement(getCtx(), 0, get_TrxName());
					yearMov.setSeqNo(1);
					yearMov.setDS_Section("A");
					yearMov.setCol_0(year);
					yearMov.setCol_1(year);
					yearMov.setCol_2(year);
					yearMov.setCol_3(year);
					yearMov.setAD_PInstance_ID(getAD_PInstance_ID());
					
					quarterMov = new MDSFundMovement(getCtx(), 0, get_TrxName());
					quarterMov.setSeqNo(2);
					quarterMov.setDS_Section("B");
					quarterMov.setCol_0("Q1");
					quarterMov.setCol_1("Q2");
					quarterMov.setCol_2("Q3");
					quarterMov.setCol_3("Q4");
					quarterMov.setAD_PInstance_ID(getAD_PInstance_ID());
					isAdded = true;
				}
				else
				{
					if(!isAdded && yearMov.getCol_4()==null)
					{
						yearMov.setCol_4(year);
						yearMov.setCol_5(year);
						yearMov.setCol_6(year);
						yearMov.setCol_7(year);
						
						quarterMov.setCol_4("Q1");
						quarterMov.setCol_5("Q2");
						quarterMov.setCol_6("Q3");
						quarterMov.setCol_7("Q4");
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_8()==null)
					{
						yearMov.setCol_8(year);
						yearMov.setCol_9(year);
						yearMov.setCol_10(year);
						yearMov.setCol_11(year);
						
						quarterMov.setCol_8("Q1");
						quarterMov.setCol_9("Q2");
						quarterMov.setCol_10("Q3");
						quarterMov.setCol_11("Q4");
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_12()==null)
					{
						yearMov.setCol_12(year);
						yearMov.setCol_13(year);
						yearMov.setCol_14(year);
						yearMov.setCol_15(year);
						
						quarterMov.setCol_12("Q1");
						quarterMov.setCol_13("Q2");
						quarterMov.setCol_14("Q3");
						quarterMov.setCol_15("Q4");
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_16()==null)
					{
						yearMov.setCol_16(year);
						yearMov.setCol_17(year);
						yearMov.setCol_18(year);
						yearMov.setCol_19(year);
						
						quarterMov.setCol_16("Q1");
						quarterMov.setCol_17("Q2");
						quarterMov.setCol_18("Q3");
						quarterMov.setCol_19("Q4");
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_20()==null)
					{
						yearMov.setCol_20(year);
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_21()==null)
					{						
						yearMov.setCol_21(year);
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_22()==null)
					{
						yearMov.setCol_22(year);
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_23()==null)
					{
						yearMov.setCol_23(year);
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_24()==null)
					{
						yearMov.setCol_24(year);
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_25()==null)
					{
						yearMov.setCol_25(year);
						isAdded = true;
					}
					if(!isAdded && yearMov.getCol_26()==null)
					{
						yearMov.setCol_26(year);
						isAdded = true;
					}
				}
				yearMov.saveEx();
				quarterMov.saveEx();
			}
		}
		catch(Exception e)
		{
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(rs, pstmt);
		}
	}

}
