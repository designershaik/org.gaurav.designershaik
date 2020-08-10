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
/** Generated Model - DO NOT CHANGE */
package org.gaurav.dsi.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for DS_FundMovement
 *  @author iDempiere (generated) 
 *  @version Release 7.1 - $Id$ */
public class X_DS_FundMovement extends PO implements I_DS_FundMovement, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20200805L;

    /** Standard Constructor */
    public X_DS_FundMovement (Properties ctx, int DS_FundMovement_ID, String trxName)
    {
      super (ctx, DS_FundMovement_ID, trxName);
      /** if (DS_FundMovement_ID == 0)
        {
			setAD_PInstance_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_FundMovement (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_DS_FundMovement[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_PInstance getAD_PInstance() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PInstance)MTable.get(getCtx(), org.compiere.model.I_AD_PInstance.Table_Name)
			.getPO(getAD_PInstance_ID(), get_TrxName());	}

	/** Set Process Instance.
		@param AD_PInstance_ID 
		Instance of the process
	  */
	public void setAD_PInstance_ID (int AD_PInstance_ID)
	{
		if (AD_PInstance_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_PInstance_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_PInstance_ID, Integer.valueOf(AD_PInstance_ID));
	}

	/** Get Process Instance.
		@return Instance of the process
	  */
	public int getAD_PInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Purchase Price.
		@param A_Purchase_Price Purchase Price	  */
	public void setA_Purchase_Price (BigDecimal A_Purchase_Price)
	{
		set_Value (COLUMNNAME_A_Purchase_Price, A_Purchase_Price);
	}

	/** Get Purchase Price.
		@return Purchase Price	  */
	public BigDecimal getA_Purchase_Price () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_Purchase_Price);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Col_0.
		@param Col_0 Col_0	  */
	public void setCol_0 (String Col_0)
	{
		set_ValueNoCheck (COLUMNNAME_Col_0, Col_0);
	}

	/** Get Col_0.
		@return Col_0	  */
	public String getCol_0 () 
	{
		return (String)get_Value(COLUMNNAME_Col_0);
	}

	/** Set Col_1.
		@param Col_1 Col_1	  */
	public void setCol_1 (String Col_1)
	{
		set_ValueNoCheck (COLUMNNAME_Col_1, Col_1);
	}

	/** Get Col_1.
		@return Col_1	  */
	public String getCol_1 () 
	{
		return (String)get_Value(COLUMNNAME_Col_1);
	}

	/** Set Col_10.
		@param Col_10 Col_10	  */
	public void setCol_10 (String Col_10)
	{
		set_ValueNoCheck (COLUMNNAME_Col_10, Col_10);
	}

	/** Get Col_10.
		@return Col_10	  */
	public String getCol_10 () 
	{
		return (String)get_Value(COLUMNNAME_Col_10);
	}

	/** Set Col_11.
		@param Col_11 Col_11	  */
	public void setCol_11 (String Col_11)
	{
		set_ValueNoCheck (COLUMNNAME_Col_11, Col_11);
	}

	/** Get Col_11.
		@return Col_11	  */
	public String getCol_11 () 
	{
		return (String)get_Value(COLUMNNAME_Col_11);
	}

	/** Set Col_12.
		@param Col_12 Col_12	  */
	public void setCol_12 (String Col_12)
	{
		set_ValueNoCheck (COLUMNNAME_Col_12, Col_12);
	}

	/** Get Col_12.
		@return Col_12	  */
	public String getCol_12 () 
	{
		return (String)get_Value(COLUMNNAME_Col_12);
	}

	/** Set Col_13.
		@param Col_13 Col_13	  */
	public void setCol_13 (String Col_13)
	{
		set_ValueNoCheck (COLUMNNAME_Col_13, Col_13);
	}

	/** Get Col_13.
		@return Col_13	  */
	public String getCol_13 () 
	{
		return (String)get_Value(COLUMNNAME_Col_13);
	}

	/** Set Col_14.
		@param Col_14 Col_14	  */
	public void setCol_14 (String Col_14)
	{
		set_ValueNoCheck (COLUMNNAME_Col_14, Col_14);
	}

	/** Get Col_14.
		@return Col_14	  */
	public String getCol_14 () 
	{
		return (String)get_Value(COLUMNNAME_Col_14);
	}

	/** Set Col_15.
		@param Col_15 Col_15	  */
	public void setCol_15 (String Col_15)
	{
		set_ValueNoCheck (COLUMNNAME_Col_15, Col_15);
	}

	/** Get Col_15.
		@return Col_15	  */
	public String getCol_15 () 
	{
		return (String)get_Value(COLUMNNAME_Col_15);
	}

	/** Set Col_16.
		@param Col_16 Col_16	  */
	public void setCol_16 (String Col_16)
	{
		set_ValueNoCheck (COLUMNNAME_Col_16, Col_16);
	}

	/** Get Col_16.
		@return Col_16	  */
	public String getCol_16 () 
	{
		return (String)get_Value(COLUMNNAME_Col_16);
	}

	/** Set Col_17.
		@param Col_17 Col_17	  */
	public void setCol_17 (String Col_17)
	{
		set_ValueNoCheck (COLUMNNAME_Col_17, Col_17);
	}

	/** Get Col_17.
		@return Col_17	  */
	public String getCol_17 () 
	{
		return (String)get_Value(COLUMNNAME_Col_17);
	}

	/** Set Col_18.
		@param Col_18 Col_18	  */
	public void setCol_18 (String Col_18)
	{
		set_ValueNoCheck (COLUMNNAME_Col_18, Col_18);
	}

	/** Get Col_18.
		@return Col_18	  */
	public String getCol_18 () 
	{
		return (String)get_Value(COLUMNNAME_Col_18);
	}

	/** Set Col_19.
		@param Col_19 Col_19	  */
	public void setCol_19 (String Col_19)
	{
		set_ValueNoCheck (COLUMNNAME_Col_19, Col_19);
	}

	/** Get Col_19.
		@return Col_19	  */
	public String getCol_19 () 
	{
		return (String)get_Value(COLUMNNAME_Col_19);
	}

	/** Set Col_2.
		@param Col_2 Col_2	  */
	public void setCol_2 (String Col_2)
	{
		set_ValueNoCheck (COLUMNNAME_Col_2, Col_2);
	}

	/** Get Col_2.
		@return Col_2	  */
	public String getCol_2 () 
	{
		return (String)get_Value(COLUMNNAME_Col_2);
	}

	/** Set Col_20.
		@param Col_20 Col_20	  */
	public void setCol_20 (String Col_20)
	{
		set_ValueNoCheck (COLUMNNAME_Col_20, Col_20);
	}

	/** Get Col_20.
		@return Col_20	  */
	public String getCol_20 () 
	{
		return (String)get_Value(COLUMNNAME_Col_20);
	}

	/** Set Col_21.
		@param Col_21 Col_21	  */
	public void setCol_21 (String Col_21)
	{
		set_Value (COLUMNNAME_Col_21, Col_21);
	}

	/** Get Col_21.
		@return Col_21	  */
	public String getCol_21 () 
	{
		return (String)get_Value(COLUMNNAME_Col_21);
	}

	/** Set Col_22.
		@param Col_22 Col_22	  */
	public void setCol_22 (String Col_22)
	{
		set_Value (COLUMNNAME_Col_22, Col_22);
	}

	/** Get Col_22.
		@return Col_22	  */
	public String getCol_22 () 
	{
		return (String)get_Value(COLUMNNAME_Col_22);
	}

	/** Set Col_23.
		@param Col_23 Col_23	  */
	public void setCol_23 (String Col_23)
	{
		set_Value (COLUMNNAME_Col_23, Col_23);
	}

	/** Get Col_23.
		@return Col_23	  */
	public String getCol_23 () 
	{
		return (String)get_Value(COLUMNNAME_Col_23);
	}

	/** Set Col_24.
		@param Col_24 Col_24	  */
	public void setCol_24 (String Col_24)
	{
		set_Value (COLUMNNAME_Col_24, Col_24);
	}

	/** Get Col_24.
		@return Col_24	  */
	public String getCol_24 () 
	{
		return (String)get_Value(COLUMNNAME_Col_24);
	}

	/** Set Col_25.
		@param Col_25 Col_25	  */
	public void setCol_25 (String Col_25)
	{
		set_Value (COLUMNNAME_Col_25, Col_25);
	}

	/** Get Col_25.
		@return Col_25	  */
	public String getCol_25 () 
	{
		return (String)get_Value(COLUMNNAME_Col_25);
	}

	/** Set Col_26.
		@param Col_26 Col_26	  */
	public void setCol_26 (String Col_26)
	{
		set_Value (COLUMNNAME_Col_26, Col_26);
	}

	/** Get Col_26.
		@return Col_26	  */
	public String getCol_26 () 
	{
		return (String)get_Value(COLUMNNAME_Col_26);
	}

	/** Set Col_3.
		@param Col_3 Col_3	  */
	public void setCol_3 (String Col_3)
	{
		set_ValueNoCheck (COLUMNNAME_Col_3, Col_3);
	}

	/** Get Col_3.
		@return Col_3	  */
	public String getCol_3 () 
	{
		return (String)get_Value(COLUMNNAME_Col_3);
	}

	/** Set Col_4.
		@param Col_4 Col_4	  */
	public void setCol_4 (String Col_4)
	{
		set_ValueNoCheck (COLUMNNAME_Col_4, Col_4);
	}

	/** Get Col_4.
		@return Col_4	  */
	public String getCol_4 () 
	{
		return (String)get_Value(COLUMNNAME_Col_4);
	}

	/** Set Col_5.
		@param Col_5 Col_5	  */
	public void setCol_5 (String Col_5)
	{
		set_ValueNoCheck (COLUMNNAME_Col_5, Col_5);
	}

	/** Get Col_5.
		@return Col_5	  */
	public String getCol_5 () 
	{
		return (String)get_Value(COLUMNNAME_Col_5);
	}

	/** Set Col_6.
		@param Col_6 Col_6	  */
	public void setCol_6 (String Col_6)
	{
		set_ValueNoCheck (COLUMNNAME_Col_6, Col_6);
	}

	/** Get Col_6.
		@return Col_6	  */
	public String getCol_6 () 
	{
		return (String)get_Value(COLUMNNAME_Col_6);
	}

	/** Set Col_7.
		@param Col_7 Col_7	  */
	public void setCol_7 (String Col_7)
	{
		set_ValueNoCheck (COLUMNNAME_Col_7, Col_7);
	}

	/** Get Col_7.
		@return Col_7	  */
	public String getCol_7 () 
	{
		return (String)get_Value(COLUMNNAME_Col_7);
	}

	/** Set Col_8.
		@param Col_8 Col_8	  */
	public void setCol_8 (String Col_8)
	{
		set_ValueNoCheck (COLUMNNAME_Col_8, Col_8);
	}

	/** Get Col_8.
		@return Col_8	  */
	public String getCol_8 () 
	{
		return (String)get_Value(COLUMNNAME_Col_8);
	}

	/** Set Col_9.
		@param Col_9 Col_9	  */
	public void setCol_9 (String Col_9)
	{
		set_ValueNoCheck (COLUMNNAME_Col_9, Col_9);
	}

	/** Get Col_9.
		@return Col_9	  */
	public String getCol_9 () 
	{
		return (String)get_Value(COLUMNNAME_Col_9);
	}

	/** Set Date.
		@param Date1 
		Date when business is not conducted
	  */
	public void setDate1 (Timestamp Date1)
	{
		set_Value (COLUMNNAME_Date1, Date1);
	}

	/** Get Date.
		@return Date when business is not conducted
	  */
	public Timestamp getDate1 () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Date1);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_ValueNoCheck (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
	}

	/** Set Fund Movement.
		@param DS_FundMovement_ID Fund Movement	  */
	public void setDS_FundMovement_ID (int DS_FundMovement_ID)
	{
		if (DS_FundMovement_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_FundMovement_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_FundMovement_ID, Integer.valueOf(DS_FundMovement_ID));
	}

	/** Get Fund Movement.
		@return Fund Movement	  */
	public int getDS_FundMovement_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_FundMovement_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_FundMovement_UU.
		@param DS_FundMovement_UU DS_FundMovement_UU	  */
	public void setDS_FundMovement_UU (String DS_FundMovement_UU)
	{
		set_Value (COLUMNNAME_DS_FundMovement_UU, DS_FundMovement_UU);
	}

	/** Get DS_FundMovement_UU.
		@return DS_FundMovement_UU	  */
	public String getDS_FundMovement_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_FundMovement_UU);
	}

	/** Short Term = Short Term */
	public static final String DS_INVESTMENT_TERM_ShortTerm = "Short Term";
	/** Mid Term = Mid Term */
	public static final String DS_INVESTMENT_TERM_MidTerm = "Mid Term";
	/** Long Term = Long Term */
	public static final String DS_INVESTMENT_TERM_LongTerm = "Long Term";
	/** Set DS_Investment_Term.
		@param DS_Investment_Term DS_Investment_Term	  */
	public void setDS_Investment_Term (String DS_Investment_Term)
	{

		set_Value (COLUMNNAME_DS_Investment_Term, DS_Investment_Term);
	}

	/** Get DS_Investment_Term.
		@return DS_Investment_Term	  */
	public String getDS_Investment_Term () 
	{
		return (String)get_Value(COLUMNNAME_DS_Investment_Term);
	}

	/** Set Section.
		@param DS_Section Section	  */
	public void setDS_Section (String DS_Section)
	{
		set_Value (COLUMNNAME_DS_Section, DS_Section);
	}

	/** Get Section.
		@return Section	  */
	public String getDS_Section () 
	{
		return (String)get_Value(COLUMNNAME_DS_Section);
	}

	public org.compiere.model.I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (org.compiere.model.I_M_AttributeSetInstance)MTable.get(getCtx(), org.compiere.model.I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_ValueNoCheck (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_ValueNoCheck (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}