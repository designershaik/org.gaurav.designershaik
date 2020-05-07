package org.factories;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.libero.infowindow.CalculateMaterialPlan;
import org.libero.process.CRP;
import org.libero.process.CRPSummary;
import org.libero.process.CalculateLowLevel;
import org.libero.process.CompletePrintOrder;
import org.libero.process.ComponentChange;
import org.libero.process.CopyFromBOM;
import org.libero.process.CopyPriceToStandard;
import org.libero.process.CreateCostElement;
import org.libero.process.CreateDocType;
import org.libero.process.CreateProductPlanning;
import org.libero.process.DistributionRunOrders;
import org.libero.process.FixPaymentCashLine;
import org.libero.process.ImportProductPlanning;
import org.libero.process.MRP;
import org.libero.process.MRPUpdate;
import org.libero.process.MovementGenerate;
import org.libero.process.PP_Product_BOM_Check;
import org.libero.process.PrintBOM;
import org.libero.process.RollupBillOfMaterial;
import org.libero.process.RollupWorkflow;
import org.libero.report.CostBillOfMaterial;

public class LiberoManfuacturingProcessFactories  implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) 
	{
		if(className.equalsIgnoreCase(CalculateLowLevel.class.getName()))
			return new CalculateLowLevel();
		
		if(className.equalsIgnoreCase(CompletePrintOrder.class.getName()))
			return new CompletePrintOrder();
		
		if(className.equalsIgnoreCase(ComponentChange.class.getName()))
			return new ComponentChange();
		
		if(className.equalsIgnoreCase(CopyFromBOM.class.getName()))
			return new CopyFromBOM();
		
		if(className.equalsIgnoreCase(CopyPriceToStandard.class.getName()))
			return new CopyPriceToStandard();
		
		if(className.equalsIgnoreCase(CreateCostElement.class.getName()))
			return new CreateCostElement();
		
		if(className.equalsIgnoreCase(CreateDocType.class.getName()))
			return new CreateDocType();
		
		if(className.equalsIgnoreCase(CreateProductPlanning.class.getName()))
			return new CreateProductPlanning();
		
		if(className.equalsIgnoreCase(CRP.class.getName()))
			return new CRP();
		
		if(className.equalsIgnoreCase(CRPSummary.class.getName()))
			return new CRPSummary();
		
		if(className.equalsIgnoreCase(DistributionRunOrders.class.getName()))
			return new DistributionRunOrders();
		
		if(className.equalsIgnoreCase(FixPaymentCashLine.class.getName()))
			return new FixPaymentCashLine();
		
		if(className.equalsIgnoreCase(ImportProductPlanning.class.getName()))
			return new ImportProductPlanning();
		
		if(className.equalsIgnoreCase(MovementGenerate.class.getName()))
			return new MovementGenerate();
		
		if(className.equalsIgnoreCase(MRP.class.getName()))
			return new MRP();
		
		if(className.equalsIgnoreCase(MRPUpdate.class.getName()))
			return new MRPUpdate();
		
		if(className.equalsIgnoreCase(PP_Product_BOM_Check.class.getName()))
			return new PP_Product_BOM_Check();
		
		if(className.equalsIgnoreCase(PrintBOM.class.getName()))
			return new PrintBOM();
		
		if(className.equalsIgnoreCase(RollupBillOfMaterial.class.getName()))
			return new RollupBillOfMaterial();
		
		if(className.equalsIgnoreCase(RollupWorkflow.class.getName()))
			return new RollupWorkflow();
		
		if(className.equalsIgnoreCase(DistributionRunOrders.class.getName()))
			return new DistributionRunOrders();
		
		if(className.equalsIgnoreCase(CostBillOfMaterial.class.getName()))
			return new CostBillOfMaterial();
		
		if(className.equalsIgnoreCase(CalculateMaterialPlan.class.getName()))
			return new CalculateMaterialPlan();
		
		return null;
	}
}
