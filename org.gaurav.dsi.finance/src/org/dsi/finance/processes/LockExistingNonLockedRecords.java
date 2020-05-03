package org.dsi.finance.processes;

import java.util.List;
import java.util.logging.Level;

import org.compiere.model.MCharge;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;

public class LockExistingNonLockedRecords extends SvrProcess{

	int p_AD_User_ID;
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_User_ID"))
				p_AD_User_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@Override
	protected String doIt() throws Exception {
		
//		List<MCharge> charges = new Query(getCtx(), tableName, whereClause, trxName)
		return null;
	}



}
