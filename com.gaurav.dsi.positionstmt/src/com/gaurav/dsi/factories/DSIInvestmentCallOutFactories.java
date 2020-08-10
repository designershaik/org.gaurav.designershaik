package com.gaurav.dsi.factories;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.compiere.model.MInvoiceLine;

import com.gaurav.dsi.callout.AddStocksOrBondsRelatedToPortfolio;

public class DSIInvestmentCallOutFactories implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName,
			String columnName) 
	{
		List<IColumnCallout> list=new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MInvoiceLine.Table_Name) 
				&& columnName.equalsIgnoreCase("M_PartType_ID"))
			list.add(new AddStocksOrBondsRelatedToPortfolio());
				
		return list!=null ? list.toArray(new IColumnCallout[0]): new IColumnCallout[0];
	}

}
