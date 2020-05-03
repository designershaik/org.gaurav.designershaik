package com.gaurav.dsi.manufacturing.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;

import org.compiere.model.MPeriod;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSAvailEmployee;
import org.gaurav.dsi.model.MDSDays;

public class GetDaysOfMonth extends SvrProcess {
	int periodID;
	MPeriod mp;
	int year;
	int month;
	int day;
	private boolean p_getEmployee = true;

	@Override
	protected void prepare() {
		periodID = getRecord_ID();
		mp = new MPeriod(getCtx(), periodID, get_TrxName());
		mp.getStartDate();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("DS_IsGetEmployees"))
				p_getEmployee = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected String doIt() throws Exception {
		year = mp.getStartDate().getYear();
		month = mp.getStartDate().getMonth();
		day = mp.getStartDate().getDay();
		Calendar mycal = new GregorianCalendar(year, month, day);
		int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28
		for (int i = 0; i < daysInMonth; i++) {
			Timestamp t = DB.getSQLValueTSEx(get_TrxName(), "select StartDate+"+i
					+ " from C_Period where C_Period_ID=? ", getRecord_ID());
			
			java.util.GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
			cal.setTime(t);

			SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
			String dayString = sdf.format(t);
		
			MDSDays ds = new MDSDays(getCtx(), 0, get_TrxName());
			ds.setDate1(t);
			ds.setC_Period_ID(periodID);
			ds.setDS_Day(dayString);
			ds.save();
			int dayID = ds.getDS_Days_ID();
			getEmployeeFor(dayID);
		}
		if(p_getEmployee)
			return "Days and Employee list generated";
		return "Days are generated";
	}

	private void getEmployeeFor(int dayID) {
		PreparedStatement pstmt;
		ResultSet rs = null;
		String sql = "select bp.name,bp.c_bpartner_id from C_BPartner bp ,GS_HR_Employee hr where "
				+ "bp.c_bpartner_id=hr.c_bpartner_id and bp.isactive='Y' and hr.ad_client_id=?";
		pstmt = DB.prepareStatement(sql, get_TrxName());
		try {
			pstmt.setInt(1, getAD_Client_ID());
			rs = pstmt.executeQuery();
			int line = 0;
			while (rs.next()) {
				line++;
				MDSAvailEmployee availEmp = new MDSAvailEmployee(getCtx(), 0,
						get_TrxName());
				availEmp.setDS_Days_ID(dayID);
				availEmp.setC_BPartner_ID(rs.getInt("c_bpartner_id"));
				availEmp.setLineNo(line);
				availEmp.setDS_IsAssigned(false);
				availEmp.setC_Period_ID(periodID);
				availEmp.save();
			}
		} catch (SQLException e) {
			log.log(Level.WARNING, "Exception: " + e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs=null;
			pstmt=null;
		}

	}

}
