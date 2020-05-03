package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSIRefillPrintForm extends X_DSI_Refill_PrintForm 
{

	public MDSIRefillPrintForm(Properties ctx, int DSI_Refill_PrintForm_ID,
			String trxName) {
		super(ctx, DSI_Refill_PrintForm_ID, trxName);

	}

	public MDSIRefillPrintForm(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
