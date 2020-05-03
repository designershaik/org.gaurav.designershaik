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

/** Generated Model for DSI_Aging
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DSI_Aging extends PO implements I_DSI_Aging, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DSI_Aging (Properties ctx, int DSI_Aging_ID, String trxName)
    {
      super (ctx, DSI_Aging_ID, trxName);
      /** if (DSI_Aging_ID == 0)
        {
			setDSI_Aging_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DSI_Aging (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DSI_Aging[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set basedue121150.
		@param basedue121150 basedue121150	  */
	public void setbasedue121150 (BigDecimal basedue121150)
	{
		set_Value (COLUMNNAME_basedue121150, basedue121150);
	}

	/** Get basedue121150.
		@return basedue121150	  */
	public BigDecimal getbasedue121150 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue121150);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue151180.
		@param basedue151180 basedue151180	  */
	public void setbasedue151180 (BigDecimal basedue151180)
	{
		set_Value (COLUMNNAME_basedue151180, basedue151180);
	}

	/** Get basedue151180.
		@return basedue151180	  */
	public BigDecimal getbasedue151180 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue151180);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue181210.
		@param basedue181210 basedue181210	  */
	public void setbasedue181210 (BigDecimal basedue181210)
	{
		set_Value (COLUMNNAME_basedue181210, basedue181210);
	}

	/** Get basedue181210.
		@return basedue181210	  */
	public BigDecimal getbasedue181210 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue181210);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue211240.
		@param basedue211240 basedue211240	  */
	public void setbasedue211240 (BigDecimal basedue211240)
	{
		set_Value (COLUMNNAME_basedue211240, basedue211240);
	}

	/** Get basedue211240.
		@return basedue211240	  */
	public BigDecimal getbasedue211240 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue211240);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue241270.
		@param basedue241270 basedue241270	  */
	public void setbasedue241270 (BigDecimal basedue241270)
	{
		set_Value (COLUMNNAME_basedue241270, basedue241270);
	}

	/** Get basedue241270.
		@return basedue241270	  */
	public BigDecimal getbasedue241270 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue241270);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue271300.
		@param basedue271300 basedue271300	  */
	public void setbasedue271300 (BigDecimal basedue271300)
	{
		set_Value (COLUMNNAME_basedue271300, basedue271300);
	}

	/** Get basedue271300.
		@return basedue271300	  */
	public BigDecimal getbasedue271300 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue271300);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue301330.
		@param basedue301330 basedue301330	  */
	public void setbasedue301330 (BigDecimal basedue301330)
	{
		set_Value (COLUMNNAME_basedue301330, basedue301330);
	}

	/** Get basedue301330.
		@return basedue301330	  */
	public BigDecimal getbasedue301330 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue301330);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Base Due 31-60.
		@param basedue3160 Base Due 31-60	  */
	public void setbasedue3160 (BigDecimal basedue3160)
	{
		set_Value (COLUMNNAME_basedue3160, basedue3160);
	}

	/** Get Base Due 31-60.
		@return Base Due 31-60	  */
	public BigDecimal getbasedue3160 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue3160);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue331365.
		@param basedue331365 basedue331365	  */
	public void setbasedue331365 (BigDecimal basedue331365)
	{
		set_Value (COLUMNNAME_basedue331365, basedue331365);
	}

	/** Get basedue331365.
		@return basedue331365	  */
	public BigDecimal getbasedue331365 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue331365);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue366425.
		@param basedue366425 basedue366425	  */
	public void setbasedue366425 (BigDecimal basedue366425)
	{
		set_Value (COLUMNNAME_basedue366425, basedue366425);
	}

	/** Get basedue366425.
		@return basedue366425	  */
	public BigDecimal getbasedue366425 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue366425);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue426485.
		@param basedue426485 basedue426485	  */
	public void setbasedue426485 (BigDecimal basedue426485)
	{
		set_Value (COLUMNNAME_basedue426485, basedue426485);
	}

	/** Get basedue426485.
		@return basedue426485	  */
	public BigDecimal getbasedue426485 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue426485);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue486545.
		@param basedue486545 basedue486545	  */
	public void setbasedue486545 (BigDecimal basedue486545)
	{
		set_Value (COLUMNNAME_basedue486545, basedue486545);
	}

	/** Get basedue486545.
		@return basedue486545	  */
	public BigDecimal getbasedue486545 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue486545);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue546585.
		@param basedue546585 basedue546585	  */
	public void setbasedue546585 (BigDecimal basedue546585)
	{
		set_Value (COLUMNNAME_basedue546585, basedue546585);
	}

	/** Get basedue546585.
		@return basedue546585	  */
	public BigDecimal getbasedue546585 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue546585);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue586645.
		@param basedue586645 basedue586645	  */
	public void setbasedue586645 (BigDecimal basedue586645)
	{
		set_Value (COLUMNNAME_basedue586645, basedue586645);
	}

	/** Get basedue586645.
		@return basedue586645	  */
	public BigDecimal getbasedue586645 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue586645);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue6190.
		@param basedue6190 basedue6190	  */
	public void setbasedue6190 (BigDecimal basedue6190)
	{
		set_Value (COLUMNNAME_basedue6190, basedue6190);
	}

	/** Get basedue6190.
		@return basedue6190	  */
	public BigDecimal getbasedue6190 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue6190);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue646705.
		@param basedue646705 basedue646705	  */
	public void setbasedue646705 (BigDecimal basedue646705)
	{
		set_Value (COLUMNNAME_basedue646705, basedue646705);
	}

	/** Get basedue646705.
		@return basedue646705	  */
	public BigDecimal getbasedue646705 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue646705);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue706766.
		@param basedue706766 basedue706766	  */
	public void setbasedue706766 (BigDecimal basedue706766)
	{
		set_Value (COLUMNNAME_basedue706766, basedue706766);
	}

	/** Get basedue706766.
		@return basedue706766	  */
	public BigDecimal getbasedue706766 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue706766);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue766plus.
		@param basedue766plus basedue766plus	  */
	public void setbasedue766plus (BigDecimal basedue766plus)
	{
		set_Value (COLUMNNAME_basedue766plus, basedue766plus);
	}

	/** Get basedue766plus.
		@return basedue766plus	  */
	public BigDecimal getbasedue766plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue766plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basedue91120.
		@param basedue91120 basedue91120	  */
	public void setbasedue91120 (BigDecimal basedue91120)
	{
		set_Value (COLUMNNAME_basedue91120, basedue91120);
	}

	/** Get basedue91120.
		@return basedue91120	  */
	public BigDecimal getbasedue91120 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedue91120);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Base DueUpto 30.
		@param basedueupto30 Base DueUpto 30	  */
	public void setbasedueupto30 (BigDecimal basedueupto30)
	{
		set_Value (COLUMNNAME_basedueupto30, basedueupto30);
	}

	/** Get Base DueUpto 30.
		@return Base DueUpto 30	  */
	public BigDecimal getbasedueupto30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basedueupto30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Base Past DueAmt.
		@param basepastdueamt Base Past DueAmt	  */
	public void setbasepastdueamt (BigDecimal basepastdueamt)
	{
		set_Value (COLUMNNAME_basepastdueamt, basepastdueamt);
	}

	/** Get Base Past DueAmt.
		@return Base Past DueAmt	  */
	public BigDecimal getbasepastdueamt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basepastdueamt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set basetotal.
		@param basetotal basetotal	  */
	public void setbasetotal (BigDecimal basetotal)
	{
		set_Value (COLUMNNAME_basetotal, basetotal);
	}

	/** Get basetotal.
		@return basetotal	  */
	public BigDecimal getbasetotal () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_basetotal);
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

	public org.compiere.model.I_C_BP_Group getC_BP_Group() throws RuntimeException
    {
		return (org.compiere.model.I_C_BP_Group)MTable.get(getCtx(), org.compiere.model.I_C_BP_Group.Table_Name)
			.getPO(getC_BP_Group_ID(), get_TrxName());	}

	/** Set Business Partner Group.
		@param C_BP_Group_ID 
		Business Partner Group
	  */
	public void setC_BP_Group_ID (int C_BP_Group_ID)
	{
		if (C_BP_Group_ID < 1) 
			set_Value (COLUMNNAME_C_BP_Group_ID, null);
		else 
			set_Value (COLUMNNAME_C_BP_Group_ID, Integer.valueOf(C_BP_Group_ID));
	}

	/** Get Business Partner Group.
		@return Business Partner Group
	  */
	public int getC_BP_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BP_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Currency getC_Currency() throws RuntimeException
    {
		return (org.compiere.model.I_C_Currency)MTable.get(getCtx(), org.compiere.model.I_C_Currency.Table_Name)
			.getPO(getC_Currency_ID(), get_TrxName());	}

	/** Set Currency.
		@param C_Currency_ID 
		The Currency for this record
	  */
	public void setC_Currency_ID (int C_Currency_ID)
	{
		if (C_Currency_ID < 1) 
			set_Value (COLUMNNAME_C_Currency_ID, null);
		else 
			set_Value (COLUMNNAME_C_Currency_ID, Integer.valueOf(C_Currency_ID));
	}

	/** Get Currency.
		@return The Currency for this record
	  */
	public int getC_Currency_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Currency_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Invoice getC_Invoice() throws RuntimeException
    {
		return (org.compiere.model.I_C_Invoice)MTable.get(getCtx(), org.compiere.model.I_C_Invoice.Table_Name)
			.getPO(getC_Invoice_ID(), get_TrxName());	}

	/** Set Invoice.
		@param C_Invoice_ID 
		Invoice Identifier
	  */
	public void setC_Invoice_ID (int C_Invoice_ID)
	{
		if (C_Invoice_ID < 1) 
			set_Value (COLUMNNAME_C_Invoice_ID, null);
		else 
			set_Value (COLUMNNAME_C_Invoice_ID, Integer.valueOf(C_Invoice_ID));
	}

	/** Get Invoice.
		@return Invoice Identifier
	  */
	public int getC_Invoice_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Invoice_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_InvoicePaySchedule getC_InvoicePaySchedule() throws RuntimeException
    {
		return (org.compiere.model.I_C_InvoicePaySchedule)MTable.get(getCtx(), org.compiere.model.I_C_InvoicePaySchedule.Table_Name)
			.getPO(getC_InvoicePaySchedule_ID(), get_TrxName());	}

	/** Set Invoice Payment Schedule.
		@param C_InvoicePaySchedule_ID 
		Invoice Payment Schedule
	  */
	public void setC_InvoicePaySchedule_ID (int C_InvoicePaySchedule_ID)
	{
		if (C_InvoicePaySchedule_ID < 1) 
			set_Value (COLUMNNAME_C_InvoicePaySchedule_ID, null);
		else 
			set_Value (COLUMNNAME_C_InvoicePaySchedule_ID, Integer.valueOf(C_InvoicePaySchedule_ID));
	}

	/** Get Invoice Payment Schedule.
		@return Invoice Payment Schedule
	  */
	public int getC_InvoicePaySchedule_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_InvoicePaySchedule_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Account Date.
		@param DateAcct 
		Accounting Date
	  */
	public void setDateAcct (Timestamp DateAcct)
	{
		set_Value (COLUMNNAME_DateAcct, DateAcct);
	}

	/** Get Account Date.
		@return Accounting Date
	  */
	public Timestamp getDateAcct () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateAcct);
	}

	/** Set Date Invoiced.
		@param DateInvoiced 
		Date printed on Invoice
	  */
	public void setDateInvoiced (Timestamp DateInvoiced)
	{
		set_Value (COLUMNNAME_DateInvoiced, DateInvoiced);
	}

	/** Get Date Invoiced.
		@return Date printed on Invoice
	  */
	public Timestamp getDateInvoiced () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateInvoiced);
	}

	/** Set Days due.
		@param DaysDue 
		Number of days due (negative: due in number of days)
	  */
	public void setDaysDue (int DaysDue)
	{
		set_Value (COLUMNNAME_DaysDue, Integer.valueOf(DaysDue));
	}

	/** Get Days due.
		@return Number of days due (negative: due in number of days)
	  */
	public int getDaysDue () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DaysDue);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Aging.
		@param DSI_Aging_ID Aging	  */
	public void setDSI_Aging_ID (int DSI_Aging_ID)
	{
		if (DSI_Aging_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DSI_Aging_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DSI_Aging_ID, Integer.valueOf(DSI_Aging_ID));
	}

	/** Get Aging.
		@return Aging	  */
	public int getDSI_Aging_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DSI_Aging_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DSI_Aging_UU.
		@param DSI_Aging_UU DSI_Aging_UU	  */
	public void setDSI_Aging_UU (String DSI_Aging_UU)
	{
		set_Value (COLUMNNAME_DSI_Aging_UU, DSI_Aging_UU);
	}

	/** Get DSI_Aging_UU.
		@return DSI_Aging_UU	  */
	public String getDSI_Aging_UU () 
	{
		return (String)get_Value(COLUMNNAME_DSI_Aging_UU);
	}

	/** Set Due Today-30.
		@param Due0_30 Due Today-30	  */
	public void setDue0_30 (BigDecimal Due0_30)
	{
		set_Value (COLUMNNAME_Due0_30, Due0_30);
	}

	/** Get Due Today-30.
		@return Due Today-30	  */
	public BigDecimal getDue0_30 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due0_30);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 121_150.
		@param Due121_150 Due 121_150	  */
	public void setDue121_150 (BigDecimal Due121_150)
	{
		set_Value (COLUMNNAME_Due121_150, Due121_150);
	}

	/** Get Due 121_150.
		@return Due 121_150	  */
	public BigDecimal getDue121_150 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due121_150);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 151_180.
		@param Due151_180 Due 151_180	  */
	public void setDue151_180 (BigDecimal Due151_180)
	{
		set_Value (COLUMNNAME_Due151_180, Due151_180);
	}

	/** Get Due 151_180.
		@return Due 151_180	  */
	public BigDecimal getDue151_180 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due151_180);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 181_210.
		@param Due181_210 Due 181_210	  */
	public void setDue181_210 (BigDecimal Due181_210)
	{
		set_Value (COLUMNNAME_Due181_210, Due181_210);
	}

	/** Get Due 181_210.
		@return Due 181_210	  */
	public BigDecimal getDue181_210 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due181_210);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 211_240.
		@param Due211_240 Due 211_240	  */
	public void setDue211_240 (BigDecimal Due211_240)
	{
		set_Value (COLUMNNAME_Due211_240, Due211_240);
	}

	/** Get Due 211_240.
		@return Due 211_240	  */
	public BigDecimal getDue211_240 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due211_240);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 241_270.
		@param Due241_270 Due 241_270	  */
	public void setDue241_270 (BigDecimal Due241_270)
	{
		set_Value (COLUMNNAME_Due241_270, Due241_270);
	}

	/** Get Due 241_270.
		@return Due 241_270	  */
	public BigDecimal getDue241_270 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due241_270);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 271_300.
		@param Due271_300 Due 271_300	  */
	public void setDue271_300 (BigDecimal Due271_300)
	{
		set_Value (COLUMNNAME_Due271_300, Due271_300);
	}

	/** Get Due 271_300.
		@return Due 271_300	  */
	public BigDecimal getDue271_300 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due271_300);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 301_330.
		@param Due301_330 Due 301_330	  */
	public void setDue301_330 (BigDecimal Due301_330)
	{
		set_Value (COLUMNNAME_Due301_330, Due301_330);
	}

	/** Get Due 301_330.
		@return Due 301_330	  */
	public BigDecimal getDue301_330 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due301_330);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 31-60.
		@param Due31_60 Due 31-60	  */
	public void setDue31_60 (BigDecimal Due31_60)
	{
		set_Value (COLUMNNAME_Due31_60, Due31_60);
	}

	/** Get Due 31-60.
		@return Due 31-60	  */
	public BigDecimal getDue31_60 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due31_60);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 331_365.
		@param Due331_365 Due 331_365	  */
	public void setDue331_365 (BigDecimal Due331_365)
	{
		set_Value (COLUMNNAME_Due331_365, Due331_365);
	}

	/** Get Due 331_365.
		@return Due 331_365	  */
	public BigDecimal getDue331_365 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due331_365);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 366_425.
		@param Due366_425 Due 366_425	  */
	public void setDue366_425 (BigDecimal Due366_425)
	{
		set_Value (COLUMNNAME_Due366_425, Due366_425);
	}

	/** Get Due 366_425.
		@return Due 366_425	  */
	public BigDecimal getDue366_425 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due366_425);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 426_485.
		@param Due426_485 Due 426_485	  */
	public void setDue426_485 (BigDecimal Due426_485)
	{
		set_Value (COLUMNNAME_Due426_485, Due426_485);
	}

	/** Get Due 426_485.
		@return Due 426_485	  */
	public BigDecimal getDue426_485 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due426_485);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 486_545.
		@param Due486_545 Due 486_545	  */
	public void setDue486_545 (BigDecimal Due486_545)
	{
		set_Value (COLUMNNAME_Due486_545, Due486_545);
	}

	/** Get Due 486_545.
		@return Due 486_545	  */
	public BigDecimal getDue486_545 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due486_545);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 546_585.
		@param Due546_585 Due 546_585	  */
	public void setDue546_585 (BigDecimal Due546_585)
	{
		set_Value (COLUMNNAME_Due546_585, Due546_585);
	}

	/** Get Due 546_585.
		@return Due 546_585	  */
	public BigDecimal getDue546_585 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due546_585);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 586_645.
		@param Due586_645 Due 586_645	  */
	public void setDue586_645 (BigDecimal Due586_645)
	{
		set_Value (COLUMNNAME_Due586_645, Due586_645);
	}

	/** Get Due 586_645.
		@return Due 586_645	  */
	public BigDecimal getDue586_645 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due586_645);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 61-90.
		@param Due61_90 Due 61-90	  */
	public void setDue61_90 (BigDecimal Due61_90)
	{
		set_Value (COLUMNNAME_Due61_90, Due61_90);
	}

	/** Get Due 61-90.
		@return Due 61-90	  */
	public BigDecimal getDue61_90 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due61_90);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 646_705.
		@param Due646_705 Due 646_705	  */
	public void setDue646_705 (BigDecimal Due646_705)
	{
		set_Value (COLUMNNAME_Due646_705, Due646_705);
	}

	/** Get Due 646_705.
		@return Due 646_705	  */
	public BigDecimal getDue646_705 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due646_705);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 706_766.
		@param Due706_766 Due 706_766	  */
	public void setDue706_766 (BigDecimal Due706_766)
	{
		set_Value (COLUMNNAME_Due706_766, Due706_766);
	}

	/** Get Due 706_766.
		@return Due 706_766	  */
	public BigDecimal getDue706_766 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due706_766);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 766Plus.
		@param Due766Plus Due 766Plus	  */
	public void setDue766Plus (BigDecimal Due766Plus)
	{
		set_Value (COLUMNNAME_Due766Plus, Due766Plus);
	}

	/** Get Due 766Plus.
		@return Due 766Plus	  */
	public BigDecimal getDue766Plus () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due766Plus);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due 91- 120.
		@param Due91_120 Due 91- 120	  */
	public void setDue91_120 (BigDecimal Due91_120)
	{
		set_Value (COLUMNNAME_Due91_120, Due91_120);
	}

	/** Get Due 91- 120.
		@return Due 91- 120	  */
	public BigDecimal getDue91_120 () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Due91_120);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Amount due.
		@param DueAmt 
		Amount of the payment due
	  */
	public void setDueAmt (BigDecimal DueAmt)
	{
		set_Value (COLUMNNAME_DueAmt, DueAmt);
	}

	/** Get Amount due.
		@return Amount of the payment due
	  */
	public BigDecimal getDueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_DueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Due Date.
		@param DueDate 
		Date when the payment is due
	  */
	public void setDueDate (Timestamp DueDate)
	{
		set_Value (COLUMNNAME_DueDate, DueDate);
	}

	/** Get Due Date.
		@return Date when the payment is due
	  */
	public Timestamp getDueDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DueDate);
	}

	/** Set Invoiced Amount.
		@param InvoicedAmt 
		The amount invoiced
	  */
	public void setInvoicedAmt (BigDecimal InvoicedAmt)
	{
		set_Value (COLUMNNAME_InvoicedAmt, InvoicedAmt);
	}

	/** Get Invoiced Amount.
		@return The amount invoiced
	  */
	public BigDecimal getInvoicedAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_InvoicedAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Sales Transaction.
		@param IsSOTrx 
		This is a Sales Transaction
	  */
	public void setIsSOTrx (boolean IsSOTrx)
	{
		set_Value (COLUMNNAME_IsSOTrx, Boolean.valueOf(IsSOTrx));
	}

	/** Get Sales Transaction.
		@return This is a Sales Transaction
	  */
	public boolean isSOTrx () 
	{
		Object oo = get_Value(COLUMNNAME_IsSOTrx);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Open Amount.
		@param OpenAmt 
		Open item amount
	  */
	public void setOpenAmt (BigDecimal OpenAmt)
	{
		set_Value (COLUMNNAME_OpenAmt, OpenAmt);
	}

	/** Get Open Amount.
		@return Open item amount
	  */
	public BigDecimal getOpenAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_OpenAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Past Due.
		@param PastDueAmt Past Due	  */
	public void setPastDueAmt (BigDecimal PastDueAmt)
	{
		set_Value (COLUMNNAME_PastDueAmt, PastDueAmt);
	}

	/** Get Past Due.
		@return Past Due	  */
	public BigDecimal getPastDueAmt () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PastDueAmt);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Statement date.
		@param StatementDate 
		Date of the statement
	  */
	public void setStatementDate (Timestamp StatementDate)
	{
		set_Value (COLUMNNAME_StatementDate, StatementDate);
	}

	/** Get Statement date.
		@return Date of the statement
	  */
	public Timestamp getStatementDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StatementDate);
	}
}