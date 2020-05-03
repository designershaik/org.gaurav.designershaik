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

/** Generated Interface for DS_LeadCheckList
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_LeadCheckList 
{

    /** TableName=DS_LeadCheckList */
    public static final String Table_Name = "DS_LeadCheckList";

    /** AD_Table_ID=1000072 */
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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Opportunity_ID */
    public static final String COLUMNNAME_C_Opportunity_ID = "C_Opportunity_ID";

	/** Set Sales Opportunity	  */
	public void setC_Opportunity_ID (int C_Opportunity_ID);

	/** Get Sales Opportunity	  */
	public int getC_Opportunity_ID();

	public org.compiere.model.I_C_Opportunity getC_Opportunity() throws RuntimeException;

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

    /** Column name DateRequested */
    public static final String COLUMNNAME_DateRequested = "DateRequested";

	/** Set Date Requested	  */
	public void setDateRequested (Timestamp DateRequested);

	/** Get Date Requested	  */
	public Timestamp getDateRequested();

    /** Column name DateResponse */
    public static final String COLUMNNAME_DateResponse = "DateResponse";

	/** Set Response Date.
	  * Date of the Response
	  */
	public void setDateResponse (Timestamp DateResponse);

	/** Get Response Date.
	  * Date of the Response
	  */
	public Timestamp getDateResponse();

    /** Column name DS_CheckList */
    public static final String COLUMNNAME_DS_CheckList = "DS_CheckList";

	/** Set Check List.
	  * Document required as a part of Lead conversion
	  */
	public void setDS_CheckList (String DS_CheckList);

	/** Get Check List.
	  * Document required as a part of Lead conversion
	  */
	public String getDS_CheckList();

    /** Column name DS_IsReceived */
    public static final String COLUMNNAME_DS_IsReceived = "DS_IsReceived";

	/** Set Received.
	  * Received the document
	  */
	public void setDS_IsReceived (boolean DS_IsReceived);

	/** Get Received.
	  * Received the document
	  */
	public boolean isDS_IsReceived();

    /** Column name DS_IsRequested */
    public static final String COLUMNNAME_DS_IsRequested = "DS_IsRequested";

	/** Set Requested.
	  * Request for the document
	  */
	public void setDS_IsRequested (boolean DS_IsRequested);

	/** Get Requested.
	  * Request for the document
	  */
	public boolean isDS_IsRequested();

    /** Column name DS_LeadCheckList_ID */
    public static final String COLUMNNAME_DS_LeadCheckList_ID = "DS_LeadCheckList_ID";

	/** Set Check List	  */
	public void setDS_LeadCheckList_ID (int DS_LeadCheckList_ID);

	/** Get Check List	  */
	public int getDS_LeadCheckList_ID();

    /** Column name DS_LeadCheckList_UU */
    public static final String COLUMNNAME_DS_LeadCheckList_UU = "DS_LeadCheckList_UU";

	/** Set DS_LeadCheckList_UU	  */
	public void setDS_LeadCheckList_UU (String DS_LeadCheckList_UU);

	/** Get DS_LeadCheckList_UU	  */
	public String getDS_LeadCheckList_UU();

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
