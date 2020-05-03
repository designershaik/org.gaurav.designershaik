package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSDunningEntryEmails extends X_DS_DunningEntry_Emails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSDunningEntryEmails(Properties ctx, int DS_DunningEntry_Emails_ID,
			String trxName) {
		super(ctx, DS_DunningEntry_Emails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSDunningEntryEmails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
