package com.logilite.esignature.topaz.webui.window;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Borderlayout;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Window;
import org.apache.commons.codec.binary.Base64;
import org.compiere.model.MImage;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.zkoss.image.AImage;
import org.zkoss.io.Files;
import org.zkoss.json.JSONArray;
import org.zkoss.json.JSONObject;
import org.zkoss.lang.Objects;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Center;
import org.zkoss.zul.Image;
import org.zkoss.zul.Script;
import org.zkoss.zul.South;

import com.logilite.esignature.topaz.util.Utils;

public class WSignatureDialog extends Window implements EventListener<Event>
{
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 6372165428638797270L;

	public final static String	ON_SIGNATURE_EVENT	= "onSignatureEvent";

	/** Logger */
	private CLogger				log					= CLogger.getCLogger(WSignatureDialog.class);

	private Borderlayout		mainLayout			= new Borderlayout();
	private Center				center;
	private ConfirmPanel		confirmPanel		= new ConfirmPanel(true, false, false, false, false, false);

	private MImage				m_mImage			= null;
	private Image				image				= new Image();

	private boolean				cancel				= false;

	public WSignatureDialog(MImage mImage)
	{
		super();

		m_mImage = mImage;
		init();

		// load data
		if (m_mImage == null)
			m_mImage = MImage.get(Env.getCtx(), 0);
		else
		{
			try
			{
				AImage aImage = new AImage(m_mImage.getName(), m_mImage.getData());
				image.setContent(aImage);
				image.setStyle("margin: 8px; padding: 1px;");
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, "load image", e);
			}
		}

