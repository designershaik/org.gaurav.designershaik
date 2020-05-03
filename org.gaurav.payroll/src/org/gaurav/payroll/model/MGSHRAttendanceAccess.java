package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRAttendanceAccess extends X_GS_HR_Attendance_Access {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRAttendanceAccess(Properties ctx,
			int GS_HR_Attendance_Access_ID, String trxName) {
		super(ctx, GS_HR_Attendance_Access_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRAttendanceAccess(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
