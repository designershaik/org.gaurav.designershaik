package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSDunningIncludeEmails extends X_DS_DunningInclude_Emails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSDunningIncludeEmails(Properties ctx,
			int DS_DunningInclude_Emails_ID, String trxName) {
		super(ctx, DS_DunningInclude_Emails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSDunningIncludeEmails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
