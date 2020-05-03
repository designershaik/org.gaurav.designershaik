package org.gaurav.payroll.callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;

public class CallOutCalculateInstallmentPerMonth implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) {

		String column = mField.getColumnName();
		BigDecimal appliedLoanAmt = Env.ZERO;
		BigDecimal installments = Env.ZERO;
		BigDecimal installmentAmt = Env.ZERO;
		BigDecimal approvedAmt = Env.ZERO;
		if(value==null)
			return null;
		
		appliedLoanAmt =	(BigDecimal)mTab.getValue("GS_HR_LoanAmt");
		installments = (BigDecimal)mTab.getValue("GS_HR_Installments");
		installmentAmt = (BigDecimal)mTab.getValue("GS_InstallmentAmt");
		approvedAmt =	(BigDecimal)mTab.getValue("DS_HR_ApprovedAmt");
		if(approvedAmt.compareTo(Env.ZERO)!=0)
			appliedLoanAmt = approvedAmt;
		if(installments.compareTo(Env.ZERO)!=0)
		{
			if(column.equalsIgnoreCase("GS_HR_LoanAmt"))
			{
				installmentAmt = appliedLoanAmt.divide(installments,2, RoundingMode.CEILING);
				mTab.setValue("GS_InstallmentAmt", installmentAmt);
			}
			if(column.equalsIgnoreCase("GS_HR_Installments"))
			{
				installmentAmt = appliedLoanAmt.divide(installments,2, RoundingMode.CEILING);
				mTab.setValue("GS_InstallmentAmt", installmentAmt);
				mTab.setValue("GS_HR_RemainingInstallments", installments);
			}
		}
		if(column.equalsIgnoreCase("GS_InstallmentAmt") && installmentAmt.intValue()!=0)
		{
			installments = appliedLoanAmt.divide(installmentAmt,2, RoundingMode.CEILING);
			mTab.setValue("GS_HR_Installments", installments);
			mTab.setValue("GS_HR_RemainingInstallments", installments);
		}
		
		if(column.equalsIgnoreCase("DS_HR_ApprovedAmt") && installments.intValue()!=0)
		{
			installments = approvedAmt.divide(installmentAmt,2, RoundingMode.CEILING);
			mTab.setValue("GS_HR_Installments", installments);
			mTab.setValue("GS_HR_RemainingInstallments", installments);
		}
		
		return null;
	}
}
