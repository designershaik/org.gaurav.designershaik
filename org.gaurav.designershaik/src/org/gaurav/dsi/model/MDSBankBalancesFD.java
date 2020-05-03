package org.gaurav.dsi.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.gaurav.dsi.model.X_DS_BankBalancesFD;

public class MDSBankBalancesFD extends X_DS_BankBalancesFD {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDSBankBalancesFD(Properties ctx, int DS_BankBalancesFD_ID, String trxName) {
		super(ctx, DS_BankBalancesFD_ID, trxName);
		
	}

	public MDSBankBalancesFD(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		
	}

}
