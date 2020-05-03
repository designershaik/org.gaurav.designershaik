package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MLocation;
import org.compiere.util.DB;

public class MDSDeliveryTripsDet extends X_DS_Delivery_Trips_Det {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2351833469250775094L;

	public MDSDeliveryTripsDet(Properties ctx, int DS_Delivery_Trips_Det_ID,
			String trxName) {
		super(ctx, DS_Delivery_Trips_Det_ID, trxName);
		
	}

	public MDSDeliveryTripsDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	/**
	 * 	After Save
	 *	@param newRecord new
	 *	@param success success
	 *	@return true if can be saved
	 */
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		
		return true;
	}

	protected boolean beforeSave(boolean newRecord)
	{
		/** Prevents saving
		log.saveError("Error", Msg.parseTranslation(getCtx(), "@C_Currency_ID@ = @C_Currency_ID@"));
		log.saveError("FillMandatory", Msg.getElement(getCtx(), "PriceEntered"));
		/** Issues message
		log.saveWarning(AD_Message, message);
		log.saveInfo (AD_Message, message);
		**/
		return true;
	}	//	beforeSave

	public void updateRouteDetails(MBPartnerLocation bplocation) 
	{
		int lineno=10;
		MLocation location=new MLocation(getCtx(), bplocation.getC_Location_ID(), get_TrxName());
		System.out.println(getDS_Delivery_Trips_Det_ID());
		lineno=DB.getSQLValue(get_TrxName(), "select coalesce(max(lineno),0) from DS_Delivery_RouteDetails where DS_IsReturnRoute='N' and DS_Delivery_Trips_ID="+getDS_Delivery_Trips_ID());
		
		MDSDeliveryRouteDetails details=new MDSDeliveryRouteDetails(getCtx(), 0, get_TrxName());
		details.setC_BPartner_ID(bplocation.getC_BPartner_ID());
		details.setC_BPartner_Location_ID(bplocation.getC_BPartner_Location_ID());
		details.setDS_ToCountry_ID(location.getC_Country_ID());
		details.setLineNo(lineno+1);
		details.setDS_IsReturnRoute(false);
		details.setDS_Delivery_Trips_ID(getDS_Delivery_Trips_ID());
		details.saveEx();
	}
}
