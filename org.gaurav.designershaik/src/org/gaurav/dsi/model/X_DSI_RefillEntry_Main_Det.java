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

/** Generated Model for DSI_RefillEntry_Main_Det
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_RefillEntry_Main_Det extends PO implements I_DSI_RefillEntry_Main_Det, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_RefillEntry_Main_Det (Properties ctx, int DSI_RefillEntry_Main_Det_ID, String trxName)
    {
      super (ctx, DSI_RefillEntry_Main_Det_ID, trxName);
      /** if (DSI_RefillEntry_Main_Det_ID == 0)
        {
			setDSI_RefillEntry_Main_Det_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_RefillEntry_Main_Det (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_RefillEntry_Main_Det[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Details about refill form.
		@param DSI_RefillEntry_Main_Det_ID Details about refill form	  */
	public void setDSI_RefillEntry_Main_Det_ID (int DSI_RefillEntry_Main_Det_ID)
	{
		if (DSI_RefillEntry_Main_Det_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_RefillEntry_Main_Det_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_RefillEntry_Main_Det_ID, Integer.valueOf(DSI_RefillEntry_Main_Det_ID));
	}

	/** Get Details about refill form.
		@return Details about refill form	  */
	public int getDSI_RefillEntry_Main_Det_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_RefillEntry_Main_Det_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_RefillEntry_Main_Det_UU.
		@param DSI_RefillEntry_Main_Det_UU DSI_RefillEntry_Main_Det_UU	  */
	public void setDSI_RefillEntry_Main_Det_UU (String DSI_RefillEntry_Main_Det_UU)
	{
		set_Value (COLUMNNAME_DSI_RefillEntry_Main_Det_UU, DSI_RefillEntry_Main_Det_UU);
	}

	/** Get DSI_RefillEntry_Main_Det_UU.
		@return DSI_RefillEntry_Main_Det_UU	  */
	public String getDSI_RefillEntry_Main_Det_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_RefillEntry_Main_Det_UU);
	}

	public I_DSI_RefillEntry_Main getDSI_RefillEntry_Main() throws RuntimeException
    {
		return (I_DSI_RefillEntry_Main)MTable.get(getCtx(), I_DSI_RefillEntry_Main.Table_Name)
			.getPO(getDSI_RefillEntry_Main_ID(), get_TrxName());	}

	/** Set Refill Main Entry Form.
		@param DSI_RefillEntry_Main_ID Refill Main Entry Form	  */
	public void setDSI_RefillEntry_Main_ID (int DSI_RefillEntry_Main_ID)
	{
		if (DSI_RefillEntry_Main_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_RefillEntry_Main_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_RefillEntry_Main_ID, Integer.valueOf(DSI_RefillEntry_Main_ID));
	}

	/** Get Refill Main Entry Form.
		@return Refill Main Entry Form	  */
	public int getDSI_RefillEntry_Main_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_RefillEntry_Main_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Serial No..
		@param DSI_SrNo Serial No.	  */
	public void setDSI_SrNo (String DSI_SrNo)
	{
		set_Value (COLUMNNAME_DSI_SrNo, DSI_SrNo);
	}

	/** Get Serial No..
		@return Serial No.	  */
	public String getDSI_SrNo () 
	{
		return (String)get_Value(COLUMNNAME_DSI_SrNo);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_ValueNoCheck (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
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
}