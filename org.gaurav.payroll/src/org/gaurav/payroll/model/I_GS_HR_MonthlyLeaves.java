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

/** Generated Interface for GS_HR_MonthlyLeaves
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_MonthlyLeaves 
{

    /** TableName=GS_HR_MonthlyLeaves */
    public static final String Table_Name = "GS_HR_MonthlyLeaves";

    /** AD_Table_ID=1000193 */
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

    /** Column name GS_HR_Attendance_Det_ID */
    public static final String COLUMNNAME_GS_HR_Attendance_Det_ID = "GS_HR_Attendance_Det_ID";

	/** Set Attendance Details	  */
	public void setGS_HR_Attendance_Det_ID (int GS_HR_Attendance_Det_ID);

	/** Get Attendance Details	  */
	public int getGS_HR_Attendance_Det_ID();

	public I_GS_HR_Attendance_Det getGS_HR_Attendance_Det() throws RuntimeException;

    /** Column name GS_HR_Leave_Master_ID */
    public static final String COLUMNNAME_GS_HR_Leave_Master_ID = "GS_HR_Leave_Master_ID";

	/** Set Leave Type	  */
	public void setGS_HR_Leave_Master_ID (int GS_HR_Leave_Master_ID);

	/** Get Leave Type	  */
	public int getGS_HR_Leave_Master_ID();

	public I_GS_HR_Leave_Master getGS_HR_Leave_Master() throws RuntimeException;

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

    /** Column name GS_HR_MonthlyAttendance_ID */
    public static final String COLUMNNAME_GS_HR_MonthlyAttendance_ID = "GS_HR_MonthlyAttendance_ID";

	/** Set Monthly Attendance	  */
	public void setGS_HR_MonthlyAttendance_ID (int GS_HR_MonthlyAttendance_ID);

	/** Get Monthly Attendance	  */
	public int getGS_HR_MonthlyAttendance_ID();

	public I_GS_HR_MonthlyAttendance getGS_HR_MonthlyAttendance() throws RuntimeException;

    /** Column name GS_HR_MonthlyLeaves_ID */
    public static final String COLUMNNAME_GS_HR_MonthlyLeaves_ID = "GS_HR_MonthlyLeaves_ID";

	/** Set Leaves Taken In Given Month	  */
	public void setGS_HR_MonthlyLeaves_ID (int GS_HR_MonthlyLeaves_ID);

	/** Get Leaves Taken In Given Month	  */
	public int getGS_HR_MonthlyLeaves_ID();

    /** Column name GS_HR_MonthlyLeaves_UU */
    public static final String COLUMNNAME_GS_HR_MonthlyLeaves_UU = "GS_HR_MonthlyLeaves_UU";

	/** Set GS_HR_MonthlyLeaves_UU	  */
	public void setGS_HR_MonthlyLeaves_UU (String GS_HR_MonthlyLeaves_UU);

	/** Get GS_HR_MonthlyLeaves_UU	  */
	public String getGS_HR_MonthlyLeaves_UU();

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

    /** Column name Year */
    public static final String COLUMNNAME_Year = "Year";

	/** Set Year	  */
	public void setYear (int Year);

	/** Get Year	  */
	public int getYear();
}
