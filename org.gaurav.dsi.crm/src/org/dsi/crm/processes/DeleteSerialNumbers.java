package org.dsi.crm.processes;

import java.util.List;

import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.Query;
import org.compiere.process.SvrProcess;
import org.gaurav.dsi.model.I_DSI_SerialNoTrx;
import org.gaurav.dsi.model.MDSISerialNoTrx;

public class DeleteSerialNumbers extends SvrProcess{

	int recordID ;
	int tableID ; 




	
	@Override
	protected void prepare() {
		
		recordID = getRecord_ID();
		tableID = getTable_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
//		319 - M_InOut
		if(tableID==319)
		{
			MInOut inout = new MInOut(getCtx(), recordID, get_TrxName());
			MInOutLine[] lines = inout.getLines();
			for(MInOutLine line : lines)
			{
				deleteSerialNumber(line);
			}
			
		}
//		320 - M_InOutLine
		if(tableID==320)
		{
			MInOutLine line = new MInOutLine(getCtx(), recordID, get_TrxName());
			deleteSerialNumber(line);
		}
		
//		323= M_Movement
		if(tableID==323)
		{
			MMovement movement = new MMovement(getCtx(), recordID, get_TrxName());
			MMovementLine[] lines = movement.getLines(false);
			for(MMovementLine line : lines)
			{
				deleteSerialNumbersFromMovement(line);
			}
			
		}
//		324 = M_MovementLine
		if(tableID==324)
		{
			MMovementLine line = new MMovementLine(getCtx(), recordID, get_TrxName());
			deleteSerialNumbersFromMovement(line);
		}
		addLog("Serial Numbers are Deleted");
		return "@OK@";
	}

	private void deleteSerialNumbersFromMovement(MMovementLine line) 
	{
		List<MDSISerialNoTrx> SerialNumbers = new Query(getCtx(), I_DSI_SerialNoTrx.Table_Name, "M_MovementLine_ID = ? ", get_TrxName()).
				setParameters(line.getM_MovementLine_ID()).list();
		for(MDSISerialNoTrx serialNumber : SerialNumbers)
		{
			serialNumber.delete(true);
		}
		
	}

	private void deleteSerialNumber(MInOutLine line) 
	{
		List<MDSISerialNoTrx> SerialNumbers = new Query(getCtx(), I_DSI_SerialNoTrx.Table_Name, "M_InOutLine_ID = ? ", get_TrxName()).
				setParameters(line.getM_InOutLine_ID()).list();
		for(MDSISerialNoTrx serialNumber : SerialNumbers)
		{
			serialNumber.delete(true);
		}
		
	}

	
}
