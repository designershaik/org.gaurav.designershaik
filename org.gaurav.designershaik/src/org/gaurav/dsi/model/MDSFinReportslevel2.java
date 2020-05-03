package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSFinReportslevel2 extends X_DS_FinReports_level2 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 651890969764296726L;

	/**
	 * 
	 */


	public MDSFinReportslevel2(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSFinReportslevel2(Properties ctx, int DS_FinReports_level2_ID,
			String trxName) {
		super(ctx, DS_FinReports_level2_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
