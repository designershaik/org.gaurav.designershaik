/**********************************************************************
* This file is part of iDempiere ERP Open Source                      *
* http://www.idempiere.org                                            *
*                                                                     *
* Copyright (C) Contributors                                          *
*                                                                     *
* This program is free software; you can redistribute it and/or       *
* modify it under the terms of the GNU General Public License         *
* as published by the Free Software Foundation; either version 2      *
* of the License, or (at your option) any later version.              *
*                                                                     *
* This program is distributed in the hope that it will be useful,     *
* but WITHOUT ANY WARRANTY; without even the implied warranty of      *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
* GNU General Public License for more details.                        *
*                                                                     *
* You should have received a copy of the GNU General Public License   *
* along with this program; if not, write to the Free Software         *
* Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
* MA 02110-1301, USA.                                                 *
*                                                                     *
* Contributors:                                                       *
* - Adaxa                                                             *
* - Ashley Ramdass                                                    *
* - Deepak Pansheriya                                                 *
* - Murilo Ht                                                         *
* - Carlos Ruiz                                                       *
**********************************************************************/

package com.gaurav.dsi.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.compiere.model.MRole;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.Env;

import com.gaurav.dsi.model.MDSIDashboardReports;

public class WDocumentStatusPanel extends Panel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7473476079783059880L;

	private List<WDocumentStatusIndicator> indicatorList = new ArrayList<WDocumentStatusIndicator>();

	/** Document Status Indicators	*/
	private MDSIDashboardReports[] 	m_indicators = null;

	/**	Logger	*/
	private static final CLogger log = CLogger.getCLogger (WDocumentStatusPanel.class);

	/**
	 * 	Get Panel if User has Document Status Indicators
	 *	@return panel
	 */
	public static WDocumentStatusPanel get()
	{
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		int AD_Role_ID = Env.getAD_Role_ID(Env.getCtx());
		MDSIDashboardReports[] indicators = getDocumentStatusIndicators(Env.getCtx(), AD_User_ID, AD_Role_ID);
		return new WDocumentStatusPanel(indicators);
	}
	
	/**
	 * 	Get Document Status Indicators
	 *	@param ctx context
	 *	@param AD_User_ID user
	 * @param AD_Role_ID 
	 *	@return array of document status
	 */
	public static MDSIDashboardReports[] getDocumentStatusIndicators(Properties ctx, int AD_User_ID, int AD_Role_ID)
	{
		if (AD_User_ID < 0)
			return new MDSIDashboardReports[0];

		String whereClause = "AD_Client_ID IN (0,?) AND ((AD_User_ID IS NULL OR AD_User_ID=?) AND ( AD_Role_ID IS NULL OR AD_Role_ID=?))";

		List<MDSIDashboardReports> list = new Query(ctx, MDSIDashboardReports.Table_Name, whereClause, null)
				.setOnlyActiveRecords(true)
				.setOrderBy(MDSIDashboardReports.COLUMNNAME_SeqNo)
				.setParameters(Env.getAD_Client_ID(ctx), AD_User_ID, AD_Role_ID)
				.list();

		/* Verify access for user/role */
		List<MDSIDashboardReports> listWithAccess = new ArrayList<MDSIDashboardReports>();
		for (MDSIDashboardReports ds : list) {
			if (ds.getAD_Window_ID() > 0) {
				Boolean access = MRole.getDefault().getWindowAccess(ds.getAD_Window_ID());
				if (access != null)
					listWithAccess.add(ds);
			} else if (ds.getAD_Form_ID() > 0) {
				Boolean access = MRole.getDefault().getFormAccess(ds.getAD_Form_ID());
				if (access != null)
					listWithAccess.add(ds);
			}
		}

		MDSIDashboardReports[] retValue = new MDSIDashboardReports[listWithAccess.size ()];
		listWithAccess.toArray (retValue);
		return retValue;
	}	//	getDocumentStatusIndicators

	/**************************************************************************
	 * 	Constructor
	 *	@param Document Status Indicators
	 */
	private WDocumentStatusPanel (MDSIDashboardReports[] indicators)
	{
		super ();
		m_indicators = indicators;
		init();
	}

	/**
	 * 	Static/Dynamic Init
	 */
	private void init()
	{
		log.info("");
		Grid grid = new Grid();
		appendChild(grid);
		grid.setWidth("100%");
		grid.makeNoStrip();
		grid.setOddRowSclass("even");

		Rows rows = new Rows();
		grid.appendChild(rows);

		for (int i = 0; i < m_indicators.length; i++)
		{
			Row row = new Row();
			rows.appendChild(row);

			WDocumentStatusIndicator pi = new WDocumentStatusIndicator(m_indicators[i]);
			row.appendChild(pi);
			indicatorList.add(pi);
		}
	}	//	init

	public void refresh() {
		for (WDocumentStatusIndicator indicator : indicatorList) {
			indicator.refresh();
		}
	}

	public void updateUI() {
		for (WDocumentStatusIndicator indicator : indicatorList) {
			indicator.updateUI();
		}
	}
}
