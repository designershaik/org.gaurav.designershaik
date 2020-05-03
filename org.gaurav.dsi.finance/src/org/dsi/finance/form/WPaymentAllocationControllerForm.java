package org.dsi.finance.form;

import org.adempiere.webui.panel.ADForm;

public class WPaymentAllocationControllerForm extends ADForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8130425936580657028L;
	
	private WPaymentAllocationController payallocation;

	public WPaymentAllocationControllerForm(WPaymentAllocationController payallocationController) 
	{
		payallocation = payallocationController;
	}

	
	
	@Override
	protected void initForm() 
	{
		
		
	}

	@Override
	public Mode getWindowMode() {
		return Mode.HIGHLIGHTED;
	}
	
	@Override
	public boolean setVisible(boolean visible) {
		 boolean ok = super.setVisible(visible);
		 if (visible && getProcessInfo() != null)
				payallocation.initForm();
		 
		 return ok;
	}
}
