package org.gaurav.alerts.model;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.TimeUtil;

public class MGSDocumentForAlerts extends X_GS_DocumentForAlerts {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MGSDocumentForAlerts(Properties ctx, int GS_DocumentForAlerts_ID, String trxName) {
		super(ctx, GS_DocumentForAlerts_ID, trxName);
		
	}

	public MGSDocumentForAlerts(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		Timestamp today = MGSDocReminderSchedule.getToday();
		if(getHR_Doc_ExpiryDate().before(TimeUtil.addDays(today, 1)) || getHR_Doc_ExpiryDate().equals(TimeUtil.addDays(today, 1)))
			throw new AdempiereException("Expiry date is wrong. It has to be atleast Today+1");
		
		if(newRecord || is_ValueChanged(COLUMNNAME_HR_Documents_ID))
		{
			int i = DB.executeUpdateEx("Delete from GS_Documents_Recipients where GS_DocumentForAlerts_ID = ? ",new Object[] {getGS_DocumentForAlerts_ID()} ,get_TrxName());
			log.info("total deleted recepients: "+i);
			List<MGSDocBaseTypeRecipients> docBaseRecords = new Query(getCtx(), MGSDocBaseTypeRecipients.Table_Name, " GS_HR_DocumentBaseType_ID="
															+ "(SELECT GS_HR_DocumentBaseType_ID From HR_Documents Where HR_Documents_ID =?) ", get_TrxName())
															.setParameters(getHR_Documents_ID())
															.list();
			for(MGSDocBaseTypeRecipients record : docBaseRecords)
			{
				MGSDocumentsRecipients rec = new MGSDocumentsRecipients(getCtx(), 0, get_TrxName());
				rec.setAD_User_ID(record.getAD_User_ID());
				rec.setGS_DocumentForAlerts_ID(getGS_DocumentForAlerts_ID());
				rec.saveEx();
			}
			
			updateAlertSchedule();
		}
		if(is_ValueChanged(COLUMNNAME_HR_Doc_ExpiryDate))
		{
			updateAlertSchedule();
		}
		
		
		return true;
	}

	private void updateAlertSchedule()
	{
		int i = DB.executeUpdateEx("Delete from GS_DocReminder_Schedule where GS_DocumentForAlerts_ID = ? ",new Object[] {getGS_DocumentForAlerts_ID()} ,get_TrxName());
		
		log.info("total deleted recepients: "+i);
		List<MGSDocBaseTypeSchedule> baseTypeSchedule = new Query(getCtx(), MGSDocBaseTypeSchedule.Table_Name, " GS_HR_DocumentBaseType_ID="
				+ "(SELECT GS_HR_DocumentBaseType_ID From HR_Documents Where HR_Documents_ID =?) ", get_TrxName())
				.setParameters(getHR_Documents_ID())
				.list();
		
		for(MGSDocBaseTypeSchedule record : baseTypeSchedule)
		{
			MGSDocReminderSchedule alert = new MGSDocReminderSchedule(getCtx(), 0, get_TrxName());
			alert.setDaysDue(record.getDaysDue());
			alert.setGS_DocumentForAlerts_ID(getGS_DocumentForAlerts_ID());
			alert.saveEx();
		}
		
	}
}
