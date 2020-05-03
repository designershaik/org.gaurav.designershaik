package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MDSDeliveryRouteDetails extends X_DS_Delivery_RouteDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2712244446520506312L;
	MDSDeliveryRouteDetails[] m_routeDetails;
	public MDSDeliveryRouteDetails(Properties ctx,
			int DS_Delivery_RouteDetails_ID, String trxName) {
		super(ctx, DS_Delivery_RouteDetails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSDeliveryRouteDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSDeliveryRouteDetails[] getAllDetailsWithBP() 
	{
		List<MDSDeliveryRouteDetails> list = new Query(getCtx(), I_DS_Delivery_RouteDetails.Table_Name, "DS_Delivery_Trips_ID=? and C_BPartner_ID is not null ", get_TrxName())
		.setParameters(getDS_Delivery_Trips_ID())
		.setOrderBy(MDSDeliveryRouteDetails.COLUMNNAME_LineNo)
		.list();
		//
		m_routeDetails = new MDSDeliveryRouteDetails[list.size()];
		list.toArray(m_routeDetails);
		return m_routeDetails;
	}
}
