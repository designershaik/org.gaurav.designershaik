package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSQARejectionReason extends X_DS_QARejection_Reason {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7491205709872785930L;

	public MDSQARejectionReason(Properties ctx, int DS_QARejection_Reason_ID,
			String trxName) {
		super(ctx, DS_QARejection_Reason_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSQARejectionReason(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
