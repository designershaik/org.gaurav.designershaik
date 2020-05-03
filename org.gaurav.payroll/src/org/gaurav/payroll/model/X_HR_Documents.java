/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Documents
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_HR_Documents extends PO implements I_HR_Documents, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190410L;

    /** Standard Constructor */
    public X_HR_Documents (Properties ctx, int HR_Documents_ID, String trxName)
    {
      super (ctx, HR_Documents_ID, trxName);
      /** if (HR_Documents_ID == 0)
        {
			setHR_Documents_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_HR_Documents (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_HR_Documents[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public I_GS_HR_DocumentBaseType getGS_HR_DocumentBaseType() throws RuntimeException
    {
		return (I_GS_HR_DocumentBaseType)MTable.get(getCtx(), I_GS_HR_DocumentBaseType.Table_Name)
			.getPO(getGS_HR_DocumentBaseType_ID(), get_TrxName());	}

	/** Set Base Type.
		@param GS_HR_DocumentBaseType_ID Base Type	  */
	public void setGS_HR_DocumentBaseType_ID (int GS_HR_DocumentBaseType_ID)
	{
		if (GS_HR_DocumentBaseType_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_DocumentBaseType_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_DocumentBaseType_ID, Integer.valueOf(GS_HR_DocumentBaseType_ID));
	}

	/** Get Base Type.
		@return Base Type	  */
	public int getGS_HR_DocumentBaseType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_DocumentBaseType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Documents.
		@param HR_Documents_ID Documents	  */
	public void setHR_Documents_ID (int HR_Documents_ID)
	{
		if (HR_Documents_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Documents_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Documents_ID, Integer.valueOf(HR_Documents_ID));
	}

	/** Get Documents.
		@return Documents	  */
	public int getHR_Documents_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Documents_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Documents_UU.
		@param HR_Documents_UU HR_Documents_UU	  */
	public void setHR_Documents_UU (String HR_Documents_UU)
	{
		set_Value (COLUMNNAME_HR_Documents_UU, HR_Documents_UU);
	}

	/** Get HR_Documents_UU.
		@return HR_Documents_UU	  */
	public String getHR_Documents_UU () 
	{
		return (String)get_Value(COLUMNNAME_HR_Documents_UU);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}