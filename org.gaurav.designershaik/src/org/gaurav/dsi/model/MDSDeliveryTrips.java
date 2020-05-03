package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MDSDeliveryTrips extends X_DS_Delivery_Trips 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9145583542763118431L;
	private MDSDeliveryRouteDetails[] m_routeDetails;
	private MDSDeliveryTripsDet[] m_tripDetails;
	
	public MDSDeliveryTrips(Properties ctx, int DS_Delivery_Trips_ID,
			String trxName) {
		super(ctx, DS_Delivery_Trips_ID, trxName);
		
	}

	public MDSDeliveryTrips(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	public MDSDeliveryRouteDetails[] getInTransitRouteDetails()
	{
		List<MDSDeliveryRouteDetails> list = new Query(getCtx(), I_DS_Delivery_RouteDetails.Table_Name, "DS_Delivery_Trips_ID=? and DS_IsTransitPoint='Y' and C_BPartner_ID is not null ", get_TrxName())
		.setParameters(getDS_Delivery_Trips_ID())
		.setOrderBy(MDSDeliveryRouteDetails.COLUMNNAME_LineNo)
		.list();
		//
		m_routeDetails = new MDSDeliveryRouteDetails[list.size()];
		list.toArray(m_routeDetails);
		return m_routeDetails;
	}	//	getTransitRouteDetails
	
	public MDSDeliveryTripsDet[] getTripDetails()
	{
		List<MDSDeliveryTripsDet> list = new Query(getCtx(), I_DS_Delivery_Trips_Det.Table_Name, "DS_Delivery_Trips_ID=?", get_TrxName())
		.setParameters(getDS_Delivery_Trips_ID())
		.setOrderBy(MDSDeliveryTripsDet.COLUMNNAME_LineNo)
		.list();
		//
		m_tripDetails = new MDSDeliveryTripsDet[list.size()];
		list.toArray(m_tripDetails);
		return m_tripDetails;		
	}
}
