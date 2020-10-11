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

/** Generated Model for DS_CashPosition_Setup
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_DS_CashPosition_Setup extends PO implements I_DS_CashPosition_Setup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200919L;

    /** Standard Constructor */
    public X_DS_CashPosition_Setup (Properties ctx, int DS_CashPosition_Setup_ID, String trxName)
    {
      super (ctx, DS_CashPosition_Setup_ID, trxName);
      /** if (DS_CashPosition_Setup_ID == 0)
        {
			setDS_CashPosition_Setup_ID (0);
			setDS_CashPosition_Setup_UU (null);
        } */
    }

    /** Load Constructor */
    public X_DS_CashPosition_Setup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_CashPosition_Setup[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** BPGroupName AD_Reference_ID=200045 */
	public static final int BPGROUPNAME_AD_Reference_ID=200045;
	/** Set BP Group Name.
		@param BPGroupName BP Group Name	  */
	public void setBPGroupName (String BPGroupName)
	{

		set_Value (COLUMNNAME_BPGroupName, BPGroupName);
	}

	/** Get BP Group Name.
		@return BP Group Name	  */
	public String getBPGroupName () 
	{
		return (String)get_Value(COLUMNNAME_BPGroupName);
	}

	/** Set Category.
		@param Category Category	  */
	public void setCategory (String Category)
	{
		set_Value (COLUMNNAME_Category, Category);
	}

	/** Get Category.
		@return Category	  */
	public String getCategory () 
	{
		return (String)get_Value(COLUMNNAME_Category);
	}

	/** Set Cash Position Setup.
		@param DS_CashPosition_Setup_ID Cash Position Setup	  */
	public void setDS_CashPosition_Setup_ID (int DS_CashPosition_Setup_ID)
	{
		if (DS_CashPosition_Setup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_CashPosition_Setup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_CashPosition_Setup_ID, Integer.valueOf(DS_CashPosition_Setup_ID));
	}

	/** Get Cash Position Setup.
		@return Cash Position Setup	  */
	public int getDS_CashPosition_Setup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_CashPosition_Setup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_CashPosition_Setup_UU.
		@param DS_CashPosition_Setup_UU DS_CashPosition_Setup_UU	  */
	public void setDS_CashPosition_Setup_UU (String DS_CashPosition_Setup_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DS_CashPosition_Setup_UU, DS_CashPosition_Setup_UU);
	}

	/** Get DS_CashPosition_Setup_UU.
		@return DS_CashPosition_Setup_UU	  */
	public String getDS_CashPosition_Setup_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_CashPosition_Setup_UU);
	}

	/** ElementValue AD_Reference_ID=182 */
	public static final int ELEMENTVALUE_AD_Reference_ID=182;
	/** Set Element Key.
		@param ElementValue 
		Key of the element
	  */
	public void setElementValue (String ElementValue)
	{

		set_Value (COLUMNNAME_ElementValue, ElementValue);
	}

	/** Get Element Key.
		@return Key of the element
	  */
	public String getElementValue () 
	{
		return (String)get_Value(COLUMNNAME_ElementValue);
	}

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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