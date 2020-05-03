package org.dsi.crm.callouts;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

public class CallOutSetDateToTripSchedule implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		
		if(value==null)
			return null;
		
		if(mField.getColumnName().equalsIgnoreCase("DS_TripDateFrom"))
		{
			Timestamp dateFrom = (Timestamp)value;
			mTab.setValue("DS_TripDateTo", dateFrom);
		}
		if(mField.getColumnName().equalsIgnoreCase("DS_TripDateTo"))
		{
			Timestamp dateTo = (Timestamp)value;
			Timestamp dateFrom = (Timestamp)mTab.getValue("DS_TripDateFrom");
			if(dateTo.before(dateFrom))
				mTab.fireDataStatusEEvent("Dateto is before datefrom", "Datefrom < dateto", true);
		}
		return null;
	}


}
