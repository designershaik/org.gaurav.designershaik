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
package com.gaurav.dsi.manufacturing.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DS_Mnfg_MaterialMeasurement
 *  @author iDempiere (generated) 
 *  @version Release 5.1
 */
@SuppressWarnings("all")
public interface I_DS_Mnfg_MaterialMeasurement 
{

    /** TableName=DS_Mnfg_MaterialMeasurement */
    public static final String Table_Name = "DS_Mnfg_MaterialMeasurement";

    /** AD_Table_ID=1000202 */
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

    /** Column name DS_ContainerWeight */
    public static final String COLUMNNAME_DS_ContainerWeight = "DS_ContainerWeight";

	/** Set Container Weight.
	  * Container Weight
	  */
	public void setDS_ContainerWeight (BigDecimal DS_ContainerWeight);

	/** Get Container Weight.
	  * Container Weight
	  */
	public BigDecimal getDS_ContainerWeight();

    /** Column name DS_GrossWeight */
    public static final String COLUMNNAME_DS_GrossWeight = "DS_GrossWeight";

	/** Set Gross Weight.
	  * Gross Weight
	  */
	public void setDS_GrossWeight (BigDecimal DS_GrossWeight);

	/** Get Gross Weight.
	  * Gross Weight
	  */
	public BigDecimal getDS_GrossWeight();

    /** Column name DS_Mnfg_MaterialMeasurement_ID */
    public static final String COLUMNNAME_DS_Mnfg_MaterialMeasurement_ID = "DS_Mnfg_MaterialMeasurement_ID";

	/** Set Material Measurement	  */
	public void setDS_Mnfg_MaterialMeasurement_ID (int DS_Mnfg_MaterialMeasurement_ID);

	/** Get Material Measurement	  */
	public int getDS_Mnfg_MaterialMeasurement_ID();

    /** Column name DS_Mnfg_MaterialMeasurement_UU */
    public static final String COLUMNNAME_DS_Mnfg_MaterialMeasurement_UU = "DS_Mnfg_MaterialMeasurement_UU";

	/** Set DS_Mnfg_MaterialMeasurement_UU	  */
	public void setDS_Mnfg_MaterialMeasurement_UU (String DS_Mnfg_MaterialMeasurement_UU);

	/** Get DS_Mnfg_MaterialMeasurement_UU	  */
	public String getDS_Mnfg_MaterialMeasurement_UU();

    /** Column name DS_NetWeight */
    public static final String COLUMNNAME_DS_NetWeight = "DS_NetWeight";

	/** Set Net Weight.
	  * Net Weight
	  */
	public void setDS_NetWeight (BigDecimal DS_NetWeight);

	/** Get Net Weight.
	  * Net Weight
	  */
	public BigDecimal getDS_NetWeight();

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

    /** Column name PP_Order_BOMLine_ID */
    public static final String COLUMNNAME_PP_Order_BOMLine_ID = "PP_Order_BOMLine_ID";

	/** Set Manufacturing Order BOM Line	  */
	public void setPP_Order_BOMLine_ID (int PP_Order_BOMLine_ID);

	/** Get Manufacturing Order BOM Line	  */
	public int getPP_Order_BOMLine_ID();

	public org.eevolution.model.I_PP_Order_BOMLine getPP_Order_BOMLine() throws RuntimeException;

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
