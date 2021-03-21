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

/** Generated Model for GS_HR_Emp_DocumentsDet
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Emp_DocumentsDet extends PO implements I_GS_HR_Emp_DocumentsDet, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_Emp_DocumentsDet (Properties ctx, int GS_HR_Emp_DocumentsDet_ID, String trxName)
    {
      super (ctx, GS_HR_Emp_DocumentsDet_ID, trxName);
      /** if (GS_HR_Emp_DocumentsDet_ID == 0)
        {
			setGS_HR_Emp_DocumentsDet_ID (0);
			setHR_DocNumber (null);
			setHR_Doc_ExpiryDate (new Timestamp( System.currentTimeMillis() ));
			setHR_Doc_IssueDate (new Timestamp( System.currentTimeMillis() ));
			setHR_Documents_ID (0);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Emp_DocumentsDet (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Emp_DocumentsDet[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Image.
		@param AD_Image_ID 
		Image or Icon
	  */
	public void setAD_Image_ID (int AD_Image_ID)
	{
		if (AD_Image_ID < 1) 
			set_Value (COLUMNNAME_AD_Image_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Image_ID, Integer.valueOf(AD_Image_ID));
	}

	/** Get Image.
		@return Image or Icon
	  */
	public int getAD_Image_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Image_ID);
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

	/** Set Employee Related Documents.
		@param GS_HR_Emp_DocumentsDet_ID Employee Related Documents	  */
	public void setGS_HR_Emp_DocumentsDet_ID (int GS_HR_Emp_DocumentsDet_ID)
	{
		if (GS_HR_Emp_DocumentsDet_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Emp_DocumentsDet_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Emp_DocumentsDet_ID, Integer.valueOf(GS_HR_Emp_DocumentsDet_ID));
	}

	/** Get Employee Related Documents.
		@return Employee Related Documents	  */
	public int getGS_HR_Emp_DocumentsDet_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Emp_DocumentsDet_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Emp_DocumentsDet_UU.
		@param GS_HR_Emp_DocumentsDet_UU GS_HR_Emp_DocumentsDet_UU	  */
	public void setGS_HR_Emp_DocumentsDet_UU (String GS_HR_Emp_DocumentsDet_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Emp_DocumentsDet_UU, GS_HR_Emp_DocumentsDet_UU);
	}

	/** Get GS_HR_Emp_DocumentsDet_UU.
		@return GS_HR_Emp_DocumentsDet_UU	  */
	public String getGS_HR_Emp_DocumentsDet_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Emp_DocumentsDet_UU);
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
			set_Value (COLUMNNAME_GS_HR_Employee_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Employee_ID, Integer.valueOf(GS_HR_Employee_ID));
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

	/** Set Issued By.
		@param GS_HR_IssuedBy 
		Issued by this place
	  */
	public void setGS_HR_IssuedBy (String GS_HR_IssuedBy)
	{
		set_Value (COLUMNNAME_GS_HR_IssuedBy, GS_HR_IssuedBy);
	}

	/** Get Issued By.
		@return Issued by this place
	  */
	public String getGS_HR_IssuedBy () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_IssuedBy);
	}

	/** Set Document No.
		@param HR_DocNumber 
		Identity/Document No
	  */
	public void setHR_DocNumber (String HR_DocNumber)
	{
		set_Value (COLUMNNAME_HR_DocNumber, HR_DocNumber);
	}

	/** Get Document No.
		@return Identity/Document No
	  */
	public String getHR_DocNumber () 
	{
		return (String)get_Value(COLUMNNAME_HR_DocNumber);
	}

	/** Set Expiry Date.
		@param HR_Doc_ExpiryDate 
		Expiry Date
	  */
	public void setHR_Doc_ExpiryDate (Timestamp HR_Doc_ExpiryDate)
	{
		set_Value (COLUMNNAME_HR_Doc_ExpiryDate, HR_Doc_ExpiryDate);
	}

	/** Get Expiry Date.
		@return Expiry Date
	  */
	public Timestamp getHR_Doc_ExpiryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HR_Doc_ExpiryDate);
	}

	/** Set Issue Date.
		@param HR_Doc_IssueDate 
		Issue Date
	  */
	public void setHR_Doc_IssueDate (Timestamp HR_Doc_IssueDate)
	{
		set_Value (COLUMNNAME_HR_Doc_IssueDate, HR_Doc_IssueDate);
	}

	/** Get Issue Date.
		@return Issue Date
	  */
	public Timestamp getHR_Doc_IssueDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_HR_Doc_IssueDate);
	}

	public I_HR_Documents getHR_Documents() throws RuntimeException
    {
		return (I_HR_Documents)MTable.get(getCtx(), I_HR_Documents.Table_Name)
			.getPO(getHR_Documents_ID(), get_TrxName());	}

	/** Set Documents.
		@param HR_Documents_ID Documents	  */
	public void setHR_Documents_ID (int HR_Documents_ID)
	{
		if (HR_Documents_ID < 1) 
			set_Value (COLUMNNAME_HR_Documents_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Documents_ID, Integer.valueOf(HR_Documents_ID));
	}

	/** Get Documents.
		@return Documents	  */
	public int getHR_Documents_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Documents_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}