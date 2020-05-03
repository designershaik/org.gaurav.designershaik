package org.gaurav.alerts.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.gaurav.alerts.process.GenerateAlerts;

public class DocumentAlertProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(className.equalsIgnoreCase(GenerateAlerts.class.getName()))
			return new GenerateAlerts();
		
		return null;
	}


}
