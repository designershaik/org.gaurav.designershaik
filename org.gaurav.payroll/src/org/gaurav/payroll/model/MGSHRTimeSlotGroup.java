package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;

public class MGSHRTimeSlotGroup extends X_GS_HR_TimeSlot_Group {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRTimeSlotGroup(Properties ctx, int GS_HR_TimeSlot_Group_ID, String trxName) {
		super(ctx, GS_HR_TimeSlot_Group_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRTimeSlotGroup(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHRTimeSlot getTimeSlotForTheDay(String daysOfTheWeek) 
	{
		int GS_HR_TimeSlot_ID = DB.getSQLValue(get_TrxName(), "Select GS_HR_TimeSlot_ID From GS_HR_TimeSlot Where GS_DayOfTheWeek=? and GS_HR_TimeSlot_Group_ID=? ",daysOfTheWeek,getGS_HR_TimeSlot_Group_ID());
		MGSHRTimeSlot slot = new MGSHRTimeSlot(getCtx(), GS_HR_TimeSlot_ID, get_TrxName());
		return slot;
	}

}
