package com.gaurav.dsi.manufacturing.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;

import com.gaurav.dsi.manufacturing.processes.AssignResource;
import com.gaurav.dsi.manufacturing.processes.CompleteSimpleProduction;
import com.gaurav.dsi.manufacturing.processes.CreateAveragePORecordAndCorrectCurrentQty;
import com.gaurav.dsi.manufacturing.processes.CreateCostAdjustmentRecords;
import com.gaurav.dsi.manufacturing.processes.CreateInventoryMove;
import com.gaurav.dsi.manufacturing.processes.GenerateSerialNumberMO;
import com.gaurav.dsi.manufacturing.processes.GetDaysOfMonth;
import com.gaurav.dsi.manufacturing.processes.GetEmployeesForDay;
import com.gaurav.dsi.manufacturing.processes.ManufacturingIssueComponents;
import com.gaurav.dsi.manufacturing.processes.PrintBoxLabel;
import com.gaurav.dsi.manufacturing.processes.ProcessBatchNumbers;
import com.gaurav.dsi.manufacturing.processes.SingeProductProductionCreate;
import com.gaurav.dsi.manufacturing.processes.StartOrStopTask;
import com.gaurav.dsi.manufacturing.processes.TrackAndPrintProductHistory;
import com.gaurav.dsi.manufacturing.processes.UpdatePerUnitProductionActivityTime;
import com.gaurav.dsi.manufacturing.processes.VerifyBOMAndCost;

public class DSIManufacturingProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equals(AssignResource.class.getName()))
			return new AssignResource();
		
		if(className.equals(GetDaysOfMonth.class.getName()))
			return new GetDaysOfMonth();
		
		if(className.equals(GetEmployeesForDay.class.getName()))
			return new GetEmployeesForDay();
		
		if(className.equals(PrintBoxLabel.class.getName()))
			return new PrintBoxLabel();
		
		if(className.equals(GenerateSerialNumberMO.class.getName()))
			return new GenerateSerialNumberMO();
		
		if(className.equals(ProcessBatchNumbers.class.getName()))
			return new ProcessBatchNumbers();
		
		if(className.equals(SingeProductProductionCreate.class.getName()))
			return new SingeProductProductionCreate();
		
		if(className.equals(CompleteSimpleProduction.class.getName()))
			return new CompleteSimpleProduction();
		
		if(className.equals(ManufacturingIssueComponents.class.getName()))
			return new ManufacturingIssueComponents();
		
		if(className.equals(CreateInventoryMove.class.getName()))
			return new CreateInventoryMove();
		
		if(className.equals(VerifyBOMAndCost.class.getName()))
			return new VerifyBOMAndCost();
		
		if(className.equals(StartOrStopTask.class.getName()))
			return new StartOrStopTask();
		
		if(className.equals(CreateCostAdjustmentRecords.class.getName()))
			return new CreateCostAdjustmentRecords();
		
		if(className.equals(TrackAndPrintProductHistory.class.getName()))
			return new TrackAndPrintProductHistory();
	
		if(className.equals(CreateAveragePORecordAndCorrectCurrentQty.class.getName()))
			return new CreateAveragePORecordAndCorrectCurrentQty();
		
		if(className.equals(UpdatePerUnitProductionActivityTime.class.getName()))
			return new UpdatePerUnitProductionActivityTime();
			
		return null;
	}

}
