package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSFinReports extends X_DS_FinReports
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2223950890469704342L;
	public MDSFinReports(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
	
	}
	public MDSFinReports(Properties ctx, int DS_FinReports_ID, String trxName) 
	{
		super(ctx, DS_FinReports_ID, trxName);
	
	}
}
