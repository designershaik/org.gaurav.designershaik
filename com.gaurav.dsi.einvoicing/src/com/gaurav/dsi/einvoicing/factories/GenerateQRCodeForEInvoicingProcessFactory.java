package com.gaurav.dsi.einvoicing.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.osgi.service.component.annotations.Component;

import com.gaurav.dsi.einvoicing.process.GenerateQRCodeForEInvoicing;


@Component(

		 property= {"service.ranking:Integer=100"},
		 service = org.adempiere.base.IProcessFactory.class
		 )
public class GenerateQRCodeForEInvoicingProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equalsIgnoreCase(GenerateQRCodeForEInvoicing.class.getName()))
			return new GenerateQRCodeForEInvoicing();
		
		return null;
	}

}
