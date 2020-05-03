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

/** Generated Model for DS_Trip_Configuration
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Trip_Configuration extends PO implements I_DS_Trip_Configuration, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180405L;

    /** Standard Constructor */
    public X_DS_Trip_Configuration (Properties ctx, int DS_Trip_Configuration_ID, String trxName)
    {
      super (ctx, DS_Trip_Configuration_ID, trxName);
      /** if (DS_Trip_Configuration_ID == 0)
        {
			setDS_Trip_Configuration_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Trip_Configuration (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Trip_Configuration[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Charge getDS_AccommodationCharge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_AccommodationCharge_ID(), get_TrxName());	}

	/** Set Accommodation Charge.
		@param DS_AccommodationCharge_ID 
		Accommodation Charge
	  */
	public void setDS_AccommodationCharge_ID (int DS_AccommodationCharge_ID)
	{
		if (DS_AccommodationCharge_ID < 1) 
			set_Value (COLUMNNAME_DS_AccommodationCharge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_AccommodationCharge_ID, Integer.valueOf(DS_AccommodationCharge_ID));
	}

	/** Get Accommodation Charge.
		@return Accommodation Charge
	  */
	public int getDS_AccommodationCharge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_AccommodationCharge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_AllowanceCharge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_AllowanceCharge_ID(), get_TrxName());	}

	/** Set Allowance Charge.
		@param DS_AllowanceCharge_ID 
		Allowance Charge
	  */
	public void setDS_AllowanceCharge_ID (int DS_AllowanceCharge_ID)
	{
		if (DS_AllowanceCharge_ID < 1) 
			set_Value (COLUMNNAME_DS_AllowanceCharge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_AllowanceCharge_ID, Integer.valueOf(DS_AllowanceCharge_ID));
	}

	/** Get Allowance Charge.
		@return Allowance Charge
	  */
	public int getDS_AllowanceCharge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_AllowanceCharge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_RequestType getDS_CashRequest_Type() throws RuntimeException
    {
		return (org.compiere.model.I_R_RequestType)MTable.get(getCtx(), org.compiere.model.I_R_RequestType.Table_Name)
			.getPO(getDS_CashRequest_Type_ID(), get_TrxName());	}

	/** Set Cash Request Type.
		@param DS_CashRequest_Type_ID Cash Request Type	  */
	public void setDS_CashRequest_Type_ID (int DS_CashRequest_Type_ID)
	{
		if (DS_CashRequest_Type_ID < 1) 
			set_Value (COLUMNNAME_DS_CashRequest_Type_ID, null);
		else 
			set_Value (COLUMNNAME_DS_CashRequest_Type_ID, Integer.valueOf(DS_CashRequest_Type_ID));
	}

	/** Get Cash Request Type.
		@return Cash Request Type	  */
	public int getDS_CashRequest_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_CashRequest_Type_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_CustomCharge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_CustomCharge_ID(), get_TrxName());	}

	/** Set Custom Charge.
		@param DS_CustomCharge_ID 
		Custom Charge
	  */
	public void setDS_CustomCharge_ID (int DS_CustomCharge_ID)
	{
		if (DS_CustomCharge_ID < 1) 
			set_Value (COLUMNNAME_DS_CustomCharge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_CustomCharge_ID, Integer.valueOf(DS_CustomCharge_ID));
	}

	/** Get Custom Charge.
		@return Custom Charge
	  */
	public int getDS_CustomCharge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_CustomCharge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_Food_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_Food_Charge_ID(), get_TrxName());	}

	/** Set Food Charge.
		@param DS_Food_Charge_ID 
		Food Charge
	  */
	public void setDS_Food_Charge_ID (int DS_Food_Charge_ID)
	{
		if (DS_Food_Charge_ID < 1) 
			set_Value (COLUMNNAME_DS_Food_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Food_Charge_ID, Integer.valueOf(DS_Food_Charge_ID));
	}

	/** Get Food Charge.
		@return Food Charge
	  */
	public int getDS_Food_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Food_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_Fuel_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_Fuel_Charge_ID(), get_TrxName());	}

	/** Set Fuel Charge.
		@param DS_Fuel_Charge_ID 
		Fuel Charge
	  */
	public void setDS_Fuel_Charge_ID (int DS_Fuel_Charge_ID)
	{
		if (DS_Fuel_Charge_ID < 1) 
			set_Value (COLUMNNAME_DS_Fuel_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Fuel_Charge_ID, Integer.valueOf(DS_Fuel_Charge_ID));
	}

	/** Get Fuel Charge.
		@return Fuel Charge
	  */
	public int getDS_Fuel_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Fuel_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_MiscExpenseCharges() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_MiscExpenseCharges_ID(), get_TrxName());	}

	/** Set Miscellaneous Expense Charge(Insurance & Others).
		@param DS_MiscExpenseCharges_ID 
		Miscellaneous Expense Charge(Insurance & Others)
	  */
	public void setDS_MiscExpenseCharges_ID (int DS_MiscExpenseCharges_ID)
	{
		if (DS_MiscExpenseCharges_ID < 1) 
			set_Value (COLUMNNAME_DS_MiscExpenseCharges_ID, null);
		else 
			set_Value (COLUMNNAME_DS_MiscExpenseCharges_ID, Integer.valueOf(DS_MiscExpenseCharges_ID));
	}

	/** Get Miscellaneous Expense Charge(Insurance & Others).
		@return Miscellaneous Expense Charge(Insurance & Others)
	  */
	public int getDS_MiscExpenseCharges_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_MiscExpenseCharges_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_Telephone_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_Telephone_Charge_ID(), get_TrxName());	}

	/** Set Telephone Charge.
		@param DS_Telephone_Charge_ID 
		Telephone Charge
	  */
	public void setDS_Telephone_Charge_ID (int DS_Telephone_Charge_ID)
	{
		if (DS_Telephone_Charge_ID < 1) 
			set_Value (COLUMNNAME_DS_Telephone_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Telephone_Charge_ID, Integer.valueOf(DS_Telephone_Charge_ID));
	}

	/** Get Telephone Charge.
		@return Telephone Charge
	  */
	public int getDS_Telephone_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Telephone_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_TravelCharge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_TravelCharge_ID(), get_TrxName());	}

	/** Set Travel Charge.
		@param DS_TravelCharge_ID 
		Travel Charge
	  */
	public void setDS_TravelCharge_ID (int DS_TravelCharge_ID)
	{
		if (DS_TravelCharge_ID < 1) 
			set_Value (COLUMNNAME_DS_TravelCharge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_TravelCharge_ID, Integer.valueOf(DS_TravelCharge_ID));
	}

	/** Get Travel Charge.
		@return Travel Charge
	  */
	public int getDS_TravelCharge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TravelCharge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trip Configurations.
		@param DS_Trip_Configuration_ID Trip Configurations	  */
	public void setDS_Trip_Configuration_ID (int DS_Trip_Configuration_ID)
	{
		if (DS_Trip_Configuration_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Trip_Configuration_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Trip_Configuration_ID, Integer.valueOf(DS_Trip_Configuration_ID));
	}

	/** Get Trip Configurations.
		@return Trip Configurations	  */
	public int getDS_Trip_Configuration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Trip_Configuration_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Trip_Configuration_UU.
		@param DS_Trip_Configuration_UU DS_Trip_Configuration_UU	  */
	public void setDS_Trip_Configuration_UU (String DS_Trip_Configuration_UU)
	{
		set_Value (COLUMNNAME_DS_Trip_Configuration_UU, DS_Trip_Configuration_UU);
	}

	/** Get DS_Trip_Configuration_UU.
		@return DS_Trip_Configuration_UU	  */
	public String getDS_Trip_Configuration_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Trip_Configuration_UU);
	}

	public org.compiere.model.I_R_RequestType getDS_TripSettlement_Type() throws RuntimeException
    {
		return (org.compiere.model.I_R_RequestType)MTable.get(getCtx(), org.compiere.model.I_R_RequestType.Table_Name)
			.getPO(getDS_TripSettlement_Type_ID(), get_TrxName());	}

	/** Set Trip Settlement Request Type.
		@param DS_TripSettlement_Type_ID Trip Settlement Request Type	  */
	public void setDS_TripSettlement_Type_ID (int DS_TripSettlement_Type_ID)
	{
		if (DS_TripSettlement_Type_ID < 1) 
			set_Value (COLUMNNAME_DS_TripSettlement_Type_ID, null);
		else 
			set_Value (COLUMNNAME_DS_TripSettlement_Type_ID, Integer.valueOf(DS_TripSettlement_Type_ID));
	}

	/** Get Trip Settlement Request Type.
		@return Trip Settlement Request Type	  */
	public int getDS_TripSettlement_Type_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TripSettlement_Type_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getDS_WithinCountryTransport() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_WithinCountryTransport_ID(), get_TrxName());	}

	/** Set Transport (Taxi/Metro/Train/Bus) Charge.
		@param DS_WithinCountryTransport_ID 
		Transport (Taxi/Metro/Train/Bus)Charge
	  */
	public void setDS_WithinCountryTransport_ID (int DS_WithinCountryTransport_ID)
	{
		if (DS_WithinCountryTransport_ID < 1) 
			set_Value (COLUMNNAME_DS_WithinCountryTransport_ID, null);
		else 
			set_Value (COLUMNNAME_DS_WithinCountryTransport_ID, Integer.valueOf(DS_WithinCountryTransport_ID));
	}

	/** Get Transport (Taxi/Metro/Train/Bus) Charge.
		@return Transport (Taxi/Metro/Train/Bus)Charge
	  */
	public int getDS_WithinCountryTransport_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_WithinCountryTransport_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_Group getR_Group() throws RuntimeException
    {
		return (org.compiere.model.I_R_Group)MTable.get(getCtx(), org.compiere.model.I_R_Group.Table_Name)
			.getPO(getR_Group_ID(), get_TrxName());	}

	/** Set Group.
		@param R_Group_ID 
		Request Group
	  */
	public void setR_Group_ID (int R_Group_ID)
	{
		if (R_Group_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_Group_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_Group_ID, Integer.valueOf(R_Group_ID));
	}

	/** Get Group.
		@return Request Group
	  */
	public int getR_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}