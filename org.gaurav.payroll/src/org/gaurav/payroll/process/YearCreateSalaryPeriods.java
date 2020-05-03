package org.gaurav.payroll.process;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;

import org.adempiere.util.IProcessUI;
import org.compiere.model.MClient;
import org.compiere.model.MYear;
import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.gaurav.payroll.model.MGSHRSalaryMonths;

public class YearCreateSalaryPeriods extends SvrProcess{

	private int	p_C_Year_ID = 0;
	private Timestamp p_StartDate;
	private String p_DateFormat;
	private MYear p_year = null;
	/**
	 * 	Prepare
	 */
	protected void prepare ()
	{
		
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("StartDate"))
				p_StartDate = (Timestamp) para[i].getParameter();
			else if (name.equals("DateFormat"))
				p_DateFormat = (String) para[i].getParameter();
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}			
		p_C_Year_ID = getRecord_ID();
		p_year = new MYear(getCtx(), p_C_Year_ID, get_TrxName());
	}	//	prepare

	@Override
	protected String doIt() throws Exception {
	
		MYear year = new MYear (getCtx(), p_C_Year_ID, get_TrxName());
		if (p_C_Year_ID == 0 || year.get_ID() != p_C_Year_ID)
			throw new AdempiereUserError ("@NotFound@: @C_Year_ID@ - " + p_C_Year_ID);
		if (log.isLoggable(Level.INFO)) log.info(year.toString());
		
		if (createStdPeriods(null, p_StartDate, p_DateFormat))
			return "@OK@";
		
		return null;
	}

	public boolean createStdPeriods(Locale locale, Timestamp startDate, String dateFormat)
	{
		if (locale == null)
		{
			MClient client = MClient.get(getCtx());
			locale = client.getLocale();
		}
		
		if (locale == null && Language.getLoginLanguage() != null)
			locale = Language.getLoginLanguage().getLocale();
		if (locale == null)
			locale = Env.getLanguage(getCtx()).getLocale();
		//
		SimpleDateFormat formatter;
		if ( dateFormat == null || dateFormat.equals("") )
			dateFormat = "MMM-yy";
		formatter = new SimpleDateFormat(dateFormat, locale);
		
		//
		int year = p_year.getYearAsInt();
		GregorianCalendar cal = new GregorianCalendar(locale);
		if ( startDate != null )
		{
			cal.setTime(startDate);
			if ( cal.get(Calendar.YEAR) != year)     // specified start date takes precedence in setting year
				year = cal.get(Calendar.YEAR);
		
		}
		else 
		{
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		//
		IProcessUI processMonitor = Env.getProcessUI(getCtx());
		for (int month = 0; month < 12; month++)
		{
			
			Timestamp start = new Timestamp(cal.getTimeInMillis());
			String name = formatter.format(start);
			// get last day of same month
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_YEAR, -1);
			Timestamp end = new Timestamp(cal.getTimeInMillis());
			//
			MGSHRSalaryMonths salaryMonth = MGSHRSalaryMonths.findByCalendar(getCtx(), start, p_year.getC_Calendar_ID(), get_TrxName());
			if (salaryMonth == null)
			{
				salaryMonth = new MGSHRSalaryMonths (p_year, month+1, name, start, end);
				salaryMonth.setC_Calendar_ID(p_year.getC_Calendar_ID());
			}
			else
			{
				salaryMonth.setC_Year_ID(p_year.getC_Year_ID());
				salaryMonth.setPeriodNo(month+1);
				salaryMonth.setName(name);
				salaryMonth.setStartDate(start);
				salaryMonth.setEndDate(end);
			}
			if (processMonitor != null)
			{
				processMonitor.statusUpdate(salaryMonth.toString());
			}
			salaryMonth.saveEx(get_TrxName());	//	Creates Period Control
			// get first day of next month
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		
		return true;
		
	}	//	createStdPeriods
	
}
