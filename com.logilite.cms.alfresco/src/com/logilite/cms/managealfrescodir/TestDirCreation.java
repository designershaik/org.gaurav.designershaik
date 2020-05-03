package com.logilite.cms.managealfrescodir;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.FolderType;
import org.apache.chemistry.opencmis.client.api.ObjectId;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.compiere.model.MClient;
import org.compiere.model.MStorageProvider;
import org.compiere.util.DB;
import org.compiere.util.Env;

import com.logilite.cms.alfresco.CmisUtil;

public class TestDirCreation {
	public void create(int recordId, String Name) {
		MClient client = new MClient(Env.getCtx(), Env.getAD_Client_ID(Env
				.getCtx()), null);
		int storageProviderId = DB
				.getSQLValue(
						null,
						"SELECT CI.ad_StorageProvider_id FROM AD_ClientInfo CI JOIN AD_StorageProvider SP ON SP.AD_StorageProvider_ID = CI.AD_StorageProvider_ID Where CI.AD_Client_ID = ? AND SP.Method = 'Alfresco'",
						client.getAD_Client_ID());

		MStorageProvider prov = new MStorageProvider(Env.getCtx(),
				storageProviderId, null);


		Session session = CmisUtil.createCmisSession(prov.getUserName(),
					prov.getPassword(), prov.getURL());
		Folder cmisRoot = (Folder) CmisUtil.getFolder(session,
					prov.getFolder());


	    Map<String, Object> folderProps = new HashMap<String, Object>();
	    folderProps.put(PropertyIds.NAME, Name);
	    folderProps.put(PropertyIds.OBJECT_TYPE_ID, "my:custfolder");
	    //folderProps.put("cmis:description", "My Description");
	   // System.out.println(FolderType.FOLDER_BASETYPE_ID);
	    
	    ObjectId folderObjectId = session.createFolder(folderProps, cmisRoot, null, null, null);
	    try{
	    Folder parFolder =(Folder) session.getObject(folderObjectId);
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }

			
	}

}
