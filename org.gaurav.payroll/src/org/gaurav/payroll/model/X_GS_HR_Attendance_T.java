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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for GS_HR_Attendance_T
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Attendance_T extends PO implements I_GS_HR_Attendance_T, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_Attendance_T (Properties ctx, int GS_HR_Attendance_T_ID, String trxName)
    {
      super (ctx, GS_HR_Attendance_T_ID, trxName);
      /** if (GS_HR_Attendance_T_ID == 0)
        {
			setGS_HR_Attendance_T_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Attendance_T (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Attendance_T[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (int Code)
	{
		set_Value (COLUMNNAME_Code, Integer.valueOf(Code));
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public int getCode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Code);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set In 1.
		@param DS_In1 In 1	  */
	public void setDS_In1 (Timestamp DS_In1)
	{
		set_Value (COLUMNNAME_DS_In1, DS_In1);
	}

	/** Get In 1.
		@return In 1	  */
	public Timestamp getDS_In1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In1);
	}

	/** Set In 10.
		@param DS_In10 In 10	  */
	public void setDS_In10 (Timestamp DS_In10)
	{
		set_Value (COLUMNNAME_DS_In10, DS_In10);
	}

	/** Get In 10.
		@return In 10	  */
	public Timestamp getDS_In10 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In10);
	}

	/** Set In 11.
		@param DS_In11 In 11	  */
	public void setDS_In11 (Timestamp DS_In11)
	{
		set_Value (COLUMNNAME_DS_In11, DS_In11);
	}

	/** Get In 11.
		@return In 11	  */
	public Timestamp getDS_In11 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In11);
	}

	/** Set In 12.
		@param DS_In12 In 12	  */
	public void setDS_In12 (Timestamp DS_In12)
	{
		set_Value (COLUMNNAME_DS_In12, DS_In12);
	}

	/** Get In 12.
		@return In 12	  */
	public Timestamp getDS_In12 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In12);
	}

	/** Set In 2.
		@param DS_In2 In 2	  */
	public void setDS_In2 (Timestamp DS_In2)
	{
		set_Value (COLUMNNAME_DS_In2, DS_In2);
	}

	/** Get In 2.
		@return In 2	  */
	public Timestamp getDS_In2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In2);
	}

	/** Set In 3.
		@param DS_In3 In 3	  */
	public void setDS_In3 (Timestamp DS_In3)
	{
		set_Value (COLUMNNAME_DS_In3, DS_In3);
	}

	/** Get In 3.
		@return In 3	  */
	public Timestamp getDS_In3 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In3);
	}

	/** Set In 4.
		@param DS_In4 In 4	  */
	public void setDS_In4 (Timestamp DS_In4)
	{
		set_Value (COLUMNNAME_DS_In4, DS_In4);
	}

	/** Get In 4.
		@return In 4	  */
	public Timestamp getDS_In4 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In4);
	}

	/** Set In 5.
		@param DS_In5 In 5	  */
	public void setDS_In5 (Timestamp DS_In5)
	{
		set_Value (COLUMNNAME_DS_In5, DS_In5);
	}

	/** Get In 5.
		@return In 5	  */
	public Timestamp getDS_In5 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In5);
	}

	/** Set In 6.
		@param DS_In6 In 6	  */
	public void setDS_In6 (Timestamp DS_In6)
	{
		set_Value (COLUMNNAME_DS_In6, DS_In6);
	}

	/** Get In 6.
		@return In 6	  */
	public Timestamp getDS_In6 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In6);
	}

	/** Set In 7.
		@param DS_In7 In 7	  */
	public void setDS_In7 (Timestamp DS_In7)
	{
		set_Value (COLUMNNAME_DS_In7, DS_In7);
	}

	/** Get In 7.
		@return In 7	  */
	public Timestamp getDS_In7 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In7);
	}

	/** Set In 8.
		@param DS_In8 In 8	  */
	public void setDS_In8 (Timestamp DS_In8)
	{
		set_Value (COLUMNNAME_DS_In8, DS_In8);
	}

	/** Get In 8.
		@return In 8	  */
	public Timestamp getDS_In8 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In8);
	}

	/** Set In 9.
		@param DS_In9 In 9	  */
	public void setDS_In9 (Timestamp DS_In9)
	{
		set_Value (COLUMNNAME_DS_In9, DS_In9);
	}

	/** Get In 9.
		@return In 9	  */
	public Timestamp getDS_In9 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_In9);
	}

	/** Set Out 1.
		@param DS_Out1 Out 1	  */
	public void setDS_Out1 (Timestamp DS_Out1)
	{
		set_Value (COLUMNNAME_DS_Out1, DS_Out1);
	}

	/** Get Out 1.
		@return Out 1	  */
	public Timestamp getDS_Out1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out1);
	}

	/** Set Out 10.
		@param DS_Out10 Out 10	  */
	public void setDS_Out10 (Timestamp DS_Out10)
	{
		set_Value (COLUMNNAME_DS_Out10, DS_Out10);
	}

	/** Get Out 10.
		@return Out 10	  */
	public Timestamp getDS_Out10 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out10);
	}

	/** Set Out 11.
		@param DS_Out11 Out 11	  */
	public void setDS_Out11 (Timestamp DS_Out11)
	{
		set_Value (COLUMNNAME_DS_Out11, DS_Out11);
	}

	/** Get Out 11.
		@return Out 11	  */
	public Timestamp getDS_Out11 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out11);
	}

	/** Set Out 12.
		@param DS_Out12 Out 12	  */
	public void setDS_Out12 (Timestamp DS_Out12)
	{
		set_Value (COLUMNNAME_DS_Out12, DS_Out12);
	}

	/** Get Out 12.
		@return Out 12	  */
	public Timestamp getDS_Out12 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out12);
	}

	/** Set Out 2.
		@param DS_Out2 Out 2	  */
	public void setDS_Out2 (Timestamp DS_Out2)
	{
		set_Value (COLUMNNAME_DS_Out2, DS_Out2);
	}

	/** Get Out 2.
		@return Out 2	  */
	public Timestamp getDS_Out2 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out2);
	}

	/** Set Out 3.
		@param DS_Out3 Out 3	  */
	public void setDS_Out3 (Timestamp DS_Out3)
	{
		set_Value (COLUMNNAME_DS_Out3, DS_Out3);
	}

	/** Get Out 3.
		@return Out 3	  */
	public Timestamp getDS_Out3 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out3);
	}

	/** Set Out 4.
		@param DS_Out4 Out 4	  */
	public void setDS_Out4 (Timestamp DS_Out4)
	{
		set_Value (COLUMNNAME_DS_Out4, DS_Out4);
	}

	/** Get Out 4.
		@return Out 4	  */
	public Timestamp getDS_Out4 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out4);
	}

	/** Set Out 5.
		@param DS_Out5 Out 5	  */
	public void setDS_Out5 (Timestamp DS_Out5)
	{
		set_Value (COLUMNNAME_DS_Out5, DS_Out5);
	}

	/** Get Out 5.
		@return Out 5	  */
	public Timestamp getDS_Out5 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out5);
	}

	/** Set Out 6.
		@param DS_Out6 Out 6	  */
	public void setDS_Out6 (Timestamp DS_Out6)
	{
		set_Value (COLUMNNAME_DS_Out6, DS_Out6);
	}

	/** Get Out 6.
		@return Out 6	  */
	public Timestamp getDS_Out6 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out6);
	}

	/** Set Out 7.
		@param DS_Out7 Out 7	  */
	public void setDS_Out7 (Timestamp DS_Out7)
	{
		set_Value (COLUMNNAME_DS_Out7, DS_Out7);
	}

	/** Get Out 7.
		@return Out 7	  */
	public Timestamp getDS_Out7 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out7);
	}

	/** Set Out 8.
		@param DS_Out8 Out 8	  */
	public void setDS_Out8 (Timestamp DS_Out8)
	{
		set_Value (COLUMNNAME_DS_Out8, DS_Out8);
	}

	/** Get Out 8.
		@return Out 8	  */
	public Timestamp getDS_Out8 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out8);
	}

	/** Set Out 9.
		@param DS_Out9 Out 9	  */
	public void setDS_Out9 (Timestamp DS_Out9)
	{
		set_Value (COLUMNNAME_DS_Out9, DS_Out9);
	}

	/** Get Out 9.
		@return Out 9	  */
	public Timestamp getDS_Out9 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_Out9);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set AM/PM.
		@param GS_AMPM 
		AM/PM
	  */
	public void setGS_AMPM (int GS_AMPM)
	{
		set_Value (COLUMNNAME_GS_AMPM, Integer.valueOf(GS_AMPM));
	}

	/** Get AM/PM.
		@return AM/PM
	  */
	public int getGS_AMPM () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_AMPM);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Exportable Attendance.
		@param GS_HR_Attendance_T_ID Exportable Attendance	  */
	public void setGS_HR_Attendance_T_ID (int GS_HR_Attendance_T_ID)
	{
		if (GS_HR_Attendance_T_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_T_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_T_ID, Integer.valueOf(GS_HR_Attendance_T_ID));
	}

	/** Get Exportable Attendance.
		@return Exportable Attendance	  */
	public int getGS_HR_Attendance_T_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Attendance_T_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Attendance_T_UU.
		@param GS_HR_Attendance_T_UU GS_HR_Attendance_T_UU	  */
	public void setGS_HR_Attendance_T_UU (String GS_HR_Attendance_T_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Attendance_T_UU, GS_HR_Attendance_T_UU);
	}

	/** Get GS_HR_Attendance_T_UU.
		@return GS_HR_Attendance_T_UU	  */
	public String getGS_HR_Attendance_T_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Attendance_T_UU);
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

	/** Set Month.
		@param GS_Month 
		Month
	  */
	public void setGS_Month (int GS_Month)
	{
		set_Value (COLUMNNAME_GS_Month, Integer.valueOf(GS_Month));
	}

	/** Get Month.
		@return Month
	  */
	public int getGS_Month () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_Month);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Punch Time.
		@param GS_PunchTime 
		Punching time either In/Out based on the type
	  */
	public void setGS_PunchTime (Timestamp GS_PunchTime)
	{
		set_Value (COLUMNNAME_GS_PunchTime, GS_PunchTime);
	}

	/** Get Punch Time.
		@return Punching time either In/Out based on the type
	  */
	public Timestamp getGS_PunchTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GS_PunchTime);
	}

	/** Set Year.
		@param GS_Year 
		Year
	  */
	public void setGS_Year (int GS_Year)
	{
		set_Value (COLUMNNAME_GS_Year, Integer.valueOf(GS_Year));
	}

	/** Get Year.
		@return Year
	  */
	public int getGS_Year () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_Year);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generate List.
		@param GenerateList 
		Generate List
	  */
	public void setGenerateList (String GenerateList)
	{
		set_Value (COLUMNNAME_GenerateList, GenerateList);
	}

	/** Get Generate List.
		@return Generate List
	  */
	public String getGenerateList () 
	{
		return (String)get_Value(COLUMNNAME_GenerateList);
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_Name)
			.getPO(getHR_Department_ID(), get_TrxName());	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) 
			set_Value (COLUMNNAME_HR_Department_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}
}