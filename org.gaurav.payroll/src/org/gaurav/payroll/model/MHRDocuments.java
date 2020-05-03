package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDocuments extends X_HR_Documents {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4207333112706562094L;

	public MHRDocuments(Properties ctx, int HR_Documents_ID, String trxName) {
		super(ctx, HR_Documents_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRDocuments(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
