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

/** Generated Interface for HR_Contract_Leave
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_HR_Contract_Leave 
{

    /** TableName=HR_Contract_Leave */
    public static final String Table_Name = "HR_Contract_Leave";

    /** AD_Table_ID=1000200 */
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

    /** Column name GS_ContractCalendar_ID */
    public static final String COLUMNNAME_GS_ContractCalendar_ID = "GS_ContractCalendar_ID";

	/** Set Contract Calendar	  */
	public void setGS_ContractCalendar_ID (int GS_ContractCalendar_ID);

	/** Get Contract Calendar	  */
	public int getGS_ContractCalendar_ID();

	public I_GS_ContractCalendar getGS_ContractCalendar() throws RuntimeException;

    /** Column name GS_HR_Leave_Master_ID */
    public static final String COLUMNNAME_GS_HR_Leave_Master_ID = "GS_HR_Leave_Master_ID";

	/** Set Leave Type	  */
	public void setGS_HR_Leave_Master_ID (int GS_HR_Leave_Master_ID);

	/** Get Leave Type	  */
	public int getGS_HR_Leave_Master_ID();

	public I_GS_HR_Leave_Master getGS_HR_Leave_Master() throws RuntimeException;

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

    /** Column name GS_HR_LeavesConsumed */
    public static final String COLUMNNAME_GS_HR_LeavesConsumed = "GS_HR_LeavesConsumed";

	/** Set Leaves Taken.
	  * Total number of leaves taken by employee
	  */
	public void setGS_HR_LeavesConsumed (BigDecimal GS_HR_LeavesConsumed);

	/** Get Leaves Taken.
	  * Total number of leaves taken by employee
	  */
	public BigDecimal getGS_HR_LeavesConsumed();

    /** Column name GS_HR_LeavesRemaining */
    public static final String COLUMNNAME_GS_HR_LeavesRemaining = "GS_HR_LeavesRemaining";

	/** Set Leaves Remaining.
	  * Total number of leaves remained
	  */
	public void setGS_HR_LeavesRemaining (BigDecimal GS_HR_LeavesRemaining);

	/** Get Leaves Remaining.
	  * Total number of leaves remained
	  */
	public BigDecimal getGS_HR_LeavesRemaining();

    /** Column name HR_Contract_ID */
    public static final String COLUMNNAME_HR_Contract_ID = "HR_Contract_ID";

	/** Set Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID);

	/** Get Payroll Contract	  */
	public int getHR_Contract_ID();

	public I_HR_Contract getHR_Contract() throws RuntimeException;

    /** Column name HR_Contract_Leave_ID */
    public static final String COLUMNNAME_HR_Contract_Leave_ID = "HR_Contract_Leave_ID";

	/** Set Leave Details	  */
	public void setHR_Contract_Leave_ID (int HR_Contract_Leave_ID);

	/** Get Leave Details	  */
	public int getHR_Contract_Leave_ID();

    /** Column name HR_Contract_Leave_UU */
    public static final String COLUMNNAME_HR_Contract_Leave_UU = "HR_Contract_Leave_UU";

	/** Set HR_Contract_Leave_UU	  */
	public void setHR_Contract_Leave_UU (String HR_Contract_Leave_UU);

	/** Get HR_Contract_Leave_UU	  */
	public String getHR_Contract_Leave_UU();

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
