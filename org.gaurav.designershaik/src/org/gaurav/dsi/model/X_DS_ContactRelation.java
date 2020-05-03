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

/** Generated Model for DS_ContactRelation
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_DS_ContactRelation extends PO implements I_DS_ContactRelation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190310L;

    /** Standard Constructor */
    public X_DS_ContactRelation (Properties ctx, int DS_ContactRelation_ID, String trxName)
    {
      super (ctx, DS_ContactRelation_ID, trxName);
      /** if (DS_ContactRelation_ID == 0)
        {
			setDS_ContactMaster_ID (0);
			setDS_ContactRelation_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_ContactRelation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_ContactRelation[")
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

	/** Set Relationship between Contacts.
		@param DS_ContactRelation_ID Relationship between Contacts	  */
	public void setDS_ContactRelation_ID (int DS_ContactRelation_ID)
	{
		if (DS_ContactRelation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_ContactRelation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_ContactRelation_ID, Integer.valueOf(DS_ContactRelation_ID));
	}

	/** Get Relationship between Contacts.
		@return Relationship between Contacts	  */
	public int getDS_ContactRelation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ContactRelation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_ContactRelation_UU.
		@param DS_ContactRelation_UU DS_ContactRelation_UU	  */
	public void setDS_ContactRelation_UU (String DS_ContactRelation_UU)
	{
		set_Value (COLUMNNAME_DS_ContactRelation_UU, DS_ContactRelation_UU);
	}

	/** Get DS_ContactRelation_UU.
		@return DS_ContactRelation_UU	  */
	public String getDS_ContactRelation_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_ContactRelation_UU);
	}

	public I_DS_ContactMaster getDS_RelatedContact() throws RuntimeException
    {
		return (I_DS_ContactMaster)MTable.get(getCtx(), I_DS_ContactMaster.Table_Name)
			.getPO(getDS_RelatedContact_ID(), get_TrxName());	}

	/** Set Related Contact.
		@param DS_RelatedContact_ID Related Contact	  */
	public void setDS_RelatedContact_ID (int DS_RelatedContact_ID)
	{
		if (DS_RelatedContact_ID < 1) 
			set_Value (COLUMNNAME_DS_RelatedContact_ID, null);
		else 
			set_Value (COLUMNNAME_DS_RelatedContact_ID, Integer.valueOf(DS_RelatedContact_ID));
	}

	/** Get Related Contact.
		@return Related Contact	  */
	public int getDS_RelatedContact_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RelatedContact_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DS_RelationShip getDS_RelationShip() throws RuntimeException
    {
		return (I_DS_RelationShip)MTable.get(getCtx(), I_DS_RelationShip.Table_Name)
			.getPO(getDS_RelationShip_ID(), get_TrxName());	}

	/** Set Relationship Type.
		@param DS_RelationShip_ID Relationship Type	  */
	public void setDS_RelationShip_ID (int DS_RelationShip_ID)
	{
		if (DS_RelationShip_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_RelationShip_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_RelationShip_ID, Integer.valueOf(DS_RelationShip_ID));
	}

	/** Get Relationship Type.
		@return Relationship Type	  */
	public int getDS_RelationShip_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RelationShip_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}