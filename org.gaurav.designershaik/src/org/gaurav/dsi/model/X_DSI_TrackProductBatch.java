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

/** Generated Model for DSI_TrackProductBatch
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DSI_TrackProductBatch extends PO implements I_DSI_TrackProductBatch, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181007L;

    /** Standard Constructor */
    public X_DSI_TrackProductBatch (Properties ctx, int DSI_TrackProductBatch_ID, String trxName)
    {
      super (ctx, DSI_TrackProductBatch_ID, trxName);
      /** if (DSI_TrackProductBatch_ID == 0)
        {
			setDSI_TrackProductBatch_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_TrackProductBatch (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_TrackProductBatch[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Batch Description.
		@param BatchDescription 
		Description of the Batch
	  */
	public void setBatchDescription (String BatchDescription)
	{
		set_Value (COLUMNNAME_BatchDescription, BatchDescription);
	}

	/** Get Batch Description.
		@return Description of the Batch
	  */
	public String getBatchDescription () 
	{
		return (String)get_Value(COLUMNNAME_BatchDescription);
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_Value (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_Value (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Track Product Batch.
		@param DSI_TrackProductBatch_ID Track Product Batch	  */
	public void setDSI_TrackProductBatch_ID (int DSI_TrackProductBatch_ID)
	{
		if (DSI_TrackProductBatch_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_TrackProductBatch_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_TrackProductBatch_ID, Integer.valueOf(DSI_TrackProductBatch_ID));
	}

	/** Get Track Product Batch.
		@return Track Product Batch	  */
	public int getDSI_TrackProductBatch_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_TrackProductBatch_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_TrackProductBatch_UU.
		@param DSI_TrackProductBatch_UU DSI_TrackProductBatch_UU	  */
	public void setDSI_TrackProductBatch_UU (String DSI_TrackProductBatch_UU)
	{
		set_Value (COLUMNNAME_DSI_TrackProductBatch_UU, DSI_TrackProductBatch_UU);
	}

	/** Get DSI_TrackProductBatch_UU.
		@return DSI_TrackProductBatch_UU	  */
	public String getDSI_TrackProductBatch_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_TrackProductBatch_UU);
	}

	/** Set Sr No Composed.
		@param DS_SerialNumberFinal Sr No Composed	  */
	public void setDS_SerialNumberFinal (String DS_SerialNumberFinal)
	{
		set_Value (COLUMNNAME_DS_SerialNumberFinal, DS_SerialNumberFinal);
	}

	/** Get Sr No Composed.
		@return Sr No Composed	  */
	public String getDS_SerialNumberFinal () 
	{
		return (String)get_Value(COLUMNNAME_DS_SerialNumberFinal);
	}

	/** Set Low Level.
		@param LowLevel 
		The Low Level is used to calculate the material plan and determines if a net requirement should be exploited
	  */
	public void setLowLevel (int LowLevel)
	{
		set_Value (COLUMNNAME_LowLevel, Integer.valueOf(LowLevel));
	}

	/** Get Low Level.
		@return The Low Level is used to calculate the material plan and determines if a net requirement should be exploited
	  */
	public int getLowLevel () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LowLevel);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Lot/Sr No..
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Lot/Sr No..
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_InOutLine getM_InOutLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOutLine)MTable.get(getCtx(), org.compiere.model.I_M_InOutLine.Table_Name)
			.getPO(getM_InOutLine_ID(), get_TrxName());	}

	/** Set Shipment/Receipt Line.
		@param M_InOutLine_ID 
		Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		if (M_InOutLine_ID < 1) 
			set_Value (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
	}

	/** Get Shipment/Receipt Line.
		@return Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOutLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Movement Date.
		@param MovementDate 
		Date a product was moved in or out of inventory
	  */
	public void setMovementDate (Timestamp MovementDate)
	{
		set_Value (COLUMNNAME_MovementDate, MovementDate);
	}

	/** Get Movement Date.
		@return Date a product was moved in or out of inventory
	  */
	public Timestamp getMovementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MovementDate);
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

	public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Order)MTable.get(getCtx(), org.eevolution.model.I_PP_Order.Table_Name)
			.getPO(getPP_Order_ID(), get_TrxName());	}

	/** Set Manufacturing Order.
		@param PP_Order_ID 
		Manufacturing Order
	  */
	public void setPP_Order_ID (int PP_Order_ID)
	{
		if (PP_Order_ID < 1) 
			set_Value (COLUMNNAME_PP_Order_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Order_ID, Integer.valueOf(PP_Order_ID));
	}

	/** Get Manufacturing Order.
		@return Manufacturing Order
	  */
	public int getPP_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}