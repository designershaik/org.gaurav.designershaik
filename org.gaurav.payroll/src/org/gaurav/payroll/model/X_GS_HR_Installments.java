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

/** Generated Model for GS_HR_Installments
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_Installments extends PO implements I_GS_HR_Installments, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_Installments (Properties ctx, int GS_HR_Installments_ID, String trxName)
    {
      super (ctx, GS_HR_Installments_ID, trxName);
      /** if (GS_HR_Installments_ID == 0)
        {
			setGS_HR_Installments_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Installments (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Installments[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
	}

	public I_GS_HR_EmployeeAdvance getGS_HR_EmployeeAdvance() throws RuntimeException
    {
		return (I_GS_HR_EmployeeAdvance)MTable.get(getCtx(), I_GS_HR_EmployeeAdvance.Table_Name)
			.getPO(getGS_HR_EmployeeAdvance_ID(), get_TrxName());	}

	/** Set Employee Advance.
		@param GS_HR_EmployeeAdvance_ID Employee Advance	  */
	public void setGS_HR_EmployeeAdvance_ID (int GS_HR_EmployeeAdvance_ID)
	{
		if (GS_HR_EmployeeAdvance_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_EmployeeAdvance_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_EmployeeAdvance_ID, Integer.valueOf(GS_HR_EmployeeAdvance_ID));
	}

	/** Get Employee Advance.
		@return Employee Advance	  */
	public int getGS_HR_EmployeeAdvance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_EmployeeAdvance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Installments.
		@param GS_HR_Installments_ID Installments	  */
	public void setGS_HR_Installments_ID (int GS_HR_Installments_ID)
	{
		if (GS_HR_Installments_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Installments_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Installments_ID, Integer.valueOf(GS_HR_Installments_ID));
	}

	/** Get Installments.
		@return Installments	  */
	public int getGS_HR_Installments_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Installments_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Installments_UU.
		@param GS_HR_Installments_UU GS_HR_Installments_UU	  */
	public void setGS_HR_Installments_UU (String GS_HR_Installments_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Installments_UU, GS_HR_Installments_UU);
	}

	/** Get GS_HR_Installments_UU.
		@return GS_HR_Installments_UU	  */
	public String getGS_HR_Installments_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Installments_UU);
	}

	/** Set Per Month Installment.
		@param GS_InstallmentAmt 
		Per Month Installment
	  */
	public void setGS_InstallmentAmt (BigDecimal GS_InstallmentAmt)
	{
		set_Value (COLUMNNAME_GS_InstallmentAmt, GS_InstallmentAmt);
	}

	/** Get Per Month Installment.
		@return Per Month Installment
	  */
	public BigDecimal getGS_InstallmentAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_InstallmentAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Break.
		@param HR_Break Break	  */
	public void setHR_Break (boolean HR_Break)
	{
		set_Value (COLUMNNAME_HR_Break, Boolean.valueOf(HR_Break));
	}

	/** Get Break.
		@return Break	  */
	public boolean isHR_Break () 
	{
		Object oo = get_Value(COLUMNNAME_HR_Break);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (boolean IsPaid)
	{
		set_Value (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
	}

	/** Get Paid.
		@return The document is paid
	  */
	public boolean isPaid () 
	{
		Object oo = get_Value(COLUMNNAME_IsPaid);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment date.
		@param PayDate 
		Date Payment made
	  */
	public void setPayDate (Timestamp PayDate)
	{
		set_Value (COLUMNNAME_PayDate, PayDate);
	}

	/** Get Payment date.
		@return Date Payment made
	  */
	public Timestamp getPayDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PayDate);
	}

	/** Set Remaining Amt.
		@param RemainingAmt 
		Remaining Amount
	  */
	public void setRemainingAmt (BigDecimal RemainingAmt)
	{
		set_Value (COLUMNNAME_RemainingAmt, RemainingAmt);
	}

	/** Get Remaining Amt.
		@return Remaining Amount
	  */
	public BigDecimal getRemainingAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_RemainingAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}