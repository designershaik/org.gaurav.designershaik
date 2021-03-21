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
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_HR_EmployeeAdvance
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_EmployeeAdvance extends PO implements I_GS_HR_EmployeeAdvance, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_EmployeeAdvance (Properties ctx, int GS_HR_EmployeeAdvance_ID, String trxName)
    {
      super (ctx, GS_HR_EmployeeAdvance_ID, trxName);
      /** if (GS_HR_EmployeeAdvance_ID == 0)
        {
			setExpectedCloseDate (new Timestamp( System.currentTimeMillis() ));
			setGS_HR_Compensation_Master_ID (0);
// @SQL=SELECT GS_HR_Compensation_Master_ID FROM GS_HR_Compensation_Master WHERE GS_HR_Advance='Y'
			setGS_HR_DateApplication (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setGS_HR_EmployeeAdvance_ID (0);
			setGS_HR_Employee_ID (0);
			setGS_HR_Installments (Env.ZERO);
			setGS_HR_LoanAmt (Env.ZERO);
			setGS_HR_Reason (null);
			setGS_HR_RemainingInstallments (Env.ZERO);
			setGS_HR_RepaidAmt (Env.ZERO);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_GS_HR_EmployeeAdvance (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_EmployeeAdvance[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR Admin.
		@param BTW_IsHRAdmin HR Admin	  */
	public void setBTW_IsHRAdmin (boolean BTW_IsHRAdmin)
	{
		throw new IllegalArgumentException ("BTW_IsHRAdmin is virtual column");	}

	/** Get HR Admin.
		@return HR Admin	  */
	public boolean isBTW_IsHRAdmin () 
	{
		Object oo = get_Value(COLUMNNAME_BTW_IsHRAdmin);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_C_BankAccount getC_BankAccount() throws RuntimeException
    {
		return (org.compiere.model.I_C_BankAccount)MTable.get(getCtx(), org.compiere.model.I_C_BankAccount.Table_Name)
			.getPO(getC_BankAccount_ID(), get_TrxName());	}

	/** Set Bank Account.
		@param C_BankAccount_ID 
		Account at the Bank
	  */
	public void setC_BankAccount_ID (int C_BankAccount_ID)
	{
		if (C_BankAccount_ID < 1) 
			set_Value (COLUMNNAME_C_BankAccount_ID, null);
		else 
			set_Value (COLUMNNAME_C_BankAccount_ID, Integer.valueOf(C_BankAccount_ID));
	}

	/** Get Bank Account.
		@return Account at the Bank
	  */
	public int getC_BankAccount_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BankAccount_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Payment getC_Payment() throws RuntimeException
    {
		return (org.compiere.model.I_C_Payment)MTable.get(getCtx(), org.compiere.model.I_C_Payment.Table_Name)
			.getPO(getC_Payment_ID(), get_TrxName());	}

	/** Set Payment.
		@param C_Payment_ID 
		Payment identifier
	  */
	public void setC_Payment_ID (int C_Payment_ID)
	{
		if (C_Payment_ID < 1) 
			set_Value (COLUMNNAME_C_Payment_ID, null);
		else 
			set_Value (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
	}

	/** Get Payment.
		@return Payment identifier
	  */
	public int getC_Payment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Payment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Payment.
		@param CreatePayment Create Payment	  */
	public void setCreatePayment (String CreatePayment)
	{
		set_Value (COLUMNNAME_CreatePayment, CreatePayment);
	}

	/** Get Create Payment.
		@return Create Payment	  */
	public String getCreatePayment () 
	{
		return (String)get_Value(COLUMNNAME_CreatePayment);
	}

	/** Set Approved Amount.
		@param DS_HR_ApprovedAmt Approved Amount	  */
	public void setDS_HR_ApprovedAmt (BigDecimal DS_HR_ApprovedAmt)
	{
		set_Value (COLUMNNAME_DS_HR_ApprovedAmt, DS_HR_ApprovedAmt);
	}

	/** Get Approved Amount.
		@return Approved Amount	  */
	public BigDecimal getDS_HR_ApprovedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_HR_ApprovedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Rejection Result.
		@param DS_RejectionResult 
		Rejection Result
	  */
	public void setDS_RejectionResult (String DS_RejectionResult)
	{
		set_Value (COLUMNNAME_DS_RejectionResult, DS_RejectionResult);
	}

	/** Get Rejection Result.
		@return Rejection Result
	  */
	public String getDS_RejectionResult () 
	{
		return (String)get_Value(COLUMNNAME_DS_RejectionResult);
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

	/** Set Expected Close Date.
		@param ExpectedCloseDate 
		Expected Close Date
	  */
	public void setExpectedCloseDate (Timestamp ExpectedCloseDate)
	{
		set_Value (COLUMNNAME_ExpectedCloseDate, ExpectedCloseDate);
	}

	/** Get Expected Close Date.
		@return Expected Close Date
	  */
	public Timestamp getExpectedCloseDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ExpectedCloseDate);
	}

	/** Set Existing Loan Amt.
		@param GS_ExistingLoanAmt 
		Existing Loan Amt
	  */
	public void setGS_ExistingLoanAmt (BigDecimal GS_ExistingLoanAmt)
	{
		set_Value (COLUMNNAME_GS_ExistingLoanAmt, GS_ExistingLoanAmt);
	}

	/** Get Existing Loan Amt.
		@return Existing Loan Amt
	  */
	public BigDecimal getGS_ExistingLoanAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_ExistingLoanAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_AD_User getGS_HR_Approval() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getGS_HR_Approval_ID(), get_TrxName());	}

	/** Set Approved By.
		@param GS_HR_Approval_ID Approved By	  */
	public void setGS_HR_Approval_ID (int GS_HR_Approval_ID)
	{
		if (GS_HR_Approval_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Approval_ID, Integer.valueOf(GS_HR_Approval_ID));
	}

	/** Get Approved By.
		@return Approved By	  */
	public int getGS_HR_Approval_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Approval_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Compensation.
		@param GS_HR_Compensation_Master_ID Compensation	  */
	public void setGS_HR_Compensation_Master_ID (int GS_HR_Compensation_Master_ID)
	{
		if (GS_HR_Compensation_Master_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Compensation_Master_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Compensation_Master_ID, Integer.valueOf(GS_HR_Compensation_Master_ID));
	}

	/** Get Compensation.
		@return Compensation	  */
	public int getGS_HR_Compensation_Master_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Compensation_Master_ID);
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

	/** Set Employee Advance.
		@param GS_HR_EmployeeAdvance_ID Employee Advance	  */
	public void setGS_HR_EmployeeAdvance_ID (int GS_HR_EmployeeAdvance_ID)
	{
		if (GS_HR_EmployeeAdvance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmployeeAdvance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_EmployeeAdvance_ID, Integer.valueOf(GS_HR_EmployeeAdvance_ID));
	}

	/** Get Employee Advance.
		@return Employee Advance	  */
	public int getGS_HR_EmployeeAdvance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_EmployeeAdvance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_EmployeeAdvance_UU.
		@param GS_HR_EmployeeAdvance_UU GS_HR_EmployeeAdvance_UU	  */
	public void setGS_HR_EmployeeAdvance_UU (String GS_HR_EmployeeAdvance_UU)
	{
		set_Value (COLUMNNAME_GS_HR_EmployeeAdvance_UU, GS_HR_EmployeeAdvance_UU);
	}

	/** Get GS_HR_EmployeeAdvance_UU.
		@return GS_HR_EmployeeAdvance_UU	  */
	public String getGS_HR_EmployeeAdvance_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_EmployeeAdvance_UU);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getGS_HR_Employee_ID()));
    }

	/** Set Total Installments.
		@param GS_HR_Installments 
		Total Installments
	  */
	public void setGS_HR_Installments (BigDecimal GS_HR_Installments)
	{
		set_Value (COLUMNNAME_GS_HR_Installments, GS_HR_Installments);
	}

	/** Get Total Installments.
		@return Total Installments
	  */
	public BigDecimal getGS_HR_Installments () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_Installments);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Is Submitted.
		@param GS_HR_IsSubmitted 
		Is Submitted
	  */
	public void setGS_HR_IsSubmitted (boolean GS_HR_IsSubmitted)
	{
		set_Value (COLUMNNAME_GS_HR_IsSubmitted, Boolean.valueOf(GS_HR_IsSubmitted));
	}

	/** Get Is Submitted.
		@return Is Submitted
	  */
	public boolean isGS_HR_IsSubmitted () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_IsSubmitted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Loan Amount.
		@param GS_HR_LoanAmt 
		Loan Amount
	  */
	public void setGS_HR_LoanAmt (BigDecimal GS_HR_LoanAmt)
	{
		set_Value (COLUMNNAME_GS_HR_LoanAmt, GS_HR_LoanAmt);
	}

	/** Get Loan Amount.
		@return Loan Amount
	  */
	public BigDecimal getGS_HR_LoanAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_LoanAmt);
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

	/** Set Remaining Installment.
		@param GS_HR_RemainingInstallments 
		Remaining Installment
	  */
	public void setGS_HR_RemainingInstallments (BigDecimal GS_HR_RemainingInstallments)
	{
		set_Value (COLUMNNAME_GS_HR_RemainingInstallments, GS_HR_RemainingInstallments);
	}

	/** Get Remaining Installment.
		@return Remaining Installment
	  */
	public BigDecimal getGS_HR_RemainingInstallments () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_RemainingInstallments);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Repaid Amount.
		@param GS_HR_RepaidAmt 
		Repaid Amount
	  */
	public void setGS_HR_RepaidAmt (BigDecimal GS_HR_RepaidAmt)
	{
		set_Value (COLUMNNAME_GS_HR_RepaidAmt, GS_HR_RepaidAmt);
	}

	/** Get Repaid Amount.
		@return Repaid Amount
	  */
	public BigDecimal getGS_HR_RepaidAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_RepaidAmt);
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

	/** Set Per Month Installment.
		@param GS_InstallmentAmt 
		Per Month Installment
	  */
	public void setGS_InstallmentAmt (BigDecimal GS_InstallmentAmt)
	{
		set_Value (COLUMNNAME_GS_InstallmentAmt, GS_InstallmentAmt);
	}

	/** Get Per Month Installment.
		@return Per Month Installment
	  */
	public BigDecimal getGS_InstallmentAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_InstallmentAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Payment date.
		@param PayDate 
		Date Payment made
	  */
	public void setPayDate (Timestamp PayDate)
	{
		set_ValueNoCheck (COLUMNNAME_PayDate, PayDate);
	}

	/** Get Payment date.
		@return Date Payment made
	  */
	public Timestamp getPayDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PayDate);
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
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

	/** Hold = Hold */
	public static final String STATUS_Hold = "Hold";
	/** Approved = Approved */
	public static final String STATUS_Approved = "Approved";
	/** New = New */
	public static final String STATUS_New = "New";
	/** InProgress = InProgress */
	public static final String STATUS_InProgress = "InProgress";
	/** Set Status.
		@param Status 
		Status of the currently running check
	  */
	public void setStatus (String Status)
	{

		set_Value (COLUMNNAME_Status, Status);
	}

	/** Get Status.
		@return Status of the currently running check
	  */
	public String getStatus () 
	{
		return (String)get_Value(COLUMNNAME_Status);
	}
}