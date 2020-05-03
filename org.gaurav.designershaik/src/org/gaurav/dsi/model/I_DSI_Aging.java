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

/** Generated Interface for DSI_Aging
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DSI_Aging 
{

    /** TableName=DSI_Aging */
    public static final String Table_Name = "DSI_Aging";

    /** AD_Table_ID=1000123 */
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

    /** Column name basedue121150 */
    public static final String COLUMNNAME_basedue121150 = "basedue121150";

	/** Set basedue121150	  */
	public void setbasedue121150 (BigDecimal basedue121150);

	/** Get basedue121150	  */
	public BigDecimal getbasedue121150();

    /** Column name basedue151180 */
    public static final String COLUMNNAME_basedue151180 = "basedue151180";

	/** Set basedue151180	  */
	public void setbasedue151180 (BigDecimal basedue151180);

	/** Get basedue151180	  */
	public BigDecimal getbasedue151180();

    /** Column name basedue181210 */
    public static final String COLUMNNAME_basedue181210 = "basedue181210";

	/** Set basedue181210	  */
	public void setbasedue181210 (BigDecimal basedue181210);

	/** Get basedue181210	  */
	public BigDecimal getbasedue181210();

    /** Column name basedue211240 */
    public static final String COLUMNNAME_basedue211240 = "basedue211240";

	/** Set basedue211240	  */
	public void setbasedue211240 (BigDecimal basedue211240);

	/** Get basedue211240	  */
	public BigDecimal getbasedue211240();

    /** Column name basedue241270 */
    public static final String COLUMNNAME_basedue241270 = "basedue241270";

	/** Set basedue241270	  */
	public void setbasedue241270 (BigDecimal basedue241270);

	/** Get basedue241270	  */
	public BigDecimal getbasedue241270();

    /** Column name basedue271300 */
    public static final String COLUMNNAME_basedue271300 = "basedue271300";

	/** Set basedue271300	  */
	public void setbasedue271300 (BigDecimal basedue271300);

	/** Get basedue271300	  */
	public BigDecimal getbasedue271300();

    /** Column name basedue301330 */
    public static final String COLUMNNAME_basedue301330 = "basedue301330";

	/** Set basedue301330	  */
	public void setbasedue301330 (BigDecimal basedue301330);

	/** Get basedue301330	  */
	public BigDecimal getbasedue301330();

    /** Column name basedue3160 */
    public static final String COLUMNNAME_basedue3160 = "basedue3160";

	/** Set Base Due 31-60	  */
	public void setbasedue3160 (BigDecimal basedue3160);

	/** Get Base Due 31-60	  */
	public BigDecimal getbasedue3160();

    /** Column name basedue331365 */
    public static final String COLUMNNAME_basedue331365 = "basedue331365";

	/** Set basedue331365	  */
	public void setbasedue331365 (BigDecimal basedue331365);

	/** Get basedue331365	  */
	public BigDecimal getbasedue331365();

    /** Column name basedue366425 */
    public static final String COLUMNNAME_basedue366425 = "basedue366425";

	/** Set basedue366425	  */
	public void setbasedue366425 (BigDecimal basedue366425);

	/** Get basedue366425	  */
	public BigDecimal getbasedue366425();

    /** Column name basedue426485 */
    public static final String COLUMNNAME_basedue426485 = "basedue426485";

	/** Set basedue426485	  */
	public void setbasedue426485 (BigDecimal basedue426485);

	/** Get basedue426485	  */
	public BigDecimal getbasedue426485();

    /** Column name basedue486545 */
    public static final String COLUMNNAME_basedue486545 = "basedue486545";

	/** Set basedue486545	  */
	public void setbasedue486545 (BigDecimal basedue486545);

	/** Get basedue486545	  */
	public BigDecimal getbasedue486545();

    /** Column name basedue546585 */
    public static final String COLUMNNAME_basedue546585 = "basedue546585";

	/** Set basedue546585	  */
	public void setbasedue546585 (BigDecimal basedue546585);

	/** Get basedue546585	  */
	public BigDecimal getbasedue546585();

    /** Column name basedue586645 */
    public static final String COLUMNNAME_basedue586645 = "basedue586645";

	/** Set basedue586645	  */
	public void setbasedue586645 (BigDecimal basedue586645);

	/** Get basedue586645	  */
	public BigDecimal getbasedue586645();

    /** Column name basedue6190 */
    public static final String COLUMNNAME_basedue6190 = "basedue6190";

	/** Set basedue6190	  */
	public void setbasedue6190 (BigDecimal basedue6190);

	/** Get basedue6190	  */
	public BigDecimal getbasedue6190();

    /** Column name basedue646705 */
    public static final String COLUMNNAME_basedue646705 = "basedue646705";

	/** Set basedue646705	  */
	public void setbasedue646705 (BigDecimal basedue646705);

	/** Get basedue646705	  */
	public BigDecimal getbasedue646705();

    /** Column name basedue706766 */
    public static final String COLUMNNAME_basedue706766 = "basedue706766";

	/** Set basedue706766	  */
	public void setbasedue706766 (BigDecimal basedue706766);

	/** Get basedue706766	  */
	public BigDecimal getbasedue706766();

    /** Column name basedue766plus */
    public static final String COLUMNNAME_basedue766plus = "basedue766plus";

	/** Set basedue766plus	  */
	public void setbasedue766plus (BigDecimal basedue766plus);

	/** Get basedue766plus	  */
	public BigDecimal getbasedue766plus();

    /** Column name basedue91120 */
    public static final String COLUMNNAME_basedue91120 = "basedue91120";

	/** Set basedue91120	  */
	public void setbasedue91120 (BigDecimal basedue91120);

	/** Get basedue91120	  */
	public BigDecimal getbasedue91120();

    /** Column name basedueupto30 */
    public static final String COLUMNNAME_basedueupto30 = "basedueupto30";

	/** Set Base DueUpto 30	  */
	public void setbasedueupto30 (BigDecimal basedueupto30);

	/** Get Base DueUpto 30	  */
	public BigDecimal getbasedueupto30();

    /** Column name basepastdueamt */
    public static final String COLUMNNAME_basepastdueamt = "basepastdueamt";

	/** Set Base Past DueAmt	  */
	public void setbasepastdueamt (BigDecimal basepastdueamt);

	/** Get Base Past DueAmt	  */
	public BigDecimal getbasepastdueamt();

    /** Column name basetotal */
    public static final String COLUMNNAME_basetotal = "basetotal";

	/** Set basetotal	  */
	public void setbasetotal (BigDecimal basetotal);

	/** Get basetotal	  */
	public BigDecimal getbasetotal();

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

    /** Column name C_BP_Group_ID */
    public static final String COLUMNNAME_C_BP_Group_ID = "C_BP_Group_ID";

	/** Set Business Partner Group.
	  * Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID);

	/** Get Business Partner Group.
	  * Business Partner Group
	  */
	public int getC_BP_Group_ID();

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException;

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

    /** Column name C_Invoice_ID */
    public static final String COLUMNNAME_C_Invoice_ID = "C_Invoice_ID";

	/** Set Invoice.
	  * Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID);

	/** Get Invoice.
	  * Invoice Identifier
	  */
	public int getC_Invoice_ID();

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException;

    /** Column name C_InvoicePaySchedule_ID */
    public static final String COLUMNNAME_C_InvoicePaySchedule_ID = "C_InvoicePaySchedule_ID";

	/** Set Invoice Payment Schedule.
	  * Invoice Payment Schedule
	  */
	public void setC_InvoicePaySchedule_ID (int C_InvoicePaySchedule_ID);

	/** Get Invoice Payment Schedule.
	  * Invoice Payment Schedule
	  */
	public int getC_InvoicePaySchedule_ID();

	public org.compiere.model.I_C_InvoicePaySchedule getC_InvoicePaySchedule() throws RuntimeException;

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

    /** Column name DateInvoiced */
    public static final String COLUMNNAME_DateInvoiced = "DateInvoiced";

	/** Set Date Invoiced.
	  * Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced);

	/** Get Date Invoiced.
	  * Date printed on Invoice
	  */
	public Timestamp getDateInvoiced();

    /** Column name DaysDue */
    public static final String COLUMNNAME_DaysDue = "DaysDue";

	/** Set Days due.
	  * Number of days due (negative: due in number of days)
	  */
	public void setDaysDue (int DaysDue);

	/** Get Days due.
	  * Number of days due (negative: due in number of days)
	  */
	public int getDaysDue();

    /** Column name DSI_Aging_ID */
    public static final String COLUMNNAME_DSI_Aging_ID = "DSI_Aging_ID";

	/** Set Aging	  */
	public void setDSI_Aging_ID (int DSI_Aging_ID);

	/** Get Aging	  */
	public int getDSI_Aging_ID();

    /** Column name DSI_Aging_UU */
    public static final String COLUMNNAME_DSI_Aging_UU = "DSI_Aging_UU";

	/** Set DSI_Aging_UU	  */
	public void setDSI_Aging_UU (String DSI_Aging_UU);

	/** Get DSI_Aging_UU	  */
	public String getDSI_Aging_UU();

    /** Column name Due0_30 */
    public static final String COLUMNNAME_Due0_30 = "Due0_30";

	/** Set Due Today-30	  */
	public void setDue0_30 (BigDecimal Due0_30);

	/** Get Due Today-30	  */
	public BigDecimal getDue0_30();

    /** Column name Due121_150 */
    public static final String COLUMNNAME_Due121_150 = "Due121_150";

	/** Set Due 121_150	  */
	public void setDue121_150 (BigDecimal Due121_150);

	/** Get Due 121_150	  */
	public BigDecimal getDue121_150();

    /** Column name Due151_180 */
    public static final String COLUMNNAME_Due151_180 = "Due151_180";

	/** Set Due 151_180	  */
	public void setDue151_180 (BigDecimal Due151_180);

	/** Get Due 151_180	  */
	public BigDecimal getDue151_180();

    /** Column name Due181_210 */
    public static final String COLUMNNAME_Due181_210 = "Due181_210";

	/** Set Due 181_210	  */
	public void setDue181_210 (BigDecimal Due181_210);

	/** Get Due 181_210	  */
	public BigDecimal getDue181_210();

    /** Column name Due211_240 */
    public static final String COLUMNNAME_Due211_240 = "Due211_240";

	/** Set Due 211_240	  */
	public void setDue211_240 (BigDecimal Due211_240);

	/** Get Due 211_240	  */
	public BigDecimal getDue211_240();

    /** Column name Due241_270 */
    public static final String COLUMNNAME_Due241_270 = "Due241_270";

	/** Set Due 241_270	  */
	public void setDue241_270 (BigDecimal Due241_270);

	/** Get Due 241_270	  */
	public BigDecimal getDue241_270();

    /** Column name Due271_300 */
    public static final String COLUMNNAME_Due271_300 = "Due271_300";

	/** Set Due 271_300	  */
	public void setDue271_300 (BigDecimal Due271_300);

	/** Get Due 271_300	  */
	public BigDecimal getDue271_300();

    /** Column name Due301_330 */
    public static final String COLUMNNAME_Due301_330 = "Due301_330";

	/** Set Due 301_330	  */
	public void setDue301_330 (BigDecimal Due301_330);

	/** Get Due 301_330	  */
	public BigDecimal getDue301_330();

    /** Column name Due31_60 */
    public static final String COLUMNNAME_Due31_60 = "Due31_60";

	/** Set Due 31-60	  */
	public void setDue31_60 (BigDecimal Due31_60);

	/** Get Due 31-60	  */
	public BigDecimal getDue31_60();

    /** Column name Due331_365 */
    public static final String COLUMNNAME_Due331_365 = "Due331_365";

	/** Set Due 331_365	  */
	public void setDue331_365 (BigDecimal Due331_365);

	/** Get Due 331_365	  */
	public BigDecimal getDue331_365();

    /** Column name Due366_425 */
    public static final String COLUMNNAME_Due366_425 = "Due366_425";

	/** Set Due 366_425	  */
	public void setDue366_425 (BigDecimal Due366_425);

	/** Get Due 366_425	  */
	public BigDecimal getDue366_425();

    /** Column name Due426_485 */
    public static final String COLUMNNAME_Due426_485 = "Due426_485";

	/** Set Due 426_485	  */
	public void setDue426_485 (BigDecimal Due426_485);

	/** Get Due 426_485	  */
	public BigDecimal getDue426_485();

    /** Column name Due486_545 */
    public static final String COLUMNNAME_Due486_545 = "Due486_545";

	/** Set Due 486_545	  */
	public void setDue486_545 (BigDecimal Due486_545);

	/** Get Due 486_545	  */
	public BigDecimal getDue486_545();

    /** Column name Due546_585 */
    public static final String COLUMNNAME_Due546_585 = "Due546_585";

	/** Set Due 546_585	  */
	public void setDue546_585 (BigDecimal Due546_585);

	/** Get Due 546_585	  */
	public BigDecimal getDue546_585();

    /** Column name Due586_645 */
    public static final String COLUMNNAME_Due586_645 = "Due586_645";

	/** Set Due 586_645	  */
	public void setDue586_645 (BigDecimal Due586_645);

	/** Get Due 586_645	  */
	public BigDecimal getDue586_645();

    /** Column name Due61_90 */
    public static final String COLUMNNAME_Due61_90 = "Due61_90";

	/** Set Due 61-90	  */
	public void setDue61_90 (BigDecimal Due61_90);

	/** Get Due 61-90	  */
	public BigDecimal getDue61_90();

    /** Column name Due646_705 */
    public static final String COLUMNNAME_Due646_705 = "Due646_705";

	/** Set Due 646_705	  */
	public void setDue646_705 (BigDecimal Due646_705);

	/** Get Due 646_705	  */
	public BigDecimal getDue646_705();

    /** Column name Due706_766 */
    public static final String COLUMNNAME_Due706_766 = "Due706_766";

	/** Set Due 706_766	  */
	public void setDue706_766 (BigDecimal Due706_766);

	/** Get Due 706_766	  */
	public BigDecimal getDue706_766();

    /** Column name Due766Plus */
    public static final String COLUMNNAME_Due766Plus = "Due766Plus";

	/** Set Due 766Plus	  */
	public void setDue766Plus (BigDecimal Due766Plus);

	/** Get Due 766Plus	  */
	public BigDecimal getDue766Plus();

    /** Column name Due91_120 */
    public static final String COLUMNNAME_Due91_120 = "Due91_120";

	/** Set Due 91- 120	  */
	public void setDue91_120 (BigDecimal Due91_120);

	/** Get Due 91- 120	  */
	public BigDecimal getDue91_120();

    /** Column name DueAmt */
    public static final String COLUMNNAME_DueAmt = "DueAmt";

	/** Set Amount due.
	  * Amount of the payment due
	  */
	public void setDueAmt (BigDecimal DueAmt);

	/** Get Amount due.
	  * Amount of the payment due
	  */
	public BigDecimal getDueAmt();

    /** Column name DueDate */
    public static final String COLUMNNAME_DueDate = "DueDate";

	/** Set Due Date.
	  * Date when the payment is due
	  */
	public void setDueDate (Timestamp DueDate);

	/** Get Due Date.
	  * Date when the payment is due
	  */
	public Timestamp getDueDate();

    /** Column name InvoicedAmt */
    public static final String COLUMNNAME_InvoicedAmt = "InvoicedAmt";

	/** Set Invoiced Amount.
	  * The amount invoiced
	  */
	public void setInvoicedAmt (BigDecimal InvoicedAmt);

	/** Get Invoiced Amount.
	  * The amount invoiced
	  */
	public BigDecimal getInvoicedAmt();

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

    /** Column name IsSOTrx */
    public static final String COLUMNNAME_IsSOTrx = "IsSOTrx";

	/** Set Sales Transaction.
	  * This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx);

	/** Get Sales Transaction.
	  * This is a Sales Transaction
	  */
	public boolean isSOTrx();

    /** Column name OpenAmt */
    public static final String COLUMNNAME_OpenAmt = "OpenAmt";

	/** Set Open Amount.
	  * Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt);

	/** Get Open Amount.
	  * Open item amount
	  */
	public BigDecimal getOpenAmt();

    /** Column name PastDueAmt */
    public static final String COLUMNNAME_PastDueAmt = "PastDueAmt";

	/** Set Past Due	  */
	public void setPastDueAmt (BigDecimal PastDueAmt);

	/** Get Past Due	  */
	public BigDecimal getPastDueAmt();

    /** Column name StatementDate */
    public static final String COLUMNNAME_StatementDate = "StatementDate";

	/** Set Statement date.
	  * Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate);

	/** Get Statement date.
	  * Date of the statement
	  */
	public Timestamp getStatementDate();

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
