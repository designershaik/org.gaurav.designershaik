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

/** Generated Model for DS_BankBalancesFD
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_BankBalancesFD extends PO implements I_DS_BankBalancesFD, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200419L;

    /** Standard Constructor */
    public X_DS_BankBalancesFD (Properties ctx, int DS_BankBalancesFD_ID, String trxName)
    {
      super (ctx, DS_BankBalancesFD_ID, trxName);
      /** if (DS_BankBalancesFD_ID == 0)
        {
			setAccountNo (null);
			setC_Bank_ID (0);
			setC_Currency_ID (0);
			setCurrentBalance (Env.ZERO);
			setDS_BankBalancesFD_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DS_BankBalancesFD (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_BankBalancesFD[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Account No.
		@param AccountNo 
		Account Number
	  */
	public void setAccountNo (String AccountNo)
	{
		set_Value (COLUMNNAME_AccountNo, AccountNo);
	}

	/** Get Account No.
		@return Account Number
	  */
	public String getAccountNo () 
	{
		return (String)get_Value(COLUMNNAME_AccountNo);
	}

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException
    {
		return (org.compiere.model.I_C_Bank)MTable.get(getCtx(), org.compiere.model.I_C_Bank.Table_Name)
			.getPO(getC_Bank_ID(), get_TrxName());	}

	/** Set Bank.
		@param C_Bank_ID 
		Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID)
	{
		if (C_Bank_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Bank_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Bank_ID, Integer.valueOf(C_Bank_ID));
	}

	/** Get Bank.
		@return Bank
	  */
	public int getC_Bank_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Bank_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current balance.
		@param CurrentBalance 
		Current Balance
	  */
	public void setCurrentBalance (BigDecimal CurrentBalance)
	{
		set_Value (COLUMNNAME_CurrentBalance, CurrentBalance);
	}

	/** Get Current balance.
		@return Current Balance
	  */
	public BigDecimal getCurrentBalance () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_CurrentBalance);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set AED.
		@param DS_AED AED	  */
	public void setDS_AED (BigDecimal DS_AED)
	{
		set_Value (COLUMNNAME_DS_AED, DS_AED);
	}

	/** Get AED.
		@return AED	  */
	public BigDecimal getDS_AED () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_AED);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Bank Balance & FD.
		@param DS_BankBalancesFD_ID Bank Balance & FD	  */
	public void setDS_BankBalancesFD_ID (int DS_BankBalancesFD_ID)
	{
		if (DS_BankBalancesFD_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_BankBalancesFD_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_BankBalancesFD_ID, Integer.valueOf(DS_BankBalancesFD_ID));
	}

	/** Get Bank Balance & FD.
		@return Bank Balance & FD	  */
	public int getDS_BankBalancesFD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_BankBalancesFD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_BankBalancesFD_UU.
		@param DS_BankBalancesFD_UU DS_BankBalancesFD_UU	  */
	public void setDS_BankBalancesFD_UU (String DS_BankBalancesFD_UU)
	{
		set_Value (COLUMNNAME_DS_BankBalancesFD_UU, DS_BankBalancesFD_UU);
	}

	/** Get DS_BankBalancesFD_UU.
		@return DS_BankBalancesFD_UU	  */
	public String getDS_BankBalancesFD_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_BankBalancesFD_UU);
	}

	/** Set Base Currency Principal Amount.
		@param DS_BasePrincipalAmount Base Currency Principal Amount	  */
	public void setDS_BasePrincipalAmount (BigDecimal DS_BasePrincipalAmount)
	{
		set_Value (COLUMNNAME_DS_BasePrincipalAmount, DS_BasePrincipalAmount);
	}

	/** Get Base Currency Principal Amount.
		@return Base Currency Principal Amount	  */
	public BigDecimal getDS_BasePrincipalAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_BasePrincipalAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set BHD.
		@param DS_BHD BHD	  */
	public void setDS_BHD (BigDecimal DS_BHD)
	{
		set_Value (COLUMNNAME_DS_BHD, DS_BHD);
	}

	/** Get BHD.
		@return BHD	  */
	public BigDecimal getDS_BHD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_BHD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set EUR.
		@param DS_EUR EUR	  */
	public void setDS_EUR (BigDecimal DS_EUR)
	{
		set_Value (COLUMNNAME_DS_EUR, DS_EUR);
	}

	/** Get EUR.
		@return EUR	  */
	public BigDecimal getDS_EUR () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_EUR);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set GBP.
		@param DS_GBP GBP	  */
	public void setDS_GBP (BigDecimal DS_GBP)
	{
		set_Value (COLUMNNAME_DS_GBP, DS_GBP);
	}

	/** Get GBP.
		@return GBP	  */
	public BigDecimal getDS_GBP () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_GBP);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set KWD.
		@param DS_KWD KWD	  */
	public void setDS_KWD (BigDecimal DS_KWD)
	{
		set_Value (COLUMNNAME_DS_KWD, DS_KWD);
	}

	/** Get KWD.
		@return KWD	  */
	public BigDecimal getDS_KWD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_KWD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set OMR.
		@param DS_OMR OMR	  */
	public void setDS_OMR (BigDecimal DS_OMR)
	{
		set_Value (COLUMNNAME_DS_OMR, DS_OMR);
	}

	/** Get OMR.
		@return OMR	  */
	public BigDecimal getDS_OMR () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_OMR);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set QAR.
		@param DS_QAR QAR	  */
	public void setDS_QAR (BigDecimal DS_QAR)
	{
		set_Value (COLUMNNAME_DS_QAR, DS_QAR);
	}

	/** Get QAR.
		@return QAR	  */
	public BigDecimal getDS_QAR () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_QAR);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set SAR.
		@param DS_SAR SAR	  */
	public void setDS_SAR (BigDecimal DS_SAR)
	{
		set_Value (COLUMNNAME_DS_SAR, DS_SAR);
	}

	/** Get SAR.
		@return SAR	  */
	public BigDecimal getDS_SAR () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_SAR);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set USD.
		@param DS_USD USD	  */
	public void setDS_USD (BigDecimal DS_USD)
	{
		set_Value (COLUMNNAME_DS_USD, DS_USD);
	}

	/** Get USD.
		@return USD	  */
	public BigDecimal getDS_USD () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_USD);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Interest Amount.
		@param InterestAmt 
		Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt)
	{
		set_Value (COLUMNNAME_InterestAmt, InterestAmt);
	}

	/** Get Interest Amount.
		@return Interest Amount
	  */
	public BigDecimal getInterestAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Interest in percent.
		@param InterestPercent 
		Percentage interest to charge on overdue invoices
	  */
	public void setInterestPercent (BigDecimal InterestPercent)
	{
		set_Value (COLUMNNAME_InterestPercent, InterestPercent);
	}

	/** Get Interest in percent.
		@return Percentage interest to charge on overdue invoices
	  */
	public BigDecimal getInterestPercent () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InterestPercent);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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