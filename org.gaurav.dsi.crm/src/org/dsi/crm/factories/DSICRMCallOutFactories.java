package org.dsi.crm.factories;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.I_AD_User;
import org.compiere.model.I_M_InOut;
import org.compiere.model.I_M_Movement;
import org.compiere.model.I_M_MovementLine;
import org.compiere.model.MBPartner;
import org.compiere.model.MInOut;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MUser;
import org.dsi.crm.callouts.CallOutBPartnerLocationSetBillLocation;
import org.dsi.crm.callouts.CallOutCopyContactMasterToUser;
import org.dsi.crm.callouts.CallOutCopyServiceRequestDetails;
import org.dsi.crm.callouts.CallOutLeadCopyNameToValue;
import org.dsi.crm.callouts.CallOutLeadOrgMaster;
import org.dsi.crm.callouts.CallOutMovementBPartner;
import org.dsi.crm.callouts.CallOutMovementLineProduct;
import org.dsi.crm.callouts.CallOutMovementPriceList;
import org.dsi.crm.callouts.CallOutRefillGetAmount;
import org.dsi.crm.callouts.CallOutRefillUpdateBP;
import org.dsi.crm.callouts.CallOutResetInventoryLineDetails;
import org.dsi.crm.callouts.CallOutSetBPartnerTypeOnOrder;
import org.dsi.crm.callouts.CallOutSetContactMaster;
import org.dsi.crm.callouts.CallOutSetDateToTripSchedule;
import org.dsi.crm.callouts.CallOutSetOrderSourceOnPOS;
import org.dsi.crm.callouts.CallOutSetOrderSourceValue;
import org.dsi.crm.callouts.CallOutSetOrgMasters;
import org.dsi.crm.callouts.CallOutSetPriceEnteredTaxPOSDetails;
import org.dsi.crm.callouts.CallOutSetProductDescription;
import org.dsi.crm.callouts.CallOutSetProfitCenters;
import org.dsi.crm.callouts.CallOutSetToCountryInTripschedule;
import org.dsi.crm.callouts.CallOutShipmentOrder;
import org.dsi.crm.callouts.CallOutTripScheduleBPartner;
import org.gaurav.dsi.model.MDSCustomerVisits;
import org.gaurav.dsi.model.MDSDeliveryTrips;
import org.gaurav.dsi.model.MDSIRefillEntryMain;
import org.gaurav.dsi.model.MDSPOSHeader;
import org.gaurav.dsi.model.MDSPOSItemDetails;
import org.gaurav.dsi.model.MDSProductRequest;
import org.gaurav.dsi.model.X_DS_Delivery_Trips;

