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

/** Generated Model for DS_ContactMaster_Location
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_ContactMaster_Location extends PO implements I_DS_ContactMaster_Location, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190310L;

    /** Standard Constructor */
    public X_DS_ContactMaster_Location (Properties ctx, int DS_ContactMaster_Location_ID, String trxName)
    {
      super (ctx, DS_ContactMaster_Location_ID, trxName);
      /** if (DS_ContactMaster_Location_ID == 0)
        {
			setC_Location_ID (0);
			setDS_ContactMaster_ID (0);
			setDS_ContactMaster_Location_ID (0);
			setName (null);
// .
        } */
    }

    /** Load Constructor */
    public X_DS_ContactMaster_Location (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_ContactMaster_Location[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	public org.compiere.model.I_C_SalesRegion getC_SalesRegion() throws RuntimeException
    {
		return (org.compiere.model.I_C_SalesRegion)MTable.get(getCtx(), org.compiere.model.I_C_SalesRegion.Table_Name)
			.getPO(getC_SalesRegion_ID(), get_TrxName());	}

	/** Set Sales Region.
		@param C_SalesRegion_ID 
		Sales coverage region
	  */
	public void setC_SalesRegion_ID (int C_SalesRegion_ID)
	{
		if (C_SalesRegion_ID < 1) 
			set_Value (COLUMNNAME_C_SalesRegion_ID, null);
		else 
			set_Value (COLUMNNAME_C_SalesRegion_ID, Integer.valueOf(C_SalesRegion_ID));
	}

	/** Get Sales Region.
		@return Sales coverage region
	  */
	public int getC_SalesRegion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_SalesRegion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DS_ContactMaster getDS_ContactMaster() throws RuntimeException
    {
		return (I_DS_ContactMaster)MTable.get(getCtx(), I_DS_ContactMaster.Table_Name)
			.getPO(getDS_ContactMaster_ID(), get_TrxName());	}

	/** Set Contact Master.
		@param DS_ContactMaster_ID Contact Master	  */
	public void setDS_ContactMaster_ID (int DS_ContactMaster_ID)
	{
		if (DS_ContactMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ContactMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ContactMaster_ID, Integer.valueOf(DS_ContactMaster_ID));
	}

	/** Get Contact Master.
		@return Contact Master	  */
	public int getDS_ContactMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ContactMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Contact Master Location.
		@param DS_ContactMaster_Location_ID Contact Master Location	  */
	public void setDS_ContactMaster_Location_ID (int DS_ContactMaster_Location_ID)
	{
		if (DS_ContactMaster_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ContactMaster_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ContactMaster_Location_ID, Integer.valueOf(DS_ContactMaster_Location_ID));
	}

	/** Get Contact Master Location.
		@return Contact Master Location	  */
	public int getDS_ContactMaster_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ContactMaster_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_ContactMaster_Location_UU.
		@param DS_ContactMaster_Location_UU DS_ContactMaster_Location_UU	  */
	public void setDS_ContactMaster_Location_UU (String DS_ContactMaster_Location_UU)
	{
		set_Value (COLUMNNAME_DS_ContactMaster_Location_UU, DS_ContactMaster_Location_UU);
	}

	/** Get DS_ContactMaster_Location_UU.
		@return DS_ContactMaster_Location_UU	  */
	public String getDS_ContactMaster_Location_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_ContactMaster_Location_UU);
	}

	/** Set Extension Phone1.
		@param DS_Phone_Ext1 Extension Phone1	  */
	public void setDS_Phone_Ext1 (String DS_Phone_Ext1)
	{
		set_Value (COLUMNNAME_DS_Phone_Ext1, DS_Phone_Ext1);
	}

	/** Get Extension Phone1.
		@return Extension Phone1	  */
	public String getDS_Phone_Ext1 () 
	{
		return (String)get_Value(COLUMNNAME_DS_Phone_Ext1);
	}

	/** Set Extension Phone2.
		@param DS_Phone_Ext2 Extension Phone2	  */
	public void setDS_Phone_Ext2 (String DS_Phone_Ext2)
	{
		set_Value (COLUMNNAME_DS_Phone_Ext2, DS_Phone_Ext2);
	}

	/** Get Extension Phone2.
		@return Extension Phone2	  */
	public String getDS_Phone_Ext2 () 
	{
		return (String)get_Value(COLUMNNAME_DS_Phone_Ext2);
	}

	/** Set Fax.
		@param Fax 
		Facsimile number
	  */
	public void setFax (String Fax)
	{
		set_Value (COLUMNNAME_Fax, Fax);
	}

	/** Get Fax.
		@return Facsimile number
	  */
	public String getFax () 
	{
		return (String)get_Value(COLUMNNAME_Fax);
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

	/** Set Landline.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Landline.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set Mobile No./LandLine 2.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get Mobile No./LandLine 2.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}
}