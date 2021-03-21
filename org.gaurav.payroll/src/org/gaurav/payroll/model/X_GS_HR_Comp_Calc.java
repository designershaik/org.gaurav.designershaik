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

/** Generated Model for GS_HR_Comp_Calc
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Comp_Calc extends PO implements I_GS_HR_Comp_Calc, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_Comp_Calc (Properties ctx, int GS_HR_Comp_Calc_ID, String trxName)
    {
      super (ctx, GS_HR_Comp_Calc_ID, trxName);
      /** if (GS_HR_Comp_Calc_ID == 0)
        {
			setGS_HR_Comp_Calc_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Comp_Calc (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Comp_Calc[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Compensation Formula.
		@param GS_HR_Comp_Calc_ID Compensation Formula	  */
	public void setGS_HR_Comp_Calc_ID (int GS_HR_Comp_Calc_ID)
	{
		if (GS_HR_Comp_Calc_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Comp_Calc_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Comp_Calc_ID, Integer.valueOf(GS_HR_Comp_Calc_ID));
	}

	/** Get Compensation Formula.
		@return Compensation Formula	  */
	public int getGS_HR_Comp_Calc_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Comp_Calc_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Comp_Calc_UU.
		@param GS_HR_Comp_Calc_UU GS_HR_Comp_Calc_UU	  */
	public void setGS_HR_Comp_Calc_UU (String GS_HR_Comp_Calc_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Comp_Calc_UU, GS_HR_Comp_Calc_UU);
	}

	/** Get GS_HR_Comp_Calc_UU.
		@return GS_HR_Comp_Calc_UU	  */
	public String getGS_HR_Comp_Calc_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Comp_Calc_UU);
	}

	/** Set Compensation.
		@param GS_HR_Compensation_Master_ID Compensation	  */
	public void setGS_HR_Compensation_Master_ID (int GS_HR_Compensation_Master_ID)
	{
		if (GS_HR_Compensation_Master_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Compensation_Master_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Compensation_Master_ID, Integer.valueOf(GS_HR_Compensation_Master_ID));
	}

	/** Get Compensation.
		@return Compensation	  */
	public int getGS_HR_Compensation_Master_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Compensation_Master_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Ref. Compensation Master.
		@param GS_HR_Ref_Compensation_ID Ref. Compensation Master	  */
	public void setGS_HR_Ref_Compensation_ID (int GS_HR_Ref_Compensation_ID)
	{
		if (GS_HR_Ref_Compensation_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Ref_Compensation_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Ref_Compensation_ID, Integer.valueOf(GS_HR_Ref_Compensation_ID));
	}

	/** Get Ref. Compensation Master.
		@return Ref. Compensation Master	  */
	public int getGS_HR_Ref_Compensation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Ref_Compensation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_ValueNoCheck (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}