public class DSICRMCallOutFactories implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) 
	{
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MBPartner.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_OrgMaster_ID"))
			list.add(new CallOutSetOrgMasters());
		
		if(tableName.equalsIgnoreCase(MBPartner.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_ContactMaster_ID"))
			list.add(new CallOutSetContactMaster());
		
		if(tableName.equalsIgnoreCase(MDSIRefillEntryMain.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSIRefillEntryMain.COLUMNNAME_DSI_Refill_PrintForm_ID))
			list.add(new CallOutRefillUpdateBP());
		
		if(tableName.equalsIgnoreCase(MDSIRefillEntryMain.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSIRefillEntryMain.COLUMNNAME_NetAmtToInvoice))
			list.add(new CallOutRefillGetAmount());
		
		if(tableName.equalsIgnoreCase(MInventoryLine.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_ServiceRequest_ID"))
			list.add(new CallOutCopyServiceRequestDetails());
		
		if(tableName.equalsIgnoreCase(MMovement.Table_Name) 
				&& columnName.equalsIgnoreCase("M_PriceList_ID"))
			list.add(new CallOutMovementPriceList());
		
		if(tableName.equalsIgnoreCase(MMovement.Table_Name) 
				&& columnName.equalsIgnoreCase(I_M_Movement.COLUMNNAME_C_BPartner_ID))
			list.add(new CallOutMovementBPartner());
		
		if(tableName.equalsIgnoreCase(MMovementLine.Table_Name) 
				&& columnName.equalsIgnoreCase(I_M_MovementLine.COLUMNNAME_M_Product_ID))
			list.add(new CallOutMovementLineProduct());
		
		if(tableName.equalsIgnoreCase(MUser.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_ContactMaster_ID"))
			list.add(new CallOutCopyContactMasterToUser());
		
		if(tableName.equalsIgnoreCase(MUser.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_OrgMaster_ID"))
			list.add(new CallOutLeadOrgMaster());
		
		if(tableName.equalsIgnoreCase(MUser.Table_Name) 
				&& columnName.equalsIgnoreCase(I_AD_User.COLUMNNAME_Name))
			list.add(new CallOutLeadCopyNameToValue());
		
		if(tableName.equalsIgnoreCase(MInOut.Table_Name) 
				&& columnName.equalsIgnoreCase(I_M_InOut.COLUMNNAME_C_Order_ID))
			list.add(new CallOutShipmentOrder());
		
		if(tableName.equalsIgnoreCase(MDSDeliveryTrips.Table_Name) 
				&& columnName.equalsIgnoreCase(X_DS_Delivery_Trips.COLUMNNAME_DS_RouteMaster_ID))
			list.add(new CallOutSetToCountryInTripschedule());
		
		if(tableName.equalsIgnoreCase(MMovementLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MMovementLine.COLUMNNAME_M_MovementLine_ID))
			list.add(new CallOutResetInventoryLineDetails());
		
		if(tableName.equalsIgnoreCase(MDSCustomerVisits.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSCustomerVisits.COLUMNNAME_DS_Customer_ID))
			list.add(new CallOutTripScheduleBPartner());
	
		if(tableName.equalsIgnoreCase(MDSProductRequest.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSProductRequest.COLUMNNAME_M_Product_ID))
			list.add(new CallOutSetProductDescription());
		
		if(tableName.equalsIgnoreCase(MOrder.Table_Name) 
				&& columnName.equalsIgnoreCase(MOrder.COLUMNNAME_Bill_BPartner_ID))
			list.add(new CallOutSetBPartnerTypeOnOrder());
		
		if(tableName.equalsIgnoreCase(MOrder.Table_Name) 
				&& columnName.equalsIgnoreCase(MOrder.COLUMNNAME_C_BPartner_ID))
			list.add(new CallOutSetBPartnerTypeOnOrder());
		
		if(tableName.equalsIgnoreCase(MDSDeliveryTrips.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSDeliveryTrips.COLUMNNAME_DS_TripDateFrom))
			list.add(new CallOutSetDateToTripSchedule());
		
		if(tableName.equalsIgnoreCase(MDSDeliveryTrips.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSDeliveryTrips.COLUMNNAME_DS_TripDateTo))
			list.add(new CallOutSetDateToTripSchedule());
			
		if(tableName.equalsIgnoreCase(MOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MOrderLine.COLUMNNAME_C_Order_ID))
			list.add(new CallOutSetProfitCenters());
		
		if(tableName.equalsIgnoreCase(MDSPOSItemDetails.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSPOSItemDetails.COLUMNNAME_M_Product_ID))
			list.add(new CallOutSetPriceEnteredTaxPOSDetails());
		
		if(tableName.equalsIgnoreCase(MDSPOSItemDetails.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSPOSItemDetails.COLUMNNAME_QtyOrdered))
			list.add(new CallOutSetPriceEnteredTaxPOSDetails());
		
		if(tableName.equalsIgnoreCase(MDSPOSHeader.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSPOSHeader.COLUMNNAME_C_OrderSource_ID))
			list.add(new CallOutSetOrderSourceValue());
		
		if(tableName.equalsIgnoreCase(MOrder.Table_Name) 
				&& columnName.equalsIgnoreCase(MOrder.COLUMNNAME_C_OrderSource_ID))
			list.add(new CallOutSetOrderSourceValue());
		
		if(tableName.equalsIgnoreCase(MDSPOSHeader.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSPOSHeader.COLUMNNAME_DS_POSHeader_ID))
			list.add(new CallOutSetOrderSourceOnPOS());
		
		if(tableName.equalsIgnoreCase(MOrder.Table_Name) 
				&& columnName.equalsIgnoreCase(MOrder.COLUMNNAME_C_BPartner_Location_ID))
			list.add(new CallOutBPartnerLocationSetBillLocation());
		
		
		
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}

}
