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

/** Generated Interface for GS_HR_Leave_Master
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_Leave_Master 
{

    /** TableName=GS_HR_Leave_Master */
    public static final String Table_Name = "GS_HR_Leave_Master";

    /** AD_Table_ID=1000106 */
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

    /** Column name GS_HR_LeaveType */
    public static final String COLUMNNAME_GS_HR_LeaveType = "GS_HR_LeaveType";

	/** Set Leave Type.
	  * Leave Type
	  */
	public void setGS_HR_LeaveType (String GS_HR_LeaveType);

	/** Get Leave Type.
	  * Leave Type
	  */
	public String getGS_HR_LeaveType();

    /** Column name GS_HR_Leave_Master_ID */
    public static final String COLUMNNAME_GS_HR_Leave_Master_ID = "GS_HR_Leave_Master_ID";

	/** Set Leave Type	  */
	public void setGS_HR_Leave_Master_ID (int GS_HR_Leave_Master_ID);

	/** Get Leave Type	  */
	public int getGS_HR_Leave_Master_ID();

    /** Column name GS_HR_Leave_Master_UU */
    public static final String COLUMNNAME_GS_HR_Leave_Master_UU = "GS_HR_Leave_Master_UU";

	/** Set GS_HR_Leave_Master_UU	  */
	public void setGS_HR_Leave_Master_UU (String GS_HR_Leave_Master_UU);

	/** Get GS_HR_Leave_Master_UU	  */
	public String getGS_HR_Leave_Master_UU();

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

    /** Column name GS_HR_WithPay */
    public static final String COLUMNNAME_GS_HR_WithPay = "GS_HR_WithPay";

	/** Set With Pay	  */
	public void setGS_HR_WithPay (boolean GS_HR_WithPay);

	/** Get With Pay	  */
	public boolean isGS_HR_WithPay();

    /** Column name GS_IsResetEveryYear */
    public static final String COLUMNNAME_GS_IsResetEveryYear = "GS_IsResetEveryYear";

	/** Set Reset Every Year	  */
	public void setGS_IsResetEveryYear (boolean GS_IsResetEveryYear);

	/** Get Reset Every Year	  */
	public boolean isGS_IsResetEveryYear();

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

    /** Column name IsSummary */
    public static final String COLUMNNAME_IsSummary = "IsSummary";

	/** Set Summary Level.
	  * This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary);

	/** Get Summary Level.
	  * This is a summary entity
	  */
	public boolean isSummary();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();
}
