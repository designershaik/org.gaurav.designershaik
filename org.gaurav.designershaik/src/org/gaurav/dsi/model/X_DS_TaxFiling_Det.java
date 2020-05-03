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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DS_TaxFiling_Det
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_TaxFiling_Det extends PO implements I_DS_TaxFiling_Det, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200119L;

    /** Standard Constructor */
    public X_DS_TaxFiling_Det (Properties ctx, int DS_TaxFiling_Det_ID, String trxName)
    {
      super (ctx, DS_TaxFiling_Det_ID, trxName);
      /** if (DS_TaxFiling_Det_ID == 0)
        {
			setC_Tax_ID (0);
			setDS_TaxFiling_Det_ID (0);
			setDS_TaxFiling_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_TaxFiling_Det (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_TaxFiling_Det[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException
    {
		return (org.compiere.model.I_C_Country)MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_Name)
			.getPO(getC_Country_ID(), get_TrxName());	}

	/** Set Country.
		@param C_Country_ID 
		Country 
	  */
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1) 
			set_Value (COLUMNNAME_C_Country_ID, null);
		else 
			set_Value (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_Value (COLUMNNAME_C_Tax_ID, null);
		else 
			set_Value (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
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

	/** Set Adjustments/ apportionments.
		@param DS_TaxAdjustments Adjustments/ apportionments	  */
	public void setDS_TaxAdjustments (BigDecimal DS_TaxAdjustments)
	{
		set_Value (COLUMNNAME_DS_TaxAdjustments, DS_TaxAdjustments);
	}

	/** Get Adjustments/ apportionments.
		@return Adjustments/ apportionments	  */
	public BigDecimal getDS_TaxAdjustments () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_TaxAdjustments);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax Filing Details.
		@param DS_TaxFiling_Det_ID Tax Filing Details	  */
	public void setDS_TaxFiling_Det_ID (int DS_TaxFiling_Det_ID)
	{
		if (DS_TaxFiling_Det_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_TaxFiling_Det_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_TaxFiling_Det_ID, Integer.valueOf(DS_TaxFiling_Det_ID));
	}

	/** Get Tax Filing Details.
		@return Tax Filing Details	  */
	public int getDS_TaxFiling_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TaxFiling_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_TaxFiling_Det_UU.
		@param DS_TaxFiling_Det_UU DS_TaxFiling_Det_UU	  */
	public void setDS_TaxFiling_Det_UU (String DS_TaxFiling_Det_UU)
	{
		set_Value (COLUMNNAME_DS_TaxFiling_Det_UU, DS_TaxFiling_Det_UU);
	}

	/** Get DS_TaxFiling_Det_UU.
		@return DS_TaxFiling_Det_UU	  */
	public String getDS_TaxFiling_Det_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_TaxFiling_Det_UU);
	}

	public I_DS_TaxFiling getDS_TaxFiling() throws RuntimeException
    {
		return (I_DS_TaxFiling)MTable.get(getCtx(), I_DS_TaxFiling.Table_Name)
			.getPO(getDS_TaxFiling_ID(), get_TrxName());	}

	/** Set Tax Filing.
		@param DS_TaxFiling_ID Tax Filing	  */
	public void setDS_TaxFiling_ID (int DS_TaxFiling_ID)
	{
		if (DS_TaxFiling_ID < 1) 
			set_Value (COLUMNNAME_DS_TaxFiling_ID, null);
		else 
			set_Value (COLUMNNAME_DS_TaxFiling_ID, Integer.valueOf(DS_TaxFiling_ID));
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

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
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

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SO/PO Type.
		@param SOPOType 
		Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public void setSOPOType (String SOPOType)
	{
		throw new IllegalArgumentException ("SOPOType is virtual column");	}

	/** Get SO/PO Type.
		@return Sales Tax applies to sales situations, Purchase Tax to purchase situations
	  */
	public String getSOPOType () 
	{
		return (String)get_Value(COLUMNNAME_SOPOType);
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_ValueNoCheck (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax base Amount.
		@param TaxBaseAmt 
		Base for calculating the tax amount
	  */
	public void setTaxBaseAmt (BigDecimal TaxBaseAmt)
	{
		set_ValueNoCheck (COLUMNNAME_TaxBaseAmt, TaxBaseAmt);
	}

	/** Get Tax base Amount.
		@return Base for calculating the tax amount
	  */
	public BigDecimal getTaxBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxBaseAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}