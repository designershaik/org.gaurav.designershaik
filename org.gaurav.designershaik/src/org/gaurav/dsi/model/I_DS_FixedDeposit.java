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

/** Generated Interface for DS_FixedDeposit
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_FixedDeposit 
{

    /** TableName=DS_FixedDeposit */
    public static final String Table_Name = "DS_FixedDeposit";

    /** AD_Table_ID=1000107 */
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

    /** Column name C_Bank_ID */
    public static final String COLUMNNAME_C_Bank_ID = "C_Bank_ID";

	/** Set Bank.
	  * Bank
	  */
	public void setC_Bank_ID (int C_Bank_ID);

	/** Get Bank.
	  * Bank
	  */
	public int getC_Bank_ID();

	public org.compiere.model.I_C_Bank getC_Bank() throws RuntimeException;

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

    /** Column name C_ConversionType_ID */
    public static final String COLUMNNAME_C_ConversionType_ID = "C_ConversionType_ID";

	/** Set Currency Type.
	  * Currency Conversion Rate Type
	  */
	public void setC_ConversionType_ID (int C_ConversionType_ID);

	/** Get Currency Type.
	  * Currency Conversion Rate Type
	  */
	public int getC_ConversionType_ID();

	public org.compiere.model.I_C_ConversionType getC_ConversionType() throws RuntimeException;

    /** Column name C_Currency_ID */
    public static final String COLUMNNAME_C_Currency_ID = "C_Currency_ID";

	/** Set Currency.
	  * The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID);

	/** Get Currency.
	  * The Currency for this record
	  */
	public int getC_Currency_ID();

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException;

    /** Column name CloseDate */
    public static final String COLUMNNAME_CloseDate = "CloseDate";

	/** Set Close Date.
	  * Close Date
	  */
	public void setCloseDate (Timestamp CloseDate);

	/** Get Close Date.
	  * Close Date
	  */
	public Timestamp getCloseDate();

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

    /** Column name DateAcct */
    public static final String COLUMNNAME_DateAcct = "DateAcct";

	/** Set Account Date.
	  * Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct);

	/** Get Account Date.
	  * Accounting Date
	  */
	public Timestamp getDateAcct();

    /** Column name DS_BaseInterestAmount */
    public static final String COLUMNNAME_DS_BaseInterestAmount = "DS_BaseInterestAmount";

	/** Set Interest Amount(Base)	  */
	public void setDS_BaseInterestAmount (BigDecimal DS_BaseInterestAmount);

	/** Get Interest Amount(Base)	  */
	public BigDecimal getDS_BaseInterestAmount();

    /** Column name DS_BasePrincipalAmount */
    public static final String COLUMNNAME_DS_BasePrincipalAmount = "DS_BasePrincipalAmount";

	/** Set Base Currency Principal Amount	  */
	public void setDS_BasePrincipalAmount (BigDecimal DS_BasePrincipalAmount);

	/** Get Base Currency Principal Amount	  */
	public BigDecimal getDS_BasePrincipalAmount();

    /** Column name DS_CertificateNumber */
    public static final String COLUMNNAME_DS_CertificateNumber = "DS_CertificateNumber";

	/** Set Certificate Number	  */
	public void setDS_CertificateNumber (String DS_CertificateNumber);

	/** Get Certificate Number	  */
	public String getDS_CertificateNumber();

    /** Column name DS_CreateFD */
    public static final String COLUMNNAME_DS_CreateFD = "DS_CreateFD";

	/** Set Create FD	  */
	public void setDS_CreateFD (String DS_CreateFD);

	/** Get Create FD	  */
	public String getDS_CreateFD();

    /** Column name DS_FDTerm */
    public static final String COLUMNNAME_DS_FDTerm = "DS_FDTerm";

	/** Set Term For FD	  */
	public void setDS_FDTerm (int DS_FDTerm);

	/** Get Term For FD	  */
	public int getDS_FDTerm();

    /** Column name DS_FDTerminateOrRenew */
    public static final String COLUMNNAME_DS_FDTerminateOrRenew = "DS_FDTerminateOrRenew";

	/** Set Terminate/Renew FD	  */
	public void setDS_FDTerminateOrRenew (String DS_FDTerminateOrRenew);

	/** Get Terminate/Renew FD	  */
	public String getDS_FDTerminateOrRenew();

    /** Column name DS_FixedDeposit_ID */
    public static final String COLUMNNAME_DS_FixedDeposit_ID = "DS_FixedDeposit_ID";

	/** Set Fixed Deposit Registration	  */
	public void setDS_FixedDeposit_ID (int DS_FixedDeposit_ID);

	/** Get Fixed Deposit Registration	  */
	public int getDS_FixedDeposit_ID();

    /** Column name DS_FixedDeposit_UU */
    public static final String COLUMNNAME_DS_FixedDeposit_UU = "DS_FixedDeposit_UU";

	/** Set DS_FixedDeposit_UU	  */
	public void setDS_FixedDeposit_UU (String DS_FixedDeposit_UU);

	/** Get DS_FixedDeposit_UU	  */
	public String getDS_FixedDeposit_UU();

    /** Column name DS_InterestAmtCharge_ID */
    public static final String COLUMNNAME_DS_InterestAmtCharge_ID = "DS_InterestAmtCharge_ID";

	/** Set Charge for Interest Amount	  */
	public void setDS_InterestAmtCharge_ID (int DS_InterestAmtCharge_ID);

	/** Get Charge for Interest Amount	  */
	public int getDS_InterestAmtCharge_ID();

	public org.compiere.model.I_C_Charge getDS_InterestAmtCharge() throws RuntimeException;

    /** Column name DS_IsNewFD */
    public static final String COLUMNNAME_DS_IsNewFD = "DS_IsNewFD";

	/** Set New FD	  */
	public void setDS_IsNewFD (boolean DS_IsNewFD);

	/** Get New FD	  */
	public boolean isDS_IsNewFD();

    /** Column name DS_IsTerminated */
    public static final String COLUMNNAME_DS_IsTerminated = "DS_IsTerminated";

	/** Set Terminated	  */
	public void setDS_IsTerminated (boolean DS_IsTerminated);

	/** Get Terminated	  */
	public boolean isDS_IsTerminated();

    /** Column name DS_Matured */
    public static final String COLUMNNAME_DS_Matured = "DS_Matured";

	/** Set Matured.
	  * FD Matured
	  */
	public void setDS_Matured (boolean DS_Matured);

	/** Get Matured.
	  * FD Matured
	  */
	public boolean isDS_Matured();

    /** Column name DS_MaturityDate */
    public static final String COLUMNNAME_DS_MaturityDate = "DS_MaturityDate";

	/** Set Maturity Date	  */
	public void setDS_MaturityDate (Timestamp DS_MaturityDate);

	/** Get Maturity Date	  */
	public Timestamp getDS_MaturityDate();

    /** Column name DS_PrincipalAmount */
    public static final String COLUMNNAME_DS_PrincipalAmount = "DS_PrincipalAmount";

	/** Set Principal Amount	  */
	public void setDS_PrincipalAmount (BigDecimal DS_PrincipalAmount);

	/** Get Principal Amount	  */
	public BigDecimal getDS_PrincipalAmount();

    /** Column name DS_PrincipalAmtCharge_ID */
    public static final String COLUMNNAME_DS_PrincipalAmtCharge_ID = "DS_PrincipalAmtCharge_ID";

	/** Set Charge for Principal Amount	  */
	public void setDS_PrincipalAmtCharge_ID (int DS_PrincipalAmtCharge_ID);

	/** Get Charge for Principal Amount	  */
	public int getDS_PrincipalAmtCharge_ID();

	public org.compiere.model.I_C_Charge getDS_PrincipalAmtCharge() throws RuntimeException;

    /** Column name DS_RenewedFD_ID */
    public static final String COLUMNNAME_DS_RenewedFD_ID = "DS_RenewedFD_ID";

	/** Set Renewed FD.
	  * Renewed FD
	  */
	public void setDS_RenewedFD_ID (int DS_RenewedFD_ID);

	/** Get Renewed FD.
	  * Renewed FD
	  */
	public int getDS_RenewedFD_ID();

	public I_DS_FixedDeposit getDS_RenewedFD() throws RuntimeException;

    /** Column name InterestAmt */
    public static final String COLUMNNAME_InterestAmt = "InterestAmt";

	/** Set Interest Amount.
	  * Interest Amount
	  */
	public void setInterestAmt (BigDecimal InterestAmt);

	/** Get Interest Amount.
	  * Interest Amount
	  */
	public BigDecimal getInterestAmt();

    /** Column name InterestPercent */
    public static final String COLUMNNAME_InterestPercent = "InterestPercent";

	/** Set Interest in percent.
	  * Percentage interest to charge on overdue invoices
	  */
	public void setInterestPercent (BigDecimal InterestPercent);

	/** Get Interest in percent.
	  * Percentage interest to charge on overdue invoices
	  */
	public BigDecimal getInterestPercent();

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
