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

/** Generated Interface for GS_HR_EmployeeAdvance
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_EmployeeAdvance 
{

    /** TableName=GS_HR_EmployeeAdvance */
    public static final String Table_Name = "GS_HR_EmployeeAdvance";

    /** AD_Table_ID=1000131 */
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

    /** Column name BTW_IsHRAdmin */
    public static final String COLUMNNAME_BTW_IsHRAdmin = "BTW_IsHRAdmin";

	/** Set HR Admin	  */
	public void setBTW_IsHRAdmin (boolean BTW_IsHRAdmin);

	/** Get HR Admin	  */
	public boolean isBTW_IsHRAdmin();

    /** Column name C_BankAccount_ID */
    public static final String COLUMNNAME_C_BankAccount_ID = "C_BankAccount_ID";

	/** Set Bank Account.
	  * Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID);

	/** Get Bank Account.
	  * Account at the Bank
	  */
	public int getC_BankAccount_ID();

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException;

    /** Column name C_Payment_ID */
    public static final String COLUMNNAME_C_Payment_ID = "C_Payment_ID";

	/** Set Payment.
	  * Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID);

	/** Get Payment.
	  * Payment identifier
	  */
	public int getC_Payment_ID();

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException;

    /** Column name CreatePayment */
    public static final String COLUMNNAME_CreatePayment = "CreatePayment";

	/** Set Create Payment	  */
	public void setCreatePayment (String CreatePayment);

	/** Get Create Payment	  */
	public String getCreatePayment();

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

    /** Column name DS_HR_ApprovedAmt */
    public static final String COLUMNNAME_DS_HR_ApprovedAmt = "DS_HR_ApprovedAmt";

	/** Set Approved Amount	  */
	public void setDS_HR_ApprovedAmt (BigDecimal DS_HR_ApprovedAmt);

	/** Get Approved Amount	  */
	public BigDecimal getDS_HR_ApprovedAmt();

    /** Column name DS_RejectionResult */
    public static final String COLUMNNAME_DS_RejectionResult = "DS_RejectionResult";

	/** Set Rejection Result.
	  * Rejection Result
	  */
	public void setDS_RejectionResult (String DS_RejectionResult);

	/** Get Rejection Result.
	  * Rejection Result
	  */
	public String getDS_RejectionResult();

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

    /** Column name ExpectedCloseDate */
    public static final String COLUMNNAME_ExpectedCloseDate = "ExpectedCloseDate";

	/** Set Expected Close Date.
	  * Expected Close Date
	  */
	public void setExpectedCloseDate (Timestamp ExpectedCloseDate);

	/** Get Expected Close Date.
	  * Expected Close Date
	  */
	public Timestamp getExpectedCloseDate();

    /** Column name GS_ExistingLoanAmt */
    public static final String COLUMNNAME_GS_ExistingLoanAmt = "GS_ExistingLoanAmt";

	/** Set Existing Loan Amt.
	  * Existing Loan Amt
	  */
	public void setGS_ExistingLoanAmt (BigDecimal GS_ExistingLoanAmt);

	/** Get Existing Loan Amt.
	  * Existing Loan Amt
	  */
	public BigDecimal getGS_ExistingLoanAmt();

    /** Column name GS_HR_Approval_ID */
    public static final String COLUMNNAME_GS_HR_Approval_ID = "GS_HR_Approval_ID";

	/** Set Approved By	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID);

	/** Get Approved By	  */
	public int getGS_HR_Approval_ID();

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException;

    /** Column name GS_HR_Compensation_Master_ID */
    public static final String COLUMNNAME_GS_HR_Compensation_Master_ID = "GS_HR_Compensation_Master_ID";

	/** Set Compensation	  */
	public void setGS_HR_Compensation_Master_ID (int GS_HR_Compensation_Master_ID);

	/** Get Compensation	  */
	public int getGS_HR_Compensation_Master_ID();

    /** Column name GS_HR_DateApplication */
    public static final String COLUMNNAME_GS_HR_DateApplication = "GS_HR_DateApplication";

	/** Set Application Date	  */
	public void setGS_HR_DateApplication (Timestamp GS_HR_DateApplication);

	/** Get Application Date	  */
	public Timestamp getGS_HR_DateApplication();

    /** Column name GS_HR_EmployeeAdvance_ID */
    public static final String COLUMNNAME_GS_HR_EmployeeAdvance_ID = "GS_HR_EmployeeAdvance_ID";

	/** Set Employee Advance	  */
	public void setGS_HR_EmployeeAdvance_ID (int GS_HR_EmployeeAdvance_ID);

	/** Get Employee Advance	  */
	public int getGS_HR_EmployeeAdvance_ID();

    /** Column name GS_HR_EmployeeAdvance_UU */
    public static final String COLUMNNAME_GS_HR_EmployeeAdvance_UU = "GS_HR_EmployeeAdvance_UU";

	/** Set GS_HR_EmployeeAdvance_UU	  */
	public void setGS_HR_EmployeeAdvance_UU (String GS_HR_EmployeeAdvance_UU);

	/** Get GS_HR_EmployeeAdvance_UU	  */
	public String getGS_HR_EmployeeAdvance_UU();

    /** Column name GS_HR_Employee_ID */
    public static final String COLUMNNAME_GS_HR_Employee_ID = "GS_HR_Employee_ID";

	/** Set Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID);

	/** Get Employee Details	  */
	public int getGS_HR_Employee_ID();

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException;

    /** Column name GS_HR_Installments */
    public static final String COLUMNNAME_GS_HR_Installments = "GS_HR_Installments";

	/** Set Total Installments.
	  * Total Installments
	  */
	public void setGS_HR_Installments (BigDecimal GS_HR_Installments);

	/** Get Total Installments.
	  * Total Installments
	  */
	public BigDecimal getGS_HR_Installments();

    /** Column name GS_HR_IsSubmitted */
    public static final String COLUMNNAME_GS_HR_IsSubmitted = "GS_HR_IsSubmitted";

	/** Set Is Submitted.
	  * Is Submitted
	  */
	public void setGS_HR_IsSubmitted (boolean GS_HR_IsSubmitted);

	/** Get Is Submitted.
	  * Is Submitted
	  */
	public boolean isGS_HR_IsSubmitted();

    /** Column name GS_HR_LoanAmt */
    public static final String COLUMNNAME_GS_HR_LoanAmt = "GS_HR_LoanAmt";

	/** Set Loan Amount.
	  * Loan Amount
	  */
	public void setGS_HR_LoanAmt (BigDecimal GS_HR_LoanAmt);

	/** Get Loan Amount.
	  * Loan Amount
	  */
	public BigDecimal getGS_HR_LoanAmt();

    /** Column name GS_HR_Reason */
    public static final String COLUMNNAME_GS_HR_Reason = "GS_HR_Reason";

	/** Set Reason	  */
	public void setGS_HR_Reason (String GS_HR_Reason);

	/** Get Reason	  */
	public String getGS_HR_Reason();

    /** Column name GS_HR_RemainingInstallments */
    public static final String COLUMNNAME_GS_HR_RemainingInstallments = "GS_HR_RemainingInstallments";

	/** Set Remaining Installment.
	  * Remaining Installment
	  */
	public void setGS_HR_RemainingInstallments (BigDecimal GS_HR_RemainingInstallments);

	/** Get Remaining Installment.
	  * Remaining Installment
	  */
	public BigDecimal getGS_HR_RemainingInstallments();

    /** Column name GS_HR_RepaidAmt */
    public static final String COLUMNNAME_GS_HR_RepaidAmt = "GS_HR_RepaidAmt";

	/** Set Repaid Amount.
	  * Repaid Amount
	  */
	public void setGS_HR_RepaidAmt (BigDecimal GS_HR_RepaidAmt);

	/** Get Repaid Amount.
	  * Repaid Amount
	  */
	public BigDecimal getGS_HR_RepaidAmt();

    /** Column name GS_HR_Submit */
    public static final String COLUMNNAME_GS_HR_Submit = "GS_HR_Submit";

	/** Set Submit For Approval	  */
	public void setGS_HR_Submit (String GS_HR_Submit);

	/** Get Submit For Approval	  */
	public String getGS_HR_Submit();

    /** Column name GS_InstallmentAmt */
    public static final String COLUMNNAME_GS_InstallmentAmt = "GS_InstallmentAmt";

	/** Set Per Month Installment.
	  * Per Month Installment
	  */
	public void setGS_InstallmentAmt (BigDecimal GS_InstallmentAmt);

	/** Get Per Month Installment.
	  * Per Month Installment
	  */
	public BigDecimal getGS_InstallmentAmt();

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

    /** Column name IsApproved */
    public static final String COLUMNNAME_IsApproved = "IsApproved";

	/** Set Approved.
	  * Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved);

	/** Get Approved.
	  * Indicates if this document requires approval
	  */
	public boolean isApproved();

    /** Column name PayDate */
    public static final String COLUMNNAME_PayDate = "PayDate";

	/** Set Payment date.
	  * Date Payment made
	  */
	public void setPayDate (Timestamp PayDate);

	/** Get Payment date.
	  * Date Payment made
	  */
	public Timestamp getPayDate();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

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

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

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
