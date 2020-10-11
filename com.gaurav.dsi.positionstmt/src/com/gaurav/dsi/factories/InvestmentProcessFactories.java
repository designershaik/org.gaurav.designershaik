package com.gaurav.dsi.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.dsi.process.DistributeAmountWithinPortfolio;
import com.gaurav.dsi.process.FundMovement;
import com.gaurav.dsi.process.GenerateCashPositions;
import com.gaurav.dsi.process.ProcessCouponPayoutSchedule;
import com.gaurav.dsi.process.ProcessCouponReceiptAndAdjustAmortization;

public class InvestmentProcessFactories implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {

		if(ProcessCouponPayoutSchedule.class.getName().equalsIgnoreCase(className))
			return new ProcessCouponPayoutSchedule();
		
		if(GenerateCashPositions.class.getName().equalsIgnoreCase(className))
			return new GenerateCashPositions();
		
		if(ProcessCouponReceiptAndAdjustAmortization.class.getName().equalsIgnoreCase(className))
			return new ProcessCouponReceiptAndAdjustAmortization();
		
		if(DistributeAmountWithinPortfolio.class.getName().equalsIgnoreCase(className))
			return new DistributeAmountWithinPortfolio();
		
		if(FundMovement.class.getName().equalsIgnoreCase(className))
			return new FundMovement();
		
		return null;
	}

}
