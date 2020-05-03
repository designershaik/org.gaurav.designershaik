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

/** Generated Interface for DS_AssetEntry
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_AssetEntry 
{

    /** TableName=DS_AssetEntry */
    public static final String Table_Name = "DS_AssetEntry";

    /** AD_Table_ID=1000085 */
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

    /** Column name CategoryName */
    public static final String COLUMNNAME_CategoryName = "CategoryName";

	/** Set Category Name.
	  * Name of the Category
	  */
	public void setCategoryName (String CategoryName);

	/** Get Category Name.
	  * Name of the Category
	  */
	public String getCategoryName();

    /** Column name Comments */
    public static final String COLUMNNAME_Comments = "Comments";

	/** Set Comments.
	  * Comments or additional information
	  */
	public void setComments (String Comments);

	/** Get Comments.
	  * Comments or additional information
	  */
	public String getComments();

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

    /** Column name DS_AssetCode */
    public static final String COLUMNNAME_DS_AssetCode = "DS_AssetCode";

	/** Set Asset Code	  */
	public void setDS_AssetCode (String DS_AssetCode);

	/** Get Asset Code	  */
	public String getDS_AssetCode();

    /** Column name DS_AssetEntry_ID */
    public static final String COLUMNNAME_DS_AssetEntry_ID = "DS_AssetEntry_ID";

	/** Set Asset Entry	  */
	public void setDS_AssetEntry_ID (int DS_AssetEntry_ID);

	/** Get Asset Entry	  */
	public int getDS_AssetEntry_ID();

    /** Column name DS_AssetEntry_UU */
    public static final String COLUMNNAME_DS_AssetEntry_UU = "DS_AssetEntry_UU";

	/** Set DS_AssetEntry_UU	  */
	public void setDS_AssetEntry_UU (String DS_AssetEntry_UU);

	/** Get DS_AssetEntry_UU	  */
	public String getDS_AssetEntry_UU();

    /** Column name DS_Brand_ID */
    public static final String COLUMNNAME_DS_Brand_ID = "DS_Brand_ID";

	/** Set Asset Brand	  */
	public void setDS_Brand_ID (int DS_Brand_ID);

	/** Get Asset Brand	  */
	public int getDS_Brand_ID();

	public I_DS_Brand getDS_Brand() throws RuntimeException;

    /** Column name DS_Location_ID */
    public static final String COLUMNNAME_DS_Location_ID = "DS_Location_ID";

	/** Set Location	  */
	public void setDS_Location_ID (int DS_Location_ID);

	/** Get Location	  */
	public int getDS_Location_ID();

	public I_DS_Location getDS_Location() throws RuntimeException;

    /** Column name DS_ReasonForInActiveAsset */
    public static final String COLUMNNAME_DS_ReasonForInActiveAsset = "DS_ReasonForInActiveAsset";

	/** Set Reason for InActive Asset	  */
	public void setDS_ReasonForInActiveAsset (String DS_ReasonForInActiveAsset);

	/** Get Reason for InActive Asset	  */
	public String getDS_ReasonForInActiveAsset();

    /** Column name DS_SubLocation_ID */
    public static final String COLUMNNAME_DS_SubLocation_ID = "DS_SubLocation_ID";

	/** Set Sub Location	  */
	public void setDS_SubLocation_ID (int DS_SubLocation_ID);

	/** Get Sub Location	  */
	public int getDS_SubLocation_ID();

	public I_DS_SubLocation getDS_SubLocation() throws RuntimeException;

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

    /** Column name ManufacturedYear */
    public static final String COLUMNNAME_ManufacturedYear = "ManufacturedYear";

	/** Set Manufactured Year	  */
	public void setManufacturedYear (int ManufacturedYear);

	/** Get Manufactured Year	  */
	public int getManufacturedYear();

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

    /** Column name Qty */
    public static final String COLUMNNAME_Qty = "Qty";

	/** Set Quantity.
	  * Quantity
	  */
	public void setQty (BigDecimal Qty);

	/** Get Quantity.
	  * Quantity
	  */
	public BigDecimal getQty();

    /** Column name Status */
    public static final String COLUMNNAME_Status = "Status";

	/** Set Status.
	  * Status of the currently running check
	  */
	public void setStatus (String Status);

	/** Get Status.
	  * Status of the currently running check
	  */
	public String getStatus();

    /** Column name UPC */
    public static final String COLUMNNAME_UPC = "UPC";

	/** Set UPC/EAN.
	  * Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public void setUPC (String UPC);

	/** Get UPC/EAN.
	  * Bar Code (Universal Product Code or its superset European Article Number)
	  */
	public String getUPC();

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
