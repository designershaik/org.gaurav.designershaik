package org.dsi.crm.processes;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MRequest;
import org.compiere.model.Query;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.gaurav.dsi.model.MDSDeliveryTrips;
import org.gaurav.dsi.model.MDSProductRequest;
import org.gaurav.dsi.model.MDSTripConfiguration;
import org.gaurav.dsi.model.MDSTripSchdCashReqstAmt;
import org.gaurav.dsi.model.MDSTripScheduleEstimations;

public class CreateCashRequestDraftTripSettlement extends SvrProcess {

	int DS_Delivery_Trips_ID = 0 ;
	int p_AD_User_ID = 0;
	int p_C_Activity_ID = 0;
	int p_User1_ID = 0;
	int p_User2_ID = 0;
	MDSDeliveryTrips trip = null;
	MDSTripConfiguration configuration = null;
	int DS_Request_SubGroup_ID = 0 ;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AD_User_ID"))
				p_AD_User_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("C_Activity_ID"))
				p_C_Activity_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("User1_ID"))
				p_User1_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("User2_ID"))
				p_User2_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		DS_Delivery_Trips_ID = getRecord_ID();
		trip = new MDSDeliveryTrips(getCtx(), DS_Delivery_Trips_ID, get_TrxName());
	}

	@Override
	protected String doIt() throws Exception 
	{
		verifyIfMandatoryExpensesAreEntered();
		int DS_Trip_Configuration_ID = DB.getSQLValue(get_TrxName(), "Select DS_Trip_Configuration_ID From DS_Trip_Configuration Where AD_Client_ID = ? ",getAD_Client_ID());
		
		int existEntriesOfCustomerVisits = DB.getSQLValue(get_TrxName(), "Select count(*) from DS_CustomerVisits "
				+ "Where DS_Delivery_Trips_ID = ? ",DS_Delivery_Trips_ID);
		
		configuration = new MDSTripConfiguration(getCtx(), DS_Trip_Configuration_ID, get_TrxName());
		
		DS_Request_SubGroup_ID = DB.getSQLValue(null, "Select DS_Request_SubGroup_ID From DS_Request_SubGroup WHERE R_Group_ID = ? ", configuration.getR_Group_ID());
		
		if(existEntriesOfCustomerVisits==0)
			throw new AdempiereException("Please enter customer details before proceeding with the cash request");
		
		
		String budgetSummary = trip.getDescription().concat("\n").concat(getExpenseToString());
		
		if(trip.getDS_TripSettlementRequest_ID()==0)
		{
			MRequest tripSettlement = createRequest(configuration.getDS_TripSettlement_Type_ID(),budgetSummary,Env.ZERO,trip.getC_Currency_ID(),getAD_User_ID());
			
			trip.setDS_TripSettlementRequest_ID(tripSettlement.getR_Request_ID());
			trip.saveEx();
			
			List<MDSTripScheduleEstimations> estimations = new Query(getCtx(), 
															MDSTripScheduleEstimations.Table_Name, 
															" DS_Delivery_Trips_ID = ? ", get_TrxName())
															.setParameters(DS_Delivery_Trips_ID)
															.list();
			for(MDSTripScheduleEstimations estimate :estimations)
			{
				createTripSettlementDetails(estimate.getDS_Budget(), tripSettlement, estimate.getDS_Trip_ExpenseType().getC_Charge_ID()
						, estimate.getDS_Trip_ExpenseType().getName(),estimate.getC_Currency_ID(),estimate.get_ValueAsString("PaymentRule"));
			}
			
		}
		if(trip.getR_Request_ID()==0)
		{
			List<MDSTripSchdCashReqstAmt> cashRequests = new Query(getCtx(), MDSTripSchdCashReqstAmt.Table_Name, " DS_Delivery_Trips_ID = ? ", get_TrxName()).setParameters(DS_Delivery_Trips_ID).list();
			for(MDSTripSchdCashReqstAmt cash : cashRequests)
			{
				if(cash.getDS_CashRequest_Amt().compareTo(Env.ZERO)>0)
				{
					MRequest cashRequest = createRequest(configuration.getDS_CashRequest_Type_ID(),budgetSummary,cash.getDS_CashRequest_Amt(),cash.getC_Currency_ID(),p_AD_User_ID);
					cash.setR_Request_ID(cashRequest.getR_Request_ID());
					cash.save();
				}
			}
		}
		addLog(Msg.getMsg(getCtx(), "DS_RequestSubmittedForApproval"));
		return "@Submitted@";
	}

	private String getExpenseToString() 
	{
		List<MDSTripScheduleEstimations> estimations = new Query(getCtx(), 
				MDSTripScheduleEstimations.Table_Name, 
				" DS_Delivery_Trips_ID = ? ", get_TrxName())
				.setParameters(DS_Delivery_Trips_ID)
				.list();
		String expenseString = "" ;
		for(MDSTripScheduleEstimations estimate :estimations)
		{
			expenseString = expenseString.isEmpty()? estimate.getDS_Trip_ExpenseType().getName().
													concat(" : "+estimate.getDS_Budget().toString()).
													concat(" " +estimate.getC_Currency().getISO_Code()) : 
														expenseString.concat("\n").concat(estimate.getDS_Trip_ExpenseType().getName().
													concat(" : "+estimate.getDS_Budget().toString()).
													concat(" "+estimate.getC_Currency().getISO_Code()));
		}
		return expenseString;
	}

	private void verifyIfMandatoryExpensesAreEntered() 
	{
		List<MDSTripScheduleEstimations> estimations = new Query(getCtx(), MDSTripScheduleEstimations.Table_Name, " DS_Delivery_Trips_ID=? ", get_TrxName())
															.setParameters(trip.getDS_Delivery_Trips_ID())
															.list();
		for(MDSTripScheduleEstimations estimate : estimations)
		{
			if(estimate.getDS_Trip_ExpenseType().isMandatory() && estimate.getDS_Budget().intValue()==0)
				throw new AdempiereException(Msg.getMsg(Env.getCtx(), "DS_BudgetAmtIsMandatory"));
		}
			
	}

	private MRequest createRequest(int R_RequestType_ID, String Summary,BigDecimal totalCashRequestAmt,int C_Currency_ID,int AD_User_ID) 
	{
		MRequest request = new MRequest(getCtx(), getAD_User_ID(), R_RequestType_ID, Summary, false, get_TrxName());
		int C_BPartner_Location_ID = DB.getSQLValue(get_TrxName(), "Select C_BPartner_Location_ID From C_BPartner_Location Where C_BPartner_ID = ? ", trip.getC_BPartner_ID());
		request.setR_Group_ID(configuration.getR_Group_ID());
		request.set_ValueOfColumn("DS_Request_SubGroup_ID", DS_Request_SubGroup_ID);
		request.setC_BPartner_ID(trip.getC_BPartner_ID());
		request.setRequestAmt(totalCashRequestAmt);
		request.setSalesRep_ID(AD_User_ID);
		request.setSummary(Summary);
		request.setAD_Table_ID(trip.get_Table_ID());
		request.setRecord_ID(DS_Delivery_Trips_ID);
		request.set_ValueOfColumn("C_BPartner_Location_ID", C_BPartner_Location_ID);
		request.set_ValueOfColumn("DS_CanApprove", "Y");
		request.set_ValueOfColumn("C_Currency_ID",C_Currency_ID);
		request.set_ValueOfColumn("DS_Delivery_Trips_ID", trip.getDS_Delivery_Trips_ID());
		request.saveEx();
		
		return request;
	}

	private void createTripSettlementDetails(BigDecimal budgetAmt ,MRequest tripSettlement,int C_Charge_ID, String productDescription, int C_Currency_ID, String paymentRule) 
	{
		MDSProductRequest product = new MDSProductRequest(getCtx(), 0, get_TrxName());
		product.setDS_Budget(budgetAmt);
		product.setProductDescription(productDescription);
		product.setC_Charge_ID(C_Charge_ID);
		product.setR_Request_ID(tripSettlement.getR_Request_ID());
		product.setC_Activity_ID(p_C_Activity_ID);
		product.setC_Currency_ID(C_Currency_ID);
		product.setUser1_ID(p_User1_ID);
		product.setUser2_ID(p_User2_ID);
		product.set_ValueNoCheck("PaymentRule", paymentRule);
		product.save();
	}
}
