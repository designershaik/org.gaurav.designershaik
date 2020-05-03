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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_HR_Compensation_Master
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_Compensation_Master extends PO implements I_GS_HR_Compensation_Master, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_Compensation_Master (Properties ctx, int GS_HR_Compensation_Master_ID, String trxName)
    {
      super (ctx, GS_HR_Compensation_Master_ID, trxName);
      /** if (GS_HR_Compensation_Master_ID == 0)
        {
			setGS_HR_Compensation_Master_ID (0);
			setIsSummary (false);
			setName (null);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Compensation_Master (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Compensation_Master[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Advance/Loan.
		@param GS_HR_Advance Advance/Loan	  */
	public void setGS_HR_Advance (boolean GS_HR_Advance)
	{
		set_Value (COLUMNNAME_GS_HR_Advance, Boolean.valueOf(GS_HR_Advance));
	}

	/** Get Advance/Loan.
		@return Advance/Loan	  */
	public boolean isGS_HR_Advance () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_Advance);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Compensation Type.
		@param GS_HR_Compensation_Master_ID Compensation Type	  */
	public void setGS_HR_Compensation_Master_ID (int GS_HR_Compensation_Master_ID)
	{
		if (GS_HR_Compensation_Master_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Compensation_Master_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Compensation_Master_ID, Integer.valueOf(GS_HR_Compensation_Master_ID));
	}

	/** Get Compensation Type.
		@return Compensation Type	  */
	public int getGS_HR_Compensation_Master_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Compensation_Master_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Compensation_Master_UU.
		@param GS_HR_Compensation_Master_UU GS_HR_Compensation_Master_UU	  */
	public void setGS_HR_Compensation_Master_UU (String GS_HR_Compensation_Master_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Compensation_Master_UU, GS_HR_Compensation_Master_UU);
	}

	/** Get GS_HR_Compensation_Master_UU.
		@return GS_HR_Compensation_Master_UU	  */
	public String getGS_HR_Compensation_Master_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Compensation_Master_UU);
	}

	/** Set Basic Salary.
		@param GS_HR_IsBasic 
		Basic salary
	  */
	public void setGS_HR_IsBasic (boolean GS_HR_IsBasic)
	{
		set_Value (COLUMNNAME_GS_HR_IsBasic, Boolean.valueOf(GS_HR_IsBasic));
	}

	/** Get Basic Salary.
		@return Basic salary
	  */
	public boolean isGS_HR_IsBasic () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_IsBasic);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Earning.
		@param GS_HR_IsEarning 
		Earning
	  */
	public void setGS_HR_IsEarning (boolean GS_HR_IsEarning)
	{
		set_Value (COLUMNNAME_GS_HR_IsEarning, Boolean.valueOf(GS_HR_IsEarning));
	}

	/** Get Earning.
		@return Earning
	  */
	public boolean isGS_HR_IsEarning () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_IsEarning);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Percent.
		@param GS_HR_IsPercent 
		Percent
	  */
	public void setGS_HR_IsPercent (boolean GS_HR_IsPercent)
	{
		set_Value (COLUMNNAME_GS_HR_IsPercent, Boolean.valueOf(GS_HR_IsPercent));
	}

	/** Get Percent.
		@return Percent
	  */
	public boolean isGS_HR_IsPercent () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_IsPercent);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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