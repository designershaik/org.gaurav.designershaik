package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSIExportPayments extends X_DSI_ExportPayments
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSIExportPayments(Properties ctx, int DSI_ExportPayments_ID,
			String trxName) {
		super(ctx, DSI_ExportPayments_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSIExportPayments(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	

}
