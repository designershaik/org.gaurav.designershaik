package com.gaurav.dsi.manufacturing.processes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.I_M_ProductionPlan;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MClient;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MProduction;
import org.compiere.model.MProductionLine;
import org.compiere.model.MProductionPlan;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class SingeProductProductionCreate extends SvrProcess{

	private int p_M_Production_ID=0;
	private MProduction m_production = null;
	private boolean mustBeStocked = false;  //not used
	private boolean recreate = false;
	private BigDecimal newQty = null;
	int lineno = 0;
	int count = 0;
	//private int p_M_Locator_ID=0;
	
	
	protected void prepare() {
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if ("Recreate".equals(name))
				recreate = "Y".equals(para[i].getParameter());
			else if ("ProductionQty".equals(name))
				newQty  = (BigDecimal) para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);		
		}
		
		p_M_Production_ID = getRecord_ID();
		m_production = new MProduction(getCtx(), p_M_Production_ID, get_TrxName());

	}	//prepare

	@Override
	protected String doIt() throws Exception {

		if ( m_production.get_ID() == 0 )
			throw new AdempiereUserError("Could not load production header");

		if ( m_production.isProcessed() )
			return "Already processed";

		return createLines();

	}
	
	private boolean costsOK(int M_Product_ID) throws AdempiereUserError {
		// Warning will not work if non-standard costing is used
		String sql = "SELECT ABS(((cc.currentcostprice-(SELECT SUM(c.currentcostprice*bom.qtybom)"
            + " FROM m_cost c"
            + " INNER JOIN PP_Product_BOMLine bom ON (c.m_product_id=bom.m_product_id)"
	            + " INNER JOIN m_costelement ce ON (c.m_costelement_id = ce.m_costelement_id AND ce.costingmethod = 'S')"
            + " WHERE bom.m_product_id = pp.m_product_id)"
            + " )/cc.currentcostprice))"
            + " FROM m_product pp"
            + " INNER JOIN m_cost cc on (cc.m_product_id=pp.m_product_id)"
            + " INNER JOIN m_costelement ce ON (cc.m_costelement_id=ce.m_costelement_id)"
            + " WHERE cc.currentcostprice > 0 AND pp.M_Product_ID = ?"
            + " AND ce.costingmethod='S'";
		
		BigDecimal costPercentageDiff = DB.getSQLValueBD(get_TrxName(), sql, M_Product_ID);
		
		if (costPercentageDiff == null)
		{
			costPercentageDiff = Env.ZERO;
			String msg = "Could not retrieve costs";
			if (MSysConfig.getBooleanValue(MSysConfig.MFG_ValidateCostsOnCreate, false, getAD_Client_ID())) {
				throw new AdempiereUserError(msg);
			} else {
				log.warning(msg);
			}
		}
		
		if ( (costPercentageDiff.compareTo(new BigDecimal("0.005")))< 0 )
			return true;
		
		return false;
	}

	protected String createLines() throws Exception {
		
		int created = 0;
		if (!m_production.isUseProductionPlan()) {
			validateEndProduct(m_production.getM_Product_ID());
			
			if (!recreate && "Y".equalsIgnoreCase(m_production.getIsCreated()))
				throw new AdempiereUserError("Production already created.");
			
			if (newQty != null )
				m_production.setProductionQty(newQty);
			
			m_production.deleteLines(get_TrxName());
			created = createLines(mustBeStocked);
		} else {
			Query planQuery = new Query(getCtx(), I_M_ProductionPlan.Table_Name, "M_ProductionPlan.M_Production_ID=?", get_TrxName());
			List<MProductionPlan> plans = planQuery.setParameters(m_production.getM_Production_ID()).list();
			for(MProductionPlan plan : plans) {
				validateEndProduct(plan.getM_Product_ID());
				
				if (!recreate && "Y".equalsIgnoreCase(m_production.getIsCreated()))
					throw new AdempiereUserError("Production already created.");
				
				plan.deleteLines(get_TrxName());
				int n = plan.createLines(mustBeStocked);
				if ( n == 0 ) 
				{return "Failed to create production lines"; }
				created = created + n;
			}
		}
		if ( created == 0 ) 
		{return "Failed to create production lines"; }
		
		
		m_production.setIsCreated("Y");
		m_production.save(get_TrxName());
		StringBuilder msgreturn = new StringBuilder().append(created).append(" production lines were created");
		return msgreturn.toString();
	}

	private void validateEndProduct(int M_Product_ID) throws Exception {
		isBom(M_Product_ID);
		
		if (!costsOK(M_Product_ID)) {
			String msg = "Excessive difference in standard costs";
			if (MSysConfig.getBooleanValue(MSysConfig.MFG_ValidateCostsDifferenceOnCreate, false, getAD_Client_ID())) {
				throw new AdempiereUserError("Excessive difference in standard costs");
			} else {
				log.warning(msg);
			}
		}
	}
	
	protected void isBom(int M_Product_ID) throws Exception
	{
		String bom = DB.getSQLValueString(get_TrxName(), "SELECT isbom FROM M_Product WHERE M_Product_ID = ?", M_Product_ID);
		if ("N".compareTo(bom) == 0)
		{
			throw new AdempiereUserError ("Attempt to create product line for Non Bill Of Materials");
		}
		int materials = DB.getSQLValue(get_TrxName(), "SELECT count(line.PP_Product_BOMLine_ID) FROM PP_Product_BOM bom, PP_Product_BOMLine line WHERE bom.PP_Product_BOM_ID=line.PP_Product_BOM_ID and bom.M_Product_ID = ?", M_Product_ID);
		if (materials == 0)
		{
			throw new AdempiereUserError ("Attempt to create product line for Bill Of Materials with no BOM Products");
		}
	}
	
	public int createLines(boolean mustBeStocked) {
		
		lineno = 100;

		int count = 0;

		// product to be produced
		MProduct finishedProduct = new MProduct(getCtx(), m_production.getM_Product_ID(), get_TrxName());
		

		MProductionLine line = new MProductionLine( m_production );
		line.setLine( lineno );
		line.setM_Product_ID( finishedProduct.get_ID() );
		line.setM_Locator_ID( m_production.getM_Locator_ID() );
		line.setMovementQty( m_production.getProductionQty());
		line.setPlannedQty(m_production.getProductionQty());
		
		line.saveEx();
		count++;
		
		createLines(mustBeStocked, finishedProduct, m_production.getProductionQty());
		
		return count;
	}
	
	private int createLines(boolean mustBeStocked, MProduct finishedProduct, BigDecimal requiredQty) {
		
		int defaultLocator = 0;
		
		MLocator finishedLocator = MLocator.get(getCtx(), m_production.getM_Locator_ID());
		
		int M_Warehouse_ID = finishedLocator.getM_Warehouse_ID();
		
		int asi = 0;

		// products used in production
		String sql = "SELECT line.M_Product_ID, line.QtyBOM" + " FROM PP_Product_BOM bom, PP_Product_BOMLine line "
				+ " WHERE bom.PP_Product_BOM_ID=line.PP_Product_BOM_ID and bom.M_Product_ID=" + finishedProduct.getM_Product_ID() + " ORDER BY line.Line";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = DB.prepareStatement(sql, get_TrxName());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				lineno = lineno + 10;
				int BOMProduct_ID = rs.getInt(1);
				BigDecimal BOMQty = rs.getBigDecimal(2);
				BigDecimal BOMMovementQty = BOMQty.multiply(requiredQty);
				
				MProduct bomproduct = new MProduct(Env.getCtx(), BOMProduct_ID, get_TrxName());
				

				if ( bomproduct.isBOM() && bomproduct.isPhantom() )
				{
					createLines(mustBeStocked, bomproduct, BOMMovementQty);
				}
				else
				{

					defaultLocator = bomproduct.getM_Locator_ID();
					if ( defaultLocator == 0 )
						defaultLocator = m_production.getM_Locator_ID();

					if (!bomproduct.isStocked())
					{					
						MProductionLine BOMLine = null;
						BOMLine = new MProductionLine( m_production );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed(BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.saveEx(get_TrxName());

						lineno = lineno + 10;
						count++;					
					}
					else if (BOMMovementQty.signum() == 0) 
					{
						MProductionLine BOMLine = null;
						BOMLine = new MProductionLine( m_production );
						BOMLine.setLine( lineno );
						BOMLine.setM_Product_ID( BOMProduct_ID );
						BOMLine.setM_Locator_ID( defaultLocator );  
						BOMLine.setQtyUsed( BOMMovementQty );
						BOMLine.setPlannedQty( BOMMovementQty );
						BOMLine.saveEx(get_TrxName());

						lineno = lineno + 10;
						count++;
					}
					else
					{

						// BOM stock info
						MStorageOnHand[] storages = null;
						MProduct usedProduct = MProduct.get(getCtx(), BOMProduct_ID);
						defaultLocator = usedProduct.getM_Locator_ID();
						if ( defaultLocator == 0 )
							defaultLocator = m_production.getM_Locator_ID();
						if (usedProduct == null || usedProduct.get_ID() == 0)
							return 0;

						MClient client = MClient.get(getCtx());
						MProductCategory pc = MProductCategory.get(getCtx(),
								usedProduct.getM_Product_Category_ID());
						String MMPolicy = pc.getMMPolicy();
						if (MMPolicy == null || MMPolicy.length() == 0) 
						{ 
							MMPolicy = client.getMMPolicy();
						}

						storages = MStorageOnHand.getWarehouse(getCtx(), M_Warehouse_ID, BOMProduct_ID, 0, null,
								MProductCategory.MMPOLICY_FiFo.equals(MMPolicy), true, 0, get_TrxName());

						MProductionLine BOMLine = null;
						int prevLoc = -1;
						int previousAttribSet = -1;
						// Create lines from storage until qty is reached
						for (int sl = 0; sl < storages.length; sl++) {

							BigDecimal lineQty = storages[sl].getQtyOnHand();
							if (lineQty.signum() != 0) {
								if (lineQty.compareTo(BOMMovementQty) > 0)
									lineQty = BOMMovementQty;


								int loc = storages[sl].getM_Locator_ID();
								int slASI = storages[sl].getM_AttributeSetInstance_ID();
								int locAttribSet = new MAttributeSetInstance(getCtx(), asi,
										get_TrxName()).getM_AttributeSet_ID();

								// roll up costing attributes if in the same locator
								if (locAttribSet == 0 && previousAttribSet == 0
										&& prevLoc == loc) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(lineQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.saveEx(get_TrxName());

								}
								// otherwise create new line
								else {
									BOMLine = new MProductionLine( m_production );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( loc );
									BOMLine.setQtyUsed( lineQty);
									BOMLine.setPlannedQty( lineQty);
									if ( slASI != 0 && locAttribSet != 0 )  // ie non costing attribute
										BOMLine.setM_AttributeSetInstance_ID(slASI);
									BOMLine.saveEx(get_TrxName());

									lineno = lineno + 10;
									count++;
								}
								prevLoc = loc;
								previousAttribSet = locAttribSet;
								// enough ?
								BOMMovementQty = BOMMovementQty.subtract(lineQty);
								if (BOMMovementQty.signum() == 0)
									break;
							}
						} // for available storages

						// fallback
						if (BOMMovementQty.signum() != 0 ) {
							if (!mustBeStocked)
							{

								// roll up costing attributes if in the same locator
								if ( previousAttribSet == 0
										&& prevLoc == defaultLocator) {
									BOMLine.setQtyUsed(BOMLine.getQtyUsed()
											.add(BOMMovementQty));
									BOMLine.setPlannedQty(BOMLine.getQtyUsed());
									BOMLine.saveEx(get_TrxName());

								}
								// otherwise create new line
								else {

									BOMLine = new MProductionLine( m_production );
									BOMLine.setLine( lineno );
									BOMLine.setM_Product_ID( BOMProduct_ID );
									BOMLine.setM_Locator_ID( defaultLocator );  
									BOMLine.setQtyUsed( BOMMovementQty);
									BOMLine.setPlannedQty( BOMMovementQty);
									BOMLine.saveEx(get_TrxName());

									lineno = lineno + 10;
									count++;
								}

							}
							else
							{
								throw new AdempiereUserError("Not enough stock of " + BOMProduct_ID);
							}
						}
					}
				}
			} // for all bom products
		} catch (Exception e) {
			throw new AdempiereException("Failed to create production lines", e);
		}
		finally {
			DB.close(rs, pstmt);
		}

		return count;
	}
}


