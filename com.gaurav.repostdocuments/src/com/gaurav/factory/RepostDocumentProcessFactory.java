package com.gaurav.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.doc.RepostDocuments;

public class RepostDocumentProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className)
	{
		if(className.equalsIgnoreCase(RepostDocuments.class.getName()))
			return new RepostDocuments();
		
		return null;
	}


}
