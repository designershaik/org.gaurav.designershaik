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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DS_Assignment
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Assignment extends PO implements I_DS_Assignment, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171121L;

    /** Standard Constructor */
    public X_DS_Assignment (Properties ctx, int DS_Assignment_ID, String trxName)
    {
      super (ctx, DS_Assignment_ID, trxName);
      /** if (DS_Assignment_ID == 0)
        {
			setDS_Assignment_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Assignment (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Assignment[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Assign From.
		@param AssignDateFrom 
		Assign resource from
	  */
	public void setAssignDateFrom (Timestamp AssignDateFrom)
	{
		set_ValueNoCheck (COLUMNNAME_AssignDateFrom, AssignDateFrom);
	}

	/** Get Assign From.
		@return Assign resource from
	  */
	public Timestamp getAssignDateFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssignDateFrom);
	}

	/** Set Assign To.
		@param AssignDateTo 
		Assign resource until
	  */
	public void setAssignDateTo (Timestamp AssignDateTo)
	{
		set_ValueNoCheck (COLUMNNAME_AssignDateTo, AssignDateTo);
	}

	/** Get Assign To.
		@return Assign resource until
	  */
	public Timestamp getAssignDateTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssignDateTo);
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException
    {
		return (org.compiere.model.I_C_ProjectPhase)MTable.get(getCtx(), org.compiere.model.I_C_ProjectPhase.Table_Name)
			.getPO(getC_ProjectPhase_ID(), get_TrxName());	}

	/** Set Project Phase.
		@param C_ProjectPhase_ID 
		Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
	{
		if (C_ProjectPhase_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectPhase_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectPhase_ID, Integer.valueOf(C_ProjectPhase_ID));
	}

	/** Get Project Phase.
		@return Phase of a Project
	  */
	public int getC_ProjectPhase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectPhase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ProjectTask getC_ProjectTask() throws RuntimeException
    {
		return (org.compiere.model.I_C_ProjectTask)MTable.get(getCtx(), org.compiere.model.I_C_ProjectTask.Table_Name)
			.getPO(getC_ProjectTask_ID(), get_TrxName());	}

	/** Set Project Task.
		@param C_ProjectTask_ID 
		Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID)
	{
		if (C_ProjectTask_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_ProjectTask_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_ProjectTask_ID, Integer.valueOf(C_ProjectTask_ID));
	}

	/** Get Project Task.
		@return Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectTask_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Allocate/DisAllocate Resource.
		@param DS_AllocDisAlloc Allocate/DisAllocate Resource	  */
	public void setDS_AllocDisAlloc (String DS_AllocDisAlloc)
	{
		set_Value (COLUMNNAME_DS_AllocDisAlloc, DS_AllocDisAlloc);
	}

	/** Get Allocate/DisAllocate Resource.
		@return Allocate/DisAllocate Resource	  */
	public String getDS_AllocDisAlloc () 
	{
		return (String)get_Value(COLUMNNAME_DS_AllocDisAlloc);
	}

	/** Set Assignment Of Employees.
		@param DS_Assignment_ID Assignment Of Employees	  */
	public void setDS_Assignment_ID (int DS_Assignment_ID)
	{
		if (DS_Assignment_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Assignment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Assignment_ID, Integer.valueOf(DS_Assignment_ID));
	}

	/** Get Assignment Of Employees.
		@return Assignment Of Employees	  */
	public int getDS_Assignment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Assignment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Assignment_UU.
		@param DS_Assignment_UU DS_Assignment_UU	  */
	public void setDS_Assignment_UU (String DS_Assignment_UU)
	{
		set_Value (COLUMNNAME_DS_Assignment_UU, DS_Assignment_UU);
	}

	/** Get DS_Assignment_UU.
		@return DS_Assignment_UU	  */
	public String getDS_Assignment_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Assignment_UU);
	}

	public I_DS_AvailEmployee getDS_AvailEmployee() throws RuntimeException
    {
		return (I_DS_AvailEmployee)MTable.get(getCtx(), I_DS_AvailEmployee.Table_Name)
			.getPO(getDS_AvailEmployee_ID(), get_TrxName());	}

	/** Set Available employees for a day.
		@param DS_AvailEmployee_ID Available employees for a day	  */
	public void setDS_AvailEmployee_ID (int DS_AvailEmployee_ID)
	{
		if (DS_AvailEmployee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_AvailEmployee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_AvailEmployee_ID, Integer.valueOf(DS_AvailEmployee_ID));
	}

	/** Get Available employees for a day.
		@return Available employees for a day	  */
	public int getDS_AvailEmployee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_AvailEmployee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Assigned.
		@param DS_IsAssigned Is Assigned	  */
	public void setDS_IsAssigned (boolean DS_IsAssigned)
	{
		set_Value (COLUMNNAME_DS_IsAssigned, Boolean.valueOf(DS_IsAssigned));
	}

	/** Get Is Assigned.
		@return Is Assigned	  */
	public boolean isDS_IsAssigned () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsAssigned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Service.
		@param DS_IsService Is Service	  */
	public void setDS_IsService (boolean DS_IsService)
	{
		set_Value (COLUMNNAME_DS_IsService, Boolean.valueOf(DS_IsService));
	}

	/** Get Is Service.
		@return Is Service	  */
	public boolean isDS_IsService () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsService);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Start/Stop Task.
		@param DS_StartStop_Task 
		Start/Stop Task
	  */
	public void setDS_StartStop_Task (String DS_StartStop_Task)
	{
		set_Value (COLUMNNAME_DS_StartStop_Task, DS_StartStop_Task);
	}

	/** Get Start/Stop Task.
		@return Start/Stop Task
	  */
	public String getDS_StartStop_Task () 
	{
		return (String)get_Value(COLUMNNAME_DS_StartStop_Task);
	}

	/** Set Final Close.
		@param IsFinalClose 
		Entries with Final Close cannot be re-opened
	  */
	public void setIsFinalClose (boolean IsFinalClose)
	{
		set_Value (COLUMNNAME_IsFinalClose, Boolean.valueOf(IsFinalClose));
	}

	/** Get Final Close.
		@return Entries with Final Close cannot be re-opened
	  */
	public boolean isFinalClose () 
	{
		Object oo = get_Value(COLUMNNAME_IsFinalClose);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Order)MTable.get(getCtx(), org.eevolution.model.I_PP_Order.Table_Name)
			.getPO(getPP_Order_ID(), get_TrxName());	}

	/** Set Manufacturing Order.
		@param PP_Order_ID 
		Manufacturing Order
	  */
	public void setPP_Order_ID (int PP_Order_ID)
	{
		if (PP_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Order_ID, Integer.valueOf(PP_Order_ID));
	}

	/** Get Manufacturing Order.
		@return Manufacturing Order
	  */
	public int getPP_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Order_Node getPP_Order_Node() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Order_Node)MTable.get(getCtx(), org.eevolution.model.I_PP_Order_Node.Table_Name)
			.getPO(getPP_Order_Node_ID(), get_TrxName());	}

	/** Set Manufacturing Order Activity.
		@param PP_Order_Node_ID 
		Workflow Node (activity), step or process
	  */
	public void setPP_Order_Node_ID (int PP_Order_Node_ID)
	{
		if (PP_Order_Node_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_PP_Order_Node_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Order_Node_ID, Integer.valueOf(PP_Order_Node_ID));
	}

	/** Get Manufacturing Order Activity.
		@return Workflow Node (activity), step or process
	  */
	public int getPP_Order_Node_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_Node_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}