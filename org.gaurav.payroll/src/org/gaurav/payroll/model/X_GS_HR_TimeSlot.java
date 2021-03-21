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

/** Generated Model for GS_HR_TimeSlot
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_TimeSlot extends PO implements I_GS_HR_TimeSlot, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_TimeSlot (Properties ctx, int GS_HR_TimeSlot_ID, String trxName)
    {
      super (ctx, GS_HR_TimeSlot_ID, trxName);
      /** if (GS_HR_TimeSlot_ID == 0)
        {
			setDS_In (new Timestamp( System.currentTimeMillis() ));
			setDS_Out (new Timestamp( System.currentTimeMillis() ));
			setGS_DayOfTheWeek (null);
			setGS_HR_GracePeriod (Env.ZERO);
			setGS_HR_TimeSlot_ID (0);
			setGS_Hour (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_TimeSlot (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_TimeSlot[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Grace Period.
		@param GS_HR_GracePeriod 
		Grace Period
	  */
	public void setGS_HR_GracePeriod (BigDecimal GS_HR_GracePeriod)
	{
		set_Value (COLUMNNAME_GS_HR_GracePeriod, GS_HR_GracePeriod);
	}

	/** Get Grace Period.
		@return Grace Period
	  */
	public BigDecimal getGS_HR_GracePeriod () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_GracePeriod);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_GS_HR_TimeSlot_Group getGS_HR_TimeSlot_Group() throws RuntimeException
    {
		return (I_GS_HR_TimeSlot_Group)MTable.get(getCtx(), I_GS_HR_TimeSlot_Group.Table_Name)
			.getPO(getGS_HR_TimeSlot_Group_ID(), get_TrxName());	}

	/** Set Time Slot.
		@param GS_HR_TimeSlot_Group_ID Time Slot	  */
	public void setGS_HR_TimeSlot_Group_ID (int GS_HR_TimeSlot_Group_ID)
	{
		if (GS_HR_TimeSlot_Group_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_TimeSlot_Group_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_TimeSlot_Group_ID, Integer.valueOf(GS_HR_TimeSlot_Group_ID));
	}

	/** Get Time Slot.
		@return Time Slot	  */
	public int getGS_HR_TimeSlot_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_TimeSlot_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Working Time.
		@param GS_HR_TimeSlot_ID Working Time	  */
	public void setGS_HR_TimeSlot_ID (int GS_HR_TimeSlot_ID)
	{
		if (GS_HR_TimeSlot_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_TimeSlot_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_TimeSlot_ID, Integer.valueOf(GS_HR_TimeSlot_ID));
	}

	/** Get Working Time.
		@return Working Time	  */
	public int getGS_HR_TimeSlot_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_TimeSlot_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_TimeSlot_UU.
		@param GS_HR_TimeSlot_UU GS_HR_TimeSlot_UU	  */
	public void setGS_HR_TimeSlot_UU (String GS_HR_TimeSlot_UU)
	{
		set_Value (COLUMNNAME_GS_HR_TimeSlot_UU, GS_HR_TimeSlot_UU);
	}

	/** Get GS_HR_TimeSlot_UU.
		@return GS_HR_TimeSlot_UU	  */
	public String getGS_HR_TimeSlot_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_TimeSlot_UU);
	}

	/** Set Weekly Off.
		@param GS_HR_WeeklyOff Weekly Off	  */
	public void setGS_HR_WeeklyOff (boolean GS_HR_WeeklyOff)
	{
		set_Value (COLUMNNAME_GS_HR_WeeklyOff, Boolean.valueOf(GS_HR_WeeklyOff));
	}

	/** Get Weekly Off.
		@return Weekly Off	  */
	public boolean isGS_HR_WeeklyOff () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_WeeklyOff);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Hour.
		@param GS_Hour 
		Hour of the Day
	  */
	public void setGS_Hour (BigDecimal GS_Hour)
	{
		set_Value (COLUMNNAME_GS_Hour, GS_Hour);
	}

	/** Get Hour.
		@return Hour of the Day
	  */
	public BigDecimal getGS_Hour () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_Hour);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}

	/** Set Day of the Week.
		@param WeekDay 
		Day of the Week
	  */
	public void setWeekDay (int WeekDay)
	{
		set_Value (COLUMNNAME_WeekDay, Integer.valueOf(WeekDay));
	}

	/** Get Day of the Week.
		@return Day of the Week
	  */
	public int getWeekDay () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_WeekDay);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}