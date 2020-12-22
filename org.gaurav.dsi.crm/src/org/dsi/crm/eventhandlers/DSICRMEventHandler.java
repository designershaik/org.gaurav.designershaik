package org.dsi.crm.eventhandlers;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.adempiere.base.Core;
import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAttributeSetInstance;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MMovement;
import org.compiere.model.MMovementLine;
import org.compiere.model.MOrder;
import org.compiere.model.MOrderLine;
import org.compiere.model.MPInstance;
import org.compiere.model.MProcess;
import org.compiere.model.MRequest;
import org.compiere.model.MStorageOnHand;
import org.compiere.model.MSysConfig;
import org.compiere.model.MUser;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.model.X_AD_Process;
import org.compiere.model.X_C_BPartner;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.I_DSI_SerialNoTrx;
import org.gaurav.dsi.model.I_DS_ContactMaster_Location;
import org.gaurav.dsi.model.MCContactActivity;
import org.gaurav.dsi.model.MDSContactMaster;
import org.gaurav.dsi.model.MDSContactMasterLocation;
import org.gaurav.dsi.model.MDSDeliveryTrips;
import org.gaurav.dsi.model.MDSDocumentsForTrip;
import org.gaurav.dsi.model.MDSISerialNoTrx;
import org.gaurav.dsi.model.MDSOrgContactRelation;
import org.gaurav.dsi.model.MDSOrgMaster;
import org.gaurav.dsi.model.MDSOrgMasterLocation;
import org.gaurav.dsi.model.MDSPOSHeader;
import org.gaurav.dsi.model.MDSPOSItemDetails;
import org.gaurav.dsi.model.MDSProductPriceSerialNo;
import org.gaurav.dsi.model.MDSRequiredDocForShipment;
import org.gaurav.dsi.model.MDSRequiredShipDocuments;
import org.gaurav.dsi.model.MDSTripExpenseType;
import org.gaurav.dsi.model.MDSTripSchdCashReqstAmt;
import org.gaurav.dsi.model.MDSTripScheduleEstimations;
import org.osgi.service.event.Event;

public class DSICRMEventHandler extends AbstractEventHandler{

