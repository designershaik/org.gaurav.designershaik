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

/** Generated Model for HR_Contract_Leave
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_HR_Contract_Leave extends PO implements I_HR_Contract_Leave, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191223L;

    /** Standard Constructor */
    public X_HR_Contract_Leave (Properties ctx, int HR_Contract_Leave_ID, String trxName)
    {
      super (ctx, HR_Contract_Leave_ID, trxName);
      /** if (HR_Contract_Leave_ID == 0)
        {
			setHR_Contract_Leave_ID (0);
// @#AD_Org_ID@
        } */
    }

    /** Load Constructor */
    public X_HR_Contract_Leave (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_Contract_Leave[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_GS_ContractCalendar getGS_ContractCalendar() throws RuntimeException
    {
		return (I_GS_ContractCalendar)MTable.get(getCtx(), I_GS_ContractCalendar.Table_Name)
			.getPO(getGS_ContractCalendar_ID(), get_TrxName());	}

	/** Set Contract Calendar.
		@param GS_ContractCalendar_ID Contract Calendar	  */
	public void setGS_ContractCalendar_ID (int GS_ContractCalendar_ID)
	{
		if (GS_ContractCalendar_ID < 1) 
			set_Value (COLUMNNAME_GS_ContractCalendar_ID, null);
		else 
			set_Value (COLUMNNAME_GS_ContractCalendar_ID, Integer.valueOf(GS_ContractCalendar_ID));
	}

	/** Get Contract Calendar.
		@return Contract Calendar	  */
	public int getGS_ContractCalendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_ContractCalendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_GS_HR_Leave_Master getGS_HR_Leave_Master() throws RuntimeException
    {
		return (I_GS_HR_Leave_Master)MTable.get(getCtx(), I_GS_HR_Leave_Master.Table_Name)
			.getPO(getGS_HR_Leave_Master_ID(), get_TrxName());	}

	/** Set Leave Type.
		@param GS_HR_Leave_Master_ID Leave Type	  */
	public void setGS_HR_Leave_Master_ID (int GS_HR_Leave_Master_ID)
	{
		if (GS_HR_Leave_Master_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Leave_Master_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Leave_Master_ID, Integer.valueOf(GS_HR_Leave_Master_ID));
	}

	/** Get Leave Type.
		@return Leave Type	  */
	public int getGS_HR_Leave_Master_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Leave_Master_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leaves Allowed.
		@param GS_HR_LeavesAllowed 
		Total number of leaves allowed per employee
	  */
	public void setGS_HR_LeavesAllowed (BigDecimal GS_HR_LeavesAllowed)
	{
		set_Value (COLUMNNAME_GS_HR_LeavesAllowed, GS_HR_LeavesAllowed);
	}

	/** Get Leaves Allowed.
		@return Total number of leaves allowed per employee
	  */
	public BigDecimal getGS_HR_LeavesAllowed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_LeavesAllowed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Leaves Taken.
		@param GS_HR_LeavesConsumed 
		Total number of leaves taken by employee
	  */
	public void setGS_HR_LeavesConsumed (BigDecimal GS_HR_LeavesConsumed)
	{
		set_Value (COLUMNNAME_GS_HR_LeavesConsumed, GS_HR_LeavesConsumed);
	}

	/** Get Leaves Taken.
		@return Total number of leaves taken by employee
	  */
	public BigDecimal getGS_HR_LeavesConsumed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_LeavesConsumed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Leaves Remaining.
		@param GS_HR_LeavesRemaining 
		Total number of leaves remained
	  */
	public void setGS_HR_LeavesRemaining (BigDecimal GS_HR_LeavesRemaining)
	{
		set_Value (COLUMNNAME_GS_HR_LeavesRemaining, GS_HR_LeavesRemaining);
	}

	/** Get Leaves Remaining.
		@return Total number of leaves remained
	  */
	public BigDecimal getGS_HR_LeavesRemaining () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_LeavesRemaining);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_HR_Contract getHR_Contract() throws RuntimeException
    {
		return (I_HR_Contract)MTable.get(getCtx(), I_HR_Contract.Table_Name)
			.getPO(getHR_Contract_ID(), get_TrxName());	}

	/** Set Payroll Contract.
		@param HR_Contract_ID Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID)
	{
		if (HR_Contract_ID < 1) 
			set_Value (COLUMNNAME_HR_Contract_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Contract_ID, Integer.valueOf(HR_Contract_ID));
	}

	/** Get Payroll Contract.
		@return Payroll Contract	  */
	public int getHR_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leave Details.
		@param HR_Contract_Leave_ID Leave Details	  */
	public void setHR_Contract_Leave_ID (int HR_Contract_Leave_ID)
	{
		if (HR_Contract_Leave_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Contract_Leave_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Contract_Leave_ID, Integer.valueOf(HR_Contract_Leave_ID));
	}

	/** Get Leave Details.
		@return Leave Details	  */
	public int getHR_Contract_Leave_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contract_Leave_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Contract_Leave_UU.
		@param HR_Contract_Leave_UU HR_Contract_Leave_UU	  */
	public void setHR_Contract_Leave_UU (String HR_Contract_Leave_UU)
	{
		set_Value (COLUMNNAME_HR_Contract_Leave_UU, HR_Contract_Leave_UU);
	}

	/** Get HR_Contract_Leave_UU.
		@return HR_Contract_Leave_UU	  */
	public String getHR_Contract_Leave_UU () 
	{
		return (String)get_Value(COLUMNNAME_HR_Contract_Leave_UU);
	}
}