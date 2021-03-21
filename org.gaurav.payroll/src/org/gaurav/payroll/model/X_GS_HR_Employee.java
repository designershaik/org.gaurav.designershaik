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
import org.compiere.util.KeyNamePair;

/** Generated Model for GS_HR_Employee
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_GS_HR_Employee extends PO implements I_GS_HR_Employee, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210321L;

    /** Standard Constructor */
    public X_GS_HR_Employee (Properties ctx, int GS_HR_Employee_ID, String trxName)
    {
      super (ctx, GS_HR_Employee_ID, trxName);
      /** if (GS_HR_Employee_ID == 0)
        {
			setC_BPartner_ID (0);
			setGS_HR_Employee_ID (0);
			setStartDate (new Timestamp( System.currentTimeMillis() ));
        } */
    }

    /** Load Constructor */
    public X_GS_HR_Employee (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_GS_HR_Employee[")
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
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
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

	/** Set Birthday.
		@param Birthday 
		Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Cost Center.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Cost Center.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
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

	/** Set Validation code.
		@param Code 
		Validation Code
	  */
	public void setCode (int Code)
	{
		set_Value (COLUMNNAME_Code, Integer.valueOf(Code));
	}

	/** Get Validation code.
		@return Validation Code
	  */
	public int getCode () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Code);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Overtime Regular 1 = Overtime Regular 1 */
	public static final String DS_OVERTIMETYPE_OvertimeRegular1 = "Overtime Regular 1";
	/** Overtime Premium 1 = Overtime Premium 1 */
	public static final String DS_OVERTIMETYPE_OvertimePremium1 = "Overtime Premium 1";
	/** Overtime Regular 2 = Overtime Regular 2 */
	public static final String DS_OVERTIMETYPE_OvertimeRegular2 = "Overtime Regular 2";
	/** Overtime Regular 3 = Overtime Regular 3 */
	public static final String DS_OVERTIMETYPE_OvertimeRegular3 = "Overtime Regular 3";
	/** Overtime Premium 2 = Overtime Premium 2 */
	public static final String DS_OVERTIMETYPE_OvertimePremium2 = "Overtime Premium 2";
	/** Set Overtime Type.
		@param DS_OvertimeType 
		Overtime Type
	  */
	public void setDS_OvertimeType (String DS_OvertimeType)
	{

		set_Value (COLUMNNAME_DS_OvertimeType, DS_OvertimeType);
	}

	/** Get Overtime Type.
		@return Overtime Type
	  */
	public String getDS_OvertimeType () 
	{
		return (String)get_Value(COLUMNNAME_DS_OvertimeType);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set CPR Designation.
		@param GS_CPRDDesignation 
		CPR Designation
	  */
	public void setGS_CPRDDesignation (String GS_CPRDDesignation)
	{
		set_Value (COLUMNNAME_GS_CPRDDesignation, GS_CPRDDesignation);
	}

	/** Get CPR Designation.
		@return CPR Designation
	  */
	public String getGS_CPRDDesignation () 
	{
		return (String)get_Value(COLUMNNAME_GS_CPRDDesignation);
	}

	/** O+ = O+ */
	public static final String GS_HR_BLOODGROUP_OPlus = "O+";
	/** O- = O- */
	public static final String GS_HR_BLOODGROUP_O_ = "O-";
	/** A+ = A+ */
	public static final String GS_HR_BLOODGROUP_APlus = "A+";
	/** A- = A- */
	public static final String GS_HR_BLOODGROUP_A_ = "A-";
	/** B- = B- */
	public static final String GS_HR_BLOODGROUP_B_ = "B-";
	/** B+ = B+ */
	public static final String GS_HR_BLOODGROUP_BPlus = "B+";
	/** AB+ = AB+ */
	public static final String GS_HR_BLOODGROUP_ABPlus = "AB+";
	/** AB- = AB- */
	public static final String GS_HR_BLOODGROUP_AB_ = "AB-";
	/** Set Blood Group.
		@param GS_HR_BloodGroup Blood Group	  */
	public void setGS_HR_BloodGroup (String GS_HR_BloodGroup)
	{

		set_Value (COLUMNNAME_GS_HR_BloodGroup, GS_HR_BloodGroup);
	}

	/** Get Blood Group.
		@return Blood Group	  */
	public String getGS_HR_BloodGroup () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_BloodGroup);
	}

	/** Set Date Of Permanency.
		@param GS_HR_DateOfPermanency 
		Date Of Permanency
	  */
	public void setGS_HR_DateOfPermanency (Timestamp GS_HR_DateOfPermanency)
	{
		set_Value (COLUMNNAME_GS_HR_DateOfPermanency, GS_HR_DateOfPermanency);
	}

	/** Get Date Of Permanency.
		@return Date Of Permanency
	  */
	public Timestamp getGS_HR_DateOfPermanency () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GS_HR_DateOfPermanency);
	}

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

	/** Set GS_HR_Employee_UU.
		@param GS_HR_Employee_UU GS_HR_Employee_UU	  */
	public void setGS_HR_Employee_UU (String GS_HR_Employee_UU)
	{
		set_Value (COLUMNNAME_GS_HR_Employee_UU, GS_HR_Employee_UU);
	}

	/** Get GS_HR_Employee_UU.
		@return GS_HR_Employee_UU	  */
	public String getGS_HR_Employee_UU () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Employee_UU);
	}

	/** Set Exempt from Late Deduction.
		@param GS_HR_ExemptFromLateDeduction 
		Exempt from Late Deduction
	  */
	public void setGS_HR_ExemptFromLateDeduction (boolean GS_HR_ExemptFromLateDeduction)
	{
		set_Value (COLUMNNAME_GS_HR_ExemptFromLateDeduction, Boolean.valueOf(GS_HR_ExemptFromLateDeduction));
	}

	/** Get Exempt from Late Deduction.
		@return Exempt from Late Deduction
	  */
	public boolean isGS_HR_ExemptFromLateDeduction () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_ExemptFromLateDeduction);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is Terminated.
		@param GS_HR_IsTerminate Is Terminated	  */
	public void setGS_HR_IsTerminate (boolean GS_HR_IsTerminate)
	{
		set_Value (COLUMNNAME_GS_HR_IsTerminate, Boolean.valueOf(GS_HR_IsTerminate));
	}

	/** Get Is Terminated.
		@return Is Terminated	  */
	public boolean isGS_HR_IsTerminate () 
	{
		Object oo = get_Value(COLUMNNAME_GS_HR_IsTerminate);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Married = M */
	public static final String GS_HR_MARITALSTATUS_Married = "M";
	/** Single = S */
	public static final String GS_HR_MARITALSTATUS_Single = "S";
	/** Unmarried = U */
	public static final String GS_HR_MARITALSTATUS_Unmarried = "U";
	/** Set Marital Status.
		@param GS_HR_MaritalStatus Marital Status	  */
	public void setGS_HR_MaritalStatus (String GS_HR_MaritalStatus)
	{

		set_Value (COLUMNNAME_GS_HR_MaritalStatus, GS_HR_MaritalStatus);
	}

	/** Get Marital Status.
		@return Marital Status	  */
	public String getGS_HR_MaritalStatus () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_MaritalStatus);
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

	/** Set Probation End Date.
		@param GS_HR_ProbationEndDate 
		Probation End Date
	  */
	public void setGS_HR_ProbationEndDate (Timestamp GS_HR_ProbationEndDate)
	{
		set_Value (COLUMNNAME_GS_HR_ProbationEndDate, GS_HR_ProbationEndDate);
	}

	/** Get Probation End Date.
		@return Probation End Date
	  */
	public Timestamp getGS_HR_ProbationEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_GS_HR_ProbationEndDate);
	}

	/** Set Remaining Installment.
		@param GS_HR_RemainingInstallments 
		Remaining Installment
	  */
	public void setGS_HR_RemainingInstallments (int GS_HR_RemainingInstallments)
	{
		throw new IllegalArgumentException ("GS_HR_RemainingInstallments is virtual column");	}

	/** Get Remaining Installment.
		@return Remaining Installment
	  */
	public int getGS_HR_RemainingInstallments () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_RemainingInstallments);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Terminate Employee.
		@param GS_HR_Terminate Terminate Employee	  */
	public void setGS_HR_Terminate (String GS_HR_Terminate)
	{
		set_Value (COLUMNNAME_GS_HR_Terminate, GS_HR_Terminate);
	}

	/** Get Terminate Employee.
		@return Terminate Employee	  */
	public String getGS_HR_Terminate () 
	{
		return (String)get_Value(COLUMNNAME_GS_HR_Terminate);
	}

	public I_GS_HR_TimeSlot_Group getGS_HR_TimeSlot_Group() throws RuntimeException
    {
		return (I_GS_HR_TimeSlot_Group)MTable.get(getCtx(), I_GS_HR_TimeSlot_Group.Table_Name)
			.getPO(getGS_HR_TimeSlot_Group_ID(), get_TrxName());	}

	/** Set Time Slot.
		@param GS_HR_TimeSlot_Group_ID Time Slot	  */
	public void setGS_HR_TimeSlot_Group_ID (int GS_HR_TimeSlot_Group_ID)
	{
		if (GS_HR_TimeSlot_Group_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_TimeSlot_Group_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_TimeSlot_Group_ID, Integer.valueOf(GS_HR_TimeSlot_Group_ID));
	}

	/** Get Time Slot.
		@return Time Slot	  */
	public int getGS_HR_TimeSlot_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GS_HR_TimeSlot_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_GS_HR_Warnings getGS_HR_Warnings() throws RuntimeException
    {
		return (I_GS_HR_Warnings)MTable.get(getCtx(), I_GS_HR_Warnings.Table_Name)
			.getPO(getGS_HR_Warnings_ID(), get_TrxName());	}

	/** Set Warnings/Notices.
		@param GS_HR_Warnings_ID Warnings/Notices	  */
	public void setGS_HR_Warnings_ID (int GS_HR_Warnings_ID)
	{
		if (GS_HR_Warnings_ID < 1) 
			set_Value (COLUMNNAME_GS_HR_Warnings_ID, null);
		else 
			set_Value (COLUMNNAME_GS_HR_Warnings_ID, Integer.valueOf(GS_HR_Warnings_ID));
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

	/** Set Termination Reason.
		@param GS_TerminationReason Termination Reason	  */
	public void setGS_TerminationReason (String GS_TerminationReason)
	{
		set_Value (COLUMNNAME_GS_TerminationReason, GS_TerminationReason);
	}

	/** Get Termination Reason.
		@return Termination Reason	  */
	public String getGS_TerminationReason () 
	{
		return (String)get_Value(COLUMNNAME_GS_TerminationReason);
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

	/** Set Name 2.
		@param Name2 
		Additional Name
	  */
	public void setName2 (String Name2)
	{
		set_Value (COLUMNNAME_Name2, Name2);
	}

	/** Get Name 2.
		@return Additional Name
	  */
	public String getName2 () 
	{
		return (String)get_Value(COLUMNNAME_Name2);
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

	public org.compiere.model.I_S_Resource getS_Resource() throws RuntimeException
    {
		return (org.compiere.model.I_S_Resource)MTable.get(getCtx(), org.compiere.model.I_S_Resource.Table_Name)
			.getPO(getS_Resource_ID(), get_TrxName());	}

	/** Set Resource.
		@param S_Resource_ID 
		Resource
	  */
	public void setS_Resource_ID (int S_Resource_ID)
	{
		if (S_Resource_ID < 1) 
			set_Value (COLUMNNAME_S_Resource_ID, null);
		else 
			set_Value (COLUMNNAME_S_Resource_ID, Integer.valueOf(S_Resource_ID));
	}

	/** Get Resource.
		@return Resource
	  */
	public int getS_Resource_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_S_Resource_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Signature.
		@param Signature Signature	  */
	public void setSignature (Object Signature)
	{
		set_Value (COLUMNNAME_Signature, Signature);
	}

	/** Get Signature.
		@return Signature	  */
	public Object getSignature () 
	{
				return get_Value(COLUMNNAME_Signature);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}
}