	CLogger log = CLogger.getCLogger(DSICRMEventHandler.class);
	Properties ctx = null;
	String trxName = null;
	@Override
	protected void doHandleEvent(Event event) 
	{
		PO po = getPO(event);
		ctx = po.getCtx();
		trxName = po.get_TrxName();
		int AD_User_ID = Env.getAD_User_ID(Env.getCtx());
		MUser loggedInUser = new MUser(Env.getCtx(), AD_User_ID, po.get_TrxName());
		if(po instanceof MBPartner )
		{
			MBPartner bpartner = (MBPartner)po;
			int bpartnerID = bpartner.getC_BPartner_ID();
			int DS_OrgMaster_ID = bpartner.get_ValueAsInt("DS_OrgMaster_ID");
			int DS_Contact_ID = bpartner.get_ValueAsInt("DS_ContactMaster_ID");
			if(event.getTopic().equals(IEventTopics.PO_AFTER_NEW))
			{
				if(bpartner.get_ValueAsInt("DS_OrgMaster_ID")!=0)
				{
					MDSOrgMaster orgMaster=new MDSOrgMaster(ctx,DS_OrgMaster_ID, trxName);
					List<MDSOrgMasterLocation> orgLocations = new Query(ctx, MDSOrgMasterLocation.Table_Name, " DS_OrgMaster_ID = ? ", trxName)
																.setParameters(DS_OrgMaster_ID)
																.list();
					orgMaster.setC_BPartner_ID(bpartnerID);
					orgMaster.saveEx();
					for(MDSOrgMasterLocation location:orgLocations)
					{
						MBPartnerLocation loc=new MBPartnerLocation(ctx,0, trxName);
						loc.setC_Location_ID(location.getC_Location_ID());
						loc.setName(location.getName());
						loc.setC_BPartner_ID(bpartnerID);
						loc.setPhone(location.getPhone());
						loc.setPhone2(location.getPhone2());
						loc.setFax(location.getFax());
						loc.save();
						location.setC_BPartner_Location_ID(loc.getC_BPartner_Location_ID());
						location.saveEx();
					}
					List<MDSOrgContactRelation> relatedContacts = new Query(ctx, MDSOrgContactRelation.Table_Name, " DS_OrgMaster_ID = ? ", trxName)
							.setParameters(DS_OrgMaster_ID)
							.list();
					for(MDSOrgContactRelation related: relatedContacts)
					{
						int contactMasterID=related.getDS_ContactMaster_ID();
						
						MDSContactMaster contact=new MDSContactMaster(ctx, contactMasterID, trxName);
						AD_User_ID = DB.getSQLValue(trxName,"Select AD_User_ID From AD_user Where DS_ContactMaster_ID = ? ",contactMasterID);
						if(AD_User_ID<=0)
						{
							MUser user=new MUser(ctx, 0, trxName);
							user.set_ValueOfColumn("DS_ContactMaster_ID", contactMasterID);
							user.setC_BPartner_ID(bpartnerID);
							user.setName(contact.getName());
							user.setPhone(contact.getPhone());
							user.setPhone2(contact.getPhone2());
							user.setEMail(contact.getEMail());
							user.setBirthday(contact.getBirthday());
							user.setTitle(contact.getTitle());
							user.setFax(contact.getFax());
							int C_BPartner_Location_ID = DB.getSQLValue(trxName, "Select C_BPartner_Location_ID From DS_OrgMaster_Location Where DS_OrgMaster_Location_ID =? ",related.getDS_OrgMaster_Location_ID());
							user.setC_BPartner_Location_ID(C_BPartner_Location_ID);
							user.save();
							contact.setAD_User_ID(user.getAD_User_ID());
							contact.save(trxName);
						}
					}
				}
				
				if(bpartner.get_ValueAsInt("DS_ContactMaster_ID")!=0)
				{
					MDSContactMaster contactMaster=new MDSContactMaster(ctx,DS_Contact_ID, trxName);
					contactMaster.setC_BPartner_ID(bpartnerID);
					contactMaster.saveEx();
					List<MDSContactMasterLocation> contLocations = new Query(Env.getCtx(), I_DS_ContactMaster_Location.Table_Name, "DS_ContactMaster_ID=?", trxName)
					.setParameters(DS_Contact_ID)
					.list();
					for(MDSContactMasterLocation location:contLocations)
					{
						MBPartnerLocation loc=new MBPartnerLocation(ctx,0, trxName);
						loc.setC_Location_ID(location.getC_Location_ID());
						loc.setName(location.getName());
						loc.setC_BPartner_ID(bpartnerID);
						loc.setPhone(location.getPhone());
						loc.setPhone2(location.getPhone2());
						loc.setFax(location.getFax());
						loc.save();
						location.setC_BPartner_Location_ID(loc.getC_BPartner_Location_ID());
						location.save(trxName);
							
						MUser user=new MUser(ctx, 0, trxName);
						user.set_ValueOfColumn("DS_ContactMaster_ID", DS_Contact_ID);
						user.setC_BPartner_ID(bpartnerID);
						user.setC_BPartner_Location_ID(loc.getC_BPartner_Location_ID());
						user.setName(contactMaster.getName());
						user.setPhone(contactMaster.getPhone());
						user.setPhone2(contactMaster.getPhone2());
						user.setEMail(contactMaster.getEMail());
						user.setBirthday(contactMaster.getBirthday());
						user.setTitle(contactMaster.getTitle());
						user.setFax(contactMaster.getFax());
						user.save();
						contactMaster.setAD_User_ID(user.getAD_User_ID());
						contactMaster.save(trxName);
					}
				}
				updateBPartnerInTheRightFolderWithSequence(bpartner);
			}
			if(event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE))
			{
				if(bpartner.get_ValueAsInt("DS_ContactMaster_ID")!=0)
				{
					MDSContactMaster contactmaster=new MDSContactMaster(ctx, DS_Contact_ID, trxName);
					if(po.is_ValueChanged("Name"))
						contactmaster.setName(bpartner.getName());
					if(po.is_ValueChanged("Description"))
						contactmaster.setDescription(bpartner.getDescription());
					if(po.is_ValueChanged("Value"))
						contactmaster.setValue(bpartner.getValue());
					if(po.is_ValueChanged("WebSite"))
						contactmaster.setWebSite(bpartner.getURL());
					contactmaster.save(trxName);
				}
				if(bpartner.get_ValueAsInt("DS_OrgMaster_ID")!=0)
				{
					MDSOrgMaster orgMaster=new MDSOrgMaster(ctx, DS_OrgMaster_ID, trxName);
					if(po.is_ValueChanged("Name"))
						orgMaster.setName(bpartner.getName());
					if(po.is_ValueChanged("Description"))
						orgMaster.setDescription(bpartner.getDescription());
					if(po.is_ValueChanged("Value"))
						orgMaster.setValue(bpartner.getValue());
					if(po.is_ValueChanged("WebSite"))
						orgMaster.setWebSite(bpartner.getURL());	
					orgMaster.save(trxName);
				} 
				if(bpartner.is_ValueChanged(X_C_BPartner.COLUMNNAME_C_BP_Group_ID))
					updateBPartnerInTheRightFolderWithSequence(bpartner);
			}			
		}
		if(po instanceof MBPartnerLocation && po.get_ValueAsBoolean("IsManual"))
		{
			int C_BPartner_Location_ID=po.get_ID();
			MBPartnerLocation loc=new MBPartnerLocation(ctx, C_BPartner_Location_ID, trxName);
			
			int contactLocationID=DB.getSQLValue(trxName, "select DS_ContactMaster_Location_ID  "
					+ "from DS_ContactMaster_Location where c_bpartner_location_id=?",C_BPartner_Location_ID);
			
			int orgLocationID=DB.getSQLValue(trxName, "select DS_OrgMaster_Location_ID "
					+ "from DS_OrgMaster_Location where c_bpartner_location_id=?",C_BPartner_Location_ID);
			if(contactLocationID!=-1 && orgLocationID==-1)
			{
					MDSContactMasterLocation contactloc=new MDSContactMasterLocation(ctx, contactLocationID, trxName);
					if(po.is_ValueChanged("Phone"))
						contactloc.setPhone(loc.getPhone());
					if(po.is_ValueChanged("Phone2"))
						contactloc.setPhone2(loc.getPhone2());
					if(po.is_ValueChanged("Fax"))
						contactloc.setFax(loc.getFax());
					if(po.is_ValueChanged("DS_Phone_Ext1"))
						contactloc.setDS_Phone_Ext1(loc.get_ValueAsString("DS_Phone_Ext1"));
					if(po.is_ValueChanged("DS_Phone_Ext2"))
						contactloc.setDS_Phone_Ext2(loc.get_ValueAsString("DS_Phone_Ext2"));
					if(po.is_ValueChanged("C_SalesRegion_ID"))
						contactloc.setC_SalesRegion_ID(loc.getC_SalesRegion_ID());
					if(po.is_ValueChanged("IsActive"))
						contactloc.setIsActive(loc.isActive());
					if(po.is_ValueChanged("Name"))
						contactloc.setName(loc.getName());
					
					contactloc.save(trxName);
			}
			if(orgLocationID!=-1 && contactLocationID==-1)
			{
				MDSOrgMasterLocation orgLoc=new MDSOrgMasterLocation(ctx, orgLocationID, trxName);
				if(po.is_ValueChanged("Phone"))
					orgLoc.setPhone(loc.getPhone());
				if(po.is_ValueChanged("Phone2"))
					orgLoc.setPhone2(loc.getPhone2());
				if(po.is_ValueChanged("Fax"))
					orgLoc.setFax(loc.getFax());
				if(po.is_ValueChanged("DS_Phone_Ext1"))
					orgLoc.setDS_Phone_Ext1(loc.get_ValueAsString("DS_Phone_Ext1"));
				if(po.is_ValueChanged("DS_Phone_Ext2"))
					orgLoc.setDS_Phone_Ext2(loc.get_ValueAsString("DS_Phone_Ext2"));
				if(po.is_ValueChanged("C_SalesRegion_ID"))
					orgLoc.setC_SalesRegion_ID(loc.getC_SalesRegion_ID());
				if(po.is_ValueChanged("IsActive"))
					orgLoc.setIsActive(loc.isActive());
				if(po.is_ValueChanged("Name"))
						orgLoc.setName(loc.getName());
				
				orgLoc.save(trxName);
			}
		}
		if(po instanceof MInOut && po.get_ValueAsBoolean("IsSoTrx"))
		{
			MInOut inout = (MInOut)po;
			if(event.getTopic().equals(IEventTopics.PO_AFTER_NEW))
			{
				int C_Order_ID = inout.getC_Order_ID();
				if(C_Order_ID>0)
				{
					MOrder order = new MOrder(ctx, C_Order_ID, trxName);
					if(order.isDelivered())
						throw new AdempiereException("This sales order is already shipped.");
					
					inout.set_ValueOfColumn("DatePromised", order.getDatePromised());
					inout.saveEx();
				}
			}
			if(event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE))
			{
				PreparedStatement ps = null;
				ResultSet rs = null;
				MOrder order = new MOrder(ctx, inout.getC_Order_ID(), trxName);
				if(inout.getC_Order_ID()!=0)
				{
					try 
					{
					String query = "SELECT SUM(QtyOrdered-QtyDelivered) FROM C_OrderLine WHERE C_Order_ID=?";
					ps = DB.prepareStatement(query, trxName);
					ps.setInt(1, inout.getC_Order_ID());
					rs = ps.executeQuery();
					
						if (rs.next()) {
							int delta = rs.getInt(1);
							if (delta==0) {
								order.setIsDelivered(true);
								order.saveEx();
							}
						}
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					}
					finally
					{
						DB.close(rs, ps);
						ps=null;
						rs=null;
					}
				}
			}
			if(event.getTopic().equals(IEventTopics.DOC_BEFORE_PREPARE))
			{
				int reversal = inout.getReversal_ID();
				String checkSerialNumberForShipment = MSysConfig.getValue("DS_CheckSerialNumber", "yes");
				if(reversal==0)
				{
					if(checkSerialNumberForShipment.equals("yes"))
					{
						MInOutLine[] lines = inout.getLines();
						for(MInOutLine line : lines)
						{
							int count = DB.getSQLValue(inout.get_TrxName(), "select count(*) from DSI_SerialNoTrx where M_Product_ID = ? and M_Locator_ID = ? ",line.getM_Product_ID(),line.getM_Locator_ID());
							if(count>0)
							{
								List<MDSISerialNoTrx> list = new Query(ctx, I_DSI_SerialNoTrx.Table_Name, "M_InOutLine_ID = ? ", trxName).
									setParameters(line.getM_InOutLine_ID()).list();
								if(list.size()!=line.getQtyEntered().intValue())
								{
								       generateSerialNumbers(po);							        
								}	
							}
						}
					}
				}
				if(reversal>0)
				{
					deleteSerialNumbers(po, reversal);
				}
			}
		}
		if(po instanceof MMovement)
		{
			MMovement movement = (MMovement)po;
			int reversal = movement.getReversal_ID();
			String checkSerialNumberForShipment = MSysConfig.getValue("DS_CheckSerialNumber", "yes");
			if(reversal==0)
			{
				if(checkSerialNumberForShipment.equals("yes"))
				{
					MMovementLine[] lines = movement.getLines(false);
					for(MMovementLine line : lines)
					{
						int count = DB.getSQLValue(movement.get_TrxName(), "select count(*) from DSI_SerialNoTrx where M_Product_ID = ? "
								+ "and M_Locator_ID = ? ",line.getM_Product_ID(),line.getM_Locator_ID());
						if(count>0)
						{
							List<MDSISerialNoTrx> list = new Query(ctx, I_DSI_SerialNoTrx.Table_Name, " M_MovementLine_ID = ? ", movement.get_TrxName()).
								setParameters(line.getM_MovementLine_ID()).list();
							if(list.size()!=line.getMovementQty().intValue())
							{
								generateSerialNumbers(po);
							}
						}
					}
				}
			}
			if(reversal>0)
			{
				deleteSerialNumbers(po, reversal);
			}
		}
		if(po instanceof MOrder )
		{
			MOrder order = (MOrder)po;
			if(order.isSOTrx() && (event.getTopic().equals(IEventTopics.PO_BEFORE_NEW) || 
					(event.getTopic().equals(IEventTopics.PO_BEFORE_CHANGE)&& order.is_ValueChanged(MOrder.COLUMNNAME_POReference))))
			{
				verifyIfThePOReferenceAlreadyExists(order);
			}
			if(order.isSOTrx())
			{
				if(event.getTopic().equals(IEventTopics.PO_AFTER_NEW))
					updateShipmentDocuments(order);
				
				if(event.getTopic().equals(IEventTopics.PO_AFTER_CHANGE))
				{
					if(order.is_ValueChanged(MOrder.COLUMNNAME_C_BPartner_Location_ID) || order.is_ValueChanged(MOrder.COLUMNNAME_M_FreightCategory_ID))
					{
						DB.executeUpdate("Delete from DS_RequiredDocForShipment Where C_Order_ID ="+order.getC_Order_ID(), trxName);
						updateShipmentDocuments(order);
					}
				}
			}
			if(!order.isSOTrx() && event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE))
			{
				MOrderLine[] lines = order.getLines();
				for(MOrderLine line : lines)
				{
					int RequestID = line.get_ValueAsInt("R_Request_ID");
					if(RequestID>0)
					{
						MRequest request = new MRequest(ctx, RequestID, trxName);
						int finalCloseStatus = DB.getSQLValue(trxName,"select stat.R_Status_ID "
								+ "from R_RequestType type, R_Status stat, R_StatusCategory cat "
								+ "where type.R_StatusCategory_ID = cat.R_StatusCategory_ID and cat.R_StatusCategory_ID=stat.R_StatusCategory_ID "
								+ "and type.R_RequestType_ID= ? and stat.IsWebCanUpdate='Y' and stat.IsFinalClose='Y' and stat.IsClosed='Y' ", request.getR_RequestType_ID());
						request.setR_Status_ID(finalCloseStatus);
						request.setResult("Closed by "+loggedInUser.getName()+" automatically after PO");
						request.save();
					}
				}
			}
		}
		if(po instanceof MInvoice)
		{
			MInvoice invoice = (MInvoice)po;
			if(!invoice.isSOTrx() && event.getTopic().equals(IEventTopics.DOC_AFTER_COMPLETE))
			{
				MInvoiceLine[] lines = invoice.getLines();
				for(MInvoiceLine line : lines)
				{
					int RequestID = line.get_ValueAsInt("R_Request_ID");
					if(RequestID>0)
					{
						MRequest request = new MRequest(ctx, RequestID, trxName);
						int finalCloseStatus = DB.getSQLValue(trxName,"select stat.R_Status_ID "
								+ "from R_RequestType type, R_Status stat, R_StatusCategory cat "
								+ "where type.R_StatusCategory_ID = cat.R_StatusCategory_ID and cat.R_StatusCategory_ID=stat.R_StatusCategory_ID "
								+ "and type.R_RequestType_ID= ? and stat.IsWebCanUpdate='Y' and stat.IsFinalClose='Y' and stat.IsClosed='Y' ", request.getR_RequestType_ID());
						request.setR_Status_ID(finalCloseStatus);
						request.setResult("Closed by "+loggedInUser.getName()+" Automatically after Invoice");
						request.save();
					}
				}
			}
		}
		if(po instanceof MDSDeliveryTrips)
		{
			MDSDeliveryTrips trip = (MDSDeliveryTrips)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW))
			{
				String sqlWhere = "";
				if(trip.getM_Product_ID()>0)
					sqlWhere = " and M_Product_ID = "+trip.getM_Product_ID();
				
				int count = DB.getSQLValue(trxName, "Select count(*) From DS_Delivery_Trips "
						+ "Where ? between DS_TripDateFrom and DS_TripDateTo and C_BPartner_ID = ?  "+sqlWhere, 
						trip.getDS_TripDateFrom(),trip.getC_BPartner_ID());
				
				if(count>=1)
					throw new AdempiereException(Msg.getMsg(ctx, "DS_Trip_AlreadyScheduled"));
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW))
			{
				if(trip.getDS_TripDateFrom().after(trip.getDS_TripDateTo()))
					throw new AdempiereException("Dates are not in sync");
				List<MDSTripExpenseType> relevantExpenses = new Query(ctx, MDSTripExpenseType.Table_Name, " M_FreightCategory_ID = ? ", trxName).setParameters(trip.getM_FreightCategory_ID()).list();
				for(MDSTripExpenseType expense : relevantExpenses)
				{
					MDSTripScheduleEstimations estimate = new MDSTripScheduleEstimations(ctx, 0, trxName);
					estimate.setC_Currency_ID(trip.getC_Currency_ID());
					estimate.setDS_Trip_ExpenseType_ID(expense.getDS_Trip_ExpenseType_ID());
					estimate.setDS_Delivery_Trips_ID(trip.getDS_Delivery_Trips_ID());
					estimate.saveEx();
				}
				updateDocuments(trip);
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE) && po.is_ValueChanged(MDSDeliveryTrips.COLUMNNAME_DS_ToCountry_ID))
			{
				DB.executeUpdate("Delete from DS_DocumentsForTrip Where DS_Delivery_Trips_ID ="+trip.getDS_Delivery_Trips_ID(), trxName);
				updateDocuments(trip);
			}
		}
		if(po instanceof MDSTripScheduleEstimations)
		{
			MDSTripScheduleEstimations estimate = (MDSTripScheduleEstimations)po;
			if(estimate.getDS_Trip_ExpenseType().isMandatory() && estimate.getDS_Budget().intValue()==0)
				throw new AdempiereException(Msg.getMsg(Env.getCtx(), "DS_BudgetAmtIsMandatory"));
			
			BigDecimal totalCashAmt = DB.getSQLValueBD(trxName, "Select coalesce(sum(coalesce(DS_Budget,0)),0) from DS_TripSchedule_Estimations where DS_Delivery_Trips_ID = ? "
						+ "and C_Currency_ID = ? and PaymentRule = 'CS' ", estimate.getDS_Delivery_Trips_ID(),estimate.getC_Currency_ID());
				
			int DS_TripSchd_CashReqstAmt_ID  = DB.getSQLValue(trxName, "Select DS_TripSchd_CashReqstAmt_ID From DS_TripSchd_CashReqstAmt Where DS_Delivery_Trips_ID = ? and C_Currency_ID = ? ", 
					estimate.getDS_Delivery_Trips_ID(),estimate.getC_Currency_ID());
			DS_TripSchd_CashReqstAmt_ID = DS_TripSchd_CashReqstAmt_ID == -1 ? 0:DS_TripSchd_CashReqstAmt_ID;
				
			MDSTripSchdCashReqstAmt cashAmt = new MDSTripSchdCashReqstAmt(ctx, DS_TripSchd_CashReqstAmt_ID, trxName);
			cashAmt.setDS_CashRequest_Amt(totalCashAmt);
			cashAmt.setDS_Delivery_Trips_ID(estimate.getDS_Delivery_Trips_ID());
			cashAmt.setC_Currency_ID(estimate.getC_Currency_ID());
			cashAmt.saveEx();
		}
		
