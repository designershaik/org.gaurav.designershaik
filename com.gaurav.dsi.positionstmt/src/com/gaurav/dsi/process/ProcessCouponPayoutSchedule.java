package com.gaurav.dsi.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
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

public class ProcessCouponPayoutSchedule extends SvrProcess{
	
	int C_InvoiceLine_ID = 0;
	MInvoiceLine line = null ;
	BigDecimal p_amortization = Env.ZERO;
	BigDecimal p_NominalValue = Env.ZERO;
	private boolean 	p_ReCreate = false;
	private Timestamp p_firstPayoutDate = null;
	@Override
	protected void prepare() {
	
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DS_Amortization"))
				p_amortization = para[i].getParameterAsBigDecimal();
			else if (name.equals("DS_FirstPayOutDate"))
				p_firstPayoutDate = para[i].getParameterAsTimestamp();
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
			Timestamp dateAcct = invoice.getDateAcct();
			MProduct investment = new MProduct(getCtx(), M_Product_ID, get_TrxName());
			Integer payOutPeriod = (Integer)investment.get_ValueAsInt("DS_PayOutPeriod");
			BigDecimal couponRate = (BigDecimal)investment.get_Value("DS_CouponRate");
			int M_AttributeSetInstance_ID = line.getM_AttributeSetInstance_ID();
			MAttributeSetInstance msi = new MAttributeSetInstance(getCtx(), M_AttributeSetInstance_ID, get_TrxName());
			if(msi.getGuaranteeDate()!=null)
			{
				int daysBetween = TimeUtil.getDaysBetween(dateAcct,msi.getGuaranteeDate());
				int totalPayouts = (new BigDecimal(daysBetween).divide(new BigDecimal(payOutPeriod),0,RoundingMode.HALF_UP)).intValue();
				BigDecimal annualReturn = p_amortization.add(p_NominalValue).multiply(couponRate).divide(Env.ONEHUNDRED, invoice.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
				BigDecimal payoutPeriodBasedCoupon = annualReturn.divide(new BigDecimal(365/payOutPeriod), invoice.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
				BigDecimal amortizationAmtOverPeriod = Env.ZERO;
				if(p_amortization.compareTo(Env.ZERO)!=0)
					amortizationAmtOverPeriod = p_amortization.divide(new BigDecimal(totalPayouts), invoice.getC_Currency().getStdPrecision(), RoundingMode.CEILING);
				for(int i=0 ; i<totalPayouts ; i++)
				{
					Timestamp couponPayOutDate = TimeUtil.addDays(p_firstPayoutDate, payOutPeriod*i);
					MDSCouponSchedule sc = new MDSCouponSchedule(getCtx(), 0, get_TrxName());
					sc.setGS_CouponAmount(payoutPeriodBasedCoupon);
					sc.setLine(i+1);
					sc.setDS_CouponRate(couponRate);
					sc.setGS_CouponDate(couponPayOutDate);
					sc.setC_Invoice_ID(invoice.getC_Invoice_ID());
					sc.setM_Product_ID(M_Product_ID);
					sc.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
					sc.setDS_AmortizationAmt_OverSchedul(amortizationAmtOverPeriod);
					sc.setC_BPartner_ID(invoice.getC_BPartner_ID());
					sc.setC_Currency_ID(invoice.getC_Currency_ID());
					sc.saveEx();
					if(i==totalPayouts-1 && couponPayOutDate.before(msi.getGuaranteeDate()))
					{
						sc = new MDSCouponSchedule(getCtx(), 0, get_TrxName());
						sc.setGS_CouponAmount(payoutPeriodBasedCoupon);
						sc.setLine(i+2);
						sc.setDS_CouponRate(couponRate);
						sc.setGS_CouponDate(msi.getGuaranteeDate());
						sc.setC_Invoice_ID(invoice.getC_Invoice_ID());
						sc.setM_Product_ID(M_Product_ID);
						sc.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
						sc.setDS_AmortizationAmt_OverSchedul(amortizationAmtOverPeriod);
						sc.setC_BPartner_ID(invoice.getC_BPartner_ID());
						sc.setC_Currency_ID(invoice.getC_Currency_ID());
						sc.saveEx();
					}
				}
				
			}
		}
		return "@Processed@";
	}
}
