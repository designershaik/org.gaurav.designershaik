package com.gaurav.display.process;

import java.util.List;

import org.compiere.model.MAcctSchema;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import com.gaurav.display.model.MDSMovementLine;

public class TransferDisplay extends SvrProcess {

	int M_MovementLine_ID = 0;
	MAcctSchema asc = null;
	boolean p_decision = false;
	int AD_Table_ID = 0 ;
	@Override
	protected void prepare() 
	{
		AD_Table_ID = getTable_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(MMovementLine.Table_ID==AD_Table_ID)
		{
			M_MovementLine_ID = getRecord_ID();
			MDSMovementLine line = new MDSMovementLine(getCtx(), M_MovementLine_ID, get_TrxName());
			if(line.get_ValueAsBoolean("IsGenerated"))
				return "Already generated ";
			else
				line.generateAssetAndTransferDisplay(line,get_TrxName());
		}
		if(MMovement.Table_ID==AD_Table_ID)
		{
			List<MDSMovementLine> lines = new Query(getCtx(), MDSMovementLine.Table_Name, " M_Movement_ID = ? ", get_TrxName()).setParameters(getRecord_ID()).list();
			for(MDSMovementLine line : lines)
			{
				if(line.get_ValueAsBoolean("IsGenerated"))
					addLog(line.getM_MovementLine_ID(), null, null,"Already generated: "+line.getLine() , MMovementLine.Table_ID, line.getM_MovementLine_ID());
				else
					line.generateAssetAndTransferDisplay(line,get_TrxName());
			}
		}
		
		return "@OK@";
	}
}
