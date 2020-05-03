package org.gaurav.payroll.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.model.Query;

public class MHRContract extends X_HR_Contract {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MHRContract(Properties ctx, int HR_Contract_ID, String trxName) {
		super(ctx, HR_Contract_ID, trxName);
	}

	public MHRContract(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}

	public List<MGSContractCalendar> getCalendar() 
	{
		List<MGSContractCalendar> calendar = new Query(getCtx(), MGSContractCalendar.Table_Name, " HR_Contract_ID= ? ", get_TrxName()).setParameters(getHR_Contract_ID()).list();
		
		return calendar;
	}
}
