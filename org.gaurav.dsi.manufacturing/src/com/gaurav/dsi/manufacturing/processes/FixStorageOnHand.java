package com.gaurav.dsi.manufacturing.processes;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class FixStorageOnHand extends SvrProcess {

	@Override
	protected void prepare() 
	{
		DB.executeUpdate("DELETE FROM DS_StorageReservation", get_TrxName());

	}

	@Override
	protected String doIt() throws Exception 
	{
		String sql = "Insert into DS_StorageReservation select * from M_StorageReservation";
		return null;
	}

}
