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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for HR_Contract
 *  @author iDempiere (generated) 
 *  @version Release 5.1 - $Id$ */
public class X_HR_Contract extends PO implements I_HR_Contract, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20191223L;

    /** Standard Constructor */
    public X_HR_Contract (Properties ctx, int HR_Contract_ID, String trxName)
    {
      super (ctx, HR_Contract_ID, trxName);
      /** if (HR_Contract_ID == 0)
        {
			setHR_Contract_ID (0);
			setHR_DocNumber (null);
			setHR_Documents_ID (0);
        } */
    }

    /** Load Constructor */
    public X_HR_Contract (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_HR_Contract[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException
    {
		return (org.compiere.model.I_C_Campaign)MTable.get(getCtx(), org.compiere.model.I_C_Campaign.Table_Name)
			.getPO(getC_Campaign_ID(), get_TrxName());	}

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
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

	/** Set Contract Date.
		@param DateContract 
		The (planned) effective date of this document.
	  */
	public void setDateContract (Timestamp DateContract)
	{
		set_Value (COLUMNNAME_DateContract, DateContract);
	}

	/** Get Contract Date.
		@return The (planned) effective date of this document.
	  */
	public Timestamp getDateContract () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateContract);
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

	public I_GS_HR_Nationality getGS_HR_Nationality() throws RuntimeException
    {
		return (I_GS_HR_Nationality)MTable.get(getCtx(), I_GS_HR_Nationality.Table_Name)
			.getPO(getGS_HR_Nationality_ID(), get_TrxName());	}

	/** Set Nationality.
		@param GS_HR_Nationality_ID Nationality	  */
	public void setGS_HR_Nationality_ID (int GS_HR_Nationality_ID)
	{
		if (GS_HR_Nationality_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Nationality_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Nationality_ID, Integer.valueOf(GS_HR_Nationality_ID));
	}

	/** Get Nationality.
		@return Nationality	  */
	public int getGS_HR_Nationality_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_Nationality_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Salary Total.
		@param GS_HR_TotalSalary 
		Salary Total
	  */
	public void setGS_HR_TotalSalary (BigDecimal GS_HR_TotalSalary)
	{
		set_Value (COLUMNNAME_GS_HR_TotalSalary, GS_HR_TotalSalary);
	}

	/** Get Salary Total.
		@return Salary Total
	  */
	public BigDecimal getGS_HR_TotalSalary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_GS_HR_TotalSalary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Payroll Contract.
		@param HR_Contract_ID Payroll Contract	  */
	public void setHR_Contract_ID (int HR_Contract_ID)
	{
		if (HR_Contract_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_HR_Contract_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_HR_Contract_ID, Integer.valueOf(HR_Contract_ID));
	}

	/** Get Payroll Contract.
		@return Payroll Contract	  */
	public int getHR_Contract_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Contract_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HR_Contract_UU.
		@param HR_Contract_UU HR_Contract_UU	  */
	public void setHR_Contract_UU (String HR_Contract_UU)
	{
		set_Value (COLUMNNAME_HR_Contract_UU, HR_Contract_UU);
	}

	/** Get HR_Contract_UU.
		@return HR_Contract_UU	  */
	public String getHR_Contract_UU () 
	{
		return (String)get_Value(COLUMNNAME_HR_Contract_UU);
	}

	public org.eevolution.model.I_HR_Department getHR_Department() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Department)MTable.get(getCtx(), org.eevolution.model.I_HR_Department.Table_Name)
			.getPO(getHR_Department_ID(), get_TrxName());	}

	/** Set Payroll Department.
		@param HR_Department_ID Payroll Department	  */
	public void setHR_Department_ID (int HR_Department_ID)
	{
		if (HR_Department_ID < 1) 
			set_Value (COLUMNNAME_HR_Department_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Department_ID, Integer.valueOf(HR_Department_ID));
	}

	/** Get Payroll Department.
		@return Payroll Department	  */
	public int getHR_Department_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Department_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.eevolution.model.I_HR_Job getHR_Job() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Job)MTable.get(getCtx(), org.eevolution.model.I_HR_Job.Table_Name)
			.getPO(getHR_Job_ID(), get_TrxName());	}

	/** Set Payroll Job.
		@param HR_Job_ID Payroll Job	  */
	public void setHR_Job_ID (int HR_Job_ID)
	{
		if (HR_Job_ID < 1) 
			set_Value (COLUMNNAME_HR_Job_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Job_ID, Integer.valueOf(HR_Job_ID));
	}

	/** Get Payroll Job.
		@return Payroll Job	  */
	public int getHR_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Net Days.
		@param NetDays 
		Net Days in which payment is due
	  */
	public void setNetDays (int NetDays)
	{
		set_Value (COLUMNNAME_NetDays, Integer.valueOf(NetDays));
	}

	/** Get Net Days.
		@return Net Days in which payment is due
	  */
	public int getNetDays () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_NetDays);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Process Now.
		@param Processing Process Now	  */
	public void setProcessing (boolean Processing)
	{
		set_Value (COLUMNNAME_Processing, Boolean.valueOf(Processing));
	}

	/** Get Process Now.
		@return Process Now	  */
	public boolean isProcessing () 
	{
		Object oo = get_Value(COLUMNNAME_Processing);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Valid from.
		@param ValidFrom 
		Valid from including this date (first day)
	  */
	public void setValidFrom (Timestamp ValidFrom)
	{
		set_Value (COLUMNNAME_ValidFrom, ValidFrom);
	}

	/** Get Valid from.
		@return Valid from including this date (first day)
	  */
	public Timestamp getValidFrom () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidFrom);
	}

	/** Set Valid to.
		@param ValidTo 
		Valid to including this date (last day)
	  */
	public void setValidTo (Timestamp ValidTo)
	{
		set_Value (COLUMNNAME_ValidTo, ValidTo);
	}

	/** Get Valid to.
		@return Valid to including this date (last day)
	  */
	public Timestamp getValidTo () 
	{
		return (Timestamp)get_Value(COLUMNNAME_ValidTo);
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}