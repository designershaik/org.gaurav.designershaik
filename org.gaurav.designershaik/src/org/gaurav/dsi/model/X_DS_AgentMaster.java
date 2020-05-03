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

/** Generated Model for DS_AgentMaster
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_AgentMaster extends PO implements I_DS_AgentMaster, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_AgentMaster (Properties ctx, int DS_AgentMaster_ID, String trxName)
    {
      super (ctx, DS_AgentMaster_ID, trxName);
      /** if (DS_AgentMaster_ID == 0)
        {
			setDS_AgentMaster_ID (0);
			setDS_RouteTo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_AgentMaster (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_AgentMaster[")
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

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getC_BPartnerSR() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartnerSR_ID(), get_TrxName());	}

	/** Set BPartner (Agent).
		@param C_BPartnerSR_ID 
		Business Partner (Agent or Sales Rep)
	  */
	public void setC_BPartnerSR_ID (int C_BPartnerSR_ID)
	{
		if (C_BPartnerSR_ID < 1) 
			set_Value (COLUMNNAME_C_BPartnerSR_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartnerSR_ID, Integer.valueOf(C_BPartnerSR_ID));
	}

	/** Get BPartner (Agent).
		@return Business Partner (Agent or Sales Rep)
	  */
	public int getC_BPartnerSR_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartnerSR_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Agent Fees.
		@param DS_AgentFees Agent Fees	  */
	public void setDS_AgentFees (BigDecimal DS_AgentFees)
	{
		set_Value (COLUMNNAME_DS_AgentFees, DS_AgentFees);
	}

	/** Get Agent Fees.
		@return Agent Fees	  */
	public BigDecimal getDS_AgentFees () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_AgentFees);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Agent Master.
		@param DS_AgentMaster_ID Agent Master	  */
	public void setDS_AgentMaster_ID (int DS_AgentMaster_ID)
	{
		if (DS_AgentMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_AgentMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_AgentMaster_ID, Integer.valueOf(DS_AgentMaster_ID));
	}

	/** Get Agent Master.
		@return Agent Master	  */
	public int getDS_AgentMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_AgentMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_AgentMaster_UU.
		@param DS_AgentMaster_UU DS_AgentMaster_UU	  */
	public void setDS_AgentMaster_UU (String DS_AgentMaster_UU)
	{
		set_Value (COLUMNNAME_DS_AgentMaster_UU, DS_AgentMaster_UU);
	}

	/** Get DS_AgentMaster_UU.
		@return DS_AgentMaster_UU	  */
	public String getDS_AgentMaster_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_AgentMaster_UU);
	}

	/** Set Clearing Charges.
		@param DS_ClearingCharges Clearing Charges	  */
	public void setDS_ClearingCharges (BigDecimal DS_ClearingCharges)
	{
		set_Value (COLUMNNAME_DS_ClearingCharges, DS_ClearingCharges);
	}

	/** Get Clearing Charges.
		@return Clearing Charges	  */
	public BigDecimal getDS_ClearingCharges () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DS_ClearingCharges);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public I_DS_RouteTo getDS_RouteTo() throws RuntimeException
    {
		return (I_DS_RouteTo)MTable.get(getCtx(), I_DS_RouteTo.Table_Name)
			.getPO(getDS_RouteTo_ID(), get_TrxName());	}

	/** Set Route to.
		@param DS_RouteTo_ID Route to	  */
	public void setDS_RouteTo_ID (int DS_RouteTo_ID)
	{
		if (DS_RouteTo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_RouteTo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_RouteTo_ID, Integer.valueOf(DS_RouteTo_ID));
	}

	/** Get Route to.
		@return Route to	  */
	public int getDS_RouteTo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RouteTo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
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

	/** Set Landline.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_ValueNoCheck (COLUMNNAME_Phone, Phone);
	}

	/** Get Landline.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set Mobile No./LandLine 2.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get Mobile No./LandLine 2.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}
}