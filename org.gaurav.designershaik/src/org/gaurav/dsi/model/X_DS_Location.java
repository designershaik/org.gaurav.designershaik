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

/** Generated Model for DS_Location
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_Location extends PO implements I_DS_Location, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190310L;

    /** Standard Constructor */
    public X_DS_Location (Properties ctx, int DS_Location_ID, String trxName)
    {
      super (ctx, DS_Location_ID, trxName);
      /** if (DS_Location_ID == 0)
        {
			setDS_Location_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_Location (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Location[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Location getC_Location() throws RuntimeException
    {
		return (I_C_Location)MTable.get(getCtx(), I_C_Location.Table_Name)
			.getPO(getC_Location_ID(), get_TrxName());	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Location.
		@param DS_Location_ID Location	  */
	public void setDS_Location_ID (int DS_Location_ID)
	{
		if (DS_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Location_ID, Integer.valueOf(DS_Location_ID));
	}

	/** Get Location.
		@return Location	  */
	public int getDS_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Location_UU.
		@param DS_Location_UU DS_Location_UU	  */
	public void setDS_Location_UU (String DS_Location_UU)
	{
		set_Value (COLUMNNAME_DS_Location_UU, DS_Location_UU);
	}

	/** Get DS_Location_UU.
		@return DS_Location_UU	  */
	public String getDS_Location_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Location_UU);
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