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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;

/** Generated Model for DS_PO_RevisionHistory
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_PO_RevisionHistory extends PO implements I_DS_PO_RevisionHistory, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_PO_RevisionHistory (Properties ctx, int DS_PO_RevisionHistory_ID, String trxName)
    {
      super (ctx, DS_PO_RevisionHistory_ID, trxName);
      /** if (DS_PO_RevisionHistory_ID == 0)
        {
			setCategory (null);
// D
			setComments (null);
			setDS_JustificationForChange (null);
			setDS_PO_RevisionHistory_ID (0);
			setDS_ProposedChanged (null);
			setDS_RequestUser_ID (0);
			setLine (0);
// @SQL=SELECT COALESCE(MAX(Line),0)+1 AS DefaultValue FROM DS_PO_RevisionHistory WHERE C_OrderLine_ID=@C_OrderLine_ID@
        } */
    }

    /** Load Constructor */
    public X_DS_PO_RevisionHistory (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_PO_RevisionHistory[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Requirements = R */
	public static final String CATEGORY_Requirements = "R";
	/** Design = D */
	public static final String CATEGORY_Design = "D";
	/** Material = M */
	public static final String CATEGORY_Material = "M";
	/** Cost = C */
	public static final String CATEGORY_Cost = "C";
	/** Quality = Q */
	public static final String CATEGORY_Quality = "Q";
	/** Schedule = S */
	public static final String CATEGORY_Schedule = "S";
	/** Set Category.
		@param Category Category	  */
	public void setCategory (String Category)
	{

		set_Value (COLUMNNAME_Category, Category);
	}

	/** Get Category.
		@return Category	  */
	public String getCategory () 
	{
		return (String)get_Value(COLUMNNAME_Category);
	}

	/** Set ChangeAmt.
		@param ChangeAmt ChangeAmt	  */
	public void setChangeAmt (BigDecimal ChangeAmt)
	{
		set_Value (COLUMNNAME_ChangeAmt, ChangeAmt);
	}

	/** Get ChangeAmt.
		@return ChangeAmt	  */
	public BigDecimal getChangeAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ChangeAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set ChangeDate.
		@param ChangeDate ChangeDate	  */
	public void setChangeDate (Timestamp ChangeDate)
	{
		set_Value (COLUMNNAME_ChangeDate, ChangeDate);
	}

	/** Get ChangeDate.
		@return ChangeDate	  */
	public Timestamp getChangeDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ChangeDate);
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

	public org.compiere.model.I_C_Order getC_Order() throws RuntimeException
    {
		return (org.compiere.model.I_C_Order)MTable.get(getCtx(), org.compiere.model.I_C_Order.Table_Name)
			.getPO(getC_Order_ID(), get_TrxName());	}

	/** Set Order.
		@param C_Order_ID 
		Order
	  */
	public void setC_Order_ID (int C_Order_ID)
	{
		if (C_Order_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_Order_ID, Integer.valueOf(C_Order_ID));
	}

	/** Get Order.
		@return Order
	  */
	public int getC_Order_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Order_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getDS_ApprovedBy() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getDS_ApprovedBy_ID(), get_TrxName());	}

	/** Set Approved By.
		@param DS_ApprovedBy_ID Approved By	  */
	public void setDS_ApprovedBy_ID (int DS_ApprovedBy_ID)
	{
		if (DS_ApprovedBy_ID < 1) 
			set_Value (COLUMNNAME_DS_ApprovedBy_ID, null);
		else 
			set_Value (COLUMNNAME_DS_ApprovedBy_ID, Integer.valueOf(DS_ApprovedBy_ID));
	}

	/** Get Approved By.
		@return Approved By	  */
	public int getDS_ApprovedBy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_ApprovedBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Approved.
		@param DS_DateApproved Date Approved	  */
	public void setDS_DateApproved (Timestamp DS_DateApproved)
	{
		set_Value (COLUMNNAME_DS_DateApproved, DS_DateApproved);
	}

	/** Get Date Approved.
		@return Date Approved	  */
	public Timestamp getDS_DateApproved () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DS_DateApproved);
	}

	/** Set Justification for Change.
		@param DS_JustificationForChange Justification for Change	  */
	public void setDS_JustificationForChange (String DS_JustificationForChange)
	{
		set_Value (COLUMNNAME_DS_JustificationForChange, DS_JustificationForChange);
	}

	/** Get Justification for Change.
		@return Justification for Change	  */
	public String getDS_JustificationForChange () 
	{
		return (String)get_Value(COLUMNNAME_DS_JustificationForChange);
	}

	/** Set PO Revision history.
		@param DS_PO_RevisionHistory_ID PO Revision history	  */
	public void setDS_PO_RevisionHistory_ID (int DS_PO_RevisionHistory_ID)
	{
		if (DS_PO_RevisionHistory_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_PO_RevisionHistory_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_PO_RevisionHistory_ID, Integer.valueOf(DS_PO_RevisionHistory_ID));
	}

	/** Get PO Revision history.
		@return PO Revision history	  */
	public int getDS_PO_RevisionHistory_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_PO_RevisionHistory_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_PO_RevisionHistory_UU.
		@param DS_PO_RevisionHistory_UU DS_PO_RevisionHistory_UU	  */
	public void setDS_PO_RevisionHistory_UU (String DS_PO_RevisionHistory_UU)
	{
		set_Value (COLUMNNAME_DS_PO_RevisionHistory_UU, DS_PO_RevisionHistory_UU);
	}

	/** Get DS_PO_RevisionHistory_UU.
		@return DS_PO_RevisionHistory_UU	  */
	public String getDS_PO_RevisionHistory_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_PO_RevisionHistory_UU);
	}

	/** Set Proposed Changed.
		@param DS_ProposedChanged Proposed Changed	  */
	public void setDS_ProposedChanged (String DS_ProposedChanged)
	{
		set_Value (COLUMNNAME_DS_ProposedChanged, DS_ProposedChanged);
	}

	/** Get Proposed Changed.
		@return Proposed Changed	  */
	public String getDS_ProposedChanged () 
	{
		return (String)get_Value(COLUMNNAME_DS_ProposedChanged);
	}

	public org.compiere.model.I_AD_User getDS_RequestUser() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getDS_RequestUser_ID(), get_TrxName());	}

	/** Set Requested By.
		@param DS_RequestUser_ID Requested By	  */
	public void setDS_RequestUser_ID (int DS_RequestUser_ID)
	{
		if (DS_RequestUser_ID < 1) 
			set_Value (COLUMNNAME_DS_RequestUser_ID, null);
		else 
			set_Value (COLUMNNAME_DS_RequestUser_ID, Integer.valueOf(DS_RequestUser_ID));
	}

	/** Get Requested By.
		@return Requested By	  */
	public int getDS_RequestUser_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_RequestUser_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Approved.
		@param IsApproved 
		Indicates if this document requires approval
	  */
	public void setIsApproved (boolean IsApproved)
	{
		set_Value (COLUMNNAME_IsApproved, Boolean.valueOf(IsApproved));
	}

	/** Get Approved.
		@return Indicates if this document requires approval
	  */
	public boolean isApproved () 
	{
		Object oo = get_Value(COLUMNNAME_IsApproved);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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