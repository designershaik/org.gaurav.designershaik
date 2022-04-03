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
import org.compiere.util.KeyNamePair;

/** Generated Model for DSI_SalesForecast
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_DSI_SalesForecast extends PO implements I_DSI_SalesForecast, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211115L;

    /** Standard Constructor */
    public X_DSI_SalesForecast (Properties ctx, int DSI_SalesForecast_ID, String trxName)
    {
      super (ctx, DSI_SalesForecast_ID, trxName);
      /** if (DSI_SalesForecast_ID == 0)
        {
			setDSI_SalesForecast_ID (0);
			setDSI_SalesForecast_UU (null);
        } */
    }

    /** Load Constructor */
    public X_DSI_SalesForecast (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_SalesForecast[")
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

	public I_DSI_SalesForecastMonth getDSI_SalesForecastMonth() throws RuntimeException
    {
		return (I_DSI_SalesForecastMonth)MTable.get(getCtx(), I_DSI_SalesForecastMonth.Table_Name)
			.getPO(getDSI_SalesForecastMonth_ID(), get_TrxName());	}

	/** Set DSI_SalesForecastMonth.
		@param DSI_SalesForecastMonth_ID DSI_SalesForecastMonth	  */
	public void setDSI_SalesForecastMonth_ID (int DSI_SalesForecastMonth_ID)
	{
		if (DSI_SalesForecastMonth_ID < 1) 
			set_Value (COLUMNNAME_DSI_SalesForecastMonth_ID, null);
		else 
			set_Value (COLUMNNAME_DSI_SalesForecastMonth_ID, Integer.valueOf(DSI_SalesForecastMonth_ID));
	}

	/** Get DSI_SalesForecastMonth.
		@return DSI_SalesForecastMonth	  */
	public int getDSI_SalesForecastMonth_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_SalesForecastMonth_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_SalesForecast_ID.
		@param DSI_SalesForecast_ID DSI_SalesForecast_ID	  */
	public void setDSI_SalesForecast_ID (int DSI_SalesForecast_ID)
	{
		if (DSI_SalesForecast_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_SalesForecast_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_SalesForecast_ID, Integer.valueOf(DSI_SalesForecast_ID));
	}

	/** Get DSI_SalesForecast_ID.
		@return DSI_SalesForecast_ID	  */
	public int getDSI_SalesForecast_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_SalesForecast_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_SalesForecast_UU.
		@param DSI_SalesForecast_UU DSI_SalesForecast_UU	  */
	public void setDSI_SalesForecast_UU (String DSI_SalesForecast_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DSI_SalesForecast_UU, DSI_SalesForecast_UU);
	}

	/** Get DSI_SalesForecast_UU.
		@return DSI_SalesForecast_UU	  */
	public String getDSI_SalesForecast_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_SalesForecast_UU);
	}

	/** Set Document Date.
		@param DateDoc 
		Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc)
	{
		set_Value (COLUMNNAME_DateDoc, DateDoc);
	}

	/** Get Document Date.
		@return Date of the Document
	  */
	public Timestamp getDateDoc () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateDoc);
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

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_Product_ID()));
    }

	/** Set Quantity Manufactured.
		@param QtyManufactured Quantity Manufactured	  */
	public void setQtyManufactured (BigDecimal QtyManufactured)
	{
		set_Value (COLUMNNAME_QtyManufactured, QtyManufactured);
	}

	/** Get Quantity Manufactured.
		@return Quantity Manufactured	  */
	public BigDecimal getQtyManufactured () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyManufactured);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity Manufactured To Pending.
		@param QtyManufacturedToPending Quantity Manufactured To Pending	  */
	public void setQtyManufacturedToPending (BigDecimal QtyManufacturedToPending)
	{
		set_Value (COLUMNNAME_QtyManufacturedToPending, QtyManufacturedToPending);
	}

	/** Get Quantity Manufactured To Pending.
		@return Quantity Manufactured To Pending	  */
	public BigDecimal getQtyManufacturedToPending () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyManufacturedToPending);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Quantity to Order.
		@param QtyToOrder Quantity to Order	  */
	public void setQtyToOrder (BigDecimal QtyToOrder)
	{
		set_Value (COLUMNNAME_QtyToOrder, QtyToOrder);
	}

	/** Get Quantity to Order.
		@return Quantity to Order	  */
	public BigDecimal getQtyToOrder () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyToOrder);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}