package org.dsi.crm.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.dsi.crm.processes.ConvertLead;
import org.dsi.crm.processes.CopyMasterContactDetails;
import org.dsi.crm.processes.CopyOrder;
import org.dsi.crm.processes.CreateCashRequestDraftTripSettlement;
import org.dsi.crm.processes.DSConvertLead;
import org.dsi.crm.processes.DSPrepareMenifest;
import org.dsi.crm.processes.DeleteSerialNumbers;
import org.dsi.crm.processes.GenerateManagementSummary;
import org.dsi.crm.processes.GenerateOrderFromDSPOS;
import org.dsi.crm.processes.MigrateBPToOrgMasters;
import org.dsi.crm.processes.MigrateUsersToContactMaster;
import org.dsi.crm.processes.OpenCreateNewLeadFormFromKanban;
import org.dsi.crm.processes.OpenAddActivityWindowFromKanban;
import org.dsi.crm.processes.PrintRefillForms;
import org.dsi.crm.processes.ProductRecall;
import org.dsi.crm.processes.ReSequenceTree;
import org.dsi.crm.processes.SendEmailToPOSCustomer;
import org.dsi.crm.processes.ShipSerialNumbers;
import org.dsi.crm.processes.ZoomToWindowFromKanbanLeadCard;

public class DSIcrmprocessfactories implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equals(ConvertLead.class.getName()))
			return new ConvertLead();
		
		if(className.equals(DeleteSerialNumbers.class.getName()))
			return new DeleteSerialNumbers();
		
		if(className.equals(CopyMasterContactDetails.class.getName()))
			return new CopyMasterContactDetails();
		
		if(className.equals(CopyOrder.class.getName()))
			return new CopyOrder();
		
		if(className.equals(DSConvertLead.class.getName()))
			return new DSConvertLead();
		
		if(className.equals(DSPrepareMenifest.class.getName()))
			return new DSPrepareMenifest();
			
		if(className.equals(MigrateBPToOrgMasters.class.getName()))
			return new MigrateBPToOrgMasters();
		
		if(className.equals(MigrateUsersToContactMaster.class.getName()))
			return new MigrateUsersToContactMaster();
		
		if(className.equals(PrintRefillForms.class.getName()))
			return new PrintRefillForms();
		
		if(className.equals(SendEmailToPOSCustomer.class.getName()))
			return new SendEmailToPOSCustomer();
		
		if(className.equals(ShipSerialNumbers.class.getName()))
			return new ShipSerialNumbers();
		
		if(className.equals(CreateCashRequestDraftTripSettlement.class.getName()))
			return new CreateCashRequestDraftTripSettlement();
		
		if(className.equals(ReSequenceTree.class.getName()))
			return new ReSequenceTree();
		
		if(className.equals(OpenAddActivityWindowFromKanban.class.getName()))
			return new OpenAddActivityWindowFromKanban();
		
		if(className.equals(OpenCreateNewLeadFormFromKanban.class.getName()))
			return new OpenCreateNewLeadFormFromKanban();
		
		if(className.equals(ZoomToWindowFromKanbanLeadCard.class.getName()))
			return new ZoomToWindowFromKanbanLeadCard();
		
		if(className.equals(GenerateManagementSummary.class.getName()))
			return new GenerateManagementSummary();
		
		if(className.equals(GenerateOrderFromDSPOS.class.getName()))
			return new GenerateOrderFromDSPOS();
		
		if(className.equals(ProductRecall.class.getName()))
			return new ProductRecall();
		
		return null;
	}

}
