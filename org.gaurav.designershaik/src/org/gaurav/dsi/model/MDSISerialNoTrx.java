package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;



public class MDSISerialNoTrx extends X_DSI_SerialNoTrx 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSISerialNoTrx(Properties ctx, int DSI_SerialNoTrx_ID,
			String trxName) 
	{
		super(ctx, DSI_SerialNoTrx_ID, trxName);
		
	}

	public MDSISerialNoTrx(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

}
