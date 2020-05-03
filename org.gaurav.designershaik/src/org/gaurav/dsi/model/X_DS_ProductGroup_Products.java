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

/** Generated Model for DS_ProductGroup_Products
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_ProductGroup_Products extends PO implements I_DS_ProductGroup_Products, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180408L;

    /** Standard Constructor */
    public X_DS_ProductGroup_Products (Properties ctx, int DS_ProductGroup_Products_ID, String trxName)
    {
      super (ctx, DS_ProductGroup_Products_ID, trxName);
      /** if (DS_ProductGroup_Products_ID == 0)
        {
			setDS_ProductGroup_Products_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_ProductGroup_Products (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_ProductGroup_Products[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date From.
		@param DateFrom 
		Starting date for a range
	  */
	public void setDateFrom (Timestamp DateFrom)
	{
		set_Value (COLUMNNAME_DateFrom, DateFrom);
	}

	/** Get Date From.
		@return Starting date for a range
	  */
	public Timestamp getDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateFrom);
	}

	/** Set Date To.
		@param DateTo 
		End date of a date range
	  */
	public void setDateTo (Timestamp DateTo)
	{
		set_Value (COLUMNNAME_DateTo, DateTo);
	}

	/** Get Date To.
		@return End date of a date range
	  */
	public Timestamp getDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateTo);
	}

	public I_DS_ProductGroup getDS_ProductGroup() throws RuntimeException
    {
		return (I_DS_ProductGroup)MTable.get(getCtx(), I_DS_ProductGroup.Table_Name)
			.getPO(getDS_ProductGroup_ID(), get_TrxName());	}

	/** Set Product Group.
		@param DS_ProductGroup_ID Product Group	  */
	public void setDS_ProductGroup_ID (int DS_ProductGroup_ID)
	{
		if (DS_ProductGroup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ProductGroup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ProductGroup_ID, Integer.valueOf(DS_ProductGroup_ID));
	}

	/** Get Product Group.
		@return Product Group	  */
	public int getDS_ProductGroup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ProductGroup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Products.
		@param DS_ProductGroup_Products_ID Products	  */
	public void setDS_ProductGroup_Products_ID (int DS_ProductGroup_Products_ID)
	{
		if (DS_ProductGroup_Products_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ProductGroup_Products_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ProductGroup_Products_ID, Integer.valueOf(DS_ProductGroup_Products_ID));
	}

	/** Get Products.
		@return Products	  */
	public int getDS_ProductGroup_Products_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ProductGroup_Products_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_ProductGroup_Products_UU.
		@param DS_ProductGroup_Products_UU DS_ProductGroup_Products_UU	  */
	public void setDS_ProductGroup_Products_UU (String DS_ProductGroup_Products_UU)
	{
		set_Value (COLUMNNAME_DS_ProductGroup_Products_UU, DS_ProductGroup_Products_UU);
	}

	/** Get DS_ProductGroup_Products_UU.
		@return DS_ProductGroup_Products_UU	  */
	public String getDS_ProductGroup_Products_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_ProductGroup_Products_UU);
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}