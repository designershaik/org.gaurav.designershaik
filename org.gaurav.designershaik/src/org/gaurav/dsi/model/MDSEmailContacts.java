package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSEmailContacts extends X_DS_EmailContacts {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSEmailContacts(Properties ctx, int DS_EmailContacts_ID,
			String trxName) {
		super(ctx, DS_EmailContacts_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSEmailContacts(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
