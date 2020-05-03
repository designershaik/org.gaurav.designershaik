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

/** Generated Model for DS_InventoryAging
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_InventoryAging extends PO implements I_DS_InventoryAging, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_InventoryAging (Properties ctx, int DS_InventoryAging_ID, String trxName)
    {
      super (ctx, DS_InventoryAging_ID, trxName);
      /** if (DS_InventoryAging_ID == 0)
        {
			setDS_InventoryAging_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_InventoryAging (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_InventoryAging[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Inventory Amt Less than One Month.
		@param DS_InvAmtBetween0To1Month Inventory Amt Less than One Month	  */
	public void setDS_InvAmtBetween0To1Month (BigDecimal DS_InvAmtBetween0To1Month)
	{
		set_Value (COLUMNNAME_DS_InvAmtBetween0To1Month, DS_InvAmtBetween0To1Month);
	}

	/** Get Inventory Amt Less than One Month.
		@return Inventory Amt Less than One Month	  */
	public BigDecimal getDS_InvAmtBetween0To1Month () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvAmtBetween0To1Month);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Amt between one & Two Month.
		@param DS_InvAmtBetween1To2Months Inventory Amt between one & Two Month	  */
	public void setDS_InvAmtBetween1To2Months (BigDecimal DS_InvAmtBetween1To2Months)
	{
		set_Value (COLUMNNAME_DS_InvAmtBetween1To2Months, DS_InvAmtBetween1To2Months);
	}

	/** Get Inventory Amt between one & Two Month.
		@return Inventory Amt between one & Two Month	  */
	public BigDecimal getDS_InvAmtBetween1To2Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvAmtBetween1To2Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Amt between 1 & 2 Months.
		@param DS_InvAmtBetween2To3Months Inventory Amt between 1 & 2 Months	  */
	public void setDS_InvAmtBetween2To3Months (BigDecimal DS_InvAmtBetween2To3Months)
	{
		set_Value (COLUMNNAME_DS_InvAmtBetween2To3Months, DS_InvAmtBetween2To3Months);
	}

	/** Get Inventory Amt between 1 & 2 Months.
		@return Inventory Amt between 1 & 2 Months	  */
	public BigDecimal getDS_InvAmtBetween2To3Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvAmtBetween2To3Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Amt between 3 & 4 Months.
		@param DS_InvAmtBetween3To4Months Inventory Amt between 3 & 4 Months	  */
	public void setDS_InvAmtBetween3To4Months (BigDecimal DS_InvAmtBetween3To4Months)
	{
		set_Value (COLUMNNAME_DS_InvAmtBetween3To4Months, DS_InvAmtBetween3To4Months);
	}

	/** Get Inventory Amt between 3 & 4 Months.
		@return Inventory Amt between 3 & 4 Months	  */
	public BigDecimal getDS_InvAmtBetween3To4Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvAmtBetween3To4Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Amt between 4 & 5 Months.
		@param DS_InvAmtBetween4To5Months Inventory Amt between 4 & 5 Months	  */
	public void setDS_InvAmtBetween4To5Months (BigDecimal DS_InvAmtBetween4To5Months)
	{
		set_Value (COLUMNNAME_DS_InvAmtBetween4To5Months, DS_InvAmtBetween4To5Months);
	}

	/** Get Inventory Amt between 4 & 5 Months.
		@return Inventory Amt between 4 & 5 Months	  */
	public BigDecimal getDS_InvAmtBetween4To5Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvAmtBetween4To5Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Amt between 5 & 6 Months.
		@param DS_InvAmtBetween5To6Months Inventory Amt between 5 & 6 Months	  */
	public void setDS_InvAmtBetween5To6Months (BigDecimal DS_InvAmtBetween5To6Months)
	{
		set_Value (COLUMNNAME_DS_InvAmtBetween5To6Months, DS_InvAmtBetween5To6Months);
	}

	/** Get Inventory Amt between 5 & 6 Months.
		@return Inventory Amt between 5 & 6 Months	  */
	public BigDecimal getDS_InvAmtBetween5To6Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvAmtBetween5To6Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Amt More than 6 months.
		@param DS_InvAmtMorethan6Months Inventory Amt More than 6 months	  */
	public void setDS_InvAmtMorethan6Months (BigDecimal DS_InvAmtMorethan6Months)
	{
		set_Value (COLUMNNAME_DS_InvAmtMorethan6Months, DS_InvAmtMorethan6Months);
	}

	/** Get Inventory Amt More than 6 months.
		@return Inventory Amt More than 6 months	  */
	public BigDecimal getDS_InvAmtMorethan6Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvAmtMorethan6Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Age Less than One Month.
		@param DS_InvBetween0To1Month Inventory Age Less than One Month	  */
	public void setDS_InvBetween0To1Month (BigDecimal DS_InvBetween0To1Month)
	{
		set_Value (COLUMNNAME_DS_InvBetween0To1Month, DS_InvBetween0To1Month);
	}

	/** Get Inventory Age Less than One Month.
		@return Inventory Age Less than One Month	  */
	public BigDecimal getDS_InvBetween0To1Month () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvBetween0To1Month);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory between one & Two Month.
		@param DS_InvBetween1To2Months Inventory between one & Two Month	  */
	public void setDS_InvBetween1To2Months (BigDecimal DS_InvBetween1To2Months)
	{
		set_Value (COLUMNNAME_DS_InvBetween1To2Months, DS_InvBetween1To2Months);
	}

	/** Get Inventory between one & Two Month.
		@return Inventory between one & Two Month	  */
	public BigDecimal getDS_InvBetween1To2Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvBetween1To2Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory between 1 & 2 Months.
		@param DS_InvBetween2To3Months Inventory between 1 & 2 Months	  */
	public void setDS_InvBetween2To3Months (BigDecimal DS_InvBetween2To3Months)
	{
		set_Value (COLUMNNAME_DS_InvBetween2To3Months, DS_InvBetween2To3Months);
	}

	/** Get Inventory between 1 & 2 Months.
		@return Inventory between 1 & 2 Months	  */
	public BigDecimal getDS_InvBetween2To3Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvBetween2To3Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory between 3 & 4 Months.
		@param DS_InvBetween3To4Months Inventory between 3 & 4 Months	  */
	public void setDS_InvBetween3To4Months (BigDecimal DS_InvBetween3To4Months)
	{
		set_Value (COLUMNNAME_DS_InvBetween3To4Months, DS_InvBetween3To4Months);
	}

	/** Get Inventory between 3 & 4 Months.
		@return Inventory between 3 & 4 Months	  */
	public BigDecimal getDS_InvBetween3To4Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvBetween3To4Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory between 4 & 5 Months.
		@param DS_InvBetween4To5Months Inventory between 4 & 5 Months	  */
	public void setDS_InvBetween4To5Months (BigDecimal DS_InvBetween4To5Months)
	{
		set_Value (COLUMNNAME_DS_InvBetween4To5Months, DS_InvBetween4To5Months);
	}

	/** Get Inventory between 4 & 5 Months.
		@return Inventory between 4 & 5 Months	  */
	public BigDecimal getDS_InvBetween4To5Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvBetween4To5Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory between 5 & 6 Months.
		@param DS_InvBetween5To6Months Inventory between 5 & 6 Months	  */
	public void setDS_InvBetween5To6Months (BigDecimal DS_InvBetween5To6Months)
	{
		set_Value (COLUMNNAME_DS_InvBetween5To6Months, DS_InvBetween5To6Months);
	}

	/** Get Inventory between 5 & 6 Months.
		@return Inventory between 5 & 6 Months	  */
	public BigDecimal getDS_InvBetween5To6Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvBetween5To6Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Inventory Aging.
		@param DS_InventoryAging_ID Inventory Aging	  */
	public void setDS_InventoryAging_ID (int DS_InventoryAging_ID)
	{
		if (DS_InventoryAging_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_InventoryAging_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_InventoryAging_ID, Integer.valueOf(DS_InventoryAging_ID));
	}

	/** Get Inventory Aging.
		@return Inventory Aging	  */
	public int getDS_InventoryAging_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_InventoryAging_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_InventoryAging_UU.
		@param DS_InventoryAging_UU DS_InventoryAging_UU	  */
	public void setDS_InventoryAging_UU (String DS_InventoryAging_UU)
	{
		set_Value (COLUMNNAME_DS_InventoryAging_UU, DS_InventoryAging_UU);
	}

	/** Get DS_InventoryAging_UU.
		@return DS_InventoryAging_UU	  */
	public String getDS_InventoryAging_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_InventoryAging_UU);
	}

	/** Set Inventory More than 6 months.
		@param DS_InvMorethan6Months Inventory More than 6 months	  */
	public void setDS_InvMorethan6Months (BigDecimal DS_InvMorethan6Months)
	{
		set_Value (COLUMNNAME_DS_InvMorethan6Months, DS_InvMorethan6Months);
	}

	/** Get Inventory More than 6 months.
		@return Inventory More than 6 months	  */
	public BigDecimal getDS_InvMorethan6Months () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_InvMorethan6Months);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
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
}