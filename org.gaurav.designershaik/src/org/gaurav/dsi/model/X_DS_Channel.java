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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DS_Channel
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_Channel extends PO implements I_DS_Channel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_Channel (Properties ctx, int DS_Channel_ID, String trxName)
    {
      super (ctx, DS_Channel_ID, trxName);
      /** if (DS_Channel_ID == 0)
        {
			setDS_Channel_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_Channel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_Channel[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
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

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Channels.
		@param DS_Channel_ID Channels	  */
	public void setDS_Channel_ID (int DS_Channel_ID)
	{
		if (DS_Channel_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_Channel_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_Channel_ID, Integer.valueOf(DS_Channel_ID));
	}

	/** Get Channels.
		@return Channels	  */
	public int getDS_Channel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_Channel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_Channel_UU.
		@param DS_Channel_UU DS_Channel_UU	  */
	public void setDS_Channel_UU (String DS_Channel_UU)
	{
		set_Value (COLUMNNAME_DS_Channel_UU, DS_Channel_UU);
	}

	/** Get DS_Channel_UU.
		@return DS_Channel_UU	  */
	public String getDS_Channel_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_Channel_UU);
	}

	/** Poor = 1_PR */
	public static final String DS_GRADE_Poor = "1_PR";
	/** Good = 2_GD */
	public static final String DS_GRADE_Good = "2_GD";
	/** Better = 3_BT */
	public static final String DS_GRADE_Better = "3_BT";
	/** Best = 4_BS */
	public static final String DS_GRADE_Best = "4_BS";
	/** Excellent = 5_EX */
	public static final String DS_GRADE_Excellent = "5_EX";
	/** Set Grade.
		@param DS_Grade Grade	  */
	public void setDS_Grade (String DS_Grade)
	{

		set_Value (COLUMNNAME_DS_Grade, DS_Grade);
	}

	/** Get Grade.
		@return Grade	  */
	public String getDS_Grade () 
	{
		return (String)get_Value(COLUMNNAME_DS_Grade);
	}

	public I_DS_OrgMaster getDS_OrgMaster() throws RuntimeException
    {
		return (I_DS_OrgMaster)MTable.get(getCtx(), I_DS_OrgMaster.Table_Name)
			.getPO(getDS_OrgMaster_ID(), get_TrxName());	}

	/** Set Organization Master.
		@param DS_OrgMaster_ID Organization Master	  */
	public void setDS_OrgMaster_ID (int DS_OrgMaster_ID)
	{
		if (DS_OrgMaster_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_OrgMaster_ID, Integer.valueOf(DS_OrgMaster_ID));
	}

	/** Get Organization Master.
		@return Organization Master	  */
	public int getDS_OrgMaster_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_OrgMaster_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Fashion = FS */
	public static final String DS_SELLCHANNEL_Fashion = "FS";
	/** Leather Goods = LG */
	public static final String DS_SELLCHANNEL_LeatherGoods = "LG";
	/** Perfume = PF */
	public static final String DS_SELLCHANNEL_Perfume = "PF";
	/** Watch = WT */
	public static final String DS_SELLCHANNEL_Watch = "WT";
	/** Set Channels.
		@param DS_SellChannel Channels	  */
	public void setDS_SellChannel (String DS_SellChannel)
	{

		set_Value (COLUMNNAME_DS_SellChannel, DS_SellChannel);
	}

	/** Get Channels.
		@return Channels	  */
	public String getDS_SellChannel () 
	{
		return (String)get_Value(COLUMNNAME_DS_SellChannel);
	}
}