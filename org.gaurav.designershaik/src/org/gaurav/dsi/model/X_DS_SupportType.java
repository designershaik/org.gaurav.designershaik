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

/** Generated Model for DS_SupportType
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_SupportType extends PO implements I_DS_SupportType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_SupportType (Properties ctx, int DS_SupportType_ID, String trxName)
    {
      super (ctx, DS_SupportType_ID, trxName);
      /** if (DS_SupportType_ID == 0)
        {
			setDS_SupportType_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_SupportType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_SupportType[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Category Name.
		@param CategoryName 
		Name of the Category
	  */
	public void setCategoryName (String CategoryName)
	{
		set_Value (COLUMNNAME_CategoryName, CategoryName);
	}

	/** Get Category Name.
		@return Name of the Category
	  */
	public String getCategoryName () 
	{
		return (String)get_Value(COLUMNNAME_CategoryName);
	}

	/** Set Support type.
		@param DS_SupportType_ID Support type	  */
	public void setDS_SupportType_ID (int DS_SupportType_ID)
	{
		if (DS_SupportType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_SupportType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_SupportType_ID, Integer.valueOf(DS_SupportType_ID));
	}

	/** Get Support type.
		@return Support type	  */
	public int getDS_SupportType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_SupportType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_SupportType_UU.
		@param DS_SupportType_UU DS_SupportType_UU	  */
	public void setDS_SupportType_UU (String DS_SupportType_UU)
	{
		set_Value (COLUMNNAME_DS_SupportType_UU, DS_SupportType_UU);
	}

	/** Get DS_SupportType_UU.
		@return DS_SupportType_UU	  */
	public String getDS_SupportType_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_SupportType_UU);
	}

	/** Set Customer.
		@param IsCustomer 
		Indicates if this Business Partner is a Customer
	  */
	public void setIsCustomer (boolean IsCustomer)
	{
		set_Value (COLUMNNAME_IsCustomer, Boolean.valueOf(IsCustomer));
	}

	/** Get Customer.
		@return Indicates if this Business Partner is a Customer
	  */
	public boolean isCustomer () 
	{
		Object oo = get_Value(COLUMNNAME_IsCustomer);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Employee.
		@param IsEmployee 
		Indicates if  this Business Partner is an employee
	  */
	public void setIsEmployee (boolean IsEmployee)
	{
		set_Value (COLUMNNAME_IsEmployee, Boolean.valueOf(IsEmployee));
	}

	/** Get Employee.
		@return Indicates if  this Business Partner is an employee
	  */
	public boolean isEmployee () 
	{
		Object oo = get_Value(COLUMNNAME_IsEmployee);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Vendor.
		@param IsVendor 
		Indicates if this Business Partner is a Vendor
	  */
	public void setIsVendor (boolean IsVendor)
	{
		set_Value (COLUMNNAME_IsVendor, Boolean.valueOf(IsVendor));
	}

	/** Get Vendor.
		@return Indicates if this Business Partner is a Vendor
	  */
	public boolean isVendor () 
	{
		Object oo = get_Value(COLUMNNAME_IsVendor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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