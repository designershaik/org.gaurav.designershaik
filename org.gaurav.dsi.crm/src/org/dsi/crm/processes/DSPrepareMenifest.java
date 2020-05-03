package org.dsi.crm.processes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInOut;
import org.compiere.model.MMovement;
import org.compiere.model.MPackage;
import org.compiere.model.MRequest;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSDeliveryRouteDetails;
import org.gaurav.dsi.model.MDSDeliveryTrips;
import org.gaurav.dsi.model.MDSDeliveryTripsDet;
import org.gaurav.dsi.model.MDSRouteMaster;
import org.gaurav.dsi.model.MDSRouteReturn;

public class DSPrepareMenifest extends SvrProcess {

	int DS_DeliveryTrips_ID;
	MDSDeliveryTrips trips;
	boolean isProcessed=false;
	PreparedStatement pstmt;
	ResultSet rs;
	@Override
	protected void prepare() 
	{
		DS_DeliveryTrips_ID=getRecord_ID();
		trips=new MDSDeliveryTrips(getCtx(), DS_DeliveryTrips_ID, get_TrxName());
		isProcessed=trips.isProcessing();
	}

	@Override
	protected String doIt() throws Exception
	{
		if(!isProcessed)
		{
			int routeMasterID=trips.getDS_RouteMaster_ID();
			MDSRouteMaster routeMaster=new MDSRouteMaster(getCtx(), routeMasterID, get_TrxName());
			MDSRouteReturn[] returnTo=routeMaster.getRouteReturn();

			String sql="select rout.DS_ISTRANSITPOINT,rout.DS_ToCountry_ID,rout.C_City_ID,rout.LineNo,agnt.DS_AGENTMASTER_ID,agnt.c_bpartner_id from ds_routeto rout"
					+ " left outer join DS_AGENTMASTER agnt on rout.DS_ROUTETO_ID=agnt.DS_ROUTETO_ID where rout.DS_ROUTEMASTER_ID=? order by rout.LineNo";
			try
			{
				pstmt=DB.prepareStatement(sql, get_TrxName());
				pstmt.setInt(1, trips.getDS_RouteMaster_ID());
				boolean transit;
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					MDSDeliveryRouteDetails details=new MDSDeliveryRouteDetails(getCtx(), 0, get_TrxName());
					details.setDS_Delivery_Trips_ID(DS_DeliveryTrips_ID);
					details.setDS_ToCountry_ID(rs.getInt("DS_ToCountry_ID"));
					details.setC_City_ID(rs.getInt("C_City_ID"));
					details.setLineNo(rs.getInt("LineNo"));
					if(rs.getString("DS_ISTRANSITPOINT").equals("Y"))
						transit=true;
					else
						transit=false;
					
					details.setDS_IsTransitPoint(transit);
					details.setC_BPartner_ID(rs.getInt("C_BPartner_ID"));
					details.setDS_AgentMaster_ID(rs.getInt("DS_AGENTMASTER_ID"));
					details.saveEx();
				}
			}
			catch (SQLException e) 
			{
				log.log(Level.WARNING, "Exception: " + e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs=null;
				pstmt=null;
			}

			for(MDSRouteReturn routeret:returnTo)
			{
				MDSDeliveryRouteDetails details=new MDSDeliveryRouteDetails(getCtx(), 0, get_TrxName());
				details.setDS_Delivery_Trips_ID(DS_DeliveryTrips_ID);
				details.setDS_ToCountry_ID(routeret.getDS_ToCountry_ID());
				details.setC_City_ID(routeret.getC_City_ID());
				details.setLineNo(routeret.getLineNo());
				details.setDS_IsReturnRoute(true);
				details.saveEx();		
			}
			addLog("Updated Route details");
			
			MDSDeliveryTripsDet[] details=trips.getTripDetails();
			for(MDSDeliveryTripsDet det:details)
			{
				int ShipmentID=det.getM_InOut_ID();
				int PackageID=det.getM_Package_ID();
				int MovementID=det.getM_Movement_ID();
				int RequestID=det.getR_Request_ID();
				if(ShipmentID!=0)
				{
					MInOut inout=new MInOut(getCtx(), ShipmentID, get_TrxName());
					inout.getC_BPartner_ID();
					MBPartnerLocation bplocation=new MBPartnerLocation(getCtx(),inout.getC_BPartner_Location_ID(),get_TrxName());
					det.updateRouteDetails(bplocation);
				}
				if(PackageID!=0)
				{
					MPackage pack=new MPackage(getCtx(), ShipmentID, get_TrxName());
					MInOut inout=(MInOut) pack.getM_InOut();
					inout.getC_BPartner_ID();
					MBPartnerLocation bplocation=new MBPartnerLocation(getCtx(),inout.getC_BPartner_Location_ID(),get_TrxName());
					det.updateRouteDetails(bplocation);
				}
				if(MovementID!=0)
				{
					MMovement movement=new MMovement(getCtx(), MovementID, get_TrxName());
					movement.getC_BPartner_ID();
					if(movement.getC_BPartner_ID()!=0)
					{
						MBPartnerLocation bplocation=new MBPartnerLocation(getCtx(),movement.getC_BPartner_Location_ID(),get_TrxName());
						det.updateRouteDetails(bplocation);
					}
				}
				if(RequestID!=0)
				{
					MRequest request=new MRequest(getCtx(), RequestID, get_TrxName());
					request.getC_BPartner_ID();
					
					if(request.getC_BPartner_ID()!=0)
					{
						MBPartnerLocation bplocation=new MBPartnerLocation(getCtx(),request.get_ValueAsInt("C_BPartner_Location_ID"),get_TrxName());
						det.updateRouteDetails(bplocation);
					}
				}
			}
			addLog("Shipment/Package/Service details updated");
			trips.setDS_ManifestPrepared(true);
			trips.save(get_TrxName());
		}
		else 
		{
			setAgents();
		}
	
		return null;
	}

	private void setAgents() 
	{
		MDSDeliveryRouteDetails[] getDetails=trips.getInTransitRouteDetails();
		for(MDSDeliveryRouteDetails routeDetails:getDetails)
		{
			int bpartner=DB.getSQLValue(get_TrxName(), "select c_bpartner_id from DS_Delivery_RouteDetails where C_BPartner_ID="+routeDetails.getC_BPartner_ID()+
					" and DS_IsTransitPoint='N' and DS_Delivery_Trips_ID="+trips.getDS_Delivery_Trips_ID());
			if(bpartner==-1)
				DB.executeUpdate("delete from DS_Delivery_RouteDetails where DS_Delivery_RouteDetails_ID="+routeDetails.getDS_Delivery_RouteDetails_ID(), get_TrxName());
		}
		
	}

}
