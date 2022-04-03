package com.gaurav.dsi.purchase.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;
//import org.gaurav.dsi.model.MDSIManufacturingPlan;
import org.gaurav.dsi.model.MDSISalesForecast;


//DSI_ManufacturingPlan
public class CallOutCopySalesForecastDetToManufacturePlan implements IColumnCallout
{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,GridField mField, Object value, Object oldValue) 
	{
		
		if(value==null)
		{
			mTab.setValue("M_Product_ID", null);
			mTab.setValue("CalendarYearMonth", null);
			mTab.setValue("C_BPartner_ID",null);
			mTab.setValue("QtyToOrder", Env.ONE);
			
			return null;
		} 
		Integer productID = 0;
		Integer warehouse_ID = 0;
		if(mField.getColumnName().equalsIgnoreCase("DSI_SalesForecast_ID"))
		{
			Integer SalesForecastID = (Integer)value;
			//Integer ManufacturingPlanID=(Integer)mTab.getValue("DSI_ManufacturingPlan_ID");
			
			
			
			MDSISalesForecast mo= new MDSISalesForecast(ctx, SalesForecastID, null);
			productID = mo.getM_Product_ID();
			mTab.setValue("M_Product_ID", productID);
	 		mTab.setValue("DSI_SalesForecastMonth_ID", mo.getDSI_SalesForecastMonth_ID());
			//mTab.setValue("C_BPartner_ID", mo.getC_BPartner_ID());
	 		mTab.setValue("C_BPartner_ID", mo.getC_BPartner_ID()==0 ? null:mo.getC_BPartner_ID());
	 		
	 		//mTab.setValue("C_ProjectPhase_ID", mo.getC_ProjectPhase_ID()==0 ? null:mo.getC_ProjectPhase_ID());
	 		
			mTab.setValue("QtyToOrder", mo.getQtyToOrder());
		}
		if(mField.getColumnName().equalsIgnoreCase("M_Warehouse_ID"))
		{
			
			warehouse_ID = (Integer)value;
			//warehouse_ID = (Integer)mTab.getValue("M_WarehouseID");
			
			productID = (Integer)mTab.getValue("M_Product_ID");
			productID = productID==null? 0: productID;
		}
		
		System.out.print(warehouse_ID);
		System.out.print(productID);
		
		if(productID!=0)
		{
 			mTab.setValue("M_Product_ID", productID);
 			//mTab.setValue("M_WarehouseID", warehouse_ID);
 			BigDecimal QtyonHand = Env.ZERO;
 			BigDecimal ReserveQty = Env.ZERO;
 			BigDecimal QtyonOrder = Env.ZERO;

 			//if(warehouse_ID<=0)
 			//	QtyonHand = DB.getSQLValueBD(null, "Select sum(QtyonHand) from DSI_QtyOnHand Where M_Product_ID =?" ,productID);
 			if(warehouse_ID>0)
	 			QtyonHand = DB.getSQLValueBD(null, "Select Sum(QtyonHand) from DSI_QtyOnHand Where M_Product_ID =? and M_Warehouse_ID=?" ,productID,warehouse_ID); // ", productID,);
	 		 	mTab.setValue("QtyManufacturedToPending", QtyonHand);
	 		
	 		 	ReserveQty = DB.getSQLValueBD(null, "Select qtyreserve from DSI_QtyOnHand Where M_Product_ID =? and M_Warehouse_ID=?" ,productID,warehouse_ID); // ", productID,);
	 		 	mTab.setValue("QtyReserve", ReserveQty);
	 		 	
	 		 	QtyonOrder = DB.getSQLValueBD(null, "select qtyentered from c_orderline Where qtyentered > 0 and processed = 'N' and M_Product_ID =? and M_Warehouse_ID=?" ,productID,warehouse_ID); // ", productID,);
	 		 	mTab.setValue("onorderqty", QtyonOrder);
 		 	
			//Example only change later
		}
		return null;
	}
		
}
