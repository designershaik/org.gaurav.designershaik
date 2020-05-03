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

/** Generated Model for DS_TripSchd_CashReqstAmt
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_TripSchd_CashReqstAmt extends PO implements I_DS_TripSchd_CashReqstAmt, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180405L;

    /** Standard Constructor */
    public X_DS_TripSchd_CashReqstAmt (Properties ctx, int DS_TripSchd_CashReqstAmt_ID, String trxName)
    {
      super (ctx, DS_TripSchd_CashReqstAmt_ID, trxName);
      /** if (DS_TripSchd_CashReqstAmt_ID == 0)
        {
			setDS_TripSchd_CashReqstAmt_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_TripSchd_CashReqstAmt (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_TripSchd_CashReqstAmt[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Total Cash Request Amt.
		@param DS_CashRequest_Amt 
		Total Cash Request Amt
	  */
	public void setDS_CashRequest_Amt (BigDecimal DS_CashRequest_Amt)
	{
		set_Value (COLUMNNAME_DS_CashRequest_Amt, DS_CashRequest_Amt);
	}

	/** Get Total Cash Request Amt.
		@return Total Cash Request Amt
	  */
	public BigDecimal getDS_CashRequest_Amt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_CashRequest_Amt);
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

	/** Set Cash Request Amt.
		@param DS_TripSchd_CashReqstAmt_ID Cash Request Amt	  */
	public void setDS_TripSchd_CashReqstAmt_ID (int DS_TripSchd_CashReqstAmt_ID)
	{
		if (DS_TripSchd_CashReqstAmt_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_TripSchd_CashReqstAmt_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_TripSchd_CashReqstAmt_ID, Integer.valueOf(DS_TripSchd_CashReqstAmt_ID));
	}

	/** Get Cash Request Amt.
		@return Cash Request Amt	  */
	public int getDS_TripSchd_CashReqstAmt_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_TripSchd_CashReqstAmt_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_TripSchd_CashReqstAmt_UU.
		@param DS_TripSchd_CashReqstAmt_UU DS_TripSchd_CashReqstAmt_UU	  */
	public void setDS_TripSchd_CashReqstAmt_UU (String DS_TripSchd_CashReqstAmt_UU)
	{
		set_Value (COLUMNNAME_DS_TripSchd_CashReqstAmt_UU, DS_TripSchd_CashReqstAmt_UU);
	}

	/** Get DS_TripSchd_CashReqstAmt_UU.
		@return DS_TripSchd_CashReqstAmt_UU	  */
	public String getDS_TripSchd_CashReqstAmt_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_TripSchd_CashReqstAmt_UU);
	}

	public org.compiere.model.I_R_Request getR_Request() throws RuntimeException
    {
		return (org.compiere.model.I_R_Request)MTable.get(getCtx(), org.compiere.model.I_R_Request.Table_Name)
			.getPO(getR_Request_ID(), get_TrxName());	}

	/** Set Request.
		@param R_Request_ID 
		Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID)
	{
		if (R_Request_ID < 1) 
			set_Value (COLUMNNAME_R_Request_ID, null);
		else 
			set_Value (COLUMNNAME_R_Request_ID, Integer.valueOf(R_Request_ID));
	}

	/** Get Request.
		@return Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Request_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}