package com.gaurav.display.process;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.logging.Level;

import org.adempiere.util.Callback;
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
import org.compiere.model.X_A_Asset;
import org.compiere.model.X_A_Asset_Info_Fin;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class TransferDisplay extends SvrProcess {

	int M_MovementLine_ID = 0;
	MAcctSchema asc = null;
	MMovement movement = null;
	boolean p_decision = false;
	int AD_Table_ID = 0 ;
	@Override
	protected void prepare() 
	{
		AD_Table_ID = getTable_ID();
	}

	@Override
	protected String doIt() throws Exception 
	{
		if(MMovementLine.Table_ID==AD_Table_ID)
		{
			M_MovementLine_ID = getRecord_ID();
			MMovementLine line = new MMovementLine(getCtx(), M_MovementLine_ID, get_TrxName());
			if(line.get_ValueAsBoolean("IsGenerated"))
				return "Already generated ";
			else
				generateAssetAndTransferDisplay(line);
		}
		if(MMovement.Table_ID==AD_Table_ID)
		{
			MMovement mov = new MMovement(getCtx(), getRecord_ID(), get_TrxName());
			MMovementLine[] lines = mov.getLines(true);
			for(MMovementLine line : lines)
			{
				if(line.get_ValueAsBoolean("IsGenerated"))
					addLog(line.getM_MovementLine_ID(), null, null,"Already generated: "+line.getLine() , MMovementLine.Table_ID, line.getM_MovementLine_ID());
				else
					generateAssetAndTransferDisplay(line);
			}
		}
		
		return "@OK@";
	}

	private String generateAssetAndTransferDisplay(MMovementLine line) 
	{
		movement = (MMovement)line.getM_Movement();
		MDocType docType = new MDocType(getCtx(), movement.getC_DocType_ID(), get_TrxName());
		if(docType.get_ValueAsBoolean("DS_IsUseCogsForMovement"))
		{
			MClient client = new MClient(getCtx(), get_TrxName());
			asc = client.getAcctSchema();
			MLocator locator = (MLocator)line.getM_Locator();
			MLocator locatorTo = (MLocator)line.getM_LocatorTo();
			MWarehouse warehouse = (MWarehouse)locator.getM_Warehouse();
			MWarehouse warehouseTo = (MWarehouse)locatorTo.getM_Warehouse();
			boolean isSourceWarehouseIsCOgs = warehouse.get_ValueAsBoolean("DS_IsUseCogsForMovement");
			boolean isDestinationIsCogs = warehouseTo.get_ValueAsBoolean("DS_IsUseCogsForMovement");
			MProductCategoryAcct acct = MProductCategoryAcct.get(getCtx(), line.getM_Product().getM_Product_Category_ID(),asc.getC_AcctSchema_ID(), get_TrxName());
			MAccount cogsAccount = new MAccount(getCtx(), acct.getP_COGS_Acct(), get_TrxName());
			if(cogsAccount.getAccountType().equalsIgnoreCase(MElementValue.ACCOUNTTYPE_Asset))
			{
				if(!isSourceWarehouseIsCOgs && isDestinationIsCogs)
					verifyExistingAssetOrCreateAsset(line, locator, locatorTo);
				
				if(isSourceWarehouseIsCOgs && isDestinationIsCogs)
				{
					int A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");
					if(A_Asset_ID>0)
						createHistoryRecord(locatorTo,A_Asset_ID,line.getM_MovementLine_ID());
					if(A_Asset_ID<=0)
					{
						int existingAsset_ID = getExistingAsset(line,locator);
						if(existingAsset_ID>0)
						{
							MAsset asset = new MAsset(getCtx(), existingAsset_ID, get_TrxName());
							String result =  "Asset Already Exist: "
									+ ""+"\n "
									+ "please confirm and add that in the inventory move: "+asset.getM_Product().getName();
							addLog(asset.getA_Asset_ID(), null, null, result+"\n"+asset.getValue(), MAsset.Table_ID, asset.getA_Asset_ID());
							return result;
						}
						else
							createAsset(line);
					}
				}
				if(isSourceWarehouseIsCOgs && !isDestinationIsCogs)
				{
					int A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");
					if(A_Asset_ID>0)
						createHistoryRecord(locatorTo,A_Asset_ID,line.getM_MovementLine_ID());
					if(A_Asset_ID<=0)
					{
						int existingAsset_ID = getExistingAsset(line,locator);
						if(existingAsset_ID>0)
						{
							MAsset asset = new MAsset(getCtx(), existingAsset_ID, get_TrxName());
							String result =  "Asset Already Exist: "
									+ ""+"\n "
									+ "please confirm and add that in the inventory move: "+asset.getM_Product().getName();
							addLog(asset.getA_Asset_ID(), null, null, result+"\n"+asset.getValue(), MAsset.Table_ID, asset.getA_Asset_ID());
							return result;
						}
					}
				}
			}
		}
		line.set_ValueNoCheck("IsGenerated", true);
		line.saveEx();
		return "@OK@";
	}

	private void verifyExistingAssetOrCreateAsset(MMovementLine line,MLocator locator,MLocator locatorTo) 
	{
		boolean isCreateAsset = line.get_ValueAsBoolean("A_CreateAsset");
		int A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");
		if(isCreateAsset && A_Asset_ID>0)
			createAsset(line);
	
		if(A_Asset_ID<=0)
		{
			int existingAsset_ID = getExistingAsset(line,locator);
			if(existingAsset_ID>0)
			{
				String result =  "Asset Already Exist: "+"\n"+" Is this new asset ? "
						+ ""+"\n "
						+ "Should we create a new Asset & Asset Addition: ";
				final StringBuffer answer = new StringBuffer();
				this.processUI.ask(result, new Callback<Boolean>() 
				{
					public void onCallback(Boolean save) 
					{
						if(save)
						{
							createAsset(line);
							answer.append("done");
							p_decision = true;
						}
						else
							p_decision = false;
					}
				});

				while (answer.length() == 0) 
				{
					try 
					{
						Thread.sleep(200);
					} 
					catch (InterruptedException e)
					{
						log.severe(e.toString());
					}
				}
			}
			else
				createAsset(line);
		}
		else
			createHistoryRecord(locatorTo,A_Asset_ID,line.getM_MovementLine_ID());
		
	}

	private void createHistoryRecord(MLocator locatorTo, int existingAsset_ID,int M_MovementLine_ID)
	{
		MAsset asset = new MAsset(getCtx(), existingAsset_ID, get_TrxName());
		X_A_Asset_Info_Fin fin = new X_A_Asset_Info_Fin(getCtx(),0,get_TrxName());
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
		
		addLog(fin.getA_Asset_Info_Fin_ID(), null, null, textMsg, X_A_Asset_Info_Fin.Table_ID, fin.getA_Asset_Info_Fin_ID());
	}

	protected void createAsset(MMovementLine line) 
	{
		MProduct product = (MProduct)line.getM_Product();
		BigDecimal currentCostPrice = MCost.getCurrentCost(product, line.getM_AttributeSetInstance_ID(), asc, 0, null, Env.ONE, 0, true, get_TrxName());
		int movementQty = line.getMovementQty().intValue();
		boolean qtyMoreThanOne = false;
		if(movementQty>1)
			qtyMoreThanOne = true;

		for(int i =1 ; i<=movementQty ; i++)
		{
			MAssetAddition addition = createAssetAddition(line, i, qtyMoreThanOne, currentCostPrice);
			if(!qtyMoreThanOne)
			{
				line.set_ValueNoCheck("A_Asset_ID",addition.getA_Asset_ID());
				line.set_ValueNoCheck("A_Asset_Addition_ID",addition.getA_Asset_Addition_ID());
				line.saveEx();
			}
		}
		
	}

	private int getExistingAsset(MMovementLine line, MLocator locator) 
	{
		System.out.println(line.getM_Product_ID()+" / "+line.getM_Locator_ID()+" / "+locator.get_ValueAsInt("C_BPartner_ID")+" / "+locator.get_Value("C_BPartner_Location_ID"));
		int A_Asset_ID = DB.getSQLValue(get_TrxName(),
				"Select workfile.A_Asset_ID from A_Asset asd,A_Depreciation_Workfile workfile "
						+ "Where asd.A_Asset_ID = workfile.A_Asset_ID "
						+ "and asd.M_Product_ID = ? and asd.M_Locator_ID = ? "
						+ "and asd.C_BPartner_ID = ? and asd.C_BPartner_Location_ID = ? "
						+ " ",
						line.getM_Product_ID(), line.getM_Locator_ID(), locator.get_Value("C_BPartner_ID"),
						locator.get_Value("C_BPartner_Location_ID"));

		return A_Asset_ID;
	}

	private MAssetAddition createAssetAddition(MMovementLine line, int count, boolean qtyMoreThanOne,
			BigDecimal currentCostPrice) {
		MAssetAddition assetAdd = new MAssetAddition(Env.getCtx(), 0, get_TrxName());
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
				asset = createAssetFromInvoiceLine(line, count, qtyMoreThanOne);
				asset.dump();

				assetAdd.setA_Asset_ID(asset.getA_Asset_ID());
				if (asset.getA_Asset_Status().equals(X_A_Asset.A_ASSET_STATUS_New)) {
					MAssetGroupAcct assetgrpacct = MAssetGroupAcct.forA_Asset_Group_ID(Env.getCtx(),
							asset.getA_Asset_Group_ID(), assetAdd.getPostingType());
					assetAdd.setDeltaUseLifeYears(assetgrpacct.getUseLifeYears());
					assetAdd.setDeltaUseLifeYears_F(assetgrpacct.getUseLifeYears_F());
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

	public MAsset createAssetFromInvoiceLine(MMovementLine line, int qtyCount, boolean qtyMoreThanOne)
			throws ParseException {
		MAsset asset = null;
		asset = CreateAsset(line, qtyCount, qtyMoreThanOne);

		return asset;
	}

	public MAsset CreateAsset(MMovementLine line, int qtyCount, boolean qtyMoreThanOne) {
		int A_Asset_ID = 0;
		int A_Asset_Group_ID = line.getM_Product().getM_Product_Category().getA_Asset_Group_ID();
		if (line.get_ValueAsInt("A_Asset_ID") != 0)
			A_Asset_ID = line.get_ValueAsInt("A_Asset_ID");

		MAsset asset = new MAsset(Env.getCtx(), A_Asset_ID, line.get_TrxName());
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
		asset.setC_BPartner_ID(movement.getC_BPartner_ID());
		asset.setC_BPartner_Location_ID(movement.getC_BPartner_Location_ID());
		asset.setM_Locator_ID(line.getM_LocatorTo_ID());
		asset.setA_Asset_CreateDate(line.getM_Movement().getMovementDate());
		asset.setAssetServiceDate(line.getM_Movement().getMovementDate());
		asset.setAssetActivationDate(line.getM_Movement().getMovementDate());
		asset.setDateAcct(line.getM_Movement().getMovementDate());
		asset.setA_Asset_Group_ID(A_Asset_Group_ID);
		asset.setHelp(Msg.getMsg(MClient.get(Env.getCtx()).getAD_Language(), "CreatedFromInvoiceLine",
				new Object[] { line.getM_Movement().getDocumentNo(), line.getLine() }));

		asset.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
		asset.saveEx();
		return asset;
	}
}
