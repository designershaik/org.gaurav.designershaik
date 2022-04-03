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

/** Generated Model for DSI_SalesForecastMonth
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_DSI_SalesForecastMonth extends PO implements I_DSI_SalesForecastMonth, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20211115L;

    /** Standard Constructor */
    public X_DSI_SalesForecastMonth (Properties ctx, int DSI_SalesForecastMonth_ID, String trxName)
    {
      super (ctx, DSI_SalesForecastMonth_ID, trxName);
      /** if (DSI_SalesForecastMonth_ID == 0)
        {
			setDSI_SalesForecastMonth_ID (0);
			setDSI_SalesForecastMonth_UU (null);
        } */
    }

    /** Load Constructor */
    public X_DSI_SalesForecastMonth (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_SalesForecastMonth[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set DSI_SalesForecastMonth.
		@param DSI_SalesForecastMonth_ID DSI_SalesForecastMonth	  */
	public void setDSI_SalesForecastMonth_ID (int DSI_SalesForecastMonth_ID)
	{
		if (DSI_SalesForecastMonth_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_SalesForecastMonth_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_SalesForecastMonth_ID, Integer.valueOf(DSI_SalesForecastMonth_ID));
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

	/** Set DSI_SalesForecastMonth_UU.
		@param DSI_SalesForecastMonth_UU DSI_SalesForecastMonth_UU	  */
	public void setDSI_SalesForecastMonth_UU (String DSI_SalesForecastMonth_UU)
	{
		set_ValueNoCheck (COLUMNNAME_DSI_SalesForecastMonth_UU, DSI_SalesForecastMonth_UU);
	}

	/** Get DSI_SalesForecastMonth_UU.
		@return DSI_SalesForecastMonth_UU	  */
	public String getDSI_SalesForecastMonth_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_SalesForecastMonth_UU);
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