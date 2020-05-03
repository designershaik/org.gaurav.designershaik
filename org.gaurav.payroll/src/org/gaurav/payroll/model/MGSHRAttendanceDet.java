package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRAttendanceDet extends X_GS_HR_Attendance_Det {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRAttendanceDet(Properties ctx, int GS_HR_Attendance_Det_ID, String trxName) {
		super(ctx, GS_HR_Attendance_Det_ID, trxName);
		
	}

	public MGSHRAttendanceDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
