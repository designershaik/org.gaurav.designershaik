package com.logilite.cms.model;

import java.util.logging.Level;

import org.compiere.model.MClient;
import org.compiere.model.MProject;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;

import com.logilite.cms.managealfrescodir.CreateDirectoryStructure;
import com.logilite.cms.managealfrescodir.TestDirCreation;

import compiere.model.MyValidator;

public class ProjectValidator implements ModelValidator{

	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MyValidator.class);
	/** Client			*/
	private int		m_AD_Client_ID = -1;
	/** User	*/
	private int		m_AD_User_ID = -1;
	/** Role	*/
	private int		m_AD_Role_ID = -1;
	
	CreateDirectoryStructure cds = null;
	
	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		if (client != null) {	
			m_AD_Client_ID = client.getAD_Client_ID();
			if (log.isLoggable(Level.INFO)) log.info(client.toString());
		}
		else  {
			if (log.isLoggable(Level.INFO)) log.info("Initializing global validator: "+this.toString());
		}
		engine.addModelChange(MProject.Table_Name, this);
	}

	@Override
	public int getAD_Client_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {

		if (po.get_TableName().equals("C_Project") && type == TYPE_AFTER_NEW)
		{
			MProject project = (MProject) po;
			cds = new CreateDirectoryStructure();
			cds.create(project.getValue());
		}else if(po.get_TableName().equals("C_Project") && type == TYPE_CHANGE)
		{
			MProject project = (MProject) po;
			TestDirCreation tdc  = new TestDirCreation();
			tdc.create(project.get_ID(), project.getValue());
		}
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