//		if(po instanceof MDSISerialNoTrx)
//		{
//			MDSISerialNoTrx trx = (MDSISerialNoTrx)po;
//			if(trx.getPP_Order_ID()!=0 && trx.getQty().compareTo(Env.ZERO)<0)
//			{
//				if(verifyIfSerialNumberExists(trx)>0);
//					throw new AdempiereException("Serial Number does not exist");
//			}
//		}
		
		if(po instanceof MOrderLine)
		{
			MOrderLine oLine = (MOrderLine)po;
			if(oLine.getC_Order().isSOTrx())
			{
				if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_NEW) ||
						(event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE) && oLine.is_ValueChanged(MOrderLine.COLUMNNAME_M_AttributeSetInstance_ID) && 
								!oLine.is_ValueChanged(MOrderLine.COLUMNNAME_PriceEntered)))
				{
					if(oLine.getM_AttributeSetInstance_ID()>0)
					{
						MAttributeSetInstance asi = new MAttributeSetInstance(ctx, oLine.getM_AttributeSetInstance_ID(), trxName);
						if(asi.getM_AttributeSet().isSerNo())
						{
							String serialNumber = asi.getSerNo();
							if(asi.getSerNo().contains("/"))
								serialNumber = asi.getSerNo().substring(0,asi.getSerNo().indexOf("/"));
							
							if(serialNumber!=null)
							{
								int srNo = Integer.parseInt(serialNumber);
								String sql = "SELECT plv.M_PriceList_Version_ID "
										+ "FROM M_PriceList_Version plv "
										+ "WHERE plv.M_PriceList_ID=? "						//	1
										+ " AND plv.ValidFrom <= ? "
										+ "ORDER BY plv.ValidFrom DESC";
									//	Use newest price list - may not be future
		
								int	M_PriceList_Version_ID = DB.getSQLValueEx(trxName, sql, oLine.getC_Order().getM_PriceList_ID(), oLine.getDateOrdered());
								
								int DS_ProductPriceSerialNo_ID = DB.getSQLValue(trxName, "Select DS_ProductPriceSerialNo_ID "
										+ "From DS_ProductPriceSerialNo Where M_PriceList_Version_ID = ? and M_Product_ID = ?  and ? between DS_SerNoFrom and DS_SerNoTo ", 
										M_PriceList_Version_ID,oLine.getM_Product_ID(),srNo);
								if(DS_ProductPriceSerialNo_ID>0)
								{
									MDSProductPriceSerialNo breakPrice = new MDSProductPriceSerialNo(ctx, DS_ProductPriceSerialNo_ID, trxName);
									oLine.setPriceEntered(breakPrice.getPriceStd());
									oLine.setPriceList(breakPrice.getPriceList());
									oLine.setPriceActual(breakPrice.getPriceStd());
									oLine.setPriceLimit(breakPrice.getPriceLimit());
									oLine.saveEx();
								}
							}
						}
					}
				}
				if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW) ||(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_CHANGE)))
				{
					if(oLine.getC_Charge_ID()>0)
					{
						int count = DB.getSQLValue(trxName, "Select count(inf.*) From AD_ClientInfo inf where inf.C_ChargeFreight_ID=? and inf.AD_Client_ID = ? ",oLine.getC_Charge_ID(),oLine.getAD_Client_ID());
						if(count>0)
						{
							int C_OrderLine_ID = DB.getSQLValue(trxName, "Select C_OrderLine_ID From C_OrderLine Where C_Order_ID = ? "
									+ "AND C_Activity_ID IS NOT NULL AND User1_ID IS NOT NULL AND User2_ID  IS NOT NULL",oLine.getC_Order_ID());
							MOrderLine line = new MOrderLine(ctx, C_OrderLine_ID, trxName);
							oLine.setC_Activity_ID(line.getC_Activity_ID());
							oLine.setUser1_ID(line.getUser1_ID());
							oLine.setUser2_ID(line.getUser2_ID());
						}
					}
				}
			}
		}
		
		if(po instanceof MCContactActivity)
		{
//			public static final String CONTACTACTIVITYTYPE_Email = "EM";
//			/** Phone call = PC */
//			public static final String CONTACTACTIVITYTYPE_PhoneCall = "PC";
//			/** Meeting = ME */
//			public static final String CONTACTACTIVITYTYPE_Meeting = "ME";
//			/** Task = TA */
//			public static final String CONTACTACTIVITYTYPE_Task = "TA";
			MCContactActivity ca = (MCContactActivity)po;
			String comment = ca.getComments();
			String summary = ca.getDescription();
			
			comment = comment == null ? "":comment.concat("\n");
			summary = summary == null ? "":summary.concat("\n");
			
			String activityType = ""; 
			if(ca.getContactActivityType().equalsIgnoreCase("EM"))
				activityType = "Email";
			if(ca.getContactActivityType().equalsIgnoreCase("PC"))
				activityType = "PhoneCall";
			if(ca.getContactActivityType().equalsIgnoreCase("ME"))
				activityType = "Meeting";
			if(ca.getContactActivityType().equalsIgnoreCase("TA"))
				activityType = "Task";
			
			String lastActivity = "Created Entry : ".concat(ca.getCreated().toString()).concat("\n")
					.concat(activityType).concat("\n")
					.concat(summary)
					.concat(comment);
			
			if(ca.getAD_User_ID()>0)
			{
				MUser user = new MUser(ctx, ca.getAD_User_ID(), trxName);
				user.set_ValueOfColumn("DS_LeadActivitiesLastResult", lastActivity);
				user.saveEx();
			}
			if(ca.get_ValueAsInt("C_BPartner_ID")>0)
			{
				MBPartner bpartner = new MBPartner(ctx, ca.get_ValueAsInt("C_BPartner_ID"), trxName);
				bpartner.set_ValueOfColumn("LastResult", lastActivity);
				bpartner.saveEx();
			}
		}
		if(po instanceof MUser)
		{
			MUser user = (MUser)po;
			if(user.isSalesLead())
			{
				if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW) || (event.getTopic().equalsIgnoreCase(IEventTopics.PO_AFTER_CHANGE) && user.is_ValueChanged("DSI_IsConverted")))
				{
					if(user.get_ValueAsBoolean("DSI_IsConverted"))
					{
						user.setIsSalesLead(false);
						user.setLeadStatus(MUser.LEADSTATUS_Converted);
						user.saveEx();
					}
				}
			}
		}
		if(po instanceof MDSPOSItemDetails)
		{
			MDSPOSItemDetails det = (MDSPOSItemDetails)po;
			MDSPOSHeader header = (MDSPOSHeader)det.getDS_POSHeader();
			int M_Warehouse_ID = header.get_ValueAsInt("M_Warehouse_ID");
			BigDecimal qtyOrdered = det.getQtyOrdered();
			BigDecimal qtyOnHand = MStorageOnHand.getQtyOnHand(det.getM_Product_ID(), M_Warehouse_ID, 0, trxName);
			if(qtyOrdered.compareTo(qtyOnHand)>0)
				throw new AdempiereException(Msg.getMsg(ctx, "NotEnoughStocked"));
		}
	}

