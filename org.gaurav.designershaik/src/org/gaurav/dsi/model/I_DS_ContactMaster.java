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
package org.gaurav.dsi.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DS_ContactMaster
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_DS_ContactMaster 
{

    /** TableName=DS_ContactMaster */
    public static final String Table_Name = "DS_ContactMaster";

    /** AD_Table_ID=1000000 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_EMail */
    public static final String COLUMNNAME_A_EMail = "A_EMail";

	/** Set Account EMail.
	  * Email Address
	  */
	public void setA_EMail (String A_EMail);

	/** Get Account EMail.
	  * Email Address
	  */
	public String getA_EMail();

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

    /** Column name AD_User_ID */
    public static final String COLUMNNAME_AD_User_ID = "AD_User_ID";

	/** Set User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID);

	/** Get User/Contact.
	  * User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID();

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException;

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_Location_ID */
    public static final String COLUMNNAME_C_Location_ID = "C_Location_ID";

	/** Set Address.
	  * Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID);

	/** Get Address.
	  * Location or Address
	  */
	public int getC_Location_ID();

	public I_C_Location getC_Location() throws RuntimeException;

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

    /** Column name DS_ContactMaster_ID */
    public static final String COLUMNNAME_DS_ContactMaster_ID = "DS_ContactMaster_ID";

	/** Set Contact Master	  */
	public void setDS_ContactMaster_ID (int DS_ContactMaster_ID);

	/** Get Contact Master	  */
	public int getDS_ContactMaster_ID();

    /** Column name DS_ContactMaster_UU */
    public static final String COLUMNNAME_DS_ContactMaster_UU = "DS_ContactMaster_UU";

	/** Set DS_ContactMaster_UU	  */
	public void setDS_ContactMaster_UU (String DS_ContactMaster_UU);

	/** Get DS_ContactMaster_UU	  */
	public String getDS_ContactMaster_UU();

    /** Column name DS_IsPerson */
    public static final String COLUMNNAME_DS_IsPerson = "DS_IsPerson";

	/** Set Individual	  */
	public void setDS_IsPerson (boolean DS_IsPerson);

	/** Get Individual	  */
	public boolean isDS_IsPerson();

    /** Column name DS_OrgMaster_ID */
    public static final String COLUMNNAME_DS_OrgMaster_ID = "DS_OrgMaster_ID";

	/** Set Organization Master	  */
	public void setDS_OrgMaster_ID (int DS_OrgMaster_ID);

	/** Get Organization Master	  */
	public int getDS_OrgMaster_ID();

	public I_DS_OrgMaster getDS_OrgMaster() throws RuntimeException;

    /** Column name DS_Phone_Ext1 */
    public static final String COLUMNNAME_DS_Phone_Ext1 = "DS_Phone_Ext1";

	/** Set Extension Phone1	  */
	public void setDS_Phone_Ext1 (String DS_Phone_Ext1);

	/** Get Extension Phone1	  */
	public String getDS_Phone_Ext1();

    /** Column name DS_Phone_Ext2 */
    public static final String COLUMNNAME_DS_Phone_Ext2 = "DS_Phone_Ext2";

	/** Set Extension Phone2	  */
	public void setDS_Phone_Ext2 (String DS_Phone_Ext2);

	/** Get Extension Phone2	  */
	public String getDS_Phone_Ext2();

    /** Column name EMail */
    public static final String COLUMNNAME_EMail = "EMail";

	/** Set EMail Address.
	  * Electronic Mail Address
	  */
	public void setEMail (String EMail);

	/** Get EMail Address.
	  * Electronic Mail Address
	  */
	public String getEMail();

    /** Column name Fax */
    public static final String COLUMNNAME_Fax = "Fax";

	/** Set Fax.
	  * Facsimile number
	  */
	public void setFax (String Fax);

	/** Get Fax.
	  * Facsimile number
	  */
	public String getFax();

    /** Column name Greeting */
    public static final String COLUMNNAME_Greeting = "Greeting";

	/** Set Greeting.
	  * For letters, e.g. "Dear 
{
0}
" or "Dear Mr. 
{
0}
" - At runtime, "
{
0}
" is replaced by the name
	  */
	public void setGreeting (String Greeting);

	/** Get Greeting.
	  * For letters, e.g. "Dear 
{
0}
" or "Dear Mr. 
{
0}
" - At runtime, "
{
0}
" is replaced by the name
	  */
	public String getGreeting();

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

    /** Column name Phone */
    public static final String COLUMNNAME_Phone = "Phone";

	/** Set Landline.
	  * Identifies a telephone number
	  */
	public void setPhone (String Phone);

	/** Get Landline.
	  * Identifies a telephone number
	  */
	public String getPhone();

    /** Column name Phone2 */
    public static final String COLUMNNAME_Phone2 = "Phone2";

	/** Set Mobile No./LandLine 2.
	  * Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2);

	/** Get Mobile No./LandLine 2.
	  * Identifies an alternate telephone number.
	  */
	public String getPhone2();

    /** Column name Title */
    public static final String COLUMNNAME_Title = "Title";

	/** Set Title.
	  * Name this entity is referred to as
	  */
	public void setTitle (String Title);

	/** Get Title.
	  * Name this entity is referred to as
	  */
	public String getTitle();

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

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value);

	/** Get Search Key.
	  * Search key for the record in the format required - must be unique
	  */
	public String getValue();

    /** Column name WebSite */
    public static final String COLUMNNAME_WebSite = "WebSite";

	/** Set WebSite	  */
	public void setWebSite (String WebSite);

	/** Get WebSite	  */
	public String getWebSite();
}
