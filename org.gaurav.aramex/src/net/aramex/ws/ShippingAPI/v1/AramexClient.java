package net.aramex.ws.ShippingAPI.v1;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.compiere.model.MInOut;
import org.compiere.model.MSysConfig;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.dsi.model.MDSAramexShipmentTracking;

import com.microsoft.schemas._2003._10.Serialization.Arrays.ArrayOfKeyValueOfstringArrayOfTrackingResultmFAkxlpYKeyValueOfstringArrayOfTrackingResultmFAkxlpY;

public class AramexClient extends SvrProcess
{
	String trx = null;

	@Override
	protected void prepare() {
		trx = get_TrxName();
		
	}

	@Override
	protected String doIt() throws Exception {
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		int M_InOut_ID = 0;
		String trackingNo;
		int i=0;
		try {
			String sql = "select distinct mi.TRACKINGNO,mi.M_INOUT_ID,mi.ad_client_id,mi.ad_org_id,mi.created,mi.createdby,mi.movementdate "
					+ " from m_inout mi where mi.TRACKINGNO is not null "
					+ " and mi.movementdate > add_months(getdate(),-6) AND MI.TrackingNo='42066217771' "
					+ " ORDER BY mi.movementdate ";
			preStatement = DB.prepareStatement(sql,trx);
			rs = preStatement.executeQuery();
			while (rs.next()) 
			{
				i++;
				trackingNo = rs.getString("TRACKINGNO");
				M_InOut_ID = rs.getInt("M_INOUT_ID");
				log.info("Tracking number"+trackingNo);
				if(!totallyDelivered(trackingNo,M_InOut_ID))
				{
				 
					String reference1 = "";
					String reference2 = "";
					String reference3 = "";
					String reference4 = "";
					String reference5 = "";

					java.lang.String[] shipments = {""+trackingNo+""};
					String airwaybillnumber;
					String problemCode;
					String updatedCode;
					Calendar updatedDateTime;
					String description;
					String location;
					String updateDT;
					boolean lastTrackingUpdateOnly = Boolean.FALSE;
					BasicHttpBinding_Service_1_0Stub main = new BasicHttpBinding_Service_1_0Stub(new URL("http://ws.aramex.net/shippingapi/tracking/service_1_0.svc?WSDL"),	null);
					ClientInfo clientInfo = getClientInfo();
					Transaction transaction = new Transaction(reference1,reference2, reference3, reference4, reference5);

					ShipmentTrackingRequest shipmentTrackingRequest = new ShipmentTrackingRequest(
							clientInfo, transaction, shipments,
							lastTrackingUpdateOnly);
					
					ShipmentTrackingResponse response = main.trackShipments(shipmentTrackingRequest);
					System.out.println("has errors "+response.getHasErrors());
					String[] responsea = response.getNonExistingWaybills();
					for(String a : responsea)
					{
						System.out.println(" Dont know "+a);
					}
					ArrayOfKeyValueOfstringArrayOfTrackingResultmFAkxlpYKeyValueOfstringArrayOfTrackingResultmFAkxlpY[] results = response
							.getTrackingResults();
				
					if(results.length>0)
					{
						for (ArrayOfKeyValueOfstringArrayOfTrackingResultmFAkxlpYKeyValueOfstringArrayOfTrackingResultmFAkxlpY result : results) 
						{
	
							TrackingResult trackingResults[] = result.getValue();
							for (TrackingResult trackingResult : trackingResults) 
							{
								airwaybillnumber = result.getKey();
								System.out.println("Airway bill - "+airwaybillnumber);
								problemCode = trackingResult.getProblemCode();
								updatedCode = trackingResult.getUpdateCode();
								updateDT = trackingResult.getUpdateDateTime().getTime().toString();
								description = trackingResult.getUpdateDescription();
								location = trackingResult.getUpdateLocation();
								updatedDateTime = trackingResult.getUpdateDateTime();
								boolean isExist = AirWayBillRecordExists(airwaybillnumber,updatedCode,M_InOut_ID);
								if(!isExist)
								{
									boolean isDelivered=false;
									String city=location;
									if(updatedCode.equals("SH005"))
									{
										isDelivered=true;
									}
									
									Date updatedDate = updatedDateTime.getTime();
									Timestamp updated = new java.sql.Timestamp(updatedDate.getTime());
									if(location.contains(","))
									{
										city=location.substring(0,location.indexOf(","));
									}
								    	String country=location.substring(location.lastIndexOf(",")+2);
	
										MDSAramexShipmentTracking tracking = new MDSAramexShipmentTracking(getCtx(), 0, trx);
										tracking.setLocationComment(location);
										tracking.setM_InOut_ID(M_InOut_ID);
										tracking.setDS_ProblemCode(problemCode);
										tracking.setCity(city);
										tracking.setCountryName(country);
										tracking.setDescription(description);
										tracking.setDateDelivered(updated);
										tracking.setDS_Updated(updateDT);
										tracking.setDS_UpdatedCode(updatedCode);
										tracking.setDS_ResultKey(airwaybillnumber);
										tracking.setIsDelivered(isDelivered);
										tracking.saveEx();
										MInOut inout = new MInOut(getCtx(), M_InOut_ID, get_TrxName());
										inout.set_ValueOfColumn("DateDelivered", updated);
										inout.saveEx();
								}	
								
							}
						}
						
					}
				}
				log.info("Total Number of tracking checked : "+i);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			DB.close(rs, preStatement);
			rs = null;
			preStatement = null;
		}
		return "Total number of shipment checked or updated : " +i;
	}

	
	private boolean totallyDelivered(String trackingNo, int m_InOut_ID) 
	{
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		String sql="SELECT TRACK.DS_UPDATEDCODE FROM DS_ARAMEX_SHIPMENTTRACKING TRACK where TRACK.M_INOUT_ID=? and TRACK.TRACKINGNO=? and TRACK.DS_UPDATEDCODE='SH247'";
		boolean totallyDelivered = false;
		try {
			preStatement = DB.prepareStatement(sql,trx);
			preStatement.setInt(1, m_InOut_ID);
			preStatement.setString(2, trackingNo);
			rs = preStatement.executeQuery();
			if (rs.next()) 
			{
				totallyDelivered=true;
						
			}
		} catch (SQLException e) 
		{
			System.out.println("Unknown exception occured while checking assignment"+e);
		}
		finally 
		{
			
		}
		return totallyDelivered;
	}
	
	private boolean AirWayBillRecordExists(String AirWayBillNumber, String updatedCode, int m_InOut_ID) 
	{
		PreparedStatement preStatement = null;
		ResultSet rs = null;
		boolean isExist=false;
		try 
		{
			String sql = "select DS_UPDATEDCODE from DS_Aramex_ShipmentTracking where ds_resultkey=? and DS_UPDATEDCODE=? AND M_INOUT_ID=?";
			preStatement = DB.prepareStatement(sql,trx);
			preStatement.setString(1, AirWayBillNumber);
			preStatement.setString(2, updatedCode);
			preStatement.setInt(3, m_InOut_ID);
			rs = preStatement.executeQuery();
			if (rs.next()) 
			{
				isExist=true;
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		finally 
		{
			
		}
		return isExist;
	}

	static ClientInfo getClientInfo()
	{
		ClientInfo clientInfo = new ClientInfo();
	
		clientInfo.setVersion(MSysConfig.getValue("DS_Aramex_Version","1.0"));
		clientInfo.setPassword(MSysConfig.getValue("DS_Aramex_Password","Dispatch2002"));
		clientInfo.setUserName(MSysConfig.getValue("DS_Aramex_UserName","dispatch@shaik.net"));
		clientInfo.setAccountPin(MSysConfig.getValue("DS_Aramex_AccountPin","216316"));
		clientInfo.setAccountEntity(MSysConfig.getValue("DS_Aramex_AccountEntity","BAH"));
		clientInfo.setAccountNumber(MSysConfig.getValue("DS_Aramex_AccountNumber","6052"));
		clientInfo.setAccountCountryCode(MSysConfig.getValue("DS_Aramex_CountryCode","BH"));
		
		return clientInfo;
	} 
}

