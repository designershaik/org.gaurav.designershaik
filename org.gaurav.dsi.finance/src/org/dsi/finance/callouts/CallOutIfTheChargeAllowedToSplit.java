package org.dsi.finance.callouts;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAsset;
import org.compiere.model.MCharge;
import org.compiere.util.Env;

public class CallOutIfTheChargeAllowedToSplit implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) 
	{
		if(value==null)
			return null;
		
		Integer C_Charge_ID =0;
		Integer DS_Vehicle_ID = 0 ;
		if(mField.getColumnName().equalsIgnoreCase("C_Charge_ID"))
		{
			C_Charge_ID = (Integer)value;
			DS_Vehicle_ID = (Integer)mTab.getValue("DS_Vehicle_ID");
		}
		if(mField.getColumnName().equalsIgnoreCase("DS_Vehicle_ID"))
		{
			DS_Vehicle_ID = (Integer)value;
			C_Charge_ID = (Integer)mTab.getValue("C_Charge_ID");
		}
		if(C_Charge_ID==null)
			return null;
		
		MCharge charge = new MCharge(ctx, C_Charge_ID, null);
		BigDecimal split = (BigDecimal)charge.get_Value("A_Split_Percent");
		if(split.compareTo(Env.ZERO)>0)
		{
			if(DS_Vehicle_ID!=null)
			{
				MAsset asset = new MAsset(ctx, DS_Vehicle_ID, null);
				if(!asset.isInPosession() && asset.get_ValueAsBoolean("DS_SplitVATAsset"))
					mTab.setValue("A_Split_Percent", split);
				else if(asset.isInPosession() && !asset.get_ValueAsBoolean("DS_SplitVATAsset"))
					mTab.setValue("A_Split_Percent", Env.ONEHUNDRED);
				else
					mTab.setValue("A_Split_Percent", Env.ZERO);
			}
			else
				mTab.setValue("A_Split_Percent", split);
		}
		return null;
	}


}
