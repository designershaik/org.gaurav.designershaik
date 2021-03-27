package org.gaurav.payroll.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.MCurrency;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;

public class MGSHRAttendanceDet extends X_GS_HR_Attendance_Det 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSHRAttendanceDet(Properties ctx, int GS_HR_Attendance_Det_ID, String trxName) {
		super(ctx, GS_HR_Attendance_Det_ID, trxName);
		
	}

	public MGSHRAttendanceDet(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	int precision = 3;
	BigDecimal presentDays = Env.ZERO;
	int totalDaysInMonth = 0 ; 
	int employee_ID = 0 ;
	BigDecimal grossSalary = Env.ZERO;
	BigDecimal netSalary = Env.ZERO;
	BigDecimal totalDeduction = Env.ZERO;
	Timestamp startDate = null;
	Timestamp endDate = null;
	public boolean calculate(int monthlySalary_Trans_ID) 
	{
		setVariables();
		employee_ID = getGS_HR_Employee_ID();
		totalDaysInMonth = TimeUtil.getDaysBetween(startDate,endDate)+1;
		
		MGSHREmployee employee = new MGSHREmployee(getCtx(), employee_ID, get_TrxName());
		BigDecimal averageWorkingHour = employee.getAverageWorkingHour();
		MClient client = MClient.get(getCtx());
		MCurrency baseCur = new MCurrency(getCtx(), client.getC_Currency_ID(), get_TrxName()); 
		precision = baseCur.getStdPrecision();
		
		MGSHREmployeeMonthlySalary monthSal = new MGSHREmployeeMonthlySalary(getCtx(), 0, get_TrxName());
		monthSal.setGS_HR_Employee_ID(getGS_HR_Employee_ID());
		monthSal.setGS_HR_MonthlySalary_ID(monthlySalary_Trans_ID);
		monthSal.setGS_HR_GrossAmt(grossSalary);
		monthSal.setGS_HR_NetAmt(netSalary);
		monthSal.saveEx();
		
		
		BigDecimal totalPaidLeaves = DB.getSQLValueBD(get_TrxName(), "Select coalesce(sum(GS_HR_LeavesConsumed),0) "
				+ "from GS_HR_MonthlyLeaves lv,GS_HR_Leave_Master mst "
				+ "where lv.gs_hr_leave_master_id = mst.gs_hr_leave_master_id "
				+ "and lv.GS_HR_Attendance_Det_ID= ? "
				+ "and mst.gs_hr_leavetype !='AL' "
				+ "and mst.isactive ='Y' "
				+ "and mst.gs_hr_withpay ='Y' ", getGS_HR_Attendance_Det_ID());
		
		BigDecimal avlDays = DB.getSQLValueBD(get_TrxName(), "Select coalesce(sum(GS_HR_LeavesConsumed),0) "
				+ "from GS_HR_MonthlyLeaves lv,GS_HR_Leave_Master mst "
				+ "where lv.gs_hr_leave_master_id = mst.gs_hr_leave_master_id "
				+ "and lv.GS_HR_Attendance_Det_ID= ? "
				+ "and mst.gs_hr_leavetype ='AL' "
				+ "and mst.isactive ='Y' "
				+ "and mst.gs_hr_withpay ='Y' ", getGS_HR_Attendance_Det_ID());
		
		presentDays = getGS_HR_PresentDays().add(totalPaidLeaves);
		BigDecimal OT1 = getGS_HR_OverTime1();
		BigDecimal OT2 = getGS_HR_OverTime2();
		BigDecimal OT3 = getGS_HR_OverTime3();
		BigDecimal OT4 = getGS_HR_OverTime4();
		BigDecimal OT5 = getGS_HR_OverTime5();
		if(presentDays.compareTo(Env.ZERO)>0 || avlDays.compareTo(Env.ZERO)>0)
		{
			List<MGSHREmpCompensation> independantComp = new Query(getCtx(), MGSHREmpCompensation.Table_Name, " GS_HR_Employee_ID = ? ", get_TrxName())
														.setOnlyActiveRecords(true)
														.setParameters(getGS_HR_Employee_ID())
														.setOrderBy("SeqNo")
														.list();
			for(MGSHREmpCompensation empComp : independantComp)
			{
				String dependantOn = empComp.getGS_HR_CompDependantOn();
				int compensation_id = empComp.getGS_HR_Compensation_Master_ID();
				MGSHRCompensationMaster comp = (MGSHRCompensationMaster)empComp.getGS_HR_Compensation_Master();
				if(dependantOn==null || dependantOn.isBlank())
					dependantOn = comp.getGS_HR_CompDependantOn();
				
				dependantOn = dependantOn==null ? "":dependantOn;
				BigDecimal calculatedAmt = Env.ZERO;
				BigDecimal alreadyCalculatedSalary = getCalculatedSalary(dependantOn,monthSal.getGS_HR_EmployeeMonthlySalary_ID(),comp.isGS_HR_CalculateOnActuals(),employee_ID);
				BigDecimal perDayAmt = empComp.getAmt().divide(new BigDecimal(totalDaysInMonth), 10, RoundingMode.CEILING);
				if(!empComp.isGS_HR_IsPercent())
					calculatedAmt = perDayAmt.multiply(presentDays);
				
				if(empComp.isGS_HR_IsPercent())
				{
					if(comp.getGS_HR_CompensationType().equalsIgnoreCase("OTI"))
					{
						BigDecimal perHour = alreadyCalculatedSalary.divide(new BigDecimal(totalDaysInMonth), 10, RoundingMode.CEILING).divide(averageWorkingHour, 10, RoundingMode.CEILING);
						if(comp.getDS_OvertimeType().equalsIgnoreCase(MGSHRCompensationMaster.DS_OVERTIMETYPE_OvertimeRegular1) && OT1.compareTo(Env.ZERO)>0)
							calculatedAmt = perHour.multiply(OT1).multiply(empComp.getPercent()).divide(Env.ONEHUNDRED,  10, RoundingMode.CEILING);
						if(comp.getDS_OvertimeType().equalsIgnoreCase(MGSHRCompensationMaster.DS_OVERTIMETYPE_OvertimePremium1) && OT2.compareTo(Env.ZERO)>0)
							calculatedAmt = perHour.multiply(OT2).multiply(empComp.getPercent()).divide(Env.ONEHUNDRED,  10, RoundingMode.CEILING);
						if(comp.getDS_OvertimeType().equalsIgnoreCase(MGSHRCompensationMaster.DS_OVERTIMETYPE_OvertimeRegular2) && OT3.compareTo(Env.ZERO)>0)
							calculatedAmt = perHour.multiply(OT3).multiply(empComp.getPercent()).divide(Env.ONEHUNDRED,  10, RoundingMode.CEILING);
						if(comp.getDS_OvertimeType().equalsIgnoreCase(MGSHRCompensationMaster.DS_OVERTIMETYPE_OvertimePremium2) && OT4.compareTo(Env.ZERO)>0)
							calculatedAmt = perHour.multiply(OT4).multiply(empComp.getPercent()).divide(Env.ONEHUNDRED,  10, RoundingMode.CEILING);
						if(comp.getDS_OvertimeType().equalsIgnoreCase(MGSHRCompensationMaster.DS_OVERTIMETYPE_OvertimeRegular3) && OT5.compareTo(Env.ZERO)>0)
							calculatedAmt = perHour.multiply(OT5).multiply(empComp.getPercent()).divide(Env.ONEHUNDRED,  10, RoundingMode.CEILING);
				}
					else
						calculatedAmt = alreadyCalculatedSalary.max(empComp.getPercent()).divide(Env.ONEHUNDRED,  10, RoundingMode.CEILING);
				}
				if(comp.getGS_HR_CompensationType().equalsIgnoreCase("AVL"))
				{
					perDayAmt = alreadyCalculatedSalary.divide(new BigDecimal(totalDaysInMonth), 10, RoundingMode.CEILING);
					calculatedAmt = perDayAmt.multiply(avlDays);
				}
				if(calculatedAmt.compareTo(Env.ZERO)!=0)
					setSalaryDetails(monthSal,calculatedAmt.setScale(precision, RoundingMode.CEILING),compensation_id,comp.isGS_HR_IsEarning());
			}
			calculateGeneralCalculations(monthSal);
		}
		monthSal.setGS_HR_GrossAmt(grossSalary);
		monthSal.setGS_HR_NetAmt(grossSalary.subtract(totalDeduction));
		monthSal.setProcessed(true);
		monthSal.saveEx();
		
		return false;
	}

	private void setVariables() 
	{
		precision = 3;
		presentDays = Env.ZERO;
		totalDaysInMonth = 0 ; 
		employee_ID = 0 ;
		grossSalary = Env.ZERO;
		netSalary = Env.ZERO;
		totalDeduction = Env.ZERO;
		startDate = getGS_HR_MonthlyAttendance().getGS_HR_SalaryMonths().getStartDate();
		endDate = getGS_HR_MonthlyAttendance().getGS_HR_SalaryMonths().getEndDate();
		
	}

	private void calculateGeneralCalculations(MGSHREmployeeMonthlySalary monthSal) 
	{
		int[] installments_id = DB.getIDsEx(get_TrxName(), "select ins.gs_hr_installments_id from gs_hr_employeeadvance adv,gs_hr_installments ins "
				+ "where adv.gs_hr_employeeadvance_id = ins.gs_hr_employeeadvance_id  "
				+ "and ins.PayDate between ? and ?  "
				+ "and adv.gs_hr_employee_id  = ? "
				+ "and (ins.hr_break='N' or ins.IsActive='N') ",startDate,endDate,getGS_HR_Employee_ID());
		for(int inst_id : installments_id)
		{
			MGSHRInstallments inst = new MGSHRInstallments(getCtx(), inst_id, get_TrxName());
			BigDecimal amt = inst.getGS_InstallmentAmt();
			
			MGSHREmployeeAdvance advance = new MGSHREmployeeAdvance(getCtx(), inst.getGS_HR_EmployeeAdvance_ID(), get_TrxName());
			MGSHREmployeeSalaryDetails det = setSalaryDetails(monthSal, amt, advance.getGS_HR_Compensation_Master_ID(), false);
			
			inst.setGS_HR_EmployeeSalaryDetails_ID(det.getGS_HR_EmployeeSalaryDetails_ID());
			inst.setDate1(monthSal.getGS_HR_MonthlySalary().getGS_HR_SalaryDate());
			inst.saveEx();
		}
	}

	private BigDecimal getCalculatedSalary(String dependantOn, int monthlySalary_ID, boolean calculatedOnActuals,int employee_ID) 
	{
		StringBuilder where = new StringBuilder("");
		if(!dependantOn.isBlank())
		{
			where = new StringBuilder("GS_HR_Compensation_Master_ID in (");
			List<String> dependant = Arrays.asList(dependantOn.split(","));
			for(String dep:dependant)
			{
				if(!where.toString().equalsIgnoreCase("GS_HR_Compensation_Master_ID in ("))
					where.append(",");
				where.append(dep);
			}
			where.append(") and ");
		}
		BigDecimal totalSalary = Env.ZERO;
		String sql = "Select coalesce(sum(Amt),0) From GS_HR_EmployeeSalaryDetails where "+where.toString()+" GS_HR_EmployeeMonthlySalary_ID = "+monthlySalary_ID;
		if(calculatedOnActuals)
			sql = "Select coalesce(sum(Amt),0) From GS_HR_EmpCompensation where "+where.toString()+" GS_HR_Employee_ID = "+employee_ID;
		
		totalSalary = DB.getSQLValueBD(get_TrxName(), sql);
		return totalSalary;
	}

	private MGSHREmployeeSalaryDetails setSalaryDetails(MGSHREmployeeMonthlySalary monthSal, BigDecimal calculatedAmt, int compensation_id,boolean isEarning) 
	{
		MGSHREmployeeSalaryDetails det = new MGSHREmployeeSalaryDetails(monthSal);
		det.setGS_HR_Compensation_Master_ID(compensation_id);
		if(isEarning)
		{
			det.setAmt(calculatedAmt);
			grossSalary = grossSalary.add(calculatedAmt);
		}
		else
		{
			det.setAmt(calculatedAmt.negate());
			totalDeduction = totalDeduction.add(calculatedAmt);
		}
		det.saveEx();
		
		return det;
	}
	
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		
		MGSHRMonthlyAttendance att = new MGSHRMonthlyAttendance(getCtx(), getGS_HR_MonthlyAttendance_ID(), get_TrxName());
		
		BigDecimal TotalMonthDays = att.getDaysBetween();
		BigDecimal totalLeaves = DB.getSQLValueBD(get_TrxName(), 
				"Select coalesce(sum(GS_HR_LeavesConsumed),0) From GS_HR_MonthlyLeaves Where GS_HR_Attendance_Det_ID = ? ", getGS_HR_Attendance_Det_ID());
		
		BigDecimal totalDays =  getGS_HR_AbsentDays().add(getGS_HR_PresentDays()).add(getGS_HR_Holidays()).add(totalLeaves);
		
		if(totalDays.compareTo(TotalMonthDays)>0)
			throw new AdempiereException("Total Days In The Month "+TotalMonthDays+" < "+totalDays+" Employee: "+getGS_HR_Employee().getName());
		
		return true;
	}

}
