package com.gaurav.display.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.MAsset;
import org.compiere.model.MBPartner;
import org.compiere.model.MClient;
import org.compiere.model.MIFixedAsset;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInventory;
import org.compiere.model.MInventoryLine;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MMatchInv;
import org.compiere.model.MProduct;
import org.compiere.model.MProject;
import org.compiere.util.DB;
import org.compiere.util.Msg;

public class MDSAsset extends MAsset{

	private static final long serialVersionUID = 1L;

	public MDSAsset(MAsset copy) {
		super(copy);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(MIFixedAsset ifa) {
		super(ifa);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(MInOut mInOut, MInOutLine sLine, int deliveryCount) {
		super(mInOut, sLine, deliveryCount);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(MInventory inventory, MInventoryLine invLine, BigDecimal qty, BigDecimal costs) {
		super(inventory, invLine, qty, costs);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(MMatchInv match) 
	{
		this(match.getCtx(), 0, match.get_TrxName());
		
		MInvoiceLine invoiceLine = new MInvoiceLine(getCtx(), match.getC_InvoiceLine_ID(), get_TrxName());
		MInOutLine inoutLine = new MInOutLine(getCtx(), match.getM_InOutLine_ID(), get_TrxName());
		
		setIsOwned(true);
		setIsInPosession(true);
		setA_Asset_CreateDate(inoutLine.getM_InOut().getMovementDate());
		//Fixed Asset should created in Organization as per the PO, MR, invoice and the asset addition document was recorded in.
		setAD_Org_ID(invoiceLine.getAD_Org_ID());
		// Asset Group:
		int A_Asset_Group_ID = invoiceLine.getA_Asset_Group_ID();
		MProduct product = MProduct.get(getCtx(), invoiceLine.getM_Product_ID());
		A_Asset_Group_ID = DB.getSQLValue(get_TrxName(), "select cat.DS_AssetGroup_ID from m_product prod,m_product_category cat where prod.M_Product_Category_ID = cat.M_Product_Category_ID and prod.M_Product_ID = ? ",match.getM_Product_ID());
		if (A_Asset_Group_ID <= 0) {
			A_Asset_Group_ID = product.getA_Asset_Group_ID();
		}
		setA_Asset_Group_ID(A_Asset_Group_ID);
		setHelp(Msg.getMsg(MClient.get(getCtx()).getAD_Language(), "CreatedFromInvoiceLine", 
				new Object[] {invoiceLine.getC_Invoice().getDocumentNo(), invoiceLine.getLine()}));
		
		String name = "";
		if (inoutLine.getM_Product_ID()>0)
		{
			name += product.getName() + "-";
			setM_Product_ID(inoutLine.getM_Product_ID());
			setM_AttributeSetInstance_ID(inoutLine.getM_AttributeSetInstance_ID());
		}
		MBPartner bp = new MBPartner(getCtx(), invoiceLine.getC_Invoice().getC_BPartner_ID(), null);
		name += bp.getName()+"-"+invoiceLine.getC_Invoice().getDocumentNo();
		if (log.isLoggable(Level.FINE)) log.fine("name=" + name);
		setValue(name);
		setName(name);
		setDescription(invoiceLine.getDescription());
	}

	public MDSAsset(MProject project) {
		super(project);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(Properties ctx, int A_Asset_ID, String trxName, String... virtualColumns) {
		super(ctx, A_Asset_ID, trxName, virtualColumns);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(Properties ctx, int A_Asset_ID, String trxName) {
		super(ctx, A_Asset_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(Properties ctx, MAsset copy, String trxName) {
		super(ctx, copy, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(Properties ctx, MAsset copy) {
		super(ctx, copy);
		// TODO Auto-generated constructor stub
	}

	public MDSAsset(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
