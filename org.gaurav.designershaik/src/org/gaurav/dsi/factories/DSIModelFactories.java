package org.gaurav.dsi.factories;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MCContactActivity;
import org.gaurav.dsi.model.MDSAgentMaster;
import org.gaurav.dsi.model.MDSAssignmentDetails;
import org.gaurav.dsi.model.MDSCustomerVisits;
import org.gaurav.dsi.model.MDSCustomerVisitsDetails;
import org.gaurav.dsi.model.MDSDocumentsForTrip;
import org.gaurav.dsi.model.MDSIAging;
import org.gaurav.dsi.model.MDSAssignment;
import org.gaurav.dsi.model.MDSAttachmentTags;
import org.gaurav.dsi.model.MDSAvailEmployee;
import org.gaurav.dsi.model.MDSB2BConfiguration;
import org.gaurav.dsi.model.MDSB2BEmailConf;
import org.gaurav.dsi.model.MDSContactMaster;
import org.gaurav.dsi.model.MDSContactMasterLocation;
import org.gaurav.dsi.model.MDSContactRelation;
import org.gaurav.dsi.model.MDSDays;
import org.gaurav.dsi.model.MDSDeliveryRouteDetails;
import org.gaurav.dsi.model.MDSDeliveryTrips;
import org.gaurav.dsi.model.MDSDeliveryTripsDet;
import org.gaurav.dsi.model.MDSDenomiationDetCashBook;
import org.gaurav.dsi.model.MDSDenomiationDetTrans;
import org.gaurav.dsi.model.MDSDenominationOnStatement;
import org.gaurav.dsi.model.MDSDunningEntryEmails;
import org.gaurav.dsi.model.MDSDunningIncludeEmails;
import org.gaurav.dsi.model.MDSEmailContacts;
import org.gaurav.dsi.model.MDSFinReports;
import org.gaurav.dsi.model.MDSFinReportslevel1;
import org.gaurav.dsi.model.MDSFinReportslevel2;
import org.gaurav.dsi.model.MDSFinReportslevel3;
import org.gaurav.dsi.model.MDSFinReportslevel4;
import org.gaurav.dsi.model.MDSFinReportslevel5;
import org.gaurav.dsi.model.MDSFinReportslevel6;
import org.gaurav.dsi.model.MDSFixedDeposit;
import org.gaurav.dsi.model.MDSIBoxLabelsT;
import org.gaurav.dsi.model.MDSIExportPayments;
import org.gaurav.dsi.model.MDSIExportPaymentsLine;
import org.gaurav.dsi.model.MDSIRefillEntryMain;
import org.gaurav.dsi.model.MDSIRefillPrintForm;
import org.gaurav.dsi.model.MDSISerialNoTrx;
import org.gaurav.dsi.model.MDSISerialNumberMaster;
import org.gaurav.dsi.model.MDSISrNoProduct;
import org.gaurav.dsi.model.MDSITRecallProducts;
import org.gaurav.dsi.model.MDSITrackProductBatch;
import org.gaurav.dsi.model.MDSImportInvoicesFiling;
import org.gaurav.dsi.model.MDSInventoryAging;
import org.gaurav.dsi.model.MDSInvoiceTaxDetails;
import org.gaurav.dsi.model.MDSManufacturingQA;
import org.gaurav.dsi.model.MDSManufacturingQABOM;
import org.gaurav.dsi.model.MDSOrgContactRelation;
import org.gaurav.dsi.model.MDSOrgMaster;
import org.gaurav.dsi.model.MDSOrgMasterLocation;
import org.gaurav.dsi.model.MDSOutstandingAgingSummary;
import org.gaurav.dsi.model.MDSPOSHeader;
import org.gaurav.dsi.model.MDSPOSItemDetails;
import org.gaurav.dsi.model.MDSProductPriceSerialNo;
import org.gaurav.dsi.model.MDSProductRequest;
import org.gaurav.dsi.model.MDSQAMORejection;
import org.gaurav.dsi.model.MDSQARejectionReason;
import org.gaurav.dsi.model.MDSRequiredDocForShipment;
import org.gaurav.dsi.model.MDSRequiredShipDocuments;
import org.gaurav.dsi.model.MDSRouteMaster;
import org.gaurav.dsi.model.MDSRouteReturn;
import org.gaurav.dsi.model.MDSRouteTo;
import org.gaurav.dsi.model.MDSServiceRequest;
import org.gaurav.dsi.model.MDSSettledInvoices;
import org.gaurav.dsi.model.MDSTags;
import org.gaurav.dsi.model.MDSTaxFiling;
import org.gaurav.dsi.model.MDSTaxFilingDet;
import org.gaurav.dsi.model.MDSTripConfiguration;
import org.gaurav.dsi.model.MDSTripExpenseType;
import org.gaurav.dsi.model.MDSTripSchdCashReqstAmt;
import org.gaurav.dsi.model.MDSTripScheduleEstimations;


