/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
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
 * Copyright (C) 2003-2007 e-Evolution,SC. All Rights Reserved.               *
 * Contributor(s): victor.perez@e-evolution.com http://www.e-evolution.com    *
 *****************************************************************************/

package org.adempiere.model.engines;

import java.math.BigDecimal;
import java.util.Properties;

/**
 *  Inventory Document Line interface
 *  @author victor.perez@e-evolution.com http://www.e-evolution.com
 *
 */
public interface IDocumentLine
{
	public Properties getCtx();
	public String get_TrxName();
	public String get_TableName();
	//
	public int get_ID();
	public int getAD_Client_ID();
	public int getAD_Org_ID();
	public int getM_Product_ID();
	public String getDescription();
	public int getM_Locator_ID();
	public void setM_Locator_ID(int M_Locator_ID);
	public int getM_AttributeSetInstance_ID();
	public void setM_AttributeSetInstance_ID(int M_AttributeSetInstance_ID);
	public BigDecimal getMovementQty();
}
