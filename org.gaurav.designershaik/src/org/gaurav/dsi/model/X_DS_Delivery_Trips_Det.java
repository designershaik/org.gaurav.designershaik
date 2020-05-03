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

/** Generated Model for DS_Delivery_Trips_Det
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Delivery_Trips_Det extends PO implements I_DS_Delivery_Trips_Det, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180327L;

    /** Standard Constructor */
    public X_DS_Delivery_Trips_Det (Properties ctx, int DS_Delivery_Trips_Det_ID, String trxName)
    {
      super (ctx, DS_Delivery_Trips_Det_ID, trxName);
      /** if (DS_Delivery_Trips_Det_ID == 0)
        {
			setDS_Delivery_Trips_Det_ID (0);
			setDS_Delivery_Trips_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Delivery_Trips_Det (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Delivery_Trips_Det[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Delivery trips Details.
		@param DS_Delivery_Trips_Det_ID Delivery trips Details	  */
	public void setDS_Delivery_Trips_Det_ID (int DS_Delivery_Trips_Det_ID)
	{
		if (DS_Delivery_Trips_Det_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_Trips_Det_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_Trips_Det_ID, Integer.valueOf(DS_Delivery_Trips_Det_ID));
	}

	/** Get Delivery trips Details.
		@return Delivery trips Details	  */
	public int getDS_Delivery_Trips_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Delivery_Trips_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Delivery_Trips_Det_UU.
		@param DS_Delivery_Trips_Det_UU DS_Delivery_Trips_Det_UU	  */
	public void setDS_Delivery_Trips_Det_UU (String DS_Delivery_Trips_Det_UU)
	{
		set_Value (COLUMNNAME_DS_Delivery_Trips_Det_UU, DS_Delivery_Trips_Det_UU);
	}

	/** Get DS_Delivery_Trips_Det_UU.
		@return DS_Delivery_Trips_Det_UU	  */
	public String getDS_Delivery_Trips_Det_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Delivery_Trips_Det_UU);
	}

	public I_DS_Delivery_Trips getDS_Delivery_Trips() throws RuntimeException
    {
		return (I_DS_Delivery_Trips)MTable.get(getCtx(), I_DS_Delivery_Trips.Table_Name)
			.getPO(getDS_Delivery_Trips_ID(), get_TrxName());	}

	/** Set Delivery trips.
		@param DS_Delivery_Trips_ID Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID)
	{
		if (DS_Delivery_Trips_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_Trips_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Delivery_Trips_ID, Integer.valueOf(DS_Delivery_Trips_ID));
	}

	/** Get Delivery trips.
		@return Delivery trips	  */
	public int getDS_Delivery_Trips_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Delivery_Trips_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_InOut getM_InOut() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOut)MTable.get(getCtx(), org.compiere.model.I_M_InOut.Table_Name)
			.getPO(getM_InOut_ID(), get_TrxName());	}

	/** Set Shipment/Receipt.
		@param M_InOut_ID 
		Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID)
	{
		if (M_InOut_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_InOut_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_InOut_ID, Integer.valueOf(M_InOut_ID));
	}

	/** Get Shipment/Receipt.
		@return Material Shipment Document
	  */
	public int getM_InOut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Inventory getM_Inventory() throws RuntimeException
    {
		return (org.compiere.model.I_M_Inventory)MTable.get(getCtx(), org.compiere.model.I_M_Inventory.Table_Name)
			.getPO(getM_Inventory_ID(), get_TrxName());	}

	/** Set Phys.Inventory.
		@param M_Inventory_ID 
		Parameters for a Physical Inventory
	  */
	public void setM_Inventory_ID (int M_Inventory_ID)
	{
		if (M_Inventory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Inventory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Inventory_ID, Integer.valueOf(M_Inventory_ID));
	}

	/** Get Phys.Inventory.
		@return Parameters for a Physical Inventory
	  */
	public int getM_Inventory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Inventory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Movement getM_Movement() throws RuntimeException
    {
		return (org.compiere.model.I_M_Movement)MTable.get(getCtx(), org.compiere.model.I_M_Movement.Table_Name)
			.getPO(getM_Movement_ID(), get_TrxName());	}

	/** Set Inventory Move.
		@param M_Movement_ID 
		Movement of Inventory
	  */
	public void setM_Movement_ID (int M_Movement_ID)
	{
		if (M_Movement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Movement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Movement_ID, Integer.valueOf(M_Movement_ID));
	}

	/** Get Inventory Move.
		@return Movement of Inventory
	  */
	public int getM_Movement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Movement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Package getM_Package() throws RuntimeException
    {
		return (org.compiere.model.I_M_Package)MTable.get(getCtx(), org.compiere.model.I_M_Package.Table_Name)
			.getPO(getM_Package_ID(), get_TrxName());	}

	/** Set Package.
		@param M_Package_ID 
		Shipment Package
	  */
	public void setM_Package_ID (int M_Package_ID)
	{
		if (M_Package_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Package_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Package_ID, Integer.valueOf(M_Package_ID));
	}

	/** Get Package.
		@return Shipment Package
	  */
	public int getM_Package_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Package_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_Request getR_Request() throws RuntimeException
    {
		return (org.compiere.model.I_R_Request)MTable.get(getCtx(), org.compiere.model.I_R_Request.Table_Name)
			.getPO(getR_Request_ID(), get_TrxName());	}

	/** Set Request.
		@param R_Request_ID 
		Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID)
	{
		if (R_Request_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_Request_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_Request_ID, Integer.valueOf(R_Request_ID));
	}

	/** Get Request.
		@return Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Request_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}