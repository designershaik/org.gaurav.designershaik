package com.logilite.cms.alfresco;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.DocumentType;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.FolderType;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.definitions.PropertyDefinition;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;
import org.compiere.model.MArchive;
import org.compiere.model.MAttachment;
import org.compiere.model.MStorageProvider;
import org.compiere.model.MTable;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

public class CmisUtil 
{
	private static CLogger	s_log	= CLogger.getCLogger (CmisUtil.class);
	public static String ALFRESCO_PATH_SEPARATOR = "/";
	
	public static Session createCmisSession(String user, String password, String url) 
	{
	    SessionFactory sessionFactory = SessionFactoryImpl.newInstance();
	    Map<String, String> parameter = new HashMap<String, String>();
	    parameter.put(SessionParameter.USER, user);
	    parameter.put(SessionParameter.PASSWORD, password);
	    parameter.put(SessionParameter.ATOMPUB_URL, url); 
	    parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
	
	    Repository repository = sessionFactory.getRepositories(parameter).get(0);
	    return repository.createSession();
	}
  
	public static Folder getFolder(Session session, String folderName) 
	{
	    ObjectType type = session.getTypeDefinition("cmis:folder");
	    PropertyDefinition<?> objectIdPropDef = type.getPropertyDefinitions().get(PropertyIds.OBJECT_ID);
	    String objectIdQueryName = objectIdPropDef.getQueryName();
	    
	    ItemIterable<QueryResult> results = session.query("SELECT * FROM cmis:folder WHERE cmis:name='" + folderName + "'", false);
	    for (QueryResult qResult : results) 
	    {
			String objectId = qResult.getPropertyValueByQueryName(objectIdQueryName);
			return (Folder) session.getObject(session.createObjectId(objectId));
	    }
	    return null;
	}
  
	public static Folder createFolder(Session session, Folder parentFolder, String folderName) 
	{
	    Map<String, Object> folderProps = new HashMap<String, Object>();
	    folderProps.put(PropertyIds.NAME, folderName);
	    folderProps.put(PropertyIds.OBJECT_TYPE_ID, FolderType.FOLDER_BASETYPE_ID);
	
	    ObjectId folderObjectId = session.createFolder(folderProps, parentFolder, null, null, null);
	    return (Folder) session.getObject(folderObjectId);
	}
  
	public static Document createDocument(Session session, Folder folder, String fileName, String mimetype, byte[] content) throws Exception 
	{
	    Map<String, Object> docProps = new HashMap<String, Object>();
	    docProps.put(PropertyIds.NAME, fileName);
	    docProps.put(PropertyIds.OBJECT_TYPE_ID, DocumentType.DOCUMENT_BASETYPE_ID);
	    
	    ByteArrayInputStream in = new ByteArrayInputStream(content);
	    ContentStream contentStream = session.getObjectFactory().createContentStream(fileName, content.length, mimetype, in);
	    
	    ObjectId documentId = session.createDocument(docProps, session.createObjectId((String) folder.getPropertyValue(PropertyIds.OBJECT_ID)), contentStream, null, null, null, null);
	    Document document = (Document) session.getObject(documentId);
	    return document;
	}
	
	public static Document createAdempiereAttachment(Session session, Folder folder, String fileName, String mimetype, byte[] content, String tableName, String recordId) throws Exception 
	{
	    Map<String, Object> docProps = new HashMap<String, Object>();
	    docProps.put(PropertyIds.NAME, fileName);
	    docProps.put(PropertyIds.OBJECT_TYPE_ID, "D:ad:attachment");
	    docProps.put("ad:tablename", tableName);
	    docProps.put("ad:recordid", recordId);
	    
	    ByteArrayInputStream in = new ByteArrayInputStream(content);
	    ContentStream contentStream = session.getObjectFactory().createContentStream(fileName, content.length, mimetype, in);
	    
	    ObjectId documentId;
		try
		{
			documentId = session.createDocument(docProps, session.createObjectId((String) folder.getPropertyValue(PropertyIds.OBJECT_ID)), contentStream, null, null, null, null);
		}
		catch (CmisContentAlreadyExistsException caee)
		{
			throw new AdempiereException("File already Exist with Name : " + fileName); 
		}
	    Document document = (Document) session.getObject(documentId);
	    return document;
	}
  

	/**
	 * 
	 * @param is
	 * @return byte[]
	 * @throws IOException
	 */
	public static byte[] getBytes(InputStream is) throws IOException
	{
		int len;
		int size = 1024;
		byte[] buf;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		if (is instanceof ByteArrayInputStream)
		{
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		}
		else
		{
			try
			{
				buf = new byte[size];
				while ((len = is.read(buf, 0, size)) != -1)
					bos.write(buf, 0, len);
				buf = bos.toByteArray();
			}
			finally
			{
				if (null != bos)
					bos.close();
			}
		}
		return buf;
	}

	/**
	 * @param session
	 * @param childFolder
	 * @param parentFolder
	 * @return org.apache.chemistry.opencmis.client.api.Folder
	 */
	public static Folder createChildFolder(Session session, String childFolder, Folder parentFolder)
	{
		Folder folder2;
		try
		{
			folder2 = (Folder) session.getObjectByPath(parentFolder.getPath() + "/" + childFolder);
			s_log.log(Level.INFO, "Folder " + childFolder + " : Alrady Exist");
		}
		catch (CmisObjectNotFoundException onfe)
		{
			folder2 = createFolder(session, parentFolder, childFolder);
		}
		return folder2;
	} //createChildFolder

