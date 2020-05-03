package com.logilite.esignature.topaz.util;

import java.net.URL;

import org.adempiere.base.Core;
import org.adempiere.base.IResourceFinder;
import org.compiere.util.DB;

public class Utils
{
	private static int	REFERENCE_DATATYPE_SIGNATURE	= 0;

	public static int getDisplayTypeSignature()
	{
		if (REFERENCE_DATATYPE_SIGNATURE <= 0)
		{
			REFERENCE_DATATYPE_SIGNATURE = DB.getSQLValue(null,
					"SELECT AD_Reference_ID FROM AD_Reference WHERE Name = 'Signature' AND ValidationType = 'D' ");
		}
		return REFERENCE_DATATYPE_SIGNATURE;
	}

	public static URL getJSResource(String name)
	{
		IResourceFinder rf = Core.getResourceFinder();
		return rf.getResource("/js/" + name);
	}

}
