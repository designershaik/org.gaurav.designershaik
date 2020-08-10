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

/** Generated Interface for DS_FundMovement
 *  @author iDempiere (generated) 
 *  @version Release 7.1
 */
@SuppressWarnings("all")
public interface I_DS_FundMovement 
{

    /** TableName=DS_FundMovement */
    public static final String Table_Name = "DS_FundMovement";

    /** AD_Table_ID=1000242 */
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

    /** Column name AD_PInstance_ID */
    public static final String COLUMNNAME_AD_PInstance_ID = "AD_PInstance_ID";

	/** Set Process Instance.
	  * Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID);

	/** Get Process Instance.
	  * Instance of the process
	  */
	public int getAD_PInstance_ID();

	public org.compiere.model.I_AD_PInstance getAD_PInstance() throws RuntimeException;

    /** Column name A_Purchase_Price */
    public static final String COLUMNNAME_A_Purchase_Price = "A_Purchase_Price";

	/** Set Purchase Price	  */
	public void setA_Purchase_Price (BigDecimal A_Purchase_Price);

	/** Get Purchase Price	  */
	public BigDecimal getA_Purchase_Price();

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

    /** Column name Col_0 */
    public static final String COLUMNNAME_Col_0 = "Col_0";

	/** Set Col_0	  */
	public void setCol_0 (String Col_0);

	/** Get Col_0	  */
	public String getCol_0();

    /** Column name Col_1 */
    public static final String COLUMNNAME_Col_1 = "Col_1";

	/** Set Col_1	  */
	public void setCol_1 (String Col_1);

	/** Get Col_1	  */
	public String getCol_1();

    /** Column name Col_10 */
    public static final String COLUMNNAME_Col_10 = "Col_10";

	/** Set Col_10	  */
	public void setCol_10 (String Col_10);

	/** Get Col_10	  */
	public String getCol_10();

    /** Column name Col_11 */
    public static final String COLUMNNAME_Col_11 = "Col_11";

	/** Set Col_11	  */
	public void setCol_11 (String Col_11);

	/** Get Col_11	  */
	public String getCol_11();

    /** Column name Col_12 */
    public static final String COLUMNNAME_Col_12 = "Col_12";

	/** Set Col_12	  */
	public void setCol_12 (String Col_12);

	/** Get Col_12	  */
	public String getCol_12();

    /** Column name Col_13 */
    public static final String COLUMNNAME_Col_13 = "Col_13";

	/** Set Col_13	  */
	public void setCol_13 (String Col_13);

	/** Get Col_13	  */
	public String getCol_13();

    /** Column name Col_14 */
    public static final String COLUMNNAME_Col_14 = "Col_14";

	/** Set Col_14	  */
	public void setCol_14 (String Col_14);

	/** Get Col_14	  */
	public String getCol_14();

    /** Column name Col_15 */
    public static final String COLUMNNAME_Col_15 = "Col_15";

	/** Set Col_15	  */
	public void setCol_15 (String Col_15);

	/** Get Col_15	  */
	public String getCol_15();

    /** Column name Col_16 */
    public static final String COLUMNNAME_Col_16 = "Col_16";

	/** Set Col_16	  */
	public void setCol_16 (String Col_16);

	/** Get Col_16	  */
	public String getCol_16();

    /** Column name Col_17 */
    public static final String COLUMNNAME_Col_17 = "Col_17";

	/** Set Col_17	  */
	public void setCol_17 (String Col_17);

	/** Get Col_17	  */
	public String getCol_17();

    /** Column name Col_18 */
    public static final String COLUMNNAME_Col_18 = "Col_18";

	/** Set Col_18	  */
	public void setCol_18 (String Col_18);

	/** Get Col_18	  */
	public String getCol_18();

    /** Column name Col_19 */
    public static final String COLUMNNAME_Col_19 = "Col_19";

	/** Set Col_19	  */
	public void setCol_19 (String Col_19);

	/** Get Col_19	  */
	public String getCol_19();

    /** Column name Col_2 */
    public static final String COLUMNNAME_Col_2 = "Col_2";

	/** Set Col_2	  */
	public void setCol_2 (String Col_2);

	/** Get Col_2	  */
	public String getCol_2();

    /** Column name Col_20 */
    public static final String COLUMNNAME_Col_20 = "Col_20";

	/** Set Col_20	  */
	public void setCol_20 (String Col_20);

	/** Get Col_20	  */
	public String getCol_20();

    /** Column name Col_21 */
    public static final String COLUMNNAME_Col_21 = "Col_21";

	/** Set Col_21	  */
	public void setCol_21 (String Col_21);

	/** Get Col_21	  */
	public String getCol_21();

