package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSAgentMaster extends X_DS_AgentMaster {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSAgentMaster(Properties ctx, int DS_AgentMaster_ID, String trxName) {
		super(ctx, DS_AgentMaster_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSAgentMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
