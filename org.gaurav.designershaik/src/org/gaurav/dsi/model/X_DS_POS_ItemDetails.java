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

/** Generated Model for DS_POS_ItemDetails
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_POS_ItemDetails extends PO implements I_DS_POS_ItemDetails, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190630L;

    /** Standard Constructor */
    public X_DS_POS_ItemDetails (Properties ctx, int DS_POS_ItemDetails_ID, String trxName)
    {
      super (ctx, DS_POS_ItemDetails_ID, trxName);
      /** if (DS_POS_ItemDetails_ID == 0)
        {
			setDS_POS_ItemDetails_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_POS_ItemDetails (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_POS_ItemDetails[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Item Details.
		@param DS_POS_ItemDetails_ID Item Details	  */
	public void setDS_POS_ItemDetails_ID (int DS_POS_ItemDetails_ID)
	{
		if (DS_POS_ItemDetails_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_POS_ItemDetails_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_POS_ItemDetails_ID, Integer.valueOf(DS_POS_ItemDetails_ID));
	}

	/** Get Item Details.
		@return Item Details	  */
	public int getDS_POS_ItemDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_POS_ItemDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_POS_ItemDetails_UU.
		@param DS_POS_ItemDetails_UU DS_POS_ItemDetails_UU	  */
	public void setDS_POS_ItemDetails_UU (String DS_POS_ItemDetails_UU)
	{
		set_Value (COLUMNNAME_DS_POS_ItemDetails_UU, DS_POS_ItemDetails_UU);
	}

	/** Get DS_POS_ItemDetails_UU.
		@return DS_POS_ItemDetails_UU	  */
	public String getDS_POS_ItemDetails_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_POS_ItemDetails_UU);
	}

	public I_DS_POSHeader getDS_POSHeader() throws RuntimeException
    {
		return (I_DS_POSHeader)MTable.get(getCtx(), I_DS_POSHeader.Table_Name)
			.getPO(getDS_POSHeader_ID(), get_TrxName());	}

	/** Set POS Header.
		@param DS_POSHeader_ID POS Header	  */
	public void setDS_POSHeader_ID (int DS_POSHeader_ID)
	{
		if (DS_POSHeader_ID < 1) 
			set_Value (COLUMNNAME_DS_POSHeader_ID, null);
		else 
			set_Value (COLUMNNAME_DS_POSHeader_ID, Integer.valueOf(DS_POSHeader_ID));
	}

	/** Get POS Header.
		@return POS Header	  */
	public int getDS_POSHeader_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_POSHeader_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line Amount.
		@param LineNetAmt 
		Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		set_Value (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Line Total.
		@param LineTotalAmt 
		Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt)
	{
		set_Value (COLUMNNAME_LineTotalAmt, LineTotalAmt);
	}

	/** Get Line Total.
		@return Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineTotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Price.
		@param Price 
		Price
	  */
	public void setPrice (BigDecimal Price)
	{
		set_Value (COLUMNNAME_Price, Price);
	}

	/** Get Price.
		@return Price
	  */
	public BigDecimal getPrice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Ordered Quantity.
		@param QtyOrdered 
		Ordered Quantity
	  */
	public void setQtyOrdered (BigDecimal QtyOrdered)
	{
		set_Value (COLUMNNAME_QtyOrdered, QtyOrdered);
	}

	/** Get Ordered Quantity.
		@return Ordered Quantity
	  */
	public BigDecimal getQtyOrdered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyOrdered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_Value (COLUMNNAME_TaxAmt, TaxAmt);
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
}