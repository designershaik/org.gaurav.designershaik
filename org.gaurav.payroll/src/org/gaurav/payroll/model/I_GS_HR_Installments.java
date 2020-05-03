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

/** Generated Interface for GS_HR_Installments
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_Installments 
{

    /** TableName=GS_HR_Installments */
    public static final String Table_Name = "GS_HR_Installments";

    /** AD_Table_ID=1000189 */
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

    /** Column name Date1 */
    public static final String COLUMNNAME_Date1 = "Date1";

	/** Set Date.
	  * Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1);

	/** Get Date.
	  * Date when business is not conducted
	  */
	public Timestamp getDate1();

    /** Column name GS_HR_EmployeeAdvance_ID */
    public static final String COLUMNNAME_GS_HR_EmployeeAdvance_ID = "GS_HR_EmployeeAdvance_ID";

	/** Set Employee Advance	  */
	public void setGS_HR_EmployeeAdvance_ID (int GS_HR_EmployeeAdvance_ID);

	/** Get Employee Advance	  */
	public int getGS_HR_EmployeeAdvance_ID();

	public I_GS_HR_EmployeeAdvance getGS_HR_EmployeeAdvance() throws RuntimeException;

    /** Column name GS_HR_Installments_ID */
    public static final String COLUMNNAME_GS_HR_Installments_ID = "GS_HR_Installments_ID";

	/** Set Installments	  */
	public void setGS_HR_Installments_ID (int GS_HR_Installments_ID);

	/** Get Installments	  */
	public int getGS_HR_Installments_ID();

    /** Column name GS_HR_Installments_UU */
    public static final String COLUMNNAME_GS_HR_Installments_UU = "GS_HR_Installments_UU";

	/** Set GS_HR_Installments_UU	  */
	public void setGS_HR_Installments_UU (String GS_HR_Installments_UU);

	/** Get GS_HR_Installments_UU	  */
	public String getGS_HR_Installments_UU();

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

    /** Column name HR_Break */
    public static final String COLUMNNAME_HR_Break = "HR_Break";

	/** Set Break	  */
	public void setHR_Break (boolean HR_Break);

	/** Get Break	  */
	public boolean isHR_Break();

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

    /** Column name IsPaid */
    public static final String COLUMNNAME_IsPaid = "IsPaid";

	/** Set Paid.
	  * The document is paid
	  */
	public void setIsPaid (boolean IsPaid);

	/** Get Paid.
	  * The document is paid
	  */
	public boolean isPaid();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

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

    /** Column name RemainingAmt */
    public static final String COLUMNNAME_RemainingAmt = "RemainingAmt";

	/** Set Remaining Amt.
	  * Remaining Amount
	  */
	public void setRemainingAmt (BigDecimal RemainingAmt);

	/** Get Remaining Amt.
	  * Remaining Amount
	  */
	public BigDecimal getRemainingAmt();

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