//	private int verifyIfSerialNumberExists(MDSISerialNoTrx trx) 
//	{
//		int count = DB.getSQLValue(trxName, "Select count(*) From dsi_available_srno_v "
//				+ "Where M_Product_ID = ? and M_Locator_ID = ? and DS_SerialNumberFinal = ? ", trx.getM_Product_ID(),trx.getM_Locator_ID(),trx.getDS_SerialNumberFinal());
//		return count;	
//	}

	private void verifyIfThePOReferenceAlreadyExists(MOrder order) 
	{
		int count = DB.getSQLValue(trxName, "select count(*) from C_Order where trim(POReference) "
				+ "like ? and ad_client_id = ? and C_BPartner_ID = ? and c_bpartner_location_ID = ? and DocStatus in ('CO','DR','IP') and C_DocTypeTarget_ID = ? ",
				order.getPOReference(),order.getAD_Client_ID(),order.getC_BPartner_ID(),order.getC_BPartner_Location_ID(),order.getC_DocTypeTarget_ID());
		if(count>1)
			throw new AdempiereException("Sales Order for this PO Reference is already created");
	}

	private void updateShipmentDocuments(MOrder order) 
	{
		List<MDSRequiredShipDocuments> requiredDocuments = new Query(ctx, MDSRequiredShipDocuments.Table_Name, " C_Country_ID = ? and M_FreightCategory_ID = ? ", trxName)
															.setParameters(order.getC_BPartner_Location().getC_Location().getC_Country_ID(),order.getM_FreightCategory_ID())
															.list();
		for(MDSRequiredShipDocuments requiredDoc : requiredDocuments)
		{
			MDSRequiredDocForShipment doc = new MDSRequiredDocForShipment(ctx, 0, trxName);
			doc.setC_Order_ID(order.getC_Order_ID());
			doc.setDS_ShipmentDocuments_ID(requiredDoc.getDS_ShipmentDocuments_ID());
			doc.setDS_IsRequired(requiredDoc.isDS_IsRequired());
			doc.saveEx();
		}
	}

	private void updateDocuments(MDSDeliveryTrips trip) 
	{
		List<MDSRequiredShipDocuments> requiredDocuments = new Query(ctx, MDSRequiredShipDocuments.Table_Name, " C_Country_ID = ? and M_FreightCategory_ID = ? ", trxName)
													.setParameters(trip.getDS_ToCountry_ID(),trip.getM_FreightCategory_ID())
													.list();
		for(MDSRequiredShipDocuments requiredDoc : requiredDocuments)
		{
			MDSDocumentsForTrip doc = new MDSDocumentsForTrip(ctx, 0, trxName);
			doc.setDS_Delivery_Trips_ID(trip.getDS_Delivery_Trips_ID());
			doc.setDS_ShipmentDocuments_ID(requiredDoc.getDS_ShipmentDocuments_ID());
			doc.setDS_IsRequired(requiredDoc.isDS_IsRequired());
			doc.saveEx();
		}
	}

	private void updateBPartnerInTheRightFolderWithSequence(MBPartner bpartner) 
	{
		String bpgroupvalue = bpartner.getC_BP_Group().getValue();
		int parent_ID = DB.getSQLValue(trxName," select C_BPartner_ID From C_BPartner where value like ?  ",bpgroupvalue);
		int i = DB.executeUpdate("UPDATE AD_TreeNodeBP SET Parent_ID = "+parent_ID+" WHERE node_id="+bpartner.getC_BPartner_ID(), trxName);
		log.info("Updated entry "+i);
	}

	private void deleteSerialNumbers(PO po, int reversal) 
	{
		// Create a process info instance. This is a composite class containing
				// the parameters.
		ProcessInfo pi = new ProcessInfo("", 1000245, po.get_Table_ID(),
				reversal);
		MProcess pr = new Query(Env.getCtx(), X_AD_Process.Table_Name,"AD_Process_ID=?", po.get_TrxName()).setParameters(1000245).first();
		// Create an instance of the process I want to run
		
		ProcessCall processCall = null;
		boolean procSuccess = false;
		processCall = Core.getProcess(pr.getClassname());
		// Create process instance (mainly for logging/sync purpose)
		MPInstance mpi = new MPInstance(Env.getCtx(), pr.get_ID(),reversal);
		mpi.saveEx();

		// Connect the process to the process instance.
		pi.setAD_PInstance_ID(mpi.get_ID());
		procSuccess = processCall.startProcess(Env.getCtx(), pi, null);
		
		if (!procSuccess)
			log.info("not deleted");
		
	}

	private void generateSerialNumbers(PO po) 
	{
		// Create a process info instance. This is a composite class containing
		// the parameters.
		int AD_Process_ID = DB.getSQLValue(po.get_TrxName(), "Select AD_Process_ID From AD_Process Where AD_Process_UU='53ae0a6b-f7de-4176-877e-d81c47c8db0f'");
		
		ProcessInfo pi = new ProcessInfo("", AD_Process_ID, po.get_Table_ID(),
				po.get_ID());

		MProcess pr = new Query(Env.getCtx(), X_AD_Process.Table_Name,"AD_Process_ID=?", po.get_TrxName()).setParameters(AD_Process_ID).first();

		// Create an instance of the process I want to run
		ProcessCall processCall = null;
		boolean procSuccess = false;
		processCall = Core.getProcess(pr.getClassname());

		// Create process instance (mainly for logging/sync purpose)
		MPInstance mpi = new MPInstance(Env.getCtx(), pr.get_ID(),
				po.get_ID());
		mpi.saveEx();

		// Connect the process to the process instance.
		pi.setAD_PInstance_ID(mpi.get_ID());
		procSuccess = processCall.startProcess(Env.getCtx(), pi, null);

		if (!procSuccess)
			log.info("not generated");
	}

	@Override
	protected void initialize() 
	{
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MInOut.Table_Name);
		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MInOut.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE,MBPartnerLocation.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MOrder.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrder.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrder.Table_Name);
		
		
		registerTableEvent(IEventTopics.DOC_BEFORE_PREPARE, MMovement.Table_Name);
		
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MInvoice.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MDSDeliveryTrips.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MDSDeliveryTrips.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MDSDeliveryTrips.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MDSTripScheduleEstimations.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MDSISerialNoTrx.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MDSISerialNoTrx.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MOrderLine.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MOrderLine.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MCContactActivity.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MCContactActivity.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MUser.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MUser.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MBPartner.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MBPartner.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MDSPOSHeader.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MDSPOSItemDetails.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MDSPOSItemDetails.Table_Name);
	}


}
