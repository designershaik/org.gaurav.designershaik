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

/** Generated Interface for DS_QA_MO_Rejection
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_QA_MO_Rejection 
{

    /** TableName=DS_QA_MO_Rejection */
    public static final String Table_Name = "DS_QA_MO_Rejection";

    /** AD_Table_ID=1000114 */
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

    /** Column name DS_Manufacturing_QA_BOM_ID */
    public static final String COLUMNNAME_DS_Manufacturing_QA_BOM_ID = "DS_Manufacturing_QA_BOM_ID";

	/** Set QA per Manufacturing BOM Products	  */
	public void setDS_Manufacturing_QA_BOM_ID (int DS_Manufacturing_QA_BOM_ID);

	/** Get QA per Manufacturing BOM Products	  */
	public int getDS_Manufacturing_QA_BOM_ID();

	public I_DS_Manufacturing_QA_BOM getDS_Manufacturing_QA_BOM() throws RuntimeException;

    /** Column name DS_QA_MO_Rejection_ID */
    public static final String COLUMNNAME_DS_QA_MO_Rejection_ID = "DS_QA_MO_Rejection_ID";

	/** Set Rejection Reason	  */
	public void setDS_QA_MO_Rejection_ID (int DS_QA_MO_Rejection_ID);

	/** Get Rejection Reason	  */
	public int getDS_QA_MO_Rejection_ID();

    /** Column name DS_QA_MO_Rejection_UU */
    public static final String COLUMNNAME_DS_QA_MO_Rejection_UU = "DS_QA_MO_Rejection_UU";

	/** Set DS_QA_MO_Rejection_UU	  */
	public void setDS_QA_MO_Rejection_UU (String DS_QA_MO_Rejection_UU);

	/** Get DS_QA_MO_Rejection_UU	  */
	public String getDS_QA_MO_Rejection_UU();

    /** Column name DS_QARejection_Reason_ID */
    public static final String COLUMNNAME_DS_QARejection_Reason_ID = "DS_QARejection_Reason_ID";

	/** Set Reasons for rejection	  */
	public void setDS_QARejection_Reason_ID (int DS_QARejection_Reason_ID);

	/** Get Reasons for rejection	  */
	public int getDS_QARejection_Reason_ID();

	public I_DS_QARejection_Reason getDS_QARejection_Reason() throws RuntimeException;

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

    /** Column name QtyReject */
    public static final String COLUMNNAME_QtyReject = "QtyReject";

	/** Set Qty Reject	  */
	public void setQtyReject (BigDecimal QtyReject);

	/** Get Qty Reject	  */
	public BigDecimal getQtyReject();

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
