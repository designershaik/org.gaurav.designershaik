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
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DSI_Refill_PrintForm
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_Refill_PrintForm extends PO implements I_DSI_Refill_PrintForm, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_Refill_PrintForm (Properties ctx, int DSI_Refill_PrintForm_ID, String trxName)
    {
      super (ctx, DSI_Refill_PrintForm_ID, trxName);
      /** if (DSI_Refill_PrintForm_ID == 0)
        {
			setDSI_Refill_PrintForm_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_Refill_PrintForm (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_Refill_PrintForm[")
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

	/** Set First Record ID.
		@param DSI_FirstID First Record ID	  */
	public void setDSI_FirstID (int DSI_FirstID)
	{
		set_Value (COLUMNNAME_DSI_FirstID, Integer.valueOf(DSI_FirstID));
	}

	/** Get First Record ID.
		@return First Record ID	  */
	public int getDSI_FirstID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_FirstID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Generate Box Labels.
		@param DSI_Print Generate Box Labels	  */
	public void setDSI_Print (String DSI_Print)
	{
		set_Value (COLUMNNAME_DSI_Print, DSI_Print);
	}

	/** Get Generate Box Labels.
		@return Generate Box Labels	  */
	public String getDSI_Print () 
	{
		return (String)get_Value(COLUMNNAME_DSI_Print);
	}

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

	/** Set DSI_Refill_PrintForm_UU.
		@param DSI_Refill_PrintForm_UU DSI_Refill_PrintForm_UU	  */
	public void setDSI_Refill_PrintForm_UU (String DSI_Refill_PrintForm_UU)
	{
		set_Value (COLUMNNAME_DSI_Refill_PrintForm_UU, DSI_Refill_PrintForm_UU);
	}

	/** Get DSI_Refill_PrintForm_UU.
		@return DSI_Refill_PrintForm_UU	  */
	public String getDSI_Refill_PrintForm_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_Refill_PrintForm_UU);
	}

	/** Set Printed.
		@param IsPrinted 
		Indicates if this document / line is printed
	  */
	public void setIsPrinted (boolean IsPrinted)
	{
		set_ValueNoCheck (COLUMNNAME_IsPrinted, Boolean.valueOf(IsPrinted));
	}

	/** Get Printed.
		@return Indicates if this document / line is printed
	  */
	public boolean isPrinted () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinted);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Value.
		@param ValueNumber 
		Numeric Value
	  */
	public void setValueNumber (BigDecimal ValueNumber)
	{
		set_Value (COLUMNNAME_ValueNumber, ValueNumber);
	}

	/** Get Value.
		@return Numeric Value
	  */
	public BigDecimal getValueNumber () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ValueNumber);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}