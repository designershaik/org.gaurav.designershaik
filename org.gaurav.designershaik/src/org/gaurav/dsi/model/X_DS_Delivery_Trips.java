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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DS_Delivery_Trips
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Delivery_Trips extends PO implements I_DS_Delivery_Trips, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180327L;

    /** Standard Constructor */
    public X_DS_Delivery_Trips (Properties ctx, int DS_Delivery_Trips_ID, String trxName)
    {
      super (ctx, DS_Delivery_Trips_ID, trxName);
      /** if (DS_Delivery_Trips_ID == 0)
        {
			setDS_Delivery_Trips_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Delivery_Trips (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Delivery_Trips[")
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

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
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

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Total Cash Request Amt.
		@param DS_CashRequest_Amt 
		Total Cash Request Amt
	  */
	public void setDS_CashRequest_Amt (BigDecimal DS_CashRequest_Amt)
	{
		set_Value (COLUMNNAME_DS_CashRequest_Amt, DS_CashRequest_Amt);
	}

	/** Get Total Cash Request Amt.
		@return Total Cash Request Amt
	  */
	public BigDecimal getDS_CashRequest_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_CashRequest_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Create Cash Request & Draft Settlement.
		@param DS_CreateCashRequest 
		Create Cash Request & Draft Settlement
	  */
	public void setDS_CreateCashRequest (String DS_CreateCashRequest)
	{
		set_Value (COLUMNNAME_DS_CreateCashRequest, DS_CreateCashRequest);
	}

	/** Get Create Cash Request & Draft Settlement.
		@return Create Cash Request & Draft Settlement
	  */
	public String getDS_CreateCashRequest () 
	{
		return (String)get_Value(COLUMNNAME_DS_CreateCashRequest);
	}

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

	/** Set DS_Delivery_Trips_UU.
		@param DS_Delivery_Trips_UU DS_Delivery_Trips_UU	  */
	public void setDS_Delivery_Trips_UU (String DS_Delivery_Trips_UU)
	{
		set_Value (COLUMNNAME_DS_Delivery_Trips_UU, DS_Delivery_Trips_UU);
	}

	/** Get DS_Delivery_Trips_UU.
		@return DS_Delivery_Trips_UU	  */
	public String getDS_Delivery_Trips_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Delivery_Trips_UU);
	}

	public org.compiere.model.I_C_BPartner getDS_Employee2() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getDS_Employee2_ID(), get_TrxName());	}

	/** Set Employee 2.
		@param DS_Employee2_ID 
		Employee 2 for trip schedule
	  */
	public void setDS_Employee2_ID (int DS_Employee2_ID)
	{
		if (DS_Employee2_ID < 1) 
			set_Value (COLUMNNAME_DS_Employee2_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Employee2_ID, Integer.valueOf(DS_Employee2_ID));
	}

	/** Get Employee 2.
		@return Employee 2 for trip schedule
	  */
	public int getDS_Employee2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Employee2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Manifest Prepared.
		@param DS_ManifestPrepared 
		Manifest Prepared
	  */
	public void setDS_ManifestPrepared (boolean DS_ManifestPrepared)
	{
		set_Value (COLUMNNAME_DS_ManifestPrepared, Boolean.valueOf(DS_ManifestPrepared));
	}

	/** Get Manifest Prepared.
		@return Manifest Prepared
	  */
	public boolean isDS_ManifestPrepared () 
	{
		Object oo = get_Value(COLUMNNAME_DS_ManifestPrepared);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_Product getDS_Product_Vehicle() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getDS_Product_Vehicle_ID(), get_TrxName());	}

	/** Set Vehicle 2.
		@param DS_Product_Vehicle_ID 
		Vehicle 2 for trip schedule
	  */
	public void setDS_Product_Vehicle_ID (int DS_Product_Vehicle_ID)
	{
		if (DS_Product_Vehicle_ID < 1) 
			set_Value (COLUMNNAME_DS_Product_Vehicle_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Product_Vehicle_ID, Integer.valueOf(DS_Product_Vehicle_ID));
	}

	/** Get Vehicle 2.
		@return Vehicle 2 for trip schedule
	  */
	public int getDS_Product_Vehicle_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Product_Vehicle_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Customer Visit = CV */
	public static final String DS_PURPOSE_CustomerVisit = "CV";
	/** Shipment/Inventory Movement = SH */
	public static final String DS_PURPOSE_ShipmentInventoryMovement = "SH";
	/** Mixed = MX */
	public static final String DS_PURPOSE_Mixed = "MX";
	/** Set Purpose.
		@param DS_Purpose Purpose	  */
	public void setDS_Purpose (String DS_Purpose)
	{

		set_Value (COLUMNNAME_DS_Purpose, DS_Purpose);
	}

	/** Get Purpose.
		@return Purpose	  */
	public String getDS_Purpose () 
	{
		return (String)get_Value(COLUMNNAME_DS_Purpose);
	}

	public I_DS_RouteMaster getDS_RouteMaster() throws RuntimeException
    {
		return (I_DS_RouteMaster)MTable.get(getCtx(), I_DS_RouteMaster.Table_Name)
			.getPO(getDS_RouteMaster_ID(), get_TrxName());	}

	/** Set Route Master.
		@param DS_RouteMaster_ID Route Master	  */
	public void setDS_RouteMaster_ID (int DS_RouteMaster_ID)
	{
		if (DS_RouteMaster_ID < 1) 
			set_Value (COLUMNNAME_DS_RouteMaster_ID, null);
		else 
			set_Value (COLUMNNAME_DS_RouteMaster_ID, Integer.valueOf(DS_RouteMaster_ID));
	}

	/** Get Route Master.
		@return Route Master	  */
	public int getDS_RouteMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RouteMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set To Country.
		@param DS_ToCountry_ID To Country	  */
	public void setDS_ToCountry_ID (int DS_ToCountry_ID)
	{
		if (DS_ToCountry_ID < 1) 
			set_Value (COLUMNNAME_DS_ToCountry_ID, null);
		else 
			set_Value (COLUMNNAME_DS_ToCountry_ID, Integer.valueOf(DS_ToCountry_ID));
	}

	/** Get To Country.
		@return To Country	  */
	public int getDS_ToCountry_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ToCountry_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Scheduled From.
		@param DS_TripDateFrom Date Scheduled From	  */
	public void setDS_TripDateFrom (Timestamp DS_TripDateFrom)
	{
		set_Value (COLUMNNAME_DS_TripDateFrom, DS_TripDateFrom);
	}

	/** Get Date Scheduled From.
		@return Date Scheduled From	  */
	public Timestamp getDS_TripDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_TripDateFrom);
	}

	/** Set Date Scheduled To.
		@param DS_TripDateTo Date Scheduled To	  */
	public void setDS_TripDateTo (Timestamp DS_TripDateTo)
	{
		set_Value (COLUMNNAME_DS_TripDateTo, DS_TripDateTo);
	}

	/** Get Date Scheduled To.
		@return Date Scheduled To	  */
	public Timestamp getDS_TripDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_TripDateTo);
	}

	public org.compiere.model.I_R_Request getDS_TripSettlementRequest() throws RuntimeException
    {
		return (org.compiere.model.I_R_Request)MTable.get(getCtx(), org.compiere.model.I_R_Request.Table_Name)
			.getPO(getDS_TripSettlementRequest_ID(), get_TrxName());	}

	/** Set Trip Settlement Request.
		@param DS_TripSettlementRequest_ID Trip Settlement Request	  */
	public void setDS_TripSettlementRequest_ID (int DS_TripSettlementRequest_ID)
	{
		if (DS_TripSettlementRequest_ID < 1) 
			set_Value (COLUMNNAME_DS_TripSettlementRequest_ID, null);
		else 
			set_Value (COLUMNNAME_DS_TripSettlementRequest_ID, Integer.valueOf(DS_TripSettlementRequest_ID));
	}

	/** Get Trip Settlement Request.
		@return Trip Settlement Request	  */
	public int getDS_TripSettlementRequest_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TripSettlementRequest_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vehicle Number.
		@param DS_VehicleNumber Vehicle Number	  */
	public void setDS_VehicleNumber (String DS_VehicleNumber)
	{
		set_Value (COLUMNNAME_DS_VehicleNumber, DS_VehicleNumber);
	}

	/** Get Vehicle Number.
		@return Vehicle Number	  */
	public String getDS_VehicleNumber () 
	{
		return (String)get_Value(COLUMNNAME_DS_VehicleNumber);
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
			set_Value (COLUMNNAME_R_Request_ID, null);
		else 
			set_Value (COLUMNNAME_R_Request_ID, Integer.valueOf(R_Request_ID));
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