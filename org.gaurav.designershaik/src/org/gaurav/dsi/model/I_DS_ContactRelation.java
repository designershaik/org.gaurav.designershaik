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

/** Generated Interface for DS_ContactRelation
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_DS_ContactRelation 
{

    /** TableName=DS_ContactRelation */
    public static final String Table_Name = "DS_ContactRelation";

    /** AD_Table_ID=1000012 */
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

    /** Column name DS_ContactMaster_ID */
    public static final String COLUMNNAME_DS_ContactMaster_ID = "DS_ContactMaster_ID";

	/** Set Contact Master	  */
	public void setDS_ContactMaster_ID (int DS_ContactMaster_ID);

	/** Get Contact Master	  */
	public int getDS_ContactMaster_ID();

	public I_DS_ContactMaster getDS_ContactMaster() throws RuntimeException;

    /** Column name DS_ContactRelation_ID */
    public static final String COLUMNNAME_DS_ContactRelation_ID = "DS_ContactRelation_ID";

	/** Set Relationship between Contacts	  */
	public void setDS_ContactRelation_ID (int DS_ContactRelation_ID);

	/** Get Relationship between Contacts	  */
	public int getDS_ContactRelation_ID();

    /** Column name DS_ContactRelation_UU */
    public static final String COLUMNNAME_DS_ContactRelation_UU = "DS_ContactRelation_UU";

	/** Set DS_ContactRelation_UU	  */
	public void setDS_ContactRelation_UU (String DS_ContactRelation_UU);

	/** Get DS_ContactRelation_UU	  */
	public String getDS_ContactRelation_UU();

    /** Column name DS_RelatedContact_ID */
    public static final String COLUMNNAME_DS_RelatedContact_ID = "DS_RelatedContact_ID";

	/** Set Related Contact	  */
	public void setDS_RelatedContact_ID (int DS_RelatedContact_ID);

	/** Get Related Contact	  */
	public int getDS_RelatedContact_ID();

	public I_DS_ContactMaster getDS_RelatedContact() throws RuntimeException;

    /** Column name DS_RelationShip_ID */
    public static final String COLUMNNAME_DS_RelationShip_ID = "DS_RelationShip_ID";

	/** Set Relationship Type	  */
	public void setDS_RelationShip_ID (int DS_RelationShip_ID);

	/** Get Relationship Type	  */
	public int getDS_RelationShip_ID();

	public I_DS_RelationShip getDS_RelationShip() throws RuntimeException;

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
