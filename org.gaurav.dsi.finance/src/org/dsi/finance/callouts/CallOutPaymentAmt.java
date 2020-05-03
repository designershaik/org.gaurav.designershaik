package org.dsi.finance.callouts;

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

public class CallOutPaymentAmt implements IColumnCallout
{
	BigDecimal remainingAmt = Env.ZERO;
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab,
			GridField mField, Object value, Object oldValue)
	{
		PreparedStatement pstmt=null;
			ResultSet rs=null;
			Integer orderID=(Integer)mTab.getValue("C_Order_ID");
			BigDecimal paidAmount = Env.ZERO;
			
			if(orderID==null)
			{
				return "";
			} 
			
			String sql="select nvl(sum(currencyConvert(cp.PAYAMT,cp.C_CURRENCY_ID,co.C_CURRENCY_ID,sysdate,114,co.AD_CLIENT_ID,co.AD_ORG_ID)),0) as PaymentAmount, "
					+ " co.GRANDTOTAL,co.DOCUMENTNO from c_order co left outer join c_payment cp on co.C_ORDER_ID=cp.C_ORDER_ID "
					+ "and co.c_currency_id<>cp.c_currency_id where co.c_order_id="+orderID+" group by co.GRANDTOTAL,co.DOCUMENTNO";
			pstmt=DB.prepareStatement(sql, null);
			try 
			{
				rs=pstmt.executeQuery();
				if(rs.next())
				{
					paidAmount=rs.getBigDecimal("PaymentAmount");
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			MOrder mo= new MOrder(ctx, orderID, null);
			remainingAmt=mo.getGrandTotal().subtract(paidAmount);
			mTab.setValue("C_Currency_ID", mo.getC_Currency_ID());
			mTab.setValue("RequestAmt", remainingAmt);
					
			return null;
	}

	
}
