package com.gaurav.dsi.event;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventManager;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MInvoice;
import org.compiere.model.MJournal;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.gaurav.dsi.model.MDSCouponSchedule;
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
public class CouponScheduleEventHandler extends AbstractEventHandler{

	@Override
	protected void doHandleEvent(Event event) {
		
		PO po = getPO(event);
		if(po instanceof MInvoice)
		{
			MInvoice invoice = (MInvoice)po;
			String trxName = invoice.get_TrxName();
			int[] coupons = DB.getIDsEx(trxName, "Select DS_CouponSchedule_ID from DS_CouponSchedule where Ref_Invoice_ID = ? ", invoice.getC_Invoice_ID());
			for(int coupon_ID : coupons)
			{
				MDSCouponSchedule schedule = new MDSCouponSchedule(Env.getCtx(),coupon_ID,trxName);
				schedule.set_ValueNoCheck("Ref_Invoice_ID", null) ;
				schedule.saveEx();
			}
		}
		if(po instanceof MJournal)
		{
			MJournal journal = (MJournal)po;
			String trxName = journal.get_TrxName();
			int[] coupons = DB.getIDsEx(trxName, "Select DS_CouponSchedule_ID from DS_CouponSchedule where GL_Journal_ID = ? ", journal.getGL_Journal_ID());
			for(int coupon_ID : coupons)
			{
				MDSCouponSchedule schedule = new MDSCouponSchedule(Env.getCtx(),coupon_ID,trxName);
				schedule.set_ValueNoCheck("GL_Journal_ID", null) ;
				schedule.saveEx();
			}
		}
	}

	@Override
	protected void initialize() {
		
		registerTableEvent(IEventTopics.DOC_AFTER_VOID,MInvoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REVERSECORRECT,MInvoice.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REVERSECORRECT,MJournal.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_VOID,MJournal.Table_Name);
	}

}
