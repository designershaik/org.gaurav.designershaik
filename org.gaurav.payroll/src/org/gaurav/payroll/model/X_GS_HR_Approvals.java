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

/** Generated Model for GS_HR_Approvals
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Approvals extends PO implements I_GS_HR_Approvals, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210123L;

    /** Standard Constructor */
    public X_GS_HR_Approvals (Properties ctx, int GS_HR_Approvals_ID, String trxName)
    {
      super (ctx, GS_HR_Approvals_ID, trxName);
      /** if (GS_HR_Approvals_ID == 0)
        {
			setGS_HR_Approvals_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Approvals (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Approvals[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Approve.
		@param DSI_IsApproved Approve	  */
	public void setDSI_IsApproved (boolean DSI_IsApproved)
	{
		set_Value (COLUMNNAME_DSI_IsApproved, Boolean.valueOf(DSI_IsApproved));
	}

	/** Get Approve.
		@return Approve	  */
	public boolean isDSI_IsApproved () 
	{
		Object oo = get_Value(COLUMNNAME_DSI_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Date Approved/Submitted.
		@param DS_DateApproved 
		Date Approved/Submitted
	  */
	public void setDS_DateApproved (Timestamp DS_DateApproved)
	{
		set_Value (COLUMNNAME_DS_DateApproved, DS_DateApproved);
	}

	/** Get Date Approved/Submitted.
		@return Date Approved/Submitted
	  */
	public Timestamp getDS_DateApproved () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_DateApproved);
	}

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getGS_HR_Approval_ID(), get_TrxName());	}

	/** Set Approved By.
		@param GS_HR_Approval_ID Approved By	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID)
	{
		if (GS_HR_Approval_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, Integer.valueOf(GS_HR_Approval_ID));
	}

	/** Get Approved By.
		@return Approved By	  */
	public int getGS_HR_Approval_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Approval_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approvals.
		@param GS_HR_Approvals_ID Approvals	  */
	public void setGS_HR_Approvals_ID (int GS_HR_Approvals_ID)
	{
		if (GS_HR_Approvals_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Approvals_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Approvals_ID, Integer.valueOf(GS_HR_Approvals_ID));
	}

	/** Get Approvals.
		@return Approvals	  */
	public int getGS_HR_Approvals_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Approvals_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Approvals_UU.
		@param GS_HR_Approvals_UU GS_HR_Approvals_UU	  */
	public void setGS_HR_Approvals_UU (String GS_HR_Approvals_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Approvals_UU, GS_HR_Approvals_UU);
	}

	/** Get GS_HR_Approvals_UU.
		@return GS_HR_Approvals_UU	  */
	public String getGS_HR_Approvals_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Approvals_UU);
	}

	public I_GS_HR_EmployeeAdvance getGS_HR_EmployeeAdvance() throws RuntimeException
    {
		return (I_GS_HR_EmployeeAdvance)MTable.get(getCtx(), I_GS_HR_EmployeeAdvance.Table_Name)
			.getPO(getGS_HR_EmployeeAdvance_ID(), get_TrxName());	}

	/** Set Employee Advance.
		@param GS_HR_EmployeeAdvance_ID Employee Advance	  */
	public void setGS_HR_EmployeeAdvance_ID (int GS_HR_EmployeeAdvance_ID)
	{
		if (GS_HR_EmployeeAdvance_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_EmployeeAdvance_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_EmployeeAdvance_ID, Integer.valueOf(GS_HR_EmployeeAdvance_ID));
	}

	/** Get Employee Advance.
		@return Employee Advance	  */
	public int getGS_HR_EmployeeAdvance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_EmployeeAdvance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_GS_HR_LeaveApplication getGS_HR_LeaveApplication() throws RuntimeException
    {
		return (I_GS_HR_LeaveApplication)MTable.get(getCtx(), I_GS_HR_LeaveApplication.Table_Name)
			.getPO(getGS_HR_LeaveApplication_ID(), get_TrxName());	}

	/** Set Leave Application.
		@param GS_HR_LeaveApplication_ID Leave Application	  */
	public void setGS_HR_LeaveApplication_ID (int GS_HR_LeaveApplication_ID)
	{
		if (GS_HR_LeaveApplication_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_LeaveApplication_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_LeaveApplication_ID, Integer.valueOf(GS_HR_LeaveApplication_ID));
	}

	/** Get Leave Application.
		@return Leave Application	  */
	public int getGS_HR_LeaveApplication_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_LeaveApplication_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}