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
package org.gaurav.dsi.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DS_DenominationOnStatement
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_DenominationOnStatement extends PO implements I_DS_DenominationOnStatement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190128L;

    /** Standard Constructor */
    public X_DS_DenominationOnStatement (Properties ctx, int DS_DenominationOnStatement_ID, String trxName)
    {
      super (ctx, DS_DenominationOnStatement_ID, trxName);
      /** if (DS_DenominationOnStatement_ID == 0)
        {
			setC_BankAccount_ID (0);
// @C_BankAccount_ID@
			setDS_DenominationOnStatement_ID (0);
			setDS_DenominationType (null);
        } */
    }

    /** Load Constructor */
    public X_DS_DenominationOnStatement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_DenominationOnStatement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BankStatement getC_BankStatement() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankStatement)MTable.get(getCtx(), org.compiere.model.I_C_BankStatement.Table_Name)
			.getPO(getC_BankStatement_ID(), get_TrxName());	}

	/** Set Bank Statement.
		@param C_BankStatement_ID 
		Bank Statement of account
	  */
	public void setC_BankStatement_ID (int C_BankStatement_ID)
	{
		if (C_BankStatement_ID < 1) 
			set_Value (COLUMNNAME_C_BankStatement_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankStatement_ID, Integer.valueOf(C_BankStatement_ID));
	}

	/** Get Bank Statement.
		@return Bank Statement of account
	  */
	public int getC_BankStatement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankStatement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Denomination Summary On Statement.
		@param DS_DenominationOnStatement_ID Denomination Summary On Statement	  */
	public void setDS_DenominationOnStatement_ID (int DS_DenominationOnStatement_ID)
	{
		if (DS_DenominationOnStatement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_DenominationOnStatement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_DenominationOnStatement_ID, Integer.valueOf(DS_DenominationOnStatement_ID));
	}

	/** Get Denomination Summary On Statement.
		@return Denomination Summary On Statement	  */
	public int getDS_DenominationOnStatement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_DenominationOnStatement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_DenominationOnStatement_UU.
		@param DS_DenominationOnStatement_UU DS_DenominationOnStatement_UU	  */
	public void setDS_DenominationOnStatement_UU (String DS_DenominationOnStatement_UU)
	{
		set_Value (COLUMNNAME_DS_DenominationOnStatement_UU, DS_DenominationOnStatement_UU);
	}

	/** Get DS_DenominationOnStatement_UU.
		@return DS_DenominationOnStatement_UU	  */
	public String getDS_DenominationOnStatement_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_DenominationOnStatement_UU);
	}

	/** 0.100 = 0.100 */
	public static final String DS_DENOMINATIONTYPE_0100 = "0.100";
	/** 0.005 = 0.005 */
	public static final String DS_DENOMINATIONTYPE_0005 = "0.005";
	/** 0.025 = 0.025 */
	public static final String DS_DENOMINATIONTYPE_0025 = "0.025";
	/** 0.010 = 0.010 */
	public static final String DS_DENOMINATIONTYPE_0010 = "0.010";
	/** 0.500 = 0.500 */
	public static final String DS_DENOMINATIONTYPE_0500 = "0.500";
	/** 1 = 1 */
	public static final String DS_DENOMINATIONTYPE_1 = "1";
	/** 5 = 5 */
	public static final String DS_DENOMINATIONTYPE_5 = "5";
	/** 10 = 10 */
	public static final String DS_DENOMINATIONTYPE_10 = "10";
	/** 20 = 20 */
	public static final String DS_DENOMINATIONTYPE_20 = "20";
	/** 50 = 50 */
	public static final String DS_DENOMINATIONTYPE_50 = "50";
	/** 100 = 100 */
	public static final String DS_DENOMINATIONTYPE_100 = "100";
	/** 500 = 500 */
	public static final String DS_DENOMINATIONTYPE_500 = "500";
	/** 200 = 200 */
	public static final String DS_DENOMINATIONTYPE_200 = "200";
	/** 0.250 = 0.250 */
	public static final String DS_DENOMINATIONTYPE_0250 = "0.250";
	/** 0.05 = 0.05 */
	public static final String DS_DENOMINATIONTYPE_005 = "0.05";
	/** 0.2 = 0.2 */
	public static final String DS_DENOMINATIONTYPE_02 = "0.2";
	/** 1000 = 1000 */
	public static final String DS_DENOMINATIONTYPE_1000 = "1000";
	/** Set Denomination Type.
		@param DS_DenominationType Denomination Type	  */
	public void setDS_DenominationType (String DS_DenominationType)
	{

		set_Value (COLUMNNAME_DS_DenominationType, DS_DenominationType);
	}

	/** Get Denomination Type.
		@return Denomination Type	  */
	public String getDS_DenominationType () 
	{
		return (String)get_Value(COLUMNNAME_DS_DenominationType);
	}

	/** Set Manual.
		@param IsManual 
		This is a manual process
	  */
	public void setIsManual (boolean IsManual)
	{
		set_Value (COLUMNNAME_IsManual, Boolean.valueOf(IsManual));
	}

	/** Get Manual.
		@return This is a manual process
	  */
	public boolean isManual () 
	{
		Object oo = get_Value(COLUMNNAME_IsManual);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Total Amount.
		@param TotalAmt 
		Total Amount
	  */
	public void setTotalAmt (BigDecimal TotalAmt)
	{
		throw new IllegalArgumentException ("TotalAmt is virtual column");	}

	/** Get Total Amount.
		@return Total Amount
	  */
	public BigDecimal getTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}