package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MConversionRate;
import org.compiere.util.Env;

public class CallOutCalculateBaseAmt implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		int C_Currency_ID = (Integer) mTab.getValue("C_Currency_ID");	
		BigDecimal interestAmt = (BigDecimal)mTab.getValue("InterestAmt");
		BigDecimal principalAmt = (BigDecimal)mTab.getValue("DS_PrincipalAmount");
		Timestamp dateAcct = (Timestamp)mTab.getValue("DateAcct");
		
		BigDecimal baseInterestAmt = MConversionRate.convertBase(ctx, interestAmt, C_Currency_ID, dateAcct, 114, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
		mTab.setValue("DS_BaseInterestAmount", baseInterestAmt);
		
		BigDecimal basePrincipal = MConversionRate.convertBase(ctx, principalAmt, C_Currency_ID, dateAcct, 114, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
		mTab.setValue("DS_BasePrincipalAmount", basePrincipal);
		
		return null;
	}

}
