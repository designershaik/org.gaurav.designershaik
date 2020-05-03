package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSAvailEmployee extends X_DS_AvailEmployee {

	public MDSAvailEmployee(Properties ctx, int DS_AvailEmployee_ID,
			String trxName) {
		super(ctx, DS_AvailEmployee_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSAvailEmployee(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
