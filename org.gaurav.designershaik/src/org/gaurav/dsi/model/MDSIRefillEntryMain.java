package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSIRefillEntryMain extends  X_DSI_RefillEntry_Main{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3036352176377825180L;

	public MDSIRefillEntryMain(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
		
	}

	public MDSIRefillEntryMain(Properties ctx, int DSI_RefillEntry_Main_ID,
			String trxName) 
	{
		super(ctx, DSI_RefillEntry_Main_ID, trxName);
		
	}

}
