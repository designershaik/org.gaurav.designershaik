/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.dsi.finance.processes;

import java.util.List;
import java.util.logging.Level;

import org.compiere.model.I_M_Product;
import org.compiere.model.MCostDetail;
import org.compiere.model.MProduct;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;

/**
 * 	Create/Update Costing for Product
 *	
 *  @author Jorg Janke
 *  @version $Id: CostCreate.java,v 1.2 2006/07/30 00:51:01 jjanke Exp $
 */
public class DSCostCreate extends SvrProcess
{
	/**	Product				*/
	private int 	p_M_Product_ID = 0; 
	private int 	p_M_Product_Category_ID=0;
	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
		//	log.fine("prepare - " + para[i]);
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_ID"))
				p_M_Product_ID = para[i].getParameterAsInt();
			else if (name.equals("M_Product_Category_ID"))
				p_M_Product_Category_ID = para[i].getParameterAsInt();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
	}	//	prepare

	/**
	 *  Perform process.
	 *  @return Message (text with variables)
	 *  @throws Exception if not successful
	 */
	protected String doIt() throws Exception
	{
		log.info("M_Product_ID=" + p_M_Product_ID+" M_Product_Category_ID="+p_M_Product_Category_ID);
//		if (p_M_Product_ID == 0 && p_M_Product_Category_ID==0)
//			throw new AdempiereUserError("@NotFound@: @M_Product_ID@ = " + p_M_Product_ID+" & @M_Product_Category_ID@="+p_M_Product_Category_ID);
		if(p_M_Product_Category_ID==0 && p_M_Product_ID!=0)
		{
			MProduct product = MProduct.get(getCtx(), p_M_Product_ID);
			if (product.get_ID() != p_M_Product_ID)
				throw new AdempiereUserError("@NotFound@: @M_Product_ID@ = " + p_M_Product_ID);
			if (MCostDetail.processProduct(product, get_TrxName()))
				return "@OK@";
		}
		else if(p_M_Product_ID==0)
		{
			String whereClause = "";
			if(p_M_Product_Category_ID!=0)
			{
				whereClause = I_M_Product.COLUMNNAME_M_Product_Category_ID+"=? AND "
										+I_M_Product.COLUMNNAME_IsActive+"=?"+I_M_Product.COLUMNNAME_IsPurchased+"=?"+I_M_Product.COLUMNNAME_IsBOM+"=?";
			}
			else if(p_M_Product_Category_ID==0)
			{
				whereClause = I_M_Product.COLUMNNAME_IsActive+" =? AND "+I_M_Product.COLUMNNAME_IsPurchased+" = ? AND "+I_M_Product.COLUMNNAME_IsBOM+"=?";
			}
			List<MProduct> list = new Query(getCtx(),I_M_Product.Table_Name,whereClause, get_TrxName()).
					setParameters(true,true,false).list();
			for (MProduct product : list)
			{
				MCostDetail.processProduct(product, get_TrxName());
			}
		}
		return "@OK@";
	}	//	doIt
	
}	//	CostCreate
