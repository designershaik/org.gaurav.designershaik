package org.dsi.finance.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.dsi.finance.processes.BankTransfer;
import org.dsi.finance.processes.CopyCashJournalLine;
import org.dsi.finance.processes.CreateDepositBatchFromPaySelection;
import org.dsi.finance.processes.CreateFD;
import org.dsi.finance.processes.DSAging;
import org.dsi.finance.processes.DSCustomDunningRunCreate;
import org.dsi.finance.processes.DSICustomDunningPrint;
import org.dsi.finance.processes.GenerateFDBankBalances;
import org.dsi.finance.processes.InventoryAging;
import org.dsi.finance.processes.OpenPaymentWindow;
import org.dsi.finance.processes.PaySelectionCreateCheck;
import org.dsi.finance.processes.PaySelectionCreateFrom;
import org.dsi.finance.processes.ProcessAccountsHiererchy;
import org.dsi.finance.processes.ProcessCreateBankTransferFiles;
import org.dsi.finance.processes.SendElectronicInvoices;
import org.dsi.finance.processes.SendInvoiceFromDSPOS;
import org.dsi.finance.processes.SettleBalances;
import org.dsi.finance.processes.SimplifiedTaxFiling;
import org.dsi.finance.processes.SplitInvoiceLineForTax;
import org.dsi.finance.processes.TaxFiling;


public class DSIFinanceProcessFactories implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(className.equals(DSICustomDunningPrint.class.getName()))
			return new DSICustomDunningPrint();
		
		if(className.equals(DSCustomDunningRunCreate.class.getName()))
			return new DSCustomDunningRunCreate();
		
		if(className.equals(ProcessCreateBankTransferFiles.class.getName()))
			return new ProcessCreateBankTransferFiles();
		
		if(className.equals(ProcessAccountsHiererchy.class.getName()))
			return new ProcessAccountsHiererchy();
		
		if(className.equals(SettleBalances.class.getName()))
			return new SettleBalances();
		
		if(className.equals(DSAging.class.getName()))
			return new DSAging();
		
		if(className.equals(CopyCashJournalLine.class.getName()))
			return new CopyCashJournalLine();
		
		if(className.equals(CreateFD.class.getName()))
			return new CreateFD();
		
		if(className.equals(InventoryAging.class.getName()))
			return new InventoryAging();
		
		if(className.equals(SendElectronicInvoices.class.getName()))
			return new SendElectronicInvoices();
		
		if(className.equals(OpenPaymentWindow.class.getName()))
			return new OpenPaymentWindow();
		
		if(className.equals(SendInvoiceFromDSPOS.class.getName()))
			return new SendInvoiceFromDSPOS();
		
		if(className.equals(SplitInvoiceLineForTax.class.getName()))
			return new SplitInvoiceLineForTax();

		if(className.equals(BankTransfer.class.getName()))
			return new BankTransfer();
		
		if(className.equals(SimplifiedTaxFiling.class.getName()))
			return new SimplifiedTaxFiling();
		
		if(className.equals(PaySelectionCreateFrom.class.getName()))
			return new PaySelectionCreateFrom();
		
		if(className.equals(CreateDepositBatchFromPaySelection.class.getName()))
			return new CreateDepositBatchFromPaySelection();
		
		if(className.equals(PaySelectionCreateCheck.class.getName()))
			return new PaySelectionCreateCheck();
		
		if(className.equals(TaxFiling.class.getName()))
			return new TaxFiling();
		
		if(className.equals(GenerateFDBankBalances.class.getName()))
			return new GenerateFDBankBalances();		
		
		return null;
	}

}
