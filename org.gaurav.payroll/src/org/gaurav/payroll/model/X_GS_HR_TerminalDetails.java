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
package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_HR_TerminalDetails
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_TerminalDetails extends PO implements I_GS_HR_TerminalDetails, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_TerminalDetails (Properties ctx, int GS_HR_TerminalDetails_ID, String trxName)
    {
      super (ctx, GS_HR_TerminalDetails_ID, trxName);
      /** if (GS_HR_TerminalDetails_ID == 0)
        {
			setGS_HR_TerminalDetails_ID (0);
			setGS_HR_TerminalWSDL (null);
			setLoginName (null);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_TerminalDetails (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_TerminalDetails[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Attendance Terminals.
		@param GS_HR_TerminalDetails_ID Attendance Terminals	  */
	public void setGS_HR_TerminalDetails_ID (int GS_HR_TerminalDetails_ID)
	{
		if (GS_HR_TerminalDetails_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_TerminalDetails_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_TerminalDetails_ID, Integer.valueOf(GS_HR_TerminalDetails_ID));
	}

	/** Get Attendance Terminals.
		@return Attendance Terminals	  */
	public int getGS_HR_TerminalDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_TerminalDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_TerminalDetails_UU.
		@param GS_HR_TerminalDetails_UU GS_HR_TerminalDetails_UU	  */
	public void setGS_HR_TerminalDetails_UU (String GS_HR_TerminalDetails_UU)
	{
		set_Value (COLUMNNAME_GS_HR_TerminalDetails_UU, GS_HR_TerminalDetails_UU);
	}

	/** Get GS_HR_TerminalDetails_UU.
		@return GS_HR_TerminalDetails_UU	  */
	public String getGS_HR_TerminalDetails_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_TerminalDetails_UU);
	}

	/** Set Terminal SN.
		@param GS_HR_TerminalSN Terminal SN	  */
	public void setGS_HR_TerminalSN (String GS_HR_TerminalSN)
	{
		set_Value (COLUMNNAME_GS_HR_TerminalSN, GS_HR_TerminalSN);
	}

	/** Get Terminal SN.
		@return Terminal SN	  */
	public String getGS_HR_TerminalSN () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_TerminalSN);
	}

	/** Set Terminal URL.
		@param GS_HR_TerminalWSDL 
		Terminal URL will be used to get the connect to terminal
	  */
	public void setGS_HR_TerminalWSDL (String GS_HR_TerminalWSDL)
	{
		set_Value (COLUMNNAME_GS_HR_TerminalWSDL, GS_HR_TerminalWSDL);
	}

	/** Get Terminal URL.
		@return Terminal URL will be used to get the connect to terminal
	  */
	public String getGS_HR_TerminalWSDL () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_TerminalWSDL);
	}

	/** Set User Name.
		@param LoginName User Name	  */
	public void setLoginName (String LoginName)
	{
		set_ValueNoCheck (COLUMNNAME_LoginName, LoginName);
	}

	/** Get User Name.
		@return User Name	  */
	public String getLoginName () 
	{
		return (String)get_Value(COLUMNNAME_LoginName);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}
}