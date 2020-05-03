package com.gaurav.dsi.manufacturing.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSAvailEmployee;

public class GetEmployeesForDay extends SvrProcess 
{
	int dayID;
	int resourceID;
	

	@Override
	protected void prepare() 
	{
		dayID=getRecord_ID();
		ProcessInfoParameter[] paras = getParameter();
		for (ProcessInfoParameter para : paras)
		{
			String name = para.getParameterName();
			if ( "S_Resource_ID".equals(name))
				resourceID  = para.getParameterAsInt();
			else 
			{
				log.log(Level.WARNING, "Unknown parameter: " + name);
			}
		}
	}

	@Override
	protected String doIt() throws Exception
	{
		PreparedStatement pstmt;
		ResultSet rs = null;
		String sql="select bp.name,bp.c_bpartner_id from C_BPartner bp ,GS_HR_Employee hr where "
				+ "bp.c_bpartner_id=hr.c_bpartner_id and bp.isactive='Y' and hr.ad_client_id=?";
		pstmt=DB.prepareStatement(sql, get_TrxName());
		try {
			pstmt.setInt(1, getAD_Client_ID());
			rs=pstmt.executeQuery();
			int line=0;
			while(rs.next())
			{
				int bpartnerID=rs.getInt("c_bpartner_id");
				if(isExists(bpartnerID))
				{
					log.info("Day/Employee already exists");
				}
				else
				{
				line++;
				MDSAvailEmployee availEmp=new MDSAvailEmployee(getCtx(), 0, get_TrxName());
				availEmp.setDS_Days_ID(dayID);
				availEmp.setC_BPartner_ID(rs.getInt("c_bpartner_id"));
				availEmp.setLineNo(line);
				availEmp.setDS_IsAssigned(false);
				availEmp.save();
				}
			}
		} 
		catch (SQLException e) 
		{
			log.log(Level.WARNING, "Exception: " + e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs=null;
			pstmt=null;
		}

		return "Employee List generated/Updated";
	}

	private boolean isExists(int bpartnerID) 
	{
		PreparedStatement pstmt;
		ResultSet rs1;
		boolean isExists=false;
		String sql="select dsa.c_bpartner_id from DS_AvailEmployee dsa where dsa.DS_Days_ID=? and dsa.c_bpartner_id=?";
		pstmt=DB.prepareStatement(sql, get_TrxName());
		try {
			pstmt.setInt(1, dayID);
			pstmt.setInt(2, bpartnerID);
			rs1=pstmt.executeQuery();
			if(rs1.next())
			{
				isExists=true;
			}
		} 
		catch (SQLException e) 
		{
			log.log(Level.WARNING, "Exception: " + e);
		}
		return isExists;
	}

}
