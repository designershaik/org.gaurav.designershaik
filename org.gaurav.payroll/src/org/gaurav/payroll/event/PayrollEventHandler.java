package org.gaurav.payroll.event;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.adempiere.base.event.AbstractEventHandler;
import org.adempiere.base.event.IEventTopics;
import org.compiere.model.MInvoice;
import org.compiere.model.MInvoiceLine;
import org.compiere.model.MJournal;
import org.compiere.model.MJournalLine;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.TimeUtil;
import org.gaurav.payroll.model.MGSContractCalendar;
import org.gaurav.payroll.model.MGSHREmployee;
import org.gaurav.payroll.model.MGSHREmployeeAdvance;
import org.gaurav.payroll.model.MGSHREmployeeSalaryDetails;
import org.gaurav.payroll.model.MGSHRInstallments;
import org.gaurav.payroll.model.MHRContract;
import org.gaurav.payroll.process.CreateLoanPayment;
import org.osgi.service.event.Event;

public class PayrollEventHandler extends AbstractEventHandler
{
	Properties ctx = Env.getCtx();
	String trxName = null;
	@Override
	protected void doHandleEvent(Event event) 
	{
		
		PO po = getPO(event);
		trxName = po.get_TrxName();
		if(po instanceof MGSHREmployeeAdvance)
		{
			MGSHREmployeeAdvance advance = (MGSHREmployeeAdvance)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_NEW)) 	
			{
				advance.setGS_HR_Approval_ID(Env.getAD_User_ID(Env.getCtx()));
				setCurrentBalance(advance);	
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.PO_BEFORE_CHANGE) && po.is_ValueChanged(MGSHREmployeeAdvance.COLUMNNAME_GS_HR_Employee_ID))
			{
				setCurrentBalance(advance);	
			}
		}
		if(po instanceof MJournal)
		{
			MJournal gl = (MJournal)po;
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_COMPLETE)) 	
			{
				updateInstallmentLines(gl,gl.getDateAcct(),true);
			}
			if(event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_VOID) || 
					event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_REVERSEACCRUAL)||
					event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_REVERSECORRECT) ||
					event.getTopic().equalsIgnoreCase(IEventTopics.DOC_AFTER_REACTIVATE)) 	
			{
				updateInstallmentLines(gl,null,false);
			}
		}
		
		if(po instanceof MGSHRInstallments)
		{
			MGSHRInstallments inst = (MGSHRInstallments)po;
			MGSHREmployeeAdvance advance = new MGSHREmployeeAdvance(ctx, inst.getGS_HR_EmployeeAdvance_ID(), trxName);
			if(inst.is_ValueChanged("PaidAmt"))
			{
				BigDecimal totalPaid = DB.getSQLValueBD(trxName, " Select coalesce(sum(PaidAmt),0) "
						+ "From GS_HR_Installments Where GS_HR_EmployeeAdvance_ID = ? ", inst.getGS_HR_EmployeeAdvance_ID());	
				advance.setGS_HR_RepaidAmt(totalPaid);
				advance.saveEx();
			}
			if(inst.is_ValueChanged(MGSHRInstallments.COLUMNNAME_IsPaid))
			{
				BigDecimal paidInstamments = DB.getSQLValueBD(trxName, " Select count(*) "
						+ "From GS_HR_Installments Where GS_HR_EmployeeAdvance_ID = ? "
						+ "and IsPaid='Y' ", inst.getGS_HR_EmployeeAdvance_ID());	
				advance.setGS_HR_RemainingInstallments(advance.getGS_HR_Installments().subtract(paidInstamments));
				advance.saveEx();
			}
		}
		if(po instanceof MHRContract)
		{
			MHRContract contract = (MHRContract)po;
			Timestamp validFrom = contract.getValidFrom();
			Timestamp validTo = contract.getValidTo();
			int daysBetween = TimeUtil.getDaysBetween(validTo, validFrom);
			int remainder = daysBetween%365;
			List<MGSContractCalendar> calendars = contract.getCalendar();
			for(MGSContractCalendar calendar : calendars)
			{
				calendar.delete(true);
			}
			if(remainder>90 && remainder<=365)
			{
				MGSContractCalendar cal = new MGSContractCalendar(ctx, 0, trxName);
				cal.setStartDate(validFrom);
				cal.setEndDate(validTo);
				cal.setHR_Contract_ID(contract.getHR_Contract_ID());
				cal.setName(contract.getName()+" Contract for days: "+daysBetween);
				cal.saveEx();
				createStdPeriods(null, validFrom, "mm/YYYY");
			}
		}
		if(po instanceof MInvoice)
		{
			MInvoice invoice = (MInvoice)po;
			int GS_HR_Compensation_Master_ID = DB.getSQLValue(trxName, 
					"select GS_HR_Compensation_Master_ID from GS_HR_Compensation_Master "
					+ "where gs_hr_compensationtype ='ADV' and AD_Client_ID = ? ",invoice.getAD_Client_ID());
			MInvoiceLine[] lines = invoice.getLines();
			for(MInvoiceLine line : lines)
			{
				int GS_HR_Employee_ID = line.get_ValueAsInt("GS_HR_Employee_ID");
				if(GS_HR_Employee_ID>0)
				{
					MGSHREmployeeAdvance advance = new MGSHREmployeeAdvance(ctx, 0, trxName);
					advance.setGS_HR_Approval_ID(invoice.getUpdatedBy());
					advance.setGS_HR_Compensation_Master_ID(GS_HR_Compensation_Master_ID);
					advance.setGS_HR_DateApplication(invoice.getDateAcct());
					advance.setGS_HR_Employee_ID(GS_HR_Employee_ID);
					advance.setGS_HR_Installments(Env.ONE);
					advance.setGS_HR_LoanAmt(line.getLineTotalAmt());
					advance.setGS_HR_Reason(line.getDescription());
					advance.setGS_HR_RemainingInstallments(Env.ONE);
					advance.setGS_HR_RepaidAmt(Env.ZERO);
					advance.setStartDate(invoice.getDateAcct());
					advance.setDS_HR_ApprovedAmt(line.getLineTotalAmt());
					advance.setPayDate(invoice.getDateAcct());
					advance.setProcessed(true);
					advance.setExpectedCloseDate(invoice.getDateAcct());
					if(advance.save())
					{
						CreateLoanPayment payment = new CreateLoanPayment();
						payment.createInstallments(advance.getGS_HR_EmployeeAdvance_ID(),trxName);
					}
					line.set_ValueNoCheck("GS_HR_EmployeeAdvance_ID", advance.getGS_HR_EmployeeAdvance_ID());
					line.saveEx();
				}
			}
			
		}
	}

	public boolean createStdPeriods(Locale locale, Timestamp startDate, String dateFormat)
	{
//		if (locale == null)
//		{
//			MClient client = MClient.get(ctx);
//			locale = client.getLocale();
//		}
//		
//		if (locale == null && Language.getLoginLanguage() != null)
//			locale = Language.getLoginLanguage().getLocale();
//		if (locale == null)
//			locale = Env.getLanguage(ctx).getLocale();
//		//
//		SimpleDateFormat formatter;
//		if ( dateFormat == null || dateFormat.equals("") )
//			dateFormat = "MMM-yy";
//		formatter = new SimpleDateFormat(dateFormat, locale);
//		
//		//
//		int year = getYearAsInt();
//		GregorianCalendar cal = new GregorianCalendar(locale);
//		if ( startDate != null )
//		{
//			cal.setTime(startDate);
//			if ( cal.get(Calendar.YEAR) != year)     // specified start date takes precedence in setting year
//				year = cal.get(Calendar.YEAR);
//		
//		}
//		else 
//		{
//			cal.set(Calendar.YEAR, year);
//			cal.set(Calendar.MONTH, 0);
//			cal.set(Calendar.DAY_OF_MONTH, 1);
//		}
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//
//		//
//		IProcessUI processMonitor = Env.getProcessUI(getCtx());
//		for (int month = 0; month < 12; month++)
//		{
//			
//			Timestamp start = new Timestamp(cal.getTimeInMillis());
//			String name = formatter.format(start);
//			// get last day of same month
//			cal.add(Calendar.MONTH, 1);
//			cal.add(Calendar.DAY_OF_YEAR, -1);
//			Timestamp end = new Timestamp(cal.getTimeInMillis());
//			//
//			MPeriod period = MPeriod.findByCalendar(getCtx(), start, getC_Calendar_ID(), get_TrxName());
//			if (period == null)
//			{
//				period = new MPeriod (this, month+1, name, start, end);
//			}
//			else
//			{
//				period.setC_Year_ID(this.getC_Year_ID());
//				period.setPeriodNo(month+1);
//				period.setName(name);
//				period.setStartDate(start);
//				period.setEndDate(end);
//			}
//			if (processMonitor != null)
//			{
//				processMonitor.statusUpdate(period.toString());
//			}
//			period.saveEx(get_TrxName());	//	Creates Period Control
//			// get first day of next month
//			cal.add(Calendar.DAY_OF_YEAR, 1);
//		}
//		
		return true;
		
	}	//	createStdPeriods
	
	private void updateInstallmentLines(MJournal gl, Timestamp payDate, boolean isPaid) 
	{
		MJournalLine[] lines = gl.getLines(true);
		for(MJournalLine line : lines )
		{
			int GS_HR_Installments_ID = line.get_ValueAsInt("GS_HR_Installments_ID");
			if(line.getAmtSourceCr().compareTo(Env.ZERO)>0 && GS_HR_Installments_ID>0)
			{
				BigDecimal paidAmt = isPaid ? line.getAmtAcctCr():Env.ZERO;
				MGSHRInstallments installment = new MGSHRInstallments(ctx, GS_HR_Installments_ID, trxName);
				installment.setIsPaid(isPaid);
				installment.setDate1(payDate);
				installment.set_ValueNoCheck("PaidAmt",paidAmt);
 				installment.saveEx();
			}
		}
		
	}

	private void setCurrentBalance(MGSHREmployeeAdvance advance) 
	{
		MGSHREmployee employee = new MGSHREmployee(ctx, advance.getGS_HR_Employee_ID(), trxName);
		BigDecimal loanAmt = employee.getLoanAmt(advance.getStartDate(),advance.getGS_HR_Compensation_Master_ID());
		advance.setGS_ExistingLoanAmt(loanAmt);
	}

	@Override
	protected void initialize() {		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MGSHREmployeeAdvance.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MGSHREmployeeAdvance.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MGSHREmployeeAdvance.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MGSHREmployeeAdvance.Table_Name);
		
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MJournal.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_VOID, MJournal.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REVERSEACCRUAL, MJournal.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REVERSECORRECT, MJournal.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REACTIVATE, MJournal.Table_Name);
		registerTableEvent(IEventTopics.DOC_AFTER_REACTIVATE, MJournal.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MGSHRInstallments.Table_Name);
		
		registerTableEvent(IEventTopics.PO_BEFORE_NEW, MHRContract.Table_Name);
		registerTableEvent(IEventTopics.PO_BEFORE_CHANGE, MHRContract.Table_Name);
		
		registerTableEvent(IEventTopics.PO_AFTER_CHANGE, MGSHREmployeeSalaryDetails.Table_Name);
		registerTableEvent(IEventTopics.PO_AFTER_NEW, MGSHREmployeeSalaryDetails.Table_Name);
		
		registerTableEvent(IEventTopics.DOC_AFTER_COMPLETE, MInvoice.Table_Name);
	}
}
