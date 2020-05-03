package com.logilite.cms.alfresco;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.compiere.model.IArchiveStore;
import org.compiere.model.MArchive;
import org.compiere.model.MStorageProvider;
import org.compiere.util.CLogger;

public class ArchiveAlfresco implements IArchiveStore
{
	private static CLogger	s_log	= CLogger.getCLogger(ArchiveAlfresco.class);

	@Override
	public byte[] loadLOBData(MArchive arch, MStorageProvider prov)
	{
		byte[] dataEntry = null;
		org.apache.chemistry.opencmis.client.api.Document file = CmisUtil.getCmisDocumentByPath(arch, prov);
		InputStream stream = file.getContentStream().getStream();

		try
		{
			dataEntry = CmisUtil.getBytes(stream);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			throw new AdempiereException(e.getLocalizedMessage());
		}
		return dataEntry;
	}

	@Override
	public void save(MArchive archive, MStorageProvider prov, byte[] inflatedData)
	{
		if (inflatedData == null || inflatedData.length == 0)
			throw new IllegalArgumentException("InflatedData is NULL");

		if (archive.get_ID() == 0)
		{
			// set binary data otherwise save will fail
			archive.setByteData(new byte[] { '0' });
			if (!archive.save())
			{
				throw new IllegalArgumentException("unable to save MArchive");
			}
		}

		//
		try
		{
			Session session = CmisUtil.createCmisSession(prov.getUserName(), prov.getPassword(), prov.getURL());
			Folder folder4 = CmisUtil.createArchiveFolderHierarchy(archive, prov, session);
			String contentType = "application/pdf";
			CmisUtil.createAdempiereAttachment(session, folder4, String.valueOf(archive.get_ID())+".pdf", contentType, inflatedData,
					String.valueOf(archive.get_Table_ID()), String.valueOf(archive.getRecord_ID()));

		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "save LOBData", e);
		}

	} //save

	@Override
	public boolean deleteArchive(MArchive archive, MStorageProvider prov)
	{
		org.apache.chemistry.opencmis.client.api.Document file = CmisUtil.getCmisDocumentByPath(archive, prov);
		try
		{
			file.deleteAllVersions();
		}
		catch (Exception e)
		{
			s_log.warning("unable to delete " + file.getName() + "Exception: " + e.getLocalizedMessage());
			return false;
		}
		return true;
	}
}
