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
import org.gaurav.dsi.model.MDSImportInvoicesFiling;
import org.gaurav.dsi.model.MDSInvoiceTaxDetails;
import org.gaurav.dsi.model.MDSTaxFiling;
import org.gaurav.dsi.model.MDSTaxFilingDet;

public class TaxFiling extends SvrProcess{

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
					+ "tax.name,tax.rate,tax.C_Tax_ID "
					+ "FROM c_invoice ci ,C_InvoiceLine line,C_Tax tax "
					+ "WHERE ci.C_Invoice_ID=line.C_Invoice_ID "
					+ "AND line.C_Tax_ID=tax.C_Tax_ID "
					+ "AND ci.DateAcct BETWEEN ? AND ? "
					+ "AND ci.DocStatus IN ('CO','RE','CL') "
					+ "AND tax.C_Tax_ID = ? "
					+ "order by ci.DateAcct ) "
					+ "select Name,Rate,sum(TaxAmtBHD) as TaxBHD,sum(TaxBaseAmtBHD) as BaseAmtBHD,C_Tax_ID "  
					+ "from t1 "
					+ "group by Name,Rate,C_Tax_ID  ";
			
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
					BigDecimal baseAmt = Env.ZERO;
					
					MDSTaxFilingDet details = new MDSTaxFilingDet(getCtx(), 0, get_TrxName());
					details.setDS_TaxFiling_ID(getRecord_ID());
					details.setC_Tax_ID(C_Tax_ID);
					details.setTaxAmt(taxAmt);
					details.setTaxBaseAmt(baseAmt);
					details.setDescription(name);
					details.setLine(getLine());
					details.set_ValueNoCheck("Rate", rate);
					details.saveEx();
					
