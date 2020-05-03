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

/** Generated Model for GS_HR_LeaveApplication
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_LeaveApplication extends PO implements I_GS_HR_LeaveApplication, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_LeaveApplication (Properties ctx, int GS_HR_LeaveApplication_ID, String trxName)
    {
      super (ctx, GS_HR_LeaveApplication_ID, trxName);
      /** if (GS_HR_LeaveApplication_ID == 0)
        {
			setEndDate (new Timestamp( System.currentTimeMillis() ));
			setGS_HR_DateApplication (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setGS_HR_Employee_ID (0);
			setGS_HR_Leave_Master_ID (0);
			setGS_HR_LeaveApplication_ID (0);
			setGS_HR_LeavesAllowed (Env.ZERO);
// 0
			setGS_HR_Reason (null);
			setGS_HR_RequiredLeaves (Env.ZERO);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_GS_HR_LeaveApplication (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_LeaveApplication[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_Value (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
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

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getGS_HR_Approval_ID(), get_TrxName());	}

	/** Set Current Approval.
		@param GS_HR_Approval_ID Current Approval	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID)
	{
		if (GS_HR_Approval_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, Integer.valueOf(GS_HR_Approval_ID));
	}

	/** Get Current Approval.
		@return Current Approval	  */
	public int getGS_HR_Approval_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Approval_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Application Date.
		@param GS_HR_DateApplication Application Date	  */
	public void setGS_HR_DateApplication (Timestamp GS_HR_DateApplication)
	{
		set_Value (COLUMNNAME_GS_HR_DateApplication, GS_HR_DateApplication);
	}

	/** Get Application Date.
		@return Application Date	  */
	public Timestamp getGS_HR_DateApplication () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GS_HR_DateApplication);
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
			set_Value (COLUMNNAME_GS_HR_Employee_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Employee_ID, Integer.valueOf(GS_HR_Employee_ID));
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

	public I_GS_HR_Leave_Master getGS_HR_Leave_Master() throws RuntimeException
    {
		return (I_GS_HR_Leave_Master)MTable.get(getCtx(), I_GS_HR_Leave_Master.Table_Name)
			.getPO(getGS_HR_Leave_Master_ID(), get_TrxName());	}

	/** Set Leave Type.
		@param GS_HR_Leave_Master_ID Leave Type	  */
	public void setGS_HR_Leave_Master_ID (int GS_HR_Leave_Master_ID)
	{
		if (GS_HR_Leave_Master_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Leave_Master_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Leave_Master_ID, Integer.valueOf(GS_HR_Leave_Master_ID));
	}

	/** Get Leave Type.
		@return Leave Type	  */
	public int getGS_HR_Leave_Master_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Leave_Master_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Leave Application.
		@param GS_HR_LeaveApplication_ID Leave Application	  */
	public void setGS_HR_LeaveApplication_ID (int GS_HR_LeaveApplication_ID)
	{
		if (GS_HR_LeaveApplication_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_LeaveApplication_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_LeaveApplication_ID, Integer.valueOf(GS_HR_LeaveApplication_ID));
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

	/** Set GS_HR_LeaveApplication_UU.
		@param GS_HR_LeaveApplication_UU GS_HR_LeaveApplication_UU	  */
	public void setGS_HR_LeaveApplication_UU (String GS_HR_LeaveApplication_UU)
	{
		set_Value (COLUMNNAME_GS_HR_LeaveApplication_UU, GS_HR_LeaveApplication_UU);
	}

	/** Get GS_HR_LeaveApplication_UU.
		@return GS_HR_LeaveApplication_UU	  */
	public String getGS_HR_LeaveApplication_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_LeaveApplication_UU);
	}

	/** Set Leaves Allowed.
		@param GS_HR_LeavesAllowed 
		Total number of leaves allowed per employee
	  */
	public void setGS_HR_LeavesAllowed (BigDecimal GS_HR_LeavesAllowed)
	{
		set_Value (COLUMNNAME_GS_HR_LeavesAllowed, GS_HR_LeavesAllowed);
	}

	/** Get Leaves Allowed.
		@return Total number of leaves allowed per employee
	  */
	public BigDecimal getGS_HR_LeavesAllowed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_LeavesAllowed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Reason.
		@param GS_HR_Reason Reason	  */
	public void setGS_HR_Reason (String GS_HR_Reason)
	{
		set_Value (COLUMNNAME_GS_HR_Reason, GS_HR_Reason);
	}

	/** Get Reason.
		@return Reason	  */
	public String getGS_HR_Reason () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Reason);
	}

	/** Set Leave Days.
		@param GS_HR_RequiredLeaves Leave Days	  */
	public void setGS_HR_RequiredLeaves (BigDecimal GS_HR_RequiredLeaves)
	{
		set_Value (COLUMNNAME_GS_HR_RequiredLeaves, GS_HR_RequiredLeaves);
	}

	/** Get Leave Days.
		@return Leave Days	  */
	public BigDecimal getGS_HR_RequiredLeaves () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_RequiredLeaves);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Submit For Approval.
		@param GS_HR_Submit Submit For Approval	  */
	public void setGS_HR_Submit (String GS_HR_Submit)
	{
		set_Value (COLUMNNAME_GS_HR_Submit, GS_HR_Submit);
	}

	/** Get Submit For Approval.
		@return Submit For Approval	  */
	public String getGS_HR_Submit () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Submit);
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_ValueNoCheck (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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