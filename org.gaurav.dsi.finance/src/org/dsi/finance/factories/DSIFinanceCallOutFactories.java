package org.dsi.finance.factories;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.I_C_CashLine;
import org.compiere.model.I_C_InvoiceLine;
import org.compiere.model.I_C_OrderLine;
import org.compiere.model.I_C_Payment;
import org.compiere.model.I_R_Request;
import org.compiere.model.MCashLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPayment;
import org.compiere.model.MRequest;
import org.dsi.finance.callouts.CallOutCalculateBaseAmt;
import org.dsi.finance.callouts.CallOutCalculateInterestAmtForFD;
import org.dsi.finance.callouts.CallOutCashJounrGetBPartnerOfInvoice;
import org.dsi.finance.callouts.CallOutCheckForProfitCenterAndCostCenter;
import org.dsi.finance.callouts.CallOutIfTheChargeAllowedToSplit;
import org.dsi.finance.callouts.CallOutPaymentAmt;
import org.dsi.finance.callouts.CallOutPaymentCurrency;
import org.dsi.finance.callouts.CallOutSetCashAdvanceByEmployee;
import org.dsi.finance.callouts.CallOutSetCostIfThePriceProduct;
import org.dsi.finance.callouts.CallOutSetInvoiceLocationOnInvoice;
import org.dsi.finance.callouts.CallOutSetIsManualOnDenomination;
import org.dsi.finance.callouts.CallOutSetIsPOPMandatory;
import org.dsi.finance.callouts.CallOutSetTaxRateBasedOnBPGroup;
import org.dsi.finance.callouts.CallOutSetTotalCashAmt;
import org.dsi.finance.callouts.CallOutSetTransactionDateBasedOnAccntDatePayment;
import org.dsi.finance.callouts.CopyInvoiceDescription;
import org.gaurav.dsi.model.I_DS_FixedDeposit;
import org.gaurav.dsi.model.I_DS_Product_Request;
import org.gaurav.dsi.model.MDSDenomiationDetCashBook;
import org.gaurav.dsi.model.MDSDenomiationDetTrans;
import org.gaurav.dsi.model.MDSFixedDeposit;
import org.gaurav.dsi.model.MDSProductRequest;

