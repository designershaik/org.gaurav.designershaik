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
package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for HR_Contract_Compensation
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_HR_Contract_Compensation 
{

    /** TableName=HR_Contract_Compensation */
    public static final String Table_Name = "HR_Contract_Compensation";

    /** AD_Table_ID=1000199 */
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

    /** Column name Amt */
    public static final String COLUMNNAME_Amt = "Amt";

	/** Set Amount.
	  * Amount
	  */
	public void setAmt (BigDecimal Amt);

	/** Get Amount.
	  * Amount
	  */
	public BigDecimal getAmt();

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name GS_HR_Compensation_Master_ID */
    public static final String COLUMNNAME_GS_HR_Compensation_Master_ID = "GS_HR_Compensation_Master_ID";

	/** Set Compensation	  */
	public void setGS_HR_Compensation_Master_ID (int GS_HR_Compensation_Master_ID);

	/** Get Compensation	  */
	public int getGS_HR_Compensation_Master_ID();

	public I_GS_HR_Compensation_Master getGS_HR_Compensation_Master() throws RuntimeException;

    /** Column name GS_HR_IsEarning */
    public static final String COLUMNNAME_GS_HR_IsEarning = "GS_HR_IsEarning";

	/** Set Earning.
	  * Earning
	  */
	public void setGS_HR_IsEarning (boolean GS_HR_IsEarning);

	/** Get Earning.
	  * Earning
	  */
	public boolean isGS_HR_IsEarning();

    /** Column name GS_HR_IsPercent */
    public static final String COLUMNNAME_GS_HR_IsPercent = "GS_HR_IsPercent";

	/** Set Percent.
	  * Percent
	  */
	public void setGS_HR_IsPercent (boolean GS_HR_IsPercent);

	/** Get Percent.
	  * Percent
	  */
	public boolean isGS_HR_IsPercent();

    /** Column name HR_Contract_Compensation_ID */
    public static final String COLUMNNAME_HR_Contract_Compensation_ID = "HR_Contract_Compensation_ID";

	/** Set Salary Details	  */
	public void setHR_Contract_Compensation_ID (int HR_Contract_Compensation_ID);

	/** Get Salary Details	  */
	public int getHR_Contract_Compensation_ID();

    /** Column name HR_Contract_Compensation_UU */
    public static final String COLUMNNAME_HR_Contract_Compensation_UU = "HR_Contract_Compensation_UU";

	/** Set HR_Contract_Compensation_UU	  */
	public void setHR_Contract_Compensation_UU (String HR_Contract_Compensation_UU);

	/** Get HR_Contract_Compensation_UU	  */
	public String getHR_Contract_Compensation_UU();

    /** Column name HR_Contract_ID */
    public static final String COLUMNNAME_HR_Contract_ID = "HR_Contract_ID";

	/** Set Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID);

	/** Get Payroll Contract	  */
	public int getHR_Contract_ID();

	public I_HR_Contract getHR_Contract() throws RuntimeException;

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

    /** Column name Percent */
    public static final String COLUMNNAME_Percent = "Percent";

	/** Set Percent.
	  * Percentage
	  */
	public void setPercent (BigDecimal Percent);

	/** Get Percent.
	  * Percentage
	  */
	public BigDecimal getPercent();

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
