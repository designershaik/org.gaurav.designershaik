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

/** Generated Model for DS_OrgContactRelation
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_OrgContactRelation extends PO implements I_DS_OrgContactRelation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190310L;

    /** Standard Constructor */
    public X_DS_OrgContactRelation (Properties ctx, int DS_OrgContactRelation_ID, String trxName)
    {
      super (ctx, DS_OrgContactRelation_ID, trxName);
      /** if (DS_OrgContactRelation_ID == 0)
        {
			setDS_ContactMaster_ID (0);
			setDS_OrgContactRelation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_OrgContactRelation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_OrgContactRelation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_DS_ContactMaster getDS_ContactMaster() throws RuntimeException
    {
		return (I_DS_ContactMaster)MTable.get(getCtx(), I_DS_ContactMaster.Table_Name)
			.getPO(getDS_ContactMaster_ID(), get_TrxName());	}

	/** Set Contact Master.
		@param DS_ContactMaster_ID Contact Master	  */
	public void setDS_ContactMaster_ID (int DS_ContactMaster_ID)
	{
		if (DS_ContactMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ContactMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ContactMaster_ID, Integer.valueOf(DS_ContactMaster_ID));
	}

	/** Get Contact Master.
		@return Contact Master	  */
	public int getDS_ContactMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ContactMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Master Org & Contact Relation.
		@param DS_OrgContactRelation_ID Master Org & Contact Relation	  */
	public void setDS_OrgContactRelation_ID (int DS_OrgContactRelation_ID)
	{
		if (DS_OrgContactRelation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_OrgContactRelation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_OrgContactRelation_ID, Integer.valueOf(DS_OrgContactRelation_ID));
	}

	/** Get Master Org & Contact Relation.
		@return Master Org & Contact Relation	  */
	public int getDS_OrgContactRelation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_OrgContactRelation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_OrgContactRelation_UU.
		@param DS_OrgContactRelation_UU DS_OrgContactRelation_UU	  */
	public void setDS_OrgContactRelation_UU (String DS_OrgContactRelation_UU)
	{
		set_Value (COLUMNNAME_DS_OrgContactRelation_UU, DS_OrgContactRelation_UU);
	}

	/** Get DS_OrgContactRelation_UU.
		@return DS_OrgContactRelation_UU	  */
	public String getDS_OrgContactRelation_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_OrgContactRelation_UU);
	}

	public I_DS_OrgMaster getDS_OrgMaster() throws RuntimeException
    {
		return (I_DS_OrgMaster)MTable.get(getCtx(), I_DS_OrgMaster.Table_Name)
			.getPO(getDS_OrgMaster_ID(), get_TrxName());	}

	/** Set Organization Master.
		@param DS_OrgMaster_ID Organization Master	  */
	public void setDS_OrgMaster_ID (int DS_OrgMaster_ID)
	{
		if (DS_OrgMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_ID, Integer.valueOf(DS_OrgMaster_ID));
	}

	/** Get Organization Master.
		@return Organization Master	  */
	public int getDS_OrgMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_OrgMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DS_OrgMaster_Location getDS_OrgMaster_Location() throws RuntimeException
    {
		return (I_DS_OrgMaster_Location)MTable.get(getCtx(), I_DS_OrgMaster_Location.Table_Name)
			.getPO(getDS_OrgMaster_Location_ID(), get_TrxName());	}

	/** Set Organization Master Location.
		@param DS_OrgMaster_Location_ID Organization Master Location	  */
	public void setDS_OrgMaster_Location_ID (int DS_OrgMaster_Location_ID)
	{
		if (DS_OrgMaster_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_Location_ID, Integer.valueOf(DS_OrgMaster_Location_ID));
	}

	/** Get Organization Master Location.
		@return Organization Master Location	  */
	public int getDS_OrgMaster_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_OrgMaster_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Title.
		@param Title 
		Name this entity is referred to as
	  */
	public void setTitle (String Title)
	{
		set_ValueNoCheck (COLUMNNAME_Title, Title);
	}

	/** Get Title.
		@return Name this entity is referred to as
	  */
	public String getTitle () 
	{
		return (String)get_Value(COLUMNNAME_Title);
	}
}