	/**
	 * @param arch
	 * @return
	 */
	public static String getArchivePathSnippet(MArchive arch)
	{
		StringBuilder path = new StringBuilder().append(arch.getAD_Client_ID()).append(ALFRESCO_PATH_SEPARATOR)
				.append(arch.getAD_Org_ID()).append(ALFRESCO_PATH_SEPARATOR)
				.append(MTable.getTableName(Env.getCtx(), arch.getAD_Table_ID())).append(ALFRESCO_PATH_SEPARATOR)
				.append(String.valueOf(arch.getRecord_ID())).append(ALFRESCO_PATH_SEPARATOR).append(arch.get_ID());

		return path.toString();
	}

	public static org.apache.chemistry.opencmis.client.api.Document getFileFromCmis(Session session, String docId)
	{
		org.apache.chemistry.opencmis.client.api.Document file;
		try
		{
			// Check document on CMIS
			List<org.apache.chemistry.opencmis.client.api.Document> versions;
			org.apache.chemistry.opencmis.client.api.Document version = (org.apache.chemistry.opencmis.client.api.Document) session
					.getObject(session.createObjectId(docId));
			versions = version.getAllVersions();
			// Get only latest version
			file = versions.get(0);
		}
		catch (CmisObjectNotFoundException e)
		{
			s_log.severe(e.getMessage());
			file = null;
		}
		return file;
	} //getFileFromCmis
	
	
	/**
	 * For retriving archived document at specific path
	 * @param arch
	 * @param prov
	 * @return org.apache.chemistry.opencmis.client.api.Document
	 */
	public static org.apache.chemistry.opencmis.client.api.Document getCmisDocumentByPath(MArchive arch,
			MStorageProvider prov)
	{
		String key = getArchivePathSnippet(arch);

		Session session = createCmisSession(prov.getUserName(), prov.getPassword(), prov.getURL());
		Folder folder = (Folder) getFolder(session, prov.getFolder());
		CmisObject cmisObject = session.getObjectByPath(folder.getPath() + "/" + key);
		if (null == cmisObject)
			throw new AdempiereException("File dose not exist " + prov.getURL() + "/" + key);
		org.apache.chemistry.opencmis.client.api.Document file = (org.apache.chemistry.opencmis.client.api.Document) cmisObject;
		
		return file;
	} //getCmisDocumentByPath

	/**
	 * @param prov
	 * @param entry
	 */
	public static void deleteFromCmis(MStorageProvider prov, AlfrescoAttachmentEntry entry)
	{
		Session session = createCmisSession(prov.getUserName(), prov.getPassword(), prov.getURL());
		org.apache.chemistry.opencmis.client.api.Document file = (org.apache.chemistry.opencmis.client.api.Document) session
				.getObject(session.createObjectId(entry.getDocId()));

		s_log.fine("delete: " + file.getName());
		if (file != null)
		{
			file.deleteAllVersions();
		}
	} //deleteFromCmis

	/**
	 * @param attach
	 * @param prov
	 * @param session
	 * @return org.apache.chemistry.opencmis.client.api.Folder
	 */
	public static Folder createFolderHierarchy(MAttachment attach, MStorageProvider prov, Session session)
	{
		Folder folder1 = null, folder2 = null, folder3 = null, folder4 = null;
		Folder cmisRoot = (Folder) getFolder(session, prov.getFolder());
		if (cmisRoot == null)
		{
			throw new AdempiereException("CMIS folder NOT found : " + prov.getFolder());
		}
		String clientID = String.valueOf(attach.getAD_Client_ID());
		String orgID = String.valueOf(attach.getAD_Org_ID());
		String tableName = MTable.getTableName(Env.getCtx(), attach.getAD_Table_ID());
		String recordId = String.valueOf(attach.getRecord_ID());
		
		folder1 = createChildFolder(session, clientID, cmisRoot);
		folder2 = createChildFolder(session, orgID, folder1);
		folder3 = createChildFolder(session, tableName, folder2);
		folder4 = createChildFolder(session, recordId, folder3);
		
		return folder4;
	} //createFolderHierarchy
	
	/**
	 * @param archive
	 * @param prov
	 * @param session
	 * @return org.apache.chemistry.opencmis.client.api.Folder
	 */
	public static Folder createArchiveFolderHierarchy(MArchive archive, MStorageProvider prov, Session session)
	{
		Folder folder1 = null, folder2 = null, folder3 = null, folder4 = null;
		Folder cmisRoot = (Folder) getFolder(session, prov.getFolder());
		if (cmisRoot == null)
		{
			throw new AdempiereException("CMIS folder NOT found : " + prov.getFolder());
		}
		String clientID = String.valueOf(archive.getAD_Client_ID());
		String orgID = String.valueOf(archive.getAD_Org_ID());
		String tableName = MTable.getTableName(Env.getCtx(), archive.getAD_Table_ID());
		String recordId = String.valueOf(archive.getRecord_ID());
		
		folder1 = createChildFolder(session, clientID, cmisRoot);
		folder2 = createChildFolder(session, orgID, folder1);
		folder3 = createChildFolder(session, tableName, folder2);
		folder4 = createChildFolder(session, recordId, folder3);
		
		return folder4;
	} //createArchiveFolderHierarchy
	
}
