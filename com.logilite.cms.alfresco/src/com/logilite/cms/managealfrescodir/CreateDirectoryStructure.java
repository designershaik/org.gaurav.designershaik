package com.logilite.cms.managealfrescodir;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.FolderType;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.compiere.model.MClient;
import org.compiere.model.MStorageProvider;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.logilite.cms.alfresco.CmisUtil;

public class CreateDirectoryStructure {
	public void create(String dirName) throws Exception {
		MClient client = new MClient(Env.getCtx(), Env.getAD_Client_ID(Env
				.getCtx()), null);
		int storageProviderId = DB
				.getSQLValue(
						null,
						"SELECT CI.ad_StorageProvider_id FROM AD_ClientInfo CI JOIN AD_StorageProvider SP ON SP.AD_StorageProvider_ID = CI.AD_StorageProvider_ID Where CI.AD_Client_ID = ? AND SP.Method = 'Alfresco'",
						client.getAD_Client_ID());

		MStorageProvider prov = new MStorageProvider(Env.getCtx(),
				storageProviderId, null);

		boolean isCmis = client.get_ValueAsBoolean("StoreAttachmentsOnCMIS");

		if (isCmis) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			File file = new File(System.getProperty("user.dir")
					+ "/xml/dir.xml");
			 Session session = CmisUtil.createCmisSession(prov.getUserName(),
			 prov.getPassword(), prov.getURL());
			 Folder cmisRoot = (Folder) CmisUtil.getFolder(session,
			 prov.getFolder());

			Folder parFolder = CmisUtil.createFolder(session, cmisRoot, dirName);
			
			 if (file.exists()) {
				Document doc = db.parse(file);
				Element docEle = doc.getDocumentElement();
				NodeList nodeList = docEle.getChildNodes();
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node n = nodeList.item(i);
					if (!n.getNodeName().equals("#text"))
						createChildDirectory(session,parFolder, n);
				}
			}
		}
	}

	public boolean createChildDirectory(Session session,Folder parent, Node child) {

		Folder childFolder = CmisUtil.createChildFolder(session, child.getNodeName(), parent);
		NodeList nodeList = child.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			if (!n.getNodeName().equals("#text"))
				createChildDirectory(session, childFolder, n);
		}
		return true;
	}
}
