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

/** Generated Model for DS_SubLocation
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_SubLocation extends PO implements I_DS_SubLocation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190310L;

    /** Standard Constructor */
    public X_DS_SubLocation (Properties ctx, int DS_SubLocation_ID, String trxName)
    {
      super (ctx, DS_SubLocation_ID, trxName);
      /** if (DS_SubLocation_ID == 0)
        {
			setDS_SubLocation_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_SubLocation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_SubLocation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_DS_Location getDS_Location() throws RuntimeException
    {
		return (I_DS_Location)MTable.get(getCtx(), I_DS_Location.Table_Name)
			.getPO(getDS_Location_ID(), get_TrxName());	}

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

	/** Set Sub Location.
		@param DS_SubLocation_ID Sub Location	  */
	public void setDS_SubLocation_ID (int DS_SubLocation_ID)
	{
		if (DS_SubLocation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_SubLocation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_SubLocation_ID, Integer.valueOf(DS_SubLocation_ID));
	}

	/** Get Sub Location.
		@return Sub Location	  */
	public int getDS_SubLocation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_SubLocation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_SubLocation_UU.
		@param DS_SubLocation_UU DS_SubLocation_UU	  */
	public void setDS_SubLocation_UU (String DS_SubLocation_UU)
	{
		set_Value (COLUMNNAME_DS_SubLocation_UU, DS_SubLocation_UU);
	}

	/** Get DS_SubLocation_UU.
		@return DS_SubLocation_UU	  */
	public String getDS_SubLocation_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_SubLocation_UU);
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