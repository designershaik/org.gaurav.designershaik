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

/** Generated Model for DS_RequiredDocForShipment
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_RequiredDocForShipment extends PO implements I_DS_RequiredDocForShipment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180516L;

    /** Standard Constructor */
    public X_DS_RequiredDocForShipment (Properties ctx, int DS_RequiredDocForShipment_ID, String trxName)
    {
      super (ctx, DS_RequiredDocForShipment_ID, trxName);
      /** if (DS_RequiredDocForShipment_ID == 0)
        {
			setDS_RequiredDocForShipment_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_RequiredDocForShipment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_RequiredDocForShipment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
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

	/** Set Prepared ?.
		@param DS_Prepared 
		Prepared ?
	  */
	public void setDS_Prepared (boolean DS_Prepared)
	{
		set_Value (COLUMNNAME_DS_Prepared, Boolean.valueOf(DS_Prepared));
	}

	/** Get Prepared ?.
		@return Prepared ?
	  */
	public boolean isDS_Prepared () 
	{
		Object oo = get_Value(COLUMNNAME_DS_Prepared);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Required Documents On SO.
		@param DS_RequiredDocForShipment_ID Required Documents On SO	  */
	public void setDS_RequiredDocForShipment_ID (int DS_RequiredDocForShipment_ID)
	{
		if (DS_RequiredDocForShipment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_RequiredDocForShipment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_RequiredDocForShipment_ID, Integer.valueOf(DS_RequiredDocForShipment_ID));
	}

	/** Get Required Documents On SO.
		@return Required Documents On SO	  */
	public int getDS_RequiredDocForShipment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RequiredDocForShipment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_RequiredDocForShipment_UU.
		@param DS_RequiredDocForShipment_UU DS_RequiredDocForShipment_UU	  */
	public void setDS_RequiredDocForShipment_UU (String DS_RequiredDocForShipment_UU)
	{
		set_Value (COLUMNNAME_DS_RequiredDocForShipment_UU, DS_RequiredDocForShipment_UU);
	}

	/** Get DS_RequiredDocForShipment_UU.
		@return DS_RequiredDocForShipment_UU	  */
	public String getDS_RequiredDocForShipment_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_RequiredDocForShipment_UU);
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

	/** Set Is Stamped.
		@param DS_Stamped 
		Is Stamped
	  */
	public void setDS_Stamped (boolean DS_Stamped)
	{
		set_Value (COLUMNNAME_DS_Stamped, Boolean.valueOf(DS_Stamped));
	}

	/** Get Is Stamped.
		@return Is Stamped
	  */
	public boolean isDS_Stamped () 
	{
		Object oo = get_Value(COLUMNNAME_DS_Stamped);
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