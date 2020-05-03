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

/** Generated Model for DSI_SrNo_Product
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_SrNo_Product extends PO implements I_DSI_SrNo_Product, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_SrNo_Product (Properties ctx, int DSI_SrNo_Product_ID, String trxName)
    {
      super (ctx, DSI_SrNo_Product_ID, trxName);
      /** if (DSI_SrNo_Product_ID == 0)
        {
			setDSI_SerialNumberMaster_ID (0);
			setDSI_SrNo_Product_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_SrNo_Product (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_SrNo_Product[")
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

	public I_DSI_SerialNumberMaster getDSI_SerialNumberMaster() throws RuntimeException
    {
		return (I_DSI_SerialNumberMaster)MTable.get(getCtx(), I_DSI_SerialNumberMaster.Table_Name)
			.getPO(getDSI_SerialNumberMaster_ID(), get_TrxName());	}

	/** Set Serial Number Master .
		@param DSI_SerialNumberMaster_ID Serial Number Master 	  */
	public void setDSI_SerialNumberMaster_ID (int DSI_SerialNumberMaster_ID)
	{
		if (DSI_SerialNumberMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_SerialNumberMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_SerialNumberMaster_ID, Integer.valueOf(DSI_SerialNumberMaster_ID));
	}

	/** Get Serial Number Master .
		@return Serial Number Master 	  */
	public int getDSI_SerialNumberMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_SerialNumberMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Products assigned to serial numbers.
		@param DSI_SrNo_Product_ID Products assigned to serial numbers	  */
	public void setDSI_SrNo_Product_ID (int DSI_SrNo_Product_ID)
	{
		if (DSI_SrNo_Product_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_SrNo_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_SrNo_Product_ID, Integer.valueOf(DSI_SrNo_Product_ID));
	}

	/** Get Products assigned to serial numbers.
		@return Products assigned to serial numbers	  */
	public int getDSI_SrNo_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_SrNo_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_SrNo_Product_UU.
		@param DSI_SrNo_Product_UU DSI_SrNo_Product_UU	  */
	public void setDSI_SrNo_Product_UU (String DSI_SrNo_Product_UU)
	{
		set_Value (COLUMNNAME_DSI_SrNo_Product_UU, DSI_SrNo_Product_UU);
	}

	/** Get DSI_SrNo_Product_UU.
		@return DSI_SrNo_Product_UU	  */
	public String getDSI_SrNo_Product_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_SrNo_Product_UU);
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