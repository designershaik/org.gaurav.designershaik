package org.gaurav.alerts.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.TimeUtil;

public class MGSDocReminderSchedule extends X_GS_DocReminder_Schedule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSDocReminderSchedule(Properties ctx, int GS_DocReminder_Schedule_ID, String trxName) {
		super(ctx, GS_DocReminder_Schedule_ID, trxName);
		
	}

	public MGSDocReminderSchedule(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	protected boolean beforeSave (boolean newRecord)
	{
		Timestamp alertDate = TimeUtil.addDays(getGS_DocumentForAlerts().getHR_Doc_ExpiryDate(), -getDaysDue());
		Timestamp today = getToday();
		if(alertDate.before(today))
			alertDate = TimeUtil.addDays(getGS_DocumentForAlerts().getHR_Doc_ExpiryDate(), -1);
		setDate1(alertDate);
		
		return true;
	}
	
	static Timestamp getToday()
	{
		return new Timestamp(TimeUtil.getToday().getTime().getTime());
	}
}

