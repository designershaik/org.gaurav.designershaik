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

/** Generated Interface for DS_Trip_Configuration
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_Trip_Configuration 
{

    /** TableName=DS_Trip_Configuration */
    public static final String Table_Name = "DS_Trip_Configuration";

    /** AD_Table_ID=1000133 */
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

    /** Column name DS_AccommodationCharge_ID */
    public static final String COLUMNNAME_DS_AccommodationCharge_ID = "DS_AccommodationCharge_ID";

	/** Set Accommodation Charge.
	  * Accommodation Charge
	  */
	public void setDS_AccommodationCharge_ID (int DS_AccommodationCharge_ID);

	/** Get Accommodation Charge.
	  * Accommodation Charge
	  */
	public int getDS_AccommodationCharge_ID();

	public org.compiere.model.I_C_Charge getDS_AccommodationCharge() throws RuntimeException;

    /** Column name DS_AllowanceCharge_ID */
    public static final String COLUMNNAME_DS_AllowanceCharge_ID = "DS_AllowanceCharge_ID";

	/** Set Allowance Charge.
	  * Allowance Charge
	  */
	public void setDS_AllowanceCharge_ID (int DS_AllowanceCharge_ID);

	/** Get Allowance Charge.
	  * Allowance Charge
	  */
	public int getDS_AllowanceCharge_ID();

	public org.compiere.model.I_C_Charge getDS_AllowanceCharge() throws RuntimeException;

    /** Column name DS_CashRequest_Type_ID */
    public static final String COLUMNNAME_DS_CashRequest_Type_ID = "DS_CashRequest_Type_ID";

	/** Set Cash Request Type	  */
	public void setDS_CashRequest_Type_ID (int DS_CashRequest_Type_ID);

	/** Get Cash Request Type	  */
	public int getDS_CashRequest_Type_ID();

	public org.compiere.model.I_R_RequestType getDS_CashRequest_Type() throws RuntimeException;

    /** Column name DS_CustomCharge_ID */
    public static final String COLUMNNAME_DS_CustomCharge_ID = "DS_CustomCharge_ID";

	/** Set Custom Charge.
	  * Custom Charge
	  */
	public void setDS_CustomCharge_ID (int DS_CustomCharge_ID);

	/** Get Custom Charge.
	  * Custom Charge
	  */
	public int getDS_CustomCharge_ID();

	public org.compiere.model.I_C_Charge getDS_CustomCharge() throws RuntimeException;

    /** Column name DS_Food_Charge_ID */
    public static final String COLUMNNAME_DS_Food_Charge_ID = "DS_Food_Charge_ID";

	/** Set Food Charge.
	  * Food Charge
	  */
	public void setDS_Food_Charge_ID (int DS_Food_Charge_ID);

	/** Get Food Charge.
	  * Food Charge
	  */
	public int getDS_Food_Charge_ID();

	public org.compiere.model.I_C_Charge getDS_Food_Charge() throws RuntimeException;

    /** Column name DS_Fuel_Charge_ID */
    public static final String COLUMNNAME_DS_Fuel_Charge_ID = "DS_Fuel_Charge_ID";

	/** Set Fuel Charge.
	  * Fuel Charge
	  */
	public void setDS_Fuel_Charge_ID (int DS_Fuel_Charge_ID);

	/** Get Fuel Charge.
	  * Fuel Charge
	  */
	public int getDS_Fuel_Charge_ID();

	public org.compiere.model.I_C_Charge getDS_Fuel_Charge() throws RuntimeException;

    /** Column name DS_MiscExpenseCharges_ID */
    public static final String COLUMNNAME_DS_MiscExpenseCharges_ID = "DS_MiscExpenseCharges_ID";

	/** Set Miscellaneous Expense Charge(Insurance & Others).
	  * Miscellaneous Expense Charge(Insurance & Others)
	  */
	public void setDS_MiscExpenseCharges_ID (int DS_MiscExpenseCharges_ID);

	/** Get Miscellaneous Expense Charge(Insurance & Others).
	  * Miscellaneous Expense Charge(Insurance & Others)
	  */
	public int getDS_MiscExpenseCharges_ID();

	public org.compiere.model.I_C_Charge getDS_MiscExpenseCharges() throws RuntimeException;

    /** Column name DS_Telephone_Charge_ID */
    public static final String COLUMNNAME_DS_Telephone_Charge_ID = "DS_Telephone_Charge_ID";

	/** Set Telephone Charge.
	  * Telephone Charge
	  */
	public void setDS_Telephone_Charge_ID (int DS_Telephone_Charge_ID);

	/** Get Telephone Charge.
	  * Telephone Charge
	  */
	public int getDS_Telephone_Charge_ID();

	public org.compiere.model.I_C_Charge getDS_Telephone_Charge() throws RuntimeException;

    /** Column name DS_TravelCharge_ID */
    public static final String COLUMNNAME_DS_TravelCharge_ID = "DS_TravelCharge_ID";

	/** Set Travel Charge.
	  * Travel Charge
	  */
	public void setDS_TravelCharge_ID (int DS_TravelCharge_ID);

	/** Get Travel Charge.
	  * Travel Charge
	  */
	public int getDS_TravelCharge_ID();

	public org.compiere.model.I_C_Charge getDS_TravelCharge() throws RuntimeException;

    /** Column name DS_Trip_Configuration_ID */
    public static final String COLUMNNAME_DS_Trip_Configuration_ID = "DS_Trip_Configuration_ID";

	/** Set Trip Configurations	  */
	public void setDS_Trip_Configuration_ID (int DS_Trip_Configuration_ID);

	/** Get Trip Configurations	  */
	public int getDS_Trip_Configuration_ID();

    /** Column name DS_Trip_Configuration_UU */
    public static final String COLUMNNAME_DS_Trip_Configuration_UU = "DS_Trip_Configuration_UU";

	/** Set DS_Trip_Configuration_UU	  */
	public void setDS_Trip_Configuration_UU (String DS_Trip_Configuration_UU);

	/** Get DS_Trip_Configuration_UU	  */
	public String getDS_Trip_Configuration_UU();

    /** Column name DS_TripSettlement_Type_ID */
    public static final String COLUMNNAME_DS_TripSettlement_Type_ID = "DS_TripSettlement_Type_ID";

	/** Set Trip Settlement Request Type	  */
	public void setDS_TripSettlement_Type_ID (int DS_TripSettlement_Type_ID);

	/** Get Trip Settlement Request Type	  */
	public int getDS_TripSettlement_Type_ID();

	public org.compiere.model.I_R_RequestType getDS_TripSettlement_Type() throws RuntimeException;

    /** Column name DS_WithinCountryTransport_ID */
    public static final String COLUMNNAME_DS_WithinCountryTransport_ID = "DS_WithinCountryTransport_ID";

	/** Set Transport (Taxi/Metro/Train/Bus) Charge.
	  * Transport (Taxi/Metro/Train/Bus)Charge
	  */
	public void setDS_WithinCountryTransport_ID (int DS_WithinCountryTransport_ID);

	/** Get Transport (Taxi/Metro/Train/Bus) Charge.
	  * Transport (Taxi/Metro/Train/Bus)Charge
	  */
	public int getDS_WithinCountryTransport_ID();

	public org.compiere.model.I_C_Charge getDS_WithinCountryTransport() throws RuntimeException;

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

    /** Column name R_Group_ID */
    public static final String COLUMNNAME_R_Group_ID = "R_Group_ID";

	/** Set Group.
	  * Request Group
	  */
	public void setR_Group_ID (int R_Group_ID);

	/** Get Group.
	  * Request Group
	  */
	public int getR_Group_ID();

	public org.compiere.model.I_R_Group getR_Group() throws RuntimeException;

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
