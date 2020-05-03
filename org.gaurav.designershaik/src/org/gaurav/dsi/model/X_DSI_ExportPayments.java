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

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DSI_ExportPayments
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_ExportPayments extends PO implements I_DSI_ExportPayments, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_ExportPayments (Properties ctx, int DSI_ExportPayments_ID, String trxName)
    {
      super (ctx, DSI_ExportPayments_ID, trxName);
      /** if (DSI_ExportPayments_ID == 0)
        {
			setDSI_ExportPayments_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_ExportPayments (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_ExportPayments[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Year.
		@param CalendarYear 
		Calendar Year
	  */
	public void setCalendarYear (int CalendarYear)
	{
		set_Value (COLUMNNAME_CalendarYear, Integer.valueOf(CalendarYear));
	}

	/** Get Year.
		@return Calendar Year
	  */
	public int getCalendarYear () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_CalendarYear);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
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

	/** Set Batch for exporting payments to textfile.
		@param DSI_ExportPayments_ID Batch for exporting payments to textfile	  */
	public void setDSI_ExportPayments_ID (int DSI_ExportPayments_ID)
	{
		if (DSI_ExportPayments_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_ExportPayments_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_ExportPayments_ID, Integer.valueOf(DSI_ExportPayments_ID));
	}

	/** Get Batch for exporting payments to textfile.
		@return Batch for exporting payments to textfile	  */
	public int getDSI_ExportPayments_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_ExportPayments_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_ExportPayments_UU.
		@param DSI_ExportPayments_UU DSI_ExportPayments_UU	  */
	public void setDSI_ExportPayments_UU (String DSI_ExportPayments_UU)
	{
		set_Value (COLUMNNAME_DSI_ExportPayments_UU, DSI_ExportPayments_UU);
	}

	/** Get DSI_ExportPayments_UU.
		@return DSI_ExportPayments_UU	  */
	public String getDSI_ExportPayments_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_ExportPayments_UU);
	}

	/** 26 Apr- 25 May = 26 Apr- 25 May */
	public static final String DSI_MONTH_26Apr_25May = "26 Apr- 25 May";
	/** 26 Aug- 25 Sep = 26 Aug- 25 Sep */
	public static final String DSI_MONTH_26Aug_25Sep = "26 Aug- 25 Sep";
	/** 26 Dec- 25 Jan = 26 Dec- 25 Jan */
	public static final String DSI_MONTH_26Dec_25Jan = "26 Dec- 25 Jan";
	/** 26 Feb- 25 Mar = 26 Feb- 25 Mar */
	public static final String DSI_MONTH_26Feb_25Mar = "26 Feb- 25 Mar";
	/** 26 Jan- 25 Feb = 26 Jan- 25 Feb */
	public static final String DSI_MONTH_26Jan_25Feb = "26 Jan- 25 Feb";
	/** 26 Jul- 25 Aug = 26 Jul- 25 Aug */
	public static final String DSI_MONTH_26Jul_25Aug = "26 Jul- 25 Aug";
	/** 26 Jun- 25 Jul = 26 Jun- 25 Jul */
	public static final String DSI_MONTH_26Jun_25Jul = "26 Jun- 25 Jul";
	/** 26 Mar- 25 Apr = 26 Mar- 25 Apr */
	public static final String DSI_MONTH_26Mar_25Apr = "26 Mar- 25 Apr";
	/** 26 May- 25 Jun = 26 May- 25 Jun */
	public static final String DSI_MONTH_26May_25Jun = "26 May- 25 Jun";
	/** 26 Nov- 25 Dec = 26 Nov- 25 Dec */
	public static final String DSI_MONTH_26Nov_25Dec = "26 Nov- 25 Dec";
	/** 26 Oct- 25 Nov = 26 Oct- 25 Nov */
	public static final String DSI_MONTH_26Oct_25Nov = "26 Oct- 25 Nov";
	/** 26 Sep- 25 Oct = 26 Sep- 25 Oct */
	public static final String DSI_MONTH_26Sep_25Oct = "26 Sep- 25 Oct";
	/** Set Month.
		@param DSI_Month Month	  */
	public void setDSI_Month (String DSI_Month)
	{

		set_Value (COLUMNNAME_DSI_Month, DSI_Month);
	}

	/** Get Month.
		@return Month	  */
	public String getDSI_Month () 
	{
		return (String)get_Value(COLUMNNAME_DSI_Month);
	}

	/** Batch Payment = BP */
	public static final String DS_PAYMENTTYPE_BatchPayment = "BP";
	/** Individual Payments = IP */
	public static final String DS_PAYMENTTYPE_IndividualPayments = "IP";
	/** Salary Payment = SP */
	public static final String DS_PAYMENTTYPE_SalaryPayment = "SP";
	/** Set Payment Type.
		@param DS_PaymentType Payment Type	  */
	public void setDS_PaymentType (String DS_PaymentType)
	{

		set_Value (COLUMNNAME_DS_PaymentType, DS_PaymentType);
	}

	/** Get Payment Type.
		@return Payment Type	  */
	public String getDS_PaymentType () 
	{
		return (String)get_Value(COLUMNNAME_DS_PaymentType);
	}

	/** Designer Shaik = DS */
	public static final String ORGNAME_DesignerShaik = "DS";
	/** Media Creative Village = MCV */
	public static final String ORGNAME_MediaCreativeVillage = "MCV";
	/** Set Organization Name.
		@param OrgName 
		Name of the Organization
	  */
	public void setOrgName (String OrgName)
	{

		set_ValueNoCheck (COLUMNNAME_OrgName, OrgName);
	}

	/** Get Organization Name.
		@return Name of the Organization
	  */
	public String getOrgName () 
	{
		return (String)get_Value(COLUMNNAME_OrgName);
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

	/** Set Send.
		@param SendIt Send	  */
	public void setSendIt (String SendIt)
	{
		set_Value (COLUMNNAME_SendIt, SendIt);
	}

	/** Get Send.
		@return Send	  */
	public String getSendIt () 
	{
		return (String)get_Value(COLUMNNAME_SendIt);
	}

	/** Set Transfer Time.
		@param TransfertTime Transfer Time	  */
	public void setTransfertTime (Timestamp TransfertTime)
	{
		set_Value (COLUMNNAME_TransfertTime, TransfertTime);
	}

	/** Get Transfer Time.
		@return Transfer Time	  */
	public Timestamp getTransfertTime () 
	{
		return (Timestamp)get_Value(COLUMNNAME_TransfertTime);
	}
}