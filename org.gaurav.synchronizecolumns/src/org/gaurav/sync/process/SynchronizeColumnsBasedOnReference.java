package org.gaurav.sync.process;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class SynchronizeColumnsBasedOnReference extends SvrProcess{

	int p_AD_Reference_ID = 0 ;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Reference_ID"))
				p_AD_Reference_ID = para[i].getParameterAsInt();
		}
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		
		return null;
	}


}
