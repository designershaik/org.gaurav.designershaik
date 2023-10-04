package com.logilite.esignature.dms.factory;

import org.compiere.model.MRole;
import org.compiere.util.Env;

import com.logilite.dms.DMS;
import com.logilite.dms.factories.IDMSUploadContent;
import com.logilite.dms.factories.IDMSUploadContentFactory;
import com.logilite.dms.model.I_DMS_Content;
import com.logilite.esignature.dms.form.WUploadContentWithSign;

/**
 * Factory for support of Signature upload in DMS
 * 
 * @author Sachin Bhimani
 */
public class SignDMSUploadContentFactory implements IDMSUploadContentFactory
{

	@Override
	public IDMSUploadContent getUploadForm(DMS dms, I_DMS_Content content, boolean isVersion, int tableID, int recordID, int windowNo, int tabNo)
	{
		boolean isDMSSignSupport = MRole.get(Env.getCtx(), Env.getAD_Role_ID(Env.getCtx())).get_ValueAsBoolean("IsDMSSignSupport");

		if (isDMSSignSupport)
			return new WUploadContentWithSign(dms, content, isVersion, tableID, recordID, windowNo, tabNo);

		return null;
	}

}
