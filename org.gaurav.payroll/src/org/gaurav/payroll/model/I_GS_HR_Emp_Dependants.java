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

/** Generated Interface for GS_HR_Emp_Dependants
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_Emp_Dependants 
{

    /** TableName=GS_HR_Emp_Dependants */
    public static final String Table_Name = "GS_HR_Emp_Dependants";

    /** AD_Table_ID=1000103 */
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

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name GS_HR_Emp_Dependants_ID */
    public static final String COLUMNNAME_GS_HR_Emp_Dependants_ID = "GS_HR_Emp_Dependants_ID";

	/** Set Employee Dependants	  */
	public void setGS_HR_Emp_Dependants_ID (int GS_HR_Emp_Dependants_ID);

	/** Get Employee Dependants	  */
	public int getGS_HR_Emp_Dependants_ID();

    /** Column name GS_HR_Emp_Dependants_UU */
    public static final String COLUMNNAME_GS_HR_Emp_Dependants_UU = "GS_HR_Emp_Dependants_UU";

	/** Set GS_HR_Emp_Dependants_UU	  */
	public void setGS_HR_Emp_Dependants_UU (String GS_HR_Emp_Dependants_UU);

	/** Get GS_HR_Emp_Dependants_UU	  */
	public String getGS_HR_Emp_Dependants_UU();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

    /** Column name GS_HR_FamilyRelation_ID */
    public static final String COLUMNNAME_GS_HR_FamilyRelation_ID = "GS_HR_FamilyRelation_ID";

	/** Set Relationship	  */
	public void setGS_HR_FamilyRelation_ID (int GS_HR_FamilyRelation_ID);

	/** Get Relationship	  */
	public int getGS_HR_FamilyRelation_ID();

	public I_GS_HR_FamilyRelation getGS_HR_FamilyRelation() throws RuntimeException;

    /** Column name GS_HR_VisaExpiryDate */
    public static final String COLUMNNAME_GS_HR_VisaExpiryDate = "GS_HR_VisaExpiryDate";

	/** Set Visa Expiry Date	  */
	public void setGS_HR_VisaExpiryDate (Timestamp GS_HR_VisaExpiryDate);

	/** Get Visa Expiry Date	  */
	public Timestamp getGS_HR_VisaExpiryDate();

    /** Column name GS_HR_VisaStartDate */
    public static final String COLUMNNAME_GS_HR_VisaStartDate = "GS_HR_VisaStartDate";

	/** Set Visa Start Date	  */
	public void setGS_HR_VisaStartDate (Timestamp GS_HR_VisaStartDate);

	/** Get Visa Start Date	  */
	public Timestamp getGS_HR_VisaStartDate();

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

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Landline.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Landline.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set Mobile No./LandLine 2.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get Mobile No./LandLine 2.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name SSCode */
    public static final String COLUMNNAME_SSCode = "SSCode";

	/** Set CPR/Social Security Code	  */
	public void setSSCode (String SSCode);

	/** Get CPR/Social Security Code	  */
	public String getSSCode();

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
