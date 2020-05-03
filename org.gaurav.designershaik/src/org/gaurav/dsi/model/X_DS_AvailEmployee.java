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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DS_AvailEmployee
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_AvailEmployee extends PO implements I_DS_AvailEmployee, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_AvailEmployee (Properties ctx, int DS_AvailEmployee_ID, String trxName)
    {
      super (ctx, DS_AvailEmployee_ID, trxName);
      /** if (DS_AvailEmployee_ID == 0)
        {
			setDS_AvailEmployee_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_AvailEmployee (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_AvailEmployee[")
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

	public org.compiere.model.I_C_Period getC_Period() throws RuntimeException
    {
		return (org.compiere.model.I_C_Period)MTable.get(getCtx(), org.compiere.model.I_C_Period.Table_Name)
			.getPO(getC_Period_ID(), get_TrxName());	}

	/** Set Period.
		@param C_Period_ID 
		Period of the Calendar
	  */
	public void setC_Period_ID (int C_Period_ID)
	{
		if (C_Period_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Period_ID, Integer.valueOf(C_Period_ID));
	}

	/** Get Period.
		@return Period of the Calendar
	  */
	public int getC_Period_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Period_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Available employees for a day.
		@param DS_AvailEmployee_ID Available employees for a day	  */
	public void setDS_AvailEmployee_ID (int DS_AvailEmployee_ID)
	{
		if (DS_AvailEmployee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_AvailEmployee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_AvailEmployee_ID, Integer.valueOf(DS_AvailEmployee_ID));
	}

	/** Get Available employees for a day.
		@return Available employees for a day	  */
	public int getDS_AvailEmployee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_AvailEmployee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_AvailEmployee_UU.
		@param DS_AvailEmployee_UU DS_AvailEmployee_UU	  */
	public void setDS_AvailEmployee_UU (String DS_AvailEmployee_UU)
	{
		set_Value (COLUMNNAME_DS_AvailEmployee_UU, DS_AvailEmployee_UU);
	}

	/** Get DS_AvailEmployee_UU.
		@return DS_AvailEmployee_UU	  */
	public String getDS_AvailEmployee_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_AvailEmployee_UU);
	}

	public I_DS_Days getDS_Days() throws RuntimeException
    {
		return (I_DS_Days)MTable.get(getCtx(), I_DS_Days.Table_Name)
			.getPO(getDS_Days_ID(), get_TrxName());	}

	/** Set Days.
		@param DS_Days_ID Days	  */
	public void setDS_Days_ID (int DS_Days_ID)
	{
		if (DS_Days_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Days_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Days_ID, Integer.valueOf(DS_Days_ID));
	}

	/** Get Days.
		@return Days	  */
	public int getDS_Days_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Days_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Assigned.
		@param DS_IsAssigned Is Assigned	  */
	public void setDS_IsAssigned (boolean DS_IsAssigned)
	{
		set_Value (COLUMNNAME_DS_IsAssigned, Boolean.valueOf(DS_IsAssigned));
	}

	/** Get Is Assigned.
		@return Is Assigned	  */
	public boolean isDS_IsAssigned () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsAssigned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}