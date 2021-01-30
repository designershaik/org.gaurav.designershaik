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

/** Generated Interface for GS_HR_EmpCompensation
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_EmpCompensation 
{

    /** TableName=GS_HR_EmpCompensation */
    public static final String Table_Name = "GS_HR_EmpCompensation";

    /** AD_Table_ID=1000101 */
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

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

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

    /** Column name GS_HR_CompDependantOn */
    public static final String COLUMNNAME_GS_HR_CompDependantOn = "GS_HR_CompDependantOn";

	/** Set Compensation Dependent On.
	  * Compensation Dependent On
	  */
	public void setGS_HR_CompDependantOn (String GS_HR_CompDependantOn);

	/** Get Compensation Dependent On.
	  * Compensation Dependent On
	  */
	public String getGS_HR_CompDependantOn();

    /** Column name GS_HR_Compensation_Master_ID */
    public static final String COLUMNNAME_GS_HR_Compensation_Master_ID = "GS_HR_Compensation_Master_ID";

	/** Set Compensation	  */
	public void setGS_HR_Compensation_Master_ID (int GS_HR_Compensation_Master_ID);

	/** Get Compensation	  */
	public int getGS_HR_Compensation_Master_ID();

	public I_GS_HR_Compensation_Master getGS_HR_Compensation_Master() throws RuntimeException;

    /** Column name GS_HR_EmpCompensation_ID */
    public static final String COLUMNNAME_GS_HR_EmpCompensation_ID = "GS_HR_EmpCompensation_ID";

	/** Set Employee Compensation	  */
	public void setGS_HR_EmpCompensation_ID (int GS_HR_EmpCompensation_ID);

	/** Get Employee Compensation	  */
	public int getGS_HR_EmpCompensation_ID();

    /** Column name GS_HR_EmpCompensation_UU */
    public static final String COLUMNNAME_GS_HR_EmpCompensation_UU = "GS_HR_EmpCompensation_UU";

	/** Set GS_HR_EmpCompensation_UU	  */
	public void setGS_HR_EmpCompensation_UU (String GS_HR_EmpCompensation_UU);

	/** Get GS_HR_EmpCompensation_UU	  */
	public String getGS_HR_EmpCompensation_UU();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
