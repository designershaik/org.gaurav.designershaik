package org.gaurav.payroll.process;

import org.compiere.process.SvrProcess;
import org.gaurav.payroll.model.MGSHRMonthlyAttendance;

public class FinalizeAttendance extends SvrProcess{

	int monthlyAttendance_id = 0 ; 
	@Override
	protected void prepare() 
	{
		monthlyAttendance_id	 = getRecord_ID();
		
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(monthlyAttendance_id>0)
		{
			MGSHRMonthlyAttendance attendance = new MGSHRMonthlyAttendance(getCtx(), monthlyAttendance_id, get_TrxName());
			attendance.set_ValueNoCheck("Processed", true);
			attendance.saveEx();
		}
		return "@OK@";
	}


}
