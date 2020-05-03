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
import org.compiere.util.KeyNamePair;

/** Generated Model for DS_ImportInvoices_Filing
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_ImportInvoices_Filing extends PO implements I_DS_ImportInvoices_Filing, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200119L;

    /** Standard Constructor */
    public X_DS_ImportInvoices_Filing (Properties ctx, int DS_ImportInvoices_Filing_ID, String trxName)
    {
      super (ctx, DS_ImportInvoices_Filing_ID, trxName);
      /** if (DS_ImportInvoices_Filing_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_DS_ImportInvoices_Filing (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_ImportInvoices_Filing[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
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

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getC_Invoice_ID()));
    }

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_InvoiceLine)MTable.get(getCtx(), org.compiere.model.I_C_InvoiceLine.Table_Name)
			.getPO(getC_InvoiceLine_ID(), get_TrxName());	}

	/** Set Invoice Line.
		@param C_InvoiceLine_ID 
		Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID)
	{
		if (C_InvoiceLine_ID < 1) 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoiceLine_ID, Integer.valueOf(C_InvoiceLine_ID));
	}

	/** Get Invoice Line.
		@return Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoiceLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Tax getC_Tax() throws RuntimeException
    {
		return (org.compiere.model.I_C_Tax)MTable.get(getCtx(), org.compiere.model.I_C_Tax.Table_Name)
			.getPO(getC_Tax_ID(), get_TrxName());	}

	/** Set Tax.
		@param C_Tax_ID 
		Tax identifier
	  */
	public void setC_Tax_ID (int C_Tax_ID)
	{
		if (C_Tax_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Tax_ID, Integer.valueOf(C_Tax_ID));
	}

	/** Get Tax.
		@return Tax identifier
	  */
	public int getC_Tax_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Tax_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Calculated Tax Amt.
		@param DS_CalculatedTaxAmt 
		This amount is calculated based on the percent and the base amount
	  */
	public void setDS_CalculatedTaxAmt (BigDecimal DS_CalculatedTaxAmt)
	{
		set_Value (COLUMNNAME_DS_CalculatedTaxAmt, DS_CalculatedTaxAmt);
	}

	/** Get Calculated Tax Amt.
		@return This amount is calculated based on the percent and the base amount
	  */
	public BigDecimal getDS_CalculatedTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_CalculatedTaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Import Invoices Tax Filing.
		@param DS_ImportInvoices_Filing_ID Import Invoices Tax Filing	  */
	public void setDS_ImportInvoices_Filing_ID (int DS_ImportInvoices_Filing_ID)
	{
		if (DS_ImportInvoices_Filing_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ImportInvoices_Filing_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ImportInvoices_Filing_ID, Integer.valueOf(DS_ImportInvoices_Filing_ID));
	}

	/** Get Import Invoices Tax Filing.
		@return Import Invoices Tax Filing	  */
	public int getDS_ImportInvoices_Filing_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ImportInvoices_Filing_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_ImportInvoices_Filing_UU.
		@param DS_ImportInvoices_Filing_UU DS_ImportInvoices_Filing_UU	  */
	public void setDS_ImportInvoices_Filing_UU (String DS_ImportInvoices_Filing_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DS_ImportInvoices_Filing_UU, DS_ImportInvoices_Filing_UU);
	}

	/** Get DS_ImportInvoices_Filing_UU.
		@return DS_ImportInvoices_Filing_UU	  */
	public String getDS_ImportInvoices_Filing_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_ImportInvoices_Filing_UU);
	}

	public I_DS_Invoice_TaxDetails getDS_Invoice_TaxDetails() throws RuntimeException
    {
		return (I_DS_Invoice_TaxDetails)MTable.get(getCtx(), I_DS_Invoice_TaxDetails.Table_Name)
			.getPO(getDS_Invoice_TaxDetails_ID(), get_TrxName());	}

	/** Set Invoice Tax Detail.
		@param DS_Invoice_TaxDetails_ID Invoice Tax Detail	  */
	public void setDS_Invoice_TaxDetails_ID (int DS_Invoice_TaxDetails_ID)
	{
		if (DS_Invoice_TaxDetails_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Invoice_TaxDetails_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Invoice_TaxDetails_ID, Integer.valueOf(DS_Invoice_TaxDetails_ID));
	}

	/** Get Invoice Tax Detail.
		@return Invoice Tax Detail	  */
	public int getDS_Invoice_TaxDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Invoice_TaxDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DS_TaxFiling getDS_TaxFiling() throws RuntimeException
    {
		return (I_DS_TaxFiling)MTable.get(getCtx(), I_DS_TaxFiling.Table_Name)
			.getPO(getDS_TaxFiling_ID(), get_TrxName());	}

	/** Set Tax Filing.
		@param DS_TaxFiling_ID Tax Filing	  */
	public void setDS_TaxFiling_ID (int DS_TaxFiling_ID)
	{
		if (DS_TaxFiling_ID < 1) 
			set_Value (COLUMNNAME_DS_TaxFiling_ID, null);
		else 
			set_Value (COLUMNNAME_DS_TaxFiling_ID, Integer.valueOf(DS_TaxFiling_ID));
	}

	/** Get Tax Filing.
		@return Tax Filing	  */
	public int getDS_TaxFiling_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TaxFiling_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line Amount.
		@param LineNetAmt 
		Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public void setLineNetAmt (BigDecimal LineNetAmt)
	{
		set_ValueNoCheck (COLUMNNAME_LineNetAmt, LineNetAmt);
	}

	/** Get Line Amount.
		@return Line Extended Amount (Quantity * Actual Price) without Freight and Charges
	  */
	public BigDecimal getLineNetAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineNetAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Line Net in Base Currency.
		@param linenetamtbase Line Net in Base Currency	  */
	public void setlinenetamtbase (BigDecimal linenetamtbase)
	{
		set_Value (COLUMNNAME_linenetamtbase, linenetamtbase);
	}

	/** Get Line Net in Base Currency.
		@return Line Net in Base Currency	  */
	public BigDecimal getlinenetamtbase () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_linenetamtbase);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Line Total.
		@param LineTotalAmt 
		Total line amount incl. Tax
	  */
	public void setLineTotalAmt (BigDecimal LineTotalAmt)
	{
		set_Value (COLUMNNAME_LineTotalAmt, LineTotalAmt);
	}

	/** Get Line Total.
		@return Total line amount incl. Tax
	  */
	public BigDecimal getLineTotalAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_LineTotalAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Line Total Amt in Base Currency.
		@param linetotalamtbase Line Total Amt in Base Currency	  */
	public void setlinetotalamtbase (BigDecimal linetotalamtbase)
	{
		set_Value (COLUMNNAME_linetotalamtbase, linetotalamtbase);
	}

	/** Get Line Total Amt in Base Currency.
		@return Line Total Amt in Base Currency	  */
	public BigDecimal getlinetotalamtbase () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_linetotalamtbase);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rate.
		@param Rate 
		Rate or Tax or Exchange
	  */
	public void setRate (BigDecimal Rate)
	{
		set_Value (COLUMNNAME_Rate, Rate);
	}

	/** Get Rate.
		@return Rate or Tax or Exchange
	  */
	public BigDecimal getRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Rate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax Amount.
		@param TaxAmt 
		Tax Amount for a document
	  */
	public void setTaxAmt (BigDecimal TaxAmt)
	{
		set_ValueNoCheck (COLUMNNAME_TaxAmt, TaxAmt);
	}

	/** Get Tax Amount.
		@return Tax Amount for a document
	  */
	public BigDecimal getTaxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Tax base Amount.
		@param TaxBaseAmt 
		Base for calculating the tax amount
	  */
	public void setTaxBaseAmt (BigDecimal TaxBaseAmt)
	{
		set_Value (COLUMNNAME_TaxBaseAmt, TaxBaseAmt);
	}

	/** Get Tax base Amount.
		@return Base for calculating the tax amount
	  */
	public BigDecimal getTaxBaseAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TaxBaseAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}