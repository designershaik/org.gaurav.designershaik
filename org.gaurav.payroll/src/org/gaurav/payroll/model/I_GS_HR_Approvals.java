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

/** Generated Interface for GS_HR_Approvals
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_Approvals 
{

    /** TableName=GS_HR_Approvals */
    public static final String Table_Name = "GS_HR_Approvals";

    /** AD_Table_ID=1000132 */
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

    /** Column name DS_DateApproved */
    public static final String COLUMNNAME_DS_DateApproved = "DS_DateApproved";

	/** Set Date Approved/Submitted.
	  * Date Approved/Submitted
	  */
	public void setDS_DateApproved (Timestamp DS_DateApproved);

	/** Get Date Approved/Submitted.
	  * Date Approved/Submitted
	  */
	public Timestamp getDS_DateApproved();

    /** Column name DSI_IsApproved */
    public static final String COLUMNNAME_DSI_IsApproved = "DSI_IsApproved";

	/** Set Approve	  */
	public void setDSI_IsApproved (boolean DSI_IsApproved);

	/** Get Approve	  */
	public boolean isDSI_IsApproved();

    /** Column name GS_HR_Approval_ID */
    public static final String COLUMNNAME_GS_HR_Approval_ID = "GS_HR_Approval_ID";

	/** Set Current Approval	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID);

	/** Get Current Approval	  */
	public int getGS_HR_Approval_ID();

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException;

    /** Column name GS_HR_Approvals_ID */
    public static final String COLUMNNAME_GS_HR_Approvals_ID = "GS_HR_Approvals_ID";

	/** Set Approvals	  */
	public void setGS_HR_Approvals_ID (int GS_HR_Approvals_ID);

	/** Get Approvals	  */
	public int getGS_HR_Approvals_ID();

    /** Column name GS_HR_Approvals_UU */
    public static final String COLUMNNAME_GS_HR_Approvals_UU = "GS_HR_Approvals_UU";

	/** Set GS_HR_Approvals_UU	  */
	public void setGS_HR_Approvals_UU (String GS_HR_Approvals_UU);

	/** Get GS_HR_Approvals_UU	  */
	public String getGS_HR_Approvals_UU();

    /** Column name GS_HR_EmployeeAdvance_ID */
    public static final String COLUMNNAME_GS_HR_EmployeeAdvance_ID = "GS_HR_EmployeeAdvance_ID";

	/** Set Employee Advance	  */
	public void setGS_HR_EmployeeAdvance_ID (int GS_HR_EmployeeAdvance_ID);

	/** Get Employee Advance	  */
	public int getGS_HR_EmployeeAdvance_ID();

	public I_GS_HR_EmployeeAdvance getGS_HR_EmployeeAdvance() throws RuntimeException;

    /** Column name GS_HR_LeaveApplication_ID */
    public static final String COLUMNNAME_GS_HR_LeaveApplication_ID = "GS_HR_LeaveApplication_ID";

	/** Set Leave Application	  */
	public void setGS_HR_LeaveApplication_ID (int GS_HR_LeaveApplication_ID);

	/** Get Leave Application	  */
	public int getGS_HR_LeaveApplication_ID();

	public I_GS_HR_LeaveApplication getGS_HR_LeaveApplication() throws RuntimeException;

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
