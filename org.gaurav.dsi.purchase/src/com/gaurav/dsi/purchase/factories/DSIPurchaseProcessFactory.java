package com.gaurav.dsi.purchase.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.dsi.purchase.processes.ApprovePurchaseRequest;
import com.gaurav.dsi.purchase.processes.CreateCashRequestFromPurchaseRequest;
import com.gaurav.dsi.purchase.processes.CreatePurchaseRequestFromFacilityRequest;
import com.gaurav.dsi.purchase.processes.CreateVendorInvoiceFromPurchaseRequest;
import com.gaurav.dsi.purchase.processes.ForwardRequest;
import com.gaurav.dsi.purchase.processes.GenerateAttributes;

public class DSIPurchaseProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equals(ForwardRequest.class.getName()))
			return new ForwardRequest();
		
		if(className.equals(GenerateAttributes.class.getName()))
			return new GenerateAttributes();
		
		if(className.equals(CreateVendorInvoiceFromPurchaseRequest.class.getName()))
			return new CreateVendorInvoiceFromPurchaseRequest();
		
		if(className.equals(ApprovePurchaseRequest.class.getName()))
			return new ApprovePurchaseRequest();
		
		if(className.equals(CreateCashRequestFromPurchaseRequest.class.getName()))
			return new CreateCashRequestFromPurchaseRequest();
		
		if(className.equals(CreatePurchaseRequestFromFacilityRequest.class.getName()))
			return new CreatePurchaseRequestFromFacilityRequest();
		
		
		
		return null;
	}
}
