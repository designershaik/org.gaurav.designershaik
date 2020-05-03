package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MDSAttachmentTags extends X_DS_Attachment_Tags {

	public MDSAttachmentTags(Properties ctx, ResultSet rs, String trxName) 
	{
		super(ctx, rs, trxName);
		
	}
	public MDSAttachmentTags(Properties ctx, int DS_Attachment_Tags_ID,
			String trxName) 
	{
		super(ctx, DS_Attachment_Tags_ID, trxName);
	}

}
