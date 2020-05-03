package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSCouponSchedule extends X_DS_CouponSchedule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSCouponSchedule(Properties ctx, int DS_CouponSchedule_ID, String trxName) {
		super(ctx, DS_CouponSchedule_ID, trxName);
		
	}

	public MDSCouponSchedule(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
