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

/** Generated Interface for DS_Aramex_ShipmentTracking
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_Aramex_ShipmentTracking 
{

    /** TableName=DS_Aramex_ShipmentTracking */
    public static final String Table_Name = "DS_Aramex_ShipmentTracking";

    /** AD_Table_ID=1000037 */
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

    /** Column name City */
    public static final String COLUMNNAME_City = "City";

	/** Set City.
	  * Identifies a City
	  */
	public void setCity (String City);

	/** Get City.
	  * Identifies a City
	  */
	public String getCity();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

    /** Column name CountryName */
    public static final String COLUMNNAME_CountryName = "CountryName";

	/** Set Country.
	  * Country Name
	  */
	public void setCountryName (String CountryName);

	/** Get Country.
	  * Country Name
	  */
	public String getCountryName();

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

    /** Column name DateDelivered */
    public static final String COLUMNNAME_DateDelivered = "DateDelivered";

	/** Set Date Delivered.
	  * Date when the product was delivered
	  */
	public void setDateDelivered (Timestamp DateDelivered);

	/** Get Date Delivered.
	  * Date when the product was delivered
	  */
	public Timestamp getDateDelivered();

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

    /** Column name DS_Aramex_ShipmentTracking_ID */
    public static final String COLUMNNAME_DS_Aramex_ShipmentTracking_ID = "DS_Aramex_ShipmentTracking_ID";

	/** Set Shipment tracking	  */
	public void setDS_Aramex_ShipmentTracking_ID (int DS_Aramex_ShipmentTracking_ID);

	/** Get Shipment tracking	  */
	public int getDS_Aramex_ShipmentTracking_ID();

    /** Column name DS_Aramex_ShipmentTracking_UU */
    public static final String COLUMNNAME_DS_Aramex_ShipmentTracking_UU = "DS_Aramex_ShipmentTracking_UU";

	/** Set DS_Aramex_ShipmentTracking_UU	  */
	public void setDS_Aramex_ShipmentTracking_UU (String DS_Aramex_ShipmentTracking_UU);

	/** Get DS_Aramex_ShipmentTracking_UU	  */
	public String getDS_Aramex_ShipmentTracking_UU();

    /** Column name DS_ProblemCode */
    public static final String COLUMNNAME_DS_ProblemCode = "DS_ProblemCode";

	/** Set Problem Code	  */
	public void setDS_ProblemCode (String DS_ProblemCode);

	/** Get Problem Code	  */
	public String getDS_ProblemCode();

    /** Column name DS_ResultKey */
    public static final String COLUMNNAME_DS_ResultKey = "DS_ResultKey";

	/** Set Result Key	  */
	public void setDS_ResultKey (String DS_ResultKey);

	/** Get Result Key	  */
	public String getDS_ResultKey();

    /** Column name DS_Updated */
    public static final String COLUMNNAME_DS_Updated = "DS_Updated";

	/** Set Udpated Date & Time	  */
	public void setDS_Updated (String DS_Updated);

	/** Get Udpated Date & Time	  */
	public String getDS_Updated();

    /** Column name DS_UpdatedCode */
    public static final String COLUMNNAME_DS_UpdatedCode = "DS_UpdatedCode";

	/** Set Updated Code	  */
	public void setDS_UpdatedCode (String DS_UpdatedCode);

	/** Get Updated Code	  */
	public String getDS_UpdatedCode();

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

    /** Column name IsDelivered */
    public static final String COLUMNNAME_IsDelivered = "IsDelivered";

	/** Set Delivered	  */
	public void setIsDelivered (boolean IsDelivered);

	/** Get Delivered	  */
	public boolean isDelivered();

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

    /** Column name LocationComment */
    public static final String COLUMNNAME_LocationComment = "LocationComment";

	/** Set Location comment.
	  * Additional comments or remarks concerning the location
	  */
	public void setLocationComment (String LocationComment);

	/** Get Location comment.
	  * Additional comments or remarks concerning the location
	  */
	public String getLocationComment();

    /** Column name M_InOut_ID */
    public static final String COLUMNNAME_M_InOut_ID = "M_InOut_ID";

	/** Set Shipment/Receipt.
	  * Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID);

	/** Get Shipment/Receipt.
	  * Material Shipment Document
	  */
	public int getM_InOut_ID();

	public org.compiere.model.I_M_InOut getM_InOut() throws RuntimeException;

    /** Column name TrackingNo */
    public static final String COLUMNNAME_TrackingNo = "TrackingNo";

	/** Set Tracking No.
	  * Number to track the shipment
	  */
	public void setTrackingNo (String TrackingNo);

	/** Get Tracking No.
	  * Number to track the shipment
	  */
	public String getTrackingNo();

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
