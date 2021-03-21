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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for GS_HR_ProfessionalHistory
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_ProfessionalHistory extends PO implements I_GS_HR_ProfessionalHistory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_ProfessionalHistory (Properties ctx, int GS_HR_ProfessionalHistory_ID, String trxName)
    {
      super (ctx, GS_HR_ProfessionalHistory_ID, trxName);
      /** if (GS_HR_ProfessionalHistory_ID == 0)
        {
			setEndDate (new Timestamp( System.currentTimeMillis() ));
			setGS_HR_CompanyName (null);
			setGS_HR_ProfessionalHistory_ID (0);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_GS_HR_ProfessionalHistory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_ProfessionalHistory[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Company Name.
		@param GS_HR_CompanyName Company Name	  */
	public void setGS_HR_CompanyName (String GS_HR_CompanyName)
	{
		set_Value (COLUMNNAME_GS_HR_CompanyName, GS_HR_CompanyName);
	}

	/** Get Company Name.
		@return Company Name	  */
	public String getGS_HR_CompanyName () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_CompanyName);
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

	/** Set Professional Background.
		@param GS_HR_ProfessionalHistory_ID Professional Background	  */
	public void setGS_HR_ProfessionalHistory_ID (int GS_HR_ProfessionalHistory_ID)
	{
		if (GS_HR_ProfessionalHistory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_ProfessionalHistory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_ProfessionalHistory_ID, Integer.valueOf(GS_HR_ProfessionalHistory_ID));
	}

	/** Get Professional Background.
		@return Professional Background	  */
	public int getGS_HR_ProfessionalHistory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_ProfessionalHistory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_ProfessionalHistory_UU.
		@param GS_HR_ProfessionalHistory_UU GS_HR_ProfessionalHistory_UU	  */
	public void setGS_HR_ProfessionalHistory_UU (String GS_HR_ProfessionalHistory_UU)
	{
		set_Value (COLUMNNAME_GS_HR_ProfessionalHistory_UU, GS_HR_ProfessionalHistory_UU);
	}

	/** Get GS_HR_ProfessionalHistory_UU.
		@return GS_HR_ProfessionalHistory_UU	  */
	public String getGS_HR_ProfessionalHistory_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_ProfessionalHistory_UU);
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