package com.gaurav.dsi.manufacturing.processes;

import java.util.List;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_ProductionPlan;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProductionPlan;
import org.compiere.model.Query;
import org.compiere.process.DocAction;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

public class CompleteSimpleProduction extends SvrProcess {

	int M_Production_ID ;
	MProduction production;
	String m_processMsg;
	@Override
	protected void prepare() 
	{
		M_Production_ID = getRecord_ID();
		production = new MProduction(getCtx(), M_Production_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		StringBuilder errors = new StringBuilder();
		int processed = 0;
		
		if(!isHaveEndProduct(production.getLines())) {
			return "Production does not contain End Product"+DocAction.STATUS_Invalid;
		}
			
		verifylotnumbers(production.getLines());
		if (!production.isUseProductionPlan()) 
		{
			MProductionLine[] lines = production.getLines();
			processLines(lines);
		}else {
			Query planQuery = new Query(Env.getCtx(), I_M_ProductionPlan.Table_Name, "M_ProductionPlan.M_Production_ID=?", get_TrxName());
			List<MProductionPlan> plans = planQuery.setParameters(production.getM_Production_ID()).list();
			for(MProductionPlan plan : plans) 
			{
				MProductionLine[] lines = plan.getLines();
				if (lines.length > 0) {
					errors.append(processLines(lines));
					if (errors.length() > 0) {
						
						m_processMsg = errors.toString();
						return DocAction.STATUS_Invalid;
					}
					processed = processed + lines.length;
				}
				plan.setProcessed(true);
				plan.saveEx();
			}
		}

		production.setProcessed(true);
		production.setDocAction(DocAction.ACTION_Close);
		production.setDocStatus(DocAction.ACTION_Complete);
		production.saveEx();
		return null;
	}
	private void verifylotnumbers(MProductionLine[] lines) 
	{
		for(MProductionLine line : lines) 
		{
			int M_AttributeSet_ID = line.getM_Product().getM_AttributeSet_ID();
			if(M_AttributeSet_ID!=0 && line.getM_AttributeSetInstance_ID()==0)
				throw new AdempiereException(Msg.getMsg(Env.getCtx(), "DS_AttributeSetMandatory"));
		}
		
	}

	private boolean isHaveEndProduct(MProductionLine[] lines) 
	{
		
		for(MProductionLine line : lines) {
			if(line.isEndProduct())
				return true;			
		}
		return false;
	}
	
	private Object processLines(MProductionLine[] lines) {
		StringBuilder errors = new StringBuilder();
		for ( int i = 0; i<lines.length; i++) {
			String error = lines[i].createTransactions(production.getMovementDate(), false);
			if (!Util.isEmpty(error)) {
				errors.append(error);
			} else { 
				lines[i].setProcessed( true );
				lines[i].saveEx(get_TrxName());
			}
		}

		return errors.toString();
	}
}
