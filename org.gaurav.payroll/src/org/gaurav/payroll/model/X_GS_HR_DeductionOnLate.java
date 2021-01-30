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

/** Generated Model for GS_HR_DeductionOnLate
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_DeductionOnLate extends PO implements I_GS_HR_DeductionOnLate, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210123L;

    /** Standard Constructor */
    public X_GS_HR_DeductionOnLate (Properties ctx, int GS_HR_DeductionOnLate_ID, String trxName)
    {
      super (ctx, GS_HR_DeductionOnLate_ID, trxName);
      /** if (GS_HR_DeductionOnLate_ID == 0)
        {
			setGS_HR_DeductionOnLate_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_DeductionOnLate (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_DeductionOnLate[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Calendar getC_Calendar() throws RuntimeException
    {
		return (org.compiere.model.I_C_Calendar)MTable.get(getCtx(), org.compiere.model.I_C_Calendar.Table_Name)
			.getPO(getC_Calendar_ID(), get_TrxName());	}

	/** Set Calendar.
		@param C_Calendar_ID 
		Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID)
	{
		if (C_Calendar_ID < 1) 
			set_Value (COLUMNNAME_C_Calendar_ID, null);
		else 
			set_Value (COLUMNNAME_C_Calendar_ID, Integer.valueOf(C_Calendar_ID));
	}

	/** Get Calendar.
		@return Accounting Calendar Name
	  */
	public int getC_Calendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Calendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Deduct Salary By Hour.
		@param GS_HR_DeductionByHour 
		Deduct Salary By Hour
	  */
	public void setGS_HR_DeductionByHour (BigDecimal GS_HR_DeductionByHour)
	{
		set_Value (COLUMNNAME_GS_HR_DeductionByHour, GS_HR_DeductionByHour);
	}

	/** Get Deduct Salary By Hour.
		@return Deduct Salary By Hour
	  */
	public BigDecimal getGS_HR_DeductionByHour () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_DeductionByHour);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Deduction On Late.
		@param GS_HR_DeductionOnLate_ID Deduction On Late	  */
	public void setGS_HR_DeductionOnLate_ID (int GS_HR_DeductionOnLate_ID)
	{
		if (GS_HR_DeductionOnLate_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_DeductionOnLate_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_DeductionOnLate_ID, Integer.valueOf(GS_HR_DeductionOnLate_ID));
	}

	/** Get Deduction On Late.
		@return Deduction On Late	  */
	public int getGS_HR_DeductionOnLate_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_DeductionOnLate_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_DeductionOnLate_UU.
		@param GS_HR_DeductionOnLate_UU GS_HR_DeductionOnLate_UU	  */
	public void setGS_HR_DeductionOnLate_UU (String GS_HR_DeductionOnLate_UU)
	{
		set_Value (COLUMNNAME_GS_HR_DeductionOnLate_UU, GS_HR_DeductionOnLate_UU);
	}

	/** Get GS_HR_DeductionOnLate_UU.
		@return GS_HR_DeductionOnLate_UU	  */
	public String getGS_HR_DeductionOnLate_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_DeductionOnLate_UU);
	}
}