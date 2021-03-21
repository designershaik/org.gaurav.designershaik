package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

public class MGSHRMonthlyAttendance extends X_GS_HR_MonthlyAttendance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRMonthlyAttendance(Properties ctx, int GS_HR_MonthlyAttendance_ID, String trxName) {
		super(ctx, GS_HR_MonthlyAttendance_ID, trxName);
		
	}

	public MGSHRMonthlyAttendance(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}
	
	public BigDecimal getDaysBetween()
	{
		BigDecimal days = Env.ZERO;
		MGSHRSalaryMonths salMonth = new MGSHRSalaryMonths(getCtx(), getGS_HR_SalaryMonths_ID(), get_TrxName());
		days = new BigDecimal(TimeUtil.getDaysBetween(salMonth.getStartDate(),salMonth.getEndDate())).add(Env.ONE);
		
		return days;
	}

}
