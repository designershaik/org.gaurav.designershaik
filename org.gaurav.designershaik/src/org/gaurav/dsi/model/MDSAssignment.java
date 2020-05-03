package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSAssignment extends X_DS_Assignment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MDSAssignment(Properties ctx, int DS_Assignment_ID, String trxName) {
		super(ctx, DS_Assignment_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSAssignment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
