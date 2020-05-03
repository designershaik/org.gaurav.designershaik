package com.gaurav.dsi.purchase.callout;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MOrder;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CallOutVerifyPaymentAmt implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue) 
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Integer orderID=(Integer)mTab.getValue("C_Order_ID");
		Integer requestCurrency=(Integer)mTab.getValue("C_Currency_ID");
		BigDecimal requestAmt=(BigDecimal)value;
		BigDecimal paidAmount = Env.ZERO;
		BigDecimal totalPaidAmt;
		BigDecimal requestedAmtInPoCurrency=Env.ZERO;
		if(orderID==null || requestAmt==Env.ZERO)
		{
			return null;
		} 
		MOrder mo= new MOrder(ctx, orderID, null);
		String sql="select nvl(sum(currencyConvert(cp.PAYAMT,cp.C_CURRENCY_ID,co.C_CURRENCY_ID,sysdate,114,co.AD_CLIENT_ID,co.AD_ORG_ID)),0) as PaymentAmount, "
				+ " currencyConvert("+mTab.getValue("RequestAmt")+","+requestCurrency+","
						+ ""+mo.getC_Currency_ID()+",sysdate,114,"+mo.getAD_Client_ID()+","+mo.getAD_Org_ID()+") as RequestedAmountInPoCurrency,"
				+ "co.GRANDTOTAL,co.DOCUMENTNO from c_order co left outer join c_payment cp on co.C_ORDER_ID=cp.C_ORDER_ID "
				+ "and co.c_currency_id<>cp.c_currency_id where co.c_order_id="+orderID+" group by co.GRANDTOTAL,co.DOCUMENTNO";
		pstmt=DB.prepareStatement(sql, null);
		try 
		{
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				paidAmount=rs.getBigDecimal("PaymentAmount");
				requestedAmtInPoCurrency=rs.getBigDecimal("RequestedAmountInPoCurrency");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			
		}
		
		if(paidAmount.intValue()!=0)
				totalPaidAmt=mo.getGrandTotal().subtract(paidAmount).subtract(requestedAmtInPoCurrency);
		else
				totalPaidAmt=mo.getGrandTotal().subtract(requestedAmtInPoCurrency);
		
		if((totalPaidAmt.compareTo(Env.ZERO))==-1)
		{
				mTab.fireDataStatusEEvent("Request amount is greater than PO Amount.", "Total paid Amount is "+paidAmount, true);
		}		
		
		return null;
	}

}
