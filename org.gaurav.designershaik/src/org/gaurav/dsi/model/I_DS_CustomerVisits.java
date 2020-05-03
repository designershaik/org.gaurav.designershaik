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

/** Generated Interface for DS_CustomerVisits
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_DS_CustomerVisits 
{

    /** TableName=DS_CustomerVisits */
    public static final String Table_Name = "DS_CustomerVisits";

    /** AD_Table_ID=1000125 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

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

    /** Column name DS_Customer_ID */
    public static final String COLUMNNAME_DS_Customer_ID = "DS_Customer_ID";

	/** Set Customer	  */
	public void setDS_Customer_ID (int DS_Customer_ID);

	/** Get Customer	  */
	public int getDS_Customer_ID();

	public org.compiere.model.I_C_BPartner getDS_Customer() throws RuntimeException;

    /** Column name DS_CustomerVisits_ID */
    public static final String COLUMNNAME_DS_CustomerVisits_ID = "DS_CustomerVisits_ID";

	/** Set Customer Visits	  */
	public void setDS_CustomerVisits_ID (int DS_CustomerVisits_ID);

	/** Get Customer Visits	  */
	public int getDS_CustomerVisits_ID();

    /** Column name DS_CustomerVisits_UU */
    public static final String COLUMNNAME_DS_CustomerVisits_UU = "DS_CustomerVisits_UU";

	/** Set DS_CustomerVisits_UU	  */
	public void setDS_CustomerVisits_UU (String DS_CustomerVisits_UU);

	/** Get DS_CustomerVisits_UU	  */
	public String getDS_CustomerVisits_UU();

    /** Column name DS_Delivery_Trips_ID */
    public static final String COLUMNNAME_DS_Delivery_Trips_ID = "DS_Delivery_Trips_ID";

	/** Set Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID);

	/** Get Delivery trips	  */
	public int getDS_Delivery_Trips_ID();

	public I_DS_Delivery_Trips getDS_Delivery_Trips() throws RuntimeException;

    /** Column name DS_Lead_ID */
    public static final String COLUMNNAME_DS_Lead_ID = "DS_Lead_ID";

	/** Set Lead.
	  * Lead
	  */
	public void setDS_Lead_ID (int DS_Lead_ID);

	/** Get Lead.
	  * Lead
	  */
	public int getDS_Lead_ID();

	public org.compiere.model.I_AD_User getDS_Lead() throws RuntimeException;

    /** Column name DS_ToCountry_ID */
    public static final String COLUMNNAME_DS_ToCountry_ID = "DS_ToCountry_ID";

	/** Set To Country	  */
	public void setDS_ToCountry_ID (int DS_ToCountry_ID);

	/** Get To Country	  */
	public int getDS_ToCountry_ID();

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
