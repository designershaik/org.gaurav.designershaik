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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DS_FixedDeposit
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_FixedDeposit extends PO implements I_DS_FixedDeposit, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_FixedDeposit (Properties ctx, int DS_FixedDeposit_ID, String trxName)
    {
      super (ctx, DS_FixedDeposit_ID, trxName);
      /** if (DS_FixedDeposit_ID == 0)
        {
			setC_BankAccount_ID (0);
			setC_Bank_ID (0);
			setC_BPartner_ID (0);
			setC_Currency_ID (0);
			setDateAcct (new Timestamp( System.currentTimeMillis() ));
			setDS_BaseInterestAmount (Env.ZERO);
			setDS_BasePrincipalAmount (Env.ZERO);
			setDS_FDTerm (0);
			setDS_FixedDeposit_ID (0);
			setDS_InterestAmtCharge_ID (0);
			setDS_MaturityDate (new Timestamp( System.currentTimeMillis() ));
			setDS_PrincipalAmount (Env.ZERO);
			setDS_PrincipalAmtCharge_ID (0);
			setInterestAmt (Env.ZERO);
			setInterestPercent (Env.ZERO);
        } */
    }

    /** Load Constructor */
    public X_DS_FixedDeposit (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_FixedDeposit[")
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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ConversionType getC_ConversionType() throws RuntimeException
    {
		return (org.compiere.model.I_C_ConversionType)MTable.get(getCtx(), org.compiere.model.I_C_ConversionType.Table_Name)
			.getPO(getC_ConversionType_ID(), get_TrxName());	}

	/** Set Currency Type.
		@param C_ConversionType_ID 
		Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID)
	{
		if (C_ConversionType_ID < 1) 
			set_Value (COLUMNNAME_C_ConversionType_ID, null);
		else 
			set_Value (COLUMNNAME_C_ConversionType_ID, Integer.valueOf(C_ConversionType_ID));
	}

	/** Get Currency Type.
		@return Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ConversionType_ID);
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

	/** Set Close Date.
		@param CloseDate 
		Close Date
	  */
	public void setCloseDate (Timestamp CloseDate)
	{
		set_Value (COLUMNNAME_CloseDate, CloseDate);
	}

	/** Get Close Date.
		@return Close Date
	  */
	public Timestamp getCloseDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_CloseDate);
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_ValueNoCheck (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Interest Amount(Base).
		@param DS_BaseInterestAmount Interest Amount(Base)	  */
	public void setDS_BaseInterestAmount (BigDecimal DS_BaseInterestAmount)
	{
		set_Value (COLUMNNAME_DS_BaseInterestAmount, DS_BaseInterestAmount);
	}

	/** Get Interest Amount(Base).
		@return Interest Amount(Base)	  */
	public BigDecimal getDS_BaseInterestAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_BaseInterestAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Certificate Number.
		@param DS_CertificateNumber Certificate Number	  */
	public void setDS_CertificateNumber (String DS_CertificateNumber)
	{
		set_Value (COLUMNNAME_DS_CertificateNumber, DS_CertificateNumber);
	}

	/** Get Certificate Number.
		@return Certificate Number	  */
	public String getDS_CertificateNumber () 
	{
		return (String)get_Value(COLUMNNAME_DS_CertificateNumber);
	}

	/** Set Create FD.
		@param DS_CreateFD Create FD	  */
	public void setDS_CreateFD (String DS_CreateFD)
	{
		set_Value (COLUMNNAME_DS_CreateFD, DS_CreateFD);
	}

	/** Get Create FD.
		@return Create FD	  */
	public String getDS_CreateFD () 
	{
		return (String)get_Value(COLUMNNAME_DS_CreateFD);
	}

	/** Set Term For FD.
		@param DS_FDTerm Term For FD	  */
	public void setDS_FDTerm (int DS_FDTerm)
	{
		set_Value (COLUMNNAME_DS_FDTerm, Integer.valueOf(DS_FDTerm));
	}

	/** Get Term For FD.
		@return Term For FD	  */
	public int getDS_FDTerm () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_FDTerm);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Terminate = TE */
	public static final String DS_FDTERMINATEORRENEW_Terminate = "TE";
	/** Renew = RE */
	public static final String DS_FDTERMINATEORRENEW_Renew = "RE";
	/** New = NE */
	public static final String DS_FDTERMINATEORRENEW_New = "NE";
	/** Set Terminate/Renew FD.
		@param DS_FDTerminateOrRenew Terminate/Renew FD	  */
	public void setDS_FDTerminateOrRenew (String DS_FDTerminateOrRenew)
	{

		set_Value (COLUMNNAME_DS_FDTerminateOrRenew, DS_FDTerminateOrRenew);
	}

	/** Get Terminate/Renew FD.
		@return Terminate/Renew FD	  */
	public String getDS_FDTerminateOrRenew () 
	{
		return (String)get_Value(COLUMNNAME_DS_FDTerminateOrRenew);
	}

	/** Set Fixed Deposit Registration.
		@param DS_FixedDeposit_ID Fixed Deposit Registration	  */
	public void setDS_FixedDeposit_ID (int DS_FixedDeposit_ID)
	{
		if (DS_FixedDeposit_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_FixedDeposit_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_FixedDeposit_ID, Integer.valueOf(DS_FixedDeposit_ID));
	}

	/** Get Fixed Deposit Registration.
		@return Fixed Deposit Registration	  */
	public int getDS_FixedDeposit_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_FixedDeposit_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_FixedDeposit_UU.
		@param DS_FixedDeposit_UU DS_FixedDeposit_UU	  */
	public void setDS_FixedDeposit_UU (String DS_FixedDeposit_UU)
	{
		set_Value (COLUMNNAME_DS_FixedDeposit_UU, DS_FixedDeposit_UU);
	}

	/** Get DS_FixedDeposit_UU.
		@return DS_FixedDeposit_UU	  */
	public String getDS_FixedDeposit_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_FixedDeposit_UU);
	}

	public org.compiere.model.I_C_Charge getDS_InterestAmtCharge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_InterestAmtCharge_ID(), get_TrxName());	}

	/** Set Charge for Interest Amount.
		@param DS_InterestAmtCharge_ID Charge for Interest Amount	  */
	public void setDS_InterestAmtCharge_ID (int DS_InterestAmtCharge_ID)
	{
		if (DS_InterestAmtCharge_ID < 1) 
			set_Value (COLUMNNAME_DS_InterestAmtCharge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_InterestAmtCharge_ID, Integer.valueOf(DS_InterestAmtCharge_ID));
	}

	/** Get Charge for Interest Amount.
		@return Charge for Interest Amount	  */
	public int getDS_InterestAmtCharge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_InterestAmtCharge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set New FD.
		@param DS_IsNewFD New FD	  */
	public void setDS_IsNewFD (boolean DS_IsNewFD)
	{
		set_Value (COLUMNNAME_DS_IsNewFD, Boolean.valueOf(DS_IsNewFD));
	}

	/** Get New FD.
		@return New FD	  */
	public boolean isDS_IsNewFD () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsNewFD);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Terminated.
		@param DS_IsTerminated Terminated	  */
	public void setDS_IsTerminated (boolean DS_IsTerminated)
	{
		set_Value (COLUMNNAME_DS_IsTerminated, Boolean.valueOf(DS_IsTerminated));
	}

	/** Get Terminated.
		@return Terminated	  */
	public boolean isDS_IsTerminated () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsTerminated);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Matured.
		@param DS_Matured 
		FD Matured
	  */
	public void setDS_Matured (boolean DS_Matured)
	{
		set_Value (COLUMNNAME_DS_Matured, Boolean.valueOf(DS_Matured));
	}

	/** Get Matured.
		@return FD Matured
	  */
	public boolean isDS_Matured () 
	{
		Object oo = get_Value(COLUMNNAME_DS_Matured);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Maturity Date.
		@param DS_MaturityDate Maturity Date	  */
	public void setDS_MaturityDate (Timestamp DS_MaturityDate)
	{
		set_Value (COLUMNNAME_DS_MaturityDate, DS_MaturityDate);
	}

	/** Get Maturity Date.
		@return Maturity Date	  */
	public Timestamp getDS_MaturityDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_MaturityDate);
	}

	/** Set Principal Amount.
		@param DS_PrincipalAmount Principal Amount	  */
	public void setDS_PrincipalAmount (BigDecimal DS_PrincipalAmount)
	{
		set_Value (COLUMNNAME_DS_PrincipalAmount, DS_PrincipalAmount);
	}

	/** Get Principal Amount.
		@return Principal Amount	  */
	public BigDecimal getDS_PrincipalAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_PrincipalAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_Charge getDS_PrincipalAmtCharge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getDS_PrincipalAmtCharge_ID(), get_TrxName());	}

	/** Set Charge for Principal Amount.
		@param DS_PrincipalAmtCharge_ID Charge for Principal Amount	  */
	public void setDS_PrincipalAmtCharge_ID (int DS_PrincipalAmtCharge_ID)
	{
		if (DS_PrincipalAmtCharge_ID < 1) 
			set_Value (COLUMNNAME_DS_PrincipalAmtCharge_ID, null);
		else 
			set_Value (COLUMNNAME_DS_PrincipalAmtCharge_ID, Integer.valueOf(DS_PrincipalAmtCharge_ID));
	}

	/** Get Charge for Principal Amount.
		@return Charge for Principal Amount	  */
	public int getDS_PrincipalAmtCharge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_PrincipalAmtCharge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DS_FixedDeposit getDS_RenewedFD() throws RuntimeException
    {
		return (I_DS_FixedDeposit)MTable.get(getCtx(), I_DS_FixedDeposit.Table_Name)
			.getPO(getDS_RenewedFD_ID(), get_TrxName());	}

	/** Set Renewed FD.
		@param DS_RenewedFD_ID 
		Renewed FD
	  */
	public void setDS_RenewedFD_ID (int DS_RenewedFD_ID)
	{
		if (DS_RenewedFD_ID < 1) 
			set_Value (COLUMNNAME_DS_RenewedFD_ID, null);
		else 
			set_Value (COLUMNNAME_DS_RenewedFD_ID, Integer.valueOf(DS_RenewedFD_ID));
	}

	/** Get Renewed FD.
		@return Renewed FD
	  */
	public int getDS_RenewedFD_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RenewedFD_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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