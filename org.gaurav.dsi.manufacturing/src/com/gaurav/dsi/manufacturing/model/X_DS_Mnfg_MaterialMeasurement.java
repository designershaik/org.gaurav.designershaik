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
package com.gaurav.dsi.manufacturing.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DS_Mnfg_MaterialMeasurement
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_Mnfg_MaterialMeasurement extends PO implements I_DS_Mnfg_MaterialMeasurement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190521L;

    /** Standard Constructor */
    public X_DS_Mnfg_MaterialMeasurement (Properties ctx, int DS_Mnfg_MaterialMeasurement_ID, String trxName)
    {
      super (ctx, DS_Mnfg_MaterialMeasurement_ID, trxName);
      /** if (DS_Mnfg_MaterialMeasurement_ID == 0)
        {
			setDS_Mnfg_MaterialMeasurement_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Mnfg_MaterialMeasurement (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Mnfg_MaterialMeasurement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Container Weight.
		@param DS_ContainerWeight 
		Container Weight
	  */
	public void setDS_ContainerWeight (BigDecimal DS_ContainerWeight)
	{
		set_Value (COLUMNNAME_DS_ContainerWeight, DS_ContainerWeight);
	}

	/** Get Container Weight.
		@return Container Weight
	  */
	public BigDecimal getDS_ContainerWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_ContainerWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Gross Weight.
		@param DS_GrossWeight 
		Gross Weight
	  */
	public void setDS_GrossWeight (BigDecimal DS_GrossWeight)
	{
		set_Value (COLUMNNAME_DS_GrossWeight, DS_GrossWeight);
	}

	/** Get Gross Weight.
		@return Gross Weight
	  */
	public BigDecimal getDS_GrossWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_GrossWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Material Measurement.
		@param DS_Mnfg_MaterialMeasurement_ID Material Measurement	  */
	public void setDS_Mnfg_MaterialMeasurement_ID (int DS_Mnfg_MaterialMeasurement_ID)
	{
		if (DS_Mnfg_MaterialMeasurement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Mnfg_MaterialMeasurement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Mnfg_MaterialMeasurement_ID, Integer.valueOf(DS_Mnfg_MaterialMeasurement_ID));
	}

	/** Get Material Measurement.
		@return Material Measurement	  */
	public int getDS_Mnfg_MaterialMeasurement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Mnfg_MaterialMeasurement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Mnfg_MaterialMeasurement_UU.
		@param DS_Mnfg_MaterialMeasurement_UU DS_Mnfg_MaterialMeasurement_UU	  */
	public void setDS_Mnfg_MaterialMeasurement_UU (String DS_Mnfg_MaterialMeasurement_UU)
	{
		set_Value (COLUMNNAME_DS_Mnfg_MaterialMeasurement_UU, DS_Mnfg_MaterialMeasurement_UU);
	}

	/** Get DS_Mnfg_MaterialMeasurement_UU.
		@return DS_Mnfg_MaterialMeasurement_UU	  */
	public String getDS_Mnfg_MaterialMeasurement_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Mnfg_MaterialMeasurement_UU);
	}

	/** Set Net Weight.
		@param DS_NetWeight 
		Net Weight
	  */
	public void setDS_NetWeight (BigDecimal DS_NetWeight)
	{
		set_Value (COLUMNNAME_DS_NetWeight, DS_NetWeight);
	}

	/** Get Net Weight.
		@return Net Weight
	  */
	public BigDecimal getDS_NetWeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_NetWeight);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.eevolution.model.I_PP_Order_BOMLine getPP_Order_BOMLine() throws RuntimeException
    {
		return (org.eevolution.model.I_PP_Order_BOMLine)MTable.get(getCtx(), org.eevolution.model.I_PP_Order_BOMLine.Table_Name)
			.getPO(getPP_Order_BOMLine_ID(), get_TrxName());	}

	/** Set Manufacturing Order BOM Line.
		@param PP_Order_BOMLine_ID Manufacturing Order BOM Line	  */
	public void setPP_Order_BOMLine_ID (int PP_Order_BOMLine_ID)
	{
		if (PP_Order_BOMLine_ID < 1) 
			set_Value (COLUMNNAME_PP_Order_BOMLine_ID, null);
		else 
			set_Value (COLUMNNAME_PP_Order_BOMLine_ID, Integer.valueOf(PP_Order_BOMLine_ID));
	}

	/** Get Manufacturing Order BOM Line.
		@return Manufacturing Order BOM Line	  */
	public int getPP_Order_BOMLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_PP_Order_BOMLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}