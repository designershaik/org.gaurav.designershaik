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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_HR_MonthlyAttendance
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_MonthlyAttendance extends PO implements I_GS_HR_MonthlyAttendance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_MonthlyAttendance (Properties ctx, int GS_HR_MonthlyAttendance_ID, String trxName)
    {
      super (ctx, GS_HR_MonthlyAttendance_ID, trxName);
      /** if (GS_HR_MonthlyAttendance_ID == 0)
        {
			setGS_HR_MonthlyAttendance_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_MonthlyAttendance (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_MonthlyAttendance[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Monthly Attendance.
		@param GS_HR_MonthlyAttendance_ID Monthly Attendance	  */
	public void setGS_HR_MonthlyAttendance_ID (int GS_HR_MonthlyAttendance_ID)
	{
		if (GS_HR_MonthlyAttendance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_MonthlyAttendance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_MonthlyAttendance_ID, Integer.valueOf(GS_HR_MonthlyAttendance_ID));
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

	/** Set GS_HR_MonthlyAttendance_UU.
		@param GS_HR_MonthlyAttendance_UU GS_HR_MonthlyAttendance_UU	  */
	public void setGS_HR_MonthlyAttendance_UU (String GS_HR_MonthlyAttendance_UU)
	{
		set_Value (COLUMNNAME_GS_HR_MonthlyAttendance_UU, GS_HR_MonthlyAttendance_UU);
	}

	/** Get GS_HR_MonthlyAttendance_UU.
		@return GS_HR_MonthlyAttendance_UU	  */
	public String getGS_HR_MonthlyAttendance_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_MonthlyAttendance_UU);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getGS_HR_SalaryMonths_ID()));
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