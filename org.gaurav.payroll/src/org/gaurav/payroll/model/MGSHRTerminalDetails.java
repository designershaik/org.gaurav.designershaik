package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRTerminalDetails extends X_GS_HR_TerminalDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1779824983587798952L;

	public MGSHRTerminalDetails(Properties ctx, int GS_HR_TerminalDetails_ID,
			String trxName) {
		super(ctx, GS_HR_TerminalDetails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRTerminalDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
