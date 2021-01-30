package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

public class MGSHREmployee extends X_GS_HR_Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHREmployee(Properties ctx, int GS_HR_Employee_ID, String trxName) {
		super(ctx, GS_HR_Employee_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MGSHREmployee(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	public static int getEmployeeID(String userID, int AD_Client_ID) 
	{
		int EmployeeID;
		EmployeeID = DB.getSQLValue(null, "Select GS_HR_Employee_ID from GS_HR_Employee where code=? and AD_Client_ID = ? ",Integer.parseInt(userID),AD_Client_ID);
		return EmployeeID;
	}

	public BigDecimal getLoanAmt(Timestamp startDate, int compensation_ID) 
	{
		BigDecimal pendingLoanAmt = Env.ZERO;
		pendingLoanAmt = DB.getSQLValueBD(get_TrxName(), "Select sum(fct.AmtSourceDr)-Sum(fct.AmtSourceCr) "
				+ "	From Fact_Acct fct, GS_HR_Compensation_Acct acct,C_ValidCombination comb "
				+ " Where acct.CH_Expense_Acct=comb.C_ValidCombination_ID "
				+ "	and fct.account_ID=comb.Account_ID and fct.dateacct <= ?"
				+ "	and acct.GS_HR_Compensation_Master_ID = ? and fct.C_BPartner_ID = ? ", startDate,compensation_ID,getC_BPartner_ID());
		
		return pendingLoanAmt;
	}

	public MGSHREmpCompensation getBasicSalary() 
	{
		MGSHREmpCompensation compensations = null ; 
		int GS_HR_EmpCompensation_ID = DB.getSQLValue(get_TrxName(), "Select comp.GS_HR_EmpCompensation_ID "
				+ "From GS_HR_EmpCompensation comp,GS_HR_Compensation_Master mast "
				+ "Where comp.GS_HR_Compensation_Master_ID= mast.GS_HR_Compensation_Master_ID "
				+ "and mast.GS_HR_IsBasic='Y'"
				+ "and comp.GS_HR_Employee_ID = ? ",getGS_HR_Employee_ID());
		if(GS_HR_EmpCompensation_ID>0)
			compensations = new MGSHREmpCompensation(getCtx(), GS_HR_EmpCompensation_ID, get_TrxName());
		
		return compensations;
	}

	public BigDecimal getAverageWorkingHour() 
	{
		return getGS_HR_TimeSlot_Group().getGS_HR_AverageWorkingHours();
	}
}
