package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MConversionRate;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrgInfo;
import org.compiere.model.MTax;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSInvoiceTaxDetails;
import org.gaurav.dsi.model.MDSTaxFiling;
import org.gaurav.dsi.model.MDSTaxFilingDet;

public class SimplifiedTaxFiling extends SvrProcess{

	int p_C_Period_ID = 0 ;
	Timestamp p_DateAcct_From = null;
	Timestamp p_DateAcct_To = null;
	MDSTaxFiling filing = null ; 
	int C_Country_ID = 0 ; 
	String p_Type = "";
	int p_Tax_ID = 0 ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("C_Period_ID"))
				p_C_Period_ID = para[i].getParameterAsInt();
			else if (name.equals("DateAcct"))
			{
				p_DateAcct_From = (Timestamp)para[i].getParameter();
				p_DateAcct_To = (Timestamp)para[i].getParameter_To();
			}
			else if(name.equals("SOPOType"))
				p_Type = para[i].getParameterAsString();
			else if(name.equals("C_Tax_ID"))
				p_Tax_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		filing = new MDSTaxFiling(getCtx(), getRecord_ID(), get_TrxName());
		MOrgInfo info = MOrgInfo.get(getCtx(), Env.getAD_Org_ID(getCtx()),get_TrxName());
		C_Country_ID = info.getC_Location().getC_Country_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		String whereClause = "";
		if(!p_Type.equalsIgnoreCase("B"))
			whereClause = " and SOPOType ='"+p_Type+"'";
		if(p_Tax_ID>0)
			whereClause = whereClause.concat(" and C_Tax_ID="+p_Tax_ID);
		
		List<MTax> taxes = new Query(getCtx(), MTax.Table_Name, " AD_Client_ID = ? "+whereClause, get_TrxName())
				.setParameters(getAD_Client_ID())
				.setOrderBy(MTax.COLUMNNAME_SOPOType)
				.setOnlyActiveRecords(true).list();
		for(MTax tax : taxes)
		{
			int C_Tax_ID = tax.getC_Tax_ID();
			boolean isIndirectBaseAmt = tax.get_ValueAsBoolean("DS_IsImportVAT");
		
			String sqlTaxSummary = "with t1 as ( "
					+ "SELECT  "
					+ "currencybase(line.TaxAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS TaxAmtBHD,line.TaxAmt ," + 
					" currencybase(line.LineNetAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS TaxBaseAmtBHD,"
					+ "tax.name,tax.rate,tax.C_Tax_ID,cloc.C_COuntry_ID "
					+ "FROM c_invoice_v ci , C_BPartner_Location loc ,C_Location cloc ,C_InvoiceLine line,C_Tax tax "
					+ "WHERE ci.C_Invoice_ID=line.C_Invoice_ID "
					+ "AND ci.C_BPartner_Location_ID = loc.C_BPartner_Location_ID "
					+ "AND loc.C_Location_ID = cloc.C_Location_Id "
					+ "AND line.C_Tax_ID=tax.C_Tax_ID "
					+ "AND ci.DateAcct BETWEEN ? AND ? "
					+ "AND ci.DocStatus IN ('CO','RE','CL') "
					+ "AND tax.C_Tax_ID = ? "
					+ "order by ci.DateAcct ) "
					+ "select Name,Rate,sum(TaxAmtBHD) as TaxBHD,sum(TaxBaseAmtBHD) as BaseAmtBHD,C_Tax_ID,C_COuntry_ID "  
					+ "from t1 "
					+ "group by Name,Rate,C_Tax_ID,C_COuntry_ID  ";
			
			PreparedStatement taxSummaryPstmt = null;
			ResultSet taxSummaryrs = null;
			try 
			{
				taxSummaryPstmt = DB.prepareStatement(sqlTaxSummary, get_TrxName());
				taxSummaryPstmt.setTimestamp(1, p_DateAcct_From);
				taxSummaryPstmt.setTimestamp(2, p_DateAcct_To);
				taxSummaryPstmt.setInt(3, C_Tax_ID);
				taxSummaryrs = taxSummaryPstmt.executeQuery();
				while(taxSummaryrs.next())
				{
					BigDecimal taxAmt = taxSummaryrs.getBigDecimal("TaxBHD");
					String name = taxSummaryrs.getString("Name");
					BigDecimal rate = taxSummaryrs.getBigDecimal("Rate");
					int country_ID = taxSummaryrs.getInt("C_Country_ID");
					BigDecimal baseAmt = Env.ZERO;
					
					MDSTaxFilingDet details = new MDSTaxFilingDet(getCtx(), 0, get_TrxName());
					details.setDS_TaxFiling_ID(getRecord_ID());
					details.setC_Tax_ID(C_Tax_ID);
					details.setTaxAmt(taxAmt);
					details.setTaxBaseAmt(baseAmt);
					details.setDescription(name);
					details.setLine(getLine());
					details.setC_Country_ID(country_ID);
					details.set_ValueNoCheck("Rate", rate);
					details.saveEx();
					
					if(!isIndirectBaseAmt)
						baseAmt = taxSummaryrs.getBigDecimal("BaseAmtBHD");
					else
						baseAmt = getBaseAmount(C_Tax_ID,country_ID,details.getDS_TaxFiling_Det_ID(),rate);
					
					details.setTaxBaseAmt(baseAmt);
					details.saveEx();
					updateInvoiceDetails(C_Tax_ID,country_ID, details.getDS_TaxFiling_Det_ID(),tax.getParent_Tax_ID(),isIndirectBaseAmt);
					
				}
			} 
			catch (SQLException e) 
			{
				log.severe(e.getMessage());
			}
		}
		filing.setSOPOType(p_Type);
		filing.setDateTo(p_DateAcct_To);
		filing.setDateFrom(p_DateAcct_From);
		filing.setProcessed(true);
		filing.saveEx();
		
		return "@OK@";
	}

	private void updateInvoiceDetails(int C_Tax_ID,int country_ID, int ds_TaxFiling_Det_ID,int parent_Tax_ID, boolean isIndirectBaseAmt) 
	{
		String sqlTaxDetail = " SELECT  "
				+ "currencybase(line.TaxAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS TaxAmtBHD," + 
				" currencybase(line.LineNetAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS LineNetAmtInBHD,"
				+ "currencybase(line.LineTotalAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS LineTotalAmtInBHD, "
				+ "line.TaxAmt,line.LineNetAmt,line.LineTotalAmt,tax.name,tax.rate,tax.C_Tax_ID,"
				+ "cloc.C_Country_ID,ci.C_BPartner_ID,ci.C_Invoice_ID,line.C_InvoiceLine_ID,ci.DateAcct,"
				+ "cloc.C_City_ID,loc.C_BPartner_Location_ID,tax.Rate,ci.C_Currency_ID "
				+ "FROM c_invoice_v ci,C_InvoiceLine_v line , C_BPartner_Location loc ,C_Location cloc ,C_Tax tax "
				+ "WHERE ci.C_Invoice_ID = line.C_Invoice_ID "
				+ "AND ci.C_Invoice_ID=line.C_Invoice_ID "
				+ "AND ci.C_BPartner_Location_ID = loc.C_BPartner_Location_ID "
				+ "AND loc.C_Location_ID = cloc.C_Location_Id "
				+ "AND cloc.C_Country_ID = ? "
				+ "AND line.C_Tax_ID=tax.C_Tax_ID "
				+ "AND ci.DocStatus IN ('CO','RE','CL') "
				+ "AND ci.DateAcct BETWEEN ? AND ? "
				+ "AND line.C_Tax_ID in(?, ?) "
				+ "order by ci.DateAcct ";
		PreparedStatement taxDetailpstmt = null;
		ResultSet rstd = null;
		try 
		{
			
			taxDetailpstmt = DB.prepareStatement(sqlTaxDetail, get_TrxName());
			
			taxDetailpstmt.setInt(1, country_ID);
			taxDetailpstmt.setTimestamp(2, p_DateAcct_From);
			taxDetailpstmt.setTimestamp(3, p_DateAcct_To);
			taxDetailpstmt.setInt(4, C_Tax_ID);
			taxDetailpstmt.setInt(5, parent_Tax_ID);
			rstd = taxDetailpstmt.executeQuery();
			while(rstd.next())
			{
				BigDecimal taxAmtInBHD = rstd.getBigDecimal("TaxAmtBHD");
				BigDecimal lineNetBHD = rstd.getBigDecimal("LineNetAmtInBHD");
				BigDecimal lineTotalBHD = rstd.getBigDecimal("LineTotalAmtInBHD");
				
				BigDecimal taxAmt = rstd.getBigDecimal("TaxAmt");
				BigDecimal LineNetAmt = rstd.getBigDecimal("LineNetAmt");
				BigDecimal LineTotalAmt = rstd.getBigDecimal("LineTotalAmt");
				BigDecimal rate = rstd.getBigDecimal("Rate");
				
				if(taxAmt.compareTo(Env.ZERO)==0 && parent_Tax_ID>0)
					taxAmt = LineNetAmt.multiply(rate).divide(Env.ONEHUNDRED, 3);
				
				if(taxAmtInBHD.compareTo(Env.ZERO)==0 && parent_Tax_ID>0)
					taxAmtInBHD = lineNetBHD.multiply(rate).divide(Env.ONEHUNDRED, 3);
				
				int C_BPartner_ID = rstd.getInt("C_BPartner_ID");
				int C_Invoice_ID = rstd.getInt("C_Invoice_ID");
				int C_InvoiceLine_ID = rstd.getInt("C_InvoiceLine_ID");
				Timestamp dateAcct = rstd.getTimestamp("DateAcct");
				
				int C_City_ID = rstd.getInt("C_City_ID");
				int C_Country_ID = rstd.getInt("C_Country_ID");
				int C_BPartner_Location_ID = rstd.getInt("C_BPartner_Location_ID");
				int C_Currency_ID = rstd.getInt("C_Currency_ID");
				
				int DS_TaxInvoiceLine_ID = DB.getSQLValue(get_TrxName(), "select impInv.C_InvoiceLine_ID "
						+ "from DS_ImportInvoices impInv "
						+ "where impInv.Ref_Invoice_ID=?",C_Invoice_ID);
				if(DS_TaxInvoiceLine_ID<=0)
					DS_TaxInvoiceLine_ID = getTaxInvoice(C_InvoiceLine_ID);
				
				createInvoiceDetails(C_BPartner_ID, C_BPartner_Location_ID, C_Invoice_ID, C_InvoiceLine_ID, 
						C_Tax_ID, C_Country_ID, dateAcct,taxAmt,  LineNetAmt, LineTotalAmt, taxAmtInBHD,lineNetBHD,lineTotalBHD,
						C_City_ID,ds_TaxFiling_Det_ID,C_Invoice_ID,rate,C_Currency_ID,DS_TaxInvoiceLine_ID,Env.ZERO,isIndirectBaseAmt);
			}
		} 
		catch (SQLException e) 
		{
			log.severe(e.getMessage());
		}
		
	}

	private int getTaxInvoice(int C_InvoiceLine_ID) 
	{
		String sql = "select land.C_InvoiceLine_ID From C_LandedCost land,M_CostElement cost " + 
				"where land.M_CostElement_ID=cost.M_CostElement_ID and cost.DS_IsVAT='Y' "+ 
				"and (land.M_InOutLine_ID in (select l.M_InOutLine_ID from C_InvoiceLine l where l.C_InvoiceLine_ID = ?) or "+ 
				"land.M_InOut_ID IN (SELECT ml.M_InOut_ID from C_InvoiceLine l,M_InOutLine ml where l.M_InOutLine_Id=ml.M_InOutLine_ID " + 
				"and l.C_InvoiceLine_ID = ? )) ";

		int DS_TaxInvoiceLine_ID = DB.getSQLValue(get_TrxName(), sql,C_InvoiceLine_ID,C_InvoiceLine_ID);
	
		return DS_TaxInvoiceLine_ID;
	}

	private int getLine() 
	{
		int line = DB.getSQLValue(get_TrxName(), "Select coalesce(max(line),0)+10 from DS_TaxFiling_Det Where DS_TaxFiling_ID = ? ",getRecord_ID());
		return line;
	}

	private BigDecimal getBaseAmount(int C_Tax_ID,int country_ID,int DS_TaxFiling_Det_ID, BigDecimal rateFromDirectLine)
	{
		PreparedStatement baseAmtPstmt = null;
		ResultSet baseAmtrs = null;
		BigDecimal totalBaseAmt = Env.ZERO;
		String sql = "select line.C_InvoiceLine_ID,line.C_Invoice_ID,cl.M_InOut_ID,cl.M_InOutLine_ID ,impInv.Ref_Invoice_ID " + 
				"from C_InvoiceLine line  left outer join C_LandedCost cl on  line.C_InvoiceLine_ID=cl.c_InvoiceLine_ID " + 
				"left outer join DS_ImportInvoices impInv on line.C_InvoiceLine_ID=impInv.C_InvoiceLine_ID,"+ 
				"C_Invoice ci,C_BPartner_Location loc,C_Location cloc Where ci.C_BPartner_Location_ID=loc.C_BPartner_Location_ID "+ 
				"AND loc.C_Location_ID=cloc.C_Location_ID and cloc.C_Country_ID = ? and line.C_Invoice_ID=ci.C_Invoice_ID "+ 
				"and line.C_Tax_ID=? and ci.DateAcct between ? and ? " ; 
		try 
		{
			baseAmtPstmt = DB.prepareStatement(sql, get_TrxName());
			baseAmtPstmt.setInt(1, country_ID);
			baseAmtPstmt.setInt(2, C_Tax_ID);
			baseAmtPstmt.setTimestamp(3, p_DateAcct_From);
			baseAmtPstmt.setTimestamp(4, p_DateAcct_To);
			baseAmtrs = baseAmtPstmt.executeQuery();
			while(baseAmtrs.next())
			{
				int C_Invoice_ID = baseAmtrs.getInt("C_Invoice_ID");
				MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
				int taxInvoiceLine_ID = baseAmtrs.getInt("C_InvoiceLine_ID");
				
				int M_InOut_ID = baseAmtrs.getInt("M_InOut_ID");
				int M_InOutLine_ID = baseAmtrs.getInt("M_InOutLine_ID");
				int import_Invoice_ID = baseAmtrs.getInt("Ref_Invoice_ID");
				BigDecimal importAmt = Env.ZERO;
				if(import_Invoice_ID>0)
				{
					MInvoice impInv = new MInvoice(getCtx(), import_Invoice_ID, get_TrxName());
					importAmt = MConversionRate.convertBase(getCtx(), impInv.getGrandTotal(), impInv.getC_Currency_ID(), invoice.getDateAcct(), invoice.getC_ConversionType_ID(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
					totalBaseAmt = totalBaseAmt.add(importAmt);
					MInvoiceLine[] lines = impInv.getLines();
					for(MInvoiceLine l : lines)
					{
						BigDecimal LineNetAmtBHD= MConversionRate.convertBase(getCtx(), l.getLineNetAmt(), impInv.getC_Currency_ID(), impInv.getDateAcct(), impInv.getC_ConversionType_ID(), impInv.getAD_Client_ID(), impInv.getAD_Org_ID());
						BigDecimal LineTotalAmtBHD= MConversionRate.convertBase(getCtx(), l.getLineTotalAmt(), impInv.getC_Currency_ID(), impInv.getDateAcct(), impInv.getC_ConversionType_ID(), impInv.getAD_Client_ID(), impInv.getAD_Org_ID());
						BigDecimal taxAmtInBHD= MConversionRate.convertBase(getCtx(),  l.getTaxAmt(), impInv.getC_Currency_ID(), impInv.getDateAcct(), impInv.getC_ConversionType_ID(), impInv.getAD_Client_ID(), impInv.getAD_Org_ID());

						createInvoiceDetails(impInv.getC_BPartner_ID(), impInv.getC_BPartner_Location_ID(), impInv.getC_Invoice_ID(),
								l.getC_InvoiceLine_ID(), l.getC_Tax_ID(), impInv.getC_BPartner_Location().getC_Location().getC_Country_ID(), impInv.getDateAcct(), 
								l.getTaxAmt(), l.getLineNetAmt(), l.getLineTotalAmt(),taxAmtInBHD,LineNetAmtBHD,LineTotalAmtBHD, 
								impInv.getC_BPartner_Location().getC_Location().getC_City_ID(),DS_TaxFiling_Det_ID,C_Invoice_ID,l.getC_Tax().getRate(),impInv.getC_Currency_ID(),
								taxInvoiceLine_ID,rateFromDirectLine,true);
					}
				}
				else
				{
					if(M_InOutLine_ID>0)
					{
						importAmt = getImportAmountFromReceiptLine(M_InOutLine_ID,invoice,DS_TaxFiling_Det_ID,taxInvoiceLine_ID,rateFromDirectLine);
						totalBaseAmt = totalBaseAmt.add(importAmt);
					}
					else
					{
						MInOut inout = new MInOut(getCtx(), M_InOut_ID, get_TrxName());
						MInOutLine[] lines = inout.getLines();
						for(MInOutLine line : lines)
						{
							importAmt = getImportAmountFromReceiptLine(line.getM_InOutLine_ID(),invoice,DS_TaxFiling_Det_ID,taxInvoiceLine_ID,rateFromDirectLine);
							totalBaseAmt = totalBaseAmt.add(importAmt);
						}
					}
				}
			}
		}
		catch (SQLException e) 
		{
			log.severe(e.getMessage());
		}
		finally
		{
			DB.close(baseAmtrs,baseAmtPstmt);
		}
		
		return totalBaseAmt;
	}

	private BigDecimal getImportAmountFromReceiptLine(int m_InOutLine_ID, MInvoice invoice,int DS_TaxFiling_Det_ID, int taxInvoiceLine_ID, BigDecimal rateFromDirectLine)
	{
		int C_InvoiceLine_ID = DB.getSQLValue(get_TrxName(), "Select l.C_InvoiceLine_ID From C_InvoiceLine l,C_Invoice ci "
				+ "Where l.C_Invoice_ID=ci.C_Invoice_ID and l.M_InOutLine_ID = ? AND ci.DocStatus IN ('CO','RE','CL') " ,m_InOutLine_ID);
//		
		MInvoiceLine l = new MInvoiceLine(getCtx(), C_InvoiceLine_ID, get_TrxName());
		MInvoice impInv = new MInvoice(getCtx(), l.getC_Invoice_ID(), get_TrxName());
		BigDecimal lineNetAmtInBHD = MConversionRate.convertBase(getCtx(), l.getLineNetAmt(), l.getC_Invoice().getC_Currency_ID(), invoice.getDateAcct(), l.getC_Invoice().getC_ConversionType_ID(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
		BigDecimal lineTotalAmtInBHD = MConversionRate.convertBase(getCtx(), l.getLineTotalAmt(), l.getC_Invoice().getC_Currency_ID(), invoice.getDateAcct(), l.getC_Invoice().getC_ConversionType_ID(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
		BigDecimal taxAmtInBHD = MConversionRate.convertBase(getCtx(), l.getTaxAmt(), l.getC_Invoice().getC_Currency_ID(), invoice.getDateAcct(), l.getC_Invoice().getC_ConversionType_ID(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID());

		createInvoiceDetails(impInv.getC_BPartner_ID(), impInv.getC_BPartner_Location_ID(), impInv.getC_Invoice_ID(),
				l.getC_InvoiceLine_ID(),  l.getC_Tax_ID(), impInv.getC_BPartner_Location().getC_Location().getC_Country_ID(), impInv.getDateAcct(), 
				l.getTaxAmt(),l.getLineNetAmt(), l.getLineTotalAmt(),taxAmtInBHD,lineNetAmtInBHD,lineTotalAmtInBHD,
				impInv.getC_BPartner_Location().getC_Location().getC_City_ID(),DS_TaxFiling_Det_ID,invoice.getC_Invoice_ID(),l.getC_Tax().getRate(),impInv.getC_Currency_ID(),
				taxInvoiceLine_ID,rateFromDirectLine,true);	
		
		return lineNetAmtInBHD;
	}
	
	private void createInvoiceDetails(int C_BPartner_ID,int C_BPartner_Location_ID,int C_Invoice_ID,
			int C_InvoiceLine_ID,int C_Tax_ID,int country_ID,
			Timestamp dateAcct,BigDecimal taxAmt,BigDecimal LineNetAmt,BigDecimal LineTotalAmt,BigDecimal taxAmtBHD,BigDecimal LineNetAmtBHD,
			BigDecimal LineTotalAmtBHD,int C_City_ID,int DS_TaxFiling_Det_ID,int tax_Invoice_ID, BigDecimal rate,
			int C_Currency_ID,int TaxInvoiceLine_ID, BigDecimal rateFromDirectLine, boolean isIndirectBaseAmt) 
	{
		BigDecimal calculatedTaxAmt = Env.ZERO;
		MInvoiceLine taxLine = new MInvoiceLine(getCtx(), TaxInvoiceLine_ID, get_TrxName());
		if(isIndirectBaseAmt)
		{
			calculatedTaxAmt = LineNetAmtBHD.multiply(rateFromDirectLine).divide(Env.ONEHUNDRED, 3, RoundingMode.CEILING);
			taxAmt = taxLine.getTaxAmt();
		}
				
		MDSInvoiceTaxDetails taxDet = new MDSInvoiceTaxDetails(getCtx(), 0, get_TrxName());
		taxDet.setC_BPartner_ID(C_BPartner_ID);
		taxDet.setC_BPartner_Location_ID(C_BPartner_Location_ID);
		taxDet.setC_Invoice_ID(C_Invoice_ID);
		taxDet.setC_InvoiceLine_ID(C_InvoiceLine_ID);
		taxDet.setDS_TaxFiling_ID(getRecord_ID());
		taxDet.setTaxAmt(taxAmt);
		taxDet.setC_Tax_ID(C_Tax_ID);
		taxDet.setC_Country_ID(country_ID);
		taxDet.setDateAcct(dateAcct);
		taxDet.setLineNetAmt(LineNetAmt);
		taxDet.setLineTotalAmt(LineTotalAmt);
		taxDet.setC_City_ID(C_City_ID);
		taxDet.setDS_TaxInvoice_ID(tax_Invoice_ID);
		taxDet.setDS_TaxFiling_Det_ID(DS_TaxFiling_Det_ID);
		taxDet.setDS_TaxFiling_ID(getRecord_ID());
		taxDet.setTaxBaseAmt(taxAmtBHD);
		taxDet.setlinenetamtbase(LineNetAmtBHD);
		taxDet.setlinetotalamtbase(LineTotalAmtBHD);
		taxDet.setC_Currency_ID(C_Currency_ID);
		taxDet.set_ValueNoCheck("Rate", rate);
		taxDet.setDS_TaxInvoiceLine_ID(TaxInvoiceLine_ID);
		taxDet.set_ValueNoCheck("DS_CalculatedTaxAmt", calculatedTaxAmt);
		taxDet.saveEx();
	}
}
