package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSTripExpenseType extends X_DS_Trip_ExpenseType {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4108824503937107742L;

	public MDSTripExpenseType(Properties ctx, int DS_Trip_ExpenseType_ID,
			String trxName) {
		super(ctx, DS_Trip_ExpenseType_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSTripExpenseType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
