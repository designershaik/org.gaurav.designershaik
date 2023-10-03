package org.gaurav.dsi.attendance.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.gaurav.dsi.attendance.process.AttendanceLogSheet;

public class DSIAttendanceProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		
		if(className.equalsIgnoreCase(AttendanceLogSheet.class.getName()))
		{
			System.out.println("Sachin - Testing this factory...");
			return  new AttendanceLogSheet();
		}
		return null;
	}


}
