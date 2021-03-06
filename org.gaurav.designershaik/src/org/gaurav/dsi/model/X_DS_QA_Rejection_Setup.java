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

/** Generated Model for DS_QA_Rejection_Setup
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_QA_Rejection_Setup extends PO implements I_DS_QA_Rejection_Setup, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171203L;

    /** Standard Constructor */
    public X_DS_QA_Rejection_Setup (Properties ctx, int DS_QA_Rejection_Setup_ID, String trxName)
    {
      super (ctx, DS_QA_Rejection_Setup_ID, trxName);
      /** if (DS_QA_Rejection_Setup_ID == 0)
        {
			setDS_QA_Rejection_Setup_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_QA_Rejection_Setup (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_QA_Rejection_Setup[")
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
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** Set Rejection Setup.
		@param DS_QA_Rejection_Setup_ID Rejection Setup	  */
	public void setDS_QA_Rejection_Setup_ID (int DS_QA_Rejection_Setup_ID)
	{
		if (DS_QA_Rejection_Setup_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_QA_Rejection_Setup_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_QA_Rejection_Setup_ID, Integer.valueOf(DS_QA_Rejection_Setup_ID));
	}

	/** Get Rejection Setup.
		@return Rejection Setup	  */
	public int getDS_QA_Rejection_Setup_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_QA_Rejection_Setup_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_QA_Rejection_Setup_UU.
		@param DS_QA_Rejection_Setup_UU DS_QA_Rejection_Setup_UU	  */
	public void setDS_QA_Rejection_Setup_UU (String DS_QA_Rejection_Setup_UU)
	{
		set_Value (COLUMNNAME_DS_QA_Rejection_Setup_UU, DS_QA_Rejection_Setup_UU);
	}

	/** Get DS_QA_Rejection_Setup_UU.
		@return DS_QA_Rejection_Setup_UU	  */
	public String getDS_QA_Rejection_Setup_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_QA_Rejection_Setup_UU);
	}

	public org.compiere.model.I_R_MailText getR_MailText() throws RuntimeException
    {
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_Name)
			.getPO(getR_MailText_ID(), get_TrxName());	}

	/** Set Mail Template.
		@param R_MailText_ID 
		Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID)
	{
		if (R_MailText_ID < 1) 
			set_Value (COLUMNNAME_R_MailText_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailText_ID, Integer.valueOf(R_MailText_ID));
	}

	/** Get Mail Template.
		@return Text templates for mailings
	  */
	public int getR_MailText_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailText_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}