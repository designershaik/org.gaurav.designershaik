package org.gaurav.payroll.process;

import java.util.logging.Level;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.gaurav.payroll.model.MGSHRAttendanceDet;
import org.gaurav.payroll.model.MGSHRMonthlySalary;

public class CalculateSalary extends SvrProcess
{
	int MonthlySalary_ID = 0 ;
	int p_GS_HR_Employee_ID = 0 ;
	int salaryMonth_ID = 0 ;
	int year_ID = 0 ; 
	MGSHRMonthlySalary sal = null ; 
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
			else
				log.log(Level.SEVERE,"prepare - Unknown Parameter: " + name);
		}
		MonthlySalary_ID = getRecord_ID();
		MGSHRMonthlySalary sal = new MGSHRMonthlySalary(getCtx(), getRecord_ID(), get_TrxName());
		salaryMonth_ID = sal.getGS_HR_SalaryMonths_ID();
		year_ID = sal.getC_Year_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		String whereClause = "  ";
		if(p_GS_HR_Employee_ID>0)
			whereClause += " and det.GS_HR_Employee_ID =  "+p_GS_HR_Employee_ID;
		
		int[] monthlyAttendanceDetails_ID = DB.getIDsEx(get_TrxName(), "select det.GS_HR_Attendance_Det_id " + 
				"from GS_HR_MonthlyAttendance mnth,GS_HR_Attendance_Det det " + 
				"where mnth.gs_hr_monthlyattendance_id = det.gs_hr_monthlyattendance_id "+
				"and mnth.C_Year_ID = ? " +
				"and mnth.GS_HR_SalaryMonths_ID = ? "+whereClause , year_ID,salaryMonth_ID);
		for(int det_id : monthlyAttendanceDetails_ID)
		{
			MGSHRAttendanceDet det = new MGSHRAttendanceDet(getCtx(), det_id, get_TrxName());
			boolean calculated = det.calculate(MonthlySalary_ID);
			log.info("Is Salary Calculated: "+calculated);
		}
		
		return null;
	}
}
