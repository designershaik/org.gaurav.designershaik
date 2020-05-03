package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSOrgContactRelation extends X_DS_OrgContactRelation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1154379318057168057L;

	public MDSOrgContactRelation(Properties ctx, int DS_OrgContactRelation_ID,
			String trxName) {
		super(ctx, DS_OrgContactRelation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSOrgContactRelation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
