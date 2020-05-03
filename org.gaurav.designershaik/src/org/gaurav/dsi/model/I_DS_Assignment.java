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
package org.gaurav.dsi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DS_Assignment
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_Assignment 
{

    /** TableName=DS_Assignment */
    public static final String Table_Name = "DS_Assignment";

    /** AD_Table_ID=1000020 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name AssignDateFrom */
    public static final String COLUMNNAME_AssignDateFrom = "AssignDateFrom";

	/** Set Assign From.
	  * Assign resource from
	  */
	public void setAssignDateFrom (Timestamp AssignDateFrom);

	/** Get Assign From.
	  * Assign resource from
	  */
	public Timestamp getAssignDateFrom();

    /** Column name AssignDateTo */
    public static final String COLUMNNAME_AssignDateTo = "AssignDateTo";

	/** Set Assign To.
	  * Assign resource until
	  */
	public void setAssignDateTo (Timestamp AssignDateTo);

	/** Get Assign To.
	  * Assign resource until
	  */
	public Timestamp getAssignDateTo();

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_ProjectPhase_ID */
    public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";

	/** Set Project Phase.
	  * Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID);

	/** Get Project Phase.
	  * Phase of a Project
	  */
	public int getC_ProjectPhase_ID();

	public org.compiere.model.I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException;

    /** Column name C_ProjectTask_ID */
    public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";

	/** Set Project Task.
	  * Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID);

	/** Get Project Task.
	  * Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID();

	public org.compiere.model.I_C_ProjectTask getC_ProjectTask() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DS_AllocDisAlloc */
    public static final String COLUMNNAME_DS_AllocDisAlloc = "DS_AllocDisAlloc";

	/** Set Allocate/DisAllocate Resource	  */
	public void setDS_AllocDisAlloc (String DS_AllocDisAlloc);

	/** Get Allocate/DisAllocate Resource	  */
	public String getDS_AllocDisAlloc();

    /** Column name DS_Assignment_ID */
    public static final String COLUMNNAME_DS_Assignment_ID = "DS_Assignment_ID";

	/** Set Assignment Of Employees	  */
	public void setDS_Assignment_ID (int DS_Assignment_ID);

	/** Get Assignment Of Employees	  */
	public int getDS_Assignment_ID();

    /** Column name DS_Assignment_UU */
    public static final String COLUMNNAME_DS_Assignment_UU = "DS_Assignment_UU";

	/** Set DS_Assignment_UU	  */
	public void setDS_Assignment_UU (String DS_Assignment_UU);

	/** Get DS_Assignment_UU	  */
	public String getDS_Assignment_UU();

    /** Column name DS_AvailEmployee_ID */
    public static final String COLUMNNAME_DS_AvailEmployee_ID = "DS_AvailEmployee_ID";

	/** Set Available employees for a day	  */
	public void setDS_AvailEmployee_ID (int DS_AvailEmployee_ID);

	/** Get Available employees for a day	  */
	public int getDS_AvailEmployee_ID();

	public I_DS_AvailEmployee getDS_AvailEmployee() throws RuntimeException;

    /** Column name DS_IsAssigned */
    public static final String COLUMNNAME_DS_IsAssigned = "DS_IsAssigned";

	/** Set Is Assigned	  */
	public void setDS_IsAssigned (boolean DS_IsAssigned);

	/** Get Is Assigned	  */
	public boolean isDS_IsAssigned();

    /** Column name DS_IsService */
    public static final String COLUMNNAME_DS_IsService = "DS_IsService";

	/** Set Is Service	  */
	public void setDS_IsService (boolean DS_IsService);

	/** Get Is Service	  */
	public boolean isDS_IsService();

    /** Column name DS_StartStop_Task */
    public static final String COLUMNNAME_DS_StartStop_Task = "DS_StartStop_Task";

	/** Set Start/Stop Task.
	  * Start/Stop Task
	  */
	public void setDS_StartStop_Task (String DS_StartStop_Task);

	/** Get Start/Stop Task.
	  * Start/Stop Task
	  */
	public String getDS_StartStop_Task();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsFinalClose */
    public static final String COLUMNNAME_IsFinalClose = "IsFinalClose";

	/** Set Final Close.
	  * Entries with Final Close cannot be re-opened
	  */
	public void setIsFinalClose (boolean IsFinalClose);

	/** Get Final Close.
	  * Entries with Final Close cannot be re-opened
	  */
	public boolean isFinalClose();

    /** Column name LineNo */
    public static final String COLUMNNAME_LineNo = "LineNo";

	/** Set Line.
	  * Line No
	  */
	public void setLineNo (int LineNo);

	/** Get Line.
	  * Line No
	  */
	public int getLineNo();

    /** Column name PP_Order_ID */
    public static final String COLUMNNAME_PP_Order_ID = "PP_Order_ID";

	/** Set Manufacturing Order.
	  * Manufacturing Order
	  */
	public void setPP_Order_ID (int PP_Order_ID);

	/** Get Manufacturing Order.
	  * Manufacturing Order
	  */
	public int getPP_Order_ID();

	public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException;

    /** Column name PP_Order_Node_ID */
    public static final String COLUMNNAME_PP_Order_Node_ID = "PP_Order_Node_ID";

	/** Set Manufacturing Order Activity.
	  * Workflow Node (activity), step or process
	  */
	public void setPP_Order_Node_ID (int PP_Order_Node_ID);

	/** Get Manufacturing Order Activity.
	  * Workflow Node (activity), step or process
	  */
	public int getPP_Order_Node_ID();

	public org.eevolution.model.I_PP_Order_Node getPP_Order_Node() throws RuntimeException;

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
