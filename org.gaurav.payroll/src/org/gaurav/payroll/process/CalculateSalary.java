package org.gaurav.payroll.process;

import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;
import org.gaurav.payroll.model.MGSHRAttendanceDet;
import org.gaurav.payroll.model.MGSHRMonthlySalary;

public class CalculateSalary extends SvrProcess
{
	int MonthlySalary_ID = 0 ;
	int p_GS_HR_Employee_ID = 0 ;
	int salaryMonth_ID = 0 ;
	int year_ID = 0 ; 
	MGSHRMonthlySalary sal = null ; 
	boolean recalculate = false;
	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("GS_HR_Employee_ID"))
				p_GS_HR_Employee_ID = para[i].getParameterAsInt();
			else if (name.equals("GS_HR_Recalculate"))
				recalculate = "Y".equals(para[i].getParameter());
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
		MonthlySalary_ID = getRecord_ID();
		sal = new MGSHRMonthlySalary(getCtx(), getRecord_ID(), get_TrxName());
		salaryMonth_ID = sal.getGS_HR_SalaryMonths_ID();
		year_ID = sal.getC_Year_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		Timestamp startDate = sal.getGS_HR_SalaryMonths().getStartDate();
		Timestamp endDate = sal.getGS_HR_SalaryMonths().getEndDate();
		Integer totalDaysInMonth = TimeUtil.getDaysBetween(startDate,endDate)+1;
		String whereClause = "  ";
		if(p_GS_HR_Employee_ID>0)
			whereClause += " and det.GS_HR_Employee_ID =  "+p_GS_HR_Employee_ID;
		
		int[] monthlyAttendanceDetails_ID = DB.getIDsEx(get_TrxName(), "select det.GS_HR_Attendance_Det_id " + 
				"from GS_HR_MonthlyAttendance mnth,GS_HR_Attendance_Det det " + 
				"where mnth.gs_hr_monthlyattendance_id = det.gs_hr_monthlyattendance_id "+
				"and mnth.C_Year_ID = ? " +
				" and mnth.AD_Client_ID = ? "+
				"and mnth.GS_HR_SalaryMonths_ID = ? "+whereClause , year_ID,sal.getAD_Client_ID(),salaryMonth_ID);
		for(int det_id : monthlyAttendanceDetails_ID)
		{
			MGSHRAttendanceDet det = new MGSHRAttendanceDet(getCtx(), det_id, get_TrxName());
			int GS_HR_EmployeeMonthlySalary_ID = DB.getSQLValue(get_TrxName(), 
					"Select GS_HR_EmployeeMonthlySalary_ID "
					+ "From GS_HR_EmployeeMonthlySalary "
					+ "Where GS_HR_MonthlySalary_ID = ? and GS_HR_Employee_ID = ? ",MonthlySalary_ID,det.getGS_HR_Employee_ID());
			
			if((GS_HR_EmployeeMonthlySalary_ID>0 && recalculate) || GS_HR_EmployeeMonthlySalary_ID<=0)
			{
				boolean calculated = det.calculate(MonthlySalary_ID,startDate,endDate,totalDaysInMonth);
				log.info("Is Salary Calculated: "+calculated);
			}
		}
		sal.setProcessed(true);
		sal.saveEx();
		
		return "@SalaryCalculated@";
	}
}
