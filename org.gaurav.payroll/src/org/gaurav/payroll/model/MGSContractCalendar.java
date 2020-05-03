package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSContractCalendar extends X_GS_ContractCalendar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -70070353101721861L;

	public MGSContractCalendar(Properties ctx, int GS_ContractCalendar_ID, String trxName) {
		super(ctx, GS_ContractCalendar_ID, trxName);
		
	}

	public MGSContractCalendar(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
