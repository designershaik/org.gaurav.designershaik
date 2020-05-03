package com.logilite.cms.alfresco;

import org.compiere.model.MAttachmentEntry;

public class AlfrescoAttachmentEntry extends MAttachmentEntry
{

	public AlfrescoAttachmentEntry(String name, byte[] data)
	{
		super(name, data);
	}

	public AlfrescoAttachmentEntry (String name, byte[] data, String docid, int index)
	{
		super (name, data, index);
		setDocId (docid);
	}	//	AlfrescoAttachmentEntry
	
	/**	The docid				*/
	private String 	m_docid = "";
	
	/**
	 * @return Returns the docid.
	 */
	public String getDocId ()
	{
		return m_docid;
	}
	
	/**
	 * @param name The docid to set.
	 */
	public void setDocId (String docid)
	{
		if (docid != null)
			m_docid = docid;
		if (docid == null)
			m_docid = "";
	}	
}
