package com.gaurav.dsi.purchase.factories;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.I_R_Request;
import org.compiere.model.MBPartner;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.MRequest;
import org.compiere.model.MUser;
import org.gaurav.dsi.model.MDSIManufacturingPlan;
import org.gaurav.dsi.model.MDSOrgMaster;
import com.gaurav.dsi.purchase.callout.CallOutApprovedPurchaseRequest;
import com.gaurav.dsi.purchase.callout.CallOutCopyPRDetailsToInvoice;
import com.gaurav.dsi.purchase.callout.CallOutCopySalesForecastDetToManufacturePlan;
import com.gaurav.dsi.purchase.callout.CallOutPurchaseRequestDetails;
import com.gaurav.dsi.purchase.callout.CallOutRequest;
import com.gaurav.dsi.purchase.callout.CallOutSetBPartnerLocation;
import com.gaurav.dsi.purchase.callout.CallOutSetDefautlSubGroup;
import com.gaurav.dsi.purchase.callout.CallOutSetPriceListForInvoiceRelatedProduct;
import com.gaurav.dsi.purchase.callout.CallOutSetPriceListForOrderRelatedProduct;
import com.gaurav.dsi.purchase.callout.CallOutTags;
import com.gaurav.dsi.purchase.callout.CallOutVerifyPaymentAmt;
import com.gaurav.dsi.purchase.callout.SetSalesRepInRequest;

public class DSIPurchaseCallOutFactory implements IColumnCalloutFactory{

	public class CallOutApplySOReserveQty {

		public CallOutApplySOReserveQty() {
			// TODO Auto-generated constructor stub
		}

	}

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) 
	{
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MRequest.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_IsApproved"))
			list.add(new CallOutApprovedPurchaseRequest());
		
		if(tableName.equalsIgnoreCase(MOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_Product_Request_ID"))
			list.add(new CallOutPurchaseRequestDetails());
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_Product_Request_ID"))
			list.add(new CallOutCopyPRDetailsToInvoice());
		
		if(tableName.equalsIgnoreCase(MRequest.Table_Name) 
				&& columnName.equalsIgnoreCase(I_R_Request.COLUMNNAME_RequestAmt))
			list.add(new CallOutVerifyPaymentAmt());
		
		if(tableName.equalsIgnoreCase(MUser.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_Tags_ID"))
			list.add(new CallOutTags());
				
		if(tableName.equalsIgnoreCase(MRequest.Table_Name) 
				&& columnName.equalsIgnoreCase(I_R_Request.COLUMNNAME_R_Group_ID))
			list.add(new SetSalesRepInRequest());
		
		if(tableName.equalsIgnoreCase(MDSOrgMaster.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSOrgMaster.COLUMNNAME_DS_Tags_ID))
			list.add(new CallOutTags());
		
		if(tableName.equalsIgnoreCase(MBPartner.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_Tags_ID"))
			list.add(new CallOutTags());
		
		if(tableName.equalsIgnoreCase(MRequest.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_IsApproved"))
			list.add(new CallOutRequest());
		
		if(tableName.equalsIgnoreCase(MRequest.Table_Name) 
				&& columnName.equalsIgnoreCase(MRequest.COLUMNNAME_C_BPartner_ID))
			list.add(new CallOutSetBPartnerLocation());
		
		if(tableName.equalsIgnoreCase(MRequest.Table_Name) 
				&& columnName.equalsIgnoreCase(MRequest.COLUMNNAME_R_Group_ID))
			list.add(new CallOutSetDefautlSubGroup());
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name) 
				&& columnName.equalsIgnoreCase("RelatedProduct_ID"))
			list.add(new CallOutSetPriceListForInvoiceRelatedProduct());
		
		if(tableName.equalsIgnoreCase(MOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase("RelatedProduct_ID"))
			list.add(new CallOutSetPriceListForOrderRelatedProduct());
		
		if(tableName.equalsIgnoreCase(MDSIManufacturingPlan.Table_Name) 
				&& columnName.equalsIgnoreCase("DSI_SalesForecast_ID"))
			list.add(new CallOutCopySalesForecastDetToManufacturePlan());
		
		if(tableName.equalsIgnoreCase(MDSIManufacturingPlan.Table_Name) 
				&& columnName.equalsIgnoreCase("M_Warehouse_ID"))
			list.add(new CallOutCopySalesForecastDetToManufacturePlan());
		
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}

}
