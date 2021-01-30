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

/** Generated Interface for GS_HR_TerminalDetails
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_GS_HR_TerminalDetails 
{

    /** TableName=GS_HR_TerminalDetails */
    public static final String Table_Name = "GS_HR_TerminalDetails";

    /** AD_Table_ID=1000130 */
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

    /** Column name GS_HR_TerminalDetails_ID */
    public static final String COLUMNNAME_GS_HR_TerminalDetails_ID = "GS_HR_TerminalDetails_ID";

	/** Set Attendance Terminals	  */
	public void setGS_HR_TerminalDetails_ID (int GS_HR_TerminalDetails_ID);

	/** Get Attendance Terminals	  */
	public int getGS_HR_TerminalDetails_ID();

    /** Column name GS_HR_TerminalDetails_UU */
    public static final String COLUMNNAME_GS_HR_TerminalDetails_UU = "GS_HR_TerminalDetails_UU";

	/** Set GS_HR_TerminalDetails_UU	  */
	public void setGS_HR_TerminalDetails_UU (String GS_HR_TerminalDetails_UU);

	/** Get GS_HR_TerminalDetails_UU	  */
	public String getGS_HR_TerminalDetails_UU();

    /** Column name GS_HR_TerminalSN */
    public static final String COLUMNNAME_GS_HR_TerminalSN = "GS_HR_TerminalSN";

	/** Set Terminal SN	  */
	public void setGS_HR_TerminalSN (String GS_HR_TerminalSN);

	/** Get Terminal SN	  */
	public String getGS_HR_TerminalSN();

    /** Column name GS_HR_TerminalWSDL */
    public static final String COLUMNNAME_GS_HR_TerminalWSDL = "GS_HR_TerminalWSDL";

	/** Set Terminal URL.
	  * Terminal URL will be used to get the connect to terminal
	  */
	public void setGS_HR_TerminalWSDL (String GS_HR_TerminalWSDL);

	/** Get Terminal URL.
	  * Terminal URL will be used to get the connect to terminal
	  */
	public String getGS_HR_TerminalWSDL();

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

    /** Column name LoginName */
    public static final String COLUMNNAME_LoginName = "LoginName";

	/** Set User Name	  */
	public void setLoginName (String LoginName);

	/** Get User Name	  */
	public String getLoginName();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

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
