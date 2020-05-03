package com.logilite.esignature.topaz.factory;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.adempiere.base.IDisplayTypeFactory;
import org.compiere.util.Language;

import com.logilite.esignature.topaz.util.Utils;

/**
 * Display Type factory for Signature
 * 
 * @author Sachin Bhimani
 */
public class SignatureDisplayTypeFactory implements IDisplayTypeFactory
{

	@Override
	public boolean isID(int displayType)
	{
		if (displayType == Utils.getDisplayTypeSignature())
			return true;
		return false;
	}

	@Override
	public boolean isNumeric(int displayType)
	{
		return false;
	}

	@Override
	public Integer getDefaultPrecision(int displayType)
	{
		return 0;
	}

	@Override
	public boolean isText(int displayType)
	{
		return false;
	}

	@Override
	public boolean isDate(int displayType)
	{
		return false;
	}

	@Override
	public boolean isLookup(int displayType)
	{
		return false;
	}

	@Override
	public boolean isLOB(int displayType)
	{
		return false;
	}

	@Override
	public DecimalFormat getNumberFormat(int displayType, Language language, String pattern)
	{
		return null;
	}

	@Override
	public SimpleDateFormat getDateFormat(int displayType, Language language, String pattern)
	{
		return null;
	}

	@Override
	public Class<?> getClass(int displayType, boolean yesNoAsBoolean)
	{
		if (displayType == Utils.getDisplayTypeSignature())
			return Integer.class;
		return null;
	}

	@Override
	public String getSQLDataType(int displayType, String columnName, int fieldLength)
	{
		if (displayType == Utils.getDisplayTypeSignature())
		{
			if (columnName.equals("BinaryData"))
				return "BLOB";
			// ID, CreatedBy/UpdatedBy, Acct
			else if (columnName.endsWith("_ID") || columnName.endsWith("tedBy") || columnName.endsWith("_Acct"))
				return "NUMBER(10)";
			else if (fieldLength < 4)
				return "CHAR(" + fieldLength + ")";
			else
				// EntityType, AD_Language fallback
				return "VARCHAR2(" + fieldLength + ")";
		}

		return null;
	}

	@Override
	public String getDescription(int displayType)
	{
		if (displayType == Utils.getDisplayTypeSignature())
			return "Signature";

		return null;
	}

}
