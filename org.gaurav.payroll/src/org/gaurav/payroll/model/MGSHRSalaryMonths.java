package org.gaurav.payroll.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MYear;
import org.compiere.util.CCache;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

public class MGSHRSalaryMonths extends X_GS_HR_SalaryMonths {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CLogger			s_log = CLogger.getCLogger (MGSHRSalaryMonths.class); 

	public MGSHRSalaryMonths(Properties ctx, int GS_HR_SalaryMonths_ID, String trxName) {
		super(ctx, GS_HR_SalaryMonths_ID, trxName);
		
	}

	public MGSHRSalaryMonths(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	public MGSHRSalaryMonths(MYear year, int PeriodNo, String name, Timestamp startDate,Timestamp endDate) 
	{
		this (year.getCtx(), 0, year.get_TrxName());
		setClientOrg(year);
		setC_Year_ID(year.getC_Year_ID());
		setPeriodNo(PeriodNo);
		setName(name);
		setStartDate(startDate);
		setEndDate(endDate);
		
	}
	private static CCache<Integer,MGSHRSalaryMonths> s_cache = new CCache<Integer,MGSHRSalaryMonths>(Table_Name, 10);
	
	public static MGSHRSalaryMonths findByCalendar(Properties ctx, Timestamp DateAcct, int C_Calendar_ID,String trxName) {
		int AD_Client_ID = Env.getAD_Client_ID(ctx);
		//	Search in Cache first
		Iterator<MGSHRSalaryMonths> it = s_cache.values().iterator();
		while (it.hasNext())
		{
			MGSHRSalaryMonths period = (MGSHRSalaryMonths)it.next();
			if (period.getC_Calendar_ID() == C_Calendar_ID && period.isInPeriod(DateAcct) 
					&& period.getAD_Client_ID() == AD_Client_ID)  // globalqss - CarlosRuiz - Fix [ 1820810 ] Wrong Period Assigned to Fact_Acct
				return period;
		}
		
		//	Get it from DB
		MGSHRSalaryMonths retValue = null;
		String sql = "SELECT * "
			+ "FROM GS_HR_SalaryMonths "
			+ "WHERE C_Year_ID IN "
				+ "(SELECT C_Year_ID FROM C_Year WHERE C_Calendar_ID= ?)"
			+ " AND ? BETWEEN TRUNC(StartDate) AND TRUNC(EndDate)"
			+ " AND IsActive=? ";
        
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt (1, C_Calendar_ID);
            pstmt.setTimestamp (2, TimeUtil.getDay(DateAcct));
            pstmt.setString(3, "Y");
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				MGSHRSalaryMonths period = new MGSHRSalaryMonths(ctx, rs, trxName);
				Integer key = new Integer(period.getGS_HR_SalaryMonths_ID());
				s_cache.put (key, period);
				retValue = period;
			}
		}
		catch (SQLException e)
		{
			s_log.log(Level.SEVERE, "DateAcct=" + DateAcct, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		if (retValue == null)
			if (s_log.isLoggable(Level.INFO)) s_log.info("No Standard Period for " + DateAcct 
				+ " (AD_Client_ID=" + AD_Client_ID + ")");
		return retValue;
	}

	/**
	 * 	Date In Period
	 *	@param date date
	 *	@return true if in period
	 */
	public boolean isInPeriod (Timestamp date)
	{
		if (date == null)
			return false;
		Timestamp dateOnly = TimeUtil.getDay(date);
		Timestamp from = TimeUtil.getDay(getStartDate());
		if (dateOnly.before(from))
			return false;
		Timestamp to = TimeUtil.getDay(getEndDate());
		if (dateOnly.after(to))
			return false;
		return true;
	}	//	isInPeriod
	
	public String toString ()
	{
		StringBuilder sb = new StringBuilder ("MPeriod[");
		sb.append (get_ID())
			.append("-").append (getName())
			.append(", ").append(getStartDate()).append("-").append(getEndDate())
			.append ("]");
		return sb.toString ();
	}	//	toString

}
