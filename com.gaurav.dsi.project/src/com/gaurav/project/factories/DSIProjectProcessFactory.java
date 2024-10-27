package com.gaurav.project.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.project.process.DSIProjectPhaseTaskIssue;

public class DSIProjectProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(DSIProjectPhaseTaskIssue.class.getName().equalsIgnoreCase(className))
			return new DSIProjectPhaseTaskIssue();
		return null;
	}


}
