package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.X_C_ContactActivity;

public class MCContactActivity extends X_C_ContactActivity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MCContactActivity(Properties ctx, int C_ContactActivity_ID, String trxName) {
		super(ctx, C_ContactActivity_ID, trxName);
		
	}

	public MCContactActivity(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
