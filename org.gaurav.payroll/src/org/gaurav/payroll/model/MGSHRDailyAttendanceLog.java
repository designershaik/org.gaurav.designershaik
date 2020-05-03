package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRDailyAttendanceLog extends X_GS_HR_DailyAttendance_Log {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRDailyAttendanceLog(Properties ctx,
			int GS_HR_DailyAttendance_Log_ID, String trxName) {
		super(ctx, GS_HR_DailyAttendance_Log_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRDailyAttendanceLog(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
