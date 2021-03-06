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

/** Generated Interface for GS_HR_Attendance_Det
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_Attendance_Det 
{

    /** TableName=GS_HR_Attendance_Det */
    public static final String Table_Name = "GS_HR_Attendance_Det";

    /** AD_Table_ID=1000191 */
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

    /** Column name GS_HR_AbsentDays */
    public static final String COLUMNNAME_GS_HR_AbsentDays = "GS_HR_AbsentDays";

	/** Set Absent Days	  */
	public void setGS_HR_AbsentDays (BigDecimal GS_HR_AbsentDays);

	/** Get Absent Days	  */
	public BigDecimal getGS_HR_AbsentDays();

    /** Column name GS_HR_Attendance_Det_ID */
    public static final String COLUMNNAME_GS_HR_Attendance_Det_ID = "GS_HR_Attendance_Det_ID";

	/** Set Attendance Details	  */
	public void setGS_HR_Attendance_Det_ID (int GS_HR_Attendance_Det_ID);

	/** Get Attendance Details	  */
	public int getGS_HR_Attendance_Det_ID();

    /** Column name GS_HR_Attendance_Det_UU */
    public static final String COLUMNNAME_GS_HR_Attendance_Det_UU = "GS_HR_Attendance_Det_UU";

	/** Set GS_HR_Attendance_Det_UU	  */
	public void setGS_HR_Attendance_Det_UU (String GS_HR_Attendance_Det_UU);

	/** Get GS_HR_Attendance_Det_UU	  */
	public String getGS_HR_Attendance_Det_UU();

    /** Column name GS_HR_ConsolidateWorkingHours */
    public static final String COLUMNNAME_GS_HR_ConsolidateWorkingHours = "GS_HR_ConsolidateWorkingHours";

	/** Set Consolidated Working Hours.
	  * Consolidated Working Hours
	  */
	public void setGS_HR_ConsolidateWorkingHours (BigDecimal GS_HR_ConsolidateWorkingHours);

	/** Get Consolidated Working Hours.
	  * Consolidated Working Hours
	  */
	public BigDecimal getGS_HR_ConsolidateWorkingHours();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

    /** Column name GS_HR_Holidays */
    public static final String COLUMNNAME_GS_HR_Holidays = "GS_HR_Holidays";

	/** Set Holidays	  */
	public void setGS_HR_Holidays (BigDecimal GS_HR_Holidays);

	/** Get Holidays	  */
	public BigDecimal getGS_HR_Holidays();

    /** Column name GS_HR_MonthlyAttendance_ID */
    public static final String COLUMNNAME_GS_HR_MonthlyAttendance_ID = "GS_HR_MonthlyAttendance_ID";

	/** Set Monthly Attendance	  */
	public void setGS_HR_MonthlyAttendance_ID (int GS_HR_MonthlyAttendance_ID);

	/** Get Monthly Attendance	  */
	public int getGS_HR_MonthlyAttendance_ID();

	public I_GS_HR_MonthlyAttendance getGS_HR_MonthlyAttendance() throws RuntimeException;

    /** Column name GS_HR_OverTime1 */
    public static final String COLUMNNAME_GS_HR_OverTime1 = "GS_HR_OverTime1";

	/** Set Overt Time 1	  */
	public void setGS_HR_OverTime1 (BigDecimal GS_HR_OverTime1);

	/** Get Overt Time 1	  */
	public BigDecimal getGS_HR_OverTime1();

    /** Column name GS_HR_OverTime2 */
    public static final String COLUMNNAME_GS_HR_OverTime2 = "GS_HR_OverTime2";

	/** Set Overt Time 2	  */
	public void setGS_HR_OverTime2 (BigDecimal GS_HR_OverTime2);

	/** Get Overt Time 2	  */
	public BigDecimal getGS_HR_OverTime2();

    /** Column name GS_HR_OverTime3 */
    public static final String COLUMNNAME_GS_HR_OverTime3 = "GS_HR_OverTime3";

	/** Set Overt Time 3	  */
	public void setGS_HR_OverTime3 (BigDecimal GS_HR_OverTime3);

	/** Get Overt Time 3	  */
	public BigDecimal getGS_HR_OverTime3();

    /** Column name GS_HR_OverTime4 */
    public static final String COLUMNNAME_GS_HR_OverTime4 = "GS_HR_OverTime4";

	/** Set Overt Time 4	  */
	public void setGS_HR_OverTime4 (BigDecimal GS_HR_OverTime4);

	/** Get Overt Time 4	  */
	public BigDecimal getGS_HR_OverTime4();

    /** Column name GS_HR_OverTime5 */
    public static final String COLUMNNAME_GS_HR_OverTime5 = "GS_HR_OverTime5";

	/** Set Overt Time 5	  */
	public void setGS_HR_OverTime5 (BigDecimal GS_HR_OverTime5);

	/** Get Overt Time 5	  */
	public BigDecimal getGS_HR_OverTime5();

    /** Column name GS_HR_PresentDays */
    public static final String COLUMNNAME_GS_HR_PresentDays = "GS_HR_PresentDays";

	/** Set Present Days	  */
	public void setGS_HR_PresentDays (BigDecimal GS_HR_PresentDays);

	/** Get Present Days	  */
	public BigDecimal getGS_HR_PresentDays();

    /** Column name GS_HR_SalaryMonths_ID */
    public static final String COLUMNNAME_GS_HR_SalaryMonths_ID = "GS_HR_SalaryMonths_ID";

	/** Set Salary Months	  */
	public void setGS_HR_SalaryMonths_ID (int GS_HR_SalaryMonths_ID);

	/** Get Salary Months	  */
	public int getGS_HR_SalaryMonths_ID();

	public I_GS_HR_SalaryMonths getGS_HR_SalaryMonths() throws RuntimeException;

    /** Column name GS_HR_TotalWorkingHours */
    public static final String COLUMNNAME_GS_HR_TotalWorkingHours = "GS_HR_TotalWorkingHours";

	/** Set Total Working Hours For Month.
	  * Total Working Hours For Month
	  */
	public void setGS_HR_TotalWorkingHours (BigDecimal GS_HR_TotalWorkingHours);

	/** Get Total Working Hours For Month.
	  * Total Working Hours For Month
	  */
	public BigDecimal getGS_HR_TotalWorkingHours();

    /** Column name GS_HR_TotalWorkingMinutes */
    public static final String COLUMNNAME_GS_HR_TotalWorkingMinutes = "GS_HR_TotalWorkingMinutes";

	/** Set Total Working Minutes.
	  * Total Working Minutes
	  */
	public void setGS_HR_TotalWorkingMinutes (BigDecimal GS_HR_TotalWorkingMinutes);

	/** Get Total Working Minutes.
	  * Total Working Minutes
	  */
	public BigDecimal getGS_HR_TotalWorkingMinutes();

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
