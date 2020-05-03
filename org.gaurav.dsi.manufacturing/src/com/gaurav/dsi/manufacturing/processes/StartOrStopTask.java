package com.gaurav.dsi.manufacturing.processes;

import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.MDSAssignment;
import org.gaurav.dsi.model.MDSAssignmentDetails;

public class StartOrStopTask extends SvrProcess {

	int DS_Assignment_ID = 0; 
	MDSAssignment assignment = null;
	int p_Code = 0;
	
	@Override
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) 
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("Code"))
				p_Code = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		DS_Assignment_ID = getRecord_ID();
		assignment = new MDSAssignment(getCtx(), DS_Assignment_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		
		String m_msg = "";
		int DS_Assignment_Details_ID = 0;
		Date currentDate = new Date();
		Timestamp now = new Timestamp(currentDate.getTime());
		int codeForBPartner = DB.getSQLValue(get_TrxName(), "Select emp.Code from GS_HR_Employee emp where emp.C_BPartner_ID = ? ",
				assignment.getDS_AvailEmployee().getC_BPartner_ID());
		if(p_Code==codeForBPartner)
		{
			DS_Assignment_Details_ID = DB.getSQLValue(get_TrxName(), "Select DS_Assignment_Details_ID "
					+ "From DS_Assignment_Details Where DS_Assignment_ID = ? and EndTime Is NULL " ,DS_Assignment_ID);
			if(DS_Assignment_Details_ID==-1)
			{	
				DS_Assignment_Details_ID = 0 ;
				MDSAssignmentDetails details = new MDSAssignmentDetails(getCtx(), DS_Assignment_Details_ID, get_TrxName());
				details.setStartTime(now);
				details.setDS_Assignment_ID(DS_Assignment_ID);
				details.saveEx();
				m_msg = Msg.getMsg(getCtx(), "DS_TaskStarted");
				addLog(m_msg);
			}
			else
			{
				MDSAssignmentDetails details = new MDSAssignmentDetails(getCtx(), DS_Assignment_Details_ID, get_TrxName());
				details.setEndTime(now);
				details.setDS_Assignment_ID(DS_Assignment_ID);
				details.saveEx();
				m_msg = Msg.getMsg(getCtx(), "DS_TaskStopped");
				addLog(m_msg);
			}
		}
		else
			throw new AdempiereException(Msg.getMsg(getCtx(), "DS_WrongCode"));
		
		return m_msg;
	}

}