					updateInvoiceDetails(C_Tax_ID,details.getDS_TaxFiling_Det_ID(),tax.getParent_Tax_ID(),isIndirectBaseAmt);
				}
			} 
			catch (SQLException e) 
			{
				log.severe(e.getMessage());
			}
		}
		
		updateUnAllocatedInvoiceDetails();
		
		filing.setSOPOType(p_Type);
		filing.setDateTo(p_DateAcct_To);
		filing.setDateFrom(p_DateAcct_From);
		filing.saveEx();
		
		return "@OK@";
	}

	private void updateUnAllocatedInvoiceDetails() 
	{
		List<MDSInvoiceTaxDetails> unAllocatedLines = new Query(getCtx(), MDSInvoiceTaxDetails.Table_Name, " IsAllocated='N' and DS_TaxFiling_ID=? ", get_TrxName()).setParameters(getRecord_ID()).list();
		for(MDSInvoiceTaxDetails det:unAllocatedLines)
		{
				int DS_Invoice_TaxDetails_ID = DB.getSQLValue(get_TrxName(), "Select det.DS_Invoice_TaxDetails_ID From DS_ImportInvoices_Filing fil,DS_Invoice_TaxDetails det "
					+ "where fil.DS_Invoice_TaxDetails_ID = det.DS_Invoice_TaxDetails_ID and fil.C_InvoiceLine_ID = ? and det.DS_TaxFiling_ID=? ",det.getC_InvoiceLine_ID(),getRecord_ID());
			if(DS_Invoice_TaxDetails_ID>0)
			{
				MDSInvoiceTaxDetails details = new MDSInvoiceTaxDetails(getCtx(), DS_Invoice_TaxDetails_ID, get_TrxName());
				det.set_ValueNoCheck("IsAllocated", true);
				det.set_ValueNoCheck("DS_TaxInvoiceLine_ID", details.getC_InvoiceLine_ID());
				det.saveEx();
			}
		}
		
	}

	private void updateInvoiceDetails(int C_Tax_ID, int ds_TaxFiling_Det_ID,int parent_Tax_ID, boolean isIndirectBaseAmt) 
	{
		String sqlTaxDetail = " SELECT  "
				+ "currencybase(line.TaxAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS TaxAmtBHD," + 
				" currencybase(line.LineNetAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS LineNetAmtInBHD,"
				+ "currencybase(line.LineTotalAmt, ci.c_currency_id, ci.dateacct::timestamp with time zone, ci.ad_client_id, ci.ad_org_id) AS LineTotalAmtInBHD, "
				+ "line.TaxAmt,line.LineNetAmt,line.LineTotalAmt,tax.name,tax.rate,tax.C_Tax_ID,"
				+ "ci.C_BPartner_ID,ci.C_Invoice_ID,line.C_InvoiceLine_ID,ci.DateAcct,ci.C_BPartner_Location_ID,tax.Rate,ci.C_Currency_ID,tax.DS_ImportZeroVAT "
				+ "FROM c_invoice ci,C_InvoiceLine line ,C_Tax tax "
				+ "WHERE ci.C_Invoice_ID = line.C_Invoice_ID "
				+ "AND ci.C_Invoice_ID=line.C_Invoice_ID "
				+ "AND line.C_Tax_ID=tax.C_Tax_ID "
				+ "AND ci.DocStatus IN ('CO','RE','CL')"
				+ "AND ci.DateAcct BETWEEN ? AND ? "
				+ "AND line.C_Tax_ID in(?, ?) "
				+ "order by ci.DateAcct ";
		PreparedStatement taxDetailpstmt = null;
		ResultSet rstd = null;
		try 
		{
			taxDetailpstmt = DB.prepareStatement(sqlTaxDetail, get_TrxName());
			taxDetailpstmt.setTimestamp(1, p_DateAcct_From);
			taxDetailpstmt.setTimestamp(2, p_DateAcct_To);
			taxDetailpstmt.setInt(3, C_Tax_ID);
			taxDetailpstmt.setInt(4, parent_Tax_ID);
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
				String isZeroImportVAT = rstd.getString("DS_ImportZeroVAT");
				if(taxAmt.compareTo(Env.ZERO)==0 && parent_Tax_ID>0)
					taxAmt = LineNetAmt.multiply(rate).divide(Env.ONEHUNDRED, 3,RoundingMode.CEILING);
				
				if(taxAmtInBHD.compareTo(Env.ZERO)==0 && parent_Tax_ID>0)
					taxAmtInBHD = lineNetBHD.multiply(rate).divide(Env.ONEHUNDRED, 3,RoundingMode.CEILING);
				
				int C_BPartner_ID = rstd.getInt("C_BPartner_ID");
				int C_Invoice_ID = rstd.getInt("C_Invoice_ID");
				int C_InvoiceLine_ID = rstd.getInt("C_InvoiceLine_ID");
				Timestamp dateAcct = rstd.getTimestamp("DateAcct");
				
				int C_BPartner_Location_ID = rstd.getInt("C_BPartner_Location_ID");
				int C_Currency_ID = rstd.getInt("C_Currency_ID");
				
				createInvoiceDetails(C_BPartner_ID, C_BPartner_Location_ID, C_Invoice_ID, C_InvoiceLine_ID,C_Tax_ID, dateAcct,taxAmt,LineNetAmt,
										LineTotalAmt, taxAmtInBHD,lineNetBHD,lineTotalBHD,ds_TaxFiling_Det_ID,C_Invoice_ID,rate,C_Currency_ID,isZeroImportVAT);
			}
		} 
		catch (SQLException e) 
		{
			log.severe(e.getMessage());
		}
		
	}

	private int getLine() 
	{
		int line = DB.getSQLValue(get_TrxName(), "Select coalesce(max(line),0)+10 from DS_TaxFiling_Det Where DS_TaxFiling_ID = ? ",getRecord_ID());
		return line;
	}
	
	private void createInvoiceDetails(int C_BPartner_ID,int C_BPartner_Location_ID,int C_Invoice_ID,
			int C_InvoiceLine_ID,int C_Tax_ID,Timestamp dateAcct,BigDecimal taxAmt,BigDecimal LineNetAmt,BigDecimal LineTotalAmt,BigDecimal taxAmtBHD,BigDecimal LineNetAmtBHD,
			BigDecimal LineTotalAmtBHD,int DS_TaxFiling_Det_ID,int tax_Invoice_ID, BigDecimal rate,int C_Currency_ID, String isZeroImportVAT) 
	{					
		boolean isAllocated = isZeroImportVAT.equalsIgnoreCase("N") ? true:false;
		MDSInvoiceTaxDetails taxDet = new MDSInvoiceTaxDetails(getCtx(), 0, get_TrxName());
		taxDet.setC_BPartner_ID(C_BPartner_ID);
		taxDet.setC_BPartner_Location_ID(C_BPartner_Location_ID);
		taxDet.setC_Invoice_ID(C_Invoice_ID);
		taxDet.setC_InvoiceLine_ID(C_InvoiceLine_ID);
		taxDet.setDS_TaxFiling_ID(getRecord_ID());
		taxDet.setTaxAmt(taxAmt);
		taxDet.setC_Tax_ID(C_Tax_ID);
		taxDet.setDateAcct(dateAcct);
		taxDet.setLineNetAmt(LineNetAmt);
		taxDet.setLineTotalAmt(LineTotalAmt);
		taxDet.setDS_TaxInvoice_ID(tax_Invoice_ID);
		taxDet.setDS_TaxFiling_Det_ID(DS_TaxFiling_Det_ID);
		taxDet.setDS_TaxFiling_ID(getRecord_ID());
		taxDet.setTaxBaseAmt(taxAmtBHD);
		taxDet.setlinenetamtbase(LineNetAmtBHD);
		taxDet.setlinetotalamtbase(LineTotalAmtBHD);
		taxDet.setC_Currency_ID(C_Currency_ID);
		taxDet.set_ValueNoCheck("Rate", rate);
		taxDet.set_ValueNoCheck("IsAllocated", isAllocated);
		if(isAllocated)
			taxDet.set_ValueNoCheck("DS_TaxInvoiceLine_ID", C_InvoiceLine_ID);
		taxDet.saveEx();
		
		updateImportTaxInvoiceDetails(taxDet);
		
	}

	private BigDecimal updateImportTaxInvoiceDetails(MDSInvoiceTaxDetails taxDet) 
	{
		
		PreparedStatement baseAmtPstmt = null;
		ResultSet baseAmtrs = null;
		BigDecimal totalBaseAmt = Env.ZERO;
		String sql = "with t1 as (select line.C_InvoiceLine_ID,line.C_Invoice_ID,cl.M_InOut_ID,cl.M_InOutLine_ID ,"
				+ "impInv.Ref_Invoice_ID,case when impInv.Ref_Invoice_ID>0 then 'Y' else elem.DS_IsVAT end DS_IsVAT,"
				+ "impInv.Ref_InvoiceLine_ID " + 
				"from C_InvoiceLine line  " + 
				"left outer join C_LandedCost cl on  line.C_InvoiceLine_ID=cl.c_InvoiceLine_ID " + 
				"left outer join DS_ImportInvoices impInv on line.C_InvoiceLine_ID=impInv.C_InvoiceLine_ID "+ 
				"left outer join M_CostElement elem on cl.M_CostElement_ID=elem.M_CostElement_ID " +
				"Where line.C_InvoiceLine_ID=? ) select * from t1 where DS_IsVAT='Y' "; 
		try 
		{
			baseAmtPstmt = DB.prepareStatement(sql, get_TrxName());
			baseAmtPstmt.setInt(1, taxDet.getC_InvoiceLine_ID());
			baseAmtrs = baseAmtPstmt.executeQuery();
			while(baseAmtrs.next())
			{
				int C_Invoice_ID = baseAmtrs.getInt("C_Invoice_ID");
				MInvoice invoice = new MInvoice(getCtx(), C_Invoice_ID, get_TrxName());
				
				int M_InOut_ID = baseAmtrs.getInt("M_InOut_ID");
				int M_InOutLine_ID = baseAmtrs.getInt("M_InOutLine_ID");
				int import_Invoice_ID = baseAmtrs.getInt("Ref_Invoice_ID");
			
				BigDecimal importAmt = Env.ZERO;
				if(import_Invoice_ID>0)
				{
					MInvoice impInv = new MInvoice(getCtx(), import_Invoice_ID, get_TrxName());
					int Ref_InvoiceLine_ID = baseAmtrs.getInt("Ref_InvoiceLine_ID");
					if(Ref_InvoiceLine_ID<=0)
					{
						importAmt = MConversionRate.convertBase(getCtx(), impInv.getGrandTotal(), impInv.getC_Currency_ID(), invoice.getDateAcct(), invoice.getC_ConversionType_ID(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
						totalBaseAmt = totalBaseAmt.add(importAmt);
						MInvoiceLine[] lines = impInv.getLines();
						for(MInvoiceLine l : lines)
						{
							updateImportDetails(l,impInv,taxDet.getDS_Invoice_TaxDetails_ID(),taxDet.getRate());
						}
					}
					else
					{
						MInvoiceLine importInvoiceLine = new MInvoiceLine(getCtx(), Ref_InvoiceLine_ID ,get_TrxName());
						importAmt = MConversionRate.convertBase(getCtx(), importInvoiceLine.getLineNetAmt(), impInv.getC_Currency_ID(), invoice.getDateAcct(), invoice.getC_ConversionType_ID(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID());
						totalBaseAmt = totalBaseAmt.add(importAmt);
						updateImportDetails(importInvoiceLine,impInv,taxDet.getDS_Invoice_TaxDetails_ID(),taxDet.getRate());
					}
				}
				else
				{
					if(M_InOutLine_ID>0)
					{
						importAmt = getImportAmountFromReceiptLine(M_InOutLine_ID,invoice,taxDet.getDS_Invoice_TaxDetails_ID(),taxDet.getRate());
						totalBaseAmt = totalBaseAmt.add(importAmt);
					}
					else
					{
						MInOut inout = new MInOut(getCtx(), M_InOut_ID, get_TrxName());
						MInOutLine[] lines = inout.getLines();
						for(MInOutLine line : lines)
						{
							importAmt = getImportAmountFromReceiptLine(line.getM_InOutLine_ID(),invoice,taxDet.getDS_Invoice_TaxDetails_ID(),taxDet.getRate());
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
	
	private BigDecimal getImportAmountFromReceiptLine(int m_InOutLine_ID, MInvoice invoice,int DS_TaxFiling_Det_ID, BigDecimal rateFromDirectLine)
	{
		int C_InvoiceLine_ID = DB.getSQLValue(get_TrxName(), "Select l.C_InvoiceLine_ID From C_InvoiceLine l,C_Invoice ci "
				+ "Where l.C_Invoice_ID=ci.C_Invoice_ID and l.M_InOutLine_ID = ? AND ci.DocStatus IN ('CO','RE','CL') " ,m_InOutLine_ID);
//		
		MInvoiceLine l = new MInvoiceLine(getCtx(), C_InvoiceLine_ID, get_TrxName());
		MInvoice impInv = new MInvoice(getCtx(), l.getC_Invoice_ID(), get_TrxName());
		BigDecimal lineNetAmtInBHD = MConversionRate.convertBase(getCtx(), l.getLineNetAmt(), l.getC_Invoice().getC_Currency_ID(), invoice.getDateAcct(), l.getC_Invoice().getC_ConversionType_ID(), invoice.getAD_Client_ID(), invoice.getAD_Org_ID());

		updateImportDetails(l,impInv,DS_TaxFiling_Det_ID,rateFromDirectLine);	
		
		return lineNetAmtInBHD;
	}

	public void updateImportDetails(MInvoiceLine taxline,MInvoice inv,int DS_Invoice_TaxDetails_ID,BigDecimal rate)
	{
		MDSImportInvoicesFiling impInv = new MDSImportInvoicesFiling(getCtx(), 0, get_TrxName());
		BigDecimal lineNetBaseAmt = MConversionRate.convertBase(getCtx(), taxline.getLineNetAmt(), inv.getC_Currency_ID(), inv.getDateAcct(), inv.getC_ConversionType_ID(), inv.getAD_Client_ID(), inv.getAD_Org_ID());	
		BigDecimal lineTotalBaseAmt = MConversionRate.convertBase(getCtx(), taxline.getLineTotalAmt(), inv.getC_Currency_ID(), inv.getDateAcct(), inv.getC_ConversionType_ID(), inv.getAD_Client_ID(), inv.getAD_Org_ID());
		BigDecimal taxAmt = taxline.getLineNetAmt().multiply(rate).divide(Env.ONEHUNDRED, 3,RoundingMode.CEILING);
		BigDecimal taxBaseAmt = MConversionRate.convertBase(getCtx(), taxAmt, inv.getC_Currency_ID(), inv.getDateAcct(), inv.getC_ConversionType_ID(), inv.getAD_Client_ID(), inv.getAD_Org_ID());
		impInv.setC_BPartner_ID(inv.getC_BPartner_ID());
		impInv.setC_BPartner_Location_ID(inv.getC_BPartner_Location_ID());
		impInv.setC_Tax_ID(taxline.getC_Tax_ID());
		impInv.setDateAcct(inv.getDateAcct());
		impInv.setC_Currency_ID(inv.getC_Currency_ID());
		impInv.setC_Invoice_ID(inv.getC_Invoice_ID());
		impInv.setC_InvoiceLine_ID(taxline.getC_InvoiceLine_ID());
		impInv.setDS_Invoice_TaxDetails_ID(DS_Invoice_TaxDetails_ID);
		impInv.setLineNetAmt(taxline.getLineNetAmt());
		impInv.setLineTotalAmt(taxline.getLineTotalAmt());
		impInv.setTaxAmt(taxAmt);
		impInv.setlinenetamtbase(lineNetBaseAmt);
		impInv.setlinetotalamtbase(lineTotalBaseAmt);
		impInv.setTaxBaseAmt(taxBaseAmt);
		impInv.saveEx();
		
	}
}
