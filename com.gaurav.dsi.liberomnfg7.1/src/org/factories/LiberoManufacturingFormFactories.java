package org.factories;

import java.util.logging.Level;

import org.adempiere.webui.factory.IFormFactory;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.IFormController;
import org.compiere.util.CLogger;
import org.libero.form.WMRPDetailed;
import org.libero.form.WOrderReceiptIssue;
import org.libero.form.WTreeBOM;

public class LiberoManufacturingFormFactories implements IFormFactory{

	@SuppressWarnings("deprecation")
	@Override
	public ADForm newFormInstance(String formName) 
	{
		CLogger log = CLogger.getCLogger(LiberoManufacturingFormFactories.class);
		
		if(formName.startsWith(WOrderReceiptIssue.class.getName()) 
				|| formName.startsWith(WTreeBOM.class.getName())
				|| formName.startsWith(WMRPDetailed.class.getName()))
		{
			Object form = null;
			Class<?> clazz = null;
			ClassLoader loader = getClass().getClassLoader();
			try 
			{
				clazz = loader.loadClass(formName);
			}
			catch (Exception e)
			{
				log.log(Level.FINE, "Form Class Initiate Failed", e);
			}
			if (clazz != null)
			{
				try
				{
					form = clazz.newInstance();
				}
				catch (Exception e)
				{
					log.log(Level.FINE, "Form Class Initiate Failed", e);
				}
			}
			if (form != null)
			{
				if (form instanceof ADForm)
				{
					return (ADForm) form;
				}
				else if (form instanceof IFormController)
				{
					IFormController controller = (IFormController) form;
					ADForm adForm = controller.getForm();
					adForm.setICustomForm(controller);
					return adForm;
				}
			}
		}
		
		return null;
	}

}
