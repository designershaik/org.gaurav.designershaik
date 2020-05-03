package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;

public class MGSHREmployeeAdvance extends X_GS_HR_EmployeeAdvance {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4206824497588466358L;

	public MGSHREmployeeAdvance(Properties ctx, int GS_HR_EmployeeAdvance_ID,
			String trxName) {
		super(ctx, GS_HR_EmployeeAdvance_ID, trxName);
		
	}

	public MGSHREmployeeAdvance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success || newRecord)
			return success;
		if(newRecord)
		{
			setGS_HR_Approval_ID(Env.getAD_User_ID(getCtx()));
			saveEx();
		}
		
		return success;
	}
}
