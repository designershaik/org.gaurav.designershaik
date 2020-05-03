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

/** Generated Model for DS_OrgMaster
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_OrgMaster extends PO implements I_DS_OrgMaster, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_OrgMaster (Properties ctx, int DS_OrgMaster_ID, String trxName)
    {
      super (ctx, DS_OrgMaster_ID, trxName);
      /** if (DS_OrgMaster_ID == 0)
        {
			setDS_OrgMaster_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_OrgMaster (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_OrgMaster[")
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

	/** Set Organization Master.
		@param DS_OrgMaster_ID Organization Master	  */
	public void setDS_OrgMaster_ID (int DS_OrgMaster_ID)
	{
		if (DS_OrgMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_ID, Integer.valueOf(DS_OrgMaster_ID));
	}

	/** Get Organization Master.
		@return Organization Master	  */
	public int getDS_OrgMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_OrgMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_OrgMaster_UU.
		@param DS_OrgMaster_UU DS_OrgMaster_UU	  */
	public void setDS_OrgMaster_UU (String DS_OrgMaster_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_UU, DS_OrgMaster_UU);
	}

	/** Get DS_OrgMaster_UU.
		@return DS_OrgMaster_UU	  */
	public String getDS_OrgMaster_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_OrgMaster_UU);
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

	public I_DS_Tags getDS_Tags() throws RuntimeException
    {
		return (I_DS_Tags)MTable.get(getCtx(), I_DS_Tags.Table_Name)
			.getPO(getDS_Tags_ID(), get_TrxName());	}

	/** Set Tags.
		@param DS_Tags_ID Tags	  */
	public void setDS_Tags_ID (int DS_Tags_ID)
	{
		if (DS_Tags_ID < 1) 
			set_Value (COLUMNNAME_DS_Tags_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Tags_ID, Integer.valueOf(DS_Tags_ID));
	}

	/** Get Tags.
		@return Tags	  */
	public int getDS_Tags_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Tags_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
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

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.idempiere.org
	  */
	public void setURL (String URL)
	{
		set_ValueNoCheck (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.idempiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}

	/** Set WebSite.
		@param WebSite WebSite	  */
	public void setWebSite (String WebSite)
	{
		set_Value (COLUMNNAME_WebSite, WebSite);
	}

	/** Get WebSite.
		@return WebSite	  */
	public String getWebSite () 
	{
		return (String)get_Value(COLUMNNAME_WebSite);
	}
}