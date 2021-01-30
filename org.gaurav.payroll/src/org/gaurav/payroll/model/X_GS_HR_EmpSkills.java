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

/** Generated Model for GS_HR_EmpSkills
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_EmpSkills extends PO implements I_GS_HR_EmpSkills, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210123L;

    /** Standard Constructor */
    public X_GS_HR_EmpSkills (Properties ctx, int GS_HR_EmpSkills_ID, String trxName)
    {
      super (ctx, GS_HR_EmpSkills_ID, trxName);
      /** if (GS_HR_EmpSkills_ID == 0)
        {
			setGS_HR_EmpSkills_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_EmpSkills (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_EmpSkills[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Employee Skills.
		@param GS_HR_EmpSkills_ID Employee Skills	  */
	public void setGS_HR_EmpSkills_ID (int GS_HR_EmpSkills_ID)
	{
		if (GS_HR_EmpSkills_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmpSkills_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmpSkills_ID, Integer.valueOf(GS_HR_EmpSkills_ID));
	}

	/** Get Employee Skills.
		@return Employee Skills	  */
	public int getGS_HR_EmpSkills_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_EmpSkills_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_EmpSkills_UU.
		@param GS_HR_EmpSkills_UU GS_HR_EmpSkills_UU	  */
	public void setGS_HR_EmpSkills_UU (String GS_HR_EmpSkills_UU)
	{
		set_Value (COLUMNNAME_GS_HR_EmpSkills_UU, GS_HR_EmpSkills_UU);
	}

	/** Get GS_HR_EmpSkills_UU.
		@return GS_HR_EmpSkills_UU	  */
	public String getGS_HR_EmpSkills_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_EmpSkills_UU);
	}

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException
    {
		return (I_GS_HR_Employee)MTable.get(getCtx(), I_GS_HR_Employee.Table_Name)
			.getPO(getGS_HR_Employee_ID(), get_TrxName());	}

	/** Set Employee Details.
		@param GS_HR_Employee_ID Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID)
	{
		if (GS_HR_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Employee_ID, Integer.valueOf(GS_HR_Employee_ID));
	}

	/** Get Employee Details.
		@return Employee Details	  */
	public int getGS_HR_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_GS_HR_Skills getGS_HR_Skills() throws RuntimeException
    {
		return (I_GS_HR_Skills)MTable.get(getCtx(), I_GS_HR_Skills.Table_Name)
			.getPO(getGS_HR_Skills_ID(), get_TrxName());	}

	/** Set Skill master.
		@param GS_HR_Skills_ID Skill master	  */
	public void setGS_HR_Skills_ID (int GS_HR_Skills_ID)
	{
		if (GS_HR_Skills_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Skills_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Skills_ID, Integer.valueOf(GS_HR_Skills_ID));
	}

	/** Get Skill master.
		@return Skill master	  */
	public int getGS_HR_Skills_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Skills_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Years Of Experience.
		@param GS_HR_YearsOfExperience 
		Years Of Experience
	  */
	public void setGS_HR_YearsOfExperience (BigDecimal GS_HR_YearsOfExperience)
	{
		set_Value (COLUMNNAME_GS_HR_YearsOfExperience, GS_HR_YearsOfExperience);
	}

	/** Get Years Of Experience.
		@return Years Of Experience
	  */
	public BigDecimal getGS_HR_YearsOfExperience () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_YearsOfExperience);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}