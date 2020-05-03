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

/** Generated Interface for DS_BankBalancesFD
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_DS_BankBalancesFD 
{

    /** TableName=DS_BankBalancesFD */
    public static final String Table_Name = "DS_BankBalancesFD";

    /** AD_Table_ID=1000223 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AccountNo */
    public static final String COLUMNNAME_AccountNo = "AccountNo";

	/** Set Account No.
	  * Account Number
	  */
	public void setAccountNo (String AccountNo);

	/** Get Account No.
	  * Account Number
	  */
	public String getAccountNo();

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

    /** Column name CurrentBalance */
    public static final String COLUMNNAME_CurrentBalance = "CurrentBalance";

	/** Set Current balance.
	  * Current Balance
	  */
	public void setCurrentBalance (BigDecimal CurrentBalance);

	/** Get Current balance.
	  * Current Balance
	  */
	public BigDecimal getCurrentBalance();

    /** Column name DS_AED */
    public static final String COLUMNNAME_DS_AED = "DS_AED";

	/** Set AED	  */
	public void setDS_AED (BigDecimal DS_AED);

	/** Get AED	  */
	public BigDecimal getDS_AED();

    /** Column name DS_BankBalancesFD_ID */
    public static final String COLUMNNAME_DS_BankBalancesFD_ID = "DS_BankBalancesFD_ID";

	/** Set Bank Balance & FD	  */
	public void setDS_BankBalancesFD_ID (int DS_BankBalancesFD_ID);

	/** Get Bank Balance & FD	  */
	public int getDS_BankBalancesFD_ID();

    /** Column name DS_BankBalancesFD_UU */
    public static final String COLUMNNAME_DS_BankBalancesFD_UU = "DS_BankBalancesFD_UU";

	/** Set DS_BankBalancesFD_UU	  */
	public void setDS_BankBalancesFD_UU (String DS_BankBalancesFD_UU);

	/** Get DS_BankBalancesFD_UU	  */
	public String getDS_BankBalancesFD_UU();

    /** Column name DS_BasePrincipalAmount */
    public static final String COLUMNNAME_DS_BasePrincipalAmount = "DS_BasePrincipalAmount";

	/** Set Base Currency Principal Amount	  */
	public void setDS_BasePrincipalAmount (BigDecimal DS_BasePrincipalAmount);

	/** Get Base Currency Principal Amount	  */
	public BigDecimal getDS_BasePrincipalAmount();

    /** Column name DS_BHD */
    public static final String COLUMNNAME_DS_BHD = "DS_BHD";

	/** Set BHD	  */
	public void setDS_BHD (BigDecimal DS_BHD);

	/** Get BHD	  */
	public BigDecimal getDS_BHD();

    /** Column name DS_EUR */
    public static final String COLUMNNAME_DS_EUR = "DS_EUR";

	/** Set EUR	  */
	public void setDS_EUR (BigDecimal DS_EUR);

	/** Get EUR	  */
	public BigDecimal getDS_EUR();

    /** Column name DS_GBP */
    public static final String COLUMNNAME_DS_GBP = "DS_GBP";

	/** Set GBP	  */
	public void setDS_GBP (BigDecimal DS_GBP);

	/** Get GBP	  */
	public BigDecimal getDS_GBP();

    /** Column name DS_KWD */
    public static final String COLUMNNAME_DS_KWD = "DS_KWD";

	/** Set KWD	  */
	public void setDS_KWD (BigDecimal DS_KWD);

	/** Get KWD	  */
	public BigDecimal getDS_KWD();

    /** Column name DS_OMR */
    public static final String COLUMNNAME_DS_OMR = "DS_OMR";

	/** Set OMR	  */
	public void setDS_OMR (BigDecimal DS_OMR);

	/** Get OMR	  */
	public BigDecimal getDS_OMR();

    /** Column name DS_QAR */
    public static final String COLUMNNAME_DS_QAR = "DS_QAR";

	/** Set QAR	  */
	public void setDS_QAR (BigDecimal DS_QAR);

	/** Get QAR	  */
	public BigDecimal getDS_QAR();

    /** Column name DS_SAR */
    public static final String COLUMNNAME_DS_SAR = "DS_SAR";

	/** Set SAR	  */
	public void setDS_SAR (BigDecimal DS_SAR);

	/** Get SAR	  */
	public BigDecimal getDS_SAR();

    /** Column name DS_USD */
    public static final String COLUMNNAME_DS_USD = "DS_USD";

	/** Set USD	  */
	public void setDS_USD (BigDecimal DS_USD);

	/** Get USD	  */
	public BigDecimal getDS_USD();

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
