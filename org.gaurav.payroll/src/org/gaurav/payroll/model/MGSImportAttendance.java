package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSImportAttendance extends X_GS_ImportAttendance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSImportAttendance(Properties ctx, int GS_ImportAttendance_ID, String trxName) {
		super(ctx, GS_ImportAttendance_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSImportAttendance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
