package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSFinReportslevel5 extends X_DS_FinReports_level5 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1624045716772874507L;

	/**
	 * 
	 */

	public MDSFinReportslevel5(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSFinReportslevel5(Properties ctx, int DS_FinReports_level5_ID,
			String trxName) {
		super(ctx, DS_FinReports_level5_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
