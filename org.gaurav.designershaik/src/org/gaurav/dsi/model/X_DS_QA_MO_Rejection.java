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
import org.compiere.util.KeyNamePair;

/** Generated Model for DS_QA_MO_Rejection
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_QA_MO_Rejection extends PO implements I_DS_QA_MO_Rejection, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20171203L;

    /** Standard Constructor */
    public X_DS_QA_MO_Rejection (Properties ctx, int DS_QA_MO_Rejection_ID, String trxName)
    {
      super (ctx, DS_QA_MO_Rejection_ID, trxName);
      /** if (DS_QA_MO_Rejection_ID == 0)
        {
			setDS_QA_MO_Rejection_ID (0);
			setDS_QARejection_Reason_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_QA_MO_Rejection (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_QA_MO_Rejection[")
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

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
	}

	public I_DS_Manufacturing_QA_BOM getDS_Manufacturing_QA_BOM() throws RuntimeException
    {
		return (I_DS_Manufacturing_QA_BOM)MTable.get(getCtx(), I_DS_Manufacturing_QA_BOM.Table_Name)
			.getPO(getDS_Manufacturing_QA_BOM_ID(), get_TrxName());	}

	/** Set QA per Manufacturing BOM Products.
		@param DS_Manufacturing_QA_BOM_ID QA per Manufacturing BOM Products	  */
	public void setDS_Manufacturing_QA_BOM_ID (int DS_Manufacturing_QA_BOM_ID)
	{
		if (DS_Manufacturing_QA_BOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Manufacturing_QA_BOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Manufacturing_QA_BOM_ID, Integer.valueOf(DS_Manufacturing_QA_BOM_ID));
	}

	/** Get QA per Manufacturing BOM Products.
		@return QA per Manufacturing BOM Products	  */
	public int getDS_Manufacturing_QA_BOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Manufacturing_QA_BOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Rejection Reason.
		@param DS_QA_MO_Rejection_ID Rejection Reason	  */
	public void setDS_QA_MO_Rejection_ID (int DS_QA_MO_Rejection_ID)
	{
		if (DS_QA_MO_Rejection_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_QA_MO_Rejection_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_QA_MO_Rejection_ID, Integer.valueOf(DS_QA_MO_Rejection_ID));
	}

	/** Get Rejection Reason.
		@return Rejection Reason	  */
	public int getDS_QA_MO_Rejection_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_QA_MO_Rejection_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_QA_MO_Rejection_UU.
		@param DS_QA_MO_Rejection_UU DS_QA_MO_Rejection_UU	  */
	public void setDS_QA_MO_Rejection_UU (String DS_QA_MO_Rejection_UU)
	{
		set_Value (COLUMNNAME_DS_QA_MO_Rejection_UU, DS_QA_MO_Rejection_UU);
	}

	/** Get DS_QA_MO_Rejection_UU.
		@return DS_QA_MO_Rejection_UU	  */
	public String getDS_QA_MO_Rejection_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_QA_MO_Rejection_UU);
	}

	public I_DS_QARejection_Reason getDS_QARejection_Reason() throws RuntimeException
    {
		return (I_DS_QARejection_Reason)MTable.get(getCtx(), I_DS_QARejection_Reason.Table_Name)
			.getPO(getDS_QARejection_Reason_ID(), get_TrxName());	}

	/** Set Reasons for rejection.
		@param DS_QARejection_Reason_ID Reasons for rejection	  */
	public void setDS_QARejection_Reason_ID (int DS_QARejection_Reason_ID)
	{
		if (DS_QARejection_Reason_ID < 1) 
			set_Value (COLUMNNAME_DS_QARejection_Reason_ID, null);
		else 
			set_Value (COLUMNNAME_DS_QARejection_Reason_ID, Integer.valueOf(DS_QARejection_Reason_ID));
	}

	/** Get Reasons for rejection.
		@return Reasons for rejection	  */
	public int getDS_QARejection_Reason_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_QARejection_Reason_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Qty Reject.
		@param QtyReject Qty Reject	  */
	public void setQtyReject (BigDecimal QtyReject)
	{
		set_Value (COLUMNNAME_QtyReject, QtyReject);
	}

	/** Get Qty Reject.
		@return Qty Reject	  */
	public BigDecimal getQtyReject () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReject);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getQtyReject()));
    }
}