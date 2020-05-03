package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSFinReports;
import org.gaurav.dsi.model.MDSFinReportslevel1;
import org.gaurav.dsi.model.MDSFinReportslevel2;
import org.gaurav.dsi.model.MDSFinReportslevel3;
import org.gaurav.dsi.model.MDSFinReportslevel4;
import org.gaurav.dsi.model.MDSFinReportslevel5;
import org.gaurav.dsi.model.MDSFinReportslevel6;


public class ProcessAccountsHiererchy extends SvrProcess {
	int DSI_FinReport;
	int C_Year_ID;
	String Name;
	String reportType;
	String whereClause = " and ";
	BigDecimal TotalLevel6;
	BigDecimal TotalLevel5;
	BigDecimal TotalLevel4;
	BigDecimal TotalLevel3;
	BigDecimal TotalLevel2;
	BigDecimal TotalLevel1;
	int treeID;
	MDSFinReports fin ;
	int C_Activity_ID = 0;
	int C_Project_ID = 0 ;
	int User1_ID = 0 ;
	int User2_ID = 0 ;
	int productGroup_ID = 0 ;
	int C_BPartner_ID = 0 ;
	Timestamp startDate = null;
	Timestamp endDate = null;
	@Override
	protected void prepare() 
	{
		DSI_FinReport = getRecord_ID();
		fin = new MDSFinReports(getCtx(), DSI_FinReport, get_TrxName());
		C_Year_ID = fin.getC_Year_ID();
		reportType = fin.getDS_ReportType();
		treeID = fin.getAD_Tree_ID();
		C_Activity_ID = fin.getC_Activity_ID();
		C_Project_ID = fin.getC_Project_ID();
		User1_ID = fin.getUser1_ID();
		User2_ID = fin.getUser2_ID();
		productGroup_ID = fin.getDS_ProductGroup_ID();
		C_BPartner_ID = fin.getC_BPartner_ID();
		startDate = fin.getStartDate();
		endDate = fin.getEndDate();
	}

