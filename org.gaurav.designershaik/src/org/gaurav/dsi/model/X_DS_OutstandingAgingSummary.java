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

/** Generated Model for DS_OutstandingAgingSummary
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_OutstandingAgingSummary extends PO implements I_DS_OutstandingAgingSummary, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_OutstandingAgingSummary (Properties ctx, int DS_OutstandingAgingSummary_ID, String trxName)
    {
      super (ctx, DS_OutstandingAgingSummary_ID, trxName);
      /** if (DS_OutstandingAgingSummary_ID == 0)
        {
			setDS_OutstandingAgingSummary_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_OutstandingAgingSummary (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_OutstandingAgingSummary[")
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

	public org.compiere.model.I_C_DunningRun getC_DunningRun() throws RuntimeException
    {
		return (org.compiere.model.I_C_DunningRun)MTable.get(getCtx(), org.compiere.model.I_C_DunningRun.Table_Name)
			.getPO(getC_DunningRun_ID(), get_TrxName());	}

	/** Set Dunning Run.
		@param C_DunningRun_ID 
		Dunning Run
	  */
	public void setC_DunningRun_ID (int C_DunningRun_ID)
	{
		if (C_DunningRun_ID < 1) 
			set_Value (COLUMNNAME_C_DunningRun_ID, null);
		else 
			set_Value (COLUMNNAME_C_DunningRun_ID, Integer.valueOf(C_DunningRun_ID));
	}

	/** Get Dunning Run.
		@return Dunning Run
	  */
	public int getC_DunningRun_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_DunningRun_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Current.
		@param DS_Current 
		Current for aging
	  */
	public void setDS_Current (BigDecimal DS_Current)
	{
		set_Value (COLUMNNAME_DS_Current, DS_Current);
	}

	/** Get Current.
		@return Current for aging
	  */
	public BigDecimal getDS_Current () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_Current);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Outstanding Balance Aging Summary.
		@param DS_OutstandingAgingSummary_ID Outstanding Balance Aging Summary	  */
	public void setDS_OutstandingAgingSummary_ID (int DS_OutstandingAgingSummary_ID)
	{
		if (DS_OutstandingAgingSummary_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_OutstandingAgingSummary_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_OutstandingAgingSummary_ID, Integer.valueOf(DS_OutstandingAgingSummary_ID));
	}

	/** Get Outstanding Balance Aging Summary.
		@return Outstanding Balance Aging Summary	  */
	public int getDS_OutstandingAgingSummary_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_OutstandingAgingSummary_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_OutstandingAgingSummary_UU.
		@param DS_OutstandingAgingSummary_UU DS_OutstandingAgingSummary_UU	  */
	public void setDS_OutstandingAgingSummary_UU (String DS_OutstandingAgingSummary_UU)
	{
		set_Value (COLUMNNAME_DS_OutstandingAgingSummary_UU, DS_OutstandingAgingSummary_UU);
	}

	/** Get DS_OutstandingAgingSummary_UU.
		@return DS_OutstandingAgingSummary_UU	  */
	public String getDS_OutstandingAgingSummary_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_OutstandingAgingSummary_UU);
	}

	/** Set Due Today-30.
		@param Due0_30 Due Today-30	  */
	public void setDue0_30 (BigDecimal Due0_30)
	{
		set_Value (COLUMNNAME_Due0_30, Due0_30);
	}

	/** Get Due Today-30.
		@return Due Today-30	  */
	public BigDecimal getDue0_30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due0_30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 31-60.
		@param Due31_60 Due 31-60	  */
	public void setDue31_60 (BigDecimal Due31_60)
	{
		set_Value (COLUMNNAME_Due31_60, Due31_60);
	}

	/** Get Due 31-60.
		@return Due 31-60	  */
	public BigDecimal getDue31_60 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due31_60);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 61-90.
		@param Due61_90 Due 61-90	  */
	public void setDue61_90 (BigDecimal Due61_90)
	{
		set_Value (COLUMNNAME_Due61_90, Due61_90);
	}

	/** Get Due 61-90.
		@return Due 61-90	  */
	public BigDecimal getDue61_90 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due61_90);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due > 91.
		@param Due91_Plus Due > 91	  */
	public void setDue91_Plus (BigDecimal Due91_Plus)
	{
		set_Value (COLUMNNAME_Due91_Plus, Due91_Plus);
	}

	/** Get Due > 91.
		@return Due > 91	  */
	public BigDecimal getDue91_Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due91_Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}