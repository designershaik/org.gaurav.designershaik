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

/** Generated Model for DS_B2B_EmailConf
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_B2B_EmailConf extends PO implements I_DS_B2B_EmailConf, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_B2B_EmailConf (Properties ctx, int DS_B2B_EmailConf_ID, String trxName)
    {
      super (ctx, DS_B2B_EmailConf_ID, trxName);
      /** if (DS_B2B_EmailConf_ID == 0)
        {
			setDS_B2B_EmailConf_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_B2B_EmailConf (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_B2B_EmailConf[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set B2B Email Configuration.
		@param DS_B2B_EmailConf_ID B2B Email Configuration	  */
	public void setDS_B2B_EmailConf_ID (int DS_B2B_EmailConf_ID)
	{
		if (DS_B2B_EmailConf_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_B2B_EmailConf_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_B2B_EmailConf_ID, Integer.valueOf(DS_B2B_EmailConf_ID));
	}

	/** Get B2B Email Configuration.
		@return B2B Email Configuration	  */
	public int getDS_B2B_EmailConf_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_B2B_EmailConf_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_B2B_EmailConf_UU.
		@param DS_B2B_EmailConf_UU DS_B2B_EmailConf_UU	  */
	public void setDS_B2B_EmailConf_UU (String DS_B2B_EmailConf_UU)
	{
		set_Value (COLUMNNAME_DS_B2B_EmailConf_UU, DS_B2B_EmailConf_UU);
	}

	/** Get DS_B2B_EmailConf_UU.
		@return DS_B2B_EmailConf_UU	  */
	public String getDS_B2B_EmailConf_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_B2B_EmailConf_UU);
	}
}