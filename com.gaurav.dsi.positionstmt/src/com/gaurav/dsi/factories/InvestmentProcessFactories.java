package com.gaurav.dsi.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.dsi.process.GenerateFDBankBalances;
import com.gaurav.dsi.process.ProcessCouponPayoutSchedule;
import com.gaurav.dsi.process.ProcessCouponReceiptAndAdjustAmortization;

public class InvestmentProcessFactories implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {

		if(ProcessCouponPayoutSchedule.class.getName().equalsIgnoreCase(className))
			return new ProcessCouponPayoutSchedule();
		
		if(GenerateFDBankBalances.class.getName().equalsIgnoreCase(className))
			return new GenerateFDBankBalances();
		
		if(ProcessCouponReceiptAndAdjustAmortization.class.getName().equalsIgnoreCase(className))
			return new ProcessCouponReceiptAndAdjustAmortization();
		
		
		return null;
	}

}
