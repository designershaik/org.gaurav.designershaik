package org.dsi.finance.processes;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.eevolution.model.MPPProductBOM;
import org.eevolution.model.MPPProductBOMLine;

public class CreateCostingRecord extends SvrProcess 
{
	int p_M_Product_ID;

	@Override
	protected void prepare() 
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("M_Product_ID"))
				p_M_Product_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}

	}

	@Override
	protected String doIt() throws Exception
	{
		int productBOMID=new Query(getCtx(), MPPProductBOM.Table_Name, " M_Product_ID="+p_M_Product_ID+" and IsActive='Y'", get_TrxName()).firstId();
		MPPProductBOM bom = new MPPProductBOM(getCtx(), productBOMID, get_TrxName());
		MPPProductBOMLine[] bomLine=bom.getLines();
		
		for(int i=0;i<bomLine.length;i++)
		{
			int productID=bomLine[i].getM_Product_ID();
//			MCost cost=new MCost(0, 0, as, AD_Org_ID, M_CostElement_ID)
		}
		return "@OK@";
	}

}
