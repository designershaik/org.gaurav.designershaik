package com.gaurav.project.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.project.process.ProjectPhaseTaskIssue;

public class DSIProjectProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(ProjectPhaseTaskIssue.class.getName().equalsIgnoreCase(className))
			return new ProjectPhaseTaskIssue();
		return null;
	}


}
