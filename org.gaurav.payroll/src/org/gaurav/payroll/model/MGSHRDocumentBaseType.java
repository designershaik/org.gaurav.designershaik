package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSHRDocumentBaseType extends X_GS_HR_DocumentBaseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRDocumentBaseType(Properties ctx, int GS_HR_DocumentBaseType_ID, String trxName) {
		super(ctx, GS_HR_DocumentBaseType_ID, trxName);
		
	}

	public MGSHRDocumentBaseType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
