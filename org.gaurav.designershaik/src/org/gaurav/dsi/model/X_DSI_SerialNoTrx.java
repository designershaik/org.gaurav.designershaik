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

/** Generated Model for DSI_SerialNoTrx
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DSI_SerialNoTrx extends PO implements I_DSI_SerialNoTrx, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20181007L;

    /** Standard Constructor */
    public X_DSI_SerialNoTrx (Properties ctx, int DSI_SerialNoTrx_ID, String trxName)
    {
      super (ctx, DSI_SerialNoTrx_ID, trxName);
      /** if (DSI_SerialNoTrx_ID == 0)
        {
			setDSI_SerialNoTrx_ID (0);
			setM_Locator_ID (0);
			setM_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_SerialNoTrx (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_SerialNoTrx[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Serial Number Transaction.
		@param DSI_SerialNoTrx_ID Serial Number Transaction	  */
	public void setDSI_SerialNoTrx_ID (int DSI_SerialNoTrx_ID)
	{
		if (DSI_SerialNoTrx_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_SerialNoTrx_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_SerialNoTrx_ID, Integer.valueOf(DSI_SerialNoTrx_ID));
	}

	/** Get Serial Number Transaction.
		@return Serial Number Transaction	  */
	public int getDSI_SerialNoTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_SerialNoTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_SerialNoTrx_UU.
		@param DSI_SerialNoTrx_UU DSI_SerialNoTrx_UU	  */
	public void setDSI_SerialNoTrx_UU (String DSI_SerialNoTrx_UU)
	{
		set_Value (COLUMNNAME_DSI_SerialNoTrx_UU, DSI_SerialNoTrx_UU);
	}

	/** Get DSI_SerialNoTrx_UU.
		@return DSI_SerialNoTrx_UU	  */
	public String getDSI_SerialNoTrx_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_SerialNoTrx_UU);
	}

	/** Set Serial No..
		@param DSI_SrNo Serial No.	  */
	public void setDSI_SrNo (int DSI_SrNo)
	{
		set_Value (COLUMNNAME_DSI_SrNo, Integer.valueOf(DSI_SrNo));
	}

	/** Get Serial No..
		@return Serial No.	  */
	public int getDSI_SrNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_SrNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Old Format.
		@param DS_OldSerialNumberFormat Is Old Format	  */
	public void setDS_OldSerialNumberFormat (boolean DS_OldSerialNumberFormat)
	{
		set_Value (COLUMNNAME_DS_OldSerialNumberFormat, Boolean.valueOf(DS_OldSerialNumberFormat));
	}

	/** Get Is Old Format.
		@return Is Old Format	  */
	public boolean isDS_OldSerialNumberFormat () 
	{
		Object oo = get_Value(COLUMNNAME_DS_OldSerialNumberFormat);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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
			set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
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

	public org.compiere.model.I_M_InventoryLine getM_InventoryLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_InventoryLine)MTable.get(getCtx(), org.compiere.model.I_M_InventoryLine.Table_Name)
			.getPO(getM_InventoryLine_ID(), get_TrxName());	}

	/** Set Phys.Inventory Line.
		@param M_InventoryLine_ID 
		Unique line in an Inventory document
	  */
	public void setM_InventoryLine_ID (int M_InventoryLine_ID)
	{
		if (M_InventoryLine_ID < 1) 
			set_Value (COLUMNNAME_M_InventoryLine_ID, null);
		else 
			set_Value (COLUMNNAME_M_InventoryLine_ID, Integer.valueOf(M_InventoryLine_ID));
	}

	/** Get Phys.Inventory Line.
		@return Unique line in an Inventory document
	  */
	public int getM_InventoryLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InventoryLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException
    {
		return (org.compiere.model.I_M_Locator)MTable.get(getCtx(), org.compiere.model.I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_MovementLine getM_MovementLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_MovementLine)MTable.get(getCtx(), org.compiere.model.I_M_MovementLine.Table_Name)
			.getPO(getM_MovementLine_ID(), get_TrxName());	}

	/** Set Move Line.
		@param M_MovementLine_ID 
		Inventory Move document Line
	  */
	public void setM_MovementLine_ID (int M_MovementLine_ID)
	{
		if (M_MovementLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_MovementLine_ID, Integer.valueOf(M_MovementLine_ID));
	}

	/** Get Move Line.
		@return Inventory Move document Line
	  */
	public int getM_MovementLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_MovementLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_M_Production getM_Production() throws RuntimeException
    {
		return (org.compiere.model.I_M_Production)MTable.get(getCtx(), org.compiere.model.I_M_Production.Table_Name)
			.getPO(getM_Production_ID(), get_TrxName());	}

	/** Set Production.
		@param M_Production_ID 
		Plan for producing a product
	  */
	public void setM_Production_ID (int M_Production_ID)
	{
		if (M_Production_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Production_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Production_ID, Integer.valueOf(M_Production_ID));
	}

	/** Get Production.
		@return Plan for producing a product
	  */
	public int getM_Production_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Production_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_ProductionLine getM_ProductionLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_ProductionLine)MTable.get(getCtx(), org.compiere.model.I_M_ProductionLine.Table_Name)
			.getPO(getM_ProductionLine_ID(), get_TrxName());	}

	/** Set Production Line.
		@param M_ProductionLine_ID 
		Document Line representing a production
	  */
	public void setM_ProductionLine_ID (int M_ProductionLine_ID)
	{
		if (M_ProductionLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_ProductionLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_ProductionLine_ID, Integer.valueOf(M_ProductionLine_ID));
	}

	/** Get Production Line.
		@return Document Line representing a production
	  */
	public int getM_ProductionLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductionLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_ProductionPlan getM_ProductionPlan() throws RuntimeException
    {
		return (org.compiere.model.I_M_ProductionPlan)MTable.get(getCtx(), org.compiere.model.I_M_ProductionPlan.Table_Name)
			.getPO(getM_ProductionPlan_ID(), get_TrxName());	}

	/** Set Production Plan.
		@param M_ProductionPlan_ID 
		Plan for how a product is produced
	  */
	public void setM_ProductionPlan_ID (int M_ProductionPlan_ID)
	{
		if (M_ProductionPlan_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_ProductionPlan_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_ProductionPlan_ID, Integer.valueOf(M_ProductionPlan_ID));
	}

	/** Get Production Plan.
		@return Plan for how a product is produced
	  */
	public int getM_ProductionPlan_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_ProductionPlan_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_RMA getM_RMA() throws RuntimeException
    {
		return (org.compiere.model.I_M_RMA)MTable.get(getCtx(), org.compiere.model.I_M_RMA.Table_Name)
			.getPO(getM_RMA_ID(), get_TrxName());	}

	/** Set RMA.
		@param M_RMA_ID 
		Return Material Authorization
	  */
	public void setM_RMA_ID (int M_RMA_ID)
	{
		if (M_RMA_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_RMA_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_RMA_ID, Integer.valueOf(M_RMA_ID));
	}

	/** Get RMA.
		@return Return Material Authorization
	  */
	public int getM_RMA_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_RMA_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_PP_Cost_Collector getPP_Cost_Collector() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Cost_Collector)MTable.get(getCtx(), org.eevolution.model.I_PP_Cost_Collector.Table_Name)
			.getPO(getPP_Cost_Collector_ID(), get_TrxName());	}

	/** Set Manufacturing Cost Collector.
		@param PP_Cost_Collector_ID Manufacturing Cost Collector	  */
	public void setPP_Cost_Collector_ID (int PP_Cost_Collector_ID)
	{
		if (PP_Cost_Collector_ID < 1) 
			set_Value (COLUMNNAME_PP_Cost_Collector_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Cost_Collector_ID, Integer.valueOf(PP_Cost_Collector_ID));
	}

	/** Get Manufacturing Cost Collector.
		@return Manufacturing Cost Collector	  */
	public int getPP_Cost_Collector_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Cost_Collector_ID);
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
			set_ValueNoCheck (COLUMNNAME_PP_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_PP_Order_ID, Integer.valueOf(PP_Order_ID));
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

	/** Set Prefix.
		@param Prefix 
		Prefix before the sequence number
	  */
	public void setPrefix (String Prefix)
	{
		set_Value (COLUMNNAME_Prefix, Prefix);
	}

	/** Get Prefix.
		@return Prefix before the sequence number
	  */
	public String getPrefix () 
	{
		return (String)get_Value(COLUMNNAME_Prefix);
	}

	/** Set Quantity.
		@param Qty 
		Quantity
	  */
	public void setQty (BigDecimal Qty)
	{
		set_Value (COLUMNNAME_Qty, Qty);
	}

	/** Get Quantity.
		@return Quantity
	  */
	public BigDecimal getQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Qty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Suffix.
		@param Suffix 
		Suffix after the number
	  */
	public void setSuffix (String Suffix)
	{
		set_Value (COLUMNNAME_Suffix, Suffix);
	}

	/** Get Suffix.
		@return Suffix after the number
	  */
	public String getSuffix () 
	{
		return (String)get_Value(COLUMNNAME_Suffix);
	}
}