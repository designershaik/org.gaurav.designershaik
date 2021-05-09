package org.dsi.finance.callouts;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.util.Env;

public class CallOutSetTaxRateBasedOnBPGroup implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		int C_BPartner_ID = Env.getContextAsInt(ctx, WindowNo, "C_BPartner_ID");
		boolean IsSOTrx = "Y".equals(Env.getContext(ctx, WindowNo, "IsSOTrx"));
		if(C_BPartner_ID>0 && !IsSOTrx)
		{
			MBPartner bp = new MBPartner(ctx, C_BPartner_ID, null);
			MBPGroup grp = (MBPGroup)bp.getC_BP_Group();
			int C_Tax_ID = grp.get_ValueAsInt("C_Tax_ID");
			if(C_Tax_ID>0)
				mTab.setValue("C_Tax_ID", C_Tax_ID);
		}
		return null;
	}


}
