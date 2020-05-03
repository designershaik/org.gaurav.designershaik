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

/** Generated Model for DS_TaxFiling
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_TaxFiling extends PO implements I_DS_TaxFiling, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200119L;

    /** Standard Constructor */
    public X_DS_TaxFiling (Properties ctx, int DS_TaxFiling_ID, String trxName)
    {
      super (ctx, DS_TaxFiling_ID, trxName);
      /** if (DS_TaxFiling_ID == 0)
        {
			setDS_TaxFiling_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_TaxFiling (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_TaxFiling[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
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

	/** Set Tax Filing.
		@param DS_TaxFiling_ID Tax Filing	  */
	public void setDS_TaxFiling_ID (int DS_TaxFiling_ID)
	{
		if (DS_TaxFiling_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_TaxFiling_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_TaxFiling_ID, Integer.valueOf(DS_TaxFiling_ID));
	}

	/** Get Tax Filing.
		@return Tax Filing	  */
	public int getDS_TaxFiling_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TaxFiling_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_TaxFiling_UU.
		@param DS_TaxFiling_UU DS_TaxFiling_UU	  */
	public void setDS_TaxFiling_UU (String DS_TaxFiling_UU)
	{
		set_Value (COLUMNNAME_DS_TaxFiling_UU, DS_TaxFiling_UU);
	}

	/** Get DS_TaxFiling_UU.
		@return DS_TaxFiling_UU	  */
	public String getDS_TaxFiling_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_TaxFiling_UU);
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

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** SOPOType AD_Reference_ID=287 */
	public static final int SOPOTYPE_AD_Reference_ID=287;
	/** Both = B */
	public static final String SOPOTYPE_Both = "B";
	/** Sales Tax = S */
	public static final String SOPOTYPE_SalesTax = "S";
	/** Purchase Tax = P */
	public static final String SOPOTYPE_PurchaseTax = "P";
	/** Set SO/PO Type.
		@param SOPOType 
		Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public void setSOPOType (String SOPOType)
	{

		set_Value (COLUMNNAME_SOPOType, SOPOType);
	}

	/** Get SO/PO Type.
		@return Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public String getSOPOType () 
	{
		return (String)get_Value(COLUMNNAME_SOPOType);
	}
}