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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_HR_TimeSlot_Group
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_TimeSlot_Group extends PO implements I_GS_HR_TimeSlot_Group, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_TimeSlot_Group (Properties ctx, int GS_HR_TimeSlot_Group_ID, String trxName)
    {
      super (ctx, GS_HR_TimeSlot_Group_ID, trxName);
      /** if (GS_HR_TimeSlot_Group_ID == 0)
        {
			setGS_HR_TimeSlot_Group_ID (0);
			setIsSummary (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_TimeSlot_Group (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_TimeSlot_Group[")
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

	/** Set Average Working Hours Per Day.
		@param GS_HR_AverageWorkingHours Average Working Hours Per Day	  */
	public void setGS_HR_AverageWorkingHours (BigDecimal GS_HR_AverageWorkingHours)
	{
		set_Value (COLUMNNAME_GS_HR_AverageWorkingHours, GS_HR_AverageWorkingHours);
	}

	/** Get Average Working Hours Per Day.
		@return Average Working Hours Per Day	  */
	public BigDecimal getGS_HR_AverageWorkingHours () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_AverageWorkingHours);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Time Slot.
		@param GS_HR_TimeSlot_Group_ID Time Slot	  */
	public void setGS_HR_TimeSlot_Group_ID (int GS_HR_TimeSlot_Group_ID)
	{
		if (GS_HR_TimeSlot_Group_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_TimeSlot_Group_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_TimeSlot_Group_ID, Integer.valueOf(GS_HR_TimeSlot_Group_ID));
	}

	/** Get Time Slot.
		@return Time Slot	  */
	public int getGS_HR_TimeSlot_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_TimeSlot_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_TimeSlot_Group_UU.
		@param GS_HR_TimeSlot_Group_UU GS_HR_TimeSlot_Group_UU	  */
	public void setGS_HR_TimeSlot_Group_UU (String GS_HR_TimeSlot_Group_UU)
	{
		set_Value (COLUMNNAME_GS_HR_TimeSlot_Group_UU, GS_HR_TimeSlot_Group_UU);
	}

	/** Get GS_HR_TimeSlot_Group_UU.
		@return GS_HR_TimeSlot_Group_UU	  */
	public String getGS_HR_TimeSlot_Group_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_TimeSlot_Group_UU);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Summary Level.
		@param IsSummary 
		This is a summary entity
	  */
	public void setIsSummary (boolean IsSummary)
	{
		set_Value (COLUMNNAME_IsSummary, Boolean.valueOf(IsSummary));
	}

	/** Get Summary Level.
		@return This is a summary entity
	  */
	public boolean isSummary () 
	{
		Object oo = get_Value(COLUMNNAME_IsSummary);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}