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
package org.gaurav.dsi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DS_Delivery_Trips
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_Delivery_Trips 
{

    /** TableName=DS_Delivery_Trips */
    public static final String Table_Name = "DS_Delivery_Trips";

    /** AD_Table_ID=1000032 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name DS_CashRequest_Amt */
    public static final String COLUMNNAME_DS_CashRequest_Amt = "DS_CashRequest_Amt";

	/** Set Total Cash Request Amt.
	  * Total Cash Request Amt
	  */
	public void setDS_CashRequest_Amt (BigDecimal DS_CashRequest_Amt);

	/** Get Total Cash Request Amt.
	  * Total Cash Request Amt
	  */
	public BigDecimal getDS_CashRequest_Amt();

    /** Column name DS_CreateCashRequest */
    public static final String COLUMNNAME_DS_CreateCashRequest = "DS_CreateCashRequest";

	/** Set Create Cash Request & Draft Settlement.
	  * Create Cash Request & Draft Settlement
	  */
	public void setDS_CreateCashRequest (String DS_CreateCashRequest);

	/** Get Create Cash Request & Draft Settlement.
	  * Create Cash Request & Draft Settlement
	  */
	public String getDS_CreateCashRequest();

    /** Column name DS_Delivery_Trips_ID */
    public static final String COLUMNNAME_DS_Delivery_Trips_ID = "DS_Delivery_Trips_ID";

	/** Set Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID);

	/** Get Delivery trips	  */
	public int getDS_Delivery_Trips_ID();

    /** Column name DS_Delivery_Trips_UU */
    public static final String COLUMNNAME_DS_Delivery_Trips_UU = "DS_Delivery_Trips_UU";

	/** Set DS_Delivery_Trips_UU	  */
	public void setDS_Delivery_Trips_UU (String DS_Delivery_Trips_UU);

	/** Get DS_Delivery_Trips_UU	  */
	public String getDS_Delivery_Trips_UU();

    /** Column name DS_Employee2_ID */
    public static final String COLUMNNAME_DS_Employee2_ID = "DS_Employee2_ID";

	/** Set Employee 2.
	  * Employee 2 for trip schedule
	  */
	public void setDS_Employee2_ID (int DS_Employee2_ID);

	/** Get Employee 2.
	  * Employee 2 for trip schedule
	  */
	public int getDS_Employee2_ID();

	public org.compiere.model.I_C_BPartner getDS_Employee2() throws RuntimeException;

    /** Column name DS_ManifestPrepared */
    public static final String COLUMNNAME_DS_ManifestPrepared = "DS_ManifestPrepared";

	/** Set Manifest Prepared.
	  * Manifest Prepared
	  */
	public void setDS_ManifestPrepared (boolean DS_ManifestPrepared);

	/** Get Manifest Prepared.
	  * Manifest Prepared
	  */
	public boolean isDS_ManifestPrepared();

    /** Column name DS_Product_Vehicle_ID */
    public static final String COLUMNNAME_DS_Product_Vehicle_ID = "DS_Product_Vehicle_ID";

	/** Set Vehicle 2.
	  * Vehicle 2 for trip schedule
	  */
	public void setDS_Product_Vehicle_ID (int DS_Product_Vehicle_ID);

	/** Get Vehicle 2.
	  * Vehicle 2 for trip schedule
	  */
	public int getDS_Product_Vehicle_ID();

	public org.compiere.model.I_M_Product getDS_Product_Vehicle() throws RuntimeException;

    /** Column name DS_Purpose */
    public static final String COLUMNNAME_DS_Purpose = "DS_Purpose";

	/** Set Purpose	  */
	public void setDS_Purpose (String DS_Purpose);

	/** Get Purpose	  */
	public String getDS_Purpose();

    /** Column name DS_RouteMaster_ID */
    public static final String COLUMNNAME_DS_RouteMaster_ID = "DS_RouteMaster_ID";

	/** Set Route Master	  */
	public void setDS_RouteMaster_ID (int DS_RouteMaster_ID);

	/** Get Route Master	  */
	public int getDS_RouteMaster_ID();

	public I_DS_RouteMaster getDS_RouteMaster() throws RuntimeException;

    /** Column name DS_ToCountry_ID */
    public static final String COLUMNNAME_DS_ToCountry_ID = "DS_ToCountry_ID";

	/** Set To Country	  */
	public void setDS_ToCountry_ID (int DS_ToCountry_ID);

	/** Get To Country	  */
	public int getDS_ToCountry_ID();

    /** Column name DS_TripDateFrom */
    public static final String COLUMNNAME_DS_TripDateFrom = "DS_TripDateFrom";

	/** Set Date Scheduled From	  */
	public void setDS_TripDateFrom (Timestamp DS_TripDateFrom);

	/** Get Date Scheduled From	  */
	public Timestamp getDS_TripDateFrom();

    /** Column name DS_TripDateTo */
    public static final String COLUMNNAME_DS_TripDateTo = "DS_TripDateTo";

	/** Set Date Scheduled To	  */
	public void setDS_TripDateTo (Timestamp DS_TripDateTo);

	/** Get Date Scheduled To	  */
	public Timestamp getDS_TripDateTo();

    /** Column name DS_TripSettlementRequest_ID */
    public static final String COLUMNNAME_DS_TripSettlementRequest_ID = "DS_TripSettlementRequest_ID";

	/** Set Trip Settlement Request	  */
	public void setDS_TripSettlementRequest_ID (int DS_TripSettlementRequest_ID);

	/** Get Trip Settlement Request	  */
	public int getDS_TripSettlementRequest_ID();

	public org.compiere.model.I_R_Request getDS_TripSettlementRequest() throws RuntimeException;

    /** Column name DS_VehicleNumber */
    public static final String COLUMNNAME_DS_VehicleNumber = "DS_VehicleNumber";

	/** Set Vehicle Number	  */
	public void setDS_VehicleNumber (String DS_VehicleNumber);

	/** Get Vehicle Number	  */
	public String getDS_VehicleNumber();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name M_FreightCategory_ID */
    public static final String COLUMNNAME_M_FreightCategory_ID = "M_FreightCategory_ID";

	/** Set Freight Category.
	  * Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID);

	/** Get Freight Category.
	  * Category of the Freight
	  */
	public int getM_FreightCategory_ID();

	public org.compiere.model.I_M_FreightCategory getM_FreightCategory() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name Processing */
    public static final String COLUMNNAME_Processing = "Processing";

	/** Set Process Now	  */
	public void setProcessing (boolean Processing);

	/** Get Process Now	  */
	public boolean isProcessing();

    /** Column name R_Request_ID */
    public static final String COLUMNNAME_R_Request_ID = "R_Request_ID";

	/** Set Request.
	  * Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID);

	/** Get Request.
	  * Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID();

	public org.compiere.model.I_R_Request getR_Request() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
