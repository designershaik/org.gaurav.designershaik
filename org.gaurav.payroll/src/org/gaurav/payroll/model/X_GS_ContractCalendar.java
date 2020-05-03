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
package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_ContractCalendar
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_ContractCalendar extends PO implements I_GS_ContractCalendar, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191223L;

    /** Standard Constructor */
    public X_GS_ContractCalendar (Properties ctx, int GS_ContractCalendar_ID, String trxName)
    {
      super (ctx, GS_ContractCalendar_ID, trxName);
      /** if (GS_ContractCalendar_ID == 0)
        {
			setGS_ContractCalendar_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_GS_ContractCalendar (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_ContractCalendar[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Contract Calendar.
		@param GS_ContractCalendar_ID Contract Calendar	  */
	public void setGS_ContractCalendar_ID (int GS_ContractCalendar_ID)
	{
		if (GS_ContractCalendar_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_ContractCalendar_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_ContractCalendar_ID, Integer.valueOf(GS_ContractCalendar_ID));
	}

	/** Get Contract Calendar.
		@return Contract Calendar	  */
	public int getGS_ContractCalendar_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_ContractCalendar_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_ContractCalendar_UU.
		@param GS_ContractCalendar_UU GS_ContractCalendar_UU	  */
	public void setGS_ContractCalendar_UU (String GS_ContractCalendar_UU)
	{
		set_Value (COLUMNNAME_GS_ContractCalendar_UU, GS_ContractCalendar_UU);
	}

	/** Get GS_ContractCalendar_UU.
		@return GS_ContractCalendar_UU	  */
	public String getGS_ContractCalendar_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_ContractCalendar_UU);
	}

	public I_HR_Contract getHR_Contract() throws RuntimeException
    {
		return (I_HR_Contract)MTable.get(getCtx(), I_HR_Contract.Table_Name)
			.getPO(getHR_Contract_ID(), get_TrxName());	}

	/** Set Payroll Contract.
		@param HR_Contract_ID Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID)
	{
		if (HR_Contract_ID < 1) 
			set_Value (COLUMNNAME_HR_Contract_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Contract_ID, Integer.valueOf(HR_Contract_ID));
	}

	/** Get Payroll Contract.
		@return Payroll Contract	  */
	public int getHR_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}
}