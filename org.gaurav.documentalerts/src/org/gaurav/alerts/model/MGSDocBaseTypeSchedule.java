package org.gaurav.alerts.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSDocBaseTypeSchedule extends X_GS_DocBaseType_Schedule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSDocBaseTypeSchedule(Properties ctx, int GS_DocBaseType_Schedule_ID, String trxName) {
		super(ctx, GS_DocBaseType_Schedule_ID, trxName);
		
	}

	public MGSDocBaseTypeSchedule(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
