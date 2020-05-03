package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRLogsImported extends X_GS_HR_LogsImported {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRLogsImported(Properties ctx, int GS_HR_LogsImported_ID, String trxName) {
		super(ctx, GS_HR_LogsImported_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRLogsImported(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
