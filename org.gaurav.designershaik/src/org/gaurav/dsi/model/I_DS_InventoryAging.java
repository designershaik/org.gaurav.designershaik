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

/** Generated Interface for DS_InventoryAging
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DS_InventoryAging 
{

    /** TableName=DS_InventoryAging */
    public static final String Table_Name = "DS_InventoryAging";

    /** AD_Table_ID=1000121 */
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

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

    /** Column name DS_InvAmtBetween0To1Month */
    public static final String COLUMNNAME_DS_InvAmtBetween0To1Month = "DS_InvAmtBetween0To1Month";

	/** Set Inventory Amt Less than One Month	  */
	public void setDS_InvAmtBetween0To1Month (BigDecimal DS_InvAmtBetween0To1Month);

	/** Get Inventory Amt Less than One Month	  */
	public BigDecimal getDS_InvAmtBetween0To1Month();

    /** Column name DS_InvAmtBetween1To2Months */
    public static final String COLUMNNAME_DS_InvAmtBetween1To2Months = "DS_InvAmtBetween1To2Months";

	/** Set Inventory Amt between one & Two Month	  */
	public void setDS_InvAmtBetween1To2Months (BigDecimal DS_InvAmtBetween1To2Months);

	/** Get Inventory Amt between one & Two Month	  */
	public BigDecimal getDS_InvAmtBetween1To2Months();

    /** Column name DS_InvAmtBetween2To3Months */
    public static final String COLUMNNAME_DS_InvAmtBetween2To3Months = "DS_InvAmtBetween2To3Months";

	/** Set Inventory Amt between 1 & 2 Months	  */
	public void setDS_InvAmtBetween2To3Months (BigDecimal DS_InvAmtBetween2To3Months);

	/** Get Inventory Amt between 1 & 2 Months	  */
	public BigDecimal getDS_InvAmtBetween2To3Months();

    /** Column name DS_InvAmtBetween3To4Months */
    public static final String COLUMNNAME_DS_InvAmtBetween3To4Months = "DS_InvAmtBetween3To4Months";

	/** Set Inventory Amt between 3 & 4 Months	  */
	public void setDS_InvAmtBetween3To4Months (BigDecimal DS_InvAmtBetween3To4Months);

	/** Get Inventory Amt between 3 & 4 Months	  */
	public BigDecimal getDS_InvAmtBetween3To4Months();

    /** Column name DS_InvAmtBetween4To5Months */
    public static final String COLUMNNAME_DS_InvAmtBetween4To5Months = "DS_InvAmtBetween4To5Months";

	/** Set Inventory Amt between 4 & 5 Months	  */
	public void setDS_InvAmtBetween4To5Months (BigDecimal DS_InvAmtBetween4To5Months);

	/** Get Inventory Amt between 4 & 5 Months	  */
	public BigDecimal getDS_InvAmtBetween4To5Months();

    /** Column name DS_InvAmtBetween5To6Months */
    public static final String COLUMNNAME_DS_InvAmtBetween5To6Months = "DS_InvAmtBetween5To6Months";

	/** Set Inventory Amt between 5 & 6 Months	  */
	public void setDS_InvAmtBetween5To6Months (BigDecimal DS_InvAmtBetween5To6Months);

	/** Get Inventory Amt between 5 & 6 Months	  */
	public BigDecimal getDS_InvAmtBetween5To6Months();

    /** Column name DS_InvAmtMorethan6Months */
    public static final String COLUMNNAME_DS_InvAmtMorethan6Months = "DS_InvAmtMorethan6Months";

	/** Set Inventory Amt More than 6 months	  */
	public void setDS_InvAmtMorethan6Months (BigDecimal DS_InvAmtMorethan6Months);

	/** Get Inventory Amt More than 6 months	  */
	public BigDecimal getDS_InvAmtMorethan6Months();

    /** Column name DS_InvBetween0To1Month */
    public static final String COLUMNNAME_DS_InvBetween0To1Month = "DS_InvBetween0To1Month";

	/** Set Inventory Age Less than One Month	  */
	public void setDS_InvBetween0To1Month (BigDecimal DS_InvBetween0To1Month);

	/** Get Inventory Age Less than One Month	  */
	public BigDecimal getDS_InvBetween0To1Month();

    /** Column name DS_InvBetween1To2Months */
    public static final String COLUMNNAME_DS_InvBetween1To2Months = "DS_InvBetween1To2Months";

	/** Set Inventory between one & Two Month	  */
	public void setDS_InvBetween1To2Months (BigDecimal DS_InvBetween1To2Months);

	/** Get Inventory between one & Two Month	  */
	public BigDecimal getDS_InvBetween1To2Months();

    /** Column name DS_InvBetween2To3Months */
    public static final String COLUMNNAME_DS_InvBetween2To3Months = "DS_InvBetween2To3Months";

	/** Set Inventory between 1 & 2 Months	  */
	public void setDS_InvBetween2To3Months (BigDecimal DS_InvBetween2To3Months);

	/** Get Inventory between 1 & 2 Months	  */
	public BigDecimal getDS_InvBetween2To3Months();

    /** Column name DS_InvBetween3To4Months */
    public static final String COLUMNNAME_DS_InvBetween3To4Months = "DS_InvBetween3To4Months";

	/** Set Inventory between 3 & 4 Months	  */
	public void setDS_InvBetween3To4Months (BigDecimal DS_InvBetween3To4Months);

	/** Get Inventory between 3 & 4 Months	  */
	public BigDecimal getDS_InvBetween3To4Months();

    /** Column name DS_InvBetween4To5Months */
    public static final String COLUMNNAME_DS_InvBetween4To5Months = "DS_InvBetween4To5Months";

	/** Set Inventory between 4 & 5 Months	  */
	public void setDS_InvBetween4To5Months (BigDecimal DS_InvBetween4To5Months);

	/** Get Inventory between 4 & 5 Months	  */
	public BigDecimal getDS_InvBetween4To5Months();

    /** Column name DS_InvBetween5To6Months */
    public static final String COLUMNNAME_DS_InvBetween5To6Months = "DS_InvBetween5To6Months";

	/** Set Inventory between 5 & 6 Months	  */
	public void setDS_InvBetween5To6Months (BigDecimal DS_InvBetween5To6Months);

	/** Get Inventory between 5 & 6 Months	  */
	public BigDecimal getDS_InvBetween5To6Months();

    /** Column name DS_InventoryAging_ID */
    public static final String COLUMNNAME_DS_InventoryAging_ID = "DS_InventoryAging_ID";

	/** Set Inventory Aging	  */
	public void setDS_InventoryAging_ID (int DS_InventoryAging_ID);

	/** Get Inventory Aging	  */
	public int getDS_InventoryAging_ID();

    /** Column name DS_InventoryAging_UU */
    public static final String COLUMNNAME_DS_InventoryAging_UU = "DS_InventoryAging_UU";

	/** Set DS_InventoryAging_UU	  */
	public void setDS_InventoryAging_UU (String DS_InventoryAging_UU);

	/** Get DS_InventoryAging_UU	  */
	public String getDS_InventoryAging_UU();

    /** Column name DS_InvMorethan6Months */
    public static final String COLUMNNAME_DS_InvMorethan6Months = "DS_InvMorethan6Months";

	/** Set Inventory More than 6 months	  */
	public void setDS_InvMorethan6Months (BigDecimal DS_InvMorethan6Months);

	/** Get Inventory More than 6 months	  */
	public BigDecimal getDS_InvMorethan6Months();

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

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

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
