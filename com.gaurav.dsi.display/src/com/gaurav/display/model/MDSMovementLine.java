package com.gaurav.display.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.compiere.model.I_M_MovementLine;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MAsset;
import org.compiere.model.MAssetAddition;
import org.compiere.model.MAssetGroupAcct;
import org.compiere.model.MClient;
import org.compiere.model.MConversionType;
import org.compiere.model.MCost;
import org.compiere.model.MDocType;
import org.compiere.model.MElementValue;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MLocator;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategoryAcct;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.model.X_A_Asset;
import org.compiere.model.X_A_Asset_Info_Fin;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MDSMovementLine extends MMovementLine{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSMovementLine(Properties ctx, int M_MovementLine_ID, String trxName) {
		super(ctx, M_MovementLine_ID, trxName);
		
	}

	public MDSMovementLine(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	public String generateAssetAndTransferDisplay(MDSMovementLine line,String trxName) 
	{
		MClient client = MClient.get(getCtx());
		MAcctSchema asc = client.getAcctSchema();
		
		MMovement movement = (MMovement)line.getM_Movement();
		MDocType docType = new MDocType(getCtx(), movement.getC_DocType_ID(), trxName);
		if(docType.get_ValueAsBoolean("DS_IsUseCogsForMovement"))
		{
			MLocator locator = (MLocator)line.getM_Locator();
			MLocator locatorTo = (MLocator)line.getM_LocatorTo();
			MWarehouse warehouse = (MWarehouse)locator.getM_Warehouse();
			MWarehouse warehouseTo = (MWarehouse)locatorTo.getM_Warehouse();
			boolean isSourceWarehouseIsCOgs = warehouse.get_ValueAsBoolean("DS_IsUseCogsForMovement");
			boolean isDestinationIsCogs = warehouseTo.get_ValueAsBoolean("DS_IsUseCogsForMovement");
			MProductCategoryAcct acct = MProductCategoryAcct.get(getCtx(), line.getM_Product().getM_Product_Category_ID(),asc.getC_AcctSchema_ID(), trxName);
			MAccount cogsAccount = new MAccount(getCtx(), acct.getP_COGS_Acct(), trxName);
			System.out.println("Account type: "+cogsAccount.getAccountType()+"  asdsa ");
			
			if(cogsAccount.getAccountType().equalsIgnoreCase(MElementValue.ACCOUNTTYPE_Expense))
			{
//				if(!isSourceWarehouseIsCOgs && isDestinationIsCogs)
//					verifyExistingAssetOrCreateAsset(line, locator, locatorTo,trxName);
				
				if(isSourceWarehouseIsCOgs && isDestinationIsCogs)
				{
					int A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");
					if(A_Asset_ID>0)
						createHistoryRecord(locatorTo,A_Asset_ID,line.getM_MovementLine_ID(),movement,trxName);
					if(A_Asset_ID<=0)
					{
						int existingAsset_ID = getExistingAsset(line,locator,trxName);
						if(existingAsset_ID>0)
						{
							MAsset asset = new MAsset(getCtx(), existingAsset_ID, trxName);
							String result =  "Asset Already Exist: "
									+ ""+"\n "
									+ "please confirm and add that in the inventory move: "+asset.getM_Product().getName();
							
							log.severe(result);

						}
						else
							createAsset(line,asc, movement.getC_BPartner_ID(), movement.getC_BPartner_Location_ID(), trxName);
					}
				}
				if(isSourceWarehouseIsCOgs && !isDestinationIsCogs)
				{
					int A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");
					if(A_Asset_ID>0)
						createHistoryRecord(locatorTo,A_Asset_ID,line.getM_MovementLine_ID(),movement,trxName);
					if(A_Asset_ID<=0)
					{
						int existingAsset_ID = getExistingAsset(line,locator,trxName);
						if(existingAsset_ID>0)
						{
							MAsset asset = new MAsset(getCtx(), existingAsset_ID, trxName);
							String result =  "Asset Already Exist: "
									+ ""+"\n "
									+ "please confirm and add that in the inventory move: "+asset.getM_Product().getName();
							log.severe(result);
						}
					}
				}
			}
		}
		line.set_ValueNoCheck("IsGenerated", true);
		line.saveEx();
		return "@OK@";
	}

//	private void verifyExistingAssetOrCreateAsset(MMovementLine line,MLocator locator,MLocator locatorTo,String trxName) 
//	{
//		boolean isCreateAsset = line.get_ValueAsBoolean("A_CreateAsset");
//		int A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");
//		if(isCreateAsset && A_Asset_ID>0)
//			createAsset(line,trxName);
//	
//		if(A_Asset_ID<=0)
//		{
//			int existingAsset_ID = getExistingAsset(line,locator,trxName);
//			if(existingAsset_ID>0)
//			{
//				String result =  "Asset Already Exist: "+"\n"+" Is this new asset ? "
//						+ ""+"\n "
//						+ "Should we create a new Asset & Asset Addition: ";
//				final StringBuffer answer = new StringBuffer();
//				this.processUI.ask(result, new Callback<Boolean>() 
//				{
//					public void onCallback(Boolean save) 
//					{
//						if(save)
//						{
//							createAsset(line,trxName);
//							answer.append("done");
//							p_decision = true;
//						}
//						else
//							p_decision = false;
//					}
//				});
//
//				while (answer.length() == 0) 
//				{
//					try 
//					{
//						Thread.sleep(200);
//					} 
//					catch (InterruptedException e)
//					{
//						log.severe(e.toString());
//					}
//				}
//			}
//			else
//				createAsset(line,trxName);
//		}
//		else
//			createHistoryRecord(locatorTo,A_Asset_ID,line.getM_MovementLine_ID(),trxName);
//		
//	}

	private void createHistoryRecord(MLocator locatorTo, int existingAsset_ID,int M_MovementLine_ID,MMovement movement,String trxName)
	{
		MAsset asset = new MAsset(getCtx(), existingAsset_ID, trxName);
		X_A_Asset_Info_Fin fin = new X_A_Asset_Info_Fin(getCtx(),0,trxName);
		fin.setC_BPartner_ID(asset.getC_BPartner_ID());
		fin.set_ValueNoCheck("C_BPartner_Location_ID", asset.getC_BPartner_Location_ID());
		fin.set_ValueNoCheck("M_Locator_ID", asset.getM_Locator_ID());
		fin.setA_Contract_Date(asset.getUpdated());
		fin.setA_Asset_ID(asset.getA_Asset_ID());
		fin.setA_Finance_Meth(X_A_Asset_Info_Fin.A_FINANCE_METH_Rented);
		fin.set_ValueNoCheck("DropShip_BPartner_ID", movement.getC_BPartner_ID());
		fin.set_ValueNoCheck("DropShip_Location_ID", movement.getC_BPartner_Location_ID());
		fin.set_ValueNoCheck("M_MovementLine_ID", M_MovementLine_ID);
		fin.saveEx();
		
		asset.setC_BPartner_ID(movement.getC_BPartner_ID());
		asset.setM_Locator_ID(locatorTo.getM_Locator_ID());
		asset.setC_BPartner_Location_ID(movement.getC_BPartner_Location_ID());
		asset.setAD_User_ID(movement.getAD_User_ID());
		asset.saveEx();
		
		String textMsg = "Transfered from: "+fin.getC_BPartner().getValue().concat(" Date: "+fin.getA_Contract_Date()).concat("\n")
						.concat(" Transferred to: "+movement.getC_BPartner().getValue());
		fin.setTextMsg(textMsg);
		fin.saveEx();
	}

	protected void createAsset(MMovementLine line,MAcctSchema asc,int bpartner_ID,int bpartnerLocation_ID,String trxName) 
	{
		MProduct product = (MProduct)line.getM_Product();
		BigDecimal currentCostPrice = MCost.getCurrentCost(product, line.getM_AttributeSetInstance_ID(), asc, 0, null, Env.ONE, 0, true, trxName);
		int movementQty = line.getMovementQty().intValue();
		boolean qtyMoreThanOne = false;
		if(movementQty>1)
			qtyMoreThanOne = true;

		for(int i =1 ; i<=movementQty ; i++)
		{
			MAssetAddition addition = createAssetAddition(line, i, qtyMoreThanOne, currentCostPrice,asc,bpartner_ID,bpartnerLocation_ID,trxName);
			if(!qtyMoreThanOne)
			{
				line.set_ValueNoCheck("A_Asset_ID",addition.getA_Asset_ID());
				line.set_ValueNoCheck("A_Asset_Addition_ID",addition.getA_Asset_Addition_ID());
				line.saveEx();
			}
		}
		
	}

	private int getExistingAsset(MMovementLine line, MLocator locator,String trxName) 
	{
		System.out.println(line.getM_Product_ID()+" / "+line.getM_Locator_ID()+" / "+locator.get_ValueAsInt("C_BPartner_ID")+" / "+locator.get_Value("C_BPartner_Location_ID"));
		int A_Asset_ID = DB.getSQLValue(trxName,
				"Select workfile.A_Asset_ID from A_Asset asd,A_Depreciation_Workfile workfile "
						+ "Where asd.A_Asset_ID = workfile.A_Asset_ID "
						+ "and asd.M_Product_ID = ? and asd.M_Locator_ID = ? "
						+ "and asd.C_BPartner_ID = ? and asd.C_BPartner_Location_ID = ? "
						+ " ",
						line.getM_Product_ID(), line.getM_Locator_ID(), locator.get_Value("C_BPartner_ID"),
						locator.get_Value("C_BPartner_Location_ID"));

		return A_Asset_ID;
	}

	private MAssetAddition createAssetAddition(MMovementLine line, int count, boolean qtyMoreThanOne,BigDecimal currentCostPrice,MAcctSchema asc,int bpartner_ID,int bpartnerLocation_ID,String trxName) {
		MAssetAddition assetAdd = new MAssetAddition(Env.getCtx(), 0, trxName);
		assetAdd.setAD_Org_ID(line.getAD_Org_ID());
		assetAdd.setPostingType(MAssetAddition.POSTINGTYPE_Actual);
		assetAdd.setA_SourceType(MAssetAddition.A_SOURCETYPE_Manual);

		// if (MAssetAddition.A_CAPVSEXP_Capital.equals(line.getA_CapvsExp())) {
		assetAdd.setA_CreateAsset(true);
		// }
		assetAdd.set_ValueNoCheck("M_MovementLine_ID", line.getM_MovementLine_ID());
		assetAdd.setM_Product_ID(line.getM_Product_ID());
		assetAdd.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
		assetAdd.setLine(line.getLine());
		assetAdd.setM_Locator_ID(line.getM_LocatorTo_ID());
		assetAdd.setA_CapvsExp(MInvoiceLine.A_CAPVSEXP_Capital);
		if (qtyMoreThanOne) {
			assetAdd.setA_QTY_Current(Env.ONE);
			assetAdd.setAssetAmtEntered(currentCostPrice);
			assetAdd.setAssetSourceAmt(currentCostPrice);
		} else {
			assetAdd.setA_QTY_Current(line.getMovementQty());
			assetAdd.setAssetAmtEntered(currentCostPrice);
			assetAdd.setAssetSourceAmt(currentCostPrice);
		}
		assetAdd.setC_Currency_ID(asc.getC_Currency_ID());
		int C_CovnersionType_ID = MConversionType.getDefault(asc.getAD_Client_ID());
		assetAdd.setC_ConversionType_ID(C_CovnersionType_ID);
		assetAdd.dump();
		StringBuilder sql = new StringBuilder("SELECT C_DocType_ID FROM C_DocType ")
				.append("WHERE AD_Client_ID=? AND AD_Org_ID IN (0,").append(line.getAD_Org_ID())
				.append(") AND DocBaseType='FAA' ").append("ORDER BY AD_Org_ID DESC, IsDefault DESC");
		int C_DocType_ID = DB.getSQLValue(null, sql.toString(), line.getAD_Client_ID());
		if (C_DocType_ID <= 0)
			log.severe("No FAA found for AD_Client_ID=" + line.getAD_Client_ID());
		else {
			if (log.isLoggable(Level.FINE))
				log.fine("(PO) - " + C_DocType_ID);
			assetAdd.setC_DocType_ID(C_DocType_ID);
		}

		MAsset asset = null;
		try {
			if (MAssetAddition.A_CAPVSEXP_Capital.equals(assetAdd.getA_CapvsExp()) && assetAdd.isA_CreateAsset()) {
				asset = createAssetFromInvoiceLine(line, count, qtyMoreThanOne,bpartner_ID,bpartnerLocation_ID,trxName);
				asset.dump();

				assetAdd.setA_Asset_ID(asset.getA_Asset_ID());
				if (asset.getA_Asset_Status().equals(X_A_Asset.A_ASSET_STATUS_New)) {
					@SuppressWarnings("deprecation")
					List<MAssetGroupAcct> assetGrpAccounts = MAssetGroupAcct.forA_Asset_Group_ID(Env.getCtx(),
							asset.getA_Asset_Group_ID(), assetAdd.getPostingType());
					for(MAssetGroupAcct assetgrpacct:assetGrpAccounts)
					{
						assetAdd.set_ValueNoCheck("DeltaUseLifeYears", assetgrpacct.getUseLifeYears());
						assetAdd.set_ValueNoCheck("DeltaUseLifeYears_F",assetgrpacct.getUseLifeYears_F());
					}
				}
			} else {
				assetAdd.setA_Asset_ID(line.get_ValueAsInt("A_Asset_ID"));
				assetAdd.setA_CreateAsset(false);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// assetAdd.setDescription(asset.getValue().concat(" -
		// ").concat(line.getDescription()));
		assetAdd.setDateDoc(line.getM_Movement().getMovementDate());
		assetAdd.setDateAcct(line.getM_Movement().getMovementDate());
		assetAdd.saveEx();

		if (line.get_ValueAsBoolean("Fil_PostAssetImmediately")) {
			assetAdd.processIt("CO");
			assetAdd.saveEx();
		}
		return assetAdd;
	}

	public MAsset createAssetFromInvoiceLine(MMovementLine line, int qtyCount, boolean qtyMoreThanOne,int bpartner_ID,int bpartnerLocation_ID,String trxName)
			throws ParseException {
		MAsset asset = null;
		asset = CreateAsset(line, qtyCount, qtyMoreThanOne,bpartner_ID,bpartnerLocation_ID, trxName);

		return asset;
	}

	public MAsset CreateAsset(MMovementLine line, int qtyCount, boolean qtyMoreThanOne,int bpartner_ID,int bpartnerLocation_ID,String trxName) {
		int A_Asset_ID = 0;
		int A_Asset_Group_ID = line.getM_Product().getM_Product_Category().getA_Asset_Group_ID();
		if (line.get_ValueAsInt("A_Asset_ID") != 0)
			A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");

		MAsset asset = new MAsset(Env.getCtx(), A_Asset_ID, trxName);
		if (A_Asset_ID == 0) 
		{
			String name = line.getM_Product().getValue().concat(" / ").concat(line.getM_Product().getName());
			asset.setDescription(line.getDescription());
			asset.setName(name);
			if (log.isLoggable(Level.FINE))
				log.fine("name=" + name);
		}
		asset.setM_Product_ID(line.getM_Product_ID());
		asset.setIsOwned(true);
		asset.setIsInPosession(false);
		asset.setC_BPartner_ID(bpartner_ID);
		asset.setC_BPartner_Location_ID(bpartnerLocation_ID);
		asset.setM_Locator_ID(line.getM_LocatorTo_ID());
		asset.setA_Asset_CreateDate(line.getM_Movement().getMovementDate());
		asset.setAssetServiceDate(line.getM_Movement().getMovementDate());
		asset.setAssetActivationDate(line.getM_Movement().getMovementDate());
		asset.setDateAcct(line.getM_Movement().getMovementDate());
		asset.setA_Asset_Group_ID(A_Asset_Group_ID);
		System.out.println(line.getM_Product().getM_Product_Category_ID());
		int DS_AssetGroup_ID = DB.getSQLValue(trxName, "Select DS_AssetGroup_ID  from M_Product_Category cat where cat.M_Product_Category_ID = ? ",line.getM_Product().getM_Product_Category_ID());
		asset.setA_Asset_Group_ID(DS_AssetGroup_ID);
		asset.setHelp(Msg.getMsg(MClient.get(Env.getCtx()).getAD_Language(), "CreatedFromInvoiceLine",
				new Object[] { line.getM_Movement().getDocumentNo(), line.getLine() }));

		asset.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
		asset.saveEx();
		return asset;
	}

	public MMovementLine[] getLines (int movementID)
	{
		MDSMovementLine[]		m_lines = null;
		
		final String whereClause = "M_Movement_ID=?";
		List<MDSMovementLine> list = new Query(getCtx(), I_M_MovementLine.Table_Name, whereClause, get_TrxName())
		 										.setParameters(movementID)
		 										.setOrderBy(MMovementLine.COLUMNNAME_Line)
		 										.list();
		m_lines = new MDSMovementLine[list.size ()];
		list.toArray (m_lines);
		return m_lines;
	}	//	getLines

}
