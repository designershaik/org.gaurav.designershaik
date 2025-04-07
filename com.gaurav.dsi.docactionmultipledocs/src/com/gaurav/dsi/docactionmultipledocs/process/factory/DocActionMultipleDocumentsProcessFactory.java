package com.gaurav.dsi.docactionmultipledocs.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.osgi.service.component.annotations.Component;

import com.gaurav.dsi.docactionmultipledocs.process.DocActionMultipleDocuments;

@Component(

		 property= {"service.ranking:Integer=100"},
		 service = org.adempiere.base.IProcessFactory.class
		 )
public class DocActionMultipleDocumentsProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(className.equalsIgnoreCase(DocActionMultipleDocuments.class.getName()))
			return new DocActionMultipleDocuments();
	
		return null;
	}

}
