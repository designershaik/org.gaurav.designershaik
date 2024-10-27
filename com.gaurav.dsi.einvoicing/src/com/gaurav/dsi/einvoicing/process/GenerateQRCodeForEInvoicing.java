package com.gaurav.dsi.einvoicing.process;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.compiere.model.MClient;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrgInfo;
import org.compiere.process.SvrProcess;

import com.gaurav.dsi.einvoicing.event.GetBase64Utils;

public class GenerateQRCodeForEInvoicing extends SvrProcess{

	
	@Override
	protected void prepare() 
	{
		
		
	}

	@Override
	protected String doIt() throws Exception
	{
		List<Integer> invoices = new ArrayList<>();
		if(getRecord_IDs()!=null)
			invoices = getRecord_IDs();
		else
			invoices.add(getRecord_ID());
		
		String sellerName = null;
        String vatRegistrationNumber = null;
        int AD_Org_ID = 0;
        int i = 0 ;
		for(Integer C_Invoice_ID : invoices)
		{
			i ++;
			MInvoice inv = new MInvoice(getCtx(),C_Invoice_ID,get_TrxName());
			
			if(AD_Org_ID==0)
			{
				AD_Org_ID = inv.getAD_Org_ID();
				MClient org = new MClient(getCtx(),inv.getAD_Client_ID(),get_TrxName());
				MOrgInfo inf = MOrgInfo.get(AD_Org_ID);
				sellerName = org.getName();
				vatRegistrationNumber = inf.getTaxID();
			}
			LocalDateTime localDateTime = inv.getDateAcct().toLocalDateTime();

	        // Define the format pattern
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	        // Convert LocalDateTime to formatted String
	        String formattedString = localDateTime.format(formatter);

	        
			String invoiceDate = formattedString;
			
			DecimalFormat df = new DecimalFormat("#.00");
	        
			String totalAmount =  df.format(inv.getTotalLines());
			String vatAmount = df.format(inv.getGrandTotal().subtract(inv.getTotalLines()));
			String invoiceData = String.format("1|%s|2|%s|3|%s|4|%s|5|%s",
                    sellerName, vatRegistrationNumber, invoiceDate, totalAmount, vatAmount); 
			String qrCode = GetBase64Utils.encodeToBase64(invoiceData);
			
			inv.set_ValueNoCheck("GS_EInvoicingRef_QrCode", qrCode);		
			inv.saveEx();
			
		}
		return "Total Generated Invoices: "+i ;
	}

	
}
