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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for GS_HR_Attendance_Det
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Attendance_Det extends PO implements I_GS_HR_Attendance_Det, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_Attendance_Det (Properties ctx, int GS_HR_Attendance_Det_ID, String trxName)
    {
      super (ctx, GS_HR_Attendance_Det_ID, trxName);
      /** if (GS_HR_Attendance_Det_ID == 0)
        {
			setGS_HR_Attendance_Det_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Attendance_Det (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Attendance_Det[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Absent Days.
		@param GS_HR_AbsentDays Absent Days	  */
	public void setGS_HR_AbsentDays (BigDecimal GS_HR_AbsentDays)
	{
		set_Value (COLUMNNAME_GS_HR_AbsentDays, GS_HR_AbsentDays);
	}

	/** Get Absent Days.
		@return Absent Days	  */
	public BigDecimal getGS_HR_AbsentDays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_AbsentDays);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Attendance Details.
		@param GS_HR_Attendance_Det_ID Attendance Details	  */
	public void setGS_HR_Attendance_Det_ID (int GS_HR_Attendance_Det_ID)
	{
		if (GS_HR_Attendance_Det_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_Det_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_Det_ID, Integer.valueOf(GS_HR_Attendance_Det_ID));
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

	/** Set GS_HR_Attendance_Det_UU.
		@param GS_HR_Attendance_Det_UU GS_HR_Attendance_Det_UU	  */
	public void setGS_HR_Attendance_Det_UU (String GS_HR_Attendance_Det_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Attendance_Det_UU, GS_HR_Attendance_Det_UU);
	}

	/** Get GS_HR_Attendance_Det_UU.
		@return GS_HR_Attendance_Det_UU	  */
	public String getGS_HR_Attendance_Det_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Attendance_Det_UU);
	}

	/** Set Consolidated Working Hours.
		@param GS_HR_ConsolidateWorkingHours 
		Consolidated Working Hours
	  */
	public void setGS_HR_ConsolidateWorkingHours (BigDecimal GS_HR_ConsolidateWorkingHours)
	{
		set_Value (COLUMNNAME_GS_HR_ConsolidateWorkingHours, GS_HR_ConsolidateWorkingHours);
	}

	/** Get Consolidated Working Hours.
		@return Consolidated Working Hours
	  */
	public BigDecimal getGS_HR_ConsolidateWorkingHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_ConsolidateWorkingHours);
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
			set_Value (COLUMNNAME_GS_HR_Employee_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Employee_ID, Integer.valueOf(GS_HR_Employee_ID));
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

	/** Set Holidays.
		@param GS_HR_Holidays Holidays	  */
	public void setGS_HR_Holidays (BigDecimal GS_HR_Holidays)
	{
		set_Value (COLUMNNAME_GS_HR_Holidays, GS_HR_Holidays);
	}

	/** Get Holidays.
		@return Holidays	  */
	public BigDecimal getGS_HR_Holidays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_Holidays);
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

	/** Set Overt Time 1.
		@param GS_HR_OverTime1 Overt Time 1	  */
	public void setGS_HR_OverTime1 (BigDecimal GS_HR_OverTime1)
	{
		set_Value (COLUMNNAME_GS_HR_OverTime1, GS_HR_OverTime1);
	}

	/** Get Overt Time 1.
		@return Overt Time 1	  */
	public BigDecimal getGS_HR_OverTime1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_OverTime1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Overt Time 2.
		@param GS_HR_OverTime2 Overt Time 2	  */
	public void setGS_HR_OverTime2 (BigDecimal GS_HR_OverTime2)
	{
		set_Value (COLUMNNAME_GS_HR_OverTime2, GS_HR_OverTime2);
	}

	/** Get Overt Time 2.
		@return Overt Time 2	  */
	public BigDecimal getGS_HR_OverTime2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_OverTime2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Overt Time 3.
		@param GS_HR_OverTime3 Overt Time 3	  */
	public void setGS_HR_OverTime3 (BigDecimal GS_HR_OverTime3)
	{
		set_Value (COLUMNNAME_GS_HR_OverTime3, GS_HR_OverTime3);
	}

	/** Get Overt Time 3.
		@return Overt Time 3	  */
	public BigDecimal getGS_HR_OverTime3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_OverTime3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Overt Time 4.
		@param GS_HR_OverTime4 Overt Time 4	  */
	public void setGS_HR_OverTime4 (BigDecimal GS_HR_OverTime4)
	{
		set_Value (COLUMNNAME_GS_HR_OverTime4, GS_HR_OverTime4);
	}

	/** Get Overt Time 4.
		@return Overt Time 4	  */
	public BigDecimal getGS_HR_OverTime4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_OverTime4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Overt Time 5.
		@param GS_HR_OverTime5 Overt Time 5	  */
	public void setGS_HR_OverTime5 (BigDecimal GS_HR_OverTime5)
	{
		set_Value (COLUMNNAME_GS_HR_OverTime5, GS_HR_OverTime5);
	}

	/** Get Overt Time 5.
		@return Overt Time 5	  */
	public BigDecimal getGS_HR_OverTime5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_OverTime5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Present Days.
		@param GS_HR_PresentDays Present Days	  */
	public void setGS_HR_PresentDays (BigDecimal GS_HR_PresentDays)
	{
		set_Value (COLUMNNAME_GS_HR_PresentDays, GS_HR_PresentDays);
	}

	/** Get Present Days.
		@return Present Days	  */
	public BigDecimal getGS_HR_PresentDays () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_PresentDays);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_GS_HR_SalaryMonths getGS_HR_SalaryMonths() throws RuntimeException
    {
		return (I_GS_HR_SalaryMonths)MTable.get(getCtx(), I_GS_HR_SalaryMonths.Table_Name)
			.getPO(getGS_HR_SalaryMonths_ID(), get_TrxName());	}

	/** Set Salary Months.
		@param GS_HR_SalaryMonths_ID Salary Months	  */
	public void setGS_HR_SalaryMonths_ID (int GS_HR_SalaryMonths_ID)
	{
		if (GS_HR_SalaryMonths_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_SalaryMonths_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_SalaryMonths_ID, Integer.valueOf(GS_HR_SalaryMonths_ID));
	}

	/** Get Salary Months.
		@return Salary Months	  */
	public int getGS_HR_SalaryMonths_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_SalaryMonths_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Total Working Hours For Month.
		@param GS_HR_TotalWorkingHours 
		Total Working Hours For Month
	  */
	public void setGS_HR_TotalWorkingHours (BigDecimal GS_HR_TotalWorkingHours)
	{
		set_Value (COLUMNNAME_GS_HR_TotalWorkingHours, GS_HR_TotalWorkingHours);
	}

	/** Get Total Working Hours For Month.
		@return Total Working Hours For Month
	  */
	public BigDecimal getGS_HR_TotalWorkingHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_TotalWorkingHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Working Minutes.
		@param GS_HR_TotalWorkingMinutes 
		Total Working Minutes
	  */
	public void setGS_HR_TotalWorkingMinutes (BigDecimal GS_HR_TotalWorkingMinutes)
	{
		set_Value (COLUMNNAME_GS_HR_TotalWorkingMinutes, GS_HR_TotalWorkingMinutes);
	}

	/** Get Total Working Minutes.
		@return Total Working Minutes
	  */
	public BigDecimal getGS_HR_TotalWorkingMinutes () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_TotalWorkingMinutes);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Year.
		@param Year Year	  */
	public void setYear (int Year)
	{
		set_Value (COLUMNNAME_Year, Integer.valueOf(Year));
	}

	/** Get Year.
		@return Year	  */
	public int getYear () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Year);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}