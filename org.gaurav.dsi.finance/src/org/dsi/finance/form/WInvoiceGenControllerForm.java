package org.dsi.finance.form;

import org.adempiere.webui.panel.ADForm;
import org.zkoss.zul.Window.Mode;

public class WInvoiceGenControllerForm extends ADForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WDSIInvoiceGen invoiceGen;

	public WInvoiceGenControllerForm(WDSIInvoiceGen invoicegencontroller) 
	{
		invoiceGen = invoicegencontroller;
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
			try {
				invoiceGen.zkInit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return ok;
	}

}
