package com.logilite.cms.alfresco;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.compiere.model.IAttachmentStore;
import org.compiere.model.MAttachment;
import org.compiere.model.MAttachmentEntry;
import org.compiere.model.MStorageProvider;
import org.compiere.model.MTable;
import org.compiere.model.MUser;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class AttachmentAlfresco implements IAttachmentStore
{

	private static CLogger	s_log	= CLogger.getCLogger(AttachmentAlfresco.class);
	MStorageProvider		provider;
	private  String AlfrescoUser ;
	private  String AlfrescoPassword;
	
	public AttachmentAlfresco()
	{
		super();
		this.provider = null;
	}

	public AttachmentAlfresco(MStorageProvider p)
	{
		super();
		this.provider = p;
	}

	@Override
	public boolean loadLOBData(MAttachment attach, MStorageProvider prov)
	{
		int AD_User_ID=Env.getAD_User_ID(Env.getCtx());
		
		MUser user=new MUser(Env.getCtx(), AD_User_ID,null);
		
		AlfrescoUser = user.getName();
		AlfrescoPassword = user.getPassword();
		
		if ("".equals(prov.getURL()) || "".equals(prov.getURL()) || "".equals(prov.getPassword())
				|| "".equals(prov.getFolder()))
		{
			s_log.severe("no CMIS parameters defined");
			return false;
		}
		// Reset
		attach.m_items = new ArrayList<MAttachmentEntry>();
		//
		byte[] data = attach.getBinaryData();
		if (data == null)
			return true;
		s_log.fine("TextFileSize=" + data.length);
		if (data.length == 0)
			return true;

		Session session = CmisUtil.createCmisSession(prov.getUserName(), prov.getPassword(), prov.getURL());
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try
		{
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(new ByteArrayInputStream(data));
			final NodeList entries = document.getElementsByTagName("entry");
			for (int i = 0; i < entries.getLength(); i++)
			{
				final Node entryNode = entries.item(i);
				final NamedNodeMap attributes = entryNode.getAttributes();
				final Node docidNode = attributes.getNamedItem("docid");
				final Node fileNode = attributes.getNamedItem("file");
				final Node nameNode = attributes.getNamedItem("name");
				if (docidNode == null || fileNode == null || nameNode == null)
				{
					s_log.severe("no filename for entry " + i);
					attach.m_items = null;
					return false;
				}
				s_log.fine("name: " + nameNode.getNodeValue());
				String docId = docidNode.getNodeValue();
				s_log.fine("docId: " + docId);

				org.apache.chemistry.opencmis.client.api.Document file;
				file = CmisUtil.getFileFromCmis(session, docId);

				if (file != null)
				{
					addAttachmentEntry(attach, docId, file);
				}
				else
				{
					s_log.severe("Document ID not found: " + docId);
				}
			}

		}
		catch (SAXException sxe)
		{
			// Error generated during parsing)
			Exception x = sxe;
			if (sxe.getException() != null)
				x = sxe.getException();
			x.printStackTrace();
			s_log.severe(x.getMessage());

		}
		catch (ParserConfigurationException pce)
		{
			// Parser with specified options can't be built
			pce.printStackTrace();
			s_log.severe(pce.getMessage());

		}
		catch (IOException ioe)
		{
			// I/O error
			ioe.printStackTrace();
			s_log.severe(ioe.getMessage());
		}

		return true;

	} //loadLOBData

	/**
	 * @param attach
	 * @param docId
	 * @param file
	 */
	private void addAttachmentEntry(MAttachment attach, String docId,
			org.apache.chemistry.opencmis.client.api.Document file)
	{
		try
		{
			InputStream fileInputStream = file.getContentStream().getStream();
			final byte[] dataEntry = CmisUtil.getBytes(fileInputStream);
			fileInputStream.close();
			final AlfrescoAttachmentEntry entry = new AlfrescoAttachmentEntry(file.getName(), dataEntry, docId,
					attach.m_items.size() + 1);
			attach.m_items.add(entry);
		}
		catch (FileNotFoundException e)
		{
			s_log.severe("File Not Found.");
			e.printStackTrace();
		}
		catch (IOException e1)
		{
			s_log.severe("Error Reading The File.");
			e1.printStackTrace();
		}
	} //addAttachmentEntry

	@Override
	public boolean save(MAttachment attach, MStorageProvider prov)
	{
		String cmisUrl = prov.getURL();
		if ("".equals(cmisUrl))
		{
			s_log.severe("no Url for CMIS defined");
			return false;
		}
		if (attach.m_items == null || attach.m_items.size() == 0)
		{
			attach.setBinaryData(null);
			return true;
		}
		boolean temp = putFile(attach, prov) ?  true : false;
		return temp;
	} //save

	/**
	 * @param attach
	 * @param prov
	 * @param cmisUrl
	 * @throws TransformerFactoryConfigurationError
	 */
	private boolean putFile(MAttachment attach, MStorageProvider prov) throws TransformerFactoryConfigurationError
	{
		boolean retValue = true;
		Session session = CmisUtil.createCmisSession(prov.getUserName(), prov.getPassword(), prov.getURL());
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try
		{
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.newDocument();
			final Element root = document.createElement("attachments");
			document.appendChild(root);
			document.setXmlStandalone(true);

			String tableName = MTable.getTableName(Env.getCtx(), attach.getAD_Table_ID());
			String recordId = String.valueOf(attach.getRecord_ID());

			// create xml entries
			for (int i = 0; i < attach.m_items.size(); i++)
			{
				s_log.fine(attach.m_items.get(i).toString());
				String docId = "";
				if (attach.m_items.get(i) instanceof AlfrescoAttachmentEntry)
				{
					docId = ((AlfrescoAttachmentEntry) attach.m_items.get(i)).getDocId();
				}

				if (docId.equals("")) // Not exists, create new document.
				{
					File entryFile = attach.m_items.get(i).getFile();
					s_log.fine("move file: " + entryFile.getAbsolutePath()+ " - " + prov.getURL());
					try
					{
						Folder folder4 = CmisUtil.createFolderHierarchy(attach, prov, session);
						org.apache.chemistry.opencmis.client.api.Document cmisDoc = CmisUtil.createAdempiereAttachment(session, folder4, attach.getEntryName(i),
								attach.m_items.get(i).getContentType(), attach.m_items.get(i).getData(), tableName,
								recordId);
						docId = cmisDoc.getId().substring(0, cmisDoc.getId().lastIndexOf(";")); // Remove
					}
					catch (IOException e)
					{
						e.printStackTrace();
						s_log.severe("unable to copy file " + entryFile.getAbsolutePath() + " to "
								+ prov.getURL() + File.separator + getAttachmentPathSnippet(attach)
								+ File.separator + entryFile.getName());
					}
				}
				final Element entry = document.createElement("entry");
				entry.setAttribute("name", attach.getEntryName(i));
				entry.setAttribute("file", attach.getEntryName(i)); // File
				entry.setAttribute("docid", docId);
				root.appendChild(entry);
			}

			final Source source = new DOMSource(document);
			final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			final Result result = new StreamResult(bos);
			final Transformer xformer = TransformerFactory.newInstance().newTransformer();
			xformer.transform(source, result);
			final byte[] xmlData = bos.toByteArray();
			s_log.fine(bos.toString());
			attach.setBinaryData(xmlData);
			attach.setTitle(MAttachment.XML);
			
		}
		catch (Exception e)
		{
			s_log.log(Level.SEVERE, "saveLOBData", e);
			retValue = false;
		}
		return retValue;
	}



	@Override
	public boolean delete(MAttachment attach, MStorageProvider prov)
	{
		while (attach.m_items.size() > 0) {
			deleteEntry(attach, prov, attach.m_items.size()-1);
		}
		return true;
	}

	@Override
	public boolean deleteEntry(MAttachment attach, MStorageProvider prov, int index)
	{
		AlfrescoAttachmentEntry entry = null;

		// remove files
		if (attach.m_items.get(index) instanceof AlfrescoAttachmentEntry)
		{
			entry = (AlfrescoAttachmentEntry) attach.m_items.get(index);
		}
		if (null == entry)
		{
			return false;
		}

		CmisUtil.deleteFromCmis(prov, entry);
		attach.m_items.remove(index);
		return true;
	}

	/**
	 * Returns a path snippet, containing client, org, table and record id.
	 * @return String
	 */
	private String getAttachmentPathSnippet(MAttachment attach)
	{
		return attach.getAD_Client_ID() + File.separator + attach.getAD_Org_ID() + File.separator
				+ attach.getAD_Table_ID() + File.separator + attach.getRecord_ID();
	}
}
