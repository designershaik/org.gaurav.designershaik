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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for DS_Days
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Days extends PO implements I_DS_Days, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_Days (Properties ctx, int DS_Days_ID, String trxName)
    {
      super (ctx, DS_Days_ID, trxName);
      /** if (DS_Days_ID == 0)
        {
			setC_Period_ID (0);
			setDS_Days_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Days (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Days[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
	}

	/** Set Day.
		@param DS_Day Day	  */
	public void setDS_Day (String DS_Day)
	{
		set_Value (COLUMNNAME_DS_Day, DS_Day);
	}

	/** Get Day.
		@return Day	  */
	public String getDS_Day () 
	{
		return (String)get_Value(COLUMNNAME_DS_Day);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getDS_Day());
    }

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

	/** Set DS_Days_UU.
		@param DS_Days_UU DS_Days_UU	  */
	public void setDS_Days_UU (String DS_Days_UU)
	{
		set_Value (COLUMNNAME_DS_Days_UU, DS_Days_UU);
	}

	/** Get DS_Days_UU.
		@return DS_Days_UU	  */
	public String getDS_Days_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Days_UU);
	}

	/** Set Generate To.
		@param GenerateTo 
		Generate To
	  */
	public void setGenerateTo (String GenerateTo)
	{
		set_Value (COLUMNNAME_GenerateTo, GenerateTo);
	}

	/** Get Generate To.
		@return Generate To
	  */
	public String getGenerateTo () 
	{
		return (String)get_Value(COLUMNNAME_GenerateTo);
	}
}