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
package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for GS_HR_LeaveApplication
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_LeaveApplication 
{

    /** TableName=GS_HR_LeaveApplication */
    public static final String Table_Name = "GS_HR_LeaveApplication";

    /** AD_Table_ID=1000129 */
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

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name GS_HR_Approval_ID */
    public static final String COLUMNNAME_GS_HR_Approval_ID = "GS_HR_Approval_ID";

	/** Set Current Approval	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID);

	/** Get Current Approval	  */
	public int getGS_HR_Approval_ID();

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException;

    /** Column name GS_HR_DateApplication */
    public static final String COLUMNNAME_GS_HR_DateApplication = "GS_HR_DateApplication";

	/** Set Application Date	  */
	public void setGS_HR_DateApplication (Timestamp GS_HR_DateApplication);

	/** Get Application Date	  */
	public Timestamp getGS_HR_DateApplication();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

    /** Column name GS_HR_Leave_Master_ID */
    public static final String COLUMNNAME_GS_HR_Leave_Master_ID = "GS_HR_Leave_Master_ID";

	/** Set Leave Type	  */
	public void setGS_HR_Leave_Master_ID (int GS_HR_Leave_Master_ID);

	/** Get Leave Type	  */
	public int getGS_HR_Leave_Master_ID();

	public I_GS_HR_Leave_Master getGS_HR_Leave_Master() throws RuntimeException;

    /** Column name GS_HR_LeaveApplication_ID */
    public static final String COLUMNNAME_GS_HR_LeaveApplication_ID = "GS_HR_LeaveApplication_ID";

	/** Set Leave Application	  */
	public void setGS_HR_LeaveApplication_ID (int GS_HR_LeaveApplication_ID);

	/** Get Leave Application	  */
	public int getGS_HR_LeaveApplication_ID();

    /** Column name GS_HR_LeaveApplication_UU */
    public static final String COLUMNNAME_GS_HR_LeaveApplication_UU = "GS_HR_LeaveApplication_UU";

	/** Set GS_HR_LeaveApplication_UU	  */
	public void setGS_HR_LeaveApplication_UU (String GS_HR_LeaveApplication_UU);

	/** Get GS_HR_LeaveApplication_UU	  */
	public String getGS_HR_LeaveApplication_UU();

    /** Column name GS_HR_LeavesAllowed */
    public static final String COLUMNNAME_GS_HR_LeavesAllowed = "GS_HR_LeavesAllowed";

	/** Set Leaves Allowed.
	  * Total number of leaves allowed per employee
	  */
	public void setGS_HR_LeavesAllowed (BigDecimal GS_HR_LeavesAllowed);

	/** Get Leaves Allowed.
	  * Total number of leaves allowed per employee
	  */
	public BigDecimal getGS_HR_LeavesAllowed();

    /** Column name GS_HR_Reason */
    public static final String COLUMNNAME_GS_HR_Reason = "GS_HR_Reason";

	/** Set Reason	  */
	public void setGS_HR_Reason (String GS_HR_Reason);

	/** Get Reason	  */
	public String getGS_HR_Reason();

    /** Column name GS_HR_RequiredLeaves */
    public static final String COLUMNNAME_GS_HR_RequiredLeaves = "GS_HR_RequiredLeaves";

	/** Set Leave Days	  */
	public void setGS_HR_RequiredLeaves (BigDecimal GS_HR_RequiredLeaves);

	/** Get Leave Days	  */
	public BigDecimal getGS_HR_RequiredLeaves();

    /** Column name GS_HR_Submit */
    public static final String COLUMNNAME_GS_HR_Submit = "GS_HR_Submit";

	/** Set Submit For Approval	  */
	public void setGS_HR_Submit (String GS_HR_Submit);

	/** Get Submit For Approval	  */
	public String getGS_HR_Submit();

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

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

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
