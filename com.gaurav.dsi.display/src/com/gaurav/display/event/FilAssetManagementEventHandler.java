//package com.gaurav.display.event;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Properties;
//import java.util.logging.Level;
//
//import org.adempiere.base.Core;
//import org.adempiere.base.event.AbstractEventHandler;
//import org.adempiere.base.event.IEventTopics;
//import org.adempiere.exceptions.AdempiereException;
//import org.adempiere.exceptions.FillMandatoryException;
//import org.compiere.model.I_A_Asset_Addition;
//import org.compiere.model.I_A_Depreciation_Exp;
//import org.compiere.model.I_A_Depreciation_Workfile;
//import org.compiere.model.I_C_Invoice;
//import org.compiere.model.I_C_InvoiceLine;
//import org.compiere.model.I_M_MatchInv;
//import org.compiere.model.MAccount;
//import org.compiere.model.MAcctSchema;
//import org.compiere.model.MAsset;
//import org.compiere.model.MAssetAcct;
//import org.compiere.model.MAssetAddition;
//import org.compiere.model.MAssetDisposed;
//import org.compiere.model.MAssetGroup;
//import org.compiere.model.MAssetGroupAcct;
//import org.compiere.model.MBPartner;
//import org.compiere.model.MClient;
//import org.compiere.model.MDepreciation;
//import org.compiere.model.MDepreciationEntry;
//import org.compiere.model.MDepreciationExp;
//import org.compiere.model.MDepreciationWorkfile;
//import org.compiere.model.MDocType;
//import org.compiere.model.MElementValue;
//import org.compiere.model.MInvoice;
//import org.compiere.model.MInvoiceLine;
//import org.compiere.model.MMatchInv;
//import org.compiere.model.MPInstance;
//import org.compiere.model.MPeriod;
//import org.compiere.model.MProcess;
//import org.compiere.model.MProduct;
//import org.compiere.model.MSequence;
//import org.compiere.model.PO;
//import org.compiere.model.Query;
//import org.compiere.model.SetGetModel;
//import org.compiere.model.SetGetUtil;
//import org.compiere.model.X_AD_Process;
//import org.compiere.model.X_A_Asset;
//import org.compiere.model.X_A_Asset_Addition;
//import org.compiere.model.X_A_Depreciation_Entry;
//import org.compiere.model.X_A_Depreciation_Exp;
//import org.compiere.model.X_C_DocType;
//import org.compiere.model.X_C_Invoice;
//import org.compiere.process.ProcessCall;
//import org.compiere.process.ProcessInfo;
//import org.compiere.process.ProcessInfoParameter;
//import org.compiere.util.CLogMgt;
//import org.compiere.util.CLogger;
//import org.compiere.util.DB;
//import org.compiere.util.Env;
//import org.compiere.util.Msg;
//import org.compiere.util.TimeUtil;
//import org.compiere.util.Trx;
//import org.fil.asset.model.MFILDepreciationExpT;
//import org.joda.time.DateTime;
//import org.osgi.service.event.Event;
//
//public class FilAssetManagementEventHandler extends AbstractEventHandler
//{
//
//	CLogger log = CLogger.getCLogger(FilAssetManagementEventHandler.class);
//	Timestamp depreciationStartDate = null;
//	Properties ctx = Env.getCtx();
//	String trxName = null;
//	Trx transaction = null;
//	MAcctSchema ass;
//
//	@Override
//	protected void initialize() 
//	{
//		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_M_MatchInv.Table_Name);
//		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_M_MatchInv.Table_Name);
//		
//		registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, I_C_Invoice.Table_Name);
//		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, I_C_Invoice.Table_Name);
//		
//		registerTableEvent(IEventTopics.DOC_BEFORE_VOID, I_C_Invoice.Table_Name);
//		registerTableEvent(IEventTopics.DOC_AFTER_VOID, I_C_Invoice.Table_Name);
//		
//		registerTableEvent(IEventTopics.PO_BEFORE_NEW, I_C_InvoiceLine.Table_Name);
//		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, I_C_InvoiceLine.Table_Name);
//		registerTableEvent(IEventTopics.PO_AFTER_NEW, I_C_InvoiceLine.Table_Name);
//		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, I_C_InvoiceLine.Table_Name);
//		registerTableEvent(IEventTopics.PO_AFTER_DELETE, I_C_InvoiceLine.Table_Name);
//		
//		registerTableEvent(IEventTopics.PO_AFTER_NEW, MDepreciationExp.Table_Name);
//		
//		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MDepreciationEntry.Table_Name);
//		
//		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MAssetAddition.Table_Name);
//		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MAssetAddition.Table_Name);
//		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MAssetAddition.Table_Name);
//		registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, MAssetAddition.Table_Name);
//		registerTableEvent(IEventTopics.PO_AFTER_NEW, MAsset.Table_Name);
//		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MAsset.Table_Name);
//		
//		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MAssetDisposed.Table_Name);
//		registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, MAssetDisposed.Table_Name);
//		registerTableEvent(IEventTopics.DOC_BEFORE_COMPLETE, MAssetDisposed.Table_Name);
//		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MAssetDisposed.Table_Name);
//		
//		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MAssetDisposed.Table_Name);
//		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MAssetDisposed.Table_Name);
//		
//		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MAssetAddition.Table_Name);
//		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MAssetAddition.Table_Name);		
//	}
//	
//	@Override
//	protected void doHandleEvent(Event event) 
//	{
//		
//		PO po = getPO(event);
//		transaction = Trx.get(po.get_TrxName(), true);
//		trxName = transaction.getTrxName();
//		if(po instanceof MInvoice)
//		{
//			MInvoice invoice = (MInvoice)po;
//			if(event.getTopic().equals(IEventTopics.DOC_AFTER_PREPARE))
//        		{
//				validateFixedAssetsInvoice_LRO(invoice);
//        		}
//			if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE))
//        		{
//				if(!invoice.isSOTrx())
//				{
//					MInvoiceLine[] lines = invoice.getLines();
//					for(MInvoiceLine line : lines)
//					{
//						if (line.isA_CreateAsset() && !line.isA_Processed())
//						{
//							int A_Asset_Addition_ID = isAssetAlreadyExistsForTheInvoiceLine(line);
//							if(A_Asset_Addition_ID==-1)
//							{
//								MAssetGroup group = new MAssetGroup(ctx, line.getA_Asset_Group_ID(), trxName);
//								boolean qtyMoreThanOne = false;
//								int totalQtyInvoiced = line.getQtyEntered().intValue();
//								boolean LowValueAsset = group.get_ValueAsBoolean("FIL_LowValueAsset");
//								if(LowValueAsset)
//								{
//									MAssetAddition addition = createAssetAddition(line,totalQtyInvoiced,false);
//									line.setA_Asset_ID(addition.getA_Asset_ID());
//									line.saveEx();
//								}
//								else
//								{
//									if(totalQtyInvoiced>1)
//										qtyMoreThanOne = true;
//										
//									for(int i =1 ; i<=totalQtyInvoiced ; i++)
//									{
//										MAssetAddition addition = createAssetAddition(line,i,qtyMoreThanOne);
//										if(!qtyMoreThanOne)
//										{
//											line.setA_Asset_ID(addition.getA_Asset_ID());
//											line.saveEx();
//										}
//									}
//								}
//							}
//							else
//							{
//								MAssetAddition addition = new MAssetAddition(ctx, A_Asset_Addition_ID, trxName);
//								if(!addition.processIt(X_A_Asset_Addition.DOCACTION_Complete))
//									validateBusinessPartner(addition.getC_Invoice().getC_BPartner_ID(),(PO)addition);
//								addition.saveEx();
//							}
//						}
//					}
//				}
//				if (invoice.isSOTrx()) 
//				{
//					MInvoiceLine[] mils = invoice.getLines();
//					for (MInvoiceLine mil: mils) {
//						if (mil.isA_CreateAsset() && !mil.isA_Processed()) {
//							MAssetDisposed.createAssetDisposed(mil);
//						}
//					}
//				}
//        		}
//			if(event.getTopic().equals(IEventTopics.DOC_BEFORE_VOID))
//	    		{
//				Env.setContext(ctx, "FIL_VOIDED", true);	
//	    		}
//			
//			if(event.getTopic().equals(IEventTopics.DOC_AFTER_VOID))
//    			{
//				Env.setContext(ctx, "FIL_VOIDED", false);
//			}
//		}
//		if(po instanceof MMatchInv)
//		{
//			if(event.getTopic().equals(IEventTopics.PO_AFTER_NEW) || 
//					(event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE) && po.is_ValueChanged(MMatchInv.COLUMNNAME_Processed)))
//			{
//				MMatchInv mi = (MMatchInv)po;
//				if (mi.isProcessed())
//				{
//					MInvoiceLine invoiceLine = new MInvoiceLine(mi.getCtx(), mi.getC_InvoiceLine_ID(), mi.get_TrxName());
//					if (invoiceLine.isA_CreateAsset() && !invoiceLine.isA_Processed())
//					{
//						MAssetAddition.createAsset(mi);
//					}
//				}
//			}
//		}
//		if (po instanceof MInvoiceLine)
//		{
//			if (event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE) || 
//					event.getTopic().equals(IEventTopics.PO_BEFORE_NEW)) 
//			{
//				MInvoiceLine line = (MInvoiceLine)po;
//				int A_Asset_ID = line.getA_Asset_ID();
//				if(A_Asset_ID!=0)
//				{
//					MInvoice invoice = new MInvoice(ctx, line.getC_Invoice_ID(),trxName);
//					if(invoice.getDocStatus().equals(X_C_Invoice.DOCSTATUS_Drafted))
//						validateifAssetIsAlreadyDepreciatedForTheGivenPeriod(A_Asset_ID,invoice.getDateAcct(),trxName,false);
//				}
//			}
//			
//			modelChange_InvoiceLine(SetGetUtil.wrap(po), event.getTopic());
//		}
//		
//		if (po instanceof MDepreciationExp)
//		{
//			MDepreciationExp exp = (MDepreciationExp)po;
//			int C_Activity_ID = exp.getA_Asset().getC_Activity_ID();
//			if(C_Activity_ID!=0)
//			{
//				exp.set_ValueOfColumn("C_Activity_ID", C_Activity_ID);
//				exp.saveEx();
//			}
//		}
//
//		if(po instanceof MDepreciationEntry)
//		{
//			MDepreciationEntry entry = (MDepreciationEntry)po;
//			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_COMPLETE))
//			{
//				MDocType dt = MDocType.get(Env.getCtx(), entry.getC_DocType_ID());
//				if (dt.isOverwriteDateOnComplete()) 
//				{ 
//					if (entry.getProcessedOn().signum() == 0) 
//					{
//						entry.setDateAcct(new Timestamp (System.currentTimeMillis()));
//						MPeriod.testPeriodOpen(Env.getCtx(), entry.getDateAcct(), 
//								entry.getC_DocType_ID(), entry.getAD_Org_ID());
//					}
//				}
//				if (dt.isOverwriteSeqOnComplete()) 
//				{
//					if (entry.getProcessedOn().signum() == 0) 
//					{
//						String value = DB.getDocumentNo(entry.getC_DocType_ID(), 
//							po.get_TrxName(), true, po);
//						if (value != null)
//							entry.setDocumentNo(value);
//					}
//				}
//			}
//		}
//		if(po instanceof MAssetAddition)
//		{
//			MAssetAddition assetAdd = (MAssetAddition)po;
//		
//			if(event.getTopic().equals(IEventTopics.DOC_BEFORE_PREPARE))
//			{
//				int Max_Asset_Addition_ID = DB.getSQLValue(trxName, "SELECT A_Asset_Addition_ID , max(DateAcct) From A_Asset_Addition Where DateAcct > ? "
//						+ " and A_Asset_Addition_ID!= ? and A_Asset_ID = ? GROUP BY A_Asset_Addition_ID ",assetAdd.getDateAcct(),assetAdd.getA_Asset_Addition_ID(),assetAdd.getA_Asset_ID());
//				if(Max_Asset_Addition_ID!=-1)
//					throw new AdempiereException("Max asset addition is remaining."+Max_Asset_Addition_ID);
//				
//				int A_Asset_Group_ID = assetAdd.getA_Asset().getA_Asset_Group_ID();
//				MAssetGroup grp = new MAssetGroup(ctx, A_Asset_Group_ID, trxName);
//				if(!grp.isDepreciated())
//				{
//					assetAdd.setDeltaUseLifeYears(99999);
//					assetAdd.setDeltaUseLifeYears_F(99999);
//					assetAdd.saveEx();
//				}
//				if(assetAdd.isA_CreateAsset())
//				{
//					assetAdd.setA_CreateAsset(false);
//					assetAdd.saveEx();
//				}
//			}
//			if(event.getTopic().equals(IEventTopics.DOC_AFTER_PREPARE))
//			{
//				int A_Asset_Group_ID = assetAdd.getA_Asset().getA_Asset_Group_ID();
//				MAssetGroup grp = new MAssetGroup(ctx, A_Asset_Group_ID, trxName);
//				if(!grp.isDepreciated())
//				{
//					assetAdd.setDeltaUseLifeYears(0);
//					assetAdd.setDeltaUseLifeYears_F(0);
//					assetAdd.saveEx();
//				}
//			}
//			if(event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE))
//			{
//				if(!assetAdd.get_ValueAsBoolean("IsConfirmed"))
//				{
//					int A_Asset_Group_ID = assetAdd.getA_Asset().getA_Asset_Group_ID();
//					MAssetGroup grp = new MAssetGroup(ctx, A_Asset_Group_ID, trxName);
//					int alreadyDepreciatedEntries = DB.getSQLValue(trxName, "Select count(*) From A_Depreciation_Exp Where "
//							+ "A_Depreciation_Entry_ID IS NOT NULL AND Processed='Y' AND Expense!=0 and A_Asset_ID = ? ",assetAdd.getA_Asset_ID());
//					
//					int openAdditions = DB.getSQLValue(trxName, "Select count(*) From A_Asset_Addition Where "
//							+ " Processed='N' AND DocStatus IN ('DR','IN' ) AND IsConfirmed='N'   AND A_Asset_ID = ? and A_Asset_Addition_ID != ? ",assetAdd.getA_Asset_ID(),assetAdd.getA_Asset_Addition_ID());
//					
//					if(grp.get_ValueAsBoolean("Fil_FollowSimplifiedHalfYrRule") && !grp.get_ValueAsBoolean("FIL_LowValueAsset"))
//					{
//						if(alreadyDepreciatedEntries>0)
//						{
//							MoveDepreciationToTempTable(assetAdd);
//						}
//						if(openAdditions>0) 
//						{
//							List<MAssetAddition> additions = new Query(ctx, I_A_Asset_Addition.Table_Name, " A_Asset_ID = ? AND DocStatus IN ('DR','IN') AND A_Asset_Addition_ID!=?  ", assetAdd.get_TrxName())
//									.setParameters(assetAdd.getA_Asset_ID(),assetAdd.getA_Asset_Addition_ID()).setOrderBy(MAssetAddition.COLUMNNAME_DateAcct +" desc").list();
//							for(MAssetAddition addition : additions)
//							{
//								addition.setDateDoc(assetAdd.getDateAcct());
//								addition.setDateAcct(assetAdd.getDateAcct());
//								addition.setDocStatus(X_A_Asset_Addition.DOCSTATUS_InProgress);
//								addition.set_ValueOfColumn("IsConfirmed",true);
//								addition.setA_CreateAsset(false);
//								addition.saveEx();
//								if(!addition.processIt(X_A_Asset_Addition.DOCACTION_Complete))
//								{
//									addition.set_ValueNoCheck("IsConfirmed", false);
//								}
//								addition.saveEx();
//							}
//						}
//					}
//				}
//			}
//			if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE))
//			{
//				MAssetGroup group = new MAssetGroup(ctx,assetAdd.getA_Asset().getA_Asset_Group_ID() , trxName);
//				if(group.get_ValueAsBoolean("Fil_FollowSimplifiedHalfYrRule") || group.get_ValueAsBoolean("FIL_LowValueAsset"))
//				{
//					if(assetAdd.getA_Period_Start()==0)
//					{
//						if(!group.get_ValueAsBoolean("FIL_LowValueAsset"))
//							UpdateDepreciationsExpense(assetAdd);
//						else
//							updateDepreciationsForLowValueAssets(assetAdd);
//						
//						UnProcessedDepreciationExpense(assetAdd.getA_Asset_ID());
//						updateDepreciationsToAssetValue(assetAdd.getA_Asset_ID());
//					}
//				}
//			}
//			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW) || event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE))
//			{
//				String description = "";
//				int C_InvoiceLine_ID = assetAdd.getC_InvoiceLine_ID();
//				if(C_InvoiceLine_ID>0)
//				{
//					MInvoiceLine line = new MInvoiceLine(ctx, C_InvoiceLine_ID, trxName);
//					description = assetAdd.getA_Asset().getValue().concat(line.getDescription()==null ? "":" - ".concat(line.getDescription()));
//				}
//				if(event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE) && assetAdd.isA_CreateAsset())
//				{
//					assetAdd.setA_CreateAsset(false);
//				}
//				assetAdd.setDescription(description);
//				if(assetAdd.getDocStatus().equals(X_A_Asset_Addition.DOCSTATUS_Drafted))
//					validateifAssetIsAlreadyDepreciatedForTheGivenPeriod(assetAdd.getA_Asset_ID(), assetAdd.getDateAcct(), trxName,true);
//				MAccount validcombination =   getValidCombination(po);
//				if(validcombination!=null)
//				{
//					int combinationcostCenterID=validcombination.getC_Activity_ID();
//					MElementValue account=new MElementValue(ctx, validcombination.getAccount_ID(), trxName);
//					if((account.getAccountType().equals("E")) || (account.getAccountType().equals("R")))
//					{
//						if(combinationcostCenterID==0)
//							addError(event, new AdempiereException("Cost Center (Activity) are null; Mandatory for P&L accounts"));
//					}
//				}
//			}
//		}
//		if(po instanceof MAsset)
//		{
//			MAsset asset = (MAsset)po;
//			int A_AssetGroup_ID = asset.getA_Asset_Group_ID();
//			MAssetGroup group = new MAssetGroup(ctx, A_AssetGroup_ID, trxName);
//			if(event.getTopic().equals(IEventTopics.PO_AFTER_NEW))
//			{
//				int AD_Sequence_ID = group.get_ValueAsInt("AD_Sequence_ID");
//				String inventoryNo = "-";
//				int inventorySeq_ID = group.get_ValueAsInt("FIL_InventoryNoDocSeq_ID");
//				if(inventorySeq_ID>0)
//				{
//					MSequence inventorySeq = new MSequence(ctx, inventorySeq_ID, trxName);
//					inventoryNo = MSequence.getDocumentNoFromSeq(inventorySeq, trxName, po);
//				}
//				MSequence seq = new MSequence(ctx, AD_Sequence_ID, trxName);
//				String nextSequence = MSequence.getDocumentNoFromSeq(seq, trxName, po);
//				String searchKey = group.get_ValueAsString("Value").concat(nextSequence);
//				asset.setValue(searchKey);
//				asset.setInventoryNo(inventoryNo);
//				asset.saveEx();
//			}
//			if(event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE))
//			{
//				boolean LowValueAsset = group.get_ValueAsBoolean("FIL_LowValueAsset");
//				if(LowValueAsset && !asset.isDepreciated() && !asset.isDisposed())
//				{
//					MDepreciationWorkfile assetwk = getCurrentDepreciationWorkFile(asset.getA_Asset_ID(), trxName);
//					Object[] params = new Object[]{X_A_Asset.A_ASSET_STATUS_Activated,assetwk.getA_Asset_ID()};
//					DB.executeUpdateEx("Update A_Asset Set IsDepreciated = 'Y', A_Asset_Status = ? Where A_Asset_ID = ? ",params, trxName);
//					
//					params = new Object[]{assetwk.getA_Depreciation_Workfile_ID()};
//					
//					DB.executeUpdateEx("Update A_Depreciation_Workfile Set "+I_A_Depreciation_Workfile.COLUMNNAME_IsDepreciated+" = 'Y' "
//							+ "Where A_Depreciation_Workfile_ID = ? ",params, trxName);
//					log.info("Updated depreciated.");
//				}
//			}
//		}
//		
//		if(po instanceof MAssetDisposed)
//		{
//			MAssetDisposed disposal = (MAssetDisposed)po;
//			int A_Asset_Group_ID = disposal.getA_Asset().getA_Asset_Group_ID();
//			MAssetGroup group = new MAssetGroup(ctx, A_Asset_Group_ID, trxName);
//			MAssetGroupAcct acct = MAssetGroupAcct.forA_Asset_Group_ID(ctx, A_Asset_Group_ID, disposal.getPostingType());
//			MDepreciationWorkfile assetwk = MDepreciationWorkfile.get(ctx, 
//					disposal.getA_Asset_ID(), disposal.getPostingType(),trxName);
//			if(group.isDepreciated())
//			{
//				if(event.getTopic().equals(IEventTopics.DOC_BEFORE_PREPARE))
//				{
//					Timestamp nextDepreciationDate = assetwk.getDateAcct();
//					BigDecimal totalRemainingDepreciationBeforeDisposal = Env.ZERO;
//					int totalDepriciatedEntries = getTotalDepreciatedEntriesForTheAsset(disposal.getA_Asset_ID(),true);
//					if(group.get_ValueAsBoolean("Fil_FollowSimplifiedHalfYrRule"))
//					{
//						if(totalDepriciatedEntries==0)
//						{
//							totalRemainingDepreciationBeforeDisposal = DB.getSQLValueBD(trxName, "Select sum(Expense) From A_Depreciation_Exp "
//									+ "where A_Asset_ID = ? and DateAcct <= ? ", disposal.getA_Asset_ID() ,TimeUtil.getMonthLastDay(disposal.getDateAcct()));
//						}
//						else
//						{
//							Timestamp firstDepricationDate  = null;
//							Timestamp secondQuarterDepreciationDate = null;
//							DateTime dateAcctJT = new DateTime(nextDepreciationDate);
//							String currentYear = Integer.toString(dateAcctJT.getYear());
//							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//							Timestamp minDepreciationDate;
//							Timestamp finalDepreciationDate = nextDepreciationDate;
//							try 
//							{
//								minDepreciationDate = new Timestamp(dateFormat.parse("30/06/"+currentYear+"").getTime());
//								firstDepricationDate = new Timestamp(dateFormat.parse("31/12/"+currentYear+"").getTime());
//								secondQuarterDepreciationDate = new Timestamp(dateFormat.parse("01/07/"+currentYear+"").getTime());
//								
//								if(nextDepreciationDate.after(minDepreciationDate))
//									finalDepreciationDate = firstDepricationDate;					
//								else
//									finalDepreciationDate = secondQuarterDepreciationDate;
//									
//								totalRemainingDepreciationBeforeDisposal = DB.getSQLValueBD(trxName, 
//										"Select coalesce(sum(Expense),0) From A_Depreciation_Exp Where DateAcct <= ?  and Processed = 'N' and A_Asset_ID = ? ", 
//										finalDepreciationDate,disposal.getA_Asset_ID());
//							}
//							catch (ParseException e) 
//							{
//								e.printStackTrace();
//							}
//						}
//					}
//					if(group.get_ValueAsBoolean("FIL_LowValueAsset"))
//					{
//						totalRemainingDepreciationBeforeDisposal = DB.getSQLValueBD(trxName, 
//								"Select coalesce(Expense,0) From A_Depreciation_Exp Where DateAcct <= ?  and Processed = 'N' and A_Asset_ID = ? ", 
//								 TimeUtil.getMonthLastDay(disposal.getDateAcct()),disposal.getA_Asset_ID());
//					}
//					totalRemainingDepreciationBeforeDisposal = totalRemainingDepreciationBeforeDisposal == null ? 
//																Env.ZERO:totalRemainingDepreciationBeforeDisposal;
//					if(totalRemainingDepreciationBeforeDisposal.compareTo(Env.ZERO)>0)
//					{
//						MDepreciationEntry entry = createDepreciationEntry(assetwk,acct);
//						int A_Depreciation_Exp_ID = DB.getSQLValue(trxName, "SELECT A_Depreciation_Exp_ID FROM A_Depreciation_Exp "
//									+ "WHERE A_Depreciation_Entry_ID = ? AND A_ASSET_ID = ? ",entry.getA_Depreciation_Entry_ID(),disposal.getA_Asset_ID());
//						
//	//					if(A_Depreciation_Exp_ID==-1)
//	//						throw new AdempiereException("Something is wrong here expense record not found for period "+TimeUtil.getMonthLastDay(assetwk.getDateAcct()));
//						if(A_Depreciation_Exp_ID!=-1)
//						{	
//							MDepreciationExp exp = new MDepreciationExp(ctx, A_Depreciation_Exp_ID, trxName);
//							exp.setExpense(totalRemainingDepreciationBeforeDisposal);
//							exp.setExpense_F(totalRemainingDepreciationBeforeDisposal);
//							exp.setA_Entry_Type(X_A_Depreciation_Exp.A_ENTRY_TYPE_Depreciation);
//							exp.setIsDepreciated(false);
//							exp.saveEx();
//							entry.setDescription("Created from Asset Disposal "+disposal.getDocumentInfo());
//							entry.setDocAction(X_A_Depreciation_Entry.DOCACTION_Complete);
//							entry.processIt(X_A_Depreciation_Entry.DOCACTION_Complete);
//							entry.saveEx();
//							assetwk.set_ValueOfColumn("FIL_BackupAccountDate", assetwk.getDateAcct());
//							assetwk.setDateAcct(disposal.getDateAcct());
//							assetwk.saveEx();
//						}
//					}
//				}
//				if(event.getTopic().equals(IEventTopics.DOC_AFTER_PREPARE))
//				{
//					if(group.get_ValueAsBoolean("Fil_FollowSimplifiedHalfYrRule"))
//					{
//						if((Timestamp)assetwk.get_Value("FIL_BackupAccountDate")!=null)
//						{
//							assetwk.setDateAcct((Timestamp)assetwk.get_Value("FIL_BackupAccountDate"));
//							assetwk.saveEx();
//						}
//					}
//				}
//				if(event.getTopic().equals(IEventTopics.DOC_BEFORE_COMPLETE))
//				{
//					BigDecimal totalDepreciation_delta = DB.getSQLValueBD(trxName, "Select coalesce(sum(Expense),0) From A_Depreciation_Exp "
//							+ "Where A_Asset_ID = ? and processed='Y' ", disposal.getA_Asset_ID());
//					BigDecimal expense = disposal.getA_Asset_Cost().subtract(totalDepreciation_delta);
//					disposal.setExpense(expense);
//					disposal.setA_Accumulated_Depr_Delta(totalDepreciation_delta);
//					disposal.setA_Accumulated_Depr(totalDepreciation_delta);
//					disposal.saveEx();		
//				}
//				if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE))
//				{
//					Object[] params = new Object[]{disposal.getDateAcct(),disposal.getA_Asset_ID()};
//					DB.executeUpdateEx("Update A_Asset Set  AssetDisposalDate = ? Where A_Asset_ID = ? ",params, trxName);
//					log.info("Updated asset disposal status .");
//				}
//			}
//			if(event.getTopic().equals(IEventTopics.PO_BEFORE_NEW) || event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE))
//			{
//				disposal.setDescription(disposal.getA_Asset().getValue());
//				validateifAssetIsAlreadyDepreciatedForTheGivenPeriod(disposal.getA_Asset_ID(), disposal.getDateAcct(), trxName,false);
//			}
//		}
//	}
//	
//	private void validateBusinessPartner(int C_BPartner_ID, PO po) 
//	{
//		Trx trx = Trx.get(trxName, false);
//		int AD_Process_ID = DB.getSQLValue(trxName, "SELECT AD_Process_ID From AD_Process Where AD_Process_UU like '8f755a2d-f8fa-4803-a302-a3a8a8040286' ");
//		ProcessInfoParameter pi1 = new ProcessInfoParameter("C_BP_Group_ID", 0,"","","");
//		ProcessInfoParameter pi2 = new ProcessInfoParameter("C_BPartner_ID",C_BPartner_ID, "","","");
//	
//		// Create a process info instance. This is a composite class containing the parameters.
//		ProcessInfo pi = new ProcessInfo("", AD_Process_ID,po.get_Table_ID(),po.get_ID());
//		pi.setParameter(new ProcessInfoParameter[] {pi1, pi2});
//		
//		MProcess pr = new Query(Env.getCtx(), X_AD_Process.Table_Name, 
//					"AD_Process_ID=?", trxName).setParameters(AD_Process_ID).first();
//			
//		if (pr==null) {
//		      log.warning("Process validate business partner does not exist.");
//		}
//		
//		ProcessCall processCall = null;
//		boolean procSuccess = false;
//
//		processCall = Core.getProcess(pr.getClassname());
//		MPInstance mpi = new MPInstance(Env.getCtx(), pr.get_ID(), po.get_ID());
//		mpi.saveEx();
//
//		pi.setAD_PInstance_ID(mpi.get_ID());
//		procSuccess = processCall.startProcess(Env.getCtx(), pi, trx);
//		if (!procSuccess)
//			log.warning("Process Failed: " + pr.getClassname());
//	}
//
//	private int isAssetAlreadyExistsForTheInvoiceLine(MInvoiceLine line) 
//	{
//		int A_Asset_Addition_ID = DB.getSQLValue(trxName, "Select A_Asset_Addition_ID From "
//				+ "A_Asset_Addition Where C_Invoice_ID = ? and C_InvoiceLine_ID = ? and DocStatus IN ('DR','IP') ", line.getC_Invoice_ID(),line.getC_InvoiceLine_ID());
//	
//		return A_Asset_Addition_ID;
//	}
//
//	private void updateDepreciationsToAssetValue(int A_Asset_ID) 
//	{
//		MAsset asset = new MAsset(ctx, A_Asset_ID, trxName);
//		final String sql = "UPDATE "+MDepreciationExp.Table_Name +" SET Description = ? "
//				+" WHERE A_Asset_ID = ? ";
//		Object[] params = new Object[]{asset.getValue(),A_Asset_ID};
//		int no = DB.executeUpdateEx(sql, params, trxName);
//		if (log.isLoggable(Level.FINE)) log.fine("sql=" + sql + "\nUpdated #" + no);
//	}
//
//	private int getTotalDepreciatedEntriesForTheAsset(int A_Asset_ID, boolean includeZeroExpense) 
//	{
//		String whereClase = " ";
//		if(!includeZeroExpense)
//			whereClase = "AND Expense!=0";
//		int totalDepreciatedEntries = DB.getSQLValue(trxName, "Select count(*) From A_Depreciation_Exp Where "
//				+ "A_Depreciation_Entry_ID IS NOT NULL AND Processed='Y' "+whereClase+"  and A_Asset_ID = ? ",A_Asset_ID );
//		return totalDepreciatedEntries;
//	}
//
//	private MAccount getValidCombination(PO po) 
//	{
//		int C_ValidCombination_ID;
//	    	int C_Charge_ID=po.get_ValueAsInt("C_Charge_ID");
//	    	MAccount validcombination = null;
//	    	if(C_Charge_ID!=0)
//	    	{
//	    		C_ValidCombination_ID=DB.getSQLValue(trxName, "select acct.ch_expense_acct from c_charge_Acct acct "
//	        			+ "where acct.c_charge_id=?",C_Charge_ID);
//	        	validcombination = new MAccount(ctx, C_ValidCombination_ID, trxName);
//	        	
//	    	}
//	    	return validcombination;
//	}
//
//	public MDepreciationEntry createDepreciationEntry(MDepreciationWorkfile assetwk, MAssetGroupAcct acct) 
//	{
//		int C_DocType_ID = DB.getSQLValue(trxName, "Select C_DocType_ID From C_DocType Where DocBaseType = ? and AD_Client_ID = ? "
//				+ "",X_C_DocType.DOCBASETYPE_FixedAssetsDepreciation,Env.getAD_Client_ID(ctx));
//		Timestamp lastDayOfTheMonth = TimeUtil.getMonthLastDay(assetwk.getDateAcct());
//		MDepreciationEntry entry = new MDepreciationEntry(ctx, 0, trxName);
//		entry.setDateAcct(lastDayOfTheMonth);
//		entry.setDateDoc(lastDayOfTheMonth);
//		entry.setC_AcctSchema_ID(acct.getC_AcctSchema_ID());
//		entry.setPostingType(acct.getPostingType());
//		entry.setC_Period_ID();
//		entry.setC_DocType_ID(C_DocType_ID);
//		entry.setIsApproved(false);
//		entry.saveEx();
//		Object[] params = new Object[]{entry.getA_Depreciation_Entry_ID(), assetwk.getA_Asset_ID()};
//		int noUpdated = DB.executeUpdateEx("UPDATE A_Depreciation_Exp SET "
//				+ "A_Depreciation_Entry_ID = NULL WHERE A_Depreciation_Entry_ID = ?  and A_Asset_ID != ? ",
//						params, trxName);
//		log.info("Update expenses to entry null "+noUpdated);
//		
//		return entry;
//	}
//
//	private void MoveDepreciationToTempTable(MAssetAddition assetAdd) 
//	{
//		List<MDepreciationExp> allDepreciations = new Query(ctx, I_A_Depreciation_Exp.Table_Name, " A_Asset_ID = ? ", trxName).
//				setParameters(assetAdd.getA_Asset_ID()).
//				list();
//		for(MDepreciationExp exp : allDepreciations)
//		{
//			MFILDepreciationExpT temp = new MFILDepreciationExpT(ctx, 0, trxName);
//			temp.setA_Account_Number_Acct(exp.getA_Account_Number_Acct());
//			temp.setA_Accumulated_Depr(exp.getA_Accumulated_Depr());
//			temp.setA_Accumulated_Depr_Delta(exp.getA_Accumulated_Depr_Delta());
//			temp.setA_Accumulated_Depr_F(exp.getA_Accumulated_Depr_F());
//			temp.setA_Accumulated_Depr_F_Delta(exp.getA_Accumulated_Depr_F_Delta());
//			temp.setA_Asset_Addition_ID(exp.getA_Asset_Addition_ID());
//			temp.setA_Asset_Cost(exp.getA_Asset_Cost());
//			temp.setA_Asset_Cost_Delta(exp.getA_Asset_Cost_Delta());
//			temp.setA_Asset_Disposed_ID(exp.getA_Asset_Disposed_ID());
//			temp.setA_Asset_ID(exp.getA_Asset_ID());
//			temp.setA_Asset_Remaining(exp.getA_Asset_Remaining());
//			temp.setA_Asset_Remaining_F(exp.getA_Asset_Remaining_F());
//			temp.setA_Depreciation_Entry_ID(exp.getA_Depreciation_Entry_ID());
//			temp.setA_Entry_Type(exp.getA_Entry_Type());
//			temp.setA_Period(exp.getA_Period());
//			temp.setC_Activity_ID(exp.get_ValueAsInt("C_Activity_ID"));
//			temp.setCR_Account_ID(exp.getCR_Account_ID());
//			temp.setDateAcct(exp.getDateAcct());
//			temp.setDescription(exp.getDescription());
//			temp.setDR_Account_ID(exp.getDR_Account_ID());
//			temp.setExpense(exp.getExpense());
//			temp.setExpense_F(exp.getExpense_F());
//			temp.setHelp(exp.getHelp());
//			temp.setIsDepreciated(exp.isDepreciated());
//			temp.setPostingType(exp.getPostingType());
//			temp.setProcessed(exp.isProcessed());
//			temp.setProcessing(exp.isProcessing());
//			temp.setReplication(exp.isReplication());
//			temp.setUseLifeMonths(exp.getUseLifeMonths());
//			temp.setUseLifeMonths_F(exp.getUseLifeMonths_F());
//			temp.saveEx();
//		}
//	}
//
//	/**
//	 * Check if is a valid fixed asset related invoice (LRO)
//	 * @param invoice
//	 */
//	private void validateFixedAssetsInvoice_LRO(MInvoice invoice)
//	{
//		if (invoice.get_ValueAsBoolean("IsFixedAssetInvoice"))
//		{
//			boolean hasFixedAssetLines = false;
//			boolean hasNormalLines = false;
//			for (MInvoiceLine line : invoice.getLines())
//			{
//				if (line.get_ValueAsBoolean("IsFixedAssetInvoice"))
//				{
//					hasFixedAssetLines = true;
//					if(line.getLineNetAmt().compareTo(Env.ZERO)==0)
//						throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_PriceEntered);
//				}
//				else if (line.getM_Product_ID() > 0)
//				{
//					MProduct product = MProduct.get(line.getCtx(), line.getM_Product_ID());
//					if (product.isItem())
//					{
//						// Only items are forbiden for FA invoices because in Romania these should use
//						// V_Liability vendor account and not V_Liability_FixedAssets vendor account
//						hasNormalLines = true;
//					}
//				}
//				//
//				// No mixed lines are allowed
//				if (hasFixedAssetLines && hasNormalLines)
//				{
//					throw new AdempiereException();
//				}
//			}
//		}
//	}
//
//	/**
//	 * Model Change Invoice Line
//	 * @param ctx
//	 * @param m model 
//	 * @param changeType set when called from model validator (See TYPE_*); else -1, when called from callout
//	 */
//	public static void modelChange_InvoiceLine(SetGetModel m, String changeType) 
//	{
//		if (IEventTopics.PO_BEFORE_NEW.equals(changeType) || IEventTopics.PO_BEFORE_CHANGE.equals(changeType)) 
//		{
//			int invoice_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_C_Invoice_ID);
//			@SuppressWarnings("unused")
//			boolean isSOTrx = DB.isSOTrx(MInvoice.Table_Name, MInvoice.COLUMNNAME_C_Invoice_ID+"="+invoice_id);
//			boolean isAsset = false;
//			int product_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_M_Product_ID);
//			if (product_id > 0) 
//			{
//				MProduct prod = MProduct.get(m.getCtx(), product_id);
//				if(prod.getProductType().equals("A"))
//					isAsset = true ;
//			}				
//			
//			m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_CreateAsset, isAsset);
//			if (isAsset) 
//			{
//				int C_Activity_ID = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_C_Activity_ID);
//				
//				if(C_Activity_ID==0)
//					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_C_Activity_ID);
//				
//				int A_AssetGroup_ID = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_A_Asset_Group_ID);
//				
//				if(A_AssetGroup_ID==0)
//					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_A_Asset_Group_ID);
//				
//				BigDecimal lineNetAmt = SetGetUtil.get_AttrValueAsBigDecimal(m, MInvoiceLine.COLUMNNAME_LineTotalAmt);
//				String voided = Env.getContext(Env.getCtx(), "FIL_VOIDED");	
//				if((voided.isEmpty() || voided.equals("N")) && lineNetAmt.compareTo(Env.ZERO)==0)
//				{
//					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_PriceEntered);
//				}
//				m.set_AttrValue("IsFixedAssetInvoice", isAsset);
//				m.set_AttrValue("A_CapvsExp", "Cap");
//				m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_CreateAsset, "Y");	
//			}
//			else {
//				m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_Group_ID, null);
//				m.set_AttrValue(MInvoiceLine.COLUMNNAME_A_Asset_ID, null);
//				m.set_AttrValue("IsFixedAssetInvoice", false);
//			}
//			if (isAsset && (m instanceof MInvoiceLine)) {
//				MInvoiceLine line = (MInvoiceLine)m;
//				if (MInvoiceLine.A_CAPVSEXP_Expense.equals(line.getA_CapvsExp()) && line.getA_Asset_ID() <= 0) {
//					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_A_Asset_ID);
//				}
//				if (line.getLineNetAmt().signum() == 0) {
//					throw new FillMandatoryException(MInvoiceLine.COLUMNNAME_QtyEntered, MInvoiceLine.COLUMNNAME_PriceEntered);
//				}
//			
//				MProduct product = line.getProduct();
//				if (product.isStocked() && line.get_ValueAsBoolean("IsFixedAssetInvoice")) {
//					throw new AdempiereException(product.getName());
//				}
//			}
//		}
//		
//		//
//		if (IEventTopics.PO_AFTER_NEW.equals(changeType) || IEventTopics.PO_AFTER_CHANGE.equals(changeType) || IEventTopics.PO_AFTER_DELETE.equals(changeType)) {
//			int invoice_id = SetGetUtil.get_AttrValueAsInt(m, MInvoiceLine.COLUMNNAME_C_Invoice_ID);
//			String sql =
//				"UPDATE C_Invoice i SET IsFixedAssetInvoice"
//						+"=(SELECT COALESCE(MAX(il.IsFixedAssetInvoice),'N')"
//								+" FROM C_InvoiceLine il"
//								+" WHERE il.C_Invoice_ID=i.C_Invoice_ID"
//									+" AND il."+MInvoiceLine.COLUMNNAME_IsDescription+"='N'"
//						+")"
//				+" WHERE C_Invoice_ID=?";
//			DB.executeUpdateEx(sql, new Object[]{invoice_id}, m.get_TrxName());
//		}
//	}
//
//	private void validateifAssetIsAlreadyDepreciatedForTheGivenPeriod(int A_Asset_ID, Timestamp dateAcct, String trx, boolean isAssetAdditionFromInvoice) 
//	{
//		MDepreciationWorkfile assetwk = getCurrentDepreciationWorkFile(A_Asset_ID,trx);
// 		if(assetwk.getDateAcct()!=null && !isAssetAdditionFromInvoice)
//		{
// 			MAsset asset = new MAsset(ctx, A_Asset_ID, trx);
// 			if((dateAcct.before(TimeUtil.addMonths(assetwk.getDateAcct(),-1)) || dateAcct.equals((TimeUtil.addMonths(assetwk.getDateAcct(),-1)))) 
// 					&& !asset.getA_Asset_Status().equals(X_A_Asset.A_ASSET_STATUS_New))
//				throw new AdempiereException(Msg.getMsg(Env.getCtx(), "FIL_ASSETALREADYDEPRECIATEDINTHISPERIOD"));
//		}
//	}
//
//	private MDepreciationWorkfile getCurrentDepreciationWorkFile(int A_Asset_ID, String trx) 
//	{
//		int A_DepreciationWorkFile_ID = DB.getSQLValue(trx, "Select A_Depreciation_Workfile_ID From A_Depreciation_Workfile "
//				+ "Where A_Asset_ID = ? ",A_Asset_ID);
//		
//		MDepreciationWorkfile workFile = new MDepreciationWorkfile(Env.getCtx(),A_DepreciationWorkFile_ID,trx);
//		
//		return workFile;
//	}
//
//	private MAssetAddition createAssetAddition(MInvoiceLine line, int count, boolean qtyMoreThanOne)
//	{
//		int C_AcctSchema_ID = DB.getSQLValue(trxName,"SELECT C_AcctSchema_ID FROM C_AcctSchema WHERE AD_Client_ID = ? ",Env.getAD_Client_ID(ctx));
//		ass=new MAcctSchema(ctx, C_AcctSchema_ID, trxName);
//		MAssetAddition assetAdd = new MAssetAddition(Env.getCtx(),0,trxName);
//		assetAdd.setAD_Org_ID(line.getAD_Org_ID());
//		assetAdd.setPostingType(MAssetAddition.POSTINGTYPE_Actual);
//		assetAdd.setA_SourceType(MAssetAddition.A_SOURCETYPE_Invoice);
//		
//		if (MAssetAddition.A_CAPVSEXP_Capital.equals(line.getA_CapvsExp())) {
//			assetAdd.setA_CreateAsset(true);
//		}
//		 
//		assetAdd.setC_Invoice_ID(line.getC_Invoice_ID());
//		assetAdd.setC_InvoiceLine_ID(line.getC_InvoiceLine_ID());
//		assetAdd.setM_Product_ID(line.getM_Product_ID());
//		assetAdd.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
//		assetAdd.setLine(line.getLine());
//		assetAdd.setM_Locator_ID(line.getM_InOutLine().getM_Locator_ID());
//		assetAdd.setA_CapvsExp(line.getA_CapvsExp());
//		if(qtyMoreThanOne)
//		{
//			assetAdd.setA_QTY_Current(Env.ONE);
//			assetAdd.setAssetAmtEntered(line.getPriceEntered());
//			assetAdd.setAssetSourceAmt(line.getPriceEntered());
//		}
//		else
//		{
//			assetAdd.setA_QTY_Current(line.getQtyInvoiced());
//			assetAdd.setAssetAmtEntered(line.getLineNetAmt());
//			assetAdd.setAssetSourceAmt(line.getLineNetAmt());	
//		}
//		assetAdd.setC_Currency_ID(line.getC_Invoice().getC_Currency_ID());
//		assetAdd.setC_ConversionType_ID(line.getC_Invoice().getC_ConversionType_ID());
//		assetAdd.dump();
//		StringBuilder sql = new StringBuilder ("SELECT C_DocType_ID FROM C_DocType ")
//				.append( "WHERE AD_Client_ID=? AND AD_Org_ID IN (0,").append(line.getAD_Org_ID())
//				.append( ") AND DocBaseType='FAA' ")
//				.append( "ORDER BY AD_Org_ID DESC, IsDefault DESC");
//			int C_DocType_ID = DB.getSQLValue(null, sql.toString(), line.getAD_Client_ID());
//			if (C_DocType_ID <= 0)
//				log.severe ("No FAA found for AD_Client_ID=" + line.getAD_Client_ID ());
//			else
//			{
//				if (log.isLoggable(Level.FINE)) log.fine("(PO) - " + C_DocType_ID);
//				assetAdd.setC_DocType_ID (C_DocType_ID);
//			}
//		
//		MAsset asset = null;
//		try 
//		{
//			if (MAssetAddition.A_CAPVSEXP_Capital.equals(assetAdd.getA_CapvsExp()) && assetAdd.isA_CreateAsset()) 
//			{ 
//				asset = createAssetFromInvoiceLine(line,count,qtyMoreThanOne);
//				asset.dump();
//				
//				assetAdd.setA_Asset_ID(asset.getA_Asset_ID());
//				if(asset.getA_Asset_Status().equals(X_A_Asset.A_ASSET_STATUS_New))
//				{
//					MAssetGroupAcct assetgrpacct = MAssetGroupAcct.forA_Asset_Group_ID(Env.getCtx(), asset.getA_Asset_Group_ID(), assetAdd.getPostingType());
//					assetAdd.setDeltaUseLifeYears(assetgrpacct.getUseLifeYears());
//					assetAdd.setDeltaUseLifeYears_F(assetgrpacct.getUseLifeYears_F());
//				}
//			} 
//			else 
//			{
//				assetAdd.setA_Asset_ID(line.getA_Asset_ID());
//				assetAdd.setA_CreateAsset(false);
//			}
//		} 
//		catch (ParseException e) 
//		{
//			e.printStackTrace();
//		}
////		assetAdd.setDescription(asset.getValue().concat(" - ").concat(line.getDescription()));
//		assetAdd.setDateDoc(line.getC_Invoice().getDateAcct());
//		assetAdd.setDateAcct(line.getC_Invoice().getDateAcct());
//		assetAdd.saveEx();
//		
//		if(line.get_ValueAsBoolean("Fil_PostAssetImmediately") )
//		{
//			assetAdd.processIt("CO");
//			assetAdd.saveEx();
//		}
//		return assetAdd;
//	}
//	
//
//	private void UnProcessedDepreciationExpense(int A_Asset_ID) 
//	{		
//		final String sql = "UPDATE "+MDepreciationExp.Table_Name +" SET Processed='N' "
//				+" WHERE Expense!=0 AND A_Asset_ID = ? AND A_Depreciation_Entry_ID IS NULL ";
//		Object[] params = new Object[]{A_Asset_ID};
//		int no = DB.executeUpdateEx(sql, params, trxName);
//		if (log.isLoggable(Level.FINE)) log.fine("sql=" + sql + "\nUpdated #" + no);
//		
//		no = DB.executeUpdate("DELETE FROM FIL_Depreciation_Exp_T",trxName);
//	}
//
//
//	private void truncDepreciation(MAssetAddition addition)
//	{
//		final String sql = "DELETE FROM "+MDepreciationExp.Table_Name
//				+" WHERE ( Processed = ? or Expense = 0)"
//					+" AND "+MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?";
//		Object[] params = new Object[]{false, addition.getA_Asset_ID()};
//		int no = DB.executeUpdateEx(sql, params, trxName);
//		if (log.isLoggable(Level.FINE)) log.fine("sql=" + sql + "\nDeleted #" + no);
//	}
//
//	private void UpdateDepreciationsExpense(MAssetAddition addition) 
//	{		
//		int alreadyDepreciatedEntries = DB.getSQLValue(trxName, "Select count(*) From A_Depreciation_Exp Where "
//				+ "A_Depreciation_Entry_ID IS NOT NULL AND Processed='Y' AND Expense!=0 and A_Asset_ID = ? ",addition.getA_Asset_ID());
//		
//		Timestamp assetRecordedDate = addition.getDateAcct();
//		BigDecimal totalOldDepreciationAmt = Env.ZERO;
//		
//		if(alreadyDepreciatedEntries==0)
//		{
//			truncDepreciation(addition);
//			buildDepreciation(addition);
//			final String updateSpecialFlag = "UPDATE A_Depreciation_Exp SET FIL_SpecialRule= 'Y'"+" WHERE A_Asset_ID = ? ";
//			Object[] params = new Object[]{addition.getA_Asset_ID()};
//			int no = DB.executeUpdateEx(updateSpecialFlag, params, trxName);	
//			int currentDepreciationExpense = DB.getSQLValue(trxName,"select A_Depreciation_Exp_ID "
//					+ "from A_Depreciation_Exp where DateAcct>= ? and A_Asset_ID = ?  order by dateacct ", 
//					assetRecordedDate,addition.getA_Asset_ID());
//			
//			List<MDepreciationExp> oldDepreciations = new Query(ctx, I_A_Depreciation_Exp.Table_Name, " A_Asset_ID = ? and DateAcct < ? and Processed='N' ", trxName).
//					setParameters(addition.getA_Asset_ID(),assetRecordedDate).
//					list();
//			for(MDepreciationExp expense : oldDepreciations)
//			{
//				BigDecimal depExpense = expense.getExpense();
//				totalOldDepreciationAmt = totalOldDepreciationAmt.add(depExpense);
//				expense.setProcessed(true);
//				expense.setExpense(Env.ZERO);
//				expense.setHelp("0|0=0");
//				expense.setExpense_F(Env.ZERO);
//				expense.saveEx();	
//			}				
//
//			if(currentDepreciationExpense!=-1)
//			{
//				MDepreciationExp recentDepreciation = new MDepreciationExp(ctx, currentDepreciationExpense, trxName);
//				BigDecimal existingDepreciation = recentDepreciation.getExpense();
//				recentDepreciation.setExpense(existingDepreciation.add(totalOldDepreciationAmt));
//				recentDepreciation.setExpense_F(existingDepreciation.add(totalOldDepreciationAmt));
//				recentDepreciation.saveEx();
//				final String sql = "UPDATE "+MDepreciationWorkfile.Table_Name +" SET A_Current_Period= "+recentDepreciation.getA_Period()
//						+" WHERE A_Asset_ID = ? ";
//				params = new Object[]{addition.getA_Asset_ID()};
//				no = DB.executeUpdateEx(sql, params, trxName);
//				log.info("Updated workfile to the correct depreciation "+no);
//			}
//		}
//		else
//			UpdateDepreciationifAssetPartiallyDepreciated(addition);
//
//	}
//	
//	private void UpdateDepreciationifAssetPartiallyDepreciated(MAssetAddition addition) 
//	{
//		int monthBetween = 0 ;
//		BigDecimal perMonthDepreciation = Env.ZERO;
//		BigDecimal totalOldDepreciationAmt = Env.ZERO;
//		BigDecimal totalDepreciation = Env.ZERO;
//		BigDecimal difference = Env.ZERO;
//		truncAllDepreciationUpdateFromBackupTable(addition);
//		
//		MDepreciationWorkfile workFile = getCurrentDepreciationWorkFile(addition.getA_Asset_ID(), trxName);
//		
//		Timestamp assetRecordedDate = TimeUtil.getMonthLastDay(addition.getDateAcct());
//		int totalProcessedEntries = DB.getSQLValue(trxName, "select count(*) from A_Depreciation_Exp where A_Asset_ID=? and dateacct < ? ",
//				addition.getA_Asset_ID(),assetRecordedDate);
//		
//		if (!workFile.isDepreciated())
//		{
//			return;
//		}
//		Timestamp nextDepreciationDate = workFile.getDateAcct();
//		Timestamp firstDepricationDate  = null;
//		Timestamp secondQuarterDepreciationDate = null;
//		DateTime dateAcctJT = new DateTime(nextDepreciationDate);
//		String currentYear = Integer.toString(dateAcctJT.getYear());
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Timestamp minDepreciationDate = null;
//		try 
//		{
//			minDepreciationDate = new Timestamp(dateFormat.parse("30/06/"+currentYear+"").getTime());
//			firstDepricationDate = new Timestamp(dateFormat.parse("01/01/"+currentYear+"").getTime());
//			secondQuarterDepreciationDate = new Timestamp(dateFormat.parse("01/07/"+currentYear+"").getTime());
//		}
//		catch (ParseException e) 
//		{
//			e.printStackTrace();
//		}
//		
//		MAssetGroupAcct groupAcct = MAssetGroupAcct.forA_Asset_Group_ID(ctx, addition.getA_Asset().getA_Asset_Group_ID(), workFile.getPostingType());
//		workFile.setUseLifeMonths_F(groupAcct.getUseLifeMonths_F());
//		workFile.setUseLifeMonths(groupAcct.getUseLifeMonths());
//		workFile.setUseLifeYears(groupAcct.getUseLifeYears());
//		workFile.setUseLifeYears_F(groupAcct.getUseLifeYears_F());
//		workFile.saveEx();
//
//		if(totalProcessedEntries>=6)
//		{
//			if(nextDepreciationDate.after(minDepreciationDate))
//			{
//				monthBetween = TimeUtil.getMonthsBetween(secondQuarterDepreciationDate, nextDepreciationDate);
//				totalProcessedEntries = totalProcessedEntries-monthBetween;
//				
//			}
//			else
//			{
//				monthBetween = TimeUtil.getMonthsBetween(firstDepricationDate,nextDepreciationDate);
//				totalProcessedEntries = totalProcessedEntries-monthBetween;
//			}
//				
//			
//			perMonthDepreciation = addition.getAssetValueAmt().divide(new BigDecimal((groupAcct.getUseLifeMonths()-totalProcessedEntries)),2,RoundingMode.HALF_EVEN);
//			totalOldDepreciationAmt = new BigDecimal((monthBetween+1)).multiply(perMonthDepreciation);
//			totalDepreciation = perMonthDepreciation.multiply(new BigDecimal((groupAcct.getUseLifeMonths()-totalProcessedEntries)));
//			difference = totalDepreciation.subtract(addition.getAssetValueAmt());
//			totalOldDepreciationAmt = totalOldDepreciationAmt.subtract(difference);
//		}
//		else
//		{
//			perMonthDepreciation = addition.getAssetValueAmt().divide(new BigDecimal(groupAcct.getUseLifeMonths()),2,RoundingMode.HALF_EVEN);
//			totalOldDepreciationAmt = new BigDecimal(totalProcessedEntries+1).multiply(perMonthDepreciation);
//		}
//		
//		List<MDepreciationExp> futureDepreciations = new Query(ctx, I_A_Depreciation_Exp.Table_Name, " A_Asset_ID = ? and DateAcct > ? and Processed='N' ", trxName).
//				setParameters(addition.getA_Asset_ID(),assetRecordedDate).
//				list();
//		for(MDepreciationExp expense : futureDepreciations)
//		{
//			expense.setProcessed(false);
//			expense.setExpense(expense.getExpense().add(perMonthDepreciation));
//			expense.setExpense_F(expense.getExpense_F().add(perMonthDepreciation));
//			expense.setHelp(expense.getHelp()+ " | " + expense.getExpense_F() + "|" + expense.getExpense_F());
//			expense.setA_Asset_Cost(expense.getA_Asset_Cost().add(addition.getAssetValueAmt()));
//			expense.saveEx();	
//		}
//		
//		int currentDepreciationExpense = DB.getSQLValue(trxName,"select A_Depreciation_Exp_ID from A_Depreciation_Exp where DateAcct>= ? and A_Asset_ID = ?  order by dateacct ", 
//										assetRecordedDate,addition.getA_Asset_ID());
//		System.out.println(addition.getA_Asset_ID());
//		MDepreciationExp recentDepreciation = new MDepreciationExp(ctx, currentDepreciationExpense, trxName);
//		BigDecimal existingDepreciation = recentDepreciation.getExpense();
//		recentDepreciation.setExpense(existingDepreciation.add(totalOldDepreciationAmt));
//		recentDepreciation.setExpense_F(existingDepreciation.add(totalOldDepreciationAmt));
//		recentDepreciation.saveEx();
//		
//		final String sql = "UPDATE "+MDepreciationWorkfile.Table_Name +" SET A_Current_Period= "+recentDepreciation.getA_Period()
//		+" WHERE A_Asset_ID = ? ";
//		Object[] params = new Object[]{addition.getA_Asset_ID()};
//		int no = DB.executeUpdateEx(sql, params, trxName);
//		log.info("Updated workfile to the correct depreciation "+no);
//	}
//
//	private void truncAllDepreciationUpdateFromBackupTable(MAssetAddition addition) {
//		
//		final String sql = "DELETE FROM "+MDepreciationExp.Table_Name
//				+" WHERE  "+MDepreciationExp.COLUMNNAME_A_Asset_ID+"=?";
//		Object[] params = new Object[]{addition.getA_Asset_ID()};
//		int no = DB.executeUpdateEx(sql, params, trxName);
//		if (log.isLoggable(Level.FINE)) log.fine("sql= " + sql + "\nDeleted #" + no);
//		
//		MoveDepreciationBackFromTempTable(addition);
//		
//	}
//
//	private void MoveDepreciationBackFromTempTable(MAssetAddition addition) 
//	{
//		List<MFILDepreciationExpT> allDepreciations = new Query(ctx, MFILDepreciationExpT.Table_Name, " A_Asset_ID = ? ", trxName).
//				setParameters(addition.getA_Asset_ID()).
//				list();
//		for(MFILDepreciationExpT exp : allDepreciations)
//		{
//			MDepreciationExp temp = new MDepreciationExp(ctx, 0, trxName);
//			temp.setA_Account_Number_Acct(exp.getA_Account_Number_Acct());
//			temp.setA_Accumulated_Depr(exp.getA_Accumulated_Depr());
//			temp.setA_Accumulated_Depr_Delta(exp.getA_Accumulated_Depr_Delta());
//			temp.setA_Accumulated_Depr_F(exp.getA_Accumulated_Depr_F());
//			temp.setA_Accumulated_Depr_F_Delta(exp.getA_Accumulated_Depr_F_Delta());
//			temp.setA_Asset_Addition_ID(exp.getA_Asset_Addition_ID());
//			temp.setA_Asset_Cost(exp.getA_Asset_Cost());
//			temp.setA_Asset_Cost_Delta(exp.getA_Asset_Cost_Delta());
//			temp.setA_Asset_Disposed_ID(exp.getA_Asset_Disposed_ID());
//			temp.setA_Asset_ID(exp.getA_Asset_ID());
//			temp.setA_Asset_Remaining(exp.getA_Asset_Remaining());
//			temp.setA_Asset_Remaining_F(exp.getA_Asset_Remaining_F());
//			temp.setA_Depreciation_Entry_ID(exp.getA_Depreciation_Entry_ID());
//			temp.setA_Entry_Type(exp.getA_Entry_Type());
//			temp.setA_Period(exp.getA_Period());
//			temp.set_ValueOfColumn("C_Activity_ID",exp.getC_Activity_ID());
//			temp.setCR_Account_ID(exp.getCR_Account_ID());
//			temp.setDateAcct(exp.getDateAcct());
//			temp.setDescription(exp.getDescription());
//			temp.setDR_Account_ID(exp.getDR_Account_ID());
//			temp.setExpense(exp.getExpense());
//			temp.setExpense_F(exp.getExpense_F());
//			temp.setHelp(exp.getHelp());
//			temp.setIsDepreciated(exp.isDepreciated());
//			temp.setPostingType(exp.getPostingType());
//			temp.setProcessed(exp.isProcessed());
//			temp.setProcessing(exp.isProcessing());
//			temp.setReplication(exp.isReplication());
//			temp.setUseLifeMonths(exp.getUseLifeMonths());
//			temp.setUseLifeMonths_F(exp.getUseLifeMonths_F());
//			temp.saveEx();
//		}
//	}
//
//	private void updateDepreciationsForLowValueAssets(MAssetAddition assetAdd) 
//	{
//		Object[] params = new Object[]{assetAdd.getA_Asset_ID()};
//		int no = 0 ; 
//		BigDecimal totalLowValueAssetAmt = Env.ZERO;
//		Timestamp assetRecordedDate = TimeUtil.getMonthLastDay(assetAdd.getDateAcct());
//		Timestamp firstDayOfTheDate = TimeUtil.getMonthFirstDay(assetAdd.getDateAcct());
//		int totalAssetAdditions = DB.getSQLValue(trxName, "Select count(*) From A_Asset_Addition Where A_Asset_ID = ?",assetAdd.getA_Asset_ID());
//		if(totalAssetAdditions>1)
//			totalLowValueAssetAmt = DB.getSQLValueBD(null, "Select coalesce(sum(AssetValueAmt),0) "
//													+ "From A_Asset_Addition "
//													+ "Where A_Asset_ID = ? and DateAcct between ? and ? and A_Asset_Addition_ID != ?  ", 
//													assetAdd.getA_Asset_ID(),firstDayOfTheDate,assetRecordedDate,assetAdd.getA_Asset_Addition_ID());
//		int currentDepreciationExpense = DB.getSQLValue(trxName,"select A_Depreciation_Exp_ID "
//				+ "from A_Depreciation_Exp where DateAcct= ? and A_Asset_ID = ? and Processed='N' order by dateacct ", 
//				assetRecordedDate,assetAdd.getA_Asset_ID());
//			
//		int periodNo = DB.getSQLValue(trxName, "Select coalesce(max(a_period),0) From A_Depreciation_Exp Where A_Asset_ID = ? ",assetAdd.getA_Asset_ID());
//		if(currentDepreciationExpense!=-1)
//		{
//			MDepreciationExp recentDepreciation = new MDepreciationExp(ctx, currentDepreciationExpense, trxName);			
//			recentDepreciation.setExpense(totalLowValueAssetAmt.add(assetAdd.getAssetValueAmt()));
//			recentDepreciation.setExpense_F(totalLowValueAssetAmt.add(assetAdd.getAssetValueAmt()));
//			recentDepreciation.saveEx();
//			final String sql = "UPDATE "+MDepreciationWorkfile.Table_Name +" SET A_Current_Period= "+recentDepreciation.getA_Period()
//			+" WHERE A_Asset_ID = ? ";
//			no = DB.executeUpdateEx(sql, params, trxName);
//			int deletedDepreciation = DB.executeUpdateEx("Delete From A_Depreciation_Exp Where A_Asset_ID = ? AND processed='N' and A_Depreciation_Exp_ID!= "+
//					currentDepreciationExpense,params ,trxName);
//			log.info("Updated workfile to the correct depreciation "+no+" Depreciations deleted : "+deletedDepreciation);
//		}	
//		else
//		{
//			int deletedDepreciation = DB.executeUpdateEx("Delete From A_Depreciation_Exp Where A_Asset_ID = ? AND processed='N' ",params,trxName);
//			log.info("Updated workfile to the correct depreciation "+no+" Depreciations deleted : "+deletedDepreciation);
//			MDepreciationExp expense = new MDepreciationExp(ctx, 0, trxName);
//			expense.setA_Asset_ID(assetAdd.getA_Asset_ID());
//			expense.setExpense(assetAdd.getAssetValueAmt());
//			expense.setExpense_F(assetAdd.getAssetValueAmt());
//			expense.setDateAcct(assetRecordedDate);
//			expense.setA_Entry_Type(X_A_Depreciation_Exp.A_ENTRY_TYPE_Depreciation);
//			expense.setPostingType(X_A_Depreciation_Exp.POSTINGTYPE_Actual);
//			expense.setIsDepreciated(false);
//			expense.setDescription(assetAdd.getAssetValueAmt()+" Date "+assetRecordedDate);
//			expense.setA_Period(periodNo+1);
//			expense.saveEx();
//		}	
//	}
//	
//	public MAsset createAssetFromInvoiceLine(MInvoiceLine line, int qtyCount, boolean qtyMoreThanOne) throws ParseException
//	{
//		MAsset asset = null;
//		asset = CreateAsset(line,qtyCount, qtyMoreThanOne);
//		
//		return asset;
//	}
//	public MAsset CreateAsset(MInvoiceLine line, int qtyCount, boolean qtyMoreThanOne)
//	{
//		int A_Asset_ID = 0;
//		
//		MAssetGroup group = new MAssetGroup(ctx, line.getA_Asset_Group_ID(), trxName);
//		boolean LowValueAsset = group.get_ValueAsBoolean("FIL_LowValueAsset");
//		if (line.getA_Asset_ID() != 0)
//		{
//			A_Asset_ID = line.getA_Asset_ID();	
//		}
//		MAsset asset = new MAsset(Env.getCtx(), A_Asset_ID, line.get_TrxName());
//		if(A_Asset_ID==0)
//		{
//			MBPartner bp = new MBPartner(Env.getCtx(), line.getC_Invoice().getC_BPartner_ID(), null);
//			String name = asset.getName()==null ? "":asset.getName();
//			String Description = line.getDescription()!=null ? (line.getDescription().length()>=25 ? 
//					line.getDescription().substring(0,25) : line.getDescription()) : "";
//
//			if(LowValueAsset && line.getA_Asset_ID()==0)
//			{
//				name = Description;
//			}
//			else
//			{
//				if(qtyMoreThanOne)
//					name = Description.concat(" - ").
//						concat(bp.getName().length()>=25 ? bp.getName().substring(0, 25) : bp.getName()) +" - "+qtyCount+"/"+line.getQtyEntered();
//				else
//					name = Description.concat(" - ").
//					concat(bp.getName().length()>=25 ? bp.getName().substring(0, 25) : bp.getName());
//				
//				asset.setDescription(line.getDescription());
//			}
//			asset.setName(name);
//			if (log.isLoggable(Level.FINE)) log.fine("name=" + name);
//		}
//		
//		
//		depreciationStartDate = getDepreciationDate(line.getC_Invoice().getDateAcct(),asset);
//		
//		asset.setM_Product_ID(line.getM_Product_ID());
//		asset.setIsOwned(true);
//		asset.setIsInPosession(true);
//		asset.setA_Asset_CreateDate(line.getC_Invoice().getDateAcct());
//		asset.setAssetServiceDate(line.getC_Invoice().getDateAcct());
//		asset.setAssetActivationDate(line.getC_Invoice().getDateAcct());
//		asset.setDateAcct(depreciationStartDate);
//		asset.setC_Activity_ID(line.getC_Activity_ID());
//		// Asset Group:
//		int A_Asset_Group_ID = line.getA_Asset_Group_ID();
//		
//		asset.setA_Asset_Group_ID(A_Asset_Group_ID);
//		asset.setHelp(Msg.getMsg(MClient.get(Env.getCtx()).getAD_Language(), "CreatedFromInvoiceLine", 
//				new Object[] {line.getC_Invoice().getDocumentNo(), line.getLine()}));
//		
//		
//		asset.setM_AttributeSetInstance_ID(line.getM_AttributeSetInstance_ID());
//		asset.saveEx();
//		return asset;
//	}
//
//	private Timestamp getDepreciationDate(Timestamp dateAcct,MAsset asset) 
//	{
//		boolean isCurrentInvoiceIsLatest = true;
//		Timestamp latestAssetAdditionDate = dateAcct;
//		Date firstDepricationDate  = null;
//		Date secondQuarterDepreciationDate = null;
//		long time;
//		Timestamp midDepreciationTimeStamp = null;
//		DateTime dateAcctJT = new DateTime(dateAcct);
//		String currentYear = Integer.toString(dateAcctJT.getYear());
//		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//		Date minDepreciationDate;
//		try {
//			minDepreciationDate = dateFormat.parse("30/06/"+currentYear+"");
//			time = minDepreciationDate.getTime();
//			midDepreciationTimeStamp = new Timestamp(time);
//			firstDepricationDate = dateFormat.parse("01/01/"+currentYear+"");
//			secondQuarterDepreciationDate = dateFormat.parse("01/07/"+currentYear+"");
//		} 
//		catch (ParseException e) 
//		{
//			e.printStackTrace();
//		}
//		if(asset.getA_Asset_ID()!=0)
//		{
//			isCurrentInvoiceIsLatest = asset.getAssetServiceDate().before(latestAssetAdditionDate);
//			if(!isCurrentInvoiceIsLatest)
//				latestAssetAdditionDate = asset.getAssetServiceDate();
//		}
//		boolean dateAfterPeriodMasterDays = latestAssetAdditionDate.before(midDepreciationTimeStamp);
//		if(!dateAfterPeriodMasterDays)
//			depreciationStartDate = new Timestamp(secondQuarterDepreciationDate.getTime());
//		else
//			depreciationStartDate = new Timestamp(firstDepricationDate.getTime());
//		
//		return depreciationStartDate;
//	}
//
//	public void buildDepreciation(MAssetAddition addition)
//	{
//
//		int A_DepreciationWorkFile_ID = DB.getSQLValue(trxName, "Select A_Depreciation_Workfile_ID From A_Depreciation_Workfile "
//				+ "Where A_Asset_ID = ? ",addition.getA_Asset_ID());
//		MDepreciationWorkfile workFile = new MDepreciationWorkfile(ctx,A_DepreciationWorkFile_ID,trxName);
//		
//		if (!workFile.isDepreciated())
//		{
//			return;
//		}
//		MAsset asset = new MAsset(ctx, addition.getA_Asset_ID(), trxName);
//		depreciationStartDate = getDepreciationDate(addition.getDateAcct(), asset);
//		
//		StringBuilder sb = new StringBuilder();
//		MAssetAcct assetacct = workFile.getA_AssetAcct(null, trxName); 
//		MAssetGroupAcct groupAcct = MAssetGroupAcct.forA_Asset_Group_ID(ctx, addition.getA_Asset().getA_Asset_Group_ID(), workFile.getPostingType());
//		workFile.setUseLifeMonths_F(groupAcct.getUseLifeMonths_F());
//		workFile.setUseLifeMonths(groupAcct.getUseLifeMonths());
//		workFile.setUseLifeYears(groupAcct.getUseLifeYears());
//		workFile.setUseLifeYears_F(groupAcct.getUseLifeYears_F());
//		workFile.setA_Current_Period(1);
//		workFile.saveEx();
//		
//		// TODO: teo_sarca: need to evaluate what happens when we change Depreciation method !!!
//		MDepreciation depreciation_C = MDepreciation.get(ctx, assetacct.getA_Depreciation_ID());
//		MDepreciation depreciation_F = MDepreciation.get(ctx, assetacct.getA_Depreciation_F_ID());
//		//~ int offset_C = depreciation_C.getFixMonthOffset();
//		//~ int offset_F = depreciation_F.getFixMonthOffset();
//		int offset_C = 0, offset_F = 0;
//		BigDecimal assetCost = workFile.getActualCost();
//		BigDecimal accumDep_C = workFile.getA_Accumulated_Depr(false);
//		BigDecimal accumDep_F = workFile.getA_Accumulated_Depr(true);
//		int lifePeriods_C = workFile.getUseLifeMonths(false) + offset_C;
//		int lifePeriods_F = workFile.getUseLifeMonths(true) + offset_F;
//		int lifePeriods = (lifePeriods_C > lifePeriods_F ? lifePeriods_C : lifePeriods_F);
//		BigDecimal exp_C = Env.ZERO;
//		BigDecimal exp_F = Env.ZERO;
//		
//		//logging
//		if(CLogMgt.isLevelFine())
//		{
//			sb.append("currentPeriod=" + workFile.getA_Current_Period() + ", AssetServiceDate=" + workFile.getAssetDepreciationDate() + "\n");
//			sb.append("offset: C|F=" + offset_C + "|" + offset_F + "\n");
//			sb.append("life: C|F=" + lifePeriods_C + "|" + lifePeriods_F + " + offset =" + lifePeriods + "\n");
//		}
//		
//		int A_Current_Period = workFile.getA_Current_Period();
//		for (int currentPeriod = A_Current_Period, cnt = 1; currentPeriod <= lifePeriods; currentPeriod++, cnt++)
//		{
//			exp_C = Env.ZERO;
//			exp_F = Env.ZERO;
////			if(!checkIfExists(currentPeriod,addition.getA_Asset_ID()))
////			{
//				String help = "" + accumDep_C + "|" + accumDep_F + " + ";
//				
//				if (lifePeriods_C > currentPeriod || !depreciation_C.requireLastPeriodAdjustment())
//				{
//					workFile.setFiscal(false);
//					exp_C = depreciation_C.invoke(workFile, assetacct, currentPeriod, accumDep_C);
//					accumDep_C = accumDep_C.add(exp_C);
//				}
//				else if (lifePeriods_C == currentPeriod)
//				{	// last period
//					exp_C = assetCost.subtract(accumDep_C);
//					accumDep_C = assetCost;
//				}
//				
//				if (lifePeriods_F > currentPeriod || !depreciation_F.requireLastPeriodAdjustment())
//				{
//					workFile.setFiscal(true);
//					exp_F = depreciation_F.invoke(workFile, assetacct, currentPeriod, accumDep_F);
//					accumDep_F = accumDep_F.add(exp_F);
//				}
//				else if (lifePeriods_F == currentPeriod)
//				{	// last period (fiscal)
//					exp_F = assetCost.subtract(accumDep_F);
//					accumDep_F = assetCost;
//				}
//				
//				help += "" + exp_C + "|" + exp_F + " = " + accumDep_C + "|" + accumDep_F;
//				
//				// added by zuhri
//				int months = 0;
//				
//				months = months + (currentPeriod - A_Current_Period);
//				Timestamp dateAcct = TimeUtil.getMonthLastDay(TimeUtil.addMonths(depreciationStartDate, months));
//				
//				MDepreciationExp.createDepreciation (workFile, currentPeriod, dateAcct,
//														exp_C, exp_F,
//														accumDep_C, accumDep_F,
//														help, trxName);
//				if (log.isLoggable(Level.FINE)) 
//				{
//					String info = "" + cnt + ": period=" + currentPeriod + "/" + lifePeriods_C + "|" + lifePeriods_F
//						+ ", exp=" + exp_C + "|" + exp_F + ", accumDep=" + accumDep_C + "|" + accumDep_F
//						+ ", DateAcct=" + dateAcct;
//					log.fine("=> " + info + Env.NL + Env.NL);
//					sb.append(info + Env.NL);
//				}
////			}
//		} // for
//		if (log.isLoggable(Level.FINE)) log.fine(sb.toString());
//		
//	}	//	buildDepreciation
//
////	private boolean checkIfExists(int currentPeriod, int A_Asset_ID) 
////	{
////		boolean exist = false;
////		int count = DB.getSQLValue(trxName, "SELECT Count(*) From A_Depreciation_Exp Where A_Asset_ID = ? "
////				+ "AND A_Depreciation_Entry_ID IS NULL AND Processed='Y' AND A_Period = ? ",A_Asset_ID,currentPeriod);
////		if(count>0)
////			exist = true;
////		
////		return exist;
////	}
//}
