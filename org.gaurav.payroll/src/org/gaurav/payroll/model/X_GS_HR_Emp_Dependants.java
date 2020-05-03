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

/** Generated Model for GS_HR_Emp_Dependants
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_GS_HR_Emp_Dependants extends PO implements I_GS_HR_Emp_Dependants, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20190724L;

    /** Standard Constructor */
    public X_GS_HR_Emp_Dependants (Properties ctx, int GS_HR_Emp_Dependants_ID, String trxName)
    {
      super (ctx, GS_HR_Emp_Dependants_ID, trxName);
      /** if (GS_HR_Emp_Dependants_ID == 0)
        {
			setGS_HR_Emp_Dependants_ID (0);
			setGS_HR_FamilyRelation_ID (0);
			setGS_HR_VisaExpiryDate (new Timestamp( System.currentTimeMillis() ));
			setGS_HR_VisaStartDate (new Timestamp( System.currentTimeMillis() ));
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Emp_Dependants (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Emp_Dependants[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Employee Dependants.
		@param GS_HR_Emp_Dependants_ID Employee Dependants	  */
	public void setGS_HR_Emp_Dependants_ID (int GS_HR_Emp_Dependants_ID)
	{
		if (GS_HR_Emp_Dependants_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Emp_Dependants_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_GS_HR_Emp_Dependants_ID, Integer.valueOf(GS_HR_Emp_Dependants_ID));
	}

	/** Get Employee Dependants.
		@return Employee Dependants	  */
	public int getGS_HR_Emp_Dependants_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Emp_Dependants_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set GS_HR_Emp_Dependants_UU.
		@param GS_HR_Emp_Dependants_UU GS_HR_Emp_Dependants_UU	  */
	public void setGS_HR_Emp_Dependants_UU (String GS_HR_Emp_Dependants_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Emp_Dependants_UU, GS_HR_Emp_Dependants_UU);
	}

	/** Get GS_HR_Emp_Dependants_UU.
		@return GS_HR_Emp_Dependants_UU	  */
	public String getGS_HR_Emp_Dependants_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Emp_Dependants_UU);
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

	public I_GS_HR_FamilyRelation getGS_HR_FamilyRelation() throws RuntimeException
    {
		return (I_GS_HR_FamilyRelation)MTable.get(getCtx(), I_GS_HR_FamilyRelation.Table_Name)
			.getPO(getGS_HR_FamilyRelation_ID(), get_TrxName());	}

	/** Set Relationship.
		@param GS_HR_FamilyRelation_ID Relationship	  */
	public void setGS_HR_FamilyRelation_ID (int GS_HR_FamilyRelation_ID)
	{
		if (GS_HR_FamilyRelation_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_FamilyRelation_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_FamilyRelation_ID, Integer.valueOf(GS_HR_FamilyRelation_ID));
	}

	/** Get Relationship.
		@return Relationship	  */
	public int getGS_HR_FamilyRelation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_FamilyRelation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Visa Expiry Date.
		@param GS_HR_VisaExpiryDate Visa Expiry Date	  */
	public void setGS_HR_VisaExpiryDate (Timestamp GS_HR_VisaExpiryDate)
	{
		set_Value (COLUMNNAME_GS_HR_VisaExpiryDate, GS_HR_VisaExpiryDate);
	}

	/** Get Visa Expiry Date.
		@return Visa Expiry Date	  */
	public Timestamp getGS_HR_VisaExpiryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GS_HR_VisaExpiryDate);
	}

	/** Set Visa Start Date.
		@param GS_HR_VisaStartDate Visa Start Date	  */
	public void setGS_HR_VisaStartDate (Timestamp GS_HR_VisaStartDate)
	{
		set_Value (COLUMNNAME_GS_HR_VisaStartDate, GS_HR_VisaStartDate);
	}

	/** Get Visa Start Date.
		@return Visa Start Date	  */
	public Timestamp getGS_HR_VisaStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GS_HR_VisaStartDate);
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
		set_Value (COLUMNNAME_Phone, Phone);
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

	/** Set CPR/Social Security Code.
		@param SSCode CPR/Social Security Code	  */
	public void setSSCode (String SSCode)
	{
		set_Value (COLUMNNAME_SSCode, SSCode);
	}

	/** Get CPR/Social Security Code.
		@return CPR/Social Security Code	  */
	public String getSSCode () 
	{
		return (String)get_Value(COLUMNNAME_SSCode);
	}
}