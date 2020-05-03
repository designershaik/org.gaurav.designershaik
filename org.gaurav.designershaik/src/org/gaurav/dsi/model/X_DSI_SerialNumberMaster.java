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

/** Generated Model for DSI_SerialNumberMaster
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_SerialNumberMaster extends PO implements I_DSI_SerialNumberMaster, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_SerialNumberMaster (Properties ctx, int DSI_SerialNumberMaster_ID, String trxName)
    {
      super (ctx, DSI_SerialNumberMaster_ID, trxName);
      /** if (DSI_SerialNumberMaster_ID == 0)
        {
			setDSI_SerialNumberMaster_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_SerialNumberMaster (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_SerialNumberMaster[")
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

	/** Set DSI_SerialNumberMaster_UU.
		@param DSI_SerialNumberMaster_UU DSI_SerialNumberMaster_UU	  */
	public void setDSI_SerialNumberMaster_UU (String DSI_SerialNumberMaster_UU)
	{
		set_Value (COLUMNNAME_DSI_SerialNumberMaster_UU, DSI_SerialNumberMaster_UU);
	}

	/** Get DSI_SerialNumberMaster_UU.
		@return DSI_SerialNumberMaster_UU	  */
	public String getDSI_SerialNumberMaster_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_SerialNumberMaster_UU);
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
}