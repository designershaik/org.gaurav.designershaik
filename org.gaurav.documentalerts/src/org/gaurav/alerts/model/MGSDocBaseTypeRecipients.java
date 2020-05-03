package org.gaurav.alerts.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MGSDocBaseTypeRecipients extends X_GS_DocBaseType_Recipients {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSDocBaseTypeRecipients(Properties ctx, int GS_DocBaseType_Recipients_ID, String trxName) {
		super(ctx, GS_DocBaseType_Recipients_ID, trxName);
		
	}

	public MGSDocBaseTypeRecipients(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
