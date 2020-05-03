package org.dsi.finance.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCharge;
import org.compiere.model.MElementValue;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class CallOutCheckForProfitCenterAndCostCenter implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		
		if(value==null)
			return null;
		
		int C_Charge_ID = (Integer)value;
		
		MAcctSchema[] mass = MAcctSchema.getClientAcctSchema(ctx, Env.getAD_Client_ID(Env.getCtx()));
		for(MAcctSchema mas : mass)
		{
			MAccount account = MCharge.getAccount(C_Charge_ID, mas)	;
			MElementValue elemValue = new MElementValue(ctx, account.getAccount_ID(), null);
			if(elemValue.get_ValueAsBoolean("DS_CCRCRequired"))
			{
				int C_Activity_ID = mTab.getValue("C_Activity_ID")==null ? 0:(Integer) mTab.getValue("C_Activity_ID");
				int User1_ID = mTab.getValue("User1_ID")==null ? 0:(Integer) mTab.getValue("User1_ID");
				int User2_ID = mTab.getValue("User2_ID")==null ? 0:(Integer) mTab.getValue("User2_ID");
				if(C_Activity_ID == 0 || User1_ID == 0 || User2_ID == 0)
					return Msg.getMsg(ctx, "DS_CostCenter_RevenueCode_ProfitCenter_Mandatory");
			}
		}
		return null;
	}


}
