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
import org.compiere.util.KeyNamePair;

/** Generated Model for DS_AssetEntry
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_AssetEntry extends PO implements I_DS_AssetEntry, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_AssetEntry (Properties ctx, int DS_AssetEntry_ID, String trxName)
    {
      super (ctx, DS_AssetEntry_ID, trxName);
      /** if (DS_AssetEntry_ID == 0)
        {
			setComments (null);
			setDS_AssetEntry_ID (0);
			setDS_Location_ID (0);
			setDS_SubLocation_ID (0);
			setName (null);
			setStatus (null);
// AC
        } */
    }

    /** Load Constructor */
    public X_DS_AssetEntry (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_AssetEntry[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Toshiba = TO */
	public static final String CATEGORYNAME_Toshiba = "TO";
	/** Plant = PLANT */
	public static final String CATEGORYNAME_Plant = "PLANT";
	/** Machinary = MACH */
	public static final String CATEGORYNAME_Machinary = "MACH";
	/** Tools & Equipment = TOOL */
	public static final String CATEGORYNAME_ToolsEquipment = "TOOL";
	/** Perfume Storage = STOR */
	public static final String CATEGORYNAME_PerfumeStorage = "STOR";
	/** Photography = PHOTO */
	public static final String CATEGORYNAME_Photography = "PHOTO";
	/** Vehicle = VEHI */
	public static final String CATEGORYNAME_Vehicle = "VEHI";
	/** Office Furniture = FURN */
	public static final String CATEGORYNAME_OfficeFurniture = "FURN";
	/** Office Equipment = EQUI */
	public static final String CATEGORYNAME_OfficeEquipment = "EQUI";
	/** Staff Quarter Equipment = STAFF */
	public static final String CATEGORYNAME_StaffQuarterEquipment = "STAFF";
	/** Computers = COMP */
	public static final String CATEGORYNAME_Computers = "COMP";
	/** Display = DISP */
	public static final String CATEGORYNAME_Display = "DISP";
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

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	/** Set Asset Code.
		@param DS_AssetCode Asset Code	  */
	public void setDS_AssetCode (String DS_AssetCode)
	{
		set_Value (COLUMNNAME_DS_AssetCode, DS_AssetCode);
	}

	/** Get Asset Code.
		@return Asset Code	  */
	public String getDS_AssetCode () 
	{
		return (String)get_Value(COLUMNNAME_DS_AssetCode);
	}

	/** Set Asset Entry.
		@param DS_AssetEntry_ID Asset Entry	  */
	public void setDS_AssetEntry_ID (int DS_AssetEntry_ID)
	{
		if (DS_AssetEntry_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_AssetEntry_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_AssetEntry_ID, Integer.valueOf(DS_AssetEntry_ID));
	}

	/** Get Asset Entry.
		@return Asset Entry	  */
	public int getDS_AssetEntry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_AssetEntry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_AssetEntry_UU.
		@param DS_AssetEntry_UU DS_AssetEntry_UU	  */
	public void setDS_AssetEntry_UU (String DS_AssetEntry_UU)
	{
		set_Value (COLUMNNAME_DS_AssetEntry_UU, DS_AssetEntry_UU);
	}

	/** Get DS_AssetEntry_UU.
		@return DS_AssetEntry_UU	  */
	public String getDS_AssetEntry_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_AssetEntry_UU);
	}

	public I_DS_Brand getDS_Brand() throws RuntimeException
    {
		return (I_DS_Brand)MTable.get(getCtx(), I_DS_Brand.Table_Name)
			.getPO(getDS_Brand_ID(), get_TrxName());	}

	/** Set Asset Brand.
		@param DS_Brand_ID Asset Brand	  */
	public void setDS_Brand_ID (int DS_Brand_ID)
	{
		if (DS_Brand_ID < 1) 
			set_Value (COLUMNNAME_DS_Brand_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Brand_ID, Integer.valueOf(DS_Brand_ID));
	}

	/** Get Asset Brand.
		@return Asset Brand	  */
	public int getDS_Brand_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Brand_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DS_Location getDS_Location() throws RuntimeException
    {
		return (I_DS_Location)MTable.get(getCtx(), I_DS_Location.Table_Name)
			.getPO(getDS_Location_ID(), get_TrxName());	}

	/** Set Location.
		@param DS_Location_ID Location	  */
	public void setDS_Location_ID (int DS_Location_ID)
	{
		if (DS_Location_ID < 1) 
			set_Value (COLUMNNAME_DS_Location_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Location_ID, Integer.valueOf(DS_Location_ID));
	}

	/** Get Location.
		@return Location	  */
	public int getDS_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Reason for InActive Asset.
		@param DS_ReasonForInActiveAsset Reason for InActive Asset	  */
	public void setDS_ReasonForInActiveAsset (String DS_ReasonForInActiveAsset)
	{
		set_Value (COLUMNNAME_DS_ReasonForInActiveAsset, DS_ReasonForInActiveAsset);
	}

	/** Get Reason for InActive Asset.
		@return Reason for InActive Asset	  */
	public String getDS_ReasonForInActiveAsset () 
	{
		return (String)get_Value(COLUMNNAME_DS_ReasonForInActiveAsset);
	}

	public I_DS_SubLocation getDS_SubLocation() throws RuntimeException
    {
		return (I_DS_SubLocation)MTable.get(getCtx(), I_DS_SubLocation.Table_Name)
			.getPO(getDS_SubLocation_ID(), get_TrxName());	}

	/** Set Sub Location.
		@param DS_SubLocation_ID Sub Location	  */
	public void setDS_SubLocation_ID (int DS_SubLocation_ID)
	{
		if (DS_SubLocation_ID < 1) 
			set_Value (COLUMNNAME_DS_SubLocation_ID, null);
		else 
			set_Value (COLUMNNAME_DS_SubLocation_ID, Integer.valueOf(DS_SubLocation_ID));
	}

	/** Get Sub Location.
		@return Sub Location	  */
	public int getDS_SubLocation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_SubLocation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Manufactured Year.
		@param ManufacturedYear Manufactured Year	  */
	public void setManufacturedYear (int ManufacturedYear)
	{
		set_Value (COLUMNNAME_ManufacturedYear, Integer.valueOf(ManufacturedYear));
	}

	/** Get Manufactured Year.
		@return Manufactured Year	  */
	public int getManufacturedYear () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ManufacturedYear);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Active = AC */
	public static final String STATUS_Active = "AC";
	/** InActive = IA */
	public static final String STATUS_InActive = "IA";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}

	/** Set UPC/EAN.
		@param UPC 
		Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC)
	{
		set_Value (COLUMNNAME_UPC, UPC);
	}

	/** Get UPC/EAN.
		@return Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC () 
	{
		return (String)get_Value(COLUMNNAME_UPC);
	}
}