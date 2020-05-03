package org.gaurav.payroll.factories;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.gaurav.payroll.model.MGSHRApprovals;
import org.gaurav.payroll.model.MGSHRAttendanceAccess;
import org.gaurav.payroll.model.MGSHRAttendanceDet;
import org.gaurav.payroll.model.MGSHRAttendanceT;
import org.gaurav.payroll.model.MGSHRCompCalc;
import org.gaurav.payroll.model.MGSHRCompensationMaster;
import org.gaurav.payroll.model.MGSHRDailyAttendanceLog;
import org.gaurav.payroll.model.MGSHRDocumentBaseType;
import org.gaurav.payroll.model.MGSHREmpCompensation;
import org.gaurav.payroll.model.MGSHREmpLeave;
import org.gaurav.payroll.model.I_GS_HR_Employee;
import org.gaurav.payroll.model.MGSContractCalendar;
import org.gaurav.payroll.model.MGSContractPeriod;
import org.gaurav.payroll.model.MGSHREmployee;
import org.gaurav.payroll.model.MGSHREmployeeAdvance;
import org.gaurav.payroll.model.MGSHRLeaveMaster;
import org.gaurav.payroll.model.MGSHRLogsImported;
import org.gaurav.payroll.model.MGSHRMonthlyAttendance;
import org.gaurav.payroll.model.MGSHRMonthlyLeaves;
import org.gaurav.payroll.model.MGSHRSalaryMonths;
import org.gaurav.payroll.model.MGSHRTerminalDetails;
import org.gaurav.payroll.model.MHRContract;
import org.gaurav.payroll.model.MHRContractCompensation;
import org.gaurav.payroll.model.MHRContractLeave;
import org.gaurav.payroll.model.MHRDocuments;


