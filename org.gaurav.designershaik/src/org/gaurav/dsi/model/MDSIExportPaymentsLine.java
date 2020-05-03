package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSIExportPaymentsLine extends X_DSI_ExportPaymentsLine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSIExportPaymentsLine(Properties ctx,
			int DSI_ExportPaymentsLine_ID, String trxName) {
		super(ctx, DSI_ExportPaymentsLine_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSIExportPaymentsLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