public class DSIFinanceCallOutFactories implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) 
	{
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MRequest.Table_Name) 
				&& columnName.equalsIgnoreCase(I_R_Request.COLUMNNAME_C_Order_ID))
			list.add(new CallOutPaymentAmt());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_Payment.COLUMNNAME_C_BankAccount_ID))
			list.add(new CallOutPaymentCurrency());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_Payment.COLUMNNAME_C_Invoice_ID))
			list.add(new CopyInvoiceDescription());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_LiquidatedGivenTo_ID"))
			list.add(new CallOutSetCashAdvanceByEmployee());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_Payment.COLUMNNAME_C_BankAccount_ID))
			list.add(new CallOutSetCashAdvanceByEmployee());
		
		if(tableName.equalsIgnoreCase(MCashLine.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_CashLine.COLUMNNAME_C_Invoice_ID))
			list.add(new CopyInvoiceDescription());
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name)
				&& columnName.equalsIgnoreCase(I_C_InvoiceLine.COLUMNNAME_C_Charge_ID))
			list.add(new CallOutCheckForProfitCenterAndCostCenter());
		
		if(tableName.equalsIgnoreCase(MOrderLine.Table_Name)
				&& columnName.equalsIgnoreCase(I_C_OrderLine.COLUMNNAME_C_Charge_ID))
			list.add(new CallOutCheckForProfitCenterAndCostCenter());
		
		if(tableName.equalsIgnoreCase(MDSProductRequest.Table_Name)
				&& columnName.equalsIgnoreCase(I_DS_Product_Request.COLUMNNAME_C_Charge_ID))
			list.add(new CallOutCheckForProfitCenterAndCostCenter());
		
		if(tableName.equalsIgnoreCase(MDSFixedDeposit.Table_Name) 
				&& columnName.equalsIgnoreCase(I_DS_FixedDeposit.COLUMNNAME_C_BankAccount_ID))
			list.add(new CallOutPaymentCurrency());
		
		if(tableName.equalsIgnoreCase(MDSFixedDeposit.Table_Name) 
				&& columnName.equalsIgnoreCase(I_DS_FixedDeposit.COLUMNNAME_InterestAmt))
			list.add(new CallOutCalculateBaseAmt());
		
		if(tableName.equalsIgnoreCase(MDSFixedDeposit.Table_Name) 
				&& columnName.equalsIgnoreCase(I_DS_FixedDeposit.COLUMNNAME_DS_PrincipalAmount))
			list.add(new CallOutCalculateBaseAmt());
		
		if(tableName.equalsIgnoreCase(MDSFixedDeposit.Table_Name) 
				&& columnName.equalsIgnoreCase(I_DS_FixedDeposit.COLUMNNAME_InterestPercent))
			list.add(new CallOutCalculateInterestAmtForFD());
		
		if(tableName.equalsIgnoreCase(MDSFixedDeposit.Table_Name) 
				&& columnName.equalsIgnoreCase(I_DS_FixedDeposit.COLUMNNAME_DS_PrincipalAmount))
			list.add(new CallOutCalculateInterestAmtForFD());
		
		if(tableName.equalsIgnoreCase(MDSFixedDeposit.Table_Name) 
				&& columnName.equalsIgnoreCase(I_DS_FixedDeposit.COLUMNNAME_DS_FDTerm))
			list.add(new CallOutCalculateInterestAmtForFD());
		
		if(tableName.equalsIgnoreCase(MCashLine.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_CashLine.COLUMNNAME_C_Invoice_ID))
			list.add(new CallOutCashJounrGetBPartnerOfInvoice());
		
		if(tableName.equalsIgnoreCase(MDSDenomiationDetTrans.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSDenomiationDetTrans.COLUMNNAME_DS_DenominationType))
			list.add(new CallOutSetIsManualOnDenomination());
		
		if(tableName.equalsIgnoreCase(MDSDenomiationDetTrans.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSDenomiationDetTrans.COLUMNNAME_Qty))
			list.add(new CallOutSetIsManualOnDenomination());
		
		if(tableName.equalsIgnoreCase(MDSDenomiationDetCashBook.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSDenomiationDetCashBook.COLUMNNAME_DS_DenominationType))
			list.add(new CallOutSetIsManualOnDenomination());
		
		if(tableName.equalsIgnoreCase(MDSDenomiationDetCashBook.Table_Name) 
				&& columnName.equalsIgnoreCase(MDSDenomiationDetCashBook.COLUMNNAME_Qty))
			list.add(new CallOutSetIsManualOnDenomination());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_Payment.COLUMNNAME_PayAmt))
			list.add(new CallOutSetTotalCashAmt());
			
		if(tableName.equalsIgnoreCase(MInvoice.Table_Name) 
				&& columnName.equalsIgnoreCase(MInvoice.COLUMNNAME_C_BPartner_ID))
			list.add(new CallOutSetInvoiceLocationOnInvoice());
		
		if(tableName.equalsIgnoreCase(MOrderLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MOrderLine.COLUMNNAME_M_Product_ID))
			list.add(new CallOutSetCostIfThePriceProduct());
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MInvoiceLine.COLUMNNAME_C_Charge_ID))
			list.add(new CallOutIfTheChargeAllowedToSplit());
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name) 
				&& columnName.equalsIgnoreCase("DS_Vehicle_ID"))
			list.add(new CallOutIfTheChargeAllowedToSplit());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase(MPayment.COLUMNNAME_DateAcct))
			list.add(new CallOutSetTransactionDateBasedOnAccntDatePayment());
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MInvoiceLine.COLUMNNAME_C_Charge_ID))
			list.add(new CallOutSetTaxRateBasedOnBPGroup());
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name) 
				&& columnName.equalsIgnoreCase(MInvoiceLine.COLUMNNAME_M_Product_ID))
			list.add(new CallOutSetTaxRateBasedOnBPGroup());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_Payment.COLUMNNAME_C_Invoice_ID))
			list.add(new CallOutSetIsPOPMandatory());
		
		if(tableName.equalsIgnoreCase(MPayment.Table_Name) 
				&& columnName.equalsIgnoreCase(I_C_Payment.COLUMNNAME_C_Order_ID))
			list.add(new CallOutSetIsPOPMandatory());
		
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}

}
