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

/** Generated Model for DS_CustomerVisits
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_CustomerVisits extends PO implements I_DS_CustomerVisits, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190130L;

    /** Standard Constructor */
    public X_DS_CustomerVisits (Properties ctx, int DS_CustomerVisits_ID, String trxName)
    {
      super (ctx, DS_CustomerVisits_ID, trxName);
      /** if (DS_CustomerVisits_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_DS_CustomerVisits (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_CustomerVisits[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
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
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
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

	public org.compiere.model.I_C_BPartner getDS_Customer() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getDS_Customer_ID(), get_TrxName());	}

	/** Set Customer.
		@param DS_Customer_ID Customer	  */
	public void setDS_Customer_ID (int DS_Customer_ID)
	{
		if (DS_Customer_ID < 1) 
			set_Value (COLUMNNAME_DS_Customer_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Customer_ID, Integer.valueOf(DS_Customer_ID));
	}

	/** Get Customer.
		@return Customer	  */
	public int getDS_Customer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Customer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Customer Visits.
		@param DS_CustomerVisits_ID Customer Visits	  */
	public void setDS_CustomerVisits_ID (int DS_CustomerVisits_ID)
	{
		if (DS_CustomerVisits_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_CustomerVisits_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_CustomerVisits_ID, Integer.valueOf(DS_CustomerVisits_ID));
	}

	/** Get Customer Visits.
		@return Customer Visits	  */
	public int getDS_CustomerVisits_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_CustomerVisits_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_CustomerVisits_UU.
		@param DS_CustomerVisits_UU DS_CustomerVisits_UU	  */
	public void setDS_CustomerVisits_UU (String DS_CustomerVisits_UU)
	{
		set_Value (COLUMNNAME_DS_CustomerVisits_UU, DS_CustomerVisits_UU);
	}

	/** Get DS_CustomerVisits_UU.
		@return DS_CustomerVisits_UU	  */
	public String getDS_CustomerVisits_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_CustomerVisits_UU);
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
			set_Value (COLUMNNAME_DS_Delivery_Trips_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Delivery_Trips_ID, Integer.valueOf(DS_Delivery_Trips_ID));
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

	public org.compiere.model.I_AD_User getDS_Lead() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getDS_Lead_ID(), get_TrxName());	}

	/** Set Lead.
		@param DS_Lead_ID 
		Lead
	  */
	public void setDS_Lead_ID (int DS_Lead_ID)
	{
		if (DS_Lead_ID < 1) 
			set_Value (COLUMNNAME_DS_Lead_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Lead_ID, Integer.valueOf(DS_Lead_ID));
	}

	/** Get Lead.
		@return Lead
	  */
	public int getDS_Lead_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Lead_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}