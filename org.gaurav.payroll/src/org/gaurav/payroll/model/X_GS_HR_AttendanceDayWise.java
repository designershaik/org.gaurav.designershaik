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
/** Generated Model - DO NOT CHANGE */
package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for GS_HR_AttendanceDayWise
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_AttendanceDayWise extends PO implements I_GS_HR_AttendanceDayWise, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_AttendanceDayWise (Properties ctx, int GS_HR_AttendanceDayWise_ID, String trxName)
    {
      super (ctx, GS_HR_AttendanceDayWise_ID, trxName);
      /** if (GS_HR_AttendanceDayWise_ID == 0)
        {
			setGS_HR_AttendanceDayWise_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_AttendanceDayWise (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_GS_HR_AttendanceDayWise[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Early Out By.
		@param DS_EarlyOutBy Early Out By	  */
	public void setDS_EarlyOutBy (String DS_EarlyOutBy)
	{
		set_Value (COLUMNNAME_DS_EarlyOutBy, DS_EarlyOutBy);
	}

	/** Get Early Out By.
		@return Early Out By	  */
	public String getDS_EarlyOutBy () 
	{
		return (String)get_Value(COLUMNNAME_DS_EarlyOutBy);
	}

	/** Set In Time.
		@param DS_In In Time	  */
	public void setDS_In (Timestamp DS_In)
	{
		set_Value (COLUMNNAME_DS_In, DS_In);
	}

	/** Get In Time.
		@return In Time	  */
	public Timestamp getDS_In () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In);
	}

	/** Set Late By.
		@param DS_LateBy Late By	  */
	public void setDS_LateBy (String DS_LateBy)
	{
		set_Value (COLUMNNAME_DS_LateBy, DS_LateBy);
	}

	/** Get Late By.
		@return Late By	  */
	public String getDS_LateBy () 
	{
		return (String)get_Value(COLUMNNAME_DS_LateBy);
	}

	/** Set Out Time.
		@param DS_Out Out Time	  */
	public void setDS_Out (Timestamp DS_Out)
	{
		set_Value (COLUMNNAME_DS_Out, DS_Out);
	}

	/** Get Out Time.
		@return Out Time	  */
	public Timestamp getDS_Out () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out);
	}

	/** Set Day.
		@param GS_Day 
		Day Of the Month
	  */
	public void setGS_Day (int GS_Day)
	{
		set_Value (COLUMNNAME_GS_Day, Integer.valueOf(GS_Day));
	}

	/** Get Day.
		@return Day Of the Month
	  */
	public int getGS_Day () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_Day);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Sunday = Sunday */
	public static final String GS_DAYOFTHEWEEK_Sunday = "Sunday";
	/** Monday = Monday */
	public static final String GS_DAYOFTHEWEEK_Monday = "Monday";
	/** Tuesday = Tuesday */
	public static final String GS_DAYOFTHEWEEK_Tuesday = "Tuesday";
	/** Wednesday = Wednesday */
	public static final String GS_DAYOFTHEWEEK_Wednesday = "Wednesday";
	/** Thursday = Thursday */
	public static final String GS_DAYOFTHEWEEK_Thursday = "Thursday";
	/** Friday = Friday */
	public static final String GS_DAYOFTHEWEEK_Friday = "Friday";
	/** Saturday = Saturday */
	public static final String GS_DAYOFTHEWEEK_Saturday = "Saturday";
	/** Set Day of the Week.
		@param GS_DayOfTheWeek 
		Day of the Week
	  */
	public void setGS_DayOfTheWeek (String GS_DayOfTheWeek)
	{

		set_Value (COLUMNNAME_GS_DayOfTheWeek, GS_DayOfTheWeek);
	}

	/** Get Day of the Week.
		@return Day of the Week
	  */
	public String getGS_DayOfTheWeek () 
	{
		return (String)get_Value(COLUMNNAME_GS_DayOfTheWeek);
	}

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getGS_HR_Approval_ID(), get_TrxName());	}

	/** Set Approved By.
		@param GS_HR_Approval_ID Approved By	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID)
	{
		if (GS_HR_Approval_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, Integer.valueOf(GS_HR_Approval_ID));
	}

	/** Get Approved By.
		@return Approved By	  */
	public int getGS_HR_Approval_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Approval_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Attendance Day Wise.
		@param GS_HR_AttendanceDayWise_ID Attendance Day Wise	  */
	public void setGS_HR_AttendanceDayWise_ID (int GS_HR_AttendanceDayWise_ID)
	{
		if (GS_HR_AttendanceDayWise_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_AttendanceDayWise_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_AttendanceDayWise_ID, Integer.valueOf(GS_HR_AttendanceDayWise_ID));
	}

	/** Get Attendance Day Wise.
		@return Attendance Day Wise	  */
	public int getGS_HR_AttendanceDayWise_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_AttendanceDayWise_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_AttendanceDayWise_UU.
		@param GS_HR_AttendanceDayWise_UU GS_HR_AttendanceDayWise_UU	  */
	public void setGS_HR_AttendanceDayWise_UU (String GS_HR_AttendanceDayWise_UU)
	{
		set_Value (COLUMNNAME_GS_HR_AttendanceDayWise_UU, GS_HR_AttendanceDayWise_UU);
	}

	/** Get GS_HR_AttendanceDayWise_UU.
		@return GS_HR_AttendanceDayWise_UU	  */
	public String getGS_HR_AttendanceDayWise_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_AttendanceDayWise_UU);
	}

	public I_GS_HR_Attendance_Det getGS_HR_Attendance_Det() throws RuntimeException
    {
		return (I_GS_HR_Attendance_Det)MTable.get(getCtx(), I_GS_HR_Attendance_Det.Table_Name)
			.getPO(getGS_HR_Attendance_Det_ID(), get_TrxName());	}

	/** Set Attendance Details.
		@param GS_HR_Attendance_Det_ID Attendance Details	  */
	public void setGS_HR_Attendance_Det_ID (int GS_HR_Attendance_Det_ID)
	{
		if (GS_HR_Attendance_Det_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Attendance_Det_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Attendance_Det_ID, Integer.valueOf(GS_HR_Attendance_Det_ID));
	}

	/** Get Attendance Details.
		@return Attendance Details	  */
	public int getGS_HR_Attendance_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Attendance_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Early Out.
		@param GS_HR_EarlyOut Early Out	  */
	public void setGS_HR_EarlyOut (BigDecimal GS_HR_EarlyOut)
	{
		set_Value (COLUMNNAME_GS_HR_EarlyOut, GS_HR_EarlyOut);
	}

	/** Get Early Out.
		@return Early Out	  */
	public BigDecimal getGS_HR_EarlyOut () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_EarlyOut);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException
    {
		return (I_GS_HR_Employee)MTable.get(getCtx(), I_GS_HR_Employee.Table_Name)
			.getPO(getGS_HR_Employee_ID(), get_TrxName());	}

	/** Set Employee Details.
		@param GS_HR_Employee_ID Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID)
	{
		if (GS_HR_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Employee_ID, Integer.valueOf(GS_HR_Employee_ID));
	}

	/** Get Employee Details.
		@return Employee Details	  */
	public int getGS_HR_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Late Arrival(Minutes).
		@param GS_HR_LateIn Late Arrival(Minutes)	  */
	public void setGS_HR_LateIn (BigDecimal GS_HR_LateIn)
	{
		set_Value (COLUMNNAME_GS_HR_LateIn, GS_HR_LateIn);
	}

	/** Get Late Arrival(Minutes).
		@return Late Arrival(Minutes)	  */
	public BigDecimal getGS_HR_LateIn () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_LateIn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_GS_HR_MonthlyAttendance getGS_HR_MonthlyAttendance() throws RuntimeException
    {
		return (I_GS_HR_MonthlyAttendance)MTable.get(getCtx(), I_GS_HR_MonthlyAttendance.Table_Name)
			.getPO(getGS_HR_MonthlyAttendance_ID(), get_TrxName());	}

	/** Set Monthly Attendance.
		@param GS_HR_MonthlyAttendance_ID Monthly Attendance	  */
	public void setGS_HR_MonthlyAttendance_ID (int GS_HR_MonthlyAttendance_ID)
	{
		if (GS_HR_MonthlyAttendance_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_MonthlyAttendance_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_MonthlyAttendance_ID, Integer.valueOf(GS_HR_MonthlyAttendance_ID));
	}

	/** Get Monthly Attendance.
		@return Monthly Attendance	  */
	public int getGS_HR_MonthlyAttendance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_MonthlyAttendance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Salary Paid On.
		@param GS_HR_SalaryPaidOn 
		Salary Paid On
	  */
	public void setGS_HR_SalaryPaidOn (BigDecimal GS_HR_SalaryPaidOn)
	{
		set_Value (COLUMNNAME_GS_HR_SalaryPaidOn, GS_HR_SalaryPaidOn);
	}

	/** Get Salary Paid On.
		@return Salary Paid On
	  */
	public BigDecimal getGS_HR_SalaryPaidOn () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_SalaryPaidOn);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Deduction(Minutes).
		@param GS_HR_TotalDeduction Total Deduction(Minutes)	  */
	public void setGS_HR_TotalDeduction (BigDecimal GS_HR_TotalDeduction)
	{
		set_Value (COLUMNNAME_GS_HR_TotalDeduction, GS_HR_TotalDeduction);
	}

	/** Get Total Deduction(Minutes).
		@return Total Deduction(Minutes)	  */
	public BigDecimal getGS_HR_TotalDeduction () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_TotalDeduction);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Hour.
		@param GS_Hour 
		Hour of the Day
	  */
	public void setGS_Hour (String GS_Hour)
	{
		set_Value (COLUMNNAME_GS_Hour, GS_Hour);
	}

	/** Get Hour.
		@return Hour of the Day
	  */
	public String getGS_Hour () 
	{
		return (String)get_Value(COLUMNNAME_GS_Hour);
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}