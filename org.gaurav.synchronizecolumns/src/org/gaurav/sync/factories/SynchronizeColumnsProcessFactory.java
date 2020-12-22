package org.gaurav.sync.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.gaurav.sync.process.GSColumnSync;

public class SynchronizeColumnsProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		
		if(className.equalsIgnoreCase(GSColumnSync.class.getName()))
			return new GSColumnSync();
		
		return null;
	}

}
