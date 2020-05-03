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

/** Generated Model for DS_ProductPriceSerialNo
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_ProductPriceSerialNo extends PO implements I_DS_ProductPriceSerialNo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180610L;

    /** Standard Constructor */
    public X_DS_ProductPriceSerialNo (Properties ctx, int DS_ProductPriceSerialNo_ID, String trxName)
    {
      super (ctx, DS_ProductPriceSerialNo_ID, trxName);
      /** if (DS_ProductPriceSerialNo_ID == 0)
        {
			setDS_ProductPriceSerialNo_ID (0);
			setDS_SerNoFrom (0);
			setM_PriceList_Version_ID (0);
			setM_Product_ID (0);
			setPriceLimit (Env.ZERO);
			setPriceList (Env.ZERO);
			setPriceStd (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_DS_ProductPriceSerialNo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_ProductPriceSerialNo[")
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

	/** Set Product Break(Serial No).
		@param DS_ProductPriceSerialNo_ID Product Break(Serial No)	  */
	public void setDS_ProductPriceSerialNo_ID (int DS_ProductPriceSerialNo_ID)
	{
		if (DS_ProductPriceSerialNo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ProductPriceSerialNo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ProductPriceSerialNo_ID, Integer.valueOf(DS_ProductPriceSerialNo_ID));
	}

	/** Get Product Break(Serial No).
		@return Product Break(Serial No)	  */
	public int getDS_ProductPriceSerialNo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ProductPriceSerialNo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_ProductPriceSerialNo_UU.
		@param DS_ProductPriceSerialNo_UU DS_ProductPriceSerialNo_UU	  */
	public void setDS_ProductPriceSerialNo_UU (String DS_ProductPriceSerialNo_UU)
	{
		set_Value (COLUMNNAME_DS_ProductPriceSerialNo_UU, DS_ProductPriceSerialNo_UU);
	}

	/** Get DS_ProductPriceSerialNo_UU.
		@return DS_ProductPriceSerialNo_UU	  */
	public String getDS_ProductPriceSerialNo_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_ProductPriceSerialNo_UU);
	}

	/** Set Serial Number From.
		@param DS_SerNoFrom 
		Serial Number From
	  */
	public void setDS_SerNoFrom (int DS_SerNoFrom)
	{
		set_Value (COLUMNNAME_DS_SerNoFrom, Integer.valueOf(DS_SerNoFrom));
	}

	/** Get Serial Number From.
		@return Serial Number From
	  */
	public int getDS_SerNoFrom () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_SerNoFrom);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Serial Number To.
		@param DS_SerNoTo 
		Serial Number To
	  */
	public void setDS_SerNoTo (int DS_SerNoTo)
	{
		set_Value (COLUMNNAME_DS_SerNoTo, Integer.valueOf(DS_SerNoTo));
	}

	/** Get Serial Number To.
		@return Serial Number To
	  */
	public int getDS_SerNoTo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_SerNoTo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_PriceList_Version getM_PriceList_Version() throws RuntimeException
    {
		return (org.compiere.model.I_M_PriceList_Version)MTable.get(getCtx(), org.compiere.model.I_M_PriceList_Version.Table_Name)
			.getPO(getM_PriceList_Version_ID(), get_TrxName());	}

	/** Set Price List Version.
		@param M_PriceList_Version_ID 
		Identifies a unique instance of a Price List
	  */
	public void setM_PriceList_Version_ID (int M_PriceList_Version_ID)
	{
		if (M_PriceList_Version_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_PriceList_Version_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_PriceList_Version_ID, Integer.valueOf(M_PriceList_Version_ID));
	}

	/** Get Price List Version.
		@return Identifies a unique instance of a Price List
	  */
	public int getM_PriceList_Version_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_PriceList_Version_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Limit Price.
		@param PriceLimit 
		Lowest price for a product
	  */
	public void setPriceLimit (BigDecimal PriceLimit)
	{
		set_Value (COLUMNNAME_PriceLimit, PriceLimit);
	}

	/** Get Limit Price.
		@return Lowest price for a product
	  */
	public BigDecimal getPriceLimit () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceLimit);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set List Price.
		@param PriceList 
		List Price
	  */
	public void setPriceList (BigDecimal PriceList)
	{
		set_Value (COLUMNNAME_PriceList, PriceList);
	}

	/** Get List Price.
		@return List Price
	  */
	public BigDecimal getPriceList () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceList);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Standard Price.
		@param PriceStd 
		Standard Price
	  */
	public void setPriceStd (BigDecimal PriceStd)
	{
		set_Value (COLUMNNAME_PriceStd, PriceStd);
	}

	/** Get Standard Price.
		@return Standard Price
	  */
	public BigDecimal getPriceStd () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PriceStd);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}