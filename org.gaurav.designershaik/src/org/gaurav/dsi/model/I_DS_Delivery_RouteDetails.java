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

/** Generated Interface for DS_Delivery_RouteDetails
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_Delivery_RouteDetails 
{

    /** TableName=DS_Delivery_RouteDetails */
    public static final String Table_Name = "DS_Delivery_RouteDetails";

    /** AD_Table_ID=1000034 */
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

    /** Column name C_City_ID */
    public static final String COLUMNNAME_C_City_ID = "C_City_ID";

	/** Set City.
	  * City
	  */
	public void setC_City_ID (int C_City_ID);

	/** Get City.
	  * City
	  */
	public int getC_City_ID();

	public org.compiere.model.I_C_City getC_City() throws RuntimeException;

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

    /** Column name DocumentNote */
    public static final String COLUMNNAME_DocumentNote = "DocumentNote";

	/** Set Document Note.
	  * Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote);

	/** Get Document Note.
	  * Additional information for a Document
	  */
	public String getDocumentNote();

    /** Column name DS_AgentMaster_ID */
    public static final String COLUMNNAME_DS_AgentMaster_ID = "DS_AgentMaster_ID";

	/** Set Agent Master	  */
	public void setDS_AgentMaster_ID (int DS_AgentMaster_ID);

	/** Get Agent Master	  */
	public int getDS_AgentMaster_ID();

	public I_DS_AgentMaster getDS_AgentMaster() throws RuntimeException;

    /** Column name DS_Delivery_RouteDetails_ID */
    public static final String COLUMNNAME_DS_Delivery_RouteDetails_ID = "DS_Delivery_RouteDetails_ID";

	/** Set Route Details	  */
	public void setDS_Delivery_RouteDetails_ID (int DS_Delivery_RouteDetails_ID);

	/** Get Route Details	  */
	public int getDS_Delivery_RouteDetails_ID();

    /** Column name DS_Delivery_RouteDetails_UU */
    public static final String COLUMNNAME_DS_Delivery_RouteDetails_UU = "DS_Delivery_RouteDetails_UU";

	/** Set DS_Delivery_RouteDetails_UU	  */
	public void setDS_Delivery_RouteDetails_UU (String DS_Delivery_RouteDetails_UU);

	/** Get DS_Delivery_RouteDetails_UU	  */
	public String getDS_Delivery_RouteDetails_UU();

    /** Column name DS_Delivery_Trips_ID */
    public static final String COLUMNNAME_DS_Delivery_Trips_ID = "DS_Delivery_Trips_ID";

	/** Set Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID);

	/** Get Delivery trips	  */
	public int getDS_Delivery_Trips_ID();

	public I_DS_Delivery_Trips getDS_Delivery_Trips() throws RuntimeException;

    /** Column name DS_IsReturnRoute */
    public static final String COLUMNNAME_DS_IsReturnRoute = "DS_IsReturnRoute";

	/** Set Return Route	  */
	public void setDS_IsReturnRoute (boolean DS_IsReturnRoute);

	/** Get Return Route	  */
	public boolean isDS_IsReturnRoute();

    /** Column name DS_IsTransitPoint */
    public static final String COLUMNNAME_DS_IsTransitPoint = "DS_IsTransitPoint";

	/** Set Transit Point	  */
	public void setDS_IsTransitPoint (boolean DS_IsTransitPoint);

	/** Get Transit Point	  */
	public boolean isDS_IsTransitPoint();

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

    /** Column name LineNo */
    public static final String COLUMNNAME_LineNo = "LineNo";

	/** Set Line.
	  * Line No
	  */
	public void setLineNo (int LineNo);

	/** Get Line.
	  * Line No
	  */
	public int getLineNo();

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
