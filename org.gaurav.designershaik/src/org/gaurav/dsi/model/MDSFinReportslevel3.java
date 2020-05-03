package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSFinReportslevel3 extends X_DS_FinReports_level3 {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1857433456956279567L;

	/**
	 * 
	 */

	public MDSFinReportslevel3(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSFinReportslevel3(Properties ctx, int DS_FinReports_level3_ID,
			String trxName) {
		super(ctx, DS_FinReports_level3_ID, trxName);
		// TODO Auto-generated constructor stub
	}

}
