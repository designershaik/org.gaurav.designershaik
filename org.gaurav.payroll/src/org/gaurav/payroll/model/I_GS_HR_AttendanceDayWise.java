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

/** Generated Interface for GS_HR_AttendanceDayWise
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_AttendanceDayWise 
{

    /** TableName=GS_HR_AttendanceDayWise */
    public static final String Table_Name = "GS_HR_AttendanceDayWise";

    /** AD_Table_ID=1000262 */
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

    /** Column name DS_EarlyOutBy */
    public static final String COLUMNNAME_DS_EarlyOutBy = "DS_EarlyOutBy";

	/** Set Early Out By	  */
	public void setDS_EarlyOutBy (String DS_EarlyOutBy);

	/** Get Early Out By	  */
	public String getDS_EarlyOutBy();

    /** Column name DS_In */
    public static final String COLUMNNAME_DS_In = "DS_In";

	/** Set In Time	  */
	public void setDS_In (Timestamp DS_In);

	/** Get In Time	  */
	public Timestamp getDS_In();

    /** Column name DS_LateBy */
    public static final String COLUMNNAME_DS_LateBy = "DS_LateBy";

	/** Set Late By	  */
	public void setDS_LateBy (String DS_LateBy);

	/** Get Late By	  */
	public String getDS_LateBy();

    /** Column name DS_Out */
    public static final String COLUMNNAME_DS_Out = "DS_Out";

	/** Set Out Time	  */
	public void setDS_Out (Timestamp DS_Out);

	/** Get Out Time	  */
	public Timestamp getDS_Out();

    /** Column name GS_Day */
    public static final String COLUMNNAME_GS_Day = "GS_Day";

	/** Set Day.
	  * Day Of the Month
	  */
	public void setGS_Day (int GS_Day);

	/** Get Day.
	  * Day Of the Month
	  */
	public int getGS_Day();

    /** Column name GS_DayOfTheWeek */
    public static final String COLUMNNAME_GS_DayOfTheWeek = "GS_DayOfTheWeek";

	/** Set Day of the Week.
	  * Day of the Week
	  */
	public void setGS_DayOfTheWeek (String GS_DayOfTheWeek);

	/** Get Day of the Week.
	  * Day of the Week
	  */
	public String getGS_DayOfTheWeek();

    /** Column name GS_HR_Approval_ID */
    public static final String COLUMNNAME_GS_HR_Approval_ID = "GS_HR_Approval_ID";

	/** Set Approved By	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID);

	/** Get Approved By	  */
	public int getGS_HR_Approval_ID();

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException;

    /** Column name GS_HR_AttendanceDayWise_ID */
    public static final String COLUMNNAME_GS_HR_AttendanceDayWise_ID = "GS_HR_AttendanceDayWise_ID";

	/** Set Attendance Day Wise	  */
	public void setGS_HR_AttendanceDayWise_ID (int GS_HR_AttendanceDayWise_ID);

	/** Get Attendance Day Wise	  */
	public int getGS_HR_AttendanceDayWise_ID();

    /** Column name GS_HR_AttendanceDayWise_UU */
    public static final String COLUMNNAME_GS_HR_AttendanceDayWise_UU = "GS_HR_AttendanceDayWise_UU";

	/** Set GS_HR_AttendanceDayWise_UU	  */
	public void setGS_HR_AttendanceDayWise_UU (String GS_HR_AttendanceDayWise_UU);

	/** Get GS_HR_AttendanceDayWise_UU	  */
	public String getGS_HR_AttendanceDayWise_UU();

    /** Column name GS_HR_Attendance_Det_ID */
    public static final String COLUMNNAME_GS_HR_Attendance_Det_ID = "GS_HR_Attendance_Det_ID";

	/** Set Attendance Details	  */
	public void setGS_HR_Attendance_Det_ID (int GS_HR_Attendance_Det_ID);

	/** Get Attendance Details	  */
	public int getGS_HR_Attendance_Det_ID();

	public I_GS_HR_Attendance_Det getGS_HR_Attendance_Det() throws RuntimeException;

    /** Column name GS_HR_EarlyOut */
    public static final String COLUMNNAME_GS_HR_EarlyOut = "GS_HR_EarlyOut";

	/** Set Early Out	  */
	public void setGS_HR_EarlyOut (BigDecimal GS_HR_EarlyOut);

	/** Get Early Out	  */
	public BigDecimal getGS_HR_EarlyOut();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

    /** Column name GS_HR_LateIn */
    public static final String COLUMNNAME_GS_HR_LateIn = "GS_HR_LateIn";

	/** Set Late Arrival(Minutes)	  */
	public void setGS_HR_LateIn (BigDecimal GS_HR_LateIn);

	/** Get Late Arrival(Minutes)	  */
	public BigDecimal getGS_HR_LateIn();

    /** Column name GS_HR_MonthlyAttendance_ID */
    public static final String COLUMNNAME_GS_HR_MonthlyAttendance_ID = "GS_HR_MonthlyAttendance_ID";

	/** Set Monthly Attendance	  */
	public void setGS_HR_MonthlyAttendance_ID (int GS_HR_MonthlyAttendance_ID);

	/** Get Monthly Attendance	  */
	public int getGS_HR_MonthlyAttendance_ID();

	public I_GS_HR_MonthlyAttendance getGS_HR_MonthlyAttendance() throws RuntimeException;

    /** Column name GS_HR_SalaryPaidOn */
    public static final String COLUMNNAME_GS_HR_SalaryPaidOn = "GS_HR_SalaryPaidOn";

	/** Set Salary Paid On.
	  * Salary Paid On
	  */
	public void setGS_HR_SalaryPaidOn (BigDecimal GS_HR_SalaryPaidOn);

	/** Get Salary Paid On.
	  * Salary Paid On
	  */
	public BigDecimal getGS_HR_SalaryPaidOn();

    /** Column name GS_HR_TotalDeduction */
    public static final String COLUMNNAME_GS_HR_TotalDeduction = "GS_HR_TotalDeduction";

	/** Set Total Deduction(Minutes)	  */
	public void setGS_HR_TotalDeduction (BigDecimal GS_HR_TotalDeduction);

	/** Get Total Deduction(Minutes)	  */
	public BigDecimal getGS_HR_TotalDeduction();

    /** Column name GS_Hour */
    public static final String COLUMNNAME_GS_Hour = "GS_Hour";

	/** Set Hour.
	  * Hour of the Day
	  */
	public void setGS_Hour (String GS_Hour);

	/** Get Hour.
	  * Hour of the Day
	  */
	public String getGS_Hour();

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

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
