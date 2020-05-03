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

/** Generated Interface for DS_PO_RevisionHistory
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_PO_RevisionHistory 
{

    /** TableName=DS_PO_RevisionHistory */
    public static final String Table_Name = "DS_PO_RevisionHistory";

    /** AD_Table_ID=1000089 */
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

    /** Column name Category */
    public static final String COLUMNNAME_Category = "Category";

	/** Set Category	  */
	public void setCategory (String Category);

	/** Get Category	  */
	public String getCategory();

    /** Column name ChangeAmt */
    public static final String COLUMNNAME_ChangeAmt = "ChangeAmt";

	/** Set ChangeAmt	  */
	public void setChangeAmt (BigDecimal ChangeAmt);

	/** Get ChangeAmt	  */
	public BigDecimal getChangeAmt();

    /** Column name ChangeDate */
    public static final String COLUMNNAME_ChangeDate = "ChangeDate";

	/** Set ChangeDate	  */
	public void setChangeDate (Timestamp ChangeDate);

	/** Get ChangeDate	  */
	public Timestamp getChangeDate();

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

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

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

    /** Column name DS_ApprovedBy_ID */
    public static final String COLUMNNAME_DS_ApprovedBy_ID = "DS_ApprovedBy_ID";

	/** Set Approved By	  */
	public void setDS_ApprovedBy_ID (int DS_ApprovedBy_ID);

	/** Get Approved By	  */
	public int getDS_ApprovedBy_ID();

	public org.compiere.model.I_AD_User getDS_ApprovedBy() throws RuntimeException;

    /** Column name DS_DateApproved */
    public static final String COLUMNNAME_DS_DateApproved = "DS_DateApproved";

	/** Set Date Approved	  */
	public void setDS_DateApproved (Timestamp DS_DateApproved);

	/** Get Date Approved	  */
	public Timestamp getDS_DateApproved();

    /** Column name DS_JustificationForChange */
    public static final String COLUMNNAME_DS_JustificationForChange = "DS_JustificationForChange";

	/** Set Justification for Change	  */
	public void setDS_JustificationForChange (String DS_JustificationForChange);

	/** Get Justification for Change	  */
	public String getDS_JustificationForChange();

    /** Column name DS_PO_RevisionHistory_ID */
    public static final String COLUMNNAME_DS_PO_RevisionHistory_ID = "DS_PO_RevisionHistory_ID";

	/** Set PO Revision history	  */
	public void setDS_PO_RevisionHistory_ID (int DS_PO_RevisionHistory_ID);

	/** Get PO Revision history	  */
	public int getDS_PO_RevisionHistory_ID();

    /** Column name DS_PO_RevisionHistory_UU */
    public static final String COLUMNNAME_DS_PO_RevisionHistory_UU = "DS_PO_RevisionHistory_UU";

	/** Set DS_PO_RevisionHistory_UU	  */
	public void setDS_PO_RevisionHistory_UU (String DS_PO_RevisionHistory_UU);

	/** Get DS_PO_RevisionHistory_UU	  */
	public String getDS_PO_RevisionHistory_UU();

    /** Column name DS_ProposedChanged */
    public static final String COLUMNNAME_DS_ProposedChanged = "DS_ProposedChanged";

	/** Set Proposed Changed	  */
	public void setDS_ProposedChanged (String DS_ProposedChanged);

	/** Get Proposed Changed	  */
	public String getDS_ProposedChanged();

    /** Column name DS_RequestUser_ID */
    public static final String COLUMNNAME_DS_RequestUser_ID = "DS_RequestUser_ID";

	/** Set Requested By	  */
	public void setDS_RequestUser_ID (int DS_RequestUser_ID);

	/** Get Requested By	  */
	public int getDS_RequestUser_ID();

	public org.compiere.model.I_AD_User getDS_RequestUser() throws RuntimeException;

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

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

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
