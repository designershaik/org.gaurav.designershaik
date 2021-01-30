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

/** Generated Model for GS_HR_DocumentBaseType
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_DocumentBaseType extends PO implements I_GS_HR_DocumentBaseType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210123L;

    /** Standard Constructor */
    public X_GS_HR_DocumentBaseType (Properties ctx, int GS_HR_DocumentBaseType_ID, String trxName)
    {
      super (ctx, GS_HR_DocumentBaseType_ID, trxName);
      /** if (GS_HR_DocumentBaseType_ID == 0)
        {
			setGS_HR_DocumentBaseType_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_DocumentBaseType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_DocumentBaseType[")
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

	/** Set Base Type.
		@param GS_HR_DocumentBaseType_ID Base Type	  */
	public void setGS_HR_DocumentBaseType_ID (int GS_HR_DocumentBaseType_ID)
	{
		if (GS_HR_DocumentBaseType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_DocumentBaseType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_DocumentBaseType_ID, Integer.valueOf(GS_HR_DocumentBaseType_ID));
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

	/** Set GS_HR_DocumentBaseType_UU.
		@param GS_HR_DocumentBaseType_UU GS_HR_DocumentBaseType_UU	  */
	public void setGS_HR_DocumentBaseType_UU (String GS_HR_DocumentBaseType_UU)
	{
		set_Value (COLUMNNAME_GS_HR_DocumentBaseType_UU, GS_HR_DocumentBaseType_UU);
	}

	/** Get GS_HR_DocumentBaseType_UU.
		@return GS_HR_DocumentBaseType_UU	  */
	public String getGS_HR_DocumentBaseType_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_DocumentBaseType_UU);
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

	public org.compiere.model.I_R_MailText getR_MailText() throws RuntimeException
    {
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_Name)
			.getPO(getR_MailText_ID(), get_TrxName());	}

	/** Set Mail Template.
		@param R_MailText_ID 
		Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID)
	{
		if (R_MailText_ID < 1) 
			set_Value (COLUMNNAME_R_MailText_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailText_ID, Integer.valueOf(R_MailText_ID));
	}

	/** Get Mail Template.
		@return Text templates for mailings
	  */
	public int getR_MailText_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailText_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}