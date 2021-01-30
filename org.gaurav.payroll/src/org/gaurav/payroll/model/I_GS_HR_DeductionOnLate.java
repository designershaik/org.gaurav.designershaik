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
package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for GS_HR_DeductionOnLate
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_DeductionOnLate 
{

    /** TableName=GS_HR_DeductionOnLate */
    public static final String Table_Name = "GS_HR_DeductionOnLate";

    /** AD_Table_ID=1000265 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name C_Calendar_ID */
    public static final String COLUMNNAME_C_Calendar_ID = "C_Calendar_ID";

	/** Set Calendar.
	  * Accounting Calendar Name
	  */
	public void setC_Calendar_ID (int C_Calendar_ID);

	/** Get Calendar.
	  * Accounting Calendar Name
	  */
	public int getC_Calendar_ID();

	public org.compiere.model.I_C_Calendar getC_Calendar() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name DS_In */
    public static final String COLUMNNAME_DS_In = "DS_In";

	/** Set In Time	  */
	public void setDS_In (Timestamp DS_In);

	/** Get In Time	  */
	public Timestamp getDS_In();

    /** Column name DS_Out */
    public static final String COLUMNNAME_DS_Out = "DS_Out";

	/** Set Out Time	  */
	public void setDS_Out (Timestamp DS_Out);

	/** Get Out Time	  */
	public Timestamp getDS_Out();

    /** Column name GS_HR_DeductionByHour */
    public static final String COLUMNNAME_GS_HR_DeductionByHour = "GS_HR_DeductionByHour";

	/** Set Deduct Salary By Hour.
	  * Deduct Salary By Hour
	  */
	public void setGS_HR_DeductionByHour (BigDecimal GS_HR_DeductionByHour);

	/** Get Deduct Salary By Hour.
	  * Deduct Salary By Hour
	  */
	public BigDecimal getGS_HR_DeductionByHour();

    /** Column name GS_HR_DeductionOnLate_ID */
    public static final String COLUMNNAME_GS_HR_DeductionOnLate_ID = "GS_HR_DeductionOnLate_ID";

	/** Set Deduction On Late	  */
	public void setGS_HR_DeductionOnLate_ID (int GS_HR_DeductionOnLate_ID);

	/** Get Deduction On Late	  */
	public int getGS_HR_DeductionOnLate_ID();

    /** Column name GS_HR_DeductionOnLate_UU */
    public static final String COLUMNNAME_GS_HR_DeductionOnLate_UU = "GS_HR_DeductionOnLate_UU";

	/** Set GS_HR_DeductionOnLate_UU	  */
	public void setGS_HR_DeductionOnLate_UU (String GS_HR_DeductionOnLate_UU);

	/** Get GS_HR_DeductionOnLate_UU	  */
	public String getGS_HR_DeductionOnLate_UU();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();
}