public class DSIModelFactories implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) 
	{
		if(tableName.equals(MDSIAging.Table_Name))
			return MDSIAging.class;
		
		if(tableName.equals(MDSEmailContacts.Table_Name))
			return MDSEmailContacts.class;
		
		if(tableName.equals(MDSFinReports.Table_Name))
			return MDSFinReports.class;
		
		if(tableName.equals(MDSFinReportslevel1.Table_Name))
			return MDSFinReportslevel1.class;
		
		if(tableName.equals(MDSFinReportslevel2.Table_Name))
			return MDSFinReportslevel2.class;
		
		if(tableName.equals(MDSFinReportslevel3.Table_Name))
			return MDSFinReportslevel3.class;
		
		if(tableName.equals(MDSFinReportslevel4.Table_Name))
			return MDSFinReportslevel4.class;
		
		if(tableName.equals(MDSFinReportslevel5.Table_Name))
			return MDSFinReportslevel5.class;
		
		if(tableName.equals(MDSFinReportslevel6.Table_Name))
			return MDSFinReportslevel6.class;

		if(tableName.equals(MDSIExportPayments.Table_Name))
			return MDSIExportPayments.class;
		
		if(tableName.equals(MDSIExportPaymentsLine.Table_Name))
			return MDSIExportPaymentsLine.class;
		
		if(tableName.equals(MDSAgentMaster.Table_Name))
			return MDSAgentMaster.class;
		
		if(tableName.equals(MDSAssignment.Table_Name))
			return MDSAssignment.class;
		
		if(tableName.equals(MDSAttachmentTags.Table_Name))
			return MDSAttachmentTags.class;
		
		if(tableName.equals(MDSAvailEmployee.Table_Name))
			return MDSAvailEmployee.class;
		
		if(tableName.equals(MDSContactMaster.Table_Name))
			return MDSContactMaster.class;
		
		if(tableName.equals(MDSContactMasterLocation.Table_Name))
			return MDSContactMasterLocation.class;
		
		if(tableName.equals(MDSContactRelation.Table_Name))
			return MDSContactRelation.class;
		
		if(tableName.equals(MDSDays.Table_Name))
			return MDSDays.class;
		
		if(tableName.equals(MDSDeliveryRouteDetails.Table_Name))
			return MDSDeliveryRouteDetails.class;
		
		if(tableName.equals(MDSDeliveryTrips.Table_Name))
			return MDSDeliveryTrips.class;
		
		if(tableName.equals(MDSDeliveryTripsDet.Table_Name))
			return MDSDeliveryTripsDet.class;
		
		if(tableName.equals(MDSIBoxLabelsT.Table_Name))
			return MDSIBoxLabelsT.class;

		if(tableName.equals(MDSIRefillEntryMain.Table_Name))
			return MDSIRefillEntryMain.class;
		
		if(tableName.equals(MDSIRefillPrintForm.Table_Name))
			return MDSIRefillPrintForm.class;
		
		if(tableName.equals(MDSISerialNoTrx.Table_Name))
			return MDSISerialNoTrx.class;
		
		if(tableName.equals(MDSISerialNumberMaster.Table_Name))
			return MDSISerialNumberMaster.class;
		
		if(tableName.equals(MDSISrNoProduct.Table_Name))
			return MDSISrNoProduct.class;
		
		if(tableName.equals(MDSOrgContactRelation.Table_Name))
			return MDSOrgContactRelation.class;
		
		if(tableName.equals(MDSOrgMaster.Table_Name))
			return MDSOrgMaster.class;
		
		if(tableName.equals(MDSOrgMasterLocation.Table_Name))
			return MDSOrgMasterLocation.class;
		
		if(tableName.equals(MDSProductRequest.Table_Name))
			return MDSProductRequest.class;
		
		if(tableName.equals(MDSRouteMaster.Table_Name))
			return MDSRouteMaster.class;
		
		if(tableName.equals(MDSRouteReturn.Table_Name))
			return MDSRouteReturn.class;
		
		if(tableName.equals(MDSRouteTo.Table_Name))
			return MDSRouteTo.class;
		
		if(tableName.equals(MDSServiceRequest.Table_Name))
			return MDSServiceRequest.class;
		
		if(tableName.equals(MDSTags.Table_Name))
			return MDSTags.class;
		
		if(tableName.equals(MDSOutstandingAgingSummary.Table_Name))
			return MDSOutstandingAgingSummary.class;
		
		if(tableName.equals(MDSB2BConfiguration.Table_Name))
			return MDSB2BConfiguration.class;
		
		if(tableName.equals(MDSB2BEmailConf.Table_Name))
			return MDSB2BEmailConf.class;
		
		if(tableName.equals(MDSDunningIncludeEmails.Table_Name))
			return MDSDunningIncludeEmails.class;
		
		if(tableName.equals(MDSDunningEntryEmails.Table_Name))
			return MDSDunningEntryEmails.class;
		
		if(tableName.equals(MDSFixedDeposit.Table_Name))
			return MDSFixedDeposit.class;
		
		if(tableName.equals(MDSManufacturingQABOM.Table_Name))
			return MDSManufacturingQABOM.class;
		
		if(tableName.equals(MDSManufacturingQA.Table_Name))
			return MDSManufacturingQA.class;
		
		if(tableName.equals(MDSInventoryAging.Table_Name))
			return MDSInventoryAging.class;
		
		if(tableName.equals(MDSCustomerVisits.Table_Name))
			return MDSCustomerVisits.class;
		
		if(tableName.equals(MDSCustomerVisitsDetails.Table_Name))
			return MDSCustomerVisitsDetails.class;
		
		if(tableName.equals(MDSTripConfiguration.Table_Name))
			return MDSTripConfiguration.class;
		
		if(tableName.equals(MDSQAMORejection.Table_Name))
			return MDSQAMORejection.class;
		
		if(tableName.equals(MDSQARejectionReason.Table_Name))
			return MDSQARejectionReason.class;
		
		if(tableName.equals(MDSAssignmentDetails.Table_Name))
			return MDSAssignmentDetails.class;
		
		if(tableName.equals(MDSTripExpenseType.Table_Name))
			return MDSTripExpenseType.class;
		
		if(tableName.equals(MDSTripScheduleEstimations.Table_Name))
			return MDSTripScheduleEstimations.class;
		
		if(tableName.equals(MDSTripSchdCashReqstAmt.Table_Name))
			return MDSTripSchdCashReqstAmt.class;
		
		if(tableName.equals(MDSDocumentsForTrip.Table_Name))
			return MDSDocumentsForTrip.class;
		
		if(tableName.equals(MDSRequiredShipDocuments.Table_Name))
			return MDSRequiredShipDocuments.class;
		
		if(tableName.equals(MDSRequiredDocForShipment.Table_Name))
			return MDSRequiredDocForShipment.class;
		
		if(tableName.equals(MDSProductPriceSerialNo.Table_Name))
			return MDSProductPriceSerialNo.class;
		
		if(tableName.equals(MCContactActivity.Table_Name))
			return MCContactActivity.class;
		
		if(tableName.equals(MDSITrackProductBatch.Table_Name))
			return MDSITrackProductBatch.class;
		
		if(tableName.equals(MDSDenomiationDetCashBook.Table_Name))
			return MDSDenomiationDetCashBook.class;
		
		if(tableName.equals(MDSDenomiationDetTrans.Table_Name))
			return MDSDenomiationDetTrans.class;
			
		if(tableName.equals(MDSSettledInvoices.Table_Name))
			return MDSSettledInvoices.class;
		
		if(tableName.equals(MDSDenominationOnStatement.Table_Name))
			return MDSDenominationOnStatement.class;
		
		if(tableName.equals(MDSPOSHeader.Table_Name))
			return MDSPOSHeader.class;
		
		if(tableName.equals(MDSPOSItemDetails.Table_Name))
			return MDSPOSItemDetails.class;
		
		if(tableName.equals(MDSTaxFiling.Table_Name))
			return MDSTaxFiling.class;
		
		if(tableName.equals(MDSTaxFilingDet.Table_Name))
			return MDSTaxFilingDet.class;
		
		if(tableName.equals(MDSInvoiceTaxDetails.Table_Name))
			return MDSInvoiceTaxDetails.class;
			
		if(tableName.equals(MDSImportInvoicesFiling.Table_Name))
			return MDSImportInvoicesFiling.class;
		
		if(tableName.equals(MDSITRecallProducts.Table_Name))
			return MDSITRecallProducts.class;
			
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) 
	{
		if(tableName.equals(MDSIAging.Table_Name))
			return new MDSIAging(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSEmailContacts.Table_Name))
			return new MDSEmailContacts(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFinReports.Table_Name))
			return new MDSFinReports(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFinReportslevel1.Table_Name))
			return new MDSFinReportslevel1(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFinReportslevel2.Table_Name))
			return new MDSFinReportslevel2(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFinReportslevel3.Table_Name))
			return new MDSFinReportslevel3(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFinReportslevel4.Table_Name))
			return new MDSFinReportslevel4(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFinReportslevel5.Table_Name))
			return new MDSFinReportslevel5(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFinReportslevel6.Table_Name))
			return new MDSFinReportslevel6(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSIExportPayments.Table_Name))
			return new MDSIExportPayments(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSIExportPaymentsLine.Table_Name))
			return new MDSIExportPaymentsLine(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSAgentMaster.Table_Name))
			return new MDSAgentMaster(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSAssignment.Table_Name))
			return new MDSAssignment(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSAttachmentTags.Table_Name))
			return new MDSAttachmentTags(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSAvailEmployee.Table_Name))
			return new MDSAvailEmployee(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSContactMaster.Table_Name))
			return new MDSContactMaster(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSContactMasterLocation.Table_Name))
			return new MDSContactMasterLocation(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSContactRelation.Table_Name))
			return new MDSContactRelation(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDays.Table_Name))
			return new MDSDays(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDeliveryRouteDetails.Table_Name))
			return new MDSDeliveryRouteDetails(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDeliveryTrips.Table_Name))
			return new MDSDeliveryTrips(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDeliveryTripsDet.Table_Name))
			return new MDSDeliveryTripsDet(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSIBoxLabelsT.Table_Name))
			return new MDSIBoxLabelsT(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSIRefillEntryMain.Table_Name))
			return new MDSIRefillEntryMain(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSIRefillPrintForm.Table_Name))
			return new MDSIRefillPrintForm(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSISerialNoTrx.Table_Name))
			return new MDSISerialNoTrx(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSISerialNumberMaster.Table_Name))
			return new MDSISerialNumberMaster(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSISrNoProduct.Table_Name))
			return new MDSISrNoProduct(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSOrgContactRelation.Table_Name))
			return new MDSOrgContactRelation(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSOrgMaster.Table_Name))
			return new MDSOrgMaster(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSOrgMasterLocation.Table_Name))
			return new MDSOrgMasterLocation(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSProductRequest.Table_Name))
			return new MDSProductRequest(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSRouteMaster.Table_Name))
			return new MDSRouteMaster(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSRouteReturn.Table_Name))
			return new MDSRouteReturn(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSRouteTo.Table_Name))
			return new MDSRouteTo(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSServiceRequest.Table_Name))
			return new MDSServiceRequest(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSOutstandingAgingSummary.Table_Name))
			return new MDSOutstandingAgingSummary(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSB2BConfiguration.Table_Name))
			return new MDSB2BConfiguration(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSB2BEmailConf.Table_Name))
			return new MDSB2BEmailConf(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDunningIncludeEmails.Table_Name))
			return new MDSDunningIncludeEmails(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDunningEntryEmails.Table_Name))
			return new MDSDunningEntryEmails(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSFixedDeposit.Table_Name))
			return new MDSFixedDeposit(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSManufacturingQABOM.Table_Name))
			return new MDSManufacturingQABOM(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSManufacturingQA.Table_Name))
			return new MDSManufacturingQA(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSInventoryAging.Table_Name))
			return new MDSInventoryAging(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSCustomerVisits.Table_Name))
			return new MDSCustomerVisits(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSCustomerVisitsDetails.Table_Name))
			return new MDSCustomerVisitsDetails(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSTripConfiguration.Table_Name))
			return new MDSTripConfiguration(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSQAMORejection.Table_Name))
			return new MDSQAMORejection(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSQARejectionReason.Table_Name))
			return new MDSQARejectionReason(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSAssignmentDetails.Table_Name))
			return new MDSAssignmentDetails(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSTripExpenseType.Table_Name))
			return new MDSTripExpenseType(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSTripScheduleEstimations.Table_Name))
			return new MDSTripScheduleEstimations(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSTripSchdCashReqstAmt.Table_Name))
			return new MDSTripSchdCashReqstAmt(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDocumentsForTrip.Table_Name))
			return new MDSDocumentsForTrip(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSRequiredShipDocuments.Table_Name))
			return new MDSRequiredShipDocuments(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSRequiredDocForShipment.Table_Name))
			return new MDSRequiredDocForShipment(Env.getCtx(), Record_ID, trxName);
	
		if(tableName.equals(MDSProductPriceSerialNo.Table_Name))
			return new MDSProductPriceSerialNo(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MCContactActivity.Table_Name))
			return new MCContactActivity(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSITrackProductBatch.Table_Name))
			return new MDSITrackProductBatch(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDenomiationDetCashBook.Table_Name))
			return new MDSDenomiationDetCashBook(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDenomiationDetTrans.Table_Name))
			return new MDSDenomiationDetTrans(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSSettledInvoices.Table_Name))
			return new MDSSettledInvoices(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSDenominationOnStatement.Table_Name))
			return new MDSDenominationOnStatement(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSPOSHeader.Table_Name))
			return new MDSPOSHeader(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSPOSItemDetails.Table_Name))
			return new MDSPOSItemDetails(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSTaxFiling.Table_Name))
			return new MDSTaxFiling(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSTaxFilingDet.Table_Name))
			return new MDSTaxFilingDet(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSInvoiceTaxDetails.Table_Name))
			return new MDSInvoiceTaxDetails(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSImportInvoicesFiling.Table_Name))
			return new MDSImportInvoicesFiling(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equals(MDSITRecallProducts.Table_Name))
			return new MDSITRecallProducts(Env.getCtx(), Record_ID, trxName);
		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName)
	{
		if(tableName.equals(MDSIAging.Table_Name))
			return new MDSIAging(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSEmailContacts.Table_Name))
			return new MDSEmailContacts(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFinReports.Table_Name))
			return new MDSFinReports(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFinReportslevel1.Table_Name))
			return new MDSFinReportslevel1(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFinReportslevel2.Table_Name))
			return new MDSFinReportslevel2(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFinReportslevel3.Table_Name))
			return new MDSFinReportslevel3(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFinReportslevel4.Table_Name))
			return new MDSFinReportslevel4(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFinReportslevel5.Table_Name))
			return new MDSFinReportslevel5(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFinReportslevel6.Table_Name))
			return new MDSFinReportslevel6(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSIExportPayments.Table_Name))
			return new MDSIExportPayments(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSIExportPaymentsLine.Table_Name))
			return new MDSIExportPaymentsLine(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSAgentMaster.Table_Name))
			return new MDSAgentMaster(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSAssignment.Table_Name))
			return new MDSAssignment(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSAttachmentTags.Table_Name))
			return new MDSAttachmentTags(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSAvailEmployee.Table_Name))
			return new MDSAvailEmployee(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSContactMaster.Table_Name))
			return new MDSContactMaster(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSContactMasterLocation.Table_Name))
			return new MDSContactMasterLocation(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSContactRelation.Table_Name))
			return new MDSContactRelation(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDays.Table_Name))
			return new MDSDays(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDeliveryRouteDetails.Table_Name))
			return new MDSDeliveryRouteDetails(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDeliveryTrips.Table_Name))
			return new MDSDeliveryTrips(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDeliveryTripsDet.Table_Name))
			return new MDSDeliveryTripsDet(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSIBoxLabelsT.Table_Name))
			return new MDSIBoxLabelsT(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSIRefillEntryMain.Table_Name))
			return new MDSIRefillEntryMain(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSIRefillPrintForm.Table_Name))
			return new MDSIRefillPrintForm(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSISerialNoTrx.Table_Name))
			return new MDSISerialNoTrx(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSISerialNumberMaster.Table_Name))
			return new MDSISerialNumberMaster(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSISrNoProduct.Table_Name))
			return new MDSISrNoProduct(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSOrgContactRelation.Table_Name))
			return new MDSOrgContactRelation(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSOrgMaster.Table_Name))
			return new MDSOrgMaster(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSOrgMasterLocation.Table_Name))
			return new MDSOrgMasterLocation(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSProductRequest.Table_Name))
			return new MDSProductRequest(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSRouteMaster.Table_Name))
			return new MDSRouteMaster(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSRouteReturn.Table_Name))
			return new MDSRouteReturn(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSRouteTo.Table_Name))
			return new MDSRouteTo(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSServiceRequest.Table_Name))
			return new MDSServiceRequest(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSOutstandingAgingSummary.Table_Name))
			return new MDSOutstandingAgingSummary(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSB2BConfiguration.Table_Name))
			return new MDSB2BConfiguration(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSB2BEmailConf.Table_Name))
			return new MDSB2BEmailConf(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDunningIncludeEmails.Table_Name))
			return new MDSDunningIncludeEmails(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDunningEntryEmails.Table_Name))
			return new MDSDunningEntryEmails(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSFixedDeposit.Table_Name))
			return new MDSFixedDeposit(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSManufacturingQABOM.Table_Name))
			return new MDSManufacturingQABOM(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSManufacturingQA.Table_Name))
			return new MDSManufacturingQA(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSInventoryAging.Table_Name))
			return new MDSInventoryAging(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSCustomerVisits.Table_Name))
			return new MDSCustomerVisits(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSCustomerVisitsDetails.Table_Name))
			return new MDSCustomerVisitsDetails(Env.getCtx(), rs, trxName);

		if(tableName.equals(MDSTripConfiguration.Table_Name))
			return new MDSTripConfiguration(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSQAMORejection.Table_Name))
			return new MDSQAMORejection(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSQAMORejection.Table_Name))
			return new MDSQAMORejection(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSAssignmentDetails.Table_Name))
			return new MDSAssignmentDetails(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSTripExpenseType.Table_Name))
			return new MDSTripExpenseType(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSTripScheduleEstimations.Table_Name))
			return new MDSTripScheduleEstimations(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSTripSchdCashReqstAmt.Table_Name))
			return new MDSTripSchdCashReqstAmt(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDocumentsForTrip.Table_Name))
			return new MDSDocumentsForTrip(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSRequiredShipDocuments.Table_Name))
			return new MDSRequiredShipDocuments(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSRequiredDocForShipment.Table_Name))
			return new MDSRequiredDocForShipment(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSProductPriceSerialNo.Table_Name))
			return new MDSProductPriceSerialNo(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MCContactActivity.Table_Name))
			return new MCContactActivity(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSITrackProductBatch.Table_Name))
			return new MDSITrackProductBatch(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDenomiationDetCashBook.Table_Name))
			return new MDSDenomiationDetCashBook(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDenomiationDetTrans.Table_Name))
			return new MDSDenomiationDetTrans(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSSettledInvoices.Table_Name))
			return new MDSSettledInvoices(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSDenominationOnStatement.Table_Name))
			return new MDSDenominationOnStatement(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSPOSHeader.Table_Name))
			return new MDSPOSHeader(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSPOSItemDetails.Table_Name))
			return new MDSPOSItemDetails(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSTaxFiling.Table_Name))
			return new MDSTaxFiling(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSTaxFilingDet.Table_Name))
			return new MDSTaxFilingDet(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSInvoiceTaxDetails.Table_Name))
			return new MDSInvoiceTaxDetails(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSImportInvoicesFiling.Table_Name))
			return new MDSImportInvoicesFiling(Env.getCtx(), rs, trxName);
		
		if(tableName.equals(MDSITRecallProducts.Table_Name))
			return new MDSITRecallProducts(Env.getCtx(), rs, trxName);
		
		return null;
	}

}
