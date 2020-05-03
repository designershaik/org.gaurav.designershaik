package org.gaurav.alerts.factories;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.gaurav.alerts.model.MGSDocBaseTypeRecipients;
import org.gaurav.alerts.model.MGSDocBaseTypeSchedule;
import org.gaurav.alerts.model.MGSDocReminderSchedule;
import org.gaurav.alerts.model.MGSDocumentForAlerts;
import org.gaurav.alerts.model.MGSDocumentsRecipients;
import org.gaurav.payroll.model.MGSHRDocumentBaseType;

public class DocumentAlertsModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		
		if(tableName.equalsIgnoreCase(MGSDocBaseTypeRecipients.Table_Name))
			return MGSDocBaseTypeRecipients.class;
		
		if(tableName.equalsIgnoreCase(MGSDocBaseTypeSchedule.Table_Name))
			return MGSDocBaseTypeSchedule.class;
		
		if(tableName.equalsIgnoreCase(MGSDocumentForAlerts.Table_Name))
			return MGSDocumentForAlerts.class;
		
		if(tableName.equalsIgnoreCase(MGSDocReminderSchedule.Table_Name))
			return MGSDocReminderSchedule.class;
		
		if(tableName.equalsIgnoreCase(MGSDocumentsRecipients.Table_Name))
			return MGSDocumentsRecipients.class;
		
		if(tableName.equalsIgnoreCase(MGSHRDocumentBaseType.Table_Name))
			return MGSHRDocumentBaseType.class;
		
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		
		if(tableName.equalsIgnoreCase(MGSDocBaseTypeRecipients.Table_Name))
			return new MGSDocBaseTypeRecipients(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocBaseTypeSchedule.Table_Name))
			return new MGSDocBaseTypeSchedule(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocumentForAlerts.Table_Name))
			return new MGSDocumentForAlerts(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocReminderSchedule.Table_Name))
			return new MGSDocReminderSchedule(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocumentsRecipients.Table_Name))
			return new MGSDocumentsRecipients(Env.getCtx(), Record_ID, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRDocumentBaseType.Table_Name))
			return new MGSHRDocumentBaseType(Env.getCtx(), Record_ID, trxName);
		
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		
		if(tableName.equalsIgnoreCase(MGSDocBaseTypeRecipients.Table_Name))
			return new MGSDocBaseTypeRecipients(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocBaseTypeSchedule.Table_Name))
			return new MGSDocBaseTypeSchedule(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocumentForAlerts.Table_Name))
			return new MGSDocumentForAlerts(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRDocumentBaseType.Table_Name))
			return new MGSHRDocumentBaseType(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocReminderSchedule.Table_Name))
			return new MGSDocReminderSchedule(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSDocumentsRecipients.Table_Name))
			return new MGSDocumentsRecipients(Env.getCtx(), rs, trxName);
		
		if(tableName.equalsIgnoreCase(MGSHRDocumentBaseType.Table_Name))
			return new MGSHRDocumentBaseType(Env.getCtx(), rs, trxName);
			
		return null;
	}


}
