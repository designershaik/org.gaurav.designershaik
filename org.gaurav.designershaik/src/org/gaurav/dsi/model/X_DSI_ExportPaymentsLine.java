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
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DSI_ExportPaymentsLine
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_ExportPaymentsLine extends PO implements I_DSI_ExportPaymentsLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_ExportPaymentsLine (Properties ctx, int DSI_ExportPaymentsLine_ID, String trxName)
    {
      super (ctx, DSI_ExportPaymentsLine_ID, trxName);
      /** if (DSI_ExportPaymentsLine_ID == 0)
        {
			setDSI_ExportPayments_ID (0);
			setDSI_ExportPaymentsLine_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_ExportPaymentsLine (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_ExportPaymentsLine[")
        .append(get_ID()).append("]");
      return sb.toString();
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
		throw new IllegalArgumentException ("C_BPartner_ID is virtual column");	}

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
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Payment_ID, Integer.valueOf(C_Payment_ID));
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

	public I_DSI_ExportPayments getDSI_ExportPayments() throws RuntimeException
    {
		return (I_DSI_ExportPayments)MTable.get(getCtx(), I_DSI_ExportPayments.Table_Name)
			.getPO(getDSI_ExportPayments_ID(), get_TrxName());	}

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

	/** Set Batch Line to Export Payments.
		@param DSI_ExportPaymentsLine_ID Batch Line to Export Payments	  */
	public void setDSI_ExportPaymentsLine_ID (int DSI_ExportPaymentsLine_ID)
	{
		if (DSI_ExportPaymentsLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_ExportPaymentsLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_ExportPaymentsLine_ID, Integer.valueOf(DSI_ExportPaymentsLine_ID));
	}

	/** Get Batch Line to Export Payments.
		@return Batch Line to Export Payments	  */
	public int getDSI_ExportPaymentsLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_ExportPaymentsLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_ExportPaymentsLine_UU.
		@param DSI_ExportPaymentsLine_UU DSI_ExportPaymentsLine_UU	  */
	public void setDSI_ExportPaymentsLine_UU (String DSI_ExportPaymentsLine_UU)
	{
		set_Value (COLUMNNAME_DSI_ExportPaymentsLine_UU, DSI_ExportPaymentsLine_UU);
	}

	/** Get DSI_ExportPaymentsLine_UU.
		@return DSI_ExportPaymentsLine_UU	  */
	public String getDSI_ExportPaymentsLine_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_ExportPaymentsLine_UU);
	}

	/** Set Payment Description.
		@param DS_PaymentDescription Payment Description	  */
	public void setDS_PaymentDescription (String DS_PaymentDescription)
	{
		throw new IllegalArgumentException ("DS_PaymentDescription is virtual column");	}

	/** Get Payment Description.
		@return Payment Description	  */
	public String getDS_PaymentDescription () 
	{
		return (String)get_Value(COLUMNNAME_DS_PaymentDescription);
	}

	/** Set Line.
		@param LineNo 
		Line No
	  */
	public void setLineNo (int LineNo)
	{
		set_Value (COLUMNNAME_LineNo, Integer.valueOf(LineNo));
	}

	/** Get Line.
		@return Line No
	  */
	public int getLineNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LineNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Payment amount.
		@param PayAmt 
		Amount being paid
	  */
	public void setPayAmt (String PayAmt)
	{
		throw new IllegalArgumentException ("PayAmt is virtual column");	}

	/** Get Payment amount.
		@return Amount being paid
	  */
	public String getPayAmt () 
	{
		return (String)get_Value(COLUMNNAME_PayAmt);
	}
}