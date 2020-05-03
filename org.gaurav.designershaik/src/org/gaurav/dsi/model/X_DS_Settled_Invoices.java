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

/** Generated Model for DS_Settled_Invoices
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_Settled_Invoices extends PO implements I_DS_Settled_Invoices, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181203L;

    /** Standard Constructor */
    public X_DS_Settled_Invoices (Properties ctx, int DS_Settled_Invoices_ID, String trxName)
    {
      super (ctx, DS_Settled_Invoices_ID, trxName);
      /** if (DS_Settled_Invoices_ID == 0)
        {
			setC_Payment_ID (0);
// @C_Payment_ID@
			setDS_Settled_Invoices_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Settled_Invoices (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Settled_Invoices[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
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

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Settled Invoices.
		@param DS_Settled_Invoices_ID Settled Invoices	  */
	public void setDS_Settled_Invoices_ID (int DS_Settled_Invoices_ID)
	{
		if (DS_Settled_Invoices_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Settled_Invoices_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Settled_Invoices_ID, Integer.valueOf(DS_Settled_Invoices_ID));
	}

	/** Get Settled Invoices.
		@return Settled Invoices	  */
	public int getDS_Settled_Invoices_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Settled_Invoices_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Settled_Invoices_UU.
		@param DS_Settled_Invoices_UU DS_Settled_Invoices_UU	  */
	public void setDS_Settled_Invoices_UU (String DS_Settled_Invoices_UU)
	{
		set_Value (COLUMNNAME_DS_Settled_Invoices_UU, DS_Settled_Invoices_UU);
	}

	/** Get DS_Settled_Invoices_UU.
		@return DS_Settled_Invoices_UU	  */
	public String getDS_Settled_Invoices_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Settled_Invoices_UU);
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

	public org.compiere.model.I_C_Payment getRef_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getRef_Payment_ID(), get_TrxName());	}

	/** Set Referenced Payment.
		@param Ref_Payment_ID Referenced Payment	  */
	public void setRef_Payment_ID (int Ref_Payment_ID)
	{
		if (Ref_Payment_ID < 1) 
			set_Value (COLUMNNAME_Ref_Payment_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_Payment_ID, Integer.valueOf(Ref_Payment_ID));
	}

	/** Get Referenced Payment.
		@return Referenced Payment	  */
	public int getRef_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Transaction Amount.
		@param TrxAmt 
		Amount of a transaction
	  */
	public void setTrxAmt (BigDecimal TrxAmt)
	{
		set_Value (COLUMNNAME_TrxAmt, TrxAmt);
	}

	/** Get Transaction Amount.
		@return Amount of a transaction
	  */
	public BigDecimal getTrxAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TrxAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}