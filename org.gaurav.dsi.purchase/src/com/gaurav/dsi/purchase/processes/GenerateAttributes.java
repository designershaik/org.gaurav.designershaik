// Not used

package com.gaurav.dsi.purchase.processes;

import java.math.BigDecimal;

import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MProduct;
import org.compiere.process.SvrProcess;
import org.compiere.util.Env;

public class GenerateAttributes extends SvrProcess {
	int orderID;
	MOrderLine lines[];
	MOrder order;

	@Override
	protected void prepare() {
		orderID = getRecord_ID();
		order = new MOrder(getCtx(), orderID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception {
		int productID;
		BigDecimal qtyReceived;
		BigDecimal priceEntered;
		int uomID;
		lines = order.getLines();
		for (int i = 0; i < lines.length; i++) 
		{
			productID = lines[i].getM_Product_ID();
			qtyReceived = lines[i].getQtyEntered();
			priceEntered=lines[i].getPriceEntered();
			if(!qtyReceived.equals(lines[i].getQtyDelivered()))
			{
				uomID=lines[i].getC_UOM_ID();
				MProduct prod = new MProduct(getCtx(), productID, get_TrxName());
				if (prod.getM_AttributeSet_ID() == 0
						|| (Integer) prod.getM_AttributeSet_ID() == null)
				{
					return "";
				} else {
					
					for (int j = 0; j < qtyReceived.intValue(); j++) 
						{
						MOrderLine line = new MOrderLine(getCtx(), 0, get_TrxName());
						line.setC_Order_ID(orderID);
						line.setM_Product_ID(productID);
						line.setQtyEntered(Env.ONE);
						MAttributeSetInstance msi = MAttributeSetInstance.create(getCtx(), prod, get_TrxName());
						msi.setDescription(msi.getSerNo());
						msi.save();
						line.setM_AttributeSetInstance_ID(msi.getM_AttributeSetInstance_ID());
						line.setPriceEntered(priceEntered);
						line.setPriceActual(priceEntered);
						line.setC_UOM_ID(uomID);
						line.setQtyOrdered(Env.ONE);
						line.save();
							}
						}
					lines[i].delete(true);
				}
		}
		

		return "";
	}
}
