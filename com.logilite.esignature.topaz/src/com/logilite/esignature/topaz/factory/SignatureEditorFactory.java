package com.logilite.esignature.topaz.factory;

import org.adempiere.webui.editor.IEditorConfiguration;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.factory.IEditorFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

import com.logilite.esignature.topaz.util.Utils;
import com.logilite.esignature.topaz.webui.editor.WSignatureEditor;

/**
 * Editor factory for Signature
 * 
 * @author Sachin Bhimani
 */
public class SignatureEditorFactory implements IEditorFactory
{

	/**
	 * Register listener of Signature command instead of doing code change in
	 * AempiereWebUI.onCreate()
	 **/
	// static
	// {
	// Executions.getCurrent().getDesktop().addListener(new SignatureCommand());
	// }

	@Override
	public WEditor getEditor(GridTab gridTab, GridField gridField, boolean tableEditor)
	{
		if (Utils.getDisplayTypeSignature() > 0 && gridField != null
				&& gridField.getDisplayType() == Utils.getDisplayTypeSignature())
			return new WSignatureEditor(gridField);

		return null;
	}

	@Override
	public WEditor getEditor(GridTab gridTab, GridField gridField, boolean tableEditor,
			IEditorConfiguration editorConfiguration) {
		// TODO Auto-generated method stub
		return null;
	}
}
