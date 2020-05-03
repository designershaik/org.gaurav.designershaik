package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSFinReportslevel1 extends X_DS_FinReports_level1 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6387198343664624230L;

	public MDSFinReportslevel1(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSFinReportslevel1(Properties ctx, int DS_FinReports_level1_ID,
			String trxName) {
		super(ctx, DS_FinReports_level1_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
