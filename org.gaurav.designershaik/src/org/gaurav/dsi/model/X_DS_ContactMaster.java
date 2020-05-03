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
import org.compiere.util.KeyNamePair;

/** Generated Model for DS_ContactMaster
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_ContactMaster extends PO implements I_DS_ContactMaster, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190310L;

    /** Standard Constructor */
    public X_DS_ContactMaster (Properties ctx, int DS_ContactMaster_ID, String trxName)
    {
      super (ctx, DS_ContactMaster_ID, trxName);
      /** if (DS_ContactMaster_ID == 0)
        {
			setDS_ContactMaster_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_ContactMaster (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_ContactMaster[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account EMail.
		@param A_EMail 
		Email Address
	  */
	public void setA_EMail (String A_EMail)
	{
		set_Value (COLUMNNAME_A_EMail, A_EMail);
	}

	/** Get Account EMail.
		@return Email Address
	  */
	public String getA_EMail () 
	{
		return (String)get_Value(COLUMNNAME_A_EMail);
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
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** Set Birthday.
		@param Birthday 
		Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
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

	/** Set DS_ContactMaster_UU.
		@param DS_ContactMaster_UU DS_ContactMaster_UU	  */
	public void setDS_ContactMaster_UU (String DS_ContactMaster_UU)
	{
		set_Value (COLUMNNAME_DS_ContactMaster_UU, DS_ContactMaster_UU);
	}

	/** Get DS_ContactMaster_UU.
		@return DS_ContactMaster_UU	  */
	public String getDS_ContactMaster_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_ContactMaster_UU);
	}

	/** Set Individual.
		@param DS_IsPerson Individual	  */
	public void setDS_IsPerson (boolean DS_IsPerson)
	{
		set_Value (COLUMNNAME_DS_IsPerson, Boolean.valueOf(DS_IsPerson));
	}

	/** Get Individual.
		@return Individual	  */
	public boolean isDS_IsPerson () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsPerson);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_DS_OrgMaster getDS_OrgMaster() throws RuntimeException
    {
		return (I_DS_OrgMaster)MTable.get(getCtx(), I_DS_OrgMaster.Table_Name)
			.getPO(getDS_OrgMaster_ID(), get_TrxName());	}

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

	/** Set Greeting.
		@param Greeting 
		For letters, e.g. "Dear {0}" or "Dear Mr. {0}" - At runtime, "{0}" is replaced by the name
	  */
	public void setGreeting (String Greeting)
	{
		set_Value (COLUMNNAME_Greeting, Greeting);
	}

	/** Get Greeting.
		@return For letters, e.g. "Dear {0}" or "Dear Mr. {0}" - At runtime, "{0}" is replaced by the name
	  */
	public String getGreeting () 
	{
		return (String)get_Value(COLUMNNAME_Greeting);
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

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		set_Value (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
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