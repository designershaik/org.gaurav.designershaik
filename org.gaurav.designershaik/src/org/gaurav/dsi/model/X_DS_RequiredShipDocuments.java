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

/** Generated Model for DS_RequiredShipDocuments
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_RequiredShipDocuments extends PO implements I_DS_RequiredShipDocuments, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180514L;

    /** Standard Constructor */
    public X_DS_RequiredShipDocuments (Properties ctx, int DS_RequiredShipDocuments_ID, String trxName)
    {
      super (ctx, DS_RequiredShipDocuments_ID, trxName);
      /** if (DS_RequiredShipDocuments_ID == 0)
        {
			setDS_RequiredShipDocuments_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_RequiredShipDocuments (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_RequiredShipDocuments[")
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
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
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

	/** Set Mandatory.
		@param DS_IsRequired Mandatory	  */
	public void setDS_IsRequired (boolean DS_IsRequired)
	{
		set_Value (COLUMNNAME_DS_IsRequired, Boolean.valueOf(DS_IsRequired));
	}

	/** Get Mandatory.
		@return Mandatory	  */
	public boolean isDS_IsRequired () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsRequired);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Required Shipment Documents.
		@param DS_RequiredShipDocuments_ID Required Shipment Documents	  */
	public void setDS_RequiredShipDocuments_ID (int DS_RequiredShipDocuments_ID)
	{
		if (DS_RequiredShipDocuments_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_RequiredShipDocuments_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_RequiredShipDocuments_ID, Integer.valueOf(DS_RequiredShipDocuments_ID));
	}

	/** Get Required Shipment Documents.
		@return Required Shipment Documents	  */
	public int getDS_RequiredShipDocuments_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RequiredShipDocuments_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_RequiredShipDocuments_UU.
		@param DS_RequiredShipDocuments_UU DS_RequiredShipDocuments_UU	  */
	public void setDS_RequiredShipDocuments_UU (String DS_RequiredShipDocuments_UU)
	{
		set_Value (COLUMNNAME_DS_RequiredShipDocuments_UU, DS_RequiredShipDocuments_UU);
	}

	/** Get DS_RequiredShipDocuments_UU.
		@return DS_RequiredShipDocuments_UU	  */
	public String getDS_RequiredShipDocuments_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_RequiredShipDocuments_UU);
	}

	public I_DS_ShipmentDocuments getDS_ShipmentDocuments() throws RuntimeException
    {
		return (I_DS_ShipmentDocuments)MTable.get(getCtx(), I_DS_ShipmentDocuments.Table_Name)
			.getPO(getDS_ShipmentDocuments_ID(), get_TrxName());	}

	/** Set Document required specific to country.
		@param DS_ShipmentDocuments_ID Document required specific to country	  */
	public void setDS_ShipmentDocuments_ID (int DS_ShipmentDocuments_ID)
	{
		if (DS_ShipmentDocuments_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ShipmentDocuments_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ShipmentDocuments_ID, Integer.valueOf(DS_ShipmentDocuments_ID));
	}

	/** Get Document required specific to country.
		@return Document required specific to country	  */
	public int getDS_ShipmentDocuments_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ShipmentDocuments_ID);
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

	public org.compiere.model.I_M_FreightCategory getM_FreightCategory() throws RuntimeException
    {
		return (org.compiere.model.I_M_FreightCategory)MTable.get(getCtx(), org.compiere.model.I_M_FreightCategory.Table_Name)
			.getPO(getM_FreightCategory_ID(), get_TrxName());	}

	/** Set Freight Category.
		@param M_FreightCategory_ID 
		Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID)
	{
		if (M_FreightCategory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_FreightCategory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_FreightCategory_ID, Integer.valueOf(M_FreightCategory_ID));
	}

	/** Get Freight Category.
		@return Category of the Freight
	  */
	public int getM_FreightCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_FreightCategory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}