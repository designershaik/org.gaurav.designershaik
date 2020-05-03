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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for DS_LeadCheckList
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_LeadCheckList extends PO implements I_DS_LeadCheckList, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_LeadCheckList (Properties ctx, int DS_LeadCheckList_ID, String trxName)
    {
      super (ctx, DS_LeadCheckList_ID, trxName);
      /** if (DS_LeadCheckList_ID == 0)
        {
			setDS_LeadCheckList_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_LeadCheckList (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_LeadCheckList[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	public org.compiere.model.I_C_Opportunity getC_Opportunity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Opportunity)MTable.get(getCtx(), org.compiere.model.I_C_Opportunity.Table_Name)
			.getPO(getC_Opportunity_ID(), get_TrxName());	}

	/** Set Sales Opportunity.
		@param C_Opportunity_ID Sales Opportunity	  */
	public void setC_Opportunity_ID (int C_Opportunity_ID)
	{
		if (C_Opportunity_ID < 1) 
			set_Value (COLUMNNAME_C_Opportunity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Opportunity_ID, Integer.valueOf(C_Opportunity_ID));
	}

	/** Get Sales Opportunity.
		@return Sales Opportunity	  */
	public int getC_Opportunity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Opportunity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Date Requested.
		@param DateRequested Date Requested	  */
	public void setDateRequested (Timestamp DateRequested)
	{
		set_Value (COLUMNNAME_DateRequested, DateRequested);
	}

	/** Get Date Requested.
		@return Date Requested	  */
	public Timestamp getDateRequested () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateRequested);
	}

	/** Set Response Date.
		@param DateResponse 
		Date of the Response
	  */
	public void setDateResponse (Timestamp DateResponse)
	{
		set_Value (COLUMNNAME_DateResponse, DateResponse);
	}

	/** Get Response Date.
		@return Date of the Response
	  */
	public Timestamp getDateResponse () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateResponse);
	}

	/** AGRMENT = AGRMENT */
	public static final String DS_CHECKLIST_AGRMENT = "AGRMENT";
	/** Billing & Shipping forms = BILLSHIPPFORM */
	public static final String DS_CHECKLIST_BillingShippingForms = "BILLSHIPPFORM";
	/** Business Plan = BPLAN */
	public static final String DS_CHECKLIST_BusinessPlan = "BPLAN";
	/** Company Profile = COMPPROF */
	public static final String DS_CHECKLIST_CompanyProfile = "COMPPROF";
	/** Floor plan/pics & Measurments of position in shop = FLOORPLAN */
	public static final String DS_CHECKLIST_FloorPlanPicsMeasurmentsOfPositionInShop = "FLOORPLAN";
	/** Intro Letter = INTRL */
	public static final String DS_CHECKLIST_IntroLetter = "INTRL";
	/** Location Forms = LOCFORM */
	public static final String DS_CHECKLIST_LocationForms = "LOCFORM";
	/** Order Forms = ORDERFORM */
	public static final String DS_CHECKLIST_OrderForms = "ORDERFORM";
	/** Presentation Kit / Samples = PRESENT */
	public static final String DS_CHECKLIST_PresentationKitSamples = "PRESENT";
	/** Pricing Structure = PRICESTRUCT */
	public static final String DS_CHECKLIST_PricingStructure = "PRICESTRUCT";
	/** Promotional Activity = PROMACT */
	public static final String DS_CHECKLIST_PromotionalActivity = "PROMACT";
	/** Questionnaire = QUEST */
	public static final String DS_CHECKLIST_Questionnaire = "QUEST";
	/** Pictures of Shop = SHOPPIC */
	public static final String DS_CHECKLIST_PicturesOfShop = "SHOPPIC";
	/** VM Proposal = VMPROP */
	public static final String DS_CHECKLIST_VMProposal = "VMPROP";
	/** Set Check List.
		@param DS_CheckList 
		Document required as a part of Lead conversion
	  */
	public void setDS_CheckList (String DS_CheckList)
	{

		set_Value (COLUMNNAME_DS_CheckList, DS_CheckList);
	}

	/** Get Check List.
		@return Document required as a part of Lead conversion
	  */
	public String getDS_CheckList () 
	{
		return (String)get_Value(COLUMNNAME_DS_CheckList);
	}

	/** Set Received.
		@param DS_IsReceived 
		Received the document
	  */
	public void setDS_IsReceived (boolean DS_IsReceived)
	{
		set_Value (COLUMNNAME_DS_IsReceived, Boolean.valueOf(DS_IsReceived));
	}

	/** Get Received.
		@return Received the document
	  */
	public boolean isDS_IsReceived () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsReceived);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Requested.
		@param DS_IsRequested 
		Request for the document
	  */
	public void setDS_IsRequested (boolean DS_IsRequested)
	{
		set_Value (COLUMNNAME_DS_IsRequested, Boolean.valueOf(DS_IsRequested));
	}

	/** Get Requested.
		@return Request for the document
	  */
	public boolean isDS_IsRequested () 
	{
		Object oo = get_Value(COLUMNNAME_DS_IsRequested);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Check List.
		@param DS_LeadCheckList_ID Check List	  */
	public void setDS_LeadCheckList_ID (int DS_LeadCheckList_ID)
	{
		if (DS_LeadCheckList_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_LeadCheckList_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_LeadCheckList_ID, Integer.valueOf(DS_LeadCheckList_ID));
	}

	/** Get Check List.
		@return Check List	  */
	public int getDS_LeadCheckList_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_LeadCheckList_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_LeadCheckList_UU.
		@param DS_LeadCheckList_UU DS_LeadCheckList_UU	  */
	public void setDS_LeadCheckList_UU (String DS_LeadCheckList_UU)
	{
		set_Value (COLUMNNAME_DS_LeadCheckList_UU, DS_LeadCheckList_UU);
	}

	/** Get DS_LeadCheckList_UU.
		@return DS_LeadCheckList_UU	  */
	public String getDS_LeadCheckList_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_LeadCheckList_UU);
	}
}