    /** Column name Col_22 */
    public static final String COLUMNNAME_Col_22 = "Col_22";

	/** Set Col_22	  */
	public void setCol_22 (String Col_22);

	/** Get Col_22	  */
	public String getCol_22();

    /** Column name Col_23 */
    public static final String COLUMNNAME_Col_23 = "Col_23";

	/** Set Col_23	  */
	public void setCol_23 (String Col_23);

	/** Get Col_23	  */
	public String getCol_23();

    /** Column name Col_24 */
    public static final String COLUMNNAME_Col_24 = "Col_24";

	/** Set Col_24	  */
	public void setCol_24 (String Col_24);

	/** Get Col_24	  */
	public String getCol_24();

    /** Column name Col_25 */
    public static final String COLUMNNAME_Col_25 = "Col_25";

	/** Set Col_25	  */
	public void setCol_25 (String Col_25);

	/** Get Col_25	  */
	public String getCol_25();

    /** Column name Col_26 */
    public static final String COLUMNNAME_Col_26 = "Col_26";

	/** Set Col_26	  */
	public void setCol_26 (String Col_26);

	/** Get Col_26	  */
	public String getCol_26();

    /** Column name Col_3 */
    public static final String COLUMNNAME_Col_3 = "Col_3";

	/** Set Col_3	  */
	public void setCol_3 (String Col_3);

	/** Get Col_3	  */
	public String getCol_3();

    /** Column name Col_4 */
    public static final String COLUMNNAME_Col_4 = "Col_4";

	/** Set Col_4	  */
	public void setCol_4 (String Col_4);

	/** Get Col_4	  */
	public String getCol_4();

    /** Column name Col_5 */
    public static final String COLUMNNAME_Col_5 = "Col_5";

	/** Set Col_5	  */
	public void setCol_5 (String Col_5);

	/** Get Col_5	  */
	public String getCol_5();

    /** Column name Col_6 */
    public static final String COLUMNNAME_Col_6 = "Col_6";

	/** Set Col_6	  */
	public void setCol_6 (String Col_6);

	/** Get Col_6	  */
	public String getCol_6();

    /** Column name Col_7 */
    public static final String COLUMNNAME_Col_7 = "Col_7";

	/** Set Col_7	  */
	public void setCol_7 (String Col_7);

	/** Get Col_7	  */
	public String getCol_7();

    /** Column name Col_8 */
    public static final String COLUMNNAME_Col_8 = "Col_8";

	/** Set Col_8	  */
	public void setCol_8 (String Col_8);

	/** Get Col_8	  */
	public String getCol_8();

    /** Column name Col_9 */
    public static final String COLUMNNAME_Col_9 = "Col_9";

	/** Set Col_9	  */
	public void setCol_9 (String Col_9);

	/** Get Col_9	  */
	public String getCol_9();

    /** Column name Date1 */
    public static final String COLUMNNAME_Date1 = "Date1";

	/** Set Date.
	  * Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1);

	/** Get Date.
	  * Date when business is not conducted
	  */
	public Timestamp getDate1();

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

    /** Column name DS_FundMovement_ID */
    public static final String COLUMNNAME_DS_FundMovement_ID = "DS_FundMovement_ID";

	/** Set Fund Movement	  */
	public void setDS_FundMovement_ID (int DS_FundMovement_ID);

	/** Get Fund Movement	  */
	public int getDS_FundMovement_ID();

    /** Column name DS_FundMovement_UU */
    public static final String COLUMNNAME_DS_FundMovement_UU = "DS_FundMovement_UU";

	/** Set DS_FundMovement_UU	  */
	public void setDS_FundMovement_UU (String DS_FundMovement_UU);

	/** Get DS_FundMovement_UU	  */
	public String getDS_FundMovement_UU();

    /** Column name DS_Investment_Term */
    public static final String COLUMNNAME_DS_Investment_Term = "DS_Investment_Term";

	/** Set DS_Investment_Term	  */
	public void setDS_Investment_Term (String DS_Investment_Term);

	/** Get DS_Investment_Term	  */
	public String getDS_Investment_Term();

    /** Column name DS_Section */
    public static final String COLUMNNAME_DS_Section = "DS_Section";

	/** Set Section	  */
	public void setDS_Section (String DS_Section);

	/** Get Section	  */
	public String getDS_Section();

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

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

	public org.compiere.model.I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

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

    /** Column name SeqNo */
    public static final String COLUMNNAME_SeqNo = "SeqNo";

	/** Set Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public void setSeqNo (int SeqNo);

	/** Get Sequence.
	  * Method of ordering records;
 lowest number comes first
	  */
	public int getSeqNo();

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
