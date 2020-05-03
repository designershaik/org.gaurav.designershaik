package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSQARejectionSetup extends X_DS_QA_Rejection_Setup {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8060719726945530312L;

	public MDSQARejectionSetup(Properties ctx, int DS_QA_Rejection_Setup_ID,
			String trxName) {
		super(ctx, DS_QA_Rejection_Setup_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSQARejectionSetup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
