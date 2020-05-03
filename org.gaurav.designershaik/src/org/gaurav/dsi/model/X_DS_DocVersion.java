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

/** Generated Model for DS_DocVersion
 *  @author iDempiere (generated) 
 *  @version Release 4.1 - $Id$ */
public class X_DS_DocVersion extends PO implements I_DS_DocVersion, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20170806L;

    /** Standard Constructor */
    public X_DS_DocVersion (Properties ctx, int DS_DocVersion_ID, String trxName)
    {
      super (ctx, DS_DocVersion_ID, trxName);
      /** if (DS_DocVersion_ID == 0)
        {
			setDS_DocVersion_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DS_DocVersion (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DS_DocVersion[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Comments.
		@param Comments 
		Comments or additional information
	  */
	public void setComments (String Comments)
	{
		set_Value (COLUMNNAME_Comments, Comments);
	}

	/** Get Comments.
		@return Comments or additional information
	  */
	public String getComments () 
	{
		return (String)get_Value(COLUMNNAME_Comments);
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

	/** Set Design document version.
		@param DS_DocVersion_ID Design document version	  */
	public void setDS_DocVersion_ID (int DS_DocVersion_ID)
	{
		if (DS_DocVersion_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DS_DocVersion_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DS_DocVersion_ID, Integer.valueOf(DS_DocVersion_ID));
	}

	/** Get Design document version.
		@return Design document version	  */
	public int getDS_DocVersion_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DS_DocVersion_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DS_DocVersion_UU.
		@param DS_DocVersion_UU DS_DocVersion_UU	  */
	public void setDS_DocVersion_UU (String DS_DocVersion_UU)
	{
		set_Value (COLUMNNAME_DS_DocVersion_UU, DS_DocVersion_UU);
	}

	/** Get DS_DocVersion_UU.
		@return DS_DocVersion_UU	  */
	public String getDS_DocVersion_UU () 
	{
		return (String)get_Value(COLUMNNAME_DS_DocVersion_UU);
	}

	/** Set File Name.
		@param FileName 
		Name of the local file or URL
	  */
	public void setFileName (String FileName)
	{
		set_Value (COLUMNNAME_FileName, FileName);
	}

	/** Get File Name.
		@return Name of the local file or URL
	  */
	public String getFileName () 
	{
		return (String)get_Value(COLUMNNAME_FileName);
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
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
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
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

	/** Set Revision.
		@param Revision Revision	  */
	public void setRevision (int Revision)
	{
		set_Value (COLUMNNAME_Revision, Integer.valueOf(Revision));
	}

	/** Get Revision.
		@return Revision	  */
	public int getRevision () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Revision);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}