package com.dsi.getperfumebatch.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.osgi.service.component.annotations.Component;

import com.dsi.getperfumebatch.processes.GetPerfumeBatch;

@Component(

		 property= {"service.ranking:Integer=100"},
		 service = org.adempiere.base.IProcessFactory.class
		 )
public class GetPerfumeProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(className.equalsIgnoreCase(GetPerfumeBatch.class.getName()))
			return new GetPerfumeBatch();
		
		return null;
	}

}
