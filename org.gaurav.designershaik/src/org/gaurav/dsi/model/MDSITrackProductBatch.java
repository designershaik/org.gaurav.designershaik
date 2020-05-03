package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSITrackProductBatch extends X_DSI_TrackProductBatch {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSITrackProductBatch(Properties ctx, int DSI_TrackProductBatch_ID, String trxName) {
		super(ctx, DSI_TrackProductBatch_ID, trxName);
		
	}

	public MDSITrackProductBatch(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
