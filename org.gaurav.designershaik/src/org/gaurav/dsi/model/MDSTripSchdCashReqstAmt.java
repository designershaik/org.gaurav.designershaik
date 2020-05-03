package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSTripSchdCashReqstAmt extends X_DS_TripSchd_CashReqstAmt {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4981780659450299555L;

	public MDSTripSchdCashReqstAmt(Properties ctx,
			int DS_TripSchd_CashReqstAmt_ID, String trxName) {
		super(ctx, DS_TripSchd_CashReqstAmt_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSTripSchdCashReqstAmt(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
