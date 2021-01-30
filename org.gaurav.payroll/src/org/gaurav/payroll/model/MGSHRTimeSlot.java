package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;
public class MGSHRTimeSlot extends X_GS_HR_TimeSlot {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRTimeSlot(Properties ctx, int GS_HR_TimeSlot_ID, String trxName) {
		super(ctx, GS_HR_TimeSlot_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRTimeSlot(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
