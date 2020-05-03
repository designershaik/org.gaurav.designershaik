package org.gaurav.alerts.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSDocumentsRecipients extends X_GS_Documents_Recipients {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSDocumentsRecipients(Properties ctx, int GS_Documents_Recipients_ID, String trxName) {
		super(ctx, GS_Documents_Recipients_ID, trxName);
		
	}

	public MGSDocumentsRecipients(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
