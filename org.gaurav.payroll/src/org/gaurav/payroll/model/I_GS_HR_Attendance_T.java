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

/** Generated Interface for GS_HR_Attendance_T
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_Attendance_T 
{

    /** TableName=GS_HR_Attendance_T */
    public static final String Table_Name = "GS_HR_Attendance_T";

    /** AD_Table_ID=1000182 */
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

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (int Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public int getCode();

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

    /** Column name DS_In1 */
    public static final String COLUMNNAME_DS_In1 = "DS_In1";

	/** Set In 1	  */
	public void setDS_In1 (Timestamp DS_In1);

	/** Get In 1	  */
	public Timestamp getDS_In1();

    /** Column name DS_In10 */
    public static final String COLUMNNAME_DS_In10 = "DS_In10";

	/** Set In 10	  */
	public void setDS_In10 (Timestamp DS_In10);

	/** Get In 10	  */
	public Timestamp getDS_In10();

    /** Column name DS_In11 */
    public static final String COLUMNNAME_DS_In11 = "DS_In11";

	/** Set In 11	  */
	public void setDS_In11 (Timestamp DS_In11);

	/** Get In 11	  */
	public Timestamp getDS_In11();

    /** Column name DS_In12 */
    public static final String COLUMNNAME_DS_In12 = "DS_In12";

	/** Set In 12	  */
	public void setDS_In12 (Timestamp DS_In12);

	/** Get In 12	  */
	public Timestamp getDS_In12();

    /** Column name DS_In2 */
    public static final String COLUMNNAME_DS_In2 = "DS_In2";

	/** Set In 2	  */
	public void setDS_In2 (Timestamp DS_In2);

	/** Get In 2	  */
	public Timestamp getDS_In2();

    /** Column name DS_In3 */
    public static final String COLUMNNAME_DS_In3 = "DS_In3";

	/** Set In 3	  */
	public void setDS_In3 (Timestamp DS_In3);

	/** Get In 3	  */
	public Timestamp getDS_In3();

    /** Column name DS_In4 */
    public static final String COLUMNNAME_DS_In4 = "DS_In4";

	/** Set In 4	  */
	public void setDS_In4 (Timestamp DS_In4);

	/** Get In 4	  */
	public Timestamp getDS_In4();

    /** Column name DS_In5 */
    public static final String COLUMNNAME_DS_In5 = "DS_In5";

	/** Set In 5	  */
	public void setDS_In5 (Timestamp DS_In5);

	/** Get In 5	  */
	public Timestamp getDS_In5();

    /** Column name DS_In6 */
    public static final String COLUMNNAME_DS_In6 = "DS_In6";

	/** Set In 6	  */
	public void setDS_In6 (Timestamp DS_In6);

	/** Get In 6	  */
	public Timestamp getDS_In6();

    /** Column name DS_In7 */
    public static final String COLUMNNAME_DS_In7 = "DS_In7";

	/** Set In 7	  */
	public void setDS_In7 (Timestamp DS_In7);

	/** Get In 7	  */
	public Timestamp getDS_In7();

    /** Column name DS_In8 */
    public static final String COLUMNNAME_DS_In8 = "DS_In8";

	/** Set In 8	  */
	public void setDS_In8 (Timestamp DS_In8);

	/** Get In 8	  */
	public Timestamp getDS_In8();

    /** Column name DS_In9 */
    public static final String COLUMNNAME_DS_In9 = "DS_In9";

	/** Set In 9	  */
	public void setDS_In9 (Timestamp DS_In9);

	/** Get In 9	  */
	public Timestamp getDS_In9();

    /** Column name DS_Out1 */
    public static final String COLUMNNAME_DS_Out1 = "DS_Out1";

	/** Set Out 1	  */
	public void setDS_Out1 (Timestamp DS_Out1);

	/** Get Out 1	  */
	public Timestamp getDS_Out1();

    /** Column name DS_Out10 */
    public static final String COLUMNNAME_DS_Out10 = "DS_Out10";

	/** Set Out 10	  */
	public void setDS_Out10 (Timestamp DS_Out10);

	/** Get Out 10	  */
	public Timestamp getDS_Out10();

    /** Column name DS_Out11 */
    public static final String COLUMNNAME_DS_Out11 = "DS_Out11";

	/** Set Out 11	  */
	public void setDS_Out11 (Timestamp DS_Out11);

	/** Get Out 11	  */
	public Timestamp getDS_Out11();

    /** Column name DS_Out12 */
    public static final String COLUMNNAME_DS_Out12 = "DS_Out12";

	/** Set Out 12	  */
	public void setDS_Out12 (Timestamp DS_Out12);

	/** Get Out 12	  */
	public Timestamp getDS_Out12();

    /** Column name DS_Out2 */
    public static final String COLUMNNAME_DS_Out2 = "DS_Out2";

	/** Set Out 2	  */
	public void setDS_Out2 (Timestamp DS_Out2);

	/** Get Out 2	  */
	public Timestamp getDS_Out2();

    /** Column name DS_Out3 */
    public static final String COLUMNNAME_DS_Out3 = "DS_Out3";

	/** Set Out 3	  */
	public void setDS_Out3 (Timestamp DS_Out3);

	/** Get Out 3	  */
	public Timestamp getDS_Out3();

    /** Column name DS_Out4 */
    public static final String COLUMNNAME_DS_Out4 = "DS_Out4";

	/** Set Out 4	  */
	public void setDS_Out4 (Timestamp DS_Out4);

	/** Get Out 4	  */
	public Timestamp getDS_Out4();

    /** Column name DS_Out5 */
    public static final String COLUMNNAME_DS_Out5 = "DS_Out5";

	/** Set Out 5	  */
	public void setDS_Out5 (Timestamp DS_Out5);

	/** Get Out 5	  */
	public Timestamp getDS_Out5();

    /** Column name DS_Out6 */
    public static final String COLUMNNAME_DS_Out6 = "DS_Out6";

	/** Set Out 6	  */
	public void setDS_Out6 (Timestamp DS_Out6);

	/** Get Out 6	  */
	public Timestamp getDS_Out6();

    /** Column name DS_Out7 */
    public static final String COLUMNNAME_DS_Out7 = "DS_Out7";

	/** Set Out 7	  */
	public void setDS_Out7 (Timestamp DS_Out7);

	/** Get Out 7	  */
	public Timestamp getDS_Out7();

    /** Column name DS_Out8 */
    public static final String COLUMNNAME_DS_Out8 = "DS_Out8";

	/** Set Out 8	  */
	public void setDS_Out8 (Timestamp DS_Out8);

	/** Get Out 8	  */
	public Timestamp getDS_Out8();

    /** Column name DS_Out9 */
    public static final String COLUMNNAME_DS_Out9 = "DS_Out9";

	/** Set Out 9	  */
	public void setDS_Out9 (Timestamp DS_Out9);

	/** Get Out 9	  */
	public Timestamp getDS_Out9();

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

    /** Column name GS_AMPM */
    public static final String COLUMNNAME_GS_AMPM = "GS_AMPM";

	/** Set AM/PM.
	  * AM/PM
	  */
	public void setGS_AMPM (int GS_AMPM);

	/** Get AM/PM.
	  * AM/PM
	  */
	public int getGS_AMPM();

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

    /** Column name GS_HR_Attendance_T_ID */
    public static final String COLUMNNAME_GS_HR_Attendance_T_ID = "GS_HR_Attendance_T_ID";

	/** Set Exportable Attendance	  */
	public void setGS_HR_Attendance_T_ID (int GS_HR_Attendance_T_ID);

	/** Get Exportable Attendance	  */
	public int getGS_HR_Attendance_T_ID();

    /** Column name GS_HR_Attendance_T_UU */
    public static final String COLUMNNAME_GS_HR_Attendance_T_UU = "GS_HR_Attendance_T_UU";

	/** Set GS_HR_Attendance_T_UU	  */
	public void setGS_HR_Attendance_T_UU (String GS_HR_Attendance_T_UU);

	/** Get GS_HR_Attendance_T_UU	  */
	public String getGS_HR_Attendance_T_UU();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

    /** Column name GS_Month */
    public static final String COLUMNNAME_GS_Month = "GS_Month";

	/** Set Month.
	  * Month
	  */
	public void setGS_Month (int GS_Month);

	/** Get Month.
	  * Month
	  */
	public int getGS_Month();

    /** Column name GS_PunchTime */
    public static final String COLUMNNAME_GS_PunchTime = "GS_PunchTime";

	/** Set Punch Time.
	  * Punching time either In/Out based on the type
	  */
	public void setGS_PunchTime (Timestamp GS_PunchTime);

	/** Get Punch Time.
	  * Punching time either In/Out based on the type
	  */
	public Timestamp getGS_PunchTime();

    /** Column name GS_Year */
    public static final String COLUMNNAME_GS_Year = "GS_Year";

	/** Set Year.
	  * Year
	  */
	public void setGS_Year (int GS_Year);

	/** Get Year.
	  * Year
	  */
	public int getGS_Year();

    /** Column name GenerateList */
    public static final String COLUMNNAME_GenerateList = "GenerateList";

	/** Set Generate List.
	  * Generate List
	  */
	public void setGenerateList (String GenerateList);

	/** Get Generate List.
	  * Generate List
	  */
	public String getGenerateList();

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Payroll Department	  */
	public int getHR_Department_ID();

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

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
