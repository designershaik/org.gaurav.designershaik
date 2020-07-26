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

/** Generated Model for DS_CouponSchedule
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_DS_CouponSchedule extends PO implements I_DS_CouponSchedule, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200722L;

    /** Standard Constructor */
    public X_DS_CouponSchedule (Properties ctx, int DS_CouponSchedule_ID, String trxName)
    {
      super (ctx, DS_CouponSchedule_ID, trxName);
      /** if (DS_CouponSchedule_ID == 0)
        {
			setC_Invoice_ID (0);
			setDS_CouponSchedule_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_CouponSchedule (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_CouponSchedule[")
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

	/** Set Amortization Divided Over schedule.
		@param DS_AmortizationAmt_OverSchedul Amortization Divided Over schedule	  */
	public void setDS_AmortizationAmt_OverSchedul (BigDecimal DS_AmortizationAmt_OverSchedul)
	{
		set_Value (COLUMNNAME_DS_AmortizationAmt_OverSchedul, DS_AmortizationAmt_OverSchedul);
	}

	/** Get Amortization Divided Over schedule.
		@return Amortization Divided Over schedule	  */
	public BigDecimal getDS_AmortizationAmt_OverSchedul () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_AmortizationAmt_OverSchedul);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coupon Rate.
		@param DS_CouponRate 
		Coupon Rate
	  */
	public void setDS_CouponRate (BigDecimal DS_CouponRate)
	{
		set_Value (COLUMNNAME_DS_CouponRate, DS_CouponRate);
	}

	/** Get Coupon Rate.
		@return Coupon Rate
	  */
	public BigDecimal getDS_CouponRate () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_CouponRate);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coupon Schedule.
		@param DS_CouponSchedule_ID Coupon Schedule	  */
	public void setDS_CouponSchedule_ID (int DS_CouponSchedule_ID)
	{
		if (DS_CouponSchedule_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_CouponSchedule_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_CouponSchedule_ID, Integer.valueOf(DS_CouponSchedule_ID));
	}

	/** Get Coupon Schedule.
		@return Coupon Schedule	  */
	public int getDS_CouponSchedule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_CouponSchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_CouponSchedule_UU.
		@param DS_CouponSchedule_UU DS_CouponSchedule_UU	  */
	public void setDS_CouponSchedule_UU (String DS_CouponSchedule_UU)
	{
		set_Value (COLUMNNAME_DS_CouponSchedule_UU, DS_CouponSchedule_UU);
	}

	/** Get DS_CouponSchedule_UU.
		@return DS_CouponSchedule_UU	  */
	public String getDS_CouponSchedule_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_CouponSchedule_UU);
	}

	public org.compiere.model.I_GL_Journal getGL_Journal() throws RuntimeException
    {
		return (org.compiere.model.I_GL_Journal)MTable.get(getCtx(), org.compiere.model.I_GL_Journal.Table_Name)
			.getPO(getGL_Journal_ID(), get_TrxName());	}

	/** Set Journal.
		@param GL_Journal_ID 
		General Ledger Journal
	  */
	public void setGL_Journal_ID (int GL_Journal_ID)
	{
		if (GL_Journal_ID < 1) 
			set_Value (COLUMNNAME_GL_Journal_ID, null);
		else 
			set_Value (COLUMNNAME_GL_Journal_ID, Integer.valueOf(GL_Journal_ID));
	}

	/** Get Journal.
		@return General Ledger Journal
	  */
	public int getGL_Journal_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GL_Journal_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Coupon Amount.
		@param GS_CouponAmount Coupon Amount	  */
	public void setGS_CouponAmount (BigDecimal GS_CouponAmount)
	{
		set_Value (COLUMNNAME_GS_CouponAmount, GS_CouponAmount);
	}

	/** Get Coupon Amount.
		@return Coupon Amount	  */
	public BigDecimal getGS_CouponAmount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_CouponAmount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Coupon Date.
		@param GS_CouponDate Coupon Date	  */
	public void setGS_CouponDate (Timestamp GS_CouponDate)
	{
		set_Value (COLUMNNAME_GS_CouponDate, GS_CouponDate);
	}

	/** Get Coupon Date.
		@return Coupon Date	  */
	public Timestamp getGS_CouponDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GS_CouponDate);
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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getRef_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getRef_Invoice_ID(), get_TrxName());	}

	/** Set Referenced Invoice.
		@param Ref_Invoice_ID Referenced Invoice	  */
	public void setRef_Invoice_ID (int Ref_Invoice_ID)
	{
		if (Ref_Invoice_ID < 1) 
			set_Value (COLUMNNAME_Ref_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Invoice_ID, Integer.valueOf(Ref_Invoice_ID));
	}

	/** Get Referenced Invoice.
		@return Referenced Invoice	  */
	public int getRef_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}