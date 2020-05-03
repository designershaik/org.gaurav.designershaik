package com.gaurav.dsi.manufacturing.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSAssignment;
import org.gaurav.dsi.model.MDSAvailEmployee;

public class AssignResource extends SvrProcess {
	MDSAssignment mda;
	int projectID;
	int phaseID;
	int taskID;
	int orderID;
	int activityID;
	int bpartnerID;
	int availEmpID;
	int empID;
	MDSAvailEmployee mae;
	@Override
	protected void prepare() 
	{
		getRecord_ID();
		mda = new MDSAssignment(getCtx(), getRecord_ID(), get_TrxName());
		availEmpID = mda.getDS_AvailEmployee_ID();
		mae=new MDSAvailEmployee(getCtx(), availEmpID, get_TrxName());
		empID=mae.getC_BPartner_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		Date d = new Date();
		Timestamp now = new Timestamp(d.getTime());
		if (mda.isDS_IsAssigned() == false) {
			if (AlreadyAssigned(empID)) 
			{
				addLog("Already assigned to other work , please dismiss before assigning");

			} 
			else 
			{
				if (mda.isDS_IsService() == true) 
				{
					projectID = mda.getC_Project_ID();
					phaseID = mda.getC_ProjectPhase_ID();
					taskID = mda.getC_ProjectTask_ID();
					if (projectID == 0 || phaseID == 0 || taskID == 0) 
					{
						throw new AdempiereUserError(
								"Assign to valid service, Select proper project/phase/task");
					}
					mda.setDS_IsAssigned(true);
					mda.setAssignDateFrom(now);
					mda.save();
					mae.setDS_IsAssigned(true);
					mae.save();
					addLog("Assigned to service");
				} 
				else if (mda.isDS_IsService() == false) 
				{
					orderID = mda.getPP_Order_ID();
					activityID = mda.getPP_Order_Node_ID();
					if (orderID == 0 || activityID == 0) 
					{
						throw new AdempiereUserError(
								"Assign to valid activity and manufacturing order");
					}
					mda.setDS_IsAssigned(true);
					mda.setAssignDateFrom(now);
					mda.save();
					mae.setDS_IsAssigned(true);
					mae.save();
					addLog("Assigned to Manufacturing order");
				}
			}
		}
		else 
			if (mda.isDS_IsAssigned() == true) 
		{
			mda.setAssignDateTo(now);
			mda.setDS_IsAssigned(false);
			mda.setIsFinalClose(true);
			mda.save();
			mae.setDS_IsAssigned(false);
			mae.save();
			addLog("Removed from task");
		}

		return "@OK@";
	}

	private boolean AlreadyAssigned(int empID) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String assigned = "";
		String sql = "select da.LINENO,da.ASSIGNDATEFROM from ds_assignment da,DS_AvailEmployee avemp where "
				+ "da.DS_AvailEmployee_ID=avemp.DS_AvailEmployee_ID and da.DS_ISASSIGNED='Y' and avemp.c_bpartner_id=?";
		Boolean isAlreadyAssigned = false;
		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());
			pstmt.setInt(1, empID );
			rs = pstmt.executeQuery();
			while (rs.next()) 
			{
				assigned=rs.getString("ASSIGNDATEFROM");
				isAlreadyAssigned=true;
				addLog("This resource is already assigned from "+assigned);
			}
		} catch (SQLException e) 
		{
			log.saveError("Unknown exception occured while checking assignment", e);
		}
		finally
		{
			DB.close(rs, pstmt);
		}
		return isAlreadyAssigned;
	}

}
