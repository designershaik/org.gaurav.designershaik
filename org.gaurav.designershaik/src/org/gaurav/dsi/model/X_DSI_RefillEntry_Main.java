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
package org.gaurav.dsi.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DSI_RefillEntry_Main
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_RefillEntry_Main extends PO implements I_DSI_RefillEntry_Main, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_RefillEntry_Main (Properties ctx, int DSI_RefillEntry_Main_ID, String trxName)
    {
      super (ctx, DSI_RefillEntry_Main_ID, trxName);
      /** if (DSI_RefillEntry_Main_ID == 0)
        {
			setDSI_RefillEntry_Main_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_RefillEntry_Main (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_RefillEntry_Main[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set A_Base_Amount.
		@param A_Base_Amount A_Base_Amount	  */
	public void setA_Base_Amount (BigDecimal A_Base_Amount)
	{
		set_Value (COLUMNNAME_A_Base_Amount, A_Base_Amount);
	}

	/** Get A_Base_Amount.
		@return A_Base_Amount	  */
	public BigDecimal getA_Base_Amount () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Base_Amount);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Country getC_Country() throws RuntimeException
    {
		return (org.compiere.model.I_C_Country)MTable.get(getCtx(), org.compiere.model.I_C_Country.Table_Name)
			.getPO(getC_Country_ID(), get_TrxName());	}

	/** Set Country.
		@param C_Country_ID 
		Country 
	  */
	public void setC_Country_ID (int C_Country_ID)
	{
		if (C_Country_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Country_ID, Integer.valueOf(C_Country_ID));
	}

	/** Get Country.
		@return Country 
	  */
	public int getC_Country_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Country_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date received.
		@param DateReceived 
		Date a product was received
	  */
	public void setDateReceived (Timestamp DateReceived)
	{
		set_Value (COLUMNNAME_DateReceived, DateReceived);
	}

	/** Get Date received.
		@return Date a product was received
	  */
	public Timestamp getDateReceived () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReceived);
	}

	/** Set Document No.
		@param DocumentNo 
		Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNo, DocumentNo);
	}

	/** Get Document No.
		@return Document sequence number of the document
	  */
	public String getDocumentNo () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNo);
	}

	/** Set Document Note.
		@param DocumentNote 
		Additional information for a Document
	  */
	public void setDocumentNote (String DocumentNote)
	{
		set_ValueNoCheck (COLUMNNAME_DocumentNote, DocumentNote);
	}

	/** Get Document Note.
		@return Additional information for a Document
	  */
	public String getDocumentNote () 
	{
		return (String)get_Value(COLUMNNAME_DocumentNote);
	}

	/** Set AttendedBy.
		@param DSI_AttendedBy AttendedBy	  */
	public void setDSI_AttendedBy (String DSI_AttendedBy)
	{
		set_Value (COLUMNNAME_DSI_AttendedBy, DSI_AttendedBy);
	}

	/** Get AttendedBy.
		@return AttendedBy	  */
	public String getDSI_AttendedBy () 
	{
		return (String)get_Value(COLUMNNAME_DSI_AttendedBy);
	}

	/** Set Refill Main Entry Form.
		@param DSI_RefillEntry_Main_ID Refill Main Entry Form	  */
	public void setDSI_RefillEntry_Main_ID (int DSI_RefillEntry_Main_ID)
	{
		if (DSI_RefillEntry_Main_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_RefillEntry_Main_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_RefillEntry_Main_ID, Integer.valueOf(DSI_RefillEntry_Main_ID));
	}

	/** Get Refill Main Entry Form.
		@return Refill Main Entry Form	  */
	public int getDSI_RefillEntry_Main_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_RefillEntry_Main_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_RefillEntry_Main_UU.
		@param DSI_RefillEntry_Main_UU DSI_RefillEntry_Main_UU	  */
	public void setDSI_RefillEntry_Main_UU (String DSI_RefillEntry_Main_UU)
	{
		set_Value (COLUMNNAME_DSI_RefillEntry_Main_UU, DSI_RefillEntry_Main_UU);
	}

	/** Get DSI_RefillEntry_Main_UU.
		@return DSI_RefillEntry_Main_UU	  */
	public String getDSI_RefillEntry_Main_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_RefillEntry_Main_UU);
	}

	public I_DSI_Refill_PrintForm getDSI_Refill_PrintForm() throws RuntimeException
    {
		return (I_DSI_Refill_PrintForm)MTable.get(getCtx(), I_DSI_Refill_PrintForm.Table_Name)
			.getPO(getDSI_Refill_PrintForm_ID(), get_TrxName());	}

	/** Set Print Refill Forms.
		@param DSI_Refill_PrintForm_ID Print Refill Forms	  */
	public void setDSI_Refill_PrintForm_ID (int DSI_Refill_PrintForm_ID)
	{
		if (DSI_Refill_PrintForm_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_Refill_PrintForm_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_Refill_PrintForm_ID, Integer.valueOf(DSI_Refill_PrintForm_ID));
	}

	/** Get Print Refill Forms.
		@return Print Refill Forms	  */
	public int getDSI_Refill_PrintForm_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_Refill_PrintForm_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set SubmittedBy.
		@param DSI_Submitter SubmittedBy	  */
	public void setDSI_Submitter (String DSI_Submitter)
	{
		set_Value (COLUMNNAME_DSI_Submitter, DSI_Submitter);
	}

	/** Get SubmittedBy.
		@return SubmittedBy	  */
	public String getDSI_Submitter () 
	{
		return (String)get_Value(COLUMNNAME_DSI_Submitter);
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Invoice net Amount.
		@param NetAmtToInvoice 
		Net amount of this Invoice
	  */
	public void setNetAmtToInvoice (BigDecimal NetAmtToInvoice)
	{
		set_ValueNoCheck (COLUMNNAME_NetAmtToInvoice, NetAmtToInvoice);
	}

	/** Get Invoice net Amount.
		@return Net amount of this Invoice
	  */
	public BigDecimal getNetAmtToInvoice () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_NetAmtToInvoice);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** PaymentRule AD_Reference_ID=195 */
	public static final int PAYMENTRULE_AD_Reference_ID=195;
	/** Cash = B */
	public static final String PAYMENTRULE_Cash = "B";
	/** Credit Card = K */
	public static final String PAYMENTRULE_CreditCard = "K";
	/** Direct Deposit = T */
	public static final String PAYMENTRULE_DirectDeposit = "T";
	/** Check = S */
	public static final String PAYMENTRULE_Check = "S";
	/** On Credit = P */
	public static final String PAYMENTRULE_OnCredit = "P";
	/** Direct Debit = D */
	public static final String PAYMENTRULE_DirectDebit = "D";
	/** Mixed POS Payment = M */
	public static final String PAYMENTRULE_MixedPOSPayment = "M";
	/** Set Payment Rule.
		@param PaymentRule 
		How you pay the invoice
	  */
	public void setPaymentRule (String PaymentRule)
	{

		set_ValueNoCheck (COLUMNNAME_PaymentRule, PaymentRule);
	}

	/** Get Payment Rule.
		@return How you pay the invoice
	  */
	public String getPaymentRule () 
	{
		return (String)get_Value(COLUMNNAME_PaymentRule);
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
}