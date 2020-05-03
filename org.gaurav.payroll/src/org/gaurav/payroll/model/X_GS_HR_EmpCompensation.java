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
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_HR_EmpCompensation
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_EmpCompensation extends PO implements I_GS_HR_EmpCompensation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_EmpCompensation (Properties ctx, int GS_HR_EmpCompensation_ID, String trxName)
    {
      super (ctx, GS_HR_EmpCompensation_ID, trxName);
      /** if (GS_HR_EmpCompensation_ID == 0)
        {
			setGS_HR_EmpCompensation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_EmpCompensation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_EmpCompensation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Amount.
		@param Amt 
		Amount
	  */
	public void setAmt (BigDecimal Amt)
	{
		set_Value (COLUMNNAME_Amt, Amt);
	}

	/** Get Amount.
		@return Amount
	  */
	public BigDecimal getAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Amt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	public I_GS_HR_Compensation_Master getGS_HR_Compensation_Master() throws RuntimeException
    {
		return (I_GS_HR_Compensation_Master)MTable.get(getCtx(), I_GS_HR_Compensation_Master.Table_Name)
			.getPO(getGS_HR_Compensation_Master_ID(), get_TrxName());	}

	/** Set Compensation Type.
		@param GS_HR_Compensation_Master_ID Compensation Type	  */
	public void setGS_HR_Compensation_Master_ID (int GS_HR_Compensation_Master_ID)
	{
		if (GS_HR_Compensation_Master_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Compensation_Master_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Compensation_Master_ID, Integer.valueOf(GS_HR_Compensation_Master_ID));
	}

	/** Get Compensation Type.
		@return Compensation Type	  */
	public int getGS_HR_Compensation_Master_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Compensation_Master_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getGS_HR_Compensation_Master_ID()));
    }

	/** Set Employee Compensation.
		@param GS_HR_EmpCompensation_ID Employee Compensation	  */
	public void setGS_HR_EmpCompensation_ID (int GS_HR_EmpCompensation_ID)
	{
		if (GS_HR_EmpCompensation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmpCompensation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmpCompensation_ID, Integer.valueOf(GS_HR_EmpCompensation_ID));
	}

	/** Get Employee Compensation.
		@return Employee Compensation	  */
	public int getGS_HR_EmpCompensation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_EmpCompensation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_EmpCompensation_UU.
		@param GS_HR_EmpCompensation_UU GS_HR_EmpCompensation_UU	  */
	public void setGS_HR_EmpCompensation_UU (String GS_HR_EmpCompensation_UU)
	{
		set_Value (COLUMNNAME_GS_HR_EmpCompensation_UU, GS_HR_EmpCompensation_UU);
	}

	/** Get GS_HR_EmpCompensation_UU.
		@return GS_HR_EmpCompensation_UU	  */
	public String getGS_HR_EmpCompensation_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_EmpCompensation_UU);
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

	/** Set Earning.
		@param GS_HR_IsEarning 
		Earning
	  */
	public void setGS_HR_IsEarning (boolean GS_HR_IsEarning)
	{
		set_Value (COLUMNNAME_GS_HR_IsEarning, Boolean.valueOf(GS_HR_IsEarning));
	}

	/** Get Earning.
		@return Earning
	  */
	public boolean isGS_HR_IsEarning () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_IsEarning);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percent.
		@param GS_HR_IsPercent 
		Percent
	  */
	public void setGS_HR_IsPercent (boolean GS_HR_IsPercent)
	{
		set_Value (COLUMNNAME_GS_HR_IsPercent, Boolean.valueOf(GS_HR_IsPercent));
	}

	/** Get Percent.
		@return Percent
	  */
	public boolean isGS_HR_IsPercent () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_IsPercent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percent.
		@param Percent 
		Percentage
	  */
	public void setPercent (BigDecimal Percent)
	{
		set_Value (COLUMNNAME_Percent, Percent);
	}

	/** Get Percent.
		@return Percentage
	  */
	public BigDecimal getPercent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Percent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}