public class PayrollModelFactories implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) 
	{
		if(tableName.equals(MGSHRCompCalc.Table_Name))
			return MGSHRCompCalc.class;
		
		if(tableName.equals(MGSHRCompensationMaster.Table_Name))
			return MGSHRCompensationMaster.class;
		
		if(tableName.equals(MGSHRDailyAttendanceLog.Table_Name))
			return MGSHRDailyAttendanceLog.class;
				
		if(tableName.equals(MGSHREmpLeave.Table_Name))
			return MGSHREmpLeave.class;
		
		if(tableName.equals(MGSHREmpCompensation.Table_Name))
			return MGSHREmpCompensation.class;
		
		if(tableName.equals(MGSHREmployee.Table_Name))
			return MGSHREmployee.class;
		
		if(tableName.equals(MGSHRLeaveMaster.Table_Name))
			return MGSHRLeaveMaster.class;
		
		if(tableName.equals(MHRDocuments.Table_Name))
			return MHRDocuments.class;
		
		if(tableName.equals(MGSHRTerminalDetails.Table_Name))
			return MGSHRTerminalDetails.class;
		
		if(tableName.equals(MGSHRTerminalDetails.Table_Name))
			return MGSHRTerminalDetails.class;
		
		if(tableName.equals(MGSHREmployeeAdvance.Table_Name))
			return MGSHREmployeeAdvance.class;
		
		if(tableName.equals(MGSHRApprovals.Table_Name))
			return MGSHRApprovals.class;
		
		if(tableName.equals(MGSHRAttendanceAccess.Table_Name))
			return MGSHRAttendanceAccess.class;
		
		if(tableName.equals(MGSHRAttendanceT.Table_Name))
			return MGSHRAttendanceT.class;
		
		if(tableName.equals(MGSHRLogsImported.Table_Name))
			return MGSHRLogsImported.class;
		
		if(tableName.equalsIgnoreCase(MGSHRDocumentBaseType.Table_Name))
			return MGSHRDocumentBaseType.class;
		
		if(tableName.equalsIgnoreCase(MGSHRAttendanceDet.Table_Name))
			return MGSHRAttendanceDet.class;
		
		if(tableName.equalsIgnoreCase(MGSHRMonthlyAttendance.Table_Name))
			return MGSHRMonthlyAttendance.class;
		
		if(tableName.equalsIgnoreCase(MGSHRMonthlyLeaves.Table_Name))
			return MGSHRMonthlyLeaves.class;
		
		if(tableName.equalsIgnoreCase(MGSHRSalaryMonths.Table_Name))
			return MGSHRSalaryMonths.class;
		
		if(tableName.equalsIgnoreCase(MHRContractCompensation.Table_Name))
			return MHRContractCompensation.class;
		
		if(tableName.equalsIgnoreCase(MHRContractLeave.Table_Name))
			return MHRContractLeave.class;
		
		if(tableName.equalsIgnoreCase(MGSContractCalendar.Table_Name))
			return MGSContractCalendar.class;
		
		if(tableName.equalsIgnoreCase(MGSContractPeriod.Table_Name))
			return MGSContractPeriod.class;
		
		if(tableName.equalsIgnoreCase(MHRContract.Table_Name))
			return MHRContract.class;
		
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) 
	{
		if(tableName.equals(MGSHRCompCalc.Table_Name))
			return new MGSHRCompCalc(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRCompensationMaster.Table_Name))
			return new MGSHRCompensationMaster(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRDailyAttendanceLog.Table_Name))
			return new MGSHRDailyAttendanceLog(Env.getCtx(),Record_ID,trxName);
				
		if(tableName.equals(MGSHREmpLeave.Table_Name))
			return new MGSHREmpLeave(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHREmpCompensation.Table_Name))
			return new MGSHREmpCompensation(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(I_GS_HR_Employee.Table_Name))
			return new MGSHREmployee(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRLeaveMaster.Table_Name))
			return new MGSHRLeaveMaster(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MHRDocuments.Table_Name))
			return new MHRDocuments(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRTerminalDetails.Table_Name))
			return new MGSHRTerminalDetails(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHREmployeeAdvance.Table_Name))
			return new MGSHREmployeeAdvance(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRApprovals.Table_Name))
			return new MGSHRApprovals(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRAttendanceAccess.Table_Name))
			return new MGSHRAttendanceAccess(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRAttendanceT.Table_Name))
			return new MGSHRAttendanceT(Env.getCtx(),Record_ID,trxName);
		
		if(tableName.equals(MGSHRLogsImported.Table_Name))
			return new MGSHRLogsImported(Env.getCtx(),Record_ID,trxName);	
		
		if(tableName.equalsIgnoreCase(MGSHRDocumentBaseType.Table_Name))
			return new MGSHRDocumentBaseType(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRAttendanceDet.Table_Name))
			return new MGSHRAttendanceDet(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRMonthlyAttendance.Table_Name))
			return new MGSHRMonthlyAttendance(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRMonthlyLeaves.Table_Name))
			return new MGSHRMonthlyLeaves(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRSalaryMonths.Table_Name))
			return new MGSHRSalaryMonths(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MHRContractCompensation.Table_Name))
			return new MHRContractCompensation(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MHRContractLeave.Table_Name))
			return new MHRContractLeave(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSContractCalendar.Table_Name))
			return new MGSContractCalendar(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSContractPeriod.Table_Name))
			return new MGSContractPeriod(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MHRContract.Table_Name))
			return new MHRContract(Env.getCtx(), Record_ID, trxName);
		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) 
	{
		if(tableName.equals(MGSHRCompCalc.Table_Name))
			return new MGSHRCompCalc(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRCompensationMaster.Table_Name))
			return new MGSHRCompensationMaster(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRDailyAttendanceLog.Table_Name))
			return new MGSHRDailyAttendanceLog(Env.getCtx(),rs,trxName);
			
		if(tableName.equals(MGSHREmpLeave.Table_Name))
			return new MGSHREmpLeave(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHREmpCompensation.Table_Name))
			return new MGSHREmpCompensation(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(I_GS_HR_Employee.Table_Name))
			return new MGSHREmployee(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRLeaveMaster.Table_Name))
			return new MGSHRLeaveMaster(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MHRDocuments.Table_Name))
			return new MHRDocuments(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRTerminalDetails.Table_Name))
			return new MGSHRTerminalDetails(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHREmployeeAdvance.Table_Name))
			return new MGSHREmployeeAdvance(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRApprovals.Table_Name))
			return new MGSHRApprovals(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRAttendanceAccess.Table_Name))
			return new MGSHRAttendanceAccess(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRAttendanceT.Table_Name))
			return new MGSHRAttendanceT(Env.getCtx(),rs,trxName);
		
		if(tableName.equals(MGSHRLogsImported.Table_Name))
			return new MGSHRLogsImported(Env.getCtx(),rs,trxName);	
		
		if(tableName.equalsIgnoreCase(MGSHRDocumentBaseType.Table_Name))
			return new MGSHRDocumentBaseType(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRAttendanceDet.Table_Name))
			return new MGSHRAttendanceDet(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRMonthlyAttendance.Table_Name))
			return new MGSHRMonthlyAttendance(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRMonthlyLeaves.Table_Name))
			return new MGSHRMonthlyLeaves(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRSalaryMonths.Table_Name))
			return new MGSHRSalaryMonths(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MHRContractCompensation.Table_Name))
			return new MHRContractCompensation(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MHRContractLeave.Table_Name))
			return new MHRContractLeave(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSContractCalendar.Table_Name))
			return new MGSContractCalendar(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSContractPeriod.Table_Name))
			return new MGSContractPeriod(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MHRContract.Table_Name))
			return new MHRContract(Env.getCtx(), rs, trxName);
		
		return null;
	}


}
