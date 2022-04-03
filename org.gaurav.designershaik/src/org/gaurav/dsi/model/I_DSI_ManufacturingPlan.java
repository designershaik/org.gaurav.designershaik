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

/** Generated Interface for DSI_ManufacturingPlan
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_DSI_ManufacturingPlan 
{

    /** TableName=DSI_ManufacturingPlan */
    public static final String Table_Name = "DSI_ManufacturingPlan";

    /** AD_Table_ID=1000277 */
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

    /** Column name DSI_ManufacturingPlan_ID */
    public static final String COLUMNNAME_DSI_ManufacturingPlan_ID = "DSI_ManufacturingPlan_ID";

	/** Set Manufacturing Plan	  */
	public void setDSI_ManufacturingPlan_ID (int DSI_ManufacturingPlan_ID);

	/** Get Manufacturing Plan	  */
	public int getDSI_ManufacturingPlan_ID();

    /** Column name DSI_ManufacturingPlan_UU */
    public static final String COLUMNNAME_DSI_ManufacturingPlan_UU = "DSI_ManufacturingPlan_UU";

	/** Set DSI_ManufacturingPlan_UU	  */
	public void setDSI_ManufacturingPlan_UU (String DSI_ManufacturingPlan_UU);

	/** Get DSI_ManufacturingPlan_UU	  */
	public String getDSI_ManufacturingPlan_UU();

    /** Column name DSI_SalesForecastMonth_ID */
    public static final String COLUMNNAME_DSI_SalesForecastMonth_ID = "DSI_SalesForecastMonth_ID";

	/** Set DSI_SalesForecastMonth	  */
	public void setDSI_SalesForecastMonth_ID (int DSI_SalesForecastMonth_ID);

	/** Get DSI_SalesForecastMonth	  */
	public int getDSI_SalesForecastMonth_ID();

	public I_DSI_SalesForecastMonth getDSI_SalesForecastMonth() throws RuntimeException;

    /** Column name DSI_SalesForecast_ID */
    public static final String COLUMNNAME_DSI_SalesForecast_ID = "DSI_SalesForecast_ID";

	/** Set DSI_SalesForecast_ID	  */
	public void setDSI_SalesForecast_ID (int DSI_SalesForecast_ID);

	/** Get DSI_SalesForecast_ID	  */
	public int getDSI_SalesForecast_ID();

	public I_DSI_SalesForecast getDSI_SalesForecast() throws RuntimeException;

    /** Column name DateDoc */
    public static final String COLUMNNAME_DateDoc = "DateDoc";

	/** Set Document Date.
	  * Date of the Document
	  */
	public void setDateDoc (Timestamp DateDoc);

	/** Get Document Date.
	  * Date of the Document
	  */
	public Timestamp getDateDoc();

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

    /** Column name DocumentNo */
    public static final String COLUMNNAME_DocumentNo = "DocumentNo";

	/** Set Document No.
	  * Document sequence number of the document
	  */
	public void setDocumentNo (String DocumentNo);

	/** Get Document No.
	  * Document sequence number of the document
	  */
	public String getDocumentNo();

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

    /** Column name M_Warehouse_ID */
    public static final String COLUMNNAME_M_Warehouse_ID = "M_Warehouse_ID";

	/** Set Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public void setM_Warehouse_ID (int M_Warehouse_ID);

	/** Get Warehouse.
	  * Storage Warehouse and Service Point
	  */
	public int getM_Warehouse_ID();

	public org.compiere.model.I_M_Warehouse getM_Warehouse() throws RuntimeException;

    /** Column name QtyManufactured */
    public static final String COLUMNNAME_QtyManufactured = "QtyManufactured";

	/** Set Quantity Manufactured	  */
	public void setQtyManufactured (BigDecimal QtyManufactured);

	/** Get Quantity Manufactured	  */
	public BigDecimal getQtyManufactured();

    /** Column name QtyManufacturedToPending */
    public static final String COLUMNNAME_QtyManufacturedToPending = "QtyManufacturedToPending";

	/** Set Quantity Manufactured To Pending	  */
	public void setQtyManufacturedToPending (BigDecimal QtyManufacturedToPending);

	/** Get Quantity Manufactured To Pending	  */
	public BigDecimal getQtyManufacturedToPending();

    /** Column name QtyReserve */
    public static final String COLUMNNAME_QtyReserve = "QtyReserve";

	/** Set QtyReserve	  */
	public void setQtyReserve (BigDecimal QtyReserve);

	/** Get QtyReserve	  */
	public BigDecimal getQtyReserve();

    /** Column name QtyToOrder */
    public static final String COLUMNNAME_QtyToOrder = "QtyToOrder";

	/** Set Quantity to Order	  */
	public void setQtyToOrder (BigDecimal QtyToOrder);

	/** Get Quantity to Order	  */
	public BigDecimal getQtyToOrder();

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

    /** Column name onorderqty */
    public static final String COLUMNNAME_onorderqty = "onorderqty";

	/** Set onorderqty	  */
	public void setonorderqty (BigDecimal onorderqty);

	/** Get onorderqty	  */
	public BigDecimal getonorderqty();
}
