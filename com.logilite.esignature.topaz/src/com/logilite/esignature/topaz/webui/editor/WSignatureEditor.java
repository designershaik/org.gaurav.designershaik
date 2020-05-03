package com.logilite.esignature.topaz.webui.editor;

import java.util.logging.Level;

import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WImageEditor;
import org.adempiere.webui.event.DialogEvents;
import org.adempiere.webui.event.ValueChangeEvent;
import org.compiere.model.GridField;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Image;

import com.logilite.esignature.topaz.webui.window.WSignatureDialog;

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
		super(new Image(), gridField);
		init();
	}

	@Override
	public Image getComponent()
	{
		return (Image) component;
	}

	private void init()
	{
		AImage img = null;
		getComponent().setContent(img);
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
		return new Integer(m_mImage.get_ID());
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
		if (readWrite)
			getComponent().setStyle("cursor: pointer; border: 1px solid;");
		else
			getComponent().setStyle("cursor: default; border: none;");
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
			AImage img = null;
			getComponent().setContent(img);
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

	}

	@Override
	public String[] getEvents()
	{
		return LISTENER_EVENTS;
	}

	@Override
	public void onEvent(Event event) throws Exception
	{
		if (Events.ON_CLICK.equals(event.getName()))
		{
			final WSignatureDialog sign = new WSignatureDialog(m_mImage);
			sign.addEventListener(DialogEvents.ON_WINDOW_CLOSE, new EventListener<Event>() {

				@Override
				public void onEvent(Event event) throws Exception
				{
					if (!sign.isCancel())
					{
						int AD_Image_ID = sign.getAD_Image_ID();
						Object oldValue = getValue();
						Integer newValue = null;
						if (AD_Image_ID != 0)
							newValue = new Integer(AD_Image_ID);
						//
						m_mImage = null; // force reload
						setValue(newValue); // set explicitly
						//
						ValueChangeEvent vce = new ValueChangeEvent(this, gridField.getColumnName(), oldValue, newValue);
						fireValueChange(vce);
					}
					
				}
			});
		}
	}
}
