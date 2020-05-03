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

/** Generated Model for DS_Delivery_RouteDetails
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Delivery_RouteDetails extends PO implements I_DS_Delivery_RouteDetails, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_Delivery_RouteDetails (Properties ctx, int DS_Delivery_RouteDetails_ID, String trxName)
    {
      super (ctx, DS_Delivery_RouteDetails_ID, trxName);
      /** if (DS_Delivery_RouteDetails_ID == 0)
        {
			setDS_Delivery_RouteDetails_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Delivery_RouteDetails (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Delivery_RouteDetails[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Document Note.
		@param DocumentNote 
		Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote)
	{
		set_Value (COLUMNNAME_DocumentNote, DocumentNote);
	}

	/** Get Document Note.
		@return Additional information for a Document
	  */
	public String getDocumentNote () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNote);
	}

	public I_DS_AgentMaster getDS_AgentMaster() throws RuntimeException
    {
		return (I_DS_AgentMaster)MTable.get(getCtx(), I_DS_AgentMaster.Table_Name)
			.getPO(getDS_AgentMaster_ID(), get_TrxName());	}

	/** Set Agent Master.
		@param DS_AgentMaster_ID Agent Master	  */
	public void setDS_AgentMaster_ID (int DS_AgentMaster_ID)
	{
		if (DS_AgentMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_AgentMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_AgentMaster_ID, Integer.valueOf(DS_AgentMaster_ID));
	}

	/** Get Agent Master.
		@return Agent Master	  */
	public int getDS_AgentMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_AgentMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Route Details.
		@param DS_Delivery_RouteDetails_ID Route Details	  */
	public void setDS_Delivery_RouteDetails_ID (int DS_Delivery_RouteDetails_ID)
	{
		if (DS_Delivery_RouteDetails_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_RouteDetails_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_RouteDetails_ID, Integer.valueOf(DS_Delivery_RouteDetails_ID));
	}

	/** Get Route Details.
		@return Route Details	  */
	public int getDS_Delivery_RouteDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Delivery_RouteDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Delivery_RouteDetails_UU.
		@param DS_Delivery_RouteDetails_UU DS_Delivery_RouteDetails_UU	  */
	public void setDS_Delivery_RouteDetails_UU (String DS_Delivery_RouteDetails_UU)
	{
		set_Value (COLUMNNAME_DS_Delivery_RouteDetails_UU, DS_Delivery_RouteDetails_UU);
	}

	/** Get DS_Delivery_RouteDetails_UU.
		@return DS_Delivery_RouteDetails_UU	  */
	public String getDS_Delivery_RouteDetails_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Delivery_RouteDetails_UU);
	}

	public I_DS_Delivery_Trips getDS_Delivery_Trips() throws RuntimeException
    {
		return (I_DS_Delivery_Trips)MTable.get(getCtx(), I_DS_Delivery_Trips.Table_Name)
			.getPO(getDS_Delivery_Trips_ID(), get_TrxName());	}

	/** Set Delivery trips.
		@param DS_Delivery_Trips_ID Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID)
	{
		if (DS_Delivery_Trips_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_Trips_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_Trips_ID, Integer.valueOf(DS_Delivery_Trips_ID));
	}

	/** Get Delivery trips.
		@return Delivery trips	  */
	public int getDS_Delivery_Trips_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Delivery_Trips_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Return Route.
		@param DS_IsReturnRoute Return Route	  */
	public void setDS_IsReturnRoute (boolean DS_IsReturnRoute)
	{
		set_Value (COLUMNNAME_DS_IsReturnRoute, Boolean.valueOf(DS_IsReturnRoute));
	}

	/** Get Return Route.
		@return Return Route	  */
	public boolean isDS_IsReturnRoute () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsReturnRoute);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Transit Point.
		@param DS_IsTransitPoint Transit Point	  */
	public void setDS_IsTransitPoint (boolean DS_IsTransitPoint)
	{
		set_Value (COLUMNNAME_DS_IsTransitPoint, Boolean.valueOf(DS_IsTransitPoint));
	}

	/** Get Transit Point.
		@return Transit Point	  */
	public boolean isDS_IsTransitPoint () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsTransitPoint);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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