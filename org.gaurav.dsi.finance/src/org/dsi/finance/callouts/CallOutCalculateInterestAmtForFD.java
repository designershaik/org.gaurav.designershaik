package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MConversionRate;
import org.compiere.util.Env;
import org.joda.time.DateTime;

public class CallOutCalculateInterestAmtForFD implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue)
	{
		if(value==null)
			return null;
		
		BigDecimal interestPercent = (BigDecimal)mTab.getValue("InterestPercent");
		BigDecimal principalAmt = (BigDecimal)mTab.getValue("DS_PrincipalAmount");
		Integer term = (Integer)mTab.getValue("DS_FDTerm");
		BigDecimal interest = interestPercent.divide(Env.ONEHUNDRED, 2,RoundingMode.CEILING);
		
		BigDecimal interestAmt = Env.ZERO;
		
		interestAmt  = principalAmt.multiply(interest).multiply((new BigDecimal(term).divide(new BigDecimal(360), 3,RoundingMode.CEILING)));
		mTab.setValue("InterestAmt", interestAmt);

		int C_Currency_ID = (Integer) mTab.getValue("C_Currency_ID");	
		Timestamp dateAcct = (Timestamp)mTab.getValue("DateAcct");
		
		BigDecimal baseInterestAmt = MConversionRate.convertBase(ctx, interestAmt, C_Currency_ID, dateAcct, 114, Env.getAD_Client_ID(ctx), Env.getAD_Org_ID(ctx));
		mTab.setValue("DS_BaseInterestAmount", baseInterestAmt);
		
		DateTime dateTime = new DateTime(dateAcct);
		dateTime = dateTime.plusDays(term);
		
		 Timestamp maturityDate = new Timestamp(dateTime.getMillis());
		 mTab.setValue("DS_MaturityDate", maturityDate);
		
		return null;
	}


}
