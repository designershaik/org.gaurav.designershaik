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

/** Generated Model for DS_RouteReturn
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_RouteReturn extends PO implements I_DS_RouteReturn, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_RouteReturn (Properties ctx, int DS_RouteReturn_ID, String trxName)
    {
      super (ctx, DS_RouteReturn_ID, trxName);
      /** if (DS_RouteReturn_ID == 0)
        {
			setDS_RouteMaster_ID (0);
			setDS_RouteReturn_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_RouteReturn (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_RouteReturn[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_City getC_City() throws RuntimeException
    {
		return (org.compiere.model.I_C_City)MTable.get(getCtx(), org.compiere.model.I_C_City.Table_Name)
			.getPO(getC_City_ID(), get_TrxName());	}

	/** Set City.
		@param C_City_ID 
		City
	  */
	public void setC_City_ID (int C_City_ID)
	{
		if (C_City_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_City_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_City_ID, Integer.valueOf(C_City_ID));
	}

	/** Get City.
		@return City
	  */
	public int getC_City_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_City_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public I_DS_RouteMaster getDS_RouteMaster() throws RuntimeException
    {
		return (I_DS_RouteMaster)MTable.get(getCtx(), I_DS_RouteMaster.Table_Name)
			.getPO(getDS_RouteMaster_ID(), get_TrxName());	}

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

	/** Set Route Return.
		@param DS_RouteReturn_ID Route Return	  */
	public void setDS_RouteReturn_ID (int DS_RouteReturn_ID)
	{
		if (DS_RouteReturn_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_RouteReturn_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_RouteReturn_ID, Integer.valueOf(DS_RouteReturn_ID));
	}

	/** Get Route Return.
		@return Route Return	  */
	public int getDS_RouteReturn_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RouteReturn_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_RouteReturn_UU.
		@param DS_RouteReturn_UU DS_RouteReturn_UU	  */
	public void setDS_RouteReturn_UU (String DS_RouteReturn_UU)
	{
		set_Value (COLUMNNAME_DS_RouteReturn_UU, DS_RouteReturn_UU);
	}

	/** Get DS_RouteReturn_UU.
		@return DS_RouteReturn_UU	  */
	public String getDS_RouteReturn_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_RouteReturn_UU);
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

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}