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

/** Generated Interface for GS_HR_Employee
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_Employee 
{

    /** TableName=GS_HR_Employee */
    public static final String Table_Name = "GS_HR_Employee";

    /** AD_Table_ID=1000097 */
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

    /** Column name AD_Image_ID */
    public static final String COLUMNNAME_AD_Image_ID = "AD_Image_ID";

	/** Set Image.
	  * Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID);

	/** Get Image.
	  * Image or Icon
	  */
	public int getAD_Image_ID();

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

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Cost Center.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Cost Center.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

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

    /** Column name Code */
    public static final String COLUMNNAME_Code = "Code";

	/** Set Validation code.
	  * Validation Code
	  */
	public void setCode (int Code);

	/** Get Validation code.
	  * Validation Code
	  */
	public int getCode();

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

    /** Column name DS_OvertimeType */
    public static final String COLUMNNAME_DS_OvertimeType = "DS_OvertimeType";

	/** Set Overtime Type.
	  * Overtime Type
	  */
	public void setDS_OvertimeType (String DS_OvertimeType);

	/** Get Overtime Type.
	  * Overtime Type
	  */
	public String getDS_OvertimeType();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name GS_CPRDDesignation */
    public static final String COLUMNNAME_GS_CPRDDesignation = "GS_CPRDDesignation";

	/** Set CPR Designation.
	  * CPR Designation
	  */
	public void setGS_CPRDDesignation (String GS_CPRDDesignation);

	/** Get CPR Designation.
	  * CPR Designation
	  */
	public String getGS_CPRDDesignation();

    /** Column name GS_HR_BloodGroup */
    public static final String COLUMNNAME_GS_HR_BloodGroup = "GS_HR_BloodGroup";

	/** Set Blood Group	  */
	public void setGS_HR_BloodGroup (String GS_HR_BloodGroup);

	/** Get Blood Group	  */
	public String getGS_HR_BloodGroup();

    /** Column name GS_HR_DateOfPermanency */
    public static final String COLUMNNAME_GS_HR_DateOfPermanency = "GS_HR_DateOfPermanency";

	/** Set Date Of Permanency.
	  * Date Of Permanency
	  */
	public void setGS_HR_DateOfPermanency (Timestamp GS_HR_DateOfPermanency);

	/** Get Date Of Permanency.
	  * Date Of Permanency
	  */
	public Timestamp getGS_HR_DateOfPermanency();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

    /** Column name GS_HR_Employee_UU */
    public static final String COLUMNNAME_GS_HR_Employee_UU = "GS_HR_Employee_UU";

	/** Set GS_HR_Employee_UU	  */
	public void setGS_HR_Employee_UU (String GS_HR_Employee_UU);

	/** Get GS_HR_Employee_UU	  */
	public String getGS_HR_Employee_UU();

    /** Column name GS_HR_ExemptFromLateDeduction */
    public static final String COLUMNNAME_GS_HR_ExemptFromLateDeduction = "GS_HR_ExemptFromLateDeduction";

	/** Set Exempt from Late Deduction.
	  * Exempt from Late Deduction
	  */
	public void setGS_HR_ExemptFromLateDeduction (boolean GS_HR_ExemptFromLateDeduction);

	/** Get Exempt from Late Deduction.
	  * Exempt from Late Deduction
	  */
	public boolean isGS_HR_ExemptFromLateDeduction();

    /** Column name GS_HR_IsTerminate */
    public static final String COLUMNNAME_GS_HR_IsTerminate = "GS_HR_IsTerminate";

	/** Set Is Terminated	  */
	public void setGS_HR_IsTerminate (boolean GS_HR_IsTerminate);

	/** Get Is Terminated	  */
	public boolean isGS_HR_IsTerminate();

    /** Column name GS_HR_MaritalStatus */
    public static final String COLUMNNAME_GS_HR_MaritalStatus = "GS_HR_MaritalStatus";

	/** Set Marital Status	  */
	public void setGS_HR_MaritalStatus (String GS_HR_MaritalStatus);

	/** Get Marital Status	  */
	public String getGS_HR_MaritalStatus();

    /** Column name GS_HR_Nationality_ID */
    public static final String COLUMNNAME_GS_HR_Nationality_ID = "GS_HR_Nationality_ID";

	/** Set Nationality	  */
	public void setGS_HR_Nationality_ID (int GS_HR_Nationality_ID);

	/** Get Nationality	  */
	public int getGS_HR_Nationality_ID();

	public I_GS_HR_Nationality getGS_HR_Nationality() throws RuntimeException;

    /** Column name GS_HR_ProbationEndDate */
    public static final String COLUMNNAME_GS_HR_ProbationEndDate = "GS_HR_ProbationEndDate";

	/** Set Probation End Date.
	  * Probation End Date
	  */
	public void setGS_HR_ProbationEndDate (Timestamp GS_HR_ProbationEndDate);

	/** Get Probation End Date.
	  * Probation End Date
	  */
	public Timestamp getGS_HR_ProbationEndDate();

    /** Column name GS_HR_RemainingInstallments */
    public static final String COLUMNNAME_GS_HR_RemainingInstallments = "GS_HR_RemainingInstallments";

	/** Set Remaining Installment.
	  * Remaining Installment
	  */
	public void setGS_HR_RemainingInstallments (int GS_HR_RemainingInstallments);

	/** Get Remaining Installment.
	  * Remaining Installment
	  */
	public int getGS_HR_RemainingInstallments();

    /** Column name GS_HR_Terminate */
    public static final String COLUMNNAME_GS_HR_Terminate = "GS_HR_Terminate";

	/** Set Terminate Employee	  */
	public void setGS_HR_Terminate (String GS_HR_Terminate);

	/** Get Terminate Employee	  */
	public String getGS_HR_Terminate();

    /** Column name GS_HR_TimeSlot_Group_ID */
    public static final String COLUMNNAME_GS_HR_TimeSlot_Group_ID = "GS_HR_TimeSlot_Group_ID";

	/** Set Time Slot	  */
	public void setGS_HR_TimeSlot_Group_ID (int GS_HR_TimeSlot_Group_ID);

	/** Get Time Slot	  */
	public int getGS_HR_TimeSlot_Group_ID();

	public I_GS_HR_TimeSlot_Group getGS_HR_TimeSlot_Group() throws RuntimeException;

    /** Column name GS_HR_Warnings_ID */
    public static final String COLUMNNAME_GS_HR_Warnings_ID = "GS_HR_Warnings_ID";

	/** Set Warnings/Notices	  */
	public void setGS_HR_Warnings_ID (int GS_HR_Warnings_ID);

	/** Get Warnings/Notices	  */
	public int getGS_HR_Warnings_ID();

	public I_GS_HR_Warnings getGS_HR_Warnings() throws RuntimeException;

    /** Column name GS_TerminationReason */
    public static final String COLUMNNAME_GS_TerminationReason = "GS_TerminationReason";

	/** Set Termination Reason	  */
	public void setGS_TerminationReason (String GS_TerminationReason);

	/** Get Termination Reason	  */
	public String getGS_TerminationReason();

    /** Column name HR_Department_ID */
    public static final String COLUMNNAME_HR_Department_ID = "HR_Department_ID";

	/** Set Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID);

	/** Get Payroll Department	  */
	public int getHR_Department_ID();

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException;

    /** Column name HR_Job_ID */
    public static final String COLUMNNAME_HR_Job_ID = "HR_Job_ID";

	/** Set Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID);

	/** Get Payroll Job	  */
	public int getHR_Job_ID();

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException;

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

    /** Column name Name2 */
    public static final String COLUMNNAME_Name2 = "Name2";

	/** Set Name 2.
	  * Additional Name
	  */
	public void setName2 (String Name2);

	/** Get Name 2.
	  * Additional Name
	  */
	public String getName2();

    /** Column name SSCode */
    public static final String COLUMNNAME_SSCode = "SSCode";

	/** Set CPR/Social Security Code	  */
	public void setSSCode (String SSCode);

	/** Get CPR/Social Security Code	  */
	public String getSSCode();

    /** Column name S_Resource_ID */
    public static final String COLUMNNAME_S_Resource_ID = "S_Resource_ID";

	/** Set Resource.
	  * Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID);

	/** Get Resource.
	  * Resource
	  */
	public int getS_Resource_ID();

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException;

    /** Column name Signature */
    public static final String COLUMNNAME_Signature = "Signature";

	/** Set Signature	  */
	public void setSignature (Object Signature);

	/** Get Signature	  */
	public Object getSignature();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

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
