package com.gaurav.dsi.purchase.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class CallOutVersioning extends CalloutEngine 
{
	public String getDesignDocVersion(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value)
	{
		if(value==null)
		{
			return "";
		}
		int productID=(Integer)value;
		String sql="select DS_DocVersion.DS_DocVersion_ID from DS_DocVersion where DS_DocVersion.M_Product_ID=?"
				+ " and DS_DocVersion.date1=(select max(ds.date1) from DS_DocVersion ds where ds.m_product_id="+productID+")";
		int designDocVersionID=DB.getSQLValue(null,sql,productID);
		if((Integer)designDocVersionID==null)
		{
			designDocVersionID=0;
		}
		mTab.setValue("DS_DocVersion_ID", designDocVersionID);
		mTab.dataRefresh();
		return "";
	}

}
