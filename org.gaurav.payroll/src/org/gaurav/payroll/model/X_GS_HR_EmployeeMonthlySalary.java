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

/** Generated Model for GS_HR_EmployeeMonthlySalary
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_EmployeeMonthlySalary extends PO implements I_GS_HR_EmployeeMonthlySalary, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_EmployeeMonthlySalary (Properties ctx, int GS_HR_EmployeeMonthlySalary_ID, String trxName)
    {
      super (ctx, GS_HR_EmployeeMonthlySalary_ID, trxName);
      /** if (GS_HR_EmployeeMonthlySalary_ID == 0)
        {
			setGS_HR_EmployeeMonthlySalary_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_EmployeeMonthlySalary (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_EmployeeMonthlySalary[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Employee Monthly Salary.
		@param GS_HR_EmployeeMonthlySalary_ID Employee Monthly Salary	  */
	public void setGS_HR_EmployeeMonthlySalary_ID (int GS_HR_EmployeeMonthlySalary_ID)
	{
		if (GS_HR_EmployeeMonthlySalary_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmployeeMonthlySalary_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmployeeMonthlySalary_ID, Integer.valueOf(GS_HR_EmployeeMonthlySalary_ID));
	}

	/** Get Employee Monthly Salary.
		@return Employee Monthly Salary	  */
	public int getGS_HR_EmployeeMonthlySalary_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_EmployeeMonthlySalary_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_EmployeeMonthlySalary_UU.
		@param GS_HR_EmployeeMonthlySalary_UU GS_HR_EmployeeMonthlySalary_UU	  */
	public void setGS_HR_EmployeeMonthlySalary_UU (String GS_HR_EmployeeMonthlySalary_UU)
	{
		set_Value (COLUMNNAME_GS_HR_EmployeeMonthlySalary_UU, GS_HR_EmployeeMonthlySalary_UU);
	}

	/** Get GS_HR_EmployeeMonthlySalary_UU.
		@return GS_HR_EmployeeMonthlySalary_UU	  */
	public String getGS_HR_EmployeeMonthlySalary_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_EmployeeMonthlySalary_UU);
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

	/** Set Gross Amount.
		@param GS_HR_GrossAmt 
		Amount
	  */
	public void setGS_HR_GrossAmt (BigDecimal GS_HR_GrossAmt)
	{
		set_Value (COLUMNNAME_GS_HR_GrossAmt, GS_HR_GrossAmt);
	}

	/** Get Gross Amount.
		@return Amount
	  */
	public BigDecimal getGS_HR_GrossAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_GrossAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_GS_HR_MonthlySalary getGS_HR_MonthlySalary() throws RuntimeException
    {
		return (I_GS_HR_MonthlySalary)MTable.get(getCtx(), I_GS_HR_MonthlySalary.Table_Name)
			.getPO(getGS_HR_MonthlySalary_ID(), get_TrxName());	}

	/** Set Monthly Salary.
		@param GS_HR_MonthlySalary_ID Monthly Salary	  */
	public void setGS_HR_MonthlySalary_ID (int GS_HR_MonthlySalary_ID)
	{
		if (GS_HR_MonthlySalary_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_MonthlySalary_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_MonthlySalary_ID, Integer.valueOf(GS_HR_MonthlySalary_ID));
	}

	/** Get Monthly Salary.
		@return Monthly Salary	  */
	public int getGS_HR_MonthlySalary_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_MonthlySalary_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Net Amount.
		@param GS_HR_NetAmt 
		Amount
	  */
	public void setGS_HR_NetAmt (BigDecimal GS_HR_NetAmt)
	{
		set_Value (COLUMNNAME_GS_HR_NetAmt, GS_HR_NetAmt);
	}

	/** Get Net Amount.
		@return Amount
	  */
	public BigDecimal getGS_HR_NetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_NetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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