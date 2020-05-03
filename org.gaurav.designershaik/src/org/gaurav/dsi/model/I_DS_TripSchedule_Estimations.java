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

/** Generated Interface for DS_TripSchedule_Estimations
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_TripSchedule_Estimations 
{

    /** TableName=DS_TripSchedule_Estimations */
    public static final String Table_Name = "DS_TripSchedule_Estimations";

    /** AD_Table_ID=1000142 */
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

    /** Column name C_InvoiceLine_ID */
    public static final String COLUMNNAME_C_InvoiceLine_ID = "C_InvoiceLine_ID";

	/** Set Invoice Line.
	  * Invoice Detail Line
	  */
	public void setC_InvoiceLine_ID (int C_InvoiceLine_ID);

	/** Get Invoice Line.
	  * Invoice Detail Line
	  */
	public int getC_InvoiceLine_ID();

	public org.compiere.model.I_C_InvoiceLine getC_InvoiceLine() throws RuntimeException;

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

    /** Column name DS_Budget */
    public static final String COLUMNNAME_DS_Budget = "DS_Budget";

	/** Set Estimated Amt.
	  * Estimated Amt
	  */
	public void setDS_Budget (BigDecimal DS_Budget);

	/** Get Estimated Amt.
	  * Estimated Amt
	  */
	public BigDecimal getDS_Budget();

    /** Column name DS_Delivery_Trips_ID */
    public static final String COLUMNNAME_DS_Delivery_Trips_ID = "DS_Delivery_Trips_ID";

	/** Set Delivery trips	  */
	public void setDS_Delivery_Trips_ID (int DS_Delivery_Trips_ID);

	/** Get Delivery trips	  */
	public int getDS_Delivery_Trips_ID();

	public I_DS_Delivery_Trips getDS_Delivery_Trips() throws RuntimeException;

    /** Column name DS_Trip_ExpenseType_ID */
    public static final String COLUMNNAME_DS_Trip_ExpenseType_ID = "DS_Trip_ExpenseType_ID";

	/** Set Expense Type	  */
	public void setDS_Trip_ExpenseType_ID (int DS_Trip_ExpenseType_ID);

	/** Get Expense Type	  */
	public int getDS_Trip_ExpenseType_ID();

	public I_DS_Trip_ExpenseType getDS_Trip_ExpenseType() throws RuntimeException;

    /** Column name DS_TripSchedule_Estimations_ID */
    public static final String COLUMNNAME_DS_TripSchedule_Estimations_ID = "DS_TripSchedule_Estimations_ID";

	/** Set Trip Schedule Estimation	  */
	public void setDS_TripSchedule_Estimations_ID (int DS_TripSchedule_Estimations_ID);

	/** Get Trip Schedule Estimation	  */
	public int getDS_TripSchedule_Estimations_ID();

    /** Column name DS_TripSchedule_Estimations_UU */
    public static final String COLUMNNAME_DS_TripSchedule_Estimations_UU = "DS_TripSchedule_Estimations_UU";

	/** Set DS_TripSchedule_Estimations_UU	  */
	public void setDS_TripSchedule_Estimations_UU (String DS_TripSchedule_Estimations_UU);

	/** Get DS_TripSchedule_Estimations_UU	  */
	public String getDS_TripSchedule_Estimations_UU();

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

    /** Column name PaymentRule */
    public static final String COLUMNNAME_PaymentRule = "PaymentRule";

	/** Set Payment Rule.
	  * How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule);

	/** Get Payment Rule.
	  * How you pay the invoice
	  */
	public String getPaymentRule();

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
