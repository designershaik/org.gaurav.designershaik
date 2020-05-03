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

/** Generated Model for DSI_GroupResponsible
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_GroupResponsible extends PO implements I_DSI_GroupResponsible, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_GroupResponsible (Properties ctx, int DSI_GroupResponsible_ID, String trxName)
    {
      super (ctx, DSI_GroupResponsible_ID, trxName);
      /** if (DSI_GroupResponsible_ID == 0)
        {
			setDSI_GroupResponsible_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_GroupResponsible (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_GroupResponsible[")
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

	/** Set Group Responsible.
		@param DSI_GroupResponsible_ID Group Responsible	  */
	public void setDSI_GroupResponsible_ID (int DSI_GroupResponsible_ID)
	{
		if (DSI_GroupResponsible_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_GroupResponsible_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_GroupResponsible_ID, Integer.valueOf(DSI_GroupResponsible_ID));
	}

	/** Get Group Responsible.
		@return Group Responsible	  */
	public int getDSI_GroupResponsible_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_GroupResponsible_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_GroupResponsible_UU.
		@param DSI_GroupResponsible_UU DSI_GroupResponsible_UU	  */
	public void setDSI_GroupResponsible_UU (String DSI_GroupResponsible_UU)
	{
		set_Value (COLUMNNAME_DSI_GroupResponsible_UU, DSI_GroupResponsible_UU);
	}

	/** Get DSI_GroupResponsible_UU.
		@return DSI_GroupResponsible_UU	  */
	public String getDSI_GroupResponsible_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_GroupResponsible_UU);
	}

	/** Set Priority.
		@param Priority 
		Indicates if this request is of a high, medium or low priority.
	  */
	public void setPriority (int Priority)
	{
		set_Value (COLUMNNAME_Priority, Integer.valueOf(Priority));
	}

	/** Get Priority.
		@return Indicates if this request is of a high, medium or low priority.
	  */
	public int getPriority () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Priority);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_R_Group getR_Group() throws RuntimeException
    {
		return (org.compiere.model.I_R_Group)MTable.get(getCtx(), org.compiere.model.I_R_Group.Table_Name)
			.getPO(getR_Group_ID(), get_TrxName());	}

	/** Set Group.
		@param R_Group_ID 
		Request Group
	  */
	public void setR_Group_ID (int R_Group_ID)
	{
		if (R_Group_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_R_Group_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_R_Group_ID, Integer.valueOf(R_Group_ID));
	}

	/** Get Group.
		@return Request Group
	  */
	public int getR_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}