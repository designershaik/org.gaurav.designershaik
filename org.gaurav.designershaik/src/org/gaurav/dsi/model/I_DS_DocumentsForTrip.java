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

/** Generated Interface for DS_DocumentsForTrip
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_DocumentsForTrip 
{

    /** TableName=DS_DocumentsForTrip */
    public static final String Table_Name = "DS_DocumentsForTrip";

    /** AD_Table_ID=1000148 */
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

    /** Column name DS_Delivery_Trips_ID */
    public static final String COLUMNNAME_DS_Delivery_Trips_ID = "DS_Delivery_Trips_ID";

	/** Set Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID);

	/** Get Delivery trips	  */
	public int getDS_Delivery_Trips_ID();

	public I_DS_Delivery_Trips getDS_Delivery_Trips() throws RuntimeException;

    /** Column name DS_DocumentsForTrip_ID */
    public static final String COLUMNNAME_DS_DocumentsForTrip_ID = "DS_DocumentsForTrip_ID";

	/** Set Required Documents	  */
	public void setDS_DocumentsForTrip_ID (int DS_DocumentsForTrip_ID);

	/** Get Required Documents	  */
	public int getDS_DocumentsForTrip_ID();

    /** Column name DS_DocumentsForTrip_UU */
    public static final String COLUMNNAME_DS_DocumentsForTrip_UU = "DS_DocumentsForTrip_UU";

	/** Set DS_DocumentsForTrip_UU	  */
	public void setDS_DocumentsForTrip_UU (String DS_DocumentsForTrip_UU);

	/** Get DS_DocumentsForTrip_UU	  */
	public String getDS_DocumentsForTrip_UU();

    /** Column name DS_IsRequired */
    public static final String COLUMNNAME_DS_IsRequired = "DS_IsRequired";

	/** Set Mandatory	  */
	public void setDS_IsRequired (boolean DS_IsRequired);

	/** Get Mandatory	  */
	public boolean isDS_IsRequired();

    /** Column name DS_Prepared */
    public static final String COLUMNNAME_DS_Prepared = "DS_Prepared";

	/** Set Prepared ?.
	  * Prepared ?
	  */
	public void setDS_Prepared (boolean DS_Prepared);

	/** Get Prepared ?.
	  * Prepared ?
	  */
	public boolean isDS_Prepared();

    /** Column name DS_ShipmentDocuments_ID */
    public static final String COLUMNNAME_DS_ShipmentDocuments_ID = "DS_ShipmentDocuments_ID";

	/** Set Document required specific to country	  */
	public void setDS_ShipmentDocuments_ID (int DS_ShipmentDocuments_ID);

	/** Get Document required specific to country	  */
	public int getDS_ShipmentDocuments_ID();

	public I_DS_ShipmentDocuments getDS_ShipmentDocuments() throws RuntimeException;

    /** Column name DS_Stamped */
    public static final String COLUMNNAME_DS_Stamped = "DS_Stamped";

	/** Set Is Stamped.
	  * Is Stamped
	  */
	public void setDS_Stamped (boolean DS_Stamped);

	/** Get Is Stamped.
	  * Is Stamped
	  */
	public boolean isDS_Stamped();

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

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

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
