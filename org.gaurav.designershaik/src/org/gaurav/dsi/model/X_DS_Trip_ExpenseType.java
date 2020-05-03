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

/** Generated Model for DS_Trip_ExpenseType
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Trip_ExpenseType extends PO implements I_DS_Trip_ExpenseType, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180405L;

    /** Standard Constructor */
    public X_DS_Trip_ExpenseType (Properties ctx, int DS_Trip_ExpenseType_ID, String trxName)
    {
      super (ctx, DS_Trip_ExpenseType_ID, trxName);
      /** if (DS_Trip_ExpenseType_ID == 0)
        {
			setDS_Trip_ExpenseType_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Trip_ExpenseType (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Trip_ExpenseType[")
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

	/** Set Expense Type.
		@param DS_Trip_ExpenseType_ID Expense Type	  */
	public void setDS_Trip_ExpenseType_ID (int DS_Trip_ExpenseType_ID)
	{
		if (DS_Trip_ExpenseType_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Trip_ExpenseType_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Trip_ExpenseType_ID, Integer.valueOf(DS_Trip_ExpenseType_ID));
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

	/** Set DS_Trip_ExpenseType_UU.
		@param DS_Trip_ExpenseType_UU DS_Trip_ExpenseType_UU	  */
	public void setDS_Trip_ExpenseType_UU (String DS_Trip_ExpenseType_UU)
	{
		set_Value (COLUMNNAME_DS_Trip_ExpenseType_UU, DS_Trip_ExpenseType_UU);
	}

	/** Get DS_Trip_ExpenseType_UU.
		@return DS_Trip_ExpenseType_UU	  */
	public String getDS_Trip_ExpenseType_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Trip_ExpenseType_UU);
	}

	/** Set Mandatory.
		@param IsMandatory 
		Data entry is required in this column
	  */
	public void setIsMandatory (boolean IsMandatory)
	{
		set_Value (COLUMNNAME_IsMandatory, Boolean.valueOf(IsMandatory));
	}

	/** Get Mandatory.
		@return Data entry is required in this column
	  */
	public boolean isMandatory () 
	{
		Object oo = get_Value(COLUMNNAME_IsMandatory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_M_FreightCategory getM_FreightCategory() throws RuntimeException
    {
		return (org.compiere.model.I_M_FreightCategory)MTable.get(getCtx(), org.compiere.model.I_M_FreightCategory.Table_Name)
			.getPO(getM_FreightCategory_ID(), get_TrxName());	}

	/** Set Freight Category.
		@param M_FreightCategory_ID 
		Category of the Freight
	  */
	public void setM_FreightCategory_ID (int M_FreightCategory_ID)
	{
		if (M_FreightCategory_ID < 1) 
			set_Value (COLUMNNAME_M_FreightCategory_ID, null);
		else 
			set_Value (COLUMNNAME_M_FreightCategory_ID, Integer.valueOf(M_FreightCategory_ID));
	}

	/** Get Freight Category.
		@return Category of the Freight
	  */
	public int getM_FreightCategory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_FreightCategory_ID);
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
}