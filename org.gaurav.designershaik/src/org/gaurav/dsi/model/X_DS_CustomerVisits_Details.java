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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DS_CustomerVisits_Details
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_CustomerVisits_Details extends PO implements I_DS_CustomerVisits_Details, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190130L;

    /** Standard Constructor */
    public X_DS_CustomerVisits_Details (Properties ctx, int DS_CustomerVisits_Details_ID, String trxName)
    {
      super (ctx, DS_CustomerVisits_Details_ID, trxName);
      /** if (DS_CustomerVisits_Details_ID == 0)
        {
        } */
    }

    /** Load Constructor */
    public X_DS_CustomerVisits_Details (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_CustomerVisits_Details[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date next action.
		@param DateNextAction 
		Date that this request should be acted on
	  */
	public void setDateNextAction (Timestamp DateNextAction)
	{
		set_Value (COLUMNNAME_DateNextAction, DateNextAction);
	}

	/** Get Date next action.
		@return Date that this request should be acted on
	  */
	public Timestamp getDateNextAction () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateNextAction);
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

	/** Set Customer Visits Details.
		@param DS_CustomerVisits_Details_ID Customer Visits Details	  */
	public void setDS_CustomerVisits_Details_ID (int DS_CustomerVisits_Details_ID)
	{
		if (DS_CustomerVisits_Details_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_CustomerVisits_Details_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_CustomerVisits_Details_ID, Integer.valueOf(DS_CustomerVisits_Details_ID));
	}

	/** Get Customer Visits Details.
		@return Customer Visits Details	  */
	public int getDS_CustomerVisits_Details_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_CustomerVisits_Details_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_CustomerVisits_Details_UU.
		@param DS_CustomerVisits_Details_UU DS_CustomerVisits_Details_UU	  */
	public void setDS_CustomerVisits_Details_UU (String DS_CustomerVisits_Details_UU)
	{
		set_Value (COLUMNNAME_DS_CustomerVisits_Details_UU, DS_CustomerVisits_Details_UU);
	}

	/** Get DS_CustomerVisits_Details_UU.
		@return DS_CustomerVisits_Details_UU	  */
	public String getDS_CustomerVisits_Details_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_CustomerVisits_Details_UU);
	}

	public I_DS_CustomerVisits getDS_CustomerVisits() throws RuntimeException
    {
		return (I_DS_CustomerVisits)MTable.get(getCtx(), I_DS_CustomerVisits.Table_Name)
			.getPO(getDS_CustomerVisits_ID(), get_TrxName());	}

	/** Set Customer Visits.
		@param DS_CustomerVisits_ID Customer Visits	  */
	public void setDS_CustomerVisits_ID (int DS_CustomerVisits_ID)
	{
		if (DS_CustomerVisits_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_CustomerVisits_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_CustomerVisits_ID, Integer.valueOf(DS_CustomerVisits_ID));
	}

	/** Get Customer Visits.
		@return Customer Visits	  */
	public int getDS_CustomerVisits_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_CustomerVisits_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** NextAction AD_Reference_ID=219 */
	public static final int NEXTACTION_AD_Reference_ID=219;
	/** None = N */
	public static final String NEXTACTION_None = "N";
	/** Follow up = F */
	public static final String NEXTACTION_FollowUp = "F";
	/** Set Next action.
		@param NextAction 
		Next Action to be taken
	  */
	public void setNextAction (String NextAction)
	{

		set_Value (COLUMNNAME_NextAction, NextAction);
	}

	/** Get Next action.
		@return Next Action to be taken
	  */
	public String getNextAction () 
	{
		return (String)get_Value(COLUMNNAME_NextAction);
	}

	public org.compiere.model.I_R_Request getR_Request() throws RuntimeException
    {
		return (org.compiere.model.I_R_Request)MTable.get(getCtx(), org.compiere.model.I_R_Request.Table_Name)
			.getPO(getR_Request_ID(), get_TrxName());	}

	/** Set Request.
		@param R_Request_ID 
		Request from a Business Partner or Prospect
	  */
	public void setR_Request_ID (int R_Request_ID)
	{
		if (R_Request_ID < 1) 
			set_Value (COLUMNNAME_R_Request_ID, null);
		else 
			set_Value (COLUMNNAME_R_Request_ID, Integer.valueOf(R_Request_ID));
	}

	/** Get Request.
		@return Request from a Business Partner or Prospect
	  */
	public int getR_Request_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Request_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}