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

/** Generated Model for DS_POSHeader
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_POSHeader extends PO implements I_DS_POSHeader, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190630L;

    /** Standard Constructor */
    public X_DS_POSHeader (Properties ctx, int DS_POSHeader_ID, String trxName)
    {
      super (ctx, DS_POSHeader_ID, trxName);
      /** if (DS_POSHeader_ID == 0)
        {
			setDS_POSHeader_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_POSHeader (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_POSHeader[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set BP Name.
		@param BPName BP Name	  */
	public void setBPName (String BPName)
	{
		set_Value (COLUMNNAME_BPName, BPName);
	}

	/** Get BP Name.
		@return BP Name	  */
	public String getBPName () 
	{
		return (String)get_Value(COLUMNNAME_BPName);
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
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
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

	public org.compiere.model.I_C_OrderSource getC_OrderSource() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderSource)MTable.get(getCtx(), org.compiere.model.I_C_OrderSource.Table_Name)
			.getPO(getC_OrderSource_ID(), get_TrxName());	}

	/** Set Order Source.
		@param C_OrderSource_ID Order Source	  */
	public void setC_OrderSource_ID (int C_OrderSource_ID)
	{
		if (C_OrderSource_ID < 1) 
			set_Value (COLUMNNAME_C_OrderSource_ID, null);
		else 
			set_Value (COLUMNNAME_C_OrderSource_ID, Integer.valueOf(C_OrderSource_ID));
	}

	/** Get Order Source.
		@return Order Source	  */
	public int getC_OrderSource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderSource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Order Source Key.
		@param C_OrderSourceValue Order Source Key	  */
	public void setC_OrderSourceValue (String C_OrderSourceValue)
	{
		set_Value (COLUMNNAME_C_OrderSourceValue, C_OrderSourceValue);
	}

	/** Get Order Source Key.
		@return Order Source Key	  */
	public String getC_OrderSourceValue () 
	{
		return (String)get_Value(COLUMNNAME_C_OrderSourceValue);
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

	/** Set POS Header.
		@param DS_POSHeader_ID POS Header	  */
	public void setDS_POSHeader_ID (int DS_POSHeader_ID)
	{
		if (DS_POSHeader_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_POSHeader_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_POSHeader_ID, Integer.valueOf(DS_POSHeader_ID));
	}

	/** Get POS Header.
		@return POS Header	  */
	public int getDS_POSHeader_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_POSHeader_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_POSHeader_UU.
		@param DS_POSHeader_UU DS_POSHeader_UU	  */
	public void setDS_POSHeader_UU (String DS_POSHeader_UU)
	{
		set_Value (COLUMNNAME_DS_POSHeader_UU, DS_POSHeader_UU);
	}

	/** Get DS_POSHeader_UU.
		@return DS_POSHeader_UU	  */
	public String getDS_POSHeader_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_POSHeader_UU);
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

	/** Set Mobile No./LandLine 2.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get Mobile No./LandLine 2.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
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

	public org.compiere.model.I_AD_User getSalesRep() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getSalesRep_ID(), get_TrxName());	}

	/** Set Sales Representative.
		@param SalesRep_ID 
		Sales Representative or Company Agent
	  */
	public void setSalesRep_ID (int SalesRep_ID)
	{
		if (SalesRep_ID < 1) 
			set_Value (COLUMNNAME_SalesRep_ID, null);
		else 
			set_Value (COLUMNNAME_SalesRep_ID, Integer.valueOf(SalesRep_ID));
	}

	/** Get Sales Representative.
		@return Sales Representative or Company Agent
	  */
	public int getSalesRep_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SalesRep_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** TenderType AD_Reference_ID=214 */
	public static final int TENDERTYPE_AD_Reference_ID=214;
	/** Credit Card = C */
	public static final String TENDERTYPE_CreditCard = "C";
	/** Check = K */
	public static final String TENDERTYPE_Check = "K";
	/** Direct Deposit = A */
	public static final String TENDERTYPE_DirectDeposit = "A";
	/** Direct Debit = D */
	public static final String TENDERTYPE_DirectDebit = "D";
	/** Account = T */
	public static final String TENDERTYPE_Account = "T";
	/** Cash = X */
	public static final String TENDERTYPE_Cash = "X";
	/** Set Tender type.
		@param TenderType 
		Method of Payment
	  */
	public void setTenderType (String TenderType)
	{

		set_Value (COLUMNNAME_TenderType, TenderType);
	}

	/** Get Tender type.
		@return Method of Payment
	  */
	public String getTenderType () 
	{
		return (String)get_Value(COLUMNNAME_TenderType);
	}
}