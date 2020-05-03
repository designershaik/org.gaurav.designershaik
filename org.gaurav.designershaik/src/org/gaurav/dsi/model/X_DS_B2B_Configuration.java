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

/** Generated Model for DS_B2B_Configuration
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_B2B_Configuration extends PO implements I_DS_B2B_Configuration, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_B2B_Configuration (Properties ctx, int DS_B2B_Configuration_ID, String trxName)
    {
      super (ctx, DS_B2B_Configuration_ID, trxName);
      /** if (DS_B2B_Configuration_ID == 0)
        {
			setDS_B2B_Configuration_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_B2B_Configuration (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_B2B_Configuration[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_PrintFormat getAD_PrintFormat() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintFormat)MTable.get(getCtx(), org.compiere.model.I_AD_PrintFormat.Table_Name)
			.getPO(getAD_PrintFormat_ID(), get_TrxName());	}

	/** Set Print Format.
		@param AD_PrintFormat_ID 
		Data Print Format
	  */
	public void setAD_PrintFormat_ID (int AD_PrintFormat_ID)
	{
		if (AD_PrintFormat_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintFormat_ID, Integer.valueOf(AD_PrintFormat_ID));
	}

	/** Get Print Format.
		@return Data Print Format
	  */
	public int getAD_PrintFormat_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintFormat_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set B2B configuration.
		@param DS_B2B_Configuration_ID B2B configuration	  */
	public void setDS_B2B_Configuration_ID (int DS_B2B_Configuration_ID)
	{
		if (DS_B2B_Configuration_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_B2B_Configuration_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_B2B_Configuration_ID, Integer.valueOf(DS_B2B_Configuration_ID));
	}

	/** Get B2B configuration.
		@return B2B configuration	  */
	public int getDS_B2B_Configuration_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_B2B_Configuration_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_B2B_Configuration_UU.
		@param DS_B2B_Configuration_UU DS_B2B_Configuration_UU	  */
	public void setDS_B2B_Configuration_UU (String DS_B2B_Configuration_UU)
	{
		set_Value (COLUMNNAME_DS_B2B_Configuration_UU, DS_B2B_Configuration_UU);
	}

	/** Get DS_B2B_Configuration_UU.
		@return DS_B2B_Configuration_UU	  */
	public String getDS_B2B_Configuration_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_B2B_Configuration_UU);
	}

	/** Set Sftp Host.
		@param DS_SftpHost 
		Sftp host for B2B file upload
	  */
	public void setDS_SftpHost (String DS_SftpHost)
	{
		set_Value (COLUMNNAME_DS_SftpHost, DS_SftpHost);
	}

	/** Get Sftp Host.
		@return Sftp host for B2B file upload
	  */
	public String getDS_SftpHost () 
	{
		return (String)get_Value(COLUMNNAME_DS_SftpHost);
	}

	/** Set Sftp Password.
		@param DS_SftpPassword 
		Sftp password for B2B file upload
	  */
	public void setDS_SftpPassword (String DS_SftpPassword)
	{
		set_Value (COLUMNNAME_DS_SftpPassword, DS_SftpPassword);
	}

	/** Get Sftp Password.
		@return Sftp password for B2B file upload
	  */
	public String getDS_SftpPassword () 
	{
		return (String)get_Value(COLUMNNAME_DS_SftpPassword);
	}

	/** Set Sftp Port.
		@param DS_SftpPort 
		Sftp port for B2B file upload
	  */
	public void setDS_SftpPort (int DS_SftpPort)
	{
		set_Value (COLUMNNAME_DS_SftpPort, Integer.valueOf(DS_SftpPort));
	}

	/** Get Sftp Port.
		@return Sftp port for B2B file upload
	  */
	public int getDS_SftpPort () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_SftpPort);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Sftp User.
		@param DS_SftpUser 
		Sftp user for B2B file upload
	  */
	public void setDS_SftpUser (String DS_SftpUser)
	{
		set_Value (COLUMNNAME_DS_SftpUser, DS_SftpUser);
	}

	/** Get Sftp User.
		@return Sftp user for B2B file upload
	  */
	public String getDS_SftpUser () 
	{
		return (String)get_Value(COLUMNNAME_DS_SftpUser);
	}

	/** Set Sftp Working Director.
		@param DS_SftpWorkingDir 
		Sftp working directory to upload the files
	  */
	public void setDS_SftpWorkingDir (String DS_SftpWorkingDir)
	{
		set_Value (COLUMNNAME_DS_SftpWorkingDir, DS_SftpWorkingDir);
	}

	/** Get Sftp Working Director.
		@return Sftp working directory to upload the files
	  */
	public String getDS_SftpWorkingDir () 
	{
		return (String)get_Value(COLUMNNAME_DS_SftpWorkingDir);
	}

	public org.compiere.model.I_R_MailText getR_MailText() throws RuntimeException
    {
		return (org.compiere.model.I_R_MailText)MTable.get(getCtx(), org.compiere.model.I_R_MailText.Table_Name)
			.getPO(getR_MailText_ID(), get_TrxName());	}

	/** Set Mail Template.
		@param R_MailText_ID 
		Text templates for mailings
	  */
	public void setR_MailText_ID (int R_MailText_ID)
	{
		if (R_MailText_ID < 1) 
			set_Value (COLUMNNAME_R_MailText_ID, null);
		else 
			set_Value (COLUMNNAME_R_MailText_ID, Integer.valueOf(R_MailText_ID));
	}

	/** Get Mail Template.
		@return Text templates for mailings
	  */
	public int getR_MailText_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_R_MailText_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}