package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSQAMORejection extends X_DS_QA_MO_Rejection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSQAMORejection(Properties ctx, int DS_QA_MO_Rejection_ID,
			String trxName) {
		super(ctx, DS_QA_MO_Rejection_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSQAMORejection(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
