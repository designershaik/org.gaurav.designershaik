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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for GS_HR_Warnings
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Warnings extends PO implements I_GS_HR_Warnings, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_Warnings (Properties ctx, int GS_HR_Warnings_ID, String trxName)
    {
      super (ctx, GS_HR_Warnings_ID, trxName);
      /** if (GS_HR_Warnings_ID == 0)
        {
			setAction (null);
			setComments (null);
			setDate1 (new Timestamp( System.currentTimeMillis() ));
// @#Date@
			setGS_HR_Warnings_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Warnings (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Warnings[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Action.
		@param Action 
		Indicates the Action to be performed
	  */
	public void setAction (String Action)
	{
		set_Value (COLUMNNAME_Action, Action);
	}

	/** Get Action.
		@return Indicates the Action to be performed
	  */
	public String getAction () 
	{
		return (String)get_Value(COLUMNNAME_Action);
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

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
	}

	public org.compiere.model.I_AD_User getGS_HR_ComplainedBy() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getGS_HR_ComplainedBy_ID(), get_TrxName());	}

	/** Set Complained By.
		@param GS_HR_ComplainedBy_ID Complained By	  */
	public void setGS_HR_ComplainedBy_ID (int GS_HR_ComplainedBy_ID)
	{
		if (GS_HR_ComplainedBy_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_ComplainedBy_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_ComplainedBy_ID, Integer.valueOf(GS_HR_ComplainedBy_ID));
	}

	/** Get Complained By.
		@return Complained By	  */
	public int getGS_HR_ComplainedBy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_ComplainedBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_GS_HR_Employee getGS_HR_Employee() throws RuntimeException
    {
		return (I_GS_HR_Employee)MTable.get(getCtx(), I_GS_HR_Employee.Table_Name)
			.getPO(getGS_HR_Employee_ID(), get_TrxName());	}

	/** Set Employee Details.
		@param GS_HR_Employee_ID Employee Details	  */
	public void setGS_HR_Employee_ID (int GS_HR_Employee_ID)
	{
		if (GS_HR_Employee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Employee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Employee_ID, Integer.valueOf(GS_HR_Employee_ID));
	}

	/** Get Employee Details.
		@return Employee Details	  */
	public int getGS_HR_Employee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Employee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Warnings/Notices.
		@param GS_HR_Warnings_ID Warnings/Notices	  */
	public void setGS_HR_Warnings_ID (int GS_HR_Warnings_ID)
	{
		if (GS_HR_Warnings_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Warnings_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Warnings_ID, Integer.valueOf(GS_HR_Warnings_ID));
	}

	/** Get Warnings/Notices.
		@return Warnings/Notices	  */
	public int getGS_HR_Warnings_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Warnings_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Warnings_UU.
		@param GS_HR_Warnings_UU GS_HR_Warnings_UU	  */
	public void setGS_HR_Warnings_UU (String GS_HR_Warnings_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Warnings_UU, GS_HR_Warnings_UU);
	}

	/** Get GS_HR_Warnings_UU.
		@return GS_HR_Warnings_UU	  */
	public String getGS_HR_Warnings_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Warnings_UU);
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_ValueNoCheck (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}