		AEnv.showCenterScreen(this);
		if(mImage == null)
			cmd_new();
	} // WSignatureDialog

	/**
	 * Initialize
	 */
	void init()
	{
		this.setAttribute(Window.MODE_KEY, Window.MODE_HIGHLIGHTED);
		this.setTitle("Signature");
		this.setStyle("position: relative;");
		this.setSclass("popup-dialog");
		this.setBorder("normal");
		this.setWidth("485px");
		this.setHeight("345px");

		Script jsSignTopazPad = new Script();
		Script jsSignApp = new Script();
		try
		{
			URL url1 = Utils.getJSResource("signature_app.js");
			URL url2 = Utils.getJSResource("SignWebTablet.js");

			byte[] byte1 = Files.readAll(url1.openStream());
			byte[] byte2 = Files.readAll(url2.openStream());

			jsSignApp.setContent(new String(byte1));
			jsSignTopazPad.setContent(new String(byte2));
		}
		catch (IOException e)
		{
			log.log(Level.SEVERE, "Issue while reading JS file.", e);
			throw new AdempiereException("Issue while reading JS file.", e);
		}

		this.appendChild(jsSignApp);
		this.appendChild(jsSignTopazPad);

		mainLayout.setParent(this);
		mainLayout.setHflex("1");
		mainLayout.setVflex("1");

		center = new Center();
		center.setParent(mainLayout);
		center.setSclass("dialog-content");
		center.appendChild(image);
		center.setStyle("background-color: transparent; box-shadow: rgba(170, 235, 255, 0.5) 0px 0px 20px 6px inset; padding: 0px !important;");

		South south = new South();
		south.setParent(mainLayout);
		south.setSclass("dialog-footer");
		south.setStyle("background-color: transparent; border: none;");
		south.appendChild(confirmPanel);

		confirmPanel.addComponentsLeft(confirmPanel.createButton(ConfirmPanel.A_NEW));
		confirmPanel.addComponentsLeft(confirmPanel.createButton(ConfirmPanel.A_DELETE));
		confirmPanel.getButton(ConfirmPanel.A_NEW).setVisible(true);
		confirmPanel.getButton(ConfirmPanel.A_DELETE).setVisible(false);
		confirmPanel.getButton(ConfirmPanel.A_OK).setVisible(false);
		confirmPanel.addActionListener(Events.ON_CLICK, this);

		this.addEventListener(ON_SIGNATURE_EVENT, this);

	} // init

	public void onEvent(Event e) throws Exception
	{
		if (e.getTarget().getId() == ConfirmPanel.A_NEW)
		{
			cmd_new();
		}
		else if (ON_SIGNATURE_EVENT.equals(e.getName()))
		{
			cmd_save(e);
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_CANCEL))
		{
			cancel = true;
			detach();
		}
		else if (e.getTarget().getId().equals(ConfirmPanel.A_DELETE))
		{
			AImage img = null;
			image.setContent(img);
		}
	}

	/**
	 * Load Signature & display board
	 */
	private void cmd_new()
	{
		System.out.println("signatureApp.initSignaturePad('" + image.getUuid() + "', '" + center.getUuid() + "', '"
				+ this.getUuid() + "', '" + confirmPanel.getButton(ConfirmPanel.A_DELETE).getUuid() + "', '"
				+ confirmPanel.getButton(ConfirmPanel.A_OK).getUuid() + "', '"
				+ confirmPanel.getButton(ConfirmPanel.A_CANCEL).getUuid() + "')");
		Clients.evalJavaScript("signatureApp.initSignaturePad('" + image.getUuid() + "', '" + center.getUuid() + "', '"
				+ this.getUuid() + "', '" + confirmPanel.getButton(ConfirmPanel.A_DELETE).getUuid() + "', '"
				+ confirmPanel.getButton(ConfirmPanel.A_OK).getUuid() + "', '"
				+ confirmPanel.getButton(ConfirmPanel.A_CANCEL).getUuid() + "')");

		confirmPanel.getButton(ConfirmPanel.A_NEW).setVisible(false);
		confirmPanel.getButton(ConfirmPanel.A_DELETE).setVisible(true);
		confirmPanel.getButton(ConfirmPanel.A_OK).setVisible(true);
	} // cmd_new

	/**
	 * Save image data from event and if data is null then remove existing
	 * records if exists.
	 * 
	 * @param e - event from signature_app.js
	 */
	private void cmd_save(Event e)
	{
		String imgData = null;

		if (e.getData() instanceof JSONObject)
		{
			JSONObject data = (JSONObject) e.getData();
			if (data == null || data.size() < 1)
			{
				log.log(Level.SEVERE, "ILLEGAL_REQUEST_WRONG_DATA", e);
				throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] { Objects.toString(data), this });
			}

			JSONArray jArray = (JSONArray) data.get("data");
			imgData = (String) jArray.get(0);
		}

		AImage aimg = null;
		if (imgData != null)
		{
			try
			{
				String encodingPrefix = "base64,";
				int contentStartIndex = imgData.indexOf(encodingPrefix) + encodingPrefix.length();
				aimg = new AImage("Sign.png", Base64.decodeBase64((imgData.substring(contentStartIndex)).getBytes()));
			}
			catch (IOException ex)
			{
				log.log(Level.SEVERE, "wrong image data", ex);
			}
		}

		image.setContent(aimg);

		// Save info
		if (m_mImage == null)
			m_mImage = MImage.get(Env.getCtx(), 0);
		m_mImage.setName("Signature");
		m_mImage.setImageURL("Signature");
		if (image.getContent() != null)
			m_mImage.setBinaryData(image.getContent().getByteData());
		else
			m_mImage.setBinaryData(null);

		if (image.getContent() != null)
		{
			m_mImage.saveEx();
		}
		else if (m_mImage != null && m_mImage.getAD_Image_ID() > 0)
		{
			m_mImage.deleteEx(true);
			m_mImage = null;
		}

		detach();
	} // cmd_save

	/**
	 * @return true if dialog cancel by user
	 */
	public boolean isCancel()
	{
		return cancel;
	}

	/**
	 * Get Image ID
	 * 
	 * @return ID or 0
	 */
	public int getAD_Image_ID()
	{
		if (m_mImage != null)
			return m_mImage.getAD_Image_ID();
		return 0;
	} // getAD_Image_ID

}
