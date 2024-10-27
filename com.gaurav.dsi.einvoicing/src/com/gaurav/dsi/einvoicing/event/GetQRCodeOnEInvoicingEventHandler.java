package com.gaurav.dsi.einvoicing.event;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventManager;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MClient;
import org.compiere.model.MInvoice;
import org.compiere.model.MOrgInfo;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.event.Event;


@Component(

		   reference = @Reference( 
		                 name = "IEventManager", bind = "bindEventManager", unbind="unbindEventManager", 
		                 policy = ReferencePolicy.STATIC, cardinality =ReferenceCardinality.MANDATORY, service = IEventManager.class)
		   )
public class GetQRCodeOnEInvoicingEventHandler extends AbstractEventHandler{

	@Override
	protected void doHandleEvent(Event event) {
		
		PO po = getPO(event);
		
		if(po instanceof MInvoice)
		{
			MInvoice inv = (MInvoice)po;
			if(inv.isSOTrx())
			{
				int AD_Org_ID = inv.getAD_Org_ID();
				MClient org = new MClient(Env.getCtx(),inv.getAD_Client_ID(),po.get_TrxName());
				MOrgInfo inf = MOrgInfo.get(AD_Org_ID);
				String sellerName = org.getName();
				String	vatRegistrationNumber = inf.getTaxID();
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
		}
		
	}

	@Override
	protected void initialize() {
		registerTableEvent(IEventTopics.DOC_AFTER_PREPARE, MInvoice.Table_Name);
		
	}

}
