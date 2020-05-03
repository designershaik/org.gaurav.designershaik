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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DS_Aramex_ShipmentTracking
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Aramex_ShipmentTracking extends PO implements I_DS_Aramex_ShipmentTracking, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_Aramex_ShipmentTracking (Properties ctx, int DS_Aramex_ShipmentTracking_ID, String trxName)
    {
      super (ctx, DS_Aramex_ShipmentTracking_ID, trxName);
      /** if (DS_Aramex_ShipmentTracking_ID == 0)
        {
			setDS_Aramex_ShipmentTracking_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Aramex_ShipmentTracking (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Aramex_ShipmentTracking[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set City.
		@param City 
		Identifies a City
	  */
	public void setCity (String City)
	{
		set_ValueNoCheck (COLUMNNAME_City, City);
	}

	/** Get City.
		@return Identifies a City
	  */
	public String getCity () 
	{
		return (String)get_Value(COLUMNNAME_City);
	}

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Country.
		@param CountryName 
		Country Name
	  */
	public void setCountryName (String CountryName)
	{
		set_ValueNoCheck (COLUMNNAME_CountryName, CountryName);
	}

	/** Get Country.
		@return Country Name
	  */
	public String getCountryName () 
	{
		return (String)get_Value(COLUMNNAME_CountryName);
	}

	/** Set Date Delivered.
		@param DateDelivered 
		Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered)
	{
		set_ValueNoCheck (COLUMNNAME_DateDelivered, DateDelivered);
	}

	/** Get Date Delivered.
		@return Date when the product was delivered
	  */
	public Timestamp getDateDelivered () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDelivered);
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

	/** Set Shipment tracking.
		@param DS_Aramex_ShipmentTracking_ID Shipment tracking	  */
	public void setDS_Aramex_ShipmentTracking_ID (int DS_Aramex_ShipmentTracking_ID)
	{
		if (DS_Aramex_ShipmentTracking_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Aramex_ShipmentTracking_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Aramex_ShipmentTracking_ID, Integer.valueOf(DS_Aramex_ShipmentTracking_ID));
	}

	/** Get Shipment tracking.
		@return Shipment tracking	  */
	public int getDS_Aramex_ShipmentTracking_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Aramex_ShipmentTracking_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Aramex_ShipmentTracking_UU.
		@param DS_Aramex_ShipmentTracking_UU DS_Aramex_ShipmentTracking_UU	  */
	public void setDS_Aramex_ShipmentTracking_UU (String DS_Aramex_ShipmentTracking_UU)
	{
		set_Value (COLUMNNAME_DS_Aramex_ShipmentTracking_UU, DS_Aramex_ShipmentTracking_UU);
	}

	/** Get DS_Aramex_ShipmentTracking_UU.
		@return DS_Aramex_ShipmentTracking_UU	  */
	public String getDS_Aramex_ShipmentTracking_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Aramex_ShipmentTracking_UU);
	}

	/** Set Problem Code.
		@param DS_ProblemCode Problem Code	  */
	public void setDS_ProblemCode (String DS_ProblemCode)
	{
		set_Value (COLUMNNAME_DS_ProblemCode, DS_ProblemCode);
	}

	/** Get Problem Code.
		@return Problem Code	  */
	public String getDS_ProblemCode () 
	{
		return (String)get_Value(COLUMNNAME_DS_ProblemCode);
	}

	/** Set Result Key.
		@param DS_ResultKey Result Key	  */
	public void setDS_ResultKey (String DS_ResultKey)
	{
		set_Value (COLUMNNAME_DS_ResultKey, DS_ResultKey);
	}

	/** Get Result Key.
		@return Result Key	  */
	public String getDS_ResultKey () 
	{
		return (String)get_Value(COLUMNNAME_DS_ResultKey);
	}

	/** Set Udpated Date & Time.
		@param DS_Updated Udpated Date & Time	  */
	public void setDS_Updated (String DS_Updated)
	{
		set_Value (COLUMNNAME_DS_Updated, DS_Updated);
	}

	/** Get Udpated Date & Time.
		@return Udpated Date & Time	  */
	public String getDS_Updated () 
	{
		return (String)get_Value(COLUMNNAME_DS_Updated);
	}

	/** Set Updated Code.
		@param DS_UpdatedCode Updated Code	  */
	public void setDS_UpdatedCode (String DS_UpdatedCode)
	{
		set_Value (COLUMNNAME_DS_UpdatedCode, DS_UpdatedCode);
	}

	/** Get Updated Code.
		@return Updated Code	  */
	public String getDS_UpdatedCode () 
	{
		return (String)get_Value(COLUMNNAME_DS_UpdatedCode);
	}

	/** Set Delivered.
		@param IsDelivered Delivered	  */
	public void setIsDelivered (boolean IsDelivered)
	{
		set_ValueNoCheck (COLUMNNAME_IsDelivered, Boolean.valueOf(IsDelivered));
	}

	/** Get Delivered.
		@return Delivered	  */
	public boolean isDelivered () 
	{
		Object oo = get_Value(COLUMNNAME_IsDelivered);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Location comment.
		@param LocationComment 
		Additional comments or remarks concerning the location
	  */
	public void setLocationComment (String LocationComment)
	{
		set_Value (COLUMNNAME_LocationComment, LocationComment);
	}

	/** Get Location comment.
		@return Additional comments or remarks concerning the location
	  */
	public String getLocationComment () 
	{
		return (String)get_Value(COLUMNNAME_LocationComment);
	}

	public org.compiere.model.I_M_InOut getM_InOut() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOut)MTable.get(getCtx(), org.compiere.model.I_M_InOut.Table_Name)
			.getPO(getM_InOut_ID(), get_TrxName());	}

	/** Set Shipment/Receipt.
		@param M_InOut_ID 
		Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID)
	{
		if (M_InOut_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_InOut_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_InOut_ID, Integer.valueOf(M_InOut_ID));
	}

	/** Get Shipment/Receipt.
		@return Material Shipment Document
	  */
	public int getM_InOut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Tracking No.
		@param TrackingNo 
		Number to track the shipment
	  */
	public void setTrackingNo (String TrackingNo)
	{
		set_Value (COLUMNNAME_TrackingNo, TrackingNo);
	}

	/** Get Tracking No.
		@return Number to track the shipment
	  */
	public String getTrackingNo () 
	{
		return (String)get_Value(COLUMNNAME_TrackingNo);
	}
}