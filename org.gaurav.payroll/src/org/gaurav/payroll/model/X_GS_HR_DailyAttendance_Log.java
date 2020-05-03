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

/** Generated Model for GS_HR_DailyAttendance_Log
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_DailyAttendance_Log extends PO implements I_GS_HR_DailyAttendance_Log, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_DailyAttendance_Log (Properties ctx, int GS_HR_DailyAttendance_Log_ID, String trxName)
    {
      super (ctx, GS_HR_DailyAttendance_Log_ID, trxName);
      /** if (GS_HR_DailyAttendance_Log_ID == 0)
        {
			setGS_HR_DailyAttendance_Log_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_DailyAttendance_Log (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_DailyAttendance_Log[")
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

	/** Set Hour.
		@param GS_Hour 
		Hour of the Day
	  */
	public void setGS_Hour (int GS_Hour)
	{
		set_Value (COLUMNNAME_GS_Hour, Integer.valueOf(GS_Hour));
	}

	/** Get Hour.
		@return Hour of the Day
	  */
	public int getGS_Hour () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_Hour);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Daily Attendance Log.
		@param GS_HR_DailyAttendance_Log_ID Daily Attendance Log	  */
	public void setGS_HR_DailyAttendance_Log_ID (int GS_HR_DailyAttendance_Log_ID)
	{
		if (GS_HR_DailyAttendance_Log_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_DailyAttendance_Log_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_DailyAttendance_Log_ID, Integer.valueOf(GS_HR_DailyAttendance_Log_ID));
	}

	/** Get Daily Attendance Log.
		@return Daily Attendance Log	  */
	public int getGS_HR_DailyAttendance_Log_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_DailyAttendance_Log_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_DailyAttendance_Log_UU.
		@param GS_HR_DailyAttendance_Log_UU GS_HR_DailyAttendance_Log_UU	  */
	public void setGS_HR_DailyAttendance_Log_UU (String GS_HR_DailyAttendance_Log_UU)
	{
		set_Value (COLUMNNAME_GS_HR_DailyAttendance_Log_UU, GS_HR_DailyAttendance_Log_UU);
	}

	/** Get GS_HR_DailyAttendance_Log_UU.
		@return GS_HR_DailyAttendance_Log_UU	  */
	public String getGS_HR_DailyAttendance_Log_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_DailyAttendance_Log_UU);
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

	public I_GS_HR_TerminalDetails getGS_HR_TerminalDetails() throws RuntimeException
    {
		return (I_GS_HR_TerminalDetails)MTable.get(getCtx(), I_GS_HR_TerminalDetails.Table_Name)
			.getPO(getGS_HR_TerminalDetails_ID(), get_TrxName());	}

	/** Set Attendance Terminals.
		@param GS_HR_TerminalDetails_ID Attendance Terminals	  */
	public void setGS_HR_TerminalDetails_ID (int GS_HR_TerminalDetails_ID)
	{
		if (GS_HR_TerminalDetails_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_TerminalDetails_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_TerminalDetails_ID, Integer.valueOf(GS_HR_TerminalDetails_ID));
	}

	/** Get Attendance Terminals.
		@return Attendance Terminals	  */
	public int getGS_HR_TerminalDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_TerminalDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Terminal SN.
		@param GS_HR_TerminalSN Terminal SN	  */
	public void setGS_HR_TerminalSN (String GS_HR_TerminalSN)
	{
		set_Value (COLUMNNAME_GS_HR_TerminalSN, GS_HR_TerminalSN);
	}

	/** Get Terminal SN.
		@return Terminal SN	  */
	public String getGS_HR_TerminalSN () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_TerminalSN);
	}

	/** Set Log ID.
		@param GS_LogID 
		Unique ID from the punching machine for the Logs
	  */
	public void setGS_LogID (int GS_LogID)
	{
		set_Value (COLUMNNAME_GS_LogID, Integer.valueOf(GS_LogID));
	}

	/** Get Log ID.
		@return Unique ID from the punching machine for the Logs
	  */
	public int getGS_LogID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_LogID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Minutes.
		@param GS_Minutes 
		Minute of the Hour
	  */
	public void setGS_Minutes (int GS_Minutes)
	{
		set_Value (COLUMNNAME_GS_Minutes, Integer.valueOf(GS_Minutes));
	}

	/** Get Minutes.
		@return Minute of the Hour
	  */
	public int getGS_Minutes () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_Minutes);
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

	/** Set Seconds.
		@param GS_Seconds 
		Seconds of the Hour
	  */
	public void setGS_Seconds (int GS_Seconds)
	{
		set_Value (COLUMNNAME_GS_Seconds, Integer.valueOf(GS_Seconds));
	}

	/** Get Seconds.
		@return Seconds of the Hour
	  */
	public int getGS_Seconds () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_Seconds);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trigger Type.
		@param GS_TriggerType 
		Punch either In/Out
	  */
	public void setGS_TriggerType (String GS_TriggerType)
	{
		set_Value (COLUMNNAME_GS_TriggerType, GS_TriggerType);
	}

	/** Get Trigger Type.
		@return Punch either In/Out
	  */
	public String getGS_TriggerType () 
	{
		return (String)get_Value(COLUMNNAME_GS_TriggerType);
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