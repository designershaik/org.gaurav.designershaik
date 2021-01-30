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

/** Generated Interface for GS_HR_DailyAttendance_Log
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_DailyAttendance_Log 
{

    /** TableName=GS_HR_DailyAttendance_Log */
    public static final String Table_Name = "GS_HR_DailyAttendance_Log";

    /** AD_Table_ID=1000099 */
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

    /** Column name GS_HR_DailyAttendance_Log_ID */
    public static final String COLUMNNAME_GS_HR_DailyAttendance_Log_ID = "GS_HR_DailyAttendance_Log_ID";

	/** Set Daily Attendance Log	  */
	public void setGS_HR_DailyAttendance_Log_ID (int GS_HR_DailyAttendance_Log_ID);

	/** Get Daily Attendance Log	  */
	public int getGS_HR_DailyAttendance_Log_ID();

    /** Column name GS_HR_DailyAttendance_Log_UU */
    public static final String COLUMNNAME_GS_HR_DailyAttendance_Log_UU = "GS_HR_DailyAttendance_Log_UU";

	/** Set GS_HR_DailyAttendance_Log_UU	  */
	public void setGS_HR_DailyAttendance_Log_UU (String GS_HR_DailyAttendance_Log_UU);

	/** Get GS_HR_DailyAttendance_Log_UU	  */
	public String getGS_HR_DailyAttendance_Log_UU();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

    /** Column name GS_HR_TerminalDetails_ID */
    public static final String COLUMNNAME_GS_HR_TerminalDetails_ID = "GS_HR_TerminalDetails_ID";

	/** Set Attendance Terminals	  */
	public void setGS_HR_TerminalDetails_ID (int GS_HR_TerminalDetails_ID);

	/** Get Attendance Terminals	  */
	public int getGS_HR_TerminalDetails_ID();

	public I_GS_HR_TerminalDetails getGS_HR_TerminalDetails() throws RuntimeException;

    /** Column name GS_HR_TerminalSN */
    public static final String COLUMNNAME_GS_HR_TerminalSN = "GS_HR_TerminalSN";

	/** Set Terminal SN	  */
	public void setGS_HR_TerminalSN (String GS_HR_TerminalSN);

	/** Get Terminal SN	  */
	public String getGS_HR_TerminalSN();

    /** Column name GS_Hour */
    public static final String COLUMNNAME_GS_Hour = "GS_Hour";

	/** Set Hour.
	  * Hour of the Day
	  */
	public void setGS_Hour (BigDecimal GS_Hour);

	/** Get Hour.
	  * Hour of the Day
	  */
	public BigDecimal getGS_Hour();

    /** Column name GS_LogID */
    public static final String COLUMNNAME_GS_LogID = "GS_LogID";

	/** Set Log ID.
	  * Unique ID from the punching machine for the Logs
	  */
	public void setGS_LogID (int GS_LogID);

	/** Get Log ID.
	  * Unique ID from the punching machine for the Logs
	  */
	public int getGS_LogID();

    /** Column name GS_Minutes */
    public static final String COLUMNNAME_GS_Minutes = "GS_Minutes";

	/** Set Minutes.
	  * Minute of the Hour
	  */
	public void setGS_Minutes (int GS_Minutes);

	/** Get Minutes.
	  * Minute of the Hour
	  */
	public int getGS_Minutes();

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

    /** Column name GS_Seconds */
    public static final String COLUMNNAME_GS_Seconds = "GS_Seconds";

	/** Set Seconds.
	  * Seconds of the Hour
	  */
	public void setGS_Seconds (int GS_Seconds);

	/** Get Seconds.
	  * Seconds of the Hour
	  */
	public int getGS_Seconds();

    /** Column name GS_TriggerType */
    public static final String COLUMNNAME_GS_TriggerType = "GS_TriggerType";

	/** Set Trigger Type.
	  * Punch either In/Out
	  */
	public void setGS_TriggerType (String GS_TriggerType);

	/** Get Trigger Type.
	  * Punch either In/Out
	  */
	public String getGS_TriggerType();

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
