package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;


public class MDSISerialNumberMaster extends X_DSI_SerialNumberMaster {

	static int i=19;
	public MDSISerialNumberMaster(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
		
	}
	public MDSISerialNumberMaster(Properties ctx,
			int DSI_SerialNumberMaster_ID, String trxName) 
	{
		super(ctx, DSI_SerialNumberMaster_ID, trxName);
		
	}
	

}
