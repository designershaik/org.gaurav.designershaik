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

/** Generated Interface for DS_OutstandingAgingSummary
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_OutstandingAgingSummary 
{

    /** TableName=DS_OutstandingAgingSummary */
    public static final String Table_Name = "DS_OutstandingAgingSummary";

    /** AD_Table_ID=1000077 */
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

    /** Column name C_DunningRun_ID */
    public static final String COLUMNNAME_C_DunningRun_ID = "C_DunningRun_ID";

	/** Set Dunning Run.
	  * Dunning Run
	  */
	public void setC_DunningRun_ID (int C_DunningRun_ID);

	/** Get Dunning Run.
	  * Dunning Run
	  */
	public int getC_DunningRun_ID();

	public org.compiere.model.I_C_DunningRun getC_DunningRun() throws RuntimeException;

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

    /** Column name DS_Current */
    public static final String COLUMNNAME_DS_Current = "DS_Current";

	/** Set Current.
	  * Current for aging
	  */
	public void setDS_Current (BigDecimal DS_Current);

	/** Get Current.
	  * Current for aging
	  */
	public BigDecimal getDS_Current();

    /** Column name DS_OutstandingAgingSummary_ID */
    public static final String COLUMNNAME_DS_OutstandingAgingSummary_ID = "DS_OutstandingAgingSummary_ID";

	/** Set Outstanding Balance Aging Summary	  */
	public void setDS_OutstandingAgingSummary_ID (int DS_OutstandingAgingSummary_ID);

	/** Get Outstanding Balance Aging Summary	  */
	public int getDS_OutstandingAgingSummary_ID();

    /** Column name DS_OutstandingAgingSummary_UU */
    public static final String COLUMNNAME_DS_OutstandingAgingSummary_UU = "DS_OutstandingAgingSummary_UU";

	/** Set DS_OutstandingAgingSummary_UU	  */
	public void setDS_OutstandingAgingSummary_UU (String DS_OutstandingAgingSummary_UU);

	/** Get DS_OutstandingAgingSummary_UU	  */
	public String getDS_OutstandingAgingSummary_UU();

    /** Column name Due0_30 */
    public static final String COLUMNNAME_Due0_30 = "Due0_30";

	/** Set Due Today-30	  */
	public void setDue0_30 (BigDecimal Due0_30);

	/** Get Due Today-30	  */
	public BigDecimal getDue0_30();

    /** Column name Due31_60 */
    public static final String COLUMNNAME_Due31_60 = "Due31_60";

	/** Set Due 31-60	  */
	public void setDue31_60 (BigDecimal Due31_60);

	/** Get Due 31-60	  */
	public BigDecimal getDue31_60();

    /** Column name Due61_90 */
    public static final String COLUMNNAME_Due61_90 = "Due61_90";

	/** Set Due 61-90	  */
	public void setDue61_90 (BigDecimal Due61_90);

	/** Get Due 61-90	  */
	public BigDecimal getDue61_90();

    /** Column name Due91_Plus */
    public static final String COLUMNNAME_Due91_Plus = "Due91_Plus";

	/** Set Due > 91	  */
	public void setDue91_Plus (BigDecimal Due91_Plus);

	/** Get Due > 91	  */
	public BigDecimal getDue91_Plus();

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
