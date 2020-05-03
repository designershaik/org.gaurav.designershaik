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

/** Generated Model for DS_TripSchedule_Estimations
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_TripSchedule_Estimations extends PO implements I_DS_TripSchedule_Estimations, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180405L;

    /** Standard Constructor */
    public X_DS_TripSchedule_Estimations (Properties ctx, int DS_TripSchedule_Estimations_ID, String trxName)
    {
      super (ctx, DS_TripSchedule_Estimations_ID, trxName);
      /** if (DS_TripSchedule_Estimations_ID == 0)
        {
			setDS_TripSchedule_Estimations_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_TripSchedule_Estimations (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_TripSchedule_Estimations[")
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

	/** Set Estimated Amt.
		@param DS_Budget 
		Estimated Amt
	  */
	public void setDS_Budget (BigDecimal DS_Budget)
	{
		set_Value (COLUMNNAME_DS_Budget, DS_Budget);
	}

	/** Get Estimated Amt.
		@return Estimated Amt
	  */
	public BigDecimal getDS_Budget () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Budget);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_DS_Delivery_Trips getDS_Delivery_Trips() throws RuntimeException
    {
		return (I_DS_Delivery_Trips)MTable.get(getCtx(), I_DS_Delivery_Trips.Table_Name)
			.getPO(getDS_Delivery_Trips_ID(), get_TrxName());	}

	/** Set Delivery trips.
		@param DS_Delivery_Trips_ID Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID)
	{
		if (DS_Delivery_Trips_ID < 1) 
			set_Value (COLUMNNAME_DS_Delivery_Trips_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Delivery_Trips_ID, Integer.valueOf(DS_Delivery_Trips_ID));
	}

	/** Get Delivery trips.
		@return Delivery trips	  */
	public int getDS_Delivery_Trips_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Delivery_Trips_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DS_Trip_ExpenseType getDS_Trip_ExpenseType() throws RuntimeException
    {
		return (I_DS_Trip_ExpenseType)MTable.get(getCtx(), I_DS_Trip_ExpenseType.Table_Name)
			.getPO(getDS_Trip_ExpenseType_ID(), get_TrxName());	}

	/** Set Expense Type.
		@param DS_Trip_ExpenseType_ID Expense Type	  */
	public void setDS_Trip_ExpenseType_ID (int DS_Trip_ExpenseType_ID)
	{
		if (DS_Trip_ExpenseType_ID < 1) 
			set_Value (COLUMNNAME_DS_Trip_ExpenseType_ID, null);
		else 
			set_Value (COLUMNNAME_DS_Trip_ExpenseType_ID, Integer.valueOf(DS_Trip_ExpenseType_ID));
	}

	/** Get Expense Type.
		@return Expense Type	  */
	public int getDS_Trip_ExpenseType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Trip_ExpenseType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Trip Schedule Estimation.
		@param DS_TripSchedule_Estimations_ID Trip Schedule Estimation	  */
	public void setDS_TripSchedule_Estimations_ID (int DS_TripSchedule_Estimations_ID)
	{
		if (DS_TripSchedule_Estimations_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_TripSchedule_Estimations_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_TripSchedule_Estimations_ID, Integer.valueOf(DS_TripSchedule_Estimations_ID));
	}

	/** Get Trip Schedule Estimation.
		@return Trip Schedule Estimation	  */
	public int getDS_TripSchedule_Estimations_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TripSchedule_Estimations_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_TripSchedule_Estimations_UU.
		@param DS_TripSchedule_Estimations_UU DS_TripSchedule_Estimations_UU	  */
	public void setDS_TripSchedule_Estimations_UU (String DS_TripSchedule_Estimations_UU)
	{
		set_Value (COLUMNNAME_DS_TripSchedule_Estimations_UU, DS_TripSchedule_Estimations_UU);
	}

	/** Get DS_TripSchedule_Estimations_UU.
		@return DS_TripSchedule_Estimations_UU	  */
	public String getDS_TripSchedule_Estimations_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_TripSchedule_Estimations_UU);
	}

	/** Set Paid.
		@param IsPaid 
		The document is paid
	  */
	public void setIsPaid (boolean IsPaid)
	{
		set_ValueNoCheck (COLUMNNAME_IsPaid, Boolean.valueOf(IsPaid));
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

	/** Cash = CS */
	public static final String PAYMENTRULE_Cash = "CS";
	/** Credit Card = CC */
	public static final String PAYMENTRULE_CreditCard = "CC";
	/** Company Paid = CO */
	public static final String PAYMENTRULE_CompanyPaid = "CO";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{

		set_Value (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
	}
}