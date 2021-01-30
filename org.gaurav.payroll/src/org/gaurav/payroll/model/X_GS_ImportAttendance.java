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

/** Generated Model for GS_ImportAttendance
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_ImportAttendance extends PO implements I_GS_ImportAttendance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20201222L;

    /** Standard Constructor */
    public X_GS_ImportAttendance (Properties ctx, int GS_ImportAttendance_ID, String trxName)
    {
      super (ctx, GS_ImportAttendance_ID, trxName);
      /** if (GS_ImportAttendance_ID == 0)
        {
			setGS_ImportAttendance_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_ImportAttendance (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_ImportAttendance[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (String Code)
	{
		set_Value (COLUMNNAME_Code, Code);
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public String getCode () 
	{
		return (String)get_Value(COLUMNNAME_Code);
	}

	/** Set Day 1.
		@param DS_Day1 Day 1	  */
	public void setDS_Day1 (BigDecimal DS_Day1)
	{
		set_Value (COLUMNNAME_DS_Day1, DS_Day1);
	}

	/** Get Day 1.
		@return Day 1	  */
	public BigDecimal getDS_Day1 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day1);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 10.
		@param DS_Day10 Day 10	  */
	public void setDS_Day10 (BigDecimal DS_Day10)
	{
		set_Value (COLUMNNAME_DS_Day10, DS_Day10);
	}

	/** Get Day 10.
		@return Day 10	  */
	public BigDecimal getDS_Day10 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day10);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 11.
		@param DS_Day11 Day 11	  */
	public void setDS_Day11 (BigDecimal DS_Day11)
	{
		set_Value (COLUMNNAME_DS_Day11, DS_Day11);
	}

	/** Get Day 11.
		@return Day 11	  */
	public BigDecimal getDS_Day11 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day11);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 12.
		@param DS_Day12 Day 12	  */
	public void setDS_Day12 (BigDecimal DS_Day12)
	{
		set_Value (COLUMNNAME_DS_Day12, DS_Day12);
	}

	/** Get Day 12.
		@return Day 12	  */
	public BigDecimal getDS_Day12 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day12);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 13.
		@param DS_Day13 Day 13	  */
	public void setDS_Day13 (BigDecimal DS_Day13)
	{
		set_Value (COLUMNNAME_DS_Day13, DS_Day13);
	}

	/** Get Day 13.
		@return Day 13	  */
	public BigDecimal getDS_Day13 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day13);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 14.
		@param DS_Day14 Day 14	  */
	public void setDS_Day14 (BigDecimal DS_Day14)
	{
		set_Value (COLUMNNAME_DS_Day14, DS_Day14);
	}

	/** Get Day 14.
		@return Day 14	  */
	public BigDecimal getDS_Day14 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day14);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 15.
		@param DS_Day15 Day 15	  */
	public void setDS_Day15 (BigDecimal DS_Day15)
	{
		set_Value (COLUMNNAME_DS_Day15, DS_Day15);
	}

	/** Get Day 15.
		@return Day 15	  */
	public BigDecimal getDS_Day15 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day15);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 16.
		@param DS_Day16 Day 16	  */
	public void setDS_Day16 (BigDecimal DS_Day16)
	{
		set_Value (COLUMNNAME_DS_Day16, DS_Day16);
	}

	/** Get Day 16.
		@return Day 16	  */
	public BigDecimal getDS_Day16 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day16);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 17.
		@param DS_Day17 Day 17	  */
	public void setDS_Day17 (BigDecimal DS_Day17)
	{
		set_Value (COLUMNNAME_DS_Day17, DS_Day17);
	}

	/** Get Day 17.
		@return Day 17	  */
	public BigDecimal getDS_Day17 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day17);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 18.
		@param DS_Day18 Day 18	  */
	public void setDS_Day18 (BigDecimal DS_Day18)
	{
		set_Value (COLUMNNAME_DS_Day18, DS_Day18);
	}

	/** Get Day 18.
		@return Day 18	  */
	public BigDecimal getDS_Day18 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day18);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 19.
		@param DS_Day19 Day 19	  */
	public void setDS_Day19 (BigDecimal DS_Day19)
	{
		set_Value (COLUMNNAME_DS_Day19, DS_Day19);
	}

	/** Get Day 19.
		@return Day 19	  */
	public BigDecimal getDS_Day19 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day19);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 2.
		@param DS_Day2 Day 2	  */
	public void setDS_Day2 (BigDecimal DS_Day2)
	{
		set_Value (COLUMNNAME_DS_Day2, DS_Day2);
	}

	/** Get Day 2.
		@return Day 2	  */
	public BigDecimal getDS_Day2 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day2);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 20.
		@param DS_Day20 Day 20	  */
	public void setDS_Day20 (BigDecimal DS_Day20)
	{
		set_Value (COLUMNNAME_DS_Day20, DS_Day20);
	}

	/** Get Day 20.
		@return Day 20	  */
	public BigDecimal getDS_Day20 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day20);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 21.
		@param DS_Day21 Day 21	  */
	public void setDS_Day21 (BigDecimal DS_Day21)
	{
		set_Value (COLUMNNAME_DS_Day21, DS_Day21);
	}

	/** Get Day 21.
		@return Day 21	  */
	public BigDecimal getDS_Day21 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day21);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 22.
		@param DS_Day22 Day 22	  */
	public void setDS_Day22 (BigDecimal DS_Day22)
	{
		set_Value (COLUMNNAME_DS_Day22, DS_Day22);
	}

	/** Get Day 22.
		@return Day 22	  */
	public BigDecimal getDS_Day22 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day22);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 23.
		@param DS_Day23 Day 23	  */
	public void setDS_Day23 (BigDecimal DS_Day23)
	{
		set_Value (COLUMNNAME_DS_Day23, DS_Day23);
	}

	/** Get Day 23.
		@return Day 23	  */
	public BigDecimal getDS_Day23 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day23);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 24.
		@param DS_Day24 Day 24	  */
	public void setDS_Day24 (BigDecimal DS_Day24)
	{
		set_Value (COLUMNNAME_DS_Day24, DS_Day24);
	}

	/** Get Day 24.
		@return Day 24	  */
	public BigDecimal getDS_Day24 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day24);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 25.
		@param DS_Day25 Day 25	  */
	public void setDS_Day25 (BigDecimal DS_Day25)
	{
		set_Value (COLUMNNAME_DS_Day25, DS_Day25);
	}

	/** Get Day 25.
		@return Day 25	  */
	public BigDecimal getDS_Day25 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day25);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 26.
		@param DS_Day26 Day 26	  */
	public void setDS_Day26 (BigDecimal DS_Day26)
	{
		set_Value (COLUMNNAME_DS_Day26, DS_Day26);
	}

	/** Get Day 26.
		@return Day 26	  */
	public BigDecimal getDS_Day26 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day26);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 27.
		@param DS_Day27 Day 27	  */
	public void setDS_Day27 (BigDecimal DS_Day27)
	{
		set_Value (COLUMNNAME_DS_Day27, DS_Day27);
	}

	/** Get Day 27.
		@return Day 27	  */
	public BigDecimal getDS_Day27 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day27);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 28.
		@param DS_Day28 Day 28	  */
	public void setDS_Day28 (BigDecimal DS_Day28)
	{
		set_Value (COLUMNNAME_DS_Day28, DS_Day28);
	}

	/** Get Day 28.
		@return Day 28	  */
	public BigDecimal getDS_Day28 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day28);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 29.
		@param DS_Day29 Day 29	  */
	public void setDS_Day29 (BigDecimal DS_Day29)
	{
		set_Value (COLUMNNAME_DS_Day29, DS_Day29);
	}

	/** Get Day 29.
		@return Day 29	  */
	public BigDecimal getDS_Day29 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day29);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 3.
		@param DS_Day3 Day 3	  */
	public void setDS_Day3 (BigDecimal DS_Day3)
	{
		set_Value (COLUMNNAME_DS_Day3, DS_Day3);
	}

	/** Get Day 3.
		@return Day 3	  */
	public BigDecimal getDS_Day3 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day3);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 30.
		@param DS_Day30 Day 30	  */
	public void setDS_Day30 (BigDecimal DS_Day30)
	{
		set_Value (COLUMNNAME_DS_Day30, DS_Day30);
	}

	/** Get Day 30.
		@return Day 30	  */
	public BigDecimal getDS_Day30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 31.
		@param DS_Day31 Day 31	  */
	public void setDS_Day31 (BigDecimal DS_Day31)
	{
		set_Value (COLUMNNAME_DS_Day31, DS_Day31);
	}

	/** Get Day 31.
		@return Day 31	  */
	public BigDecimal getDS_Day31 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day31);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 4.
		@param DS_Day4 Day 4	  */
	public void setDS_Day4 (BigDecimal DS_Day4)
	{
		set_Value (COLUMNNAME_DS_Day4, DS_Day4);
	}

	/** Get Day 4.
		@return Day 4	  */
	public BigDecimal getDS_Day4 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day4);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 5.
		@param DS_Day5 Day 5	  */
	public void setDS_Day5 (BigDecimal DS_Day5)
	{
		set_Value (COLUMNNAME_DS_Day5, DS_Day5);
	}

	/** Get Day 5.
		@return Day 5	  */
	public BigDecimal getDS_Day5 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day5);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 6.
		@param DS_Day6 Day 6	  */
	public void setDS_Day6 (BigDecimal DS_Day6)
	{
		set_Value (COLUMNNAME_DS_Day6, DS_Day6);
	}

	/** Get Day 6.
		@return Day 6	  */
	public BigDecimal getDS_Day6 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day6);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 7.
		@param DS_Day7 Day 7	  */
	public void setDS_Day7 (BigDecimal DS_Day7)
	{
		set_Value (COLUMNNAME_DS_Day7, DS_Day7);
	}

	/** Get Day 7.
		@return Day 7	  */
	public BigDecimal getDS_Day7 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day7);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 8.
		@param DS_Day8 Day 8	  */
	public void setDS_Day8 (BigDecimal DS_Day8)
	{
		set_Value (COLUMNNAME_DS_Day8, DS_Day8);
	}

	/** Get Day 8.
		@return Day 8	  */
	public BigDecimal getDS_Day8 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day8);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Day 9.
		@param DS_Day9 Day 9	  */
	public void setDS_Day9 (BigDecimal DS_Day9)
	{
		set_Value (COLUMNNAME_DS_Day9, DS_Day9);
	}

	/** Get Day 9.
		@return Day 9	  */
	public BigDecimal getDS_Day9 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Day9);
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

	/** Set Import Attendance.
		@param GS_ImportAttendance_ID Import Attendance	  */
	public void setGS_ImportAttendance_ID (int GS_ImportAttendance_ID)
	{
		if (GS_ImportAttendance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_ImportAttendance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_ImportAttendance_ID, Integer.valueOf(GS_ImportAttendance_ID));
	}

	/** Get Import Attendance.
		@return Import Attendance	  */
	public int getGS_ImportAttendance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_ImportAttendance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_ImportAttendance_UU.
		@param GS_ImportAttendance_UU GS_ImportAttendance_UU	  */
	public void setGS_ImportAttendance_UU (String GS_ImportAttendance_UU)
	{
		set_Value (COLUMNNAME_GS_ImportAttendance_UU, GS_ImportAttendance_UU);
	}

	/** Get GS_ImportAttendance_UU.
		@return GS_ImportAttendance_UU	  */
	public String getGS_ImportAttendance_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_ImportAttendance_UU);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}
}