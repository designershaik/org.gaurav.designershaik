package com.gaurav.display.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.display.process.TransferDisplay;

public class DisplayProcessFactories implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equalsIgnoreCase(TransferDisplay.class.getName()))
			return new TransferDisplay();
		
		return null;
	}

}
