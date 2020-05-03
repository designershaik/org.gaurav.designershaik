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

/** Generated Model for DS_EmailContacts
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_EmailContacts extends PO implements I_DS_EmailContacts, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190310L;

    /** Standard Constructor */
    public X_DS_EmailContacts (Properties ctx, int DS_EmailContacts_ID, String trxName)
    {
      super (ctx, DS_EmailContacts_ID, trxName);
      /** if (DS_EmailContacts_ID == 0)
        {
			setDS_EmailContacts_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_EmailContacts (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_EmailContacts[")
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
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	/** Set Email Contacts.
		@param DS_EmailContacts_ID Email Contacts	  */
	public void setDS_EmailContacts_ID (int DS_EmailContacts_ID)
	{
		if (DS_EmailContacts_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_EmailContacts_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_EmailContacts_ID, Integer.valueOf(DS_EmailContacts_ID));
	}

	/** Get Email Contacts.
		@return Email Contacts	  */
	public int getDS_EmailContacts_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_EmailContacts_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_EmailContacts_UU.
		@param DS_EmailContacts_UU DS_EmailContacts_UU	  */
	public void setDS_EmailContacts_UU (String DS_EmailContacts_UU)
	{
		set_Value (COLUMNNAME_DS_EmailContacts_UU, DS_EmailContacts_UU);
	}

	/** Get DS_EmailContacts_UU.
		@return DS_EmailContacts_UU	  */
	public String getDS_EmailContacts_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_EmailContacts_UU);
	}

	/** Set Is Dunning Email.
		@param DS_IsDunningEmail Is Dunning Email	  */
	public void setDS_IsDunningEmail (boolean DS_IsDunningEmail)
	{
		set_Value (COLUMNNAME_DS_IsDunningEmail, Boolean.valueOf(DS_IsDunningEmail));
	}

	/** Get Is Dunning Email.
		@return Is Dunning Email	  */
	public boolean isDS_IsDunningEmail () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsDunningEmail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Invoice Contact.
		@param DS_IsInvoiceContact Invoice Contact	  */
	public void setDS_IsInvoiceContact (boolean DS_IsInvoiceContact)
	{
		throw new IllegalArgumentException ("DS_IsInvoiceContact is virtual column");	}

	/** Get Invoice Contact.
		@return Invoice Contact	  */
	public boolean isDS_IsInvoiceContact () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsInvoiceContact);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_ValueNoCheck (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}