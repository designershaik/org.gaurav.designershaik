package com.gaurav.dsi.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MProduct;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.gaurav.dsi.model.MDSCouponSchedule;
import org.joda.time.DateTime;

public class ProcessCouponPayoutSchedule extends SvrProcess{
	
	int C_InvoiceLine_ID = 0;
	MInvoiceLine line = null ;
	BigDecimal p_amortization = Env.ZERO;
	BigDecimal p_NominalValue = Env.ZERO;
	private boolean 	p_ReCreate = false;
	private Timestamp p_firstPayoutDate = null ;
	private Timestamp p_SecondPayOutDate = null ; 
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null);
			else if (name.equals("DS_Amortization"))
				p_amortization = para[i].getParameterAsBigDecimal();
			else if (name.equals("DS_FirstPayOutDate"))
				p_firstPayoutDate = para[i].getParameterAsTimestamp();
			else if (name.equals("DS_LastPayOutDate"))
				p_SecondPayOutDate = para[i].getParameterAsTimestamp();
			else if (name.equals("DS_NominalValue"))
				p_NominalValue = para[i].getParameterAsBigDecimal();
			else if (name.equals("Recreate"))
				p_ReCreate = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
		C_InvoiceLine_ID = getRecord_ID();
		line = new MInvoiceLine(getCtx(), C_InvoiceLine_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(p_ReCreate)
		{
			int count = DB.getSQLValue(get_TrxName(), "Select count(*) From DS_CouponSchedule Where ref_Invoice_ID is not null and C_InvoiceLine_ID = ? ",C_InvoiceLine_ID);
			if(count>0)
				throw new AdempiereException("Payout has been already processed for one of the coupon. Schedule can't be changed.");
			DB.executeUpdateEx("Delete from DS_CouponSchedule Where C_InvoiceLine_ID = ?",new Object[] {C_InvoiceLine_ID}, get_TrxName());
		}
		else
		{
			int count = DB.getSQLValue(get_TrxName(), "Select count(*) From DS_CouponSchedule Where C_InvoiceLine_ID= ?",C_InvoiceLine_ID);
			if(count>0)
				throw new AdempiereException("AlreadyExist");
		}
		int M_Product_ID = line.getM_Product_ID();
		if(M_Product_ID>0)
		{
			MInvoice invoice = new MInvoice(getCtx(), line.getC_Invoice_ID(), get_TrxName());
			boolean isSOTrx = invoice.isSOTrx();
			if(!isSOTrx)
			{
				MProduct investment = new MProduct(getCtx(), M_Product_ID, get_TrxName());
				Integer payOutPeriod = (Integer)investment.get_ValueAsInt("DS_PayOutPeriod");
				BigDecimal couponRate = (BigDecimal)investment.get_Value("DS_CouponRate");
				int M_AttributeSetInstance_ID = line.getM_AttributeSetInstance_ID();
				MAttributeSetInstance msi = new MAttributeSetInstance(getCtx(), M_AttributeSetInstance_ID, get_TrxName());
				if(msi.getGuaranteeDate()!=null)
				{
					int daysBetween = TimeUtil.getDaysBetween(p_firstPayoutDate,msi.getGuaranteeDate());
					int totalPayouts = (new BigDecimal(daysBetween).divide(new BigDecimal(payOutPeriod),0,RoundingMode.UP)).intValue();
					//BigDecimal annualReturn = p_amortization.add(p_NominalValue).multiply(couponRate).divide(Env.ONEHUNDRED, invoice.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
					BigDecimal annualReturn = p_NominalValue.multiply(couponRate).divide(Env.ONEHUNDRED, invoice.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
					System.out.println(p_NominalValue);
					System.out.println(annualReturn);
					BigDecimal totalInstallmentinyear = new BigDecimal(365/payOutPeriod);
					System.out.println(totalInstallmentinyear);
					BigDecimal payoutPeriodBasedCoupon = annualReturn.divide(totalInstallmentinyear, invoice.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
					System.out.println(payoutPeriodBasedCoupon);
					BigDecimal amortizationAmtOverPeriod = Env.ZERO;
					if(p_amortization.compareTo(Env.ZERO)!=0)
						amortizationAmtOverPeriod = p_amortization.divide(new BigDecimal(totalPayouts), invoice.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
					for(int i=0 ; i<totalPayouts ; i++)
					{
						Timestamp couponPayOutDate = TimeUtil.addDays(p_firstPayoutDate, payOutPeriod*i);
						DateTime dateTime = new DateTime(couponPayOutDate.getTime());
						int year = dateTime.getYear();
						int month = dateTime.getMonthOfYear();
						int Day = couponPayOutDate.getDay();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					    Date parsedDate = dateFormat.parse(year+"-"+month+"-"+Day);
					    Timestamp RevisedCouponPayOutDate = new java.sql.Timestamp(parsedDate.getTime());
						
						MDSCouponSchedule sc = new MDSCouponSchedule(getCtx(), 0, get_TrxName());
						sc.setGS_CouponAmount(payoutPeriodBasedCoupon);
						sc.setLine(i+1);
						sc.setDS_CouponRate(couponRate);
						sc.setGS_CouponDate(RevisedCouponPayOutDate);
						sc.setC_Invoice_ID(invoice.getC_Invoice_ID());
						sc.setM_Product_ID(M_Product_ID);
						sc.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
						sc.setDS_AmortizationAmt_OverSchedul(amortizationAmtOverPeriod);
						sc.setC_BPartner_ID(invoice.getC_BPartner_ID());
						sc.setC_Currency_ID(invoice.getC_Currency_ID());
						sc.saveEx();
//						if(i==totalPayouts-1 && couponPayOutDate.before(msi.getGuaranteeDate()))
//						{
//							sc = new MDSCouponSchedule(getCtx(), 0, get_TrxName());
//							sc.setGS_CouponAmount(payoutPeriodBasedCoupon);
//							sc.setLine(i+2);
//							sc.setDS_CouponRate(couponRate);
//							sc.setGS_CouponDate(msi.getGuaranteeDate());
//							sc.setC_Invoice_ID(invoice.getC_Invoice_ID());
//							sc.setM_Product_ID(M_Product_ID);
//							sc.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
//							sc.setDS_AmortizationAmt_OverSchedul(amortizationAmtOverPeriod);
//							sc.setC_BPartner_ID(invoice.getC_BPartner_ID());
//							sc.setC_Currency_ID(invoice.getC_Currency_ID());
//							sc.saveEx();
//						}
					}
				}
			}
//			if(!isSOTrx)
//			{
//				List<MDSCouponSchedule> schedueles = new Query(getCtx(), MDSCouponSchedule.Table_Name, " M_Product_ID = ? and M_AttributeSetInstance_ID = ? and Ref_Invoice_ID is null and GL_Journal_ID is null ", get_TrxName()).list();
//			}
		}
		return "@Processed@";
	}
}
