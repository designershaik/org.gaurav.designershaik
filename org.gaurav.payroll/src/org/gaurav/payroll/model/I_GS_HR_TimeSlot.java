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

/** Generated Interface for GS_HR_TimeSlot
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_TimeSlot 
{

    /** TableName=GS_HR_TimeSlot */
    public static final String Table_Name = "GS_HR_TimeSlot";

    /** AD_Table_ID=1000263 */
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

    /** Column name DS_In */
    public static final String COLUMNNAME_DS_In = "DS_In";

	/** Set In Time	  */
	public void setDS_In (Timestamp DS_In);

	/** Get In Time	  */
	public Timestamp getDS_In();

    /** Column name DS_Out */
    public static final String COLUMNNAME_DS_Out = "DS_Out";

	/** Set Out Time	  */
	public void setDS_Out (Timestamp DS_Out);

	/** Get Out Time	  */
	public Timestamp getDS_Out();

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

    /** Column name GS_HR_GracePeriod */
    public static final String COLUMNNAME_GS_HR_GracePeriod = "GS_HR_GracePeriod";

	/** Set Grace Period.
	  * Grace Period
	  */
	public void setGS_HR_GracePeriod (BigDecimal GS_HR_GracePeriod);

	/** Get Grace Period.
	  * Grace Period
	  */
	public BigDecimal getGS_HR_GracePeriod();

    /** Column name GS_HR_TimeSlot_Group_ID */
    public static final String COLUMNNAME_GS_HR_TimeSlot_Group_ID = "GS_HR_TimeSlot_Group_ID";

	/** Set Time Slot	  */
	public void setGS_HR_TimeSlot_Group_ID (int GS_HR_TimeSlot_Group_ID);

	/** Get Time Slot	  */
	public int getGS_HR_TimeSlot_Group_ID();

	public I_GS_HR_TimeSlot_Group getGS_HR_TimeSlot_Group() throws RuntimeException;

    /** Column name GS_HR_TimeSlot_ID */
    public static final String COLUMNNAME_GS_HR_TimeSlot_ID = "GS_HR_TimeSlot_ID";

	/** Set Working Time	  */
	public void setGS_HR_TimeSlot_ID (int GS_HR_TimeSlot_ID);

	/** Get Working Time	  */
	public int getGS_HR_TimeSlot_ID();

    /** Column name GS_HR_TimeSlot_UU */
    public static final String COLUMNNAME_GS_HR_TimeSlot_UU = "GS_HR_TimeSlot_UU";

	/** Set GS_HR_TimeSlot_UU	  */
	public void setGS_HR_TimeSlot_UU (String GS_HR_TimeSlot_UU);

	/** Get GS_HR_TimeSlot_UU	  */
	public String getGS_HR_TimeSlot_UU();

    /** Column name GS_HR_WeeklyOff */
    public static final String COLUMNNAME_GS_HR_WeeklyOff = "GS_HR_WeeklyOff";

	/** Set Weekly Off	  */
	public void setGS_HR_WeeklyOff (boolean GS_HR_WeeklyOff);

	/** Get Weekly Off	  */
	public boolean isGS_HR_WeeklyOff();

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

    /** Column name ValidFrom */
    public static final String COLUMNNAME_ValidFrom = "ValidFrom";

	/** Set Valid from.
	  * Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom);

	/** Get Valid from.
	  * Valid from including this date (first day)
	  */
	public Timestamp getValidFrom();

    /** Column name ValidTo */
    public static final String COLUMNNAME_ValidTo = "ValidTo";

	/** Set Valid to.
	  * Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo);

	/** Get Valid to.
	  * Valid to including this date (last day)
	  */
	public Timestamp getValidTo();

    /** Column name WeekDay */
    public static final String COLUMNNAME_WeekDay = "WeekDay";

	/** Set Day of the Week.
	  * Day of the Week
	  */
	public void setWeekDay (int WeekDay);

	/** Get Day of the Week.
	  * Day of the Week
	  */
	public int getWeekDay();
}
