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

/** Generated Interface for DS_RequiredDocForShipment
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_RequiredDocForShipment 
{

    /** TableName=DS_RequiredDocForShipment */
    public static final String Table_Name = "DS_RequiredDocForShipment";

    /** AD_Table_ID=1000152 */
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

    /** Column name C_Order_ID */
    public static final String COLUMNNAME_C_Order_ID = "C_Order_ID";

	/** Set Order.
	  * Order
	  */
	public void setC_Order_ID (int C_Order_ID);

	/** Get Order.
	  * Order
	  */
	public int getC_Order_ID();

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException;

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

    /** Column name DS_RequiredDocForShipment_ID */
    public static final String COLUMNNAME_DS_RequiredDocForShipment_ID = "DS_RequiredDocForShipment_ID";

	/** Set Required Documents On SO	  */
	public void setDS_RequiredDocForShipment_ID (int DS_RequiredDocForShipment_ID);

	/** Get Required Documents On SO	  */
	public int getDS_RequiredDocForShipment_ID();

    /** Column name DS_RequiredDocForShipment_UU */
    public static final String COLUMNNAME_DS_RequiredDocForShipment_UU = "DS_RequiredDocForShipment_UU";

	/** Set DS_RequiredDocForShipment_UU	  */
	public void setDS_RequiredDocForShipment_UU (String DS_RequiredDocForShipment_UU);

	/** Get DS_RequiredDocForShipment_UU	  */
	public String getDS_RequiredDocForShipment_UU();

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
