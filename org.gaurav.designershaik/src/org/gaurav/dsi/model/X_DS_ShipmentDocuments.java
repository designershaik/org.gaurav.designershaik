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
package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for DS_ShipmentDocuments
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_ShipmentDocuments extends PO implements I_DS_ShipmentDocuments, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_ShipmentDocuments (Properties ctx, int DS_ShipmentDocuments_ID, String trxName)
    {
      super (ctx, DS_ShipmentDocuments_ID, trxName);
      /** if (DS_ShipmentDocuments_ID == 0)
        {
			setDS_ShipmentDocuments_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_ShipmentDocuments (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_ShipmentDocuments[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Category Name.
		@param CategoryName 
		Name of the Category
	  */
	public void setCategoryName (String CategoryName)
	{
		set_Value (COLUMNNAME_CategoryName, CategoryName);
	}

	/** Get Category Name.
		@return Name of the Category
	  */
	public String getCategoryName () 
	{
		return (String)get_Value(COLUMNNAME_CategoryName);
	}

	/** Set Document required specific to country.
		@param DS_ShipmentDocuments_ID Document required specific to country	  */
	public void setDS_ShipmentDocuments_ID (int DS_ShipmentDocuments_ID)
	{
		if (DS_ShipmentDocuments_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ShipmentDocuments_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ShipmentDocuments_ID, Integer.valueOf(DS_ShipmentDocuments_ID));
	}

	/** Get Document required specific to country.
		@return Document required specific to country	  */
	public int getDS_ShipmentDocuments_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ShipmentDocuments_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_ShipmentDocuments_UU.
		@param DS_ShipmentDocuments_UU DS_ShipmentDocuments_UU	  */
	public void setDS_ShipmentDocuments_UU (String DS_ShipmentDocuments_UU)
	{
		set_Value (COLUMNNAME_DS_ShipmentDocuments_UU, DS_ShipmentDocuments_UU);
	}

	/** Get DS_ShipmentDocuments_UU.
		@return DS_ShipmentDocuments_UU	  */
	public String getDS_ShipmentDocuments_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_ShipmentDocuments_UU);
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