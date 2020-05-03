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

/** Generated Model for DS_RouteMaster
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_RouteMaster extends PO implements I_DS_RouteMaster, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_RouteMaster (Properties ctx, int DS_RouteMaster_ID, String trxName)
    {
      super (ctx, DS_RouteMaster_ID, trxName);
      /** if (DS_RouteMaster_ID == 0)
        {
			setDS_RouteMaster_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_RouteMaster (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_RouteMaster[")
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

	/** Set From Country.
		@param DS_FromCountry_ID From Country	  */
	public void setDS_FromCountry_ID (int DS_FromCountry_ID)
	{
		if (DS_FromCountry_ID < 1) 
			set_Value (COLUMNNAME_DS_FromCountry_ID, null);
		else 
			set_Value (COLUMNNAME_DS_FromCountry_ID, Integer.valueOf(DS_FromCountry_ID));
	}

	/** Get From Country.
		@return From Country	  */
	public int getDS_FromCountry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_FromCountry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Route Code.
		@param DS_RouteCode Route Code	  */
	public void setDS_RouteCode (String DS_RouteCode)
	{
		set_Value (COLUMNNAME_DS_RouteCode, DS_RouteCode);
	}

	/** Get Route Code.
		@return Route Code	  */
	public String getDS_RouteCode () 
	{
		return (String)get_Value(COLUMNNAME_DS_RouteCode);
	}

	/** Set Route Master.
		@param DS_RouteMaster_ID Route Master	  */
	public void setDS_RouteMaster_ID (int DS_RouteMaster_ID)
	{
		if (DS_RouteMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_RouteMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_RouteMaster_ID, Integer.valueOf(DS_RouteMaster_ID));
	}

	/** Get Route Master.
		@return Route Master	  */
	public int getDS_RouteMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RouteMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_RouteMaster_UU.
		@param DS_RouteMaster_UU DS_RouteMaster_UU	  */
	public void setDS_RouteMaster_UU (String DS_RouteMaster_UU)
	{
		set_Value (COLUMNNAME_DS_RouteMaster_UU, DS_RouteMaster_UU);
	}

	/** Get DS_RouteMaster_UU.
		@return DS_RouteMaster_UU	  */
	public String getDS_RouteMaster_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_RouteMaster_UU);
	}

	/** Set To Country.
		@param DS_ToCountry_ID To Country	  */
	public void setDS_ToCountry_ID (int DS_ToCountry_ID)
	{
		if (DS_ToCountry_ID < 1) 
			set_Value (COLUMNNAME_DS_ToCountry_ID, null);
		else 
			set_Value (COLUMNNAME_DS_ToCountry_ID, Integer.valueOf(DS_ToCountry_ID));
	}

	/** Get To Country.
		@return To Country	  */
	public int getDS_ToCountry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ToCountry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}