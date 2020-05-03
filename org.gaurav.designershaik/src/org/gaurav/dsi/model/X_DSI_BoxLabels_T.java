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

/** Generated Model for DSI_BoxLabels_T
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_BoxLabels_T extends PO implements I_DSI_BoxLabels_T, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20180108L;

    /** Standard Constructor */
    public X_DSI_BoxLabels_T (Properties ctx, int DSI_BoxLabels_T_ID, String trxName)
    {
      super (ctx, DSI_BoxLabels_T_ID, trxName);
      /** if (DSI_BoxLabels_T_ID == 0)
        {
			setDSI_BoxLabels_T_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_BoxLabels_T (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_BoxLabels_T[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Print Box Labels.
		@param DSI_BoxLabels_T_ID Print Box Labels	  */
	public void setDSI_BoxLabels_T_ID (int DSI_BoxLabels_T_ID)
	{
		if (DSI_BoxLabels_T_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_BoxLabels_T_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_BoxLabels_T_ID, Integer.valueOf(DSI_BoxLabels_T_ID));
	}

	/** Get Print Box Labels.
		@return Print Box Labels	  */
	public int getDSI_BoxLabels_T_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_BoxLabels_T_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_BoxLabels_T_UU.
		@param DSI_BoxLabels_T_UU DSI_BoxLabels_T_UU	  */
	public void setDSI_BoxLabels_T_UU (String DSI_BoxLabels_T_UU)
	{
		set_Value (COLUMNNAME_DSI_BoxLabels_T_UU, DSI_BoxLabels_T_UU);
	}

	/** Get DSI_BoxLabels_T_UU.
		@return DSI_BoxLabels_T_UU	  */
	public String getDSI_BoxLabels_T_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_BoxLabels_T_UU);
	}

	/** Set Box No.
		@param DSI_BoxNo Box No	  */
	public void setDSI_BoxNo (int DSI_BoxNo)
	{
		set_Value (COLUMNNAME_DSI_BoxNo, Integer.valueOf(DSI_BoxNo));
	}

	/** Get Box No.
		@return Box No	  */
	public int getDSI_BoxNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_BoxNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
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
}