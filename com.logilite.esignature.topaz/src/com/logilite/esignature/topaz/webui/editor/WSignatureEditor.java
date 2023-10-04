package com.logilite.esignature.topaz.webui.editor;

import java.util.logging.Level;

import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WImageEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.compiere.model.GridField;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;

import com.logilite.esignature.topaz.webui.component.SignatureImgBox;

/**
 * Digital Signature Editor
 * 
 * @author <a href="mailto:sachin.bhimani89@gmail.com"> Sachin Bhimani </a>
 */
public class WSignatureEditor extends WEditor
{
	private static final String[]	LISTENER_EVENTS	= { Events.ON_CLICK };

	private static final CLogger	logger			= CLogger.getCLogger(WImageEditor.class);

	/** The Image Model */
	private MImage					m_mImage		= null;

	private boolean					m_mandatory;
	private boolean					readwrite;

	public WSignatureEditor(GridField gridField)
	{
		super(new SignatureImgBox(true), gridField);
		init();
	}

	@Override
	public SignatureImgBox getComponent()
	{
		return (SignatureImgBox) component;
	}

	private void init()
	{
		getComponent().setContent(null);
		getComponent().setMImage(null);
		getComponent().addEventListener(Events.ON_CHANGE, this);
	}

	@Override
	public String getDisplay()
	{
		return m_mImage.getName();
	}

	@Override
	public Object getValue()
	{
		if (m_mImage == null || m_mImage.get_ID() == 0)
			return null;
		return Integer.valueOf(m_mImage.get_ID());
	}

	@Override
	public boolean isMandatory()
	{
		return m_mandatory;
	}

	@Override
	public void setMandatory(boolean mandatory)
	{
		m_mandatory = mandatory;
	}

	@Override
	public boolean isReadWrite()
	{
		return readwrite;
	}

	@Override
	public void setReadWrite(boolean readWrite)
	{
		this.readwrite = readWrite;
		getComponent().setReadWrite(readWrite);
	}

	@Override
	public void setValue(Object value)
	{
		int newValue = 0;
		if (value instanceof Integer)
			newValue = ((Integer) value).intValue();
		if (newValue == 0)
		{
			m_mImage = null;
			getComponent().setContent(null);
			getComponent().setMImage(null);
			return;
		}
		// Get/Create Image
		if (m_mImage == null || newValue != m_mImage.get_ID())
			m_mImage = MImage.get(Env.getCtx(), newValue);
		//
		logger.fine(m_mImage.toString());
		AImage img = null;
		byte[] data = m_mImage.getData();
		if (data != null && data.length > 0)
		{
			try
			{
				img = new AImage(null, data);
			}
			catch (Exception e)
			{
				logger.log(Level.WARNING, e.getLocalizedMessage(), e);
			}
		}
		getComponent().setContent(img);
		getComponent().setMImage(m_mImage);

	}

	@Override
	public String[] getEvents()
	{
		return LISTENER_EVENTS;
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		if (Events.ON_CHANGE.equals(event.getName()))
		{
			int AD_Image_ID = getComponent().getAD_Image_ID();
			Object oldValue = getValue();
			Integer newValue = null;
			if (AD_Image_ID != 0)
				newValue = Integer.valueOf(AD_Image_ID);
			//
			m_mImage = null; // force reload
			setValue(newValue); // set explicitly
			//
			ValueChangeEvent vce = new ValueChangeEvent(this, gridField.getColumnName(), oldValue, newValue);
			fireValueChange(vce);
		}
	}
}