	@Override
	protected String doIt() throws Exception 
	{
		
		ArrayList<BigDecimal> TotalBalance;
		BigDecimal TotalDr = Env.ZERO;
		BigDecimal TotalCr = Env.ZERO;
		ArrayList<BigDecimal> OpeningBalance;
		BigDecimal OpeningDr = Env.ZERO;
		BigDecimal OpeningCr = Env.ZERO;
		int level1ID;
		String AccountType;
		int elementID;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String IsSummary;
		BigDecimal level1 = Env.ZERO;
		TotalLevel1 = Env.ZERO;
		BigDecimal finalLevel2 = Env.ZERO;
		log.info("Final Level "+finalLevel2);
		if (reportType.equalsIgnoreCase("BS")) {
			whereClause = " and val.accounttype NOT in ('E','R') and ";
		} else if (reportType.equalsIgnoreCase("PL")) {
			whereClause = " and val.accounttype in ('E','R') and ";
		}
		StringBuilder sql = new StringBuilder("select nd.node_id,val.name,val.ISSUMMARY,val.accounttype,nd.seqno "
				+ "from ad_treenode nd,c_elementvalue val where nd.PARENT_ID =0 "
				+ "and nd.aD_tree_id="
				+ treeID
				+ " "
				+ whereClause
				+ " nd.NODE_ID=val.c_elementvalue_ID and val.isactive='Y' and val.postbudget='Y' ");
		sql.append(" order by nd.seqno");

		try {
			pstmt = DB.prepareStatement(sql.toString(), get_TrxName());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				IsSummary = rs.getString("IsSummary");
				elementID = rs.getInt("node_id");
				AccountType = rs.getString("accounttype");
				MDSFinReportslevel1 lev1 = new MDSFinReportslevel1(getCtx(), 0,
						get_TrxName());
				lev1.setC_ElementValue_ID(elementID);
				lev1.setName(rs.getString("Name"));
				lev1.setAccountType(AccountType);
				lev1.setSeqNo(rs.getInt("seqno"));
				if (IsSummary.equalsIgnoreCase("Y")) {
					lev1.setIsSummary(true);
				} else {
					lev1.setIsSummary(false);
				}

				log.info("Info ID - " + DSI_FinReport);
				lev1.setDS_FinReports_ID(DSI_FinReport);
				lev1.save();
				level1ID = lev1.getDS_FinReports_level1_ID();
				if (IsSummary.equalsIgnoreCase("Y")) {
					log.info("Level 1 ID - "
							+ lev1.getDS_FinReports_level1_ID());
					finalLevel2 = setlevel2(elementID, level1ID);
				} else {
					TotalBalance = getBalance(elementID);
					OpeningBalance = getOpeningBalance(elementID);
					TotalDr = TotalBalance.get(0);
					TotalCr = TotalBalance.get(1);
					OpeningDr = OpeningBalance.get(0);
					OpeningCr = OpeningBalance.get(1);
					BigDecimal BalanceDr = TotalDr.add(OpeningDr);
					BigDecimal BalanceCr = TotalCr.add(OpeningCr);
					BigDecimal FinalBal = Env.ZERO;
					FinalBal = BalanceDr.subtract(BalanceCr);
					lev1.setAmtSourceDr(TotalDr);
					lev1.setAmtSourceCr(TotalCr);
					lev1.setTotalDr(BalanceDr);
					lev1.setTotalCr(BalanceCr);
					if (AccountType.equalsIgnoreCase("A")
							|| AccountType.equals("E")) {
						level1 = FinalBal;
						lev1.setBalance(FinalBal);
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) <= 0) {
						level1 = FinalBal.negate();
						lev1.setBalance(FinalBal.negate());
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) >= 0) {
						level1 = FinalBal.negate();
						lev1.setBalance(FinalBal.negate());
					}
					TotalLevel1 = TotalLevel1.add(level1);
					lev1.saveEx();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs, pstmt);
			rs = null;
			pstmt = null;
		}
		fin.setIsGenerated(true);
		fin.save();
		return "@OK@";
	}

	private BigDecimal setlevel2(int nodeID, int levelid) {
		ArrayList<BigDecimal> TotalBalance;
		BigDecimal TotalDr = Env.ZERO;
		BigDecimal TotalCr = Env.ZERO;
		ArrayList<BigDecimal> OpeningBalance;
		BigDecimal OpeningDr = Env.ZERO;
		BigDecimal OpeningCr = Env.ZERO;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		int level2ID;
		String AccountType;
		int elementID;
		BigDecimal level2 = Env.ZERO;
		TotalLevel2 = Env.ZERO;
		BigDecimal finalLevel3 = Env.ZERO;
		BigDecimal level1Balance = Env.ZERO;
		MDSFinReportslevel1 lev1 = new MDSFinReportslevel1(getCtx(), levelid,get_TrxName());
		String sql = "select  nd.node_id,val.name,val.ISSUMMARY,val.accounttype,nd.seqno from ad_treenode nd , "
				+ "c_elementvalue val  where nd.parent_id="
				+ nodeID
				+ " and nd.NODE_ID=val.c_elementvalue_ID  "
				+ whereClause
				+ " nd.ad_tree_id="+treeID+" and  val.isactive='Y' and val.postbudget='Y' ";
		
		

		try {
			pstmt1 = DB.prepareStatement(sql, get_TrxName());
			rs1 = pstmt1.executeQuery();
			while (rs1.next()) {
				String IsSummary = rs1.getString("IsSummary");
				elementID = rs1.getInt("node_id");
				AccountType = rs1.getString("accounttype");
				MDSFinReportslevel2 lev2 = new MDSFinReportslevel2(getCtx(), 0,
						get_TrxName());
				lev2.setC_ElementValue_ID(elementID);
				lev2.setName(rs1.getString("Name"));
				lev2.setAccountType(AccountType);
				lev2.setSeqNo(rs1.getInt("seqno"));
				if (IsSummary.equalsIgnoreCase("Y")) {
					lev2.setIsSummary(true);
				} else {
					lev2.setIsSummary(false);
				}
				lev2.setDS_FinReports_level1_ID(levelid);
				lev2.save();
				level2ID = lev2.getDS_FinReports_level2_ID();
				if (IsSummary.equalsIgnoreCase("Y")) {
					log.info("Level ID - " + lev2.getDS_FinReports_level2_ID());
					finalLevel3 = setleve3(elementID, level2ID);

					level1Balance = lev1.getBalance();
					level1Balance = level1Balance.add(finalLevel3);
					lev1.setBalance(level1Balance);
					lev1.save();
				} else {
					TotalBalance = getBalance(elementID);
					OpeningBalance = getOpeningBalance(elementID);
					TotalDr = TotalBalance.get(0);
					TotalCr = TotalBalance.get(1);
					OpeningDr = OpeningBalance.get(0);
					OpeningCr = OpeningBalance.get(1);
					BigDecimal BalanceDr = TotalDr.add(OpeningDr);
					BigDecimal BalanceCr = TotalCr.add(OpeningCr);
					BigDecimal FinalBal = Env.ZERO;

					FinalBal = BalanceDr.subtract(BalanceCr);
					lev2.setAmtSourceDr(TotalDr);
					lev2.setAmtSourceCr(TotalCr);
					lev2.setTotalDr(BalanceDr);
					lev2.setTotalCr(BalanceCr);
					if (AccountType.equalsIgnoreCase("A")
							|| AccountType.equals("E")) {
						level2 = FinalBal;
						lev2.setBalance(FinalBal);

						level1Balance = lev1.getBalance();
						level1Balance = level1Balance.add(level2);
						lev1.setBalance(level1Balance);
						lev1.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) <= 0) {
						level2 = FinalBal.negate();
						lev2.setBalance(FinalBal.negate());
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) >= 0) {
						level2 = FinalBal.negate();
						lev2.setBalance(FinalBal.negate());
					}
					TotalLevel2 = TotalLevel2.add(level2);
					lev2.saveEx();
				}
			}
			TotalLevel2 = TotalLevel2.add(finalLevel3);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DB.close(rs1, pstmt1);
			rs1 = null;
			pstmt1 = null;
		}
		return TotalLevel2;

	}

	private BigDecimal setleve3(int nodeID, int levelid) {
		ArrayList<BigDecimal> TotalBalance;
		BigDecimal TotalDr = Env.ZERO;
		BigDecimal TotalCr = Env.ZERO;
		ArrayList<BigDecimal> OpeningBalance;
		BigDecimal OpeningDr = Env.ZERO;
		BigDecimal OpeningCr = Env.ZERO;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		int level3ID;
		String AccountType;
		int elementID;
		BigDecimal level3 = Env.ZERO;
		TotalLevel3 = Env.ZERO;
		BigDecimal finalLevel4 = Env.ZERO;
		BigDecimal level2Balance = Env.ZERO;
		MDSFinReportslevel2 lev2 = new MDSFinReportslevel2(getCtx(), levelid,
				get_TrxName());
		String sql = "select  nd.node_id,val.name,val.ISSUMMARY,val.accounttype,nd.seqno from ad_treenode nd , "
				+ "c_elementvalue val  where nd.parent_id="
				+ nodeID
				+ " and nd.NODE_ID=val.c_elementvalue_ID  "
				+ whereClause
				+ " nd.ad_tree_id="+treeID+" and val.isactive='Y' and val.postbudget='Y' order by nd.seqno";

		try {
			pstmt2 = DB.prepareStatement(sql, get_TrxName());
			rs2 = pstmt2.executeQuery();
			while (rs2.next()) {
				String IsSummary = rs2.getString("IsSummary");
				elementID = rs2.getInt("node_id");
				AccountType = rs2.getString("accounttype");
				MDSFinReportslevel3 lev3 = new MDSFinReportslevel3(getCtx(), 0,
						get_TrxName());
				lev3.setC_ElementValue_ID(elementID);
				lev3.setName(rs2.getString("Name"));
				lev3.setAccountType(AccountType);
				lev3.setSeqNo(rs2.getInt("seqno"));
				if (IsSummary.equalsIgnoreCase("Y")) {
					lev3.setIsSummary(true);
				} else {
					lev3.setIsSummary(false);
				}
				lev3.setDS_FinReports_level2_ID(levelid);
				lev3.save();
				level3ID = lev3.getDS_FinReports_level3_ID();
				if (IsSummary.equalsIgnoreCase("Y")) {
					log.info("Level ID - " + lev3.getDS_FinReports_level3_ID());
					finalLevel4 = setleve4(elementID, level3ID);

					level2Balance = lev2.getBalance();
					level2Balance = level2Balance.add(finalLevel4);
					lev2.setBalance(level2Balance);
					lev2.save();
				} else {
					TotalBalance = getBalance(elementID);
					OpeningBalance = getOpeningBalance(elementID);
					TotalDr = TotalBalance.get(0);
					TotalCr = TotalBalance.get(1);
					OpeningDr = OpeningBalance.get(0);
					OpeningCr = OpeningBalance.get(1);
					BigDecimal BalanceDr = TotalDr.add(OpeningDr);
					BigDecimal BalanceCr = TotalCr.add(OpeningCr);
					BigDecimal FinalBal = Env.ZERO;

					FinalBal = BalanceDr.subtract(BalanceCr);
					lev3.setAmtSourceDr(TotalDr);
					lev3.setAmtSourceCr(TotalCr);
					lev3.setTotalDr(BalanceDr);
					lev3.setTotalCr(BalanceCr);
					if (AccountType.equalsIgnoreCase("A")
							|| AccountType.equals("E")) {
						level3 = FinalBal;
						lev3.setBalance(level3);

						level2Balance = lev2.getBalance();
						level2Balance = level2Balance.add(level3);
						lev2.setBalance(level2Balance);
						lev2.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) <= 0) {
						level3 = FinalBal.negate();
						lev3.setBalance(FinalBal.negate());

						level2Balance = lev2.getBalance();
						level2Balance = level2Balance.add(level3);
						lev2.setBalance(level2Balance);
						lev2.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) >= 0) {
						level3 = FinalBal.negate();
						lev3.setBalance(FinalBal.negate());

						level2Balance = lev2.getBalance();
						level2Balance = level2Balance.add(level3);
						lev2.setBalance(level2Balance);
						lev2.save();
					}
					TotalLevel3 = TotalLevel3.add(level3);
					lev3.saveEx();
				}
			}
			TotalLevel3 = TotalLevel3.add(finalLevel4);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DB.close(rs2, pstmt2);
			rs2 = null;
			pstmt2 = null;
		}
		return TotalLevel3;
	}

	private BigDecimal setleve4(int nodeID, int levelid) {
		ArrayList<BigDecimal> TotalBalance;
		BigDecimal TotalDr = Env.ZERO;
		BigDecimal TotalCr = Env.ZERO;
		ArrayList<BigDecimal> OpeningBalance;
		BigDecimal OpeningDr = Env.ZERO;
		BigDecimal OpeningCr = Env.ZERO;
		PreparedStatement pstmt3 = null;
		ResultSet rs3 = null;
		int level4ID;
		String AccountType;
		int elementID;
		BigDecimal level4 = Env.ZERO;
		TotalLevel4 = Env.ZERO;
		BigDecimal finalLevel5 = Env.ZERO;
		BigDecimal level3Balance = Env.ZERO;
		MDSFinReportslevel3 lev3 = new MDSFinReportslevel3(getCtx(), levelid,
				get_TrxName());
		String sql = "select  nd.node_id,val.name,val.ISSUMMARY,val.accounttype,nd.seqno from ad_treenode nd , "
				+ "c_elementvalue val  where nd.parent_id="
				+ nodeID
				+ " and nd.NODE_ID=val.c_elementvalue_ID  "
				+ whereClause
				+ " nd.ad_tree_id="+treeID+" and val.isactive='Y' and val.postbudget='Y' order by nd.seqno";

		try {
			pstmt3 = DB.prepareStatement(sql, get_TrxName());
			rs3 = pstmt3.executeQuery();
			while (rs3.next()) {
				String IsSummary = rs3.getString("IsSummary");
				elementID = rs3.getInt("node_id");
				AccountType = rs3.getString("accounttype");
				MDSFinReportslevel4 lev4 = new MDSFinReportslevel4(getCtx(), 0,
						get_TrxName());
				lev4.setC_ElementValue_ID(elementID);
				lev4.setName(rs3.getString("Name"));
				lev4.setAccountType(AccountType);
				lev4.setSeqNo(rs3.getInt("seqno"));
				if (IsSummary.equalsIgnoreCase("Y")) {
					lev4.setIsSummary(true);
				} else {
					lev4.setIsSummary(false);
				}
				lev4.setDS_FinReports_level3_ID(levelid);
				lev4.save();
				level4ID = lev4.getDS_FinReports_level4_ID();
				if (IsSummary.equalsIgnoreCase("Y")) {
					log.info("Level ID - " + lev4.getDS_FinReports_level4_ID());
					finalLevel5 = setleve5(elementID, level4ID);

					level3Balance = lev3.getBalance();
					level3Balance = level3Balance.add(finalLevel5);
					lev3.setBalance(level3Balance);
					lev3.save();
				} else {
					TotalBalance = getBalance(elementID);
					OpeningBalance = getOpeningBalance(elementID);
					TotalDr = TotalBalance.get(0);
					TotalCr = TotalBalance.get(1);
					OpeningDr = OpeningBalance.get(0);
					OpeningCr = OpeningBalance.get(1);
					BigDecimal BalanceDr = TotalDr.add(OpeningDr);
					BigDecimal BalanceCr = TotalCr.add(OpeningCr);
					BigDecimal FinalBal = Env.ZERO;

					FinalBal = BalanceDr.subtract(BalanceCr);
					lev4.setAmtSourceDr(TotalDr);
					lev4.setAmtSourceCr(TotalCr);
					lev4.setTotalDr(BalanceDr);
					lev4.setTotalCr(BalanceCr);
					if (AccountType.equalsIgnoreCase("A")
							|| AccountType.equals("E")) {
						level4 = FinalBal;
						lev4.setBalance(FinalBal);

						level3Balance = lev3.getBalance();
						level3Balance = level3Balance.add(level4);
						lev3.setBalance(level3Balance);
						lev3.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) <= 0) {
						level4 = FinalBal.negate();
						lev4.setBalance(level4);

						level3Balance = lev3.getBalance();
						level3Balance = level3Balance.add(level4);
						lev3.setBalance(level3Balance);
						lev3.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) >= 0) {
						level4 = FinalBal.negate();
						lev4.setBalance(level4);

						level3Balance = lev3.getBalance();
						level3Balance = level3Balance.add(level4);
						lev3.setBalance(level3Balance);
						lev3.save();
						}
						
					TotalLevel4 = TotalLevel4.add(level4);
					lev4.saveEx();
				}
			}
			TotalLevel4 = TotalLevel4.add(finalLevel5);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DB.close(rs3, pstmt3);
			rs3 = null;
			pstmt3 = null;
		}
		return TotalLevel4;
	}

	private BigDecimal setleve5(int nodeID, int levelid) throws SQLException {
		ArrayList<BigDecimal> TotalBalance;
		BigDecimal TotalDr = Env.ZERO;
		BigDecimal TotalCr = Env.ZERO;
		ArrayList<BigDecimal> OpeningBalance;
		BigDecimal OpeningDr = Env.ZERO;
		BigDecimal OpeningCr = Env.ZERO;
		PreparedStatement pstmt4 = null;
		ResultSet rs4 = null;
		int level5ID;
		String AccountType;
		int elementID;
		BigDecimal level5 = Env.ZERO;
		TotalLevel5 = Env.ZERO;
		BigDecimal finalLevel6 = Env.ZERO;
		BigDecimal level4Balance = Env.ZERO;
		MDSFinReportslevel4 lev4 = new MDSFinReportslevel4(getCtx(), levelid,
				get_TrxName());
		String sql = "select  nd.node_id,val.name,val.ISSUMMARY,val.accounttype,nd.seqno from ad_treenode nd , "
				+ "c_elementvalue val  where nd.parent_id="
				+ nodeID
				+ " and nd.NODE_ID=val.c_elementvalue_ID  "
				+ whereClause
				+ " nd.ad_tree_id="+treeID+" and val.isactive='Y' and val.postbudget='Y' order by nd.seqno";

		try {
			pstmt4 = DB.prepareStatement(sql, get_TrxName());
			rs4 = pstmt4.executeQuery();
			while (rs4.next()) {
				String IsSummary = rs4.getString("IsSummary");
				elementID = rs4.getInt("node_id");
				AccountType = rs4.getString("accounttype");
				MDSFinReportslevel5 lev5 = new MDSFinReportslevel5(getCtx(), 0,
						get_TrxName());
				lev5.setC_ElementValue_ID(elementID);
				lev5.setName(rs4.getString("Name"));
				lev5.setAccountType(AccountType);
				lev5.setSeqNo(rs4.getInt("seqno"));
				if (IsSummary.equalsIgnoreCase("Y")) {
					lev5.setIsSummary(true);
				} else {
					lev5.setIsSummary(false);
				}
				lev5.setDS_FinReports_level4_ID(levelid);
				lev5.save();
				level5ID = lev5.getDS_FinReports_level5_ID();
				if (IsSummary.equalsIgnoreCase("Y")) {
					log.info("Level ID - " + lev5.getDS_FinReports_level5_ID());
					finalLevel6 = setleve6(elementID, level5ID);
					level4Balance = lev4.getBalance();
					level4Balance = level4Balance.add(finalLevel6);
					lev4.setBalance(level4Balance);
					lev4.save();
				} else {
					TotalBalance = getBalance(elementID);
					OpeningBalance = getOpeningBalance(elementID);
					TotalDr = TotalBalance.get(0);
					TotalCr = TotalBalance.get(1);
					OpeningDr = OpeningBalance.get(0);
					OpeningCr = OpeningBalance.get(1);
					BigDecimal BalanceDr = TotalDr.add(OpeningDr);
					BigDecimal BalanceCr = TotalCr.add(OpeningCr);
					BigDecimal FinalBal = Env.ZERO;
					FinalBal = BalanceDr.subtract(BalanceCr);
					lev5.setAmtSourceDr(TotalDr);
					lev5.setAmtSourceCr(TotalCr);
					lev5.setTotalDr(BalanceDr);
					lev5.setTotalCr(BalanceCr);

					if (AccountType.equalsIgnoreCase("A")
							|| AccountType.equals("E")) {
						level5 = FinalBal;
						lev5.setBalance(level5);
						level4Balance = lev4.getBalance();
						level4Balance = level4Balance.add(level5);
						lev4.setBalance(level4Balance);
						lev4.save();
					} else if ((AccountType.equalsIgnoreCase("L")|| AccountType.equalsIgnoreCase("O") || AccountType.equalsIgnoreCase("R"))&& FinalBal.compareTo(Env.ZERO) <= 0) {
						level5 = FinalBal.negate();
						lev5.setBalance(level5);

						level4Balance = lev4.getBalance();
						level4Balance = level4Balance.add(level5);
						lev4.setBalance(level4Balance);
						lev4.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) >= 0) {
						level5 = FinalBal.negate();
						lev5.setBalance(level5);
						level4Balance = lev4.getBalance();
						level4Balance = level4Balance.add(level5);
						lev4.setBalance(level4Balance);
						lev4.save();
					}
					TotalLevel5 = TotalLevel5.add(level5);
					lev5.saveEx();
				}
			}
			TotalLevel5 = TotalLevel5.add(finalLevel6);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs4, pstmt4);
			rs4 = null;
			pstmt4 = null;
		}
		return TotalLevel5;
	}

	private BigDecimal setleve6(int nodeID, int levelid) throws SQLException {
		ArrayList<BigDecimal> TotalBalance;
		BigDecimal TotalDr = Env.ZERO;
		BigDecimal TotalCr = Env.ZERO;
		ArrayList<BigDecimal> OpeningBalance;
		BigDecimal OpeningDr = Env.ZERO;
		BigDecimal OpeningCr = Env.ZERO;
		PreparedStatement pstmt5 = null;
		ResultSet rs5 = null;
		String AccountType;
		int elementID;
		BigDecimal level6 = Env.ZERO;
		TotalLevel6 = Env.ZERO;
		MDSFinReportslevel5 lev5 = new MDSFinReportslevel5(getCtx(), levelid,
				get_TrxName());
		BigDecimal level5Balance;
		String sql = "select  nd.node_id,val.name,val.ISSUMMARY,val.accounttype,nd.seqno from ad_treenode nd , "
				+ "c_elementvalue val  where nd.parent_id="
				+ nodeID
				+ " and nd.NODE_ID=val.c_elementvalue_ID  "
				+ whereClause
				+ " nd.ad_tree_id="+treeID+" and val.isactive='Y' and val.postbudget='Y' order by nd.seqno";

		try {
			pstmt5 = DB.prepareStatement(sql, get_TrxName());
			rs5 = pstmt5.executeQuery();
			while (rs5.next()) {
				String IsSummary = rs5.getString("IsSummary");
				elementID = rs5.getInt("node_id");
				AccountType = rs5.getString("accounttype");
				MDSFinReportslevel6 lev6 = new MDSFinReportslevel6(getCtx(), 0,
						get_TrxName());
				lev6.setC_ElementValue_ID(elementID);
				lev6.setName(rs5.getString("Name"));
				lev6.setAccountType(AccountType);
				lev6.setSeqNo(rs5.getInt("seqno"));
				lev6.setIsSummary(false);
				lev6.setDS_FinReports_level5_ID(levelid);
				lev6.save();
				if (IsSummary.equalsIgnoreCase("Y")) {
					log.info("Level ID - " + lev6.getDS_FinReports_level6_ID());
				} else {
					TotalBalance = getBalance(elementID);
					OpeningBalance = getOpeningBalance(elementID);
					TotalDr = TotalBalance.get(0);
					TotalCr = TotalBalance.get(1);
					OpeningDr = OpeningBalance.get(0);
					OpeningCr = OpeningBalance.get(1);
					BigDecimal BalanceDr = TotalDr.add(OpeningDr);
					BigDecimal BalanceCr = TotalCr.add(OpeningCr);
					BigDecimal FinalBal = Env.ZERO;

					FinalBal = BalanceDr.subtract(BalanceCr);
					lev6.setAmtSourceDr(TotalDr);
					lev6.setAmtSourceCr(TotalCr);
					lev6.setTotalDr(BalanceDr);
					lev6.setTotalCr(BalanceCr);
					if (AccountType.equalsIgnoreCase("A")
							|| AccountType.equals("E")) {

						level6 = FinalBal;
						lev6.setBalance(level6);
						level5Balance = lev5.getBalance();
						level5Balance = level5Balance.add(level6);
						lev5.setBalance(level5Balance);
						lev5.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) <= 0) {
						level6 = FinalBal.negate();
						lev6.setBalance(level6);
						level5Balance = lev5.getBalance();
						level5Balance = level5Balance.add(level6);
						lev5.setBalance(level5Balance);
						lev5.save();
					} else if ((AccountType.equalsIgnoreCase("L")
							|| AccountType.equalsIgnoreCase("O") || AccountType
								.equalsIgnoreCase("R"))
							&& FinalBal.compareTo(Env.ZERO) >= 0) {
						level6 = FinalBal.negate();
						lev6.setBalance(level6);
						level5Balance = lev5.getBalance();
						level5Balance = level5Balance.add(level6);
						lev5.setBalance(level5Balance);
						lev5.save();
					}
					lev6.saveEx();
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			DB.close(rs5, pstmt5);
			rs5 = null;
			pstmt5 = null;
		}
		return TotalLevel6;

	}

	private ArrayList<BigDecimal> getBalance(int elementID) 
	{
		PreparedStatement ps;
		ResultSet rs = null;
		ArrayList<BigDecimal> balInfo = new ArrayList<BigDecimal>();
		StringBuilder sql = new StringBuilder("");
		if(startDate==null && endDate==null)
			sql = new StringBuilder("select coalesce(sum(acct.amtacctdr),0) as Debit,coalesce(sum(acct.amtacctcr),0) as Credit "
									+ "from fact_acct acct where acct.dateAcct between (select min(STARTDATE) from C_Period where C_YEAR_ID= ?) "
									+ " and (select MAX(ENDDATE) from C_Period where C_YEAR_ID= ?) and acct.account_id=? ");
		if(startDate!=null && endDate!=null)
			sql = new StringBuilder("select coalesce(sum(acct.amtacctdr),0) as Debit,coalesce(sum(acct.amtacctcr),0) as Credit "
											+ "from fact_acct acct where acct.dateAcct between ? and ? and acct.account_id=? ");
		if(startDate==null && endDate!=null)
			sql = new StringBuilder("select coalesce(sum(acct.amtacctdr),0) as Debit,coalesce(sum(acct.amtacctcr),0) as Credit "
									+ "from fact_acct acct where acct.dateAcct between (select min(STARTDATE) from C_Period where C_YEAR_ID= ?) "
									+ " and ? and acct.account_id=? ");
		
		if(startDate!=null && endDate==null)
			sql = new StringBuilder("select coalesce(sum(acct.amtacctdr),0) as Debit,coalesce(sum(acct.amtacctcr),0) as Credit "
									+ "from fact_acct acct where acct.dateAcct between ? "
									+ " and (select MAX(ENDDATE) from C_Period where C_YEAR_ID= ?) and acct.account_id=? ");
		
		if(C_Activity_ID!=0)
			sql.append(" and acct.C_Activity_ID =  "+C_Activity_ID);
		if(C_Project_ID!=0)
			sql.append(" and acct.C_Project_ID =  "+C_Project_ID);
		if(User1_ID!=0)
			sql.append(" and acct.User1_ID =  "+User1_ID);
		if(User2_ID!=0)
			sql.append(" and acct.User2_ID =  "+User2_ID);
		if(productGroup_ID!=0)
			sql.append(" and acct.M_Product_ID IN  ( SELECT M_Product_ID FROM DS_ProductGroup_Products WHERE DS_ProductGroup_ID = "+productGroup_ID+")");
		if(C_BPartner_ID!=0)
			sql.append(" and acct.C_BPartner_ID =  "+C_BPartner_ID);
		
		ps = DB.prepareStatement(sql.toString(), get_TrxName());
		try 
		{
			if(startDate==null && endDate==null)
			{
				ps.setInt(1, C_Year_ID);
				ps.setInt(2, C_Year_ID);
				ps.setInt(3, elementID);
			}
			if(startDate!=null && endDate!=null)
			{
				ps.setTimestamp(1, startDate);
				ps.setTimestamp(2, endDate);
				ps.setInt(3, elementID);
			}
			if(startDate==null && endDate!=null)
			{
				ps.setInt(1, C_Year_ID);
				ps.setTimestamp(2, endDate);
				ps.setInt(3, elementID);
			}
			if(startDate!=null && endDate==null)
			{
				ps.setTimestamp(1, startDate);
				ps.setInt(2, C_Year_ID);
				ps.setInt(3, elementID);
			}
			rs = ps.executeQuery();
			while (rs.next()) 
			{
				BigDecimal balanceDebit = rs.getBigDecimal("Debit");
				BigDecimal balanceCredit = rs.getBigDecimal("Credit");
				balInfo.add(0, balanceDebit);
				balInfo.add(1, balanceCredit);
			}
		} 
		catch (SQLException e) 
		{
			System.out.println(ps);
			e.printStackTrace();
		} 
		finally 
		{
			DB.close(rs, ps);
			rs = null;
			ps = null;
		}
		return balInfo;
	}

	private ArrayList<BigDecimal> getOpeningBalance(int elementID) {
		PreparedStatement ps;
		ResultSet rs = null;
		ArrayList<BigDecimal> begBalance = new ArrayList<BigDecimal>();
		StringBuilder sql = new StringBuilder("");
		Timestamp firstDateOfTheYear = DB.getSQLValueTS(get_TrxName(), "Select cp.StartDate From C_Period cp where cp.C_Year_ID = ? order by cp.startdate ", C_Year_ID);
		Timestamp yearEndDate = DB.getSQLValueTS(get_TrxName(), "Select cp.StartDate From C_Period cp where cp.C_Year_ID = ? order by cp.startdate desc ", C_Year_ID);
		if(startDate==null)
			startDate = firstDateOfTheYear;
		if(reportType.equalsIgnoreCase("PL") && (startDate.equals(firstDateOfTheYear) || startDate!=null && endDate!=null))
		{
			begBalance.add(0, Env.ZERO);
			begBalance.add(1, Env.ZERO);
		}
		else
		{
			if (reportType.equalsIgnoreCase("PL"))
				sql = new StringBuilder("select coalesce(sum(acct.amtacctdr),0) as OpeningDebit,"
						+ " coalesce(sum(acct.amtacctcr),0) as OpeningCredit "
						+ " from fact_acct acct "
						+ "	where acct.ACCOUNT_ID= ? and acct.dateacct between ? and ? ");
			
			if(reportType.equalsIgnoreCase("BS"))
				sql = new StringBuilder("select coalesce(sum(acct.amtacctdr),0) as OpeningDebit,"
						+ " coalesce(sum(acct.amtacctcr),0) as OpeningCredit "
						+ " from fact_acct acct "
						+ "where acct.ACCOUNT_ID= ? and acct.dateacct < (select min(STARTDATE) from C_Period where C_YEAR_ID="
						+ C_Year_ID + ")  ");
			
				
			if(C_Activity_ID!=0)
				sql.append(" and acct.C_Activity_ID =  "+C_Activity_ID);
			if(C_Project_ID!=0)
				sql.append(" and acct.C_Project_ID =  "+C_Project_ID);
			if(User1_ID!=0)
				sql.append(" and acct.User1_ID =  "+User1_ID);
			if(User2_ID!=0)
				sql.append(" and acct.User2_ID =  "+User2_ID);
			if(productGroup_ID!=0)
				sql.append(" and acct.M_Product_ID IN  ( SELECT M_Product_ID FROM DS_ProductGroup_Products WHERE DS_ProductGroup_ID = "+productGroup_ID+")");
			if(C_BPartner_ID!=0)
				sql.append(" and acct.C_BPartner_ID =  "+C_BPartner_ID);
			
			ps = DB.prepareStatement(sql.toString(), get_TrxName());
			try 
			{
				ps.setInt(1, elementID);
				if (reportType.equalsIgnoreCase("PL") && startDate==null && endDate==null)
				{
					ps.setTimestamp(2, firstDateOfTheYear);
					ps.setTimestamp(3, yearEndDate);
				}
				if (reportType.equalsIgnoreCase("PL") && startDate==null && endDate!=null)
				{
					ps.setTimestamp(2, firstDateOfTheYear);
					ps.setTimestamp(3, yearEndDate);
				}
				rs = ps.executeQuery();
				while (rs.next()) 
				{	
					begBalance.add(0, rs.getBigDecimal("OpeningDebit"));
					begBalance.add(1, rs.getBigDecimal("OpeningCredit"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DB.close(rs, ps);
				rs = null;
				ps = null;
			}
		}
		return begBalance;
	}
}

