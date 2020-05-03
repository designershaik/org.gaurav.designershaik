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

/** Generated Model for DS_Assignment_Details
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Assignment_Details extends PO implements I_DS_Assignment_Details, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171121L;

    /** Standard Constructor */
    public X_DS_Assignment_Details (Properties ctx, int DS_Assignment_Details_ID, String trxName)
    {
      super (ctx, DS_Assignment_Details_ID, trxName);
      /** if (DS_Assignment_Details_ID == 0)
        {
			setDS_Assignment_Details_ID (0);
			setDS_Assignment_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Assignment_Details (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Assignment_Details[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assignment Of Employees.
		@param DS_Assignment_Details_ID Assignment Of Employees	  */
	public void setDS_Assignment_Details_ID (int DS_Assignment_Details_ID)
	{
		if (DS_Assignment_Details_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Assignment_Details_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Assignment_Details_ID, Integer.valueOf(DS_Assignment_Details_ID));
	}

	/** Get Assignment Of Employees.
		@return Assignment Of Employees	  */
	public int getDS_Assignment_Details_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Assignment_Details_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Assignment_Details_UU.
		@param DS_Assignment_Details_UU DS_Assignment_Details_UU	  */
	public void setDS_Assignment_Details_UU (String DS_Assignment_Details_UU)
	{
		set_Value (COLUMNNAME_DS_Assignment_Details_UU, DS_Assignment_Details_UU);
	}

	/** Get DS_Assignment_Details_UU.
		@return DS_Assignment_Details_UU	  */
	public String getDS_Assignment_Details_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Assignment_Details_UU);
	}

	public I_DS_Assignment getDS_Assignment() throws RuntimeException
    {
		return (I_DS_Assignment)MTable.get(getCtx(), I_DS_Assignment.Table_Name)
			.getPO(getDS_Assignment_ID(), get_TrxName());	}

	/** Set Assignment Of Employees.
		@param DS_Assignment_ID Assignment Of Employees	  */
	public void setDS_Assignment_ID (int DS_Assignment_ID)
	{
		if (DS_Assignment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Assignment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Assignment_ID, Integer.valueOf(DS_Assignment_ID));
	}

	/** Get Assignment Of Employees.
		@return Assignment Of Employees	  */
	public int getDS_Assignment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Assignment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set End Time.
		@param EndTime 
		End of the time span
	  */
	public void setEndTime (Timestamp EndTime)
	{
		set_Value (COLUMNNAME_EndTime, EndTime);
	}

	/** Get End Time.
		@return End of the time span
	  */
	public Timestamp getEndTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndTime);
	}

	/** Set Start Time.
		@param StartTime 
		Time started
	  */
	public void setStartTime (Timestamp StartTime)
	{
		set_Value (COLUMNNAME_StartTime, StartTime);
	}

	/** Get Start Time.
		@return Time started
	  */
	public Timestamp getStartTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartTime);
	}
}