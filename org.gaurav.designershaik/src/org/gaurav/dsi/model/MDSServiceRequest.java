package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSServiceRequest extends X_DS_ServiceRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5378323861249409443L;

	public MDSServiceRequest(Properties ctx, int DS_ServiceRequest_ID,
			String trxName) {
		super(ctx, DS_ServiceRequest_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSServiceRequest(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
