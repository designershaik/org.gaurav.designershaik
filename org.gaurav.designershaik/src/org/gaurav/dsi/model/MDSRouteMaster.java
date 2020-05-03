package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MDSRouteMaster extends X_DS_RouteMaster {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5657366885014638814L;
	private MDSRouteTo[] m_routeto;
	private MDSRouteReturn[] m_routeReturn;
	public MDSRouteMaster(Properties ctx, int DS_RouteMaster_ID, String trxName) {
		super(ctx, DS_RouteMaster_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSRouteMaster(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	public MDSRouteTo[] getRouteTo ()
	{
		List<MDSRouteTo> list = new Query(getCtx(), I_DS_RouteTo.Table_Name, "DS_RouteMaster_ID=?", get_TrxName())
		.setParameters(getDS_RouteMaster_ID())
		.setOrderBy(MDSRouteTo.COLUMNNAME_LineNo)
		.list();
		//
		m_routeto = new MDSRouteTo[list.size()];
		list.toArray(m_routeto);
		return m_routeto;
	}	//	getMInOutLines
	
	public MDSRouteReturn[] getRouteReturn()
	{
		List<MDSRouteReturn> list = new Query(getCtx(), I_DS_RouteReturn.Table_Name, "DS_RouteMaster_ID=?", get_TrxName())
		.setParameters(getDS_RouteMaster_ID())
		.setOrderBy(MDSRouteReturn.COLUMNNAME_LineNo)
		.list();
		//
		m_routeReturn = new MDSRouteReturn[list.size()];
		list.toArray(m_routeReturn);
		return m_routeReturn;
	}	//	getMInOutLines
}

