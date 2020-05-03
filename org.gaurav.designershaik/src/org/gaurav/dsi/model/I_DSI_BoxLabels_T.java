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

/** Generated Interface for DSI_BoxLabels_T
 *  @author iDempiere (generated) 
 *  @version Release 4.1
 */
@SuppressWarnings("all")
public interface I_DSI_BoxLabels_T 
{

    /** TableName=DSI_BoxLabels_T */
    public static final String Table_Name = "DSI_BoxLabels_T";

    /** AD_Table_ID=1000035 */
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

    /** Column name DSI_BoxLabels_T_ID */
    public static final String COLUMNNAME_DSI_BoxLabels_T_ID = "DSI_BoxLabels_T_ID";

	/** Set Print Box Labels	  */
	public void setDSI_BoxLabels_T_ID (int DSI_BoxLabels_T_ID);

	/** Get Print Box Labels	  */
	public int getDSI_BoxLabels_T_ID();

    /** Column name DSI_BoxLabels_T_UU */
    public static final String COLUMNNAME_DSI_BoxLabels_T_UU = "DSI_BoxLabels_T_UU";

	/** Set DSI_BoxLabels_T_UU	  */
	public void setDSI_BoxLabels_T_UU (String DSI_BoxLabels_T_UU);

	/** Get DSI_BoxLabels_T_UU	  */
	public String getDSI_BoxLabels_T_UU();

    /** Column name DSI_BoxNo */
    public static final String COLUMNNAME_DSI_BoxNo = "DSI_BoxNo";

	/** Set Box No	  */
	public void setDSI_BoxNo (int DSI_BoxNo);

	/** Get Box No	  */
	public int getDSI_BoxNo();

    /** Column name DSI_SrNo */
    public static final String COLUMNNAME_DSI_SrNo = "DSI_SrNo";

	/** Set Serial No.	  */
	public void setDSI_SrNo (int DSI_SrNo);

	/** Get Serial No.	  */
	public int getDSI_SrNo();

    /** Column name DS_SerialNumberFinal */
    public static final String COLUMNNAME_DS_SerialNumberFinal = "DS_SerialNumberFinal";

	/** Set Sr No Composed	  */
	public void setDS_SerialNumberFinal (String DS_SerialNumberFinal);

	/** Get Sr No Composed	  */
	public String getDS_SerialNumberFinal();

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

    /** Column name PP_Order_ID */
    public static final String COLUMNNAME_PP_Order_ID = "PP_Order_ID";

	/** Set Manufacturing Order.
	  * Manufacturing Order
	  */
	public void setPP_Order_ID (int PP_Order_ID);

	/** Get Manufacturing Order.
	  * Manufacturing Order
	  */
	public int getPP_Order_ID();

	public org.eevolution.model.I_PP_Order getPP_Order() throws RuntimeException;

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
