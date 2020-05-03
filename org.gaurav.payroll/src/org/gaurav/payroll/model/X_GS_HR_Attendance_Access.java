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

/** Generated Model for GS_HR_Attendance_Access
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_Attendance_Access extends PO implements I_GS_HR_Attendance_Access, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_Attendance_Access (Properties ctx, int GS_HR_Attendance_Access_ID, String trxName)
    {
      super (ctx, GS_HR_Attendance_Access_ID, trxName);
      /** if (GS_HR_Attendance_Access_ID == 0)
        {
			setGS_HR_Attendance_Access_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Attendance_Access (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Attendance_Access[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Attendance Access.
		@param GS_HR_Attendance_Access_ID Attendance Access	  */
	public void setGS_HR_Attendance_Access_ID (int GS_HR_Attendance_Access_ID)
	{
		if (GS_HR_Attendance_Access_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_Access_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_Access_ID, Integer.valueOf(GS_HR_Attendance_Access_ID));
	}

	/** Get Attendance Access.
		@return Attendance Access	  */
	public int getGS_HR_Attendance_Access_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Attendance_Access_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Attendance_Access_UU.
		@param GS_HR_Attendance_Access_UU GS_HR_Attendance_Access_UU	  */
	public void setGS_HR_Attendance_Access_UU (String GS_HR_Attendance_Access_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Attendance_Access_UU, GS_HR_Attendance_Access_UU);
	}

	/** Get GS_HR_Attendance_Access_UU.
		@return GS_HR_Attendance_Access_UU	  */
	public String getGS_HR_Attendance_Access_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Attendance_Access_UU);
	}

	public I_GS_HR_Employee getGS_HR_Attendance_Admin() throws RuntimeException
    {
		return (I_GS_HR_Employee)MTable.get(getCtx(), I_GS_HR_Employee.Table_Name)
			.getPO(getGS_HR_Attendance_Admin_ID(), get_TrxName());	}

	/** Set Attendance Admin.
		@param GS_HR_Attendance_Admin_ID Attendance Admin	  */
	public void setGS_HR_Attendance_Admin_ID (int GS_HR_Attendance_Admin_ID)
	{
		if (GS_HR_Attendance_Admin_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_Admin_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Attendance_Admin_ID, Integer.valueOf(GS_HR_Attendance_Admin_ID));
	}

	/** Get Attendance Admin.
		@return Attendance Admin	  */
	public int getGS_HR_Attendance_Admin_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Attendance_Admin_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}