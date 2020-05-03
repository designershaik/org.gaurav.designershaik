package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSAssignmentDetails extends X_DS_Assignment_Details {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6993855267400486287L;

	public MDSAssignmentDetails(Properties ctx, int DS_Assignment_Details_ID,
			String trxName) {
		super(ctx, DS_Assignment_Details_ID, trxName);
		
	}

	public MDSAssignmentDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
