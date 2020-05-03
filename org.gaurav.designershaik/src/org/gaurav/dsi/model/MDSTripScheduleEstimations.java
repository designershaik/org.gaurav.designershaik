package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSTripScheduleEstimations extends X_DS_TripSchedule_Estimations {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4409455682718541690L;

	public MDSTripScheduleEstimations(Properties ctx,
			int DS_TripSchedule_Estimations_ID, String trxName) {
		super(ctx, DS_TripSchedule_Estimations_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSTripScheduleEstimations(Properties ctx, ResultSet rs,
			String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
