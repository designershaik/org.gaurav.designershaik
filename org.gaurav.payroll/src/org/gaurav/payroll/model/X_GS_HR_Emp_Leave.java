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

/** Generated Model for GS_HR_Emp_Leave
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Emp_Leave extends PO implements I_GS_HR_Emp_Leave, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_Emp_Leave (Properties ctx, int GS_HR_Emp_Leave_ID, String trxName)
    {
      super (ctx, GS_HR_Emp_Leave_ID, trxName);
      /** if (GS_HR_Emp_Leave_ID == 0)
        {
			setGS_HR_Emp_Leave_ID (0);
// @#AD_Org_ID@
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Emp_Leave (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Emp_Leave[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Employee Leave.
		@param GS_HR_Emp_Leave_ID Employee Leave	  */
	public void setGS_HR_Emp_Leave_ID (int GS_HR_Emp_Leave_ID)
	{
		if (GS_HR_Emp_Leave_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Emp_Leave_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Emp_Leave_ID, Integer.valueOf(GS_HR_Emp_Leave_ID));
	}

	/** Get Employee Leave.
		@return Employee Leave	  */
	public int getGS_HR_Emp_Leave_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Emp_Leave_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Emp_Leave_UU.
		@param GS_HR_Emp_Leave_UU GS_HR_Emp_Leave_UU	  */
	public void setGS_HR_Emp_Leave_UU (String GS_HR_Emp_Leave_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Emp_Leave_UU, GS_HR_Emp_Leave_UU);
	}

	/** Get GS_HR_Emp_Leave_UU.
		@return GS_HR_Emp_Leave_UU	  */
	public String getGS_HR_Emp_Leave_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Emp_Leave_UU);
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
}